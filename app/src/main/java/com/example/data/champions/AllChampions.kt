package com.example.data.champions

import com.example.model.*

object AllChampions {
    val list = listOf(
        Champion(
            id = "aatrox",
            name = "Aatrox",
            nameEn = "",
            namePt = "",
            title = "la Espada de los Oscuros",
            titleEn = "",
            titlePt = "",
            ddragonId = "Aatrox",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Aatrox.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "A",
            winrate = 48.74,
            pickRate = 6.69,
            banRate = 10.23,
            damageType = DamageType.PHYSICAL,
            summary = "Aatrox y sus hermanos, otrora respetados defensores de Shurima contra el VacÃ­o, acabarÃ­an convirtiÃ©ndose en una amenaza aÃºn mayor para Runaterra y solo conocieron la derrota ante el uso astuto de hechizos mortales. No obstante, tras siglos de reclusiÃ³n...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Aatrox en TOP. Coordina el uso de su El Aniquilador de mundos para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Aspecto de la muerte", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Aatrox_Passive.png", description = "De forma periÃ³dica, el siguiente ataque bÃ¡sico de Aatrox inflige daÃ±o fÃ­sico adicional y cura a Aatrox en funciÃ³n de la vida mÃ¡xima del objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "La Espada de los Oscuros", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AatroxQ.png", description = "Aatrox golpea con su espadÃ³n e inflige daÃ±o fÃ­sico. Puede atacar tres veces, cada vez con un Ã¡rea de efecto distinta.", cooldown = "14/12/10/8/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Cadenas infernales", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AatroxW.png", description = "Aatrox golpea el suelo, lo que inflige daÃ±o al primer enemigo impactado. Los campeones y monstruos gigantes deben abandonar el Ã¡rea de impacto rÃ¡pidamente para evitar ser arrastrados hacia el centro y volver a recibir daÃ±o.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Deslizamiento sombrÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AatroxE.png", description = "Aatrox se cura de forma pasiva cuando inflige daÃ±o a campeones enemigos. Al activarse, se desliza en una direcciÃ³n.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "El Aniquilador de mundos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AatroxR.png", description = "Aatrox libera su forma demonÃ­aca, lo que aterroriza a los sÃºbditos enemigos cercanos, ademÃ¡s de aumentar su daÃ±o de ataque, curaciÃ³n y velocidad de movimiento. Si consigue un asesinato o asistencia, la duraciÃ³n de este efecto se alarga.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aatrox",
            wrMetaUrl = "https://wr-meta.com/champion/aatrox/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/aatrox/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/aatrox"
        ),
        Champion(
            id = "ahri",
            name = "Ahri",
            nameEn = "",
            namePt = "",
            title = "La Mujer Zorro de nueve Colas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ahri",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ahri.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 49.59,
            pickRate = 6.68,
            banRate = 0.43,
            damageType = DamageType.MAGIC,
            summary = "Ahri es una raposa vastaya conectada de forma innata a la magia del reino de los espÃ­ritus. Es capaz de manipular las emociones de su presa antes de consumir su esencia, proceso que le transmite los recuerdos de cada alma que consume. Otrora una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ahri en MID. Coordina el uso de su Impulso espiritual para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Robo de esencias", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ahri_SoulEater2.png", description = "Ahri se cura tras matar a 9 sÃºbditos o monstruos.Ahri se cura mÃ¡s cantidad tras eliminar a un campeÃ³n enemigo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Orbe del engaÃ±o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriQ.png", description = "Ahri lanza y recupera su orbe, lo que inflige daÃ±o mÃ¡gico al lanzarlo y daÃ±o verdadero al recuperarlo.", cooldown = "7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Fuego zorruno", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriW.png", description = "Ahri obtiene una breve mejora de velocidad de movimiento y lanza tres fuegos zorrunos que fijan y atacan a los enemigos cercanos.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Hechizar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriE.png", description = "Ahri lanza un beso que daÃ±a y hechiza al enemigo al que alcance primero, con lo que cancela instantÃ¡neamente todas sus habilidades de movimiento y hace que avance hacia ella de forma inofensiva.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Impulso espiritual", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AhriR.png", description = "Ahri se desliza hacia delante y libera rayos de esencia que infligen daÃ±o a los enemigos cercanos. Impulso espiritual puede usarse hasta tres veces antes de entrar en enfriamiento y las eliminaciones de campeones enemigos otorgan lanzamientos adicionales.", cooldown = "130/115/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ahri",
            wrMetaUrl = "https://wr-meta.com/champion/ahri/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ahri/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ahri"
        ),
        Champion(
            id = "akali",
            name = "Akali",
            nameEn = "",
            namePt = "",
            title = "la Asesina Sigilosa",
            titleEn = "",
            titlePt = "",
            ddragonId = "Akali",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Akali.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 46.47,
            pickRate = 5.08,
            banRate = 0.53,
            damageType = DamageType.MAGIC,
            summary = "Tras abandonar la Orden Kinkou y su tÃ­tulo de PuÃ±o de la Sombra, Akali actÃºa ahora en solitario y estÃ¡ lista para convertirse en el arma mortal que necesita su gente. Aunque no renuncia a las enseÃ±anzas de Shen, su maestro, ha jurado defender Jonia de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Akali en MID. Coordina el uso de su EjecuciÃ³n perfecta para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Marca del asesino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Akali_P.png", description = "Infligir daÃ±o de hechizo a un campeÃ³n crea un anillo de energÃ­a a su alrededor. Salir del anillo potencia el prÃ³ximo ataque de Akali con alcance y daÃ±o adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Pleno de cinco puntas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliQ.png", description = "Akali lanza cinco kunais que ralentizan e infligen daÃ±o en funciÃ³n de su daÃ±o de ataque y poder de habilidad adicionales.", cooldown = "1.5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Velo del crepÃºsculo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliW.png", description = "Akali lanza una cortina de humo y obtiene brevemente velocidad de movimiento. Mientras se encuentra dentro del Ã¡rea, Akali se vuelve invisible, no puede ser seleccionada como objetivo de hechizos ni ataques enemigos. Si ataca o usa habilidades, se revela durante unos instantes.", cooldown = "20/19/18/17/16s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Voltereta shuriken", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliE.png", description = "Da una voltereta hacia atrÃ¡s y lanza un shuriken hacia adelante que inflige daÃ±o mÃ¡gico. La primera nube de humo o enemigo golpeado queda marcado. Puede volver a usarse para desplazarse hasta el objetivo marcado e infligir daÃ±o adicional.", cooldown = "16/14.5/13/11.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "EjecuciÃ³n perfecta", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkaliR.png", description = "Akali salta hacia una direcciÃ³n y daÃ±a a los enemigos golpeados. Relanzamiento: Akali se desliza hacia una direcciÃ³n y ejecuta a todos los enemigos golpeados.", cooldown = "120/90/60s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/akali",
            wrMetaUrl = "https://wr-meta.com/champion/akali/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/akali/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/akali"
        ),
        Champion(
            id = "akshan",
            name = "Akshan",
            nameEn = "",
            namePt = "",
            title = "el Centinela Rebelde",
            titleEn = "",
            titlePt = "",
            ddragonId = "Akshan",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Akshan.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.ADC),
            tier = "A",
            winrate = 50.1,
            pickRate = 13.3,
            banRate = 4.3,
            damageType = DamageType.PHYSICAL,
            summary = "ImpÃ¡vido ante el peligro, Akshan combate el mal con gran carisma, ganas de impartir justa venganza y una evidente falta de camisetas. Domina el arte del combate sigiloso, por lo que es capaz de desaparecer a los ojos de sus enemigos y volver a aparecer...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Akshan en MID. Coordina el uso de su Merecido para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "ArtimaÃ±as", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/akshan_p.png", description = "Cada tres impactos de ataques y habilidades contra campeones, Akshan inflige daÃ±o adicional y obtiene un escudo.Cuando Akshan ataca, lanza un ataque adicional que inflige daÃ±o reducido. Si cancela el ataque adicional, obtiene velocidad de movimiento.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "BumerÃ¡n vengador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanQ.png", description = "Akshan lanza un bumerÃ¡n que inflige daÃ±o a la ida y a la vuelta. AdemÃ¡s, su alcance aumenta con cada enemigo golpeado.", cooldown = "8/7.25/6.5/5.75/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Rebelde", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanW.png", description = "Akshan marca de forma pasiva a los campeones enemigos como canallas cuando matan a sus aliados. Si Akshan mata a un canalla, revive a los aliados que haya asesinado, obtiene oro adicional y elimina todas las marcas.Al activarse, Akshan entra en estado de camuflaje y obtiene velocidad de movimiento y regeneraciÃ³n de manÃ¡ mientras se mueve hacia los canallas. Akshan pierde el camuflaje rÃ¡pidamente mientras no estÃ¡ en la maleza o cerca de un obstÃ¡culo del terreno.", cooldown = "18/14/10/6/2s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Balanceo heroico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanE.png", description = "Akshan lanza un gancho hacia un obstÃ¡culo del terreno y ataca repetidamente al enemigo mÃ¡s cercano conforme se balancea. Se puede soltar de la cuerda cuando quiera o al chocar contra campeones u obstÃ¡culo del terreno.", cooldown = "18/16.5/15/13.5/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Merecido", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AkshanR.png", description = "Akshan fija a un campeÃ³n enemigo como objetivo y empieza a acumular balas. Cuando se lanza la habilidad, dispara todas las balas acumuladas, lo que inflige daÃ±o segÃºn la vida que le falte al primer campeÃ³n, sÃºbdito o estructura golpeados.", cooldown = "100/85/70s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/akshan",
            wrMetaUrl = "https://wr-meta.com/champion/akshan/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/akshan/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/akshan"
        ),
        Champion(
            id = "alistar",
            name = "Alistar",
            nameEn = "",
            namePt = "",
            title = "El Minotauro",
            titleEn = "",
            titlePt = "",
            ddragonId = "Alistar",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Alistar.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 51.16,
            pickRate = 3.38,
            banRate = 0.67,
            damageType = DamageType.MAGIC,
            summary = "Alistar, un poderoso guerrero con una reputaciÃ³n temible, busca venganza por la muerte de su clan a manos del imperio noxiano. Aunque fue esclavizado y forzado a vivir como gladiador, fue su voluntad inquebrantable lo que le impidiÃ³ convertirse en una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Alistar en SUPPORT. Coordina el uso de su Voluntad inquebrantable para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Rugido triunfal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Alistar_E.png", description = "Alistar carga su rugido al aturdir o desplazar a campeones enemigos o cuando los enemigos cercanos mueren. Cuando estÃ¡ cargado al mÃ¡ximo, se cura a sÃ­ mismo y a los campeones aliados cercanos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "PulverizaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Pulverize.png", description = "Alistar golpea con fuerza el suelo, lo que inflige daÃ±o a los enemigos cercanos y los lanza por los aires.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Testarazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Headbutt.png", description = "Alistar propina un cabezazo al objetivo, daÃ±Ã¡ndolo y haciÃ©ndolo retroceder.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Pisotear", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AlistarE.png", description = "Alistar pisotea a las unidades enemigas cercanas, ignora la colisiÃ³n con unidades y obtiene acumulaciones si daÃ±a a un campeÃ³n enemigo. Con el mÃ¡ximo de acumulaciones, el siguiente ataque bÃ¡sico de Alistar contra un campeÃ³n enemigo inflige daÃ±o mÃ¡gico adicional y lo aturde.", cooldown = "12/11.5/11/10.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Voluntad inquebrantable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FerociousHowl.png", description = "Alistar profiere un gran rugido con el que elimina todos los efectos de control de adversario que le afectan y reduce el daÃ±o fÃ­sico y mÃ¡gico recibido mientras dura el efecto.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/alistar",
            wrMetaUrl = "https://wr-meta.com/champion/alistar/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/alistar/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/alistar"
        ),
        Champion(
            id = "ambessa",
            name = "Ambessa",
            nameEn = "",
            namePt = "",
            title = "la Matriarca de la Guerra",
            titleEn = "",
            titlePt = "",
            ddragonId = "ambessa",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ambessa.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "D",
            winrate = 50.27,
            pickRate = 1.38,
            banRate = 0.83,
            damageType = DamageType.PHYSICAL,
            summary = "Ambessa Medarda comanda el campo de batalla con implacable disciplina militar. Sus cadenas y hojas gemelas le permiten deslizarse continuamente entre habilidades para desgarrar a sus rivales.",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ambessa en TOP. Coordina el uso de su EjecuciÃ³n PÃºblica para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Paso de Cazadora", nameEn = "", namePt = "", iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/799.png", description = "Usar una habilidad permite a Ambessa deslizarse al atacar y potencia su siguiente ataque con alcance y daÃ±o fÃ­sico adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe de GuadaÃ±a", nameEn = "", namePt = "", iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/799.png", description = "Golpea con sus hojas gemelas en un arco infligiendo daÃ±o fÃ­sico a los enemigos cercanos, con daÃ±o crÃ­tico en el borde exterior.", cooldown = "8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Reprimenda", nameEn = "", namePt = "", iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/799.png", description = "Se protege con un escudo de absorciÃ³n y descarga un impacto sÃ­smico en el suelo causando daÃ±o fÃ­sico en Ã¡rea.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Macerar", nameEn = "", namePt = "", iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/799.png", description = "Gira sus cadenas con fuerza arrolladora, ralentizando a los enemigos impactados e infligiendo daÃ±o continuo.", cooldown = "10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "EjecuciÃ³n PÃºblica", nameEn = "", namePt = "", iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/799.png", description = "Se desliza imparable hacia el campeÃ³n enemigo objetivo en lÃ­nea recta, lo suprime y lo estrella contra el suelo causÃ¡ndole daÃ±o devastador.", cooldown = "65s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ambessa",
            wrMetaUrl = "https://wr-meta.com/champion/ambessa/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ambessa/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ambessa"
        ),
        Champion(
            id = "amumu",
            name = "Amumu",
            nameEn = "",
            namePt = "",
            title = "La Momia Triste",
            titleEn = "",
            titlePt = "",
            ddragonId = "Amumu",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Amumu.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 55.05,
            pickRate = 1.6,
            banRate = 0.11,
            damageType = DamageType.MAGIC,
            summary = "Cuenta la leyenda que Amumu es un alma solitaria y melancÃ³lica de la vieja Shurima que vaga por el mundo en busca de un amigo. Condenado por una maldiciÃ³n ancestral, su destino es permanecer solo para siempre, pues su tacto es muerte y su cariÃ±o, la...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Amumu en JUNGLE. Coordina el uso de su MaldiciÃ³n de la momia triste para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Toque maldito", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Amumu_Passive.png", description = "Los ataques bÃ¡sicos de Amumu aplican una maldiciÃ³n a sus enemigos, lo que provoca que reciban daÃ±o verdadero adicional cada vez que se les inflige daÃ±o mÃ¡gico.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Lanzamiento de vendas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BandageToss.png", description = "Amumu lanza una venda adhesiva a un objetivo, causÃ¡ndole daÃ±o y aturdimiento mientras se acerca a Ã©l.", cooldown = "3s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "DesesperaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuraofDespair.png", description = "Abrumados por la angustia, los enemigos cercanos pierden cada segundo un porcentaje de su vida mÃ¡xima y regeneran sus Maldiciones.", cooldown = "1s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Berrinche", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Tantrum.png", description = "Reduce permanentemente el daÃ±o fÃ­sico que recibe Amumu. Amumu puede desatar su furia para infligir daÃ±o a enemigos cercanos. Cada vez que recibe un golpe, se reduce el enfriamiento de Berrinche.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "MaldiciÃ³n de la momia triste", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CurseoftheSadMummy.png", description = "Amumu envuelve en vendas a las unidades enemigas cercanas, les aplica su MaldiciÃ³n, les inflige daÃ±o y las aturde.", cooldown = "150/125/100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/amumu",
            wrMetaUrl = "https://wr-meta.com/champion/amumu/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/amumu/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/amumu"
        ),
        Champion(
            id = "annie",
            name = "Annie",
            nameEn = "",
            namePt = "",
            title = "La Hija de la Oscuridad",
            titleEn = "",
            titlePt = "",
            ddragonId = "Annie",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Annie.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 47.04,
            pickRate = 2.41,
            banRate = 0.17,
            damageType = DamageType.MAGIC,
            summary = "Peligrosa pero encantadoramente precoz, Annie es una pequeÃ±a maga con un inmenso poder piromÃ¡ntico. Incluso en los parajes montaÃ±osos al norte de Noxus es una hechicera sin precedentes. Su afinidad natural con el fuego se manifestÃ³ a temprana edad en...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Annie en MID. Coordina el uso de su Invocar: Tibbers para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "PiromanÃ­a", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Annie_Passive.png", description = "DespuÃ©s de 4 lanzamientos, el siguiente hechizo ofensivo de Annie aturdirÃ¡ al objetivo.Annie comienza la partida y reaparece con PiromanÃ­a disponible.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "DesintegraciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieQ.png", description = "Annie lanza una bola de fuego imbuida de manÃ¡ que daÃ±a al objetivo y le devuelve el manÃ¡ gastado si este resulta destruido.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "IncineraciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieW.png", description = "Annie lanza un abrasador cono de fuego, daÃ±ando a todos los enemigos de la zona.", cooldown = "8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Escudo fundido", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieE.png", description = "Otorga a Annie o a un aliado un escudo y velocidad de movimiento adicional de forma temporal, y daÃ±a a los enemigos que le inflijan daÃ±o con ataques o hechizos.", cooldown = "12/11.5/11/10.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Invocar: Tibbers", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AnnieR.png", description = "Annie da vida a su oso Tibbers, que daÃ±a a todas las unidades de la zona. Tibbers puede atacar y quemar a los enemigos adyacentes.", cooldown = "130/115/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/annie",
            wrMetaUrl = "https://wr-meta.com/champion/annie/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/annie/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/annie"
        ),
        Champion(
            id = "ashe",
            name = "Ashe",
            nameEn = "",
            namePt = "",
            title = "La Arquera de Hielo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ashe",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ashe.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "B",
            winrate = 50.84,
            pickRate = 7.61,
            banRate = 0.24,
            damageType = DamageType.PHYSICAL,
            summary = "Ashe, comandante hija del hielo de la tribu de Avarosa, lidera las hordas mÃ¡s numerosas del norte. Impasible, inteligente e idealista, aunque incÃ³moda en su papel de lÃ­der, utiliza los poderes mÃ¡gicos ancestrales de su linaje para empuÃ±ar un arco de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ashe en ADC. Coordina el uso de su Flecha de cristal encantada para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Tiro congelador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ashe_P.png", description = "Los ataques de Ashe ralentizan a sus objetivos, aumentando el subsiguiente daÃ±o que les inflija.Los golpes crÃ­ticos de Ashe no infligen daÃ±o adicional al objetivo pero le aplican una ralentizaciÃ³n mÃ¡s potente.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "ConcentraciÃ³n mÃ¡xima", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AsheQ.png", description = "Ashe acumula ConcentraciÃ³n al atacar. Al alcanzar el nivel mÃ¡ximo, Ashe puede lanzar ConcentraciÃ³n mÃ¡xima para consumir todas las acumulaciones, aumentar temporalmente su velocidad de ataque y transformar su ataque bÃ¡sico en un poderoso ataque de rÃ¡faga durante unos instantes.", cooldown = "0s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Descarga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Volley.png", description = "Ashe dispara flechas en un cono para causar mÃ¡s daÃ±o. TambiÃ©n aplica Tiro congelador.", cooldown = "18/14.5/11/7.5/4s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Disparo de halcÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AsheSpiritOfTheHawk.png", description = "Ashe puede enviar su EspÃ­ritu HalcÃ³n en misiÃ³n de exploraciÃ³n para reconocer el terreno.", cooldown = "5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Flecha de cristal encantada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EnchantedCrystalArrow.png", description = "Ashe dispara un proyectil de hielo en lÃ­nea recta. Si la flecha impacta contra un campeÃ³n enemigo, le inflige daÃ±o y aturde segÃºn la distancia que haya recorrido la flecha. AdemÃ¡s, las unidades enemigas circundantes reciben daÃ±o y se ralentizan.", cooldown = "100/80/60s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ashe",
            wrMetaUrl = "https://wr-meta.com/champion/ashe/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ashe/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ashe"
        ),
        Champion(
            id = "aurelion_sol",
            name = "Aurelion Sol",
            nameEn = "",
            namePt = "",
            title = "El Forjador de Estrellas",
            titleEn = "",
            titlePt = "",
            ddragonId = "AurelionSol",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/AurelionSol.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 51.8,
            pickRate = 8.4,
            banRate = 10.1,
            damageType = DamageType.MAGIC,
            summary = "Aurelion Sol solÃ­a agraciar al vasto vacÃ­o del cosmos con las maravillas celestiales que Ã©l mismo ideaba. Ahora, se ve forzado a hacer uso de su increÃ­ble poder para satisfacer los deseos de un imperio espacial que lo ha engaÃ±ado para convertirlo en su...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Aurelion Sol en MID. Coordina el uso de su Estrella fugaz / Ocaso celeste para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Creador cÃ³smico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/AurelionSolP.png", description = "Las habilidades de daÃ±o de Aurelion Sol cosechan acumulaciones de polvo estelar de sus enemigos, lo que mejora permanentemente cada habilidad.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "El aliento de los dioses", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolQ.png", description = "Aurelion Sol canaliza su aliento de dragÃ³n durante unos segundos, lo que inflige daÃ±o al primer enemigo golpeado y daÃ±o reducido a los enemigos cercanos. Cada segundo que el aliento se canalice directamente a un enemigo, infligirÃ¡ daÃ±o adicional, que mejora segÃºn la cantidad de polvo estelar conseguido. Esta habilidad otorga polvo estelar si el objetivo es un campeÃ³n.", cooldown = "3s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Vuelo astral", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolW.png", description = "Aurelion Sol sobrevuela el terreno en la direcciÃ³n indicada. En este estado, puede lanzar otras habilidades. El aliento de los dioses ya no tiene enfriamiento, la puede canalizar sin lÃ­mite de tiempo e inflige daÃ±o adicional mientras vuela.El enfriamiento restante de Vuelo astral se reduce cada vez que muere un campeÃ³n tras recibir daÃ±o de Aurelion Sol.El polvo estelar aumenta el alcance mÃ¡ximo de Vuelo astral.", cooldown = "0s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Singularidad", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolE.png", description = "Aurelion Sol invoca un agujero negro, lo que inflige daÃ±o a los enemigos y los atrae lentamente hacia su centro. Esta habilidad otorga polvo estelar cada vez que muere un enemigo en el agujero negro y por cada segundo que haya un campeÃ³n enemigo en su interior. El centro del agujero negro ejecuta a los enemigos que estÃ©n por debajo de cierto porcentaje de su vida mÃ¡xima. El polvo estelar aumenta la zona de Singularidad y el umbral de ejecuciÃ³n.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Estrella fugaz / Ocaso celeste", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AurelionSolR.png", description = "Estrella fugaz: Aurelion Sol provoca que una estrella se estampe contra el suelo. Este impacto inflige daÃ±o mÃ¡gico y aturde a los enemigos. AdemÃ¡s, otorga polvo estelar por cada campeÃ³n golpeado. Al conseguir polvo estelar suficiente, transforma la siguiente Estrella fugaz de Aurelion Sol en Ocaso celeste.Ocaso celeste: Aurelion Sol lanza una gigantesca estrella con un Ã¡rea de impacto aumentada, potencia el daÃ±o infligido y lanza a los enemigos por los aires, en lugar de aturdirlos. DespuÃ©s, emite una enorme onda de choque desde los bordes del Ã¡rea de impacto, lo que inflige daÃ±o y ralentiza a los campeones golpeados. El polvo estelar aumenta el Ã¡rea de impacto de Estrella fugaz y Ocaso celeste.", cooldown = "120/110/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aurelion-sol",
            wrMetaUrl = "https://wr-meta.com/champion/aurelion-sol/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/aurelion-sol/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/aurelion-sol"
        ),
        Champion(
            id = "aurora",
            name = "Aurora",
            nameEn = "",
            namePt = "",
            title = "la Bruja entre Mundos",
            titleEn = "",
            titlePt = "",
            ddragonId = "Aurora",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Aurora.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "D",
            winrate = 49.42,
            pickRate = 3.45,
            banRate = 1.82,
            damageType = DamageType.MAGIC,
            summary = "Desde que naciÃ³, Aurora ha tenido una visiÃ³n Ãºnica de la vida gracias a su capacidad para moverse entre los reinos material y espiritual. Decidida a conocer mejor a los habitantes del reino espiritual, abandonÃ³ su hogar para ampliar sus conocimientos...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Aurora en MID. Coordina el uso de su Entre mundos para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "AbjuraciÃ³n espiritual", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/AuroraPassive.png", description = "Las habilidades y ataques de Aurora exorcizan espÃ­ritus de los enemigos a los que inflige daÃ±o. Los espÃ­ritus exorcizados siguen a Aurora, la curan y le otorgan velocidad de movimiento adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Ãnimas malditas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraQ.png", description = "Aurora lanza un proyectil que maldice a los enemigos golpeados. DespuÃ©s, puede reactivar la habilidad para atraer hacia ella las maldiciones activas, lo que inflige daÃ±o a los enemigos golpeados por el camino.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "A travÃ©s del velo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraW.png", description = "Aurora brinca, se adentra en el reino espiritual al aterrizar y se vuelve invisible durante un breve periodo de tiempo.", cooldown = "22/21/20/19/18s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Estallido espectral", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraE.png", description = "Aurora hace converger los reinos y emite un pulso de energÃ­a espiritual que inflige daÃ±o y ralentiza a los enemigos alcanzados antes de brincar a un lugar seguro.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Entre mundos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AuroraR.png", description = "Aurora brinca y emite una onda de choque que inflige daÃ±o y ralentiza a los enemigos golpeados. DespuÃ©s, crea un Ã¡rea que atrapa a los enemigos en su interior y permite a Aurora teleportarse de un lado al otro del Ã¡rea.", cooldown = "140/120/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aurora",
            wrMetaUrl = "https://wr-meta.com/champion/aurora/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/aurora/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/aurora"
        ),
        Champion(
            id = "bard",
            name = "Bardo",
            nameEn = "",
            namePt = "",
            title = "El GuardiÃ¡n Errante",
            titleEn = "",
            titlePt = "",
            ddragonId = "Bard",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Bard.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 52.6,
            pickRate = 10.0,
            banRate = 10.5,
            damageType = DamageType.MAGIC,
            summary = "Bardo, un viajero de mÃ¡s allÃ¡ de las estrellas, es un agente de la serendipia que lucha para mantener un equilibrio en el que la vida pueda soportar la indiferencia del caos. Muchos habitantes de Runaterra cantan canciones que hablan de su naturaleza...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Bardo en SUPPORT. Coordina el uso de su Destino maleable para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Llamada del viajero", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Bard_Passive.png", description = "Meeps: Bardo atrae a unos espÃ­ritus menores que aumentan la potencia de sus ataques bÃ¡sicos para infligir daÃ±o mÃ¡gico adicional. Cuando Bardo haya recogido suficientes campanas, sus meeps tambiÃ©n infligirÃ¡n daÃ±o en Ã¡rea y ralentizarÃ¡n a los enemigos golpeados.Campanas: Aparecen campanas antiguas al azar que Bardo puede recoger. Otorgan experiencia, restauran manÃ¡ y otorgan velocidad de movimiento fuera de combate.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Cadenas cÃ³smicas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardQ.png", description = "Bardo dispara un proyectil que ralentiza al primer enemigo alcanzado, antes de seguir su trayectoria. A partir de ahÃ­, si golpea un muro, el objetivo inicial queda aturdido. Si golpea a otro enemigo, los dos sufren el efecto.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Santuario del protector", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardW.png", description = "Hace aparecer un santuario de vida. El santuario, que tarda unos segundos en cargarse a plena potencia, desaparece despuÃ©s de curar y aumentar la velocidad al primer aliado que lo toca.", cooldown = "0s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Periplo mÃ¡gico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardE.png", description = "Bardo abre un portal en un obstÃ¡culo del terreno cercano. Tanto sus aliados como sus enemigos pueden atravesarlo para cruzar al otro lado, pero solo funciona en un sentido.", cooldown = "22/20.5/19/17.5/16s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Destino maleable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BardR.png", description = "Bardo lanza su energÃ­a espiritual a un objetivo, dejando en estasis a todas las unidades y torretas de la zona durante breve tiempo.", cooldown = "110/95/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/bard",
            wrMetaUrl = "https://wr-meta.com/champion/bard/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/bard/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/bard"
        ),
        Champion(
            id = "blitzcrank",
            name = "Blitzcrank",
            nameEn = "",
            namePt = "",
            title = "El Gran GÃ³lem de Vapor",
            titleEn = "",
            titlePt = "",
            ddragonId = "Blitzcrank",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Blitzcrank.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 49.89,
            pickRate = 9.87,
            banRate = 4.09,
            damageType = DamageType.MAGIC,
            summary = "Blitzcrank es un autÃ³mata enorme, casi indestructible, creado originalmente para el tratamiento de residuos tÃ³xicos. Sin embargo, este propÃ³sito original le parecÃ­a demasiado restrictivo, asÃ­ que se automodificÃ³ para servir mejor a los dÃ©biles del...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Blitzcrank en SUPPORT. Coordina el uso de su Campo estÃ¡tico para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Barrera de manÃ¡", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Blitzcrank_ManaBarrier.png", description = "Cuando le queda poca vida, Blitzcrank obtiene un escudo en funciÃ³n de su manÃ¡.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Agarre misil", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RocketGrab.png", description = "Blitzcrank dispara su mano derecha para apresar a un rival que encuentre en su camino, le inflige daÃ±o y lo atrae hacia Ã©l.", cooldown = "20/19/18/17/16s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Sobrecarga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Overdrive.png", description = "Blitzcrank se sobrecarga para aumentar drÃ¡sticamente su velocidad de movimiento y su velocidad de ataque. Se ve ralentizado temporalmente cuando termina el efecto.", cooldown = "15s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "PuÃ±o de poder", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PowerFist.png", description = "Blitzcrank carga su puÃ±o para que su siguiente ataque cause el doble de daÃ±o y lance al objetivo por los aires.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Campo estÃ¡tico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/StaticField.png", description = "Los enemigos a los que Blitzcrank ataca quedan marcados y reciben una descarga elÃ©ctrica despuÃ©s de 1 s. AdemÃ¡s, Blitzcrank puede activar esta habilidad para eliminar los escudos de los enemigos cercanos, infligirles daÃ±o y silenciarlos durante un breve periodo.", cooldown = "60/40/20s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/blitzcrank",
            wrMetaUrl = "https://wr-meta.com/champion/blitzcrank/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/blitzcrank/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/blitzcrank"
        ),
        Champion(
            id = "brand",
            name = "Brand",
            nameEn = "",
            namePt = "",
            title = "La Venganza Ardiente",
            titleEn = "",
            titlePt = "",
            ddragonId = "Brand",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Brand.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "S",
            winrate = 51.83,
            pickRate = 9.49,
            banRate = 10.79,
            damageType = DamageType.MAGIC,
            summary = "Brand, antiguo miembro de la tribu Kegan Rodhe del helado Freljord, es una lecciÃ³n sobre la tentaciÃ³n de un poder mayor. En busca de una de las legendarias Runas GeogÃ©nicas, Kegan traicionÃ³ a sus compaÃ±eros y se quedÃ³ con la runa. El hombre desapareciÃ³...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Brand en SUPPORT. Coordina el uso de su DetonaciÃ³n Ã­gnea para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Nube de fuego", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/BrandP.png", description = "Los hechizos de Brand prenden fuego a sus objetivos, lo que inflige daÃ±o durante 4 s. Puede acumularse hasta 3 veces. Si Brand asesina a un enemigo que estÃ© en llamas, recupera manÃ¡. Cuando Nube de fuego alcanza el mÃ¡ximo de acumulaciones en un campeÃ³n o monstruo gigante, se vuelve inestable. Explota al cabo de 2 s, aplica efectos de hechizo e inflige una gran cantidad de daÃ±o en la zona que rodea a la vÃ­ctima.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Abrasar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandQ.png", description = "Brand lanza una bola de fuego que inflige daÃ±o mÃ¡gico. Si el objetivo estÃ¡ en llamas, Abrasar lo dejarÃ¡ aturdido durante 1,5 s.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Pilar de llamas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandW.png", description = "Tras un breve retardo, Brand crea un Pilar de llamas en la ubicaciÃ³n del objetivo que inflige daÃ±o mÃ¡gico a las unidades enemigas dentro de la misma. Las unidades que estÃ©n en llamas recibirÃ¡n un 25% de daÃ±o adicional.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Incendio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandE.png", description = "Brand lanza un ataque poderoso a su objetivo que se extiende a los enemigos cercanos e inflige daÃ±o mÃ¡gico. Si el objetivo estÃ¡ en llamas, se duplica la propagaciÃ³n de Incendio.", cooldown = "13/12/11/10/9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "DetonaciÃ³n Ã­gnea", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BrandR.png", description = "Brand libera un torrente de fuego devastador que rebota hasta 5 veces entre Brand y los enemigos cercanos, e inflige daÃ±o mÃ¡gico a los enemigos cada vez que rebota. Estos rebotes priorizan acumular al mÃ¡ximo Nube de fuego en los campeones. Si un objetivo estÃ¡ en llamas, DetonaciÃ³n Ã­gnea lo ralentizarÃ¡ brevemente.", cooldown = "110/100/90s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/brand",
            wrMetaUrl = "https://wr-meta.com/champion/brand/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/brand/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/brand"
        ),
        Champion(
            id = "braum",
            name = "Braum",
            nameEn = "",
            namePt = "",
            title = "El CorazÃ³n de Freljord",
            titleEn = "",
            titlePt = "",
            ddragonId = "Braum",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Braum.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 51.72,
            pickRate = 3.67,
            banRate = 1.17,
            damageType = DamageType.MAGIC,
            summary = "Bendecido con bÃ­ceps enormes y un corazÃ³n aÃºn mÃ¡s grande, Braum es un hÃ©roe muy apreciado en Freljord. Todas las tabernas al norte del Fuerte Helado brindan por su fuerza legendaria. Se dice que talÃ³ un bosque de robles en una sola noche y convirtiÃ³ una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Braum en SUPPORT. Coordina el uso de su Fisura glacial para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Golpes conmocionantes", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Braum_Passive.png", description = "Los ataques bÃ¡sicos de Braum aplican Golpes conmocionantes. Cuando se aplique la primera acumulaciÃ³n, los ataques bÃ¡sicos de los aliados tambiÃ©n acumulan Golpes conmocionantes. Al llegar a 4 acumulaciones, el objetivo queda aturdido y recibe daÃ±o mÃ¡gico. Durante los siguientes segundos no puede recibir acumulaciones, pero recibe daÃ±o mÃ¡gico adicional de los ataques de Braum.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Mordisco invernal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumQ.png", description = "Braum lanza un chorro de gÃ©lido hielo desde el escudo que ralentiza y causa daÃ±o mÃ¡gico.Aplica una acumulaciÃ³n de Golpes conmocionantes.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "DetrÃ¡s de mÃ­", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumW.png", description = "Braum salta hacia un campeÃ³n o sÃºbdito aliado. Al alcanzarlo, ambos obtienen armadura y resistencia mÃ¡gica durante unos segundos.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Inquebrantable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumE.png", description = "Braum alza su escudo en una direcciÃ³n durante varios segundos e intercepta todos los proyectiles, que lo golpean y son destruidos. Anula por completo el daÃ±o del primero y reduce el de los siguientes que llegan desde la misma direcciÃ³n.", cooldown = "16/14/12/10/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Fisura glacial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/BraumRWrapper.png", description = "Braum golpea el suelo y lanza por los aires a los enemigos cercanos y situados en una lÃ­nea delante de Ã©l. A lo largo de esta lÃ­nea se abre una fisura que ralentiza a los enemigos.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/braum",
            wrMetaUrl = "https://wr-meta.com/champion/braum/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/braum/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/braum"
        ),
        Champion(
            id = "caitlyn",
            name = "Caitlyn",
            nameEn = "",
            namePt = "",
            title = "La Sheriff de Piltover",
            titleEn = "",
            titlePt = "",
            ddragonId = "Caitlyn",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Caitlyn.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 48.59,
            pickRate = 22.16,
            banRate = 3.01,
            damageType = DamageType.PHYSICAL,
            summary = "Reconocida como su mejor pacificadora, Caitlyn es tambiÃ©n la mejor arma de Piltover para librar a la ciudad de sus elusivos elementos criminales. A menudo trabaja con Vi, y actÃºa como un frÃ­o y eficiente contrapunto para la naturaleza mÃ¡s impetuosa de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Caitlyn en ADC. Coordina el uso de su As en la manga para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Disparo a la cabeza", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Caitlyn_Headshot.png", description = "Cada pocos ataques bÃ¡sicos o contra un objetivo atrapado en una de sus trampas o en su red, Caitlyn realiza un tiro a la cabeza que inflige daÃ±o adicional, el cual aumenta con su probabilidad de crÃ­tico. El alcance de Disparo a la cabeza de Caitlyn se duplica cuando ataca a objetivos atrapados en una trampa o una red.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Pacificadora de Piltover", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CaitlynQ.png", description = "Caitlyn carga su rifle durante 1 s para liberar un disparo penetrante que provoca daÃ±o fÃ­sico y se ensancha al alcanzar a un objetivo (causa menos daÃ±o a los objetivos posteriores).", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Trampa para yordles", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CaitlynW.png", description = "Caitlyn pone una trampa que, al activarse, inmoviliza al campeÃ³n enemigo y lo revela durante 1,5 s, lo que permite que Caitlyn aseste un Disparo a la cabeza potenciado.", cooldown = "0.5s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Red de calibre 90", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CaitlynE.png", description = "Caitlyn lanza una red pesada para ralentizar a su objetivo. El retroceso empuja hacia atrÃ¡s a Caitlyn.", cooldown = "16/14/12/10/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "As en la manga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CaitlynR.png", description = "Caitlyn se toma su tiempo para preparar el tiro perfecto, causando una gran cantidad de daÃ±o a un solo objetivo a gran distancia. Los campeones enemigos pueden interceptar la bala para sus aliados.", cooldown = "90s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/caitlyn",
            wrMetaUrl = "https://wr-meta.com/champion/caitlyn/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/caitlyn/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/caitlyn"
        ),
        Champion(
            id = "camille",
            name = "Camille",
            nameEn = "",
            namePt = "",
            title = "la Sombra de Acero",
            titleEn = "",
            titlePt = "",
            ddragonId = "Camille",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Camille.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "D",
            winrate = 50.36,
            pickRate = 1.41,
            banRate = 0.11,
            damageType = DamageType.PHYSICAL,
            summary = "Convertida en un arma viviente diseÃ±ada para operar fuera de la ley, Camille es la jefa de espÃ­as del clan Ferros, una elegante agente de Ã©lite que se asegura de que nada amenace el funcionamiento de Piltover ni de Zaun. Su adaptabilidad y minuciosidad...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Camille en TOP. Coordina el uso de su UltimÃ¡tum hextech para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Defensa adaptable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Camille_Passive.png", description = "Los ataques bÃ¡sicos a campeones le otorgan un escudo equivalente a un porcentaje de la vida mÃ¡xima de Camille que la protege de su tipo de daÃ±o (fÃ­sico o mÃ¡gico) durante un corto periodo de tiempo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Protocolo de precisiÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CamilleQ.png", description = "El prÃ³ximo ataque de Camille inflige daÃ±o adicional y otorga mÃ¡s velocidad de movimiento. Este hechizo puede lanzarse de nuevo durante un breve periodo de tiempo, de modo que inflige una cantidad adicional de daÃ±o considerablemente mayor si Camille deja un tiempo entre los dos ataques.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Barrido tÃ¡ctico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CamilleW.png", description = "Tras un breve lapso de tiempo, Camille provoca una explosiÃ³n en forma de cono que inflige daÃ±o. Los enemigos en la mitad exterior se ralentizan y reciben daÃ±o adicional al mismo tiempo que curan a Camille.", cooldown = "17/15.5/14/12.5/11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Tiro de gancho", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CamilleE.png", description = "Camille se desliza hasta un muro, salta y golpea a los enemigos al caer.", cooldown = "16/15/14/13/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "UltimÃ¡tum hextech", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CamilleR.png", description = "Camille se desliza hacia un campeÃ³n objetivo y lo ancla en el Ã¡rea. TambiÃ©n inflige daÃ±o mÃ¡gico adicional al objetivo con sus ataques bÃ¡sicos.", cooldown = "140/115/90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/camille",
            wrMetaUrl = "https://wr-meta.com/champion/camille/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/camille/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/camille"
        ),
        Champion(
            id = "cho_gath",
            name = "Cho'Gath",
            nameEn = "",
            namePt = "",
            title = "El Terror del VacÃ­o",
            titleEn = "",
            titlePt = "",
            ddragonId = "Chogath",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Chogath.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.5,
            pickRate = 14.8,
            banRate = 14.4,
            damageType = DamageType.MAGIC,
            summary = "Desde el momento en que Cho'Gath emergiÃ³ por primera vez a la dura luz solar de Runaterra, a la bestia solo le impulsaba el hambre mÃ¡s pura e insaciable. La perfecta expresiÃ³n del deseo del VacÃ­o de acabar con la vida, la compleja biologÃ­a de Cho'Gath...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Cho'Gath en TOP. Coordina el uso de su FestÃ­n para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "CarnÃ­voro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/GreenTerror_TailSpike.png", description = "Cho'Gath recupera vida y manÃ¡ cada vez que elimina a un enemigo. La cantidad aumenta con el nivel de Cho'Gath.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Ruptura", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Rupture.png", description = "Resquebraja el suelo de un lugar determinado y lanza por los aires a los enemigos, lo que les inflige daÃ±o y les ralentiza.", cooldown = "6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Grito salvaje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FeralScream.png", description = "Cho'Gath lanza un terrible grito a los enemigos en un cono, lo que les inflige daÃ±o mÃ¡gico y los silencia durante unos pocos segundos.", cooldown = "11/10.5/10/9.5/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Pinchos mortÃ­feros", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/VorpalSpikes.png", description = "Los ataques de Cho'Gath liberan pinchos mortales que daÃ±an y ralentizan a todas las unidades enemigas frente a Ã©l.", cooldown = "8/7/6/5/4s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "FestÃ­n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Feast.png", description = "Devora a una unidad enemiga e inflige una gran cantidad de daÃ±o verdadero. Si el objetivo muere, Cho'Gath crece y aumenta su vida mÃ¡xima.", cooldown = "80/70/60s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/cho-gath",
            wrMetaUrl = "https://wr-meta.com/champion/cho-gath/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/cho-gath/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/cho-gath"
        ),
        Champion(
            id = "corki",
            name = "Corki",
            nameEn = "",
            namePt = "",
            title = "El Bombardero Osado",
            titleEn = "",
            titlePt = "",
            ddragonId = "Corki",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Corki.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.ADC),
            tier = "S+",
            winrate = 52.5,
            pickRate = 5.5,
            banRate = 13.3,
            damageType = DamageType.TRUE_HYBRID,
            summary = "El piloto yordle Corki adora dos cosas por encima de todas las demÃ¡s: volar y su glamuroso bigote. Aunque no necesariamente en ese orden. Tras abandonar la Ciudad de Bandle, se estableciÃ³ en Piltover y se enamorÃ³ de las mÃ¡quinas maravillosas que allÃ­...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Corki en MID. Coordina el uso de su Andanada de Proyectiles para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Municiones hextech", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Corki_RapidReload.png", description = "Un porcentaje del daÃ±o de ataque bÃ¡sico de Corki se inflige como daÃ±o verdadero adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Bomba de fÃ³sforo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PhosphorusBomb.png", description = "Corki lanza una bomba luminosa a la ubicaciÃ³n seleccionada e inflige daÃ±o mÃ¡gico a los enemigos cercanos. AdemÃ¡s, este ataque revela a las unidades de la zona durante un tiempo.", cooldown = "9/8.5/8/7.5/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Valquiria", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/CarpetBomb.png", description = "Corki vuela una corta distancia lanzando bombas y dejando tras de sÃ­ una estela de fuego, la cual inflige daÃ±o a los enemigos que permanezcan dentro.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "CaÃ±Ã³n de repeticiÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GGun.png", description = "El caÃ±Ã³n de repeticiÃ³n de Corki abre fuego sobre un Ã¡rea cÃ³nica. Los enemigos alcanzados sufren daÃ±o y pierden armadura y resistencia mÃ¡gica.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Andanada de Proyectiles", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissileBarrage.png", description = "Corki dispara hacia la zona objetivo un proyectil que explota al impactar e inflige daÃ±o a los enemigos cercanos. Corki acumula misiles segÃºn pasa el tiempo, hasta un mÃ¡ximo determinado. Cada 3 proyectiles sale uno enorme que inflige daÃ±o adicional.", cooldown = "2s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/corki",
            wrMetaUrl = "https://wr-meta.com/champion/corki/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/corki/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/corki"
        ),
        Champion(
            id = "darius",
            name = "Darius",
            nameEn = "",
            namePt = "",
            title = "La Mano de Noxus",
            titleEn = "",
            titlePt = "",
            ddragonId = "Darius",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Darius.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 50.41,
            pickRate = 18.9,
            banRate = 9.03,
            damageType = DamageType.PHYSICAL,
            summary = "No hay mayor sÃ­mbolo del poder de Noxus que Darius, el comandante mÃ¡s temido y mÃ¡s curtido en batallas de toda la naciÃ³n. Pasando de orÃ­genes humildes a convertirse en la Mano de Noxus, se abre paso a tajos entre los enemigos del imperio, muchos de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Darius en TOP. Coordina el uso de su Guillotina noxiana para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Hemorragia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Darius_Icon_Hemorrhage.png", description = "Los ataques y habilidades de daÃ±o de Darius hacen que los enemigos sangren durante 5 s, lo que les causa daÃ±o fÃ­sico. Se puede acumular hasta 5 veces. Darius se enfurece y obtiene un gran daÃ±o de ataque cuando su objetivo alcanza el mÃ¡ximo de acumulaciones.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Diezmar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DariusCleave.png", description = "Darius reÃºne fuerzas y traza un amplio cÃ­rculo con su hacha. Los enemigos a los que golpee con su hoja sufrirÃ¡n mÃ¡s daÃ±o que los que sean golpeados por el mango. Darius se cura en funciÃ³n al nÃºmero de campeones enemigos y monstruos gigantes alcanzados por la hoja.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Golpe atroz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DariusNoxianTacticsONH.png", description = "El siguiente ataque de Darius alcanza al enemigo en una arteria vital. Mientras se desangra, su velocidad de movimiento disminuye.", cooldown = "5s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Atrapar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DariusAxeGrabCone.png", description = "Darius pone a punto su hacha, lo cual provoca que, de forma pasiva, su daÃ±o fÃ­sico ignore un porcentaje de la armadura de su objetivo. Cuando se activa, Darius barre a sus enemigos con el gancho de su hacha y los acerca hacia Ã©l.", cooldown = "24/21.5/19/16.5/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Guillotina noxiana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DariusExecute.png", description = "Darius salta hacia un campeÃ³n enemigo y le asesta un golpe letal que inflige daÃ±o verdadero. Este daÃ±o se ve incrementado por cada acumulaciÃ³n de Hemorragia que tenga el objetivo. Si el ataque con Guillotina noxiana es letal, se reinicia el enfriamiento durante un corto periodo de tiempo.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/darius",
            wrMetaUrl = "https://wr-meta.com/champion/darius/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/darius/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/darius"
        ),
        Champion(
            id = "diana",
            name = "Diana",
            nameEn = "",
            namePt = "",
            title = "El DesdÃ©n de la Luna",
            titleEn = "",
            titlePt = "",
            ddragonId = "Diana",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Diana.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "C",
            winrate = 52.67,
            pickRate = 1.46,
            banRate = 0.3,
            damageType = DamageType.MAGIC,
            summary = "Portadora de una espada en forma de media luna, Diana es una guerrera de los Lunari, una fe rechazada en casi todas las tierras a los pies del Monte Targon. Ataviada con una armadura reluciente del color de la nieve en una noche de invierno, es la...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Diana en JUNGLE. Coordina el uso de su Lluvia de luna para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Hoja lunaplata", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Diana_Passive_LunarBlade.png", description = "Cada tres golpes, inflige daÃ±o mÃ¡gico adicional a los enemigos cercanos. DespuÃ©s de lanzar un hechizo, Diana obtiene velocidad de ataque durante 5 segundos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Impacto creciente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DianaQ.png", description = "Libera un rayo de energÃ­a lunar en un arco que inflige daÃ±o mÃ¡gico.Los enemigos golpeados se verÃ¡n afectados por Luz lunar, que revela durante 3 s a los objetivos que no estÃ©n en sigilo.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Cascada pÃ¡lida", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DianaOrbs.png", description = "Diana crea tres esferas que orbitan a su alrededor y explotan al entrar en contacto con enemigos, lo que inflige daÃ±o en una zona. TambiÃ©n obtiene un escudo temporal que absorbe daÃ±o. Si la tercera esfera explota, el escudo absorbe mÃ¡s daÃ±o.", cooldown = "15/13.5/12/10.5/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Torrente lunar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DianaTeleport.png", description = "Diana se transforma en la encarnaciÃ³n de la luna mÃ¡s vengativa, se desliza hacia un enemigo y le inflige daÃ±o mÃ¡gico.Torrente lunar no tiene enfriamiento cuando se usa para deslizarse hacia un objetivo afectado por Luz lunar. El efecto de Luz lunar desaparecerÃ¡ del resto de enemigos, independientemente de haber sido atacados con Torrente lunar o no.", cooldown = "22/20/18/16/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Lluvia de luna", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DianaR.png", description = "Diana revela y atrae a todos los enemigos cercanos, y los ralentiza.Si Diana atrae a un campeÃ³n enemigo o mÃ¡s, un torrente de luz lunar se precipita hacia ella tras unos instantes e inflige daÃ±o mÃ¡gico en una zona a su alrededor, que aumenta por cada objetivo adicional atraÃ­do con la habilidad.", cooldown = "100/90/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/diana",
            wrMetaUrl = "https://wr-meta.com/champion/diana/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/diana/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/diana"
        ),
        Champion(
            id = "dr_mundo",
            name = "Dr. Mundo",
            nameEn = "",
            namePt = "",
            title = "El Loco de Zaun",
            titleEn = "",
            titlePt = "",
            ddragonId = "DrMundo",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/DrMundo.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "A+",
            winrate = 51.4,
            pickRate = 8.5,
            banRate = 5.1,
            damageType = DamageType.MAGIC,
            summary = "Loco de remate, trÃ¡gicamente homicida, terriblemente morado: el Dr. Mundo es lo que mantiene en sus casas a muchos habitantes de Zaun en las noches especialmente oscuras. Este autoproclamado doctor fue paciente del psiquiÃ¡trico mÃ¡s infame de Zaun. Tras...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Dr. Mundo en TOP. Coordina el uso de su Dosis mÃ¡xima para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Va donde quiere", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/DrMundo_P.png", description = "Dr. Mundo resiste el siguiente efecto inmovilizador que lo golpea, pero pierde vida y deja caer un vial quÃ­mico cerca. Puede recoger dicho vial caminando sobre Ã©l, lo que restaura vida y reduce el enfriamiento de esta pasiva.Dr. Mundo tambiÃ©n tiene gran regeneraciÃ³n de vida adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Serrucho infectado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DrMundoQ.png", description = "Dr. Mundo lanza un serrucho infectado que inflige daÃ±o al primer enemigo golpeado segÃºn su vida actual y lo ralentiza.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "DesisfibrulaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DrMundoW.png", description = "Dr. Mundo se autoelectrocuta, lo que inflige daÃ±o constante a los enemigos cercanos y almacena parte del daÃ±o recibido. Al final de la duraciÃ³n o al volver a lanzar la habilidad, Dr. Mundo inflige daÃ±o explosivo a los enemigos cercanos. Si la explosiÃ³n alcanza a un enemigo, cura un porcentaje del daÃ±o acumulado.", cooldown = "17/16.5/16/15.5/15s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Traumatismo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DrMundoE.png", description = "Pasiva: Dr. Mundo obtiene daÃ±o de ataque adicional, que aumenta segÃºn la vida mÃ¡xima.Activa: Dr. Mundo golpea con su ''botiquÃ­n'' a un enemigo, lo que inflige daÃ±o adicional segÃºn la vida que le falte. Si el enemigo muere, lo empuja, lo que inflige daÃ±o a los enemigos a los que atraviesa.", cooldown = "9/8.25/7.5/6.75/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Dosis mÃ¡xima", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DrMundoR.png", description = "Dr. Mundo se inyecta productos quÃ­micos, lo que restaura al instante un porcentaje de la vida que le falte. DespuÃ©s, obtiene velocidad de movimiento adicional y se cura parte de su vida mÃ¡xima durante un largo periodo de tiempo.", cooldown = "120s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/dr-mundo",
            wrMetaUrl = "https://wr-meta.com/champion/dr-mundo/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/dr-mundo/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/dr-mundo"
        ),
        Champion(
            id = "draven",
            name = "Draven",
            nameEn = "",
            namePt = "",
            title = "El Ejecutor Glorioso",
            titleEn = "",
            titlePt = "",
            ddragonId = "Draven",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Draven.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 47.67,
            pickRate = 10.1,
            banRate = 7.54,
            damageType = DamageType.PHYSICAL,
            summary = "En Noxus, los guerreros conocidos como 'justicieros' se enfrentan en recintos donde corre la sangre y se pone a prueba la fortaleza. Pero ninguno ha alcanzado la fama de Draven, un antiguo soldado cuyo sentido del espectÃ¡culo y habilidad sin igual con...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Draven en ADC. Coordina el uso de su Espiral de muerte para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "League of Draven", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Draven_passive.png", description = "Draven consigue la AdoraciÃ³n de sus fans cuando atrapa un Hacha giratoria o elimina a un sÃºbdito, monstruo o torre. Asesinar a campeones enemigos le otorga a Draven oro adicional en funciÃ³n de la AdoraciÃ³n que tenga.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Hacha giratoria", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DravenSpinning.png", description = "El siguiente ataque de Draven inflige daÃ±o fÃ­sico adicional. Tras golpear al objetivo, el hacha saldrÃ¡ volando por los aires. Si Draven la atrapa, prepararÃ¡ automÃ¡ticamente otra Hacha giratoria. Draven puede tener dos Hachas giratorias a la vez.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "SubidÃ³n de adrenalina", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DravenFury.png", description = "Draven aumenta la velocidad de movimiento y la velocidad de ataque. La bonificaciÃ³n de velocidad de movimiento disminuye rÃ¡pidamente con el tiempo. Al atrapar un Hacha giratoria, se recuperarÃ¡ el enfriamiento de SubidÃ³n de adrenalina.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "A un lado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DravenDoubleShot.png", description = "Draven lanza sus hachas, lo que inflige daÃ±o fÃ­sico a los objetivos golpeados y los echa a un lado. Los objetivos impactados se ven ralentizados.", cooldown = "18/17/16/15/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Espiral de muerte", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DravenRCast.png", description = "Draven lanza dos hachas gigantescas para infligir daÃ±o fÃ­sico a cada unidad alcanzada. Las hachas de Espiral de muerte cambian lentamente de direcciÃ³n y regresan a Draven tras golpear a un campeÃ³n enemigo. Draven tambiÃ©n puede activar esta habilidad mientras las hachas estÃ¡n de camino para hacer que vuelvan antes. Inflige menos daÃ±o por cada unidad que golpea y se reinicia cuando el hacha cambia de direcciÃ³n. Ejecuta a los enemigos que tengan menos vida que el nÃºmero de acumulaciones de AdoraciÃ³n de Draven.", cooldown = "100/90/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/draven",
            wrMetaUrl = "https://wr-meta.com/champion/draven/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/draven/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/draven"
        ),
        Champion(
            id = "ekko",
            name = "Ekko",
            nameEn = "",
            namePt = "",
            title = "El Chico que QuebrÃ³ el Tiempo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ekko",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ekko.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "B",
            winrate = 50.11,
            pickRate = 3.84,
            banRate = 0.38,
            damageType = DamageType.MAGIC,
            summary = "Ekko, un prodigio surgido de las implacables calles de Zaun, manipula el tiempo para sacar ventaja de todas las situaciones. Con una mÃ¡quina de su invenciÃ³n llamada Dispositivo Z, explora las distintas posibilidades de la realidad hasta alcanzar el...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ekko en JUNGLE. Coordina el uso de su Fisura temporal para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Resonancia Z", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ekko_P.png", description = "Cada tercer ataque o hechizo daÃ±ino lanzado sobre un mismo objetivo inflige daÃ±o mÃ¡gico adicional, y Ekko obtiene una mejora de velocidad de movimiento si el objetivo es un campeÃ³n.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Engranaje temporal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EkkoQ.png", description = "Ekko lanza una granada temporal que, al alcanzar a un campeÃ³n enemigo, genera un campo de distorsiÃ³n cronolÃ³gica. Las unidades sorprendidas en su interior sufren daÃ±o y quedan ralentizadas. Al cabo de unos instantes, la granada regresa a Ekko y daÃ±a a todo cuanto se encuentre en su camino.", cooldown = "9/8.5/8/7.5/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Convergencia paralela", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EkkoW.png", description = "Los ataques bÃ¡sicos de Ekko infligen daÃ±o mÃ¡gico adicional a objetivos con poca vida. Con Convergencia paralela puede romper la lÃ­nea temporal y, al cabo de unos segundos, crea una anomalÃ­a que ralentiza a los enemigos situados dentro. Si el propio Ekko entra en ella, recibe un escudo y aturde a los enemigos al dejarlos congelados en el tiempo.", cooldown = "22/20/18/16/14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Salto de fase", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EkkoE.png", description = "Ekko rueda por el suelo en un movimiento evasivo mientras activa su Dispositivo Z. Su siguiente ataque inflige daÃ±o adicional y distorsiona la realidad para teleportarlo hasta su objetivo.", cooldown = "9/8.5/8/7.5/7s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Fisura temporal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EkkoR.png", description = "Ekko fragmenta su lÃ­nea temporal y se vuelve imposible de seleccionar como objetivo mientras regresa a un momento pasado mÃ¡s favorable. Reaparece en el mismo sitio donde se encontraba unos segundos antes y recupera una parte de la vida que hubiera perdido en ese tiempo. Los enemigos situados cerca de su zona de llegada reciben muchÃ­simo daÃ±o.", cooldown = "110/80/50s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ekko",
            wrMetaUrl = "https://wr-meta.com/champion/ekko/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ekko/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ekko"
        ),
        Champion(
            id = "evelynn",
            name = "Evelynn",
            nameEn = "",
            namePt = "",
            title = "El Abrazo AgÃ³nico",
            titleEn = "",
            titlePt = "",
            ddragonId = "Evelynn",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Evelynn.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.26,
            pickRate = 1.96,
            banRate = 0.55,
            damageType = DamageType.MAGIC,
            summary = "En los oscuros adentros de Runaterra, el sÃºcubo Evelynn deambula en busca de su siguiente vÃ­ctima. Acecha a sus presas con una voluptuosa fachada femenina, pero cuando alguien sucumbe a sus encantos, Evelynn libera su autÃ©ntica forma. DespuÃ©s somete a...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Evelynn en JUNGLE. Coordina el uso de su Hacedora de viudas para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Sombra demonÃ­aca", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Evelynn_Passive.png", description = "Cuando no estÃ¡ en combate, Evelynn entra en modo Sombra demonÃ­aca. Sombra demonÃ­aca cura a Evelynn cuando estÃ¡ baja de vida y le otorga camuflaje tras alcanzar el nivel 6.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "PÃºa de odio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EvelynnQ.png", description = "Evelynn golpea con su lÃ¡tigo e inflige daÃ±o a la primera unidad que alcanza. DespuÃ©s, Evelynn puede lanzar una lÃ­nea de pÃºas a los enemigos cercanos hasta 3 veces.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Sed de lujuria", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EvelynnW.png", description = "Evelynn maldice a su objetivo y provoca que, tras un breve lapso de tiempo, el prÃ³ximo ataque o habilidad lo hechice y le reduzca la resistencia mÃ¡gica.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Latigazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EvelynnE.png", description = "Evelynn azota a su objetivo con su lÃ¡tigo y le inflige daÃ±o. Obtiene velocidad de movimiento durante un breve periodo de tiempo.", cooldown = "8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Hacedora de viudas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EvelynnR.png", description = "Evelynn se vuelve invulnerable brevemente y asola la zona que tiene delante, y despuÃ©s reaparece mucho mÃ¡s atrÃ¡s.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/evelynn",
            wrMetaUrl = "https://wr-meta.com/champion/evelynn/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/evelynn/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/evelynn"
        ),
        Champion(
            id = "ezreal",
            name = "Ezreal",
            nameEn = "",
            namePt = "",
            title = "El Explorador PrÃ³digo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ezreal",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ezreal.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "A",
            winrate = 51.83,
            pickRate = 10.21,
            banRate = 0.59,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Ezreal, un aventurero aficionado a deslizarse y dotado de artes mÃ¡gicas sin saberlo, saquea catacumbas perdidas, lidia con maldiciones ancestrales y supera con facilidad adversidades imposibles. De valor y chulerÃ­a sin lÃ­mites, prefiere improvisar para...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ezreal en ADC. Coordina el uso de su Andanada certera para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Pies Veloces (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Linaje â€¢ Golpe de Gracia â€¢ Coraza Ã“sea",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Fuerza de hechizo naciente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ezreal_RisingSpellForce.png", description = "La velocidad de ataque de Ezreal aumenta progresivamente cada vez que acierta un hechizo, hasta un mÃ¡ximo de 5 acumulaciones.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Disparo mÃ­stico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EzrealQ.png", description = "Ezreal lanza un rayo de energÃ­a que reduce brevemente todos sus enfriamientos si alcanza a alguna unidad enemiga.", cooldown = "5.5/5.25/5/4.75/4.5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Flujo de esencia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EzrealW.png", description = "Ezreal dispara un orbe que se adhiere al primer campeÃ³n u objetivo alcanzado. Si Ezreal golpea a un enemigo con el orbe, este explota e inflige daÃ±o.", cooldown = "8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "AlteraciÃ³n arcana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EzrealE.png", description = "Ezreal se teleporta a una ubicaciÃ³n cercana a un objetivo y dispara un rayo certero que golpea a la unidad enemiga mÃ¡s cercana. DarÃ¡ prioridad a los enemigos marcados con Flujo de esencia.", cooldown = "26/23/20/17/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Andanada certera", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EzrealR.png", description = "Ezreal se prepara antes de disparar una potente andanada de energÃ­a que inflige mucho daÃ±o a cada unidad que atraviese (el daÃ±o es menor contra sÃºbditos y monstruos no Ã©picos).", cooldown = "120/105/90s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ezreal",
            wrMetaUrl = "https://wr-meta.com/champion/ezreal/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ezreal/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ezreal"
        ),
        Champion(
            id = "fiddlesticks",
            name = "Fiddlesticks",
            nameEn = "",
            namePt = "",
            title = "el Terror Ancestral",
            titleEn = "",
            titlePt = "",
            ddragonId = "Fiddlesticks",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Fiddlesticks.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 49.47,
            pickRate = 1.68,
            banRate = 0.23,
            damageType = DamageType.MAGIC,
            summary = "Algo ha despertado en Runaterra. Algo ancestral. Algo terrible. El horror conocido como Fiddlesticks acecha en las lindes de la sociedad, en las zonas en las que impera la paranoia, donde se alimenta de aquellos a los que aterroriza. Armada con una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Fiddlesticks en JUNGLE. Coordina el uso de su Tormenta de cuervos para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Un simple espantapÃ¡jaros", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/FiddlesticksP.png", description = "El talismÃ¡n de Fiddlesticks se reemplaza por figuras de espantapÃ¡jaros.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Terror", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FiddleSticksQ.png", description = "Fiddlesticks infunde miedo al infligir daÃ±o con habilidades sin ser visto o al activar Terror seleccionando como objetivo a una unidad enemiga, provocando que los enemigos afectados huyan despavoridos mientras dure el efecto.", cooldown = "15/14.5/14/13.5/13s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Cosecha abundante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FiddleSticksW.png", description = "Fiddlesticks drena la vida de los enemigos cercanos e inflige daÃ±o de ejecuciÃ³n adicional al final del efecto.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Segar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FiddleSticksE.png", description = "Fiddlesticks ataca en Ã¡rea con su guadaÃ±a, lo que ralentiza a todos los enemigos alcanzados y silencia a aquellos que golpea en el centro de la cuchillada.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Tormenta de cuervos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FiddleSticksR.png", description = "Una bandada de cuervos rodea a Fiddlesticks e inflige daÃ±o cada segundo a todas las unidades enemigas de la zona.", cooldown = "140/110/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/fiddlesticks",
            wrMetaUrl = "https://wr-meta.com/champion/fiddlesticks/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/fiddlesticks/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/fiddlesticks"
        ),
        Champion(
            id = "fiora",
            name = "Fiora",
            nameEn = "",
            namePt = "",
            title = "La Estocada Excelsa",
            titleEn = "",
            titlePt = "",
            ddragonId = "Fiora",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Fiora.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 45.46,
            pickRate = 2.57,
            banRate = 0.41,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Fiora, la duelista mÃ¡s temida de Valoran, ha alcanzado renombre por su estilo brusco y su mente astuta, asÃ­ como por la velocidad de su estoque. Fiora naciÃ³ en el seno de la Casa Laurent, en el reino de Demacia, y asumiÃ³ el control de la familia a raÃ­z...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Vayne", "Morgana", "Zed"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Fiora en TOP. Coordina el uso de su Duelo excelso para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Ã‰gida de Fuego Solar", "Cota de Espinas", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Coraza del Muerto", "Protector PÃ©treo"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3742.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Baile de duelista", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Fiora_P.png", description = "Fiora ha revelado un punto vital de este campeÃ³n. Si golpea este punto vital, restaura vida y obtiene velocidad de movimiento.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Embestida", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FioraQ.png", description = "Fiora se abalanza hacia un punto y apuÃ±ala a un enemigo cercano, al que causa daÃ±o fÃ­sico y aplica efectos de impacto.", cooldown = "13/11.25/9.5/7.75/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "RÃ©plica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FioraW.png", description = "Fiora bloquea todo el daÃ±o y las debilitaciones durante breve tiempo y luego golpea en una direcciÃ³n. La estocada ralentiza al primer campeÃ³n enemigo alcanzado, o lo aturde si Fiora bloqueÃ³ un efecto de inmovilizaciÃ³n con la habilidad.", cooldown = "24/22/20/18/16s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Esgrima", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FioraE.png", description = "Fiora cuenta con velocidad de ataque adicional durante dos ataques. El primero ralentiza al objetivo y el segundo asesta un golpe crÃ­tico.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Duelo excelso", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FioraR.png", description = "Fiora localiza los cuatro puntos vitales de un campeÃ³n enemigo y obtiene velocidad de movimiento mientras estÃ¡ cerca de ellos. Si lo alcanza en los cuatro puntos vitales o el objetivo muere despuÃ©s de que haya alcanzado al menos uno, Fiora y sus aliados de la zona reciben curaciÃ³n durante los siguientes segundos.", cooldown = "110/90/70s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/fiora",
            wrMetaUrl = "https://wr-meta.com/champion/fiora/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/fiora/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/fiora"
        ),
        Champion(
            id = "fizz",
            name = "Fizz",
            nameEn = "",
            namePt = "",
            title = "El Gamberro de las Mareas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Fizz",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Fizz.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 49.59,
            pickRate = 3.51,
            banRate = 6.28,
            damageType = DamageType.MAGIC,
            summary = "Fizz es un yordle anfibio que habita entre los arrecifes de alrededor de Aguas Estancadas. Suele recuperar y devolver los diezmos arrojados al mar por capitanes supersticiosos, pero incluso los marineros mÃ¡s agudos saben que no hay que plantarle cara...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Fizz en MID. Coordina el uso de su Carnaza para tiburones para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Luchador Ã¡gil", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Fizz_P.png", description = "Fizz se puede mover a travÃ©s de unidades y reduce una cantidad fija el daÃ±o proveniente de cualquier fuente.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe de erizo de mar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzQ.png", description = "Fizz atraviesa a su objetivo, inflige daÃ±o mÃ¡gico y aplica efectos de impacto.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Tridente piedramar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzW.png", description = "Los ataques de Fizz hacen que sus enemigos se desangren y sufran daÃ±o mÃ¡gico durante varios segundos. Fizz puede potenciar su siguiente ataque para infligir daÃ±o adicional y potenciar sus siguientes ataques durante un breve periodo de tiempo.", cooldown = "7/6.5/6/5.5/5s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "JuguetÃ³n/Gamberro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzE.png", description = "Fizz salta al aire y aterriza grÃ¡cilmente sobre su lanza, siendo imposible atacarlo. Desde esta posiciÃ³n, Fizz puede dejarse caer para aplastar el suelo u optar por saltar de nuevo antes de hacerlo.", cooldown = "16/14/12/10/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Carnaza para tiburones", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/FizzR.png", description = "Fizz lanza hacia una direcciÃ³n un pez que se adhiere al primer campeÃ³n que toca y lo ralentiza. Tras un momento, sale un tiburÃ³n de la tierra que lanza al objetivo por los aires y empuja a un lado a los enemigos cercanos. Todos los enemigos alcanzados sufren daÃ±o mÃ¡gico y son ralentizados.", cooldown = "100/85/70s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/fizz",
            wrMetaUrl = "https://wr-meta.com/champion/fizz/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/fizz/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/fizz"
        ),
        Champion(
            id = "galio",
            name = "Galio",
            nameEn = "",
            namePt = "",
            title = "el Coloso",
            titleEn = "",
            titlePt = "",
            ddragonId = "Galio",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Galio.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "A",
            winrate = 52.17,
            pickRate = 9.55,
            banRate = 2.42,
            damageType = DamageType.MAGIC,
            summary = "Fuera de la reluciente ciudad de Demacia, el coloso de piedra Galio se mantiene vigilante. Construido como un baluarte contra los magos enemigos, suele permanecer inmÃ³vil durante dÃ©cadas hasta que la presencia de magia poderosa lo vuelve a traer a la...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Galio en MID. Coordina el uso de su Entrada heroica para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Aplastamiento colosal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Galio_Passive.png", description = "Cada pocos segundos, el siguiente ataque bÃ¡sico de Galio inflige daÃ±o mÃ¡gico adicional en un Ã¡rea.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Vientos de guerra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioQ.png", description = "Galio lanza dos remolinos de viento que se unen y convierten en un gran tornado que inflige daÃ±o prolongado.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Escudo de Durand", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioW.png", description = "Galio carga en posiciÃ³n defensiva y se mueve lentamente. Tras liberar la carga, Galio provocarÃ¡ y daÃ±arÃ¡ a los enemigos cercanos.", cooldown = "18/17/16/15/14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "PuÃ±etazo justiciero", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioE.png", description = "Galio darÃ¡ un pequeÃ±o paso hacia atrÃ¡s, cargarÃ¡ hacia delante y aturdirÃ¡ al primer enemigo que se encuentre en su camino.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Entrada heroica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GalioR.png", description = "Galio designa la ubicaciÃ³n de un campeÃ³n aliado como punto de aterrizaje y otorga un escudo mÃ¡gico a todos los aliados en la zona. Tras un breve lapso de tiempo, Galio aterriza en la ubicaciÃ³n y lanza a los enemigos cercanos por los aires.", cooldown = "180/160/140s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/galio",
            wrMetaUrl = "https://wr-meta.com/champion/galio/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/galio/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/galio"
        ),
        Champion(
            id = "garen",
            name = "Garen",
            nameEn = "",
            namePt = "",
            title = "El Poder de Demacia",
            titleEn = "",
            titlePt = "",
            ddragonId = "Garen",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Garen.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 50.91,
            pickRate = 11.19,
            banRate = 2.4,
            damageType = DamageType.PHYSICAL,
            summary = "Garen, un orgulloso y noble guerrero, lucha en las filas de la Vanguardia ImpertÃ©rrita. Es querido entre sus compaÃ±eros y respetado por sus enemigos, y no solo por ser vÃ¡stago de la prestigiosa familia Crownguard, responsable de la defensa de Demacia y...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Garen en TOP. Coordina el uso de su Justicia demaciana para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Perseverancia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Garen_Passive.png", description = "Si Garen no se ha visto afectado recientemente por ataques o habilidades de enemigos, regenera un porcentaje de su vida mÃ¡xima cada segundo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe decisivo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GarenQ.png", description = "Garen obtiene un aumento de velocidad de movimiento y se libra de todas las ralentizaciones. Su siguiente ataque golpea una zona vital de su enemigo, lo que inflige daÃ±o adicional y lo silencia.", cooldown = "8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Coraje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GarenW.png", description = "Garen aumenta de forma pasiva su armadura y su resistencia mÃ¡gica al asesinar a enemigos. TambiÃ©n puede activar esta habilidad para obtener un escudo y una mejora de tenacidad durante un breve periodo de tiempo, seguido de una reducciÃ³n de daÃ±o menor que dura mÃ¡s.", cooldown = "23/21/19/17/15s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Juicio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GarenE.png", description = "Garen hace girar su espada rÃ¡pidamente a su alrededor e inflige daÃ±o fÃ­sico a los enemigos cercanos.", cooldown = "9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Justicia demaciana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GarenR.png", description = "Garen invoca el poder de Demacia para intentar ejecutar a un campeÃ³n enemigo.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/garen",
            wrMetaUrl = "https://wr-meta.com/champion/garen/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/garen/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/garen"
        ),
        Champion(
            id = "gnar",
            name = "Gnar",
            nameEn = "",
            namePt = "",
            title = "El EslabÃ³n Perdido",
            titleEn = "",
            titlePt = "",
            ddragonId = "Gnar",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Gnar.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 53.23,
            pickRate = 2.34,
            banRate = 0.37,
            damageType = DamageType.PHYSICAL,
            summary = "Gnar es un yordle primitivo cuyas payasadas lÃºdicas pueden estallar en la ira de un niÃ±o pequeÃ±o en un instante, transformÃ¡ndolo en una bestia enorme empeÃ±ada en la destrucciÃ³n. Congelado en Hielo Puro durante milenios, la curiosa criatura se liberÃ³ y...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Gnar en TOP. Coordina el uso de su Â¡GNAR! para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Gen de furia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Gnar_Passive.png", description = "Gnar genera furia en combate. Al llegar a su nivel mÃ¡ximo, su prÃ³xima habilidad lo transforma en Mega-Gnar, una forma en la que es mÃ¡s resistente y tiene acceso a nuevas habilidades.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "BumerÃ¡n / PeÃ±ascazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GnarQ.png", description = "Gnar lanza un bumerÃ¡n que daÃ±a y ralentiza a los enemigos que alcanza antes de volver a Ã©l. Si recoge el bumerÃ¡n, el enfriamiento se reduce.Mega-Gnar lanza un peÃ±asco a un enemigo y daÃ±a y ralentiza a los que estÃ¡n a su alrededor. TambiÃ©n en este caso se puede recoger el proyectil para reducir el enfriamiento.", cooldown = "20/17.5/15/12.5/10s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "HÃ­per / Golpazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GnarW.png", description = "Los ataques y hechizos de Gnar lo aceleran, con lo que causa daÃ±o adicional y aumenta su velocidad de movimiento.Mega-Gnar estÃ¡ demasiado furioso para correr y lo que hace es levantarse y golpear con violencia la zona que tiene delante. Los enemigos situados en ella quedan aturdidos.", cooldown = "7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Brinco / Sacudida", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GnarE.png", description = "Gnar salta sobre una ubicaciÃ³n y, si aterriza sobre la cabeza de una unidad, puede seguir saltando.Mega-Gnar es demasiado grande para rebotar, asÃ­ que aterriza con fuerza devastadora y causa daÃ±o en la zona circundante.", cooldown = "22/19.5/17/14.5/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Â¡GNAR!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GnarR.png", description = "Mega-Gnar arroja en una direcciÃ³n a todos los enemigos circundantes, que resultan daÃ±ados y ralentizados. Cualquier enemigo que choque con un muro queda aturdido y recibe daÃ±o adicional.", cooldown = "90/60/30s"),
        ),
            isRanged = true,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/gnar",
            wrMetaUrl = "https://wr-meta.com/champion/gnar/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/gnar/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/gnar"
        ),
        Champion(
            id = "gragas",
            name = "Gragas",
            nameEn = "",
            namePt = "",
            title = "El Camorrista",
            titleEn = "",
            titlePt = "",
            ddragonId = "Gragas",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Gragas.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.MID),
            tier = "S+",
            winrate = 53.3,
            pickRate = 4.8,
            banRate = 3.7,
            damageType = DamageType.MAGIC,
            summary = "Alegre e imponente por partes iguales, Gragas es un cervecero enorme y provocador en su propia bÃºsqueda de la pinta de cerveza perfecta. Proveniente de lugares desconocidos, ahora busca ingredientes raros entre los intactos pÃ¡ramos de Freljord, probando...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Gragas en JUNGLE. Coordina el uso de su Tonel explosivo para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Hora feliz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/GragasPassiveHeal.png", description = "Gragas se cura de forma periÃ³dica al usar una habilidad.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Barril rodante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GragasQ.png", description = "Gragas hace rodar su tonel hacia un lugar especÃ­fico. La explosiÃ³n se puede activar o, de lo contrario, explotarÃ¡ automÃ¡ticamente tras 4 s. La potencia de la explosiÃ³n aumenta a lo largo del tiempo. Los enemigos a los que alcance la explosiÃ³n sufrirÃ¡n una reducciÃ³n de velocidad de movimiento.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Furia ebria", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GragasW.png", description = "Gragas engulle cerveza de su tonel durante 1 s. Al terminar, su estado de embriaguez lo hace mÃ¡s poderoso, por lo que inflige daÃ±o mÃ¡gico a los enemigos cercanos en su siguiente ataque bÃ¡sico y disminuye el daÃ±o recibido.", cooldown = "5s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Barrigazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GragasE.png", description = "Gragas carga hacia un lugar determinado y choca contra el primer enemigo con el que se topa, lo que inflige daÃ±o a todas las unidades enemigas cercanas y las aturde.", cooldown = "14/13.5/13/12.5/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Tonel explosivo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GragasR.png", description = "Gragas arroja su tonel hacia un lugar, lo que causa daÃ±o y empuja hacia atrÃ¡s a los enemigos a los que alcance con la explosiÃ³n.", cooldown = "100/85/70s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/gragas",
            wrMetaUrl = "https://wr-meta.com/champion/gragas/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/gragas/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/gragas"
        ),
        Champion(
            id = "graves",
            name = "Graves",
            nameEn = "",
            namePt = "",
            title = "El Forajido",
            titleEn = "",
            titlePt = "",
            ddragonId = "Graves",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Graves.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S",
            winrate = 50.27,
            pickRate = 9.25,
            banRate = 0.94,
            damageType = DamageType.PHYSICAL,
            summary = "Malcolm Graves es un famoso mercenario, jugador y ladrÃ³n. Un hombre buscado en todas las ciudades e imperios que ha visitado. A pesar de su temperamento explosivo, posee un firme sentido del honor criminal, que a menudo ejecuta con su escopeta de doble...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Graves en JUNGLE. Coordina el uso de su DaÃ±o colateral para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Nuevo destino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/GravesTrueGrit.png", description = "La escopeta de Graves tiene caracterÃ­sticas Ãºnicas. Tiene que recargar cuando se queda sin municiÃ³n. Los ataques lanzan 4 balas que no pueden atravesar unidades. Hacen retroceder a los objetivos que no sean campeones y hayan sido alcanzados por varias balas.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Fin de trayecto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GravesQLineSpell.png", description = "Graves dispara un proyectil explosivo que detona tras 2 segundos, o tras 0,2 segundos si impacta en un obstÃ¡culo.", cooldown = "13/11.25/9.5/7.75/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Pantalla de humo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GravesSmokeGrenade.png", description = "Graves dispara una bomba al Ã¡rea seleccionada y crea una nube de humo que reduce el alcance de visiÃ³n. Los enemigos a los que alcance el primer impacto sufren daÃ±o mÃ¡gico y su velocidad de movimiento se reduce brevemente.", cooldown = "26/24/22/20/18s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Disparo veloz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GravesMove.png", description = "Graves se lanza hacia adelante, obteniendo una mejora de armadura durante varios segundos. Si se desplaza hacia un campeÃ³n enemigo, en cambio, gana dos acumulaciones de AutÃ©ntico valor. Golpear a los enemigos con ataques bÃ¡sicos reduce el enfriamiento de esta habilidad y renueva las mejoras defensivas.", cooldown = "16/15/14/13/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "DaÃ±o colateral", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GravesChargeShot.png", description = "Graves dispara un tiro explosivo que inflige mucho daÃ±o al primer campeÃ³n en el que impacte. Tras golpear a un campeÃ³n o llegar al final de su alcance, la bala explota infligiendo daÃ±o en un cono.", cooldown = "100/80/60s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/graves",
            wrMetaUrl = "https://wr-meta.com/champion/graves/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/graves/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/graves"
        ),
        Champion(
            id = "gwen",
            name = "Gwen",
            nameEn = "",
            namePt = "",
            title = "La Costurera Consagrada",
            titleEn = "",
            titlePt = "",
            ddragonId = "Gwen",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Gwen.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 50.32,
            pickRate = 3.38,
            banRate = 1.43,
            damageType = DamageType.MAGIC,
            summary = "Gwen, una muÃ±eca a la que la magia confiriÃ³ vida, va armada con los mismos utensilios que en su dÃ­a la confeccionaron. Lleva consigo la fuerza del amor de su creadora en cada paso que da y es consciente de la maravilla de vivir. La Niebla consagrada...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Gwen en TOP. Coordina el uso de su Bordado para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Mil cortes", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Gwen_Passive.png", description = "Los ataques de Gwen infligen daÃ±o mÃ¡gico adicional segÃºn la vida de los objetivos. Se cura una parte del daÃ±o que inflige a campeones con este efecto.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Â¡Corta, corta!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GwenQ.png", description = "Gwen blande sus tijeras hasta 6 veces en un arco frente a ella infligiendo daÃ±o mÃ¡gico. Inflige daÃ±o verdadero a las unidades en el centro y aplica su pasiva con cada ataque.", cooldown = "6.5/5.75/5/4.25/3.5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Niebla consagrada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GwenW.png", description = "Gwen invoca una niebla que la protege de los enemigos en el exterior. Solo puede ser objetivo de los enemigos que entren en la niebla.", cooldown = "22/21/20/19/18s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Salto y corte", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GwenE.png", description = "Gwen se desliza una pequeÃ±a distancia y obtiene velocidad de ataque, alcance y daÃ±o mÃ¡gico al golpear durante unos pocos segundos. Si golpea a un enemigo durante ese periodo de tiempo, el enfriamiento de esta habilidad se recupera parcialmente.", cooldown = "13/12.5/12/11.5/11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Bordado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/GwenR.png", description = "Gwen lanza una aguja que ralentiza, inflige daÃ±o mÃ¡gico y aplica Mil cortes a los campeones golpeados. Esta habilidad se puede lanzar hasta dos veces mÃ¡s y cada lanzamiento arroja agujas adicionales e inflige mÃ¡s daÃ±o. Gwen debe golpear a un enemigo entre cada lanzamiento para desbloquear el siguiente.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/gwen",
            wrMetaUrl = "https://wr-meta.com/champion/gwen/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/gwen/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/gwen"
        ),
        Champion(
            id = "hecarim",
            name = "Hecarim",
            nameEn = "",
            namePt = "",
            title = "La Sombra de la Guerra",
            titleEn = "",
            titlePt = "",
            ddragonId = "Hecarim",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Hecarim.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 46.11,
            pickRate = 1.67,
            banRate = 0.74,
            damageType = DamageType.PHYSICAL,
            summary = "Hecarim es una fusiÃ³n espectral de hombre y bestia, condenado a arrollar las almas de los vivos por toda la eternidad. Cuando las Islas Bendecidas cayeron en la sombra, este orgulloso caballero fue aniquilado por las energÃ­as destructivas de la Ruina...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Hecarim en JUNGLE. Coordina el uso de su Envite de sombras para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Sendero de guerra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Hecarim_Passive.png", description = "Hecarim obtiene daÃ±o de ataque igual a un porcentaje de su velocidad de movimiento adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Alboroto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HecarimRapidSlash.png", description = "Hecarim hiende su arma, infligiendo daÃ±o fÃ­sico a los enemigos cercanos. Si Hecarim inflige daÃ±o al menos a un enemigo, aumenta el daÃ±o y reduce el enfriamiento de los siguientes Alborotos.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "EspÃ­ritu de pavor", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HecarimW.png", description = "Hecarim obtiene armadura y resistencia mÃ¡gica. Hecarim inflige daÃ±o mÃ¡gico a los enemigos cercanos y recupera una cantidad de vida equivalente a un porcentaje del daÃ±o recibido por dichos enemigos.", cooldown = "14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Carga devastadora", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HecarimRamp.png", description = "La velocidad de movimiento de Hecarim aumenta y puede moverse entre unidades durante un breve periodo de tiempo. Su siguiente ataque empuja al objetivo e inflige daÃ±o fÃ­sico adicional en funciÃ³n de la distancia recorrida tras haber activado la habilidad.", cooldown = "20/19/18/17/16s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Envite de sombras", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HecarimUlt.png", description = "Hecarim invoca jinetes espectrales y carga, infligiendo daÃ±o mÃ¡gico en lÃ­nea recta. Hecarim crea una onda de choque al terminar su carga que hace que los enemigos cercanos huyan aterrorizados.", cooldown = "140/120/100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/hecarim",
            wrMetaUrl = "https://wr-meta.com/champion/hecarim/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/hecarim/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/hecarim"
        ),
        Champion(
            id = "heimerdinger",
            name = "Heimerdinger",
            nameEn = "",
            namePt = "",
            title = "El Inventor Venerado",
            titleEn = "",
            titlePt = "",
            ddragonId = "Heimerdinger",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Heimerdinger.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.SUPPORT),
            tier = "D",
            winrate = 50.69,
            pickRate = 2.1,
            banRate = 1.7,
            damageType = DamageType.MAGIC,
            summary = "El profesor Cecil B. Heimerdinger, un cientÃ­fico yordle excÃ©ntrico pero brillante, es considerado una de las mentes mÃ¡s innovadoras y uno de los inventores mÃ¡s admirados de la historia de Piltover. Tiene una dedicaciÃ³n incesante en su trabajo hasta el...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Heimerdinger en MID. Coordina el uso de su Â¡MEJORA! para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Afinidad hextech", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Heimerdinger_Passive.png", description = "ObtÃ©n velocidad de movimiento al estar cerca de torres aliadas y de torretas desplegadas por Heimerdinger.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Torreta evolucionada H-28 G", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerQ.png", description = "Heimerdinger despliega una torreta de fuego rÃ¡pido equipada con un segundo caÃ±Ã³n de ataque (las torretas infligen la mitad de daÃ±o a las torres).", cooldown = "1s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Microcohetes hextech", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerW.png", description = "Heimerdinger dispara proyectiles de largo alcance que convergen en su cursor.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Granada de tormenta de electrones CH-2", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerE.png", description = "Heimerdinger lanza una granada que causa daÃ±o, aturde a las unidades que reciben el impacto de forma directa y ralentiza a las unidades cercanas.", cooldown = "11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Â¡MEJORA!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HeimerdingerR.png", description = "Heimerdinger inventa una mejora con la que aumenta los efectos de su prÃ³xima habilidad.", cooldown = "100/85/70s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/heimerdinger",
            wrMetaUrl = "https://wr-meta.com/champion/heimerdinger/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/heimerdinger/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/heimerdinger"
        ),
        Champion(
            id = "irelia",
            name = "Irelia",
            nameEn = "",
            namePt = "",
            title = "la Danza de las Cuchillas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Irelia",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Irelia.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 49.74,
            pickRate = 2.19,
            banRate = 0.99,
            damageType = DamageType.PHYSICAL,
            summary = "La ocupaciÃ³n noxiana de Jonia produjo mucho hÃ©roes, pero ninguno mÃ¡s improbable que la joven Irelia de Navori. Se entrenÃ³ en las antiguas danzas de su gente y adaptÃ³ su arte para la guerra, usando los movimientos elegantes y diligentemente practicados...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Irelia en TOP. Coordina el uso de su Filo de la vanguardia para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Fervor jonio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Irelia_Passive.png", description = "Cuando las habilidades de Irelia golpean a enemigos, obtiene una mejora acumulable de velocidad de ataque. Con el mÃ¡ximo de acumulaciones, sus ataques bÃ¡sicos tambiÃ©n infligen daÃ±o adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Embate de espada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/IreliaQ.png", description = "Irelia se desliza hacia delante, golpea a su objetivo y se cura. Si el objetivo queda marcado o muere con Embate de espada, se reinicia el enfriamiento.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Danza desafiante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/IreliaW.png", description = "Irelia carga un golpe que inflige mÃ¡s daÃ±o cuanto mÃ¡s lo cargue. Recibe menos daÃ±o fÃ­sico mientras carga.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "DÃºo impecable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/IreliaE.png", description = "Irelia envÃ­a dos cuchillas que convergen entre sÃ­. Los enemigos atrapados en medio de las cuchillas reciben daÃ±o, quedan aturdidos y son marcados.", cooldown = "16/15/14/13/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Filo de la vanguardia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/IreliaR.png", description = "Irelia libera una gran cantidad de cuchillas que explotan hacia fuera al alcanzar a un campeÃ³n enemigo. Los enemigos alcanzados por las cuchillas reciben daÃ±o y quedan marcados. DespuÃ©s, las cuchillas forman un muro que inflige daÃ±o y ralentiza a los enemigos que pasen por Ã©l.", cooldown = "125/105/85s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/irelia",
            wrMetaUrl = "https://wr-meta.com/champion/irelia/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/irelia/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/irelia"
        ),
        Champion(
            id = "janna",
            name = "Janna",
            nameEn = "",
            namePt = "",
            title = "La Furia de la Tormenta",
            titleEn = "",
            titlePt = "",
            ddragonId = "Janna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Janna.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 47.22,
            pickRate = 1.18,
            banRate = 0.17,
            damageType = DamageType.MAGIC,
            summary = "Janna, armada con el poder de los vendavales de Runaterra, es un misterioso espÃ­ritu elemental que aprovecha el viento para proteger a los mÃ¡s desfavorecidos de Zaun. Hay quien cree que surgiÃ³ de los ruegos de los marineros de Runaterra, que rezaban por...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Janna en SUPPORT. Coordina el uso de su MonzÃ³n para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Empuje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/JannaP.png", description = "Los aliados de Janna obtienen velocidad de movimiento al avanzar hacia ella.Janna inflige parte de la velocidad de movimiento adicional como daÃ±o mÃ¡gico adicional al golpear y con CÃ©firo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Temporal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/HowlingGale.png", description = "Cambiando puntualmente la presiÃ³n y la temperatura, Janna logra crear una pequeÃ±a tormenta que aumenta de tamaÃ±o con el tiempo. Se puede activar de nuevo el hechizo para lanzar la tormenta. Al lanzarla, esta tormenta se desplaza hacia la direcciÃ³n en que fue arrojada, lo que inflige daÃ±o y lanza por los aires a los enemigos que se encuentren en su camino.", cooldown = "14s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "CÃ©firo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SowTheWind.png", description = "Janna invoca un elemental de aire que aumenta de forma pasiva su velocidad de movimiento y le permite atravesar unidades. TambiÃ©n puede activar esta habilidad para infligir daÃ±o y reducir la velocidad de movimiento de un enemigo.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Ojo de la tormenta", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/EyeOfTheStorm.png", description = "Janna conjura un vendaval defensivo que protege a una torreta o un campeÃ³n aliado del daÃ±o y aumenta su daÃ±o de ataque.", cooldown = "16/15/14/13/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "MonzÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ReapTheWhirlwind.png", description = "Janna se envuelve con una tormenta mÃ¡gica que repele a sus enemigos. Una vez pasada la tormenta, unos vientos reparadores curan a los aliados cercanos mientras la habilidad se encuentra activa.", cooldown = "130/115/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/janna",
            wrMetaUrl = "https://wr-meta.com/champion/janna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/janna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/janna"
        ),
        Champion(
            id = "jarvan_iv",
            name = "Jarvan IV",
            nameEn = "",
            namePt = "",
            title = "El Ejemplo de Demacia",
            titleEn = "",
            titlePt = "",
            ddragonId = "JarvanIV",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/JarvanIV.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S+",
            winrate = 52.7,
            pickRate = 5.6,
            banRate = 12.6,
            damageType = DamageType.PHYSICAL,
            summary = "El prÃ­ncipe Jarvan, descendiente de la dinastÃ­a Escudo de Luz, es heredero al trono de Demacia. Criado para ser ejemplo de las mayores virtudes de su naciÃ³n, se ve obligado a equilibrar las grandes expectativas que se le imponen con su propio deseo de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Jarvan IV en JUNGLE. Coordina el uso de su Cataclismo para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Cadencia marcial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/JarvanIVP.png", description = "El primer ataque bÃ¡sico de Jarvan contra un enemigo le inflige daÃ±o fÃ­sico adicional segÃºn la vida que tenga. Este efecto no puede aplicarse de nuevo al mismo enemigo durante unos segundos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe del dragÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JarvanIVDragonStrike.png", description = "Jarvan IV proyecta su lanza ante sÃ­, causando daÃ±o fÃ­sico y mermando la armadura a los enemigos que se encuentre en su camino. AdemÃ¡s, de este modo Jarvan se lanzarÃ¡ hacia su Estandarte demaciano, derribando a los enemigos que se encuentren en su camino.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "ProtecciÃ³n Ã¡urea", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JarvanIVGoldenAegis.png", description = "Jarvan IV invoca a los antiguos reyes de Demacia para que le protejan de todo posible daÃ±o y ralenticen a los enemigos que le rodeen.", cooldown = "9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Estandarte demaciano", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JarvanIVDemacianStandard.png", description = "Jarvan IV representa el orgullo de Demacia y goza, de forma pasiva, de una bonificaciÃ³n de velocidad de ataque. Al activar Estandarte demaciano, Jarvan IV puede colocar una bandera de Demacia que inflige daÃ±o mÃ¡gico al impactar y otorga velocidad de ataque a los campeones aliados cercanos.", cooldown = "12/11.5/11/10.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Cataclismo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JarvanIVCataclysm.png", description = "Jarvan IV salta heroicamente al combate cayendo sobre un objetivo, con tal fuerza que levanta la tierra a su alrededor para delimitar una zona de lucha. Los enemigos cercanos reciben daÃ±o en el momento del impacto.", cooldown = "120/105/90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/jarvan-iv",
            wrMetaUrl = "https://wr-meta.com/champion/jarvan-iv/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/jarvan-iv/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/jarvan-iv"
        ),
        Champion(
            id = "jax",
            name = "Jax",
            nameEn = "",
            namePt = "",
            title = "El Maestro de Armas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Jax",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Jax.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 53.37,
            pickRate = 2.73,
            banRate = 0.6,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Incomparable en su habilidad con armas especiales y su mordaz sarcasmo, Jax es el Ãºltimo maestro de armas conocido de Icathia. DespuÃ©s de que su tierra natal fuera devastada por su propia arrogancia al desatar el VacÃ­o, Jax y los suyos juraron proteger...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Jax en TOP. Coordina el uso de su Gran Maestro de Armas para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Ã‰gida de Fuego Solar", "Cota de Espinas", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Coraza del Muerto", "Protector PÃ©treo"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3742.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Asalto implacable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Armsmaster_MasterOfArms.png", description = "Los ataques bÃ¡sicos consecutivos de Jax aumentan su velocidad de ataque constantemente.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe en salto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JaxQ.png", description = "Jax salta hacia una unidad. Si es un enemigo, lo golpea con su arma.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "PotenciaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JaxW.png", description = "Jax carga su arma con energÃ­a para que su siguiente ataque cause daÃ±o adicional.", cooldown = "7/6/5/4/3s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Contraataque", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JaxE.png", description = "La habilidad de Jax para el combate le permite esquivar todos los ataques que reciba durante un breve perÃ­odo de tiempo y luego contraatacar rÃ¡pidamente, aturdiendo a todos los enemigos de los alrededores.", cooldown = "17/15/13/11/9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Gran Maestro de Armas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JaxR.png", description = "Cada tercer golpe consecutivo inflige daÃ±o mÃ¡gico adicional. AdemÃ¡s, Jax puede activar esta habilidad para infligir daÃ±o a su alrededor y fortalecer su resoluciÃ³n, lo que aumenta su armadura y resistencia mÃ¡gica durante un breve periodo.", cooldown = "100/90/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/jax",
            wrMetaUrl = "https://wr-meta.com/champion/jax/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/jax/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/jax"
        ),
        Champion(
            id = "jayce",
            name = "Jayce",
            nameEn = "",
            namePt = "",
            title = "El Defensor del MaÃ±ana",
            titleEn = "",
            titlePt = "",
            ddragonId = "Jayce",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Jayce.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 50.91,
            pickRate = 1.38,
            banRate = 0.36,
            damageType = DamageType.PHYSICAL,
            summary = "Jayce es un brillante inventor que ha jurado defender con su vida a Piltover y a su irreductible afÃ¡n de progreso. EmpuÃ±ando su martillo hextech transformable, Jayce hace uso de su fuerza, coraje y considerable inteligencia para proteger su ciudad natal...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Jayce en TOP. Coordina el uso de su CaÃ±Ã³n de mercurio / Martillo de mercurio para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Condensador hextech", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Jayce_Passive.png", description = "Cuando Jayce cambia de arma, obtiene velocidad de movimiento durante un breve periodo de tiempo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Â¡Hacia los cielos! / ExplosiÃ³n elÃ©ctrica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JayceToTheSkies.png", description = "PosiciÃ³n de Martillo: Salta a una zona, infligiendo daÃ±o fÃ­sico y ralentizando a los enemigos.PosiciÃ³n de CaÃ±Ã³n: Lanza un orbe de electricidad que detona al golpear a un enemigo o llegar a su alcance mÃ¡ximo, infligiendo daÃ±o fÃ­sico a todos los enemigos alcanzados.", cooldown = "16/14/12/10/8/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Campo de rayos / Hipercarga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JayceStaticField.png", description = "PosiciÃ³n de Martillo: Pasiva: Restaura manÃ¡ por cada golpe. Activa: Crea un campo de rayos que daÃ±a a los enemigos cercanos durante varios segundos.PosiciÃ³n de CaÃ±Ã³n: Obtiene un impulso de energÃ­a y aumenta al mÃ¡ximo la velocidad de ataque durante varios ataques.", cooldown = "10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Golpe tormentoso / Portal de aceleraciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JayceThunderingBlow.png", description = "PosiciÃ³n de martillo: Inflige daÃ±o mÃ¡gico y desplaza levemente a un enemigo.PosiciÃ³n de caÃ±Ã³n: Lanza un Portal de aceleraciÃ³n que aumenta la velocidad de movimiento de todos los campeones aliados que lo atraviesen. Si se lanza ExplosiÃ³n elÃ©ctrica a travÃ©s del portal, la velocidad, daÃ±o y alcance del proyectil aumentarÃ¡n.", cooldown = "20/18/16/14/12/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "CaÃ±Ã³n de mercurio / Martillo de mercurio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JayceStanceHtG.png", description = "PosiciÃ³n de Martillo: Se transforma en el CaÃ±Ã³n de mercurio, con nuevas habilidades y mayor alcance. Su primer ataque reduce la armadura y la resistencia mÃ¡gica del objetivo.PosiciÃ³n de CaÃ±Ã³n: Se transforma en el Martillo de mercurio, con nuevas habilidades y un aumento de sus defensas. Su primer ataque inflige daÃ±o mÃ¡gico adicional.", cooldown = "6s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/jayce",
            wrMetaUrl = "https://wr-meta.com/champion/jayce/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/jayce/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/jayce"
        ),
        Champion(
            id = "jhin",
            name = "Jhin",
            nameEn = "",
            namePt = "",
            title = "El Virtuoso",
            titleEn = "",
            titlePt = "",
            ddragonId = "Jhin",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Jhin.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 51.46,
            pickRate = 10.56,
            banRate = 0.63,
            damageType = DamageType.PHYSICAL,
            summary = "Jhin es un meticuloso criminal psicÃ³pata que ve el asesinato como arte. Otrora prisionero jonio, fue liberado gracias a los sombrÃ­os tejemanejes del consejo de Jonia. Ahora, el asesino en serie trabaja como sicario de la secta. Con su pistola como...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Jhin en ADC. Coordina el uso de su Abajo el telÃ³n para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "CompÃ¡s Letal (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Presteza â€¢ Golpe de Gracia â€¢ Cazador de Titanes",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Susurro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Jhin_P.png", description = "Susurro, el caÃ±Ã³n de mano de Jhin, es un instrumento preciso diseÃ±ado para infligir un gran daÃ±o. Dispara con una cadencia fija y solo puede portar cuatro balas. Jhin baÃ±a la Ãºltima bala con magia oscura para asestar impactos crÃ­ticos e infligir daÃ±o de ejecuciÃ³n adicional. Siempre que Susurro asesta un impacto crÃ­tico, Jhin obtiene un aumento de velocidad de movimiento.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Granada danzante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JhinQ.png", description = "Jhin lanza un cartucho mÃ¡gico a un enemigo. PodrÃ¡ impactar a un mÃ¡ximo de cuatro objetivos y cada vez que mata suma daÃ±o.", cooldown = "7/6.5/6/5.5/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Florecer mortal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JhinW.png", description = "Jhin blande su bastÃ³n y dispara un proyectil con un alcance increÃ­ble. Atraviesa a los sÃºbditos y los monstruos, pero se detiene en el primer campeÃ³n impactado. El objetivo queda inmovilizado si ha sido vÃ­ctima de los ataques de los aliados de Jhin, de las trampas de loto o del daÃ±o de Jhin.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "PÃºblico entregado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JhinE.png", description = "Jhin coloca una trampa de loto invisible que florece al pasar sobre ella. Ralentiza a los enemigos cercanos antes de infligir daÃ±o con una explosiÃ³n de pÃ©talos serrados. Belleza en la muerte: Cuando Jhin mate a un campeÃ³n enemigo, florecerÃ¡ una trampa de loto junto a su cadÃ¡ver.", cooldown = "2s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Abajo el telÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JhinR.png", description = "Jhin concentra su energÃ­a y transforma Susurro en un megacaÃ±Ã³n de hombro capaz de realizar 4 superdisparos de gran alcance, que atraviesan a los sÃºbditos y a los monstruos pero se detienen en el primer campeÃ³n impactado. Susurro incapacita a los enemigos impactados, los ralentiza e inflige daÃ±o de ejecuciÃ³n. El cuarto disparo se prepara con sumo cuidado, tiene una potencia Ã©pica y garantiza un impacto crÃ­tico.", cooldown = "120/105/90s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/jhin",
            wrMetaUrl = "https://wr-meta.com/champion/jhin/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/jhin/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/jhin"
        ),
        Champion(
            id = "jinx",
            name = "Jinx",
            nameEn = "",
            namePt = "",
            title = "La Bala Perdida",
            titleEn = "",
            titlePt = "",
            ddragonId = "Jinx",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Jinx.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 49.11,
            pickRate = 13.74,
            banRate = 1.36,
            damageType = DamageType.PHYSICAL,
            summary = "Jinx, una criminal perturbada e impulsiva de Zaun, vive para sembrar el caos sin importarle las consecuencias. Provoca las explosiones mÃ¡s ruidosas y cegadoras con su arsenal de armas letales para dejar un rastro de terror y destrucciÃ³n a su paso. Jinx...",
            advantageAgainst = listOf("Sion", "Lux", "Maestro Yi"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Jinx en ADC. Coordina el uso de su Â¡Supermegacohete mortal! para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Pies Veloces (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Linaje â€¢ Golpe de Gracia â€¢ Coraza Ã“sea",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Â¡A tope!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Jinx_Passive.png", description = "Jinx recibe un gran aumento de velocidad de movimiento y velocidad de ataque cuando ayuda a matar a un campeÃ³n enemigo, a un monstruo Ã©pico de la jungla o a destruir una estructura.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Â¡Cambiazo!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JinxQ.png", description = "Jinx modifica sus ataques bÃ¡sicos al alternar entre Pium Pium, su ametralladora, y Espinas, su lanzacohetes. Los ataques con Pium Pium otorgan velocidad de ataque, mientras que con Espinas inflige daÃ±o en Ã¡rea e incrementa su alcance, pero drena manÃ¡ y ataca mÃ¡s despacio.", cooldown = "0.9s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Â¡Zap!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JinxW.png", description = "Jinx usa a Chispita, su pistola de rayos, para disparar un rayo que ralentiza e inflige daÃ±o al primer enemigo que golpea, revelÃ¡ndolo.", cooldown = "8/7/6/5/4s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Â¡Mascafuegos!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JinxE.png", description = "Jinx lanza una hilera de granadas paralizantes que explotan tras 5 s y envuelven en llamas a los enemigos circundantes. Las Mascafuegos morderÃ¡n e inmovilizarÃ¡n a los campeones enemigos que pasen por encima de ellas.", cooldown = "24/20.5/17/13.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Â¡Supermegacohete mortal!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JinxR.png", description = "Jinx dispara un supercohete por todo el mapa que va aumentando su daÃ±o a medida que avanza. El cohete explotarÃ¡ al impactar contra un campeÃ³n enemigo y tanto este como los enemigos circundantes recibirÃ¡n una cantidad de daÃ±o proporcional a la vida que les falte en ese momento.", cooldown = "85/65/45s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/jinx",
            wrMetaUrl = "https://wr-meta.com/champion/jinx/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/jinx/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/jinx"
        ),
        Champion(
            id = "k_sante",
            name = "K'Sante",
            nameEn = "",
            namePt = "",
            title = "el Orgullo de Nazumah",
            titleEn = "",
            titlePt = "",
            ddragonId = "KSante",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/KSante.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 52.6,
            pickRate = 13.8,
            banRate = 9.2,
            damageType = DamageType.PHYSICAL,
            summary = "K'Sante, un guerrero desafiante y valiente, lucha contra gigantescas bestias y despiadados Ascendidos para proteger su hogar, Nazumah, un codiciado oasis ubicado entre las dunas shurimanas. Tras cortar lazos con su pareja, K'Sante se percata de que...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de K'Sante en TOP. Coordina el uso de su Ã“rdago para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Instinto intrÃ©pido", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Icons_KSante_P.png", description = "Las habilidades de K'Sante marcan a los objetivos para que reciban mÃ¡s daÃ±o de su siguiente ataque.En forma de Ã“rdago, K'Sante inflige mÃ¡s daÃ±o con todos los ataques y habilidades.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpes de ntofo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KSanteQ.png", description = "K'Sante ataca, infligiendo daÃ±o y ralentizando a los enemigos en una lÃ­nea corta.Al impactar, obtiene una acumulaciÃ³n de Golpes de ntofo. Con 2 acumulaciones, K'Sante emite una onda de choque que atrae a los enemigos.En forma de Ã“rdago, se reduce el enfriamiento.", cooldown = "3.5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Pionero", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KSanteW.png", description = "K'Sante carga y recibe daÃ±o reducido. Luego, se desliza, empujando y aturdiendo a los enemigos.En forma Ã“rdago, inflige daÃ±o adicional, pero no empuja ni aturde.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Juego de pies", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KSanteE.png", description = "K'Sante se desliza y obtiene un escudo. Si apunta como objetivo a un aliado, K'Sante obtiene alcance adicional, se desliza hasta Ã©l y otorga un escudo a ambos.En forma de Ã“rdago, se reduce el enfriamiento y aumenta la velocidad.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Ã“rdago", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KSanteR.png", description = "K'Sante empuja a un enemigo y lo lanza contra cualquier muro que haya en su camino. Luego, K'Sante entra en forma de Ã“rdago y se desliza hasta Ã©l, lo que le otorga daÃ±o y curaciÃ³n. AdemÃ¡s, transforma las habilidades, pero reduce su defensa.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/k-sante",
            wrMetaUrl = "https://wr-meta.com/champion/k-sante/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/k-sante/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/k-sante"
        ),
        Champion(
            id = "kai_sa",
            name = "Kai'Sa",
            nameEn = "",
            namePt = "",
            title = "La Hija del VacÃ­o",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kaisa",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kaisa.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.3,
            pickRate = 13.8,
            banRate = 6.7,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Reclamada por el VacÃ­o cuando era solo una niÃ±a, Kai'Sa logrÃ³ sobrevivir por pura tenacidad y fuerza de voluntad. Sus experiencias la han convertido en una cazadora letal y, para algunos, el presagio de un futuro que preferirÃ­an no vivir para ver. Ahora...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kai'Sa en ADC. Coordina el uso de su Instinto asesino para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Pies Veloces (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Linaje â€¢ Golpe de Gracia â€¢ Coraza Ã“sea",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Segunda piel", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kaisa_Passive.png", description = "Los ataques bÃ¡sicos de Kai'Sa acumulan Plasma, lo que inflige daÃ±o mÃ¡gico adicional en aumento. Los efectos de inmovilizaciÃ³n de los aliados ayudan a acumular Plasma. AdemÃ¡s, los objetos que Kai'Sa compre mejoran sus hechizos bÃ¡sicos y les otorgan propiedades mÃ¡s potentes.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Lluvia de Icathia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaisaQ.png", description = "Kai'Sa libera una rÃ¡faga de proyectiles que buscan campeones enemigos cercanos.Arma viviente: Lluvia de Icathia mejora y dispara mÃ¡s misiles.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Buscador del VacÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaisaW.png", description = "Kai'Sa dispara un misil de largo alcance y marca a los enemigos con la pasiva.Arma viviente: Buscador del VacÃ­o mejora y aplica mÃ¡s acumulaciones de la pasiva, ademÃ¡s de reducir el enfriamiento al golpear a un campeÃ³n.", cooldown = "22/20/18/16/14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Supercarga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaisaE.png", description = "Kai'Sa aumenta brevemente su velocidad de movimiento y despuÃ©s su velocidad de ataque.Arma viviente: Supercarga mejora y otorga brevemente invisibilidad.", cooldown = "16/14.5/13/11.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Instinto asesino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaisaR.png", description = "Kai'Sa se desliza cerca de un campeÃ³n enemigo.", cooldown = "130/100/70s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kai-sa",
            wrMetaUrl = "https://wr-meta.com/champion/kai-sa/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kai-sa/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kai-sa"
        ),
        Champion(
            id = "kalista",
            name = "Kalista",
            nameEn = "",
            namePt = "",
            title = "El EspÃ­ritu de la Venganza",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kalista",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kalista.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 51.4,
            pickRate = 6.89,
            banRate = 4.78,
            damageType = DamageType.PHYSICAL,
            summary = "Kalista, un espectro de cÃ³lera y castigo, es el inmortal espÃ­ritu de la venganza, una pesadilla acorazada que llega desde las Islas de la Sombra para dar caza a los embusteros y los traidores. Puede que todos los que han sido traicionados pidan venganza...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kalista en ADC. Coordina el uso de su Llamada del destino para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Aplomo marcial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kalista_Passive.png", description = "Si Kalista da una orden de movimiento mientras prepara un ataque bÃ¡sico o Atravesar, al asestar el golpe recorrerÃ¡ una corta distancia en la direcciÃ³n indicada.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Atravesar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KalistaMysticShot.png", description = "Arroja una veloz lanza que atraviesa a los enemigos que asesina.", cooldown = "8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Centinela", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KalistaW.png", description = "Obtiene daÃ±o adicional cuando Kalista y su Juramentado golpean al mismo objetivo. ActÃ­vala para enviar un alma a patrullar una zona. El alma revelarÃ¡ la zona que tiene delante.", cooldown = "30s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Desgarrar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KalistaExpungeWrapper.png", description = "Cuando ataca, atraviesa a sus enemigos con lanzas. ActÃ­vala para arrancarlas, lo que ralentiza a las vÃ­ctimas y les causa daÃ±o en funciÃ³n del nivel.", cooldown = "0s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Llamada del destino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KalistaRx.png", description = "Kalista teleporta al Juramentado hasta sÃ­. Este adquiere la capacidad de lanzarse hacia una posiciÃ³n y derribar a los campeones enemigos con los que se encuentra.", cooldown = "160/140/120s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kalista",
            wrMetaUrl = "https://wr-meta.com/champion/kalista/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kalista/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kalista"
        ),
        Champion(
            id = "karma",
            name = "Karma",
            nameEn = "",
            namePt = "",
            title = "La Iluminada",
            titleEn = "",
            titlePt = "",
            ddragonId = "Karma",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Karma.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "C",
            winrate = 51.07,
            pickRate = 2.77,
            banRate = 0.36,
            damageType = DamageType.MAGIC,
            summary = "No hay mortal que ejemplifique las tradiciones espirituales de Jonia mejor que Karma. Es la personificaciÃ³n de un alma antigua reencarnada un sinfÃ­n de veces, que acumula en cada vida sucesiva los recuerdos de las vidas pasadas y que ha sido bendecida...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Karma en SUPPORT. Coordina el uso de su Mantra para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Fuego reunificador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Karma_Passive.png", description = "Las habilidades de daÃ±o de Karma reducen el enfriamiento de Mantra.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Llama interior", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaQ.png", description = "Karma lanza una bola de energÃ­a espiritual que explota e inflige daÃ±o al golpear a una unidad enemiga.BonificaciÃ³n de Mantra: AdemÃ¡s de la explosiÃ³n, Mantra aumenta el poder destructivo de Llama interior, lo que crea una onda abrasiva que inflige daÃ±o tras unos instantes.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "ResoluciÃ³n concentrada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaSpiritBind.png", description = "Karma crea un vÃ­nculo entre el objetivo enemigo y ella que inflige daÃ±o y lo revela. Si no se rompe el vÃ­nculo, el objetivo queda inmovilizado y vuelve a recibir daÃ±o.BonificaciÃ³n de Mantra: Karma fortalece el vÃ­nculo, lo que, ademÃ¡s de curarla, amplÃ­a la duraciÃ³n de la inmovilizaciÃ³n.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "InspiraciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaSolKimShield.png", description = "Karma invoca un escudo protector que absorbe el daÃ±o recibido y aumenta la velocidad de movimiento del aliado protegido.BonificaciÃ³n de Mantra: El objetivo irradia energÃ­a, lo que refuerza el escudo inicial y aplica InspiraciÃ³n a los campeones aliados cercanos.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Mantra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KarmaMantra.png", description = "Karma potencia su siguiente habilidad para conseguir un efecto adicional. Mantra estÃ¡ disponible al nivel 1 y no necesita puntos de habilidad.", cooldown = "40/38/36/34s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/karma",
            wrMetaUrl = "https://wr-meta.com/champion/karma/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/karma/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/karma"
        ),
        Champion(
            id = "kassadin",
            name = "Kassadin",
            nameEn = "",
            namePt = "",
            title = "El Caminante del VacÃ­o",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kassadin",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kassadin.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.08,
            pickRate = 1.05,
            banRate = 0.12,
            damageType = DamageType.MAGIC,
            summary = "Dejando tras sÃ­ una huella ardiente por los lugares mÃ¡s oscuros del mundo, Kassadin sabe que sus dÃ­as estÃ¡n contados. GuÃ­a y aventurero de Shurima que ha viajado por medio mundo, habÃ­a elegido formar a una familia entre las pacÃ­ficas tribus del sur...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kassadin en MID. Coordina el uso de su Camino del VacÃ­o para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Piedra del VacÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kassadin_Passive.png", description = "Kassadin recibe menos daÃ±o mÃ¡gico e ignora la colisiÃ³n con unidades.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Esfera negativa", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NullLance.png", description = "Kassadin dispara una esfera de energÃ­a del VacÃ­o contra un objetivo que inflige daÃ±o e interrumpe canalizaciones. El excedente de energÃ­a envuelve a Kassadin y le otorga un escudo temporal que absorbe daÃ±o mÃ¡gico.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Cuchilla infernal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NetherBlade.png", description = "Pasiva: Los ataques bÃ¡sicos de Kassadin infligen daÃ±o mÃ¡gico adicional. Activa: Los ataques bÃ¡sicos de Kassadin infligen bastante daÃ±o mÃ¡gico adicional y restauran manÃ¡.", cooldown = "7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Pulso de fuerza", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ForcePulse.png", description = "Kassadin extrae energÃ­a de los hechizos lanzados cerca de Ã©l. Al cargarse, Kassadin puede utilizar Pulso de fuerza para infligir daÃ±o y ralentizar a los enemigos que se encuentren en un cono frente a Ã©l.", cooldown = "21/20/19/18/17s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Camino del VacÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RiftWalk.png", description = "Kassadin se teleporta a un lugar cercano e inflige daÃ±o a las unidades enemigas cercanas. Si se utiliza repetidamente Camino del VacÃ­o en poco tiempo, costarÃ¡ mÃ¡s manÃ¡, pero tambiÃ©n infligirÃ¡ daÃ±o adicional.", cooldown = "5/3.5/2s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kassadin",
            wrMetaUrl = "https://wr-meta.com/champion/kassadin/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kassadin/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kassadin"
        ),
        Champion(
            id = "katarina",
            name = "Katarina",
            nameEn = "",
            namePt = "",
            title = "La Cuchilla Siniestra",
            titleEn = "",
            titlePt = "",
            ddragonId = "Katarina",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Katarina.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.12,
            pickRate = 1.64,
            banRate = 0.77,
            damageType = DamageType.MAGIC,
            summary = "Con un juicio decisivo y letal en el combate, Katarina es una de las mejores asesinas noxianas. Al ser la primogÃ©nita del legendario general Du Couteau, ha dado a conocer sus habilidades con asesinatos rÃ¡pidos contra enemigos inconscientes. Una ambiciÃ³n...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Katarina en MID. Coordina el uso de su Loto mortal para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Ansia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Katarina_Passive.png", description = "Cuando muere un campeÃ³n que haya recibido daÃ±o de Katarina en los Ãºltimos segundos, el enfriamiento de sus habilidades se reduce drÃ¡sticamente.Si Katarina recoge una daga, la usa para acuchillar a todos los enemigos cercanos, lo que inflige daÃ±o mÃ¡gico.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Hoja rebotante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaQ.png", description = "Katarina lanza al objetivo una daga que rebota hacia los enemigos cercanos antes de caer al suelo.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "PreparaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaW.png", description = "Katarina obtiene un aumento de velocidad de movimiento y lanza una daga al aire encima de ella.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Velocidad del rayo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaEWrapper.png", description = "Katarina aparece junto al objetivo. Lo golpea si es un enemigo o, de lo contrario, golpea al enemigo mÃ¡s cercano.", cooldown = "14/12.5/11/9.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Loto mortal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KatarinaR.png", description = "Katarina despide una rÃ¡faga de hojas e inflige gran cantidad de daÃ±o mÃ¡gico a los 3 campeones enemigos mÃ¡s cercanos.", cooldown = "90/60/45s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/katarina",
            wrMetaUrl = "https://wr-meta.com/champion/katarina/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/katarina/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/katarina"
        ),
        Champion(
            id = "kayle",
            name = "Kayle",
            nameEn = "",
            namePt = "",
            title = "la Justa",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kayle",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kayle.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 53.16,
            pickRate = 3.17,
            banRate = 0.4,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Kayle, nacida de un Aspecto de Targon en el punto Ã¡lgido de las Guerras RÃºnicas, honrÃ³ el legado de su madre al continuar la lucha por la justicia con sus alas de llamas divinas. Durante muchos aÃ±os, ella y su hermana gemela Morgana fueron las...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kayle en TOP. Coordina el uso de su Veredicto divino para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Ascenso divino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kayle_P.png", description = "Al subir de nivel y gastar puntos de habilidad, Kayle recibe apoyo divino en sus ataques. Sus alas prenden en llamas mientras, de forma progresiva, obtiene velocidad de ataque, velocidad de movimiento, alcance de ataque y ondas de fuego con sus ataques.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "RÃ¡faga radiante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleQ.png", description = "Kayle invoca un portal que lanza una espada celestial que atraviesa a los enemigos, lo que reduce las resistencias de todos los enemigos golpeados, quedan ralentizados y reciben daÃ±o.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Gracia celestial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleW.png", description = "Bendecida por lo divino, Kayle se cura a sÃ­ misma y al aliado mÃ¡s cercano, y ambos obtienen velocidad de movimiento.", cooldown = "15s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Filo purificador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleE.png", description = "Pasiva: Virtud, la espada celestial de Kayle, inflige daÃ±o mÃ¡gico adicional a los enemigos que ataca.Activa: el prÃ³ximo ataque de Kayle aplasta a su objetivo con fuego celestial e inflige daÃ±o mÃ¡gico adicional en proporciÃ³n a la vida que le falte.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Veredicto divino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KayleR.png", description = "Kayle vuelve invulnerable a un aliado e invoca al antiguo Aspecto de la Justicia para que purifique con una lluvia de espadas sagradas la zona que rodea al objetivo.", cooldown = "160/120/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kayle",
            wrMetaUrl = "https://wr-meta.com/champion/kayle/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kayle/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kayle"
        ),
        Champion(
            id = "kayn",
            name = "Kayn",
            nameEn = "",
            namePt = "",
            title = "la GuadaÃ±a de las Sombras",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kayn",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kayn.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 50.53,
            pickRate = 5.49,
            banRate = 0.46,
            damageType = DamageType.PHYSICAL,
            summary = "Shieda Kayn, un practicante sin par de la mortÃ­fera magia sombrÃ­a, lucha por alcanzar su verdadero destino: llegar a guiar algÃºn dÃ­a a la Orden de la Sombra hacia una nueva era de supremacÃ­a jonia. EmpuÃ±a la oscura arma viviente Rhaast, impasible ante...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kayn en JUNGLE. Coordina el uso de su InvasiÃ³n sombrÃ­a para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "La guadaÃ±a de los oscuros", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kayn_Passive_Primary.png", description = "Kayn es portador de un arma muy antigua y lucha contra Rhaast, el oscuro que alberga, en un tira y afloja por hacerse con el control. O Rhaast el oscuro triunfarÃ¡ o Kayn conseguirÃ¡ dominarlo y se convertirÃ¡ en el Asesino de las sombras.Oscuro: Se cura un porcentaje del daÃ±o de hechizos infligido a campeones.Asesino de las sombras: Inflige daÃ±o adicional durante los primeros segundos en combate contra campeones enemigos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Corte de guadaÃ±a", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaynQ.png", description = "Kayn se desliza y luego ataca. Ambas acciones infligen daÃ±o.", cooldown = "7/6.5/6/5.5/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Golpe de hoja", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaynW.png", description = "Kayn daÃ±a y ralentiza a los objetivos en una lÃ­nea.", cooldown = "13/12/11/10/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Pasos de sombra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaynE.png", description = "Kayn puede atravesar obstÃ¡culos de terreno.", cooldown = "21/19/17/15/13s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "InvasiÃ³n sombrÃ­a", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KaynR.png", description = "Kayn se oculta en el cuerpo de un enemigo e inflige un daÃ±o enorme al abandonarlo.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kayn",
            wrMetaUrl = "https://wr-meta.com/champion/kayn/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kayn/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kayn"
        ),
        Champion(
            id = "kennen",
            name = "Kennen",
            nameEn = "",
            namePt = "",
            title = "El CorazÃ³n de la Tempestad",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kennen",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kennen.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "D",
            winrate = 52.0,
            pickRate = 1.23,
            banRate = 0.57,
            damageType = DamageType.MAGIC,
            summary = "MÃ¡s allÃ¡ de ser el rÃ¡pido encargado de mantener el equilibrio en Jonia, Kennen es tambiÃ©n el Ãºnico yordle de los kinkou. A pesar de su pequeÃ±o y peludo tamaÃ±o, siempre estÃ¡ impaciente por enfrentarse a cualquier amenaza dando rienda suelta a una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kennen en TOP. Coordina el uso de su Tempestad cercenante para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Marca de la tormenta", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kennen_Passive.png", description = "Kennen aturde a los enemigos a los que golpea 3 veces con sus habilidades.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Shuriken atronador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KennenShurikenHurlMissile1.png", description = "Kennen arroja hacia un lugar un veloz shuriken, causando daÃ±o y aÃ±adiendo una Marca de la tormenta a todos los rivales que golpea.", cooldown = "7/6.25/5.5/4.75/4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "TensiÃ³n elÃ©ctrica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KennenBringTheLight.png", description = "Kennen inflige daÃ±o adicional de forma pasiva y aÃ±ade a su objetivo una Marca de la tormenta cada varios ataques. Puede activar esta habilidad para daÃ±ar y aÃ±adir otra Marca de la tormenta a los objetivos que ya estÃ¡n marcados.", cooldown = "13/11.25/9.5/7.75/6s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Ataque del rayo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KennenLightningRush.png", description = "Kennen adopta la forma de un rayo, lo que le permite atravesar a las unidades y aplicar una Marca de la tormenta. Kennen obtiene velocidad de movimiento cuando adopta esta forma y velocidad de ataque cuando la abandona.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Tempestad cercenante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KennenShurikenStorm.png", description = "Kennen invoca una tormenta que golpea a los campeones enemigos cercanos y les causa daÃ±o mÃ¡gico.", cooldown = "120/100/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kennen",
            wrMetaUrl = "https://wr-meta.com/champion/kennen/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kennen/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kennen"
        ),
        Champion(
            id = "kha_zix",
            name = "Kha'Zix",
            nameEn = "",
            namePt = "",
            title = "El Saqueador del VacÃ­o",
            titleEn = "",
            titlePt = "",
            ddragonId = "Khazix",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Khazix.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.0,
            pickRate = 15.7,
            banRate = 13.4,
            damageType = DamageType.PHYSICAL,
            summary = "El VacÃ­o crece y el VacÃ­o se adapta; verdades que son mÃ¡s evidentes en Kha'Zix que en ningÃºn otro de sus innumerables engendros. La evoluciÃ³n impulsa el nÃºcleo de este horror mutante, nacido para sobrevivir y matar a los fuertes. Cuando se esfuerza por...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kha'Zix en JUNGLE. Coordina el uso de su Asalto del VacÃ­o para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Amenaza invisible", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Khazix_P.png", description = "Los enemigos cercanos que estÃ©n aislados de sus aliados quedan marcados. Las habilidades de Kha'Zix interactÃºan con los objetivos aislados.Cuando Kha'Zix se vuelve invisible para el equipo enemigo, obtiene Amenaza invisible, que provoca que su siguiente ataque bÃ¡sico contra un campeÃ³n enemigo inflija daÃ±o mÃ¡gico adicional y ralentice al enemigo durante unos segundos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Saborea su miedo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KhazixQ.png", description = "Inflige daÃ±o fÃ­sico al objetivo. El daÃ±o es mayor en objetivos aislados. Si decide evolucionar Garras desgarradoras, recupera parte del enfriamiento contra objetivos aislados. Kha'Zix tambiÃ©n obtiene mÃ¡s alcance, tanto en Saborea su miedo como en sus ataques bÃ¡sicos.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Pincho del VacÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KhazixW.png", description = "Kha'Zix lanza pinchos explosivos que infligen daÃ±o fÃ­sico a los enemigos golpeados. Kha'Zix se cura si tambiÃ©n estÃ¡ dentro del radio de la explosiÃ³n. Si decide evolucionar Pinchos, entonces Pincho del VacÃ­o lanza tres pinchos en un cono, ralentiza a los enemigos golpeados y revela a los campeones enemigos golpeados durante 2 s. Los objetivos aislados sufren ralentizaciÃ³n adicional.", cooldown = "9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Salto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KhazixE.png", description = "Kha'Zix salta hacia una zona e inflige daÃ±o fÃ­sico al aterrizar. Si decide evolucionar Alas, el alcance de Salto aumenta en 200. AdemÃ¡s, al matar o ayudar a matar a un campeÃ³n, el enfriamiento de Salto se restablece.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Asalto del VacÃ­o", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KhazixR.png", description = "Cada rango permite que Kha'Zix evolucione una de sus habilidades, lo que les proporciona un efecto adicional Ãºnico. Al activarse, Kha'Zix se vuelve invisible, lo que activa Amenaza invisible y aumenta su velocidad de movimiento. Si decide evolucionar Ocultamiento adaptable, Asalto del VacÃ­o aumenta la duraciÃ³n de su invisibilidad y obtiene un uso adicional.", cooldown = "100/85/70s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kha-zix",
            wrMetaUrl = "https://wr-meta.com/champion/kha-zix/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kha-zix/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kha-zix"
        ),
        Champion(
            id = "kindred",
            name = "Kindred",
            nameEn = "",
            namePt = "",
            title = "Los Cazadores Eternos",
            titleEn = "",
            titlePt = "",
            ddragonId = "Kindred",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Kindred.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.ADC),
            tier = "C",
            winrate = 52.6,
            pickRate = 2.33,
            banRate = 0.17,
            damageType = DamageType.PHYSICAL,
            summary = "Divididos, pero nunca separados, Kindred representan las dos esencias de la muerte. El arco de Cordera ofrece una rÃ¡pida liberaciÃ³n de los pesares del reino mortal a aquellos que aceptan su destino. Lobo da caza a quienes intentan escapar de Ã©l y les...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kindred en JUNGLE. Coordina el uso de su Respiro de cordera para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Marca de Kindred", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Kindred_Passive.png", description = "Kindred puede marcar objetivos a los que cazar. Completar con Ã©xito una caza potencia de forma permanente las habilidades bÃ¡sicas de Kindred. Cada 4 cazas completadas, el alcance de los ataques bÃ¡sicos de Kindred tambiÃ©n aumenta.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Danza de flechas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KindredQ.png", description = "Kindred se mueve y dispara hasta tres flechas a objetivos cercanos.", cooldown = "9s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "FrenesÃ­ de lobo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KindredW.png", description = "Lobo enfurece y ataca a los enemigos de su alrededor. Cordera consigue acumulaciones de forma pasiva al moverse y atacar. Cuando estÃ© al mÃ¡ximo, el siguiente ataque de Cordera restaura vida.", cooldown = "18/17/16/15/14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Temor creciente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KindredEWrapper.png", description = "Cordera apunta cuidadosamente y su disparo ralentiza al objetivo. Si Cordera le ataca dos veces mÃ¡s, su tercer ataque harÃ¡ que Lobo salte sobre el objetivo y le provoque un daÃ±o descomunal.", cooldown = "14/12.5/11/9.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Respiro de cordera", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KindredR.png", description = "Cordera proporciona a todos los seres vivos que estÃ©n dentro de una zona un respiro ante la muerte. Nada podrÃ¡ morir hasta que se pase el efecto. Al final, se curan las unidades.", cooldown = "180/150/120s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kindred",
            wrMetaUrl = "https://wr-meta.com/champion/kindred/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kindred/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kindred"
        ),
        Champion(
            id = "kog_maw",
            name = "Kog'Maw",
            nameEn = "",
            namePt = "",
            title = "La Boca del Abismo",
            titleEn = "",
            titlePt = "",
            ddragonId = "KogMaw",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/KogMaw.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "A+",
            winrate = 51.1,
            pickRate = 6.2,
            banRate = 3.9,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Escupido de una incursiÃ³n deteriorada del VacÃ­o en los yermos de Icathia, Kog'Maw es una criatura curiosa y pÃºtrida con una boca enorme y cÃ¡ustica. Esta particular criatura necesita morder y babear todo lo que estÃ© a su alcance para comprenderlo. Aunque...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Kog'Maw en ADC. Coordina el uso de su ArtillerÃ­a viviente para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Pies Veloces (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Linaje â€¢ Golpe de Gracia â€¢ Coraza Ã“sea",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Sorpresa de Icathia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/KogMaw_IcathianSurprise.png", description = "4 s despuÃ©s de morir, Kog'Maw explota e inflige daÃ±o verdadero a los enemigos cercanos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Baba cÃ¡ustica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KogMawQ.png", description = "Kog'Maw lanza un proyectil corrosivo que inflige daÃ±o mÃ¡gico y corroe la armadura y la resistencia mÃ¡gica del objetivo durante un breve periodo. AdemÃ¡s, Kog'Maw obtiene velocidad de ataque adicional.", cooldown = "7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Andanada bioarcana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KogMawBioArcaneBarrage.png", description = "Kog'Maw aumenta el alcance de sus ataques, que infligen un porcentaje de la vida mÃ¡xima del objetivo como daÃ±o mÃ¡gico.", cooldown = "17s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "VacÃ­o rezumante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KogMawVoidOoze.png", description = "Kog'Maw lanza un fluido purulento que inflige daÃ±o a todos los enemigos que atraviesa. AdemÃ¡s, deja un rastro que ralentiza a los rivales que lo tocan.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "ArtillerÃ­a viviente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/KogMawLivingArtillery.png", description = "Kog'Maw dispara un proyectil de largo alcance que inflige daÃ±o mÃ¡gico (el cual aumenta notablemente contra enemigos con poca vida) y revela a los objetivos que no estÃ©n en sigilo. El lanzamiento de mÃºltiples ArtillerÃ­as vivientes en un breve perÃ­odo de tiempo supondrÃ¡ un coste adicional de manÃ¡.", cooldown = "2/1.5/1s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kog-maw",
            wrMetaUrl = "https://wr-meta.com/champion/kog-maw/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/kog-maw/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/kog-maw"
        ),
        Champion(
            id = "lee_sin",
            name = "Lee Sin",
            nameEn = "",
            namePt = "",
            title = "El Monje Ciego",
            titleEn = "",
            titlePt = "",
            ddragonId = "LeeSin",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/LeeSin.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.6,
            pickRate = 11.3,
            banRate = 9.7,
            damageType = DamageType.PHYSICAL,
            summary = "Lee Sin, maestro de las artes marciales ancestrales de Jonia, es un luchador con principios que canaliza la esencia del espÃ­ritu del dragÃ³n para enfrentarse a cualquier desafÃ­o. Aunque perdiÃ³ la vista hace muchos aÃ±os, el monje guerrero ha dedicado su...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lee Sin en JUNGLE. Coordina el uso de su Ira del dragÃ³n para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "RÃ¡faga", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/LeeSinPassive.png", description = "DespuÃ©s de que Lee Sin use una habilidad, sus 2 ataques bÃ¡sicos siguientes reciben velocidad de ataque y restauran energÃ­a.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Onda sÃ³nica / Golpe resonante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeeSinQOne.png", description = "Onda sÃ³nica: Lee Sin emite una onda sonora discordante para localizar a sus enemigos e inflige daÃ±o fÃ­sico al que detecta primero. Si Onda sÃ³nica golpea, Lee Sin dispone de 3 s para lanzar Golpe resonante.Golpe resonante: Lee Sin se abalanza sobre el enemigo golpeado por Onda sÃ³nica y le causa daÃ±o fÃ­sico segÃºn la vida que le falte al objetivo.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Salvaguarda / Voluntad de hierro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeeSinWOne.png", description = "Salvaguarda: Lee Sin salta sobre un aliado de su elecciÃ³n y genera un escudo que lo protege del daÃ±o. Si el aliado es un campeÃ³n, tambiÃ©n lo escuda. Tras utilizar Salvaguarda, Lee Sin puede usar Voluntad de hierro.Voluntad de hierro: El intenso entrenamiento de Lee Sin le permite crecerse en la batalla. Lee Sin obtiene robo de vida y succiÃ³n de hechizo.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Tempestad / Incapacitar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeeSinEOne.png", description = "Tempestad: Lee Sin golpea el suelo y provoca una onda de choque que inflige daÃ±o mÃ¡gico y revela a los enemigos alcanzados. Si Tempestad golpea a un enemigo, Lee Sin puede usar Incapacitar.Incapacitar: Lee Sin incapacita a los enemigos cercanos daÃ±ados por Tempestad y reduce su velocidad de movimiento. La velocidad de movimiento se recupera gradualmente.", cooldown = "9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Ira del dragÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeeSinR.png", description = "Lee Sin realiza una potente patada giratoria, que lanza hacia atrÃ¡s al objetivo e inflige daÃ±o fÃ­sico a Ã©l y a todos los enemigos con los que impacte. Estos Ãºltimos salen por los aires durante un breve tiempo. Esta tÃ©cnica se la enseÃ±Ã³ Jesse Perring, aunque Lee Sin no patea a los jugadores fuera del mapa.", cooldown = "110/85/60s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lee-sin",
            wrMetaUrl = "https://wr-meta.com/champion/lee-sin/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lee-sin/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lee-sin"
        ),
        Champion(
            id = "leona",
            name = "Leona",
            nameEn = "",
            namePt = "",
            title = "El Amanecer Radiante",
            titleEn = "",
            titlePt = "",
            ddragonId = "Leona",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Leona.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 51.13,
            pickRate = 11.53,
            banRate = 5.89,
            damageType = DamageType.MAGIC,
            summary = "Imbuida del fuego del sol, Leona es una guerrera sagrada de los Solari que defiende el Monte Targon con su Hoja del cÃ©nit y su Escudo del amanecer. Su piel brilla como las estrellas y sus ojos resplandecen con el poder del aspecto celestial de su...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Leona en SUPPORT. Coordina el uso de su Llamarada solar para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Luz solar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/LeonaSunlight.png", description = "Los hechizos de daÃ±o aplican al objetivo Luz solar durante 1,5 s. Cuando los campeones aliados infligen daÃ±o a esos objetivos, consumen Luz solar para infligir daÃ±o mÃ¡gico adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Escudo del amanecer", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaShieldOfDaybreak.png", description = "Leona usa su escudo para realizar su siguiente ataque bÃ¡sico, infligiendo daÃ±o mÃ¡gico adicional y aturdiendo al objetivo.", cooldown = "5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Eclipse", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaSolarBarrier.png", description = "Leona alza su escudo, lo que le otorga armadura, resistencia mÃ¡gica y reducciÃ³n de daÃ±o. Cuando finaliza el efecto por primera vez, si hay enemigos cerca, les inflige daÃ±o mÃ¡gico y prolonga la duraciÃ³n del efecto.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Hoja del cÃ©nit", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaZenithBlade.png", description = "Leona proyecta una imagen solar de su espada, infligiendo daÃ±o mÃ¡gico a todos los enemigos en lÃ­nea recta. Cuando la imagen desaparece, el Ãºltimo campeÃ³n enemigo alcanzado por ella se quedarÃ¡ inmovilizado brevemente, y Leona irÃ¡ rÃ¡pidamente hacia Ã©l.", cooldown = "12/10.5/9/7.5/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Llamarada solar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LeonaSolarFlare.png", description = "Leona invoca un rayo de energÃ­a solar que inflige daÃ±o a los enemigos en un Ã¡rea. Los enemigos en el centro del Ã¡rea se ven aturdidos, mientras que los situados en la franja exterior se ven ralentizados.", cooldown = "90/75/60s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/leona",
            wrMetaUrl = "https://wr-meta.com/champion/leona/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/leona/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/leona"
        ),
        Champion(
            id = "lillia",
            name = "Lillia",
            nameEn = "",
            namePt = "",
            title = "el TÃ­mido Florecer",
            titleEn = "",
            titlePt = "",
            ddragonId = "Lillia",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lillia.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "B",
            winrate = 53.42,
            pickRate = 2.78,
            banRate = 0.59,
            damageType = DamageType.MAGIC,
            summary = "Lillia es una tÃ­mida cervatilla feÃ©rica que merodea a su antojo por los bosques jonios. Se oculta de los mortales, cuya misteriosa naturaleza la cautiva y la intimida a partes iguales, con la esperanza de descubrir por quÃ© los sueÃ±os humanos han dejado...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lillia en JUNGLE. Coordina el uso de su Nana rÃ­tmica para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Rama de los sueÃ±os", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lillia_Icon_Passive.png", description = "Golpear a un campeÃ³n o a un monstruo con una habilidad le infligirÃ¡ daÃ±o por vida mÃ¡xima adicional a lo largo del tiempo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpes florecientes", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LilliaQ.png", description = "De forma pasiva, Lillia obtiene velocidad de movimiento acumulable al golpear a enemigos con sus hechizos. Puede activar la habilidad para infligir daÃ±o mÃ¡gico a los enemigos cercanos e infligir daÃ±o verdadero adicional en el borde.", cooldown = "6/5.5/5/4.5/4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Â¡Cuidado! Â¡Yip!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LilliaW.png", description = "Lillia inflige daÃ±o en una zona cercana. El daÃ±o aumenta en el centro.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Semilla rodante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LilliaE.png", description = "Lillia lanza una semilla que inflige daÃ±o y ralentiza a aquellos sobre los que aterriza. Si no impacta contra nada, seguirÃ¡ rodando hasta impactar contra un muro o un objetivo.", cooldown = "14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Nana rÃ­tmica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LilliaR.png", description = "Lillia provoca que todos los enemigos afectados por Polvo de los sueÃ±os queden aletargados hasta que se quedan dormidos. Esos enemigos reciben daÃ±o adicional al ser despertados a la fuerza.", cooldown = "150/130/110s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lillia",
            wrMetaUrl = "https://wr-meta.com/champion/lillia/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lillia/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lillia"
        ),
        Champion(
            id = "lissandra",
            name = "Lissandra",
            nameEn = "",
            namePt = "",
            title = "La Bruja de Hielo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Lissandra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lissandra.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.86,
            pickRate = 2.57,
            banRate = 1.95,
            damageType = DamageType.MAGIC,
            summary = "La magia de Lissandra convierte el poder del hielo en algo oscuro y terrible. Con la fuerza de su hielo negro, ademÃ¡s de congelar a aquellos que se le oponen, los empala y los destruye sin mostrar piedad. Se la conoce como ''la Bruja de Hielo'' entre...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lissandra en MID. Coordina el uso de su Tumba helada para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Yugo de la Hija del Hielo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lissandra_Passive.png", description = "Cuando un campeÃ³n enemigo muere cerca de Lissandra, se convierte en un esclavo congelado. Los esclavos congelados ralentizan a los enemigos cercanos y, tras unos instantes, se hacen trizas por el intenso frÃ­o, lo que inflige daÃ±o mÃ¡gico a objetivos cercanos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Fragmento de hielo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraQ.png", description = "Dispara una lanza de hielo que se divide cuando impacta en un enemigo, infligiendo daÃ±o mÃ¡gico y reduciendo la velocidad de movimiento. Los fragmentos atraviesan al objetivo, infligiendo el mismo daÃ±o a los demÃ¡s enemigos impactados.", cooldown = "8/7/6/5/4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Anillo de escarcha", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraW.png", description = "Congela a los enemigos cercanos, infligiendo daÃ±o mÃ¡gico e inmovilizÃ¡ndolos.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Camino glacial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraE.png", description = "Lissandra crea una garra de hielo que inflige daÃ±o mÃ¡gico. Volver a usar esta habilidad transporta a Lissandra a la posiciÃ³n de la garra.", cooldown = "24/21/18/15/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Tumba helada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LissandraR.png", description = "Si se lanza sobre un campeÃ³n enemigo, lo congela, dejÃ¡ndolo aturdido. Si se lanza sobre Lissandra, se encierra en Hielo Oscuro, de forma que se cura, pasa a ser invulnerable e imposibilita cualquier acciÃ³n enemiga sobre ella. Del objetivo emana Hielo Oscuro, que inflige daÃ±o mÃ¡gico y reduce la velocidad de movimiento de los enemigos.", cooldown = "120/100/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lissandra",
            wrMetaUrl = "https://wr-meta.com/champion/lissandra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lissandra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lissandra"
        ),
        Champion(
            id = "lucian",
            name = "Lucian",
            nameEn = "",
            namePt = "",
            title = "El Destello Purificador",
            titleEn = "",
            titlePt = "",
            ddragonId = "Lucian",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lucian.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "B",
            winrate = 48.45,
            pickRate = 6.38,
            banRate = 0.2,
            damageType = DamageType.PHYSICAL,
            summary = "Lucian, antiguo Centinela de la Luz, es un sombrÃ­o cazador de espÃ­ritus eternos a los que persigue implacablemente y los aniquila con sus pistolas reliquias. DespuÃ©s de que el macabro espectro Thresh asesinara a su mujer, Lucian se dejÃ³ llevar por el...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lucian en ADC. Coordina el uso de su El sacrificio para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Pies Veloces (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Linaje â€¢ Golpe de Gracia â€¢ Coraza Ã“sea",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Balas de luz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lucian_Passive.png", description = "Cuando Lucian usa una habilidad, su siguiente ataque es un disparo doble. Cuando Lucian recibe una curaciÃ³n o un escudo de un aliado, o se encuentra cerca de un campeÃ³n enemigo inmovilizado, sus siguientes 2 ataques bÃ¡sicos infligen daÃ±o mÃ¡gico adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Luz lacerante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LucianQ.png", description = "Lucian dispara un rayo de luz lacerante a travÃ©s de un objetivo.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Resplandor ardiente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LucianW.png", description = "Lucian dispara un misil que explota en forma de estrella, marca a los enemigos y los revela brevemente. Lucian obtiene velocidad de movimiento por atacar a los enemigos marcados.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "PersecuciÃ³n implacable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LucianE.png", description = "Lucian se desplaza una corta distancia. Los ataques de Balas de luz reducen el enfriamiento de PersecuciÃ³n implacable.", cooldown = "19/17.75/16.5/15.25/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "El sacrificio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LucianR.png", description = "Lucian desata un torrente de disparos de sus armas.", cooldown = "110/100/90s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lucian",
            wrMetaUrl = "https://wr-meta.com/champion/lucian/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lucian/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lucian"
        ),
        Champion(
            id = "lulu",
            name = "Lulu",
            nameEn = "",
            namePt = "",
            title = "El Hada Hechicera",
            titleEn = "",
            titlePt = "",
            ddragonId = "Lulu",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lulu.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 48.94,
            pickRate = 6.06,
            banRate = 2.27,
            damageType = DamageType.MAGIC,
            summary = "Lulu, la yordle maga, es conocida por invocar ilusiones onÃ­ricas y criaturas imaginarias en sus viajes por Runaterra con su hada compaÃ±era, Pix. Lulu le da forma a la realidad a su antojo, transforma el tejido del mundo y de lo que ve como los lÃ­mites...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lulu en SUPPORT. Coordina el uso de su Crecimiento salvaje para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Pix, el hada compaÃ±era", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Lulu_PixFaerieCompanion.png", description = "Pix lanza rayos de energÃ­a mÃ¡gicos cuando el campeÃ³n al que estÃ¡ siguiendo ataca a otra unidad enemiga. Estos rayos son certeros, pero pueden ser interceptados por otras unidades.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Lanza reluciente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluQ.png", description = "Pix y Lulu lanzan un rayo de energÃ­a mÃ¡gica que inflige daÃ±o y ralentiza en gran medida a todos los enemigos a los que alcanza.", cooldown = "7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Banal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluW.png", description = "Si se usa sobre un aliado, le otorga velocidad de ataque y velocidad de movimiento durante un breve periodo de tiempo. Si se usa sobre un enemigo, lo convierte en un animalillo adorable que no puede atacar ni lanzar hechizos.", cooldown = "17/16.5/16/15.5/15s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Â¡Ayuda, Pix!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluE.png", description = "Si se lanza sobre un aliado, ordena a Pix que salte sobre Ã©l y protegerle. Entonces le sigue y asiste sus ataques. Si se lanza sobre un enemigo, ordena a Pix que salte sobre Ã©l e infligirle daÃ±o. Entonces le sigue, revelÃ¡ndolo.", cooldown = "8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Crecimiento salvaje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuluR.png", description = "Lulu hace crecer a un aliado. Los enemigos cercanos salen despedidos y el aliado afectado consigue gran cantidad de vida adicional. Durante los siguientes segundos, el aliado obtiene ademÃ¡s un aura que ralentiza a los enemigos prÃ³ximos.", cooldown = "100/90/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lulu",
            wrMetaUrl = "https://wr-meta.com/champion/lulu/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lulu/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lulu"
        ),
        Champion(
            id = "lux",
            name = "Lux",
            nameEn = "",
            namePt = "",
            title = "La Dama Luminosa",
            titleEn = "",
            titlePt = "",
            ddragonId = "Lux",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Lux.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S+",
            winrate = 50.43,
            pickRate = 12.9,
            banRate = 18.17,
            damageType = DamageType.MAGIC,
            summary = "Luxanna Crownguard procede de Demacia, un reino insular en el que las habilidades mÃ¡gicas se observan con temor y suspicacia. Capaz de manipular la luz a su voluntad, creciÃ³ temiendo que la descubriesen y la exiliaran, por lo que se vio obligada a...",
            advantageAgainst = listOf("Sion", "Jinx", "Maestro Yi"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Lux en MID. Coordina el uso de su Chispa final para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "IluminaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/LuxIlluminatingFraulein.png", description = "Las habilidades de Lux que infligen daÃ±o cargan de energÃ­a al objetivo durante unos segundos. Su siguiente ataque desata la energÃ­a e inflige daÃ±o mÃ¡gico adicional (segÃºn el nivel de Lux) al objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Enlace de luz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxLightBinding.png", description = "Lux lanza una esfera de luz que inmoviliza y causa daÃ±o a un mÃ¡ximo de 2 unidades enemigas.", cooldown = "11/10.5/10/9.5/9s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Barrera prismÃ¡tica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxPrismaticWave.png", description = "Lux lanza su varita y concentra la luz alrededor de los objetivos aliados a los que alcanza, protegiÃ©ndolos de cualquier daÃ±o.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Singularidad brillante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxLightStrikeKugel.png", description = "Lanza una anomalÃ­a de luz entrelazada a un Ã¡rea, que ralentiza a los enemigos cercanos. Lux puede activarla para daÃ±ar a los enemigos dentro del Ã¡rea de efecto.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Chispa final", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/LuxR.png", description = "Tras reunir la energÃ­a necesaria, Lux lanza un rayo de luz que inflige daÃ±o a todos los objetivos de la zona. AdemÃ¡s, activa la pasiva de Lux y reinicia la duraciÃ³n de la debilitaciÃ³n de IluminaciÃ³n.", cooldown = "60/50/40s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lux",
            wrMetaUrl = "https://wr-meta.com/champion/lux/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/lux/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/lux"
        ),
        Champion(
            id = "malphite",
            name = "Malphite",
            nameEn = "",
            namePt = "",
            title = "El Fragmento del Monolito",
            titleEn = "",
            titlePt = "",
            ddragonId = "Malphite",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Malphite.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.SUPPORT),
            tier = "S+",
            winrate = 50.96,
            pickRate = 10.54,
            banRate = 19.63,
            damageType = DamageType.MAGIC,
            summary = "Como una criatura enorme de piedra viviente, a Malphite le cuesta imponer el orden en un mundo caÃ³tico. Creado como sirviente de fragmentos de un obelisco sobrenatural conocido como el Monolito, usÃ³ su increÃ­ble fuerza elemental para mantener y proteger...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Lulu", "Amumu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Malphite en TOP. Coordina el uso de su Fuerza imparable para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Escudo de granito", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Malphite_GraniteShield.png", description = "Malphite queda protegido por una envoltura de piedra que absorbe daÃ±o hasta un mÃ¡ximo del 10% de su vida mÃ¡xima. Si no recibe daÃ±o durante unos segundos el efecto se reinicia.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Fragmento sÃ­smico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeismicShard.png", description = "Malphite lanza un fragmento de tierra por el suelo hacia su enemigo. AdemÃ¡s de causarle daÃ±o, le roba velocidad de movimiento durante 3 s.", cooldown = "8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Atronar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Obduracy.png", description = "Malphite ataca con tal fuerza que crea una explosiÃ³n sÃ³nica. Durante los prÃ³ximos segundos, sus ataques crean seÃ­smos frente a Ã©l.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Golpe en el suelo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Landslide.png", description = "Malphite golpea el suelo y provoca una onda de choque que inflige daÃ±o mÃ¡gico en funciÃ³n de su armadura y reduce la velocidad de ataque de los enemigos durante un breve periodo de tiempo.", cooldown = "7s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Fuerza imparable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/UFSlash.png", description = "Malphite se lanza hacia una ubicaciÃ³n a gran velocidad, inflige daÃ±o a los enemigos y los lanza por los aires.", cooldown = "130/105/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/malphite",
            wrMetaUrl = "https://wr-meta.com/champion/malphite/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/malphite/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/malphite"
        ),
        Champion(
            id = "maokai",
            name = "Maokai",
            nameEn = "",
            namePt = "",
            title = "El Treant Retorcido",
            titleEn = "",
            titlePt = "",
            ddragonId = "Maokai",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Maokai.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "C",
            winrate = 49.97,
            pickRate = 2.42,
            banRate = 0.56,
            damageType = DamageType.MAGIC,
            summary = "Maokai es un imponente y feroz treant que lucha contra los horrores antinaturales de las Islas de la Sombra. Las ansias de venganza le inundaron despuÃ©s de que un cataclismo mÃ¡gico destruyera su hogar, y sobrevive a la podredumbre Ãºnicamente por las...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Maokai en SUPPORT. Coordina el uso de su Garras de la naturaleza para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "AbsorciÃ³n de magia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Maokai_Passive.png", description = "Los ataques bÃ¡sicos de Maokai tambiÃ©n lo curan e infligen daÃ±o adicional tras un enfriamiento moderado. Cada vez que Maokai lanza un hechizo o es golpeado por un hechizo enemigo, este enfriamiento se reduce.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Zarzal opresor", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiQ.png", description = "Maokai derriba a los enemigos cercanos con una onda de choque que les causa daÃ±o mÃ¡gico y los ralentiza.", cooldown = "7/6.5/6/5.5/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Avance retorcido", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiW.png", description = "Maokai se retuerce para convertirse en una masa de raÃ­ces mÃ³viles, se vuelve invulnerable y se lanza hacia el objetivo. Al llegar, inmoviliza al objetivo.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Lanzamiento de pimpollo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiE.png", description = "Maokai lanza un pimpollo a la zona seleccionada para que la vigile. Es mÃ¡s efectivo en la maleza.", cooldown = "18/17/16/15/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Garras de la naturaleza", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MaokaiR.png", description = "Maokai invoca un muro de zarzas y pinchos enorme que avanza lentamente hacia delante e inmoviliza y daÃ±a a los enemigos en su camino.", cooldown = "130/110/90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/maokai",
            wrMetaUrl = "https://wr-meta.com/champion/maokai/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/maokai/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/maokai"
        ),
        Champion(
            id = "master_yi",
            name = "Maestro Yi",
            nameEn = "",
            namePt = "",
            title = "El EspadachÃ­n Wuju",
            titleEn = "",
            titlePt = "",
            ddragonId = "MasterYi",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/MasterYi.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "A+",
            winrate = 51.0,
            pickRate = 15.3,
            banRate = 6.9,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Maestro Yi ha atemperado su cuerpo y agudizado su mente, de modo que el pensamiento y la acciÃ³n se han convertido casi en uno. Aunque elige emplear la violencia solo como Ãºltimo recurso, la gracia y la velocidad de su espada aseguran que el final...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Maestro Yi en JUNGLE. Coordina el uso de su Imparable para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Golpe doble", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/MasterYi_Passive1.png", description = "Cada varios ataques bÃ¡sicos consecutivos, Maestro Yi golpea dos veces.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe fulgurante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AlphaStrike.png", description = "Maestro Yi se teleporta por el campo de batalla a toda velocidad, infligiendo daÃ±o fÃ­sico a mÃºltiples unidades a su paso. AdemÃ¡s, no se le puede marcar como objetivo. Golpe fulgurante puede asestar golpes crÃ­ticos e inflige daÃ±o fÃ­sico adicional a los monstruos. Los ataques bÃ¡sicos reducen el enfriamiento de Golpe fulgurante.", cooldown = "20/19.5/19/18.5/18s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Meditar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Meditate.png", description = "Maestro Yi medita y rejuvenece su cuerpo, restaurando vida y reduciendo el daÃ±o recibido durante un breve periodo de tiempo. AdemÃ¡s, obtiene acumulaciones de Golpe doble y pausa la duraciÃ³n restante de Estilo Wuju e Imparable por cada segundo de canalizaciÃ³n.", cooldown = "10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Estilo Wuju", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/WujuStyle.png", description = "Otorga daÃ±o verdadero adicional con los ataques bÃ¡sicos.", cooldown = "14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Imparable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Highlander.png", description = "Maestro Yi se mueve con una agilidad sin igual, lo que aumenta temporalmente su velocidad de ataque y su velocidad de movimiento, ademÃ¡s de hacerlo inmune a todos los efectos de ralentizaciÃ³n. Mientras la habilidad siga activa, los asesinatos de campeones y las ayudas aumentan la duraciÃ³n de Imparable. Reduce de forma pasiva el enfriamiento de sus otras habilidades al conseguir un asesinato o una ayuda.", cooldown = "85s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/master-yi",
            wrMetaUrl = "https://wr-meta.com/champion/master-yi/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/master-yi/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/master-yi"
        ),
        Champion(
            id = "mel",
            name = "Mel",
            nameEn = "",
            namePt = "",
            title = "la Consejera Dorada",
            titleEn = "",
            titlePt = "",
            ddragonId = "mel",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Mel.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S+",
            winrate = 47.89,
            pickRate = 9.9,
            banRate = 28.38,
            damageType = DamageType.MAGIC,
            summary = "Mel domina las lÃ­neas con magia solar Ã¡urea, proveyendo daÃ±o a distancia, escudos reflectantes y potente control de masas en combates de equipo.",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Mel en MID. Coordina el uso de su Trascendencia Imperial para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Resonancia Radiante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Mel_Passive.png", description = "Lanzar habilidades marca a los enemigos; consumir la marca otorga velocidad de movimiento y daÃ±o mÃ¡gico extra.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Rayo del Solio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelQ.png", description = "Dispara un haz solar penetrante en lÃ­nea recta infligiendo daÃ±o mÃ¡gico a todos los enemigos a su paso.", cooldown = "6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Decreto Protector", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelW.png", description = "Otorga un escudo Ã¡ureo a un aliado o a sÃ­ misma que mitiga daÃ±o y refleja una fracciÃ³n hacia los agresores.", cooldown = "13s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Manto de la Discordia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelE.png", description = "Despliega una zona de luz que ralentiza y silencia a los oponentes que permanecen en su interior.", cooldown = "11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Trascendencia Imperial", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelR.png", description = "Canaliza una tormenta solar que baÃ±a una gran Ã¡rea con daÃ±o mÃ¡gico colosal y aturde a los campeones alcanzados.", cooldown = "70s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/mel",
            wrMetaUrl = "https://wr-meta.com/champion/mel/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/mel/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/mel"
        ),
        Champion(
            id = "milio",
            name = "Milio",
            nameEn = "",
            namePt = "",
            title = "la Llama Serena",
            titleEn = "",
            titlePt = "",
            ddragonId = "Milio",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Milio.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.0,
            pickRate = 8.3,
            banRate = 9.1,
            damageType = DamageType.MAGIC,
            summary = "Milio es un jovencito amable de Ixtal que, a pesar de su corta edad, ha conseguido dominar el axioma Ã­gneo y ha descubierto las llamas de la calma. Con este nuevo poder, Milio pretende ayudar a su familia y poner fin a su exilio uniÃ©ndose a los Yun Tal...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Milio en SUPPORT. Coordina el uso de su Aliento vital para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Â¡Al calorcito!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Milio_P.png", description = "Las habilidades de Milio hechizan a sus aliados al tocarlos, lo que hace que la prÃ³xima vez que inflijan daÃ±o tambiÃ©n generen una explosiÃ³n de daÃ±o adicional y quemen a su objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Ultramegapatada ardiente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioQ.png", description = "Patea una bola que empuja a un enemigo. La bola sale disparada hacia arriba y cae sobre el enemigo, lo que inflige daÃ±o y ralentiza a los enemigos de la zona al impactar.", cooldown = "10s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Hoguera reconfortante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioW.png", description = "Crea un Ã¡rea de potenciaciÃ³n que cura a los aliados y aumenta el alcance de quienes se encuentran en su interior. El Ã¡rea sigue al aliado mÃ¡s cercano al punto de lanzamiento.", cooldown = "29/27/25/23/21s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Abrazos cÃ¡lidos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioE.png", description = "Milio lanza un escudo a un aliado, lo que aumenta temporalmente su velocidad de movimiento. Esta habilidad tiene 2 cargas.", cooldown = "0.5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Aliento vital", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MilioR.png", description = "Milio lanza una ola de llamas reconfortantes que cura y elimina los efectos de control de adversario de los aliados que se encuentren a su alcance.", cooldown = "160/145/130s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/milio",
            wrMetaUrl = "https://wr-meta.com/champion/milio/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/milio/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/milio"
        ),
        Champion(
            id = "miss_fortune",
            name = "Miss Fortune",
            nameEn = "",
            namePt = "",
            title = "La Cazarrecompensas",
            titleEn = "",
            titlePt = "",
            ddragonId = "MissFortune",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/MissFortune.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S+",
            winrate = 53.3,
            pickRate = 15.5,
            banRate = 8.8,
            damageType = DamageType.PHYSICAL,
            summary = "Sarah Fortune se cuenta sin duda entre los capitanes mÃ¡s conocidos de Aguas Estancadas y es tan cÃ©lebre por su apariencia como temida por su ferocidad. Por eso despunta entre los criminales mÃ¡s peligrosos de esta ciudad portuaria. De niÃ±a, presenciÃ³...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Miss Fortune en ADC. Coordina el uso de su Lluvia de balas para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Cachete", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/MissFortune_W.png", description = "Miss Fortune inflige daÃ±o fÃ­sico adicional cada vez que golpea con un ataque bÃ¡sico a un nuevo objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Redoble", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissFortuneRicochetShot.png", description = "Miss Fortune dispara una bala a un enemigo que daÃ±a a este y a un objetivo que estÃ© detrÃ¡s. Ambos golpes pueden aplicar los efectos de Cachete.", cooldown = "7/6/5/4/3s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Alarde", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissFortuneViciousStrikes.png", description = "Si no la atacan, Miss Fortune recibe velocidad de movimiento de forma pasiva. Esta habilidad puede activarse para aumentar la velocidad de ataque durante un breve periodo de tiempo. Mientras estÃ¡ en enfriamiento, Cachete reduce el enfriamiento restante de Alarde.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Que llueva", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissFortuneScattershot.png", description = "Miss Fortune revela una zona con una lluvia de balas, infligiendo oleadas de daÃ±o a los oponentes y ralentizÃ¡ndolos.", cooldown = "18/17/16/15/14s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Lluvia de balas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MissFortuneBulletTime.png", description = "Miss Fortune canaliza una lluvia de balas en un cono frente a ella, infligiendo una gran cantidad de daÃ±o a los enemigos. Cada oleada de Lluvia de balas puede asestar impactos crÃ­ticos.", cooldown = "120/110/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/miss-fortune",
            wrMetaUrl = "https://wr-meta.com/champion/miss-fortune/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/miss-fortune/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/miss-fortune"
        ),
        Champion(
            id = "mordekaiser",
            name = "Mordekaiser",
            nameEn = "",
            namePt = "",
            title = "La Pesadilla de Hierro",
            titleEn = "",
            titlePt = "",
            ddragonId = "Mordekaiser",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Mordekaiser.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 48.43,
            pickRate = 11.52,
            banRate = 11.27,
            damageType = DamageType.MAGIC,
            summary = "Mordekaiser es un sanguinario seÃ±or de la guerra proveniente de tiempos olvidados al que los siglos han visto nacer en tres ocasiones y morir en otras dos. Utiliza sus poderes necromÃ¡nticos para atar almas perdidas a una vida eterna a su servicio. Ya no...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Mordekaiser en TOP. Coordina el uso de su Reino de la muerte para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "El auge de la oscuridad", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/MordekaiserPassive.png", description = "Mordekaiser obtiene una poderosa aura de daÃ±o y velocidad de movimiento despuÃ©s de acertar tres ataques o habilidades contra campeones o monstruos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "AniquilaciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MordekaiserQ.png", description = "Mordekaiser golpea el suelo con su maza e inflige daÃ±o a todos los enemigos golpeados. El daÃ±o aumenta cuando golpea a un solo objetivo.", cooldown = "8/7/6/5/4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Indestructible", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MordekaiserW.png", description = "Mordekaiser almacena el daÃ±o infligido para generar un escudo. Puede consumir el escudo para curarse.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Abrazo de la muerte", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MordekaiserE.png", description = "Mordekaiser atrae a todos los enemigos de una zona.", cooldown = "18/16/14/12/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Reino de la muerte", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MordekaiserR.png", description = "Mordekaiser arrastra a su vÃ­ctima a otra dimensiÃ³n y le roba una parte de sus estadÃ­sticas. Si consigue matarla, se queda con las estadÃ­sticas robadas hasta que la vÃ­ctima vuelva a aparecer en la fuente.", cooldown = "140/120/100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/mordekaiser",
            wrMetaUrl = "https://wr-meta.com/champion/mordekaiser/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/mordekaiser/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/mordekaiser"
        ),
        Champion(
            id = "morgana",
            name = "Morgana",
            nameEn = "",
            namePt = "",
            title = "la CaÃ­da",
            titleEn = "",
            titlePt = "",
            ddragonId = "Morgana",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Morgana.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.09,
            pickRate = 5.9,
            banRate = 21.01,
            damageType = DamageType.MAGIC,
            summary = "En vistas del conflicto entre su naturaleza celestial y su naturaleza mortal, Morgana decidiÃ³ atarse las alas para aceptar la humanidad y deja caer el peso de su dolor y rencor sobre los deshonestos y los corruptos. Se opone a las leyes y tradiciones...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Zed"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Morgana en SUPPORT. Coordina el uso de su Grilletes del alma para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Absorbealmas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/FallenAngel_Empathize.png", description = "Morgana absorbe el espÃ­ritu de sus enemigos y se cura cuando sus hechizos daÃ±an a campeones, sÃºbditos grandes y monstruos gigantes y medianos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Hechizo oscuro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaQ.png", description = "Morgana atrapa y detiene con magia negra a un enemigo, lo hace experimentar todo el dolor que ha causado y le inflige daÃ±o mÃ¡gico.", cooldown = "10s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Sombra atormentada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaW.png", description = "Morgana invoca a una sombra maldita en una zona e inflige daÃ±o a todos los enemigos que osen caminar sobre su cÃ­rculo de oscuridad. Reciben daÃ±o mÃ¡gico prologando que aumenta en funciÃ³n de la vida que les falte.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Escudo negro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaE.png", description = "Morgana escuda a un aliado con una barrera protectora de fuego estelar que absorbe daÃ±o mÃ¡gico e impide que se apliquen efectos incapacitantes hasta que se haya roto.", cooldown = "26/23.5/21/18.5/16s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Grilletes del alma", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MorganaR.png", description = "Morgana desata todo el potencial de su poder celestial, se suelta las alas y flota sobre el suelo. Lanza cadenas de oscuro dolor hacia los campeones enemigos, lo que le otorga velocidad de movimiento. Las cadenas ralentizan e infligen daÃ±o inicialmente y, tras unos instantes, aturden a todos aquellos que no hayan conseguido liberarse de ellas.", cooldown = "120/110/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/morgana",
            wrMetaUrl = "https://wr-meta.com/champion/morgana/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/morgana/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/morgana"
        ),
        Champion(
            id = "nami",
            name = "Nami",
            nameEn = "",
            namePt = "",
            title = "La Invocadora de Mareas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nami",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nami.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 51.11,
            pickRate = 3.28,
            banRate = 0.26,
            damageType = DamageType.MAGIC,
            summary = "Nami, una joven y testaruda vastaya marina, fue la primera de la tribu marai en abandonar las olas y aventurarse en tierra firme cuando se rompiÃ³ el ancestral acuerdo de su tribu con los targonianos. A falta de otra opciÃ³n, Nami se encargÃ³ de completar...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nami en SUPPORT. Coordina el uso de su Maremoto para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Oleaje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/NamiPassive.png", description = "Cuando las habilidades de Nami impactan sobre campeones aliados, obtienen velocidad de movimiento durante unos instantes.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "PrisiÃ³n de agua", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiQ.png", description = "Lanza una burbuja a la zona objetivo que inflige daÃ±o y aturde a todos los enemigos al impactar.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Resaca y oleada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiW.png", description = "Libera una corriente de agua que rebota entre campeones aliados y enemigos, de forma que cura a los aliados e inflige daÃ±o a los enemigos.", cooldown = "10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "BendiciÃ³n de la marea", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiE.png", description = "Potencia a un campeÃ³n aliado durante un breve periodo de tiempo. Los hechizos y ataques bÃ¡sicos del aliado infligen daÃ±o mÃ¡gico adicional y ralentizan al objetivo.", cooldown = "11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Maremoto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NamiR.png", description = "Invoca un gigantesco maremoto que derriba, ralentiza e inflige daÃ±o a los enemigos. Los aliados alcanzados se benefician del efecto de Oleaje multiplicado por dos.", cooldown = "120/110/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nami",
            wrMetaUrl = "https://wr-meta.com/champion/nami/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nami/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nami"
        ),
        Champion(
            id = "nasus",
            name = "Nasus",
            nameEn = "",
            namePt = "",
            title = "El GuardiÃ¡n de las Arenas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nasus",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nasus.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 48.4,
            pickRate = 5.85,
            banRate = 1.69,
            damageType = DamageType.PHYSICAL,
            summary = "Nasus es un imponente ser Ascendido con cabeza de chacal procedente de la antigua Shurima; una figura heroica a la que las gentes del desierto han encumbrado al nivel de semidiÃ³s. Poseedor de una increÃ­ble inteligencia, fue un guardiÃ¡n del saber y...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nasus en TOP. Coordina el uso de su Furia de las arenas para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Devorador de almas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Nasus_Passive.png", description = "Nasus drena la energÃ­a espiritual de su enemigo, lo que le proporciona robo de vida adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe absorbente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NasusQ.png", description = "Nasus golpea a su enemigo e inflige daÃ±o. Si mata a su objetivo, aumenta el poder de sus siguientes golpes absorbentes.", cooldown = "7.5/6.5/5.5/4.5/3.5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Marchitar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NasusW.png", description = "Nasus envejece al campeÃ³n enemigo, reduciÃ©ndole progresivamente la velocidad de movimiento y la velocidad de ataque durante un perÃ­odo de tiempo.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Fuego espiritual", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NasusE.png", description = "Nasus desata una llama espiritual en un punto, daÃ±ando a los enemigos cercanos y reduciendo su armadura.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Furia de las arenas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NasusR.png", description = "Nasus desata una poderosa tormenta de arena que azota a los enemigos cercanos. Mientras ruge la tormenta, obtiene una bonificaciÃ³n de vida y alcance de ataque. DaÃ±a a los enemigos cercanos y otorga armadura y resistencia mÃ¡gica adicionales. AdemÃ¡s, el enfriamiento de Golpe absorbente se reduce.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nasus",
            wrMetaUrl = "https://wr-meta.com/champion/nasus/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nasus/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nasus"
        ),
        Champion(
            id = "nautilus",
            name = "Nautilus",
            nameEn = "",
            namePt = "",
            title = "El TitÃ¡n Abisal",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nautilus",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nautilus.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "S",
            winrate = 49.33,
            pickRate = 14.02,
            banRate = 3.58,
            damageType = DamageType.MAGIC,
            summary = "El gigante acorazado Nautilus, una leyenda solitaria tan antigua como los pecios de Aguas Estancadas, recorre las turbias aguas que rodean las costas de las Islas de la Llama Azul. Impulsado por una traiciÃ³n olvidada, ataca con su enorme ancla sin...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nautilus en SUPPORT. Coordina el uso de su Carga de profundidad para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Golpe maestro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Nautilus_StaggeringBlow.png", description = "El primer ataque de Nautilus contra un objetivo inflige daÃ±o fÃ­sico adicional y lo inmoviliza brevemente.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "LÃ­nea de dragado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusAnchorDrag.png", description = "Nautilus arroja su ancla. Si impacta con un enemigo, arrastra a ambos hasta una posiciÃ³n intermedia e inflige daÃ±o mÃ¡gico. Si impacta contra un obstÃ¡culo, Nautilus se propulsa hacia Ã©l.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Ira del titÃ¡n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusPiercingGaze.png", description = "Nautilus obtiene un escudo temporal. Mientras estÃ¡ activo, sus ataques infligen daÃ±o prolongado a su objetivo y a los enemigos cercanos.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Aguas revueltas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusSplashZone.png", description = "Nautilus crea tres ondas de explosiones a su alrededor. Cada explosiÃ³n inflige daÃ±o y ralentiza a los enemigos.", cooldown = "7/6.5/6/5.5/5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Carga de profundidad", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NautilusGrandLine.png", description = "Nautilus lanza una onda expansiva al suelo que persigue al rival. Esta onda expansiva hace pedazos la tierra, lo que provoca que los enemigos salten por los aires. Cuando alcanza al enemigo, la onda expansiva estalla y los enemigos saltan por los aires y quedan aturdidos.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nautilus",
            wrMetaUrl = "https://wr-meta.com/champion/nautilus/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nautilus/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nautilus"
        ),
        Champion(
            id = "nidalee",
            name = "Nidalee",
            nameEn = "",
            namePt = "",
            title = "La Cazadora Bestial",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nidalee",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nidalee.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 44.37,
            pickRate = 1.77,
            banRate = 1.34,
            damageType = DamageType.MAGIC,
            summary = "Criada en lo mÃ¡s profundo de la jungla, Nidalee es una rastreadora maestra que puede convertirse en un felino feroz a voluntad. No es completamente mujer ni bestia, pero defiende con fiereza su territorio de todos los invasores, con trampas colocadas...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nidalee en JUNGLE. Coordina el uso de su Aspecto felino para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Cometa Arcano (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Trascendencia",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Acecho", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Nidalee_Passive.png", description = "Moverse por la maleza aumenta la velocidad de movimiento de Nidalee un 10% durante 2 s. El aumento es del 30% si se mueve hacia campeones enemigos visibles a una distancia de 1400 o menos.Si daÃ±a a algÃºn campeÃ³n o monstruo con Jabalina o Emboscada, se activa una CacerÃ­a contra Ã©l durante 4 s, lo que le otorga visiÃ³n verdadera del objetivo y un 10% de velocidad de movimiento adicional (un 30% en direcciÃ³n al objetivo Cazado). AdemÃ¡s, cuando use Tumbar y Asalto contra Ã©l serÃ¡ mÃ¡s eficaz.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Jabalina / Tumbar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/JavelinToss.png", description = "En forma humana, Nidalee lanza a su objetivo una jabalina cuyo daÃ±o aumenta proporcionalmente a la distancia. Como felino, intentarÃ¡ herir de muerte a su objetivo con su siguiente ataque, causÃ¡ndole mÃ¡s daÃ±o cuanta menos vida le quede.", cooldown = "6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Emboscada / Asalto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Bushwhack.png", description = "En forma humana, Nidalee coloca una trampa para rivales incautos que revela y causa daÃ±o a quien la acciona. Como felino, salta en una direcciÃ³n y causa daÃ±o de Ã¡rea donde aterriza.", cooldown = "13/12/11/10/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "TensiÃ³n primaria / Zarpazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PrimalSurge.png", description = "En forma humana, Nidalee concentra el poder del espÃ­ritu felino para sanar a sus aliados y otorgarles velocidad de ataque durante un breve periodo de tiempo. Como felino, lanza zarpazos en una direcciÃ³n e inflige daÃ±o a los enemigos que tiene delante.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Aspecto felino", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/AspectOfTheCougar.png", description = "Nidalee se transforma en felino salvaje, con lo que obtiene habilidades nuevas.", cooldown = "3s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nidalee",
            wrMetaUrl = "https://wr-meta.com/champion/nidalee/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nidalee/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nidalee"
        ),
        Champion(
            id = "nilah",
            name = "Nilah",
            nameEn = "",
            namePt = "",
            title = "la AlegrÃ­a Desatada",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nilah",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nilah.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.1,
            pickRate = 4.2,
            banRate = 13.1,
            damageType = DamageType.PHYSICAL,
            summary = "Nilah es una guerrera ascÃ©tica proveniente de tierras lejanas que busca a los adversarios mÃ¡s mortÃ­feros y gigantescos para poder desafiarlos y destruirlos. Tras obtener sus poderes en un encuentro con el demonio de la alegrÃ­a, confinado desde hace una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nilah en ADC. Coordina el uso de su Apoteosis para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "JÃºbilo sin fin", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/NIlahP.png", description = "Nilah obtiene experiencia adicional por asestar el Ãºltimo golpe a sÃºbditos, ademÃ¡s de la capacidad de mejorar y compartir la curaciÃ³n y los escudos de los aliados cercanos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Hoja sin forma", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NilahQ.png", description = "Con un chasquido de su espada lÃ¡tigo, Nilah daÃ±a a todos los enemigos golpeados en una lÃ­nea recta en la direcciÃ³n elegida. Esta acciÃ³n aumenta brevemente el alcance de sus ataques.", cooldown = "4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Velo exultante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NilahW.png", description = "Nilah se envuelve en niebla, lo que aumenta su velocidad de movimiento y le permite esquivar con elegancia todos los ataques que reciba. Los aliados a los que toque durante la duraciÃ³n de la niebla tambiÃ©n obtendrÃ¡n este efecto.", cooldown = "26/25/24/23/22s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Estela", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NilahE.png", description = "Nilah se desliza hacia su objetivo con entusiasmo e inflige daÃ±o a todos los enemigos a los que atraviesa.", cooldown = "0.5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Apoteosis", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NilahR.png", description = "Haciendo girar su espada lÃ¡tigo con gran regocijo, Nilah inflige daÃ±o a los enemigos que la rodean y los atrae hacia ella.", cooldown = "110/95/80s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nilah",
            wrMetaUrl = "https://wr-meta.com/champion/nilah/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nilah/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nilah"
        ),
        Champion(
            id = "nocturne",
            name = "Nocturne",
            nameEn = "",
            namePt = "",
            title = "La Pesadilla Eterna",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nocturne",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nocturne.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 49.96,
            pickRate = 3.24,
            banRate = 2.87,
            damageType = DamageType.PHYSICAL,
            summary = "El ser conocido como Nocturne es una fusiÃ³n demonÃ­aca extraÃ­da de las pesadillas que acechan a todas las mentes conscientes y se ha convertido en una fuerza primordial de pura maldad. Tiene un aspecto lÃ­quido y caÃ³tico, una sombra sin rostro con ojos...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nocturne en JUNGLE. Coordina el uso de su Paranoia para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Espadas oscuras", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Nocturne_UmbraBlades.png", description = "Cada pocos segundos, el siguiente ataque de Nocturne golpea a los enemigos cercanos, inflige daÃ±o fÃ­sico adicional y lo cura. Los ataques bÃ¡sicos de Nocturne reducen este enfriamiento.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Portador del anochecer", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NocturneDuskbringer.png", description = "Nocturne lanza una espada de sombras que inflige daÃ±o, deja un rastro de oscuridad y provoca que los campeones dejen un rastro de oscuridad. Dentro de dicho rastro, Nocturne puede moverse entre unidades y ve incrementada su velocidad de movimiento y daÃ±o de ataque.", cooldown = "8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Velo de oscuridad", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NocturneShroudofDarkness.png", description = "Nocturne potencia sus espadas, ganando velocidad de ataque de forma pasiva. Utilizar Velo de oscuridad le permite a Nocturne perderse en las sombras, creando una barrera mÃ¡gica que bloquea la habilidad de un enemigo y, en caso de tener Ã©xito, dobla su velocidad de ataque pasiva.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Horror inenarrable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NocturneUnspeakableHorror.png", description = "Nocturne planta una pesadilla en la mente de su objetivo, que inflige daÃ±o cada segundo y aplica miedo al objetivo si este no sale de su alcance antes de que concluya la duraciÃ³n del hechizo.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Paranoia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NocturneParanoia.png", description = "Nocturne reduce el radio de visiÃ³n de todos los campeones enemigos y elimina su visiÃ³n de los aliados. Entonces, puede lanzarse hacia un campeÃ³n enemigo cercano.", cooldown = "140/115/90s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nocturne",
            wrMetaUrl = "https://wr-meta.com/champion/nocturne/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nocturne/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nocturne"
        ),
        Champion(
            id = "norra",
            name = "Norra",
            nameEn = "",
            namePt = "",
            title = "la Maestra de los Portales",
            titleEn = "",
            titlePt = "",
            ddragonId = "norra",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "D",
            winrate = 48.07,
            pickRate = 1.47,
            banRate = 5.71,
            damageType = DamageType.MAGIC,
            summary = "Norra abre fisuras hacia la Ciudad de Bandle para dotar de hipermovilidad a su equipo y desorientar a los adversarios con proyectiles cÃ³smicos.",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Norra en MID. Coordina el uso de su Puerta de Bandle para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Portales del Reino Espiritual", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png", description = "Al lanzar hechizos crea vÃ³rtices efÃ­meros que potencian las curaciones, escudos y movilidad del equipo aliado.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Proyectil Astral", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png", description = "Lanza una esfera de energÃ­a espiritual que rebota entre enemigos infligiendo daÃ±o mÃ¡gico creciente.", cooldown = "7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "DistorsiÃ³n Dimensional", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png", description = "Desplaza a un objetivo aliado o a sÃ­ misma a travÃ©s de una grieta hacia una posiciÃ³n segura con velocidad extra.", cooldown = "14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "VÃ­nculo de Bandle", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png", description = "Enlaza a un aliado otorgÃ¡ndole manÃ¡ continuo y daÃ±o mÃ¡gico adicional en sus siguientes impactos.", cooldown = "10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Puerta de Bandle", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Yuumi.png", description = "Abre un portal cÃ³smico masivo que succiona a los enemigos hacia el vÃ³rtice central, aturdiÃ©ndolos e infligiendo daÃ±o masivo.", cooldown = "75s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/norra",
            wrMetaUrl = "https://wr-meta.com/champion/norra/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/norra/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/norra"
        ),
        Champion(
            id = "nunu_willump",
            name = "Nunu y Willump",
            nameEn = "",
            namePt = "",
            title = "Un NiÃ±o y su Yeti",
            titleEn = "",
            titlePt = "",
            ddragonId = "Nunu",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Nunu.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 50.3,
            pickRate = 6.1,
            banRate = 14.1,
            damageType = DamageType.MAGIC,
            summary = "HabÃ­a una vez un niÃ±o que querÃ­a acabar con un temible monstruo para demostrar que era un hÃ©roe; pero que terminÃ³ descubriendo que la bestia, un yeti solitario y mÃ¡gico, solo necesitaba un amigo. Ahora, Nunu y Willump, unidos por un poder ancestral y...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Nunu y Willump en JUNGLE. Coordina el uso de su Cero absoluto para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Llamada de Freljord", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/NunuPassive.png", description = "Nunu aumenta la velocidad de ataque y de movimiento de Willump y de un aliado cercano, y hace que los ataques bÃ¡sicos de Willump inflijan daÃ±o a los enemigos de alrededor del objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Voracidad", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NunuQ.png", description = "Willump muerde a un sÃºbdito, monstruo o campeÃ³n enemigo, lo que le inflige daÃ±o y restaura su propia vida.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Â¡La bola mÃ¡s grande de la historia!", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NunuW.png", description = "Willump hace una bola de nieve que aumenta en tamaÃ±o y velocidad mientras la hace rodar. La bola de nieve inflige daÃ±o a los enemigos y los lanza por los aires.", cooldown = "14s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Alud de bolas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NunuE.png", description = "Nunu lanza varias bolas de nieve que daÃ±an a los enemigos. Una vez ha terminado, Willump inmoviliza a todos los campeones o monstruos gigantes que hayan recibido el impacto de una bola de nieve.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Cero absoluto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/NunuR.png", description = "Nunu y Willump generan un poderoso torbellino en un Ã¡rea que ralentiza a los enemigos e inflige un montÃ³n de daÃ±o al final.", cooldown = "110/100/90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nunu-willump",
            wrMetaUrl = "https://wr-meta.com/champion/nunu-willump/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/nunu-willump/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/nunu-willump"
        ),
        Champion(
            id = "olaf",
            name = "Olaf",
            nameEn = "",
            namePt = "",
            title = "El Berserker",
            titleEn = "",
            titlePt = "",
            ddragonId = "Olaf",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Olaf.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "D",
            winrate = 49.89,
            pickRate = 1.75,
            banRate = 1.03,
            damageType = DamageType.PHYSICAL,
            summary = "Olaf porta sus hachas de forma que es una fuerza imparable de destrucciÃ³n que no quiere nada, salvo morir en un combate glorioso. Natural de Lokfar, una brutal penÃ­nsula de Freljord, una vez escuchÃ³ una profecÃ­a que lo avisaba de una muerte tranquila...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Olaf en TOP. Coordina el uso de su Ragnarok para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Furia berserker", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Olaf_Passive.png", description = "Olaf obtiene velocidad de ataque y robo de vida segÃºn la vida que le falte.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Lanzamiento", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OlafAxeThrowCast.png", description = "Olaf lanza un hacha que impacta en la ubicaciÃ³n seleccionada. Inflige daÃ±o a los enemigos que atraviesa y reduce su armadura y su velocidad de movimiento. Si Olaf recoge el hacha, el enfriamiento de la habilidad se restablece.", cooldown = "9s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Aguante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OlafFrenziedStrikes.png", description = "Aumenta la velocidad de ataque de Olaf y obtiene un escudo.", cooldown = "16/15/14/13/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Embestida temeraria", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OlafRecklessStrike.png", description = "Olaf ataca con tal fuerza que causa daÃ±o verdadero a su objetivo y a sÃ­ mismo, pero el daÃ±o autoinfligido se recupera si destruye al objetivo.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Ragnarok", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OlafRagnarok.png", description = "Olaf gana armadura y resistencia mÃ¡gica de forma pasiva. Puede activar esta habilidad para volverse inmune a las incapacitaciones siempre y cuando siga atacando.", cooldown = "100/90/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/olaf",
            wrMetaUrl = "https://wr-meta.com/champion/olaf/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/olaf/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/olaf"
        ),
        Champion(
            id = "orianna",
            name = "Orianna",
            nameEn = "",
            namePt = "",
            title = "La Dama MecÃ¡nica",
            titleEn = "",
            titlePt = "",
            ddragonId = "Orianna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Orianna.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 53.63,
            pickRate = 3.0,
            banRate = 0.24,
            damageType = DamageType.MAGIC,
            summary = "Orianna, antaÃ±o una chica curiosa de carne y hueso, es ahora una maravilla tecnolÃ³gica compuesta exclusivamente de engranajes. CayÃ³ gravemente enferma tras un accidente en los distritos inferiores de Zaun y su cuerpo moribundo tuvo que ser reemplazado...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Orianna en MID. Coordina el uso de su Orden: onda de choque para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Primer Golpe (InspiraciÃ³n)",
            runeTreeDetails = "InspiraciÃ³n: Destello Hextech â€¢ Se Avecina Tormenta â€¢ Trascendencia â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Dando cuerda", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/OriannaPassive.png", description = "Los ataques de Orianna infligen daÃ±o mÃ¡gico adicional. El daÃ±o aumenta conforme Orianna sigue atacando al mismo objetivo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Orden: atacar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaIzunaCommand.png", description = "Orianna ordena a La Bola que salga disparada hacia una ubicaciÃ³n, lo que inflige daÃ±o mÃ¡gico a los objetivos que encuentre en el camino (inflige menos daÃ±o a cada objetivo subsiguiente). DespuÃ©s, La Bola permanece en esa ubicaciÃ³n.", cooldown = "6/5.25/4.5/3.75/3s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Orden: disonancia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaDissonanceCommand.png", description = "Orianna ordena a La Bola que libere un pulso de energÃ­a que inflige daÃ±o mÃ¡gico a su alrededor, lo cual crea una zona que acelera a los aliados y ralentiza a los enemigos.", cooldown = "7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Orden: proteger", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaRedactCommand.png", description = "Orianna ordena a La Bola que se adhiera a un campeÃ³n aliado, le otorga un escudo e inflige daÃ±o mÃ¡gico a los enemigos que atraviese a su paso. AdemÃ¡s, La Bola otorga armadura y resistencia mÃ¡gica adicional al campeÃ³n que protege.", cooldown = "9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Orden: onda de choque", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrianaDetonateCommand.png", description = "Orianna ordena a La Bola que desencadene una onda de choque que inflige daÃ±o mÃ¡gico y lanza a los enemigos cercanos hacia esta Ãºltima despuÃ©s de unos instantes.", cooldown = "110/95/80s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/orianna",
            wrMetaUrl = "https://wr-meta.com/champion/orianna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/orianna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/orianna"
        ),
        Champion(
            id = "ornn",
            name = "Ornn",
            nameEn = "",
            namePt = "",
            title = "Las Llamas de la Forja",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ornn",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ornn.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 47.55,
            pickRate = 1.42,
            banRate = 0.39,
            damageType = DamageType.MAGIC,
            summary = "Ornn es el espÃ­ritu de Freljord de la forja y la artesanÃ­a. Trabaja en la soledad de una enorme forja esculpida en las cavernas de lava bajo el volcÃ¡n de Dulcehogar. En ella modela objetos de calidad sin igual y depura menas en burbujeantes calderos de...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ornn en TOP. Coordina el uso de su Llamada del dios de la forja para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Forja viviente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/OrnnP.png", description = "Ornn obtiene armadura y resistencia mÃ¡gica adicionales de todas las fuentes.Ornn puede gastar oro para forjar objetos no consumibles en cualquier lugar.AdemÃ¡s, puede crear objetos selectos para Ã©l y sus aliados.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Ruptura volcÃ¡nica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrnnQ.png", description = "Ornn golpea el suelo y crea una fisura que inflige daÃ±o y ralentiza a los enemigos golpeados. Tras unos breves instantes, se forma un pilar de magma en la ubicaciÃ³n final.", cooldown = "9/8.5/8/7.5/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Aliento de fuelle", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrnnW.png", description = "Ornn avanza mientras escupe fuego. A los enemigos a los que alcance la Ãºltima llamarada se les aplicarÃ¡ el efecto quebradizo.", cooldown = "12/11.5/11/10.5/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Carga candente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrnnE.png", description = "Ornn carga e inflige daÃ±o a los enemigos sobre los que pasa. Si Ornn choca contra algÃºn obstÃ¡culo del terreno mientras carga, el impacto crea una onda expansiva a su alrededor que inflige daÃ±o y lanza por los aires a los enemigos.", cooldown = "14/13.5/13/12.5/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Llamada del dios de la forja", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/OrnnR.png", description = "Ornn invoca un gÃ³lem de fuego enorme en una ubicaciÃ³n que se dirige hacia Ã©l con una velocidad creciente. El gÃ³lem ralentiza, daÃ±a y aplica el efecto quebradizo a los enemigos sobre los que pasa. Ornn puede volver a lanzar la habilidad para cargar hacia el gÃ³lem de fuego y redirigirlo en la direcciÃ³n en la que lo golpea. El gÃ³lem derriba a los enemigos golpeados, les inflige el mismo daÃ±o y les vuelve a aplicar el efecto quebradizo.", cooldown = "140/120/100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ornn",
            wrMetaUrl = "https://wr-meta.com/champion/ornn/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ornn/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ornn"
        ),
        Champion(
            id = "pantheon",
            name = "Pantheon",
            nameEn = "",
            namePt = "",
            title = "la Lanza Inquebrantable",
            titleEn = "",
            titlePt = "",
            ddragonId = "Pantheon",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Pantheon.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.SUPPORT, LaneRole.JUNGLE),
            tier = "B",
            winrate = 52.57,
            pickRate = 5.65,
            banRate = 2.63,
            damageType = DamageType.PHYSICAL,
            summary = "Tiempo atrÃ¡s, Atreus habÃ­a albergado al Aspecto de la Guerra en su interior contra su voluntad, pero sobreviviÃ³ al golpe que acabÃ³ con el poder divino y arrancÃ³ estrellas del firmamento. Con el paso de los aÃ±os, aprendiÃ³ a aceptar el poder de su propia...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Pantheon en TOP. Coordina el uso de su Gran descarga estelar para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Voluntad mortal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Pantheon_Passive.png", description = "Cada pocos hechizos o ataques, la siguiente habilidad de Pantheon estarÃ¡ potenciada.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Lanza cometa", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PantheonQ.png", description = "Pantheon embiste con su lanza o la arroja en la direcciÃ³n objetivo.", cooldown = "11/10.25/9.5/8.75/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Salto con escudo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PantheonW.png", description = "Pantheon se desliza hasta un objetivo, le inflige daÃ±o y lo aturde.", cooldown = "13/12/11/10/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Asalto protector", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PantheonE.png", description = "Pantheon alza su escudo, se vuelve invulnerable a los ataques frontales y golpea repetidamente con su lanza.", cooldown = "22/21/20/19/18s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Gran descarga estelar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PantheonR.png", description = "Pantheon se prepara para saltar por los aires y aterriza en una ubicaciÃ³n objetivo como un cometa.", cooldown = "180/165/150s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/pantheon",
            wrMetaUrl = "https://wr-meta.com/champion/pantheon/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/pantheon/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/pantheon"
        ),
        Champion(
            id = "poppy",
            name = "Poppy",
            nameEn = "",
            namePt = "",
            title = "La Guardiana del Martillo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Poppy",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Poppy.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE, LaneRole.SUPPORT),
            tier = "A+",
            winrate = 50.6,
            pickRate = 12.3,
            banRate = 11.2,
            damageType = DamageType.PHYSICAL,
            summary = "En Runaterra hay un gran nÃºmero de campeones valerosos, pero muy pocos son tan tenaces como Poppy. Esta yordle tan obstinada porta el legendario martillo de Orlon, que la dobla en tamaÃ±o, y se ha pasado infinidad de aÃ±os buscando en secreto al famoso...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Poppy en TOP. Coordina el uso de su Veredicto de la guardiana para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Embajadora de hierro", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Poppy_Passive.png", description = "Poppy lanza su broquel, el cual rebotarÃ¡ en el objetivo. PodrÃ¡ recogerlo para obtener un escudo temporal.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Impacto de martillo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PoppyQ.png", description = "Poppy usa su martillo, inflige daÃ±o y crea una zona que ralentizarÃ¡ a los enemigos y explotarÃ¡ tras un breve lapso de tiempo.", cooldown = "8/7/6/5/4s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Entereza inalterable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PoppyW.png", description = "Poppy gana armadura y resistencia mÃ¡gica de forma pasiva. La bonificaciÃ³n aumenta cuando tiene poca vida. Poppy puede activar Entereza inalterable para obtener velocidad de movimiento y evitar que los enemigos se deslicen a su alrededor. Si detiene un deslizamiento, el enemigo queda ralentizado y anclado.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Carga heroica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PoppyE.png", description = "Poppy carga hacia su objetivo y lo empuja. Si el objetivo choca contra un muro, se queda aturdido.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Veredicto de la guardiana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PoppyR.png", description = "Poppy ejecuta un golpe con el martillo que lanza a los enemigos muy, muy lejos.", cooldown = "140/120/100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/poppy",
            wrMetaUrl = "https://wr-meta.com/champion/poppy/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/poppy/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/poppy"
        ),
        Champion(
            id = "pyke",
            name = "Pyke",
            nameEn = "",
            namePt = "",
            title = "el Destripador de los Puertos",
            titleEn = "",
            titlePt = "",
            ddragonId = "Pyke",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Pyke.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "C",
            winrate = 48.64,
            pickRate = 6.15,
            banRate = 2.59,
            damageType = DamageType.PHYSICAL,
            summary = "A Pyke, un conocido arponero de los muelles del matadero de Aguas Estancadas, le esperaba la muerte en el estÃ³mago de una gigantesca criatura marina... y sin embargo, regresÃ³. Desde entonces acecha en los frÃ­os y hÃºmedos callejones y caminos de la que...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Pyke en SUPPORT. Coordina el uso de su Muerte de las profundidades para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Promesa de Caballero", "Convergencia de Zeke", "Relicario de los Solari de Hierro"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3109.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3050.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3190.png"),
            situationalItems = listOf("Cota de Espinas", "Protector PÃ©treo"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3193.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "BendiciÃ³n de los ahogados", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/PykePassive.png", description = "Cuando Pyke se oculta de los enemigos, regenera el daÃ±o que haya sufrido recientemente de campeones. AdemÃ¡s, en vez de obtener vida mÃ¡xima extra de cualquier tipo, gana DA adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "EspetÃ³n de huesos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeQ.png", description = "Pyke apuÃ±ala a un enemigo que tenga delante o lo arrastra hacia Ã©l.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "InmersiÃ³n espectromarina", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeW.png", description = "Pyke entra en estado de camuflaje y obtiene una buena cantidad de velocidad de movimiento que disminuye a lo largo del tiempo.", cooldown = "14/13/12/11/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Corriente fantasma", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeE.png", description = "Pyke se desliza y deja un espÃ­ritu tras Ã©l que, cuando vuelve a su cuerpo, aturde a los campeones enemigos que encuentra a su paso.", cooldown = "15/14/13/12/11s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Muerte de las profundidades", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PykeR.png", description = "Pyke aparece junto a enemigos con poca vida y los ejecuta, lo que le permite lanzar de nuevo su hechizo y otorgar oro adicional al aliado que le asista.", cooldown = "100/85/70s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/pyke",
            wrMetaUrl = "https://wr-meta.com/champion/pyke/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/pyke/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/pyke"
        ),
        Champion(
            id = "rakan",
            name = "Rakan",
            nameEn = "",
            namePt = "",
            title = "El Encantador",
            titleEn = "",
            titlePt = "",
            ddragonId = "Rakan",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rakan.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 49.95,
            pickRate = 2.52,
            banRate = 0.41,
            damageType = DamageType.MAGIC,
            summary = "Con un carÃ¡cter tan impulsivo como encantador, Rakan es un infame alborotador vastaya y el mejor bailarÃ­n de batalla de la historia tribal de Lhotlan. Para los humanos de las montaÃ±as de Jonia, su nombre siempre ha sido sinÃ³nimo de festivales...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rakan en SUPPORT. Coordina el uso de su La premura para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Plumas encantadas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Rakan_P.png", description = "Rakan obtiene un escudo de forma periÃ³dica.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "PÃ©ndola reluciente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanQ.png", description = "Lanza una pluma mÃ¡gica que inflige daÃ±o mÃ¡gico. Al golpear a un campeÃ³n o a un monstruo Ã©pico, Rakan puede curar a sus aliados.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Entrada grandiosa", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanW.png", description = "Se desliza hacia una ubicaciÃ³n y lanza por los aires a los enemigos cercanos al llegar.", cooldown = "16/14.5/13/11.5/10s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Danza de batalla", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanE.png", description = "Vuela hacia un campeÃ³n aliado y le otorga un escudo. Se puede lanzar de nuevo sin coste alguno durante un periodo corto de tiempo.", cooldown = "0s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "La premura", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RakanR.png", description = "Obtiene velocidad de movimiento y hechiza e inflige daÃ±o mÃ¡gico a los enemigos que toque.", cooldown = "130/110/90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rakan",
            wrMetaUrl = "https://wr-meta.com/champion/rakan/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rakan/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rakan"
        ),
        Champion(
            id = "rammus",
            name = "Rammus",
            nameEn = "",
            namePt = "",
            title = "El Armadurillo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Rammus",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rammus.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 53.92,
            pickRate = 1.98,
            banRate = 1.18,
            damageType = DamageType.MAGIC,
            summary = "Idealizado por muchos, ignorado por otros, inexplicable para todos, Rammus, el extraÃ±o ser, es todo un enigma. Protegido por un armazÃ³n de pinchos, inspira teorÃ­as cada vez mÃ¡s absurdas sobre sus orÃ­genes allÃ¡ donde va. Estas pasan por considerarlo un...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rammus en JUNGLE. Coordina el uso de su Golpe veloz para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "ArmazÃ³n de pinchos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Armordillo_ScavengeArmor.png", description = "Los ataques bÃ¡sicos de Rammus infligen daÃ±o mÃ¡gico adicional que progresa con su armadura.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Bola de poder", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PowerBall.png", description = "Rammus se lanza como una bola a gran velocidad contra sus enemigos, infligiÃ©ndoles daÃ±o y ralentizando a los afectados por el impacto.", cooldown = "16/13.5/11/8.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "PosiciÃ³n defensiva", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/DefensiveBallCurl.png", description = "Rammus se sitÃºa en posiciÃ³n defensiva, lo que aumenta drÃ¡sticamente su armadura y su resistencia mÃ¡gica, asÃ­ como el poder de ArmazÃ³n de pinchos; ademÃ¡s, devuelve el daÃ±o a los enemigos que lo golpean con ataques bÃ¡sicos.", cooldown = "7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "ProvocaciÃ³n frenÃ©tica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PuncturingTaunt.png", description = "Rammus provoca a un monstruo o a un campeÃ³n enemigo para que se lance a lo loco contra Ã©l. AdemÃ¡s, obtiene velocidad de ataque durante un periodo corto, pero esta bonificaciÃ³n se ve prolongada al tener activo cualquiera de sus otros hechizos.", cooldown = "12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Golpe veloz", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Tremors2.png", description = "Rammus salta y golpea una ubicaciÃ³n objetivo, lo que inflige daÃ±o mÃ¡gico y ralentiza a los enemigos. Si se lanza mientras Rammus usa Bola de poder, Rammus tambiÃ©n lanza por los aires a los enemigos que se encuentren cerca del centro.", cooldown = "90s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rammus",
            wrMetaUrl = "https://wr-meta.com/champion/rammus/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rammus/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rammus"
        ),
        Champion(
            id = "rell",
            name = "Rell",
            nameEn = "",
            namePt = "",
            title = "la Dama de Hierro",
            titleEn = "",
            titlePt = "",
            ddragonId = "Rell",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rell.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "C",
            winrate = 51.88,
            pickRate = 2.38,
            banRate = 1.27,
            damageType = DamageType.MAGIC,
            summary = "Rell, producto de crueles experimentos a manos de la Rosa Negra, es ahora una intrÃ©pida arma viviente decidida a hacer caer Noxus. Su infancia estuvo marcada por la desdicha y el horror, pues se vio sometida a espeluznantes tratamientos para...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rell en SUPPORT. Coordina el uso de su Tormenta magnÃ©tica para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Rompemoldes", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/RellP.png", description = "Los ataques y habilidades de Rell roban armadura y resistencia mÃ¡gica al impactar.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Golpe demoledor", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellQ.png", description = "Rell inflige daÃ±o mÃ¡gico a unidades en una lÃ­nea, lo que rompe sus escudos y los aturde.", cooldown = "11/10.5/10/9.5/9s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Ferromancia - CaÃ­da", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellW_Dismount.png", description = "Montada: Rell se desmonta y cae con fuerza con su armadura, lanza por los aires a los enemigos y obtiene un gran escudo. Cuando estÃ¡ desmontada obtiene armadura, resistencia mÃ¡gica, velocidad de ataque y alcance de ataque, pero se ve ralentizada.Desmontada: Rell forma su montura, obtiene una mejora de velocidad y lanza por los aires al siguiente enemigo al que ataca.", cooldown = "11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Justa", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellE.png", description = "Pasiva: Rell gana velocidad de movimiento fuera de combate.Activa: Rell y un aliado obtienen velocidad de movimiento, que se duplica cuando avanzan el uno hacia el otro y hacia enemigos. Su prÃ³ximo ataque explotarÃ¡ e infligirÃ¡ daÃ±o mÃ¡gico.", cooldown = "15s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Tormenta magnÃ©tica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RellR.png", description = "Rell genera una furiosa explosiÃ³n magnÃ©tica y atrae violentamente a los enemigos cercanos. DespuÃ©s, Rell atrae de forma constante a los enemigos cercanos hacia ella durante un breve periodo de tiempo, lo que inflige daÃ±o mÃ¡gico a lo largo del tiempo.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rell",
            wrMetaUrl = "https://wr-meta.com/champion/rell/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rell/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rell"
        ),
        Champion(
            id = "renekton",
            name = "Renekton",
            nameEn = "",
            namePt = "",
            title = "El Carnicero de las Arenas",
            titleEn = "",
            titlePt = "",
            ddragonId = "Renekton",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Renekton.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "C",
            winrate = 48.16,
            pickRate = 5.5,
            banRate = 1.1,
            damageType = DamageType.PHYSICAL,
            summary = "Renekton es una terrorÃ­fica criatura Ascendida movida por la ira y procedente de los desiertos abrasadores de Shurima. En su dÃ­a fue el guerrero mÃ¡s admirado del imperio, un lÃ­der que condujo a los ejÃ©rcitos de la naciÃ³n a incontables victorias. Sin...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Renekton en TOP. Coordina el uso de su Dominus para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Reino de la ira", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Renekton_Passive.png", description = "Los ataques de Renekton generan furia que aumenta cuando tiene poca vida. Esta furia puede potenciar sus habilidades con efectos adicionales.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Preferencia por el dÃ³cil", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RenektonCleave.png", description = "Renekton blande su hoja, infligiendo una cantidad moderada de daÃ±o fÃ­sico a todos los objetivos que lo rodean y recibe una curaciÃ³n equivalente a una pequeÃ±a parte del daÃ±o infligido. Si tiene mÃ¡s de 50 puntos de furia, aumentan su daÃ±o y su curaciÃ³n.", cooldown = "7s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Depredador implacable", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RenektonPreExecute.png", description = "Renekton da dos tajos a su objetivo, lo que le inflige una cantidad moderada de daÃ±o fÃ­sico y lo aturde durante 0,75 s. Si Renekton tiene mÃ¡s de 50 puntos de furia, da tres tajos a su objetivo, con lo que destruye los escudos que lo protejan, le inflige mucho daÃ±o fÃ­sico y lo aturde durante 1,5 s.", cooldown = "16/14/12/10/8s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Cortar y trocear", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RenektonSliceAndDice.png", description = "Renekton se arroja hacia la direcciÃ³n marcada, causando daÃ±o a todas las unidades que se encuentre en el camino. Si estÃ¡ potenciado, Renekton inflige daÃ±o adicional y reduce la armadura de los enemigos impactados.", cooldown = "16/14.5/13/11.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Dominus", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RenektonReignOfTheTyrant.png", description = "Renekton se transforma en tirano, ganando vida adicional y causando daÃ±o a los enemigos que lo rodean. Mientras estÃ¡ en esta forma, Renekton gana furia periÃ³dicamente.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/renekton",
            wrMetaUrl = "https://wr-meta.com/champion/renekton/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/renekton/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/renekton"
        ),
        Champion(
            id = "rengar",
            name = "Rengar",
            nameEn = "",
            namePt = "",
            title = "El Acechador Orgulloso",
            titleEn = "",
            titlePt = "",
            ddragonId = "Rengar",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rengar.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "D",
            winrate = 48.47,
            pickRate = 1.53,
            banRate = 1.22,
            damageType = DamageType.PHYSICAL,
            summary = "Rengar es un feroz cazador de trofeos vastaya que vive por el placer de perseguir y asesinar criaturas peligrosas. Explora el mundo en busca de las bestias mÃ¡s aterradoras, pero, ante todo, quiere encontrar alguna pista que lo lleve hasta Kha'Zix, la...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rengar en JUNGLE. Coordina el uso de su EmociÃ³n por la cacerÃ­a para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Fuerza de la Trinidad", "Espada Fantasma de Youmuu", "Filo de la Noche"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3142.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3814.png"),
            situationalItems = listOf("Rencor de Serylda", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6694.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Depredador invisible", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Rengar_P.png", description = "Cuando estÃ¡ en la maleza, Rengar se abalanza sobre su objetivo con su ataque bÃ¡sico.Rengar genera ferocidad cuando lanza una habilidad. Al llegar al mÃ¡ximo de ferocidad, su siguiente habilidad estÃ¡ potenciada.Al asesinar campeones enemigos, el Collar dientehueso de Rengar obtiene trofeos que otorgan daÃ±o de ataque adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Fiereza", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RengarQ.png", description = "El siguiente ataque de Rengar apuÃ±ala brutalmente a su objetivo y le inflige daÃ±o adicional.Efecto de ferocidad: inflige daÃ±o aumentado y otorga velocidad de ataque.", cooldown = "0.25s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Rugido de batalla", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RengarW.png", description = "Rengar profiere un rugido de batalla que daÃ±a a los enemigos y le cura parte del daÃ±o que ha recibido recientemente.Efecto de Ferocidad: ademÃ¡s, elimina los efectos de control de adversario.", cooldown = "0.25s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Golpe bola", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RengarE.png", description = "Rengar lanza una boleadora que ralentiza durante unos momentos al primer objetivo al que alcanza.Efecto de Ferocidad: inmoviliza al objetivo.", cooldown = "0.25s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "EmociÃ³n por la cacerÃ­a", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RengarR.png", description = "Los instintos de depredador de Rengar toman el control y, ademÃ¡s de camuflarlo, le revelan la presencia del campeÃ³n mÃ¡s prÃ³ximo en una zona de amplio radio. Durante EmociÃ³n por la cacerÃ­a, Rengar aumenta su velocidad de movimiento y puede abalanzarse sobre los enemigos aunque no estÃ© en la maleza y reducir su armadura cuando lo hace.", cooldown = "110/100/90s"),
        ),
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rengar",
            wrMetaUrl = "https://wr-meta.com/champion/rengar/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rengar/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rengar"
        ),
        Champion(
            id = "riven",
            name = "Riven",
            nameEn = "",
            namePt = "",
            title = "La Exiliada",
            titleEn = "",
            titlePt = "",
            ddragonId = "Riven",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Riven.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "D",
            winrate = 45.46,
            pickRate = 2.79,
            banRate = 0.77,
            damageType = DamageType.PHYSICAL,
            summary = "AntaÃ±o maestra de la espada de las huestes de Noxus, Riven es una expatriada en la tierra que previamente habÃ­a tratado de conquistar. Fue ascendiendo de rango gracias a la fuerza de su convicciÃ³n y a su brutal eficiencia, y fue recompensada con una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Riven en TOP. Coordina el uso de su Hoja del exilio para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Hoja rÃºnica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/RivenRunicBlades.png", description = "Las habilidades de Riven cargan su espada y sus ataques bÃ¡sicos gastan cargas para infligir daÃ±o adicional.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Alas rotas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RivenTriCleave.png", description = "Riven lanza una serie de golpes. Esta habilidad puede reactivarse tres veces en poco tiempo. El tercer impacto harÃ¡ retroceder a los enemigos cercanos.", cooldown = "13s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Estallido de ki", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RivenMartyr.png", description = "Riven emite un Estallido de ki que daÃ±a y aturde a los enemigos cercanos.", cooldown = "11/10/9/8/7s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Valor", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RivenFeint.png", description = "Riven avanza una corta distancia y bloquea el daÃ±o que recibe.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Hoja del exilio", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RivenFengShuiEngine.png", description = "Riven potencia su fiel arma con energÃ­a que aumenta su daÃ±o de ataque y alcance. AdemÃ¡s, mientras dure la activaciÃ³n, obtiene la capacidad de usar una vez Cuchillada de viento, un potente ataque a distancia.", cooldown = "120/90/60s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/riven",
            wrMetaUrl = "https://wr-meta.com/champion/riven/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/riven/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/riven"
        ),
        Champion(
            id = "rumble",
            name = "Rumble",
            nameEn = "",
            namePt = "",
            title = "La Amenaza MecÃ¡nica",
            titleEn = "",
            titlePt = "",
            ddragonId = "Rumble",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Rumble.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.JUNGLE),
            tier = "C",
            winrate = 49.7,
            pickRate = 2.09,
            banRate = 0.53,
            damageType = DamageType.MAGIC,
            summary = "Rumble es un joven inventor con temperamento. Utilizando nada mÃ¡s que sus propias manos y un montÃ³n de chatarra, el yordle peleÃ³n construyÃ³ un traje mecha colosal equipado con un arsenal de arpones electrificados y cohetes incendiarios. Aunque los demÃ¡s...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Rumble en TOP. Coordina el uso de su Equilibrador para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "TitÃ¡n del desguace", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Rumble_JunkyardTitan1.png", description = "Cada hechizo que lanza Rumble le otorga Calor. Cuando alcanza el 50% de Calor, entra en la Zona de riesgo, lo que otorga efectos adicionales a todas sus habilidades bÃ¡sicas. Cuando alcanza el 100% de Calor, comienza a sobrecalentarse, lo que proporciona velocidad de ataque adicional y daÃ±o adicional a sus ataques bÃ¡sicos, pero tambiÃ©n le impide lanzar hechizos durante unos segundos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Escupellamas", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RumbleFlameThrower.png", description = "Rumble incinera a los oponentes que estÃ©n ante Ã©l, infligiendo daÃ±o mÃ¡gico en un cono durante 3 s. Dentro de la Zona de riesgo, el daÃ±o se incrementa.", cooldown = "10/9/8/7/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Escudo de restos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RumbleShield.png", description = "Rumble activa un escudo que lo protege del daÃ±o y le garantiza un impulso de velocidad. Dentro de la Zona de riesgo, la fuerza del escudo y la bonificaciÃ³n de velocidad se incrementan.", cooldown = "6s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "ArpÃ³n elÃ©ctrico", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RumbleGrenade.png", description = "Rumble lanza un arpÃ³n que electrocuta al objetivo, lo que inflige daÃ±o mÃ¡gico, ralentiza su velocidad de movimiento y reduce su resistencia mÃ¡gica. Rumble puede llevar 2 arpones al mismo tiempo. Dentro de la Zona de riesgo, el daÃ±o y el porcentaje de ralentizaciÃ³n aumentan.", cooldown = "0.5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Equilibrador", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RumbleCarpetBomb.png", description = "Rumble lanza una salva de misiles, creando un muro de llamas que inflige daÃ±o y ralentiza a los enemigos.", cooldown = "130/105/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rumble",
            wrMetaUrl = "https://wr-meta.com/champion/rumble/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/rumble/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/rumble"
        ),
        Champion(
            id = "ryze",
            name = "Ryze",
            nameEn = "",
            namePt = "",
            title = "El Hechicero RÃºnico",
            titleEn = "",
            titlePt = "",
            ddragonId = "Ryze",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Ryze.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "C",
            winrate = 48.22,
            pickRate = 3.19,
            banRate = 2.23,
            damageType = DamageType.MAGIC,
            summary = "Ryze es considerado uno de los hechiceros con mÃ¡s experiencia de Runaterra. El archimago ancestral y gruÃ±Ã³n lleva sobre sus hombros una pesada carga. Con su constituciÃ³n sin lÃ­mites y su inmenso poder arcano, Ryze se pasa la vida buscando...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Ryze en MID. Coordina el uso de su DistorsiÃ³n de reinos para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Electrocutar (DominaciÃ³n)",
            runeTreeDetails = "DominaciÃ³n: Impacto Repentino â€¢ ColecciÃ³n de Globos Oculares â€¢ Cazador Ingenioso â€¢ ConcentraciÃ³n",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "MaestrÃ­a arcana", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Ryze_P.png", description = "Los hechizos de Ryze infligen daÃ±o adicional segÃºn su manÃ¡ adicional. AdemÃ¡s, obtiene un porcentaje que se aÃ±ade a su manÃ¡ mÃ¡ximo segÃºn su poder de habilidad.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Descarga elÃ©ctrica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeQWrapper.png", description = "De forma pasiva, las demÃ¡s habilidades bÃ¡sicas de Ryze reinician el enfriamiento de Descarga elÃ©ctrica y cargan una runa. Si Ryze lanza Descarga elÃ©ctrica con dos runas cargadas, obtiene un aumento de velocidad de movimiento durante un breve periodo.Con el lanzamiento, Ryze libera una carga de energÃ­a pura en lÃ­nea recta que daÃ±a al primer enemigo al que alcanza. Si el objetivo estÃ¡ marcado con Flujo, Descarga elÃ©ctrica infligirÃ¡ daÃ±o adicional y rebotarÃ¡ en los enemigos cercanos que tambiÃ©n tengan una marca de Flujo.", cooldown = "5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "PrisiÃ³n rÃºnica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeW.png", description = "Ryze atrapa a una unidad enemiga objetivo en una prisiÃ³n de runas que le inflige daÃ±o y lo ralentiza. Si el objetivo estÃ¡ marcado con Flujo, quedarÃ¡ inmovilizado.", cooldown = "11/10.5/10/9.5/9s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Tormenta elÃ©ctrica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeE.png", description = "Ryze lanza un orbe de poder mÃ¡gico puro que daÃ±a a un enemigo y debilita a los demÃ¡s que estÃ©n cerca. Los hechizos de Ryze tienen efectos adicionales contra enemigos debilitados.", cooldown = "3.5/3.25/3/2.75/2.5s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "DistorsiÃ³n de reinos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/RyzeR.png", description = "De forma pasiva, Descarga elÃ©ctrica inflige mÃ¡s daÃ±o a los objetivos marcados con Flujo.Con el lanzamiento, Ryze crea un portal a una ubicaciÃ³n cercana. Tras unos segundos, los aliados que estÃ©n dentro del portal se teleportan a la ubicaciÃ³n objetivo.", cooldown = "180/160/140s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ryze",
            wrMetaUrl = "https://wr-meta.com/champion/ryze/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/ryze/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/ryze"
        ),
        Champion(
            id = "samira",
            name = "Samira",
            nameEn = "",
            namePt = "",
            title = "la Rosa del Desierto",
            titleEn = "",
            titlePt = "",
            ddragonId = "Samira",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Samira.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 50.69,
            pickRate = 9.22,
            banRate = 5.25,
            damageType = DamageType.PHYSICAL,
            summary = "Samira mira de cara a la muerte con una confianza fÃ©rrea y busca emociones extremas allÃ¡ donde va. Tras la destrucciÃ³n de su hogar en Shurima cuando era una niÃ±a, Samira encontrÃ³ su verdadera vocaciÃ³n en Noxus, donde se ganÃ³ la reputaciÃ³n de ser una...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Samira en ADC. Coordina el uso de su Gatillo infernal para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "CompÃ¡s Letal (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Presteza â€¢ Golpe de Gracia â€¢ Cazador de Titanes",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Impulso temerario", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/SamiraP.png", description = "Samira realiza un combo cuando golpea con ataques o habilidades distintas al golpe anterior. Los ataques cuerpo a cuerpo de Samira infligen daÃ±o mÃ¡gico adicional. Los ataques de Samira contra enemigos afectados por inmovilizaciones harÃ¡n que se deslice hasta que estÃ©n a su alcance. Si el enemigo ha sido lanzado por los aires, lo mantendrÃ¡ en el aire unos instantes mÃ¡s.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Clase", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SamiraQ.png", description = "Samira realiza un disparo o ataca con su espada, lo que inflige daÃ±o. Si se lanza durante Carrera salvaje, golpea a todos los enemigos que haya en su camino al completarse.", cooldown = "6/5/4/3/2s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Giro de espada", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SamiraW.png", description = "Samira golpea a su alrededor, inflige daÃ±o a los enemigos y destruye los proyectiles enemigos.", cooldown = "30/28/26/24/22s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Carrera salvaje", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SamiraE.png", description = "Samira se desliza a travÃ©s de un enemigo (incluye estructuras), corta a los enemigos a los que atraviesa y obtiene velocidad de ataque. Asesinar a un campeÃ³n enemigo reinicia el enfriamiento de esta habilidad.", cooldown = "20/18/16/14/12s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Gatillo infernal", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SamiraR.png", description = "Samira desata con sus armas un torrente de disparos con los que daÃ±a a todos los enemigos que se encuentren a su alrededor.", cooldown = "5s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/samira",
            wrMetaUrl = "https://wr-meta.com/champion/samira/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/samira/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/samira"
        ),
        Champion(
            id = "senna",
            name = "Senna",
            nameEn = "",
            namePt = "",
            title = "la Redentora",
            titleEn = "",
            titlePt = "",
            ddragonId = "Senna",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Senna.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "B",
            winrate = 52.46,
            pickRate = 4.9,
            banRate = 0.84,
            damageType = DamageType.PHYSICAL,
            summary = "Maldita desde que era apenas una niÃ±a y perseguida por la Niebla Negra, Senna se uniÃ³ a una orden sagrada conocida como los Centinelas de la Luz y pasÃ³ aÃ±os luchando sin descanso hasta que fue asesinada y encerrada en el interior de la linterna del...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Senna en ADC. Coordina el uso de su Sombra del amanecer para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "CompÃ¡s Letal (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Leyenda: Presteza â€¢ Golpe de Gracia â€¢ Cazador de Titanes",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Filo del Infinito", "Hoja del Rey Arruinado", "CaÃ±Ã³n de Fuego RÃ¡pido"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3031.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3153.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3094.png"),
            situationalItems = listOf("Recordatorio Mortal", "Ãngel GuardiÃ¡n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3033.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3026.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "AbsoluciÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Senna_Passive.png", description = "Cuando mueren unidades cerca de Senna, la Niebla Negra atrapa sus almas en intervalos periÃ³dicos. Senna puede atacar a estas almas para liberarlas y absorber la niebla que las retiene. La Niebla Negra aumenta el poder de su caÃ±Ã³n reliquia: daÃ±o de ataque, alcance y probabilidad de impacto crÃ­tico aumentados. Los ataques del caÃ±Ã³n reliquia de Senna tardan mÃ¡s en dispararse, infligen daÃ±o adicional y le otorgan una parte de la velocidad de movimiento de su objetivo durante un breve periodo de tiempo.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Oscuridad lacerante", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaQ.png", description = "Senna lanza un rayo de luz y sombra de los caÃ±ones gemelos de su arma reliquia que atraviesa la zona objetivo, cura a los aliados e inflige daÃ±o a los enemigos.", cooldown = "15s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Ãšltimo abrazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaW.png", description = "Senna lanza una ola de Niebla Negra. Si golpea a un enemigo, se queda enganchada a Ã©l, lo inmoviliza y, poco despuÃ©s, inmoviliza a todas las unidades cercanas.", cooldown = "11s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "MaldiciÃ³n de la Niebla Negra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaE.png", description = "Senna reÃºne la niebla que ha acumulado en su arma e invoca una tormenta a su alrededor, entregÃ¡ndose a la oscuridad y convirtiÃ©ndose en un espectro. Los aliados que entran en la zona quedan camuflados y adoptan la apariencia de espectros mientras estÃ¡n envueltos en niebla. Los espectros aumentan su velocidad de movimiento, no se pueden seleccionar como objetivos y ocultan su identidad.", cooldown = "26/24.5/23/21.5/20s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Sombra del amanecer", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SennaR.png", description = "Senna invoca la fuerza de las reliquias de los Centinelas caÃ­dos y su caÃ±Ã³n despliega una mezcla sagrada de luz y oscuridad. Entonces, dispara un rayo global que otorga un escudo a sus aliados e inflige daÃ±o a los enemigos que estÃ©n en el centro del impacto.", cooldown = "140/120/100s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/senna",
            wrMetaUrl = "https://wr-meta.com/champion/senna/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/senna/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/senna"
        ),
        Champion(
            id = "seraphine",
            name = "Seraphine",
            nameEn = "",
            namePt = "",
            title = "la Cantante SoÃ±adora",
            titleEn = "",
            titlePt = "",
            ddragonId = "Seraphine",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Seraphine.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.ADC),
            tier = "A",
            winrate = 50.44,
            pickRate = 11.99,
            banRate = 2.66,
            damageType = DamageType.MAGIC,
            summary = "Seraphine, de padres zaunitas, naciÃ³ en Piltover y es capaz de escuchar las almas de los demÃ¡s. El mundo le canta y ella le devuelve la canciÃ³n. Aunque esos sonidos le resultaban abrumadores cuando era pequeÃ±a, ahora le sirven de inspiraciÃ³n, y...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Seraphine en SUPPORT. Coordina el uso de su Bis para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Aery (BrujerÃ­a)",
            runeTreeDetails = "BrujerÃ­a: Banda de ManÃ¡ â€¢ Trascendencia â€¢ PirolÃ¡ser â€¢ Fuente de Vida",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Eco de Luden", "Sombrero Mortal de Rabadon", "BÃ¡culo del VacÃ­o"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3285.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3089.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3135.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "MorellonomicÃ³n"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3165.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Presencia escÃ©nica", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Seraphine_Passive.png", description = "Cada tercera habilidad bÃ¡sica que utilice Seraphine se lanzarÃ¡ dos veces. AdemÃ¡s, lanzar hechizos cerca de los aliados otorga daÃ±o mÃ¡gico adicional y alcance en su siguiente ataque bÃ¡sico.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Nota alta", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineQ.png", description = "Seraphine inflige daÃ±o en un Ã¡rea.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Sonido envolvente", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineW.png", description = "Seraphine otorga un escudo y acelera a los aliados cercanos. Si ella ya tiene un escudo, tambiÃ©n cura a los aliados cercanos.", cooldown = "22s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "ClÃ­max musical", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineE.png", description = "Seraphine inflige daÃ±o e inmoviliza a los enemigos en una lÃ­nea.", cooldown = "11/10.5/10/9.5/9s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Bis", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SeraphineR.png", description = "Seraphine inflige daÃ±o y hechiza a los enemigos golpeados. Restaura el alcance con cada campeÃ³n aliado o enemigo que alcanza.", cooldown = "160/140/120s"),
        ),
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/seraphine",
            wrMetaUrl = "https://wr-meta.com/champion/seraphine/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/seraphine/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/seraphine"
        ),
        Champion(
            id = "sett",
            name = "Sett",
            nameEn = "",
            namePt = "",
            title = "el Jefe",
            titleEn = "",
            titlePt = "",
            ddragonId = "Sett",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Sett.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S",
            winrate = 50.93,
            pickRate = 10.07,
            banRate = 1.62,
            damageType = DamageType.PHYSICAL,
            summary = "Sett es una prominente figura en los emergentes cÃ­rculos criminales jonios, que asegurÃ³ su posiciÃ³n al inicio de la guerra con Noxus. A pesar de sus humildes comienzos como luchador en el foso de Navori, no tardÃ³ en labrarse una reputaciÃ³n con la ayuda...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Sett en TOP. Coordina el uso de su El gran espectÃ¡culo para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Conquistador (PrecisiÃ³n)",
            runeTreeDetails = "PrecisiÃ³n: Triunfo â€¢ Golpe de Gracia â€¢ Leyenda: Presteza â€¢ Sobrecrecimiento",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fuerza de la Trinidad", "Cuchilla Oscura", "Calibrador de Sterak"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3078.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3071.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3053.png"),
            situationalItems = listOf("Danza de la Muerte", "Cota de Espinas"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/6333.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3075.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Coraje del foso", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Sett_P.png", description = "Los ataques bÃ¡sicos de Sett alternan entre un puÃ±etazo con la izquierda y uno con la derecha. El puÃ±etazo con la derecha es ligeramente mÃ¡s fuerte y rÃ¡pido. Sett odia perder, por lo que gana regeneraciÃ³n de vida adicional en funciÃ³n de la vida que le falte.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Nudillos a punto", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SettQ.png", description = "Los siguientes dos ataques de Sett infligen daÃ±o adicional en funciÃ³n de la vida mÃ¡xima del objetivo. Sett tambiÃ©n obtiene velocidad de movimiento mientras se mueve hacia campeones enemigos.", cooldown = "9/8/7/6/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Trastazo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SettW.png", description = "Sett acumula de forma pasiva el daÃ±o que recibe como coraje. Con el lanzamiento, Sett gasta todo el coraje acumulado para obtener un escudo y da un puÃ±etazo en una zona, lo que inflige daÃ±o verdadero en el centro y daÃ±o fÃ­sico a los lados.", cooldown = "18/16.5/15/13.5/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Rompecaras", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SettE.png", description = "Sett atrae a todos los enemigos que estÃ¡n a ambos lados, los aturde y les inflige daÃ±o. Si solo habÃ­a enemigos en un lado, quedan ralentizados en vez de aturdidos.", cooldown = "16/14.5/13/11.5/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "El gran espectÃ¡culo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/SettR.png", description = "Sett levanta a un campeÃ³n enemigo y lo estampa contra el suelo, lo que inflige daÃ±o y ralentiza a todos los enemigos prÃ³ximos a la zona de impacto.", cooldown = "120/100/80s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/sett",
            wrMetaUrl = "https://wr-meta.com/champion/sett/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/sett/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/sett"
        ),
        Champion(
            id = "shen",
            name = "Shen",
            nameEn = "",
            namePt = "",
            title = "El Ojo del CrepÃºsculo",
            titleEn = "",
            titlePt = "",
            ddragonId = "Shen",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Shen.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.SUPPORT),
            tier = "S",
            winrate = 52.4,
            pickRate = 13.3,
            banRate = 7.3,
            damageType = DamageType.MAGIC,
            summary = "Shen, el Ojo del CrepÃºsculo, es el cabecilla de los sigilosos guerreros de Jonia conocidos como Kinkou. Su objetivo es mantenerse libre de las ataduras de cualquier emociÃ³n, prejuicio o ego, y avanza por el camino invisible del juicio desapasionado...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Shen en TOP. Coordina el uso de su Mantenerse unidos para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Barrera ki", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Shen_Passive.png", description = "Shen obtiene un escudo tras lanzar un hechizo. Si afecta a otros campeones, reduce el enfriamiento del efecto.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Asalto del crepÃºsculo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShenQ.png", description = "Shen recupera su espada espiritual para atacar con ella e infligir un daÃ±o basado en la vida mÃ¡xima del objetivo. Los ataques se verÃ¡n muy potenciados si la espada impacta en un campeÃ³n enemigo. AdemÃ¡s, todos los enemigos impactados se verÃ¡n ralentizados al intentar huir de Shen.", cooldown = "8/7.25/6.5/5.75/5s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Refugio espiritual", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShenW.png", description = "Se bloquean los ataques que pudiesen recibir Shen o sus aliados cerca de la espada espiritual.", cooldown = "18/16.5/15/13.5/12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Movimiento de sombra", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShenE.png", description = "Shen se abalanza en una direcciÃ³n y provoca a los enemigos que se encuentre a su paso.", cooldown = "18/16/14/12/10s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Mantenerse unidos", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShenR.png", description = "Shen protege del daÃ±o a un campeÃ³n aliado y poco despuÃ©s se teleporta hasta su ubicaciÃ³n.", cooldown = "200/180/160s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/shen",
            wrMetaUrl = "https://wr-meta.com/champion/shen/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/shen/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/shen"
        ),
        Champion(
            id = "shyvana",
            name = "Shyvana",
            nameEn = "",
            namePt = "",
            title = "La Medio DragÃ³n",
            titleEn = "",
            titlePt = "",
            ddragonId = "Shyvana",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Shyvana.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = emptyList(),
            tier = "A",
            winrate = 51.55,
            pickRate = 7.06,
            banRate = 1.46,
            damageType = DamageType.MAGIC,
            summary = "Shyvana es una criatura con la magia de un fragmento de runa incandescente alojada en lo mÃ¡s profundo de su corazÃ³n. Pese a que su aspecto mÃ¡s comÃºn es el de humana, su verdadera forma es la de una feroz dragona capaz de reducir a sus enemigos a cenizas...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Shyvana en JUNGLE. Coordina el uso de su Ascendencia de dragÃ³n para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Castigo", "Destello"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641", "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Furia de la hija del dragÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Shyvana_Passive.png", description = "Shyvana inflige daÃ±o adicional a los dragones y obtiene armadura y resistencia mÃ¡gica. Cuantos mÃ¡s dragones asesine su equipo, mayor serÃ¡ su bonificaciÃ³n de armadura y resistencia mÃ¡gica.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Mordisco doble", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShyvanaQ.png", description = "Shyvana golpea dos veces en su prÃ³ximo ataque y se otorga velocidad de ataque en los prÃ³ximos ataques. Los ataques bÃ¡sicos reducen el enfriamiento de Mordisco doble en 0,5 s.Forma de DragÃ³n: Mordisco doble golpea a todas las unidades que hay ante Shyvana.", cooldown = "8/7.5/7/6.5/6s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Quemado", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShyvanaW.png", description = "Shyvana se rodea de fuego, inflige daÃ±o mÃ¡gico cada segundo a los enemigos cercanos y se mueve mÃ¡s rÃ¡pido durante 3 s. Cuando Shyvana vuelve a alcanzar con sus ataques bÃ¡sicos a un enemigo con el efecto de Quemado aÃºn activo, recibe parte de ese daÃ±o de nuevo. La velocidad de movimiento se reduce mientras dura el hechizo. Los ataques bÃ¡sicos aumentan la duraciÃ³n de Quemado. Forma de dragÃ³n: Quemado aumenta su tamaÃ±o.", cooldown = "12s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Aliento de fuego", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShyvanaE.png", description = "Shyvana lanza una bola de fuego que inflige daÃ±o a todos los enemigos con los que se encuentra y deja cenizas que los marcan durante 5 s. Los ataques bÃ¡sicos de Shyvana contra los objetivos marcados infligen un porcentaje de la vida mÃ¡xima como daÃ±o al golpear.Forma de dragÃ³n: Aliento de fuego revienta al impactar o en la ubicaciÃ³n objetivo, lo que inflige daÃ±o adicional y abrasa el suelo durante un breve periodo de tiempo.", cooldown = "12/11/10/9/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "Ascendencia de dragÃ³n", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/ShyvanaR.png", description = "Shyvana se transforma en un dragÃ³n y vuela hacia un lugar objetivo. Los enemigos con los que se encuentre en su camino reciben daÃ±o y se ven empujados hacia su lugar objetivo.Shyvana obtiene furia de forma pasiva y 2 de furia con cada ataque bÃ¡sico.", cooldown = "0s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/shyvana",
            wrMetaUrl = "https://wr-meta.com/champion/shyvana/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/shyvana/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/shyvana"
        ),
        Champion(
            id = "singed",
            name = "Singed",
            nameEn = "",
            namePt = "",
            title = "El QuÃ­mico Loco",
            titleEn = "",
            titlePt = "",
            ddragonId = "Singed",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Singed.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "A+",
            winrate = 50.7,
            pickRate = 10.4,
            banRate = 14.1,
            damageType = DamageType.MAGIC,
            summary = "Singed es un alquimista zaunita de intelecto sin igual que se ha entregado en cuerpo y alma a sobrepasar los lÃ­mites del conocimiento, sin importarle el precio que tenga que pagar. Â¿Acaso su locura se rige por pautas? Sus brebajes no suelen fallar, pero...",
            advantageAgainst = listOf("Sion", "Jinx", "Lux"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Singed en TOP. Coordina el uso de su PociÃ³n de demencia para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Soberano GÃ©lido (Valor)",
            runeTreeDetails = "Valor: Fuente de Vida â€¢ Condicionamiento â€¢ Sobrecrecimiento â€¢ Golpe de Escudo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/veteranaftershock/veteranaftershock.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Creador de Grietas", "Ã‰gida de Fuego Solar", "Apariencia Espiritual"),
            coreItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/4633.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3068.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3065.png"),
            situationalItems = listOf("Reloj de Arena de Zhonya", "Presagio de Randuin"),
            situationalItemsIcons = listOf("https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3157.png", "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/item/3143.png"),
            skillOrder = "Habilidad 1 > Habilidad 2 > Habilidad 3 (Priorizar Definitiva)",
            skills = listOf(
            ChampionSkill(slot = "P", slotName = "Pasiva", name = "Estela nociva", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Singed_Passive.png", description = "Singed toma el rebufo de los campeones cercanos, obteniendo asÃ­ un aumento de la velocidad de movimiento al adelantarlos.", cooldown = ""),
            ChampionSkill(slot = "1", slotName = "Habilidad 1", name = "Rastro de veneno", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/PoisonTrail.png", description = "Deja un rastro de veneno tras Singed que daÃ±a a los enemigos.", cooldown = "0s"),
            ChampionSkill(slot = "2", slotName = "Habilidad 2", name = "Megaadhesivo", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MegaAdhesive.png", description = "Lanza un frasco de megaadhesivo al suelo que ralentiza y ancla a todos los enemigos que lo pisen.", cooldown = "17/16/15/14/13s"),
            ChampionSkill(slot = "3", slotName = "Habilidad 3", name = "Lanzar", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/Fling.png", description = "DaÃ±a a la unidad enemiga objetivo y la lanza al aire detrÃ¡s de Singed. AdemÃ¡s, si el objetivo cae sobre el Megaadhesivo, queda inmovilizado.", cooldown = "10/9.5/9/8.5/8s"),
            ChampionSkill(slot = "4", slotName = "Definitiva", name = "PociÃ³n de demencia", nameEn = "", namePt = "", iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/InsanityPotion.png", description = "Singed ingiere una potente pÃ³cima quÃ­mica que aumenta su capacidad de combate y hace que Rastro de veneno aplique heridas graves.", cooldown = "100s"),
        ),
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/singed",
            wrMetaUrl = "https://wr-meta.com/champion/singed/",
            wildRiftCoreUrl = "https://wildriftcore.com/es/champions/singed/",
            bestBuildWrUrl = "https://bestbuildwr.com/champion/singed"
        ),
        Champion(
            id = "sion",
            name = "Sion",
            nameEn = "",
            namePt = "",
            title = "El Coloso no Muerto",
            titleEn = "",
            titlePt = "",
            ddragonId = "Sion",
            avatarUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Sion.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "B",
            winrate = 50.53,
            pickRate = 1.65,
            banRate = 0.31,
            damageType = DamageType.PHYSICAL,
            summary = "Sion fue un hÃ©roe de guerra en el pasado y los noxianos lo veneraban porque habÃ­a arrebatado la vida de un rey demaciano con sus simples manos. No obstante, se le denegÃ³ su viaje al mÃ¡s allÃ¡, pues fue revivido para servir a su imperio incluso despuÃ©s de...",
            advantageAgainst = listOf("Jinx", "Lux", "Maestro Yi"),
            counteredBy = listOf("Fiora", "Vayne", "Morgana"),
            synergies = listOf("Yasuo", "Malphite", "Lulu"),
            tacticalAdvice = "Aprovecha el escalado y combos de Sion en TOP. Coordina el uso de su Embestida imparable para ganar ventajas en peleas grupales y objetivos de dragÃ³n/barÃ³n.",
            recommendedRunes = "Garras del Inmortal (Valor)",
            runeTreeDetails = "Valor: Demoler â€¢ Coraza Ã“sea â€¢ Sobrecrecimiento â€¢ Triunfo",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "IgniciÃ³n"),
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es", "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"),
            coreItems = listOf("Fxœì}ËrY’å~¾":Íz*Ë†	|‰R[Ï“bJª‘RQ™m5›´‹ÀT ¦ ÕÌrÖýšZ”å.—?™/?î÷Fâ‚P ŠUBY·’d¼ïËÝÏ=~<ÕÑåŒ´ã+çmäÞH¾Ùs¾9OÝkÏ§?¾ŽÝ4Rü'å{ÃHÂç_%:Rï¾ùãÞr
ÿsÃH¿Hô4~á†Aìü«ã{qòzüí7×I2‹Ÿôû£Q¤&aÐóµš¤:ûz¢ƒQÜsÃißýÁIÿ×÷¦“¾G7êî?:íÍ‚	ÞàKn2há&Ç‡|“Ò7Ç^’ªÄåó§¾ú©
²Ö}•ê(ÑÜaÂ¼ˆg^ â»î×ZSž¶Ñ”Çµ­ðŽ†Ëëh¤#zÕož«¡çc,9ç¿:ùoK¿:ß^F^yTä<Õc~‰w«þøMÍ­-°tðüZMgÔXW8éÛØ<ÿ’¾?ÿ¨¦šW1Ý˜þ˜?<óé±ÊÑºfj»G/7¿\&æ:á§ÈÇo›4ÝLÅôºEoúË¥übGäHÇnäÍÐã¸ýSÏÒÅ§#dJ/í9¸Ê‰µixSåP?ÌÂHùS$zÏ™é(tâÔ¹¥F•ÓF©KÿY|œÑ_ø¤žs•ÆŽJÔ_R;1ÝnšÎÍaìÌ?t0Ïƒ=Gôtñ‘ïcß›x4ßÕâ¯!ñ\–ôÉâwn:~&ÿï5Ò¾o4ubØ£/sÃÐ…¿Jƒ–ÆK}¿JýVG…Î{1)7¡
¼¿¤ž%i›Ï´ïs×ýú.ãþqU4QN8³&A‡¦¹úÿ=ú;µ•þ@Cx¨±†îq›*GQËÇ4õÔ›Ð<÷Ñ#ãÝF‡Ÿ|ê<;ˆŸ0B7Ñ!jëÄÃ@ ®SÇÈXXº›ò],@¸_¢¦Coñ‰†‘òyÐ›¿Ó8âk”iŒzÙ‘
èÕÓh„¡QîÆÁ~ÿqÿ´ÿ¨RY·ê{ô ±G
=ú<Œ‚ÇjÕMgþÛŠÎ¤Y4‹Â„îC_ Wé¤tRÑðwÕ/Kë5 5ÏLKW„U¤ÊS‡fÈ„^¼ÜÝ®Ž¨‡¨•óTt{~¸ŠuL†NV>Ï3s>ÍÍ³™ÇT4èaXØ²Ùofb¥×Žûƒ£þà°?8èköÜacÏzîM:¡q"]ÇïvÓ{+zG7O¹0qk¹×!¦"~
#¬˜.ÏM3i5ÄÌHÈ¡‹g‘7%SfZ>ŸIKë#/´ÔÇÔú*š*ÌLž«4’HI?ÑÐ‰¿G=5ÄoS²æI”Òh*Žh	Æ'Ì4f›s­\2J*¡¿Ò˜x¹4—yäà}í»Ó„MýDÑ¹|M×=,d3ð7|öM®·ø- ¼Õ|LÞ|¤–^½<h0Vú2á×2G¥!“ÛôÂˆ¹˜uœàáÞseèoÕôæCæÍê	/£FÁªÉJ‹Û]òn¤e‰í9—<ã],¨ä¶Ð,§yÈFÝ=¢uÔ•ÆNcžÒ41È²Æì5ûÔ!	kì[M7“¬o©cuyC%FÚö2Ü–qÈcÄ»Ñe4*<ëmòTzôhŸús¿²¿ÔŸ¥®õâ7*˜è]1V~¬ËGˆÂ ñ½ I¹tüWÏ½ñÆÉÔ¥^ûõ×_{8Ñá1æîš¤ÞH÷cêƒ’×÷kôJ'ª|‹è»)ýU:ÚA¾¸_¾Ú¼ÆyX}ó
ˆUøF:Îî×Þãõ{r4Fÿ•î…#Cù5ª¾Ó7u-l'Î²ë¡µ¿¡¥Ü‹JÏ¶³æªá˜3•vþ,H¼ÄçÛ½TÎ•&ËÙó=­WïÕ_û>Rû39_Œš^[ÝÒ³Ê-yŸ)µ2ß]fõÒ°z«hþ&ä}I¦?öÎžž—"íÂBÈ™ˆ0ÈŸJæ/i.}ûÇòçJ`ó}eœ4¥ñ”£ÓÞàqé5<÷Ý9zØ;=-*˜cû½ýÒ…äµ«‰~;ŸáðÓì—Þåó?_½8?{YúŠtŠ¯Íš›|^üÕ˜®¤ev˜Æ.|dîêDÓRÄ«‰«f­ä4¢…xk-…üÙz÷´ØÐÂE‹&5AÂ~ÏÕuŠ¦¥0
o±ðCKâHÇ)]Ãh*8J?Ð&ŠN§›Â«šè©"‹u­,ÐKáÉôƒäê°mˆ©¥xhÆâYÁ§öízÉÞWÐëõÊCltKN25ÛÙDydoáó/5Î7ò‚÷øïËô}ÅHi9'ùý¼pá©2ò³šÈ¿
#¼o%¦ÃÑÄÓÅ ýÏ*NC¾Fù³kO€€—©Ÿ–/NàHºÊ?Ýz.OÕ3òDo5yt™.åKSÑÌ†ì‡˜^àäJ†!ùîŸžÆÜadåÏÄ*|PÒŠxïˆì5ÒbƒBfRÓO“()ŸcÊñ30=ÉÜô‡äp+µ7½pJFp¤GoÒ€¿ü›ó0 èÝM¶}2 1,V9¼è‚·‘ÖOia÷8Îÿ&?ù	P©4‡Îÿû_ÿ×yú36³Ï"ö”ð·—zŽñö„#-Æúƒüõ*Ò=p›)ÌvØ°@Ð£_TÝ‹HýŠugš’ï27ëuuß§ù'ý™O>/™ŠÈ}7Ôßù¡ÿÝ„ÜïF4Nû?*¿?ÒcE¼;èSxþî;s˜¬K2§¶íÏäûhsÑH.FùO5ëY¡y¯àÎ,aLøjšP/&Ç^AeHòUMHõ9²Íï<ÕBrÞyºè¤_Zûæ3õõ )y—íGúV¾Fè¿¹Ã=Øœƒ“ýGƒ“ƒÓÿ<SÉõwôÕcïý¿ê¸@ÝûÙãþø¨%_lÅÃ÷ÉÛÙ?<<:nÄ(—æ¶/1Ì‹€=VnÍçá`oôÜ9‹¢ÔÃÚ$Pèâ¯Æ÷ú!Õ¼$¥8ô°$spÜøøèžpèÆÍ’0òB‡OZÖñ
‹ÿMî¦ï<K­[tˆRc¶Ñ'ÿ €è+‚^çf((Ù+ƒ6e0iáü®€}ZÎo5"ÆÔ›–Çf”Þš…ž‚-»h-vÂ¥@æ¦3éj®mB“Ù¾Éþs¡-5U#2‰fTQ„¥ÌÄúp‰°¯H­¡L°*hy…I(‘°72ç‹?H®WR+÷û{Ç€	èßu¡‚õpÁ7ÔBîµN:jÅHð%#³©"ÊÏaôÌó2Ã4X“þÍQó9Ùñaˆ¨žNÍ+¼e§V°C@#ÉòÃ	CßTAšVÑ¼A9éí¯Éãô>„[]
-ß çGZ€<r;#à¤J 2ôêÉñÅïþ|}•Ìu:˜Ð> %c¸„Òc=2é®7ÔàÌ9vahuåÊB]hñ3³‡•;åà¨p@sá`Ð`fZÅÏ2O¾£®i„Í0ôéûÑ-X\°ßàYx
Á	¢Û¹ƒX"LB³9âä”›–Ê™ —¥©Ùí†]-=çL0XÙî¨LGÙÿ
Ô>¦HTîËƒâ¼ãtuã@ ²Óu!²*¶ŒÕ@h›BdU$å>]Ý"HVs·P2|Ó½a²w*¢»	(k<º!TF®)E“4ÊƒbL•4k¼r@ùå@Yík·•Éý×ËþôÓÏ^^Ü—4»èíëËàìi3pvÒ;=iÎ½ã8VÎN-ggŽi =ìCZ;°ÜGÊ}§#²Kc8Rþïh'ò€Zâ‡Xî^¼O°/(Ž/hX|Æ)5Ö(Œ´YyhÕXËhR²|ý™Öµ·
›¼@6F{­fó‘·–Ð‚‘¢@8$N¦p×¦XZÜèg2
‘žð–€òbŸ,^Ï~™9òµl	¹‹S @CuÒ{«œ¯@3íDM$Ã¾	E»˜Îèêh‡¡9ÿPÚ9ÍK
1¤28m»Ú~ÿ¸5]Ä:9|dö¥pÝ: Zº#îˆ„_!‘ð¹¦¡“…r”Ò~&æj5‚ö²Y÷æ­¸cz,“‰Fä‡œùLŒD&ËÚ85óÉŽ‚±øOËÜ%XHÞÊHå¦ÓÔW€;ŒË#—–hiÇÞA‰Uå4,qñtúc4±¦a!Ÿ½M8¯ÚXN¿ÐZDàÒmMðžñ[½dà
‘CG½8#’ù x«à•¥±0é%bç×Yh0ã0’ë4×äPù•@ù°? ÛØgØïQ«°ß÷°Ç1Æ5dgÝD
4] ¤3B 
®à	,z&4àÝtN«‚¥¸À?3ÈžúÎYPž^_Ê»\ï{Æ‰ù³‡fñ¹³–n"ð™–
–!ÄQÄÃÐÜ8uK‘´V8Œ“ÅGŠÂØ¿ö¥‚IxÔä< ºbi`‰…çL¼‰ua>€l›]8M£p™Ôéë¬…(KT0ÀýþàqpÚ<¢6h—?ÇësgÕ„ÿ™ŽÚöh¹A—:›.N.`/‚;Ž;ƒ­DnOÞÂŽÎÃÂu»øL‹ÓDòçÅ¼Þ!G/aÂ¤•iŠ"ša•Ô¬†Ðlï¸ÿøáÑßjÁŸ{ {r}{ø^ýý6AøÌ—Ýã›R¿5c|G7Çøž¯@&¯±óÒW4îZ‡ùjß¼E˜Oîß&'®Œñ½zñ´ã»jÄø(à9:jÄøõä¸“ÞÉaKßk×%‘àsÀ‚¢oY(òtN%‰÷9Æ<ÇÞF¾÷À‡›dæƒ Í,|Z¦”ÐËiõ›ªQÄyÎMx/X=ƒ,ùÒd—FÔý%]|ÂF6¼kjm^®®óÁçSƒê†÷ºuä)ß¢‰24/žê6Œ$qàO)ŽDÈÝ* °N­ÀmàîÛñµ#|Òwäþãã«³W/èÿÔÂw‰Fø[_ôßv¾Í{)©	õ#³ >àâßc]æý>lV=ŒÃ0ù5ŒÞ-ÿ¶ãÉíxr;žÜŠ;îxr[Àû.à=-£Fpi€o¡°¸wè/Æ”­Fÿx½ˆWµÀ¹/PÈvMáv:ªH§œùòmŠi
­&Jád¸>ãq¨ë3ó»©]zäjÆ%dÐ¤1J^ÒÆç.¾Ýpñ1&+_´ÞÛDúš¿ºƒ@[>¯	ä³®KìÒ»9c^H%ÆNsN£s­åœÿD1Øzs¶¢ô
³…²–º¦ÜÂÇ½ãþqÿˆþ=êöŽ[ùþãã™{½øÿSgÜ îeÞ¡ìíÃœqãrÖÌÿD#? Þ·ø<	´ðôôû™rÑ'Ùtk@7ÊPDžÛì·
éûÈÃeõÈ©4^žgšVùØÙæ—r¼I€ ´…fhdd¸‘m2.)¡Db‡ –|×ÔsÚ2ð–G5jöfÕFÁP™ÄŸµðØÄb¡ÌbAW›Òõ¨õ«LÓ²¾ÃR2ôòæÉÓ›r ˜ü0¿e9¿XÍ¨§^9¤ÿ?xxìºZ\æœ\ßW¿M8óe÷Fàh$4Áoõ‡6OEýÙ‹’ÖÉŸóT“ÿÛ>øV}ï‘7ºùú°ÛÕO——¯ß¼½z»3µ™Uw¼_É8- n½Ç%8®Èª;:Xq{uöìE=,ä¢†€WbÌ¢„ÓDÙ¹-töS=eGÆ•½düª®ðryÐB‡´VÅÐm ÜÀä8lìhê%{››Ø±Í¶ãÉPøáhñ™#ñ[€í "%éÿZ1Ý=¼'fm•1 6*ŸY{´x’±…
XF¼,^«¹¬ˆ!-Û4ù-¿z¼;?pÌ8oÜÎ#ºšQÑ®ð¶3M£óÛï£ôFG4,Ö€Ù²sŸ8ß+£'òJ‹¢a×
_ ›íøË¥…>tš¡øõ‡ÔjCüLÞÊÃÂÙâ0r©=ú˜µÔaË?îðµîñµÉ†x™ÒxÂÛ]Ñ„Š°/ (Ž½Qá£ß‹ÓÌ>óÏpîX;8=n:}Ü:wXO [¬ùáñX~øŸ×a0WfåÄðÂ©çÖÜ­kƒãG-´ÅÉ?™îVŠVZcÉ;áÒQZ(ízíˆ˜ÀuÉqÔúDì›…P–2—Š§Ødƒ%z€˜Èd…7˜C•ó’œ!ÝÚ±P‹üÅg&;g°”‰Ç®‰^!„bG†U0¼Å§÷œ²™P°Ò…D(Î#PÆ*J—½c¯ÔOœsøJ3ZcazR©lŠ §Ld3›÷h€å3B³±æ’ V¢Áð ª5Ö,ðW£8¸øÝ#¤ u¤]/rÈÄµ»…GÂ>ùAÃçÞÔÈÖÝ*¿¤>¨	&Dc'à<Ì°üb{Ü6ÂÔ‰Ôé¬‚ÎÆN--«Õ‰Õ9ßÎÌò°„.Ï3NUüÇB†^1§Ò(×®)3£g—áÀœˆ<
sYB“Q˜é"Š`çã]ÒØ­Œ;CÜjBÞÚÍO>‹2Å-šÓ±¾Åú@Ž`7#¡‰¬X	MoˆÐ*-„G’rD“,,$˜"ë2Ë×`ð›<pô$wXP­3cÝNÏ“A…2;Š·F'ßÑóUnd» ªZb!ó¢o²:èÓ&5ëSz¥¦7Ü+¶¯ZjÆlò6&7Û¥öžÝXF¼íVð\bÔÌÙ»Ÿ^ÛDp³p³›¾lBo³¾k’íln‚CéÄ‚Q‡´8¯Ø[pËçùŠVyDÈµö¶ç\”\ 1šwï¼á]sMa—…nÉ#È]ªé,Æv‘@ýàðÝ*ôwp—.nÙ­¹Ù&°.>èþ˜n¤Þ5£º7ÇuŸ{7¦{A¾ÌÈPÝš÷n×Åí²;è7»G½ÃGMÀî wPB„7vÑ"¢i,~cAÏB]èð‘GÎ],;þ0Qqâ1„Çëšò}V¾õá5Ò5o‘Ù`©¼Yü&NcŠ´BzÁ”Á6fÙÑŠ[ìÈI:ƒ…é$ê?¬‡ô`YŒ>q1t83:÷‚+<dj{Â-gxz¤-”ÎƒÆ²,}µÃuÑ÷k »—@åá{î€Ý°»vwÀîØýŠÝ+åß
W°LFêA1d4"C‘3ÕPµn)x‘ëªZ¾Q«…M‚[zŒÄö¼êv’æÇ-Ñ€*ž+Í~ž6Þ;/IÌ"2€L:ôìÇdÒ]ãGª†^daÅfrçmšŽ1ê¹Gjk†Îú®âüYð[Å _s+ùÏI‰~W]ÖÿqÆŠþ8.ŽõsN=»d øàA‡ me,C¾ÜpÜhýÃ~ËbƒIÉÇˆéªÅîRdŒŠž/~_’¬VéÚƒOëjÏùÉTù1ìP/Gš73ŒX]‹™J;ˆnnE¢°
Vo/‰Øºç]µ|#Æc•Š4à-Ì]™öAÁŒ¯ˆ1ËE\‹6u7š0Èo½ÀõSÖ°§¥ëÕª8 #?H:b¦qÀ
—·YÕÝn#ÐŠ?ëÞ°Õ¯Y7¡VÇ6O~†gØ‰¿üÇð}Ú>hUóÚ-bV¸ûúÕ«Oï€«Ê9ÀärÖPþ;[‰d5—Ì8ê=*á\Å¬àG%ÉÀÍ ¬?ÑÂâ7  ì˜r´§{å/>ÃÃ¶ƒÀ@^Öçx?ã„Û€©Â¤uê–Gg³&VÏ¹Ä>=oX#Å .fÄœE)'fþN—€Ò¬:ø!t¼déÆÑŠê~ÈàmþDU,EˆxËžäÏÐgM’‡CS½n[<£Eª€_;‚%]84ê‹e ù‰ÍÛ4Ÿ•Û’•©>[|ò1&¾ý›øk Z|Þ“2er}³Åföêþ–3…EúaáZ‘¦9FÞ­NXBsLÿ‰¯C÷]õ/;”«{”Ž´‘õ{y4.ùÿgâ	º*IÀW¡Äµ4Yi•?vÜjûH×ÑI+é¬'mÈ	6 ;›!]PïT/41Qxõ÷†vÕ+#þ}¡]¼›`
O’Ñ¾Ý®Š~†zÁ°ýrÙ¬hÞ%7‚XAû²òvrX…/fjÃK±»¥B£u”Æmd/4k¡ƒºÍ&„Ek5Ñî¸%¥¬¯s¯16»‘–¬[)<@)ÌÒÞ¬ˆYEõ­T2¯ßk5…Âx«¼`˜áqŽÍH1×”;Á _ýÃV¡¯Ÿ=ë$egG½Ñ„}qo(z@-êÙBaÎ3ÌšePrùr	Tßrƒ‚4¾©Ì)d(.œM¦l.)™npç ",ÄÓ*²vIž* ¥.Ðc|Zi­87Šåxe^d®µÅÜb$äT“Á.GÉvt/«ËLµ£ß,p¢é‡éÆdÊË»u+gÑçjEîOB)$l‹SÔF×«R÷ÈŽ:¼	ÎãV—òÇA,†AvþA+“–[ãÖóÙö²‚ÉK¤b'Ð)Ðu],›]ìf#ez†)ÇYú&byúÅ,Å©nå—t/8¶oÅ¦™¼ižÀfTÈçØ¡rZµ Æ,yMþ•ž’ƒŽˆÓTôæ7Äwë%ºt³Ð'Š¬_¢÷.H²5‰ÂÀê€$®n¬»Û&p$Ó½ÑÈy0ŠItM¿ N¯)‘)¨·ŒDÖ½q‹P$ß¾M,ò¨sG§½£æR#G½ýRâs8ô%0rCî7‡­ÐK1yºX97 õÌJd,$WKZ|Æ)Lyñ,Œ›èQPãÞâ¯ŠvT‰€
 e}#¦Œš†Ÿ‚=Œûb¯ŠV'pPXL|nšœŒM¯à)¶¼:¸õ­ ŽDVº«<ˆQEð¾õ2Kf`±c‹´j.ì¦Ãˆ^ÐdBCP'å²%ìí„Í X‰?^ÿÂÒæF_øÚ¥îuS¨B}û4œzë^z,œýÄy1)äo½Ñ3Ü 4¤o7Êù!š¢œ
røõñ£×XŠíu«‹XÉC$GüÙðú:k=]üy‡CîØv;¶]Ó]þøÁ¶[¢.w=²Q[Í·»GîÌïˆ„PvÀ—ÍL°dá›%«™S‹©í[uªB€hè ±ÝÍ+vFªiÔ½‹xÏ%d×ù‰µ¾å;)æ)õò<´¼ Ñmè§(ýñ¢§èè
5,Úœ_õÔƒ%Î²µF‹Oô.:~âœå©®ä]—‘§šÈlxoÙxâ\Üh˜fG-Ÿ×=q©1;@¸³špWéIzÎMjÐ¥¥×sè‡ââÊ™³váÇÚŒ
ú‹7ãav•1Ê€ÏŠª¡íV?©½®Úº	Uµm=á‚®LÐª68×Õ°Ð¬{vë¼¼tŠ¬Â¬|acX½ÖTË:ˆŠ"
¤´Š¢6Mæ®z£	U5Ät–ÞT@Ð¹ÉµCÇÄ¦gâs¡x­Ç^{!g·z#}KÕ“Ââ`Ý™°IqiìªÙ›°Miö‚"e#3™s.eRi¿¿£jÃu¨Ð=ð>¾¼=À¯öv› ~òY÷…ü}8W×˜ßÛÆ£›ƒ~oõ†ø,¿KOWûâ‹q¿ú·nø3÷ßrÁáæb$+hz'Í(àAEþ°(ø¸”]»
hÚÆÂ€,¤ãr­“N è:d‡=ž ÞXÌR¹„¹û^[SÉrÜ#4k€=®Ïhêd†-;/õi²Rœ"»ÐšåV˜7¦9IoMb€ì PuTš,>À½³§­?(k£™L&…æÁÔ±•KüÚq¿¬Ëï*1ü*µ2ºù"° ¿ pW‹øKkïÐ¿ú÷£Wi4æ”êuí„|hÌÛêœ[kku?ó;¬ŒÙ>]¹´Ë*sdëm%A¦i„XË6Q²D‹clÛÞ4y·#Š×Œ-µF¿ç<5l$ìúæb{p‹¼”Œ¬HsêS[B¸m…H1JÝãÅGèŠ²ÌÌÓ’¡kßQdBëáÝ–.Ý	•€|_Ô–¶¸)ªIcY«§jNV?!ÿð°ÝFZ^'ÄÆ€+Ý 6­úo?Ÿ¯ð4C£vYÓe#@ó“¼B7Ó­!ªUÆg­"Ç[ûÞM•¼vÔ àó ÿ˜þÓ.ÔfêlCl'ažðeÓ¢0›mo37é‘±Jþ^\£ó¬Lì•r…‘Kû\â9tbdj1pÇÚ† ƒ×˜íh0yàZ…EÝ®^MÑY{7àkåöæ²ËÓtnŠ¾C®2k•7Z¥…N÷ûœÒûðÒyëñ£õá4s}kxZÃý6 Ôì—m€¨…M)½oŽ}	…Ž" +ƒzÏTE¯©8­òÒ­‚iá=z;ƒÒŽN{ƒZ¶¥VUYJ»³®/7ŽIÞuS÷šVC»¸1î.Ò¨1¸<Â3õ‰¾‰r±:1‚ÒzÏ‹±î\§(GÎ˜ÇûÎÊgånB°ò'd†±•2‰¿9òÀn4Wu¦…Ï›páµHÏÒÄ¦ËÆ"ê\ÿ´o¤ëL¢1§#.´„7På|ú>èœHå¾ùUã®¿S;CÉ/²t¨‡`fÆ	ƒZíT÷­«ä›UüE`<¶.½÷agy_¤£0ÊÚafcf9ç .aÄÉ |#ÖÒù‹J,	Óišâ )Œk~ÝkÝE]ßG-¤ÝŽ¾ü&§ƒû×õ\ÉŽ¾ÒÑÜççoWÕ÷äÄ&ÞUõ¥,äuŒäëð¦ÒÛ®éº¶¼FnçLè˜MCƒÍ,gQ3¦&žF“ÄÇeÒ´C¸¸È!Y@ÈÎ¹$,É£ØŠƒ5Lm84ÏEÑZhÉ¸Ž#mÞgR?ñLò°–b6Y5
é¶“m±6¼§áRQ.ß{ðPºˆjÃ¦ä`Ó–³tñWÅuä
XÝ•gZ0/¦Égp
£«íŽ*§Jîö üÀ%WLÁÞD–ó(x^|N˜ÙC·è4yèÏEfL1‹ŸsŸÖ¾`äË…Xa¼	r?\º,²ú2öÝ*{äü²àŒ¾¹U‰/—›1óÊvY,_ïÒ|ôïº"gëÁxo È¢i‰°ÜÒ:é…Ó…>@`öùGjbà$Ÿ“%çÙ7è£¹Ì"[še¼øÌªýœ÷m•æhAáLòB]Äbaè3<Â[ú `3òóJ%_jK¿F_;La ›^%2=îŸ”Žl¹ÇÔ3¥lLøÖQW6Ã‚˜P¦$$¦’›*Ÿ¥š
uŒ3S‰¬ŠÐVh×a}2
Ç(¸„äXœM&ã*€ À(Z|4‹µ:á<fäf	¶;õà±	@lvh`i­¡NN*+qËÙÉË!WG½Ö.2D@qŸ+õL‹ÀÜÇ¢¥¥\%á\#ãnž¯ƒkM1ÖÓÞÆÞ|öTcÍä*xnK†É—c™RgËÎhºË”®20yÃJ»Ìé´KC	å/hHÐì–²Ù`wŠ(ãý_œƒ\ƒ“nH­ l÷‚QÃö²kï¶„Þ?9Ñäo5¨Ç6P/|ç»£?Ó\øÐ:zZóÆ-¢§¸ûúèéÛ×—­A§+Ä÷{'‡ÐéQï ‘…8x\á/6a§oßütñËó?ÿ¦’U]HIöX…œ¯95lËTV‚-7j¦t‡…4™mVõÁDÜÎ²&Ê·Ók¢Ii¦3=Ó)´rÁb Ëcwˆzu°jÃh$" çrr›5WsVXPcø&£Lå¨ßegzNWûÆ§£IêûÚ¸Òz—Þé|ñÛÈ¯•>é«P¥Ë‡}zú6¢y„|ÉÒÄÝÁ§—4Ai	äùí‹ "qk‹§?q,.è<×ïjAHµsFíƒ/~F’ U_ûÃ °Bõä™|H†2Ní¼ÓÅŸwÙÇÝgï°Ô–úc©ÏX§=ÃŠÅžÔ|ë O…)[ƒì{kÉdsX61%nT&–Mý0ãß`;VÉò(Q2F5‡cVÊ‚{œ›@œ“$¬ƒ$@,‡«pNñ ”@fgFó¾¡Ã±&’ÈlTØcûD¼+Ø`_Ù»xÆ’eñ+Í‰1øøÐ"ŒfP	Í5YÒ±ßÚúÍÅFÐp¢‚š¬¿V!\qIY@Å] vh¨ôöÇtè³÷mAæ°˜çÇ:=	çÜ@õ;¨ÍvóÇ²{¯˜–ƒêYïR{ë¼ZÄv3Œ_å†©zÓQK7á£vVòø–tøB>pF³C6WåÅ§2„`o'ÏÀäé›¡è1¡PR(½4cuLþ^ Þû¿áÐ–+GVhÀµV„Þ1ñš\ ‚Áñ†WÃKüÙ3½k“)…e4_W$#×&®ËºŒB'Yüö¾‚+š²©–Ý¼p¼JxV-í›ì1NM‹|7^û6u9b¼2|²Re‹Ø‘SY´ZE;+!RGÍÙwrûåû‰¼ž,R´øsˆÎ3Ç`Æh+ƒúli»'áë[[j}‚[%Ö-ß`àÊâ^ŒrdÎ¤C?Ž¹²²5TNó/'¯‡¢n»X.ÔóÒ‡Å2[]#EÒ‡2 ºÕ0¤A&:g#åvk­±Úô~ïàøÁqGk ±{@ž¸º=È³în›@žüM÷†<¯ÉsiLÂn:¸9èyŽÍ›-/¦3{¡¾nø¬{í‘O¾}çÅ‹¯V¡ž›QÏÁaoÐ„zžö—È¤æ^s“0•\É–ŒDŒ„…Ûk²Ÿ®¦C,Ì …F®A ¸\ðlñ•
ìZù"6:F¾å÷œ×ItbãP–9
ò^"Å|Ï¼<®I,J¶{¼Õ¾ø^ÔMéÉZüÅLîJ¾ÌùÊn¸g"è=çìä¥[Š€àË`óQn&Áíâ³›Ðv˜'€5j¿„,ÔÍ®ðÏÒ]á—äi¦û®ðË®ðË®ðKô‰ÅVÝ:KvGÒµX;0Ü0†iq/hÛÇ• Á|
äyØdŽþa6c–>ùH6ƒ0ˆÔÈöÙš.S^f«ážH‰ÎjÂH³6ñ>¥Í¡°33,Ú"nH®…«0P8â•úy‘tÁØ”ôj½Ì“¨èÓ…ö¬7›æ>9RYÁãkŽûÈçÐ·Š,ºç]u)ñÇ7¡ŠÒ29Zàs¥^SâWJ¶p×°¿ÓXE6¯6ñ²PœçS 3Âq}Ï5Pö«œÄ”¼Øö¯	ÈJ}R¨Ó_[BoMCÍ-¥o:áÀò×51'¥;hH“#ƒÖaÙGNxîFyFi+”9B[sÅ0"¹L°¶©î>h`¹Ê„‰|•M
r½E0‹9å©—)(Ö”x.h!Vòä±À mej"EþàX&Ö`¿÷ÿ¶‰3šˆ¤«ž|s‰p§¾;Qëš‚»3ÂùÚ¥´ø ïÑ Çj£•¢ü:!Ï–ÕÛª}R<ÝƒãËÛájo·	
'Ÿuo.bsS”·Í‡7Oà>‹ ]Ö?3Ã«u®þ¥[âÌÖ‡âÎž–1¬ÍYˆß¯Jà~T‚Õ
xÜIï´	ŽÛïJ¥›7Îß6õ‘y»A‰ŒyIJs DFÃˆG:+Îý«Rhiò²+ÇP6øbZÓq#Fó§0Ù^ÉÊ6±Æ~o’"³«F*SÃU@Ò(wš‘ìÄ&:èˆ+R™;Š‘®XÜ˜Þ7<¹ø¹#Èþþê«1g=@‚F|#Ñc½I©£îˆh
0ž]úo;ùÛY®öKúÊÝ˜ÓMvA‘ßºø÷X——¥“¹MkŸNÆa˜üFï–Û¡pâ¡gt_~5^ñyxÃ•7zNv4J½@Ä´)V[üÕ8o‚Ï½1\˜‡‡ƒ0£ã6Ð¼Ç÷gºà¨c1·’’Sî!5fíðÁ=d;áÚ.úpÆ|­†àŠµ?òìÞBè»d	MáÈ
[e›ð·Yw40ó™MÈ™m.}·&AÑÁž§¶<‘2¶’+ô:­‚]Wœ7¥…Ûï®ášÀ.Ûp¨¨lIj6ñ•È›¥~\ÈûÇÞr
dØP yÂì“bÚî	Gè3yÙTúµÒê\õYr<SÊv½È\Y£ò>wÎI¿erÛ9ôÉ˜NÝuPÅÍÈ+Ø~‚‚“#¸¤4Õž¨lFá\“÷Ð¯ïˆ§¨Ÿˆò·¾ªU–±«¥GŽ‹U~`xLÆƒÒ„R‘¯}q:\Æ©E2Âˆ
wŠ"°a(Éå.0²d`˜W‘âk tü1Ð2 ÒK·ÊË[ŽºMÄ¼l‘W>+Ž!©|x]ã’	áŽõÅæ‚>šI] KO©=Sh:åú:n¶¯ ê£ºDßGw«Gbî¶™´‡·5ÜpÄÍ~Üý1·9ÍŸ)ö¨ÛŠ6§À!fø~ñ1¢ˆ¹Liskxå6Q7ûˆmfÿŠö\î¿4sá{‡Íâ‰ƒýÞ~øvpÐ´¥žøbÊŽAÆ‰WÎ8<eRs#²­¼	
XØÌ6«‡>°:…ê 1K‘È}~ˆ´Ca.UcOhhCj“D±ð´±Y\¶¯ q¤PP¥Ëª3XÍÆ‘<	ÛkŒ ªÅïìÉ¶#=ºc®‘:C3|•Æ¯¡;ô-ëÈÕ)À?ðð‚©TPØâþæ ÜŽ÷°¡¸¦às¨UÎk[E“"ò[þÜzïÝßKö/uG79®'5cpO™Ó!­ûÊ2~¾9%7œÂ@Uâˆíeÿ¶‚À=úG¨>"–‚:a¨8xí…³fì*\nî`¼Œ£wÅÍóÏö÷#ÌÄôÈŽ‰ºž±~’ÎÄ›‘\MVncs%%Qœ²œdƒ‡Å"l57ÏªsH¡/²L£ I!<_S0UH'í2é
ê"X¶ýÑ€ž=Ÿ…áaÖëi˜³qàÑÀ3žï-Ñ…Xª2”Lì=ùS!éY‚åOì9—Ü	¦@ÉRSXÌÄt]l}d¬áäŽV©,­Ï"O”¿¦á¸$Ë6z#Ø˜õ
ï±§Y”?OöÍQcã¿ø‡úå·xÎ¸[®Î¤îòÞ2¸%y¸ÊÈ¤ŠÃjiROx´*!xË)µÖ¼Ó;L Í‚ÿ.;¬‰{WˆØ ™PMÀ¡ƒ¬T
®%ÇiÃ£Õ³…jÌk!yËÈö›ô§`4÷‚Ér$ze©Ç$ükñJ•]qUÈù¶Õ˜(jÞÃïdð]BÝÖ«Vâ¹/OîN}¾ÖˆrMÎ}à;{‹¼¦[náeŸxoïWr³ôè—±Ja<9Åù¡þ”Í¼W
 xh“ÏUÄ9:-#zòö5/ß"¤—?c}LïUEþîþ)­+q¼fï¸Å«"|f´É^MÔõâw¨! áÓ–Så/W!ÁÃl7ucá?hÃ2uA“‚€Li¸gª›°üB¬­›*^™”h*d*à(ÌF <(XCÿz4µ‚l²­Ád<¼_ä¹¼häÝòëx“Úªo8w,Só•„SY)éü]6ërïõ&È²3º«~‚õÝ9ãü	çÛï£ôFƒÚ¹~—ûÄùjÙxW*X|lÐç»ô¢Ð'o‘&kå„‡ÜÅaäêhÞçÌo4šNŠ?ïÀºîÁº]Áà]Áà¯¸`ðS˜0$Ôv	`®#çs4U´nE¿\iåç®\þñM³ešÌ’Å3E- çÐWüá‚	ÇŒ	ÿá\“Âõ†Bs›b0*V=T²~²M|b8Z|Þjn°„¯ÿF¡Ú9nÀªM˜DœdYå'&€õ„C=‘‘¾zëa‡¸Üt'ýc¤ÀÞsŒ´¸ãvkôÆn8±K¯½ýF½$·þ­ºF£jQ²—3¤e<1ò¡ ,ÂNpj•éïkôdÜ€Ñha)¤#Â€q>j«dªuéëaYßËK2-IÂÅm7p¾2\‘+ünf.¡UÙ„f@ÿÈòk`âjÕË­ŠÄ÷{ûÍ(š¯Ñ|ä”<‘eC/(04ûPì‡Ê
X.áµá6!â¤<â¸dïI†09©Z .µ¼~«Býò­U”>($ìW•ûr¡_ù´ïj ž{@`…›´‚­¸é&0XñC7 Â·QÈ­éàæÙ£o@µ=›±žnû…ëÞ·U¼‹n¿Í´Ñ•ôµóU™£GÇ°×A…ÚVL=h±|w®x}3–Mþ »Ë¾.´X£‡Ö(àõ¡¨¸½§È<«L§®§Y°„.Qc	¢÷d¥¢Æ@ïóZºé¨.`¼"rØéÆR¯‚M;JùÊ;ai3.Ó_ÒÅgŠq°kÚ½¬§Ø¹ŒÔàa×Þlñ­Órá¥ç'!—ì’ŒÔ›ü:2cr·"YŠwÐwÏêÔQÑùoÌ¹u™=º+_±+_ñw‡†í²HwY¤_wéÏ,\ílŸjØØ­&¯½,¤‹)”åÝ²ÜR ¬Ý€MQ,½ZŽ€œ
8$Q¸$”¾MÐëæÚeÏ{ë<n‹ç‰½® ¼ñk3þ…FÃ¦š¦c¿©æÇŠ2JR'còô"!£Éoô]`Cc.eÀ'¸!K%
¬‚œâŽ6¶KC•½ÅH{0|ª®
oÖÕ,¼V¡¹·aÀ‚,F¾«Ž§Æ§ç*nŽÐéVÎ)½e1ãî Àè†íå	°ÉÝÂ¹GQÍ|@{tËÛbwÔÊƒYvT»¬³FÙ”LrŽÜŠï)Ši¢FÙÙåi®SÊs‚ic Ù‹àRVâuöBis“²k|
œ~F‘ÇºÙ4 59ºRuÅ<«]5ò©ïŸ¥ ;À-\ÕÔÈ²J„«‡Í´8‹ñè<Ë"-æ›Ô¼0qÒ—ÐeuÐ¬\y°’	øø!¢sU¨æ^¸]Þ&"Ws»Í°8|Ö}Q¸4š„I÷SÃ±18²¯—4šFœøòJ»äØÒ‚Ù6W÷Òíq|÷6ÓHï¤œ5coÇƒÞ Yµí°wTJ-pÎz'kVQ¸3q”„|²Œ“TËt‚Âäï=…RXcÀþ‘’ã®h.Õ/Çþ*$´!VXìõò.Se±iCbÜ¦Ad;Ùå½¦ŠB©ÇFw
‡äãÊ•Šâê kÝ9÷tco"å™²º±cIÏiÏ§4Ô¿°í%/5CPŽMnðÜÉS6hÞHíØ÷þY£¯<=2©d„ ¡óŠ²Ð‚¿¤Ì2¤×Ž†[^[¦ë1öDPµ‰¥Ë•Ü\4Í•(ÿi‡½íÒFwi£»´ÑN 7üº©¯à,€,îÓßé~c[¶ú&fpî\’7M9øÀdîÄ\Ö.Rúë˜ÑqCY¹QV¼i¼øŒ»9yMKrP£Þ­ 4­RÑX€ˆÖ‡¨#y,n©†<Ï—™D¿»¬ÚeÔÀ’få5GºqšÒ ËD Û¨ó
v“E²ò+G»F“Ï/bU©¤þcTKèŸÒ¿ëâë¡e2v:ê†ŒNÇqA¼½P™ ºÅJRq‘±’æXV$á)ËÂ‡f#c(o}y2yšœcm½ýŸœ%“èœcä4.5€ÐãþIÿ°¿.‹kíŠ
£Å§8œÜ2	Òjf¶n–W(Øs&šKRçÅ-æ¨Ñ&º2²"´*Èû‰K˜b¹"WF¡9øi[YSê˜ oH´àôŠÑ­
¾­9:ê²í7é2‹Á%©lÅ=¥XGönfÂ¨ þ8ÍV–òâ:?ihyš³ZRÀ®‚lÓèÝ3SÌ7ÁÉ%NZ|¢á-•{ð»¾ÑnJýçW»É¦ÇýG­.C‚µ>œÇW·†æÕÞm0O¾é¾XÞ­ŠÒr:gŽ_×ÛœO÷ƒÏÐ ìð¹ŠžmCyuïÜ”Çwh•Žz‡Í˜ÞqoŸnM-¸uètHQÀ&h¦P,ð^¦­æpã9Æº¨XÇŸL±“â#’«u•­E™J¤™ñ%Z†×°@guTýA®#[°#³¡c¬Mù>ÑXF¡¯±Õî	£± Ux6ª÷š7"K!¢oö;žApÎFÓ{ð¹Ã¯Ñ3»’Lw.µ“X«&ŠÒ™8ä»jÃÛUcxXXÞŽG·ãÑ}í<º	‡·Þ-gµuæ±»CŽµ#pß„c%n"‡¼…™-ØýÛ3&Ñ;M nO•h»•Õ¬ê* öœ88ÃP¶ÅZÆÑ—¹¢aX¨Ôi´;lT·MôÏxð3zrÂ´í‡ÆÜ–øŸ´sœ	üOà5ÐFË4”û,L´›„‚å›`_0¼ysÕH ºXz’÷¬Å'¸:*èZ-G\uc©š]TÛà†m€üdº>qš mé”R©«-ŸgZÎý-€3 dqQá+€xÙmG:¡«‹×4 ¹ö4Y!óì+Æ4ðç÷d¼—°àžsÆáó,pà–k+C¿< ŽÚ…_úé­(bJÀÒA-~p(MS¨‰[~½b”÷(æLƒPè8mÊ‹°Ö¶)4"Çhjå 2ú«ž5kÌB©‘
t˜Z .a¥Eúk¤×tkzSD„9–­æíÖ=õb–XîENŸÇˆÊ5'ÕøåMzÍïófHiÌJÞf«oÌ|X”8´û0Ëró·2t6?ùò†lk¹$åø/QÓ¡'gß#áB$5å`Yâ®òàh„u`Ûú°#_ÝìX{·`Gù¦ûÃŽŒa4ÀŽõÇ6‡Ï)‘æüº4ÚoÕ¤u
aÝK·‰;ÒÝ·‰;¾}}Ù€;ž5ãŽû½ýR%×îxºw|\9ö%ÀãÕµÂJÆm$ž0vƒM³åPÆ‡}“)V£À`ðù,ü¨¬¨ÝºŠË¨‰Ã@^xŠmL9›nDž?¯’t_O‰¿1JIËÅ”–(Õs.£aù%¸X’%T3ÌÉE%„G8MÕ®TÎÈ{Us
É3àxhÏ|eúX5;-ÞH?þO=z °#wøJØñ9Æ€`¸j×Íà½¾ä!Ö2Ø¸D¬…eàs~——Ðô.Û›:Ò¼V>‡ÆÅŸñÏtÜŽ;ÐqÅw ã@ÇÌK
^b°#™±_~ô&×És6¿M¬üÄ[¾rÕÐ×K±”²\kžAM1´©K.*"A«æ^e˜Pm›â¥GñW*lÜ„oÓ)5Üª66:Øe›É»ÙR¨RDLªs,~{ïM-Ðîqš…«6sºPÙÒ"^5*wý£þa¿]¬ð²£‡ÊgdºjÛ+Ï¿e÷1ô“†üiibÁ‘žªR¸”=e,…´"Ý™†¥¿ç$hk×Èû\C£œFüâÓ¨#cI4uh´ZT=\”ŽAQb§Ž~I$·têÅÓ0W³ðaÊºü.ûˆÚlý7B‡Raµœ!ßØåí"„¨H¦ƒ®z—Ÿ6Võk¬\3o@§Pu)¤\)š3y¿Û.¡iU X|¢eÊ·˜îœW*%
!Iºz¤±-±Ur”½%=-¢ŠK&¼‹BÅ:2µ#etP(WIJÆ—c£üyÇô-Ô^ï_7Ì£–Žºö':yèÌ•Ý›ï³˜¾ä†+3¨ëâÓÌãHÖ ònWÖœèbø­Y‰#£i`ÌÁž€º–¸½¬HÀO7'Ò³x· ÉÒåö	5†~ÛÄÅ!Ä*Ît‘®nB¬¹ÛF"¾éÞ¢ö˜ü^!6Ü¼Æ¥¦á‹±œWÄ@u¿u$±îÕ[„ùömVÀ(c‰gOÏï%=îN±ÄÁ~¯t°—|P®¡±Y-Œ‹ Ici…ÓbÉúmB¾q ' ûIë'd_›n0çŠ#vHòÜ„Yˆ½PQóÚ*PdûÔ¨ýå0g’üœÅçˆ"<<ðÜKÍ†ê¾Þcôqñ[¶xMS<×ìLÓ*Ä¡(6~JL1õ›áPü€9Å¸ŠŸMž½¶9…ZðÕ“¥gW×Á )Ãçz²˜-8£C`ñÂ'o‰üPíoŸ†H9_W°pöç…©$øFÏ°Ù„†¨èëÜÜ>óC4ÌkäË`h±Ådô†¦MY\—ó}ÌîæCBGüÙ¬¨³ÖÓÅŸwPã®jÆ®jFÓ]wU3¶€3^Z¶ß+£QäFzñyèwÃrd;GÞNÎ„Þ­)Œ$aºáÔ$g°¨ðxHœ°Îº.(cÞRHˆ[‘;áüÇÇ¹hÞê¸‡it§[šÚÿdnAé®Jƒ\Êmž’ƒÉp§o2>£úC“ç‘Áå<à¥*²‡Ì™ÛD5Ï$Ü¥Ïõ&]ÙIs~O®Æ8õ¯
¸½›‰ÙEÔÁ`“Ã5Î×Q+uâæByM˜@KÉÑ’û9&÷²XùÃ0eQÄøðƒÃ„ý?º§y/FßÄ@ÔŒ‡|Ô•9 Jÿ®›º ú
#Ú³íÒUO>UÑ;zró~€4—Ü†sãô¤©Š…ô°ôÒ¦ˆ”æs=pîL*vc~|¥ «é}éVå¦Ó<“ºn-3œ¼ NMr¹E·‹‘>Ç2ï.¼«ã°"¥¬²ˆÌ¬ìÅH')ÐkžX>ôÞ%Ap´2B•HÄmñ	jFo/Fº.5U31¾Zè)ÔLJu«©ÔuQRWMÞ@{¼ðs3‘pÔÈÕeåÈâ²—Íªbý§Òlxê!ªÌNÈÌÙ2ëØ$6ŽAr(
íþýPëP©{ |y{Ècíí6å³î=ú¿¼?4‚þþ{íÑÍÑÇ×7KPë˜£_ýœ61GÜ~›˜ãÕO——¯ß¼mÂW”àôšs§OWÀŽ‡mæNÿÈ)<H+ŠdgË!¨k°,«0®%}(–²ù2»pÈ3\r“A¯ZI¶‘Ž^ÀËRxÓá(*Ò7­%lÄ‹Øf)À%.õy¿¤]>QváõL€,ÔñåZ¼Úmˆå²ÀögaY“S“Ë¿î9‹O¾ð*éóvÐ£töjìñøéäD'À¢ÉOvÂoUÜ‘; rD¶!šxA^7ym?`Š™ñáü9¤ˆ'ÅAÃ”Ä5?†îµþ{‘N|ùMN÷§<®QšÔÑÜçj;ÂãÉ‰álî´†¸’aV[Zñ¬¾v8¤O6ïíDUM5¶Ò¦Ž6~CEµÏ·Ü H*®.ƒ{Å|jD†Ìà«–(Q½¶škíeOùX‡º¢á©7%[›¶/P¹,x(ïg!â¡7N#WY’i•“eÛ
Û,ÆMžç­æ½q~ “K¹ð,Ì³C+õ[3L¸Ü!ZÅŸEžNÔR$ÖM—4¤bgÓaY¸‚_P-ÅËme’4÷öä—ËÖìåuL²« ÛWpŠÞº%™×Cé.5tž†‡LÈ+¢ÿny%*6oSÎ³i^,À/T¾Ïµ­ïbÏ‘Ê˜Â‘,}ååldœEcPÓ:‰Ú8RÂäŽ™ÅH˜ð®1³(PjEh`-äîÎ£«þhÊ^nXxÝñ&,ÉŠð/n3¨ÜŒf¯0 ðÀW/F8µ|ßFÃ0 æÊèÞÃƒöüïªèÏ}°=¾¾Ep¯ö~¡{òe÷‡÷Þ7B{uG6/nòrñû$¥µ»}@¯üžm¢yïÛ„òî¬fòtkð`U%á£Ã&øn¿w\‚ö6eÂm…Ã¥ppñ^Ä~&LÈ²°BÅC¯—p¥l£Æ¡p(5¶€.FéT	ºFg JÅDñ˜Á$ƒÞ%´£0†êrŒM5qnñZHÒ8ÁíÁ G]W.{Ož wˆ³•l²âú˜ßÐ1ÂÍN¼øºã{¬ûZ”‚áåâþì »÷kS¹aáŠíª›9º«¼ãv¬À+pÇ
,êÏ³ùÆÞÅ_œšØÇ}ù™³ÕHì²¿¬0Z^j„•,†áÉ$äâi9…K”âàô [“[Š”á¥à`:ª™Ž‰	(&‹+¸RZIS–Ú|²8/YU¤ADn†PÍRÍ2Wùr^$–Ù&îVòþœ·VÊèŒÕPšxÔ×•6Únmä,Ë~œÃ."ó÷wTa)¤t¢=SW¹äá@•ÛAn
“hnY®Ô>§dÔ8'ºUÈîÂ0¨hÈÆa7)•ú}\÷Ú$Eæå5*@fCu”6  õ 8V6.Ímõ¡#‘Oý¾ƒ{‘3ºaôd8°[¡lYˆÓLüK»––¶Zay;8ëûwÒ€ ÙÓ,£¦ð„8(«a-š“©‘±&Us¨½ƒTß†K˜¼¬®ùjk$8°ØrTZûh¿oð°‡†•“û aï[Á*÷Ú {ðËkÂ¾êl.Êw61t&çÒóŠ·ËìÂ/ÁÊ/Ü"æ­ýé§Ÿ½¼øRlEýƒÞ~	è* `'½ý“(XIÀoãš¾¯“HäöhB2ËKgõouD}C9›Õ 8ôPi@Kˆgåú¦é}¥7¥>oc‡PÒ= tMÓ¹dD Ã©Œ›ÉÁŒ2M¥‰”ë9"ÕMA;Ý7}ÅOC±sò9}ñûÙK‡Pî‹SÙóàaŠC‡'‡X½9mÖ•'y°Ì­‹a…¸4ðŽÃæ¡¿e¨7Ö Aé)VQ¤7òŠR€bWá|PòHŸ->±iþögå‡ÑˆŸ÷2gf½úŒnÃ83A‚ù˜ºÂ½ËR}ìö=,@Œ&UèßÂö¡l ÆôŸø:tßUÿrO`,+òääÙva±ýþþqÿjº2ur4ø"ìK1¸]]ß]]ß]]ßz<LJ‰öæ@a?{«!0ÏTÍÌƒw¸&`ç«ØhK±*ó•ÌÔd‰M†Hu±åŒÕ"çi‹ÐÑ›â½)J²u•zM\2Ûšp¼&È ƒŸ°¡Ï2SgENÔ©§G†õí¦óÐøŠ”’K§ ™”:‚‹3pyŠš²±R“Yg(S@wö•Ðräz•DÈS¶*`f/ÕRÍöîÙ¼HŠI]ñØÁ>ü}Dÿž´ŠeÛEß5‘Î<'Â¢FÍº(–ž˜òË/¢ïHRe¨«P	„J&°Ÿ±\ûA¤Ã¶›'jm®­ÙE7@^(Œ‘!çF7M|ìS (—wŒˆ96ÍÃ»2h[-{QãçwÑšM¤1F³|#t
§´PXjózŒ¿KÝGI[Ò™æ¥X¬=/Nl7!²bÇ¥Òâò¼%F ø>È™ügd@qõc²È‘74*ñ²<ÕfZSÖÅËjð°íTÒ­ E÷€Ë¼öÐ²Ê­6Ë¼°2])a›ÃeõÇ6DÌª"ÜmƒeÕ×m/£»·™­_;w…îÜioP’+`gz	 ½“è¶1vÆ³EA(d!ˆ4öÏæÊ¡ØÖŠÎ §a#³˜
„<ßÁ’[¶=-QØ>J†œL¨òú¹Ž­29›!KEÄ¨x;Ò Á¤rFBwN¢p¬I–¹flŒ £Û¼Áì9g×ÀüÌëƒ8ÆZ-’‡Ê:L--ƒËÄð…F¡UQÁ5¶¿zøúw"hìº3Í™ÈÝ•´`}Ø„Ýïv*ZÔÕ®¨¯rQÆÓv–×³pÑH4Þ£ü§Z¶CËvhÙ-Û>ZVH«·~D7Yœ°[w$q–QWix7ÄÖ)2BŒ+æ/æÍ>ò©i`,.gü†˜\°k5ßì‘=siN•hÑAØÎ/WÜÅ5Z|¼=1¸?6þËÑ	ÊZCü)ðfèªdBu×Õ1‚j3Wé-³Åø¤+°ó“t®—˜ô ;pÊÏ‚ÇÄ!j`¹pP'JR/±ùèq:W¦ðå6Ä¬,PD‰*”ÚzˆO}ÝÈAc­¬Q!_"w¬–Á´£8Ân‚Š…øLn'XQjpn~bÝ;ræjü2ˆ‘%åtÔ4…6
Ãi¶½õ†`ìDärð»õäÅ—Ác.¥PÃƒûgfÅöi“CLƒÈ;O¹âæÜT<ˆê "”í4-ÊÍê“Ò^ÿþ]—<µÈùƒ‚J[Þº¬“Z»îÔÑ‹ºˆY=D+‘“fÙ©TëRW<[²F‡¡Ï³úGO!$KÜ™Â¯ØÖ ´’nÕÙ$°ªB›l·¦Ã[²!ÁZW»)—›Ÿ¢!ÐÖ¹.ðRë™qK£Û{†Qà©›kôr{#ó‹ˆ>ˆL9FsúEƒBVn-\íªi:öÉVì5 ˜¯fÛòÃýÓµ·ÖËè]Š;êÉF Ö´:özfÈêp„×Y Â+hðú%{8Š˜>½Šö‡Ê¡€YÞÜ²Ñ…+ÎXò|Ù´JbÑ«ë'Ëáûø“IÏ. Â°wqbÅRëy¤÷Íòí¨­â{÷ÁjéêáÚš»m„Øâ›îÚ¾£¹ÛˆÚ6Ü\ºï9yãþ(´Y¡¯ÿ’r Ô:‚[óêmB¸¸}§™¿ç+eûšuû{ƒAn;è”.Ü,óWšƒUc®óÆ2¶4äÒj7ýÅo Q÷IÚ+äå…açÃ	8XÆ‰¿NaÌDøôH¶¨Hg‹j—æBÄdÂ†"HV¥¬WBØÎ•ýBÔ[DM†•dµÕˆÍ&5…gá²7fS‹+ÝIà“çµì¨ŽÜów¤ÿf‰±{v¨Î·ËþÝeÿî²wÙ¿»ìß¿#üöâ6ôS‰€h£—ˆ;‚oaÊîHýs'‘òTß„R·ß4›Ð#¢Ç$e’BáÕ†Ð©M€’lQÉ§ŠÉœ—.`4W#ë‘Û’1;ûŠ÷%­‚=õ‘¶[û®Jcù%É*©­	*g¢îeºó¾Ê“´G¥lÃ$“¾
{¯¸/ŸØªtAcËeO ‡žìÿ3½…ù˜»*Mçè*KË4Ì_Av}Ü7™À­ÂŠç
ó&àÏ%R€½«ÁÐˆ+rû™ºÒÐÀn‹ïiZŽ‹Ýfâ‡5È-Tã>†¡Fñ…–ø¯&CÑ[†v§´Á±Í
	¶¤%´ÕBÔ‚½¥Fn•Æ³1SVæÂ‚$@ÑK¶:ê¥´Ý;2rõIÎ­BœFåŽk#Ú]ÌNB#ÂÉM•ÆŒBÕa#`ñ[’½¶r®w—SAµo}ÓP±H!#™‹Ï-r]“ÁL¦8Æ%á‘”¢GÐ5!Ä¤ls:(hå¦“šÀ!Ë"ÔTÒà%¬$¹WÃ— ù¤iß&òY‰ÀºêåFôS¦{Ü¤¦v2YÆ	dÍ¨íÈæ2L™A–\£Xhªò“‚ÍS¶½^ÊÌÛk¸a±Ø–™Êõã$S%Èba<|¦>pº†yëhøË_˜¦Jl,‰äLÄÇ?8þgá·E‹3¯ºãñ€µë0¸ûÀ¬¸¼Eœµîv­üY÷FZ}²DS¯km>¼9Úz¥'Ìé9W4ÈâÅçÖQÖú—ng5Øf¹”·¯/ï/¶xÚ;^%¶x¸"Íü´táfëOì¸„gj—hv5cšä‘6µ–÷Û|€[±Ì¥¦.
#¡q"{›Îáû”u…FRÇ$+d7Z|VRyË›b—<rlœOeð6[1¹šZ ?‰eS‡:Ó—÷³D`ïÒ'L»Ô Ž±„bå±ClÍcY$ë:cz	YZ®w÷µ£®¶_Wã®Ï©uÉŽLT‡äX¬ãÎ™P}û}-Ek ­Ù¹O¸Ú7§W*X|l T/=4iªVNxX(kU4ïCe&@£é¤øóaÝ!¬;„µé®;„uU—¹Ò”›y…€«Æb]ÖÇƒç |î;³ÔJÎÈ~°…Ë
®Œd"SËò¹NmiÜ=Ç[^q;Q‚+áöNªÏu¾E“òP)‰XúÛ‡lÓXjwm=R7MÛ„ÓÚ–G[KÉ,&ìgõ;m(œHê&”(¹+‚ÌÑµf#}/«L@‘.­À·ÌöpZlƒ¼üFê^‡&ÝËˆÛ1S9ÿ{?}Ô{Ü?éö{úG½vsÐ!n0œÇ»«ä^ÓœW5§˜ý’>÷Ž®‹á’S?±êåÚ¾x4ì™ü\YdÔû–sk_Ä¡¸jø4ïò¼°„gÁÁÞZð¬AYçNa|ÅŽ’·4”iX)Ô{Ú?8îô©UZEA_©H©wnŒî:±	µ2ƒûn(ÌBf—ùÇ½+ãÉš<aC“— ÚZ$öQqÞ÷1Re¸K‚ÁÅGàî®¹)yÞ,qç›$vDÎÐCÐf,úÅšæÂVÆ§iÒ4\{c-D3‹mºë*ûÈ´²9øÿ  ÿÿì]M“ÛVvÝçW ©J•§Â!û[’³jK[Ë£¨53™Ù¸Ðäën´A€=¢ª²È2ëì²ÓÒ‹^y7Ëðåž{ï{øf“·G¬²äÙ÷yïyçžSé3jbÆ™L§å¹K2v$æ‡_•åàOMdñdµÂB7µn£¬)Ä¿Mk.mîüö.‚k*­À4óÜo•o½ûn½e1äá%ÕÏ'&úš·Óó´†}xôøÐÈf¬j<R/Ð"ÙrÁm0Iûp£’1V†š™µC%ÛßÞÞçÅAÞ¯¢1¨Êóî_šo»C\R¿`}\òÝïÞlˆKJõó6
˜'íÐäÉðYåÍš<žU`ËO±q~Ã9´[„¶ÈgxØBE†}m+âÈ„éh?‹r]¡Ð·UþéwÂWY:ä%iÐU8…W[r`LWŠb½™E™8E”øvj§Ê9§5S…3CÉémœL„0?§±À‹÷}5ØüÛ¥µ|/¡åÐ»å,CÝLÂÅEŸ=Déú6òhì·C”>¸ëîÞðÊ¯Y/‹W‘Œ‘µ0_ÐN*
ùõ¾·üx•·
`>J.¨¾¼¦™ÅWóC³bD×õWöˆeÿˆåò¿¯õå70¬¤b«Wœ0;ýDëñjH-ì£œÿ¬“rþ.pÏ˜®²Ô™‹•€«ùy´¾Á ö‡÷fùã<1u	Š¡•Oº0¨>|Ö¬jðËB+]¼äñbˆ¥n`ß£O¿oÁ-‘¿I’ŒÍ¨jÏl·@W!5U?¬Dä`¯Å=ßB^4±;–S7‡N®iÑ%4ùŠý‹¡^DIÈ=äßÚŽmø¤mæ:ùÒª@Qh Ž-G.xå,l‰qƒ‰²žƒ+ÁŒº¥©Nõxd9[ÝŠ[òÑ Vü«ÄD´øôãÿ«MÙFÒ´-]¥Ï•5ÙlDà	ž™…3Ú(91MîX'By}•Â~Ë”fsŸ"ôØ. iñ›¤Uº­[·f)*hÌõAªÓGjCóRŸ
ÌÂåGê€k"+7™Å´a·©7˜ˆiÉw®ÿ›··ú˜[õ¿H¨åbº±É	’u ©[°°)Ëè¯»ÚX®ÁX
Ó.N¥.*ˆ«¬ƒª_ú·1«i:¼Ð­Š¥ƒ­!³–©%ÊgrðÖ¥²an0ÄÞdô­·ÆR «‚2©hrLÀß³Z"Oi||5àÍÑx ^ ;<°å‚Ûàöá6Åÿê'Æ?´Àl}w{4ð-/Xt‚	_’ã z·kP°ùæ»Ãõú?$¸’­X%$–ØŠ‡ã›"[ñ¤¢ú) ¶ÝxS±šw¶xŒ&E¼²‰‡´šäÜ2[ÅPÏ…U[sÆDIÌ9ïqtÌ"-)Ó•6ˆ•ñ£‡“dXõ0†ëÀqqÂÌFÄ¬Æ´Uú	+S]ÆÓK?¨["‹À‘’ñÄ¤„ìwôßÚUÆÖÕá_²åý”Öî¡w%cwáUúŸ="èz% ¹K{ä|î±À=¸Ç÷Xà¤.(I µ™ÒŽm),¨ÛÖ  Ö—6;Í®£6ýEí/K¢F+g,ÛÐéÁ?[Jâ€A3®~DmÛ\Êä(.¹ã¬5òªHˆBˆq1­^…eµ«CŽ/¬i²u‰!"õ¼aCÉÈ‘ÇôQ®Ý$Ún 8ÀPº‡ãeÔuNL±j7qH^Ï¯+j©a1W”Ê…EaswæXé‘n™ŒÏ±â¥Â(D¤Û[g´`¶Yn2ŠóZ-\"7ž¹6È9jó˜Î»©ÈómW&tˆ|Ž9†‡õÊÒ*NO£zŒ×‚U§ªÁ…<·P^JPÁy:zr0:=tëæsž‰ÿ2‚J¬E}ûk÷µà”¶ssyë\Ú/ünG,œz"yLÛWf„îÜ$V¤11@@1	Ã¼ëUóÐ±@j'%4¼~ä¾ìÐ«#X¹³øè°Sg Z¦Ó[¯´À‘¶WT´x$ þâ>¿`³1‹àªP›#§>(yñ+×-iÆG+YWG,ÌÑ~ž‡"Héº‡ÇBµƒP-@3ç±ŒÍPÚúø¢~¾3x±åz[ ‹öÉ6³âZ¶è°Å¶7·/†GÐë8êÜˆ®ùƒYü[P»ÝîÀÄü+#žølødœx\‹pb‡ÜÚ'H†‹B‰fzIá$—ºßÁÔlá¤N)²tÅ~´G\ë)šž#=F}QG‹žÒùbuÞfZ4Í"CsÛáPê¬}'·ÊÂ÷§ì#×šÇßjI# áBCµŠ¥ž™¦†cìý)xúpÖ ™ÃéH©úçÁô³÷²Ý¿K|ŒCl*{[íGƒ(vo«½G;@÷^A{¯ ÏÒ+èM ªÐ³ÀL’^d&óÈîbN±ÝoÔ’ŽÉgù	®m’¼<¢„--l‰rî;ì a6¾ñmfÅ™Ÿ¨.Ü	ìN	ƒ²-ù³ÙŽ¹›’åæ-ü"Î.Cs>§ã‡•lõ¥EKH41£0€Å¿$ZXø¢löÖ‹cK¥y5Ç¶Æ~2:ëÜ ûkkÎ–÷c?éõ)´<íØ‹•MŽà<3€¬F;uÔ@µÏ ÛÖ€? ¡»õE9sÂEø|ŽÆQNÑ\—{t4‚nÚSÖU<énsqXP´Úg£G_˜¥µº˜1ërf9'ÖDw0ôAÓZgMmaú_U¬R6*TŽæuPEM ¶¡rN%¬¥¯FP—
œ×ÁÐÑ³áÑéè)&Æð	ýÕ©#·d}vÌmðïâ?>°Ò›÷Xée±¹£6ˆ0,­0×t†3cJ°µÜZ<$7;|UávÂ'Ë«ŠNÜÕî´5½•Ü0Þ>x„ÆÛÀÒH¼; ­ñrÛàlòX›Âlïý…Ó‚²ýGË{[‚lßúÞ[siÂIç%¼M7Ú¼ÆW_Y;QâÛØ¾eEÁîáðYÅ¡¥T°{¶N;ªàp[Ãiª{…ªWxøÒÚÃM$øšïPµÄˆ6{\`Ã!ä_bl‹SS>~æ}ƒKuMSw–¡÷ûyÀ[<Ë3Ïh™Àtžˆ“r˜Y˜M}ˆ" …¦‚“5ÕM)þBóŸ/?ÎÀ¾Ci¯…þXvFÈW-ß1»¹õÉ$*æ-ësÓ´«#Æù.¶kz3qASü[ý¿Çmç®ý-=è­iuá.rø –{mÓŠgæWq<ÿkœüPþ×Jû ´@•_É±.·¦3‰Å‘ÔyÁ$öŸžSh¦	½ùÞŠÌsˆÚq`ØáiXÒ³“©A&b] ¢¸…åQèz_g>-aË=ªt‚©ý`j´hÎà³} j¼}­´mác:ø‡ÔÝ†ÓÜ¤™É'›£:Ó*©Àç;›8~+LÁ¿*šO´Yî`{‘§I¼c:¤¤´Ü-ô9«êFÛù×ª’R!A.Ôo~1²±D.3á@Úïêe›Œ
œÂþ^×a=¤ìnâVt³—÷W¦¬Œ›«…ü&MiÙKEóBø•7¬…-«ô‡PºZf©Uƒ®ß|À©&
Î<ŽŸSƒÑXŒïéˆJ:…Ñ^ðSáÞ©@i¡3ZÈj:®CÊ¦<Q *ŒÙ-[žä´Ð
'”þfëu›?ƒù‡ÞzOÚÂ3“u,3`îA"à¯C„(¤EÍš†‹Êáòã/wÅY€åC”¶ÇÒj²r19¡‘+ÎM¨®	¾YËâOwe5^m$Kžic +ˆ¾ÿpãW©Ë
"ïÏÍoŠeFÄ92†Ø3õëjòŸmQÃmwnÉõ×‡·„¶!w¬Ý6ãâ_V‰ÓUjMP×ááð¸ê¢=¿#¤ËŽ¥Ž	!+vBrp¨÷CŽiE7"²‚c¨ÈOç8yéìá¾Í>½—-Mc®;Ç'+Ã•¦²Ü_ÅAŽâ÷¢¶Æ•Q*ðxî‚;Ð»XÞ†–¬y©u<âôâŽà[˜tè½¡àd g`dU]×\UXnøÝÈO…›n®é;Òì³‡º\ïGžû6ÀKrHÞj¦?„Íï‹Qÿ~ŠQŸÓ”¦@ãËA_»E»F§£‹éz€ÓÙÉá'Á[Ÿ
­íyc{ÞØž7ÖŒq½ ku¨Zm/(—Duo€ýU6tcc“XàA•,»#îž6Aýmo×¼·õƒJ‹µÀU¨Ü=nÿ"h‹„¦.8+—dÂ,X;ÖUø4Z2PfYT›W	_£Sú»[o_º«;Åp´~€½IØ¥öÖìmÐ–mQö.PÍ+
s˜(§¥dÒ˜à×±…§x©¦ó˜5èiì¢ÄrnVû={€fé9>D†`ÕUÐÈpÙYÌzÎëgÿCoÐg•; ç9¿FhÐÖ\¶ËÅ±}‹H‚µêZ«	ž:)¸Bó8LSo¬G·v[§u™o½Ö†™Ù^ãV@Z’±T[ìp4\Á¸öb|ôŠ8'Ö–4'âØ\ ›RâÙv-câÊk]C¾ÝLgÙ­xy?EÅ2GÖ£,¡PÚ^:YïÉ’“‘Æª'îò>a÷5xƒ·ØßnäõÆJ{D¿n€¯6ÀòôÝÁy-ÜÑ³·)¨·ô¾ÑûSË{Û³Ó(FÆ¦‘Ò ÿ&ŽjnÄŸŒå5ÝqwH_}ÇÞ·ƒÍmsHïäYÍR¢ˆé=žž´z‡Ãƒ®`=n*Èµ÷•,‡Â»uþ¸!®\²
[„:Í X—`éûÌ?§FšûÓË Ršt©›åGÚÍéU¢ñÍò>Ê­½KµwVC"j[Æv—Íøx"èL×¾ïhÕ‹“ë*ÑmFi¾n¢ãÎ>¼ù¦&…,‡œ2O¥Rtÿ½›_ ¬WCó N5Í¦ÀzÜû¸á.ÿ—+g=Awã=qiqY0åÝÐ×š¨jŽÒ]‰9ÌJš`¾Çíå$¶1‰©$ÿiO^{Du ºþkJ*b™Šiòò4VÊ>ó]<¾1¿Tï°]µ§‡›s×¢±‚ &Y„ÿge®)ùnÏ\£4q*§Q>{Ov¼ƒ}¿’Åv®™1M³ŒXmƒ$öÇÉò~ˆ¹µ¹)§ì‘w¾3³…3BfåÛ]µh
—UÊä9G×7Ù`ŠÕ[ø®EÈ‹]]éN-r)û4©ê}ùã^([Ü‚ÿ~øÇÄŸÑfÖ+:®Jbµ2íÖ¢Ç"MãÏCË;äzû®ÿ\!pÁ…†¯(H+OÑgób*D%hÒ¹ë ãxõ¥ê—‘à,sÃQ÷ðX®tP§òx†×a šñs…àèŠŠO+ÕRüÖDPjâ¼¦q=á_·Xs=°ôuÆÆÀ÷4„Z Ñç0a…06îiºüé.TGMW˜5ÕÂÐ„MaìØ>?©kdÁ6÷xttÈd½u‹)×C6™Ù>FÏöRÛÍí×‚l^äÅÃ\%|·üQNÐ-J–Ûƒ®§˜Y£=ª–­Z³¿j¢
eâvéó#Î5nP)eë\îN±º~Çó@H®Úãô¶œ&gÓ[5;±w¾z>_M“7­…V“mm!É9¯ŸcUu±ë”¹ƒáéè`xBŽéÏýév­¤g=¥¸UöJJ®i£3¶®9¬ûß:ç‘‘ël+ÃêjyŸò°r»q@^°ñ(\mÆ†’t8¡;ÀR¢5üw²!\…ÙmœÛ©ÏÐó‰Ñ…
T)Ó‚Jk}ó¦1¤j–ÑœYaÊ>1Ê!ê<žÔE…T\Ïd;szµìªqDÏbæA'¢«ãVÍF§lv…ôvÆÙl5×ÇyùÓ¼WÛá•gÚÞ£j-°Cw›ßÚÜ3Šf#&è„£¿ŽÝúÍvˆëÒÅwë¶ ¹çíÔÌãá³J¥qÇ}2lEqÏ†OÖq×ñ¡>A]à¡h§Zêú©xHéCñb6SÜ‚ëxJ•øùrÒbBÆ(Lzˆô2‡œ=~‡åIÄh8
˜R®	h•}{õWÕÆWHéAÌEÍÐþS¦¥¸‚Ð8_7ÄXyÁ,µBû¬Ëã_.§…qM™xø
0ÿ@Ür3ÑÛ`¸ ùÐÂÉñÇ÷îÃÝc¸{wáî1Ü:†;ö?ìÚ¡ÖÁ·´w­oyskÄ<	'¹‘H!K”a5Ô« Ä.ÁÖs¦¶Ì~×‰95Uió‹®›° Sñ°¨“ +µŽJ/(`¯å+3MK_õx¡WW"‡ËýŒ­øõ­c›RôzWz…MiPù­äFö„¾"3Ú¹¹C.Û'Ü¡6'KŽ1¡-ÿÆ–Óà—8.œÍKÎÎ8ÄMt•¾³0}ÔxOt¨è[ Yj{OŠ-îºžo^~k¬Ïzñl·þ$!#)}Ù¤¢%z¥Aæ¯Ùòmã2Ìí$ýyÐöañ=ëSñ³f{÷$AÕ¯B²‰Ò|ZÂ¤~01Ü9JüÑ; 1zÌ5¦ˆÑ´:§E…".o¿§Aglo²®çÌZ`n%Oë§SÛ°\mYzyŠ¯›tá$—Ó/"»…=Â­Åã|îÌ!ZrJUÕ@íeÁ€ùrãÑ®«(uè£§×¬£`ÀšôáîPÍ†‹mjâ6Æ4i$Õ2p‡j¶½¹½%ò;sk(nå­ÿ‰²éeí>ßl|¦OF8i7¹
Bƒ)=:}vÖ¥Âbã|ýêEYuUùùA;ÆyxLÒÊU=usÊ¨±òŠó€ë"iÙ1s=–4¯Uê3¼¼Ó2Å*ˆ‘G+-'N5Y -^üïLB[ˆ…wøÂVu{íqZô’øÌáFí‚ueCš— ûö&{(ÖX‚~ñ*BÌ&iÉ¨cñ×¿ô,®æ}cÞÏ©QV4Þ9µÙ=¥ûé·YXòQ–…ò„s–@’tN{pðƒ)þ¼G÷ò‡{ùÃWÜËî €ü†·çRè¶YöÓñÒ)!Ù¯ñEéèÔÁåëûbVZg¤†2¡ˆ’S¿Pí IJ°à´ªi(¼Tâ“]‚•¿	Û®26O·ùnóÑ/‚”ƒ‹
úWdûÅÓP®ÃQQ±7*Xj:n¦ƒªÞmÙùh_¾¤íqL¹ðÆHÞÏÒÞ³µ6ô*ÂQl‡1;ö§ÙU(ºhA5\ã)~þ4x'±^Ø:ÐÙQ§ÐÙ[3)*ü,³00Š¶'ò ¼žÌ2(‚: ¿E5QQpàpþ¥¦î¥Ü)/±žüRš™>/XV˜Ý10IŒ«´'ßª{²è
—ŒÉÓ°†WOTÜ©gª5KòG'¦Øsl añÇ»±/·Œ%µ9•MƒV«ù½ía¬¯1 _ó!gçðUÓÝvÈÏÃÕ×¯.~ÿæÍïÞ¾{ Àú‹“ÓáaE±ÄÍ« SÔêÙðpMÔêõù×¯ª\¯¢ˆÍºUÎ®9<ydºå}EÛ&ª¨AÝ„#äcÇý,‚¢ÀMZ0ÆJcð‘ïhq²~º!¼ë(§ø=•ƒ!ŠŠw ë* ýÇ·U÷ÉaFÂ*‘¬Ûß—rªÆ¹bfügÍãh.Lþq¶€Î/’öÕÏž˜Ç­Líª½Õo×ŸÑî ­Ž«ÀÀô€”¤_|•d·†¶§Z’× ¹ßý’-Ì×~´üØ‚€½	’8¤¬HÕË>½Kcäb‹&/íàå÷ˆXÿˆØË±ªÅÒxÂÝ]0W9±¸Þ{ë_Òœãw¿Z~dµ¬ZðÇËû> °£§§ 7OŸu€§7«®‚ÂÂøxNÑ-Oä?ßÄÑÂ×Ã7Š§Á¸iäî;<}ÒA[œý=(%žO™7í]Ší{qØåíêMk^Ä…v´I)3W (¬ìu ‡ÿU¶T\õ‘ÝÑ*Í‹Lï|à>'Çæs„®Æë#ý^„2»‰?|mK¯+oØKµ.ônyñÚ`€² “*‚T¯Ÿª¬Þ‚0ýW"j¢žþenù¸‘¾Ô=‰)ìõãu…+ ø–v‰Ú½¦…6Då"";æl*cÚB3”F˜88ñhÐT.ZÔ¿k€Þ,åƒ;F®Ñ®Ç™_‰û*(VK„àHÛ3ÅF‡ƒãS#(W^ZñZ­Œ$-2áÎöü)êTÙSÔ}“ÄÔ)cIî&Âõ‹±%±qÝ©ê-çgÅ6öÁ¥ðÕ©&IÌŽ·ÇY’‚Jý`Íamv‹`þßÇ?e³à{z-,D—ÑXŽ¨c’^XxiÆ<uÑ™#QAÛF¢q™„JôÜlXX6S›˜˜%ð_(ƒ1¥ÅR˜Éø!IhJƒD>†aU•Ž[—j¶êje(1íE©‘»¨…Aø&‰çt?¹#Ô sÉ#ªÜÊ‚ÙÎ¢xé]S*-ì)ð¼Œ0^.©÷®¥¬Z€ºî¡Ô©“r9ßë©åÛh~²qQ¶ÉÓf7/¦kOüÒqIMdû²ÛHåj²fQG¡úo`û k—šÒ$^eô¹e,ôÝ½ðt¨ÌÂ›ga…+
iS\#QbèÏŒ˜6—Áì‚­Cu› °ôéØ†«m…¿â™6…_?˜IøúçÆw¶w±y-ÂO½9Ùê„­ßuw,]{—ÒâýÑÄ>obÏ†OÛØ“»°€ÄÖÜ­¥.©ixõ›ÑZ%Ab2Ã¬” Åv‡—÷º—Ã‡!A„ ?Ëp4—!F
bYj@(ñ`9‡i(U^þD)3ª)­PÔÓœOr(’h\hDj;PdOqb~GMÞÏ²PxíÊeôSøÛ8›œ¡÷"_HÅX¡ëçŽÄR×?P!ýšuÎ¥Ÿ§lÑû2ÄÙ<Îh`x_¼ˆ=Â€lá·¿„®óHÞšò”(VVïÑS€¯Ãmò;Þéûù7¤ÞÏ{E[\@TU>yçU«†ÇÖNø±™Ãh\ë™âÏ{Äv_A½¯ n»Þ¾‚z'Þ6)‹:Ä®®t²üñô³>Ð[ÚâZÜm)Šº)ÖÜ"µÌàžËxlÅØV^çBSÞË)0‰á` ™Ÿð‘ï9ÝšXMÂ+}s±$x‡¸éÅMª{D/ÜÑÎ¾ûœZ»5}‡Bhí)sã¥NÍžÙn[o8²Äžýg­r»E M ¥Ø)(ñ±cFÓ‹ª!µY(“óË²!šxQj_i•Hˆ,ü¬½¸¥|Þâ}ìòÞRV
é}¼åÞwãkBŽPˆù±?<gyØ/)%³|2˜%$³XPÍÆ ô4kLk{PQ:dÁ|¾LbT€¶Ò70¤û¢ýbÁÎ%Nöø&´·Ó£qu¡ÎÚ±ëzB-d}´ø¶œ5òjæì->0C”µš-¹ÒVMPãN†ôgtLw‰IÖóŸ^Ú¾”,j~
:OMçVH7QõiR<ˆtÎL¸EEš©Ê•:v›ŸìˆË•“kH¥¸õœ¨W‹ç^Aè8ÜXQÕwZ@,ýK½z/<uÈm}è‘>ÛðØp­-`G<Íæ cÒFùüsó[Û»í<¿Á‘)/ý~VMµ;€k·Û%Þ˜lÀø|¸\ùA¶ç
ñéðìi+Èx8<l•b\ßOgåßÓ‚!.5H¥ý$›ðÙp¨4¬e¡SòsGZ$n}[¹Ž!0ôÐ®ù©s7¯¡x.ÏPHžb3:´½ty¯!v-ï*ãÓc“¤±\Çé<|–çx‹O,_AëÕ4¶º;²óy`²¸½Ö²Ò]§n°ÀiöŽ§Ú£ŒIð@]ôó›˜ÏÔ›J¦û¢h;Q÷EÑ{@q_½/ŠÞE—Ò²¯hö€o/ýne¾r$1	Ö€í†×‚‚ ‚m ’œ­@—zç¡%‡¬ÿtÎš÷3Ê‘ù8–/¯5Ô9 "ñ‡kHôq@#‚àÍ‰¨¿ˆÁQ«©äò'~uµ¿÷N¬ËÏ×G~´15+÷â8›O(#þãÚ¦#XH›Ï-p6ËdÅm©më‘¿q®Ž\L)@é§¹[àHÉ^,Nñã<˜+žÜµá¹¢µÁtõ‚"$P4§†'‹±SWû¨(-e_&™˜"Á¨U:œ—$×±u™îÍéû"»Ô¼hn¢TNœûè°Vð/)±IYÌŽÅ@'–®9ŠŒZ? KÒ×­×Ö5[7þXj¶ó’ì
@H½ë‡Ì,¦µÙKú>9Ag~cÂªü…òZ¡ì/žÓñ¸ŽáÁÒÈ"¹ËÙM?=Ø!r†Á¥Qaˆ(¾kæÂe 1exÙ)´kj†E·µqQÕïÈyÈ,rGöâ„L\¯¨¬qó;µºfÛòM&«È,ÖaçÑÎB#gl‰Á<4ø&³»µäŸ¼î˜ËxÎ‰ý<1åñW9OFONGOU²«Û®Lº#J6]l+À2Ù‚&\_W¹Š²lyo{ªäË÷”GÏÙ<ø7&»§J6Üw‡à%®¾Kºd»3øŠ¢õ§Ã³Ã\É§­0æÑpMªäêšõ¯™×=µvS )^ÆÓKÆ£C¤ÌâN½©èñ¶˜rÅŠT¥{Ü¬V£ÑŽ¢8-£‘?Ïð|êu  —$ú‹lb¿œ}ÉŒñÖç½	Â91ì ½´^ßahî|q˜a„+š™%IÀðë4Ž–?A^	­ß¨xÇÌØ©xÅ1á³Ç0¥ïVs%ÍµÏ#‚úà^à'– º‡2÷PæÊÜW³"º¯fÿ;«fÍ›‡
½@˜ØÅÔWæ­ñ'‹Ö¢vñnEÍìò§	m–©Ù(ƒ…¶†ÝíÍ†45ZdÑd‚sÃI6`[J	å’YjÊò»„"¿âÝ[¿^¨>xÆ,Rž?t5—å›[á6\e3ŽN‡§#¥Huë¹Â@âØVDôa·Á­Ò'VZLpÎ<Ò†ÅDM`#rTÅC†A5
X¿@„Pž	,·Cõš )ZÆ|µ,”ÿ9e·ìA/d_^~A)ZêâËÀWï]˜^dW‰^~¨“B`YÊÕRú˜grèîÝðíÍm-•-ë–»¡î²ÂgbïMDÆ§ìáq2êX\ò¹¯F27Å¼¸—ñÒ†fjBFM7FZ$…e”Ð*Ç÷Ÿ.@çQÎ dñÍ’ÃÇ©k< È˜a”êµs!\¹‡4ØT®Ü¿ÛQé*‡K²‚6PéÖ¥¢®Ç…lÊozêÁ64³0ã}ñ(ÈhXÜ€è«5^ö‰¾Äœi|ê“*?œø¨&1ÖG›;[›Ã²–5T:\b3‹”ÊïltÂ²'7¿K£á¶ÍÃåÙ£„€§0E|º;P±éjÛ ŠüL›ÃŠ4„«¤ÄWlys{`ÑD9–%Lº‡n»K\—ïZs}'—íàâ³áÉ
'—aköÁðÉ“.ÀEiŽ²±FàÌ§øÀådT,¢bðyéy5¦Ì>ðù3¾„4<W]í§zè%ömîà ™º¢zliÒÊ1.ja¢­Æ!!ÉãL¬m¾=ô^EpùÀ¥Ÿ0´ˆm+›sÐÆx‰étºúò§+fe.¤Ñ;‘ûåÕôšîVULæ½Þ#ŒkÈbž‡ôX~nò§]¼WÇÜ«cîñÄ=ž¸Ç?c<QÂBÏÐ¦2ß½Wf+bëú~¥aµno~8õÇF¶4Ý¸ÄTÇæ±"©…"Üòc’šÐ:×9A-5C•¢Ó4ƒRïÅëðŽH{%’jö¹èîB=>•+ô«7‡Á˜ÞMKI½¡^#3³ÈÝínÍÙžA¼3÷KßÍ5Ù©Ä¥€•lÄG“’7Ø>’q<wbyž$ ¡ç`¥üŒY´D“9í¦”%lníOn(Þ4žh‡f‘
‡J™(½òÅL'’jÎÅøW‹ä†RÑchYÖ‰™ÔcÆ/Z ˆ9ÚÎ^ÏgªšS‰ÑÀ.JŽY+õSú|”ü7fô”ÐOÁ¤¿×e—­‡Â¾5w¡aÜ™“û¿åI!SŠ‚ÎRUºÊæl5ôñ©àÿ©bW\Rj*Ôï¢/+)*ËÊÉƒ_oe –àa>u[…ý‚Öè8ÄwŽq±…)Ñæ­ý.˜š?úÉlus‹ýM¾ ú!µ)_ËtH™]¡äv7«d$e¡´ÔÚØ÷B¿´†¶J­Ö:g]}ÕµÐÈæ4h÷}òü&¡çâ&¸š¯ì–qB/D¶‘"«Q[íŠØ­U‡pç9CÔ=ª¬ ïØƒµAû6%¤)“èŠ×è£ÃhâÁèñÙé4bh›À‰øx‡xbÓå¶ù±6Fãª9¶ƒßÙK¤AtÎ¾j’÷<§uzù·yPçpbí¶;Äã&¾ÎÖÅë«Wb‡gÏVa‡öa<<ªfo>G°)%ÍÐ6©„|ÐygÒ,a‚^@ömµñ(ÔÆ`¹Å8`á¥þ-°GJåÒ©Zó¼C	>äGG¹>ŽÁ(¶{y€•l.”ì1Ó˜Z¼©(¡ÒzŠËRtJ¤<Ñ±Ó'©m¦¨\‚Ì£¯q PÄ/©/p‡_£¨Î‹Ï:ŒÍÔDÔK š	ý*jë{Z¢NÕ=-q#îaÄ=Œ¸‡‹Ö¯‘q¨ÌÉN-,†›ï[ª«ß9É?>ld·¿ñ@DÞ´¸°‰’·Ñpz)k~³ü8á{¾ÉëÈLlÚˆ„Ôæ3ÔPœ$Áuh†é#Gc€u®Þ19¥æÆ_ø¼û ~„³Ðpy~mN!fò¢Z`”ºÖÊå€€žÐßOF]›®|ëOeäè8éƒc›6D,Î[y_Ñ?…ÇƒÄSø—½OfEwñ˜E§‹ÔAÖØ›3!L?#“©óÀkª¨è°qËƒ´_Pº>z$?ˆc²ÛRnãÅ@¤ûjêÃ£u{p=¤í«,¹)2§ÑòþÊô¢v›Šàs[n*O‚»Re”Dg&™ú5wjëi0‘bór£ KRžS¹÷iX0Ùy:ÛU¹ë'¤DŠ¹Âµxs|,2;°íW#Ò,"@ªñG‡§Lõ<^Ÿí¹:WK8zéÄ6–`Ì=„^aI9–È.]ª¬çÉ<ôRNÓY¶ü1õÔÓYS`TJ™ë0Û!-k§c«0 l±é]«_khžfc\mQ³ìvÀZó[ÛK³ÁBû’¦g´‹”úýv©ÑÅ{ ç¼õlS.Z·Ó£ái;[ïdxÖ¸ŸVjˆ·CÜ¾óq`Â1-©È\™hù¬õó×J^Ù#¦Ž”»xhhÐŠý„1//Hêå¿_â’)6ÔøÞ,ï/5â‚Á,ôÅYï&AOý¨¼0»fEØ(Ã±—ûªÌø€Cïö¸rÊY¬sþÁgOåˆ 4.Û=Ç‡´Mù*ÆÇZA1Œ0Ì`¸i>DÕ{G1ÁØÈR°'èí	z{dm¬í‘µÏYû­ŸL–÷|8glˆØ¸FÛU¸vNÛ’³çç*ûi™>Â]^Œóó¯ & ¬‰ÊJJ¨ œ£Baü"uŒ»¡^…qÅñ4>Î#‘˜¦ýòž6KÊriä²ó–¯¨ƒ•[q·X¨uãîEßD£"æNP¼å£¢˜µÁÂÝzMWŸ¥‡T—Ú³ì»@!šƒC
Žß(4RÃ‘R"+Ñ¢VˆFAŠìppKƒ5dÒVŸ›2çÍÈ‰l^6iÄh£ÖÇ©vÞ«è6íÕt%¿ô2…û¹~¼„J•¿™¾	CÃÜÖùv-Ž´ô¹Û¢çÄxã„Ï¶c¦mÞím@#Ï0™ ¥FåÖËkü|v .(ÒŸ4Vj%Q™†p–ï!-ÍÂH&,ç—Åµ@µã(Fõea~k]Ð‡¡wŽÊ&¦ïÚ>W-§B	¸Ê5Ðôc[ïìÖ4º©Ñúm¥Íheéc™þ²ê
 Œæ»8¼3‰R«mŠõËvæã0!úhÕEï”å0#RLïÆuýZß;ÿ@‹ß(õR¶(ÈéÖõ%e D«™‹®"»Î…ëæ,&xýôwÄ‰þtŸÏx [ô!1ôÃØžÕ”@O›f
Ö«¶=r¢PÐKAÐ±âXc7!€P¥U¨ˆI{qU(—TOl1u˜ß4ÿÆUÆqIdeg˜Ñ³ÇGf¬cs ­ôáî Ö†‹mƒµâª-ü«øÏø   ÿÿ Þ(6+