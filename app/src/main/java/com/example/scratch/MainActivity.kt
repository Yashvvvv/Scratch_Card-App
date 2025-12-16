package com.example.scratch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scratch.ui.scratchcard.ScratchCardScreen
import com.example.scratch.ui.scratchcard.ScratchCardViewModel
import com.example.scratch.ui.theme.ScratchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScratchTheme {
                ScratchCardDemo()
            }
        }
    }
}

@Composable
fun ScratchCardDemo() {
    val context = LocalContext.current
    val viewModel: ScratchCardViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Show "Try Your Luck" button when card is not visible
            if (!uiState.isCardVisible) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🎁",
                        fontSize = 64.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "You have a reward waiting!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Scratch to reveal your surprise",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { viewModel.resetCard() },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6B4EFF)
                        ),
                        modifier = Modifier
                            .height(56.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "🎰  Try Your Luck!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Scratch card overlay
            ScratchCardScreen(
                viewModel = viewModel,
                onClaimOffer = { url ->
                    // Opens Google offers page in browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                onDismiss = {
                    // Card dismissed - the button will appear automatically
                }
            )
        }
    }
}