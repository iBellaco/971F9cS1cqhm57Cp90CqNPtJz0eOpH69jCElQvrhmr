import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

match = re.search(r'\)\s*val itemNamesEnToEs = itemNamesEsToEn\.entries\.associate \{', content)
if match:
    garbage_start = match.start()
    composable_idx = content.find("@Composable", garbage_start)
    if composable_idx != -1:
        replacement = """
)

val itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }

"""
        content = content[:garbage_start] + replacement + content[composable_idx:]
        with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
            f.write(content)
        print("Fixed garbage!")
else:
    print("Not found regex")
