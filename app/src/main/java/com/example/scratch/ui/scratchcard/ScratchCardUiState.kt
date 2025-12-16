package com.example.scratch.ui.scratchcard

import com.example.scratch.data.model.ScratchReward

// Holds the current state of scratch card UI
data class ScratchCardUiState(
    val isCardVisible: Boolean = true,
    val isScratched: Boolean = false,
    val scratchProgress: Float = 0f,
    val showTermsSheet: Boolean = false,
    val reward: ScratchReward = ScratchReward()
)

