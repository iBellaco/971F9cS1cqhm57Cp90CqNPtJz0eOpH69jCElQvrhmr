import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

blocks = text.split("add(WildRiftItem(")
items = []
for idx, b in enumerate(blocks[1:], 1):
    id_m = re.search(r'id\s*=\s*"([^"]+)"', b)
    name_m = re.search(r'name\s*=\s*"([^"]+)"', b)
    cat_m = re.search(r'category\s*=\s*"([^"]+)"', b)
    gold_m = re.search(r'goldCost\s*=\s*(\d+)', b)
    stats_m = re.search(r'stats\s*=\s*"((?:[^"\\]|\\.)*)"', b)
    passive_m = re.search(r'passive\s*=\s*"((?:[^"\\]|\\.)*)"', b, re.DOTALL)
    icon_m = re.search(r'iconUrl\s*=\s*"([^"]+)"', b)
    
    items.append({
        "id": id_m.group(1),
        "name": name_m.group(1),
        "category": cat_m.group(1),
        "goldCost": int(gold_m.group(1)),
        "stats": stats_m.group(1),
        "passive": passive_m.group(1),
        "iconUrl": icon_m.group(1)
    })

print(f"Loaded {len(items)} items.")
categories = set(it["category"] for it in items)
print("Categories:", categories)

# Let's check how many passives have "TIPS" or English words
has_tips = [it["name"] for it in items if "TIPS" in it["passive"]]
print(f"Items with TIPS in passive: {len(has_tips)}")
has_attack_damage = [it["name"] for it in items if "Attack Damage" in it["stats"] or "Attack Damage" in it["passive"]]
print(f"Items with 'Attack Damage' in stats or passive: {len(has_attack_damage)}")

