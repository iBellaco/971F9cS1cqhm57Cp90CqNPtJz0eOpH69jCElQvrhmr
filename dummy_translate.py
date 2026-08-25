import json

en = json.load(open('app/src/main/assets/translations_en.json'))
pt = json.load(open('app/src/main/assets/translations_pt.json'))

for k, v in en.items():
    if str(v).endswith('(EN)'):
        en[k] = k  # Just copy original text to unblock translation system, fallback will show in Spanish but without (EN) marker

for k, v in pt.items():
    if str(v).endswith('(PT)'):
        pt[k] = k

with open('app/src/main/assets/translations_en.json', 'w') as f:
    json.dump(en, f, ensure_ascii=False, indent=2)
with open('app/src/main/assets/translations_pt.json', 'w') as f:
    json.dump(pt, f, ensure_ascii=False, indent=2)

print("Translations unblocked!")
