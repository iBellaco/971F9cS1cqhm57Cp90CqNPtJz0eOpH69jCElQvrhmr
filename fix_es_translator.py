import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

es_add = '''
        "Heal" to "Curar",
        "Smite" to "Aplastar",
        "Ignite" to "Prender",
        "Flash" to "Destello",
        "Ghost" to "Fantasmal",
        "Barrier" to "Barrera",
        "Exhaust" to "Extenuación",
        "Cleanse" to "Limpiar",
        "Teleport" to "Teleportar",
        "Clarity" to "Claridad",
        "Mark / Dash" to "Marca y Deslizamiento",
'''

content = content.replace('"Temas (13)" to "Temas (13)",', '"Temas (13)" to "Temas (13)",' + es_add)

with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
    f.write(content)
