# Just to be sure, I'll count the items in the DB by category
import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = re.findall(r'WildRiftItem\((.*?)\)', content, re.DOTALL)
categories = {}
for item in items:
    cat_match = re.search(r'category\s*=\s*"([^"]+)"', item)
    if cat_match:
        cat = cat_match.group(1)
        categories[cat] = categories.get(cat, 0) + 1

print(categories)
