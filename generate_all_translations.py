import re
import os

with open('app/src/main/java/com/example/util/Translator.kt', 'r', encoding='utf-8') as f:
    translator_code = f.read()

# Parse existing mapOf in translations["pt"] and translations["en"]
pt_match = re.search(r'"pt"\s*to\s*mapOf\((.*?)\),\s*"en"', translator_code, re.DOTALL)
en_match = re.search(r'"en"\s*to\s*mapOf\((.*?)\)\s*\)', translator_code, re.DOTALL)

def parse_pairs(block):
    pairs = {}
    for line in block.split('\n'):
        m = re.match(r'\s*"([^"\\]*(?:\\.[^"\\]*)*)"\s*to\s*"([^"\\]*(?:\\.[^"\\]*)*)"', line)
        if m:
            pairs[m.group(1)] = m.group(2)
    return pairs

existing_pt = parse_pairs(pt_match.group(1)) if pt_match else {}
existing_en = parse_pairs(en_match.group(1)) if en_match else {}

all_keys = set()
for root, _, files in os.walk('app/src/main/java'):
    for file in files:
        if file.endswith('.kt') and file != 'Translator.kt':
            with open(os.path.join(root, file), 'r', encoding='utf-8') as f:
                code = f.read()
                calls = re.findall(r'tr\("([^"]+)"\)', code)
                all_keys.update(calls)

print(f"Total keys extracted from app: {len(all_keys)}")
print(f"Existing PT: {len(existing_pt)}, Existing EN: {len(existing_en)}")

missing_in_en = {k for k in all_keys if k not in existing_en}
missing_in_pt = {k for k in all_keys if k not in existing_pt}

print(f"Missing in EN: {len(missing_in_en)}")
print(f"Missing in PT: {len(missing_in_pt)}")

with open('missing_keys.txt', 'w', encoding='utf-8') as f:
    for k in sorted(missing_in_en | missing_in_pt):
        f.write(f"{k}\n")
print("Written missing_keys.txt")
