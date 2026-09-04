import json

updates = {
    'yone': {
        'items': ["Espada del Rey Arruinado", "Sanguinaria", "Cortasendas", "Recordatorio mortal", "Hojas rápidas de Navori"],
        'bootBase': "Grebas de berserker",
        'bootUpgrade': "Grebas de metal",
        'runes': "Garras del Inmortal, Impacto Repentino, Golpe de Gracia, Leyenda: Presteza, Fuerzas Renovadas",
        'spells': ["Destello", "Prender"]
    },
    'viego': {
        'items': ["Saqueador de esencia", "Recordatorio mortal", "Muramana", "Borde infinito", "Sanguinaria"],
        'bootBase': "Grebas de berserker",
        'bootUpgrade': "Grebas de metal",
        'runes': "Conquistador, Impacto Repentino, Golpe de Gracia, Leyenda: Presteza, Concentración Absoluta",
        'spells': ["Destello", "Aplastar"]
    },
    'gwen': {
        'items': ["Vara de las edades", "Anochecer y amanecer", "Malignance", "Gorro de muerte del Rabadon", "Bastón vacío"],
        'bootBase': "Botas de maná",
        'bootUpgrade': "Botas del lanzahechizos",
        'runes': "Pies Veloces, Triunfo, Fuerzas Renovadas, Perseverancia, Leyenda: Linaje",
        'spells': ["Fantasmal", "Prender"]
    },
    'ezreal': {
        'items': ["Muramana", "Fuerza trinitaria", "El rencor de Serylda", "Lanza de Shojin", "Espada del Rey Arruinado"],
        'bootBase': "Grebas codiciosas",
        'bootUpgrade': "Botas inmortales",
        'runes': "Conquistador, Impacto Repentino, Golpe de Gracia, Leyenda: Presteza, Revestimiento de Huesos",
        'spells': ["Destello", "Barrera"]
    },
    'kaisa': {
        'items': ["Matakrakens", "Hoja de furia de Guinsoo", "Terminus", "El huracán de Runaan", "Anochecer y amanecer"],
        'bootBase': "Grebas de berserker",
        'bootUpgrade': "Grebas de metal",
        'runes': "Compás Letal, Impacto Repentino, Golpe de Gracia, Leyenda: Linaje, Revestimiento de Huesos",
        'spells': ["Destello", "Limpiar"]
    },
    'zed': {
        'items': ["Colmillo de serpiente", "Fuerza trinitaria", "El rencor de Serylda", "Lanza de Shojin", "Filo de la noche"],
        'bootBase': "Botas dinámicas",
        'bootUpgrade': "Botas quebrantarmaduras",
        'runes': "Conquistador, Fervor de Batalla, Golpe de Gracia, Leyenda: Presteza, Trascendencia",
        'spells': ["Destello", "Aplastar"]
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
            
            # Update the first build
            b = champ['builds'][0]
            b['title'] = b.get('title', "Build Óptima")
            b['items'] = upd['items']
            b['bootBase'] = upd['bootBase']
            b['bootUpgrade'] = upd['bootUpgrade']
            b['runes'] = upd['runes']
            b['spells'] = upd['spells']
            
            # Clear situational elements to avoid confusion if needed, or leave them.
            # We'll leave them to maintain schema intact.
            
            changed = True
            print(f"Updated {cid}")

    if changed:
        with open(file_path, 'w', encoding='utf-8') as f:
            json.dump(champions, f, ensure_ascii=False, indent=2)

