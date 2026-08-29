import json, sys

with open('scraped_builds_list.json', 'r') as f:
    overview = json.load(f)

with open('detailed_builds.json', 'r') as f:
    details = json.load(f)

def clean_item_name(name):
    # Basic mapping or just uppercase first letter
    return name.title().replace(" De ", " de ").replace(" Del ", " del ").replace(" La ", " la ")

def process_build(build_path):
    build_data = details.get(build_path, {})
    items_raw = build_data.get('items', [])
    items = []
    # Sort items by slot or just take as is. Usually they are in order.
    # Exclude boots enchantment if it is separate, but we want 6 items.
    for i in items_raw:
        if i.get('enchantment', False):
            pass # Keep it, but usually WR has 5 items + 1 boots
        items.append(clean_item_name(i['name']))
        
    spells_raw = build_data.get('spells', [])
    spells = []
    for s in spells_raw:
        n = s['name'].title()
        if n not in spells:
            spells.append(n)
            
    runes = build_data.get('keystone', {}).get('name', 'Conquistador').title()
    return items, runes, spells

def patch_file(filename):
    with open(filename, 'r') as f:
        champs = json.load(f)
        
    for champ in champs:
        slug = champ['name'].lower().replace(" ", "-").replace("'", "").replace(".", "")
        if slug == "nunu-&-willump": slug = "nunu-y-willump"
        if slug == "aurelion-sol": slug = "aurelion-sol"
        
        builds_list = overview.get(slug, [])
        if len(builds_list) > 0:
            b1_path = builds_list[0].get('path')
            i1, r1, s1 = process_build(b1_path)
            champ['coreItems'] = i1[:8]
            champ['recommendedRunes'] = r1
            champ['recommendedSpells'] = s1[:2]
            
            if len(builds_list) > 1:
                b2_path = builds_list[1].get('path')
                i2, r2, s2 = process_build(b2_path)
            else:
                # generate criteria based on b1
                i2, r2, s2 = list(reversed(i1)), r1, s1
                
            champ['situationalItems'] = i2[:8]
            champ['build2Runes'] = r2
            champ['build2Spells'] = s2[:2]
        else:
            # fallback
            champ['build2Runes'] = champ.get('recommendedRunes', '')
            champ['build2Spells'] = champ.get('recommendedSpells', [])

    with open(filename, 'w') as f:
        json.dump(champs, f, indent=2)

patch_file('app/src/main/res/raw/champions_part1.json')
patch_file('app/src/main/res/raw/champions_part2.json')
print("Patched JSONs")
