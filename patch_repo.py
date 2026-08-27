import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

old_func = """    fun initChampions(context: android.content.Context) {
        if (champions.isNotEmpty()) return
        try {
            val jsonString = context.assets.open("champions.json").bufferedReader().use { it.readText() }
            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            champions = format.decodeFromString<List<Champion>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }"""

new_func = """    fun initChampions(context: android.content.Context) {
        if (champions.isNotEmpty()) return
        try {
            val jsonString = context.assets.open("champions.json").bufferedReader().use { it.readText() }
            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            champions = format.decodeFromString<List<Champion>>(jsonString)
            android.util.Log.d("WildRiftRepository", "Loaded ${champions.size} champions successfully")
        } catch (e: Exception) {
            android.util.Log.e("WildRiftRepository", "Failed to load champions", e)
        }
    }"""

content = content.replace(old_func, new_func)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
