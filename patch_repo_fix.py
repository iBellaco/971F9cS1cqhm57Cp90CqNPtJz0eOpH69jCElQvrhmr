with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r', encoding='utf-8') as f:
    content = f.read()

off_role_logic = """
        // Check for Off-role / Troll pick
        val isOffRole = champ.primaryRole != myRole && !champ.secondaryRoles.contains(myRole)
        if (isOffRole) {
            score -= 15.0 // heavy penalty
            badge = "❌ SELECCIÓN ATÍPICA (OFF-META)"
            reasonParts.add("Este campeón no es idóneo para esta línea. Jugarlo aquí es considerado atípico o desventajoso para el equipo.")
        }
"""

target_str = "        var counterText = \"\"\n"

if target_str in content:
    content = content.replace(target_str, target_str + off_role_logic)
else:
    print("Could not find target_str")

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w', encoding='utf-8') as f:
    f.write(content)

