import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace('fun getItemIconByName(name: String): String?', 'fun getItemIconByName(name: String): String')
content = content.replace('return list.find { it.name.equals(name, ignoreCase = true) }?.iconUrl', 'return list.find { it.name.equals(name, ignoreCase = true) }?.iconUrl ?: ""')

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(content)

