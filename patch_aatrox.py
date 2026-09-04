import json

file_path = 'app/src/main/res/raw/champions_part1.json'

with open(file_path, 'r', encoding='utf-8') as f:
    champions = json.load(f)

for champ in champions:
    if champ['id'] == 'aatrox':
        print("Aatrox found!")
        
        # update core properties
        champ['coreItems'] = ["Eclipse", "Fuerza trinitaria", "Cimitarra mercurial", "El rencor de Serylda", "Guantelete de Sterak"]
        champ['recommendedRunes'] = "Conquistador,Coraje del coloso,Fuerzas renovadas,Perseverancia,Impacto repentino"
        champ['recommendedSpells'] = ["Destello", "Prender"]
        
        new_build = {
            "name": "Build Luchador (Trinidad)",
            "items": ["Eclipse", "Fuerza trinitaria", "Cimitarra mercurial", "El rencor de Serylda", "Guantelete de Sterak"],
            "boots": "Botas blindadas",
            "bootEnchant": "Avance blindado",
            "runes": ["Conquistador", "Coraje del coloso", "Fuerzas renovadas", "Perseverancia", "Impacto repentino"],
            "summonerSpells": ["Destello", "Prender"]
        }
        
        # Override all builds with this one for consistency based on user request
        champ['builds'] = [new_build]
        break

with open(file_path, 'w', encoding='utf-8') as f:
    json.dump(champions, f, ensure_ascii=False, indent=2)

