import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Fix the broken end of map
broken = '        "Cazador - Asesino" to "Hunter - Assassin", (k, v) -> v to k }'
fixed = '        "Cazador - Asesino" to "Hunter - Assassin"\n    )\n)\n\nval itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()\nval itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()\nval itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }'

content = content.replace(broken, fixed)

# Now check if we accidentally deleted something else.
# Wait, look at line 2380 above:
#        "Fantasmal" to "Ghost",
#        "Curar" to "Heal",
# Wait, these are English translations! Why are they under PT?
# Ah! My script injected BOTH EN and PT at the same spot maybe?!
