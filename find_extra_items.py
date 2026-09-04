with open('phys_items.txt', 'r', encoding='utf-8') as f:
    web_items = set([line.strip().lower() for line in f.readlines()[1:]])

with open('current_phys.txt', 'r', encoding='utf-8') as f:
    current_lines = f.readlines()[1:]

import re
extra = []

# Create a normalized set of web item IDs/names
web_normalized = set()
for wi in web_items:
    wid = wi.replace(' ', '_').replace("'", "_").replace('’', '_').replace("-", "_").lower()
    if wid == "dominik_s_regards": wid = "lord_dominik_s_regards"
    web_normalized.add(wid)

for line in current_lines:
    match = re.search(r'\(([^)]+)\)', line)
    if match:
        cid = match.group(1)
        if cid not in web_normalized:
            extra.append(line.strip())

print(f"Extra items ({len(extra)}):")
for item in extra:
    print(item)
