import re

fpath = "app/src/main/java/com/example/util/ChampionRoleAdapter.kt"
with open(fpath, "r", encoding="utf-8") as f:
    content = f.read()

# Replace the coreTarget lines
content = content.replace('val coreTarget = coreItems.find { it.contains("Rabadon") || it.contains("Infinito") || it.contains("Luden") } ?: coreItems.firstOrNull() ?: "Luden\'s Echo"',
'''val validCoreItems = coreItems.filter { !it.contains("Botas", ignoreCase = true) && !it.contains("Grebas", ignoreCase = true) }
            val coreTarget = validCoreItems.find { it.contains("Rabadon") || it.contains("Infinito") || it.contains("Luden") } ?: validCoreItems.firstOrNull() ?: "Luden's Echo"''')

content = content.replace('val coreTarget = coreItems.find { it.contains("Fuerza") || it.contains("Amanecer") || it.contains("Muerto") } ?: coreItems.firstOrNull() ?: "Plato del hombre muerto"',
'''val validCoreItems = coreItems.filter { !it.contains("Botas", ignoreCase = true) && !it.contains("Grebas", ignoreCase = true) }
            val coreTarget = validCoreItems.find { it.contains("Fuerza") || it.contains("Amanecer") || it.contains("Muerto") } ?: validCoreItems.firstOrNull() ?: "Plato del hombre muerto"''')

content = content.replace('val coreTarget = coreItems.find { it.contains("Danza") || it.contains("Cuchilla") || it.contains("Fuego") } ?: coreItems.firstOrNull() ?: "Black Cleaver"',
'''val validCoreItems = coreItems.filter { !it.contains("Botas", ignoreCase = true) && !it.contains("Grebas", ignoreCase = true) }
            val coreTarget = validCoreItems.find { it.contains("Danza") || it.contains("Cuchilla") || it.contains("Fuego") } ?: validCoreItems.firstOrNull() ?: "Black Cleaver"''')

with open(fpath, "w", encoding="utf-8") as f:
    f.write(content)

print("Fixed core target logic")
