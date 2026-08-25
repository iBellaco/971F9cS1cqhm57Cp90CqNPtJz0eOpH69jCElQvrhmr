import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

# Let's find where `@Composable\nfun tr(key: String): String {` starts
match = re.search(r'(@Composable\s*fun tr\(key: String\): String \{)', content)
if not match:
    print("Not found composable tr")
    exit(1)

pos = match.start(1)

# Now go backwards from pos to find the `val itemNamesEnToEs = ...`
# Actually, I'll just clear EVERYTHING between `val translations = mapOf(` and `@Composable` and write it properly? No, that's 2500 lines.
# I'll find where `val itemNamesEsToEn` starts.
# We will just replace everything from `val itemNamesEsToEn` to `@Composable` with the correct code.

start_code = content.find("val itemNamesEsToEn = translations")
if start_code != -1:
    content = content[:start_code] + """
val itemNamesEsToEn = translations["en"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEsToPt = translations["pt"]?.entries?.associate { (k, v) -> v to k } ?: emptyMap()
val itemNamesEnToEs = itemNamesEsToEn.entries.associate { (k, v) -> v to k }

""" + content[pos:]

# Now let's clean up any dangling `)val itemNamesEnToEs = itemNamesEsToEn.entries.associate {` that might be lingering.
content = re.sub(r'\)val itemNamesEnToEs = itemNamesEsToEn\.entries\.associate \{\s*".*?" to ".*?",.*?(?=@Composable)', '', content, flags=re.DOTALL)

# Let's also check if there is any garbage just before `val itemNamesEsToEn`.
# Ensure the map closes properly.
# We can find `    "pt" to mapOf(` and just manually balance the brackets?

with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
    f.write(content)

print("done")
