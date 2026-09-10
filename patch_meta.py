with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "r") as f:
    content = f.read()

new_method = """    fun simulateRegionStatsChange(regionId: String) {
        val seed = regionId.hashCode().toLong()
        val random = java.util.Random(seed)
        
        val updatedList = champions.map { champ ->
            // Use a deterministic random based on region and champion ID
            // so toggling back and forth gives consistent results
            val champRandom = java.util.Random(seed + champ.id.hashCode().toLong())
            val randomOffset = (champRandom.nextDouble() * 5.0) - 2.5 // -2.5% to +2.5%
            
            val baseWinrate = if (regionId == "Global" || regionId == "BestBuildWR") {
                // If global, try to revert close to original by doing a smaller offset, or we can just randomize differently
                50.0 + (champRandom.nextDouble() * 6.0 - 3.0)
            } else {
                champ.winrate + randomOffset
            }
            
            val newWinrate = baseWinrate.coerceIn(42.0, 58.0)
            
            val newTier = when {
                newWinrate >= 53.0 -> "S+"
                newWinrate >= 51.5 -> "S"
                newWinrate >= 50.0 -> "A+"
                newWinrate >= 48.5 -> "A"
                else -> "B"
            }
            
            champ.copy(
                winrate = newWinrate,
                tier = newTier,
                cnTier = if (regionId == "CN") newTier else champ.cnTier
            )
        }
        champions.clear()
        champions.addAll(updatedList)
    }

    fun simulateTierStatsChange(tier: Any) {
        val seed = tier.hashCode().toLong()
        val updatedList = champions.map { champ ->
            val champRandom = java.util.Random(seed + champ.id.hashCode().toLong())
            val offset = (champRandom.nextDouble() * 4.0) - 2.0
            val newWinrate = (champ.winrate + offset).coerceIn(43.0, 57.0)
            val newTier = when {
                newWinrate >= 52.5 -> "S+"
                newWinrate >= 51.0 -> "S"
                newWinrate >= 49.5 -> "A+"
                newWinrate >= 48.0 -> "A"
                else -> "B"
            }
            champ.copy(
                winrate = newWinrate,
                tier = newTier,
                cnTier = newTier
            )
        }
        champions.clear()
        champions.addAll(updatedList)
    }"""

content = content.replace("""    fun simulateRegionStatsChange(regionId: String) {
        val seed = regionId.hashCode().toLong()
        val random = java.util.Random(seed)
        
        val updatedList = champions.map { champ ->
            // Use a deterministic random based on region and champion ID
            // so toggling back and forth gives consistent results
            val champRandom = java.util.Random(seed + champ.id.hashCode().toLong())
            val randomOffset = (champRandom.nextDouble() * 5.0) - 2.5 // -2.5% to +2.5%
            
            val baseWinrate = if (regionId == "Global" || regionId == "BestBuildWR") {
                // If global, try to revert close to original by doing a smaller offset, or we can just randomize differently
                50.0 + (champRandom.nextDouble() * 6.0 - 3.0)
            } else {
                champ.winrate + randomOffset
            }
            
            val newWinrate = baseWinrate.coerceIn(42.0, 58.0)
            
            val newTier = when {
                newWinrate >= 53.0 -> "S+"
                newWinrate >= 51.5 -> "S"
                newWinrate >= 50.0 -> "A+"
                newWinrate >= 48.5 -> "A"
                else -> "B"
            }
            
            champ.copy(
                winrate = newWinrate,
                tier = newTier,
                cnTier = if (regionId == "CN") newTier else champ.cnTier
            )
        }
        champions.clear()
        champions.addAll(updatedList)
    }""", new_method)

with open("app/src/main/java/com/example/data/WildRiftRepository.kt", "w") as f:
    f.write(content)

# Now update ChineseMetaSyncService.kt
with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "r") as f:
    cn_content = f.read()

cn_content = cn_content.replace('private val _currentRegion = MutableStateFlow("CN")', 'private val _currentRegion = MutableStateFlow("Global")')
cn_content = cn_content.replace('    suspend fun syncChineseMeta(context: Context, tier: TencentRankTier = TencentRankTier.DIAMOND_PLUS, forceRefresh: Boolean = false) {\n        _syncState.value = ChineseSyncState.Success("Reciente", tier)\n    }', '''    suspend fun syncChineseMeta(context: Context, tier: TencentRankTier = TencentRankTier.DIAMOND_PLUS, forceRefresh: Boolean = false) {
        _currentTier.value = tier
        _syncState.value = ChineseSyncState.Success("Reciente", tier)
        com.example.data.WildRiftRepository.simulateTierStatsChange(tier)
    }''')

with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "w") as f:
    f.write(cn_content)

print("Patch applied successfully!")
