import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = re.findall(r'add\(WildRiftItem\(\s*id\s*=\s*"[^"]+",\s*name\s*=\s*"([^"]+)",\s*category\s*=\s*ItemCategory\.BASIC.*?(?:passive\s*=.*?)?iconUrl\s*=\s*"([^"]+)"', content, re.DOTALL)

for name, icon in items:
    print(f"- **{name}**: {icon}")
