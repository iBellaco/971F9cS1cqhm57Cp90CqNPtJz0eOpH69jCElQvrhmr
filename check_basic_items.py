import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

items = re.findall(r'name\s*=\s*"([^"]+)".*?iconUrl\s*=\s*"([^"]+)"', content, re.DOTALL)
for name, url in items:
    if any(basic in name.lower() for basic in ['espada larga', 'botas', 'lagrima', 'cristal', 'tomo', 'armadura de tela', 'manto', 'daga', 'guantes', 'rubi', 'zafiro', 'long sword', 'boots', 'ruby', 'cloth', 'dagger', 'bramble']):
        print(f"Item: {name} -> URL: {url}")
