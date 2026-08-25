import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

urls = re.findall(r'iconUrl\s*=\s*"([^"]+)"', content)
print(f"Total item URLs: {len(urls)}")
empty = [u for u in urls if not u.strip()]
print(f"Empty URLs: {len(empty)}")

