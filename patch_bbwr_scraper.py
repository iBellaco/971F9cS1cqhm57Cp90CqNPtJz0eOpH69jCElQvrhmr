import re

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'r') as f:
    text = f.read()

new_func = """
    suspend fun syncGlobalTierList(context: Context): Boolean {
        _syncState.value = BestBuildSyncState.Syncing
        return try {
            val html = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val request = Request.Builder().url("https://bestbuildwr.com/tierlist").build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw Exception("Failed to fetch BBWR tierlist")
                    response.body?.string() ?: ""
                }
            }
            
            val regex = "<script id=\\"__NEXT_DATA__\\" type=\\"application/json\\">(.*?)</script>".toRegex()
            val matchResult = regex.find(html)
            if (matchResult != null) {
                val jsonString = matchResult.groupValues[1]
                val jsonObject = org.json.JSONObject(jsonString)
                val championsArray = jsonObject
                    .optJSONObject("props")?.optJSONObject("pageProps")
                    ?.optJSONObject("tierData")?.optJSONArray("champions")
                
                if (championsArray != null) {
                    val champTiers = mutableMapOf<String, String>()
                    val validLabels = listOf("Top", "Jungla", "Mid", "ADC", "Support")
                    
                    for (i in 0 until championsArray.length()) {
                        val categoryObj = championsArray.optJSONObject(i) ?: continue
                        val label = categoryObj.optString("label")
                        if (!validLabels.contains(label)) continue
                        
                        val tiersObj = categoryObj.optJSONObject("tiers") ?: continue
                        val keys = tiersObj.keys()
                        while (keys.hasNext()) {
                            val tierKey = keys.next()
                            val champsInTier = tiersObj.optJSONArray(tierKey) ?: continue
                            for (j in 0 until champsInTier.length()) {
                                val champObj = champsInTier.optJSONObject(j) ?: continue
                                val rawName = champObj.optString("name").uppercase()
                                // Normalize name
                                val normalizedName = rawName.replace("'", "").replace(" ", "").replace(".", "")
                                
                                // Keep highest tier if seen multiple times.
                                // In BestBuildWR, S > A > B > C > D
                                val existingTier = champTiers[normalizedName]
                                if (existingTier == null || isHigherTier(tierKey, existingTier)) {
                                    champTiers[normalizedName] = tierKey
                                }
                            }
                        }
                    }
                    
                    val currentChamps = WildRiftRepository.champions.toList()
                    val updatedChamps = currentChamps.map { champ ->
                        val searchName = champ.name.uppercase().replace("'", "").replace(" ", "").replace(".", "")
                        val newTier = champTiers[searchName]
                        if (newTier != null) {
                            champ.copy(tier = newTier)
                        } else {
                            champ
                        }
                    }
                    
                    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                        WildRiftRepository.champions.clear()
                        WildRiftRepository.champions.addAll(updatedChamps)
                    }
                    
                    AppLogger.d(TAG, "Global Tier List synced from BBWR")
                    _syncState.value = BestBuildSyncState.Success(updatedChamps.size, "BBWR", System.currentTimeMillis())
                    return true
                }
            }
            false
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error syncing BBWR tierlist", e)
            _syncState.value = BestBuildSyncState.Error(e.localizedMessage ?: "Unknown error")
            false
        }
    }
    
    private fun isHigherTier(newTier: String, oldTier: String): Boolean {
        val rank = mapOf("S+" to 6, "S" to 5, "A+" to 4, "A" to 3, "B" to 2, "C" to 1, "D" to 0)
        return (rank[newTier] ?: -1) > (rank[oldTier] ?: -1)
    }
"""

# Insert before the last closing brace
text = text[:text.rfind('}')] + new_func + "\n}"

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'w') as f:
    f.write(text)

