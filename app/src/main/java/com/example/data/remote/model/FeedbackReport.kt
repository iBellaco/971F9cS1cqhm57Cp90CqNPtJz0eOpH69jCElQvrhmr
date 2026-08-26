package com.example.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackReport(
    val id: String? = null,
    val type: String = "BUG",
    val title: String = "",
    val description: String = "",
    @SerialName("app_version") val appVersion: String = "",
    @SerialName("device_info") val deviceInfo: String = "",
    @SerialName("created_at") val createdAt: String? = null,
    val status: String? = null,
    @SerialName("is_completed") val isCompleted: Boolean? = null
) {
    val parsedEmail: String?
        get() {
            val match = Regex("^Correo de contacto: (.*?)\n\n").find(description)
            return match?.groupValues?.get(1)
        }

    val cleanDescription: String
        get() {
            return description.replaceFirst(Regex("^Correo de contacto: (.*?)\n\n"), "")
        }
}

