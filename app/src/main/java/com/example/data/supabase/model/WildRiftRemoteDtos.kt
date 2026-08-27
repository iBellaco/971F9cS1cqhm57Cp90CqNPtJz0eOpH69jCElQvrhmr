package com.example.data.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WrPatchDto(
    @SerialName("id") val id: String = "current",
    @SerialName("version") val version: String,
    @SerialName("notes") val notes: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)
