import json

champs = []
try:
    with open('app/src/main/res/raw/champions_part1.json', 'r') as f:
        champs.extend(json.load(f))
except Exception as e:
    print("Err1", e)

try:
    with open('app/src/main/res/raw/champions_part2.json', 'r') as f:
        champs.extend(json.load(f))
except Exception as e:
    print("Err2", e)

print("Total parsed:", len(champs))
for i in range(min(5, len(champs))):
    print(champs[i].get("id"), champs[i].get("avatarUrl"))
