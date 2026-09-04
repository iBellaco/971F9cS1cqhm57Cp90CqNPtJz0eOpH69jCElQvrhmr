import json

file_path = 'app/src/main/res/raw/champions_part2.json'

with open(file_path, 'r', encoding='utf-8') as f:
    champions = json.load(f)

for champ in champions:
    if champ['id'] == 'smolder':
        # We can just update the first build or all of them. Let's just give him the requested one.
        # But wait, let's see what builds he already has.
        print("Smolder found!")
        
        # We will preserve the name if it exists, or just set it
        new_build = {
            "name": "Build Híbrida / Escalar",
            "items": ["Manamune", "Fuerza trinitaria", "El rencor de Serylda", "Lanza de Shojin", "Ángel custodio"],
            "boots": "Grebas codiciosas",
            "bootEnchant": "Botas inmortales",
            "runes": ["Pies veloces", "Brutal", "Derribado", "Leyenda: Linaje", "Trascendencia"],
            "summonerSpells": ["Destello", "Fantasmal"]
        }
        
        champ['builds'] = [new_build]
        break

with open(file_path, 'w', encoding='utf-8') as f:
    json.dump(champions, f, ensure_ascii=False, indent=2)

