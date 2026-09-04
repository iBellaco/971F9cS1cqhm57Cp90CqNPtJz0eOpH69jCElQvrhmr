import json

updates = {
    'kai_sa': {
        'items': ["Matakrakens", "Hoja de furia de Guinsoo", "Terminus", "El huracán de Runaan", "Anochecer y amanecer"],
        'bootBase': "Grebas de berserker",
        'bootUpgrade': "Grebas de metal",
        'runes': "Compás Letal, Impacto Repentino, Golpe de Gracia, Leyenda: Linaje, Revestimiento de Huesos",
        'spells': ["Destello", "Limpiar"]
    }
}

files = ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']

for file_path in files:
    with open(file_path, 'r', encoding='utf-8') as f:
        champions = json.load(f)

    changed = False
    for champ in champions:
        cid = champ['id']
        if cid in updates:
            upd = updates[cid]
            if len(champ.get('builds', [])) == 0:
                champ['builds'] = [{}]
            
            b = champ['builds'][0]
            b['title'] = b.get('title', "Build Óptima")
            b['items'] = upd['items']
            b['bootBase'] = upd['bootBase']
            b['bootUpgrade'] = upd['bootUpgrade']
            b['runes'] = upd['runes']
            b['spells'] = upd['spells']
            changed = True
            print(f"Updated {cid}")

    if changed:
        with open(file_path, 'w', encoding='utf-8') as f:
            json.dump(champions, f, ensure_ascii=False, indent=2)

