import re, json

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt") as f:
    text = f.read()

blocks = text.split("add(WildRiftItem(")
items = []

for idx, b in enumerate(blocks[1:], 1):
    id_m = re.search(r'id\s*=\s*"([^"]+)"', b)
    name_m = re.search(r'name\s*=\s*"([^"]+)"', b)
    cat_m = re.search(r'category\s*=\s*"([^"]+)"', b)
    gold_m = re.search(r'goldCost\s*=\s*(\d+)', b)
    stats_m = re.search(r'stats\s*=\s*"((?:[^"\\]|\\.)*)"', b)
    passive_m = re.search(r'passive\s*=\s*"((?:[^"\\]|\\.)*)"', b, re.DOTALL)
    icon_m = re.search(r'iconUrl\s*=\s*"([^"]+)"', b)
    
    items.append({
        "id": id_m.group(1),
        "name": name_m.group(1),
        "category": cat_m.group(1),
        "goldCost": int(gold_m.group(1)),
        "stats": stats_m.group(1),
        "passive": passive_m.group(1),
        "iconUrl": icon_m.group(1)
    })

print(f"Extracted {len(items)} items")

# Let's clean the passives
def clean_passive_structure(raw_passive, item_name, gold_cost):
    lines = [l.strip() for l in raw_passive.split("\\n") if l.strip()]
    if not lines:
        return "", ""
    
    # Check if there is a TIPS section
    tips_text = ""
    passive_lines = []
    
    for l in lines:
        if "TIPS:" in l or "Consejos:" in l or "Dicas:" in l:
            tips_text = l
        elif l.isdigit() and int(l) == gold_cost:
            continue
        elif l.startswith("+") and ("Damage" in l or "Health" in l or "Armor" in l or "Speed" in l or "Mana" in l or "Power" in l or "Haste" in l or "Vamp" in l or "Regen" in l or "Crítico" in l or "Ataque" in l):
            continue
        elif l.lower() == item_name.lower() or l.lower() in [
            "increases physical vamp", "revives at death", "increases attack range and damage",
            "ranged attacks hit 3 targets", "increases movement speed", "attacks deal bonus damage",
            "armor penetration (%) and reduced enemy healing", "physical damage reduces armor",
            "converts mana to attack damage", "well-rounded", "converts damage from attacks into a magic shield",
            "increases ability power", "bonus magic damage based on target's health",
            "increases attack speed and damage", "shields allies", "enhances durability",
            "health and armor", "health and magic resistance", "slows enemies on attack",
            "grants gold and health on minion execute", "grants gold on champion hit"
        ]:
            continue
        else:
            passive_lines.append(l)
    
    main_passive = "\n\n".join(passive_lines) if passive_lines else raw_passive.replace("\\n", "\n")
    return main_passive, tips_text

sample_p, sample_t = clean_passive_structure(items[0]["passive"], items[0]["name"], items[0]["goldCost"])
print("--- CLEANED PASSIVE SAMPLE (Item 0) ---")
print("Main:", sample_p)
print("Tips:", sample_t)

