import re

with open("app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt", "r") as f:
    text = f.read()

replacement = """
    suspend fun syncAllChampionBuilds(context: Context, forceRefresh: Boolean = false): Boolean {
        _syncState.value = BestBuildSyncState.Syncing

        return try {
            val championsSnapshot = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) { 
                WildRiftRepository.champions.toList() 
            }
            
            val updatedChampions = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                AppLogger.d(TAG, "Conectando e indexando catálogo interno de objetos, runas y hechizos...")
                championsSnapshot.map { champ ->
                    connectChampionWithCatalog(champ)
                }
            }

            kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                WildRiftRepository.champions.clear()
                WildRiftRepository.champions.addAll(updatedChampions)
            }

            val updatedCount = updatedChampions.size
            AppLogger.d(TAG, "Sincronización interna completada: $updatedCount campeones vinculados con el catálogo.")
            _syncState.value = BestBuildSyncState.Success(
                championsUpdated = updatedCount,
                sourceUrl = "Local Catalog",
                timestamp = System.currentTimeMillis()
            )
            true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante vinculación de catálogo", e)
            _syncState.value = BestBuildSyncState.Error(e.localizedMessage ?: "Error al vincular catálogo")
            false
        }
    }
"""

text = re.sub(r'suspend fun syncAllChampionBuilds.*?    \/\*\*', replacement.strip() + '\n\n    /**', text, flags=re.DOTALL)

with open("app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt", "w") as f:
    f.write(text)
