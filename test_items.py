import json
with open("app/src/main/res/raw/champions_part1.json") as f:
    data = json.load(f)
for champ in data:
    if champ["id"] == "aatrox":
        print("Aatrox:", list(champ.keys()))
