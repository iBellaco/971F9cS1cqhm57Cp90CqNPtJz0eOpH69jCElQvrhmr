import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = []
current_name = None
current_url = None

for line in content.split('\n'):
    name_match = re.search(r'name\s*=\s*"([^"]+)"', line)
    url_match = re.search(r'iconUrl\s*=\s*"([^"]+)"', line)
    
    if name_match:
        current_name = name_match.group(1)
    
    if url_match:
        current_url = url_match.group(1)
        if current_name and current_url:
            items.append((current_name, current_url))
            current_name = None
            current_url = None

print("| Objeto | Icono | URL |")
print("|--------|-------|-----|")
for name, url in items:
    print(f"| {name} | <img src='{url}' width='40' /> | `{url}` |")

