import re

with open('app/src/main/java/com/example/data/supabase/WildRiftSupabaseRepository.kt', 'r', encoding='utf-8') as f:
    content = f.read()

replacement = """
            if (validItems.isNotEmpty()) {
                val spellIds = com.example.data.WildRiftSpellsAndRunes.summonerSpells.map { it.id }.toSet()
                val filteredItems = validItems.filter { item ->
                    val isSpell = item.id.endsWith("_basic") && item.id.replace("_basic", "") in spellIds.map { it.replace("spell_", "") }
                    !isSpell && !item.id.startsWith("spell_")
                }
                val merged = (WildRiftRepository.items.associateBy { it.id } + filteredItems.associateBy { it.id }).values.toList()
                WildRiftRepository.items = merged
            }
"""

content = re.sub(
    r'if\s*\(validItems\.isNotEmpty\(\)\)\s*\{\s*val\s*merged\s*=\s*\(WildRiftRepository\.items\.associateBy\s*\{\s*it\.id\s*\}\s*\+\s*validItems\.associateBy\s*\{\s*it\.id\s*\}\)\.values\.toList\(\)\s*WildRiftRepository\.items\s*=\s*merged\s*\}',
    replacement,
    content
)

with open('app/src/main/java/com/example/data/supabase/WildRiftSupabaseRepository.kt', 'w', encoding='utf-8') as f:
    f.write(content)

