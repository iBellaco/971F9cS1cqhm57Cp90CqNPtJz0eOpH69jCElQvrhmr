import json

en = json.load(open('app/src/main/assets/translations_en.json'))
pt = json.load(open('app/src/main/assets/translations_pt.json'))

# The ones that failed have the exact same string as the key in both key and value, OR they end with (EN)/(PT).
# But wait, in dummy_translate.py, I made en[k] = k. So the key equals the value.
missing_es = []
for k, v in en.items():
    if k == v and len(k) > 15: # only log long descriptions to translate
        missing_es.append(k)

print(f"Total missing: {len(missing_es)}")
for i, text in enumerate(missing_es[:20]):
    print(f"---[{i}]---\n{text}")
