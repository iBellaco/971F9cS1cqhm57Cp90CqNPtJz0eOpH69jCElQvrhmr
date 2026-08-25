import json
import re

def main():
    with open('app/src/main/assets/translations_en.json', 'r', encoding='utf-8') as f:
        en = json.load(f)

    with open('app/src/main/assets/translations_pt.json', 'r', encoding='utf-8') as f:
        pt = json.load(f)

    # Detailed translations for all runes & spells descriptions
    rune_desc_map = {
        "Golpe Bajo": (
            "True Damage on Impaired Enemies\n\nDamaging champion with impaired movement or actions deals 10-45 bonus true damage. Cooldown: 4s.",
            "Dano Verdadeiro contra Inimigos com Movimento Debilitado\n\nCausar dano a campeões com movimento debilitado causa 10-45 de dano verdadeiro adicional. Tempo de recarga: 4s."
        ),
        "Impacto Repentino": (
            "Lethality and Magic Penetration\n\nAfter using a dash, leap, blink, teleport, or exiting stealth, hitting an enemy champion grants Armor Penetration and Magic Penetration for 4s.",
            "Letalidade e Penetração Mágica\n\nApós usar um avanço, salto, teleporte ou sair de furtividade, acertar um campeão inimigo concede Penetração de Armadura e Mágica por 4s."
        ),
        "Ataque Potenciado": (
            "Empowered Attacks\n\nEvery 10s, your next basic attack deals bonus damage and slows the target.",
            "Ataques Fortalecidos\n\nA cada 10s, seu próximo ataque básico causa dano adicional e aplica lentidão ao alvo."
        ),
        "Asalto Encadenado": (
            "Chain Assault\n\nHitting unique enemy champions grants bonus damage and movement speed.",
            "Ataque em Cadeia\n\nAtingir campeões inimigos distintos concede dano bônus e velocidade de movimento."
        ),
        "Tirano": (
            "Bonus Damage Against CC'd Targets\n\nDeal increased damage to enemy champions that are slowed or immobilized.",
            "Dano Bônus contra Alvos com Controle de Grupo\n\nCausa dano aumentado a campeões inimigos que estejam sob lentidão ou imobilizados."
        ),
        "Soberbia": (
            "Execute and Bonus Damage\n\nTakedowns grant a statue monument, providing bonus Attack Damage or Ability Power.",
            "Execução e Dano Bônus\n\nEliminações concedem um monumento que fornece Dano de Ataque ou Poder de Habilidade adicional."
        ),
        "Colección de Globos Oculares": (
            "Bonus Adaptive Stats on Takedown\n\nCollect eyeballs on champion takedowns. Grants bonus AD or AP for each eyeball up to a maximum cap.",
            "Atributos Adaptativos em Eliminações\n\nColete olhos ao eliminar campeões. Concede Dano de Ataque ou Poder de Habilidade por cada olho até o limite máximo."
        ),
        "Cazador Ingenioso": (
            "Item Ability Haste\n\nGrants Item Ability Haste on unique champion takedowns.",
            "Aceleração de Itens\n\nConcede Aceleração de Itens para cada eliminação única de campeão."
        ),
        "Cazador Incesante": (
            "Out-of-Combat Movement Speed\n\nGrants out-of-combat movement speed for each unique champion takedown.",
            "Velocidade Fora de Combate\n\nConcede velocidade de movimento fora de combate para cada eliminação única de campeão."
        ),
        "Guardián Zombi": (
            "Zombie Ward\n\nKilling enemy wards summons an allied Zombie Ward in its place, granting vision and bonus stats.",
            "Sentinela Zumbi\n\nDestruir sentinelas inimigas faz surgir uma Sentinela Zumbi aliada no local, concedendo visão e atributos adicionais."
        ),
        "Brutal": (
            "On-hit Damage\n\nBasic attacks deal bonus on-hit physical or magical adaptive damage.",
            "Dano ao Impacto\n\nAtaques básicos causam dano adaptativo físico ou mágico adicional ao contato."
        ),
        "Triunfo": (
            "Takedown Health Restore\n\nChampion takedowns restore 10% missing health and grant bonus damage against low health enemies.",
            "Restauração de Vida em Eliminações\n\nEliminações de campeões restauram 10% da vida perdida e concedem dano bônus contra alvos com pouca vida."
        ),
        "Fervor de Batalla": (
            "Stacking Attack Damage\n\nConsecutive basic attacks on enemy champions grant stacking Attack Damage.",
            "Dano de Ataque Cumulativo\n\nAtaques básicos consecutivos em campeões inimigos concedem Dano de Ataque cumulativo."
        ),
        "Último Esfuerzo": (
            "Bonus Damage at Low Health\n\nDeal up to 11% bonus damage to enemy champions while below 60% health.",
            "Dano Bônus com Vida Baixa\n\nCausa até 11% de dano bônus contra campeões inimigos enquanto estiver abaixo de 60% de vida."
        ),
        "Derribado": (
            "Bonus Damage to High-Health Enemies\n\nDeal increased damage to enemy champions with greater maximum health than you.",
            "Dano Bônus contra Inimigos com Mais Vida\n\nCausa dano aumentado a campeões inimigos com mais vida máxima que você."
        ),
        "Golpe de Gracia": (
            "Execute Low Health Enemies\n\nDeal 7% bonus damage to enemy champions below 40% health.",
            "Dano Bônus em Alvos com Pouca Vida\n\nCausa 7% de dano adicional a campeões inimigos com menos de 40% de vida."
        ),
        "Leyenda: Presteza": (
            "Stacking Attack Speed\n\nGain bonus Attack Speed plus additional Attack Speed per Legend stack earned via takedowns and monster kills.",
            "Velocidade de Ataque Cumulativa\n\nReceba velocidade de ataque bônus mais velocidade de ataque por cada acúmulo de Lenda obtido em abates."
        ),
        "Leyenda: Tenacidad": (
            "Stacking Tenacity\n\nGain Tenacity and Slow Resist per Legend stack earned via champion takedowns.",
            "Tenacidade Cumulativa\n\nReceba Tenacidade e Resistência a Lentidão por cada acúmulo de Lenda obtido em abates."
        ),
        "Leyenda: Linaje": (
            "Stacking Lifesteal\n\nGain bonus Lifesteal per Legend stack earned via champion and monster takedowns.",
            "Roubo de Vida Cumulativo\n\nReceba Roubo de Vida por cada acúmulo de Lenda obtido em abates."
        ),
        "Fuente de Vida": (
            "Mark & Allied Healing\n\nImpairing the movement of an enemy champion marks them. Allies who attack the marked enemy restore health over time.",
            "Marca e Cura para Aliados\n\nDebilitar o movimento de um campeão inimigo o marca. Aliados que atacarem o alvo marcado regeneram vida ao longo do tempo."
        ),
        "Coraje del Coloso": (
            "Shield on Hard CC\n\nGain a protective shield after immobilizing an enemy champion.",
            "Escudo em Controle de Grupo Pesado\n\nReceba um escudo protetor após imobilizar um campeão inimigo."
        ),
        "Orbe Anulador": (
            "Emergency Magic Shield\n\nGain a protective magic shield when taking damage that reduces health below 35%.",
            "Escudo Mágico de Emergência\n\nRecebe um escudo mágico protetor ao sofrer dano que reduza sua vida abaixo de 35%."
        ),
        "Revestimiento de Huesos": (
            "Damage Reduction\n\nAfter taking damage from a champion, the next 3 spells or attacks you take deal significantly reduced damage.",
            "Redução de Dano\n\nApós sofrer dano de um campeão, os próximos 3 ataques ou habilidades recebidos causam dano significativamente reduzido."
        ),
        "Fuerzas Renovadas": (
            "Health Regeneration on Damage\n\nAfter taking damage from an enemy champion, regenerate a portion of your missing health over time.",
            "Regeneração de Vida após Dano\n\nApós sofrer dano de um campeão inimigo, regenera uma porcentagem da sua vida perdida ao longo do tempo."
        ),
        "Inquebrantable": (
            "Tenacity & Slow Resist\n\nGain bonus Tenacity and Slow Resistance, which increases further at lower health levels.",
            "Tenacidade e Resistência a Lentidão\n\nReceba Tenacidade e Resistência a Lentidão bônus, que aumentam ainda mais quando sua vida estiver baixa."
        ),
        "Sobrecrecimiento": (
            "Permanent Health Stacking\n\nGain permanent maximum health when minions or monsters die near you.",
            "Aumento Permanente de Vida\n\nAumenta permanentemente a vida máxima quando tropas ou monstros são abatidos perto de você."
        ),
        "Revitalizar": (
            "Heal & Shield Power\n\nIncreases healing and shielding power by 5%, boosted further on low health targets.",
            "Poder de Cura e Escudo\n\nAumenta a eficácia de curas e escudos em 5%, com bônus adicional em alvos com pouca vida."
        ),
        "Perseverancia": (
            "Burst Resistances on CC\n\nGain bonus Armor and Magic Resist when immobilized or crowd controlled.",
            "Armadura e Resistência em Controle de Grupo\n\nReceba Armadura e Resistência Mágica bônus ao sofrer efeitos de imobilização."
        ),
        "Demoler": (
            "Turret Demolition\n\nCharge a massive empowered attack against enemy turrets while standing near them.",
            "Demolição de Torres\n\nCarrega um poderoso ataque contra torres inimigas ao permanecer próximo a elas."
        ),
        "Banda de Maná": (
            "Max Mana Stacking\n\nHitting an enemy champion with an ability permanently increases your maximum mana pool up to a cap.",
            "Aumento de Mana Máxima\n\nAtingir um campeão inimigo com uma habilidade aumenta permanentemente sua reserva máxima de mana até um limite."
        ),
        "Arcanólogo Axiomático": (
            "Cooldown Reduction on Ultimate\n\nTakedowns refund a percentage of your Ultimate ability's maximum cooldown.",
            "Redução de Tempo de Recarga da Ultimate\n\nEliminações de campeões restauram uma porcentagem do tempo de recarga máximo da sua Habilidade Ultimate."
        ),
        "Trascendencia": (
            "Ability Haste\n\nGain bonus Ability Haste upon reaching specific level thresholds. Takedowns refund basic ability cooldowns.",
            "Aceleração de Habilidade\n\nReceba Aceleração de Habilidade adicional ao atingir certos níveis. Eliminações restauram tempo de recarga de habilidades básicas."
        ),
        "Celeridad": (
            "Bonus Movement Speed\n\nIncreases the effectiveness of all movement speed bonuses applied to you.",
            "Velocidade de Movimento Aumentada\n\nAumenta a eficácia de todos os bônus de velocidade de movimento concedidos a você."
        ),
        "Concentración Absoluta": (
            "Stats Above 70% Health\n\nGain bonus Attack Damage or Ability Power while your health is above 70%.",
            "Atributos acima de 70% de Vida\n\nConcede Dano de Ataque ou Poder de Habilidade adicional enquanto estiver com mais de 70% de vida."
        ),
        "Capa del Nimbo": (
            "Burst Speed on Summoner Spell\n\nCasting a Summoner Spell grants a burst of bonus movement speed and ghosting for 2s.",
            "Velocidade ao Usar Feitiço de Invocador\n\nUsar um Feitiço de Invocador concede um surto de velocidade de movimento adicional por 2s."
        ),
        "Piroláser": (
            "Periodic Burn Damage\n\nYour first damaging ability hitting a champion burns them for bonus magic damage.",
            "Dano de Queimadura Periódico\n\nSua primeira habilidade ofensiva a atingir um campeão o queima, causando dano mágico adicional."
        ),
        "Se Avecina Tormenta": (
            "Scaling AD/AP Over Time\n\nGain increasing amounts of Attack Damage or Ability Power every few minutes as the match progresses.",
            "Escalamento de AD/AP com o Tempo\n\nReceba quantidades crescentes de Dano de Ataque ou Poder de Habilidade a cada período de jogo."
        ),
        "Botanista": (
            "Bonus Honeyfruit Healing & Gold\n\nIncreases Honeyfruit healing and grants bonus gold whenever you or a nearby ally consumes one.",
            "Cura e Ouro Bônus em Frutomel\n\nAumenta a cura do Frutomel e concede ouro bônus sempre que você ou um aliado próximo consumir um fruto."
        ),
        "Hextello": (
            "Channeling Flash\n\nWhile Flash is on cooldown, it is replaced by Hexflash, allowing you to channel and blink to a new location.",
            "Destello Canalizado\n\nEnquanto o Flash estiver em tempo de recarga, é substituído pelo Hextempo, permitindo canalizar e saltar para um novo local."
        ),
        "Semillero Ixtalí": (
            "Plant Seed Harvesting\n\nHarvest seeds from nearby plants to replant them anywhere on the map for tactical vision and utility.",
            "Coleta e Plantio de Sementes\n\nColete sementes de plantas próximas para replantá-las em qualquer local do mapa para visão e utilidade tática."
        )
    }

    # Match each rune from WildRiftSpellsAndRunes.kt
    with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r', encoding='utf-8') as f:
        code = f.read()

    rune_blocks = re.findall(r'RuneItem\((.*?)\n\s*\)', code, re.DOTALL)
    for r in rune_blocks:
        name_m = re.search(r'name\s*=\s*\"([^\"]+)\"', r)
        desc_m = re.search(r'description\s*=\s*\"(.*?)\"', r, re.DOTALL)
        if name_m and desc_m:
            r_name = name_m.group(1).strip()
            r_desc = desc_m.group(1).replace('\\n', '\n').replace('\\"', '"')
            if r_name in rune_desc_map:
                en_v, pt_v = rune_desc_map[r_name]
                en[r_desc] = en_v
                pt[r_desc] = pt_v
            # Also register name
            en[r_name] = en.get(r_name, r_name)
            pt[r_name] = pt.get(r_name, r_name)

    # Save
    with open('app/src/main/assets/translations_en.json', 'w', encoding='utf-8') as f:
        json.dump(en, f, ensure_ascii=False, indent=2)

    with open('app/src/main/assets/translations_pt.json', 'w', encoding='utf-8') as f:
        json.dump(pt, f, ensure_ascii=False, indent=2)

    print("All runes and spells detailed descriptions mapped and saved!")

if __name__ == '__main__':
    main()
