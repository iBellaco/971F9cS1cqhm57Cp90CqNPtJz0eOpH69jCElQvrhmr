import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

# Let's see some sample items in WildRiftItemsData.kt
blocks = text.split("add(WildRiftItem(")
print(f"Total blocks: {len(blocks)-1}")

for i in range(1, min(10, len(blocks))):
    print(f"--- ITEM {i} ---")
    print(blocks[i][:400])

