import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r') as f:
    text = f.read()

items = re.findall(r'add\(WildRiftItem\("[^"]+", "([^"]+)",', text)
print(items[:100])
