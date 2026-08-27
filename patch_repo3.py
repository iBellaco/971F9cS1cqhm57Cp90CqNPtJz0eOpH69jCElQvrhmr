import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

# Change `var champions: List<Champion> by mutableStateOf(emptyList())`
# To `val champions = androidx.compose.runtime.mutableStateListOf<Champion>()`
old_champ_decl = "    var champions: List<Champion> by mutableStateOf(emptyList())"
new_champ_decl = "    val champions = androidx.compose.runtime.mutableStateListOf<Champion>()"

content = content.replace(old_champ_decl, new_champ_decl)

old_init = """            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            champions = format.decodeFromString<List<Champion>>(jsonString)
            android.util.Log.d("WildRiftRepository", "Loaded ${champions.size} champions successfully")"""

new_init = """            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            val parsed = format.decodeFromString<List<Champion>>(jsonString)
            champions.clear()
            champions.addAll(parsed)
            android.util.Log.d("WildRiftRepository", "Loaded ${champions.size} champions successfully")"""

content = content.replace(old_init, new_init)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
