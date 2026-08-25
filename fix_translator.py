import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Append to Portuguese
pt_add = '''
        "Heal" to "Curar",
        "Smite" to "Golpear",
        "Ignite" to "Incendiar",
        "Flash" to "Flash",
        "Ghost" to "Fantasma",
        "Barrier" to "Barreira",
        "Exhaust" to "Exaustão",
        "Cleanse" to "Purificar",
        "Teleport" to "Teleporte",
        "Clarity" to "Clareza",
        "Mark / Dash" to "Marcação / Avanço",
        "Transferencia / Alias / Pix" to "Transferência / Alias / Pix",
        "Latinoamérica / Internacional" to "América Latina / Internacional",
        "Entendido / Cerrar" to "Entendido / Fechar",
        "Copiar" to "Copiar",
'''
content = content.replace('// === HECHIZOS DE INVOCADOR (PORTUGUÊS) ===', '// === HECHIZOS DE INVOCADOR (PORTUGUÊS) ===\n' + pt_add)

# Append to English
en_add = '''
        "Transferencia / Alias / Pix" to "Transfer / Alias / Pix",
        "Latinoamérica / Internacional" to "Latin America / International",
        "Entendido / Cerrar" to "Understood / Close",
        "Copiar" to "Copy",
        "Smite" to "Smite",
        "Heal" to "Heal",
        "Ignite" to "Ignite",
        "Flash" to "Flash",
        "Ghost" to "Ghost",
        "Barrier" to "Barrier",
        "Exhaust" to "Exhaust",
        "Cleanse" to "Cleanse",
        "Teleport" to "Teleport",
'''
content = content.replace('// === SUMMONER SPELLS (ENGLISH) ===', '// === SUMMONER SPELLS (ENGLISH) ===\n' + en_add)

# To handle reverse mappings (if the database has English names but app is in Spanish)
# Let's add them to Spanish (since Spanish is the default, it usually doesn't have a map).
# Wait, `Translator.kt` has `currentLanguage`. 
# If current language is "es", it returns the original string if not found.
# Let's check how "es" is handled.
