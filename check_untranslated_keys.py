import os, re, json

with open("app/src/main/assets/translations_en.json") as f:
    en_dict = json.load(f)

with open("app/src/main/assets/translations_pt.json") as f:
    pt_dict = json.load(f)

print(f"EN keys count: {len(en_dict)}")
print(f"PT keys count: {len(pt_dict)}")

# Let's search all tr("...") calls in app/src/main/java
tr_pattern = re.compile(r'tr\("([^"]+)"\)|trStr\([^,]+,\s*"([^"]+)"\)')

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

print(f"Total distinct tr() keys found in code: {len(all_tr_keys)}")

missing_en = []
missing_pt = []
for k in all_tr_keys:
    if k not in en_dict:
        missing_en.append(k)
    if k not in pt_dict:
        missing_pt.append(k)

print(f"Missing in EN ({len(missing_en)}):")
for k in sorted(missing_en)[:30]:
    print("  -", repr(k))

print(f"\nMissing in PT ({len(missing_pt)}):")
for k in sorted(missing_pt)[:30]:
    print("  -", repr(k))
