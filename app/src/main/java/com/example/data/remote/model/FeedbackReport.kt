package com.example.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackReport(
    val id: String? = null,
    val type: String,
    val title: String,
    val description: String,
    @SerialName("app_version") val appVersion: String,
    @SerialName("device_info") val deviceInfo: String,
    @SerialName("created_at") val createdAt: String? = null
)
