import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Just remove the injected duplicate lines right before @Composable
to_remove = """val itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }"""

content = content.replace(to_remove, "")

with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
    f.write(content)

