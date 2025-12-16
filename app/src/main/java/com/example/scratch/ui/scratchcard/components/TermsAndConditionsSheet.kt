package com.example.scratch.ui.scratchcard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Bottom sheet displaying terms and conditions
@Composable
fun TermsAndConditionsSheet(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TermSection(
            title = "Eligibility:",
            content = "Only fake users who meet the campaign criteria (as defined by the brand/platform) are eligible to participate in the Scratch CRC program."
        )

        TermSection(
            title = "Non-Transferable & One-Time Use:",
            content = "Each scratch code/reward is unique, valid for a single use, and cannot be transferred, exchanged, or redeemed for cash unless explicitly stated."
        )

        TermSection(
            title = "Fraud Prevention:",
            content = "Any misuse, duplication, unauthorized distribution, or suspicious activity related to the scratch code will result in immediate disqualification and potential blocking of the user/account."
        )

        TermSection(
            title = "Validity & Expiry:",
            content = "All scratch cards/codes must be redeemed within the specified validity period. Expired or tampered codes will not be honored."
        )

        TermSection(
            title = "Platform Discretion:",
            content = "The brand/platform reserves the right to modify, suspend, or terminate the Scratch CRC program at any time without prior notice."
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun TermSection(
    title: String,
    content: String
) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A237E)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
    }
}

