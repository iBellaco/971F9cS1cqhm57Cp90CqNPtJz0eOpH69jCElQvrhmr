import json

def patch_file(filename):
    with open(filename, "r") as f:
        data = json.load(f)
    for champ in data:
        for skill in champ.get("skills", []):
            if not skill.get("description"):
                skill["description"] = "Descripción de la habilidad de " + champ["name"]
    with open(filename, "w") as f:
        json.dump(data, f, indent=2)

patch_file("app/src/main/res/raw/champions_part1.json")
patch_file("app/src/main/res/raw/champions_part2.json")
print("Done patching skills")
