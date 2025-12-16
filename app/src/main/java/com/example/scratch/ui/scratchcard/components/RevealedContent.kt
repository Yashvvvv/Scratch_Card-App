package com.example.scratch.ui.scratchcard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scratch.data.model.ScratchReward
import com.example.scratch.ui.theme.CouponBorderYellow

// Displays the reward content after scratching
@Composable
fun RevealedContent(
    reward: ScratchReward,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Reward image with amount badge
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = reward.amount,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = reward.label,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                // Gift icon drawn with Canvas
                Canvas(modifier = Modifier.size(40.dp)) {
                    val width = size.width
                    val height = size.height

                    // Gift box (blue)
                    drawRoundRect(
                        color = Color(0xFF2196F3),
                        topLeft = Offset(width * 0.15f, height * 0.45f),
                        size = Size(width * 0.7f, height * 0.5f),
                        cornerRadius = CornerRadius(4f, 4f)
                    )

                    // Lid (darker blue)
                    drawRoundRect(
                        color = Color(0xFF1976D2),
                        topLeft = Offset(width * 0.1f, height * 0.35f),
                        size = Size(width * 0.8f, height * 0.15f),
                        cornerRadius = CornerRadius(4f, 4f)
                    )

                    // Ribbon vertical (yellow)
                    drawRect(
                        color = Color(0xFFFFC107),
                        topLeft = Offset(width * 0.45f, height * 0.35f),
                        size = Size(width * 0.1f, height * 0.6f)
                    )

                    // Bow (orange)
                    drawCircle(
                        color = Color(0xFFFF9800),
                        radius = width * 0.12f,
                        center = Offset(width * 0.35f, height * 0.3f)
                    )
                    drawCircle(
                        color = Color(0xFFFF9800),
                        radius = width * 0.12f,
                        center = Offset(width * 0.65f, height * 0.3f)
                    )
                    drawCircle(
                        color = Color(0xFFF57C00),
                        radius = width * 0.08f,
                        center = Offset(width * 0.5f, height * 0.32f)
                    )
                }
            }
        }

        // Offer title
        Text(
            text = reward.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Offer description
        Text(
            text = reward.description,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        // Coupon code box with yellow border
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(
                    width = 1.5.dp,
                    color = CouponBorderYellow,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 10.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = reward.couponCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CouponBorderYellow,
                letterSpacing = 2.sp
            )
        }
    }
}
