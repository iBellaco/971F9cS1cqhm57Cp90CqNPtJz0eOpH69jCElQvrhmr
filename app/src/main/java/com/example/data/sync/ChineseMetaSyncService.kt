package com.example.data.sync

import android.content.Context
import com.example.data.WildRiftRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

    private val _currentRegion = MutableStateFlow("Global")
    val currentRegion: StateFlow<String> = _currentRegion.asStateFlow()

    fun loadRegion(context: Context) {
        BestBuildWrScraper.initialize(context)
    }

    fun setRegion(context: Context, regionId: String, scope: CoroutineScope) {
        _currentRegion.value = regionId
        WildRiftRepository.updateStatsForRegionAndTier(regionId, _currentTier.value)
        _syncState.value = ChineseSyncState.Success(BestBuildWrScraper.lastSyncFormattedTime.value, _currentTier.value)
        scope.launch(kotlinx.coroutines.Dispatchers.IO) {
            BestBuildWrScraper.syncGlobalTierList(context, regionId, force = true)
        }
    }

    suspend fun syncChineseMeta(context: Context, tier: TencentRankTier = TencentRankTier.DIAMOND_PLUS, forceRefresh: Boolean = false) {
        _currentTier.value = tier
        WildRiftRepository.updateStatsForRegionAndTier(_currentRegion.value, tier)
        BestBuildWrScraper.syncGlobalTierList(context, _currentRegion.value, force = forceRefresh)
        _syncState.value = ChineseSyncState.Success(BestBuildWrScraper.lastSyncFormattedTime.value, tier)
    }

    suspend fun getFilteredRankings(context: Context, tier: TencentRankTier, lane: LaneRole?): List<Champion> {
        return emptyList()
    }

    fun getLastSyncInfo(context: Context): Pair<String, String> {
        val isOnline = BestBuildWrScraper.isOnline.value
        val time = BestBuildWrScraper.lastSyncFormattedTime.value
        return Pair(if (isOnline) "En vivo" else "Sin conexión", time)
    }
}
