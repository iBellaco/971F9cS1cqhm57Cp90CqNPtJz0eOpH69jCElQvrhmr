import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    code = f.read()

# Let's count how many items exist
items = re.findall(r'WildRiftItem\s*\(\s*id\s*=\s*"([^"]+)",\s*name\s*=\s*"([^"]+)",\s*category\s*=\s*"([^"]+)",\s*goldCost\s*=\s*(\d+),\s*stats\s*=\s*"([^"]*)",\s*passive\s*=\s*"([^"]*)",\s*iconUrl\s*=\s*"([^"]*)"', code)

print(f"Total items parsed with strict regex: {len(items)}")

# Let's see if some passives have multiple lines
items_blocks = code.split("add(WildRiftItem(")
print(f"Total item blocks: {len(items_blocks) - 1}")

