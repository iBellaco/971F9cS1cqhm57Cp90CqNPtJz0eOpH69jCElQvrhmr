import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = []
current_name = None
current_cat = None
current_url = None

for line in content.split('\n'):
    name_match = re.search(r'name\s*=\s*"([^"]+)"', line)
    cat_match = re.search(r'category\s*=\s*"([^"]+)"', line)
    url_match = re.search(r'iconUrl\s*=\s*"([^"]+)"', line)
    
    if name_match:
        current_name = name_match.group(1)
    if cat_match:
        current_cat = cat_match.group(1)
    if url_match:
        current_url = url_match.group(1)
        if current_name and current_cat and current_url:
            if current_cat == "Básicos":
                items.append((current_name, current_url))
            # Reset after finding a complete item (usually iconUrl is last or near last, but just to be safe we reset everything if we hit a new item... actually let's just reset when we process the url)
            current_name = None
            current_cat = None
            current_url = None

print("| Objeto | Icono | URL |")
print("|--------|-------|-----|")
for name, url in items:
    print(f"| {name} | <img src='{url}' width='40' /> | `{url}` |")

