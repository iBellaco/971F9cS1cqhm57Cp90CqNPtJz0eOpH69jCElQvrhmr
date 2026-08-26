import json

with open("app/src/main/assets/translations_en.json") as f:
    en = json.load(f)

print("Sample keys from translations_en.json:")
for k in list(en.keys())[:30]:
    print(" ", repr(k), "->", repr(en[k]))
