import json
import re

def load_json(filepath):
    try:
        with open(filepath, 'r', encoding='utf-8') as f:
            return json.load(f)
    except FileNotFoundError:
        return {}

def save_json(filepath, data):
    with open(filepath, 'w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

en = load_json('app/src/main/assets/translations_en.json')
pt = load_json('app/src/main/assets/translations_pt.json')

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# match name = "...", category = "...", description = "..."
names = re.findall(r'name\s*=\s*"([^"]+)"', content)
categories = re.findall(r'category\s*=\s*"([^"]+)"', content)
descriptions = re.findall(r'description\s*=\s*"([^"]+)"', content)

for n in names + categories + descriptions:
    if n not in en:
        en[n] = n + " (EN)"
    if n not in pt:
        pt[n] = n + " (PT)"

# Also add explicit categories like 'Rama', 'TODOS'
extras = ["Rama", "TODOS"]
for e in extras:
    if e not in en: en[e] = e + " (EN)"
    if e not in pt: pt[e] = e + " (PT)"

save_json('app/src/main/assets/translations_en.json', en)
save_json('app/src/main/assets/translations_pt.json', pt)

print("Translations updated from KT!")
