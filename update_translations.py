import json

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
runes = load_json('app/src/main/assets/runes.json')

for r in runes:
    # Adding names
    name = r['name']
    if name not in en:
        en[name] = name + " (EN)"
    if name not in pt:
        pt[name] = name + " (PT)"
    
    # Adding descriptions
    desc = r['description']
    if desc not in en:
        en[desc] = desc + " (EN)"
    if desc not in pt:
        pt[desc] = desc + " (PT)"
        
    # Adding categories
    tree = r['tree']
    if tree not in en:
        en[tree] = tree + " (EN)"
    if tree not in pt:
        pt[tree] = tree + " (PT)"

save_json('app/src/main/assets/translations_en.json', en)
save_json('app/src/main/assets/translations_pt.json', pt)

print("Translations updated!")
