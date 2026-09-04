import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = re.findall(r'WildRiftItem\((.*?)\)', content, re.DOTALL)
count = 0
for item in items:
    if '"Objetos con Daños Físicos"' in item:
        count += 1
        id_match = re.search(r'id\s*=\s*"([^"]+)"', item)
        name_match = re.search(r'name\s*=\s*"([^"]+)"', item)
        print(f"- {id_match.group(1)} ({name_match.group(1)})")

print(f"Total: {count}")
