import re

with open('app/src/main/java/com/example/data/local/WildRiftLocalCache.kt', 'r') as f:
    content = f.read()

old_cache_champs = """            val championsJson = prefs.getString(KEY_CHAMPIONS, null)
            if (!championsJson.isNullOrBlank()) {
                val loadedChamps = json.decodeFromString<List<Champion>>(championsJson)
                if (loadedChamps.isNotEmpty()) {
                    WildRiftRepository.champions.clear(); WildRiftRepository.champions.addAll(loadedChamps)
                    hasLoadedAny = true
                }
            }"""

new_cache_champs = """            val championsJson = prefs.getString(KEY_CHAMPIONS, null)
            if (!championsJson.isNullOrBlank()) {
                try {
                    val loadedChamps = json.decodeFromString<List<Champion>>(championsJson)
                    if (loadedChamps.isNotEmpty()) {
                        WildRiftRepository.champions.clear(); WildRiftRepository.champions.addAll(loadedChamps)
                        hasLoadedAny = true
                    }
                } catch (e: Exception) {
                    android.util.Log.e("WildRiftLocalCache", "Corrupted champions cache, clearing", e)
                    prefs.edit().remove(KEY_CHAMPIONS).apply()
                }
            }"""

content = content.replace(old_cache_champs, new_cache_champs)

with open('app/src/main/java/com/example/data/local/WildRiftLocalCache.kt', 'w') as f:
    f.write(content)
