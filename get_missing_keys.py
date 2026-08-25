import json

en = json.load(open('app/src/main/assets/translations_en.json'))
missing_es = []
for k, v in en.items():
    if k == v and len(k) > 1:
        missing_es.append(k)

print(json.dumps(missing_es, ensure_ascii=False, indent=2))
