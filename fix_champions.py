import json

files = ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']

for file_path in files:
    with open(file_path, 'r', encoding='utf-8') as f:
        champions = json.load(f)

    for champ in champions:
        if champ['id'] in ['aatrox', 'smolder']:
            for build in champ.get('builds', []):
                if 'name' in build:
                    build['title'] = build.pop('name')
                if 'boots' in build:
                    build['bootBase'] = build.pop('boots')
                if 'bootEnchant' in build:
                    build['bootUpgrade'] = build.pop('bootEnchant')
                if 'summonerSpells' in build:
                    build['spells'] = build.pop('summonerSpells')
                if 'runes' in build and isinstance(build['runes'], list):
                    build['runes'] = ", ".join(build['runes'])

    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(champions, f, ensure_ascii=False, indent=2)

print("Fixed builds!")
