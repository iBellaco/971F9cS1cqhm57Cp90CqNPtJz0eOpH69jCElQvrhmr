import re

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    code = f.read()

# Let's inspect some of the items and passives to see the exact structure
blocks = code.split("add(WildRiftItem(")
print(f"Total items: {len(blocks) - 1}")

for i in range(1, 15):
    print(f"\n================ ITEM {i} ================")
    lines = blocks[i].strip().split("\n")
    for l in lines[:15]:
        print(l)

