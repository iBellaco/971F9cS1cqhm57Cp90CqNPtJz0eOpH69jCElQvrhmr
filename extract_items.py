import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Find the list of items
items_match = re.findall(r'Item\((.*?)\)', content, re.DOTALL)

physical_items = []

for item_str in items_match:
    if '"Objetos con Daños Físicos"' in item_str:
        name_match = re.search(r'name\s*=\s*"([^"]+)"', item_str)
        icon_match = re.search(r'iconUrl\s*=\s*"([^"]+)"', item_str)
        cost_match = re.search(r'goldCost\s*=\s*(\d+)', item_str)
        stats_match = re.search(r'stats\s*=\s*"([^"]+)"', item_str)
        
        name = name_match.group(1) if name_match else "N/A"
        icon = icon_match.group(1) if icon_match else "N/A"
        cost = cost_match.group(1) if cost_match else "N/A"
        stats = stats_match.group(1) if stats_match else "N/A"
        
        physical_items.append({
            'name': name,
            'icon': icon,
            'cost': cost,
            'stats': stats
        })

print(f"Total encontrados: {len(physical_items)}")
for item in physical_items:
    print(f"- **{item['name']}** (Oro: {item['cost']})\n  - Stats: {item['stats']}\n  - Icono: {item['icon']}")
