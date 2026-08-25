import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# The block to remove:
block_to_remove = """
        // Check for Off-role / Troll pick
        val isOffRole = champ.primaryRole != myRole && !champ.secondaryRoles.contains(myRole)
        if (isOffRole) {
            score -= 15.0 // heavy penalty
            badge = "❌ SELECCIÓN ATÍPICA (OFF-META)"
            reasonParts.add("Este campeón no es idóneo para esta línea. Jugarlo aquí es considerado atípico o desventajoso para el equipo.")
        }
"""

content = content.replace(block_to_remove, "")

# Now insert it only in the evaluateChampion function.
# Let's find:
#         var counterText = ""
# and only replace the first occurrence!
content = content.replace('var counterText = ""', 'var counterText = ""' + block_to_remove, 1)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w', encoding='utf-8') as f:
    f.write(content)

