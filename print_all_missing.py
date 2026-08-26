import re, json

with open("app/src/main/assets/translations_en.json") as f:
    en_dict = json.load(f)

tr_pattern = re.compile(r'tr\("([^"]+)"\)|trStr\([^,]+,\s*"([^"]+)"\)')

import os
all_tr_keys = set()
for root, _, files in os.walk("app/src/main/java"):
    for f in files:
        if f.endswith(".kt"):
            path = os.path.join(root, f)
            with open(path) as file:
                content = file.read()
                matches = tr_pattern.findall(content)
                for m in matches:
                    k = m[0] if m[0] else m[1]
                    all_tr_keys.add(k)

missing = sorted([k for k in all_tr_keys if k not in en_dict])
print(f"Total missing: {len(missing)}")
for idx, k in enumerate(missing):
    print(f"{idx+1}. {repr(k)}")
