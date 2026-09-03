import json

def update_champs():
    files = ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']
    
    updates = {
        'aatrox': {
            'items': ["Eclipse", "Fuerza trinitaria", "El rencor de Serylda", "Guantelete de Sterak", "La danza de la muerte"],
            'bootBase': "Botas blindadas",
            'bootUpgrade': "Cimitarra mercurial",
            'runes': "Conquistador,Coraje del coloso,Fuerzas renovadas,Sobrecrecimiento,Impacto repentino",
            'spells': ["Destello", "Prender"]
        },
        'yone': {
            'items': ["Espada del Rey Arruinado", "La sanguinaria", "Filo infinito", "Recordatorio mortal", "Ángel de la guarda"],
            'bootBase': "Grebas de berserker",
            'bootUpgrade': "Cimitarra mercurial",
            'runes': "Garras del inmortal,Brutal,Golpe de gracia,Leyenda: Presteza,Sobrecrecimiento",
            'spells': ["Destello", "Prender"]
        },
        'gwen': {
            'items': ["Hacedor de grietas", "Diente de Nashor", "Gorro de muerte del miércoles", "Bastón vacío", "Orbe infinito"],
            'bootBase': "Botas de maná",
            'bootUpgrade': "Estasis",
            'runes': "Conquistador,Brutal,Golpe de gracia,Leyenda: Presteza,Ataque Potenciado",
            'spells': ["Fantasmal", "Prender"]
        },
        'smolder': {
            'items': ["Saqueador de esencia", "Cuchilla negra", "Fuerza trinitaria", "Lanza de Shojin", "Ángel de la guarda"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Estasis",
            'runes': "Pies veloces,Brutal,Derribado,Leyenda: Linaje,Banda de maná",
            'spells': ["Destello", "Barrera"]
        },
        'viego': {
            'items': ["Fuerza trinitaria", "Espada del Rey Arruinado", "Recordatorio mortal", "Filo infinito", "La sanguinaria"],
            'bootBase': "Botas blindadas",
            'bootUpgrade': "Estasis",
            'runes': "Conquistador,Brutal,Golpe de gracia,Leyenda: Presteza,Impacto repentino",
            'spells': ["Destello", "Aplastar"]
        },
        'ezreal': {
            'items': ["Manamune", "Fuerza trinitaria", "El rencor de Serylda", "Lanza de Shojin", "Espada del Rey Arruinado"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Cinturón de cohetes hextech",
            'runes': "Conquistador,Brutal,Derribado,Leyenda: Linaje,Revestimiento de huesos",
            'spells': ["Destello", "Extenuación"]
        },
        'kaisa': {
            'items': ["Blaster magnético", "Hoja de furia de Guinsoo", "Terminus", "El huracán de Runaan", "Al filo de la cordura"],
            'bootBase': "Grebas de berserker",
            'bootUpgrade': "Estasis",
            'runes': "Compás letal,Brutal,Golpe de gracia,Leyenda: Linaje,Revestimiento de huesos",
            'spells': ["Destello", "Fantasmal"]
        },
        'zed': {
            'items': ["El cuchillo fantasma de Youmuu", "Colmillo de serpiente", "El rencor de Serylda", "Lanza de Shojin", "Filo de la noche"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Cinturón de cohetes hextech",
            'runes': "Conquistador,Brutal,Golpe de gracia,Leyenda: Presteza,Trascendencia",
            'spells': ["Destello", "Prender"]
        }
    }
    
    for file in files:
        with open(file, 'r') as f:
            data = json.load(f)
            
        modified = False
        for champ in data:
            if champ['id'] in updates:
                up = updates[champ['id']]
                champ['coreItems'] = up['items']
                champ['recommendedRunes'] = up['runes']
                champ['recommendedSpells'] = up['spells']
                
                if champ.get('builds'):
                    for b in champ['builds']:
                        b['items'] = up['items']
                        b['bootBase'] = up['bootBase']
                        b['bootUpgrade'] = up['bootUpgrade']
                        b['runes'] = up['runes']
                        b['spells'] = up['spells']
                
                modified = True
                
        if modified:
            with open(file, 'w') as f:
                json.dump(data, f, indent=2, ensure_ascii=False)

if __name__ == "__main__":
    update_champs()
