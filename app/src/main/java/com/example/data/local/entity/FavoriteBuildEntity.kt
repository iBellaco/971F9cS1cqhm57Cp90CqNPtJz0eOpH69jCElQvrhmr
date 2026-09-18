package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_builds")
data class FavoriteBuildEntity(
    @PrimaryKey
    val buildId: String,
    val timestamp: Long = System.currentTimeMillis()
)
