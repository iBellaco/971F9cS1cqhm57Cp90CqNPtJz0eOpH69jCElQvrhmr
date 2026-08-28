import re

with open("app/src/main/java/com/example/data/sync/OfflineResourceManager.kt", "r") as f:
    code = f.read()

# Fix items
code = code.replace("WildRiftItemsData.items.forEach", "WildRiftItemsData.list.forEach")

# Fix spells
code = code.replace("WildRiftSpellsAndRunes.spells.forEach", "WildRiftSpellsAndRunes.summonerSpells.forEach")

# Fix runes
code = code.replace("WildRiftSpellsAndRunes.keystones.forEach { rune ->", "WildRiftSpellsAndRunes.runes.forEach { rune ->")
code = code.replace("        WildRiftSpellsAndRunes.secondaryRunes.forEach {\n            if (it.iconUrl.isNotEmpty()) urls.add(it.iconUrl)\n        }\n", "")
code = code.replace("        WildRiftSpellsAndRunes.secondaryRunes.forEach { rune ->\n            if (rune.iconUrl.isNotEmpty()) urls.add(rune.iconUrl)\n        }\n", "")

with open("app/src/main/java/com/example/data/sync/OfflineResourceManager.kt", "w") as f:
    f.write(code)

