import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = []
current_name = None

for line in content.split('\n'):
    name_match = re.search(r'name\s*=\s*"([^"]+)"', line)
    passive_match = re.search(r'passive\s*=\s*"([^\\"]+)', line)
    
    if name_match:
        current_name = name_match.group(1)
    if passive_match and current_name:
        en_name = passive_match.group(1).strip()
        items.append((current_name, en_name))
        current_name = None

print("val itemNamesEsToEn = mapOf(")
for es, en in items:
    # Escape quotes if necessary
    es = es.replace('"', '\\"')
    en = en.replace('"', '\\"')
    print(f'    "{es}" to "{en}",')
print(")")

