import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = []
current_item = {}

for line in content.split('\n'):
    line = line.strip()
    if line.startswith("add(WildRiftItem("):
        current_item = {}
        continue
    
    name_match = re.search(r'name\s*=\s*"([^"]+)"', line)
    cat_match = re.search(r'category\s*=\s*"([^"]+)"', line)
    cost_match = re.search(r'goldCost\s*=\s*(\d+)', line)
    stats_match = re.search(r'stats\s*=\s*"([^"]*)"', line)
    passive_match = re.search(r'passive\s*=\s*"([^"]+)"', line)
    url_match = re.search(r'iconUrl\s*=\s*"([^"]+)"', line)
    
    if name_match: current_item['name'] = name_match.group(1)
    if cat_match: current_item['category'] = cat_match.group(1)
    if cost_match: current_item['goldCost'] = cost_match.group(1)
    if stats_match: current_item['stats'] = stats_match.group(1)
    if passive_match: current_item['passive'] = passive_match.group(1)
    if url_match: 
        current_item['iconUrl'] = url_match.group(1)
        if current_item.get('category') == "Básicos":
            items.append(current_item)

for item in items:
    # Adding a markdown link so the user can click and open the icon
    name = item.get('name', 'N/A')
    url = item.get('iconUrl', '')
    cost = item.get('goldCost', 'N/A')
    stats = item.get('stats', 'Ninguna')
    print(f"- **[{name}]({url})** (Haz clic para ver el ícono)")
    print(f"  - **Costo:** {cost} 🟡")
    print(f"  - **Estadísticas:** {stats}")

