import json

with open('app/src/main/assets/translations_en.json', 'r', encoding='utf-8') as f:
    en = json.load(f)
    
untranslated = []
for k, v in en.items():
    if k == v and len(k) > 30 and ' ' in k:
        untranslated.append(k)

print(repr(untranslated))
