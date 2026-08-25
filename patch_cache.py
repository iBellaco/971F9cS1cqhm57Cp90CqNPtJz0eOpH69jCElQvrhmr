import re

with open('app/src/main/java/com/example/data/local/WildRiftLocalCache.kt', 'r', encoding='utf-8') as f:
    content = f.read()

replacement = """
                if (loadedItems.isNotEmpty()) {
                    // Filter out spells that were previously saved as basic items
                    val spellIds = com.example.data.WildRiftSpellsAndRunes.summonerSpells.map { it.id }.toSet()
                    val filteredItems = loadedItems.filter { item ->
                        val isSpell = item.id.endsWith("_basic") && item.id.replace("_basic", "") in spellIds.map { it.replace("spell_", "") }
                        !isSpell && !item.id.startsWith("spell_")
                    }
                    WildRiftRepository.items = if (filteredItems.isNotEmpty()) filteredItems else loadedItems
                    hasLoadedAny = true
                }
"""

# Replace the block
content = re.sub(
    r'if\s*\(loadedItems\.isNotEmpty\(\)\)\s*\{\s*WildRiftRepository\.items\s*=\s*loadedItems\s*hasLoadedAny\s*=\s*true\s*\}',
    replacement,
    content
)

with open('app/src/main/java/com/example/data/local/WildRiftLocalCache.kt', 'w', encoding='utf-8') as f:
    f.write(content)

