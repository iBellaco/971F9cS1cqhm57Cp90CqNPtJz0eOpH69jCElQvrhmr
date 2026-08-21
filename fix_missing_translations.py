import re

file_path = 'app/src/main/java/com/example/util/Translator.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

translations_to_add = """
        // Draft UI Extra
        "Equipo Aliado" to "Equipe Aliada",
        "Equipo Rival" to "Equipe Inimiga",
        "Seleccionar Campeón Aliado" to "Selecionar Campeão Aliado",
        "Seleccionar Campeón Rival" to "Selecionar Campeão Inimigo",
"""

translations_to_add_en = """
        // Draft UI Extra
        "Equipo Aliado" to "Ally Team",
        "Equipo Rival" to "Enemy Team",
        "Seleccionar Campeón Aliado" to "Select Ally Champion",
        "Seleccionar Campeón Rival" to "Select Enemy Champion",
"""

# Insert for PT
content = re.sub(
    r'(fun trStr\(lang: String, text: String\): String \{\n    val map = when \(lang\) \{\n        "pt" -> mapOf\(\n)',
    r'\1' + translations_to_add,
    content
)

# Insert for EN
content = re.sub(
    r'("en" -> mapOf\(\n)',
    r'\1' + translations_to_add_en,
    content
)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)

print("Added missing translations to Translator.kt")
