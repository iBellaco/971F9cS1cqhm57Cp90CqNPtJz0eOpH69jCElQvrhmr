import re

with open('app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt', 'r') as f:
    text = f.read()

replacement = """
    fun setRegion(context: Context, region: String, coroutineScope: kotlinx.coroutines.CoroutineScope) {
        _currentRegion.value = region
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString("META_REGION", region).apply()
        
        if (region == "NA") {
            WildRiftRepository.initChampions(context, forceReload = true)
            _syncState.value = ChineseSyncState.Idle
        } else if (region == "BestBuildWR") {
            coroutineScope.launch {
                WildRiftRepository.initChampions(context, forceReload = true)
                _syncState.value = ChineseSyncState.Syncing
                val success = com.example.data.sync.BestBuildWrScraper.syncGlobalTierList(context)
                if (success) {
                    _syncState.value = ChineseSyncState.Success(
                        TencentRankTier.DIAMOND_PLUS,
                        WildRiftRepository.champions.size,
                        "Reciente"
                    )
                } else {
                    _syncState.value = ChineseSyncState.Error("Fallo al obtener BestBuildWR")
                }
            }
        } else {
            coroutineScope.launch {
                syncChineseMeta(context, _currentTier.value, forceRefresh = true)
            }
        }
    }
"""

text = re.sub(r'fun setRegion\(.*?\{.*?(?=\s+private val _currentTier)', replacement, text, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt', 'w') as f:
    f.write(text)
