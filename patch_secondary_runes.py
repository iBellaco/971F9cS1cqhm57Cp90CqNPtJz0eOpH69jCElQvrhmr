import json

file_path = 'app/src/main/assets/runes.json'

with open(file_path, 'r', encoding='utf-8') as f:
    runes = json.load(f)

new_runes = [
    {
        "id": "triumph",
        "name": "Triunfo",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Las eliminaciones de campeones restauran un 10% de la vida perdida y un 10% del maná y la energía máximos. Además, otorga 35 de velocidad de movimiento durante 2 s.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/triumph.png"
    },
    {
        "id": "brutal",
        "name": "Fervor de Batalla",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Obtienes un 1,4% de amplificación de daño de las habilidades básicas acumulable cada 1 s mientras estás en combate con un campeón. Se acumula hasta 3 veces y solo tiene efecto contra campeones enemigos.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png"
    },
    {
        "id": "giant_slayer",
        "name": "Derribado",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Tus ataques infligen un 6,5% de daño adicional a campeones enemigos con más del 60% de vida.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/cutdown/cutdown.png"
    },
    {
        "id": "coup_de_grace",
        "name": "Golpe de Gracia",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Inflige un 8% de daño adicional a campeones enemigos con menos del 40% de vida.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/coupdegrace/coupdegrace.png"
    },
    {
        "id": "legend_alacrity",
        "name": "Leyenda: Presteza",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Otorga un 3% de velocidad de ataque. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 18% de velocidad de ataque adicional.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendalacrity/legendalacrity.png"
    },
    {
        "id": "legend_tenacity",
        "name": "Leyenda: Tenacidad",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Otorga un 3% de tenacidad y un 3% de resistencia a las ralentizaciones. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 15% de tenacidad adicional y un 20% de resistencia a las ralentizaciones.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendtenacity/legendtenacity.png"
    },
    {
        "id": "legend_bloodline",
        "name": "Leyenda: Linaje",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Otorga un 1% de omnisucción. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 7% de omnisucción.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendbloodline/legendbloodline.png"
    },
    {
        "id": "last_stand",
        "name": "Último Esfuerzo",
        "tree": "Precisión",
        "type": "Secundaria",
        "description": "Cuando tu vida está por debajo del 60%, los ataques asestados a campeones enemigos infligen un 5%-11% de daño adicional. Otorga el daño adicional máximo cuando la vida es inferior al 30%.",
        "iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/laststand/laststand.png"
    }
]

# Merge logic (if it exists, update it; otherwise append)
for nr in new_runes:
    exists = False
    for i, r in enumerate(runes):
        if r['id'] == nr['id']:
            runes[i] = nr
            exists = True
            break
    if not exists:
        runes.append(nr)

with open(file_path, 'w', encoding='utf-8') as f:
    json.dump(runes, f, ensure_ascii=False, indent=2)

print("Added precision runes.")
