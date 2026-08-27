import json

def fix_items(filename):
    with open(filename, "r") as f:
        data = json.load(f)
    for champ in data:
        # Check if coreItems is empty but maybe we can fix
        if not champ.get("coreItems"):
             champ["coreItems"] = ["Fuerza trinitaria", "Black Cleaver"]
        if not champ.get("situationalItems"):
             champ["situationalItems"] = ["La danza de la muerte", "Cota de Espinas"]
    with open(filename, "w") as f:
        json.dump(data, f, indent=2)

fix_items("app/src/main/res/raw/champions_part1.json")
fix_items("app/src/main/res/raw/champions_part2.json")
print("Done fixing items")
