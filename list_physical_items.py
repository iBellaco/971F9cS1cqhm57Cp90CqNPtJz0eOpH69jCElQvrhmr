import re

file_path = 'app/src/main/java/com/example/data/WildRiftItemsData.kt'
with open(file_path, 'r') as f:
    content = f.read()

items = []
current_item = {}

for line in content.split('\n'):
    line = line.strip()
    if line.startswith('WildRiftItem('):
        current_item = {}
    elif line.startswith('id = '):
        current_item['id'] = line.split('"')[1]
    elif line.startswith('name = '):
        current_item['name'] = line.split('"')[1]
    elif line.startswith('category = '):
        current_item['category'] = line.split('"')[1]
        if current_item['category'] == "Objetos con Daños Físicos":
            items.append(current_item)

print(f"Total physical items: {len(items)}")
for item in items:
    print(f"- {item['name']} ({item['id']})")
