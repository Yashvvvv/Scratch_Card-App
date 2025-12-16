package com.example.scratch.data.model

// Represents the reward revealed after scratching
data class ScratchReward(
    val amount: String = "$10",
    val label: String = "Cashback",
    val title: String = "Offer from AppStorys",
    val description: String = "Cashback on mobile and recharge",
    val couponCode: String = "APPSTORYS50",
    val claimUrl: String = "https://www.google.com/search?q=offers"
)

