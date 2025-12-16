package com.example.scratch.ui.scratchcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Manages scratch card state and user interactions
class ScratchCardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScratchCardUiState())
    val uiState: StateFlow<ScratchCardUiState> = _uiState.asStateFlow()

    // Threshold to reveal the reward (70% scratched)
    private val revealThreshold = 0.70f

    fun updateScratchProgress(progress: Float) {
        _uiState.update { currentState ->
            val newProgress = (currentState.scratchProgress + progress).coerceIn(0f, 1f)
            currentState.copy(
                scratchProgress = newProgress,
                isScratched = newProgress >= revealThreshold
            )
        }
    }

    fun showTermsAndConditions() {
        _uiState.update { it.copy(showTermsSheet = true) }
    }

    fun hideTermsAndConditions() {
        _uiState.update { it.copy(showTermsSheet = false) }
    }

    fun dismissCard() {
        _uiState.update { it.copy(isCardVisible = false) }
    }

    fun resetCard() {
        _uiState.update { ScratchCardUiState() }
    }
}

