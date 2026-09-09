package com.example.data.sync

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.model.Champion
import com.example.model.LaneRole

object ChineseMetaSyncService {
    private val _syncState = MutableStateFlow<ChineseSyncState>(ChineseSyncState.Idle)
    val syncState: StateFlow<ChineseSyncState> = _syncState.asStateFlow()

    private val _currentTier = MutableStateFlow(TencentRankTier.DIAMOND_PLUS)
    val currentTier: StateFlow<TencentRankTier> = _currentTier.asStateFlow()

    private val _currentRegion = MutableStateFlow("CN")
    val currentRegion: StateFlow<String> = _currentRegion.asStateFlow()

    data class CnChampionStat(
        val winRate: Float,
        val pickRate: Float,
        val banRate: Float,
        val tier: String
    )

    fun loadRegion(context: Context) {
        // Stub
    }

    fun setRegion(context: Context, regionId: String, scope: CoroutineScope) {
        _currentRegion.value = regionId
    }

    suspend fun syncChineseMeta(context: Context, tier: TencentRankTier = TencentRankTier.DIAMOND_PLUS, forceRefresh: Boolean = false) {
        _syncState.value = ChineseSyncState.Success("Reciente", tier)
    }

    suspend fun getFilteredRankings(context: Context, tier: TencentRankTier, lane: LaneRole?): List<Champion> {
        return emptyList()
    }

    fun getLastSyncInfo(context: Context): Pair<String, String> {
        return Pair("Sincronizado", "Reciente")
    }
}
