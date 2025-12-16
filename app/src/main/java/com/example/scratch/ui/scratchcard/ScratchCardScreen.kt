package com.example.scratch.ui.scratchcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scratch.ui.scratchcard.components.RevealedContent
import com.example.scratch.ui.scratchcard.components.ScratchCanvas
import com.example.scratch.ui.scratchcard.components.TermsAndConditionsSheet
import com.example.scratch.ui.theme.CardClipPurple
import com.example.scratch.ui.theme.ClaimButtonBlue
import com.example.scratch.ui.theme.OverlayBackground
import com.example.scratch.ui.theme.ScratchCardBackground

// Main scratch card dialog overlay
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchCardScreen(
    viewModel: ScratchCardViewModel,
    onClaimOffer: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (!uiState.isCardVisible) return

    // Fullscreen overlay
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OverlayBackground)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { /* Consume clicks on overlay */ },
        contentAlignment = Alignment.Center
    ) {
        // Card container with clips on sides
        Box(contentAlignment = Alignment.Center) {
            // Left clip decoration
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(50.dp)
                    .offset(x = (-10).dp)
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp))
                    .background(CardClipPurple)
            )

            // Right clip decoration
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(50.dp)
                    .offset(x = 10.dp)
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(topStart = 25.dp, bottomStart = 25.dp))
                    .background(CardClipPurple)
            )

            // Main scratch card
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Close button
                IconButton(
                    onClick = {
                        viewModel.dismissCard()
                        onDismiss()
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(40.dp)
                        .background(Color.LightGray.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.DarkGray
                    )
                }

                // Scratch card body
                Box(
                    modifier = Modifier
                        .width(280.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(ScratchCardBackground),
                    contentAlignment = Alignment.Center
                ) {
                    // Reward content (always present, revealed by scratching)
                    RevealedContent(
                        reward = uiState.reward
                    )

                    // Scratch overlay (disappears after threshold)
                    if (!uiState.isScratched) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp))
                                .graphicsLayer { alpha = 0.99f }
                        ) {
                            ScratchCanvas(
                                onScratchProgress = { progress ->
                                    viewModel.updateScratchProgress(progress)
                                }
                            )
                        }
                    }
                }

                // Show buttons only after card is scratched
                if (uiState.isScratched) {
                    // Claim button - outside the scratch card
                    ClaimButton(
                        onClick = { onClaimOffer(uiState.reward.claimUrl) }
                    )

                    // Terms and conditions link - outside the scratch card
                    TermsAndConditionsLink(
                        onClick = { viewModel.showTermsAndConditions() }
                    )
                }
            }
        }

    }

    // Terms and Conditions bottom sheet - moved outside the Box to prevent overlay issues
    if (uiState.showTermsSheet) {
        ModalBottomSheet(
            dragHandle = {},
            onDismissRequest = { viewModel.hideTermsAndConditions() },
            sheetState = sheetState,
            containerColor = Color.White,
            scrimColor = Color.Black.copy(alpha = 0.5f)
        ) {
            TermsAndConditionsSheet()
        }
    }
}

@Composable
private fun ClaimButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(0.75f)
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ClaimButtonBlue),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = "Claim offer now",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
private fun TermsAndConditionsLink(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Terms & Conditions*",
        fontSize = 13.sp,
        color = Color.LightGray,
        modifier = modifier
            .clickable { onClick() }
            .padding(top = 8.dp)
    )
}

