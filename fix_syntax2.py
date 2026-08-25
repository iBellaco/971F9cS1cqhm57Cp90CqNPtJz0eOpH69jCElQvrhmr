import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# I will find the part after "Cazador - Asesino" to "Caçador - Assassino"
# And wipe out all the garbage until `@Composable`
# Because all that garbage is wrong English translations injected into PT map, and breaking the syntax.

match = re.search(r'("Cazador - Asesino"\s*to\s*"Caçador - Assassino",?)(.*?)(@Composable)', content, re.DOTALL)
if match:
    # Everything in group 2 is garbage. We need to close the map and add the associate code.
    correct_end = """
    )
)

val itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }

"""
    new_content = content[:match.start(2)] + correct_end + content[match.start(3):]
    with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
        f.write(new_content)
    print("Fixed!")
else:
    print("Not found")

