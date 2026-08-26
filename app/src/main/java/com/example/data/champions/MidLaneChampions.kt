package com.example.data.champions

import com.example.model.Champion
import com.example.model.ChampionSkill
import com.example.model.DamageType
import com.example.model.LaneRole
import com.example.model.ItemSwap

object MidLaneChampions {
    val list: List<Champion> = listOf(
        Champion(
            id = "ahri",
            name = "Ahri",
            title = "La Mujer Zorro de nueve Colas",
            ddragonId = "Ahri",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ahri.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 49.59,
            pickRate = 6.68,
            banRate = 0.43,
            damageType = DamageType.MAGIC,
            summary = "Ahri es una raposa vastaya conectada de forma innata a la magia del reino de los espíritus. Es capaz de manipular las emociones de su presa antes de consumir su esencia, proceso que le transmite los recuerdos de cada alma que consume. Otrora una...",
            advantageAgainst = listOf("Akali", "Katarina", "Ahri"),
            counteredBy = listOf("Yasuo", "Zed", "Fizz"),
            synergies = listOf("Vi", "Jarvan IV", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ahri en MID. Coordina el uso de su Impulso espiritual para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Robo de esencias",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ahri_SoulEater2.png",
                    description = "Ahri se cura tras matar a 9 súbditos o monstruos.Ahri se cura más cantidad tras eliminar a un campeón enemigo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Orbe del engaño",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriQ.png",
                    description = "Ahri lanza y recupera su orbe, lo que inflige daño mágico al lanzarlo y daño verdadero al recuperarlo.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Fuego zorruno",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriW.png",
                    description = "Ahri obtiene una breve mejora de velocidad de movimiento y lanza tres fuegos zorrunos que fijan y atacan a los enemigos cercanos.",
                    cooldown = "9/8/7/6/5s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Hechizar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriE.png",
                    description = "Ahri lanza un beso que daña y hechiza al enemigo al que alcance primero, con lo que cancela instantáneamente todas sus habilidades de movimiento y hace que avance hacia ella de forma inofensiva.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Impulso espiritual",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriR.png",
                    description = "Ahri se desliza hacia delante y libera rayos de esencia que infligen daño a los enemigos cercanos. Impulso espiritual puede usarse hasta tres veces antes de entrar en enfriamiento y las eliminaciones de campeones enemigos otorgan lanzamientos adicionales.",
                    cooldown = "130/115/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ahri",
            wrMetaUrl = "https://wr-meta.com/champion/ahri/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ahri/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ahri"
        ),
        Champion(
            id = "akali",
            name = "Akali",
            title = "la Asesina Sigilosa",
            ddragonId = "Akali",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Akali.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 46.47,
            pickRate = 5.08,
            banRate = 0.53,
            damageType = DamageType.MAGIC,
            summary = "Tras abandonar la Orden Kinkou y su título de Puño de la Sombra, Akali actúa ahora en solitario y está lista para convertirse en el arma mortal que necesita su gente. Aunque no renuncia a las enseñanzas de Shen, su maestro, ha jurado defender Jonia de...",
            advantageAgainst = listOf("Yasuo", "Zed", "Katarina"),
            counteredBy = listOf("Galio", "Annie", "Lissandra"),
            synergies = listOf("Amumu", "Amumu", "Leona"),
            tacticalAdvice = "Aprovecha el escalado y combos de Akali en MID. Coordina el uso de su Ejecución perfecta para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Marca del asesino",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Akali_P.png",
                    description = "Infligir daño de hechizo a un campeón crea un anillo de energía a su alrededor. Salir del anillo potencia el próximo ataque de Akali con alcance y daño adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Pleno de cinco puntas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliQ.png",
                    description = "Akali lanza cinco kunais que ralentizan e infligen daño en función de su daño de ataque y poder de habilidad adicionales.",
                    cooldown = "1.5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Velo del crepúsculo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliW.png",
                    description = "Akali lanza una cortina de humo y obtiene brevemente velocidad de movimiento. Mientras se encuentra dentro del área, Akali se vuelve invisible, no puede ser seleccionada como objetivo de hechizos ni ataques enemigos. Si ataca o usa habilidades, se revela durante unos instantes.",
                    cooldown = "20/19/18/17/16s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Voltereta shuriken",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliE.png",
                    description = "Da una voltereta hacia atrás y lanza un shuriken hacia adelante que inflige daño mágico. La primera nube de humo o enemigo golpeado queda marcado. Puede volver a usarse para desplazarse hasta el objetivo marcado e infligir daño adicional.",
                    cooldown = "16/14.5/13/11.5/10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Ejecución perfecta",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliR.png",
                    description = "Akali salta hacia una dirección y daña a los enemigos golpeados. Relanzamiento: Akali se desliza hacia una dirección y ejecuta a todos los enemigos golpeados.",
                    cooldown = "120/90/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/akali",
            wrMetaUrl = "https://wr-meta.com/champion/akali/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/akali/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/akali"
        ),
        Champion(
            id = "akshan",
            name = "Akshan",
            title = "el Centinela Rebelde",
            ddragonId = "Akshan",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Akshan.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "A",
            winrate = 50.1,
            pickRate = 13.3,
            banRate = 4.3,
            damageType = DamageType.PHYSICAL,
            summary = "Impávido ante el peligro, Akshan combate el mal con gran carisma, ganas de impartir justa venganza y una evidente falta de camisetas. Domina el arte del combate sigiloso, por lo que es capaz de desaparecer a los ojos de sus enemigos y volver a aparecer...",
            advantageAgainst = listOf("Kassadin", "Ekko", "Katarina"),
            counteredBy = listOf("Yasuo", "Zed", "Irelia"),
            synergies = listOf("Nami", "Lulu", "Yuumi"),
            tacticalAdvice = "Aprovecha el escalado y combos de Akshan en MID. Coordina el uso de su Merecido para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Artimañas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/akshan_p.png",
                    description = "Cada tres impactos de ataques y habilidades contra campeones, Akshan inflige daño adicional y obtiene un escudo.Cuando Akshan ataca, lanza un ataque adicional que inflige daño reducido. Si cancela el ataque adicional, obtiene velocidad de movimiento.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Bumerán vengador",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanQ.png",
                    description = "Akshan lanza un bumerán que inflige daño a la ida y a la vuelta. Además, su alcance aumenta con cada enemigo golpeado.",
                    cooldown = "8/7.25/6.5/5.75/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Rebelde",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanW.png",
                    description = "Akshan marca de forma pasiva a los campeones enemigos como canallas cuando matan a sus aliados. Si Akshan mata a un canalla, revive a los aliados que haya asesinado, obtiene oro adicional y elimina todas las marcas.Al activarse, Akshan entra en estado de camuflaje y obtiene velocidad de movimiento y regeneración de maná mientras se mueve hacia los canallas. Akshan pierde el camuflaje rápidamente mientras no está en la maleza o cerca de un obstáculo del terreno.",
                    cooldown = "18/14/10/6/2s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Balanceo heroico",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanE.png",
                    description = "Akshan lanza un gancho hacia un obstáculo del terreno y ataca repetidamente al enemigo más cercano conforme se balancea. Se puede soltar de la cuerda cuando quiera o al chocar contra campeones u obstáculo del terreno.",
                    cooldown = "18/16.5/15/13.5/12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Merecido",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanR.png",
                    description = "Akshan fija a un campeón enemigo como objetivo y empieza a acumular balas. Cuando se lanza la habilidad, dispara todas las balas acumuladas, lo que inflige daño según la vida que le falte al primer campeón, súbdito o estructura golpeados.",
                    cooldown = "100/85/70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/akshan",
            wrMetaUrl = "https://wr-meta.com/champion/akshan/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/akshan/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/akshan"
        ),
        Champion(
            id = "annie",
            name = "Annie",
            title = "La Hija de la Oscuridad",
            ddragonId = "Annie",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Annie.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 47.04,
            pickRate = 2.41,
            banRate = 0.17,
            damageType = DamageType.MAGIC,
            summary = "Peligrosa pero encantadoramente precoz, Annie es una pequeña maga con un inmenso poder piromántico. Incluso en los parajes montañosos al norte de Noxus es una hechicera sin precedentes. Su afinidad natural con el fuego se manifestó a temprana edad en...",
            advantageAgainst = listOf("Sona", "Soraka", "Nami"),
            counteredBy = listOf("Leona", "Nautilus", "Blitzcrank"),
            synergies = listOf("Jhin", "Miss Fortune", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Annie en MID. Coordina el uso de su Invocar: Tibbers para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Piromanía",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Annie_Passive.png",
                    description = "Después de 4 lanzamientos, el siguiente hechizo ofensivo de Annie aturdirá al objetivo.Annie comienza la partida y reaparece con Piromanía disponible.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Desintegración",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieQ.png",
                    description = "Annie lanza una bola de fuego imbuida de maná que daña al objetivo y le devuelve el maná gastado si este resulta destruido.",
                    cooldown = "4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Incineración",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieW.png",
                    description = "Annie lanza un abrasador cono de fuego, dañando a todos los enemigos de la zona.",
                    cooldown = "8s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Escudo fundido",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieE.png",
                    description = "Otorga a Annie o a un aliado un escudo y velocidad de movimiento adicional de forma temporal, y daña a los enemigos que le inflijan daño con ataques o hechizos.",
                    cooldown = "12/11.5/11/10.5/10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Invocar: Tibbers",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieR.png",
                    description = "Annie da vida a su oso Tibbers, que daña a todas las unidades de la zona. Tibbers puede atacar y quemar a los enemigos adyacentes.",
                    cooldown = "130/115/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/annie",
            wrMetaUrl = "https://wr-meta.com/champion/annie/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/annie/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/annie"
        ),
        Champion(
            id = "aurelion_sol",
            name = "Aurelion Sol",
            title = "El Forjador de Estrellas",
            ddragonId = "AurelionSol",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/AurelionSol.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 51.8,
            pickRate = 8.4,
            banRate = 10.1,
            damageType = DamageType.MAGIC,
            summary = "Aurelion Sol solía agraciar al vasto vacío del cosmos con las maravillas celestiales que él mismo ideaba. Ahora, se ve forzado a hacer uso de su increíble poder para satisfacer los deseos de un imperio espacial que lo ha engañado para convertirlo en su...",
            advantageAgainst = listOf("Katarina", "Akali", "Talon"),
            counteredBy = listOf("Zed", "Fizz", "Kassadin"),
            synergies = listOf("Leona", "Nautilus", "Thresh"),
            tacticalAdvice = "Aprovecha el escalado y combos de Aurelion Sol en MID. Coordina el uso de su Estrella fugaz / Ocaso celeste para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Creador cósmico",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/AurelionSolP.png",
                    description = "Las habilidades de daño de Aurelion Sol cosechan acumulaciones de polvo estelar de sus enemigos, lo que mejora permanentemente cada habilidad.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "El aliento de los dioses",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolQ.png",
                    description = "Aurelion Sol canaliza su aliento de dragón durante unos segundos, lo que inflige daño al primer enemigo golpeado y daño reducido a los enemigos cercanos. Cada segundo que el aliento se canalice directamente a un enemigo, infligirá daño adicional, que mejora según la cantidad de polvo estelar conseguido. Esta habilidad otorga polvo estelar si el objetivo es un campeón.",
                    cooldown = "3s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Vuelo astral",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolW.png",
                    description = "Aurelion Sol sobrevuela el terreno en la dirección indicada. En este estado, puede lanzar otras habilidades. El aliento de los dioses ya no tiene enfriamiento, la puede canalizar sin límite de tiempo e inflige daño adicional mientras vuela.El enfriamiento restante de Vuelo astral se reduce cada vez que muere un campeón tras recibir daño de Aurelion Sol.El polvo estelar aumenta el alcance máximo de Vuelo astral.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Singularidad",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolE.png",
                    description = "Aurelion Sol invoca un agujero negro, lo que inflige daño a los enemigos y los atrae lentamente hacia su centro. Esta habilidad otorga polvo estelar cada vez que muere un enemigo en el agujero negro y por cada segundo que haya un campeón enemigo en su interior. El centro del agujero negro ejecuta a los enemigos que estén por debajo de cierto porcentaje de su vida máxima. El polvo estelar aumenta la zona de Singularidad y el umbral de ejecución.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Estrella fugaz / Ocaso celeste",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolR.png",
                    description = "Estrella fugaz: Aurelion Sol provoca que una estrella se estampe contra el suelo. Este impacto inflige daño mágico y aturde a los enemigos. Además, otorga polvo estelar por cada campeón golpeado. Al conseguir polvo estelar suficiente, transforma la siguiente Estrella fugaz de Aurelion Sol en Ocaso celeste.Ocaso celeste: Aurelion Sol lanza una gigantesca estrella con un área de impacto aumentada, potencia el daño infligido y lanza a los enemigos por los aires, en lugar de aturdirlos. Después, emite una enorme onda de choque desde los bordes del área de impacto, lo que inflige daño y ralentiza a los campeones golpeados. El polvo estelar aumenta el área de impacto de Estrella fugaz y Ocaso celeste.",
                    cooldown = "120/110/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aurelion-sol",
            wrMetaUrl = "https://wr-meta.com/champion/aurelion-sol/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/aurelion-sol/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/aurelion-sol"
        ),
        Champion(
            id = "aurora",
            name = "Aurora",
            title = "la Bruja entre Mundos",
            ddragonId = "Aurora",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Aurora.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "D",
            winrate = 49.42,
            pickRate = 3.45,
            banRate = 1.82,
            damageType = DamageType.MAGIC,
            summary = "Desde que nació, Aurora ha tenido una visión única de la vida gracias a su capacidad para moverse entre los reinos material y espiritual. Decidida a conocer mejor a los habitantes del reino espiritual, abandonó su hogar para ampliar sus conocimientos...",
            advantageAgainst = listOf("Yasuo", "Zed", "Akali"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Amumu", "Malphite", "Jarvan IV"),
            tacticalAdvice = "Aprovecha el escalado y combos de Aurora en MID. Coordina el uso de su Entre mundos para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Abjuración espiritual",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/AuroraPassive.png",
                    description = "Las habilidades y ataques de Aurora exorcizan espíritus de los enemigos a los que inflige daño. Los espíritus exorcizados siguen a Aurora, la curan y le otorgan velocidad de movimiento adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Ánimas malditas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraQ.png",
                    description = "Aurora lanza un proyectil que maldice a los enemigos golpeados. Después, puede reactivar la habilidad para atraer hacia ella las maldiciones activas, lo que inflige daño a los enemigos golpeados por el camino.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "A través del velo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraW.png",
                    description = "Aurora brinca, se adentra en el reino espiritual al aterrizar y se vuelve invisible durante un breve periodo de tiempo.",
                    cooldown = "22/21/20/19/18s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Estallido espectral",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraE.png",
                    description = "Aurora hace converger los reinos y emite un pulso de energía espiritual que inflige daño y ralentiza a los enemigos alcanzados antes de brincar a un lugar seguro.",
                    cooldown = "15/14/13/12/11s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Entre mundos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraR.png",
                    description = "Aurora brinca y emite una onda de choque que inflige daño y ralentiza a los enemigos golpeados. Después, crea un área que atrapa a los enemigos en su interior y permite a Aurora teleportarse de un lado al otro del área.",
                    cooldown = "140/120/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aurora",
            wrMetaUrl = "https://wr-meta.com/champion/aurora/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/aurora/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/aurora"
        ),
        Champion(
            id = "brand",
            name = "Brand",
            title = "La Venganza Ardiente",
            ddragonId = "Brand",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Brand.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "S",
            winrate = 51.83,
            pickRate = 9.49,
            banRate = 10.79,
            damageType = DamageType.MAGIC,
            summary = "Brand, antiguo miembro de la tribu Kegan Rodhe del helado Freljord, es una lección sobre la tentación de un poder mayor. En busca de una de las legendarias Runas Geogénicas, Kegan traicionó a sus compañeros y se quedó con la runa. El hombre desapareció...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Fizz", "Zed", "Katarina"),
            synergies = listOf("Amumu", "Leona", "Nautilus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Brand en SUPPORT. Coordina el uso de su Detonación ígnea para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Nube de fuego",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/BrandP.png",
                    description = "Los hechizos de Brand prenden fuego a sus objetivos, lo que inflige daño durante 4 s. Puede acumularse hasta 3 veces. Si Brand asesina a un enemigo que esté en llamas, recupera maná. Cuando Nube de fuego alcanza el máximo de acumulaciones en un campeón o monstruo gigante, se vuelve inestable. Explota al cabo de 2 s, aplica efectos de hechizo e inflige una gran cantidad de daño en la zona que rodea a la víctima.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Abrasar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandQ.png",
                    description = "Brand lanza una bola de fuego que inflige daño mágico. Si el objetivo está en llamas, Abrasar lo dejará aturdido durante 1,5 s.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Pilar de llamas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandW.png",
                    description = "Tras un breve retardo, Brand crea un Pilar de llamas en la ubicación del objetivo que inflige daño mágico a las unidades enemigas dentro de la misma. Las unidades que estén en llamas recibirán un 25% de daño adicional.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Incendio",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandE.png",
                    description = "Brand lanza un ataque poderoso a su objetivo que se extiende a los enemigos cercanos e inflige daño mágico. Si el objetivo está en llamas, se duplica la propagación de Incendio.",
                    cooldown = "13/12/11/10/9s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Detonación ígnea",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandR.png",
                    description = "Brand libera un torrente de fuego devastador que rebota hasta 5 veces entre Brand y los enemigos cercanos, e inflige daño mágico a los enemigos cada vez que rebota. Estos rebotes priorizan acumular al máximo Nube de fuego en los campeones. Si un objetivo está en llamas, Detonación ígnea lo ralentizará brevemente.",
                    cooldown = "110/100/90s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/brand",
            wrMetaUrl = "https://wr-meta.com/champion/brand/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/brand/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/brand"
        ),
        Champion(
            id = "corki",
            name = "Corki",
            title = "El Bombardero Osado",
            ddragonId = "Corki",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Corki.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.ADC),
            tier = "S+",
            winrate = 52.5,
            pickRate = 5.5,
            banRate = 13.3,
            damageType = DamageType.TRUE_HYBRID,
            summary = "El piloto yordle Corki adora dos cosas por encima de todas las demás: volar y su glamuroso bigote. Aunque no necesariamente en ese orden. Tras abandonar la Ciudad de Bandle, se estableció en Piltover y se enamoró de las máquinas maravillosas que allí...",
            advantageAgainst = listOf("Orianna", "Orianna", "Viktor"),
            counteredBy = listOf("Zed", "Talon", "Fizz"),
            synergies = listOf("Leona", "Nautilus", "Thresh"),
            tacticalAdvice = "Aprovecha el escalado y combos de Corki en MID. Coordina el uso de su Andanada de Proyectiles para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler • Coraza Ósea • Sobrecrecimiento • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Municiones hextech",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Corki_RapidReload.png",
                    description = "Un porcentaje del daño de ataque básico de Corki se inflige como daño verdadero adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Bomba de fósforo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PhosphorusBomb.png",
                    description = "Corki lanza una bomba luminosa a la ubicación seleccionada e inflige daño mágico a los enemigos cercanos. Además, este ataque revela a las unidades de la zona durante un tiempo.",
                    cooldown = "9/8.5/8/7.5/7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Valquiria",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CarpetBomb.png",
                    description = "Corki vuela una corta distancia lanzando bombas y dejando tras de sí una estela de fuego, la cual inflige daño a los enemigos que permanezcan dentro.",
                    cooldown = "20/18/16/14/12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Cañón de repetición",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GGun.png",
                    description = "El cañón de repetición de Corki abre fuego sobre un área cónica. Los enemigos alcanzados sufren daño y pierden armadura y resistencia mágica.",
                    cooldown = "12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Andanada de Proyectiles",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissileBarrage.png",
                    description = "Corki dispara hacia la zona objetivo un proyectil que explota al impactar e inflige daño a los enemigos cercanos. Corki acumula misiles según pasa el tiempo, hasta un máximo determinado. Cada 3 proyectiles sale uno enorme que inflige daño adicional.",
                    cooldown = "2s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/corki",
            wrMetaUrl = "https://wr-meta.com/champion/corki/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/corki/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/corki"
        ),
        Champion(
            id = "fizz",
            name = "Fizz",
            title = "El Gamberro de las Mareas",
            ddragonId = "Fizz",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Fizz.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 49.59,
            pickRate = 3.51,
            banRate = 6.28,
            damageType = DamageType.MAGIC,
            summary = "Fizz es un yordle anfibio que habita entre los arrecifes de alrededor de Aguas Estancadas. Suele recuperar y devolver los diezmos arrojados al mar por capitanes supersticiosos, pero incluso los marineros más agudos saben que no hay que plantarle cara...",
            advantageAgainst = listOf("Syndra", "Orianna", "Veigar"),
            counteredBy = listOf("Galio", "Lissandra", "Kassadin"),
            synergies = listOf("Vi", "Jarvan IV", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Fizz en MID. Coordina el uso de su Carnaza para tiburones para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Luchador ágil",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Fizz_P.png",
                    description = "Fizz se puede mover a través de unidades y reduce una cantidad fija el daño proveniente de cualquier fuente.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Golpe de erizo de mar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzQ.png",
                    description = "Fizz atraviesa a su objetivo, inflige daño mágico y aplica efectos de impacto.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Tridente piedramar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzW.png",
                    description = "Los ataques de Fizz hacen que sus enemigos se desangren y sufran daño mágico durante varios segundos. Fizz puede potenciar su siguiente ataque para infligir daño adicional y potenciar sus siguientes ataques durante un breve periodo de tiempo.",
                    cooldown = "7/6.5/6/5.5/5s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Juguetón/Gamberro",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzE.png",
                    description = "Fizz salta al aire y aterriza grácilmente sobre su lanza, siendo imposible atacarlo. Desde esta posición, Fizz puede dejarse caer para aplastar el suelo u optar por saltar de nuevo antes de hacerlo.",
                    cooldown = "16/14/12/10/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Carnaza para tiburones",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzR.png",
                    description = "Fizz lanza hacia una dirección un pez que se adhiere al primer campeón que toca y lo ralentiza. Tras un momento, sale un tiburón de la tierra que lanza al objetivo por los aires y empuja a un lado a los enemigos cercanos. Todos los enemigos alcanzados sufren daño mágico y son ralentizados.",
                    cooldown = "100/85/70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/fizz",
            wrMetaUrl = "https://wr-meta.com/champion/fizz/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/fizz/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/fizz"
        ),
        Champion(
            id = "galio",
            name = "Galio",
            title = "el Coloso",
            ddragonId = "Galio",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Galio.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "A",
            winrate = 52.17,
            pickRate = 9.55,
            banRate = 2.42,
            damageType = DamageType.MAGIC,
            summary = "Fuera de la reluciente ciudad de Demacia, el coloso de piedra Galio se mantiene vigilante. Construido como un baluarte contra los magos enemigos, suele permanecer inmóvil durante décadas hasta que la presencia de magia poderosa lo vuelve a traer a la...",
            advantageAgainst = listOf("Leona", "Nautilus", "Blitzcrank"),
            counteredBy = listOf("Janna", "Lulu", "Morgana"),
            synergies = listOf("Yasuo", "Samira", "Tristana"),
            tacticalAdvice = "Aprovecha el escalado y combos de Galio en MID. Coordina el uso de su Entrada heroica para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Égida de Fuego Solar", "Apariencia Espiritual", "Botas de Maná", "Sombrero Mortal de Rabadon", "Báculo del Vacío"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Aplastamiento colosal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Galio_Passive.png",
                    description = "Cada pocos segundos, el siguiente ataque básico de Galio inflige daño mágico adicional en un área.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Vientos de guerra",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioQ.png",
                    description = "Galio lanza dos remolinos de viento que se unen y convierten en un gran tornado que inflige daño prolongado.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Escudo de Durand",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioW.png",
                    description = "Galio carga en posición defensiva y se mueve lentamente. Tras liberar la carga, Galio provocará y dañará a los enemigos cercanos.",
                    cooldown = "18/17/16/15/14s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Puñetazo justiciero",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioE.png",
                    description = "Galio dará un pequeño paso hacia atrás, cargará hacia delante y aturdirá al primer enemigo que se encuentre en su camino.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Entrada heroica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioR.png",
                    description = "Galio designa la ubicación de un campeón aliado como punto de aterrizaje y otorga un escudo mágico a todos los aliados en la zona. Tras un breve lapso de tiempo, Galio aterriza en la ubicación y lanza a los enemigos cercanos por los aires.",
                    cooldown = "180/160/140s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/galio",
            wrMetaUrl = "https://wr-meta.com/champion/galio/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/galio/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/galio"
        ),
        Champion(
            id = "heimerdinger",
            name = "Heimerdinger",
            title = "El Inventor Venerado",
            ddragonId = "Heimerdinger",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Heimerdinger.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.SUPPORT),
            tier = "D",
            winrate = 50.69,
            pickRate = 2.1,
            banRate = 1.7,
            damageType = DamageType.MAGIC,
            summary = "El profesor Cecil B. Heimerdinger, un científico yordle excéntrico pero brillante, es considerado una de las mentes más innovadoras y uno de los inventores más admirados de la historia de Piltover. Tiene una dedicación incesante en su trabajo hasta el...",
            advantageAgainst = listOf("Yasuo", "Katarina", "Akali"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Jhin", "Caitlyn", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Heimerdinger en MID. Coordina el uso de su ¡MEJORA! para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Afinidad hextech",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Heimerdinger_Passive.png",
                    description = "Obtén velocidad de movimiento al estar cerca de torres aliadas y de torretas desplegadas por Heimerdinger.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Torreta evolucionada H-28 G",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerQ.png",
                    description = "Heimerdinger despliega una torreta de fuego rápido equipada con un segundo cañón de ataque (las torretas infligen la mitad de daño a las torres).",
                    cooldown = "1s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Microcohetes hextech",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerW.png",
                    description = "Heimerdinger dispara proyectiles de largo alcance que convergen en su cursor.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Granada de tormenta de electrones CH-2",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerE.png",
                    description = "Heimerdinger lanza una granada que causa daño, aturde a las unidades que reciben el impacto de forma directa y ralentiza a las unidades cercanas.",
                    cooldown = "11s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "¡MEJORA!",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerR.png",
                    description = "Heimerdinger inventa una mejora con la que aumenta los efectos de su próxima habilidad.",
                    cooldown = "100/85/70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/heimerdinger",
            wrMetaUrl = "https://wr-meta.com/champion/heimerdinger/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/heimerdinger/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/heimerdinger"
        ),
        Champion(
            id = "kassadin",
            name = "Kassadin",
            title = "El Caminante del Vacío",
            ddragonId = "Kassadin",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kassadin.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.08,
            pickRate = 1.05,
            banRate = 0.12,
            damageType = DamageType.MAGIC,
            summary = "Dejando tras sí una huella ardiente por los lugares más oscuros del mundo, Kassadin sabe que sus días están contados. Guía y aventurero de Shurima que ha viajado por medio mundo, había elegido formar a una familia entre las pacíficas tribus del sur...",
            advantageAgainst = listOf("Katarina", "Akali", "Fizz"),
            counteredBy = listOf("Lucian", "Tristana", "Talon"),
            synergies = listOf("Vi", "Jarvan IV", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kassadin en MID. Coordina el uso de su Camino del Vacío para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Piedra del Vacío",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kassadin_Passive.png",
                    description = "Kassadin recibe menos daño mágico e ignora la colisión con unidades.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Esfera negativa",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NullLance.png",
                    description = "Kassadin dispara una esfera de energía del Vacío contra un objetivo que inflige daño e interrumpe canalizaciones. El excedente de energía envuelve a Kassadin y le otorga un escudo temporal que absorbe daño mágico.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Cuchilla infernal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NetherBlade.png",
                    description = "Pasiva: Los ataques básicos de Kassadin infligen daño mágico adicional. Activa: Los ataques básicos de Kassadin infligen bastante daño mágico adicional y restauran maná.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Pulso de fuerza",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ForcePulse.png",
                    description = "Kassadin extrae energía de los hechizos lanzados cerca de él. Al cargarse, Kassadin puede utilizar Pulso de fuerza para infligir daño y ralentizar a los enemigos que se encuentren en un cono frente a él.",
                    cooldown = "21/20/19/18/17s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Camino del Vacío",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RiftWalk.png",
                    description = "Kassadin se teleporta a un lugar cercano e inflige daño a las unidades enemigas cercanas. Si se utiliza repetidamente Camino del Vacío en poco tiempo, costará más maná, pero también infligirá daño adicional.",
                    cooldown = "5/3.5/2s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kassadin",
            wrMetaUrl = "https://wr-meta.com/champion/kassadin/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kassadin/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kassadin"
        ),
        Champion(
            id = "katarina",
            name = "Katarina",
            title = "La Cuchilla Siniestra",
            ddragonId = "Katarina",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Katarina.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.12,
            pickRate = 1.64,
            banRate = 0.77,
            damageType = DamageType.MAGIC,
            summary = "Con un juicio decisivo y letal en el combate, Katarina es una de las mejores asesinas noxianas. Al ser la primogénita del legendario general Du Couteau, ha dado a conocer sus habilidades con asesinatos rápidos contra enemigos inconscientes. Una ambición...",
            advantageAgainst = listOf("Veigar", "Lux", "Vel'Koz"),
            counteredBy = listOf("Galio", "Annie", "Lissandra"),
            synergies = listOf("Amumu", "Malphite", "Nautilus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Katarina en MID. Coordina el uso de su Loto mortal para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Ansia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Katarina_Passive.png",
                    description = "Cuando muere un campeón que haya recibido daño de Katarina en los últimos segundos, el enfriamiento de sus habilidades se reduce drásticamente.Si Katarina recoge una daga, la usa para acuchillar a todos los enemigos cercanos, lo que inflige daño mágico.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Hoja rebotante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaQ.png",
                    description = "Katarina lanza al objetivo una daga que rebota hacia los enemigos cercanos antes de caer al suelo.",
                    cooldown = "11/10/9/8/7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Preparación",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaW.png",
                    description = "Katarina obtiene un aumento de velocidad de movimiento y lanza una daga al aire encima de ella.",
                    cooldown = "15/14/13/12/11s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Velocidad del rayo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaEWrapper.png",
                    description = "Katarina aparece junto al objetivo. Lo golpea si es un enemigo o, de lo contrario, golpea al enemigo más cercano.",
                    cooldown = "14/12.5/11/9.5/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Loto mortal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaR.png",
                    description = "Katarina despide una ráfaga de hojas e inflige gran cantidad de daño mágico a los 3 campeones enemigos más cercanos.",
                    cooldown = "90/60/45s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/katarina",
            wrMetaUrl = "https://wr-meta.com/champion/katarina/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/katarina/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/katarina"
        ),
        Champion(
            id = "kayle",
            name = "Kayle",
            title = "la Justa",
            ddragonId = "Kayle",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kayle.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 53.16,
            pickRate = 3.17,
            banRate = 0.4,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Kayle, nacida de un Aspecto de Targon en el punto álgido de las Guerras Rúnicas, honró el legado de su madre al continuar la lucha por la justicia con sus alas de llamas divinas. Durante muchos años, ella y su hermana gemela Morgana fueron las...",
            advantageAgainst = listOf("Kassadin", "Galio", "Vladimir"),
            counteredBy = listOf("Zed", "Talon", "Fizz"),
            synergies = listOf("Maestro Yi", "Lulu", "Nunu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kayle en TOP. Coordina el uso de su Veredicto divino para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián", "Danza de la Muerte"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6333.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Danza de la Muerte", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6333.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Ascenso divino",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kayle_P.png",
                    description = "Al subir de nivel y gastar puntos de habilidad, Kayle recibe apoyo divino en sus ataques. Sus alas prenden en llamas mientras, de forma progresiva, obtiene velocidad de ataque, velocidad de movimiento, alcance de ataque y ondas de fuego con sus ataques.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Ráfaga radiante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleQ.png",
                    description = "Kayle invoca un portal que lanza una espada celestial que atraviesa a los enemigos, lo que reduce las resistencias de todos los enemigos golpeados, quedan ralentizados y reciben daño.",
                    cooldown = "12/11/10/9/8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Gracia celestial",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleW.png",
                    description = "Bendecida por lo divino, Kayle se cura a sí misma y al aliado más cercano, y ambos obtienen velocidad de movimiento.",
                    cooldown = "15s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Filo purificador",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleE.png",
                    description = "Pasiva: Virtud, la espada celestial de Kayle, inflige daño mágico adicional a los enemigos que ataca.Activa: el próximo ataque de Kayle aplasta a su objetivo con fuego celestial e inflige daño mágico adicional en proporción a la vida que le falte.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Veredicto divino",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleR.png",
                    description = "Kayle vuelve invulnerable a un aliado e invoca al antiguo Aspecto de la Justicia para que purifique con una lluvia de espadas sagradas la zona que rodea al objetivo.",
                    cooldown = "160/120/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kayle",
            wrMetaUrl = "https://wr-meta.com/champion/kayle/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kayle/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kayle"
        ),
        Champion(
            id = "lissandra",
            name = "Lissandra",
            title = "La Bruja de Hielo",
            ddragonId = "Lissandra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lissandra.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.86,
            pickRate = 2.57,
            banRate = 1.95,
            damageType = DamageType.MAGIC,
            summary = "La magia de Lissandra convierte el poder del hielo en algo oscuro y terrible. Con la fuerza de su hielo negro, además de congelar a aquellos que se le oponen, los empala y los destruye sin mostrar piedad. Se la conoce como ''la Bruja de Hielo'' entre...",
            advantageAgainst = listOf("Katarina", "Akali", "Zed"),
            counteredBy = listOf("Syndra", "Orianna", "Swain"),
            synergies = listOf("Amumu", "Malphite", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lissandra en MID. Coordina el uso de su Tumba helada para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Yugo de la Hija del Hielo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lissandra_Passive.png",
                    description = "Cuando un campeón enemigo muere cerca de Lissandra, se convierte en un esclavo congelado. Los esclavos congelados ralentizan a los enemigos cercanos y, tras unos instantes, se hacen trizas por el intenso frío, lo que inflige daño mágico a objetivos cercanos.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Fragmento de hielo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraQ.png",
                    description = "Dispara una lanza de hielo que se divide cuando impacta en un enemigo, infligiendo daño mágico y reduciendo la velocidad de movimiento. Los fragmentos atraviesan al objetivo, infligiendo el mismo daño a los demás enemigos impactados.",
                    cooldown = "8/7/6/5/4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Anillo de escarcha",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraW.png",
                    description = "Congela a los enemigos cercanos, infligiendo daño mágico e inmovilizándolos.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Camino glacial",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraE.png",
                    description = "Lissandra crea una garra de hielo que inflige daño mágico. Volver a usar esta habilidad transporta a Lissandra a la posición de la garra.",
                    cooldown = "24/21/18/15/12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Tumba helada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraR.png",
                    description = "Si se lanza sobre un campeón enemigo, lo congela, dejándolo aturdido. Si se lanza sobre Lissandra, se encierra en Hielo Oscuro, de forma que se cura, pasa a ser invulnerable e imposibilita cualquier acción enemiga sobre ella. Del objetivo emana Hielo Oscuro, que inflige daño mágico y reduce la velocidad de movimiento de los enemigos.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lissandra",
            wrMetaUrl = "https://wr-meta.com/champion/lissandra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lissandra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lissandra"
        ),
        Champion(
            id = "mel",
            name = "Mel",
            title = "la Consejera Dorada",
            ddragonId = "mel",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Mel.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S+",
            winrate = 47.89,
            pickRate = 9.9,
            banRate = 28.38,
            damageType = DamageType.MAGIC,
            summary = "Mel domina las líneas con magia solar áurea, proveyendo daño a distancia, escudos reflectantes y potente control de masas en combates de equipo.",
            advantageAgainst = listOf("Yasuo", "Zed", "Katarina"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Amumu", "Malphite", "Nautilus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Mel en MID. Coordina el uso de su Trascendencia Imperial para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Resonancia Radiante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Mel_Passive.png",
                    description = "Lanzar habilidades marca a los enemigos; consumir la marca otorga velocidad de movimiento y daño mágico extra.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Rayo del Solio",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelQ.png",
                    description = "Dispara un haz solar penetrante en línea recta infligiendo daño mágico a todos los enemigos a su paso.",
                    cooldown = "6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Decreto Protector",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelW.png",
                    description = "Otorga un escudo áureo a un aliado o a sí misma que mitiga daño y refleja una fracción hacia los agresores.",
                    cooldown = "13s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Manto de la Discordia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelE.png",
                    description = "Despliega una zona de luz que ralentiza y silencia a los oponentes que permanecen en su interior.",
                    cooldown = "11s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Trascendencia Imperial",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelR.png",
                    description = "Canaliza una tormenta solar que baña una gran área con daño mágico colosal y aturde a los campeones alcanzados.",
                    cooldown = "70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/mel",
            wrMetaUrl = "https://wr-meta.com/champion/mel/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/mel/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/mel"
        ),
        Champion(
            id = "orianna",
            name = "Orianna",
            title = "La Dama Mecánica",
            ddragonId = "Orianna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Orianna.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "C",
            winrate = 53.63,
            pickRate = 3.0,
            banRate = 0.24,
            damageType = DamageType.MAGIC,
            summary = "Orianna, antaño una chica curiosa de carne y hueso, es ahora una maravilla tecnológica compuesta exclusivamente de engranajes. Cayó gravemente enferma tras un accidente en los distritos inferiores de Zaun y su cuerpo moribundo tuvo que ser reemplazado...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Zed", "Fizz", "Katarina"),
            synergies = listOf("Malphite", "Jarvan IV", "Wukong"),
            tacticalAdvice = "Aprovecha el escalado y combos de Orianna en MID. Coordina el uso de su Orden: onda de choque para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Dando cuerda",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/OriannaPassive.png",
                    description = "Los ataques de Orianna infligen daño mágico adicional. El daño aumenta conforme Orianna sigue atacando al mismo objetivo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Orden: atacar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaIzunaCommand.png",
                    description = "Orianna ordena a La Bola que salga disparada hacia una ubicación, lo que inflige daño mágico a los objetivos que encuentre en el camino (inflige menos daño a cada objetivo subsiguiente). Después, La Bola permanece en esa ubicación.",
                    cooldown = "6/5.25/4.5/3.75/3s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Orden: disonancia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaDissonanceCommand.png",
                    description = "Orianna ordena a La Bola que libere un pulso de energía que inflige daño mágico a su alrededor, lo cual crea una zona que acelera a los aliados y ralentiza a los enemigos.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Orden: proteger",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaRedactCommand.png",
                    description = "Orianna ordena a La Bola que se adhiera a un campeón aliado, le otorga un escudo e inflige daño mágico a los enemigos que atraviese a su paso. Además, La Bola otorga armadura y resistencia mágica adicional al campeón que protege.",
                    cooldown = "9s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Orden: onda de choque",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaDetonateCommand.png",
                    description = "Orianna ordena a La Bola que desencadene una onda de choque que inflige daño mágico y lanza a los enemigos cercanos hacia esta última después de unos instantes.",
                    cooldown = "110/95/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/orianna",
            wrMetaUrl = "https://wr-meta.com/champion/orianna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/orianna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/orianna"
        ),
        Champion(
            id = "ryze",
            name = "Ryze",
            title = "El Hechicero Rúnico",
            ddragonId = "Ryze",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ryze.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 48.22,
            pickRate = 3.19,
            banRate = 2.23,
            damageType = DamageType.MAGIC,
            summary = "Ryze es considerado uno de los hechiceros con más experiencia de Runaterra. El archimago ancestral y gruñón lleva sobre sus hombros una pesada carga. Con su constitución sin límites y su inmenso poder arcano, Ryze se pasa la vida buscando...",
            advantageAgainst = listOf("Katarina", "Akali", "Yasuo"),
            counteredBy = listOf("Swain", "Syndra", "Orianna"),
            synergies = listOf("Renekton", "Pantheon", "Shen"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ryze en MID. Coordina el uso de su Distorsión de reinos para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Maestría arcana",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ryze_P.png",
                    description = "Los hechizos de Ryze infligen daño adicional según su maná adicional. Además, obtiene un porcentaje que se añade a su maná máximo según su poder de habilidad.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Descarga eléctrica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeQWrapper.png",
                    description = "De forma pasiva, las demás habilidades básicas de Ryze reinician el enfriamiento de Descarga eléctrica y cargan una runa. Si Ryze lanza Descarga eléctrica con dos runas cargadas, obtiene un aumento de velocidad de movimiento durante un breve periodo.Con el lanzamiento, Ryze libera una carga de energía pura en línea recta que daña al primer enemigo al que alcanza. Si el objetivo está marcado con Flujo, Descarga eléctrica infligirá daño adicional y rebotará en los enemigos cercanos que también tengan una marca de Flujo.",
                    cooldown = "5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Prisión rúnica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeW.png",
                    description = "Ryze atrapa a una unidad enemiga objetivo en una prisión de runas que le inflige daño y lo ralentiza. Si el objetivo está marcado con Flujo, quedará inmovilizado.",
                    cooldown = "11/10.5/10/9.5/9s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Tormenta eléctrica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeE.png",
                    description = "Ryze lanza un orbe de poder mágico puro que daña a un enemigo y debilita a los demás que estén cerca. Los hechizos de Ryze tienen efectos adicionales contra enemigos debilitados.",
                    cooldown = "3.5/3.25/3/2.75/2.5s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Distorsión de reinos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeR.png",
                    description = "De forma pasiva, Descarga eléctrica inflige más daño a los objetivos marcados con Flujo.Con el lanzamiento, Ryze crea un portal a una ubicación cercana. Tras unos segundos, los aliados que estén dentro del portal se teleportan a la ubicación objetivo.",
                    cooldown = "180/160/140s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ryze",
            wrMetaUrl = "https://wr-meta.com/champion/ryze/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ryze/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ryze"
        ),
        Champion(
            id = "swain",
            name = "Swain",
            title = "el Gran General de Noxus",
            ddragonId = "Swain",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Swain.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.TOP),
            tier = "A",
            winrate = 51.59,
            pickRate = 4.75,
            banRate = 6.76,
            damageType = DamageType.MAGIC,
            summary = "Jericho Swain es el visionario líder de Noxus, una nación expansionista que solo venera la fuerza. Pese a haber sido relegado y a haber perdido el brazo izquierdo en las guerras de Jonia, se hizo con el control del imperio con una despiadada...",
            advantageAgainst = listOf("Yasuo", "Katarina", "Akali"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Jhin", "Caitlyn", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Swain en MID. Coordina el uso de su Ascensión demoníaca para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Soberano Gélido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Égida de Fuego Solar", "Apariencia Espiritual", "Botas de Maná", "Sombrero Mortal de Rabadon", "Báculo del Vacío"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            itemSwaps = listOf(
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Sombrero Mortal de Rabadon", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = true,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Bandada de cuervos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Swain_P.png",
                    description = "Los cuervos de Swain recogen fragmentos de alma que lo curan y aumentan su vida máxima de forma permanente.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "La mano de la muerte",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SwainQ.png",
                    description = "Swain libera varios rayos de poder sobrenatural que atraviesan a los enemigos. Los enemigos alcanzados reciben más daño por cada rayo que los alcanza.",
                    cooldown = "7/6/5/4/3s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Visión del imperio",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SwainW.png",
                    description = "Swain abre el ojo del demonio, que inflige daño y ralentiza a los enemigos. Los campeones golpeados quedan revelados y otorgan un fragmento de alma a Swain.",
                    cooldown = "22/21/20/19/18s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Paralizar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SwainE.png",
                    description = "Swain libera una onda de poder demoníaco hacia delante. La onda vuelve hacia él e inmoviliza a los enemigos que alcanza. Swain puede atraer a todos los campeones inmovilizados. Esta habilidad tiene un enfriamiento más corto durante Ascensión demoníaca.",
                    cooldown = "10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Ascensión demoníaca",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SwainR.png",
                    description = "Swain se transforma en demonio y drena vida de los campeones enemigos, súbditos y monstruos neutrales cercanos. Swain puede lanzar Alma ígnea para diezmar y ralentizar a sus enemigos con una nova de fuego de alma. Esta forma tiene una duración indefinida siempre y cuando Swain drene a campeones enemigos.",
                    cooldown = "100/80/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/swain",
            wrMetaUrl = "https://wr-meta.com/champion/swain/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/swain/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/swain"
        ),
        Champion(
            id = "syndra",
            name = "Syndra",
            title = "La Soberana Oscura",
            ddragonId = "Syndra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Syndra.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.ADC),
            tier = "D",
            winrate = 48.46,
            pickRate = 4.02,
            banRate = 2.15,
            damageType = DamageType.MAGIC,
            summary = "Syndra es una temible maga jonia con un poder increíble a su disposición. De niña, inquietó a los ancianos del lugar con su imprudente y tempestuosa magia. La enviaron a que aprendiera a controlarla mejor, pero al final descubrió que su supuesto mentor...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Fizz", "Zed", "Katarina"),
            synergies = listOf("Evelynn", "Lee Sin", "Nidalee"),
            tacticalAdvice = "Aprovecha el escalado y combos de Syndra en MID. Coordina el uso de su Poder desatado para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Trascendencia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/SyndraPassive.png",
                    description = "Syndra reúne astillas de ira al subir de nivel e infligir daño a enemigos, lo que sirve para mejorar sus habilidades.Esfera oscura: Syndra puede acumular una carga adicional.Fuerza de voluntad: Inflige daño verdadero adicional.Dispersar a los débiles: Aumenta el ancho y ralentiza a todos los objetivos.Poder desatado: Ejecuta a los objetivos con poca vida.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Esfera oscura",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SyndraQ.png",
                    description = "Syndra conjura una Esfera oscura e inflige daño mágico. La esfera permanece y se puede manipular con las demás habilidades.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Fuerza de voluntad",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SyndraW.png",
                    description = "Syndra coge y lanza una Esfera oscura o un súbdito enemigo, infligiendo daño mágico y reduciendo la velocidad de movimiento de sus enemigos.",
                    cooldown = "12/11/10/9/8s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Dispersar a los débiles",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SyndraE.png",
                    description = "Syndra empuja a los enemigos y las Esferas oscuras e inflige daño mágico. Los enemigos impactados por las Esferas oscuras quedan aturdidos.",
                    cooldown = "17s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Poder desatado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SyndraR.png",
                    description = "Syndra bombardea a un campeón enemigo con todas sus Esferas oscuras.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/syndra",
            wrMetaUrl = "https://wr-meta.com/champion/syndra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/syndra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/syndra"
        ),
        Champion(
            id = "talon",
            name = "Talon",
            title = "La Sombra de la Espada",
            ddragonId = "Talon",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Talon.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 48.12,
            pickRate = 2.14,
            banRate = 0.47,
            damageType = DamageType.PHYSICAL,
            summary = "Talon es el cuchillo de la oscuridad, un asesino despiadado capaz de atacar sin previo aviso y huir antes de que salte alarma alguna. Logró granjearse una peligrosa reputación en las terribles calles de Noxus, donde se vio obligado a luchar, matar y...",
            advantageAgainst = listOf("Evelynn", "Nidalee", "Lee Sin"),
            counteredBy = listOf("Kha'Zix", "Rengar", "Olaf"),
            synergies = listOf("Yuumi", "Shen", "Galio"),
            tacticalAdvice = "Aprovecha el escalado y combos de Talon en JUNGLE. Coordina el uso de su Asalto de sombra para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Conquistador (Precisión)",
            runeTreeDetails = "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián", "Danza de la Muerte"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6333.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Danza de la Muerte", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6333.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Final de la hoja",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/TalonP.png",
                    description = "Los hechizos de Talon provocan una herida a los campeones y monstruos gigantes, que puede acumularse hasta 3 veces. Cuando Talon ataca a un objetivo con 3 heridas, este sufre una hemorragia que le causa daño prolongado.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Diplomacia noxiana",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/TalonQ.png",
                    description = "Talon apuñala al objetivo. Si este se encuentra al alcance de sus golpes cuerpo a cuerpo, el ataque es un golpe crítico. Si no, Talon se abalanza sobre él antes de apuñalarlo. Si consigue acabar con el objetivo, recupera algo de vida y reduce el enfriamiento de la habilidad.",
                    cooldown = "8/7.5/7/6.5/6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Rastrillar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/TalonW.png",
                    description = "Talon lanza una andanada de dagas que luego vuelven a él y causan daño físico cada vez que pasan a través de un enemigo. Al volver, las dagas infligen daño adicional y ralentizan a las unidades alcanzadas.",
                    cooldown = "9/8.5/8/7.5/7s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Camino del asesino",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/TalonE.png",
                    description = "Talon salta sobre cualquier obstáculo o estructura hasta una distancia máxima. Esta habilidad tiene poco tiempo de enfriamiento, pero no podrá volver a utilizarla en la misma zona durante un buen rato.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Asalto de sombra",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/TalonR.png",
                    description = "Talon esparce un anillo de hojas, se hace invisible y consigue velocidad de movimiento adicional. Cuando se hace visible de nuevo, las hojas convergen sobre él. Cada vez que se mueven las hojas, Asalto de sombra inflige daño físico a los enemigos alcanzados al menos por una de ellas.",
                    cooldown = "100/80/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/talon",
            wrMetaUrl = "https://wr-meta.com/champion/talon/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/talon/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/talon"
        ),
        Champion(
            id = "twisted_fate",
            name = "Twisted Fate",
            title = "El Maestro de las Cartas",
            ddragonId = "TwistedFate",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/TwistedFate.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.ADC, LaneRole.TOP),
            tier = "S+",
            winrate = 53.2,
            pickRate = 5.0,
            banRate = 10.0,
            damageType = DamageType.MAGIC,
            summary = "Twisted Fate es un tahúr y timador de mala reputación que ha viajado por buena parte del mundo conocido, granjeándose con su encanto y su habilidad con los naipes la admiración y la enemistad de ricos y necios por igual. Raras veces se toma las cosas...",
            advantageAgainst = listOf("Katarina", "Akali", "Talon"),
            counteredBy = listOf("Fizz", "Zed", "Yasuo"),
            synergies = listOf("Nocturne", "Shen", "Pantheon"),
            tacticalAdvice = "Aprovecha el escalado y combos de Twisted Fate en MID. Coordina el uso de su Destino para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Dado trucado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Cardmaster_SealFate.png",
                    description = "Al matar a una unidad, Twisted Fate lanza su dado ''de la suerte'' y recibe una cantidad de oro adicional entre 1 y 6.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Comodín",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/WildCards.png",
                    description = "Twisted Fate lanza tres cartas que dañan a todas las unidades enemigas que atraviesen.",
                    cooldown = "6/5.75/5.5/5.25/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Escoge una carta",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PickACard.png",
                    description = "Twisted Fate elige una carta mágica de su baraja y la usa para su siguiente ataque, que causa efectos adicionales.",
                    cooldown = "6s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Baraja de cartas",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CardmasterStack.png",
                    description = "Twisted Fate inflige daño adicional cada 4 ataques. Además, aumenta su velocidad de ataque.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Destino",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Destiny.png",
                    description = "Twisted Fate predice la fortuna de sus enemigos, revelando a los campeones enemigos y activando el uso de Portal, que teleporta a Twisted Fate a cualquier lugar objetivo en 1,5 s.",
                    cooldown = "180/150/120s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/twisted-fate",
            wrMetaUrl = "https://wr-meta.com/champion/twisted-fate/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/twisted-fate/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/twisted-fate"
        ),
        Champion(
            id = "veigar",
            name = "Veigar",
            title = "El Pequeño Maestro del Mal",
            ddragonId = "Veigar",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Veigar.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.ADC, LaneRole.SUPPORT),
            tier = "A",
            winrate = 49.18,
            pickRate = 10.8,
            banRate = 12.2,
            damageType = DamageType.MAGIC,
            summary = "Entusiasta maestro de la magia negra, Veigar ha hecho suyos poderes a los que pocos mortales se atreven a acercarse. Como espíritu de Ciudad de Bandle, buscó durante mucho tiempo deshacerse de las limitaciones de la magia yordle, así que su atención se...",
            advantageAgainst = listOf("Kassadin", "Vladimir", "Ryze"),
            counteredBy = listOf("Katarina", "Fizz", "Zed"),
            synergies = listOf("Thresh", "Blitzcrank", "Nautilus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Veigar en MID. Coordina el uso de su Estallido primordial para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Poder de Maldad increíble",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/VeigarEntropy.png",
                    description = "Veigar es el mayor mal que se haya visto en el corazón de Runaterra... ¡y cada vez es más malvado! Veigar aumenta su poder de habilidad de forma permanente al golpear a un campeón enemigo con un hechizo o al acabar con un enemigo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Ataque maligno",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VeigarBalefulStrike.png",
                    description = "Veigar lanza un rayo de energía oscura que inflige daño mágico a los dos primeros enemigos alcanzados. Las unidades asesinadas por el rayo otorgan a Veigar un poco de poder de habilidad permanente.",
                    cooldown = "6/5.5/5/4.5/4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Materia oscura",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VeigarDarkMatter.png",
                    description = "Veigar invoca una gran masa de materia oscura que cae del cielo sobre la ubicación del objetivo e inflige daño mágico. Las acumulaciones de Maldad increíble reducen el enfriamiento de Materia oscura.",
                    cooldown = "0s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Horizonte de sucesos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VeigarEventHorizon.png",
                    description = "Veigar retuerce los límites del espacio y crea una cárcel que aturde a los enemigos que la atraviesan.",
                    cooldown = "20/18.5/17/15.5/14s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Estallido primordial",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VeigarR.png",
                    description = "El campeón objetivo sufre una explosión que inflige una gran cantidad de daño mágico. Dicha cantidad aumenta según la vida que le falte al objetivo.",
                    cooldown = "100/80/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/veigar",
            wrMetaUrl = "https://wr-meta.com/champion/veigar/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/veigar/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/veigar"
        ),
        Champion(
            id = "vel_koz",
            name = "Vel'Koz",
            title = "El Ojo del Vacío",
            ddragonId = "Velkoz",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Velkoz.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "A+",
            winrate = 51.4,
            pickRate = 8.8,
            banRate = 13.7,
            damageType = DamageType.TRUE_HYBRID,
            summary = "No es seguro que Vel'Koz sea el primer ente del Vacío que ha aparecido en Runaterra, pero no ha habido ningún otro con su nivel de crueldad y conciencia calculadora. Mientras que los de su especie devoran y profanan todo cuanto encuentran, él busca el...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Zed", "Fizz", "Katarina"),
            synergies = listOf("Leona", "Nautilus", "Thresh"),
            tacticalAdvice = "Aprovecha el escalado y combos de Vel'Koz en MID. Coordina el uso de su Rayo desintegrador de seres vivos para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Deconstrucción orgánica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/VelKoz_Passive.png",
                    description = "Las habilidades de Vel'Koz aplican Deconstrucción orgánica a los enemigos al impactar. Con 3 acumulaciones, el enemigo sufrirá una explosión de daño verdadero.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Fisión de plasma",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VelkozQ.png",
                    description = "Vel'Koz dispara un rayo de plasma que se bifurca cuando impacta contra un enemigo o si se vuelve a activar la habilidad. El rayo ralentiza e inflige daño al golpear.",
                    cooldown = "7s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Grieta del Vacío",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VelkozW.png",
                    description = "Vel'Koz abre una grieta al Vacío que inflige daño y luego, tras unos instantes, explota e inflige más.",
                    cooldown = "1.5s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Perturbación tectónica",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VelkozE.png",
                    description = "Vel'Koz hace estallar una zona, lo que levanta por los aires a los enemigos alcanzados y los empuja ligeramente si están cerca de él.",
                    cooldown = "16/15/14/13/12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Rayo desintegrador de seres vivos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VelkozR.png",
                    description = "Vel'Koz dispara un rayo que sigue al cursor durante 2,5 s e inflige daño mágico. Deconstrucción orgánica analiza a los campeones enemigos y les inflige daño verdadero.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/vel-koz",
            wrMetaUrl = "https://wr-meta.com/champion/vel-koz/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/vel-koz/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/vel-koz"
        ),
        Champion(
            id = "vex",
            name = "Vex",
            title = "la Lúgubre",
            ddragonId = "Vex",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Vex.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.25,
            pickRate = 2.43,
            banRate = 0.58,
            damageType = DamageType.MAGIC,
            summary = "En el oscuro corazón de las Islas de la Sombra, una yordle solitaria atraviesa arduamente la bruma espectral, satisfecha con su tenebrosa miseria. Con una angustia adolescente inagotable y una poderosa sombra tras de sí, Vex vive en un planeta de...",
            advantageAgainst = listOf("Yasuo", "Irelia", "Katarina"),
            counteredBy = listOf("Syndra", "Orianna", "Ziggs"),
            synergies = listOf("Amumu", "Malphite", "Nautilus"),
            tacticalAdvice = "Aprovecha el escalado y combos de Vex en MID. Coordina el uso de su Estallido sombrío para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Miseria y pesimismo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Icons_Vex_Passive.png",
                    description = "Vex se potencia periódicamente, lo que provoca que su siguiente habilidad básica aterrorice a los enemigos e interrumpa los deslizamientos. Cuando un enemigo cercano se desliza, Vex aplica una marca que se puede consumir para infligir daño adicional. También reduce el enfriamiento de su estado potenciado.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Proyectil mistral",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VexQ.png",
                    description = "Lanza un proyectil dañino que acelera a mitad de vuelo.",
                    cooldown = "8/7/6/5/4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Espacio personal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VexW.png",
                    description = "Obtiene un escudo e inflige daño a los enemigos cercanos.",
                    cooldown = "16/15/14/13/12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Oscuridad amenazante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VexE.png",
                    description = "Invoca una zona de ralentización que inflige daño y aplica Pesimismo a los enemigos.",
                    cooldown = "13s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Estallido sombrío",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VexR.png",
                    description = "Dispara un misil que marca a un campeón enemigo. Si se activa de nuevo la habilidad, Vex se desliza hacia este y le inflige daño.",
                    cooldown = "140/120/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/vex",
            wrMetaUrl = "https://wr-meta.com/champion/vex/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/vex/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/vex"
        ),
        Champion(
            id = "viktor",
            name = "Viktor",
            title = "El Heraldo de las Máquinas",
            ddragonId = "Viktor",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Viktor.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 51.44,
            pickRate = 3.11,
            banRate = 1.24,
            damageType = DamageType.MAGIC,
            summary = "Viktor, el heraldo de una nueva era tecnológica, ha consagrado su vida al progreso de la humanidad. Es un idealista que busca llevar al pueblo de Zaun a un nuevo nivel de comprensión y cree que la humanidad solo podrá alcanzar su máximo potencial...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Zed", "Fizz", "Katarina"),
            synergies = listOf("Amumu", "Malphite", "Jarvan IV"),
            tacticalAdvice = "Aprovecha el escalado y combos de Viktor en MID. Coordina el uso de su Tormenta del caos para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Evolución gloriosa",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Viktor_Passive.png",
                    description = "Viktor puede mejorar sus habilidades básicas cuando consigue asesinatos contra enemigos.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Transferencia de potencia",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ViktorQ.png",
                    description = "Viktor ataca a una unidad enemiga. Además de causarle daño mágico y obtener un escudo, aumenta la potencia de su siguiente ataque básico.Mejora: El escudo de Transferencia de potencia aumenta un 60% y Viktor obtiene velocidad de movimiento adicional tras lanzar la habilidad.",
                    cooldown = "9/8/7/6/5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Campo gravitatorio",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ViktorW.png",
                    description = "Viktor crea un potente campo gravitacional que ralentiza a los enemigos que estén en su radio. Los enemigos que se queden demasiado tiempo dentro del dispositivo quedan aturdidos.Mejora: Las habilidades no periódicas de Viktor aplican una ralentización a los enemigos.",
                    cooldown = "17/16/15/14/13s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Rayo de la muerte",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ViktorE.png",
                    description = "Viktor usa su brazo robótico para lanzar un rayo que atraviesa el campo de batalla en línea recta e inflige daño a todos los enemigos en su trayectoria.Mejora: Tras el Rayo de la muerte se produce una explosión que causa daño mágico.",
                    cooldown = "12/11/10/9/8s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Tormenta del caos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ViktorR.png",
                    description = "Viktor conjura una singularidad en el campo de batalla que inflige daño mágico e interrumpe las canalizaciones enemigas. A continuación, inflige daño mágico de forma periódica a todos los enemigos cercanos. Viktor es capaz de redirigir la singularidad.Mejora: Tormenta del caos se desplaza un 25% más rápido.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/viktor",
            wrMetaUrl = "https://wr-meta.com/champion/viktor/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/viktor/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/viktor"
        ),
        Champion(
            id = "vladimir",
            name = "Vladimir",
            title = "El Segador Carmesí",
            ddragonId = "Vladimir",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Vladimir.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "D",
            winrate = 48.55,
            pickRate = 2.36,
            banRate = 0.85,
            damageType = DamageType.MAGIC,
            summary = "Un demonio con sed de sangre mortal, Vladimir ha influido en el destino de Noxus desde los primeros días del imperio. Además de alargar su vida de forma sobrenatural, su amplio conocimiento de la hemomancia le posibilita controlar el cuerpo y la mente...",
            advantageAgainst = listOf("Zed", "Talon", "Fizz"),
            counteredBy = listOf("Kassadin", "Veigar", "Lissandra"),
            synergies = listOf("Amumu", "Amumu", "Leona"),
            tacticalAdvice = "Aprovecha el escalado y combos de Vladimir en MID. Coordina el uso de su Hemoplaga para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Cometa Arcano (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Pacto carmesí",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/VladimirP.png",
                    description = "Cada 30 puntos de vida adicional, Vladimir recibe 1 punto de poder de habilidad, y cada punto de poder de habilidad le proporciona 1,6 de vida adicional (no se acumulan entre sí).",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Transfusión",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VladimirQ.png",
                    description = "Vladimir le roba vida al enemigo objetivo. Cuando los recursos de Vladimir estén al máximo, durante un breve periodo Transfusión inflige mucho más daño y cura mucho más.",
                    cooldown = "9/7.9/6.8/5.7/4.6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Estanque sangriento",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VladimirSanguinePool.png",
                    description = "Vladimir se sumerge en un estanque de sangre, por lo que no se lo puede seleccionar como objetivo durante 2 s. Además, ralentiza a los enemigos que estén dentro y Vladimir les absorbe vida.",
                    cooldown = "28/25/22/19/16s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Mareas de sangre",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VladimirE.png",
                    description = "Vladimir paga con su propia vida para cargar una reserva de sangre que, una vez liberada, inflige daño en el área circundante. Este ataque puede ser bloqueado por las unidades enemigas.",
                    cooldown = "13/11/9/7/5s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Hemoplaga",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VladimirHemoplague.png",
                    description = "Vladimir infecta una zona con una plaga virulenta. Los enemigos afectados reciben más daño mientras dura el efecto. Pasados unos segundos, Hemoplaga inflige daño mágico a los enemigos infectados y cura a Vladimir por cada campeón enemigo golpeado.",
                    cooldown = "120s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/vladimir",
            wrMetaUrl = "https://wr-meta.com/champion/vladimir/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/vladimir/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/vladimir"
        ),
        Champion(
            id = "yasuo",
            name = "Yasuo",
            title = "La Espada sin Honor",
            ddragonId = "Yasuo",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yasuo.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.ADC),
            tier = "S+",
            winrate = 49.95,
            pickRate = 17.54,
            banRate = 10.05,
            damageType = DamageType.PHYSICAL,
            summary = "Yasuo, un intrépido jonio con una fuerza de voluntad inquebrantable, es también un hábil espadachín capaz de controlar el viento y utilizarlo contra sus enemigos. Siendo un joven orgulloso fue injustamente acusado de asesinar a su maestro. Incapaz de...",
            advantageAgainst = listOf("Gnar", "Ahri", "Syndra"),
            counteredBy = listOf("Renekton", "Annie", "Veigar"),
            synergies = listOf("Malphite", "Diana", "Alistar"),
            tacticalAdvice = "Aprovecha el escalado y combos de Yasuo en MID. Coordina el uso de su Último aliento para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Conquistador (Precisión)",
            runeTreeDetails = "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Camino del alma errante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Yasuo_Passive.png",
                    description = "Aumenta la probabilidad de impacto crítico de Yasuo. Además, Yasuo va acumulando su escudo mientras se mueve. El escudo se activa cuando recibe daño de un campeón o monstruo.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Tempestad de acero",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YasuoQ1Wrapper.png",
                    description = "Lanza una estocada al frente que inflige daño a los enemigos en una línea.Al golpear, otorga una acumulación de Tormenta inminente durante unos cuantos segundos. Con 2 acumulaciones, Tempestad de acero inicia un remolino que lanza al objetivo por el aire.Tempestad de acero se comporta como un ataque básico y progresa del mismo modo.",
                    cooldown = "4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Muro de viento",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YasuoW.png",
                    description = "Crea un muro móvil que bloquea todos los proyectiles enemigos durante 4 s.",
                    cooldown = "25/23/21/19/17s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Hoja cortante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YasuoE.png",
                    description = "Se desliza a través del enemigo seleccionado, al que inflige daño mágico. Cada vez que se usa, aumenta el daño del siguiente uso hasta alcanzar un máximo.No se puede emplear más de una vez contra el mismo enemigo durante unos cuantos segundos.Si Tempestad de acero se utiliza durante un deslizamiento, golpeará de forma circular.",
                    cooldown = "0.5/0.4/0.3/0.2/0.1s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Último aliento",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YasuoR.png",
                    description = "Yasuo aparece junto al campeón enemigo que esté en el aire y le inflige daño físico. Además, todos los enemigos que estén en el aire permanecen suspendidos. Otorga flujo máximo, pero reinicia todas las acumulaciones de Tormenta inminente.Después, durante un breve periodo de tiempo, los golpes críticos de Yasuo obtienen una cantidad importante de penetración de armadura adicional.",
                    cooldown = "70/50/30s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/yasuo",
            wrMetaUrl = "https://wr-meta.com/champion/yasuo/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/yasuo/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/yasuo"
        ),
        Champion(
            id = "yone",
            name = "Yone",
            title = "el Imperecedero",
            ddragonId = "Yone",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yone.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "A",
            winrate = 53.92,
            pickRate = 7.4,
            banRate = 6.75,
            damageType = DamageType.TRUE_HYBRID,
            summary = "En vida, fue Yone, hermanastro de Yasuo y pupilo de renombre en la escuela de esgrima de su aldea. No obstante, al morir a manos de su hermano, se vio perseguido por una maligna entidad del reino espiritual y acabó por asesinarlo con su propia espada...",
            advantageAgainst = listOf("Zoe", "Lux", "Syndra"),
            counteredBy = listOf("Renekton", "Annie", "Veigar"),
            synergies = listOf("Malphite", "Diana", "Alistar"),
            tacticalAdvice = "Aprovecha el escalado y combos de Yone en MID. Coordina el uso de su Destino sellado para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Conquistador (Precisión)",
            runeTreeDetails = "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Camino del cazador",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/YonePassive.png",
                    description = "Yone inflige daño mágico cada dos ataques. Además, su probabilidad de impacto crítico aumenta.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Acero mortal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YoneQ.png",
                    description = "Lanza una estocada al frente que inflige daño a los enemigos en una línea.Al golpear, otorga una acumulación de Tormenta inminente durante unos cuantos segundos. Con 2 acumulaciones, Acero mortal desliza a Yone hacia delante con una ráfaga de viento que lanza a los enemigos por los aires.Acero mortal se comporta como un ataque básico y progresa del mismo modo.",
                    cooldown = "4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Cuchilla espiritual",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YoneW.png",
                    description = "Realiza un tajo hacia delante que daña a todos los enemigos en un cono. Otorga a Yone un escudo cuyo valor aumenta en función del número de campeones alcanzados por el ataque.El enfriamiento y el tiempo de lanzamiento de Cuchillada espiritual progresan con la velocidad de ataque.",
                    cooldown = "14s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Alma desatada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YoneE.png",
                    description = "El espíritu de Yone deja su cuerpo atrás y obtiene velocidad de movimiento. Cuando esta habilidad acaba, el espíritu de Yone se ve obligado a regresar a su cuerpo y repite una parte del daño que infligió como espíritu.",
                    cooldown = "22/19/16/13/10s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Destino sellado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/YoneR.png",
                    description = "Yone se desplaza detrás del último campeón en una línea con una cuchillada tan poderosa que arrastra hacia él a todos los enemigos golpeados.",
                    cooldown = "120/100/80s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/yone",
            wrMetaUrl = "https://wr-meta.com/champion/yone/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/yone/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/yone"
        ),
        Champion(
            id = "zed",
            name = "Zed",
            title = "El Maestro de las Sombras",
            ddragonId = "Zed",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Zed.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 46.83,
            pickRate = 4.02,
            banRate = 1.13,
            damageType = DamageType.PHYSICAL,
            summary = "Zed, despiadado y nada compasivo, es el líder de la Orden de la Sombra, una organización que él mismo creó con el propósito de militarizar las tradiciones marciales y mágicas de Jonia para expulsar a los invasores noxianos. Durante la guerra, la...",
            advantageAgainst = listOf("Veigar", "Lux", "Vel'Koz"),
            counteredBy = listOf("Lissandra", "Veigar", "Zilean"),
            synergies = listOf("Vi", "Jarvan IV", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Zed en MID. Coordina el uso de su Marca de la muerte para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Electrocutar (Dominación)",
            runeTreeDetails = "Dominación: Impacto Repentino • Colección de Globos Oculares • Cazador Ingenioso • Concentración",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche", "Punteras Revestidas", "Ángel Guardián"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png"),
            situationalItems = listOf("Rencor de Serylda", "Ángel Guardián"),
            itemSwaps = listOf(
ItemSwap(coreItem="Ángel Guardián", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png", altItem="Cota de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo"),
ItemSwap(coreItem="Punteras Revestidas", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = false,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Desprecio por los débiles",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/ZedP.png",
                    description = "Los ataques básicos de Zed contra objetivos con poca vida infligen daño mágico adicional. Este efecto no puede aplicarse al mismo campeón enemigo más de una vez cada pocos segundos.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Shuriken navaja",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZedQ.png",
                    description = "Tanto Zed como sus sombras lanzan los shurikens.Cada shuriken inflige daño a todos los enemigos que golpea.",
                    cooldown = "6s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Sombra viviente",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZedW.png",
                    description = "Pasiva: Cada vez que Zed y sus sombras golpean a un objetivo con la misma habilidad, Zed obtiene energía. Solo se puede obtener energía una vez por habilidad lanzada.Activa: La sombra de Zed se desliza hacia delante y se queda en el sitio durante unos s. Si Zed reactiva Sombra viviente, intercambia su posición con la sombra.",
                    cooldown = "20/19/18/17/16s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Cuchillada de sombra",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZedE.png",
                    description = "Zed y sus sombras acuchillan e infligen daño a los enemigos cercanos. Se ralentiza a los enemigos golpeados por Cuchillada de sombra.",
                    cooldown = "5/4.5/4/3.5/3s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Marca de la muerte",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZedR.png",
                    description = "No se puede marcar a Zed como objetivo y se desliza hacia un campeón enemigo, marcándolo. Tras 3 segundos, la marca se activa, repitiendo una parte del daño infligido por Zed al objetivo mientras estaba marcado.",
                    cooldown = "120/110/100s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/zed",
            wrMetaUrl = "https://wr-meta.com/champion/zed/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/zed/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/zed"
        ),
        Champion(
            id = "ziggs",
            name = "Ziggs",
            title = "El Experto en Hexplosivos",
            ddragonId = "Ziggs",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ziggs.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.ADC),
            tier = "B",
            winrate = 48.61,
            pickRate = 4.84,
            banRate = 2.3,
            damageType = DamageType.MAGIC,
            summary = "Gran amante de las bombas grandes y las mechas cortas, el yordle Ziggs es una explosiva fuerza de la naturaleza. Trabajaba como ayudante de un inventor en Piltover, pero, cansado de llevar una vida tan aburrida y monótona, decidió hacerse amigo de una...",
            advantageAgainst = listOf("Annie", "Veigar", "Veigar"),
            counteredBy = listOf("Fizz", "Zed", "Katarina"),
            synergies = listOf("Leona", "Nautilus", "Thresh"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ziggs en MID. Coordina el uso de su Megabomba incendiaria para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Mecha corta",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/ZiggsPassiveReady.png",
                    description = "De forma periódica, el próximo ataque básico de Ziggs inflige daño mágico adicional. Este enfriamiento se reducirá cada vez que Ziggs use una habilidad.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Bomba rebotante",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZiggsQ.png",
                    description = "Ziggs lanza una bomba rebotante que inflige daño mágico.",
                    cooldown = "6/5.5/5/4.5/4s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Carga concentrada",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZiggsW.png",
                    description = "Ziggs lanza una carga explosiva que detona tras un retardo o cuando vuelva a activarse esta habilidad. La explosión inflige daño mágico a los enemigos y los empuja. También empuja hacia atrás a Ziggs, pero no sufre daño. Ziggs puede usar la carga para hacer hexplotar las torretas enemigas vulnerables.",
                    cooldown = "20/18/16/14/12s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Campo de hexplosivos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZiggsE.png",
                    description = "Ziggs esparce minas de proximidad que detonan al entrar en contacto con el enemigo, lo que inflige daño mágico y ralentiza. Las detonaciones de minas sucesivas sobre el mismo objetivo infligen daño reducido.",
                    cooldown = "16s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Megabomba incendiaria",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZiggsR.png",
                    description = "Ziggs lanza a gran distancia su creación definitiva: la Megabomba incendiaria. Los enemigos que se encuentren en el centro de la zona de impacto recibirán más daño que los que estén más alejados.",
                    cooldown = "120/95/70s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ziggs",
            wrMetaUrl = "https://wr-meta.com/champion/ziggs/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ziggs/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ziggs"
        ),
        Champion(
            id = "zilean",
            name = "Zilean",
            title = "El Guardián del Tiempo",
            ddragonId = "Zilean",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Zilean.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 49.42,
            pickRate = 1.2,
            banRate = 0.77,
            damageType = DamageType.MAGIC,
            summary = "Zilean, el que un día fuera un poderoso mago de Icathia, se obsesionó con el paso del tiempo tras ser testigo de la destrucción de su tierra natal a manos del Vacío. Incapaz de parar un minuto a llorar tan catastrófica pérdida, se entregó a la vetusta...",
            advantageAgainst = listOf("Leona", "Alistar", "Braum"),
            counteredBy = listOf("Sona", "Soraka", "Nami"),
            synergies = listOf("Hecarim", "Olaf", "Maestro Yi"),
            tacticalAdvice = "Aprovecha el escalado y combos de Zilean en SUPPORT. Coordina el uso de su Alteración del tiempo para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Aery (Brujería)",
            runeTreeDetails = "Brujería: Banda de Maná • Trascendencia • Piroláser • Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "Tiempo embotellado",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Zilean_Passive.png",
                    description = "Zilean almacena el tiempo como experiencia y puede otorgársela a sus aliados. Cuando tiene suficiente experiencia para terminar el nivel de un aliado, puede hacer clic derecho sobre él para entregársela. Zilean recibe tanta experiencia como da.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Bomba de relojería",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZileanQ.png",
                    description = "Arroja una bomba a una zona. El artefacto, que se adhiere a las unidades cercanas (prioriza a los campeones), detona pasados 3 s e inflige daño de área. Además, si la Bomba de relojería detona antes de tiempo por la acción de otra, aturde a los enemigos afectados.",
                    cooldown = "10/9.5/9/8.5/8s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Retroceder",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZileanW.png",
                    description = "Zilean puede prepararse para futuros enfrentamientos reduciendo el enfriamiento de sus demás habilidades básicas.",
                    cooldown = "14/12/10/8/6s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Distorsión temporal",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/TimeWarp.png",
                    description = "Zilean pliega el tiempo alrededor de cualquier unidad, lo que reduce la velocidad de movimiento de un enemigo o aumenta la de un aliado durante un breve periodo.",
                    cooldown = "15s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Alteración del tiempo",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ChronoShift.png",
                    description = "Zilean coloca sobre un campeón aliado una runa temporal protectora que lo lleva atrás en el tiempo en caso de sufrir daño letal.",
                    cooldown = "120/90/60s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/zilean",
            wrMetaUrl = "https://wr-meta.com/champion/zilean/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/zilean/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/zilean"
        ),
        Champion(
            id = "zoe",
            name = "Zoe",
            title = "el Aspecto del Crepúsculo",
            ddragonId = "Zoe",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Zoe.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 49.69,
            pickRate = 1.23,
            banRate = 0.28,
            damageType = DamageType.MAGIC,
            summary = "Como personificación de la travesura, de la imaginación y del cambio, Zoe es la mensajera cósmica de Targon y anuncia acontecimientos importantes que remodelan mundos. Su mera presencia distorsiona las matemáticas arcanas que gobiernan las realidades y...",
            advantageAgainst = listOf("Orianna", "Orianna", "Viktor"),
            counteredBy = listOf("Zed", "Talon", "Fizz"),
            synergies = listOf("Ezreal", "Jhin", "Ashe"),
            tacticalAdvice = "Aprovecha el escalado y combos de Zoe en MID. Coordina el uso de su Salto dimensional para ganar ventajas en peleas grupales y objetivos de dragón/barón.",
            recommendedRunes = "Primer Golpe (Inspiración)",
            runeTreeDetails = "Inspiración: Destello Hextech • Se Avecina Tormenta • Trascendencia • Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Ignición"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "Báculo del Vacío", "Botas de Maná"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Morellonomicón"),
            itemSwaps = listOf(
ItemSwap(coreItem="Botas de Maná", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox"),
ItemSwap(coreItem="Báculo del Vacío", coreItemIcon="https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")
),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            isRanged = true,
            isFrontline = false,
            skills = listOf(
                ChampionSkill(
                    slot = "P",
                    slotName = "Pasiva",
                    name = "¡Más chispas!",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Zoe_P.png",
                    description = "Tras lanzar un hechizo, el siguiente ataque básico de Zoe inflige daño mágico adicional.",
                    cooldown = ""
                ),
                ChampionSkill(
                    slot = "1",
                    slotName = "Habilidad 1",
                    name = "Pádel estelar",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZoeQ.png",
                    description = "Zoe dispara un misil que puede redirigir en el aire. Cuanta más distancia haya recorrido en línea recta, más daño infligirá.",
                    cooldown = "8.5/8/7.5/7/6.5s"
                ),
                ChampionSkill(
                    slot = "2",
                    slotName = "Habilidad 2",
                    name = "Ladrona de hechizos",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZoeW.png",
                    description = "Zoe puede recoger los restos de los hechizos de invocador enemigos y las activas de los objetos y lanzarlos una vez. Cuando lanza un hechizo de invocador, consigue 3 misiles que dispara al objetivo más cercano.",
                    cooldown = "0.25s"
                ),
                ChampionSkill(
                    slot = "3",
                    slotName = "Habilidad 3",
                    name = "Burbuja somnífera",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZoeE.png",
                    description = "Deja al objetivo aletargado y hace que se duerma. Mientras está dormido, se reduce la resistencia mágica del objetivo. El daño del primer ataque que despierte al objetivo se duplica, hasta cierto límite.",
                    cooldown = "16/15/14/13/12s"
                ),
                ChampionSkill(
                    slot = "4",
                    slotName = "Definitiva",
                    name = "Salto dimensional",
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ZoeR.png",
                    description = "Zoe se traslada a una ubicación cercana durante 1 s y después vuelve al punto inicial.",
                    cooldown = "11/8/5s"
                )
            ),
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/zoe",
            wrMetaUrl = "https://wr-meta.com/champion/zoe/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/zoe/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/zoe"
        )
    )
}
