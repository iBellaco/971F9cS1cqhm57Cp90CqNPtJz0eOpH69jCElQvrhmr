import json

def update_champs():
    files = ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']
    
    updates = {
        'aatrox': {
            'items': ["Eclipse", "Fuerza de trinidad", "El rencor de Serylda", "Calibrador de Sterak", "La danza de la muerte"],
            'bootBase': "Botas blindadas",
            'bootUpgrade': "Cimitarra mercurial",
            'runes': "Conquistador,Coraje del coloso,Fuerzas renovadas,Sobrecrecimiento,Impacto repentino",
            'spells': ["Destello", "Prender"]
        },
        'yone': {
            'items': ["Hoja del rey arruinado", "La sanguinaria", "Filo del infinito", "Recordatorio mortal", "Ángel custodio"],
            'bootBase': "Grebas de berserker",
            'bootUpgrade': "Cimitarra mercurial",
            'runes': "Garras del inmortal,Brutal,Golpe de gracia,Leyenda: Presteza,Sobrecrecimiento",
            'spells': ["Destello", "Prender"]
        },
        'gwen': {
            'items': ["Creagrietas", "Diente de Nashor", "Sombrero mortífero de Rabadon", "Báculo del vacío", "Orbe del infinito"],
            'bootBase': "Botas de maná",
            'bootUpgrade': "Estasis",
            'runes': "Conquistador,Brutal,Golpe de gracia,Leyenda: Presteza,Ataque Potenciado",
            'spells': ["Fantasmal", "Prender"]
        },
        'smolder': {
            'items': ["Saqueador de esencias", "Hidra voraz", "Fuerza de trinidad", "Lanza de Shojin", "Ángel custodio"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Estasis",
            'runes': "Pies veloces,Brutal,Verdugo de gigantes,Leyenda: Linaje,Banda de maná",
            'spells': ["Destello", "Barrera"]
        },
        'viego': {
            'items': ["Fuerza de trinidad", "Hoja del rey arruinado", "Recordatorio mortal", "Filo del infinito", "La sanguinaria"],
            'bootBase': "Botas blindadas",
            'bootUpgrade': "Estasis",
            'runes': "Conquistador,Brutal,Golpe de gracia,Leyenda: Presteza,Impacto repentino",
            'spells': ["Destello", "Aplastar"]
        },
        'ezreal': {
            'items': ["Manamune", "Fuerza de trinidad", "El rencor de Serylda", "Lanza de Shojin", "Hoja del rey arruinado"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Cinturón de cohetes",
            'runes': "Conquistador,Brutal,Verdugo de gigantes,Leyenda: Linaje,Revestimiento de huesos",
            'spells': ["Destello", "Extenuación"]
        },
        'kaisa': {
            'items': ["Explosivo magnético", "Espadafuria de Guinsoo", "Terminus", "Huracán de Runaan", "Al final del ingenio"],
            'bootBase': "Grebas de berserker",
            'bootUpgrade': "Estasis",
            'runes': "Compás letal,Brutal,Golpe de gracia,Leyenda: Linaje,Revestimiento de huesos",
            'spells': ["Destello", "Fantasmal"]
        },
        'zed': {
            'items': ["El cuchillo fantasma de Youmuu", "Colmillo de serpiente", "El rencor de Serylda", "Lanza de Shojin", "Filo de la noche"],
            'bootBase': "Botas jonias de la lucidez",
            'bootUpgrade': "Cinturón de cohetes",
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
                    b = champ['builds'][0]
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
