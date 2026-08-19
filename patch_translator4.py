import os

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

translations = """
    "↓ Desliza para cerrar" to mapOf(
        "en" to "↓ Swipe down to close",
        "pt" to "↓ Deslize para fechar"
    ),"""

content = content.replace("val dictionary = mapOf<String, Map<String, String>>(", "val dictionary = mapOf<String, Map<String, String>>(" + translations)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Translator patched again!")
