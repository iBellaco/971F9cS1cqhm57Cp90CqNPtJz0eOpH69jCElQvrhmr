import re, json

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

# Let's extract each WildRiftItem block
pattern = re.compile(r'WildRiftItem\(\s*id\s*=\s*"([^"]+)",\s*name\s*=\s*"([^"]+)",\s*category\s*=\s*"([^"]+)",\s*goldCost\s*=\s*(\d+),\s*stats\s*=\s*"([^"]*)",\s*passive\s*=\s*"([^"]*)",\s*iconUrl\s*=\s*"([^"]*)"', re.DOTALL)

items = pattern.findall(text)
print(f"Parsed {len(items)} items")
if len(items) < 204:
    # let's find which ones failed to match
    blocks = text.split("add(WildRiftItem(")
    print(f"Total blocks: {len(blocks)-1}")

