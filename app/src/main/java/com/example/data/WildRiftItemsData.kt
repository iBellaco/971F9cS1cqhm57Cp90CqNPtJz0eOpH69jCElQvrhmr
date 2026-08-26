package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

/**
 * Catálogo oficial exhaustivo de todos los 204 objetos de League of Legends: Wild Rift
 * Totalmente localizado y traducido al Español, Inglés y Portugués.
 */
object WildRiftItemsData {
    val list: List<WildRiftItem> = buildList {
        add(WildRiftItem(
            id = "bloodthirster_physical",
            name = "Sanguinaria",
            nameEn = "Bloodthirster",
            namePt = "Sedenta por Sangue",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Daño de Ataque • +250 Vida Máxima • +25% Probabilidad de Crítico",
            statsEn = "+55 Attack Damage • +250 Max Health • +25% Critical Rate",
            statsPt = "+55 Dano de Ataque • +250 Vida Máxima • +25% Chance de Crítico",
            passive = "Bloody: +8% Vampirismo Físico, Attacks that asestar un Golpe Crítico gain an additional 4% Vampirismo Físico.\n\n💡 Consejos del Coach: Este objeto es esencial para tiradores e hiper-carries de autoataques, otorgando una gran mejora de damage, critical chance, and Velocidad de Ataque. Su pasiva convierte los golpes críticos en robo de vida mejorado, haciendo que los intercambios prolongados y peleas sostenidas se inclinen a tu favor. Al bajar a niveles críticos de vida, se activa un Escudo salvador que otorga Defensas extra y la oportunidad de sobrevivir a momentos decisivos. Ideal para tiradores y luchadores de autoataques que necesitan tanto alto DPS como sustain confiable.",
            passiveEn = "Bloody: +8% Vampirismo Físico, Attacks that asestar un Golpe Crítico gain an additional 4% Vampirismo Físico.\n\n💡 Coach Tips: Este objeto es esencial para tiradores e hiper-carries de autoataques, otorgando una gran mejora de damage, critical chance, and Velocidad de Ataque. Su pasiva convierte los golpes críticos en robo de vida mejorado, haciendo que los intercambios prolongados y peleas sostenidas se inclinen a tu favor. Al bajar a niveles críticos de vida, se activa un Escudo salvador que otorga Defensas extra y la oportunidad de sobrevivir a momentos decisivos. Ideal para tiradores y luchadores de autoataques que necesitan tanto alto DPS como sustain confiable.",
            passivePt = "Bloody: +8% Vampirismo Físico, Attacks that asestar un Acerto Crítico gain an additional 4% Vampirismo Físico.\n\n💡 Dicas do Coach: Este item é essencial para tiradores e hiper-carries de autoataques, otorgando una gran mejora de damage, critical chance, and Velocidade de Ataque. Su pasiva convierte los golpes críticos en robo de vida mejorado, haciendo que los intercambios prolongados y peleas sostenidas se inclinen a tu favor. Al bajar a niveles críticos de vida, se activa un Escudo salvador que otorga Defensas extra y la oportunidad de sobrevivir a momentos decisivos. Ideal para tiradores y luchadores de autoataques que necesitan tanto alto DPS como sustain confiable.",
            iconUrl = "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp"
        ))
        add(WildRiftItem(
            id = "guardian_angel_defense",
            name = "Ángel Guardián",
            nameEn = "Guardian Angel",
            namePt = "Anjo Guardião",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+45 Daño de Ataque • +40 Armadura",
            statsEn = "+45 Attack Damage • +40 Armor",
            statsPt = "+45 Dano de Ataque • +40 Armadura",
            passive = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need a segundo chance in teamfights. Es especialmente efectivo contra Campeones con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            passiveEn = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need a segundo chance in teamfights. Es especialmente efectivo contra Campeones con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            passivePt = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need a segundo chance in teamfights. É especialmente efetivo contra Campeões con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp"
        ))
        add(WildRiftItem(
            id = "magnetic_blaster_physical",
            name = "Bláster Magnético",
            nameEn = "Magnetic Blaster",
            namePt = "Canhão Magnético",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+30 Daño de Ataque • +25% Probabilidad de Crítico • +35% Velocidad de Ataque",
            statsEn = "+30 Attack Damage • +25% Critical Rate • +35% Attack Speed",
            statsPt = "+30 Dano de Ataque • +25% Chance de Crítico • +35% Velocidade de Ataque",
            passive = "Fervor:  +5% Velocidad de Movimiento.\nEnergized: Moverse y atacar genera un Ataque Energizado.\nPower Blitz: Los Ataques Energizados obtienen 100 de alcance (50 para cuerpo a cuerpo), infligen 40-100 de daño mágico adicional y otorgan 60 de Velocidad de Movimiento durante 0.75s. Este daño rebota a 5 enemigos cercanos y puede asestar Golpes Críticos.\n(Inflige 50-80% de daño adicional contra súbditos.)\n\n💡 Consejos del Coach: Este objeto amplía your attack range and adds hybrid damage by empowering every fourth attack with a magic burst that can bounce to multiple targets and asestar un Golpe Crítico. Moverse y atacar charges the empowered strike, and upon activation you gain a hefty Velocidad de Movimiento boost, aiding both chase and retreat.  — Perfecto para tiradores y colosos de autoataques que buscan mayor alcance, daño en área y movilidad en escaramuzas.",
            passiveEn = "Fervor:  +5% Velocidad de Movimiento.\nEnergized: Moverse y atacar genera un Ataque Energizado.\nPower Blitz: Los Ataques Energizados obtienen 100 de alcance (50 para cuerpo a cuerpo), infligen 40-100 de daño mágico adicional y otorgan 60 de Velocidad de Movimiento durante 0.75s. Este daño rebota a 5 enemigos cercanos y puede asestar Golpes Críticos.\n(Inflige 50-80% de daño adicional contra súbditos.)\n\n💡 Coach Tips: Este objeto amplía your attack range and adds hybrid damage by empowering every fourth attack with a magic burst that can bounce to multiple targets and asestar un Golpe Crítico. Moverse y atacar charges the empowered strike, and upon activation you gain a hefty Velocidad de Movimiento boost, aiding both chase and retreat.  — Perfecto para tiradores y colosos de autoataques que buscan mayor alcance, daño en área y movilidad en escaramuzas.",
            passivePt = "Fervor:  +5% Velocidade de Movimento.\nEnergized: Moverse y atacar genera un Ataque Energizado.\nPower Blitz: Los Ataques Energizados obtienen 100 de alcance (50 para Corpo a corpo), infligen 40-100 de Dano Mágico adicional y otorgan 60 de Velocidade de Movimento durante 0.75s. Este daño rebota a 5 enemigos cercanos y puede asestar Golpes Críticos.\n(Inflige 50-80% de daño adicional contra Tropas.)\n\n💡 Dicas do Coach: Este item amplia your attack range and adds hybrid damage by empowering every fourth attack with a magic burst that can bounce to multiple targets and asestar un Acerto Crítico. Moverse y atacar charges the empowered strike, and upon activation you gain a hefty Velocidade de Movimento boost, aiding both chase and retreat.  — Perfecto para tiradores y colosos de autoataques que buscan mayor alcance, daño en área y movilidad en escaramuzas.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300773_magnetic-blaster.webp"
        ))
        add(WildRiftItem(
            id = "blade_of_the_ruined_king_physical",
            name = "Hoja del Rey Arruinado",
            nameEn = "Blade of the Ruined King",
            namePt = "Espada do Rei Destruído",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+20 Daño de Ataque, +35% Velocidad de Ataque, +10% Vampirismo Físico",
            statsEn = "+20 Attack Damage, +35% Attack Speed, +10% Physical Vamp",
            statsPt = "+20 Dano de Ataque, +35% Velocidade de Ataque, +10% Vampirismo Físico",
            passive = "Golpe de Niebla: Los ataques infligen un 7% (cuerpo a cuerpo) o 4% (a distancia) de la vida actual del objetivo.",
            passiveEn = "Golpe de Niebla: Los ataques infligen un 7% (cuerpo a cuerpo) o 4% (a distancia) de la vida actual del objetivo.",
            passivePt = "Golpe de Niebla: Los ataques infligen un 7% (Corpo a corpo) o 4% (À distância) de la vida actual del objetivo.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300221_blade-of-the-ruined-king.webp"
        ))
        add(WildRiftItem(
            id = "runaan_s_hurricane_magic",
            name = "Huracán de Runaan",
            nameEn = "Runaan's Hurricane",
            namePt = "Furacão de Runaan",
            category = "Daño Físico",
            goldCost = 2900,
            stats = "+35% Velocidad de Ataque • +25% Probabilidad de Crítico",
            statsEn = "+35% Attack Speed • +25% Critical Rate",
            statsPt = "+35% Velocidade de Ataque • +25% Chance de Crítico",
            passive = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por campeones cuerpo a cuerpo y a distancia.\n\n💡 Consejos del Coach: Este objeto convierte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Cuerpo a cuerpo users can use it too, but it shines brightest on A distancia tiradores y campeones de autoataque.",
            passiveEn = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por campeones cuerpo a cuerpo y a distancia.\n\n💡 Coach Tips: Este objeto convierte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Cuerpo a cuerpo users can use it too, but it shines brightest on A distancia tiradores y campeones de autoataque.",
            passivePt = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de Dano Físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por Campeões Corpo a corpo y À distância.\n\n💡 Dicas do Coach: Este item converte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Corpo a corpo users can use it too, but it shines brightest on À distância tiradores y Campeões de autoataque.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300839_yordle-runaans-hurricane.webp"
        ))
        add(WildRiftItem(
            id = "youmuu_s_ghostblade_physical",
            name = "Espada Fantasma de Youmuu",
            nameEn = "Youmuu's Ghostblade",
            namePt = "Lâmina Fantasma de Youmuu",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+55 Attack Damage • +15 Ability Haste",
            statsPt = "+55 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Corte: +15 de Penetración de Armadura.\nMomentum: Moverse acumula Impulso, granting up to 50 Velocidad de Movimiento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nSpectral Haste: Attacking with max Momentum grants 25% Velocidad de Ataque for 4 segundos.\n\n💡 Consejos del Coach: Este objeto es ideal para assassins and Campeones who need to get in and out of fights quickly. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, along with Penetración de Armadura, helping you deal more damage to enemies. The Momentum effect increases your Velocidad de Movimiento and Penetración de Armadura as you move, giving you an advantage in mobility during fights. When Momentum is fully stacked, Los ataques otorgan Adicional Velocidad de Ataque, making the item a great choice for Campeones who need to quickly deal damage and escape from fights.",
            passiveEn = "Corte: +15 de Penetración de Armadura.\nMomentum: Moverse acumula Impulso, granting up to 50 Velocidad de Movimiento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nSpectral Haste: Attacking with max Momentum grants 25% Velocidad de Ataque for 4 segundos.\n\n💡 Coach Tips: Este objeto es ideal para assassins and Campeones who need to get in and out of fights quickly. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, along with Penetración de Armadura, helping you deal more damage to enemies. The Momentum effect increases your Velocidad de Movimiento and Penetración de Armadura as you move, giving you an advantage in mobility during fights. When Momentum is fully stacked, Los ataques otorgan Adicional Velocidad de Ataque, making the item a great choice for Campeones who need to quickly deal damage and escape from fights.",
            passivePt = "Corte: +15 de Penetração de Armadura.\nMomentum: Moverse acumula Impulso, granting up to 50 Velocidade de Movimento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nSpectral Haste: Attacking with max Momentum grants 25% Velocidade de Ataque for 4 segundos.\n\n💡 Dicas do Coach: Este item é ideal para assassins and Campeões who need to get in and out of fights quickly. It provides bonuses to Dano de Ataque and Aceleração de Habilidade, along with Penetração de Armadura, helping you deal more damage to enemies. The Momentum effect increases your Velocidade de Movimento and Penetração de Armadura as you move, giving you an advantage in mobility during fights. When Momentum is fully stacked, Los ataques otorgan Adicional Velocidade de Ataque, making the item a great choice for Campeões who need to quickly deal damage and escape from fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300869_youmuus-ghostblade.webp"
        ))
        add(WildRiftItem(
            id = "duskblade_of_draktharr_physical",
            name = "Filo Fantasma de Draktharr",
            nameEn = "Duskblade of Draktharr",
            namePt = "Crepúsculo de Draktharr",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Daño de Ataque • +10 Aceleración de Habilidad",
            statsEn = "+55 Attack Damage • +10 Ability Haste",
            statsPt = "+55 Dano de Ataque • +10 Aceleração de Habilidade",
            passive = "Cuchilla: +18 de Penetración de Armadura.\nNightstalker: The first attack against a Campeón deals 60-160 Adicional Daño Físico and Ralentiza them by 99% for 0.35s (10s Enfriamiento). Campeón takedowns refresh Enfriamiento.\nThis item is a pure assassin tool: it boosts your Penetración de Armadura and makes your first strike on an enemy deal a deadly burst with a brief Ralentización. Securing a takedown grants stealth and a fast reposition window, letting you escape or continue hunting—perfect for single-target picks. It shines on mobile killers who focus on quick executions and roams; it’s less effective against bulky, high-HP frontliners.",
            passiveEn = "Cuchilla: +18 de Penetración de Armadura.\nNightstalker: The first attack against a Campeón deals 60-160 Adicional Daño Físico and Ralentiza them by 99% for 0.35s (10s Enfriamiento). Campeón takedowns refresh Enfriamiento.\nThis item is a pure assassin tool: it boosts your Penetración de Armadura and makes your first strike on an enemy deal a deadly burst with a brief Ralentización. Securing a takedown grants stealth and a fast reposition window, letting you escape or continue hunting—perfect for single-target picks. It shines on mobile killers who focus on quick executions and roams; it’s less effective against bulky, high-HP frontliners.",
            passivePt = "Cuchilla: +18 de Penetração de Armadura.\nNightstalker: The first attack against a Campeão deals 60-160 Adicional Dano Físico and Ralentiza them by 99% for 0.35s (10s Tempo de Recarga). Campeão takedowns refresh Tempo de Recarga.\nThis item is a pure assassin tool: it boosts your Penetração de Armadura and makes your first strike on an enemy deal a deadly burst with a brief Ralentización. Securing a takedown grants stealth and a fast reposition window, letting you escape or continue hunting—perfect for single-target picks. It shines on mobile killers who focus on quick executions and roams; it’s less effective against bulky, high-HP frontliners.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300931_yordle-duskblade-of-draktharr.webp"
        ))
        add(WildRiftItem(
            id = "infinity_edge_physical",
            name = "Filo del Infinito",
            nameEn = "Infinity Edge",
            namePt = "Gume do Infinito",
            category = "Daño Físico",
            goldCost = 3400,
            stats = "+55 Daño de Ataque, +25% Probabilidad de Impacto Crítico",
            statsEn = "+55 Attack Damage, +25% Critical Rate",
            statsPt = "+55 Dano de Ataque, +25% Probabilidad de Impacto Crítico",
            passive = "Infinito: Los impactos críticos infligen un 205% de daño en lugar del 175%.",
            passiveEn = "Infinito: Los impactos críticos infligen un 205% de daño en lugar del 175%.",
            passivePt = "Infinito: Los impactos críticos infligen un 205% de daño en lugar del 175%.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300938_infinity-edge.webp"
        ))
        add(WildRiftItem(
            id = "mortal_reminder_physical",
            name = "Recordatorio Mortal",
            nameEn = "Mortal Reminder",
            namePt = "Lembrete Mortal",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+25 Daño de Ataque • +25% Probabilidad de Crítico • +15% Velocidad de Ataque",
            statsEn = "+25 Attack Damage • +25% Critical Rate • +15% Attack Speed",
            statsPt = "+25 Dano de Ataque • +25% Chance de Crítico • +15% Velocidade de Ataque",
            passive = "Last Whisper:  +30% Penetración de Armadura. Attacks that asestar un Golpe Crítico gain an additional  6% Penetración de Armadura.\nSepsis: Dealing Daño Físico to campeones enemigos applies 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Consejos del Coach: Este objeto es ideal para auto‑attackers who need to shred through Armadura and cut down enemy healing. It boosts your penetration to deal more damage against tanky targets and applies Heridas Graves on hit, reducing all healing and regen effects.  — Ideal against tanks and high‑heal Campeones, and for marksmen and auto‑attack fighters who need to pierce Defensas and curb enemy sustain.",
            passiveEn = "Last Whisper:  +30% Penetración de Armadura. Attacks that asestar un Golpe Crítico gain an additional  6% Penetración de Armadura.\nSepsis: Dealing Daño Físico to campeones enemigos applies 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Coach Tips: Este objeto es ideal para auto‑attackers who need to shred through Armadura and cut down enemy healing. It boosts your penetration to deal more damage against tanky targets and applies Heridas Graves on hit, reducing all healing and regen effects.  — Ideal against tanks and high‑heal Campeones, and for marksmen and auto‑attack fighters who need to pierce Defensas and curb enemy sustain.",
            passivePt = "Last Whisper:  +30% Penetração de Armadura. Attacks that asestar un Acerto Crítico gain an additional  6% Penetração de Armadura.\nSepsis: Dealing Dano Físico to campeões inimigos applies 50% Feridas Dolorosas for 3 segundos.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Dicas do Coach: Este item é ideal para auto‑attackers who need to shred through Armadura and cut down enemy healing. It boosts your penetration to deal more damage against tanky targets and applies Feridas Dolorosas on hit, reducing all healing and regen effects.  — Ideal against tanks and high‑heal Campeões, and for marksmen and auto‑attack fighters who need to pierce Defensas and curb enemy sustain.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301062_mortal-reminder.webp"
        ))
        add(WildRiftItem(
            id = "black_cleaver_physical",
            name = "Cuchilla Negra",
            nameEn = "Black Cleaver",
            namePt = "Cléver Negro",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+400 Vida Máxima • +40 Daño de Ataque • +20 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +40 Attack Damage • +20 Ability Haste",
            statsPt = "+400 Vida Máxima • +40 Dano de Ataque • +20 Aceleração de Habilidade",
            passive = "Sunder: Infligir daño físico a un campeón reduce su Armadura un 6% durante 6s, acumulándose 5 veces hasta un 30% de reducción.\nRage: Obtienes 20 de Velocidad de Movimiento al infligir daño físico. Al moverte hacia campeones enemigos con 5 acumulaciones de Hender, obtienes 40 de Velocidad de Movimiento. A distancia Campeones gain halved values.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who deal Daño Físico and need to fight tanky opponents. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sunder\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the enemy’s Armadura when dealing Daño Físico, making it effective at shredding through tanks with high resistance. The Velocidad de Movimiento Adicional, activated when dealing Daño Físico, helps maintain mobility on the battlefield. It's a great choice for fighters and junglers who can quickly apply multiple stacks of the item’s passive, reducing the enemy's Armadura and increasing the overall damage dealt.",
            passiveEn = "Sunder: Infligir daño físico a un campeón reduce su Armadura un 6% durante 6s, acumulándose 5 veces hasta un 30% de reducción.\nRage: Obtienes 20 de Velocidad de Movimiento al infligir daño físico. Al moverte hacia campeones enemigos con 5 acumulaciones de Hender, obtienes 40 de Velocidad de Movimiento. A distancia Campeones gain halved values.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who deal Daño Físico and need to fight tanky opponents. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sunder\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the enemy’s Armadura when dealing Daño Físico, making it effective at shredding through tanks with high resistance. The Velocidad de Movimiento Adicional, activated when dealing Daño Físico, helps maintain mobility on the battlefield. It's a great choice for fighters and junglers who can quickly apply multiple stacks of the item’s passive, reducing the enemy's Armadura and increasing the overall damage dealt.",
            passivePt = "Sunder: Infligir Dano Físico a un Campeão reduce su Armadura un 6% durante 6s, acumulándose 5 veces hasta un 30% de reducción.\nRage: Obtienes 20 de Velocidade de Movimento al infligir Dano Físico. Al moverte hacia campeões inimigos con 5 acumulaciones de Hender, obtienes 40 de Velocidade de Movimento. À distância Campeões gain halved values.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who deal Dano Físico and need to fight tanky opponents. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sunder\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the enemy’s Armadura when dealing Dano Físico, making it effective at shredding through tanks with high resistance. The Velocidade de Movimento Adicional, activated when dealing Dano Físico, helps maintain mobility on the battlefield. It's a great choice for fighters and junglers who can quickly apply multiple stacks of the item’s passive, reducing the enemy's Armadura and increasing the overall damage dealt.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301421_black-cleaver.webp"
        ))
        add(WildRiftItem(
            id = "manamune_physical",
            name = "Manamune",
            nameEn = "Manamune",
            namePt = "Manamune",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25 Daño de Ataque • +300 Maná Máximo • +20 Aceleración de Habilidad",
            statsEn = "+25 Attack Damage • +300 Max Mana • +20 Ability Haste",
            statsPt = "+25 Dano de Ataque • +300 Mana Máxima • +20 Aceleração de Habilidade",
            passive = "+300 Maná Máximo\nAwe: Grants Daño de Ataque equal to 1.5%  of Maná Máximo and refunds 15%  of all Maná spent.\nManá Charge: Aumenta el Maná máximo en 18 con cada ataque o al gastar Maná. Hasta 700 de Maná adicional, transformando Manamune en Muramana. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Consejos del Coach: Este objeto es ideal para AD Campeones who rely on Maná and abilities to deal damage. It provides bonuses to Daño de Ataque, maximum Maná, and Aceleración de Habilidad, allowing you to use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Maná with each attack or Maná usage, which significantly boosts your damage once the item is completed. After reaching the maximum Adicional Maná, the item transforms into Muramana, greatly enhancing your attacks. It’s an excellent choice for Campeones who use Maná to activate their abilities, such as Ezreal or Twitch.",
            passiveEn = "+300 Maná Máximo\nAwe: Grants Daño de Ataque equal to 1.5%  of Maná Máximo and refunds 15%  of all Maná spent.\nManá Charge: Aumenta el Maná máximo en 18 con cada ataque o al gastar Maná. Hasta 700 de Maná adicional, transformando Manamune en Muramana. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Coach Tips: Este objeto es ideal para AD Campeones who rely on Maná and abilities to deal damage. It provides bonuses to Daño de Ataque, maximum Maná, and Aceleración de Habilidad, allowing you to use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Maná with each attack or Maná usage, which significantly boosts your damage once the item is completed. After reaching the maximum Adicional Maná, the item transforms into Muramana, greatly enhancing your attacks. It’s an excellent choice for Campeones who use Maná to activate their abilities, such as Ezreal or Twitch.",
            passivePt = "+300 Mana Máxima\nAwe: Grants Dano de Ataque equal to 1.5%  of Mana Máxima and refunds 15%  of all Maná spent.\nManá Charge: Aumenta el Mana Máxima en 18 con cada ataque o al gastar Maná. Hasta 700 de Maná adicional, transformando Manamune en Muramana. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Dicas do Coach: Este item é ideal para AD Campeões who rely on Maná and abilities to deal damage. It provides bonuses to Dano de Ataque, maximum Maná, and Aceleração de Habilidade, allowing you to use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Maná with each attack or Maná usage, which significantly boosts your damage once the item is completed. After reaching the maximum Adicional Maná, the item transforms into Muramana, greatly enhancing your attacks. It’s an excellent choice for Campeões who use Maná to activate their abilities, such as Ezreal or Twitch.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301442_manamune.webp"
        ))
        add(WildRiftItem(
            id = "muramana_physical",
            name = "Muramana",
            nameEn = "Muramana",
            namePt = "Muramana",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25 Daño de Ataque • +1000 Maná Máximo • +20 Aceleración de Habilidad",
            statsEn = "+25 Attack Damage • +1000 Max Mana • +20 Ability Haste",
            statsPt = "+25 Dano de Ataque • +1000 Mana Máxima • +20 Aceleração de Habilidade",
            passive = "+1000 Maná Máximo\nAwe: Grants Daño de Ataque equal to 2%  of Maná Máximo and refunds 15%  of all Maná spent.\nShock: When you hit an campeón enemigo with autoataque, it drains 2.5% of current Maná and deals Adicional Daño Físico equal to the amount consumed. When dealing ability damage to campeón enemigo drains 4% of current Maná and deals an additional Daño Físico equal to the amount consumed + 6%. This effect only triggers when remaining Maná is above 20%. A single attack or ability will only trigger this effect once on the same Campeón.\n\n💡 Consejos del Coach: Este objeto es ideal para AD Campeones who rely on Maná to activate their abilities and autoataques. It provides bonuses to Daño de Ataque, maximum Maná, and Aceleración de Habilidad, significantly enhancing your attacks and abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Shock\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to deal Adicional Daño Físico by consuming Maná with each autoataque or ability. This is especially useful for Campeones like Ezreal, who actively use Maná to deal damage. The effect doesn't trigger if your Maná is below 20%, so it's important to manage your resources carefully. Overall, Este objeto otorga a huge power spike once completed and is ideal for Campeones who rely on Maná and physical attacks.",
            passiveEn = "+1000 Maná Máximo\nAwe: Grants Daño de Ataque equal to 2%  of Maná Máximo and refunds 15%  of all Maná spent.\nShock: When you hit an campeón enemigo with autoataque, it drains 2.5% of current Maná and deals Adicional Daño Físico equal to the amount consumed. When dealing ability damage to campeón enemigo drains 4% of current Maná and deals an additional Daño Físico equal to the amount consumed + 6%. This effect only triggers when remaining Maná is above 20%. A single attack or ability will only trigger this effect once on the same Campeón.\n\n💡 Coach Tips: Este objeto es ideal para AD Campeones who rely on Maná to activate their abilities and autoataques. It provides bonuses to Daño de Ataque, maximum Maná, and Aceleración de Habilidad, significantly enhancing your attacks and abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Shock\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to deal Adicional Daño Físico by consuming Maná with each autoataque or ability. This is especially useful for Campeones like Ezreal, who actively use Maná to deal damage. The effect doesn't trigger if your Maná is below 20%, so it's important to manage your resources carefully. Overall, Este objeto otorga a huge power spike once completed and is ideal for Campeones who rely on Maná and physical attacks.",
            passivePt = "+1000 Mana Máxima\nAwe: Grants Dano de Ataque equal to 2%  of Mana Máxima and refunds 15%  of all Maná spent.\nShock: When you hit an campeão inimigo with autoataque, it drains 2.5% of current Maná and deals Adicional Dano Físico equal to the amount consumed. When dealing ability damage to campeão inimigo drains 4% of current Maná and deals an additional Dano Físico equal to the amount consumed + 6%. This effect only triggers when remaining Maná is above 20%. A single attack or ability will only trigger this effect once on the same Campeão.\n\n💡 Dicas do Coach: Este item é ideal para AD Campeões who rely on Maná to activate their abilities and autoataques. It provides bonuses to Dano de Ataque, maximum Maná, and Aceleração de Habilidade, significantly enhancing your attacks and abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Shock\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to deal Adicional Dano Físico by consuming Maná with each autoataque or ability. This is especially useful for Campeões like Ezreal, who actively use Maná to deal damage. The effect doesn't trigger if your Maná is below 20%, so it's important to manage your resources carefully. Overall, Este item concede a huge power spike once completed and is ideal for Campeões who rely on Maná and physical attacks.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301487_muramana.webp"
        ))
        add(WildRiftItem(
            id = "trinity_force_physical",
            name = "Fuerza de la Trinidad",
            nameEn = "Trinity Force",
            namePt = "Força da Trindade",
            category = "Daño Físico",
            goldCost = 3333,
            stats = "+333 Vida Máxima • +30 Daño de Ataque • +30% Velocidad de Ataque • +20 Aceleración de Habilidad",
            statsEn = "+333 Max Health • +30 Attack Damage • +30% Attack Speed • +20 Ability Haste",
            statsPt = "+333 Vida Máxima • +30 Dano de Ataque • +30% Velocidade de Ataque • +20 Aceleração de Habilidade",
            passive = "Fervor:  +5% Velocidad de Movimiento.\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Físico equal to 200% base AD(1.5s Enfriamiento). El daño se reduce contra estructuras.\nValor: Los ataques otorgan 20 Velocidad de Movimiento for 2 segundos. Los efectos no se acumulan. A distancia Campeones gain halved values.\n\n💡 Consejos del Coach: Este objeto otorga a well-rounded set of stats and enhances damage through the combination of abilities and ataques básicos. It is ideal for Campeones who frequently weave abilities between attacks and rely on consistent trading. Brilla en luchadores y tiradores con alta movilidad que buscan versatilidad, velocidad y daño explosivo.",
            passiveEn = "Fervor:  +5% Velocidad de Movimiento.\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Físico equal to 200% base AD(1.5s Enfriamiento). El daño se reduce contra estructuras.\nValor: Los ataques otorgan 20 Velocidad de Movimiento for 2 segundos. Los efectos no se acumulan. A distancia Campeones gain halved values.\n\n💡 Coach Tips: Este objeto otorga a well-rounded set of stats and enhances damage through the combination of abilities and ataques básicos. It is ideal for Campeones who frequently weave abilities between attacks and rely on consistent trading. Brilla en luchadores y tiradores con alta movilidad que buscan versatilidad, velocidad y daño explosivo.",
            passivePt = "Fervor:  +5% Velocidade de Movimento.\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Dano Físico equal to 200% base AD(1.5s Tempo de Recarga). El daño se reduce contra estructuras.\nValor: Los ataques otorgan 20 Velocidade de Movimento for 2 segundos. Los efectos no se acumulan. À distância Campeões gain halved values.\n\n💡 Dicas do Coach: Este item concede a well-rounded set of stats and enhances damage through the combination of abilities and ataques básicos. It is ideal for Campeões who frequently weave abilities between attacks and rely on consistent trading. Brilla en luchadores y tiradores con alta movilidad que buscan versatilidad, velocidad y daño explosivo.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301537_trinity-force.webp"
        ))
        add(WildRiftItem(
            id = "maw_of_malmortius_physical",
            name = "Fauces de Malmortius",
            nameEn = "Maw of Malmortius",
            namePt = "Mandíbula de Malmortius",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Daño de Ataque • +45 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+55 Attack Damage • +45 Magic Resistance • +10 Ability Haste",
            statsPt = "+55 Dano de Ataque • +45 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Lifeline: Al recibir daño mágico que reduciría tu vida por debajo del 35%, obtienes +10% de Omnivampirismo hasta salir de combate y un Escudo que absorbe 220-530 de Daño Mágico durante 3s. (70s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto otorga a mix of damage, Resistencia Mágica and Aceleración de Habilidad, but its core value is the anti-burst passive: when hit by a dangerous burst of Daño Mágico, you instantly gain a strong protective Escudo and a temporary lifesteal/Omnivampirismo effect that lasts through the fight. Permite sobrevivir al daño explosivo de magos AP y mantenerse firme en la pelea.  Best for assassins, bruisers and AD Campeones who need to survive enemy magic burst and power through fights.",
            passiveEn = "Lifeline: Al recibir daño mágico que reduciría tu vida por debajo del 35%, obtienes +10% de Omnivampirismo hasta salir de combate y un Escudo que absorbe 220-530 de Daño Mágico durante 3s. (70s Enfriamiento)\n\n💡 Coach Tips: Este objeto otorga a mix of damage, Resistencia Mágica and Aceleración de Habilidad, but its core value is the anti-burst passive: when hit by a dangerous burst of Daño Mágico, you instantly gain a strong protective Escudo and a temporary lifesteal/Omnivampirismo effect that lasts through the fight. Permite sobrevivir al daño explosivo de magos AP y mantenerse firme en la pelea.  Best for assassins, bruisers and AD Campeones who need to survive enemy magic burst and power through fights.",
            passivePt = "Lifeline: Al recibir Dano Mágico que reduciría tu vida por debajo del 35%, obtienes +10% de Omnivampirismo hasta salir de combate y un Escudo que absorbe 220-530 de Dano Mágico durante 3s. (70s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item concede a mix of damage, Resistência Mágica and Aceleração de Habilidade, but its core value is the anti-burst passive: when hit by a dangerous burst of Dano Mágico, you instantly gain a strong protective Escudo and a temporary lifesteal/Omnivampirismo effect that lasts through the fight. Permite sobrevivir al daño explosivo de magos AP y mantenerse firme en la pelea.  Best for assassins, bruisers and AD Campeões who need to survive enemy magic burst and power through fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301577_maw-of-malmortius.webp"
        ))
        add(WildRiftItem(
            id = "death_s_dance_defense",
            name = "Danza de la Muerte",
            nameEn = "Death's Dance",
            namePt = "Dança da Morte",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+35 Daño de Ataque • +40 Armadura • +15 Aceleración de Habilidad",
            statsEn = "+35 Attack Damage • +40 Armor • +15 Ability Haste",
            statsPt = "+35 Dano de Ataque • +40 Armadura • +15 Aceleração de Habilidade",
            passive = "Defy: Campeón takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Daño Físico and Daño Mágico received (12% for A distancia Campeones) is dealt to you over 3 segundos as Daño Verdadero instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleración de Habilidad, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            passiveEn = "Defy: Campeón takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Daño Físico and Daño Mágico received (12% for A distancia Campeones) is dealt to you over 3 segundos as Daño Verdadero instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleración de Habilidad, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            passivePt = "Defy: Campeão takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Dano Físico and Dano Mágico received (12% for À distância Campeões) is dealt to you over 3 segundos as Dano Verdadeiro instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleração de Habilidade, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301539_yordle-deaths-dance.webp"
        ))
        add(WildRiftItem(
            id = "phantom_dancer_physical",
            name = "Bailarín Espectral",
            nameEn = "Phantom Dancer",
            namePt = "Dançarina Fantasma",
            category = "Daño Físico",
            goldCost = 2900,
            stats = "+20 Daño de Ataque • +25% Probabilidad de Crítico • +40% Velocidad de Ataque",
            statsEn = "+20 Attack Damage • +25% Critical Rate • +40% Attack Speed",
            statsPt = "+20 Dano de Ataque • +25% Chance de Crítico • +40% Velocidade de Ataque",
            passive = "Swift-Footed:  +5% Velocidad de Movimiento.\nSpectral Waltz: One hit, your attacks grants 25% Velocidad de Ataque and +7% Movement Speedfor 6s. Los efectos no se acumulan. (10s Enfriamiento reduced by 1s when your attack hits an enemy.)\n\n💡 Consejos del Coach: Este objeto otorga a strong boost to Velocidad de Ataque and mobility, turning your ataques básicos into a tool for controlling the tempo of fights. Hits on campeones enemigos temporarily increase your attack and Velocidad de Movimiento, and frequent hits reduce the effect’s downtime — perfect for kiting, chasing, and extended duels. Ideal for marksmen and autoataque bruisers who need mobility and consistent DPS.",
            passiveEn = "Swift-Footed:  +5% Velocidad de Movimiento.\nSpectral Waltz: One hit, your attacks grants 25% Velocidad de Ataque and +7% Movement Speedfor 6s. Los efectos no se acumulan. (10s Enfriamiento reduced by 1s when your attack hits an enemy.)\n\n💡 Coach Tips: Este objeto otorga a strong boost to Velocidad de Ataque and mobility, turning your ataques básicos into a tool for controlling the tempo of fights. Hits on campeones enemigos temporarily increase your attack and Velocidad de Movimiento, and frequent hits reduce the effect’s downtime — perfect for kiting, chasing, and extended duels. Ideal for marksmen and autoataque bruisers who need mobility and consistent DPS.",
            passivePt = "Swift-Footed:  +5% Velocidade de Movimento.\nSpectral Waltz: One hit, your attacks grants 25% Velocidade de Ataque and +7% Movement Speedfor 6s. Los efectos no se acumulan. (10s Tempo de Recarga reduced by 1s when your attack hits an enemy.)\n\n💡 Dicas do Coach: Este item concede a strong boost to Velocidade de Ataque and mobility, turning your ataques básicos into a tool for controlling the tempo of fights. Hits on campeões inimigos temporarily increase your attack and Velocidade de Movimento, and frequent hits reduce the effect’s downtime — perfect for kiting, chasing, and extended duels. Ideal for marksmen and autoataque bruisers who need mobility and consistent DPS.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302001_phantom-dancer.webp"
        ))
        add(WildRiftItem(
            id = "nashor_s_tooth_magic",
            name = "Diente de Nashor",
            nameEn = "Nashor's Tooth",
            namePt = "Dente de Nashor",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+45% Velocidad de Ataque • +20 Aceleración de Habilidad",
            statsEn = "+45% Attack Speed • +20 Ability Haste",
            statsPt = "+45% Velocidade de Ataque • +20 Aceleração de Habilidade",
            passive = "Magic Fang: Obtain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who blend autoataques with Daño Mágico. It provides a hefty boost to Velocidad de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Daño de Ataque or Poder de Habilidad, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Daño Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeones like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Daño Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            passiveEn = "Magic Fang: Obtain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who blend autoataques with Daño Mágico. It provides a hefty boost to Velocidad de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Daño de Ataque or Poder de Habilidad, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Daño Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeones like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Daño Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            passivePt = "Magic Fang: Obtain 25 Dano de Ataque or 50 Poder de Habilidade (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who blend autoataques with Dano Mágico. It provides a hefty boost to Velocidade de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Dano de Ataque or Poder de Habilidade, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Dano Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeões like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Dano Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302010_nashors-tooth.webp"
        ))
        add(WildRiftItem(
            id = "wit_s_end_physical",
            name = "Wit's End",
            nameEn = "Wit's End",
            namePt = "Wit's End",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+45% Velocidad de Ataque • +45 Resistencia Mágica",
            statsEn = "+45% Attack Speed • +45 Magic Resistance",
            statsPt = "+45% Velocidade de Ataque • +45 Resistência Mágica",
            passive = "ataque básico deals Adicional Damage\nAt Wit's End: Basic Los ataques infligen 10-55 Adicional Daño Mágico. While below 50%Vida, Infligir daño to an campeón enemigo heals you for (Cuerpo a cuerpo 100% / Range 66%) of this effect's post-mitigation damage.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who want to deal sustained damage and have some defensive stats against Daño Mágico threats. It provides bonuses to Velocidad de Ataque and Resistencia Mágica, as well as adding Daño Mágico to your autoataques, making it effective against magic-based threats. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"While below 50% Vida\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect heals you when Infligir daño to an campeón enemigo, increasing survivability in fights. This item is especially useful for Campeones like Vayne, Irelia, or Master Yi, who can benefit from its on-hit Daño Mágico and Vida restoration effect.",
            passiveEn = "ataque básico deals Adicional Damage\nAt Wit's End: Basic Los ataques infligen 10-55 Adicional Daño Mágico. While below 50%Vida, Infligir daño to an campeón enemigo heals you for (Cuerpo a cuerpo 100% / Range 66%) of this effect's post-mitigation damage.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who want to deal sustained damage and have some defensive stats against Daño Mágico threats. It provides bonuses to Velocidad de Ataque and Resistencia Mágica, as well as adding Daño Mágico to your autoataques, making it effective against magic-based threats. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"While below 50% Vida\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect heals you when Infligir daño to an campeón enemigo, increasing survivability in fights. This item is especially useful for Campeones like Vayne, Irelia, or Master Yi, who can benefit from its on-hit Daño Mágico and Vida restoration effect.",
            passivePt = "ataque básico deals Adicional Damage\nAt Wit's End: Basic Los ataques infligen 10-55 Adicional Dano Mágico. While below 50%Vida, Infligir daño to an campeão inimigo heals you for (Corpo a corpo 100% / Range 66%) of this effect's post-mitigation damage.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who want to deal sustained damage and have some defensive stats against Dano Mágico threats. It provides bonuses to Velocidade de Ataque and Resistência Mágica, as well as adding Dano Mágico to your autoataques, making it effective against magic-based threats. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"While below 50% Vida\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect heals you when Infligir daño to an campeão inimigo, increasing survivability in fights. This item is especially useful for Campeões like Vayne, Irelia, or Master Yi, who can benefit from its on-hit Dano Mágico and Vida restoration effect.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302068_wits-end.webp"
        ))
        add(WildRiftItem(
            id = "essence_reaver_physical",
            name = "Segador de Esencia",
            nameEn = "Essence Reaver",
            namePt = "Colhedor de Essência",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+40 Daño de Ataque • +25% Probabilidad de Crítico • +20 Aceleración de Habilidad",
            statsEn = "+40 Attack Damage • +25% Critical Rate • +20 Ability Haste",
            statsPt = "+40 Dano de Ataque • +25% Chance de Crítico • +20 Aceleração de Habilidade",
            passive = "Los ataques otorgan Maná Regen, and damage amplification\nSpellblade: Casting an ability generates a Spellblade charge (max 2 charges) that lasts up to 10 segundo(s). Hitting an enemy with an attack consumes a charge, dealing 70 bonusas Daño Físico and granting 40 Velocidad de Movimiento for 2 segundo(s). This Adicional damage can asestar un Golpe Crítico. Each ability generates only one charge per 2 segundo(s).\nManá Siphon: Attacks restore 3% missingMana on-hit.\n\n💡 Consejos del Coach: Este objeto es ideal para hybrid auto‑attack Campeones who need Maná sustain and enhanced damage after casting abilities. It boosts Aceleración de Habilidad, and after using a skill, your next ataque básico hits harder and grants a burst of Velocidad de Movimiento. Additionally, auto‑attacks restore a portion of your missing Maná, keeping you in fights longer.  — A great pick for marksmen and fighters who weave spells into their auto‑attack rotations for maximum DPS and Maná sustainability.",
            passiveEn = "Los ataques otorgan Maná Regen, and damage amplification\nSpellblade: Casting an ability generates a Spellblade charge (max 2 charges) that lasts up to 10 segundo(s). Hitting an enemy with an attack consumes a charge, dealing 70 bonusas Daño Físico and granting 40 Velocidad de Movimiento for 2 segundo(s). This Adicional damage can asestar un Golpe Crítico. Each ability generates only one charge per 2 segundo(s).\nManá Siphon: Attacks restore 3% missingMana on-hit.\n\n💡 Coach Tips: Este objeto es ideal para hybrid auto‑attack Campeones who need Maná sustain and enhanced damage after casting abilities. It boosts Aceleración de Habilidad, and after using a skill, your next ataque básico hits harder and grants a burst of Velocidad de Movimiento. Additionally, auto‑attacks restore a portion of your missing Maná, keeping you in fights longer.  — A great pick for marksmen and fighters who weave spells into their auto‑attack rotations for maximum DPS and Maná sustainability.",
            passivePt = "Los ataques otorgan Maná Regen, and damage amplification\nSpellblade: Casting an ability generates a Spellblade charge (max 2 charges) that lasts up to 10 segundo(s). Hitting an enemy with an attack consumes a charge, dealing 70 bonusas Dano Físico and granting 40 Velocidade de Movimento for 2 segundo(s). This Adicional damage can asestar un Acerto Crítico. Each ability generates only one charge per 2 segundo(s).\nManá Siphon: Attacks restore 3% missingMana on-hit.\n\n💡 Dicas do Coach: Este item é ideal para hybrid auto‑attack Campeões who need Maná sustain and enhanced damage after casting abilities. It boosts Aceleração de Habilidade, and after using a skill, your next ataque básico hits harder and grants a burst of Velocidade de Movimento. Additionally, auto‑attacks restore a portion of your missing Maná, keeping you in fights longer.  — A great pick for marksmen and fighters who weave spells into their auto‑attack rotations for maximum DPS and Maná sustainability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302228_essence-reaver.webp"
        ))
        add(WildRiftItem(
            id = "serylda_s_grudge_physical",
            name = "Rencor de Serylda",
            nameEn = "Serylda's Grudge",
            namePt = "Rancor de Serylda",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+40 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+40 Attack Damage • +15 Ability Haste",
            statsPt = "+40 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Serylda’s Grudge\nPenetración de Armadura (%) and apply Ralentiza\nLast Whisper: Gain  +33% Penetración de Armadura.\nIcy: Damaging active abilities and empowered attacks Ralentización enemies by 30% for 1 segundo.\nFrostbite: Apply Frostbite to enemies slowed by Icy durante 6s. At 3 Frostbite stacks, all stacks are consumed to apply bleed, dealing (5 + 1-15 () + 15% Adicional) Daño Físico over 2s. Also applies 50% Heridas Graves durante 3s. (5s Enfriamiento per target)\nSerylda’\nThis item blends heavy Penetración de Armadura with crowd control: your active abilities and empowered hits Ralentización targets, and repeated Ralentiza trigger a bleed that also applies Heridas Graves. Perfect for Campeones who need to kite, execute priority targets, and curb their healing.",
            passiveEn = "Serylda’s Grudge\nPenetración de Armadura (%) and apply Ralentiza\nLast Whisper: Gain  +33% Penetración de Armadura.\nIcy: Damaging active abilities and empowered attacks Ralentización enemies by 30% for 1 segundo.\nFrostbite: Apply Frostbite to enemies slowed by Icy durante 6s. At 3 Frostbite stacks, all stacks are consumed to apply bleed, dealing (5 + 1-15 () + 15% Adicional) Daño Físico over 2s. Also applies 50% Heridas Graves durante 3s. (5s Enfriamiento per target)\nSerylda’\nThis item blends heavy Penetración de Armadura with crowd control: your active abilities and empowered hits Ralentización targets, and repeated Ralentiza trigger a bleed that also applies Heridas Graves. Perfect for Campeones who need to kite, execute priority targets, and curb their healing.",
            passivePt = "Serylda’s Grudge\nPenetração de Armadura (%) and apply Ralentiza\nLast Whisper: Gain  +33% Penetração de Armadura.\nIcy: Damaging active abilities and empowered attacks Ralentización enemies by 30% for 1 segundo.\nFrostbite: Apply Frostbite to enemies slowed by Icy durante 6s. At 3 Frostbite stacks, all stacks are consumed to apply bleed, dealing (5 + 1-15 () + 15% Adicional) Dano Físico over 2s. Also applies 50% Feridas Dolorosas durante 3s. (5s Tempo de Recarga per target)\nSerylda’\nThis item blends heavy Penetração de Armadura with crowd control: your active abilities and empowered hits Ralentización targets, and repeated Ralentiza trigger a bleed that also applies Feridas Dolorosas. Perfect for Campeões who need to kite, execute priority targets, and curb their healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302257_yordle-seryldas-grudge.webp"
        ))
        add(WildRiftItem(
            id = "navori_quickblades_physical",
            name = "Cuchillas Raudas de Navori",
            nameEn = "Navori Quickblades",
            namePt = "Adagas Rápidas Navori",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25% Probabilidad de Crítico • +45% Velocidad de Ataque • +5% Velocidad de Movimiento",
            statsEn = "+25% Critical Rate • +45% Attack Speed • +5% Move Speed",
            statsPt = "+25% Chance de Crítico • +45% Velocidade de Ataque • +5% Velocidade de Movimento",
            passive = "Abilities grant damage amplification and Enfriamiento reduction\nDeft Strikes: Attacks reduce the remaining cooldowns of your basic abilities by 15%.\n\n💡 Consejos del Coach: Este objeto es ideal para auto‑attackers who aim to amplify their ability damage and reduce cooldowns. Critical strikes accelerate your non‑ultimate abilities, while your skills hit harder based on your crit chance.  — A top pick for marksmen and assassins who weave auto‑attacks with spells to swiftly eliminate targets and maintain combat momentum.",
            passiveEn = "Abilities grant damage amplification and Enfriamiento reduction\nDeft Strikes: Attacks reduce the remaining cooldowns of your basic abilities by 15%.\n\n💡 Coach Tips: Este objeto es ideal para auto‑attackers who aim to amplify their ability damage and reduce cooldowns. Critical strikes accelerate your non‑ultimate abilities, while your skills hit harder based on your crit chance.  — A top pick for marksmen and assassins who weave auto‑attacks with spells to swiftly eliminate targets and maintain combat momentum.",
            passivePt = "Abilities grant damage amplification and Tempo de Recarga reduction\nDeft Strikes: Attacks reduce the remaining cooldowns of your basic abilities by 15%.\n\n💡 Dicas do Coach: Este item é ideal para auto‑attackers who aim to amplify their ability damage and reduce cooldowns. Critical strikes accelerate your non‑ultimate abilities, while your skills hit harder based on your crit chance.  — A top pick for marksmen and assassins who weave auto‑attacks with spells to swiftly eliminate targets and maintain combat momentum.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302298_navori-quickblades.webp"
        ))
        add(WildRiftItem(
            id = "edge_of_night_physical",
            name = "Filo de la Noche",
            nameEn = "Edge of Night",
            namePt = "Limiar da Noite",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+250 Vida Máxima • +50 Daño de Ataque",
            statsEn = "+250 Max Health • +50 Attack Damage",
            statsPt = "+250 Vida Máxima • +50 Dano de Ataque",
            passive = "Gouge:  +8 Penetración de Armadura.\nAnnul: Grants a spell Escudo that blocks the next hostile ability. This spell Escudo refreshes upon leaving combat with campeones enemigos. (35 segundo Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need a mix of offense and defense. It provides bonuses to maximum Vida and Daño de Ataque, along with increased Penetración de Armadura to help you shred through enemy Defensas. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Annul\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect grants you a spell Escudo, blocking the next enemy ability. This spell Escudo refreshes when leaving combat with campeones enemigos, making the item a great choice for Campeones who need protection from crowd control and to engage in fights, such as Ashe, Lux, or Morgana.",
            passiveEn = "Gouge:  +8 Penetración de Armadura.\nAnnul: Grants a spell Escudo that blocks the next hostile ability. This spell Escudo refreshes upon leaving combat with campeones enemigos. (35 segundo Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need a mix of offense and defense. It provides bonuses to maximum Vida and Daño de Ataque, along with increased Penetración de Armadura to help you shred through enemy Defensas. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Annul\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect grants you a spell Escudo, blocking the next enemy ability. This spell Escudo refreshes when leaving combat with campeones enemigos, making the item a great choice for Campeones who need protection from crowd control and to engage in fights, such as Ashe, Lux, or Morgana.",
            passivePt = "Gouge:  +8 Penetração de Armadura.\nAnnul: Grants a spell Escudo that blocks the next hostile ability. This spell Escudo refreshes upon leaving combat with campeões inimigos. (35 segundo Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need a mix of offense and defense. It provides bonuses to maximum Vida and Dano de Ataque, along with increased Penetração de Armadura to help you shred through enemy Defensas. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Annul\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect grants you a spell Escudo, blocking the next enemy ability. This spell Escudo refreshes when leaving combat with campeões inimigos, making the item a great choice for Campeões who need protection from crowd control and to engage in fights, such as Ashe, Lux, or Morgana.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302335_edge-of-night.webp"
        ))
        add(WildRiftItem(
            id = "divine_sunderer_physical",
            name = "Cercenador Divino",
            nameEn = "Divine Sunderer",
            namePt = "Ruptor Divino",
            category = "Daño Físico",
            goldCost = 3400,
            stats = "+425 Vida Máxima • +25 Daño de Ataque • +25 Aceleración de Habilidad",
            statsEn = "+425 Max Health • +25 Attack Damage • +25 Ability Haste",
            statsPt = "+425 Vida Máxima • +25 Dano de Ataque • +25 Aceleração de Habilidade",
            passive = "Anti-Vida attacks\nSpellblade: After using an ability, your next attack within 10 segundos deals (10% Cuerpo a cuerpo / 7% A distancia) of target’s maximum Vida as Adicional Daño Físico. If the target is a Campeón, heal for (6% Cuerpo a cuerpo / 2.5% A distancia) of the target's maximum Vida. (1.5s Enfriamiento) Damage is reduced vs structure.\nThis item offers a strong blend of survivability and damage: after using an ability your next ataque básico is empowered to deal Adicional damage based on the target’s Vida Máxima, and it heals you when used on campeones enemigos. Perfect for fighter-bruisiers and solo laners who weave abilities into autos and need sustain versus tanks and duelists.",
            passiveEn = "Anti-Vida attacks\nSpellblade: After using an ability, your next attack within 10 segundos deals (10% Cuerpo a cuerpo / 7% A distancia) of target’s maximum Vida as Adicional Daño Físico. If the target is a Campeón, heal for (6% Cuerpo a cuerpo / 2.5% A distancia) of the target's maximum Vida. (1.5s Enfriamiento) Damage is reduced vs structure.\nThis item offers a strong blend of survivability and damage: after using an ability your next ataque básico is empowered to deal Adicional damage based on the target’s Vida Máxima, and it heals you when used on campeones enemigos. Perfect for fighter-bruisiers and solo laners who weave abilities into autos and need sustain versus tanks and duelists.",
            passivePt = "Anti-Vida attacks\nSpellblade: After using an ability, your next attack within 10 segundos deals (10% Corpo a corpo / 7% À distância) of target’s maximum Vida as Adicional Dano Físico. If the target is a Campeão, heal for (6% Corpo a corpo / 2.5% À distância) of the target's maximum Vida. (1.5s Tempo de Recarga) Damage is reduced vs structure.\nThis item offers a strong blend of survivability and damage: after using an ability your next ataque básico is empowered to deal Adicional damage based on the target’s Vida Máxima, and it heals you when used on campeões inimigos. Perfect for fighter-bruisiers and solo laners who weave abilities into autos and need sustain versus tanks and duelists.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753303444_divine-sunderer.webp"
        ))
        add(WildRiftItem(
            id = "serpent_s_fang_physical",
            name = "Colmillo de Serpiente",
            nameEn = "Serpent's Fang",
            namePt = "Presa da Serpente",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+50 Daño de Ataque • +10 Aceleración de Habilidad",
            statsEn = "+50 Attack Damage • +10 Ability Haste",
            statsPt = "+50 Dano de Ataque • +10 Aceleração de Habilidade",
            passive = "Stab:  +15 Penetración de Armadura.\nEscudo Reaver: Infligir daño to an campeón enemigo reduces any shields they gain durante 3s. Cuerpo a cuerpo Campeones apply (10% of Adicional AD + 40)% Escudo reduction, capped at 60%; while A distancia Campeones apply (10% of Adicional AD + 25)% Escudo reduction, capped at 45%. When you damage an enemy who is unaffected by Escudo Reaver, all shields on them are reduced by the same values.\n\n💡 Consejos del Coach: Este objeto es ideal para assassins and Campeones who face enemies with a lot of shields. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, along with increased Penetración de Armadura, making it effective against well-protected enemies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Escudo Reaver\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to reduce the effectiveness of shields gained by campeones enemigos, depending on your Adicional Daño de Ataque, helping to quickly break through shields. This item is especially useful against Campeones who rely on shields for defense, such as Lux, Braum, and others.",
            passiveEn = "Stab:  +15 Penetración de Armadura.\nEscudo Reaver: Infligir daño to an campeón enemigo reduces any shields they gain durante 3s. Cuerpo a cuerpo Campeones apply (10% of Adicional AD + 40)% Escudo reduction, capped at 60%; while A distancia Campeones apply (10% of Adicional AD + 25)% Escudo reduction, capped at 45%. When you damage an enemy who is unaffected by Escudo Reaver, all shields on them are reduced by the same values.\n\n💡 Coach Tips: Este objeto es ideal para assassins and Campeones who face enemies with a lot of shields. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, along with increased Penetración de Armadura, making it effective against well-protected enemies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Escudo Reaver\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to reduce the effectiveness of shields gained by campeones enemigos, depending on your Adicional Daño de Ataque, helping to quickly break through shields. This item is especially useful against Campeones who rely on shields for defense, such as Lux, Braum, and others.",
            passivePt = "Stab:  +15 Penetração de Armadura.\nEscudo Reaver: Infligir daño to an campeão inimigo reduces any shields they gain durante 3s. Corpo a corpo Campeões apply (10% of Adicional AD + 40)% Escudo reduction, capped at 60%; while À distância Campeões apply (10% of Adicional AD + 25)% Escudo reduction, capped at 45%. When you damage an enemy who is unaffected by Escudo Reaver, all shields on them are reduced by the same values.\n\n💡 Dicas do Coach: Este item é ideal para assassins and Campeões who face enemies with a lot of shields. It provides bonuses to Dano de Ataque and Aceleração de Habilidade, along with increased Penetração de Armadura, making it effective against well-protected enemies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Escudo Reaver\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect allows you to reduce the effectiveness of shields gained by campeões inimigos, depending on your Adicional Dano de Ataque, helping to quickly break through shields. This item is especially useful against Campeões who rely on shields for defense, such as Lux, Braum, and others.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753303456_serpents-fang.webp"
        ))
        add(WildRiftItem(
            id = "chempunk_chainsword_physical",
            name = "Espada Sierra Quimopunk",
            nameEn = "Chempunk Chainsword",
            namePt = "Espada Quimiopunk",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+400 Vida Máxima • +45 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +45 Attack Damage • +15 Ability Haste",
            statsPt = "+400 Vida Máxima • +45 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Punishment: Dealing Daño Físico to campeones enemigos applies 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who face enemies with high sustain, such as Dr. Mundo, Soraka, and Yuumi, who have significant healing abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Punishment\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect applies Heridas Graves, reducing the effectiveness of healing and regeneration by 50%, making this item effective against Campeones who rely on healing. It’s also useful against Campeones who rely on lifesteal, such as Aatrox, Darius, and Fiora, as it helps reduce the effectiveness of their healing.",
            passiveEn = "Punishment: Dealing Daño Físico to campeones enemigos applies 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who face enemies with high sustain, such as Dr. Mundo, Soraka, and Yuumi, who have significant healing abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Punishment\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect applies Heridas Graves, reducing the effectiveness of healing and regeneration by 50%, making this item effective against Campeones who rely on healing. It’s also useful against Campeones who rely on lifesteal, such as Aatrox, Darius, and Fiora, as it helps reduce the effectiveness of their healing.",
            passivePt = "Punishment: Dealing Dano Físico to campeões inimigos applies 50% Feridas Dolorosas for 3 segundos.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who face enemies with high sustain, such as Dr. Mundo, Soraka, and Yuumi, who have significant healing abilities. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Punishment\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect applies Feridas Dolorosas, reducing the effectiveness of healing and regeneration by 50%, making this item effective against Campeões who rely on healing. It’s also useful against Campeões who rely on lifesteal, such as Aatrox, Darius, and Fiora, as it helps reduce the effectiveness of their healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304959_chempunk-chainsword.webp"
        ))
        add(WildRiftItem(
            id = "the_collector_physical",
            name = "El Coleccionista",
            nameEn = "The Collector",
            namePt = "A Coletora",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+45 Daño de Ataque • +25% Probabilidad de Crítico",
            statsEn = "+45 Attack Damage • +25% Critical Rate",
            statsPt = "+45 Dano de Ataque • +25% Chance de Crítico",
            passive = "Killer:  +10 Penetración de Armadura.\nDeath and Taxes: Infligir daño that would leave an campeón enemigo below (4% + 2% Probabilidad de Crítico) of their Vida Máxima executes them, permanently increases the Vida Máxima percentage execution threshold by 0.1%, and grants 25 Adicional gold.\nLimited to 1 The Collector.\n\n💡 Consejos del Coach: Este objeto convierte your auto‑attacks into a finisher: it boosts your penetration for shredding Armadura and automatically executes low‑Vida enemies, rewarding you with extra gold.  — Perfect for marksmen and assassins who need reliable executes on vulnerable targets while snowballing their gold income.",
            passiveEn = "Killer:  +10 Penetración de Armadura.\nDeath and Taxes: Infligir daño that would leave an campeón enemigo below (4% + 2% Probabilidad de Crítico) of their Vida Máxima executes them, permanently increases the Vida Máxima percentage execution threshold by 0.1%, and grants 25 Adicional gold.\nLimited to 1 The Collector.\n\n💡 Coach Tips: Este objeto convierte your auto‑attacks into a finisher: it boosts your penetration for shredding Armadura and automatically executes low‑Vida enemies, rewarding you with extra gold.  — Perfect for marksmen and assassins who need reliable executes on vulnerable targets while snowballing their gold income.",
            passivePt = "Killer:  +10 Penetração de Armadura.\nDeath and Taxes: Infligir daño that would leave an campeão inimigo below (4% + 2% Chance de Crítico) of their Vida Máxima executes them, permanently increases the Vida Máxima percentage execution threshold by 0.1%, and grants 25 Adicional gold.\nLimited to 1 The Collector.\n\n💡 Dicas do Coach: Este item converte your auto‑attacks into a finisher: it boosts your penetration for shredding Armadura and automatically executes low‑Vida enemies, rewarding you with extra gold.  — Perfect for marksmen and assassins who need reliable executes on vulnerable targets while snowballing their gold income.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304861_the-collector.webp"
        ))
        add(WildRiftItem(
            id = "sterak_s_gage_physical",
            name = "Guantelete de Sterak",
            nameEn = "Sterak's Gage",
            namePt = "Sinal de Sterak",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+400 Vida Máxima",
            statsEn = "+400 Max Health",
            statsPt = "+400 Vida Máxima",
            passive = "Heavy Handed: +50% base Daño de Ataque as Adicional Daño de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Enfriamiento).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Daño de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeones with burst damage, such as Zed and Talon, and against Campeones with heavy CC, like Lissandra and Nautilus.",
            passiveEn = "Heavy Handed: +50% base Daño de Ataque as Adicional Daño de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Enfriamiento).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Daño de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeones with burst damage, such as Zed and Talon, and against Campeones with heavy CC, like Lissandra and Nautilus.",
            passivePt = "Heavy Handed: +50% base Dano de Ataque as Adicional Dano de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Tempo de Recarga).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Dano de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeões with burst damage, such as Zed and Talon, and against Campeões with heavy CC, like Lissandra and Nautilus.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305021_steraks-gage.webp"
        ))
        add(WildRiftItem(
            id = "spear_of_shojin_physical",
            name = "Lanza de Shojin",
            nameEn = "Lanza de Shojin",
            namePt = "Lanza de Shojin",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+450 Vida Máxima • +40 Daño de Ataque",
            statsEn = "+450 Max Health • +40 Attack Damage",
            statsPt = "+450 Vida Máxima • +40 Dano de Ataque",
            passive = "Dragonforce:  +20% Aceleración de Habilidad.\nFocused Will: Infligir daño to Monstruos or enemies with abilities increases your Campeón’s ability and passive damage by 3% durante 6s. (Stacks 4 times).\n\n💡 Consejos del Coach: Este objeto otorga a mix of survivability and empowered ability usage: it increases your staying power, reduces ability cooldowns, and temporarily boosts your ability and passive damage after engaging enemies or clearing Monstruos. Perfect for duelist bruisers who want to cast more often in fights and gain an edge in extended skirmishes or split-push scenarios.  Suited for Campeones who weave autos with frequent ability casts.",
            passiveEn = "Dragonforce:  +20% Aceleración de Habilidad.\nFocused Will: Infligir daño to Monstruos or enemies with abilities increases your Campeón’s ability and passive damage by 3% durante 6s. (Stacks 4 times).\n\n💡 Coach Tips: Este objeto otorga a mix of survivability and empowered ability usage: it increases your staying power, reduces ability cooldowns, and temporarily boosts your ability and passive damage after engaging enemies or clearing Monstruos. Perfect for duelist bruisers who want to cast more often in fights and gain an edge in extended skirmishes or split-push scenarios.  Suited for Campeones who weave autos with frequent ability casts.",
            passivePt = "Dragonforce:  +20% Aceleração de Habilidade.\nFocused Will: Infligir daño to Monstros or enemies with abilities increases your Campeão’s ability and passive damage by 3% durante 6s. (Stacks 4 times).\n\n💡 Dicas do Coach: Este item concede a mix of survivability and empowered ability usage: it increases your staying power, reduces ability cooldowns, and temporarily boosts your ability and passive damage after engaging enemies or clearing Monstros. Perfect for duelist bruisers who want to cast more often in fights and gain an edge in extended skirmishes or split-push scenarios.  Suited for Campeões who weave autos with frequent ability casts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304984_spear-of-shojin.webp"
        ))
        add(WildRiftItem(
            id = "titanic_hydra_defense",
            name = "Hidra Titánica",
            nameEn = "Titanic Hydra",
            namePt = "Hidra Titânica",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+450 Vida Máxima • +40 Daño de Ataque",
            statsEn = "+450 Max Health • +40 Attack Damage",
            statsPt = "+450 Vida Máxima • +40 Dano de Ataque",
            passive = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Daño Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Daño Físico equal to 80 + 10% Adicional to enemies behind the target. A distancia Campeones deal 75% of the damage.\n\n💡 Consejos del Coach: Este objeto convierte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Daño Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Cuerpo a cuerpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            passiveEn = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Daño Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Daño Físico equal to 80 + 10% Adicional to enemies behind the target. A distancia Campeones deal 75% of the damage.\n\n💡 Coach Tips: Este objeto convierte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Daño Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Cuerpo a cuerpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            passivePt = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Dano Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Dano Físico equal to 80 + 10% Adicional to enemies behind the target. À distância Campeões deal 75% of the damage.\n\n💡 Dicas do Coach: Este item converte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Dano Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Corpo a corpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304937_titanic-hydra.webp"
        ))
        add(WildRiftItem(
            id = "terminus_physical",
            name = "Términus",
            nameEn = "Terminus",
            namePt = "Término",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+40 Daño de Ataque • +30% Velocidad de Ataque",
            statsEn = "+40 Attack Damage • +30% Attack Speed",
            statsPt = "+40 Dano de Ataque • +30% Velocidade de Ataque",
            passive = "Increases Armadura Pen, Megic Pen, Armadura, and Resistencia Mágica\nShadow: Los ataques infligen 35 Adicional Daño Mágico on-hit.\nJuxtaposition: Alternate between Light and Dark on-hits when attacking. Light Los ataques otorgan 5-8 Armadura and Resistencia Mágica for 5 segundos on hit. Dark Los ataques otorgan 11% Armadura Pen and 11% Magic Pen for 5 segundos on hit. Each on-hit effect stacks up to 3 times. While you have this item, Adicional Armadura Pen and Magic Pen granted by it is capped at 40%.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who rely heavily on autoataques and can benefit from mixed penetration effects and stacking resistances. It provides bonuses to Daño de Ataque and Velocidad de Ataque, and adds Adicional Daño Mágico to your autoataques. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Juxtaposition\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect alternates between Light and Dark on-hits, granting you bonuses to resistances or penetration depending on which effect is triggered. Light Los ataques otorgan Armadura and Resistencia Mágica, while Dark Los ataques otorgan Armadura and Penetración Mágica. This item is especially useful for Campeones who autoataque frequently and can take advantage of the stacking resistances and penetration effects.",
            passiveEn = "Increases Armadura Pen, Megic Pen, Armadura, and Resistencia Mágica\nShadow: Los ataques infligen 35 Adicional Daño Mágico on-hit.\nJuxtaposition: Alternate between Light and Dark on-hits when attacking. Light Los ataques otorgan 5-8 Armadura and Resistencia Mágica for 5 segundos on hit. Dark Los ataques otorgan 11% Armadura Pen and 11% Magic Pen for 5 segundos on hit. Each on-hit effect stacks up to 3 times. While you have this item, Adicional Armadura Pen and Magic Pen granted by it is capped at 40%.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who rely heavily on autoataques and can benefit from mixed penetration effects and stacking resistances. It provides bonuses to Daño de Ataque and Velocidad de Ataque, and adds Adicional Daño Mágico to your autoataques. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Juxtaposition\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect alternates between Light and Dark on-hits, granting you bonuses to resistances or penetration depending on which effect is triggered. Light Los ataques otorgan Armadura and Resistencia Mágica, while Dark Los ataques otorgan Armadura and Penetración Mágica. This item is especially useful for Campeones who autoataque frequently and can take advantage of the stacking resistances and penetration effects.",
            passivePt = "Increases Armadura Pen, Megic Pen, Armadura, and Resistência Mágica\nShadow: Los ataques infligen 35 Adicional Dano Mágico on-hit.\nJuxtaposition: Alternate between Light and Dark on-hits when attacking. Light Los ataques otorgan 5-8 Armadura and Resistência Mágica for 5 segundos on hit. Dark Los ataques otorgan 11% Armadura Pen and 11% Magic Pen for 5 segundos on hit. Each on-hit effect stacks up to 3 times. While you have this item, Adicional Armadura Pen and Magic Pen granted by it is capped at 40%.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who rely heavily on autoataques and can benefit from mixed penetration effects and stacking resistances. It provides bonuses to Dano de Ataque and Velocidade de Ataque, and adds Adicional Dano Mágico to your autoataques. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Juxtaposition\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect alternates between Light and Dark on-hits, granting you bonuses to resistances or penetration depending on which effect is triggered. Light Los ataques otorgan Armadura and Resistência Mágica, while Dark Los ataques otorgan Armadura and Penetração Mágica. This item is especially useful for Campeões who autoataque frequently and can take advantage of the stacking resistances and penetration effects.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305038_terminus.webp"
        ))
        add(WildRiftItem(
            id = "sundered_sky_physical",
            name = "Cielo Desgarrado",
            nameEn = "Sundered Sky",
            namePt = "Céu Dividido",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+350 Vida Máxima • +40 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +40 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Periodically empowers attacks\nLightshield Strike: The first attack against an campeón enemigo deals asestan Golpes Críticos (6s Enfriamiento per target), dealing 160% damage  and restores Vida (equal to 125% base Daño de Ataque + 6% of missing Vida to you.\n\n💡 Consejos del Coach: Este objeto es ideal para AD bruisers who deal Daño Físico and need sustain during fights. It provides bonuses to maximum Vida, Daño de Ataque, and Aceleración de Habilidad, helping to improve both survivability and damage output. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lightshield Strike\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect causes your first attack against an campeón enemigo to asestar un Golpe Crítico, dealing 160% damage and restoring Vida based on your base Daño de Ataque and a percentage of your missing Vida. This makes the item a great choice for AD bruisers who engage in fights and require both extra damage and sustain.",
            passiveEn = "Periodically empowers attacks\nLightshield Strike: The first attack against an campeón enemigo deals asestan Golpes Críticos (6s Enfriamiento per target), dealing 160% damage  and restores Vida (equal to 125% base Daño de Ataque + 6% of missing Vida to you.\n\n💡 Coach Tips: Este objeto es ideal para AD bruisers who deal Daño Físico and need sustain during fights. It provides bonuses to maximum Vida, Daño de Ataque, and Aceleración de Habilidad, helping to improve both survivability and damage output. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lightshield Strike\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect causes your first attack against an campeón enemigo to asestar un Golpe Crítico, dealing 160% damage and restoring Vida based on your base Daño de Ataque and a percentage of your missing Vida. This makes the item a great choice for AD bruisers who engage in fights and require both extra damage and sustain.",
            passivePt = "Periodically empowers attacks\nLightshield Strike: The first attack against an campeão inimigo deals asestan Golpes Críticos (6s Tempo de Recarga per target), dealing 160% damage  and restores Vida (equal to 125% base Dano de Ataque + 6% of missing Vida to you.\n\n💡 Dicas do Coach: Este item é ideal para AD bruisers who deal Dano Físico and need sustain during fights. It provides bonuses to maximum Vida, Dano de Ataque, and Aceleração de Habilidade, helping to improve both survivability and damage output. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lightshield Strike\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect causes your first attack against an campeão inimigo to asestar un Acerto Crítico, dealing 160% damage and restoring Vida based on your base Dano de Ataque and a percentage of your missing Vida. This makes the item a great choice for AD bruisers who engage in fights and require both extra damage and sustain.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305059_sundered-sky.webp"
        ))
        add(WildRiftItem(
            id = "eclipse_physical",
            name = "Eclipse",
            nameEn = "Eclipse",
            namePt = "Eclipse",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+65 Daño de Ataque • +20 Aceleración de Habilidad",
            statsEn = "+65 Attack Damage • +20 Ability Haste",
            statsPt = "+65 Dano de Ataque • +20 Aceleração de Habilidade",
            passive = "Gain a Escudo and deal Adicional damage\nEver Rising Moon: Hitting an campeón enemigo with 2 separate attacks or abilities within 1.8s deals Adicional Daño Físico equal to 7% of the target's Vida Máxima(3.5% for A distancia Campeones), and grants you a Escudo that absorbs damage equal to 140 + 35% Adicional Daño de Ataque (70 + 18% Adicional Daño de Ataque for A distancia Campeones) durante 2s. (6s Cooldawn)\n\n💡 Consejos del Coach: Este objeto es ideal para assassins who want to burst down enemies quickly. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, allowing you to deal more damage and use your abilities more frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Ever Rising Moon\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when hitting an campeón enemigo with two separate attacks or abilities within 1.8 segundos, dealing Adicional Daño Físico based on the target's Vida Máxima and granting you a Escudo that absorbs damage. This makes the item a great choice for assassins who want to quickly eliminate targets and gain extra survivability.",
            passiveEn = "Gain a Escudo and deal Adicional damage\nEver Rising Moon: Hitting an campeón enemigo with 2 separate attacks or abilities within 1.8s deals Adicional Daño Físico equal to 7% of the target's Vida Máxima(3.5% for A distancia Campeones), and grants you a Escudo that absorbs damage equal to 140 + 35% Adicional Daño de Ataque (70 + 18% Adicional Daño de Ataque for A distancia Campeones) durante 2s. (6s Cooldawn)\n\n💡 Coach Tips: Este objeto es ideal para assassins who want to burst down enemies quickly. It provides bonuses to Daño de Ataque and Aceleración de Habilidad, allowing you to deal more damage and use your abilities more frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Ever Rising Moon\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when hitting an campeón enemigo with two separate attacks or abilities within 1.8 segundos, dealing Adicional Daño Físico based on the target's Vida Máxima and granting you a Escudo that absorbs damage. This makes the item a great choice for assassins who want to quickly eliminate targets and gain extra survivability.",
            passivePt = "Gain a Escudo and deal Adicional damage\nEver Rising Moon: Hitting an campeão inimigo with 2 separate attacks or abilities within 1.8s deals Adicional Dano Físico equal to 7% of the target's Vida Máxima(3.5% for À distância Campeões), and grants you a Escudo that absorbs damage equal to 140 + 35% Adicional Dano de Ataque (70 + 18% Adicional Dano de Ataque for À distância Campeões) durante 2s. (6s Cooldawn)\n\n💡 Dicas do Coach: Este item é ideal para assassins who want to burst down enemies quickly. It provides bonuses to Dano de Ataque and Aceleração de Habilidade, allowing you to deal more damage and use your abilities more frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Ever Rising Moon\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when hitting an campeão inimigo with two separate attacks or abilities within 1.8 segundos, dealing Adicional Dano Físico based on the target's Vida Máxima and granting you a Escudo that absorbs damage. This makes the item a great choice for assassins who want to quickly eliminate targets and gain extra survivability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305145_eclipse.webp"
        ))
        add(WildRiftItem(
            id = "soul_transfer_physical",
            name = "Transferencia de Alma",
            nameEn = "Transferencia de Alma",
            namePt = "Transferencia de Alma",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+25 Daño de Ataque • +25% Probabilidad de Crítico • +30% Velocidad de Ataque",
            statsEn = "+25 Attack Damage • +25% Critical Rate • +30% Attack Speed",
            statsPt = "+25 Dano de Ataque • +25% Chance de Crítico • +30% Velocidade de Ataque",
            passive = "Shadow Dance: When your attack asestan Golpes Críticos an campeón enemigo or a large monster, summon a clone that lasts 4 segundo(s) to attack nearby enemies. The clone inherits 20% of your Daño de Ataque and additionally gains 30% of your Probabilidad de Crítico as Velocidad de Ataque. Up to two clones can exist at one time.\nIf a clone moves more than 600 units away from you, it will disappear early.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones relying on critical strikes and autoataques, especially in extended teamfights. It grants Adicional Daño de Ataque, Golpe Crítico chance, and Velocidad de Ataque. On a Golpe Crítico against a Campeón or large monster, you summon a clone that attacks nearby enemies— the clone inherits a portion of your AD and converts extra crit chance into Velocidad de Ataque.  Ideal for marksmen who frequently land crits and need extra multi-target damage.",
            passiveEn = "Shadow Dance: When your attack asestan Golpes Críticos an campeón enemigo or a large monster, summon a clone that lasts 4 segundo(s) to attack nearby enemies. The clone inherits 20% of your Daño de Ataque and additionally gains 30% of your Probabilidad de Crítico as Velocidad de Ataque. Up to two clones can exist at one time.\nIf a clone moves more than 600 units away from you, it will disappear early.\n\n💡 Coach Tips: Este objeto es ideal para Campeones relying on critical strikes and autoataques, especially in extended teamfights. It grants Adicional Daño de Ataque, Golpe Crítico chance, and Velocidad de Ataque. On a Golpe Crítico against a Campeón or large monster, you summon a clone that attacks nearby enemies— the clone inherits a portion of your AD and converts extra crit chance into Velocidad de Ataque.  Ideal for marksmen who frequently land crits and need extra multi-target damage.",
            passivePt = "Shadow Dance: When your attack asestan Golpes Críticos an campeão inimigo or a large monster, summon a clone that lasts 4 segundo(s) to attack nearby enemies. The clone inherits 20% of your Dano de Ataque and additionally gains 30% of your Chance de Crítico as Velocidade de Ataque. Up to two clones can exist at one time.\nIf a clone moves more than 600 units away from you, it will disappear early.\n\n💡 Dicas do Coach: Este item é ideal para Campeões relying on critical strikes and autoataques, especially in extended teamfights. It grants Adicional Dano de Ataque, Acerto Crítico chance, and Velocidade de Ataque. On a Acerto Crítico against a Campeão or large monster, you summon a clone that attacks nearby enemies— the clone inherits a portion of your AD and converts extra crit chance into Velocidade de Ataque.  Ideal for marksmen who frequently land crits and need extra multi-target damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305204_soul-transfer.webp"
        ))
        add(WildRiftItem(
            id = "hullbreaker_physical",
            name = "Rompecascos",
            nameEn = "Hullbreaker",
            namePt = "Quebracascos",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+400 Vida Máxima • +50 Daño de Ataque",
            statsEn = "+400 Max Health • +50 Attack Damage",
            statsPt = "+400 Vida Máxima • +50 Dano de Ataque",
            passive = "Set Sail: Gain 5% Velocidad de Movimiento.\nSkipper: Every 4th attack against Campeones and epic Monstruos deals Adicional Daño Físico equal to 160% base  plus 5%(A distancia Campeones deal 40% of the damage), increased to 240% base  plus 9%against structures (A distancia Campeones deal 40% of the damage).\nBoarding Party: Nearby allied siege and super Súbditos gain 20-130 Armadura (25% Adicional if you're a A distancia Campeón) and 10-120 Resistencia Mágica() (25% Adicional if you're a A distancia Campeón).\nThis item is the cornerstone of a powerful split‑push strategy. It provides significant Vida and Daño de Ataque, boosting both your survivability and tower‑breaking potential. The “Set Sail” passive steadily increases your Velocidad de Movimiento, helping you rotate between lanes and avoid ganks. Every fifth attack on Campeones or epic Monstruos triggers “Skipper”, dealing hefty Adicional Daño Físico—and even more against structures—making it indispensable for rapid turret takedowns. Additionally, “Boarding Party” buffs nearby allied siege and super Súbditos with extra resistances, amplifying your split‑push threat. Perfect for solo laners who want to apply pressure on the map and force enemy responses without directly joining teamfights.",
            passiveEn = "Set Sail: Gain 5% Velocidad de Movimiento.\nSkipper: Every 4th attack against Campeones and epic Monstruos deals Adicional Daño Físico equal to 160% base  plus 5%(A distancia Campeones deal 40% of the damage), increased to 240% base  plus 9%against structures (A distancia Campeones deal 40% of the damage).\nBoarding Party: Nearby allied siege and super Súbditos gain 20-130 Armadura (25% Adicional if you're a A distancia Campeón) and 10-120 Resistencia Mágica() (25% Adicional if you're a A distancia Campeón).\nThis item is the cornerstone of a powerful split‑push strategy. It provides significant Vida and Daño de Ataque, boosting both your survivability and tower‑breaking potential. The “Set Sail” passive steadily increases your Velocidad de Movimiento, helping you rotate between lanes and avoid ganks. Every fifth attack on Campeones or epic Monstruos triggers “Skipper”, dealing hefty Adicional Daño Físico—and even more against structures—making it indispensable for rapid turret takedowns. Additionally, “Boarding Party” buffs nearby allied siege and super Súbditos with extra resistances, amplifying your split‑push threat. Perfect for solo laners who want to apply pressure on the map and force enemy responses without directly joining teamfights.",
            passivePt = "Set Sail: Gain 5% Velocidade de Movimento.\nSkipper: Every 4th attack against Campeões and epic Monstros deals Adicional Dano Físico equal to 160% base  plus 5%(À distância Campeões deal 40% of the damage), increased to 240% base  plus 9%against structures (À distância Campeões deal 40% of the damage).\nBoarding Party: Nearby allied siege and super Tropas gain 20-130 Armadura (25% Adicional if you're a À distância Campeão) and 10-120 Resistência Mágica() (25% Adicional if you're a À distância Campeão).\nThis item is the cornerstone of a powerful split‑push strategy. It provides significant Vida and Dano de Ataque, boosting both your survivability and tower‑breaking potential. The “Set Sail” passive steadily increases your Velocidade de Movimento, helping you rotate between lanes and avoid ganks. Every fifth attack on Campeões or epic Monstros triggers “Skipper”, dealing hefty Adicional Dano Físico—and even more against structures—making it indispensable for rapid turret takedowns. Additionally, “Boarding Party” buffs nearby allied siege and super Tropas with extra resistances, amplifying your split‑push threat. Perfect for solo laners who want to apply pressure on the map and force enemy responses without directly joining teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304821_hullbreaker.webp"
        ))
        add(WildRiftItem(
            id = "guinsoo_s_rageblade_magic",
            name = "Espadafuria de Guinsoo",
            nameEn = "Espadafuria de Guinsoo",
            namePt = "Espadafuria de Guinsoo",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+30% Velocidad de Ataque",
            statsEn = "+30% Attack Speed",
            statsPt = "+30% Velocidade de Ataque",
            passive = "Surge: Gain 5% Velocidad de Movimiento.\nChaos: Gain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nWrath: Los ataques infligen 30 Daño Mágico but no longer Golpe Crítico. Fore every 1% Golpe Crítico Rate gained from items, your Daño Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Probabilidad de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidad de Ataque, staking up to 4 times for a maximum of 32% Velocidad de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Consejos del Coach: Este objeto es ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Daño Mágico, provides a powerful ramp of Velocidad de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            passiveEn = "Surge: Gain 5% Velocidad de Movimiento.\nChaos: Gain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nWrath: Los ataques infligen 30 Daño Mágico but no longer Golpe Crítico. Fore every 1% Golpe Crítico Rate gained from items, your Daño Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Probabilidad de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidad de Ataque, staking up to 4 times for a maximum of 32% Velocidad de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Coach Tips: Este objeto es ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Daño Mágico, provides a powerful ramp of Velocidad de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            passivePt = "Surge: Gain 5% Velocidade de Movimento.\nChaos: Gain 25 Dano de Ataque or 50 Poder de Habilidade (Adaptive).\nWrath: Los ataques infligen 30 Dano Mágico but no longer Acerto Crítico. Fore every 1% Acerto Crítico Rate gained from items, your Dano Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Chance de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidade de Ataque, staking up to 4 times for a maximum of 32% Velocidade de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Dicas do Coach: Este item é ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Dano Mágico, provides a powerful ramp of Velocidade de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1768000022_abb24fd724faa77b82baf985dea956b8eae9f31a-512x512.webp"
        ))
        add(WildRiftItem(
            id = "kraken_slayer_physical",
            name = "Verdugo de Krakens",
            nameEn = "Verdugo de Krakens",
            namePt = "Verdugo de Krakens",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+40 Daño de Ataque • +30% Velocidad de Ataque",
            statsEn = "+40 Attack Damage • +30% Attack Speed",
            statsPt = "+40 Dano de Ataque • +30% Velocidade de Ataque",
            passive = "Deal Adicional Daño Físico\nCloud Stride:  +5% Velocidad de Movimiento.\nBring it Down: Every third attack deals 120-160 () Adicional Daño Físico (110-150 () for A distancia Campeones), increased by 1% per 1% Vida the target is missing, up to an increase of 70%.\nThis item boosts your ataques básicos by providing extra damage, Velocidad de Ataque, and a mobility Adicional for better positioning. Periodically your hits deal Adicional damage that scales with the target’s missing Vida, making it strong both versus bulky targets and for finishing off low-HP enemies. A top pick for marksmen and autoataque focused builds that want reliable sustained DPS and execute potential.",
            passiveEn = "Deal Adicional Daño Físico\nCloud Stride:  +5% Velocidad de Movimiento.\nBring it Down: Every third attack deals 120-160 () Adicional Daño Físico (110-150 () for A distancia Campeones), increased by 1% per 1% Vida the target is missing, up to an increase of 70%.\nThis item boosts your ataques básicos by providing extra damage, Velocidad de Ataque, and a mobility Adicional for better positioning. Periodically your hits deal Adicional damage that scales with the target’s missing Vida, making it strong both versus bulky targets and for finishing off low-HP enemies. A top pick for marksmen and autoataque focused builds that want reliable sustained DPS and execute potential.",
            passivePt = "Deal Adicional Dano Físico\nCloud Stride:  +5% Velocidade de Movimento.\nBring it Down: Every third attack deals 120-160 () Adicional Dano Físico (110-150 () for À distância Campeões), increased by 1% per 1% Vida the target is missing, up to an increase of 70%.\nThis item boosts your ataques básicos by providing extra damage, Velocidade de Ataque, and a mobility Adicional for better positioning. Periodically your hits deal Adicional damage that scales with the target’s missing Vida, making it strong both versus bulky targets and for finishing off low-HP enemies. A top pick for marksmen and autoataque focused builds that want reliable sustained DPS and execute potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630263_kraken-slayer.webp"
        ))
        add(WildRiftItem(
            id = "overlord_s_bloodmail_defense",
            name = "Armadura Sangrienta del Señor Supremo",
            nameEn = "Armadura Sangrienta del Señor Supremo",
            namePt = "Armadura Sangrienta del Señor Supremo",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+450 Vida Máxima • +30 Daño de Ataque",
            statsEn = "+450 Max Health • +30 Attack Damage",
            statsPt = "+450 Vida Máxima • +30 Dano de Ataque",
            passive = "Gain Daño de Ataque when losing Vida\nTyranny: Gain Daño de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Daño de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            passiveEn = "Gain Daño de Ataque when losing Vida\nTyranny: Gain Daño de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Daño de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            passivePt = "Gain Dano de Ataque when losing Vida\nTyranny: Gain Dano de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Dano de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630274_overlords-bloodmail.webp"
        ))
        add(WildRiftItem(
            id = "experimental_hexplate_physical",
            name = "Hexoplaca Experimental",
            nameEn = "Hexoplaca Experimental",
            namePt = "Hexoplaca Experimental",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+400 Vida Máxima • +35 Daño de Ataque • +20% Velocidad de Ataque",
            statsEn = "+400 Max Health • +35 Attack Damage • +20% Attack Speed",
            statsPt = "+400 Vida Máxima • +35 Dano de Ataque • +20% Velocidade de Ataque",
            passive = "Gain Velocidad de Ataque & Velocidad de Movimiento when your ultimate is cast\nHexcharged:  Gain +20 Aceleración de Habilidad for your ultimate ability.\nOverdrive: After using your ultimate ability, gain 40% Velocidad de Ataque (20% for A distancia Campeones) and 20% Velocidad de Movimiento (10% for A distancia Campeones) for 8s. (30s Enfriamiento)\nThis item blends survivability with explosive offensive potential. It grants extra Vida, attack power and Velocidad de Ataque, shortens ultimate cooldowns, and—most importantly—grants a strong burst of attack and Velocidad de Movimiento after using your ultimate, enabling you to convert your engage into high sustained damage and chase. Ideal for fighters and bruisers who rely on their ultimate to start fights and immediately follow up with empowered autos and mobility.",
            passiveEn = "Gain Velocidad de Ataque & Velocidad de Movimiento when your ultimate is cast\nHexcharged:  Gain +20 Aceleración de Habilidad for your ultimate ability.\nOverdrive: After using your ultimate ability, gain 40% Velocidad de Ataque (20% for A distancia Campeones) and 20% Velocidad de Movimiento (10% for A distancia Campeones) for 8s. (30s Enfriamiento)\nThis item blends survivability with explosive offensive potential. It grants extra Vida, attack power and Velocidad de Ataque, shortens ultimate cooldowns, and—most importantly—grants a strong burst of attack and Velocidad de Movimiento after using your ultimate, enabling you to convert your engage into high sustained damage and chase. Ideal for fighters and bruisers who rely on their ultimate to start fights and immediately follow up with empowered autos and mobility.",
            passivePt = "Gain Velocidade de Ataque & Velocidade de Movimento when your ultimate is cast\nHexcharged:  Gain +20 Aceleração de Habilidade for your ultimate ability.\nOverdrive: After using your ultimate ability, gain 40% Velocidade de Ataque (20% for À distância Campeões) and 20% Velocidade de Movimento (10% for À distância Campeões) for 8s. (30s Tempo de Recarga)\nThis item blends survivability with explosive offensive potential. It grants extra Vida, attack power and Velocidade de Ataque, shortens ultimate cooldowns, and—most importantly—grants a strong burst of attack and Velocidade de Movimento after using your ultimate, enabling you to convert your engage into high sustained damage and chase. Ideal for fighters and bruisers who rely on their ultimate to start fights and immediately follow up with empowered autos and mobility.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630328_experimental-hexplate.webp"
        ))
        add(WildRiftItem(
            id = "dominik_s_regards_physical",
            name = "Recuerdos de Lord Dominik",
            nameEn = "Lord Dominik's Regards",
            namePt = "Lembranças do Lorde Dominik",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+30 Daño de Ataque • +36% Penetración de Armadura • +25% Probabilidad de Crítico",
            statsEn = "+30 Attack Damage • +36% Armor Penetration • +25% Critical Rate",
            statsPt = "+30 Dano de Ataque • +36% Penetração de Armadura • +25% Chance de Crítico",
            passive = "Dominik’s Regards\nAlready equipped with percentage Penetración de Armadura and Adicional damage, but lacks durability\nGiant Slayer: Deal Adicional damage based on the target’s Adicional Vida. At 1500 Adicional Vida, the Adicional damage is increased up to 15%.\nDominik’\nIs built to punish bulky foes. It converts a chunk of your offensive power into Penetración de Armadura and adds Adicional damage that scales with the enemy’s extra Vida, making it a go-to pick when the enemy team stacks HP and Armadura. Because it focuses on penetration and damage rather than survivability, use it when you need to cut through tanky targets quickly while relying on positioning or teammates for protection.",
            passiveEn = "Dominik’s Regards\nAlready equipped with percentage Penetración de Armadura and Adicional damage, but lacks durability\nGiant Slayer: Deal Adicional damage based on the target’s Adicional Vida. At 1500 Adicional Vida, the Adicional damage is increased up to 15%.\nDominik’\nIs built to punish bulky foes. It converts a chunk of your offensive power into Penetración de Armadura and adds Adicional damage that scales with the enemy’s extra Vida, making it a go-to pick when the enemy team stacks HP and Armadura. Because it focuses on penetration and damage rather than survivability, use it when you need to cut through tanky targets quickly while relying on positioning or teammates for protection.",
            passivePt = "Dominik’s Regards\nAlready equipped with percentage Penetração de Armadura and Adicional damage, but lacks durability\nGiant Slayer: Deal Adicional damage based on the target’s Adicional Vida. At 1500 Adicional Vida, the Adicional damage is increased up to 15%.\nDominik’\nIs built to punish bulky foes. It converts a chunk of your offensive power into Penetração de Armadura and adds Adicional damage that scales with the enemy’s extra Vida, making it a go-to pick when the enemy team stacks HP and Armadura. Because it focuses on penetration and damage rather than survivability, use it when you need to cut through tanky targets quickly while relying on positioning or teammates for protection.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774953440_lord-dominiks-regards.webp"
        ))
        add(WildRiftItem(
            id = "stridebreaker_active",
            name = "Rompeavances",
            nameEn = "Stridebreaker",
            namePt = "Quebrapassos",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+400 Vida Máxima • +40 Daño de Ataque • +25% Velocidad de Ataque",
            statsEn = "+400 Max Health • +40 Attack Damage • +25% Attack Speed",
            statsPt = "+400 Vida Máxima • +40 Dano de Ataque • +25% Velocidade de Ataque",
            passive = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Daño Físico to nearby enemies and slowing them by 40% durante 3s (25s Enfriamiento)\nStride (Passive): Gain 20 Velocidad de Movimiento for 2 segundo(s) when you deal Daño Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidad de Movimiento whenever you deal Daño Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Cuerpo a cuerpo range.",
            passiveEn = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Daño Físico to nearby enemies and slowing them by 40% durante 3s (25s Enfriamiento)\nStride (Passive): Gain 20 Velocidad de Movimiento for 2 segundo(s) when you deal Daño Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidad de Movimiento whenever you deal Daño Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Cuerpo a cuerpo range.",
            passivePt = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Dano Físico to nearby enemies and slowing them by 40% durante 3s (25s Tempo de Recarga)\nStride (Passive): Gain 20 Velocidade de Movimento for 2 segundo(s) when you deal Dano Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidade de Movimento whenever you deal Dano Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Corpo a corpo range.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_stridebreaker.webp"
        ))
        add(WildRiftItem(
            id = "goredrinker_active",
            name = "Bebedor de Sangre",
            nameEn = "Bebedor de Sangre",
            namePt = "Bebedor de Sangre",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+350 Vida Máxima • +40 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +40 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Daño Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeón enemigo hit. (12s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Daño Físico and restores Vida based on the number of campeones enemigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            passiveEn = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Daño Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeón enemigo hit. (12s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Daño Físico and restores Vida based on the number of campeones enemigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            passivePt = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Dano Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeão inimigo hit. (12s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Dano Físico and restores Vida based on the number of campeões inimigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389878_goredrinker.webp"
        ))
        add(WildRiftItem(
            id = "galeforce_active",
            name = "Fuerza del Viento",
            nameEn = "Fuerza del Viento",
            namePt = "Fuerza del Viento",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+50 Daño de Ataque • +25% Probabilidad de Crítico • +15% Velocidad de Ataque • +5% Velocidad de Movimiento",
            statsEn = "+50 Attack Damage • +25% Critical Rate • +15% Attack Speed • +5% Move Speed",
            statsPt = "+50 Dano de Ataque • +25% Chance de Crítico • +15% Velocidade de Ataque • +5% Velocidade de Movimento",
            passive = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeones. Deal Daño Físico equal to 40-125 () plus 35% Adicional. (60s Enfriamiento)\nThis item greatly improves the mobility of marksmen and AD Campeones by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeones who value mobility, safety, and strong burst potential.",
            passiveEn = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeones. Deal Daño Físico equal to 40-125 () plus 35% Adicional. (60s Enfriamiento)\nThis item greatly improves the mobility of marksmen and AD Campeones by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeones who value mobility, safety, and strong burst potential.",
            passivePt = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeões. Deal Dano Físico equal to 40-125 () plus 35% Adicional. (60s Tempo de Recarga)\nThis item greatly improves the mobility of marksmen and AD Campeões by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeões who value mobility, safety, and strong burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_galeforce.webp"
        ))
        add(WildRiftItem(
            id = "mercurial_scimitar_active",
            name = "Cimitarra Mercurial",
            nameEn = "Cimitarra Mercurial",
            namePt = "Cimitarra Mercurial",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+45 Daño de Ataque • +10% Vampirismo Físico • +40 Resistencia Mágica",
            statsEn = "+45 Attack Damage • +10% Physical Vamp • +40 Magic Resistance",
            statsPt = "+45 Dano de Ataque • +10% Vampirismo Físico • +40 Resistência Mágica",
            passive = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            passiveEn = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            passivePt = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Tempo de Recarga)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp"
        ))
        add(WildRiftItem(
            id = "luden_s_echo_magic",
            name = "Eco de Luden",
            nameEn = "Luden's Echo",
            namePt = "Eco de Luden",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+85 Poder de Habilidad, +300 Maná Máximo, +20 Aceleración de Habilidad",
            statsEn = "+85 Ability Power, +300 Max Mana, +20 Ability Haste",
            statsPt = "+85 Poder de Habilidade, +300 Mana Máxima, +20 Aceleração de Habilidade",
            passive = "Tiro Discordante: Al moverte y lanzar habilidades acumulas cargas. Al llegar a 100, el próximo hechizo inflige 100 + 10% PH adicional a hasta 3 objetivos.",
            passiveEn = "Tiro Discordante: Al moverte y lanzar habilidades acumulas cargas. Al llegar a 100, el próximo hechizo inflige 100 + 10% PH adicional a hasta 3 objetivos.",
            passivePt = "Tiro Discordante: Al moverte y lanzar habilidades acumulas cargas. Al llegar a 100, el próximo hechizo inflige 100 + 10% PH adicional a hasta 3 objetivos.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388218_ludens-echo.webp"
        ))
        add(WildRiftItem(
            id = "morellonomicon_support",
            name = "Morellonomicón",
            nameEn = "Morellonomicon",
            namePt = "Morellonomicon",
            category = "Daño Mágico",
            goldCost = 2650,
            stats = "+300 Vida Máxima • +75 Poder de Habilidad • +15 Aceleración de Habilidad",
            statsEn = "+300 Max Health • +75 Ability Power • +15 Ability Haste",
            statsPt = "+300 Vida Máxima • +75 Poder de Habilidade • +15 Aceleração de Habilidade",
            passive = "Daño Mágico reduces enemy healing\nAffliction: Dealing Daño Mágico to campeones enemigos inflicts 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeones with strong healing and sustain. Any Daño Mágico you deal applies Heridas Graves, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            passiveEn = "Daño Mágico reduces enemy healing\nAffliction: Dealing Daño Mágico to campeones enemigos inflicts 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeones with strong healing and sustain. Any Daño Mágico you deal applies Heridas Graves, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            passivePt = "Dano Mágico reduces enemy healing\nAffliction: Dealing Dano Mágico to campeões inimigos inflicts 50% Feridas Dolorosas for 3 segundos.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeões with strong healing and sustain. Any Dano Mágico you deal applies Feridas Dolorosas, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388237_morellonomicon.webp"
        ))
        add(WildRiftItem(
            id = "rabadon_s_deathcap_magic",
            name = "Sombrero Mortal de Rabadon",
            nameEn = "Rabadon's Deathcap",
            namePt = "Capuz da Morte de Rabadon",
            category = "Daño Mágico",
            goldCost = 3400,
            stats = "+120 Poder de Habilidad",
            statsEn = "+120 Ability Power",
            statsPt = "+120 Poder de Habilidade",
            passive = "Opus: Aumenta el Poder de Habilidad total en un 40% a 45% (según el nivel).",
            passiveEn = "Opus: Aumenta el Poder de Habilidad total en un 40% a 45% (según el nivel).",
            passivePt = "Opus: Aumenta el Poder de Habilidade total en un 40% a 45% (según el nivel).",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388295_rabadons-deathcap.webp"
        ))
        add(WildRiftItem(
            id = "rylai_s_crystal_scepter_magic",
            name = "Cetro de Cristal de Rylai",
            nameEn = "Cetro de Cristal de Rylai",
            namePt = "Cetro de Cristal de Rylai",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+350 Vida Máxima • +65 Poder de Habilidad",
            statsEn = "+350 Max Health • +65 Ability Power",
            statsPt = "+350 Vida Máxima • +65 Poder de Habilidade",
            passive = "Abilities apply Ralentiza\nIcy: Damaging abilities and empowered attacks Ralentización enemies by 30% for 0.75 segundo.\nThis item enhances your crowd control by causing your abilities and empowered attacks to Ralentización enemies with every hit. The Adicional Vida improves your durability, while the consistent Ralentización makes it much easier to land follow-up abilities, chase fleeing targets, and support your teammates. It is an excellent choice for damage-over-time mages and Campeones who rely on keeping enemies within the range of their abilities.",
            passiveEn = "Abilities apply Ralentiza\nIcy: Damaging abilities and empowered attacks Ralentización enemies by 30% for 0.75 segundo.\nThis item enhances your crowd control by causing your abilities and empowered attacks to Ralentización enemies with every hit. The Adicional Vida improves your durability, while the consistent Ralentización makes it much easier to land follow-up abilities, chase fleeing targets, and support your teammates. It is an excellent choice for damage-over-time mages and Campeones who rely on keeping enemies within the range of their abilities.",
            passivePt = "Abilities apply Ralentiza\nIcy: Damaging abilities and empowered attacks Ralentización enemies by 30% for 0.75 segundo.\nThis item enhances your crowd control by causing your abilities and empowered attacks to Ralentización enemies with every hit. The Adicional Vida improves your durability, while the consistent Ralentización makes it much easier to land follow-up abilities, chase fleeing targets, and support your teammates. It is an excellent choice for damage-over-time mages and Campeões who rely on keeping enemies within the range of their abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388390_rylais-crystal-scepter.webp"
        ))
        add(WildRiftItem(
            id = "liandry_s_torment_magic",
            name = "Tormento de Liandry",
            nameEn = "Liandry's Torment",
            namePt = "Tormento de Liandry",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+300 Vida Máxima • +70 Poder de Habilidad",
            statsEn = "+300 Max Health • +70 Ability Power",
            statsPt = "+300 Vida Máxima • +70 Poder de Habilidade",
            passive = "Torment: Damaging abilities and empowered attacks burn enemies for 2% Vida Máxima Daño Mágico for 3 segundos.\nMadness: Deals 2% more damage for each segundo En combate against Campeones, capped at 6% after 3 segundos.\nThis item excels in extended fights. Your abilities and empowered attacks ignite enemies, Infligir daño over time based on their maximum Vida, while your overall damage steadily increases the longer you remain En combate. It is an excellent choice for damage-over-time mages and AP bruisers who want to wear down even the toughest frontline Campeones.",
            passiveEn = "Torment: Damaging abilities and empowered attacks burn enemies for 2% Vida Máxima Daño Mágico for 3 segundos.\nMadness: Deals 2% more damage for each segundo En combate against Campeones, capped at 6% after 3 segundos.\nThis item excels in extended fights. Your abilities and empowered attacks ignite enemies, Infligir daño over time based on their maximum Vida, while your overall damage steadily increases the longer you remain En combate. It is an excellent choice for damage-over-time mages and AP bruisers who want to wear down even the toughest frontline Campeones.",
            passivePt = "Torment: Damaging abilities and empowered attacks burn enemies for 2% Vida Máxima Dano Mágico for 3 segundos.\nMadness: Deals 2% more damage for each segundo En combate against Campeões, capped at 6% after 3 segundos.\nThis item excels in extended fights. Your abilities and empowered attacks ignite enemies, Infligir daño over time based on their maximum Vida, while your overall damage steadily increases the longer you remain En combate. It is an excellent choice for damage-over-time mages and AP bruisers who want to wear down even the toughest frontline Campeões.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388368_yorlde-liandrys-torment.webp"
        ))
        add(WildRiftItem(
            id = "rod_of_ages_magic",
            name = "Vara de las Edades",
            nameEn = "Rod of Ages",
            namePt = "Bastão das Eras",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+350 Vida Máxima • +50 Poder de Habilidad • +400 Maná Máximo",
            statsEn = "+350 Max Health • +50 Ability Power • +400 Max Mana",
            statsPt = "+350 Vida Máxima • +50 Poder de Habilidade • +400 Mana Máxima",
            passive = "+400 Maná Máximo\nEternity: Restore Maná equal to 15% of the damage taken from Campeones. Regen Vida equal to 20% Maná spent. Capped at 25 Vida per cast.\nVeteran: Each stack provides 15 Vida, 30 Maná and 4 Poder de Habilidad, stacking at a rate of 1 every 35 segundos. Max of 10 stacks, providing 150 Vida, 300 Maná, and 40 Poder de Habilidad.\nThis item grows stronger over the course of the game, gradually increasing its stats and becoming one of the best scaling options available. It provides an excellent balance of durability, Maná, and Poder de Habilidad while restoring both Vida and Maná Durante el combate, allowing you to stay in fights much longer. A perfect choice for mages and AP bruisers who thrive in the late game and excel in extended teamfights.",
            passiveEn = "+400 Maná Máximo\nEternity: Restore Maná equal to 15% of the damage taken from Campeones. Regen Vida equal to 20% Maná spent. Capped at 25 Vida per cast.\nVeteran: Each stack provides 15 Vida, 30 Maná and 4 Poder de Habilidad, stacking at a rate of 1 every 35 segundos. Max of 10 stacks, providing 150 Vida, 300 Maná, and 40 Poder de Habilidad.\nThis item grows stronger over the course of the game, gradually increasing its stats and becoming one of the best scaling options available. It provides an excellent balance of durability, Maná, and Poder de Habilidad while restoring both Vida and Maná Durante el combate, allowing you to stay in fights much longer. A perfect choice for mages and AP bruisers who thrive in the late game and excel in extended teamfights.",
            passivePt = "+400 Mana Máxima\nEternity: Restore Maná equal to 15% of the damage taken from Campeões. Regen Vida equal to 20% Maná spent. Capped at 25 Vida per cast.\nVeteran: Each stack provides 15 Vida, 30 Maná and 4 Poder de Habilidade, stacking at a rate of 1 every 35 segundos. Max of 10 stacks, providing 150 Vida, 300 Maná, and 40 Poder de Habilidade.\nThis item grows stronger over the course of the game, gradually increasing its stats and becoming one of the best scaling options available. It provides an excellent balance of durability, Maná, and Poder de Habilidade while restoring both Vida and Maná Durante el combate, allowing you to stay in fights much longer. A perfect choice for mages and AP bruisers who thrive in the late game and excel in extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388412_rod-of-ages.webp"
        ))
        add(WildRiftItem(
            id = "lich_bane_magic",
            name = "Maldición del Liche",
            nameEn = "Lich Bane",
            namePt = "Perdição de Lich",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+100 Poder de Habilidad • +10 Aceleración de Habilidad • +5% Velocidad de Movimiento",
            statsEn = "+100 Ability Power • +10 Ability Haste • +5% Move Speed",
            statsPt = "+100 Poder de Habilidade • +10 Aceleração de Habilidade • +5% Velocidade de Movimento",
            passive = "Los ataques infligen Adicional damage aster ability casts\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Mágico equal to 75% base AD  + 45% AP . (1.5s Enfriamiento) El daño se reduce contra estructuras.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who weave abilities between their ataques básicos. After casting a spell, your next attack is empowered with a powerful burst of Adicional Daño Mágico, greatly increasing your combo potential. The Adicional Velocidad de Movimiento also improves your mobility, making it easier to reposition and chase targets. An excellent choice for mobile mages, AP assassins, and hybrid Campeones who rely on short, high-damage ability rotations.",
            passiveEn = "Los ataques infligen Adicional damage aster ability casts\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Mágico equal to 75% base AD  + 45% AP . (1.5s Enfriamiento) El daño se reduce contra estructuras.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who weave abilities between their ataques básicos. After casting a spell, your next attack is empowered with a powerful burst of Adicional Daño Mágico, greatly increasing your combo potential. The Adicional Velocidad de Movimiento also improves your mobility, making it easier to reposition and chase targets. An excellent choice for mobile mages, AP assassins, and hybrid Campeones who rely on short, high-damage ability rotations.",
            passivePt = "Los ataques infligen Adicional damage aster ability casts\nSpellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Dano Mágico equal to 75% base AD  + 45% AP . (1.5s Tempo de Recarga) El daño se reduce contra estructuras.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who weave abilities between their ataques básicos. After casting a spell, your next attack is empowered with a powerful burst of Adicional Dano Mágico, greatly increasing your combo potential. The Adicional Velocidade de Movimento also improves your mobility, making it easier to reposition and chase targets. An excellent choice for mobile mages, AP assassins, and hybrid Campeões who rely on short, high-damage ability rotations.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388405_lich-bane.webp"
        ))
        add(WildRiftItem(
            id = "nashor_s_tooth_magic",
            name = "Diente de Nashor",
            nameEn = "Nashor's Tooth",
            namePt = "Dente de Nashor",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+45% Velocidad de Ataque • +20 Aceleración de Habilidad",
            statsEn = "+45% Attack Speed • +20 Ability Haste",
            statsPt = "+45% Velocidade de Ataque • +20 Aceleração de Habilidade",
            passive = "Magic Fang: Obtain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who blend autoataques with Daño Mágico. It provides a hefty boost to Velocidad de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Daño de Ataque or Poder de Habilidad, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Daño Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeones like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Daño Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            passiveEn = "Magic Fang: Obtain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who blend autoataques with Daño Mágico. It provides a hefty boost to Velocidad de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Daño de Ataque or Poder de Habilidad, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Daño Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeones like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Daño Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            passivePt = "Magic Fang: Obtain 25 Dano de Ataque or 50 Poder de Habilidade (Adaptive).\nGnaw: Los ataques infligen Daño Adaptable (15 + 20% Adicional+ 30% Adicional) on hit.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who blend autoataques with Dano Mágico. It provides a hefty boost to Velocidade de Ataque and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either Adicional Dano de Ataque or Poder de Habilidade, adding flexibility.  With each autoataque, “Gnaw” deals adaptive Dano Mágico on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for Campeões like Teemo, Kayle, and Jax, who rely on sustained autoataques supported by Dano Mágico and need frequent ability usage to maximize DPS in extended engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302010_nashors-tooth.webp"
        ))
        add(WildRiftItem(
            id = "archangel_s_staff_magic",
            name = "Báculo del Arcángel",
            nameEn = "Archangel's Staff",
            namePt = "Cajado do Arcanjo",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+60 Poder de Habilidad • +500 Maná Máximo • +25 Aceleración de Habilidad",
            statsEn = "+60 Ability Power • +500 Max Mana • +25 Ability Haste",
            statsPt = "+60 Poder de Habilidade • +500 Mana Máxima • +25 Aceleração de Habilidade",
            passive = "Converts Maná to Poder de Habilidad\n+500 Maná Máximo\nAwe: Grants Poder de Habilidad equal to 1% Maná Máximo and refunds 25%  of all Maná spent.\nManá Charge: Increases Maná Máximo by 14 every time Maná is spent. Hasta un máximo de 700 Adicional Maná, transforming Archangel's Staff into Seraph's Embrace. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Consejos del Coach: Este objeto es ideal para mages who rely on a large Maná pool and need a significant boost to their Poder de Habilidad. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad, helping you deal damage and use your abilities frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidad based on your maximum Maná and refunds a portion of Maná spent, helping you sustain in fights. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná every time you spend Maná, eventually transforming the item into Seraph's Embrace, giving you additional bonuses. This item is ideal for Campeones who want to scale well into the late game with a large amount of AP and Maná.",
            passiveEn = "Converts Maná to Poder de Habilidad\n+500 Maná Máximo\nAwe: Grants Poder de Habilidad equal to 1% Maná Máximo and refunds 25%  of all Maná spent.\nManá Charge: Increases Maná Máximo by 14 every time Maná is spent. Hasta un máximo de 700 Adicional Maná, transforming Archangel's Staff into Seraph's Embrace. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Coach Tips: Este objeto es ideal para mages who rely on a large Maná pool and need a significant boost to their Poder de Habilidad. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad, helping you deal damage and use your abilities frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidad based on your maximum Maná and refunds a portion of Maná spent, helping you sustain in fights. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná every time you spend Maná, eventually transforming the item into Seraph's Embrace, giving you additional bonuses. This item is ideal for Campeones who want to scale well into the late game with a large amount of AP and Maná.",
            passivePt = "Converts Maná to Poder de Habilidade\n+500 Mana Máxima\nAwe: Grants Poder de Habilidade equal to 1% Mana Máxima and refunds 25%  of all Maná spent.\nManá Charge: Increases Mana Máxima by 14 every time Maná is spent. Hasta un máximo de 700 Adicional Maná, transforming Archangel's Staff into Seraph's Embrace. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Dicas do Coach: Este item é ideal para mages who rely on a large Maná pool and need a significant boost to their Poder de Habilidade. It provides bonuses to Poder de Habilidade, Penetração Mágica, maximum Maná, and Aceleração de Habilidade, helping you deal damage and use your abilities frequently. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidade based on your maximum Maná and refunds a portion of Maná spent, helping you sustain in fights. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná every time you spend Maná, eventually transforming the item into Seraph's Embrace, giving you additional bonuses. This item is ideal for Campeões who want to scale well into the late game with a large amount of AP and Maná.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388461_archangels-staff.webp"
        ))
        add(WildRiftItem(
            id = "seraph_s_embrace_magic",
            name = "Abrazo del Serafín",
            nameEn = "Seraph's Embrace",
            namePt = "Abraço de Seraph",
            category = "Daño Mágico",
            goldCost = 2950,
            stats = "+60 Poder de Habilidad • +1200 Maná Máximo • +25 Aceleración de Habilidad",
            statsEn = "+60 Ability Power • +1200 Max Mana • +25 Ability Haste",
            statsPt = "+60 Poder de Habilidade • +1200 Mana Máxima • +25 Aceleração de Habilidade",
            passive = "Converts Maná to Poder de Habilidad\n+1200 Maná Máximo\nAwe: Grants Poder de Habilidad equal to 2% Maná Máximo and refunds 25%  of all Maná spent.\nLifeline: Damage that puts you under 35%  Vida consumes 20% of your current Maná to grant a Escudo, equal to that amount +100 for 2 segundos. (70s Enfriamiento).\n\n💡 Consejos del Coach: Este objeto es ideal para mages who need a large Maná pool and survivability in team fights. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad, helping you deal damage and use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidad based on your maximum Maná and refunds a portion of Maná spent, allowing you to stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that helps you survive the initial burst damage and prolong your presence in the fight. This item is ideal for Campeones who want to scale well into the late game with a large amount of AP and Maná, while also gaining extra survivability.",
            passiveEn = "Converts Maná to Poder de Habilidad\n+1200 Maná Máximo\nAwe: Grants Poder de Habilidad equal to 2% Maná Máximo and refunds 25%  of all Maná spent.\nLifeline: Damage that puts you under 35%  Vida consumes 20% of your current Maná to grant a Escudo, equal to that amount +100 for 2 segundos. (70s Enfriamiento).\n\n💡 Coach Tips: Este objeto es ideal para mages who need a large Maná pool and survivability in team fights. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad, helping you deal damage and use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidad based on your maximum Maná and refunds a portion of Maná spent, allowing you to stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that helps you survive the initial burst damage and prolong your presence in the fight. This item is ideal for Campeones who want to scale well into the late game with a large amount of AP and Maná, while also gaining extra survivability.",
            passivePt = "Converts Maná to Poder de Habilidade\n+1200 Mana Máxima\nAwe: Grants Poder de Habilidade equal to 2% Mana Máxima and refunds 25%  of all Maná spent.\nLifeline: Damage that puts you under 35%  Vida consumes 20% of your current Maná to grant a Escudo, equal to that amount +100 for 2 segundos. (70s Tempo de Recarga).\n\n💡 Dicas do Coach: Este item é ideal para mages who need a large Maná pool and survivability in team fights. It provides bonuses to Poder de Habilidade, Penetração Mágica, maximum Maná, and Aceleração de Habilidade, helping you deal damage and use your abilities effectively. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Awe\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your Poder de Habilidade based on your maximum Maná and refunds a portion of Maná spent, allowing you to stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that helps you survive the initial burst damage and prolong your presence in the fight. This item is ideal for Campeões who want to scale well into the late game with a large amount of AP and Maná, while also gaining extra survivability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388429_seraphs-embrace.webp"
        ))
        add(WildRiftItem(
            id = "infinity_orb_magic",
            name = "Orbe del Infinito",
            nameEn = "Infinity Orb",
            namePt = "Orbe do Infinito",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+85 Poder de Habilidad, +5% Velocidad de Movimiento, +15 Penetración Mágica",
            statsEn = "+85 Ability Power, +5% Move Speed, +15 Magic Penetration",
            statsPt = "+85 Poder de Habilidade, +5% Velocidade de Movimento, +15 Penetração Mágica",
            passive = "Juicio Inevitable: Las habilidades y ataques mejorados asestan golpes críticos que infligen un 20% más de daño contra enemigos por debajo del 35% de vida.",
            passiveEn = "Juicio Inevitable: Las habilidades y ataques mejorados asestan golpes críticos que infligen un 20% más de daño contra enemigos por debajo del 35% de vida.",
            passivePt = "Juicio Inevitable: Las habilidades y ataques mejorados asestan golpes críticos que infligen un 20% más de daño contra enemigos por debajo del 35% de vida.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388486_yordle-infinity-orb.webp"
        ))
        add(WildRiftItem(
            id = "oceanid_s_trident_support",
            name = "Tridente del Oceánida",
            nameEn = "Oceanid's Trident",
            namePt = "Tridente do Oceanida",
            category = "Daño Mágico",
            goldCost = 2600,
            stats = "+200 Vida Máxima • +80 Poder de Habilidad • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +80 Ability Power • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +80 Poder de Habilidade • +10 Aceleração de Habilidade",
            passive = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeón enemigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeones. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            passiveEn = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeón enemigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeones. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            passivePt = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeão inimigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeões. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388583_oceanids-trident.webp"
        ))
        add(WildRiftItem(
            id = "cosmic_drive_magic",
            name = "Impulso Cósmico",
            nameEn = "Cosmic Drive",
            namePt = "Ímpeto Cósmico",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+300 Vida Máxima • +70 Poder de Habilidad • +25 Aceleración de Habilidad • +5% Velocidad de Movimiento",
            statsEn = "+300 Max Health • +70 Ability Power • +25 Ability Haste • +5% Move Speed",
            statsPt = "+300 Vida Máxima • +70 Poder de Habilidade • +25 Aceleração de Habilidade • +5% Velocidade de Movimento",
            passive = "Dealing ability damage grants Velocidad de Movimiento\nSpelldance: Dealing magic or Daño Verdadero to Campeones grants 30 Velocidad de Movimiento for 4 segundo(s).\nThis item combines Poder de Habilidad, durability, and exceptional mobility. Infligir daño with your abilities grants a burst of Velocidad de Movimiento, making it easier to kite enemies, chase fleeing targets, or reposition safely during fights. It is an excellent choice for mobile mages, AP bruisers, and Campeones who thrive in extended skirmishes while constantly staying on the move.",
            passiveEn = "Dealing ability damage grants Velocidad de Movimiento\nSpelldance: Dealing magic or Daño Verdadero to Campeones grants 30 Velocidad de Movimiento for 4 segundo(s).\nThis item combines Poder de Habilidad, durability, and exceptional mobility. Infligir daño with your abilities grants a burst of Velocidad de Movimiento, making it easier to kite enemies, chase fleeing targets, or reposition safely during fights. It is an excellent choice for mobile mages, AP bruisers, and Campeones who thrive in extended skirmishes while constantly staying on the move.",
            passivePt = "Dealing ability damage grants Velocidade de Movimento\nSpelldance: Dealing magic or Dano Verdadeiro to Campeões grants 30 Velocidade de Movimento for 4 segundo(s).\nThis item combines Poder de Habilidade, durability, and exceptional mobility. Infligir daño with your abilities grants a burst of Velocidade de Movimento, making it easier to kite enemies, chase fleeing targets, or reposition safely during fights. It is an excellent choice for mobile mages, AP bruisers, and Campeões who thrive in extended skirmishes while constantly staying on the move.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388596_cosmic-drive.webp"
        ))
        add(WildRiftItem(
            id = "riftmaker_magic",
            name = "Creador de Grietas",
            nameEn = "Riftmaker",
            namePt = "Criafendas",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+350 Vida Máxima • +70 Poder de Habilidad • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +70 Ability Power • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +70 Poder de Habilidade • +15 Aceleração de Habilidade",
            passive = "Void Corruption: Every 1 segundo(s) En combate with campeones enemigos, deal 2% Adicional damage, up to 8%.\nAt maximum strength, gainOmni Vamp. (10% for Cuerpo a cuerpo Campeones / 6% for A distancia Campeones).\nVoid Infusion: Gain 2% of your Adicional Vida as Poder de Habilidad.\nThis item is built for extended fights, gradually increasing your damage the longer you remain En combate. Once fully ramped up, it grants Omnivampirismo for improved sustain, while your Adicional Vida is partially converted into Poder de Habilidad, further increasing your overall damage. An excellent choice for AP bruisers and battlemages who excel in prolonged teamfights and thrive by scaling throughout combat.",
            passiveEn = "Void Corruption: Every 1 segundo(s) En combate with campeones enemigos, deal 2% Adicional damage, up to 8%.\nAt maximum strength, gainOmni Vamp. (10% for Cuerpo a cuerpo Campeones / 6% for A distancia Campeones).\nVoid Infusion: Gain 2% of your Adicional Vida as Poder de Habilidad.\nThis item is built for extended fights, gradually increasing your damage the longer you remain En combate. Once fully ramped up, it grants Omnivampirismo for improved sustain, while your Adicional Vida is partially converted into Poder de Habilidad, further increasing your overall damage. An excellent choice for AP bruisers and battlemages who excel in prolonged teamfights and thrive by scaling throughout combat.",
            passivePt = "Void Corruption: Every 1 segundo(s) En combate with campeões inimigos, deal 2% Adicional damage, up to 8%.\nAt maximum strength, gainOmni Vamp. (10% for Corpo a corpo Campeões / 6% for À distância Campeões).\nVoid Infusion: Gain 2% of your Adicional Vida as Poder de Habilidade.\nThis item is built for extended fights, gradually increasing your damage the longer you remain En combate. Once fully ramped up, it grants Omnivampirismo for improved sustain, while your Adicional Vida is partially converted into Poder de Habilidade, further increasing your overall damage. An excellent choice for AP bruisers and battlemages who excel in prolonged teamfights and thrive by scaling throughout combat.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388604_riftmaker.webp"
        ))
        add(WildRiftItem(
            id = "horizon_focus_magic",
            name = "Enfoque al Horizonte",
            nameEn = "Horizon Focus",
            namePt = "Foco do Horizonte",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+80 Poder de Habilidad • +25 Aceleración de Habilidad",
            statsEn = "+80 Ability Power • +25 Ability Haste",
            statsPt = "+80 Poder de Habilidade • +25 Aceleração de Habilidade",
            passive = "Hypershot: Damaging an campeón enemigo with an ability from 600 units away reveals them for 8 segundos and increases damage dealt to them by 10%.\nFocus: When Hypershot is triggered, it reveals all campeones enemigos within 1.200 units of the target durante 3s. (12s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para long-range mages and poke-oriented Campeones. Hitting an enemy with a spell from a distance marks and reveals them while increasing all subsequent damage they take. It also exposes nearby campeones enemigos, providing valuable vision for your team and making follow-up attacks much easier. An excellent choice for artillery mages and Campeones who excel at controlling fights from a safe distance.",
            passiveEn = "Hypershot: Damaging an campeón enemigo with an ability from 600 units away reveals them for 8 segundos and increases damage dealt to them by 10%.\nFocus: When Hypershot is triggered, it reveals all campeones enemigos within 1.200 units of the target durante 3s. (12s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para long-range mages and poke-oriented Campeones. Hitting an enemy with a spell from a distance marks and reveals them while increasing all subsequent damage they take. It also exposes nearby campeones enemigos, providing valuable vision for your team and making follow-up attacks much easier. An excellent choice for artillery mages and Campeones who excel at controlling fights from a safe distance.",
            passivePt = "Hypershot: Damaging an campeão inimigo with an ability from 600 units away reveals them for 8 segundos and increases damage dealt to them by 10%.\nFocus: When Hypershot is triggered, it reveals all campeões inimigos within 1.200 units of the target durante 3s. (12s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para long-range mages and poke-oriented Campeões. Hitting an enemy with a spell from a distance marks and reveals them while increasing all subsequent damage they take. It also exposes nearby campeões inimigos, providing valuable vision for your team and making follow-up attacks much easier. An excellent choice for artillery mages and Campeões who excel at controlling fights from a safe distance.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388673_horizon-focus.webp"
        ))
        add(WildRiftItem(
            id = "runaan_s_hurricane_magic",
            name = "Huracán de Runaan",
            nameEn = "Runaan's Hurricane",
            namePt = "Furacão de Runaan",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+35% Velocidad de Ataque • +25% Probabilidad de Crítico",
            statsEn = "+35% Attack Speed • +25% Critical Rate",
            statsPt = "+35% Velocidade de Ataque • +25% Chance de Crítico",
            passive = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por campeones cuerpo a cuerpo y a distancia.\n\n💡 Consejos del Coach: Este objeto convierte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Cuerpo a cuerpo users can use it too, but it shines brightest on A distancia tiradores y campeones de autoataque.",
            passiveEn = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por campeones cuerpo a cuerpo y a distancia.\n\n💡 Coach Tips: Este objeto convierte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Cuerpo a cuerpo users can use it too, but it shines brightest on A distancia tiradores y campeones de autoataque.",
            passivePt = "Wind's Fury: Los ataques golpean a 2 enemigos cercanos adicionales, cada uno infligiendo un 55%. Estos proyectiles pueden asestar Golpes Críticos y activar efectos de impacto.\nWind Blade: Los ataques infligen 15 de Dano Físico adicional al impacto contra los objetivos.\nEste objeto puede ser utilizado por Campeões Corpo a corpo y À distância.\n\n💡 Dicas do Coach: Este item converte your ataques básicos into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. Mejora enormemente la limpieza de oleadas y aporta daño masivo en peleas de equipo. Perfecto para tiradores y composiciones de efectos de impacto que buscan cadencia de ataque sostenida y presencia en área. Corpo a corpo users can use it too, but it shines brightest on À distância tiradores y Campeões de autoataque.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300839_yordle-runaans-hurricane.webp"
        ))
        add(WildRiftItem(
            id = "malignance_magic",
            name = "Malevolencia",
            nameEn = "Malignance",
            namePt = "Malevolência",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+90 Poder de Habilidad • +500 Maná Máximo • +15 Aceleración de Habilidad",
            statsEn = "+90 Ability Power • +500 Max Mana • +15 Ability Haste",
            statsPt = "+90 Poder de Habilidade • +500 Mana Máxima • +15 Aceleração de Habilidade",
            passive = "+500 Maná Máximo\nScorn: Your Ultimate abilities gain 20 Aceleración de Habilidad.\nHatefog: Damaging a Campeón with your Ultimate burns the ground beneath them for 3 segundo(s), dealing Daño Mágico equal to 60 plus 5% AP per segundo and reducing their Resistencia Mágica by 10. Burn radius increases with damage, reaching maximum radius at 800 damage.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who focus on their ultimate abilities and want to maximize their effectiveness in fights. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Scorn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the Enfriamiento of your ultimate ability, enhancing its efficiency and uptime. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Hatefog\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect deals Daño Mágico to enemies in the area after using your ultimate, creating a scorched earth effect. Enemies within this area take damage and have their Resistencia Mágica reduced, making this item ideal for Campeones who want to weaken their opponents and increase their damage. It’s especially useful against enemies with high Resistencia Mágica.",
            passiveEn = "+500 Maná Máximo\nScorn: Your Ultimate abilities gain 20 Aceleración de Habilidad.\nHatefog: Damaging a Campeón with your Ultimate burns the ground beneath them for 3 segundo(s), dealing Daño Mágico equal to 60 plus 5% AP per segundo and reducing their Resistencia Mágica by 10. Burn radius increases with damage, reaching maximum radius at 800 damage.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who focus on their ultimate abilities and want to maximize their effectiveness in fights. It provides bonuses to Poder de Habilidad, Penetración Mágica, maximum Maná, and Aceleración de Habilidad. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Scorn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the Enfriamiento of your ultimate ability, enhancing its efficiency and uptime. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Hatefog\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect deals Daño Mágico to enemies in the area after using your ultimate, creating a scorched earth effect. Enemies within this area take damage and have their Resistencia Mágica reduced, making this item ideal for Campeones who want to weaken their opponents and increase their damage. It’s especially useful against enemies with high Resistencia Mágica.",
            passivePt = "+500 Mana Máxima\nScorn: Your Ultimate abilities gain 20 Aceleração de Habilidade.\nHatefog: Damaging a Campeão with your Ultimate burns the ground beneath them for 3 segundo(s), dealing Dano Mágico equal to 60 plus 5% AP per segundo and reducing their Resistência Mágica by 10. Burn radius increases with damage, reaching maximum radius at 800 damage.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who focus on their ultimate abilities and want to maximize their effectiveness in fights. It provides bonuses to Poder de Habilidade, Penetração Mágica, maximum Maná, and Aceleração de Habilidade. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Scorn\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect reduces the Tempo de Recarga of your ultimate ability, enhancing its efficiency and uptime. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Hatefog\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect deals Dano Mágico to enemies in the area after using your ultimate, creating a scorched earth effect. Enemies within this area take damage and have their Resistência Mágica reduced, making this item ideal for Campeões who want to weaken their opponents and increase their damage. It’s especially useful against enemies with high Resistência Mágica.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388765_malignance.webp"
        ))
        add(WildRiftItem(
            id = "guinsoo_s_rageblade_magic",
            name = "Espadafuria de Guinsoo",
            nameEn = "Espadafuria de Guinsoo",
            namePt = "Espadafuria de Guinsoo",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+30% Velocidad de Ataque",
            statsEn = "+30% Attack Speed",
            statsPt = "+30% Velocidade de Ataque",
            passive = "Surge: Gain 5% Velocidad de Movimiento.\nChaos: Gain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nWrath: Los ataques infligen 30 Daño Mágico but no longer Golpe Crítico. Fore every 1% Golpe Crítico Rate gained from items, your Daño Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Probabilidad de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidad de Ataque, staking up to 4 times for a maximum of 32% Velocidad de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Consejos del Coach: Este objeto es ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Daño Mágico, provides a powerful ramp of Velocidad de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            passiveEn = "Surge: Gain 5% Velocidad de Movimiento.\nChaos: Gain 25 Daño de Ataque or 50 Poder de Habilidad (Adaptive).\nWrath: Los ataques infligen 30 Daño Mágico but no longer Golpe Crítico. Fore every 1% Golpe Crítico Rate gained from items, your Daño Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Probabilidad de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidad de Ataque, staking up to 4 times for a maximum of 32% Velocidad de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Coach Tips: Este objeto es ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Daño Mágico, provides a powerful ramp of Velocidad de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            passivePt = "Surge: Gain 5% Velocidade de Movimento.\nChaos: Gain 25 Dano de Ataque or 50 Poder de Habilidade (Adaptive).\nWrath: Los ataques infligen 30 Dano Mágico but no longer Acerto Crítico. Fore every 1% Acerto Crítico Rate gained from items, your Dano Mágico increases by 1.5, up to a max increase of 75 (reached at 50% Chance de Crítico).\nSeething Strike: Los ataques otorgan 8% Velocidade de Ataque, staking up to 4 times for a maximum of 32% Velocidade de Ataque). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n\n💡 Dicas do Coach: Este item é ideal para on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit Dano Mágico, provides a powerful ramp of Velocidade de Ataque and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1768000022_abb24fd724faa77b82baf985dea956b8eae9f31a-512x512.webp"
        ))
        add(WildRiftItem(
            id = "blackfire_torch_magic",
            name = "Antorcha de Fuego Negro",
            nameEn = "Blackfire Torch",
            namePt = "Tocha de Fogo Negro",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+80 Poder de Habilidad • +500 Maximum Mana • +20 Aceleración de Habilidad",
            statsEn = "+80 Ability Power • +500 Maximum Mana • +20 Ability Haste",
            statsPt = "+80 Poder de Habilidade • +500 Maximum Mana • +20 Aceleração de Habilidade",
            passive = "+500 Maximum Maná\nBaleful Blaze: Infligir daño with abilities causes enemies to burn for 20 + 2%Daño Mágico per segundo for 3 segundos.\nDeal 40 plus 2%Daño Mágico every segundo to Monstruos.\nBlackfire: For each campeón enemigo or monster affected by your Baleful Blaze, gain 4% Poder de Habilidad.\n\n💡 Consejos del Coach: Este objeto es ideal para mages who specialize in sustained spell damage. Your abilities ignite enemies, burning them over time, and the more enemies affected by the burn, the more Poder de Habilidad you gain. It excels on Campeones with area-of-effect and damage-over-time abilities, boosting both your overall damage and your ability to clear waves and jungle camps efficiently.",
            passiveEn = "+500 Maximum Maná\nBaleful Blaze: Infligir daño with abilities causes enemies to burn for 20 + 2%Daño Mágico per segundo for 3 segundos.\nDeal 40 plus 2%Daño Mágico every segundo to Monstruos.\nBlackfire: For each campeón enemigo or monster affected by your Baleful Blaze, gain 4% Poder de Habilidad.\n\n💡 Coach Tips: Este objeto es ideal para mages who specialize in sustained spell damage. Your abilities ignite enemies, burning them over time, and the more enemies affected by the burn, the more Poder de Habilidad you gain. It excels on Campeones with area-of-effect and damage-over-time abilities, boosting both your overall damage and your ability to clear waves and jungle camps efficiently.",
            passivePt = "+500 Maximum Maná\nBaleful Blaze: Infligir daño with abilities causes enemies to burn for 20 + 2%Dano Mágico per segundo for 3 segundos.\nDeal 40 plus 2%Dano Mágico every segundo to Monstros.\nBlackfire: For each campeão inimigo or monster affected by your Baleful Blaze, gain 4% Poder de Habilidade.\n\n💡 Dicas do Coach: Este item é ideal para mages who specialize in sustained spell damage. Your abilities ignite enemies, burning them over time, and the more enemies affected by the burn, the more Poder de Habilidade you gain. It excels on Campeões with area-of-effect and damage-over-time abilities, boosting both your overall damage and your ability to clear waves and jungle camps efficiently.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191589_blackfire-torch.webp"
        ))
        add(WildRiftItem(
            id = "dusk_and_dawn_magic",
            name = "Anochecer y Amanecer",
            nameEn = "Anochecer y Amanecer",
            namePt = "Anochecer y Amanecer",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+350 Maximum Health • +25% Velocidad de Ataque • +70 Poder de Habilidad • +20 Aceleración de Habilidad",
            statsEn = "+350 Maximum Health • +25% Attack Speed • +70 Ability Power • +20 Ability Haste",
            statsPt = "+350 Maximum Health • +25% Velocidade de Ataque • +70 Poder de Habilidade • +20 Aceleração de Habilidade",
            passive = "+350 Maximum Vida\nSpellblade: After using an ability, your next attack deals (75% base+ 10%) Adicional Daño Mágico. After a brief delay, apply on-hits to the target 1 additional time. (1.5s Enfriamiento)\nDeals reduced damage to structures.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who weave abilities into their ataques básicos. After casting a spell, your next attack is empowered with Adicional Daño Mágico and then triggers all on-hit effects an additional time, dramatically increasing your overall damage output. It excels on hybrid AP fighters and Cuerpo a cuerpo mages who rely on chaining abilities and autoataques to maximize their DPS.",
            passiveEn = "+350 Maximum Vida\nSpellblade: After using an ability, your next attack deals (75% base+ 10%) Adicional Daño Mágico. After a brief delay, apply on-hits to the target 1 additional time. (1.5s Enfriamiento)\nDeals reduced damage to structures.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who weave abilities into their ataques básicos. After casting a spell, your next attack is empowered with Adicional Daño Mágico and then triggers all on-hit effects an additional time, dramatically increasing your overall damage output. It excels on hybrid AP fighters and Cuerpo a cuerpo mages who rely on chaining abilities and autoataques to maximize their DPS.",
            passivePt = "+350 Maximum Vida\nSpellblade: After using an ability, your next attack deals (75% base+ 10%) Adicional Dano Mágico. After a brief delay, apply on-hits to the target 1 additional time. (1.5s Tempo de Recarga)\nDeals reduced damage to structures.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who weave abilities into their ataques básicos. After casting a spell, your next attack is empowered with Adicional Dano Mágico and then triggers all on-hit effects an additional time, dramatically increasing your overall damage output. It excels on hybrid AP fighters and Corpo a corpo mages who rely on chaining abilities and autoataques to maximize their DPS.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191855_2510_11zon.webp"
        ))
        add(WildRiftItem(
            id = "stormsurge_magic",
            name = "Sobrecarga Tormentosa",
            nameEn = "Stormsurge",
            namePt = "Ápice da Tempestade",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+90 Poder de Habilidad • +15 Penetración Mágica • +6% Velocidad de Movimiento",
            statsEn = "+90 Ability Power • +15 Magic Penetration • +6% Move Speed",
            statsPt = "+90 Poder de Habilidade • +15 Penetração Mágica • +6% Velocidade de Movimento",
            passive = "+15 Penetración Mágica\nStormraider: When damaging a Campeón, Infligir daño equal to 25% of their Vida Máxima within 2.5 segundo(s) applies Squall to them and grants you 25% Adicional Velocidad de Movimiento for 2.5s. (25s Enfriamiento)\nSquall: After 2 segundo(s), strike the target, dealing Daño Mágico equal to 125 plus 10%. If the target is killed before the strike, it detonates immediately in a large area and grants 25 gold.\n\n💡 Consejos del Coach: Este objeto es ideal para mages capable of delivering heavy burst damage. After landing a strong combo, it marks the target, grants you a burst of Velocidad de Movimiento, and follows up with an additional magic strike. If the target dies before the effect triggers, it immediately explodes in an area and rewards you with Adicional gold. An excellent choice for scaling mages and AP assassins looking to secure kills and snowball their advantage.",
            passiveEn = "+15 Penetración Mágica\nStormraider: When damaging a Campeón, Infligir daño equal to 25% of their Vida Máxima within 2.5 segundo(s) applies Squall to them and grants you 25% Adicional Velocidad de Movimiento for 2.5s. (25s Enfriamiento)\nSquall: After 2 segundo(s), strike the target, dealing Daño Mágico equal to 125 plus 10%. If the target is killed before the strike, it detonates immediately in a large area and grants 25 gold.\n\n💡 Coach Tips: Este objeto es ideal para mages capable of delivering heavy burst damage. After landing a strong combo, it marks the target, grants you a burst of Velocidad de Movimiento, and follows up with an additional magic strike. If the target dies before the effect triggers, it immediately explodes in an area and rewards you with Adicional gold. An excellent choice for scaling mages and AP assassins looking to secure kills and snowball their advantage.",
            passivePt = "+15 Penetração Mágica\nStormraider: When damaging a Campeão, Infligir daño equal to 25% of their Vida Máxima within 2.5 segundo(s) applies Squall to them and grants you 25% Adicional Velocidade de Movimento for 2.5s. (25s Tempo de Recarga)\nSquall: After 2 segundo(s), strike the target, dealing Dano Mágico equal to 125 plus 10%. If the target is killed before the strike, it detonates immediately in a large area and grants 25 gold.\n\n💡 Dicas do Coach: Este item é ideal para mages capable of delivering heavy burst damage. After landing a strong combo, it marks the target, grants you a burst of Velocidade de Movimento, and follows up with an additional magic strike. If the target dies before the effect triggers, it immediately explodes in an area and rewards you with Adicional gold. An excellent choice for scaling mages and AP assassins looking to secure kills and snowball their advantage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192090_4646_11zon.webp"
        ))
        add(WildRiftItem(
            id = "void_staff_magic",
            name = "Báculo del Vacío",
            nameEn = "Void Staff",
            namePt = "Cajado do Vazio",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+95 Poder de Habilidad • +40% Penetración Mágica",
            statsEn = "+95 Ability Power • +40% Magic Penetration",
            statsPt = "+95 Poder de Habilidade • +40% Penetração Mágica",
            passive = "Penetración Mágica (%)\n+40%\nThis item is the premier choice against enemies stacking Resistencia Mágica. It greatly increases the effectiveness of your spells through powerful Penetración Mágica, allowing you to deal consistent damage even to the toughest targets. An excellent pickup for any mage once the enemy team starts investing in Resistencia Mágica.",
            passiveEn = "Penetración Mágica (%)\n+40%\nThis item is the premier choice against enemies stacking Resistencia Mágica. It greatly increases the effectiveness of your spells through powerful Penetración Mágica, allowing you to deal consistent damage even to the toughest targets. An excellent pickup for any mage once the enemy team starts investing in Resistencia Mágica.",
            passivePt = "Penetração Mágica (%)\n+40%\nThis item is the premier choice against enemies stacking Resistência Mágica. It greatly increases the effectiveness of your spells through powerful Penetração Mágica, allowing you to deal consistent damage even to the toughest targets. An excellent pickup for any mage once the enemy team starts investing in Resistência Mágica.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192299_3135_11zon.webp"
        ))
        add(WildRiftItem(
            id = "cryptbloom_magic",
            name = "Flor Cripta",
            nameEn = "Cryptbloom",
            namePt = "Criptoflora",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+75 Poder de Habilidad • +30% Penetración Mágica • +20 Aceleración de Habilidad",
            statsEn = "+75 Ability Power • +30% Magic Penetration • +20 Ability Haste",
            statsPt = "+75 Poder de Habilidade • +30% Penetração Mágica • +20 Aceleração de Habilidade",
            passive = "Restore Vida on Campeón kill\n+30% Penetración Mágica\nLife from Death: When a Campeón that you damaged within 3s dies, a nova spreads from their corpse that restores 100 plus 20%Vida to allies. (60s Enfriamiento)\nThis item combines powerful Penetración Mágica with valuable team utility. In addition to boosting your spell damage, Campeones you recently damaged release a healing nova upon death, restoring Vida to nearby allies. It is an excellent choice for mages who want to deal heavy damage while providing extra sustain for their team during extended teamfights.",
            passiveEn = "Restore Vida on Campeón kill\n+30% Penetración Mágica\nLife from Death: When a Campeón that you damaged within 3s dies, a nova spreads from their corpse that restores 100 plus 20%Vida to allies. (60s Enfriamiento)\nThis item combines powerful Penetración Mágica with valuable team utility. In addition to boosting your spell damage, Campeones you recently damaged release a healing nova upon death, restoring Vida to nearby allies. It is an excellent choice for mages who want to deal heavy damage while providing extra sustain for their team during extended teamfights.",
            passivePt = "Restore Vida on Campeão kill\n+30% Penetração Mágica\nLife from Death: When a Campeão that you damaged within 3s dies, a nova spreads from their corpse that restores 100 plus 20%Vida to allies. (60s Tempo de Recarga)\nThis item combines powerful Penetração Mágica with valuable team utility. In addition to boosting your spell damage, Campeões you recently damaged release a healing nova upon death, restoring Vida to nearby allies. It is an excellent choice for mages who want to deal heavy damage while providing extra sustain for their team during extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192528_3137_11zon.webp"
        ))
        add(WildRiftItem(
            id = "bloodletter_s_curse_magic",
            name = "Maldición del Sangrador",
            nameEn = "Maldición del Sangrador",
            namePt = "Maldición del Sangrador",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+350 Maximum Health • +65 Poder de Habilidad • +15 Aceleración de Habilidad",
            statsEn = "+350 Maximum Health • +65 Ability Power • +15 Ability Haste",
            statsPt = "+350 Maximum Health • +65 Poder de Habilidade • +15 Aceleração de Habilidade",
            passive = "Reduces enemy's Resistencia Mágica\n+350 Maximum Vida\nVile Decay: Dealing Daño Mágico with abilities or passives to Campeones reduces their Resistencia Mágica by 7.5% for 6 segundos (max 30%).\nThis item greatly enhances your Daño Mágico by gradually reducing the target's Resistencia Mágica whenever your abilities or passive effects deal damage. It excels in extended fights, allowing both you and your AP teammates to deal increasingly higher damage to the same target. An excellent choice for AP bruisers, damage-over-time mages, and Campeones who can consistently keep the debuff active on multiple enemies.",
            passiveEn = "Reduces enemy's Resistencia Mágica\n+350 Maximum Vida\nVile Decay: Dealing Daño Mágico with abilities or passives to Campeones reduces their Resistencia Mágica by 7.5% for 6 segundos (max 30%).\nThis item greatly enhances your Daño Mágico by gradually reducing the target's Resistencia Mágica whenever your abilities or passive effects deal damage. It excels in extended fights, allowing both you and your AP teammates to deal increasingly higher damage to the same target. An excellent choice for AP bruisers, damage-over-time mages, and Campeones who can consistently keep the debuff active on multiple enemies.",
            passivePt = "Reduces enemy's Resistência Mágica\n+350 Maximum Vida\nVile Decay: Dealing Dano Mágico with abilities or passives to Campeões reduces their Resistência Mágica by 7.5% for 6 segundos (max 30%).\nThis item greatly enhances your Dano Mágico by gradually reducing the target's Resistência Mágica whenever your abilities or passive effects deal damage. It excels in extended fights, allowing both you and your AP teammates to deal increasingly higher damage to the same target. An excellent choice for AP bruisers, damage-over-time mages, and Campeões who can consistently keep the debuff active on multiple enemies.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561458_bloodletters-curse.webp"
        ))
        add(WildRiftItem(
            id = "banshee_s_veil_defense",
            name = "Velo de la Banshee",
            nameEn = "Velo de la Banshee",
            namePt = "Velo de la Banshee",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+105 Poder de Habilidad • +40 Resistencia Mágica",
            statsEn = "+105 Ability Power • +40 Magic Resistance",
            statsPt = "+105 Poder de Habilidade • +40 Resistência Mágica",
            passive = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto otorga strong protection against Daño Mágico while granting a spell Escudo that blocks the next hostile ability. Es especialmente efectivo contra Campeones who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            passiveEn = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Enfriamiento)\n\n💡 Coach Tips: Este objeto otorga strong protection against Daño Mágico while granting a spell Escudo that blocks the next hostile ability. Es especialmente efectivo contra Campeones who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            passivePt = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item concede strong protection against Dano Mágico while granting a spell Escudo that blocks the next hostile ability. É especialmente efetivo contra Campeões who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192846_3102_11zon.webp"
        ))
        add(WildRiftItem(
            id = "hextech_roketbelt_active",
            name = "Cinturón Protocohete Hextech",
            nameEn = "Cinturón Protocohete Hextech",
            namePt = "Cinturón Protocohete Hextech",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+250 Vida Máxima • +70 Poder de Habilidad • +20 Aceleración de Habilidad",
            statsEn = "+250 Max Health • +70 Ability Power • +20 Ability Haste",
            statsPt = "+250 Vida Máxima • +70 Poder de Habilidade • +20 Aceleração de Habilidade",
            passive = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Daño Mágico. (30s Enfriamiento)\nIf Campeones or Monstruos are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidad with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Daño Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeones who need to dive in, secure kills, or dodge key enemy abilities.",
            passiveEn = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Daño Mágico. (30s Enfriamiento)\nIf Campeones or Monstruos are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidad with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Daño Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeones who need to dive in, secure kills, or dodge key enemy abilities.",
            passivePt = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Dano Mágico. (30s Tempo de Recarga)\nIf Campeões or Monstros are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidade with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Dano Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeões who need to dive in, secure kills, or dodge key enemy abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389704_protobelt-enchant.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            nameEn = "Zhonya's Hourglass",
            namePt = "Ampulheta de Zhonya",
            category = "Daño Mágico",
            goldCost = 3300,
            stats = "+40 Armadura • +110 Poder de Habilidad",
            statsEn = "+40 Armor • +110 Ability Power",
            statsPt = "+40 Armadura • +110 Poder de Habilidade",
            passive = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passiveEn = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passivePt = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Tempo de Recarga)\nThis item combines high Poder de Habilidade with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            nameEn = "Redemption",
            namePt = "Redenção",
            category = "Daño Mágico",
            goldCost = 2600,
            stats = "+150 Vida Máxima • +50 Poder de Habilidad • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+150 Vida Máxima • +50 Poder de Habilidade • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passiveEn = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passivePt = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Dano Verdadeiro to campeões inimigos. (60s Tempo de Recarga)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Dano Verdadeiro to campeões inimigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "guardian_angel_defense",
            name = "Ángel Guardián",
            nameEn = "Guardian Angel",
            namePt = "Anjo Guardião",
            category = "Defensa",
            goldCost = 3200,
            stats = "+45 Daño de Ataque • +40 Armadura",
            statsEn = "+45 Attack Damage • +40 Armor",
            statsPt = "+45 Dano de Ataque • +40 Armadura",
            passive = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need a segundo chance in teamfights. Es especialmente efectivo contra Campeones con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            passiveEn = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need a segundo chance in teamfights. Es especialmente efectivo contra Campeones con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            passivePt = "Resurrect: Al recibir daño letal, restaura un 50% de Vida y un 100% de Maná tras 4 segundos en estasis. (180s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need a segundo chance in teamfights. É especialmente efetivo contra Campeões con alto daño explosivo como Zed, Syndra, or Zoe, así como contra iniciadores agresivos como Camille, Kha'Zix, or Lee Sin. El efecto de Resurrección te permite volver al combate tras recibir daño letal, restaurando vida y maná para seguir luchando y asistir a tu equipo en momentos críticos.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp"
        ))
        add(WildRiftItem(
            id = "sunfire_aegis_defense",
            name = "Égida de Fuego Solar",
            nameEn = "Sunfire Aegis",
            namePt = "Égide de Fogo Solar",
            category = "Defensa",
            goldCost = 2900,
            stats = "+500 Vida Máxima, +15 Aceleración de Habilidad",
            statsEn = "+500 Max Health, +15 Ability Haste",
            statsPt = "+500 Vida Máxima, +15 Aceleração de Habilidade",
            passive = "Inmolar: Inflige daño mágico por segundo a enemigos cercanos. Aumenta un 10% por segundo al estar en combate.",
            passiveEn = "Inmolar: Inflige daño mágico por segundo a enemigos cercanos. Aumenta un 10% por segundo al estar en combate.",
            passivePt = "Inmolar: Inflige Dano Mágico por segundo a enemigos cercanos. Aumenta un 10% por segundo al estar en combate.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389023_yordle-sunfire-aegis.webp"
        ))
        add(WildRiftItem(
            id = "randuin_s_omen_defense",
            name = "Presagio de Randuin",
            nameEn = "Randuin's Omen",
            namePt = "Presságio de Randuin",
            category = "Defensa",
            goldCost = 2800,
            stats = "+400 Vida Máxima • +75 Armadura",
            statsEn = "+400 Max Health • +75 Armor",
            statsPt = "+400 Vida Máxima • +75 Armadura",
            passive = "Counters Golpe Crítico Damage\nResilience: Critically Struck deal 30% less damage to you.\nCountercurrent: Gain 1 stacks of Countercurrent when Critically Struck by Daño Físico. Each stuck grants 5% Velocidad de Movimiento and 5% Ralentización resist. Max 4 stacks.\nThis item is built to counter crit-heavy builds. It provides a large Vida pool and Armadura while reducing damage from critical strikes, making you much tougher in head-on engagements. When you are critically struck, you gain stacks that boost your movement and Ralentización resistance, helping you hold position and control fight spacing.  — Perfect for tanks and bruisers who need to stand up to high-crit tiradores y campeones de autoataque and survive extended teamfights.",
            passiveEn = "Counters Golpe Crítico Damage\nResilience: Critically Struck deal 30% less damage to you.\nCountercurrent: Gain 1 stacks of Countercurrent when Critically Struck by Daño Físico. Each stuck grants 5% Velocidad de Movimiento and 5% Ralentización resist. Max 4 stacks.\nThis item is built to counter crit-heavy builds. It provides a large Vida pool and Armadura while reducing damage from critical strikes, making you much tougher in head-on engagements. When you are critically struck, you gain stacks that boost your movement and Ralentización resistance, helping you hold position and control fight spacing.  — Perfect for tanks and bruisers who need to stand up to high-crit tiradores y campeones de autoataque and survive extended teamfights.",
            passivePt = "Counters Acerto Crítico Damage\nResilience: Critically Struck deal 30% less damage to you.\nCountercurrent: Gain 1 stacks of Countercurrent when Critically Struck by Dano Físico. Each stuck grants 5% Velocidade de Movimento and 5% Ralentización resist. Max 4 stacks.\nThis item is built to counter crit-heavy builds. It provides a large Vida pool and Armadura while reducing damage from critical strikes, making you much tougher in head-on engagements. When you are critically struck, you gain stacks that boost your movement and Ralentización resistance, helping you hold position and control fight spacing.  — Perfect for tanks and bruisers who need to stand up to high-crit tiradores y Campeões de autoataque and survive extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389031_randuins-omen.webp"
        ))
        add(WildRiftItem(
            id = "thornmail_defense",
            name = "Cota de Espinas",
            nameEn = "Thornmail",
            namePt = "Armadura de Espinhos",
            category = "Defensa",
            goldCost = 2700,
            stats = "+200 Vida Máxima • +75 Armadura",
            statsEn = "+200 Max Health • +75 Armor",
            statsPt = "+200 Vida Máxima • +75 Armadura",
            passive = "Thorns: When struck by an attack, deal 20 + 6% Adicional Armadura + 1% Adicional Vida Daño Mágico to the attacker.\nEntwine: Apply 50% Heridas Graves to campeones enemigos for 3 segundo(s) when stuck by their attacks or Infligir daño to them.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item reflects a portion of incoming Daño Físico back to attackers as Daño Mágico and applies an effect that reduces enemy healing effectiveness. A strong pick versus teams with heavy auto-Daño de Ataque and sustain — ideal for tanks and bruisers who need to absorb focus and cut down opponent healing.",
            passiveEn = "Thorns: When struck by an attack, deal 20 + 6% Adicional Armadura + 1% Adicional Vida Daño Mágico to the attacker.\nEntwine: Apply 50% Heridas Graves to campeones enemigos for 3 segundo(s) when stuck by their attacks or Infligir daño to them.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item reflects a portion of incoming Daño Físico back to attackers as Daño Mágico and applies an effect that reduces enemy healing effectiveness. A strong pick versus teams with heavy auto-Daño de Ataque and sustain — ideal for tanks and bruisers who need to absorb focus and cut down opponent healing.",
            passivePt = "Thorns: When struck by an attack, deal 20 + 6% Adicional Armadura + 1% Adicional Vida Dano Mágico to the attacker.\nEntwine: Apply 50% Feridas Dolorosas to campeões inimigos for 3 segundo(s) when stuck by their attacks or Infligir daño to them.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.\nThis item reflects a portion of incoming Dano Físico back to attackers as Dano Mágico and applies an effect that reduces enemy healing effectiveness. A strong pick versus teams with heavy auto-Dano de Ataque and sustain — ideal for tanks and bruisers who need to absorb focus and cut down opponent healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389035_thornmail.webp"
        ))
        add(WildRiftItem(
            id = "warmog_s_armor_defense",
            name = "Armadura de Warmog",
            nameEn = "Warmog's Armor",
            namePt = "Armadura de Warmog",
            category = "Defensa",
            goldCost = 2850,
            stats = "+700 Vida Máxima • +100% Regeneración de Vida • +20 Aceleración de Habilidad",
            statsEn = "+700 Max Health • +100% Health Regen • +20 Ability Haste",
            statsPt = "+700 Vida Máxima • +100% Regeneração de Vida • +20 Aceleração de Habilidade",
            passive = "Warmog's Heart: If you have at least 950 Adicional Vida, restore 3.5% Vida per segundo if you haven't taken damage within the last 5 segundos.\nBlessed: Increases all healing and shielding effects on you by 30%.\nThis item is a top survivability pickup: it grants a massive Vida pool and strong out-of-combat regeneration, letting you recover quickly between fights. It also amplifies healing and shields, making you much harder to finish off. Perfect for tanks and bruisers who need high survivability and fast recovery after engagements.",
            passiveEn = "Warmog's Heart: If you have at least 950 Adicional Vida, restore 3.5% Vida per segundo if you haven't taken damage within the last 5 segundos.\nBlessed: Increases all healing and shielding effects on you by 30%.\nThis item is a top survivability pickup: it grants a massive Vida pool and strong out-of-combat regeneration, letting you recover quickly between fights. It also amplifies healing and shields, making you much harder to finish off. Perfect for tanks and bruisers who need high survivability and fast recovery after engagements.",
            passivePt = "Warmog's Heart: If you have at least 950 Adicional Vida, restore 3.5% Vida per segundo if you haven't taken damage within the last 5 segundos.\nBlessed: Increases all healing and shielding effects on you by 30%.\nThis item is a top survivability pickup: it grants a massive Vida pool and strong out-of-combat regeneration, letting you recover quickly between fights. It also amplifies healing and shields, making you much harder to finish off. Perfect for tanks and bruisers who need high survivability and fast recovery after engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389074_warmogs-armor.webp"
        ))
        add(WildRiftItem(
            id = "sterak_s_gage_defense",
            name = "Guantelete de Sterak",
            nameEn = "Sterak's Gage",
            namePt = "Sinal de Sterak",
            category = "Defensa",
            goldCost = 3200,
            stats = "+400 Vida Máxima",
            statsEn = "+400 Max Health",
            statsPt = "+400 Vida Máxima",
            passive = "Heavy Handed: +50% base Daño de Ataque as Adicional Daño de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Enfriamiento).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Daño de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeones with burst damage, such as Zed and Talon, and against Campeones with heavy CC, like Lissandra and Nautilus.",
            passiveEn = "Heavy Handed: +50% base Daño de Ataque as Adicional Daño de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Enfriamiento).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Daño de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeones with burst damage, such as Zed and Talon, and against Campeones with heavy CC, like Lissandra and Nautilus.",
            passivePt = "Heavy Handed: +50% base Dano de Ataque as Adicional Dano de Ataque.\nLifeline: Damage that puts you under 35% Vida grants a Escudo that equal to 75% of your Adicional Vida that decays over 3 segundos (75s Tempo de Recarga).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 segundos.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum Vida, Dano de Ataque, and helps increase your survivability. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Lifeline\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when your Vida drops below 35%, granting a Escudo that absorbs damage, helping you survive heavy hits. \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Sterak's Fury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 segundos, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against Campeões with burst damage, such as Zed and Talon, and against Campeões with heavy CC, like Lissandra and Nautilus.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305021_steraks-gage.webp"
        ))
        add(WildRiftItem(
            id = "iceborn_gauntlet_defense",
            name = "Guantelete de Hielo Oscuro",
            nameEn = "Iceborn Gauntlet",
            namePt = "Manopla dos Glacinatas",
            category = "Defensa",
            goldCost = 3000,
            stats = "+300 Vida Máxima • +50 Armadura • +250 Maná Máximo • +30 Aceleración de Habilidad",
            statsEn = "+300 Max Health • +50 Armor • +250 Max Mana • +30 Ability Haste",
            statsPt = "+300 Vida Máxima • +50 Armadura • +250 Mana Máxima • +30 Aceleração de Habilidade",
            passive = "+250 Maná Máximo\nSpellblade: Using an ability causes your next attack within 10 segundos to deal Adicional Daño Físico equal to (100% base AD  + 25% Adicional Armadura ) in an area and creates an icy field for 2 segundos that Ralentiza by 30%. Armadura increases the size of the icy field. (1.5s Enfriamiento)\nEl daño se reduce contra estructuras.\nThis item greatly boosts your Vida, Armadura, Maná, and Aceleración de Habilidad, making you much tankier. After casting an ability, your next attack in an area deals Adicional Daño Físico and creates an icy field that Ralentiza enemies inside by 30%. The field’s size scales with your Armadura. This makes the item a great choice for Campeones who want to combine high survivability with crowd control and extra AOE damage.",
            passiveEn = "+250 Maná Máximo\nSpellblade: Using an ability causes your next attack within 10 segundos to deal Adicional Daño Físico equal to (100% base AD  + 25% Adicional Armadura ) in an area and creates an icy field for 2 segundos that Ralentiza by 30%. Armadura increases the size of the icy field. (1.5s Enfriamiento)\nEl daño se reduce contra estructuras.\nThis item greatly boosts your Vida, Armadura, Maná, and Aceleración de Habilidad, making you much tankier. After casting an ability, your next attack in an area deals Adicional Daño Físico and creates an icy field that Ralentiza enemies inside by 30%. The field’s size scales with your Armadura. This makes the item a great choice for Campeones who want to combine high survivability with crowd control and extra AOE damage.",
            passivePt = "+250 Mana Máxima\nSpellblade: Using an ability causes your next attack within 10 segundos to deal Adicional Dano Físico equal to (100% base AD  + 25% Adicional Armadura ) in an area and creates an icy field for 2 segundos that Ralentiza by 30%. Armadura increases the size of the icy field. (1.5s Tempo de Recarga)\nEl daño se reduce contra estructuras.\nThis item greatly boosts your Vida, Armadura, Maná, and Aceleração de Habilidade, making you much tankier. After casting an ability, your next attack in an area deals Adicional Dano Físico and creates an icy field that Ralentiza enemies inside by 30%. The field’s size scales with your Armadura. This makes the item a great choice for Campeões who want to combine high survivability with crowd control and extra AOE damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389028_iceborn-gauntlet.webp"
        ))
        add(WildRiftItem(
            id = "dead_man_s_plate_defense",
            name = "Coraza del Muerto",
            nameEn = "Dead Man's Plate",
            namePt = "Couraça do Defunto",
            category = "Defensa",
            goldCost = 2800,
            stats = "+350 Vida Máxima • +70 Armadura",
            statsEn = "+350 Max Health • +70 Armor",
            statsPt = "+350 Vida Máxima • +70 Armadura",
            passive = "Relentless: +5% Velocidad de Movimiento.\nMomentum: Moverse acumula Impulso, granting up to 40 Velocidad de Movimiento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nCrushing Blow: Los ataques infligen up to 100 Adicional Daño Mágico based on Momentum removed. Cuerpo a cuerpo attacks with max Momentum Ralentiza by 75% for 1 segundo.\nideal for Campeones who need to close distance and absorb damage; pairs extremely well with Spellblade items (e.g., Divine Sunderer) for additional burst damage on engage.\n\n💡 Consejos del Coach: Este objeto otorga a substantial boost to Vida and Armadura, and its “Momentum” passive builds Velocidad de Movimiento as you move—up to a cap—until you land an attack, which then triggers “Crushing Blow”, dealing Adicional Daño Mágico based on the momentum removed and slowing enemies at full stacks. This makes it an excellent choice for tanks and bruisers who need extra mobility to engage quickly and sustain through fights.",
            passiveEn = "Relentless: +5% Velocidad de Movimiento.\nMomentum: Moverse acumula Impulso, granting up to 40 Velocidad de Movimiento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nCrushing Blow: Los ataques infligen up to 100 Adicional Daño Mágico based on Momentum removed. Cuerpo a cuerpo attacks with max Momentum Ralentiza by 75% for 1 segundo.\nideal for Campeones who need to close distance and absorb damage; pairs extremely well with Spellblade items (e.g., Divine Sunderer) for additional burst damage on engage.\n\n💡 Coach Tips: Este objeto otorga a substantial boost to Vida and Armadura, and its “Momentum” passive builds Velocidad de Movimiento as you move—up to a cap—until you land an attack, which then triggers “Crushing Blow”, dealing Adicional Daño Mágico based on the momentum removed and slowing enemies at full stacks. This makes it an excellent choice for tanks and bruisers who need extra mobility to engage quickly and sustain through fights.",
            passivePt = "Relentless: +5% Velocidade de Movimento.\nMomentum: Moverse acumula Impulso, granting up to 40 Velocidade de Movimento at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nCrushing Blow: Los ataques infligen up to 100 Adicional Dano Mágico based on Momentum removed. Corpo a corpo attacks with max Momentum Ralentiza by 75% for 1 segundo.\nideal for Campeões who need to close distance and absorb damage; pairs extremely well with Spellblade items (e.g., Divine Sunderer) for additional burst damage on engage.\n\n💡 Dicas do Coach: Este item concede a substantial boost to Vida and Armadura, and its “Momentum” passive builds Velocidade de Movimento as you move—up to a cap—until you land an attack, which then triggers “Crushing Blow”, dealing Adicional Dano Mágico based on the momentum removed and slowing enemies at full stacks. This makes it an excellent choice for tanks and bruisers who need extra mobility to engage quickly and sustain through fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389106_dead-mans-plate.webp"
        ))
        add(WildRiftItem(
            id = "zeke_s_convergence_support",
            name = "Convergencia de Zeke",
            nameEn = "Zeke's Convergence",
            namePt = "Convergência de Zeke",
            category = "Defensa",
            goldCost = 2700,
            stats = "+40 Armadura • +350 Vida Máxima • +150 Maná Máximo • +15 Aceleración de Habilidad",
            statsEn = "+40 Armor • +350 Max Health • +150 Max Mana • +15 Ability Haste",
            statsPt = "+40 Armadura • +350 Vida Máxima • +150 Mana Máxima • +15 Aceleração de Habilidade",
            passive = "Boosts allies Daño de Ataque\n+150 Maná Máximo\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeones on the trail gain 40 Adicional Velocidad de Movimiento for 1 segundo. (30s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para tanky support Campeones who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleración de Habilidad. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidad de Movimiento to allies. During the effect, the attacks of a nearby marked ally deal additional Daño Mágico, giving your team a powerful advantage in teamfights.",
            passiveEn = "Boosts allies Daño de Ataque\n+150 Maná Máximo\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeones on the trail gain 40 Adicional Velocidad de Movimiento for 1 segundo. (30s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para tanky support Campeones who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleración de Habilidad. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidad de Movimiento to allies. During the effect, the attacks of a nearby marked ally deal additional Daño Mágico, giving your team a powerful advantage in teamfights.",
            passivePt = "Boosts allies Dano de Ataque\n+150 Mana Máxima\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeões on the trail gain 40 Adicional Velocidade de Movimento for 1 segundo. (30s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para tanky support Campeões who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleração de Habilidade. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidade de Movimento to allies. During the effect, the attacks of a nearby marked ally deal additional Dano Mágico, giving your team a powerful advantage in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp"
        ))
        add(WildRiftItem(
            id = "death_s_dance_defense",
            name = "Danza de la Muerte",
            nameEn = "Death's Dance",
            namePt = "Dança da Morte",
            category = "Defensa",
            goldCost = 3100,
            stats = "+35 Daño de Ataque • +40 Armadura • +15 Aceleración de Habilidad",
            statsEn = "+35 Attack Damage • +40 Armor • +15 Ability Haste",
            statsPt = "+35 Dano de Ataque • +40 Armadura • +15 Aceleração de Habilidade",
            passive = "Defy: Campeón takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Daño Físico and Daño Mágico received (12% for A distancia Campeones) is dealt to you over 3 segundos as Daño Verdadero instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleración de Habilidad, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            passiveEn = "Defy: Campeón takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Daño Físico and Daño Mágico received (12% for A distancia Campeones) is dealt to you over 3 segundos as Daño Verdadero instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleración de Habilidad, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            passivePt = "Defy: Campeão takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum Vida over 2 segundos.\nCauterize: 27% of all Dano Físico and Dano Mágico received (12% for À distância Campeões) is dealt to you over 3 segundos as Dano Verdadeiro instead.\nThis item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with Armadura and Aceleração de Habilidade, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301539_yordle-deaths-dance.webp"
        ))
        add(WildRiftItem(
            id = "winter_s_approach_defense",
            name = "Llegada del Invierno",
            nameEn = "Winter's Approach",
            namePt = "Aproximação do Inverno",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Vida Máxima • +500 Maná Máximo • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +500 Max Mana • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +500 Mana Máxima • +15 Aceleração de Habilidade",
            passive = "Converts Maná to Vida\n+500 Maná Máximo\nAwe: Grants Adicional Vida equal to 8% of Maná Máximo and refunds 15% of all Maná spent.\nManá Charge: Increases Maná Máximo by 12 every attack, when Maná is spent or when taking damage from Campeones, epic Monstruos, or towers. Hasta un máximo de 700 Adicional Maná, transforming Winter's Approach into Fimbulwinter. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Consejos del Coach: Este objeto es ideal para defensive Campeones, especially tanks who rely on frequent ability casts and autoataques. It provides bonuses to Vida, maximum Maná, and Aceleración de Habilidad, while also refunding a portion of Maná spent, helping you stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná on each ataque básico, Maná expenditure, or when taking damage from Campeones, epic Monstruos, or turrets, allowing you to build a massive Maná pool and eventually transform the item into a more powerful version. Combined with its Vida and defensive stats, this grants immense survivability and the ability to cast spells more often—ideal for spell-weaving tanks who can build huge shields and effectively control engagements.",
            passiveEn = "Converts Maná to Vida\n+500 Maná Máximo\nAwe: Grants Adicional Vida equal to 8% of Maná Máximo and refunds 15% of all Maná spent.\nManá Charge: Increases Maná Máximo by 12 every attack, when Maná is spent or when taking damage from Campeones, epic Monstruos, or towers. Hasta un máximo de 700 Adicional Maná, transforming Winter's Approach into Fimbulwinter. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Coach Tips: Este objeto es ideal para defensive Campeones, especially tanks who rely on frequent ability casts and autoataques. It provides bonuses to Vida, maximum Maná, and Aceleración de Habilidad, while also refunding a portion of Maná spent, helping you stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná on each ataque básico, Maná expenditure, or when taking damage from Campeones, epic Monstruos, or turrets, allowing you to build a massive Maná pool and eventually transform the item into a more powerful version. Combined with its Vida and defensive stats, this grants immense survivability and the ability to cast spells more often—ideal for spell-weaving tanks who can build huge shields and effectively control engagements.",
            passivePt = "Converts Maná to Vida\n+500 Mana Máxima\nAwe: Grants Adicional Vida equal to 8% of Mana Máxima and refunds 15% of all Maná spent.\nManá Charge: Increases Mana Máxima by 12 every attack, when Maná is spent or when taking damage from Campeões, epic Monstros, or towers. Hasta un máximo de 700 Adicional Maná, transforming Winter's Approach into Fimbulwinter. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.\n\n💡 Dicas do Coach: Este item é ideal para defensive Campeões, especially tanks who rely on frequent ability casts and autoataques. It provides bonuses to Vida, maximum Maná, and Aceleração de Habilidade, while also refunding a portion of Maná spent, helping you stay in fights longer. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Maná Charge\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect increases your maximum Maná on each ataque básico, Maná expenditure, or when taking damage from Campeões, epic Monstros, or turrets, allowing you to build a massive Maná pool and eventually transform the item into a more powerful version. Combined with its Vida and defensive stats, this grants immense survivability and the ability to cast spells more often—ideal for spell-weaving tanks who can build huge shields and effectively control engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389175_winters-approach.webp"
        ))
        add(WildRiftItem(
            id = "fimbulwinter_defense",
            name = "Invierno Eterno",
            nameEn = "Fimbulwinter",
            namePt = "Fimbulwinter",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Vida Máxima • +1200 Maná Máximo • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +1200 Max Mana • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +1200 Mana Máxima • +15 Aceleração de Habilidade",
            passive = "Converts Maná to Vida\n+1200 Maná Máximo\nAwe: Grants Adicional Vida equal to 10% of Maná Máximo and refunds 15% of all Maná spent.\nFrozen Colossus: Immobilizing or slowing an campeón enemigo consumes 3% current Maná and grants a Escudo for 3 segundos, absorbing 90-180 +4.5% current Maná, increased by 80% if there is more than 1 campeón enemigo nearby.\nOnly triggers when above 20% Maná Máximo. (8s Enfriamiento).\nEscudo is 50% effective for A distancia Campeones.\n\n💡 Consejos del Coach: Este objeto otorga massive Maná, Vida, and Aceleración de Habilidad, and its “Frozen Colossus” passive consumes Maná when you Ralentización or immobilize an enemy to grant a strong Escudo that scales with your Maná pool and increases near multiple enemies. Perfect for spell-weaving tanks needing extra protection from their Maná reserves.",
            passiveEn = "Converts Maná to Vida\n+1200 Maná Máximo\nAwe: Grants Adicional Vida equal to 10% of Maná Máximo and refunds 15% of all Maná spent.\nFrozen Colossus: Immobilizing or slowing an campeón enemigo consumes 3% current Maná and grants a Escudo for 3 segundos, absorbing 90-180 +4.5% current Maná, increased by 80% if there is more than 1 campeón enemigo nearby.\nOnly triggers when above 20% Maná Máximo. (8s Enfriamiento).\nEscudo is 50% effective for A distancia Campeones.\n\n💡 Coach Tips: Este objeto otorga massive Maná, Vida, and Aceleración de Habilidad, and its “Frozen Colossus” passive consumes Maná when you Ralentización or immobilize an enemy to grant a strong Escudo that scales with your Maná pool and increases near multiple enemies. Perfect for spell-weaving tanks needing extra protection from their Maná reserves.",
            passivePt = "Converts Maná to Vida\n+1200 Mana Máxima\nAwe: Grants Adicional Vida equal to 10% of Mana Máxima and refunds 15% of all Maná spent.\nFrozen Colossus: Immobilizing or slowing an campeão inimigo consumes 3% current Maná and grants a Escudo for 3 segundos, absorbing 90-180 +4.5% current Maná, increased by 80% if there is more than 1 campeão inimigo nearby.\nOnly triggers when above 20% Mana Máxima. (8s Tempo de Recarga).\nEscudo is 50% effective for À distância Campeões.\n\n💡 Dicas do Coach: Este item concede massive Maná, Vida, and Aceleração de Habilidade, and its “Frozen Colossus” passive consumes Maná when you Ralentización or immobilize an enemy to grant a strong Escudo that scales with your Maná pool and increases near multiple enemies. Perfect for spell-weaving tanks needing extra protection from their Maná reserves.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389109_fimbulwinter.webp"
        ))
        add(WildRiftItem(
            id = "force_of_nature_defense",
            name = "Fuerza de la Naturaleza",
            nameEn = "Force of Nature",
            namePt = "Força da Natureza",
            category = "Defensa",
            goldCost = 2750,
            stats = "+350 Vida Máxima • +60 Resistencia Mágica • +5% Velocidad de Movimiento",
            statsEn = "+350 Max Health • +60 Magic Resistance • +5% Move Speed",
            statsPt = "+350 Vida Máxima • +60 Resistência Mágica • +5% Velocidade de Movimento",
            passive = "Stacking Resistencia Mágica and Velocidad de Movimiento\nAbsorb: Taking ability damage from campeones enemigos grants 1 stack(s) of Steadfast for 7 segundos, max 4 stacks. Receiving damage from an campeón enemigo will refresh the duration of the stacks. At maximum stacks, gain 10% Velocidad de Movimiento and reduce all incoming Daño Mágico by 20%.\n\n💡 Consejos del Coach: Este objeto otorga a substantial boost to Vida and Resistencia Mágica, and its “Absorb” passive stacks up when you take ability damage from campeones enemigos, reducing all incoming Daño Mágico and granting Adicional Velocidad de Movimiento at max stacks. It’s perfect for tanks who need to withstand teams heavy in Daño Mágico and maintain mobility to be in the right position during fights. Pick this up when the enemy team builds Daño Mágico (e.g., Syndra, Brand) and you need extra Velocidad de Movimiento to initiate effectively or escape dangerous situations.",
            passiveEn = "Stacking Resistencia Mágica and Velocidad de Movimiento\nAbsorb: Taking ability damage from campeones enemigos grants 1 stack(s) of Steadfast for 7 segundos, max 4 stacks. Receiving damage from an campeón enemigo will refresh the duration of the stacks. At maximum stacks, gain 10% Velocidad de Movimiento and reduce all incoming Daño Mágico by 20%.\n\n💡 Coach Tips: Este objeto otorga a substantial boost to Vida and Resistencia Mágica, and its “Absorb” passive stacks up when you take ability damage from campeones enemigos, reducing all incoming Daño Mágico and granting Adicional Velocidad de Movimiento at max stacks. It’s perfect for tanks who need to withstand teams heavy in Daño Mágico and maintain mobility to be in the right position during fights. Pick this up when the enemy team builds Daño Mágico (e.g., Syndra, Brand) and you need extra Velocidad de Movimiento to initiate effectively or escape dangerous situations.",
            passivePt = "Stacking Resistência Mágica and Velocidade de Movimento\nAbsorb: Taking ability damage from campeões inimigos grants 1 stack(s) of Steadfast for 7 segundos, max 4 stacks. Receiving damage from an campeão inimigo will refresh the duration of the stacks. At maximum stacks, gain 10% Velocidade de Movimento and reduce all incoming Dano Mágico by 20%.\n\n💡 Dicas do Coach: Este item concede a substantial boost to Vida and Resistência Mágica, and its “Absorb” passive stacks up when you take ability damage from campeões inimigos, reducing all incoming Dano Mágico and granting Adicional Velocidade de Movimento at max stacks. It’s perfect for tanks who need to withstand teams heavy in Dano Mágico and maintain mobility to be in the right position during fights. Pick this up when the enemy team builds Dano Mágico (e.g., Syndra, Brand) and you need extra Velocidade de Movimento to initiate effectively or escape dangerous situations.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389153_force-of-nature.webp"
        ))
        add(WildRiftItem(
            id = "frozen_heart_support",
            name = "Corazón de Hielo",
            nameEn = "Frozen Heart",
            namePt = "Coração Congelado",
            category = "Defensa",
            goldCost = 2650,
            stats = "+80 Armadura • +250 Maná Máximo • +20 Aceleración de Habilidad",
            statsEn = "+80 Armor • +250 Max Mana • +20 Ability Haste",
            statsPt = "+80 Armadura • +250 Mana Máxima • +20 Aceleração de Habilidade",
            passive = "+250 Maná Máximo\nWinter's Caress: ataques básicos and Daño Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeón enemigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidad de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidad de Ataque reduction. Each individual ability has a 3 segundos Enfriamiento on applying Chill stacks.\nThis item is ideal for tanks and support Campeones who need to Ralentización enemy Velocidad de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleración de Habilidad. The “Winter’s Caress” passive applies up to four stacks of Chill on campeones enemigos through your ataques básicos, abilities, or any Daño Mágico they take—each stack Ralentiza their Velocidad de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            passiveEn = "+250 Maná Máximo\nWinter's Caress: ataques básicos and Daño Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeón enemigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidad de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidad de Ataque reduction. Each individual ability has a 3 segundos Enfriamiento on applying Chill stacks.\nThis item is ideal for tanks and support Campeones who need to Ralentización enemy Velocidad de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleración de Habilidad. The “Winter’s Caress” passive applies up to four stacks of Chill on campeones enemigos through your ataques básicos, abilities, or any Daño Mágico they take—each stack Ralentiza their Velocidad de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            passivePt = "+250 Mana Máxima\nWinter's Caress: ataques básicos and Dano Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeão inimigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidade de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidade de Ataque reduction. Each individual ability has a 3 segundos Tempo de Recarga on applying Chill stacks.\nThis item is ideal for tanks and support Campeões who need to Ralentización enemy Velocidade de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleração de Habilidade. The “Winter’s Caress” passive applies up to four stacks of Chill on campeões inimigos through your ataques básicos, abilities, or any Dano Mágico they take—each stack Ralentiza their Velocidade de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389159_frozen-heart.webp"
        ))
        add(WildRiftItem(
            id = "dawnshroud_support",
            name = "Manto del Amanecer",
            nameEn = "Dawnshroud",
            namePt = "Manto da Alvorada",
            category = "Defensa",
            goldCost = 2700,
            stats = "+250 Vida Máxima • +50 Armadura • +30 Resistencia Mágica",
            statsEn = "+250 Max Health • +50 Armor • +30 Magic Resistance",
            statsPt = "+250 Vida Máxima • +50 Armadura • +30 Resistência Mágica",
            passive = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeón Campeón or are immobilized within 400 units of an campeón enemigo, reveal all nearby campeones enemigos for 3 segundos, deal Daño Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistencia Mágica (3s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es excelente para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeones, deals an explosive burst of Daño Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            passiveEn = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeón Campeón or are immobilized within 400 units of an campeón enemigo, reveal all nearby campeones enemigos for 3 segundos, deal Daño Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistencia Mágica (3s Enfriamiento)\n\n💡 Coach Tips: Este objeto es excelente para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeones, deals an explosive burst of Daño Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            passivePt = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeão Campeão or are immobilized within 400 units of an campeão inimigo, reveal all nearby campeões inimigos for 3 segundos, deal Dano Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistência Mágica (3s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ótimo para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeões, deals an explosive burst of Dano Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp"
        ))
        add(WildRiftItem(
            id = "amaranth_s_twinguard_defense",
            name = "Protección Gemela de Amaranth",
            nameEn = "Protección Gemela de Amaranth",
            namePt = "Protección Gemela de Amaranth",
            category = "Defensa",
            goldCost = 3100,
            stats = "+60 Armadura • +60 Resistencia Mágica",
            statsEn = "+60 Armor • +60 Magic Resistance",
            statsPt = "+60 Armadura • +60 Resistência Mágica",
            passive = "In-combat durability\nEndurance: Gain 1 stacks of Endurance every 1 segundos while En combate with campeones enemigos (max 5 stacks). At maximum stacks, gain 20% size, 20% Tenacity, and increase Armadura by 30% and Resistencia Mágica by 30% until fuera de combate with Campeón.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who spend a lot of time in the thick of fights and need extra durability and crowd control resistance. It provides bonuses to Armadura and Resistencia Mágica. The “Endurance” passive stacks up to five times Durante el combate, and at full stacks you increase in size, gain enhanced tenacity, and receive Adicional Armadura and Resistencia Mágica until you exit combat. This allows you to stay in the frontline longer and withstand enemy attacks more effectively. Due to its versatile utility, this item is one of the most popular defensive choices in the game and is used by the majority of tanks, fighters, and other classes.",
            passiveEn = "In-combat durability\nEndurance: Gain 1 stacks of Endurance every 1 segundos while En combate with campeones enemigos (max 5 stacks). At maximum stacks, gain 20% size, 20% Tenacity, and increase Armadura by 30% and Resistencia Mágica by 30% until fuera de combate with Campeón.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who spend a lot of time in the thick of fights and need extra durability and crowd control resistance. It provides bonuses to Armadura and Resistencia Mágica. The “Endurance” passive stacks up to five times Durante el combate, and at full stacks you increase in size, gain enhanced tenacity, and receive Adicional Armadura and Resistencia Mágica until you exit combat. This allows you to stay in the frontline longer and withstand enemy attacks more effectively. Due to its versatile utility, this item is one of the most popular defensive choices in the game and is used by the majority of tanks, fighters, and other classes.",
            passivePt = "In-combat durability\nEndurance: Gain 1 stacks of Endurance every 1 segundos while En combate with campeões inimigos (max 5 stacks). At maximum stacks, gain 20% size, 20% Tenacity, and increase Armadura by 30% and Resistência Mágica by 30% until fuera de combate with Campeão.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who spend a lot of time in the thick of fights and need extra durability and crowd control resistance. It provides bonuses to Armadura and Resistência Mágica. The “Endurance” passive stacks up to five times Durante el combate, and at full stacks you increase in size, gain enhanced tenacity, and receive Adicional Armadura and Resistência Mágica until you exit combat. This allows you to stay in the frontline longer and withstand enemy attacks more effectively. Due to its versatile utility, this item is one of the most popular defensive choices in the game and is used by the majority of tanks, fighters, and other classes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389236_amaranths-twinguard.webp"
        ))
        add(WildRiftItem(
            id = "mantle_of_the_twelfth_hour_defense",
            name = "Manto de la Duodécima Hora",
            nameEn = "Mantle of the Twelfth Hour",
            namePt = "Manto da Décima Segunda Hora",
            category = "Defensa",
            goldCost = 2900,
            stats = "+200 Vida Máxima • +40 Armadura • +40 Resistencia Mágica",
            statsEn = "+200 Max Health • +40 Armor • +40 Magic Resistance",
            statsPt = "+200 Vida Máxima • +40 Armadura • +40 Resistência Mágica",
            passive = "Increases Vida Máxima when your Vida is low\nLifeline: Damage that puts you under 35%, grants Adicional Vida Máxima equal to 180 + 45% Adicional Vida for 3 segundos, and provides 50% Ralentización Resistance and 30 Velocidad de Movimiento for 3 segundos. (70s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who find themselves on the brink of death and need an instant survivability boost. When you take damage that drops you below 35% Vida, you gain Adicional maximum Vida, significant Velocidad de Movimiento, and high Ralentización resistance for a short duration. This gives you the chance to escape danger or stay in the fight. The item is especially effective for tanks and bruisers who need to endure critical moments while retaining mobility at low Vida.",
            passiveEn = "Increases Vida Máxima when your Vida is low\nLifeline: Damage that puts you under 35%, grants Adicional Vida Máxima equal to 180 + 45% Adicional Vida for 3 segundos, and provides 50% Ralentización Resistance and 30 Velocidad de Movimiento for 3 segundos. (70s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para Campeones who find themselves on the brink of death and need an instant survivability boost. When you take damage that drops you below 35% Vida, you gain Adicional maximum Vida, significant Velocidad de Movimiento, and high Ralentización resistance for a short duration. This gives you the chance to escape danger or stay in the fight. The item is especially effective for tanks and bruisers who need to endure critical moments while retaining mobility at low Vida.",
            passivePt = "Increases Vida Máxima when your Vida is low\nLifeline: Damage that puts you under 35%, grants Adicional Vida Máxima equal to 180 + 45% Adicional Vida for 3 segundos, and provides 50% Ralentización Resistance and 30 Velocidade de Movimento for 3 segundos. (70s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para Campeões who find themselves on the brink of death and need an instant survivability boost. When you take damage that drops you below 35% Vida, you gain Adicional maximum Vida, significant Velocidade de Movimento, and high Ralentización resistance for a short duration. This gives you the chance to escape danger or stay in the fight. The item is especially effective for tanks and bruisers who need to endure critical moments while retaining mobility at low Vida.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389204_mantle-of-the-twelfth-hour.webp"
        ))
        add(WildRiftItem(
            id = "searing_crown_defense",
            name = "Corona Abrasadora",
            nameEn = "Searing Crown",
            namePt = "Coroa Incandescente",
            category = "Defensa",
            goldCost = 2700,
            stats = "+300 Vida Máxima • +50 Armadura",
            statsEn = "+300 Max Health • +50 Armor",
            statsPt = "+300 Vida Máxima • +50 Armadura",
            passive = "Fiery Touch: After Infligir daño with an attack or ability, burn target for 3 segundos, dealing 1.4% of the target’s maximum Vida as Daño Mágico per segundo (damage reduced to 0.8% for A distancia users).\nDeals 150% damage to Súbditos and Monstruos.\nMaximum 125 damage to Monstruos.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who want to combine high survivability with Adicional Vida-percentage damage. It provides a substantial boost to Vida and Armadura, and its “Fiery Touch” passive burns targets on hit with attacks or abilities, dealing Daño Mágico equal to a percentage of their maximum Vida. This makes it effective against tanks and high-Vida Campeones, while also speeding up waveclear and jungle clear. Ideal for tanks and bruisers who want to leave a mark in fights while staying durable.",
            passiveEn = "Fiery Touch: After Infligir daño with an attack or ability, burn target for 3 segundos, dealing 1.4% of the target’s maximum Vida as Daño Mágico per segundo (damage reduced to 0.8% for A distancia users).\nDeals 150% damage to Súbditos and Monstruos.\nMaximum 125 damage to Monstruos.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who want to combine high survivability with Adicional Vida-percentage damage. It provides a substantial boost to Vida and Armadura, and its “Fiery Touch” passive burns targets on hit with attacks or abilities, dealing Daño Mágico equal to a percentage of their maximum Vida. This makes it effective against tanks and high-Vida Campeones, while also speeding up waveclear and jungle clear. Ideal for tanks and bruisers who want to leave a mark in fights while staying durable.",
            passivePt = "Fiery Touch: After Infligir daño with an attack or ability, burn target for 3 segundos, dealing 1.4% of the target’s maximum Vida as Dano Mágico per segundo (damage reduced to 0.8% for À distância users).\nDeals 150% damage to Tropas and Monstros.\nMaximum 125 damage to Monstros.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who want to combine high survivability with Adicional Vida-percentage damage. It provides a substantial boost to Vida and Armadura, and its “Fiery Touch” passive burns targets on hit with attacks or abilities, dealing Dano Mágico equal to a percentage of their maximum Vida. This makes it effective against tanks and high-Vida Campeões, while also speeding up waveclear and jungle clear. Ideal for tanks and bruisers who want to leave a mark in fights while staying durable.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389203_searing-crown.webp"
        ))
        add(WildRiftItem(
            id = "heartsteel_defense",
            name = "Corazón de Acero",
            nameEn = "Heartsteel",
            namePt = "Coração de Aço",
            category = "Defensa",
            goldCost = 3000,
            stats = "+700 Vida Máxima, +150% Regeneración de Vida Básica, +20 Aceleración de Habilidad",
            statsEn = "+700 Max Health, +150% Health Regen Básica, +20 Ability Haste",
            statsPt = "+700 Vida Máxima, +150% Regeneração de Vida Básica, +20 Aceleração de Habilidade",
            passive = "Consumo Colosal: Carga un golpe devastador contra un campeón dentro de 700 unidades. Inflige 120 + 5% de vida máxima y otorga vida máxima permanente equivalente al 10% del daño infligido.",
            passiveEn = "Consumo Colosal: Carga un golpe devastador contra un campeón dentro de 700 unidades. Inflige 120 + 5% de vida máxima y otorga vida máxima permanente equivalente al 10% del daño infligido.",
            passivePt = "Consumo Colosal: Carga un golpe devastador contra un Campeão dentro de 700 unidades. Inflige 120 + 5% de Vida Máxima y otorga Vida Máxima permanente equivalente al 10% del daño infligido.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389201_heartsteel.webp"
        ))
        add(WildRiftItem(
            id = "titanic_hydra_defense",
            name = "Hidra Titánica",
            nameEn = "Titanic Hydra",
            namePt = "Hidra Titânica",
            category = "Defensa",
            goldCost = 3000,
            stats = "+450 Vida Máxima • +40 Daño de Ataque",
            statsEn = "+450 Max Health • +40 Attack Damage",
            statsPt = "+450 Vida Máxima • +40 Dano de Ataque",
            passive = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Daño Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Daño Físico equal to 80 + 10% Adicional to enemies behind the target. A distancia Campeones deal 75% of the damage.\n\n💡 Consejos del Coach: Este objeto convierte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Daño Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Cuerpo a cuerpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            passiveEn = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Daño Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Daño Físico equal to 80 + 10% Adicional to enemies behind the target. A distancia Campeones deal 75% of the damage.\n\n💡 Coach Tips: Este objeto convierte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Daño Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Cuerpo a cuerpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            passivePt = "Los ataques infligen Adicional damage in a area\nCleave: Every 1.75 segundo(s), your next attack deals Adicional Dano Físico equal to 25 + 3% Adicional (also applies to turrets), creating a shockwave that deals Dano Físico equal to 80 + 10% Adicional to enemies behind the target. À distância Campeões deal 75% of the damage.\n\n💡 Dicas do Coach: Este item converte your ataques básicos into an AOE tool: periodically your next hit becomes a sweeping strike that deals Adicional Dano Físico to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for Corpo a corpo bruisers and tanks who combine a big Vida pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304937_titanic-hydra.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            nameEn = "Redemption",
            namePt = "Redenção",
            category = "Defensa",
            goldCost = 2600,
            stats = "+150 Vida Máxima • +50 Poder de Habilidad • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+150 Vida Máxima • +50 Poder de Habilidade • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passiveEn = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passivePt = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Dano Verdadeiro to campeões inimigos. (60s Tempo de Recarga)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Dano Verdadeiro to campeões inimigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "kaenic_rookern_defense",
            name = "Rookern Kaénico",
            nameEn = "Kaenic Rookern",
            namePt = "Rookern Caênico",
            category = "Defensa",
            goldCost = 2800,
            stats = "+350 Vida Máxima • +100% Regeneración de Vida • +85 Resistencia Mágica",
            statsEn = "+350 Max Health • +100% Health Regen • +85 Magic Resistance",
            statsPt = "+350 Vida Máxima • +100% Regeneração de Vida • +85 Resistência Mágica",
            passive = "Gains a magic Escudo when fuera de combate\nMagebane: After not taking Daño Mágico for 12 segundos, gain a magic Escudo that absorbs damage equal to 50-150 + 14% of Vida Máxima.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who need extra protection against Daño Mágico, particularly when spending some time fuera de combate. It provides significant bonuses to Vida, regeneration, and Resistencia Mágica. The “Magebane” passive activates after 12 segundos without taking Daño Mágico, granting you a magic Escudo that absorbs damage based on your maximum Vida. This allows you to safely recover between fights and confidently re-enter combat. It’s especially effective against teams heavy in AP damage.",
            passiveEn = "Gains a magic Escudo when fuera de combate\nMagebane: After not taking Daño Mágico for 12 segundos, gain a magic Escudo that absorbs damage equal to 50-150 + 14% of Vida Máxima.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who need extra protection against Daño Mágico, particularly when spending some time fuera de combate. It provides significant bonuses to Vida, regeneration, and Resistencia Mágica. The “Magebane” passive activates after 12 segundos without taking Daño Mágico, granting you a magic Escudo that absorbs damage based on your maximum Vida. This allows you to safely recover between fights and confidently re-enter combat. It’s especially effective against teams heavy in AP damage.",
            passivePt = "Gains a magic Escudo when fuera de combate\nMagebane: After not taking Dano Mágico for 12 segundos, gain a magic Escudo that absorbs damage equal to 50-150 + 14% of Vida Máxima.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who need extra protection against Dano Mágico, particularly when spending some time fuera de combate. It provides significant bonuses to Vida, regeneration, and Resistência Mágica. The “Magebane” passive activates after 12 segundos without taking Dano Mágico, granting you a magic Escudo that absorbs damage based on your maximum Vida. This allows you to safely recover between fights and confidently re-enter combat. It’s especially effective against teams heavy in AP damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389228_kaenic-rookern.webp"
        ))
        add(WildRiftItem(
            id = "yordle_trap_support",
            name = "Trampa Yordle",
            nameEn = "Trampa Yordle",
            namePt = "Trampa Yordle",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Vida Máxima • +40 Armadura • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Armor • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +40 Armadura • +15 Aceleração de Habilidade",
            passive = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidad de Movimiento for 3 segundo(s) and mark the target, reducing their Armadura and Resistencia Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeones with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidad de Movimiento while marking the target, reducing their Armadura and Resistencia Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            passiveEn = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidad de Movimiento for 3 segundo(s) and mark the target, reducing their Armadura and Resistencia Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeones with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidad de Movimiento while marking the target, reducing their Armadura and Resistencia Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            passivePt = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidade de Movimento for 3 segundo(s) and mark the target, reducing their Armadura and Resistência Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeões with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidade de Movimento while marking the target, reducing their Armadura and Resistência Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389328_yordle-trap.webp"
        ))
        add(WildRiftItem(
            id = "radiant_virtue_defense",
            name = "Virtud Radiante",
            nameEn = "Virtud Radiante",
            namePt = "Virtud Radiante",
            category = "Defensa",
            goldCost = 2850,
            stats = "+300 Vida Máxima • +45 Armadura • +15 Aceleración de Habilidad",
            statsEn = "+300 Max Health • +45 Armor • +15 Ability Haste",
            statsPt = "+300 Vida Máxima • +45 Armadura • +15 Aceleração de Habilidade",
            passive = "Heal allies upon casting your ultimate ability.\nGuiding Light: Upon casting your ultimate ability, you Transcend, increasing your Vida Máxima by 10% durante 6s. While Transcended, allied Campeones within 1,200 units of you heal for 2.5% of your Vida Máxima per segundo over the duration. (60s Enfriamiento) If you're a A distancia Campeón, heals granted are reduced by 50%.\nThis item boosts your durability by granting extra Vida Máxima, Armadura, and Aceleración de Habilidad. Its passive causes you to transcend after casting your ultimate: you temporarily raise your Vida Máxima, and nearby allies are healed based on that boosted Vida. The healing is reduced for A distancia Campeones. A strong pick for frontliners and supports who want to survive engages while providing teamwide sustain during fights.",
            passiveEn = "Heal allies upon casting your ultimate ability.\nGuiding Light: Upon casting your ultimate ability, you Transcend, increasing your Vida Máxima by 10% durante 6s. While Transcended, allied Campeones within 1,200 units of you heal for 2.5% of your Vida Máxima per segundo over the duration. (60s Enfriamiento) If you're a A distancia Campeón, heals granted are reduced by 50%.\nThis item boosts your durability by granting extra Vida Máxima, Armadura, and Aceleración de Habilidad. Its passive causes you to transcend after casting your ultimate: you temporarily raise your Vida Máxima, and nearby allies are healed based on that boosted Vida. The healing is reduced for A distancia Campeones. A strong pick for frontliners and supports who want to survive engages while providing teamwide sustain during fights.",
            passivePt = "Heal allies upon casting your ultimate ability.\nGuiding Light: Upon casting your ultimate ability, you Transcend, increasing your Vida Máxima by 10% durante 6s. While Transcended, allied Campeões within 1,200 units of you heal for 2.5% of your Vida Máxima per segundo over the duration. (60s Tempo de Recarga) If you're a À distância Campeão, heals granted are reduced by 50%.\nThis item boosts your durability by granting extra Vida Máxima, Armadura, and Aceleração de Habilidade. Its passive causes you to transcend after casting your ultimate: you temporarily raise your Vida Máxima, and nearby allies are healed based on that boosted Vida. The healing is reduced for À distância Campeões. A strong pick for frontliners and supports who want to survive engages while providing teamwide sustain during fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1760128035_radiant-virtue.webp"
        ))
        add(WildRiftItem(
            id = "abyssal_mask_defense",
            name = "Máscara Abisal",
            nameEn = "Abyssal Mask",
            namePt = "Máscara Abissal",
            category = "Defensa",
            goldCost = 3000,
            stats = "+400 Vida Máxima • +55 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +55 Magic Resistance • +10 Ability Haste",
            statsPt = "+400 Vida Máxima • +55 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Reduces the Resistencia Mágica of nearby enemies and increases yours\nUnmake: Curse campeones enemigos within 600 units, reducing their Resistencia Mágica by 5 plus 1.2% Adicional, up to 25 Resistencia Mágica. For each campeón enemigo cursed, gain 9 Adicional Resistencia Mágica.\nThis item is a solid anti-magic pickup: it boosts your Resistencia Mágica while reducing the Resistencia Mágica of nearby enemies, making them easier to shred with spell damage. Great for tanks and frontliners who need to both soak Daño Mágico and amplify their team’s ability to take down AP threats and durable targets.",
            passiveEn = "Reduces the Resistencia Mágica of nearby enemies and increases yours\nUnmake: Curse campeones enemigos within 600 units, reducing their Resistencia Mágica by 5 plus 1.2% Adicional, up to 25 Resistencia Mágica. For each campeón enemigo cursed, gain 9 Adicional Resistencia Mágica.\nThis item is a solid anti-magic pickup: it boosts your Resistencia Mágica while reducing the Resistencia Mágica of nearby enemies, making them easier to shred with spell damage. Great for tanks and frontliners who need to both soak Daño Mágico and amplify their team’s ability to take down AP threats and durable targets.",
            passivePt = "Reduces the Resistência Mágica of nearby enemies and increases yours\nUnmake: Curse campeões inimigos within 600 units, reducing their Resistência Mágica by 5 plus 1.2% Adicional, up to 25 Resistência Mágica. For each campeão inimigo cursed, gain 9 Adicional Resistência Mágica.\nThis item is a solid anti-magic pickup: it boosts your Resistência Mágica while reducing the Resistência Mágica of nearby enemies, making them easier to shred with spell damage. Great for tanks and frontliners who need to both soak Dano Mágico and amplify their team’s ability to take down AP threats and durable targets.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767912720_abyssal-mask.webp"
        ))
        add(WildRiftItem(
            id = "hollow_radiance_defense",
            name = "Resplandor Hueco",
            nameEn = "Hollow Radiance",
            namePt = "Resplendor Vazio",
            category = "Defensa",
            goldCost = 2800,
            stats = "+400 Vida Máxima • +40 Resistencia Mágica • +15 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +40 Magic Resistance • +15 Ability Haste",
            statsPt = "+400 Vida Máxima • +40 Resistência Mágica • +15 Aceleração de Habilidade",
            passive = "Immolate: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nDesolate: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional in an area around them.\n\n💡 Consejos del Coach: Este objeto convierte you into a steady source of pressure in fights: while engaged, it emits an area Daño Mágico aura that helps clear waves and punish nearby small targets. On killing a neutral or enemy, it detonates for area damage, making it great for fast clears and threat creation when entering skirmishes. Perfect for tanks and frontline bruisers who need to hold the center of fights and force opponents into mistakes.",
            passiveEn = "Immolate: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nDesolate: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional in an area around them.\n\n💡 Coach Tips: Este objeto convierte you into a steady source of pressure in fights: while engaged, it emits an area Daño Mágico aura that helps clear waves and punish nearby small targets. On killing a neutral or enemy, it detonates for area damage, making it great for fast clears and threat creation when entering skirmishes. Perfect for tanks and frontline bruisers who need to hold the center of fights and force opponents into mistakes.",
            passivePt = "Immolate: While En combate, deal Dano Mágico equal to 20–30 plus 1% of Adicional per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstros and 200% damage against Tropas.\nDesolate: Killing a neutral monster or an enemy deals Dano Mágico equal to 30 plus 2% of Adicional in an area around them.\n\n💡 Dicas do Coach: Este item converte you into a steady source of pressure in fights: while engaged, it emits an area Dano Mágico aura that helps clear waves and punish nearby small targets. On killing a neutral or enemy, it detonates for area damage, making it great for fast clears and threat creation when entering skirmishes. Perfect for tanks and frontline bruisers who need to hold the center of fights and force opponents into mistakes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767913515_6664_11zon.webp"
        ))
        add(WildRiftItem(
            id = "knight_s_vow_support",
            name = "Promesa de Caballero",
            nameEn = "Promesa de Caballero",
            namePt = "Promesa de Caballero",
            category = "Defensa",
            goldCost = 2500,
            stats = "+400 Vida Máxima • +40 Armadura • +10 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +40 Armor • +10 Ability Haste",
            statsPt = "+400 Vida Máxima • +40 Armadura • +10 Aceleração de Habilidade",
            passive = "Pledge: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nSacrifice: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            passiveEn = "Pledge: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nSacrifice: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            passivePt = "Pledge: While En combate, deal Dano Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstros and 200% damage against Tropas.\nSacrifice: Killing a neutral monster or an enemy deals Dano Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767914013_3109_11zon.webp"
        ))
        add(WildRiftItem(
            id = "overlord_s_bloodmail_defense",
            name = "Armadura Sangrienta del Señor Supremo",
            nameEn = "Armadura Sangrienta del Señor Supremo",
            namePt = "Armadura Sangrienta del Señor Supremo",
            category = "Defensa",
            goldCost = 3200,
            stats = "+450 Vida Máxima • +30 Daño de Ataque",
            statsEn = "+450 Max Health • +30 Attack Damage",
            statsPt = "+450 Vida Máxima • +30 Dano de Ataque",
            passive = "Gain Daño de Ataque when losing Vida\nTyranny: Gain Daño de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Daño de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            passiveEn = "Gain Daño de Ataque when losing Vida\nTyranny: Gain Daño de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Daño de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            passivePt = "Gain Dano de Ataque when losing Vida\nTyranny: Gain Dano de Ataque equal to 2.5% of your Adicional Vida.\nRetribution: Gain up to 9% increased Dano de Ataque based on your missing Vida. Maximum Retribution Adicional while below 30% Vida.\nThis item converts Adicional Vida into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack Vida and embrace high-risk, high-reward skirmishes: the more Adicional Vida you have, the stronger your raw attacks become, and when you fight at low Vida you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630274_overlords-bloodmail.webp"
        ))
        add(WildRiftItem(
            id = "unending_despair_defense",
            name = "Desesperanza Infinita",
            nameEn = "Unending Despair",
            namePt = "Desespero Eterno",
            category = "Defensa",
            goldCost = 3000,
            stats = "+200 Vida Máxima • +45 Armadura • +45 Resistencia Mágica",
            statsEn = "+200 Max Health • +45 Armor • +45 Magic Resistance",
            statsPt = "+200 Vida Máxima • +45 Armadura • +45 Resistência Mágica",
            passive = "Increases tanks' sustain in teamfights\nAnguish: Every 4 segundo(s) while En combate with a Campeón, deal 3% of your Vida Máxima as Daño Mágico to nearby Campeones and heal for 250% of the damage dealt. Anguish is unaffected by Item Aceleración de Habilidad.\n\n💡 Consejos del Coach: Este objeto convierte you into a self-sustaining frontline: while fighting you periodically deal Daño Mágico around you and heal for a portion of that damage. Great for tanks and frontliners — it helps you soak focus, remain in the heart of fights longer, and excel in extended team engagements. Less effective in very short burst trades or on Campeones that avoid standing in the center of combat.",
            passiveEn = "Increases tanks' sustain in teamfights\nAnguish: Every 4 segundo(s) while En combate with a Campeón, deal 3% of your Vida Máxima as Daño Mágico to nearby Campeones and heal for 250% of the damage dealt. Anguish is unaffected by Item Aceleración de Habilidad.\n\n💡 Coach Tips: Este objeto convierte you into a self-sustaining frontline: while fighting you periodically deal Daño Mágico around you and heal for a portion of that damage. Great for tanks and frontliners — it helps you soak focus, remain in the heart of fights longer, and excel in extended team engagements. Less effective in very short burst trades or on Campeones that avoid standing in the center of combat.",
            passivePt = "Increases tanks' sustain in teamfights\nAnguish: Every 4 segundo(s) while En combate with a Campeão, deal 3% of your Vida Máxima as Dano Mágico to nearby Campeões and heal for 250% of the damage dealt. Anguish is unaffected by Item Aceleração de Habilidade.\n\n💡 Dicas do Coach: Este item converte you into a self-sustaining frontline: while fighting you periodically deal Dano Mágico around you and heal for a portion of that damage. Great for tanks and frontliners — it helps you soak focus, remain in the heart of fights longer, and excel in extended team engagements. Less effective in very short burst trades or on Campeões that avoid standing in the center of combat.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630350_unending-despair.webp"
        ))
        add(WildRiftItem(
            id = "banshee_s_veil_defense",
            name = "Velo de la Banshee",
            nameEn = "Velo de la Banshee",
            namePt = "Velo de la Banshee",
            category = "Defensa",
            goldCost = 3000,
            stats = "+105 Poder de Habilidad • +40 Resistencia Mágica",
            statsEn = "+105 Ability Power • +40 Magic Resistance",
            statsPt = "+105 Poder de Habilidade • +40 Resistência Mágica",
            passive = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto otorga strong protection against Daño Mágico while granting a spell Escudo that blocks the next hostile ability. Es especialmente efectivo contra Campeones who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            passiveEn = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Enfriamiento)\n\n💡 Coach Tips: Este objeto otorga strong protection against Daño Mágico while granting a spell Escudo that blocks the next hostile ability. Es especialmente efectivo contra Campeones who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            passivePt = "Blocks an enemy ability\nAnnul: Grants a spell Escudo that blocks the next hostile ability. (30s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item concede strong protection against Dano Mágico while granting a spell Escudo that blocks the next hostile ability. É especialmente efetivo contra Campeões who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192846_3102_11zon.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            nameEn = "Zhonya's Hourglass",
            namePt = "Ampulheta de Zhonya",
            category = "Defensa",
            goldCost = 3300,
            stats = "+40 Armadura • +110 Poder de Habilidad",
            statsEn = "+40 Armor • +110 Ability Power",
            statsPt = "+40 Armadura • +110 Poder de Habilidade",
            passive = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passiveEn = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passivePt = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Tempo de Recarga)\nThis item combines high Poder de Habilidade with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "gargoyle_stoneplate_active",
            name = "Protector Pétreo",
            nameEn = "Gargoyle Stoneplate",
            namePt = "Placa Gargolítica",
            category = "Defensa",
            goldCost = 2900,
            stats = "+200 Vida Máxima • +45 Armadura • +45 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +45 Armor • +45 Magic Resistance • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +45 Armadura • +45 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Enfriamiento)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            passiveEn = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Enfriamiento)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            passivePt = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Tempo de Recarga)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp"
        ))
        add(WildRiftItem(
            id = "bulwark_of_the_mountain_support",
            name = "Baluarte de la Montaña",
            nameEn = "Baluarte de la Montaña",
            namePt = "Baluarte de la Montaña",
            category = "Soporte",
            goldCost = 0,
            stats = "+175 Vida Máxima • +10 Aceleración de Habilidad",
            statsEn = "+175 Max Health • +10 Ability Haste",
            statsPt = "+175 Vida Máxima • +10 Aceleração de Habilidade",
            passive = "Kill Súbditos to earn Adicional gold\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Daño de Ataque, or 4 Poder de Habilidad (Adaptive); up to 250 Vida and 20 Daño de Ataque or 40 Poder de Habilidad (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidad de Movimiento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players and grants passive gold income every 60 segundos along with Soulforce stacks that boost your Vida, Daño de Ataque, or Poder de Habilidad. At 10 stacks, you gain a significant adaptive stat Adicional. While it reduces gold from killing Súbditos and Monstruos, it accelerates your team’s economic pace. An additional effect deals extra damage to revealed Sight Wards, making it easier to clear vision and maintain map control.",
            passiveEn = "Kill Súbditos to earn Adicional gold\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Daño de Ataque, or 4 Poder de Habilidad (Adaptive); up to 250 Vida and 20 Daño de Ataque or 40 Poder de Habilidad (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidad de Movimiento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players and grants passive gold income every 60 segundos along with Soulforce stacks that boost your Vida, Daño de Ataque, or Poder de Habilidad. At 10 stacks, you gain a significant adaptive stat Adicional. While it reduces gold from killing Súbditos and Monstruos, it accelerates your team’s economic pace. An additional effect deals extra damage to revealed Sight Wards, making it easier to clear vision and maintain map control.",
            passivePt = "Kill Tropas to earn Adicional gold\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Dano de Ataque, or 4 Poder de Habilidade (Adaptive); up to 250 Vida and 20 Dano de Ataque or 40 Poder de Habilidade (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidade de Movimento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players and grants passive gold income every 60 segundos along with Soulforce stacks that boost your Vida, Dano de Ataque, or Poder de Habilidade. At 10 stacks, you gain a significant adaptive stat Adicional. While it reduces gold from killing Tropas and Monstros, it accelerates your team’s economic pace. An additional effect deals extra damage to revealed Sight Wards, making it easier to clear vision and maintain map control.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389518_bulwark-of-the-mountain.webp"
        ))
        add(WildRiftItem(
            id = "black_mist_scythe_support",
            name = "Guadaña de Niebla Negra",
            nameEn = "Guadaña de Niebla Negra",
            namePt = "Guadaña de Niebla Negra",
            category = "Soporte",
            goldCost = 0,
            stats = "+10 Aceleración de Habilidad",
            statsEn = "+10 Ability Haste",
            statsPt = "+10 Aceleração de Habilidade",
            passive = "Versatile: Gain 14 Daño de Ataque or 28 Poder de Habilidad (Adaptive).\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Daño de Ataque, or 4 Poder de Habilidad (Adaptive); up to 250 Vida and 20 Daño de Ataque, or 40 Poder de Habilidad (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidad de Movimiento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players, granting passive bonuses to gold and stats. It reduces your gold from killing Súbditos and Monstruos but provides 75 gold and 1 Soulforce stack every 60 segundos. Each Soulforce stack adaptively grants Vida, Daño de Ataque, or Poder de Habilidad, and at 10 stacks you gain a significant Adicional to one of these stats. The item also increases your effectiveness in clearing vision by dealing extra damage to revealed enemy wards.  Ideal for map-control–focused supports who want to help their team without worrying about farming; you’ll steadily generate resources and strengthen your utility for both protect and peel.",
            passiveEn = "Versatile: Gain 14 Daño de Ataque or 28 Poder de Habilidad (Adaptive).\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Daño de Ataque, or 4 Poder de Habilidad (Adaptive); up to 250 Vida and 20 Daño de Ataque, or 40 Poder de Habilidad (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidad de Movimiento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players, granting passive bonuses to gold and stats. It reduces your gold from killing Súbditos and Monstruos but provides 75 gold and 1 Soulforce stack every 60 segundos. Each Soulforce stack adaptively grants Vida, Daño de Ataque, or Poder de Habilidad, and at 10 stacks you gain a significant Adicional to one of these stats. The item also increases your effectiveness in clearing vision by dealing extra damage to revealed enemy wards.  Ideal for map-control–focused supports who want to help their team without worrying about farming; you’ll steadily generate resources and strengthen your utility for both protect and peel.",
            passivePt = "Versatile: Gain 14 Dano de Ataque or 28 Poder de Habilidade (Adaptive).\nSoulcast: Every 60 segundos, gains 75 gold, 25 Vida and 2 Dano de Ataque, or 4 Poder de Habilidade (Adaptive); up to 250 Vida and 20 Dano de Ataque, or 40 Poder de Habilidade (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When fuera de combate, gain 10% Velocidade de Movimento when you move toward your Perfect Partner. If you're more than 2,500 units apart, this Adicional increases to 30%.\nThis item is designed for support players, granting passive bonuses to gold and stats. It reduces your gold from killing Tropas and Monstros but provides 75 gold and 1 Soulforce stack every 60 segundos. Each Soulforce stack adaptively grants Vida, Dano de Ataque, or Poder de Habilidade, and at 10 stacks you gain a significant Adicional to one of these stats. The item also increases your effectiveness in clearing vision by dealing extra damage to revealed enemy wards.  Ideal for map-control–focused supports who want to help their team without worrying about farming; you’ll steadily generate resources and strengthen your utility for both protect and peel.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389555_black-mist-scythe.webp"
        ))
        add(WildRiftItem(
            id = "morellonomicon_support",
            name = "Morellonomicón",
            nameEn = "Morellonomicon",
            namePt = "Morellonomicon",
            category = "Soporte",
            goldCost = 2650,
            stats = "+300 Vida Máxima • +75 Poder de Habilidad • +15 Aceleración de Habilidad",
            statsEn = "+300 Max Health • +75 Ability Power • +15 Ability Haste",
            statsPt = "+300 Vida Máxima • +75 Poder de Habilidade • +15 Aceleração de Habilidade",
            passive = "Daño Mágico reduces enemy healing\nAffliction: Dealing Daño Mágico to campeones enemigos inflicts 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeones with strong healing and sustain. Any Daño Mágico you deal applies Heridas Graves, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            passiveEn = "Daño Mágico reduces enemy healing\nAffliction: Dealing Daño Mágico to campeones enemigos inflicts 50% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeones with strong healing and sustain. Any Daño Mágico you deal applies Heridas Graves, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            passivePt = "Dano Mágico reduces enemy healing\nAffliction: Dealing Dano Mágico to campeões inimigos inflicts 50% Feridas Dolorosas for 3 segundos.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.\nThis item is designed to counter Campeões with strong healing and sustain. Any Dano Mágico you deal applies Feridas Dolorosas, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388237_morellonomicon.webp"
        ))
        add(WildRiftItem(
            id = "ardent_censer_support",
            name = "Pebetero Ardiente",
            nameEn = "Ardent Censer",
            namePt = "Turíbulo Ardente",
            category = "Soporte",
            goldCost = 2700,
            stats = "+250 Vida Máxima • +45 Poder de Habilidad • +50% Regeneración de Maná • +10 Aceleración de Habilidad • +5% Heal and Shield Strength • +5% Velocidad de Movimiento.",
            statsEn = "+250 Max Health • +45 Ability Power • +50% Mana Regen • +10 Ability Haste • +5% Heal and Shield Strength • +5% Move Speed.",
            statsPt = "+250 Vida Máxima • +45 Poder de Habilidade • +50% Regeneração de Mana • +10 Aceleração de Habilidade • +5% Heal and Shield Strength • +5% Velocidade de Movimento.",
            passive = "+5% Heal and Escudo Strength\nCenser: When you heal or Escudo, an allied Campeón other than yourself, they gain 15-34% Velocidad de Ataque and their Los ataques infligen 16-22 Adicional Daño Mágico. for 6 segundos. This damage can asestar un Golpe Crítico.\nThis item enhances your heals and shields, granting shielded allies increased Velocidad de Ataque and Adicional Daño Mágico on their attacks for a short duration. Perfect for enchanter supports who want to protect and empower their carries when it matters most.",
            passiveEn = "+5% Heal and Escudo Strength\nCenser: When you heal or Escudo, an allied Campeón other than yourself, they gain 15-34% Velocidad de Ataque and their Los ataques infligen 16-22 Adicional Daño Mágico. for 6 segundos. This damage can asestar un Golpe Crítico.\nThis item enhances your heals and shields, granting shielded allies increased Velocidad de Ataque and Adicional Daño Mágico on their attacks for a short duration. Perfect for enchanter supports who want to protect and empower their carries when it matters most.",
            passivePt = "+5% Heal and Escudo Strength\nCenser: When you heal or Escudo, an allied Campeão other than yourself, they gain 15-34% Velocidade de Ataque and their Los ataques infligen 16-22 Adicional Dano Mágico. for 6 segundos. This damage can asestar un Acerto Crítico.\nThis item enhances your heals and shields, granting shielded allies increased Velocidade de Ataque and Adicional Dano Mágico on their attacks for a short duration. Perfect for enchanter supports who want to protect and empower their carries when it matters most.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388425_ardent-censer.webp"
        ))
        add(WildRiftItem(
            id = "harmonic_echo_support",
            name = "Eco Armónico",
            nameEn = "Harmonic Echo",
            namePt = "Eco Harmônico",
            category = "Soporte",
            goldCost = 2800,
            stats = "+100 Vida Máxima • +50 Poder de Habilidad • +300 Maná Máximo • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+100 Max Health • +50 Ability Power • +300 Max Mana • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+100 Vida Máxima • +50 Poder de Habilidade • +300 Mana Máxima • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "+300 Maná Máximo\n+5% Heal and Escudo Strength\nHarmonic Echo: Moving and casting abilities builds Harmony. At 100 Harmony your next healing or shielding ability on an ally restore an additional equal to (100-160 () + 15% AP) Vida. If the target has less than 30% Vida, heal effectiveness is increased to 130% of the original.\nThis item greatly enhances your healing and shielding capabilities. Moving and casting abilities builds Harmony, empowering your next heal or Escudo with additional healing, while allies at low Vida receive an even stronger recovery. It is an excellent choice for enchanter supports who focus on keeping their team alive and saving allies during critical moments.",
            passiveEn = "+300 Maná Máximo\n+5% Heal and Escudo Strength\nHarmonic Echo: Moving and casting abilities builds Harmony. At 100 Harmony your next healing or shielding ability on an ally restore an additional equal to (100-160 () + 15% AP) Vida. If the target has less than 30% Vida, heal effectiveness is increased to 130% of the original.\nThis item greatly enhances your healing and shielding capabilities. Moving and casting abilities builds Harmony, empowering your next heal or Escudo with additional healing, while allies at low Vida receive an even stronger recovery. It is an excellent choice for enchanter supports who focus on keeping their team alive and saving allies during critical moments.",
            passivePt = "+300 Mana Máxima\n+5% Heal and Escudo Strength\nHarmonic Echo: Moving and casting abilities builds Harmony. At 100 Harmony your next healing or shielding ability on an ally restore an additional equal to (100-160 () + 15% AP) Vida. If the target has less than 30% Vida, heal effectiveness is increased to 130% of the original.\nThis item greatly enhances your healing and shielding capabilities. Moving and casting abilities builds Harmony, empowering your next heal or Escudo with additional healing, while allies at low Vida receive an even stronger recovery. It is an excellent choice for enchanter supports who focus on keeping their team alive and saving allies during critical moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388518_yordle-harmonic-echo.webp"
        ))
        add(WildRiftItem(
            id = "staff_of_flowing_water_support",
            name = "Báculo de Agua Fluyente",
            nameEn = "Staff of Flowing Water",
            namePt = "Cajado Aquafluxo",
            category = "Soporte",
            goldCost = 2500,
            stats = "+100 Vida Máxima • +50 Poder de Habilidad • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+100 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+100 Vida Máxima • +50 Poder de Habilidade • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "Enhance allies Poder de Habilidad and Aceleración de Habilidad\n+5% Heal and Escudo Strength\nRapids: Healing or shielding an ally grants you both +15  Aceleración de Habilidad and 30-50  (based on target's level) Poder de Habilidad for 6 segundos.\n\n💡 Consejos del Coach: Este objeto es ideal para Campeones who rely on healing or shielding abilities to support their team. It provides bonuses to maximum Vida, Poder de Habilidad, Aceleración de Habilidad, and amplifies healing and shielding effects. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Rapids\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you heal or Escudo an ally, granting both you and your ally bonuses to Aceleración de Habilidad and Poder de Habilidad for 6 segundos. This item is especially useful for Campeones who actively support their team, such as Soraka, Nami, or Lulu.",
            passiveEn = "Enhance allies Poder de Habilidad and Aceleración de Habilidad\n+5% Heal and Escudo Strength\nRapids: Healing or shielding an ally grants you both +15  Aceleración de Habilidad and 30-50  (based on target's level) Poder de Habilidad for 6 segundos.\n\n💡 Coach Tips: Este objeto es ideal para Campeones who rely on healing or shielding abilities to support their team. It provides bonuses to maximum Vida, Poder de Habilidad, Aceleración de Habilidad, and amplifies healing and shielding effects. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Rapids\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you heal or Escudo an ally, granting both you and your ally bonuses to Aceleración de Habilidad and Poder de Habilidad for 6 segundos. This item is especially useful for Campeones who actively support their team, such as Soraka, Nami, or Lulu.",
            passivePt = "Enhance allies Poder de Habilidade and Aceleração de Habilidade\n+5% Heal and Escudo Strength\nRapids: Healing or shielding an ally grants you both +15  Aceleração de Habilidade and 30-50  (based on target's level) Poder de Habilidade for 6 segundos.\n\n💡 Dicas do Coach: Este item é ideal para Campeões who rely on healing or shielding abilities to support their team. It provides bonuses to maximum Vida, Poder de Habilidade, Aceleração de Habilidade, and amplifies healing and shielding effects. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Rapids\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you heal or Escudo an ally, granting both you and your ally bonuses to Aceleração de Habilidade and Poder de Habilidade for 6 segundos. This item is especially useful for Campeões who actively support their team, such as Soraka, Nami, or Lulu.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388553_staff-of-flowing-water.webp"
        ))
        add(WildRiftItem(
            id = "oceanid_s_trident_support",
            name = "Tridente del Oceánida",
            nameEn = "Oceanid's Trident",
            namePt = "Tridente do Oceanida",
            category = "Soporte",
            goldCost = 2600,
            stats = "+200 Vida Máxima • +80 Poder de Habilidad • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +80 Ability Power • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +80 Poder de Habilidade • +10 Aceleração de Habilidade",
            passive = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeón enemigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeones. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            passiveEn = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeón enemigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeones. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            passivePt = "Anti-Shielding\nLethal Weapon: Dealing ability damage to an campeão inimigo reduces any shields they gain for 3 segundos. Area of effect abilities apply (5% of Adicional AP + 25)% Escudo reduction, capped at 45%; while single target abilities apply (5% of Adicional AP + 40)% Escudo reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\nThis item is designed to counter Escudo-heavy Campeões. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against Escudo-reliant compositions, allowing your team to break through enemy Defensas and eliminate priority targets more effectively.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388583_oceanids-trident.webp"
        ))
        add(WildRiftItem(
            id = "imperial_mandate_support",
            name = "Mandato Imperial",
            nameEn = "Imperial Mandate",
            namePt = "Mandato Imperial",
            category = "Soporte",
            goldCost = 2500,
            stats = "+200 Vida Máxima • +50 Poder de Habilidad • +20 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +50 Ability Power • +20 Ability Haste",
            statsPt = "+200 Vida Máxima • +50 Poder de Habilidade • +20 Aceleração de Habilidade",
            passive = "Crowd control grants additional ally damage\nCoordinated Fire: Abilities that Ralentización or Immobilize a Campeón deal 47-75 Adicional Daño Mágico and marks them for 4 segundos (6 segundos Enfriamiento per campeón enemigo). Allied Campeón damage detonates the mark, dealing an additional 94-150 Daño Mágico (based on ally level) and granting you both 20% Velocidad de Movimiento, for 2 segundos.\n\n💡 Consejos del Coach: Este objeto es ideal para support Campeones who have abilities that Ralentización or immobilize enemies, allowing you to activate effects for your team. It provides bonuses to maximum Vida, Poder de Habilidad, and Aceleración de Habilidad, helping you deal damage while also enhancing teamwork with your allies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Coordinated Fire\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you Ralentización or immobilize an enemy, dealing Adicional Daño Mágico and marking them for 4 segundos. When an allied Campeón damages the marked target, it detonates the mark, dealing additional Daño Mágico and granting both you and your ally 20% Adicional Velocidad de Movimiento for 2 segundos. This item is especially useful for Campeones with crowd control abilities.",
            passiveEn = "Crowd control grants additional ally damage\nCoordinated Fire: Abilities that Ralentización or Immobilize a Campeón deal 47-75 Adicional Daño Mágico and marks them for 4 segundos (6 segundos Enfriamiento per campeón enemigo). Allied Campeón damage detonates the mark, dealing an additional 94-150 Daño Mágico (based on ally level) and granting you both 20% Velocidad de Movimiento, for 2 segundos.\n\n💡 Coach Tips: Este objeto es ideal para support Campeones who have abilities that Ralentización or immobilize enemies, allowing you to activate effects for your team. It provides bonuses to maximum Vida, Poder de Habilidad, and Aceleración de Habilidad, helping you deal damage while also enhancing teamwork with your allies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Coordinated Fire\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you Ralentización or immobilize an enemy, dealing Adicional Daño Mágico and marking them for 4 segundos. When an allied Campeón damages the marked target, it detonates the mark, dealing additional Daño Mágico and granting both you and your ally 20% Adicional Velocidad de Movimiento for 2 segundos. This item is especially useful for Campeones with crowd control abilities.",
            passivePt = "Crowd control grants additional ally damage\nCoordinated Fire: Abilities that Ralentización or Immobilize a Campeão deal 47-75 Adicional Dano Mágico and marks them for 4 segundos (6 segundos Tempo de Recarga per campeão inimigo). Allied Campeão damage detonates the mark, dealing an additional 94-150 Dano Mágico (based on ally level) and granting you both 20% Velocidade de Movimento, for 2 segundos.\n\n💡 Dicas do Coach: Este item é ideal para support Campeões who have abilities that Ralentización or immobilize enemies, allowing you to activate effects for your team. It provides bonuses to maximum Vida, Poder de Habilidade, and Aceleração de Habilidade, helping you deal damage while also enhancing teamwork with your allies. The \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"Coordinated Fire\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" effect activates when you Ralentización or immobilize an enemy, dealing Adicional Dano Mágico and marking them for 4 segundos. When an allied Campeão damages the marked target, it detonates the mark, dealing additional Dano Mágico and granting both you and your ally 20% Adicional Velocidade de Movimento for 2 segundos. This item is especially useful for Campeões with crowd control abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388613_imperial-mandate.webp"
        ))
        add(WildRiftItem(
            id = "zeke_s_convergence_support",
            name = "Convergencia de Zeke",
            nameEn = "Zeke's Convergence",
            namePt = "Convergência de Zeke",
            category = "Soporte",
            goldCost = 2700,
            stats = "+40 Armadura • +350 Vida Máxima • +150 Maná Máximo • +15 Aceleración de Habilidad",
            statsEn = "+40 Armor • +350 Max Health • +150 Max Mana • +15 Ability Haste",
            statsPt = "+40 Armadura • +350 Vida Máxima • +150 Mana Máxima • +15 Aceleração de Habilidade",
            passive = "Boosts allies Daño de Ataque\n+150 Maná Máximo\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeones on the trail gain 40 Adicional Velocidad de Movimiento for 1 segundo. (30s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para tanky support Campeones who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleración de Habilidad. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidad de Movimiento to allies. During the effect, the attacks of a nearby marked ally deal additional Daño Mágico, giving your team a powerful advantage in teamfights.",
            passiveEn = "Boosts allies Daño de Ataque\n+150 Maná Máximo\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeones on the trail gain 40 Adicional Velocidad de Movimiento for 1 segundo. (30s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para tanky support Campeones who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleración de Habilidad. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidad de Movimiento to allies. During the effect, the attacks of a nearby marked ally deal additional Daño Mágico, giving your team a powerful advantage in teamfights.",
            passivePt = "Boosts allies Dano de Ataque\n+150 Mana Máxima\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 segundos. Your blizzard deals a maximum of 320–600 damage, Ralentiza enemies by 25% and leaves a trail behind you. Allied Campeões on the trail gain 40 Adicional Velocidade de Movimento for 1 segundo. (30s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para tanky support Campeões who initiate fights and provide frontline crowd control. It grants Armadura, Vida, Maná, and Aceleração de Habilidade. When you cast your ultimate, an icy blizzard surrounds you, Infligir daño and slowing enemies, while leaving a trail that grants Adicional Velocidade de Movimento to allies. During the effect, the attacks of a nearby marked ally deal additional Dano Mágico, giving your team a powerful advantage in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp"
        ))
        add(WildRiftItem(
            id = "frozen_heart_support",
            name = "Corazón de Hielo",
            nameEn = "Frozen Heart",
            namePt = "Coração Congelado",
            category = "Soporte",
            goldCost = 2650,
            stats = "+80 Armadura • +250 Maná Máximo • +20 Aceleración de Habilidad",
            statsEn = "+80 Armor • +250 Max Mana • +20 Ability Haste",
            statsPt = "+80 Armadura • +250 Mana Máxima • +20 Aceleração de Habilidade",
            passive = "+250 Maná Máximo\nWinter's Caress: ataques básicos and Daño Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeón enemigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidad de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidad de Ataque reduction. Each individual ability has a 3 segundos Enfriamiento on applying Chill stacks.\nThis item is ideal for tanks and support Campeones who need to Ralentización enemy Velocidad de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleración de Habilidad. The “Winter’s Caress” passive applies up to four stacks of Chill on campeones enemigos through your ataques básicos, abilities, or any Daño Mágico they take—each stack Ralentiza their Velocidad de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            passiveEn = "+250 Maná Máximo\nWinter's Caress: ataques básicos and Daño Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeón enemigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidad de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidad de Ataque reduction. Each individual ability has a 3 segundos Enfriamiento on applying Chill stacks.\nThis item is ideal for tanks and support Campeones who need to Ralentización enemy Velocidad de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleración de Habilidad. The “Winter’s Caress” passive applies up to four stacks of Chill on campeones enemigos through your ataques básicos, abilities, or any Daño Mágico they take—each stack Ralentiza their Velocidad de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            passivePt = "+250 Mana Máxima\nWinter's Caress: ataques básicos and Dano Mágico caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the campeão inimigo for 3 segundos. Each stack of Chill Ralentiza enemy Velocidade de Ataque by 9%, up to a maximum of 4 stacks or 36% Velocidade de Ataque reduction. Each individual ability has a 3 segundos Tempo de Recarga on applying Chill stacks.\nThis item is ideal for tanks and support Campeões who need to Ralentización enemy Velocidade de Ataque and maintain a healthy Maná pool. It provides substantial bonuses to Armadura, Maná, and Aceleração de Habilidade. The “Winter’s Caress” passive applies up to four stacks of Chill on campeões inimigos through your ataques básicos, abilities, or any Dano Mágico they take—each stack Ralentiza their Velocidade de Ataque by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389159_frozen-heart.webp"
        ))
        add(WildRiftItem(
            id = "dawnshroud_support",
            name = "Manto del Amanecer",
            nameEn = "Dawnshroud",
            namePt = "Manto da Alvorada",
            category = "Soporte",
            goldCost = 2700,
            stats = "+250 Vida Máxima • +50 Armadura • +30 Resistencia Mágica",
            statsEn = "+250 Max Health • +50 Armor • +30 Magic Resistance",
            statsPt = "+250 Vida Máxima • +50 Armadura • +30 Resistência Mágica",
            passive = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeón Campeón or are immobilized within 400 units of an campeón enemigo, reveal all nearby campeones enemigos for 3 segundos, deal Daño Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistencia Mágica (3s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es excelente para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeones, deals an explosive burst of Daño Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            passiveEn = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeón Campeón or are immobilized within 400 units of an campeón enemigo, reveal all nearby campeones enemigos for 3 segundos, deal Daño Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistencia Mágica (3s Enfriamiento)\n\n💡 Coach Tips: Este objeto es excelente para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeones, deals an explosive burst of Daño Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            passivePt = "Immobilize effects damage and reveal around you\nDawnbringer: When you immobilize a Campeão Campeão or are immobilized within 400 units of an campeão inimigo, reveal all nearby campeões inimigos for 3 segundos, deal Dano Mágico equal to 40 + 2.5% bonusand gain 20% Armadura and Resistência Mágica (3s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ótimo para tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby Campeões, deals an explosive burst of Dano Mágico, and briefly boosts your Defensas. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp"
        ))
        add(WildRiftItem(
            id = "yordle_trap_support",
            name = "Trampa Yordle",
            nameEn = "Trampa Yordle",
            namePt = "Trampa Yordle",
            category = "Soporte",
            goldCost = 2600,
            stats = "+350 Vida Máxima • +40 Armadura • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Armor • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +40 Armadura • +15 Aceleração de Habilidade",
            passive = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidad de Movimiento for 3 segundo(s) and mark the target, reducing their Armadura and Resistencia Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeones with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidad de Movimiento while marking the target, reducing their Armadura and Resistencia Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            passiveEn = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidad de Movimiento for 3 segundo(s) and mark the target, reducing their Armadura and Resistencia Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeones with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidad de Movimiento while marking the target, reducing their Armadura and Resistencia Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            passivePt = "Catcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Velocidade de Movimento for 3 segundo(s) and mark the target, reducing their Armadura and Resistência Mágica by 5–12 for 8 segundo(s). If the target dies while they are marked, their death grants 100–140 Adicional gold () that will be evenly shared among you and nearby allies.\nThis Adicional gold can only be obtained once every 10 segundo(s).\nThis item is designed for Campeões with displacement abilities and strong engage tools. Successfully displacing an enemy grants you Adicional Velocidade de Movimento while marking the target, reducing their Armadura and Resistência Mágica to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive Adicional gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389328_yordle-trap.webp"
        ))
        add(WildRiftItem(
            id = "knight_s_vow_support",
            name = "Promesa de Caballero",
            nameEn = "Promesa de Caballero",
            namePt = "Promesa de Caballero",
            category = "Soporte",
            goldCost = 2500,
            stats = "+400 Vida Máxima • +40 Armadura • +10 Aceleración de Habilidad",
            statsEn = "+400 Max Health • +40 Armor • +10 Ability Haste",
            statsPt = "+400 Vida Máxima • +40 Armadura • +10 Aceleração de Habilidade",
            passive = "Pledge: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nSacrifice: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            passiveEn = "Pledge: While En combate, deal Daño Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstruos and 200% damage against Súbditos.\nSacrifice: Killing a neutral monster or an enemy deals Daño Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            passivePt = "Pledge: While En combate, deal Dano Mágico equal to 20–30 plus 1% of Adicional Vida per segundo for 5 segundo(s) to nearby enemies. Deals 125% damage against Monstros and 200% damage against Tropas.\nSacrifice: Killing a neutral monster or an enemy deals Dano Mágico equal to 30 plus 2% of Adicional Vida in an area around them.\nThis item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767914013_3109_11zon.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            nameEn = "Redemption",
            namePt = "Redenção",
            category = "Soporte",
            goldCost = 2600,
            stats = "+150 Vida Máxima • +50 Poder de Habilidad • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+150 Vida Máxima • +50 Poder de Habilidade • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passiveEn = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passivePt = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Dano Verdadeiro to campeões inimigos. (60s Tempo de Recarga)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Dano Verdadeiro to campeões inimigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "mikael_s_blessing_active",
            name = "Bendición de Mikael",
            nameEn = "Mikael's Blessing",
            namePt = "Benção de Mikael",
            category = "Soporte",
            goldCost = 2500,
            stats = "+300 Vida Máxima • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +9% Heal and Shield Strength",
            statsEn = "+300 Max Health • +50% Mana Regen • +15 Ability Haste • +9% Heal and Shield Strength",
            statsPt = "+300 Vida Máxima • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +9% Heal and Shield Strength",
            passive = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeón, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Enfriamiento)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            passiveEn = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeón, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Enfriamiento)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            passivePt = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeão, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Tempo de Recarga)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783571078_3222_11zon.webp"
        ))
        add(WildRiftItem(
            id = "locket_of_the_iron_solari_active",
            name = "Relicario de los Solari de Hierro",
            nameEn = "Locket of the Iron Solari",
            namePt = "Medalhão dos Solari de Ferro",
            category = "Soporte",
            goldCost = 2600,
            stats = "+200 Vida Máxima • +30 Armadura • +30 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +30 Armor • +30 Magic Resistance • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +30 Armadura • +30 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeones that each absorbs 250-370 damage for 2.5 segundos. (60s Enfriamiento)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Consejos del Coach: Este objeto otorga powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            passiveEn = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeones that each absorbs 250-370 damage for 2.5 segundos. (60s Enfriamiento)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Coach Tips: Este objeto otorga powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            passivePt = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeões that each absorbs 250-370 damage for 2.5 segundos. (60s Tempo de Recarga)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Dicas do Coach: Este item concede powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389710_locket-enchant.webp"
        ))
        add(WildRiftItem(
            id = "shurelya_s_battlesong_active",
            name = "Canción de Batalla de Shurelya",
            nameEn = "Shurelya's Battlesong",
            namePt = "Hino Bélico de Shurelya",
            category = "Soporte",
            goldCost = 2600,
            stats = "+55 Poder de Habilidad • +50% Regeneración de Manáeration • +20 Aceleración de Habilidad • +5% Velocidad de Movimiento",
            statsEn = "+55 Ability Power • +50% Mana Regeneration • +20 Ability Haste • +5% Move Speed",
            statsPt = "+55 Poder de Habilidade • +50% Regeneração de Manaeration • +20 Aceleração de Habilidade • +5% Velocidade de Movimento",
            passive = "Grans allies Velocidad de Movimiento\nInspiring Speech (Active): Grant nearby allies Campeones 30% Velocidad de Movimiento for 4 segundos. (60s Enfriamiento)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeones a burst of Velocidad de Movimiento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeones who excel at controlling the pace of teamfights and enabling their teammates.",
            passiveEn = "Grans allies Velocidad de Movimiento\nInspiring Speech (Active): Grant nearby allies Campeones 30% Velocidad de Movimiento for 4 segundos. (60s Enfriamiento)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeones a burst of Velocidad de Movimiento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeones who excel at controlling the pace of teamfights and enabling their teammates.",
            passivePt = "Grans allies Velocidade de Movimento\nInspiring Speech (Active): Grant nearby allies Campeões 30% Velocidade de Movimento for 4 segundos. (60s Tempo de Recarga)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeões a burst of Velocidade de Movimento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeões who excel at controlling the pace of teamfights and enabling their teammates.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783187148_shurelyas-battlesong.webp"
        ))
        add(WildRiftItem(
            id = "quicksilver_sash_mid_tier",
            name = "Fajín de Mercurio",
            nameEn = "Quicksilver Enchant",
            namePt = "Encantamento de Mercúrio",
            category = "Encantamientos",
            goldCost = 1100,
            stats = "",
            statsEn = "",
            statsPt = "",
            passive = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            passiveEn = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            passivePt = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Tempo de Recarga)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389685_quicksilver-enchant.webp"
        ))
        add(WildRiftItem(
            id = "seeker_s_armguard_mid_tier",
            name = "Guardabrazo de la Buscadora",
            nameEn = "Guardabrazo de la Buscadora",
            namePt = "Guardabrazo de la Buscadora",
            category = "Encantamientos",
            goldCost = 1200,
            stats = "+20 Armadura • +35 Poder de Habilidad",
            statsEn = "+20 Armor • +35 Ability Power",
            statsPt = "+20 Armadura • +35 Poder de Habilidade",
            passive = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Enfriamiento)\nThis item combines Poder de Habilidad with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            passiveEn = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Enfriamiento)\nThis item combines Poder de Habilidad with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            passivePt = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Tempo de Recarga)\nThis item combines Poder de Habilidade with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2022-01/1641809649_seekers-armguard.png"
        ))
        add(WildRiftItem(
            id = "stridebreaker_active",
            name = "Rompeavances",
            nameEn = "Stridebreaker",
            namePt = "Quebrapassos",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+400 Vida Máxima • +40 Daño de Ataque • +25% Velocidad de Ataque",
            statsEn = "+400 Max Health • +40 Attack Damage • +25% Attack Speed",
            statsPt = "+400 Vida Máxima • +40 Dano de Ataque • +25% Velocidade de Ataque",
            passive = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Daño Físico to nearby enemies and slowing them by 40% durante 3s (25s Enfriamiento)\nStride (Passive): Gain 20 Velocidad de Movimiento for 2 segundo(s) when you deal Daño Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidad de Movimiento whenever you deal Daño Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Cuerpo a cuerpo range.",
            passiveEn = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Daño Físico to nearby enemies and slowing them by 40% durante 3s (25s Enfriamiento)\nStride (Passive): Gain 20 Velocidad de Movimiento for 2 segundo(s) when you deal Daño Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidad de Movimiento whenever you deal Daño Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Cuerpo a cuerpo range.",
            passivePt = "Breaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Dano Físico to nearby enemies and slowing them by 40% durante 3s (25s Tempo de Recarga)\nStride (Passive): Gain 20 Velocidade de Movimento for 2 segundo(s) when you deal Dano Físico.\nThis item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily Ralentización them, while the passive grants Adicional Velocidade de Movimento whenever you deal Dano Físico. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within Corpo a corpo range.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_stridebreaker.webp"
        ))
        add(WildRiftItem(
            id = "goredrinker_active",
            name = "Bebedor de Sangre",
            nameEn = "Bebedor de Sangre",
            namePt = "Bebedor de Sangre",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+350 Vida Máxima • +40 Daño de Ataque • +15 Aceleración de Habilidad",
            statsEn = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            statsPt = "+350 Vida Máxima • +40 Dano de Ataque • +15 Aceleração de Habilidade",
            passive = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Daño Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeón enemigo hit. (12s Enfriamiento)\n\n💡 Consejos del Coach: Este objeto es ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Daño Físico and restores Vida based on the number of campeones enemigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            passiveEn = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Daño Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeón enemigo hit. (12s Enfriamiento)\n\n💡 Coach Tips: Este objeto es ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Daño Físico and restores Vida based on the number of campeones enemigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            passivePt = "Goredrink (Passive): Gain8% Omnivampirismo.\nThirsting Slash (Active): Deal 175% base AD as Dano Físico to nearby enemies. Restore Vida equal to 20% plus 10% missing for each campeão inimigo hit. (12s Tempo de Recarga)\n\n💡 Dicas do Coach: Este item é ideal para fighters who excel in extended combat. It grants Omnivampirismo, while its active ability deals area Dano Físico and restores Vida based on the number of campeões inimigos hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389878_goredrinker.webp"
        ))
        add(WildRiftItem(
            id = "galeforce_active",
            name = "Fuerza del Viento",
            nameEn = "Fuerza del Viento",
            namePt = "Fuerza del Viento",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+50 Daño de Ataque • +25% Probabilidad de Crítico • +15% Velocidad de Ataque • +5% Velocidad de Movimiento",
            statsEn = "+50 Attack Damage • +25% Critical Rate • +15% Attack Speed • +5% Move Speed",
            statsPt = "+50 Dano de Ataque • +25% Chance de Crítico • +15% Velocidade de Ataque • +5% Velocidade de Movimento",
            passive = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeones. Deal Daño Físico equal to 40-125 () plus 35% Adicional. (60s Enfriamiento)\nThis item greatly improves the mobility of marksmen and AD Campeones by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeones who value mobility, safety, and strong burst potential.",
            passiveEn = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeones. Deal Daño Físico equal to 40-125 () plus 35% Adicional. (60s Enfriamiento)\nThis item greatly improves the mobility of marksmen and AD Campeones by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeones who value mobility, safety, and strong burst potential.",
            passivePt = "Grants a dash and damage Adicional\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Vida enemy near your destination, prioritizing Campeões. Deal Dano Físico equal to 40-125 () plus 35% Adicional. (60s Tempo de Recarga)\nThis item greatly improves the mobility of marksmen and AD Campeões by granting a dash that also fires projectiles at the lowest-Vida nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for Campeões who value mobility, safety, and strong burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_galeforce.webp"
        ))
        add(WildRiftItem(
            id = "mercurial_scimitar_active",
            name = "Cimitarra Mercurial",
            nameEn = "Cimitarra Mercurial",
            namePt = "Cimitarra Mercurial",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+45 Daño de Ataque • +10% Vampirismo Físico • +40 Resistencia Mágica",
            statsEn = "+45 Attack Damage • +10% Physical Vamp • +40 Magic Resistance",
            statsPt = "+45 Dano de Ataque • +10% Vampirismo Físico • +40 Resistência Mágica",
            passive = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            passiveEn = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            passivePt = "Quicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Tempo de Recarga)\nCannot be used during knock up or knock back effects.\nThis item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and Ralentiza once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep Infligir daño against heavy-CC team compositions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp"
        ))
        add(WildRiftItem(
            id = "hextech_roketbelt_active",
            name = "Cinturón Protocohete Hextech",
            nameEn = "Cinturón Protocohete Hextech",
            namePt = "Cinturón Protocohete Hextech",
            category = "Encantamientos",
            goldCost = 2700,
            stats = "+250 Vida Máxima • +70 Poder de Habilidad • +20 Aceleración de Habilidad",
            statsEn = "+250 Max Health • +70 Ability Power • +20 Ability Haste",
            statsPt = "+250 Vida Máxima • +70 Poder de Habilidade • +20 Aceleração de Habilidade",
            passive = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Daño Mágico. (30s Enfriamiento)\nIf Campeones or Monstruos are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidad with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Daño Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeones who need to dive in, secure kills, or dodge key enemy abilities.",
            passiveEn = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Daño Mágico. (30s Enfriamiento)\nIf Campeones or Monstruos are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidad with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Daño Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeones who need to dive in, secure kills, or dodge key enemy abilities.",
            passivePt = "Protobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% Dano Mágico. (30s Tempo de Recarga)\nIf Campeões or Monstros are hit by more than one missile, missiles after the first will deal only 10% damage.\nThis item combines Poder de Habilidade with extra mobility, allowing you to quickly close the gap or reposition Durante el combate. Its active grants a short dash while firing a cone of rockets that deal area Dano Mágico. It is an excellent choice for AP assassins, mobile mages, and engage-oriented Campeões who need to dive in, secure kills, or dodge key enemy abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389704_protobelt-enchant.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            nameEn = "Zhonya's Hourglass",
            namePt = "Ampulheta de Zhonya",
            category = "Encantamientos",
            goldCost = 3300,
            stats = "+40 Armadura • +110 Poder de Habilidad",
            statsEn = "+40 Armor • +110 Ability Power",
            statsPt = "+40 Armadura • +110 Poder de Habilidade",
            passive = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passiveEn = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Enfriamiento)\nThis item combines high Poder de Habilidad with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            passivePt = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (90s Tempo de Recarga)\nThis item combines high Poder de Habilidade with extra Armadura, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            nameEn = "Redemption",
            namePt = "Redenção",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+150 Vida Máxima • +50 Poder de Habilidad • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +5% Heal and Shield Strength",
            statsEn = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            statsPt = "+150 Vida Máxima • +50 Poder de Habilidade • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +5% Heal and Shield Strength",
            passive = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passiveEn = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Daño Verdadero to campeones enemigos. (60s Enfriamiento)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Daño Verdadero to campeones enemigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            passivePt = "+5% Heal and Escudo Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Vida (based on ally's level) to allied units and deal 10% of max as Dano Verdadeiro to campeões inimigos. (60s Tempo de Recarga)\nCan be cast while dead.\nThis item is designed to provide game-changing team support. Its active restores Vida to all allied units in a large area while dealing Dano Verdadeiro to campeões inimigos, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "gargoyle_stoneplate_active",
            name = "Protector Pétreo",
            nameEn = "Gargoyle Stoneplate",
            namePt = "Placa Gargolítica",
            category = "Encantamientos",
            goldCost = 2900,
            stats = "+200 Vida Máxima • +45 Armadura • +45 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +45 Armor • +45 Magic Resistance • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +45 Armadura • +45 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Enfriamiento)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            passiveEn = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Enfriamiento)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            passivePt = "Escudo\nStoneplate (Active): Gain a base Escudo that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Tempo de Recarga)\nThis item greatly increases your survivability during teamfights. Its active grants a powerful Escudo that scales with your Adicional Vida, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp"
        ))
        add(WildRiftItem(
            id = "mikael_s_blessing_active",
            name = "Bendición de Mikael",
            nameEn = "Mikael's Blessing",
            namePt = "Benção de Mikael",
            category = "Encantamientos",
            goldCost = 2500,
            stats = "+300 Vida Máxima • +50% Regeneración de Maná • +15 Aceleración de Habilidad • +9% Heal and Shield Strength",
            statsEn = "+300 Max Health • +50% Mana Regen • +15 Ability Haste • +9% Heal and Shield Strength",
            statsPt = "+300 Vida Máxima • +50% Regeneração de Mana • +15 Aceleração de Habilidade • +9% Heal and Shield Strength",
            passive = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeón, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Enfriamiento)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            passiveEn = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeón, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Enfriamiento)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            passivePt = "+9% Heal and Escudo Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied Campeão, grant them crowd control immunity for 0.2s, and heal them for 150–250 Vida. (75s Tempo de Recarga)\nThis item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their Vida, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783571078_3222_11zon.webp"
        ))
        add(WildRiftItem(
            id = "locket_of_the_iron_solari_active",
            name = "Relicario de los Solari de Hierro",
            nameEn = "Locket of the Iron Solari",
            namePt = "Medalhão dos Solari de Ferro",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+200 Vida Máxima • +30 Armadura • +30 Resistencia Mágica • +10 Aceleración de Habilidad",
            statsEn = "+200 Max Health • +30 Armor • +30 Magic Resistance • +10 Ability Haste",
            statsPt = "+200 Vida Máxima • +30 Armadura • +30 Resistência Mágica • +10 Aceleração de Habilidade",
            passive = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeones that each absorbs 250-370 damage for 2.5 segundos. (60s Enfriamiento)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Consejos del Coach: Este objeto otorga powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            passiveEn = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeones that each absorbs 250-370 damage for 2.5 segundos. (60s Enfriamiento)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Coach Tips: Este objeto otorga powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            passivePt = "Locket (Active):  Grants a Escudo to yourself and nearby allied Campeões that each absorbs 250-370 damage for 2.5 segundos. (60s Tempo de Recarga)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 segundos.\n\n💡 Dicas do Coach: Este item concede powerful team-wide protection during fights. Its active grants a Escudo to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389710_locket-enchant.webp"
        ))
        add(WildRiftItem(
            id = "shurelya_s_battlesong_active",
            name = "Canción de Batalla de Shurelya",
            nameEn = "Shurelya's Battlesong",
            namePt = "Hino Bélico de Shurelya",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+55 Poder de Habilidad • +50% Regeneración de Manáeration • +20 Aceleración de Habilidad • +5% Velocidad de Movimiento",
            statsEn = "+55 Ability Power • +50% Mana Regeneration • +20 Ability Haste • +5% Move Speed",
            statsPt = "+55 Poder de Habilidade • +50% Regeneração de Manaeration • +20 Aceleração de Habilidade • +5% Velocidade de Movimento",
            passive = "Grans allies Velocidad de Movimiento\nInspiring Speech (Active): Grant nearby allies Campeones 30% Velocidad de Movimiento for 4 segundos. (60s Enfriamiento)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeones a burst of Velocidad de Movimiento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeones who excel at controlling the pace of teamfights and enabling their teammates.",
            passiveEn = "Grans allies Velocidad de Movimiento\nInspiring Speech (Active): Grant nearby allies Campeones 30% Velocidad de Movimiento for 4 segundos. (60s Enfriamiento)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeones a burst of Velocidad de Movimiento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeones who excel at controlling the pace of teamfights and enabling their teammates.",
            passivePt = "Grans allies Velocidade de Movimento\nInspiring Speech (Active): Grant nearby allies Campeões 30% Velocidade de Movimento for 4 segundos. (60s Tempo de Recarga)\nThis item greatly enhances your team's mobility. Its active grants nearby allied Campeões a burst of Velocidade de Movimento, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility Campeões who excel at controlling the pace of teamfights and enabling their teammates.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783187148_shurelyas-battlesong.webp"
        ))
        add(WildRiftItem(
            id = "gluttonous_greaves_boots_t2",
            name = "Grebas Glotonas",
            nameEn = "Gluttonous Greaves",
            namePt = "Grevas Gulosas",
            category = "Botas N2",
            goldCost = 1000,
            stats = "+45 Velocidad de Movimiento",
            statsEn = "+45 Move Speed",
            statsPt = "+45 Velocidade de Movimento",
            passive = "Daño de Ataque, Omnivampirismo\nBalance of Power: Gain 12 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeón takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nThese boots combine mobility, adaptive offensive power, and sustained healing. They increase your damage while Omnivampirismo restores Vida from all damage you deal. Campeón takedowns further increase your Omnivampirismo, making them an excellent choice for Campeones who want to balance high damage output with strong sustain during extended fights.",
            passiveEn = "Daño de Ataque, Omnivampirismo\nBalance of Power: Gain 12 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeón takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nThese boots combine mobility, adaptive offensive power, and sustained healing. They increase your damage while Omnivampirismo restores Vida from all damage you deal. Campeón takedowns further increase your Omnivampirismo, making them an excellent choice for Campeones who want to balance high damage output with strong sustain during extended fights.",
            passivePt = "Dano de Ataque, Omnivampirismo\nBalance of Power: Gain 12 Dano de Ataque or 20 Poder de Habilidade (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeão takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nThese boots combine mobility, adaptive offensive power, and sustained healing. They increase your damage while Omnivampirismo restores Vida from all damage you deal. Campeão takedowns further increase your Omnivampirismo, making them an excellent choice for Campeões who want to balance high damage output with strong sustain during extended fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389849_gluttonous-greaves.webp"
        ))
        add(WildRiftItem(
            id = "berserker_s_greaves_boots_t2",
            name = "Grebas de Berserker",
            nameEn = "Berserker's Greaves",
            namePt = "Grevas do Berserker",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+35% Velocidad de Ataque • +45 Velocidad de Movimiento",
            statsEn = "+35% Attack Speed • +45 Move Speed",
            statsPt = "+35% Velocidade de Ataque • +45 Velocidade de Movimento",
            passive = "Velocidad de Ataque\nBlessed Blade: Attacks restore 10 Vida on hit.\nThese boots grant a significant boost to Velocidad de Ataque and Velocidad de Movimiento, while empowering your ataques básicos with on‑hit life steal.  — A great pick for marksmen and auto‑attack bruisers who need mobility, rapid attack cadence, and constant sustain in fights.",
            passiveEn = "Velocidad de Ataque\nBlessed Blade: Attacks restore 10 Vida on hit.\nThese boots grant a significant boost to Velocidad de Ataque and Velocidad de Movimiento, while empowering your ataques básicos with on‑hit life steal.  — A great pick for marksmen and auto‑attack bruisers who need mobility, rapid attack cadence, and constant sustain in fights.",
            passivePt = "Velocidade de Ataque\nBlessed Blade: Attacks restore 10 Vida on hit.\nThese boots grant a significant boost to Velocidade de Ataque and Velocidade de Movimento, while empowering your ataques básicos with on‑hit life steal.  — A great pick for marksmen and auto‑attack bruisers who need mobility, rapid attack cadence, and constant sustain in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389864_berserkers-greaves.webp"
        ))
        add(WildRiftItem(
            id = "mercury_s_treads_boots_t2",
            name = "Pasos de Mercurio",
            nameEn = "Mercury's Treads",
            namePt = "Passos de Mercúrio",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+150 Vida Máxima • +25 Resistencia Mágica • +30 Tenacidad • +45 Velocidad de Movimiento",
            statsEn = "+150 Max Health • +25 Magic Resistance • +30 Tenacity • +45 Move Speed",
            statsPt = "+150 Vida Máxima • +25 Resistência Mágica • +30 Tenacidade • +45 Velocidade de Movimento",
            passive = "Increases Resistencia Mágica\n+\nThese boots increase your Resistencia Mágica while making you more resilient to crowd control through Tenacity. The Adicional Vida and Velocidad de Movimiento improve both survivability and mobility, allowing you to perform more effectively against Daño Mágico and heavy-CC team compositions. They are an excellent choice for tanks, fighters, and any Campeón who needs to stay in the fight longer.",
            passiveEn = "Increases Resistencia Mágica\n+\nThese boots increase your Resistencia Mágica while making you more resilient to crowd control through Tenacity. The Adicional Vida and Velocidad de Movimiento improve both survivability and mobility, allowing you to perform more effectively against Daño Mágico and heavy-CC team compositions. They are an excellent choice for tanks, fighters, and any Campeón who needs to stay in the fight longer.",
            passivePt = "Increases Resistência Mágica\n+\nThese boots increase your Resistência Mágica while making you more resilient to crowd control through Tenacity. The Adicional Vida and Velocidade de Movimento improve both survivability and mobility, allowing you to perform more effectively against Dano Mágico and heavy-CC team compositions. They are an excellent choice for tanks, fighters, and any Campeão who needs to stay in the fight longer.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389863_mercurys-treads.webp"
        ))
        add(WildRiftItem(
            id = "plated_steelcaps_boots_t2",
            name = "Punteras Revestidas",
            nameEn = "Plated Steelcaps",
            namePt = "Botas Galvanizadas",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+150 Vida Máxima • +20 Armadura • +45 Velocidad de Movimiento",
            statsEn = "+150 Max Health • +20 Armor • +45 Move Speed",
            statsPt = "+150 Vida Máxima • +20 Armadura • +45 Velocidade de Movimento",
            passive = "Reduces damage from Campeón attacks\nBlock: Reduces damage from Campeón attacks by 10%.\nThese boots provide reliable protection against Campeones who rely heavily on ataques básicos. They increase your Vida and Armadura, while the passive further reduces damage taken from campeón enemigo attacks. An excellent choice against marksmen, AD fighters, and other autoataque-focused Campeones.",
            passiveEn = "Reduces damage from Campeón attacks\nBlock: Reduces damage from Campeón attacks by 10%.\nThese boots provide reliable protection against Campeones who rely heavily on ataques básicos. They increase your Vida and Armadura, while the passive further reduces damage taken from campeón enemigo attacks. An excellent choice against marksmen, AD fighters, and other autoataque-focused Campeones.",
            passivePt = "Reduces damage from Campeão attacks\nBlock: Reduces damage from Campeão attacks by 10%.\nThese boots provide reliable protection against Campeões who rely heavily on ataques básicos. They increase your Vida and Armadura, while the passive further reduces damage taken from campeão inimigo attacks. An excellent choice against marksmen, AD fighters, and other autoataque-focused Campeões.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389651_plated-steelcaps.webp"
        ))
        add(WildRiftItem(
            id = "ionian_boots_of_lucidity_boots_t2",
            name = "Botas Jonias de la Lucidez",
            nameEn = "Ionian Boots of Lucidity",
            namePt = "Botas Ionianas da Lucidez",
            category = "Botas N2",
            goldCost = 1000,
            stats = "+50% Regeneración de Maná • +15 Aceleración de Habilidad • +45 Velocidad de Movimiento",
            statsEn = "+50% Mana Regen • +15 Ability Haste • +45 Move Speed",
            statsPt = "+50% Regeneração de Mana • +15 Aceleração de Habilidade • +45 Velocidade de Movimento",
            passive = "Reduces ability cooldowns\nSummoned: Reduces spell cooldowns by 15%.\nThese boots are designed for Campeones who rely on casting abilities as often as possible. They provide Maná regeneration, Aceleración de Habilidad, and further reduce the Enfriamiento of Summoner Spells, allowing you to use key abilities more frequently while bringing back Flash, Smite, Ignite, and other Summoner Spells faster. They are an excellent choice for mages, supports, fighters, and any Campeón who benefits from maximizing ability uptime.",
            passiveEn = "Reduces ability cooldowns\nSummoned: Reduces spell cooldowns by 15%.\nThese boots are designed for Campeones who rely on casting abilities as often as possible. They provide Maná regeneration, Aceleración de Habilidad, and further reduce the Enfriamiento of Summoner Spells, allowing you to use key abilities more frequently while bringing back Flash, Smite, Ignite, and other Summoner Spells faster. They are an excellent choice for mages, supports, fighters, and any Campeón who benefits from maximizing ability uptime.",
            passivePt = "Reduces ability cooldowns\nSummoned: Reduces spell cooldowns by 15%.\nThese boots are designed for Campeões who rely on casting abilities as often as possible. They provide Maná regeneration, Aceleração de Habilidade, and further reduce the Tempo de Recarga of Summoner Spells, allowing you to use key abilities more frequently while bringing back Flash, Smite, Ignite, and other Summoner Spells faster. They are an excellent choice for mages, supports, fighters, and any Campeão who benefits from maximizing ability uptime.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389658_ionian-boots-of-lucidity.webp"
        ))
        add(WildRiftItem(
            id = "boots_of_mana_boots_t2",
            name = "Botas de Maná",
            nameEn = "Boots of Mana",
            namePt = "Botas de Mana",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+25 Poder de Habilidad • +8 Penetración Mágica • +75% Regeneración de Manáeration • +45 Velocidad de Movimiento",
            statsEn = "+25 Ability Power • +8 Magic Penetration • +75% Mana Regeneration • +45 Move Speed",
            statsPt = "+25 Poder de Habilidade • +8 Penetração Mágica • +75% Regeneração de Manaeration • +45 Velocidade de Movimento",
            passive = "Boots of Maná\nPoder de Habilidad, Magic Pen, Maná Regeneration\n+8 Penetración Mágica\nEquilibrium: Campeones without Maná gain 50% Adicional Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Daño Verdadero to Súbditos.\nThese boots greatly enhance your early Daño Mágico by providing Poder de Habilidad, Penetración Mágica, and increased Maná regeneration. They also improve wave clear by dealing Adicional Daño Verdadero to Súbditos, while Campeones without Maná instead gain additional Vida regeneration. They are an excellent choice for mages and AP supports who value strong laning, frequent spell casting, and efficient wave clearing.",
            passiveEn = "Boots of Maná\nPoder de Habilidad, Magic Pen, Maná Regeneration\n+8 Penetración Mágica\nEquilibrium: Campeones without Maná gain 50% Adicional Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Daño Verdadero to Súbditos.\nThese boots greatly enhance your early Daño Mágico by providing Poder de Habilidad, Penetración Mágica, and increased Maná regeneration. They also improve wave clear by dealing Adicional Daño Verdadero to Súbditos, while Campeones without Maná instead gain additional Vida regeneration. They are an excellent choice for mages and AP supports who value strong laning, frequent spell casting, and efficient wave clearing.",
            passivePt = "Boots of Maná\nPoder de Habilidade, Magic Pen, Maná Regeneration\n+8 Penetração Mágica\nEquilibrium: Campeões without Maná gain 50% Adicional Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Dano Verdadeiro to Tropas.\nThese boots greatly enhance your early Dano Mágico by providing Poder de Habilidade, Penetração Mágica, and increased Maná regeneration. They also improve wave clear by dealing Adicional Dano Verdadeiro to Tropas, while Campeões without Maná instead gain additional Vida regeneration. They are an excellent choice for mages and AP supports who value strong laning, frequent spell casting, and efficient wave clearing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389619_boots-of-mana.webp"
        ))
        add(WildRiftItem(
            id = "boots_of_dynamism_boots_t2",
            name = "Botas de Dinamismo",
            nameEn = "Boots of Dynamism",
            namePt = "Botas do Dinamismo",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+15 Daño de Ataque • +10 Penetración de Armadura • +45 Velocidad de Movimiento",
            statsEn = "+15 Attack Damage • +10 Armor Penetration • +45 Move Speed",
            statsPt = "+15 Dano de Ataque • +10 Penetração de Armadura • +45 Velocidade de Movimento",
            passive = "Boots of Dynamism\nDaño de Ataque,\nThese boots increase your Daño Físico by providing Adicional Daño de Ataque and Penetración de Armadura. They are especially effective during the early stages of the game, allowing you to cut through enemy Defensas and win trades more easily. They are an excellent choice for marksmen, assassins, and fighters looking to maximize their damage output and eliminate enemies more efficiently.",
            passiveEn = "Boots of Dynamism\nDaño de Ataque,\nThese boots increase your Daño Físico by providing Adicional Daño de Ataque and Penetración de Armadura. They are especially effective during the early stages of the game, allowing you to cut through enemy Defensas and win trades more easily. They are an excellent choice for marksmen, assassins, and fighters looking to maximize their damage output and eliminate enemies more efficiently.",
            passivePt = "Boots of Dynamism\nDano de Ataque,\nThese boots increase your Dano Físico by providing Adicional Dano de Ataque and Penetração de Armadura. They are especially effective during the early stages of the game, allowing you to cut through enemy Defensas and win trades more easily. They are an excellent choice for marksmen, assassins, and fighters looking to maximize their damage output and eliminate enemies more efficiently.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389665_boots-of-dynamism.webp"
        ))
        add(WildRiftItem(
            id = "immortal_treds_boots_t3",
            name = "Botas Inmortales Nivel 3",
            nameEn = "Immortal Treads Tier 3",
            namePt = "Passos Imortais Nível 3",
            category = "Botas N3",
            goldCost = 2000,
            stats = "+45 Velocidad de Movimiento",
            statsEn = "+45 Move Speed",
            statsPt = "+45 Velocidade de Movimento",
            passive = "Balance of Power: Gain 12 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeón takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nNow and Forever: When you have more than 50% Vida, deal 5% Adicional damage. When below 50% Vida, gain 12% increased healing and shielding.\nThese boots combine adaptive offensive power, sustain, and increased combat effectiveness. While above 50% Vida, you deal increased damage, and when below 50% Vida, you benefit from stronger healing and shielding to improve your survivability. The additional Omnivampirismo further restores Vida from all damage you deal. They are an excellent choice for fighters, AP bruisers, and Campeones who want to balance offensive power with sustained durability throughout extended fights.",
            passiveEn = "Balance of Power: Gain 12 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeón takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nNow and Forever: When you have more than 50% Vida, deal 5% Adicional damage. When below 50% Vida, gain 12% increased healing and shielding.\nThese boots combine adaptive offensive power, sustain, and increased combat effectiveness. While above 50% Vida, you deal increased damage, and when below 50% Vida, you benefit from stronger healing and shielding to improve your survivability. The additional Omnivampirismo further restores Vida from all damage you deal. They are an excellent choice for fighters, AP bruisers, and Campeones who want to balance offensive power with sustained durability throughout extended fights.",
            passivePt = "Balance of Power: Gain 12 Dano de Ataque or 20 Poder de Habilidade (Adaptive).\nConversion: Gain 5% Omnivampirismo. Campeão takedowns grant an additional 0.5% Omnivampirismo, up to 5%.\nNow and Forever: When you have more than 50% Vida, deal 5% Adicional damage. When below 50% Vida, gain 12% increased healing and shielding.\nThese boots combine adaptive offensive power, sustain, and increased combat effectiveness. While above 50% Vida, you deal increased damage, and when below 50% Vida, you benefit from stronger healing and shielding to improve your survivability. The additional Omnivampirismo further restores Vida from all damage you deal. They are an excellent choice for fighters, AP bruisers, and Campeões who want to balance offensive power with sustained durability throughout extended fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561637_immortal-treds.webp"
        ))
        add(WildRiftItem(
            id = "gunmetal_greaves_boots_t3",
            name = "Grebas de Metal Nivel 3",
            nameEn = "Gunmetal Greaves Tier 3",
            namePt = "Grevas Bélicas Nível 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+50% Velocidad de Ataque • +5% Vampirismo Físico • +45 Velocidad de Movimiento",
            statsEn = "+50% Attack Speed • +5% Physical Vamp • +45 Move Speed",
            statsPt = "+50% Velocidade de Ataque • +5% Vampirismo Físico • +45 Velocidade de Movimento",
            passive = "Noxian Gait: Attacks against campeones enemigos grant Velocidad de Movimiento (10% for Cuerpo a cuerpo Campeones / 7% for A distancia Campeones) decaying over 2 segundos.\nBlessed Blade: Attacks restore 12 Vida on hit.\nThese boots greatly increase your Velocidad de Ataque while improving your mobility En combate. Attacking campeones enemigos grants a burst of Velocidad de Movimiento, making it easier to chase opponents or kite effectively, while Vampirismo Físico and on-hit healing provide valuable sustain during extended fights. They are an excellent choice for marksmen and Campeones who rely on frequent ataques básicos to deal damage.",
            passiveEn = "Noxian Gait: Attacks against campeones enemigos grant Velocidad de Movimiento (10% for Cuerpo a cuerpo Campeones / 7% for A distancia Campeones) decaying over 2 segundos.\nBlessed Blade: Attacks restore 12 Vida on hit.\nThese boots greatly increase your Velocidad de Ataque while improving your mobility En combate. Attacking campeones enemigos grants a burst of Velocidad de Movimiento, making it easier to chase opponents or kite effectively, while Vampirismo Físico and on-hit healing provide valuable sustain during extended fights. They are an excellent choice for marksmen and Campeones who rely on frequent ataques básicos to deal damage.",
            passivePt = "Noxian Gait: Attacks against campeões inimigos grant Velocidade de Movimento (10% for Corpo a corpo Campeões / 7% for À distância Campeões) decaying over 2 segundos.\nBlessed Blade: Attacks restore 12 Vida on hit.\nThese boots greatly increase your Velocidade de Ataque while improving your mobility En combate. Attacking campeões inimigos grants a burst of Velocidade de Movimento, making it easier to chase opponents or kite effectively, while Vampirismo Físico and on-hit healing provide valuable sustain during extended fights. They are an excellent choice for marksmen and Campeões who rely on frequent ataques básicos to deal damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561598_gunmetal-greaves.webp"
        ))
        add(WildRiftItem(
            id = "chainlaced_crushers_boots_t3",
            name = "Trituradoras Eslabadas Nivel 3",
            nameEn = "Chainlaced Crushers Tier 3",
            namePt = "Trituradores Encadeados Nível 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+150 Vida Máxima • +30 Resistencia Mágica • +30% Tenacidad • +45 Velocidad de Movimiento",
            statsEn = "+150 Max Health • +30 Magic Resistance • +30% Tenacity • +45 Move Speed",
            statsPt = "+150 Vida Máxima • +30 Resistência Mágica • +30% Tenacidade • +45 Velocidade de Movimento",
            passive = "Gain a magic Escudo upon taking Daño Mágico\n+30% Tenacity\nNoxian Persistence: After taking Daño Mágico from a Campeón, gain a magic Escudo that absorbs 10-120 plus 5% maxfor 5s. (12s Enfriamiento)\nThese boots greatly improve your survivability against Daño Mágico. After taking Daño Mágico from an campeón enemigo, you gain a magic Escudo that helps absorb follow-up spells, while the Adicional Resistencia Mágica and Tenacity make you far more resilient against AP threats and crowd control. They are an excellent choice against teams with heavy Daño Mágico and strong CC.",
            passiveEn = "Gain a magic Escudo upon taking Daño Mágico\n+30% Tenacity\nNoxian Persistence: After taking Daño Mágico from a Campeón, gain a magic Escudo that absorbs 10-120 plus 5% maxfor 5s. (12s Enfriamiento)\nThese boots greatly improve your survivability against Daño Mágico. After taking Daño Mágico from an campeón enemigo, you gain a magic Escudo that helps absorb follow-up spells, while the Adicional Resistencia Mágica and Tenacity make you far more resilient against AP threats and crowd control. They are an excellent choice against teams with heavy Daño Mágico and strong CC.",
            passivePt = "Gain a magic Escudo upon taking Dano Mágico\n+30% Tenacity\nNoxian Persistence: After taking Dano Mágico from a Campeão, gain a magic Escudo that absorbs 10-120 plus 5% maxfor 5s. (12s Tempo de Recarga)\nThese boots greatly improve your survivability against Dano Mágico. After taking Dano Mágico from an campeão inimigo, you gain a magic Escudo that helps absorb follow-up spells, while the Adicional Resistência Mágica and Tenacity make you far more resilient against AP threats and crowd control. They are an excellent choice against teams with heavy Dano Mágico and strong CC.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561548_chainlaced-crushers.webp"
        ))
        add(WildRiftItem(
            id = "armored_advance_boots_t3",
            name = "Avance Blindado Nivel 3",
            nameEn = "Armored Advance Tier 3",
            namePt = "Avanço Blindado Nível 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+150 Vida Máxima • +30 Armadura • +45 Velocidad de Movimiento",
            statsEn = "+150 Max Health • +30 Armor • +45 Move Speed",
            statsPt = "+150 Vida Máxima • +30 Armadura • +45 Velocidade de Movimento",
            passive = "Block: Reduce damage from Campeón attacks by 10%.\nNoxian Endurance: After taking Daño Físico from a Campeón grants a physical Escudo that absorbs damage equal to 10-140 plus 8% Vida Máxima. (12s Enfriamiento)\nThese boots provide excellent protection against Daño Físico. They reduce damage taken from campeón enemigo attacks and grant a protective Escudo after taking Daño Físico from a Campeón, helping you survive extended trades and heavy bursts of Daño Físico. They are an excellent choice against marksmen, fighters, and other Campeones who rely primarily on physical attacks.",
            passiveEn = "Block: Reduce damage from Campeón attacks by 10%.\nNoxian Endurance: After taking Daño Físico from a Campeón grants a physical Escudo that absorbs damage equal to 10-140 plus 8% Vida Máxima. (12s Enfriamiento)\nThese boots provide excellent protection against Daño Físico. They reduce damage taken from campeón enemigo attacks and grant a protective Escudo after taking Daño Físico from a Campeón, helping you survive extended trades and heavy bursts of Daño Físico. They are an excellent choice against marksmen, fighters, and other Campeones who rely primarily on physical attacks.",
            passivePt = "Block: Reduce damage from Campeão attacks by 10%.\nNoxian Endurance: After taking Dano Físico from a Campeão grants a physical Escudo that absorbs damage equal to 10-140 plus 8% Vida Máxima. (12s Tempo de Recarga)\nThese boots provide excellent protection against Dano Físico. They reduce damage taken from campeão inimigo attacks and grant a protective Escudo after taking Dano Físico from a Campeão, helping you survive extended trades and heavy bursts of Dano Físico. They are an excellent choice against marksmen, fighters, and other Campeões who rely primarily on physical attacks.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561641_armored-advance.webp"
        ))
        add(WildRiftItem(
            id = "crimson_lucidity_boots_t3",
            name = "Lucidez Carmesí Nivel 3",
            nameEn = "Crimson Lucidity Tier 3",
            namePt = "Lucidez Carmesim Nível 3",
            category = "Botas N3",
            goldCost = 2000,
            stats = "+75% Regeneración de Manáeration • +25 Aceleración de Habilidad • +45 Velocidad de Movimiento",
            statsEn = "+75% Mana Regeneration • +25 Ability Haste • +45 Move Speed",
            statsPt = "+75% Regeneração de Manaeration • +25 Aceleração de Habilidade • +45 Velocidade de Movimento",
            passive = "Summoned: Reduces spell Enfriamiento by 20%.\nNoxian Haste: Healing or shielding allied Campeones, casting a spell, or Infligir daño to enemies with abilities grants Velocidad de Movimiento (10% for Cuerpo a cuerpo Campeones / 8% for A distancia Campeones) for 4 segundos.\nThis effect can only be triggered once every 4 segundos per ability.\nThese boots are ideal for Campeones who rely on casting abilities as often as possible. They greatly reduce the Enfriamiento of both abilities and Summoner Spells while granting Adicional Velocidad de Movimiento whenever you heal or Escudo allies, cast spells, or damage enemies with abilities. They are an excellent choice for mages, supports, and fighters who value high mobility and maximum ability uptime.",
            passiveEn = "Summoned: Reduces spell Enfriamiento by 20%.\nNoxian Haste: Healing or shielding allied Campeones, casting a spell, or Infligir daño to enemies with abilities grants Velocidad de Movimiento (10% for Cuerpo a cuerpo Campeones / 8% for A distancia Campeones) for 4 segundos.\nThis effect can only be triggered once every 4 segundos per ability.\nThese boots are ideal for Campeones who rely on casting abilities as often as possible. They greatly reduce the Enfriamiento of both abilities and Summoner Spells while granting Adicional Velocidad de Movimiento whenever you heal or Escudo allies, cast spells, or damage enemies with abilities. They are an excellent choice for mages, supports, and fighters who value high mobility and maximum ability uptime.",
            passivePt = "Summoned: Reduces spell Tempo de Recarga by 20%.\nNoxian Haste: Healing or shielding allied Campeões, casting a spell, or Infligir daño to enemies with abilities grants Velocidade de Movimento (10% for Corpo a corpo Campeões / 8% for À distância Campeões) for 4 segundos.\nThis effect can only be triggered once every 4 segundos per ability.\nThese boots are ideal for Campeões who rely on casting abilities as often as possible. They greatly reduce the Tempo de Recarga of both abilities and Summoner Spells while granting Adicional Velocidade de Movimento whenever you heal or Escudo allies, cast spells, or damage enemies with abilities. They are an excellent choice for mages, supports, and fighters who value high mobility and maximum ability uptime.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561706_crimson-lucidity.webp"
        ))
        add(WildRiftItem(
            id = "spellslinger_s_shoes_boots_t3",
            name = "Zapatos de Hechicero Nivel 3",
            nameEn = "Spellslinger's Shoes Tier 3",
            namePt = "Sapatos do Feiticeiro Nível 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+35 Poder de Habilidad • +18 Penetración Mágica • +8% Penetración Mágica • +100% Regeneración de Manáeration • +45 Velocidad de Movimiento",
            statsEn = "+35 Ability Power • +18 Magic Penetration • +8% Magic Penetration • +100% Mana Regeneration • +45 Move Speed",
            statsPt = "+35 Poder de Habilidade • +18 Penetração Mágica • +8% Penetração Mágica • +100% Regeneração de Manaeration • +45 Velocidade de Movimento",
            passive = "Deal Adicional damage to Súbditos\n+18 Penetración Mágica\n+8% Penetración Mágica\nEquilibrium: Campeones without Maná gain 50% base Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Daño Verdadero to Súbditos.\nThese boots greatly increase your Daño Mágico through a combination of Poder de Habilidad and both flat and percentage Penetración Mágica. The high Maná regeneration allows for frequent spell casting, while the Adicional Daño Verdadero to Súbditos significantly improves wave clear. Campeones without Maná instead gain increased Vida regeneration. They are an excellent choice for mages and AP supports who value strong damage, constant lane pressure, and efficient farming.",
            passiveEn = "Deal Adicional damage to Súbditos\n+18 Penetración Mágica\n+8% Penetración Mágica\nEquilibrium: Campeones without Maná gain 50% base Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Daño Verdadero to Súbditos.\nThese boots greatly increase your Daño Mágico through a combination of Poder de Habilidad and both flat and percentage Penetración Mágica. The high Maná regeneration allows for frequent spell casting, while the Adicional Daño Verdadero to Súbditos significantly improves wave clear. Campeones without Maná instead gain increased Vida regeneration. They are an excellent choice for mages and AP supports who value strong damage, constant lane pressure, and efficient farming.",
            passivePt = "Deal Adicional damage to Tropas\n+18 Penetração Mágica\n+8% Penetração Mágica\nEquilibrium: Campeões without Maná gain 50% base Vida Regen.\nBig Bully: Attacks and active abilities deal 18 Adicional Dano Verdadeiro to Tropas.\nThese boots greatly increase your Dano Mágico through a combination of Poder de Habilidade and both flat and percentage Penetração Mágica. The high Maná regeneration allows for frequent spell casting, while the Adicional Dano Verdadeiro to Tropas significantly improves wave clear. Campeões without Maná instead gain increased Vida regeneration. They are an excellent choice for mages and AP supports who value strong damage, constant lane pressure, and efficient farming.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561512_spellslingers-shoes.webp"
        ))
        add(WildRiftItem(
            id = "armorcrusher_boots_boots_t3",
            name = "Botas Rompearmaduras Nivel 3",
            nameEn = "Armorcrusher Boots Tier 3",
            namePt = "Botas Quebra-Armadura Nível 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+25 Daño de Ataque • +12 Penetración de Armadura • +6% Penetración de Armadura • +45 Velocidad de Movimiento",
            statsEn = "+25 Attack Damage • +12 Armor Penetration • +6% Armor Penetration • +45 Move Speed",
            statsPt = "+25 Dano de Ataque • +12 Penetração de Armadura • +6% Penetração de Armadura • +45 Velocidade de Movimento",
            passive = "Gain out-of-combat Velocidad de Movimiento\nCloudwalker: Gain 20 out-of combat Velocidad de Movimiento.\nThese boots greatly increase your Daño Físico by providing Adicional Daño de Ataque along with both flat and percentage Penetración de Armadura. The additional out-of-combat Velocidad de Movimiento allows you to rotate around the map faster, chase enemies more effectively, and respond to fights more quickly. They are an excellent choice for marksmen, assassins, and fighters who value high mobility and maximum damage against armored targets.",
            passiveEn = "Gain out-of-combat Velocidad de Movimiento\nCloudwalker: Gain 20 out-of combat Velocidad de Movimiento.\nThese boots greatly increase your Daño Físico by providing Adicional Daño de Ataque along with both flat and percentage Penetración de Armadura. The additional out-of-combat Velocidad de Movimiento allows you to rotate around the map faster, chase enemies more effectively, and respond to fights more quickly. They are an excellent choice for marksmen, assassins, and fighters who value high mobility and maximum damage against armored targets.",
            passivePt = "Gain out-of-combat Velocidade de Movimento\nCloudwalker: Gain 20 out-of combat Velocidade de Movimento.\nThese boots greatly increase your Dano Físico by providing Adicional Dano de Ataque along with both flat and percentage Penetração de Armadura. The additional out-of-combat Velocidade de Movimento allows you to rotate around the map faster, chase enemies more effectively, and respond to fights more quickly. They are an excellent choice for marksmen, assassins, and fighters who value high mobility and maximum damage against armored targets.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561651_armorbreaker-boots.webp"
        ))
        add(WildRiftItem(
            id = "quicksilver_sash_mid_tier",
            name = "Fajín de Mercurio",
            nameEn = "Quicksilver Enchant",
            namePt = "Encantamento de Mercúrio",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "",
            statsEn = "",
            statsPt = "",
            passive = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            passiveEn = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Enfriamiento)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            passivePt = "Quicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 segundos.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Ralentización Resist for 1.5 segundos. (60s Tempo de Recarga)\nCannot be used during knock up or knock back effects.\nThis enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and Ralentiza, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389685_quicksilver-enchant.webp"
        ))
        add(WildRiftItem(
            id = "seeker_s_armguard_mid_tier",
            name = "Guardabrazo de la Buscadora",
            nameEn = "Guardabrazo de la Buscadora",
            namePt = "Guardabrazo de la Buscadora",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Armadura • +35 Poder de Habilidad",
            statsEn = "+20 Armor • +35 Ability Power",
            statsPt = "+20 Armadura • +35 Poder de Habilidade",
            passive = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Enfriamiento)\nThis item combines Poder de Habilidad with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            passiveEn = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Enfriamiento)\nThis item combines Poder de Habilidad with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            passivePt = "Turn invulnerable\nEstasis (Active): Become invulnerable and untargetable for 2.5 segundos, but unable to move, attack, cast abilities or use items. (120s Tempo de Recarga)\nThis item combines Poder de Habilidade with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2022-01/1641809649_seekers-armguard.png"
        ))
        add(WildRiftItem(
            id = "vampiric_scepter_mid_tier",
            name = "Cetro Vampírico",
            nameEn = "Cetro Vampírico",
            namePt = "Cetro Vampírico",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Daño de Ataque • +8% Vampirismo Físico",
            statsEn = "+20 Attack Damage • +8% Physical Vamp",
            statsPt = "+20 Dano de Ataque • +8% Vampirismo Físico",
            passive = "Vampiric Scepter",
            passiveEn = "Vampiric Scepter",
            passivePt = "Vampiric Scepter",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985546_1053.png"
        ))
        add(WildRiftItem(
            id = "zeal_mid_tier",
            name = "Fervor",
            nameEn = "Fervor",
            namePt = "Fervor",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+15% Probabilidad de Crítico • +15% Velocidad de Ataque",
            statsEn = "+15% Critical Rate • +15% Attack Speed",
            statsPt = "+15% Chance de Crítico • +15% Velocidade de Ataque",
            passive = "Fervor:  +5% Velocidad de Movimiento.",
            passiveEn = "Fervor:  +5% Velocidad de Movimiento.",
            passivePt = "Fervor:  +5% Velocidade de Movimento.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985588_3086.png"
        ))
        add(WildRiftItem(
            id = "kircheis_shard_mid_tier",
            name = "Fragmento de Kircheis",
            nameEn = "Fragmento de Kircheis",
            namePt = "Fragmento de Kircheis",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+15 Daño de Ataque",
            statsEn = "+15 Attack Damage",
            statsPt = "+15 Dano de Ataque",
            passive = "Jolt: Energy Attacks gain 50 Adicional Daño Mágico. Muving and attacking generate Energy Attacks.",
            passiveEn = "Jolt: Energy Attacks gain 50 Adicional Daño Mágico. Muving and attacking generate Energy Attacks.",
            passivePt = "Jolt: Energy Attacks gain 50 Adicional Dano Mágico. Muving and attacking generate Energy Attacks.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985587_2015.png"
        ))
        add(WildRiftItem(
            id = "serrated_dirk_mid_tier",
            name = "Daga Dentada",
            nameEn = "Daga Dentada",
            namePt = "Daga Dentada",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Daño de Ataque",
            statsEn = "+20 Attack Damage",
            statsPt = "+20 Dano de Ataque",
            passive = "Sharp:  +8 Penetración de Armadura.",
            passiveEn = "Sharp:  +8 Penetración de Armadura.",
            passivePt = "Sharp:  +8 Penetração de Armadura.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985626_3134.png"
        ))
        add(WildRiftItem(
            id = "recurve_bow_mid_tier",
            name = "Arco Recurvo",
            nameEn = "Arco Recurvo",
            namePt = "Arco Recurvo",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+30% Velocidad de Ataque",
            statsEn = "+30% Attack Speed",
            statsPt = "+30% Velocidade de Ataque",
            passive = "Reinforced: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.",
            passiveEn = "Reinforced: Los ataques infligen 15 de daño físico adicional al impacto contra los objetivos.",
            passivePt = "Reinforced: Los ataques infligen 15 de Dano Físico adicional al impacto contra los objetivos.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985621_1043.png"
        ))
        add(WildRiftItem(
            id = "b_f_sword_mid_tier",
            name = "Espadón",
            nameEn = "Espadón",
            namePt = "Espadón",
            category = "Nivel Medio",
            goldCost = 1500,
            stats = "+40 Daño de Ataque",
            statsEn = "+40 Attack Damage",
            statsPt = "+40 Dano de Ataque",
            passive = "B. F. Sword",
            passiveEn = "B. F. Sword",
            passivePt = "B. F. Sword",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985610_1038.png"
        ))
        add(WildRiftItem(
            id = "cloak_of_agility_mid_tier",
            name = "Capa de Agilidad",
            nameEn = "Capa de Agilidad",
            namePt = "Capa de Agilidad",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20% Probabilidad de Crítico",
            statsEn = "+20% Critical Rate",
            statsPt = "+20% Chance de Crítico",
            passive = "Cloak of Agility",
            passiveEn = "Cloak of Agility",
            passivePt = "Cloak of Agility",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985612_1018.png"
        ))
        add(WildRiftItem(
            id = "last_whisper_mid_tier",
            name = "Último Suspiro",
            nameEn = "Último Suspiro",
            namePt = "Último Suspiro",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "",
            statsEn = "",
            statsPt = "",
            passive = "Penetración de Armadura (%)\nLast Whisper:  +12% Penetración de Armadura.",
            passiveEn = "Penetración de Armadura (%)\nLast Whisper:  +12% Penetración de Armadura.",
            passivePt = "Penetração de Armadura (%)\nLast Whisper:  +12% Penetração de Armadura.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611440927_last-whisper.png"
        ))
        add(WildRiftItem(
            id = "executioner_s_calling_mid_tier",
            name = "Llamado del Verdugo",
            nameEn = "Llamado del Verdugo",
            namePt = "Llamado del Verdugo",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+15 Daño de Ataque",
            statsEn = "+15 Attack Damage",
            statsPt = "+15 Dano de Ataque",
            passive = "Daño Físico reduces enemy healing\nRend: Daño Físico inflicts 40% Heridas Graves to campeones enemigos for 3 segundos. Heridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passiveEn = "Daño Físico reduces enemy healing\nRend: Daño Físico inflicts 40% Heridas Graves to campeones enemigos for 3 segundos. Heridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passivePt = "Dano Físico reduces enemy healing\nRend: Dano Físico inflicts 40% Feridas Dolorosas to campeões inimigos for 3 segundos. Feridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985718_3123.png"
        ))
        add(WildRiftItem(
            id = "phage_mid_tier",
            name = "Bacteriófago",
            nameEn = "Bacteriófago",
            namePt = "Bacteriófago",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+150 Vida Máxima • +15 Daño de Ataque",
            statsEn = "+150 Max Health • +15 Attack Damage",
            statsPt = "+150 Vida Máxima • +15 Dano de Ataque",
            passive = "Rage: Los ataques otorgan 20 Velocidad de Movimiento and kills grant 60 Velocidad de Movimiento for 2 segundos. Los efectos no se acumulan. A distancia Campeones gain halved values.",
            passiveEn = "Rage: Los ataques otorgan 20 Velocidad de Movimiento and kills grant 60 Velocidad de Movimiento for 2 segundos. Los efectos no se acumulan. A distancia Campeones gain halved values.",
            passivePt = "Rage: Los ataques otorgan 20 Velocidade de Movimento and kills grant 60 Velocidade de Movimento for 2 segundos. Los efectos no se acumulan. À distância Campeões gain halved values.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985701_3044.png"
        ))
        add(WildRiftItem(
            id = "stinger_mid_tier",
            name = "Aguijón",
            nameEn = "Aguijón",
            namePt = "Aguijón",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+30% Velocidad de Ataque • +10 Aceleración de Habilidad",
            statsEn = "+30% Attack Speed • +10 Ability Haste",
            statsPt = "+30% Velocidade de Ataque • +10 Aceleração de Habilidade",
            passive = "Stinger",
            passiveEn = "Stinger",
            passivePt = "Stinger",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985695_winged_moonplate_item_hd.jpg"
        ))
        add(WildRiftItem(
            id = "caulfield_s_warhammer_mid_tier",
            name = "Martillo de Guerra de Caulfield",
            nameEn = "Martillo de Guerra de Caulfield",
            namePt = "Martillo de Guerra de Caulfield",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+25 Daño de Ataque • +10 Aceleración de Habilidad",
            statsEn = "+25 Attack Damage • +10 Ability Haste",
            statsPt = "+25 Dano de Ataque • +10 Aceleração de Habilidade",
            passive = "Caulfield's Warhammer",
            passiveEn = "Caulfield's Warhammer",
            passivePt = "Caulfield's Warhammer",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611441611_caulfields-warhammer.png"
        ))
        add(WildRiftItem(
            id = "jaurim_s_fist_mid_tier",
            name = "Puño de Jaurim",
            nameEn = "Puño de Jaurim",
            namePt = "Puño de Jaurim",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+175 Vida Máxima • +15 Daño de Ataque",
            statsEn = "+175 Max Health • +15 Attack Damage",
            statsPt = "+175 Vida Máxima • +15 Dano de Ataque",
            passive = "Jaurim's Fist",
            passiveEn = "Jaurim's Fist",
            passivePt = "Jaurim's Fist",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611444901_jaurims-fist.png"
        ))
        add(WildRiftItem(
            id = "aether_wisp_mid_tier",
            name = "Brisa de Éter",
            nameEn = "Brisa de Éter",
            namePt = "Brisa de Éter",
            category = "Nivel Medio",
            goldCost = 950,
            stats = "+35 Poder de Habilidad",
            statsEn = "+35 Ability Power",
            statsPt = "+35 Poder de Habilidade",
            passive = "Wisp:  +5% Velocidad de Movimiento.",
            passiveEn = "Wisp:  +5% Velocidad de Movimiento.",
            passivePt = "Wisp:  +5% Velocidade de Movimento.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611442631_aether-wisp.png"
        ))
        add(WildRiftItem(
            id = "lost_chapter_mid_tier",
            name = "Capítulo Perdido",
            nameEn = "Capítulo Perdido",
            namePt = "Capítulo Perdido",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+35 Poder de Habilidad • +200 Maná Máximo • +10 Aceleración de Habilidad",
            statsEn = "+35 Ability Power • +200 Max Mana • +10 Ability Haste",
            statsPt = "+35 Poder de Habilidade • +200 Mana Máxima • +10 Aceleração de Habilidade",
            passive = "Restore Maná when leveling up\n+200 Maná Máximo\nEnlighten: Leveling up restores 20%Maná Máximo over 3 segundos.",
            passiveEn = "Restore Maná when leveling up\n+200 Maná Máximo\nEnlighten: Leveling up restores 20%Maná Máximo over 3 segundos.",
            passivePt = "Restore Maná when leveling up\n+200 Mana Máxima\nEnlighten: Leveling up restores 20%Mana Máxima over 3 segundos.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985899_3802.png"
        ))
        add(WildRiftItem(
            id = "fiendish_codex_mid_tier",
            name = "Códice Diabólico",
            nameEn = "Códice Diabólico",
            namePt = "Códice Diabólico",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+25 Poder de Habilidad • +10 Aceleración de Habilidad",
            statsEn = "+25 Ability Power • +10 Ability Haste",
            statsPt = "+25 Poder de Habilidade • +10 Aceleração de Habilidade",
            passive = "Fiendish Codex",
            passiveEn = "Fiendish Codex",
            passivePt = "Fiendish Codex",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985893_3108.png"
        ))
        add(WildRiftItem(
            id = "blasting_wand_mid_tier",
            name = "Varita Explosiva",
            nameEn = "Varita Explosiva",
            namePt = "Varita Explosiva",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Poder de Habilidad",
            statsEn = "+40 Ability Power",
            statsPt = "+40 Poder de Habilidade",
            passive = "Blasting Wand",
            passiveEn = "Blasting Wand",
            passivePt = "Blasting Wand",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985924_1026.png"
        ))
        add(WildRiftItem(
            id = "needlessly_large_rod_mid_tier",
            name = "Vara Innecesariamente Grande",
            nameEn = "Vara Innecesariamente Grande",
            namePt = "Vara Innecesariamente Grande",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+65 Poder de Habilidad",
            statsEn = "+65 Ability Power",
            statsPt = "+65 Poder de Habilidade",
            passive = "Needlessly Large Rod",
            passiveEn = "Needlessly Large Rod",
            passivePt = "Needlessly Large Rod",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985984_1058.png"
        ))
        add(WildRiftItem(
            id = "haunting_guise_mid_tier",
            name = "Disfraz Encantado",
            nameEn = "Disfraz Encantado",
            namePt = "Disfraz Encantado",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+200 Vida Máxima • +30 Poder de Habilidad",
            statsEn = "+200 Max Health • +30 Ability Power",
            statsPt = "+200 Vida Máxima • +30 Poder de Habilidade",
            passive = "Madness: Every 1 segundo(s) En combate with campeones enemigos, deal 2% Adicional damage, up to 6%.",
            passiveEn = "Madness: Every 1 segundo(s) En combate with campeones enemigos, deal 2% Adicional damage, up to 6%.",
            passivePt = "Madness: Every 1 segundo(s) En combate with campeões inimigos, deal 2% Adicional damage, up to 6%.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611443224_haunting-guise.png"
        ))
        add(WildRiftItem(
            id = "sheen_mid_tier",
            name = "Brillo",
            nameEn = "Brillo",
            namePt = "Brillo",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+10 Aceleración de Habilidad",
            statsEn = "+10 Ability Haste",
            statsPt = "+10 Aceleração de Habilidade",
            passive = "Spellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Físico equal to 100% base Daño de Ataque . (1.5s Enfriamiento) El daño se reduce contra estructuras.",
            passiveEn = "Spellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Daño Físico equal to 100% base Daño de Ataque . (1.5s Enfriamiento) El daño se reduce contra estructuras.",
            passivePt = "Spellblade: Using an ability causes the next attack used within 10 segundos to deal Adicional Dano Físico equal to 100% base Dano de Ataque . (1.5s Tempo de Recarga) El daño se reduce contra estructuras.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985975_3057.png"
        ))
        add(WildRiftItem(
            id = "oblivion_orb_mid_tier",
            name = "Orbe del Olvido",
            nameEn = "Orbe del Olvido",
            namePt = "Orbe del Olvido",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+35 Poder de Habilidad",
            statsEn = "+35 Ability Power",
            statsPt = "+35 Poder de Habilidade",
            passive = "Daño Mágico reduces enemy healing\nCursed Wounds: Dealing Daño Mágico to campeones enemigos applies 40% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passiveEn = "Daño Mágico reduces enemy healing\nCursed Wounds: Dealing Daño Mágico to campeones enemigos applies 40% Heridas Graves for 3 segundos.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passivePt = "Dano Mágico reduces enemy healing\nCursed Wounds: Dealing Dano Mágico to campeões inimigos applies 40% Feridas Dolorosas for 3 segundos.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985971_3916.png"
        ))
        add(WildRiftItem(
            id = "bami_s_cinder_mid_tier",
            name = "Ceniza de Bami",
            nameEn = "Ceniza de Bami",
            namePt = "Ceniza de Bami",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+250 Vida Máxima",
            statsEn = "+250 Max Health",
            statsPt = "+250 Vida Máxima",
            passive = "Cinders: Deals 10-20 Daño Mágico per segundo to nearby enemies. Deals 15% Adicional damage to Súbditos and Monstruos.",
            passiveEn = "Cinders: Deals 10-20 Daño Mágico per segundo to nearby enemies. Deals 15% Adicional damage to Súbditos and Monstruos.",
            passivePt = "Cinders: Deals 10-20 Dano Mágico per segundo to nearby enemies. Deals 15% Adicional damage to Tropas and Monstros.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986281_6660.png"
        ))
        add(WildRiftItem(
            id = "spectre_s_cowl_mid_tier",
            name = "Hábito del Espectro",
            nameEn = "Hábito del Espectro",
            namePt = "Hábito del Espectro",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+175 Vida Máxima • +20 Resistencia Mágica",
            statsEn = "+175 Max Health • +20 Magic Resistance",
            statsPt = "+175 Vida Máxima • +20 Resistência Mágica",
            passive = "Spectral Visit: Grants 150% Vida Regen for 10 segundos after taking damage from an campeón enemigo.",
            passiveEn = "Spectral Visit: Grants 150% Vida Regen for 10 segundos after taking damage from an campeón enemigo.",
            passivePt = "Spectral Visit: Grants 150% Vida Regen for 10 segundos after taking damage from an campeão inimigo.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986306_3211.png"
        ))
        add(WildRiftItem(
            id = "kindlegem_mid_tier",
            name = "Gema Avivadora",
            nameEn = "Gema Avivadora",
            namePt = "Gema Avivadora",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+175 Vida Máxima • +10 Aceleración de Habilidad",
            statsEn = "+175 Max Health • +10 Ability Haste",
            statsPt = "+175 Vida Máxima • +10 Aceleração de Habilidade",
            passive = "Kindlegem",
            passiveEn = "Kindlegem",
            passivePt = "Kindlegem",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986347_3067.png"
        ))
        add(WildRiftItem(
            id = "giant_s_belt_mid_tier",
            name = "Cinturón de Gigante",
            nameEn = "Cinturón de Gigante",
            namePt = "Cinturón de Gigante",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+300 Vida Máxima",
            statsEn = "+300 Max Health",
            statsPt = "+300 Vida Máxima",
            passive = "Giant's Belt",
            passiveEn = "Giant's Belt",
            passivePt = "Giant's Belt",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986359_1011.png"
        ))
        add(WildRiftItem(
            id = "warden_s_mail_mid_tier",
            name = "Malla del Guardián",
            nameEn = "Malla del Guardián",
            namePt = "Malla del Guardián",
            category = "Nivel Medio",
            goldCost = 1050,
            stats = "+35 Armadura",
            statsEn = "+35 Armor",
            statsPt = "+35 Armadura",
            passive = "Cold Steel: Reduce the Velocidad de Ataque of enemies by 15% for 1.5 segundos when struck by an attack.",
            passiveEn = "Cold Steel: Reduce the Velocidad de Ataque of enemies by 15% for 1.5 segundos when struck by an attack.",
            passivePt = "Cold Steel: Reduce the Velocidade de Ataque of enemies by 15% for 1.5 segundos when struck by an attack.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986370_3082.png"
        ))
        add(WildRiftItem(
            id = "catalyst_of_aeons_mid_tier",
            name = "Catalizador de Eones",
            nameEn = "Catalizador de Eones",
            namePt = "Catalizador de Eones",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+200 Vida Máxima • +300 Maná Máximo",
            statsEn = "+200 Max Health • +300 Max Mana",
            statsPt = "+200 Vida Máxima • +300 Mana Máxima",
            passive = "Consumes Maná to heal\n+300 Maná Máximo\nEternity: Restore Maná equal to 15% of the damage taken from Campeones. Regen Vida equal to 20% of Maná spent. Capped at 15 Vida per cast.",
            passiveEn = "Consumes Maná to heal\n+300 Maná Máximo\nEternity: Restore Maná equal to 15% of the damage taken from Campeones. Regen Vida equal to 20% of Maná spent. Capped at 15 Vida per cast.",
            passivePt = "Consumes Maná to heal\n+300 Mana Máxima\nEternity: Restore Maná equal to 15% of the damage taken from Campeões. Regen Vida equal to 20% of Maná spent. Capped at 15 Vida per cast.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611444645_catalyst-of-aeons.png"
        ))
        add(WildRiftItem(
            id = "chain_vest_mid_tier",
            name = "Cota de Malla",
            nameEn = "Cota de Malla",
            namePt = "Cota de Malla",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Armadura",
            statsEn = "+40 Armor",
            statsPt = "+40 Armadura",
            passive = "Chain Vest",
            passiveEn = "Chain Vest",
            passivePt = "Chain Vest",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986413_1031.png"
        ))
        add(WildRiftItem(
            id = "bramble_vest_mid_tier",
            name = "Chaleco de Zarzas",
            nameEn = "Chaleco de Zarzas",
            namePt = "Chaleco de Zarzas",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+30 Armadura",
            statsEn = "+30 Armor",
            statsPt = "+30 Armadura",
            passive = "Thorns: When struck by an attack, deal 4 Daño Mágico + 6% Adicional Armadura  to the attacker and inflict 40% Heridas Graves for 3 segundos if they are a Campeón.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passiveEn = "Thorns: When struck by an attack, deal 4 Daño Mágico + 6% Adicional Armadura  to the attacker and inflict 40% Heridas Graves for 3 segundos if they are a Campeón.\nHeridas Graves reduces the effectiveness of Healing and Regeneration effects.",
            passivePt = "Thorns: When struck by an attack, deal 4 Dano Mágico + 6% Adicional Armadura  to the attacker and inflict 40% Feridas Dolorosas for 3 segundos if they are a Campeão.\nFeridas Dolorosas reduces the effectiveness of Healing and Regeneration effects.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986594_3076.png"
        ))
        add(WildRiftItem(
            id = "hexdrinker_mid_tier",
            name = "Sorbemaleficios",
            nameEn = "Sorbemaleficios",
            namePt = "Sorbemaleficios",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Daño de Ataque • +20 Resistencia Mágica",
            statsEn = "+20 Attack Damage • +20 Magic Resistance",
            statsPt = "+20 Dano de Ataque • +20 Resistência Mágica",
            passive = "Hexdrinker",
            passiveEn = "Hexdrinker",
            passivePt = "Hexdrinker",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986547_3155.png"
        ))
        add(WildRiftItem(
            id = "negatron_cloak_mid_tier",
            name = "Manto de Negatrones",
            nameEn = "Manto de Negatrones",
            namePt = "Manto de Negatrones",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Resistencia Mágica",
            statsEn = "+40 Magic Resistance",
            statsPt = "+40 Resistência Mágica",
            passive = "Negatron Cloak",
            passiveEn = "Negatron Cloak",
            passivePt = "Negatron Cloak",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986529_1057.png"
        ))
        add(WildRiftItem(
            id = "glacial_shroud_mid_tier",
            name = "Manto Glacial",
            nameEn = "Manto Glacial",
            namePt = "Manto Glacial",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Armadura • +150 Maná Máximo • +10 Aceleración de Habilidad",
            statsEn = "+20 Armor • +150 Max Mana • +10 Ability Haste",
            statsPt = "+20 Armadura • +150 Mana Máxima • +10 Aceleração de Habilidade",
            passive = "+150 Maná Máximo",
            passiveEn = "+150 Maná Máximo",
            passivePt = "+150 Mana Máxima",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986572_3024.png"
        ))
        add(WildRiftItem(
            id = "winged_moonplate_mid_tier",
            name = "Placa Lunar Alada",
            nameEn = "Placa Lunar Alada",
            namePt = "Placa Lunar Alada",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+150 Vida Máxima",
            statsEn = "+150 Max Health",
            statsPt = "+150 Vida Máxima",
            passive = "Flight:  +5% Velocidad de Movimiento.",
            passiveEn = "Flight:  +5% Velocidad de Movimiento.",
            passivePt = "Flight:  +5% Velocidade de Movimento.",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616981123_winged_moonplate_item_hd-min.png"
        ))
        add(WildRiftItem(
            id = "nashor_s_talon_mid_tier",
            name = "Garra de Nashor",
            nameEn = "Garra de Nashor",
            namePt = "Garra de Nashor",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "",
            statsEn = "",
            statsPt = "",
            passive = "Gain Daño de Ataque or Poder de Habilidad\nMagic Needle: Gain 15 Daño de Ataque or 30 Poder de Habilidad (adaptive).",
            passiveEn = "Gain Daño de Ataque or Poder de Habilidad\nMagic Needle: Gain 15 Daño de Ataque or 30 Poder de Habilidad (adaptive).",
            passivePt = "Gain Dano de Ataque or Poder de Habilidade\nMagic Needle: Gain 15 Dano de Ataque or 30 Poder de Habilidade (adaptive).",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698258000_nashors-talon.png"
        ))
        add(WildRiftItem(
            id = "noonquiver_mid_tier",
            name = "Carcaj de Mediodía",
            nameEn = "Carcaj de Mediodía",
            namePt = "Carcaj de Mediodía",
            category = "Nivel Medio",
            goldCost = 1350,
            stats = "+25 Daño de Ataque • +15% Velocidad de Ataque",
            statsEn = "+25 Attack Damage • +15% Attack Speed",
            statsPt = "+25 Dano de Ataque • +15% Velocidade de Ataque",
            passive = "Noonquiver",
            passiveEn = "Noonquiver",
            passivePt = "Noonquiver",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-05/1685137714_noonquiver_item.webp"
        ))
        add(WildRiftItem(
            id = "hextech_alternator_mid_tier",
            name = "Alternador Hextech",
            nameEn = "Alternador Hextech",
            namePt = "Alternador Hextech",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+45 Poder de Habilidad",
            statsEn = "+45 Ability Power",
            statsPt = "+45 Poder de Habilidade",
            passive = "Revved: Damaging abilities and empowered attacks against Campeones deal 25-60 Adicional Daño Mágico. (20s Enfriamiento)",
            passiveEn = "Revved: Damaging abilities and empowered attacks against Campeones deal 25-60 Adicional Daño Mágico. (20s Enfriamiento)",
            passivePt = "Revved: Damaging abilities and empowered attacks against Campeões deal 25-60 Adicional Dano Mágico. (20s Tempo de Recarga)",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698176528_hextech_alternator_item.webp"
        ))
        add(WildRiftItem(
            id = "mejai_s_soulstealer_mid_tier",
            name = "Robaalmas de Mejai",
            nameEn = "Mejai's Soulstealer",
            namePt = "Ladrão de Almas de Mejai",
            category = "Nivel Medio",
            goldCost = 1800,
            stats = "+70 Vida Máxima • +25 Poder de Habilidad",
            statsEn = "+70 Max Health • +25 Ability Power",
            statsPt = "+70 Vida Máxima • +25 Poder de Habilidade",
            passive = "Glory: Gain up to 30 stacks of Glory after a Campeón takedown. Cuerpo a cuerpo Campeones gain 3 stack(s) for each kill and 2 stack(s) for each assist; A distancia Campeones gain 4 stack(s) for each kill and 2 stack(s) for each assist. You lose 10 stack(s) on death.\nFear: Gain 5 AP for every stack of Glory you have. At 10 stack(s) of Glory and above, gain 10% Adicional Velocidad de Movimiento.\n\n💡 Consejos del Coach: Este objeto convierte takedowns and assists into potent scaling power — collected stacks boost your spell damage and grant movement benefits at high stack counts. It’s a high-risk, high-reward choice: ideal for aggressive midlaners and snowballing Campeones who secure kills frequently, but vulnerable to heavy setbacks on death. Perfect when you can stay alive and keep accumulating advantages.",
            passiveEn = "Glory: Gain up to 30 stacks of Glory after a Campeón takedown. Cuerpo a cuerpo Campeones gain 3 stack(s) for each kill and 2 stack(s) for each assist; A distancia Campeones gain 4 stack(s) for each kill and 2 stack(s) for each assist. You lose 10 stack(s) on death.\nFear: Gain 5 AP for every stack of Glory you have. At 10 stack(s) of Glory and above, gain 10% Adicional Velocidad de Movimiento.\n\n💡 Coach Tips: Este objeto convierte takedowns and assists into potent scaling power — collected stacks boost your spell damage and grant movement benefits at high stack counts. It’s a high-risk, high-reward choice: ideal for aggressive midlaners and snowballing Campeones who secure kills frequently, but vulnerable to heavy setbacks on death. Perfect when you can stay alive and keep accumulating advantages.",
            passivePt = "Glory: Gain up to 30 stacks of Glory after a Campeão takedown. Corpo a corpo Campeões gain 3 stack(s) for each kill and 2 stack(s) for each assist; À distância Campeões gain 4 stack(s) for each kill and 2 stack(s) for each assist. You lose 10 stack(s) on death.\nFear: Gain 5 AP for every stack of Glory you have. At 10 stack(s) of Glory and above, gain 10% Adicional Velocidade de Movimento.\n\n💡 Dicas do Coach: Este item converte takedowns and assists into potent scaling power — collected stacks boost your spell damage and grant movement benefits at high stack counts. It’s a high-risk, high-reward choice: ideal for aggressive midlaners and snowballing Campeões who secure kills frequently, but vulnerable to heavy setbacks on death. Perfect when you can stay alive and keep accumulating advantages.",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698177176_mejai27s_soulstealer_item_hd.webp"
        ))
        add(WildRiftItem(
            id = "surging_scales_mid_tier",
            name = "Escamas Crecientes",
            nameEn = "Escamas Crecientes",
            namePt = "Escamas Crecientes",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+40 Armadura",
            statsEn = "+40 Armor",
            statsPt = "+40 Armadura",
            passive = "In-combat Ralentización Resist\nSurge: Gain 20% Ralentización Resist while En combate with an campeón enemigo.",
            passiveEn = "In-combat Ralentización Resist\nSurge: Gain 20% Ralentización Resist while En combate with an campeón enemigo.",
            passivePt = "In-combat Ralentización Resist\nSurge: Gain 20% Ralentización Resist while En combate with an campeão inimigo.",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698258154_surging-scales.png"
        ))
        add(WildRiftItem(
            id = "forbidden_idol_mid_tier",
            name = "Ídolo Prohibido",
            nameEn = "Ídolo Prohibido",
            namePt = "Ídolo Prohibido",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+100 Vida Máxima • +5 Aceleración de Habilidad • +4% Heal and Shield Strength • +25% Regeneración de Maná",
            statsEn = "+100 Max Health • +5 Ability Haste • +4% Heal and Shield Strength • +25% Mana Regen",
            statsPt = "+100 Vida Máxima • +5 Aceleração de Habilidade • +4% Heal and Shield Strength • +25% Regeneração de Mana",
            passive = "+4% Heal and Escudo Strength",
            passiveEn = "+4% Heal and Escudo Strength",
            passivePt = "+4% Heal and Escudo Strength",
            iconUrl = "https://wr-meta.com/uploads/posts/2024-12/1735325004_forbidden_idol_item_hd.webp"
        ))
        add(WildRiftItem(
            id = "fated_ashes_mid_tier",
            name = "Cenizas Fatídicas",
            nameEn = "Cenizas Fatídicas",
            namePt = "Cenizas Fatídicas",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Poder de Habilidad",
            statsEn = "+40 Ability Power",
            statsPt = "+40 Poder de Habilidade",
            passive = "Kindle: Damaging abilities deal 5 Adicional Daño Mágico over 3 segundos.\nDeals an additional 15 Daño Mágico to Monstruos.",
            passiveEn = "Kindle: Damaging abilities deal 5 Adicional Daño Mágico over 3 segundos.\nDeals an additional 15 Daño Mágico to Monstruos.",
            passivePt = "Kindle: Damaging abilities deal 5 Adicional Dano Mágico over 3 segundos.\nDeals an additional 15 Dano Mágico to Monstros.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783190595_fated-ashes.webp"
        ))
        add(WildRiftItem(
            id = "void_amethyst_mid_tier",
            name = "Amatista del Vacío",
            nameEn = "Amatista del Vacío",
            namePt = "Amatista del Vacío",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Poder de Habilidad • +10% Penetración Mágica",
            statsEn = "+20 Ability Power • +10% Magic Penetration",
            statsPt = "+20 Poder de Habilidade • +10% Penetração Mágica",
            passive = "+10% Penetración Mágica",
            passiveEn = "+10% Penetración Mágica",
            passivePt = "+10% Penetração Mágica",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783190939_void_amethyst.webp"
        ))
        add(WildRiftItem(
            id = "verdant_barrier_mid_tier",
            name = "Barrera Frondosa",
            nameEn = "Barrera Frondosa",
            namePt = "Barrera Frondosa",
            category = "Nivel Medio",
            goldCost = 1600,
            stats = "+40 Poder de Habilidad • +25 Resistencia Mágica",
            statsEn = "+40 Ability Power • +25 Magic Resistance",
            statsPt = "+40 Poder de Habilidade • +25 Resistência Mágica",
            passive = "Annul: Grants a spell Escudo that blocks the next enemy ability. (50s Enfriamiento)",
            passiveEn = "Annul: Grants a spell Escudo that blocks the next enemy ability. (50s Enfriamiento)",
            passivePt = "Annul: Grants a spell Escudo that blocks the next enemy ability. (50s Tempo de Recarga)",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191133_verdant_barrier_item_hd_11zon.png"
        ))
        add(WildRiftItem(
            id = "boots_of_speed_basic",
            name = "Botas de Velocidad",
            nameEn = "Boots of Speed",
            namePt = "Botas da Velocidade",
            category = "Básicos",
            goldCost = 400,
            stats = "+25 Velocidad de Movimiento.",
            statsEn = "+25 Move Speed.",
            statsPt = "+25 Velocidade de Movimento.",
            passive = "Boots of Speed",
            passiveEn = "Boots of Speed",
            passivePt = "Boots of Speed",
            iconUrl = "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp"
        ))
        add(WildRiftItem(
            id = "long_sword_basic",
            name = "Espada Larga",
            nameEn = "Espada Larga",
            namePt = "Espada Larga",
            category = "Básicos",
            goldCost = 500,
            stats = "+12 Daño de Ataque",
            statsEn = "+12 Attack Damage",
            statsPt = "+12 Dano de Ataque",
            passive = "Long Sword",
            passiveEn = "Long Sword",
            passivePt = "Long Sword",
            iconUrl = "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp"
        ))
        add(WildRiftItem(
            id = "brawler_s_gloves_basic",
            name = "Guantes de Peleador",
            nameEn = "Guantes de Peleador",
            namePt = "Guantes de Peleador",
            category = "Básicos",
            goldCost = 500,
            stats = "+10% Probabilidad de Crítico",
            statsEn = "+10% Critical Rate",
            statsPt = "+10% Chance de Crítico",
            passive = "Brawler's Gloves",
            passiveEn = "Brawler's Gloves",
            passivePt = "Brawler's Gloves",
            iconUrl = "https://i.postimg.cc/QBJq8zsr/1753390558-brawlers-gloves.webp"
        ))
        add(WildRiftItem(
            id = "dagger_basic",
            name = "Daga",
            nameEn = "Daga",
            namePt = "Daga",
            category = "Básicos",
            goldCost = 500,
            stats = "+15% Velocidad de Ataque",
            statsEn = "+15% Attack Speed",
            statsPt = "+15% Velocidade de Ataque",
            passive = "Dagger",
            passiveEn = "Dagger",
            passivePt = "Dagger",
            iconUrl = "https://i.postimg.cc/CZgNV0y5/1753390550-dagger.webp"
        ))
        add(WildRiftItem(
            id = "shimmering_spark_basic",
            name = "Chispa Reluciente",
            nameEn = "Chispa Reluciente",
            namePt = "Chispa Reluciente",
            category = "Básicos",
            goldCost = 500,
            stats = "+50 Vida Máxima",
            statsEn = "+50 Max Health",
            statsPt = "+50 Vida Máxima",
            passive = "Burn: Deals 5-10 Daño Mágico per segundo to nearby enemies.",
            passiveEn = "Burn: Deals 5-10 Daño Mágico per segundo to nearby enemies.",
            passivePt = "Burn: Deals 5-10 Dano Mágico per segundo to nearby enemies.",
            iconUrl = "https://i.postimg.cc/bGhTjPXv/1753390582-shimmering-spark.webp"
        ))
        add(WildRiftItem(
            id = "tear_of_the_goddess_basic",
            name = "Lágrima de la Diosa",
            nameEn = "Lágrima de la Diosa",
            namePt = "Lágrima de la Diosa",
            category = "Básicos",
            goldCost = 500,
            stats = "+200 Maná Máximo • +5 Aceleración de Habilidad",
            statsEn = "+200 Max Mana • +5 Ability Haste",
            statsPt = "+200 Mana Máxima • +5 Aceleração de Habilidade",
            passive = "Increases Maná\n+200 Maná Máximo\nAwe: 10% of Maná spent is refunded.\nManá Charge: Increases Maná Máximo by 6 every time Maná is spent. Hasta un máximo de 700 Adicional Maná. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.",
            passiveEn = "Increases Maná\n+200 Maná Máximo\nAwe: 10% of Maná spent is refunded.\nManá Charge: Increases Maná Máximo by 6 every time Maná is spent. Hasta un máximo de 700 Adicional Maná. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.",
            passivePt = "Increases Maná\n+200 Mana Máxima\nAwe: 10% of Maná spent is refunded.\nManá Charge: Increases Mana Máxima by 6 every time Maná is spent. Hasta un máximo de 700 Adicional Maná. Se activa hasta 3 veces cada 10 segundos. Solo puedes portar un objeto de Lágrima de la Diosa a la vez.",
            iconUrl = "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png"
        ))
        add(WildRiftItem(
            id = "amplifying_tome_basic",
            name = "Tomo Amplificador",
            nameEn = "Tomo Amplificador",
            namePt = "Tomo Amplificador",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Poder de Habilidad",
            statsEn = "+20 Ability Power",
            statsPt = "+20 Poder de Habilidade",
            passive = "Amplifying Tome",
            passiveEn = "Amplifying Tome",
            passivePt = "Amplifying Tome",
            iconUrl = "https://i.postimg.cc/qtTLdrfM/1753390572-amplifying-tome.webp"
        ))
        add(WildRiftItem(
            id = "ruby_crystal_basic",
            name = "Cristal de Rubí",
            nameEn = "Cristal de Rubí",
            namePt = "Cristal de Rubí",
            category = "Básicos",
            goldCost = 500,
            stats = "+150 Vida Máxima",
            statsEn = "+150 Max Health",
            statsPt = "+150 Vida Máxima",
            passive = "Ruby Crystal",
            passiveEn = "Ruby Crystal",
            passivePt = "Ruby Crystal",
            iconUrl = "https://i.postimg.cc/rdkJLT6y/1753390626-ruby-crystal.webp"
        ))
        add(WildRiftItem(
            id = "cloth_armor_basic",
            name = "Armadura de Tela",
            nameEn = "Armadura de Tela",
            namePt = "Armadura de Tela",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Armadura",
            statsEn = "+20 Armor",
            statsPt = "+20 Armadura",
            passive = "Cloth Armadura",
            passiveEn = "Cloth Armadura",
            passivePt = "Cloth Armadura",
            iconUrl = "https://i.postimg.cc/3yTBHhM8/1753390581-cloth-armor.webp"
        ))
        add(WildRiftItem(
            id = "null_magic_mantle_basic",
            name = "Manto Anulamagia",
            nameEn = "Manto Anulamagia",
            namePt = "Manto Anulamagia",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Resistencia Mágica",
            statsEn = "+20 Magic Resistance",
            statsPt = "+20 Resistência Mágica",
            passive = "Null-Magic Mantle",
            passiveEn = "Null-Magic Mantle",
            passivePt = "Null-Magic Mantle",
            iconUrl = "https://i.postimg.cc/4Kg5TGCJ/1753390606-null-magic-mantle.webp"
        ))
        add(WildRiftItem(
            id = "ring_of_revelation_basic",
            name = "Anillo de Revelación",
            nameEn = "Anillo de Revelación",
            namePt = "Anillo de Revelación",
            category = "Básicos",
            goldCost = 300,
            stats = "+5 Aceleración de Habilidad",
            statsEn = "+5 Ability Haste",
            statsPt = "+5 Aceleração de Habilidade",
            passive = "Reduces ability cooldowns",
            passiveEn = "Reduces ability cooldowns",
            passivePt = "Reduces ability cooldowns",
            iconUrl = "https://i.postimg.cc/wtpVdzKH/1753390605-ring-of-revelation.webp"
        ))
        add(WildRiftItem(
            id = "relic_shield_basic",
            name = "Escudo Reliquia",
            nameEn = "Relic Shield",
            namePt = "Escudo Relíquia",
            category = "Básicos",
            goldCost = 500,
            stats = "+125 Vida Máxima",
            statsEn = "+125 Max Health",
            statsPt = "+125 Vida Máxima",
            passive = "Kill Súbditos to earn Adicional gold\nThis item is for support players. When equipped, it will reduce the gold you receive from killing Súbditos and Monstruos. If there are multiples of this item within the party, only one of them can take effect at any given time.\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeones enemigos or structures.\n2. Attacking Súbditos below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain and binds you and the ally with the most Tribute stacks as Perfect Partners.",
            passiveEn = "Kill Súbditos to earn Adicional gold\nThis item is for support players. When equipped, it will reduce the gold you receive from killing Súbditos and Monstruos. If there are multiples of this item within the party, only one of them can take effect at any given time.\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeones enemigos or structures.\n2. Attacking Súbditos below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain and binds you and the ally with the most Tribute stacks as Perfect Partners.",
            passivePt = "Kill Tropas to earn Adicional gold\nThis item is for support players. When equipped, it will reduce the gold you receive from killing Tropas and Monstros. If there are multiples of this item within the party, only one of them can take effect at any given time.\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeões inimigos or structures.\n2. Attacking Tropas below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain and binds you and the ally with the most Tribute stacks as Perfect Partners.",
            iconUrl = "https://i.postimg.cc/sBrcRzFs/1753390612-relic-shield.webp"
        ))
        add(WildRiftItem(
            id = "spectral_sickle_basic",
            name = "Hoz Espectral",
            nameEn = "Spectral Sickle",
            namePt = "Foice Espectral",
            category = "Básicos",
            goldCost = 500,
            stats = "Quest:",
            statsEn = "Quest:",
            statsPt = "Quest:",
            passive = "This item is for support players. When equipped, it will reduce the gold you receive from killing Súbditos and Monstruos. If there are multiples of this item within the party, only one of them can take effect at any given time.\nVersatile: Gain 10 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeones enemigos or structures.\n2. Attacking Súbditos below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.",
            passiveEn = "This item is for support players. When equipped, it will reduce the gold you receive from killing Súbditos and Monstruos. If there are multiples of this item within the party, only one of them can take effect at any given time.\nVersatile: Gain 10 Daño de Ataque or 20 Poder de Habilidad (Adaptive).\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeones enemigos or structures.\n2. Attacking Súbditos below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.",
            passivePt = "This item is for support players. When equipped, it will reduce the gold you receive from killing Tropas and Monstros. If there are multiples of this item within the party, only one of them can take effect at any given time.\nVersatile: Gain 10 Dano de Ataque or 20 Poder de Habilidade (Adaptive).\nTribute: Gain 1 encircling energy orb(s) every 30 segundos (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Vida 20-80:\n1. Using abilities or attacks to damage campeões inimigos or structures.\n2. Attacking Tropas below 65% Vida. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.",
            iconUrl = "https://i.postimg.cc/2qDwfYpY/1753390656-spectral-sickle.webp"
        ))
    }

    private val normalizedItemMap: Map<String, WildRiftItem> by lazy {
        val map = mutableMapOf<String, WildRiftItem>()
        list.forEach { item ->
            map[normalizeKey(item.id)] = item
            map[normalizeKey(item.name)] = item
            if (item.nameEn.isNotBlank()) map[normalizeKey(item.nameEn)] = item
            if (item.namePt.isNotBlank()) map[normalizeKey(item.namePt)] = item
        }
        map
    }

    private fun normalizeKey(raw: String): String {
        return raw.lowercase()
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u")
            .replace("ã", "a").replace("õ", "o").replace("ç", "c").replace("ñ", "n")
            .replace(Regex("[^a-z0-9]"), "")
    }

    fun getItemById(id: String): WildRiftItem? {
        return list.firstOrNull { it.id.equals(id, ignoreCase = true) }
    }

    fun getItemByName(name: String): WildRiftItem? {
        val normalized = normalizeKey(name)
        normalizedItemMap[normalized]?.let { return it }
        
        return list.firstOrNull { item ->
            val normItemName = normalizeKey(item.name)
            val normItemNameEn = normalizeKey(item.nameEn)
            val normItemNamePt = normalizeKey(item.namePt)
            val normId = normalizeKey(item.id)
            
            normItemName == normalized ||
            normItemNameEn == normalized ||
            normItemNamePt == normalized ||
            normId == normalized ||
            (normalized.length >= 4 && normItemName.contains(normalized)) ||
            (normalized.length >= 4 && normalized.contains(normItemName)) ||
            (normalized.length >= 4 && normItemNameEn.contains(normalized)) ||
            (normalized.length >= 4 && normalized.contains(normItemNameEn))
        }
    }

    fun getItemIconByName(name: String): String {
        val item = getItemByName(name)
        if (item != null && item.iconUrl.isNotBlank()) {
            return item.iconUrl
        }
        return "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/1001.png"
    }

    fun getItemsByCategory(category: String): List<WildRiftItem> {
        return list.filter { it.category.equals(category, ignoreCase = true) }
    }
}
