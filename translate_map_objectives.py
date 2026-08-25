import json

def main():
    with open('app/src/main/assets/translations_en.json', 'r', encoding='utf-8') as f:
        en = json.load(f)

    with open('app/src/main/assets/translations_pt.json', 'r', encoding='utf-8') as f:
        pt = json.load(f)

    objectives_data = [
        {
            "name": "Dragón Infernal (Fuego)",
            "name_en": "Infernal Dragon (Fire)",
            "name_pt": "Dragão Infernal (Fogo)",
            "spawnTime": "Minuto 4:00",
            "spawnTime_en": "Minute 4:00",
            "spawnTime_pt": "Minuto 4:00",
            "respawnTime": "Reaparece cada 4:00",
            "respawnTime_en": "Respawns every 4:00",
            "respawnTime_pt": "Renasce a cada 4:00",
            "buffDescription": "Otorga a todo el equipo +3% de daño de ataque y +3% de poder de habilidad acumulable.",
            "buffDescription_en": "Grants the entire team +3% Attack Damage and +3% Ability Power (stacking).",
            "buffDescription_pt": "Concede a toda a equipe +3% de Dano de Ataque e +3% de Poder de Habilidade cumulativo.",
            "tactics": "Prioriza asegurar la línea de dragón empujando oleadas 30s antes de su aparición. Ideal para composiciones de daño explosivo.",
            "tactics_en": "Prioritize securing dragon lane by pushing waves 30s before spawn. Ideal for burst damage team comps.",
            "tactics_pt": "Priorize garantir a rota do dragão empurrando tropas 30s antes do surgimento. Ideal para composições de dano explosivo."
        },
        {
            "name": "Dragón de Montaña (Tierra)",
            "name_en": "Mountain Dragon (Earth)",
            "name_pt": "Dragão da Montanha (Terra)",
            "spawnTime": "Minuto 4:00",
            "spawnTime_en": "Minute 4:00",
            "spawnTime_pt": "Minuto 4:00",
            "respawnTime": "Reaparece cada 4:00",
            "respawnTime_en": "Respawns every 4:00",
            "respawnTime_pt": "Renasce a cada 4:00",
            "buffDescription": "Otorga a todo el equipo +6% de armadura y resistencia mágica adicionales.",
            "buffDescription_en": "Grants the entire team +6% bonus Armor and Magic Resist.",
            "buffDescription_pt": "Concede a toda a equipe +6% de Armadura e Resistência Mágica adicionais.",
            "tactics": "Refuerza la línea frontal de los tanques, facilitando asedios prolongados bajo torre enemiga.",
            "tactics_en": "Bolsters tank frontline, enabling extended sieges under enemy turrets.",
            "tactics_pt": "Reforça a linha de frente dos tanques, facilitando cercos prolongados sob a torre inimiga."
        },
        {
            "name": "Dragón de los Océanos (Agua)",
            "name_en": "Ocean Dragon (Water)",
            "name_pt": "Dragão do Oceano (Água)",
            "spawnTime": "Minuto 4:00",
            "spawnTime_en": "Minute 4:00",
            "spawnTime_pt": "Minuto 4:00",
            "respawnTime": "Reaparece cada 4:00",
            "respawnTime_en": "Respawns every 4:00",
            "respawnTime_pt": "Renasce a cada 4:00",
            "buffDescription": "Restaura un 2.5% de la vida faltante cada 5 segundos a todos los miembros del equipo.",
            "buffDescription_en": "Restores 2.5% missing health every 5 seconds to all team members.",
            "buffDescription_pt": "Restaura 2,5% da vida perdida a cada 5 segundos para todos os membros da equipe.",
            "tactics": "Otorga sustain inagotable en el mapa para desgastar al rival sin necesidad de volver a base.",
            "tactics_en": "Provides endless sustain on the map to poke and wear down rivals without needing to recall.",
            "tactics_pt": "Fornece sustentação infinita no mapa para desgastar o rival sem precisar voltar à base."
        },
        {
            "name": "Dragón de Hielo (Glacial)",
            "name_en": "Ice Dragon (Hextech/Glacial)",
            "name_pt": "Dragão de Gelo (Glacial)",
            "spawnTime": "Minuto 4:00",
            "spawnTime_en": "Minute 4:00",
            "spawnTime_pt": "Minuto 4:00",
            "respawnTime": "Reaparece cada 4:00",
            "respawnTime_en": "Respawns every 4:00",
            "respawnTime_pt": "Renasce a cada 4:00",
            "buffDescription": "Otorga +7 de aceleración de habilidad a todo el equipo y crea zonas de escarcha.",
            "buffDescription_en": "Grants +7 Ability Haste to the entire team and creates frost zones.",
            "buffDescription_pt": "Concede +7 de Aceleração de Habilidade para toda a equipe e cria zonas de gelo.",
            "tactics": "Permite rotar habilidades mucho más rápido en escaramuzas y peleas por el Barón.",
            "tactics_en": "Allows abilities to rotate much faster in skirmishes and Baron teamfights.",
            "tactics_pt": "Permite usar habilidades muito mais rápido em escaramuças e lutas pelo Barão."
        },
        {
            "name": "Dragón Anciano (Elder Dragon)",
            "name_en": "Elder Dragon",
            "name_pt": "Dragão Ancião (Elder Dragon)",
            "spawnTime": "Minuto 12:00",
            "spawnTime_en": "Minute 12:00",
            "spawnTime_pt": "Minuto 12:00",
            "respawnTime": "Reaparece cada 5:00",
            "respawnTime_en": "Respawns every 5:00",
            "respawnTime_pt": "Renasce a cada 5:00",
            "buffDescription": "Ataques y habilidades queman a los rivales. Si la vida del rival cae por debajo del 15%, es ejecutado de inmediato.",
            "buffDescription_en": "Attacks and abilities burn enemies. If an enemy's health drops below 15%, they are instantly executed.",
            "buffDescription_pt": "Ataques e habilidades queimam inimigos. Se a vida do inimigo cair abaixo de 15%, é executado imediatamente.",
            "tactics": "El buff más decisivo de Wild Rift en el juego tardío. Asegura visión perimetral con centinelas antes de iniciar.",
            "tactics_en": "The most game-deciding buff in Wild Rift late game. Secure perimeter vision with wards before engaging.",
            "tactics_pt": "O bônus mais decisivo do Wild Rift no fim de jogo. Garanta visão no perímetro com sentinelas antes de iniciar."
        },
        {
            "name": "Heraldo de la Grieta (Rift Herald)",
            "name_en": "Rift Herald",
            "name_pt": "Arauto do Vale (Rift Herald)",
            "spawnTime": "Minuto 5:00",
            "spawnTime_en": "Minute 5:00",
            "spawnTime_pt": "Minuto 5:00",
            "respawnTime": "Solo aparece 1 por partida",
            "respawnTime_en": "Only spawns 1 per match",
            "respawnTime_pt": "Apenas 1 por partida",
            "buffDescription": "Al recoger el Ojo del Heraldo, permite invocar al Heraldo para embestir y destruir placas de torretas enemigas.",
            "buffDescription_en": "Picking up the Eye of the Herald allows summoning the Herald to charge and destroy enemy turret platings.",
            "buffDescription_pt": "Ao coletar o Olho do Arauto, permite invocar o Arauto para avançar e destruir torres inimigas.",
            "tactics": "Úsalo en la línea de Barón o Mid para derribar la primera torreta y desbloquear rotaciones tempranas.",
            "tactics_en": "Use it in Baron or Mid lane to take first turret and unlock early team rotations.",
            "tactics_pt": "Use-o na rota do Barão ou Meio para derrubar a primeira torre e desbloquear rotações rápidas."
        },
        {
            "name": "Barón Nashor",
            "name_en": "Baron Nashor",
            "name_pt": "Barão Nashor",
            "spawnTime": "Minuto 12:00",
            "spawnTime_en": "Minute 12:00",
            "spawnTime_pt": "Minuto 12:00",
            "respawnTime": "Reaparece cada 5:00",
            "respawnTime_en": "Respawns every 5:00",
            "respawnTime_pt": "Renasce a cada 5:00",
            "buffDescription": "Otorga Mano del Barón: potencia el daño de los súbditos aliados cercanos y reduce el tiempo de Retirada a 4 segundos.",
            "buffDescription_en": "Grants Hand of Baron: empowers nearby allied minions and reduces Recall channel time to 4 seconds.",
            "buffDescription_pt": "Concede Mão do Barão: fortalece tropas aliadas próximas e reduz o tempo de Retorno para 4 segundos.",
            "tactics": "Aprovecha el buff para asediar las tres líneas simultáneamente y forzar la caída de inhibidores.",
            "tactics_en": "Use the buff to siege all three lanes simultaneously and force inhibitor structures down.",
            "tactics_pt": "Aproveite o bônus para cercar as três rotas simultaneamente e forçar a queda dos inibidores."
        },
        {
            "name": "Cangrejo Escurridizo",
            "name_en": "Scuttle Crab",
            "name_pt": "Aronguejo",
            "spawnTime": "Minuto 1:25",
            "spawnTime_en": "Minute 1:25",
            "spawnTime_pt": "Minuto 1:25",
            "respawnTime": "Reaparece cada 2:30",
            "respawnTime_en": "Respawns every 2:30",
            "respawnTime_pt": "Renasce a cada 2:30",
            "buffDescription": "Genera un Santuario de Velocidad y visión inquebrantable en el río frente al Dragón o Barón.",
            "buffDescription_en": "Spawns a Speed Shrine and vision zone in the river in front of Dragon or Baron pit.",
            "buffDescription_pt": "Gera um Santuário de Velocidade e zona de visão no rio em frente ao Dragão ou Barão.",
            "tactics": "Aplica control de masas duro para romper su escudo de inmediato y acelerar la limpieza del río.",
            "tactics_en": "Apply hard crowd control to instantly break its shield and speed up river clearing.",
            "tactics_pt": "Aplique controle de grupo pesado para quebrar seu escudo imediatamente e acelerar a limpeza do rio."
        },
        {
            "name": "Ancestro Ígneo (Buff Rojo)",
            "name_en": "Red Brambleback (Red Buff)",
            "name_pt": "Rubrivira (Buff Vermelho)",
            "spawnTime": "Minuto 0:20",
            "spawnTime_en": "Minute 0:20",
            "spawnTime_pt": "Minuto 0:20",
            "respawnTime": "Reaparece cada 2:30",
            "respawnTime_en": "Respawns every 2:30",
            "respawnTime_pt": "Renasce a cada 2:30",
            "buffDescription": "Otorga Escudo de Cenizas: ataques básicos queman causando daño verdadero periódico y ralentizan.",
            "buffDescription_en": "Grants Crest of Cinders: basic attacks burn enemies for periodic true damage and apply a slow.",
            "buffDescription_pt": "Concede Brasas: ataques básicos queimam causando dano verdadeiro periódico e lentidão.",
            "tactics": "Esencial para tiradores y junglas físicos para aumentar el potencial de persecución y hostigamiento.",
            "tactics_en": "Essential for AD carries and physical junglers to boost chase and harassment potential.",
            "tactics_pt": "Essencial para atiradores e caçadores físicos para aumentar o potencial de perseguição e poke."
        },
        {
            "name": "Coloso Celeste (Buff Azul)",
            "name_en": "Blue Sentinel (Blue Buff)",
            "name_pt": "Azuporã (Buff Azul)",
            "spawnTime": "Minuto 0:20",
            "spawnTime_en": "Minute 0:20",
            "spawnTime_pt": "Minuto 0:20",
            "respawnTime": "Reaparece cada 2:30",
            "respawnTime_en": "Respawns every 2:30",
            "respawnTime_pt": "Renasce a cada 2:30",
            "buffDescription": "Otorga Emblema de Perspicacia: acelera la regeneración de Maná y Energía y reduce tiempos de enfriamiento.",
            "buffDescription_en": "Grants Crest of Insight: accelerates Mana and Energy regeneration and grants Ability Haste.",
            "buffDescription_pt": "Concede Discernimento: acelera a regeneração de Mana e Energia e concede Aceleração de Habilidade.",
            "tactics": "Crítico para magos y campeones dependientes de habilidades para spamear combos sin quedarse sin recursos.",
            "tactics_en": "Critical for mages and ability-reliant champions to sustain combo rotations without running out of mana.",
            "tactics_pt": "Crítico para magos e campeões dependentes de habilidades para usar combos sem esgotar recursos."
        }
    ]

    for obj in objectives_data:
        en[obj["name"]] = obj["name_en"]
        pt[obj["name"]] = obj["name_pt"]

        en[obj["spawnTime"]] = obj["spawnTime_en"]
        pt[obj["spawnTime"]] = obj["spawnTime_pt"]

        en[obj["respawnTime"]] = obj["respawnTime_en"]
        pt[obj["respawnTime"]] = obj["respawnTime_pt"]

        en[obj["buffDescription"]] = obj["buffDescription_en"]
        pt[obj["buffDescription"]] = obj["buffDescription_pt"]

        en[obj["tactics"]] = obj["tactics_en"]
        pt[obj["tactics"]] = obj["tactics_pt"]

    # Common UI labels in Map Objectives
    labels = {
        "Monstruos Épicos & Tiempos de Aparición": ("Epic Monsters & Spawn Times", "Monstros Épicos e Tempos de Surgimento"),
        "Conocer los tiempos exactos de aparición en Wild Rift asegura la victoria de tu equipo:": (
            "Knowing exact spawn timers in Wild Rift secures victory for your team:",
            "Conhecer os tempos exatos de surgimento no Wild Rift garante a vitória da sua equipe:"
        ),
        "Reaparición:": ("Respawn:", "Renasce:"),
        "Mejora:": ("Buff:", "Bônus:"),
        "Táctica:": ("Tactics:", "Tática:"),
        "Objetivos": ("Objectives", "Objetivos")
    }

    for k, (v_en, v_pt) in labels.items():
        en[k] = v_en
        pt[k] = v_pt

    with open('app/src/main/assets/translations_en.json', 'w', encoding='utf-8') as f:
        json.dump(en, f, ensure_ascii=False, indent=2)

    with open('app/src/main/assets/translations_pt.json', 'w', encoding='utf-8') as f:
        json.dump(pt, f, ensure_ascii=False, indent=2)

    print("Map objectives translated perfectly into EN and PT!")

if __name__ == '__main__':
    main()
