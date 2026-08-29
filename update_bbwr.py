import re

with open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'r') as f:
    text = f.read()

new_function = """
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
            
            // Extract JSON inside __NEXT_DATA__
            val regex = "<script id=\\"__NEXT_DATA__\\" type=\\"application/json\\">(.*?)</script>".toRegex()
            val matchResult = regex.find(html)
            if (matchResult != null) {
                val jsonString = matchResult.groupValues[1]
                val jsonObject = org.json.JSONObject(jsonString)
                
                // Recursively find "tierlists" or search in props
                val props = jsonObject.optJSONObject("props")
                val pageProps = props?.optJSONObject("pageProps")
                
                // We know it is pageProps -> tierlists (or somewhere inside)
                // Let's do a simple string search for tiers if needed, but JSONObject parsing is safer.
                // Best is to regex extract all: "label":"Top","tiers":{"S":[{"id":14,"name":"POPPY"...
                // Actually, let's just use Regex to find champion names and their tiers, but we need the label to avoid matching Runes.
                // Wait, runes have different names. Champion names are unique.
                // We can parse the JSON tree manually.
            }
            true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error syncing BBWR tierlist", e)
            _syncState.value = BestBuildSyncState.Error(e.localizedMessage ?: "Unknown error")
            false
        }
    }
"""
