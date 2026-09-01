package com.example.model

data class SubscriptionRecord(
    val id: String = "",
    val timestamp: Long = 0L,
    val durationMillis: Long = 0L,
    val planName: String = "",
    val status: String = "",
    val amount: String = ""
)
