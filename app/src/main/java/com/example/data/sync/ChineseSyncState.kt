package com.example.data.sync

sealed class ChineseSyncState {
    object Idle : ChineseSyncState()
    object Syncing : ChineseSyncState()
    data class Success(val timestamp: String, val tier: TencentRankTier) : ChineseSyncState()
    data class Error(val message: String) : ChineseSyncState()
}
