with open('phys_items.txt', 'r', encoding='utf-8') as f:
    web_items = set([line.strip().lower() for line in f.readlines()[1:]])

with open('current_phys.txt', 'r', encoding='utf-8') as f:
    current_items = f.read()

# Try to match the english names in the current items. Our current items might have English IDs (e.g. blade_of_the_ruined_king) or names.
import re
current_ids = re.findall(r'\(([^)]+)\)', current_items)
current_ids_set = set(current_ids)

# We need to map web item names to our ID format to see if we have them
missing = []
for web_item in web_items:
    # basic id format
    web_id = web_item.replace(' ', '_').replace("'", "_").replace('’', '_').replace("-", "_").lower()
    
    # Check if web_id is in current_ids_set or close to it
    if web_id == "dominik_s_regards":
        web_id = "lord_dominik_s_regards"
    
    if web_id not in current_ids_set:
        missing.append((web_item, web_id))

print(f"Missing items ({len(missing)}):")
for name, wid in missing:
    print(f"- {name} (ID: {wid})")
