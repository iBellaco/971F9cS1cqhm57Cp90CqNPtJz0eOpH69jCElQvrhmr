package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AvatarItem(
    val id: String,
    val name: String,
    val title: String,
    val region: String,
    val rarity: String, // "Clásico", "Épico", "Legendario", "Mítico"
    val imageUrl: String,
    val borderHex: String = "#C8AA6E",
    val description: String = "",
    val isDefault: Boolean = false
)
