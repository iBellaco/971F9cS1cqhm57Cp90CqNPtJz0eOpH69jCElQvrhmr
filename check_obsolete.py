import json

with open('valid_items.txt') as f:
    valid_items = set([x.strip().lower() for x in f.readlines()])

obsolete = set()

def check_file(filename):
    with open(filename) as f:
        data = json.load(f)
    for champ in data:
        for it in champ.get('coreItems', []):
            if it.strip().lower() not in valid_items:
                obsolete.add(it)
        for it in champ.get('situationalItems', []):
            if it.strip().lower() not in valid_items:
                obsolete.add(it)

check_file('app/src/main/res/raw/champions_part1.json')
check_file('app/src/main/res/raw/champions_part2.json')

for item in sorted(obsolete):
    print(item)
