import re

with open('item_names_map.txt', 'r', encoding='utf-8') as f:
    map_code = f.read()

with open('app/src/main/java/com/example/util/Translator.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Insert the map before the tr function
content = content.replace("@Composable", map_code + "\n@Composable", 1)

# In the trStr function, if effectiveLang == "en", add name replacement
en_block_search = r'if \(effectiveLang == "en"\) \{'
en_block_replace = r'''if (effectiveLang == "en") {
        val itemNameEn = itemNamesEsToEn[key]
        if (itemNameEn != null) return itemNameEn
'''
content = re.sub(en_block_search, en_block_replace, content)

# In the trStr function, if effectiveLang == "pt", maybe use English names for now if pt doesn't have it?
pt_block_search = r'\} else if \(effectiveLang == "pt"\) \{'
pt_block_replace = r'''} else if (effectiveLang == "pt") {
        // Fallback to English names if no direct pt translation
        val itemNameEn = itemNamesEsToEn[key]
        if (itemNameEn != null) return itemNameEn
'''
content = re.sub(pt_block_search, pt_block_replace, content)


with open('app/src/main/java/com/example/util/Translator.kt', 'w', encoding='utf-8') as f:
    f.write(content)

