import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

names = re.findall(r'name\s*=\s*"([^"]+)"', text)
print(names)
