import re

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'r') as f:
    text = f.read()

replacement = """
    suspend fun syncGlobalTierList(context: Context): Boolean {
        _syncState.value = BestBuildSyncState.Syncing
        return try {
            val bbwrData = fetchBbwrTiers()
            val wrfData = fetchWrfTiers()
            
            val combinedTiers = mutableMapOf<String, String>()
            val combinedRoles = mutableMapOf<String, MutableSet<com.example.model.LaneRole>>()
            
            val allNames = bbwrData.keys + wrfData.keys
            for (name in allNames) {
                val bData = bbwrData[name]
                val wData = wrfData[name]
                
                val v1 = getTierValue(bData?.first)
                val v2 = getTierValue(wData?.first)
                
                val finalTier = if (v1 >= 0 && v2 >= 0) {
                    getTierFromValue(Math.round((v1 + v2) / 2.0).toInt())
                } else if (v1 >= 0) {
                    bData!!.first
                } else if (v2 >= 0) {
                    wData!!.first
                } else {
                    "B"
                }
                combinedTiers[name] = finalTier
                
                val roles = mutableSetOf<com.example.model.LaneRole>()
                if (bData != null) roles.addAll(bData.second)
                if (wData != null) roles.addAll(wData.second)
                combinedRoles[name] = roles
            }

            val currentChamps = WildRiftRepository.champions.toList()
            val updatedChamps = currentChamps.map { champ ->
                val searchName = champ.name.uppercase().replace("'", "").replace(" ", "").replace(".", "")
                val newTier = combinedTiers[searchName]
                val newRoles = combinedRoles[searchName]
                
                if (newTier != null) {
                    // Update roles if we found new ones
                    val finalPrimary = if (newRoles != null && newRoles.isNotEmpty() && !newRoles.contains(champ.primaryRole)) {
                        newRoles.first() // If old primary role is no longer played, change it
                    } else {
                        champ.primaryRole
                    }
                    
                    val finalSecondary = if (newRoles != null) {
                        newRoles.filter { it != finalPrimary }
                    } else {
                        champ.secondaryRoles
                    }
                    
                    champ.copy(tier = newTier, primaryRole = finalPrimary, secondaryRoles = finalSecondary)
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

    private suspend fun fetchBbwrTiers(): Map<String, Pair<String, MutableSet<com.example.model.LaneRole>>> {
        val champData = mutableMapOf<String, Pair<String, MutableSet<com.example.model.LaneRole>>>()
        try {
            val html = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val request = Request.Builder().url("https://bestbuildwr.com/tierlist").build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@withContext ""
                    response.body?.string() ?: ""
                }
            }
            if (html.isEmpty()) return champData

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
                        
                        val mappedRole = when(label) {
                            "Top" -> com.example.model.LaneRole.TOP
                            "Jungla" -> com.example.model.LaneRole.JUNGLE
                            "Mid" -> com.example.model.LaneRole.MID
                            "ADC" -> com.example.model.LaneRole.ADC
                            "Support" -> com.example.model.LaneRole.SUPPORT
                            else -> continue
                        }

                        val tiersObj = categoryObj.optJSONObject("tiers") ?: continue
                        val keys = tiersObj.keys()
                        while (keys.hasNext()) {
                            val tierKey = keys.next()
                            val champsInTier = tiersObj.optJSONArray(tierKey) ?: continue
                            for (j in 0 until champsInTier.length()) {
                                val champObj = champsInTier.optJSONObject(j) ?: continue
                                val rawName = champObj.optString("name").uppercase()
                                val normalizedName = rawName.replace("'", "").replace(" ", "").replace(".", "")

                                val existing = champData[normalizedName]
                                if (existing == null) {
                                    champData[normalizedName] = Pair(tierKey, mutableSetOf(mappedRole))
                                } else {
                                    existing.second.add(mappedRole)
                                    if (isHigherTier(tierKey, existing.first)) {
                                        champData[normalizedName] = Pair(tierKey, existing.second)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Failed to fetch BBWR", e)
        }
        return champData
    }

    private suspend fun fetchWrfTiers(): Map<String, Pair<String, MutableSet<com.example.model.LaneRole>>> {
        val champData = mutableMapOf<String, Pair<String, MutableSet<com.example.model.LaneRole>>>()
        try {
            val html = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val request = Request.Builder().url("https://www.wildriftfire.com/tier-list").build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@withContext ""
                    response.body?.string() ?: ""
                }
            }
            if (html.isEmpty()) return champData

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
                    
                    val rawRole = link.attr("data-role")
                    val mappedRole = when(rawRole) {
                        "Solo" -> com.example.model.LaneRole.TOP
                        "Jungle" -> com.example.model.LaneRole.JUNGLE
                        "Mid" -> com.example.model.LaneRole.MID
                        "Duo" -> com.example.model.LaneRole.ADC
                        "Support" -> com.example.model.LaneRole.SUPPORT
                        else -> null
                    }

                    val existing = champData[normalizedName]
                    if (existing == null) {
                        val roles = mutableSetOf<com.example.model.LaneRole>()
                        if (mappedRole != null) roles.add(mappedRole)
                        champData[normalizedName] = Pair(mappedTier, roles)
                    } else {
                        if (mappedRole != null) existing.second.add(mappedRole)
                        if (isHigherTier(mappedTier, existing.first)) {
                            champData[normalizedName] = Pair(mappedTier, existing.second)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Failed to fetch WRF", e)
        }
        return champData
    }
"""

text = re.sub(r'suspend fun syncGlobalTierList.*(?=\s*private fun getTierValue)', replacement.strip() + "\n\n", text, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'w') as f:
    f.write(text)
