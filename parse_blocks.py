import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

blocks = text.split("add(WildRiftItem(")
for idx, b in enumerate(blocks[1:], 1):
    id_m = re.search(r'id\s*=\s*"([^"]+)"', b)
    name_m = re.search(r'name\s*=\s*"([^"]+)"', b)
    cat_m = re.search(r'category\s*=\s*"([^"]+)"', b)
    gold_m = re.search(r'goldCost\s*=\s*(\d+)', b)
    # stats
    stats_m = re.search(r'stats\s*=\s*"((?:[^"\\]|\\.)*)"', b)
    # passive
    passive_m = re.search(r'passive\s*=\s*"((?:[^"\\]|\\.)*)"', b, re.DOTALL)
    # iconUrl
    icon_m = re.search(r'iconUrl\s*=\s*"([^"]+)"', b)
    
    if not (id_m and name_m and cat_m and gold_m and stats_m and passive_m and icon_m):
        print(f"Failed on block {idx}: id={id_m}, name={name_m}, stats={bool(stats_m)}, passive={bool(passive_m)}, icon={bool(icon_m)}")
        print("--- CONTENT ---")
        print(b[:300])

