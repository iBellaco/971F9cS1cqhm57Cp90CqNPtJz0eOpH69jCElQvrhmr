import re

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'r') as f:
    text = f.read()

replacement = """
    suspend fun syncGlobalTierList(context: Context): Boolean {
        _syncState.value = BestBuildSyncState.Syncing
        return try {
            val bbwrTiers = fetchBbwrTiers()
            val wrfTiers = fetchWrfTiers()
            
            val combinedTiers = mutableMapOf<String, String>()
            val allNames = bbwrTiers.keys + wrfTiers.keys
            for (name in allNames) {
                val v1 = getTierValue(bbwrTiers[name])
                val v2 = getTierValue(wrfTiers[name])
                
                val finalTier = if (v1 >= 0 && v2 >= 0) {
                    getTierFromValue(Math.round((v1 + v2) / 2.0).toInt())
                } else if (v1 >= 0) {
                    bbwrTiers[name]!!
                } else if (v2 >= 0) {
                    wrfTiers[name]!!
                } else {
                    "B"
                }
                combinedTiers[name] = finalTier
            }

            val currentChamps = WildRiftRepository.champions.toList()
            val updatedChamps = currentChamps.map { champ ->
                val searchName = champ.name.uppercase().replace("'", "").replace(" ", "").replace(".", "")
                val newTier = combinedTiers[searchName]
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

            AppLogger.d(TAG, "Global Tier List synced from BBWR and WRF")
            _syncState.value = BestBuildSyncState.Success(updatedChamps.size, "BBWR + WildRiftFire", System.currentTimeMillis())
            true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error syncing BBWR/WRF tierlist", e)
            _syncState.value = BestBuildSyncState.Error(e.localizedMessage ?: "Unknown error")
            false
        }
    }

    private suspend fun fetchBbwrTiers(): Map<String, String> {
        val champTiers = mutableMapOf<String, String>()
        try {
            val html = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val request = Request.Builder().url("https://bestbuildwr.com/tierlist").build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@withContext ""
                    response.body?.string() ?: ""
                }
            }
            if (html.isEmpty()) return champTiers

            val regex = "<script id=\\"__NEXT_DATA__\\" type=\\"application/json\\">(.*?)</script>".toRegex()
            val matchResult = regex.find(html)
            if (matchResult != null) {
                val jsonString = matchResult.groupValues[1]
                val jsonObject = org.json.JSONObject(jsonString)
                val championsArray = jsonObject
                    .optJSONObject("props")?.optJSONObject("pageProps")
                    ?.optJSONObject("tierData")?.optJSONArray("champions")

                if (championsArray != null) {
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
                                val normalizedName = rawName.replace("'", "").replace(" ", "").replace(".", "")

                                val existingTier = champTiers[normalizedName]
                                if (existingTier == null || isHigherTier(tierKey, existingTier)) {
                                    champTiers[normalizedName] = tierKey
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Failed to fetch BBWR", e)
        }
        return champTiers
    }

    private suspend fun fetchWrfTiers(): Map<String, String> {
        val champTiers = mutableMapOf<String, String>()
        try {
            val html = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val request = Request.Builder().url("https://www.wildriftfire.com/tier-list").build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@withContext ""
                    response.body?.string() ?: ""
                }
            }
            if (html.isEmpty()) return champTiers

            val document = org.jsoup.Jsoup.parse(html)
            val blocks = document.select(".wf-tier-list__tiers__block")
            for (block in blocks) {
                val tierDiv = block.selectFirst(".tier") ?: continue
                val tierClass = tierDiv.className().replace("tier", "").trim()
                val mappedTier = when {
                    tierClass.contains("splus") -> "S+"
                    tierClass.contains("s") -> "S"
                    tierClass.contains("a") -> "A"
                    tierClass.contains("b") -> "B"
                    tierClass.contains("c") -> "C"
                    else -> continue
                }

                val links = block.select("a.ico-holder")
                for (link in links) {
                    val nameSpan = link.selectFirst("span:not(.tier-delta)") ?: continue
                    val rawName = nameSpan.text()
                    val normalizedName = rawName.uppercase().replace("'", "").replace(" ", "").replace(".", "")

                    val existing = champTiers[normalizedName]
                    if (existing == null || isHigherTier(mappedTier, existing)) {
                        champTiers[normalizedName] = mappedTier
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Failed to fetch WRF", e)
        }
        return champTiers
    }

    private fun getTierValue(tier: String?): Int {
        return when (tier) {
            "S+" -> 6
            "S" -> 5
            "A+" -> 4
            "A" -> 3
            "B" -> 2
            "C" -> 1
            "D" -> 0
            else -> -1
        }
    }

    private fun getTierFromValue(value: Int): String {
        return when (value) {
            6 -> "S+"
            5 -> "S"
            4 -> "A+"
            3 -> "A"
            2 -> "B"
            1 -> "C"
            0 -> "D"
            else -> "C"
        }
    }

    private fun isHigherTier(newTier: String, oldTier: String): Boolean {
        return getTierValue(newTier) > getTierValue(oldTier)
    }
"""

text = re.sub(r'suspend fun syncGlobalTierList.*$', replacement.strip() + "\n}", text, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'w') as f:
    f.write(text)
