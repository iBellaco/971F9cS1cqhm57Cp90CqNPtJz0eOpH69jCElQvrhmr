import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Add them back correctly
vars_to_add = """
val itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }

@Composable"""

content = content.replace("@Composable", vars_to_add, 1)

with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
    f.write(content)
