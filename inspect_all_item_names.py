import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

blocks = text.split("add(WildRiftItem(")
for idx, b in enumerate(blocks[1:], 1):
    id_m = re.search(r'id\s*=\s*"([^"]+)"', b)
    name_m = re.search(r'name\s*=\s*"([^"]+)"', b)
    cat_m = re.search(r'category\s*=\s*"([^"]+)"', b)
    stats_m = re.search(r'stats\s*=\s*"((?:[^"\\]|\\.)*)"', b)
    gold_m = re.search(r'goldCost\s*=\s*(\d+)', b)
    print(f"{idx}. [{cat_m.group(1)}] {name_m.group(1)} ({id_m.group(1)}) - {gold_m.group(1)}G | stats: {stats_m.group(1)[:40]}")

