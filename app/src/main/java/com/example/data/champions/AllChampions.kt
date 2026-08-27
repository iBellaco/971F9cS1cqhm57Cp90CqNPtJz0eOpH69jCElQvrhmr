package com.example.data.champions

import com.example.model.*

object AllChampions {
    private val chunk0 = listOf(
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
    )

    private val chunk1 = listOf(
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
    )

    private val chunk2 = listOf(
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
    )

    private val chunk3 = listOf(
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
    )

    private val chunk4 = listOf(
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
    )

    private val chunk5 = listOf(
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
            spellsIcons = listOf("https://static.wikia.nocookie.net/leagueoflegends/images/xœì}ËrY’å¾¿":ÍzJeÃ¾D©­gŒ¢˜’j¤GTfYõ&í"p	ˆ@Åƒ´šYÎº¿@³ÓB‹²Üå²ñ'ó%ãÇýÞˆ@<@
D1K(ëN‘Œ·ß—û¹Ç?ì?<èÿà«øª7ÆýHßx±}_%:Nþ»;ü·½ÝÁñ`08Ú}88Ú;þ/S•\}?ô¥÷áßtüÝŽóÝU’LãÇý~œ¨Äs{ï½wžê¡†ï<ÝtÒ÷µ§:¼ôõX£¸ïMÔXÇýËþåAÿÅ8ð½ìá»‡ƒƒÝÝýýƒÃïþ¸óONáné‰žÄÎ¿9¾'¯/|÷Cª£ÊiÇWÎÛÈ¼‘á5OS÷Êóé¯c7ÿIùÞ0R£0Âù‰ŽÔ»Æg¼pÃ ø ûÙ£Q¤ÆaÐ+}dÏ'}wôG=ü}ô¸O_:éïï><Æ÷m·ÎM-ÜäpŸoRúæØKRjË0P~Ù¼OUY÷™:ÑlÈ0á?žÅS/Pñm÷kÍ”Gûûûm˜ò°Ö
ï¨»¼ŽF:¢Wýî¹z>ú’3pþ›“ÿ¶·ðÛ¾óà<òÂÈû¨"ç)êÜÞúãw5·.X`áàé•šLÉX8éAì‡	žNßˆŸTÍ¿«˜nLÌžùôXåè M3±Mƒ£gŽ›_Îó‹GðSäã·uL7U1½€î_Ð›þr.¿Ø9Ò±yS´8nÿTÇÓtþ9F™ÐKF;®rbíDZ48ÔÓ0RþD‰Þq¦:
8unÈ¨rÚ(uéŸù§)ý…Oê9iì¨Dý5Õ±Óí&éÌœÆÎÌñCã<ØqÔHOæŸøñ^pé{cÆ»šÿ-¤#žËÝ’2žÿÆ¦ãgÒùð^#í;áðZS#†=ú2šÔüQø^Zê/õí6(µ[¡ïÅdªÜ„^(ðþšz>¦¤M6^<Õ¾ÏM÷?ë›ŒÛÇUÑX9iàLCas;ôÿ;ôw²•þH]x¨1‡î°M•£Èò1uC=ñÆô}´Èe„f£ÃóÏ>5žÇÄO¡™èÙ:ñÐ¨é/©a¤/,ÜMù.& Ü/Q“¡7ÿLÝHùÜéÍß©ñ5Ê‹4ú½ìHôêi4B×(7ã`·ÿ¨ÜØ?ªÌ[õ-º×Ø¢{…}FAÈý‡zµê¦1ÿ¼¤1iM£0¡ûÐ×hU:)…d!êþ®âeiþ"’Áxdº˜º"\¨"U:4BÆôâåævuD-DVvNSÐíùá*Ö1-t²òyœ™óil¦Í¼xLT@‰†‰-ýf$VZí°?8èöûƒ½þ`°bËí7¶Ü~¡åÞ¤cê'ÒtüÞa7­w¶¤õ¸wóƒ[Ë½
1ñSaæÀÈpylšH³!FFBkšxyZÊŒåó‘´0?òDKmLÖWÑDadòX¥NDJÚ‰ºN<ÿm8òè©!~›ÐjžD)Ý‰†âˆ¦`|ÂTc´9WÊ¥EI%ôWê/Æ2÷¼¯}w°©Ÿ(:7o áºƒÉ€ÖüßŸ}“ëÍè#o4“7©…W/wô•¾ø»ÌA©Ëäkz¡ÇœM†ä¯âáÞceèotéÍ»Ì›å^zÂª&3-fl?t¹Ë»‘–)¶çœóˆw1¡’ÛB£œÆ!/²hîÍ£®;yHÓÀ •5f¯Ù§IèXcÛjºiœdmK«£È*Y¤m+Ãm¹LyŒx—#ºŒz…g½Mþ‚J‹ìR{îövÚ³Ô´^üFc=¢+.•ëòÑ¢0H|/@cRG.ïù£7Þeò¢Ôjïß¿ïápD‡/é07×8õFº@¦äõ½^éD•o}?¡¿JC›.È÷ËW›×8«¯a^±
ßˆ‚+{¯¸öfè¯OÈÑý9*ÝG†8ò>ª¾Ówu¶gÑ‹õ`íïh*÷¢Ò³í¨¹h8fÇLå€?/ñùv/•s¡ieŠl‡yBóÅ{uç×>‚Ô>ÃÎ£¦×V7ô¬²%ï2¤3+óÝeT/<³·ŠfoBþØ—´TâÇÞÉÓÓRd¡]¬r&"ò§’ÙKKþXþ\	lžTúY@CO98î•^Ãsß½‘£û½ããR§R9¶ÛÛ-]H^;ÅüogS~šýÒ;þ—‹§'/K_‘Nðµ™¹ÉWáÉ_]Ò•4ÍÓØ…ÌMhšŠx6qÕÔ£9ƒœòBt a¢©pŒ?[ïž&š¸hÒ$$ì÷\\¥00M…Qxƒ‰Ç,tXIi8e¢«Ë0šÈ
¥écE§ÓMáUõDÑŠu¥,ÐKáÉôƒäêðÚ“¥¸kÆâYÁ§öí|ÉÞWÐëõÊ]ltCN2™íd¬<ZoáóO5Îwò‚ø÷eú¡Šb¤4ÓùdVÄJ(Re äg58FxßJ(L‡£±§‹Aû_Tœ†|ò§Wž /S?-_œÀ‘t•2ºñ\ª'ä‰ÞhêóhZº”/¦¢‘1Ù1­8ÔÁÉ•CòÝ>=¹Áh•?‘Uá£+â½#ZßÈH×ŠZ&5ý4ŽÒ©ò9°¡?Ã“–›þZÀJö¦E/œÐ"8Ò£7iÀ_þÝiPt†æ¦µ}Z@c¬Xåð>¢ÞFZ?¥‰Ýã8ÿ»üäÇ@¥Òà2tþßÿú¿Î³ÐŸò2û,bO	{©gèo©3Òd¬?Ê_/Â!Ý·™`Ù&zô‹ª{©÷˜w&)ù.33/QS¤­?õÉç¥¥"r§ßõ÷~è?¦	÷ûõÓþØ‡Êïô¥¢¼3èSxþî{âÅÉŒlÛŸÊ÷ÑæÂH.FùO5óYÁ¼pg0&|5t(ÀƒìTº$_Õ„$Ý‹|ø‡ƒz¾Ä0/öXÙšÏÃkÁ4Þè™sE©‡¹I ÐùßŒïõCª)>x#HJpè~Hæà° ðÑÁáÐ7Ô#%aä…Mž4­ãæÿ›ÜMßy–*š·(:é%c¶a‡½£ @ô•GA¯Çc³”Ö+ƒ6eXÒÂ!ù]û4ßhDŒ©/kZ›1Pzc&z
¶8ì¢¹ØE—™›L5†«	¸6	Mfû&Cø/èÌ„¶dªFdfTQ„©ÌÄúp‰°¯HÖP&X´Š¼Â$”HØ™óÅ$×+©†•»ýG½CÀôßU¡‚ÕpÁ7d!÷J'Y±|ÉÈ,Eªˆòs=ó¼L7û¤sÔ|Fëø0DTO§æÎ•Þ²S+Ø! †‘€Æ´òÃ	CÛTAšVÑ¼3A9éí¯Èãô>†
–o€óN#-@¹ðNR%Pú õäøü· ¾Ê¾JÆ:LèaÉ.¡´X–t×j	pf»0´ºtf¡&4Œø™ÙÃÊ²wÐßÛ£±°7è02­âg™'ßQÓ4ÂfèúôýhL.Øoð,<…àÑíÌA,&¡ÙñrÊ¥r&@Å%Bi2»Ýð «eÃ£çœ+Û•á(û_Ú/)•;Åò 8o8]ÝßØˆìxUˆ¬Š€-"d5ÚºYI¹FFW·’ÕÜm-”ßtg˜ìŠ(ÂnÊ®	•‘kJÑ$õò ¤“z%¯P~=PVûÚ-BerÿÕÁ²?ýôã³—g·àeÆÍ.zûú¼8{ÚœõŽ³Aï°Ž³ãAKÀÙ‰c´Ã€}Hs¦ûH¹ïtD«CàRŽ”¿Ã;Ú‰¼  …ø!¦»ìŠcÅ&ŸË”Œ5
#mfšµ–ÅÒ›”L_¡yí­Â&/‘Ç^«Ù|ä­%X0’@‡Ä©“îÚc@“ýL‹B¤Ç¼% ¼Ø§¯g¿‰–9òµl	¹óO @CuÒ{«œo@3v"I·oBÑÎ&Sº:ÚbhÎ?†vJã’Ct©NÛ,‚¶Ûß=ì_LV±Ž_™}-\·%n‰„["a=nö\SWŽi…r”Ò~&ËÕríe)4²îÍ[q'.é±L&‘râ41-YvSSŸÖQP#æ_âI™»„’7…rÄ¤rÓIê+ÀÆå‘KK´´ˆcï Dªr¸…x:ý1Û¥a!Ÿ½I8¯j,§_°V¸4[¼güE/¸‚„DäÐQ+NÉd>Þ*xei,Lz‰ØyÄuÌ8Œä:Í49T~%PÞïÀ6vö{Ø*ì÷ë1:BŒþC†ìÌÐM¤@cè éTp½ OPgÑ“0¡ï¦3š,Åþ™Aöl×/pÎ‚òðúZÞåjxßó0NÌŸ=„0ó/Yº‰Àg,-,Cˆ£ˆ‡! ¸qê†"±V8Œ“ù'ŠÂØ¿ö¥‚IxdrîÐ]±°°ÀÂsÆÞX	‹º0@¶Í.œ¤Q¸HêôuÖ€B”¥*ànð¨?8î’ÚåÏñüÜYC5á¦¡„6‡½šc®aàR£±éâáö"¸á¸1x•È×“·XGçaâº™¡É‡i"ùóbžïŽ£•À0aÒÊ$ÅÍX•Ô´†Ðlï°ÿèþÑßjÁŸ; {r}{ø^ýýÖAøÌ—Ýã›P»5c|G×Çøž¯@&Ï±óÒWÔïZ‡ùjß¼E˜Oîß&'®Œñ½zñ´ã»hÄø(à98hÄøöä¸£ÞÑ~Kßk×%‘àsÀ‚¢)oY(òtN%‰÷y‰qŽ½ üà7ÈÌA›Yø4M)¡—Óì7Q£ˆó<œëð+¼`=ôZ¡È—¦ui$@Ý_ÓùgldÃ»&kótu•w>Ÿ¨kÞëÖ‘§|‹&JwÐ<yª›0’Ä?¥Ô9!w«€Âz8m4·»o;Ä·Žð‰n!Éýç§W'¯^Ðÿþ¹3„ïFø[_ôo;_†æ½”ŽÔ„úÑ² >àü?b]æýÞlf=\†aò>ŒÞ-þ¶åÉmyr[žÜ’;nyrÀûÎà=-##¸ÔÁ7ŒPXÜô³”-Gÿx¾ˆWµÀ¹/PhíšÀítT!!‘N9ñåÛ(ÓZ•ÂÉp}ÆãP9ÖgÆw“]zäjÆ%dÐ¤1J^ÒÆç.¾Ýpþ)¦U¾¸zoékþêmù¼&Ïº.±Kïæ\òD*1všs3l-çü'
ˆÁÖ£ˆ˜³ Wˆ˜-”µÐ4eöû‡ýúïA¿wØ*È÷ŸŸNÜ«ù—øŸ;3p¸w’y‡²·rÊÆå¬™!ÿ‰z~ ¼oþeháééS;0ä¢³áÖ€n”¡ˆ<·?ØmÒ;õ‘‡Ë<6jÿ.S1^žgL«üìlóK9Þ8@ ÚB3Œ‡ˆ42ZØÈ6“”P"±CPK¾ëê9nxË£ƒÎŒÚ„½£úÀ(*“øS¢î›˜,”™,èjCºY¿Ê4-ë;,$C/nÞ˜1¸)ÒÉsñ[6‘ó‹Õôzj•}úÿ½ûÇ®«Åeî€ÀÉõí!põ÷[3_vgŽzBüVhýTÔŸ½(I±:™îsšjòÛßªïÝ"òF7_v»øéüüõ›··@o·¦£6³êw+§Äm¯÷¨ÇYu{«!n¯Nž½(£‡…\ÔðJÌ€Y”pš(»"7…Æ~ª'ìÈ¸²—¬“÷êŠ/—{ MDqHsUÝÀLŽÃÈÁ¦æQ²·‰°‰Ûl;ž
?Í¿Ðd$bë °@¤$aÁ¿RLwï‰Y[å›•Ï¬=š<i±…
XF<-^©™Ìˆ!MÛÔù-¿y¼?pL?oÜN#ºšQÑ®ð¶M½óÁ“(½Öu‹`¶ìÜÇÎeôD^©`þ‰A4ìZád³9÷¢Ð§ŽN#¿þZmˆŸÉ[¹_8[F.Ù£QK¶øã_ë_;“lˆ—)õ'¼Ý¨û‚áØ9>úDœfö™¦€ûKÀÚÞñaXÐñ£Ð¹ýzÙ2`Í¯aÄ`øáß¯Â`¦ÌÌ‰î„Ï­ë¹×‡[°ÅÑ?™î«Í´f%ï„KG-j¡´_èµ#
<`×¥Åˆ£ÖÇ²¾Ye!s©ˆaq*€5 /X¢‡ˆ‰<AVxÃr¨¢r^’3¤;C#j‘?ÿÂdaç+eâ±k¢WÈ¡ØÑÂ*ÞüóNÙL(¬Ò…D(Î#PfU•.{Ç^©;§ð•¦4ÆÂô(¤RÙAN™È(f6ïÑ:‹1f&„fcÍA­DƒáTëR³À_âàü7ŒÔeBv½È!×î	ûäŸ{#[w£üNBú &˜ÆNÀy™añÅvØ6ÂÔ‰Ôé¬‚ÎÆN--«Õ‰Õ9¦fzX@—g§*þc!C¯À˜Si”kWŠ”™Ñ³Ëp`ND…¹,¡É(ÌtE°ó£ñ.©ïVú!n5!oíæ'ŸD™âéXß`~ G°›žÐDV,ö„¦7Dh•Â#I9¢AL‘u™e‚k0øM8Z’›¬¨Ö™Ž±j£çÉ †B™Å[£‘oiù*7²] U-°yÒ7Ù´iŒšµ)½RÓîí«Ì˜ÞÆäf;ÕÞ±Ëˆ·} ÂžKŒš1{ûÓ+a›nnvÓ–MèmÖ–`Mò:›/Á¡4baQ‡49/Ù[pËçùŠfyDÈµëmÏ9+¹ ²hÞ¾ó†wÍ5…]º%  w©¦±ÛEõ½Ãw«ÐßÀ]º¸=d·æfëÀºø »cº‘z×Œê6\×}î]g˜îùXF6€êÖ¼w›¸.nŸÝAï°Ø=èí?lv½½"¼.°‹ˆ¦u0ÿ•5<u¡ÁG9w±ìøc‰Š!<ž×”ï³ò­¯‘®y‹Ìž SåõüWq2S¤Ò&~ð²Á0a–­øA`±ÅŽœ¤3X˜N¢öÃ|HæÅèÇC‡3£x/¸ÂC¦¶'Ür†§GÚÒ@é<h,ÁÒW[\m¿²{T¾çØÝ»[`wìnÝoØ½Pþp»Át±HÝ"0(£ÈPäL5C­[žCdÄºª–oÔja“à†#±=Ïº¤ù±%PÅS¥ÙÏÓÆ{ç)‰YDI‡ž}ã˜–t—Ãø‘ª¡YX±™\Äy›¦aLÅ…zGn‘cí`!³¾©8|ÆV1ÀÁÌJþsR¢ßU“5ÂlÀXÑ/‹cÃÀBýœSÏ. >xÐ!h[™¥Ë—ÇFëï÷[ükJî8zLW»Me1*fpx¾ø}EH²Z¥G h<Í«=ç'SåÇ°C½HiÞÌ0bu,f*í º¹‰Â*X½¹$bëžweùF(Œû*MiÀZ»2ì‚‚_}–:Š¸æŸmên4a¼ÀõSÖ°§©ëÕª8 #ÞK:b¦qÀ
—·YÕÝn-ÐŠ?ëÎ°Õ{Š¬›P«†cë'?ÃÆÇ3ìDŠ_þcø!m´ªyí1+Ü}uÈêÕ‹§·ÀUå`r9+(ÿ,E²šKfô–p®bVðÃ’dàzPÖŸhâñ(;¦í)ä^ùó/ð°m'0—õ9>L9á6`j£° ižºážÃÄ¬‰ÕsÎ±OÏÖH1€‹1gQÊ‰™¿ÓÄÇ% 4«~ï#-ôGãhÅ5?dð6¢‡*–"Ä¼eOòÎgè³‹&ÉÃ¡©^·-žÒ¤UÀoÁ’¦êõÅ2€üÄf‹mó/ÊíÉÊÔFŸÍ?ûè~Æ&þ
ˆŸ÷¸„L™\ßÀl±™ýŸ:¿ÅLaÑ‚¾_¸V¤iŒQ„w£–Ð¼¤â«Ð}WýËåêå‚#mdýžEõK~Çùÿ{‚®JðE(q-VšeÅÏ†„]·Úß<ÒupÔJ:ëQr‚èÎzHÔ;ÕØMF^ýÞÐ®ƒzeÄßÚÅ»	¦ð$-Ú7›UÑÏP/,l¿œ7«šwÉAÌ‡ }Yy;9†
¬Â35á¥ØŒÝR¡Ñ:Jã&²—
šÇ†µÐAÝfÂÂZM´;¶¤”õunà5Æ†b7Ò’u+…(…YÚ›1«¨¾•êBæõ{­f¢BOc•t3<Î±)æšr#à«¿ß*ôõ³g¤Ìãì¨5š°/n…B¨E}-[(ìÃy†Y³J.#_.€
’â[@nPÆ7•9…Å…‚³Á”%%Ã­îì¡@„…xZEÖÎÉS¤ÔzŒOk"­ÇF±¯Œ‹Ìµ¶˜»QŒƒd€œj2Øå(9ÃŽ.àeu™©¶÷›	N4ý0Ü˜,Cyy³.âoå,ú\í±ÈâA(…„mqŠÚˆam¢âju@êÙQƒ7Áylu)Ä²0èÀŽ?he²ÓrcÜÁz>ÛNV0yTì:º®‹e³‹Íl¤LO0ä8KßDl#O¤˜¥8Ô­¼à‚îÇöá¬i&oš°éò9¶G¨\€ÖA-€K–¼&ÿJOÈAGÄi*zóâ»õ]ºYèÅGV/Ñ{$ÙšDa`u@W·‡GÖÝm8’¿éÎhä,E$º¦ƒ_Q§×”‹ÈÔ[F"ëÞ¸E(’oß&ùÔ¹ƒãÞAs©‘ƒÞn)ñ9÷zƒ¹&wŽÍa+ôRÌãA^„.VÎ5@=3™’«%Í¿à&¼xÆ‚Mô(¨qoþ7E;ªD@Ð²¾SFMÃOÁÆ}±WE³8(,¦>7NÆ¦Wð¯¼:¸ñÍ ŽDVº«<ˆQEð¾õ52K¦`±c‹´j.ì¦Ãˆ^ÐdBCP'å²%ìm…MXŠ?žÿÂÒæFŸùÚ¥æuS¨B=xN<ƒu¯ =Î~ì¼˜Lò·Þè)Ü 4¤o7Êù!LQN…
9|†úÈøÑ‹€k,Åöº€ÕE,ä>’#þlø?}YOÞâ[¶Ý–m×t×û†?þC°í¨Ë@¼¨-çÛÉºGîÌoˆ„PvÀ—ÍL°dá›%«™S‹©í[uªB€hè ±ÝÍ+vFªiÔ½³øžKÈ®óc»ú\@”ï¤˜§ÔÈóÐò‚F7¡Ÿ¢ôÇcˆž £(Ô°hs~ÕS–8ËÖÍ?Ó»èø±s’§º’wvUFžrh"[Ã{‹^ÀcçìZcivÔâyÝÑŒÙÒÀÕ„»JKÒs®Sƒ.-¼žÓ@?WÎ4˜µ_8Ö¦WÐ_¼)wØUú(>Kª†¶[ý¤Úõº²uªjm=æ‚®LÐªœëjXèÆÖ;Œu^^º@EVaV¾°± ¬^ kªeDERZEQ›sW­Ñ„ªšb2M¯+ èÌäÚ¡abÓ2ñ’±P¼Öc¯½³[½‘¾¥êIaq°êHX¤¸0veö&lSÌ^P¤ld&ÓdÎ¥L*öûU®C…î€÷ñåí~µ·[ñ“Ïº+ä‡ìÃ™ºjÀüÞ6]ô{«¯5Äg1ù{ºÚ_ûÕ¿u{ÀŸ¹ÿ†7#Y’@»×;jF÷*ò‡EiÄG¥ìÚõP@c²ŽËµFL:-€¢«4’öxŒzG`1Kåæî{al—J–ãÁ@®ö¸p<¥¡“-lÙy©Oƒ•âÙ…Ö,·Â¼1ÈI’xkdÅ€z¬ã Òdþ9 îå˜=mýQÙ5šÉdRhŽL[¹Äo÷Ëšü¶Ã¯R+£›O[ ðÛ ·µˆ¿¶ñýÛ¢ß0úw‘F—œ’@-ƒ¢®Íò¶<çÖ®µº‰…€ŸùVF„Ö>]¹´Ë*sdã¶’ Ó¡Ö²&ÊA–hþéÛÃ¶5MÞíˆâ5³–ÚE¿ç<5l$ìúæb;p‹¼”Œ¬HsêS[B¸m…H1JÝãù'èŠ²ÌÔÓ’¡kßQd‚õðn—îˆ„J@¾¯jK[ÜÕ¤±ÌÕ5£U?!ÿð°ÝFZ^'ÄÆ3€+Ý 6VýóÏ§Ë{<ÍÐ¨]Ö4™ÄÐü$¯ÐÍtkÈ*CE•~ÄY«ÈñÖ¾w]%¯ô{(ø<è?¢Ú…ÚLmˆíD"ÌÓ¾l,Ú ³Y{›±ÁHôUò÷âReÅ`b¯”+Œ\šØçÏ¡#S‹;Ö61¼ÆlGƒÉ ×*,êv…ðj‚ŒÎìÝ€¯•íÍe—'éÌ}‡\et©Un´Š…ŽwûœÒ{ÿÒyëñ£Õá4s}kxZÃýÖ Ôì—­¨…M)½oŽ}…Ž" +ƒzOUE¯©8­òÒ­‚iáz;ƒÒŽ{ƒZ¶ ¥–UYJ»µ®/Ç$ïº©{E³¡ÜwiTˆ˜Š \žá‹©úÈDßD¹Ø?AiH­çÅ˜w®R”#gL‹Îã}gå³r·!XùcZ†±•2Žæ¿:còÀ®5WÁêLŸ7æÂk‘ž¦‰M–EÔ¹ÿ,hßH×™Dc
NG\h	o"
 Êùô}Ð9‘Ê}³-ªÆM¦v‚“_d&è
P;ÁÌŒµÚ©î[WÉ7«ø‹Àxl]zïýÎò¿.Œ¤£0ÊÚbfcf9ç .aÄÉ |'«¥ó&•Xþþ¦“4ÅAS×üºWº‹º¾[H»ì}ýMŽw¯ë¸’}¡£™Ï1Îß¯ªïÑ‘)L¼­êKYÈëèÉWáu'¤9^»–¤ëÚòù:gBÿÀllf1‹ª˜15ö¤0š$>.’Þ ÂÅEöitìœJÂ’<ŠÝ!¡8Ø%©ûæ¹(úA--®—‘6ï3	)†{&yXK1›¬…tÛq¶X›@ÞS‚p©(„<x(]DµaSr°±å4ÿMq¹VwáæÅ4ùNatµÝQå´SÉÝžÂ”¸äŠ)xÁ›ÈrÏó/	3{èö&o ýZ‘SÌâçÜ§µ/ùr!foŒÜ—.‹¬¾Œ}÷‚Ê9¿¼ pFßÌªÄ—ËÍ˜qe›¬F–¯wi>úïª"g«Áxo È¢i‰°lé´BŽéB 0ûü#56p’ÏI‡’óŠì´ÑLF‘-Ír9ÿÂªýœ÷m•æhBáLòB]Äbaè<Âú `3òóJ%_jK¿F_;La ›^%2=ê”†l¹ÇÄ3¥lLøÖQS6Ã‚P¦$$†’›*Ÿ¥š
uŒ3S‰¬ŠÐVh×a~2
Ç(¸„äXM&ã*€ À(š2‹¹:á<fäf	¶;ñà±	@lv¨ci®¡FN*3qËÙÉ‹!WG­Ö.2D@qŸ+õL‹`¹EKK¹ JÂ¹FÆÝ,ŸW(šbVO{{ðÙS9“«làY¸-5_ŽeJ-;¢é.ºÊÀæ+Aì"§ÓN%”¿ !A£[ÊfƒÝ)¢`Œ÷urNº9 µ‚²Ý	FÛËB®½Ûzjx÷,äD“¿Õ 6[@=ó3ìnDŒbüLcácëèiÍ·ˆžâî«£§o_Ÿ·.?Üíí7B§½½FâàQ…¿Ø„¾}óÓÙ/ÏÿòäM%«º’ì±
-8_1rjx-SY	¶|Q3¥;,¤Éls(°ª&Ê`;ËN˜(ßNRÌ‰&¥™fÌ4öTL§ÐÌ…X»CÔŠ¨Ã€Y{F#9•ËÛ¬¹š³Â„Ã7e
@(G%ø.;Ó3ºÚ7>uSß×Æ•ÖcÀ¸ôN§ó_Gžx­ôIß<€*M8Ôé›ÐÓ·#tˆàcH+MÜ|zN”Æ‘@ž^‰[™X<ý±cqAç¹þI!ÕÎ	Ù_ü6Œ$A«¾ö‡A`ï„êÉ2ùÊ8A´óNÞfwŸ}¼ÅR·Xê7Œ¥>cb´+{Ró­<KÙòdÏ¬w±–L6‡eS°"¡Äm‚ÊdÁ²©fülÇŠ"Y%JÆ¨æpÌª@Yp‚sˆs’„uˆÅãàpÎ	„ÈìÌhÞ7t8ÖD™
{¼>Ñ#ï
k°¯ì]<ãÉƒ²ø•ÆÄ%øøÐ"Œ¦P	Í5YÒ±ßÚúÍE#Hh8QAMÖ_«®Š¸¤, â.P;ª½ý1ú,ä}S9,æù±NOÂ97Pýj³]gü1ìÞ+¦å zÖºdoW‹Øl†ñ«Â0Uo:²t>jG%÷oI‡/ägô8ÛesÕQž|*]¸ Ñöfpò1Hž¾Šª%…âÑ#VÇäïà]°ÿm¹rd…\kEè“¯É*ox5¼TÀŸ=…Ð»6™RøXFóuE2reàª¬Ë(t’ù¯º!¸Â”MX0íæ…ãmTÂ£jaßd‡ñp2-òÝ,xíÛÔåˆñrÈðÉL•MbNeÒjí¬„H™³	îdûåû‰¼žLR4ùsˆÎ#Ç`Æ°•A}^i»'áë[j}‚[%«[¾ÁÀ•Å½åhm48wuþ8æÊÊÖP9Í¿œ¼Šºí`-8¸PÏKËhuqHÊH èVÃ:-ÁÐ9)·[kÕ¦w{{‡÷Ž;ZÝòÄÕíAžuw[òäoº3äyEžKcvÓÁõAÏSløØly1™úØõuëÀgÝk·ˆ|òí;/^|±õ|ÔŒzö{ƒ&Ôó¸÷¨D&]3÷šMÂ|PTr¥µd$b$ä(¤Ø^“ýt5b’`(ü3rÅå‚§ó/¨T`çÊ±Ñ1ò-Ï¸ç¼N"  c‡²ÌQ÷)æ{ÆàåqMbQ²Ýá­öù¯ô¢nJOÖâ/fBpWòiÎWvÃ=Aï9'Wx$ï(ÝP_›r#0	næ_Ü„î°Å<¹¬PÃø%d¡®·…_x”n¿l!O3Ü·…_¶…_¶…_j OL¶:è®Ð±¬d·$]Ëj'†ÆXEÜÚöq%C°ŸBy6-GÿŠ0›1KŸ|$›ÁDj´öÙš.S^f£ážH‰ÎjÂˆY›xŸbs(ìL‹¶ˆ’ká*tŽx¥>E^ä‚]06%½Z/òÃ$êºÁt¡ëÍÄ¦ ‚¹OŽTV0¤GøšÃ>òyôï£V‘EC×ã¼«.0%þø&TQ,“£>Wê5%~¥d7û;Udój/5Áy<2"×÷\õgÏ°ÊILÉ‹mûš€¬Ô&…:0ý•%ôV1Ôl)}Ý	–¿®‰9)ÍA]šX‡e9á¸å¥­Pæu4lÍÃˆä2ÁÚ¦ºû q€å*&òU6(ÈõÁ,æ@–‡^¦ XSâ¹ …XÉ“Ç6 ´•¡µù½CXƒÝÞCü·MœÑD$]µä›s„;õÍ‰Z×ØÎ×.¥Åy‹9V…¨å×	y¶¬†ØVí“Zàé_ÞW{»uP8ù¬;Ãp“˜›* ¼m>¼~÷I½hì²þ…^­Ãpõ/Ý"g°:wò´Œa­ÏB|²,ûa	V+àqG½ã&8n·7(•n^;ÛÔGæí%R0Bä)Y(}X˜%2F8ÓYqîïX•BK“—]q8†²ÁÓœŽ1š§8…É¶JV¶‰×iì÷& )2»j¤25\ô'r§ÉÞHŒ`¢ƒŽ¸"•¹ð§ÙèŠÅé=qÓÈ“‹ß;‚ìïo¾sÖ´BPo¤ z¬W")aÔ¦ ãÙ¥ÛÉßÎrµ_ÒW^ëÆœnZù­óÿˆuyZº7™Û4÷éä2“÷aônñ·-
÷w zFÇð…áWãŸ‡×\YÑy£g´ŽF©ˆ˜6Åjó¿çMð¹7†ÒópÐftØš÷èîÌCuLæVRòïÊ=$c¶a‡î!¯. Í¢ g–¯å\±öGžÝ[}VBS82…Â–CÙ$|Æ6ëŽf>³	9³VàÒw+ìyjË)c+9°B¯Ó*ØuÁ	yš¸ýî×vYÃ¡F ²%uÈlâ+‘#6Mý¸÷½åÈ
°¡@ò„Ù'Å°ÝŽÐgò²©ôkÅê\õYr<SÊv½È\Y£ò.7ÎQ¿erÛ)ôÉ˜NÝ5PÅÍÈ+Øv‚‚“#¸¤˜jGT6£p¦É†û
è×÷Ä‡ÔODù[_Õ€*‹ØÕ‚Á‘ãb•“þà†4 TäkC_œqj‘Œ0¢†Â¢lJry,éæU¤øÅÊ= ´ˆ4ÃÒ­òòã†î:A1/›ä•€ÏŠcHj Ÿ^×¸dB¸c}±™ fP(ÄÒRjÇÚ„G¹¾Ž›í+¨„Ú¨.Ñ÷ÞÑÝê‘˜; mæíám7\q³wwÌmFãgB}#ê¶ä„õ)pˆžÌ?E1—	!-`n¯Ü&êf±Éì_Ñžk¢Âý×f.Ü~o¿Y<q°ÛÛmßöözƒ¶Ô_LØ1È8ñÊ¹L#O™ÔÜˆÖVÞÆ‚…,lfÍêÄ¡¬N¡:HÌR$rŸ"í_S˜„KÕ¥'4´!Ù$Q,<mÖ,.ÛWÐ8R(¨ÒeÕLfãHž„í5F Õü·@ö‡dÛ‘=êº—\#%t†¦û*3>
Œ!žC·è[ÖËS€à.á© °%ÄýÝA¸-!î~CqM9À§"P«œ×¶Š&E*ä·ü¹´Þ»ßKö/5G79¬'5cpO™Ó!Ö}e?ß†’Na *qÄæ²÷[Aàþ#T‘•‚a¨8xí…³ËØ-T¸|¹Ãâe¸+nž¶c¸a&¦Gë˜¨ë™ÕOÒ™x3’«ÉÊmln¢¤$Š“C+'­ÁÃb¶š›gÕ9¤P†Y¦ŠQ€¤ž/)‹*¤“v™t…u,Ûöh@OŠŠÏB‰ðÎ0êõ$ÌÙ8ðhàÏvèB,UJ&öŽü©ô,Ár†'öœsnS dÁ31ÍC[s8¹£U*K«@ã³Èå¯IxÙ	’eÞ6f­BýûÒÓ¬JŽŸ'ûæ¨1ˆþ_HüÃý¢Á-ž3î«©»¼³nI®ò2©b÷€ZšÔ-KÞpJ­]ÞéÆfÁŽ—ÖÄ½+DlÐL¨&àPŽAV*×’ã´áÑêÙB5æ•¼Ådó&ý)Í¼`ü†\Ä‰^™ê1	?Ä®x¥ÀÊÎ8‰*ä|ÛjL5ïàwZð]BÝÖ«Vâ¹+OîV}¾ÖˆrMÎ]à;{‹¼¦[®áeŸxgï=¹YzôË¥Ja<9Åù¡þ”õ¼W
 xh“OUÄ9:-#zòö5/ß"¤—?cuLïUEþîî)­Kq¼fï°Å«"|kf´:É^MÔÕü7¨! áÓ–å/V!ÁÃl7umá?hÃ2uA“‚€–ÒpÇT7aù…X[7U¼2)ÑTÈTÀQ,ò `ýëÑÄ
²É¶¯( ãáý"Ïåí¤@#ï–_Ç§ÐV}£À¹c™r,_I8‘™’Îßf³.¶~àP_oBñ€ì ;£»ê'˜ßÎŸp<‰Òkjç
ø]vîcç	Ô²ñ¯T0ÿÔ ÏwîE¡OÞ"ÖÊ	÷¸‹ÃÈÕÑ¬Ï™ß0šNŠ?oÁºîÁºmÁàmÁào¸`ðS,)`H¨ÍÀ,\GÎçh¢hÞŠ~¹ÐÊÏ]¹
üã›:g‹4™…Ïµ œC_ñ‡?&3&ü‡?pM
×
ÍnŠEÂ¨Xõ@RÉtúÑ&ñ5ZÃÑüËFsƒ%|ý3….°sÜ€TM˜DœdYå'&€õ„C=‘‘¾zãa‡¸lº£þ!Rà@ï9DZÜa»5zc7Û†¥×Þ¼QÏÉ­?UW0ªf%{9CÊQÆ#
Â"ì§VP™þ¹FOöÇK!Œó^[%S­Jo\Ëz"/É´$	7mà|f¸ WøÝ
f.¡UÙ€f@ÿÀòk`ârÕËŠÄ÷{óf”ÍV09e#OdÙ.¡˜šƒ}(öC†²–xmA¸MÈ…8)8ÎÙ{’.LNªˆK-ÎßªP?…|kå„
I;‡Uå¾û\èW>íûˆçXá&í`KnºVüÐ5€°Ämrk:¸~öèPmO¦¬§Û~áßº÷mï¢Ûo2mt)}ítYæèÁa#ìµW¡¶SG÷Z,_Á+^ß”e“?*Äî²¯-Ãè¡9
x}(*n(2Ï*“Å©ëi,¡KÔ¥Ñ;2S‘ñÐû<W†n:j…˜…¯ˆvº±Ô«à¥¥|å0µ—é¯éüÅ8Øµí^æSì\Fê#ð°+o:ÿ•æi¹ðÜó“KvIFêu
~™„1¹[‘LÅ[è‹›gyê(‡èü7æ\ºÌÝ–¯Ø–¯øÝ¡aÛ,Òmé·Eú3W;›§d6^È–“×^ÒE‡ÊònY¾R ¬Ý€MQ,½ZŽ€œ
8$Q¸ ”¾IÐëËµËž÷Æy0l‹ç‰½. ¼ñ¾ÿ‚Ñ°‡©&é¥ßTócI™Œ¥©“1yz‘ÑäŽ×ú6°¡1—²àÜ…VANqC›Û…®ÊÞb¤=,|ª®
oÖÕ,¼V¡¹·aÀ‚,F¾«†'c„“S7Ght«ç”Þ²˜ñw`tÃvòXƒ€änáÌ£¨¦> =ºåM±9jåÁ,;ª]ÖYH½lBKrŽlÅ³Å4Q£ìèò4×)å1Á´1€ìEp)+ñÀ:{¡ØÜ¤ìŸ§_…Qä±n6uhMŽ.‡T]1ÏªDWFþ!õý“t¸…ËL,«D¸zxÑL‹³Î²,ÒbŽ±I@Í!})]VÍÊ•K™€î#:W…jî„ËÑåm"r5·[‹ÃgÝ…K£q˜4€p?5[ƒ£õõœzÓˆ_^i—[š0ÛFâê^º= ŽïÞfé­”³fìípÐ4«¶í÷JÉ¡ÎYïhÅ*
·&Ž²AÈÇ¡•qœŠc™ŽQ˜üƒ§P
ë°¤dGÆ¸+šKõÀË±¿
	mˆ{½¼ËÀTYlÚÐ£÷ÂÒ ²ìò^‘¢Pê±ÑÂ!ù¸r¥¢¸:ÀÜGwÎ=ÝØKy¦¬nì¥¤g‰´çSêê_yíÁJ^jL† ›"Üà¹“§lÐ¼‘þÖ±7ný[²F_yzdR1h‚F„Î+VtÈBþš2Ëz\;nuzm™®ÄØýQÕ&–Þ/.Wrsa$+QþÓ{Û¦nÓF·i£@nøuS_ÁY YÜ§¿3ÓüÆkÙzè›,ƒ3çœ¼i2äà“¹sYG¸,´Ò_/7”•keÅ›.ç_p7'¯iIîjÔ»”¦U*Ñüu$Å–jÈó|™Iô»‹ª]&A,©aV^s¤‹§)ºH²FU°›,’•_9Ú5š|~{¨J%õ¡ZBÿ˜þ»*¾°Z&}§£hÈè”~ÄÛ•iá ¢Y¬$+iŽeEž²,|ñg62†rëË“ÉÓäkëí‡üä,™Dç#§qª¨„õúûýUY\+WTÍ?wÀádË4$tŠÕÌhÜ,¯P°ãŒ5—¤Î‹[ÌP£3LtedEhU·— 1ÅrE®ŒBsð5Ò¶²¦Ô1AÞhÁé%	¢|[rtÔdÚoÒdƒKRÙŠ	zB±ŽìÝL…3PAýqš­
,åÅu~ÒÐò4=fµ¤€]Ù¦Þ»c†˜o
‚“K 4ÿLÝ[*÷àw}­Ý”ÚÏ¯6’Mûï[]†:ku8¯nÍ«½Û`ž|Ó]±¼¥åtÎ¿®?¶>ŸîŸ¡¬Ã§*N¨{¶åÕ½s{Pßý¾Ub8<èí7cz‡½Ý%|ºµàV¡Ó!E› ™B±À{™¶šÃÆsÌê¢b{|2ÅNŠS0Œ@J®Ö1T¶id*‘f‹(Ñ2¼†	:«£ê‡rÙ‚™ýcnÊ÷‰.¥ú[màž0úP…g£z¯y#Z)dBôÍ~Ç3ÎÙhz>wø­#z¦a—’éN¥vkÕDQ:‡|[á^`xÛj÷ËÛòè¶<ºoG7æòÆ»á¬¶.À<^Æn‘cíˆ Ü7áX‰CÈ!o!FfvÿvÌ’h‹&P·§ÀJ´ÝÊjVuP{Îœa(Û²ZÆÑ—¹¢nX¨Ôi´;lT·IôÏxðSzrÂ´Í‡ÆlËüOìg¿Àx´Ñ2$M å>m…ÅÆ¡`ù&ØoÖ\5’:ˆ.–ž$ÃãžµøWG]«åâ¨‘«®-U³‹jlØÈO†ëc§	Ú–F)•°Úòy¦åÌÐß¬s€‚,"î!*|/»íH'tuñš$×>+dž}Å˜þüï,¸çœ0BøØ<ƒ;¸…ÄÚJ×/w€ƒváÂ—~z#Š˜°tFËŸß Ši
5qË¯WlƒòÅŒi
§MyÖÚ6…Fä­DF{Õ³fm€YH"5R€S«„Â%¬´H´àšn-@oŠˆ0Ç²Õ¼ÝÚ §£VlÀË­ÂéüË%¢rÍI5~9F“V…ù}Þ©"YÉÛlö™‹‡vfQ®Ñ`þC†Îæç ŸÞm-—¤ÿ%j2ôäŒâ{$\ˆ¤¦,KÜõî°l[vä«[ƒkï¶ì(ßtwØ‘1ŒØ±þØú°ã)Eà#rÀœC—zû·N!¬{é6qGºû&qÇ·¯ÏpÇ“fÜq··[ªäZÀ—àŽ*Ç¾x¼¸R˜ÉØFâ	c7Ø´8¯Êø°o2Álì>Ÿ…•µA×@q5qÈO±)gÓÈóçY’îë‰ã!QÀüWF)iº˜Ð¥zÎy¢ ,¿K²„j†9¹¨„ð'éüoÚ•Êyb¯
b®C!yí˜¯ŒC³fÇÀ£ÅéÇ×£{ ;rƒ/…Ÿ£†Û¡vÝÞëKîb-ƒÁZ¸Q:>çwy	ïòzso@G‚WÊçÐ¸ø3þ³· ãt\rÇ-è¸Ð1óƒ‚—ØìHËØ/?zã«ä9/¿MV;ø5ˆ·|åª¡¯*b)e¸*Ö,ƒ›bhS+–\TD‚VÍ½Ê1¡Ú&!Åsâ¯.TØØ„oÓ	n™M…vÙ¦òn¶ª“êó_?x4†;œæGáªÍœ.T¶´ˆWÊ]ÿ ¿ßo+</äè¡ò-
]ÙöÂóoØ}ý¤!ZLL=8Òã@U
—²§Œ‚¥V¤;S·ôwœ¶vü°Ï5DÑË©ÇÏ?_ud,‰†õV‹ª‡‹‚Ò1(JìÔÑ/‰ä–N¼xæªa>LY—ßeQ›­ÿFèP*¬–3ä›¼]„ÉtÐUëòÓ&Á²v•kÆèª.…”+ÅBs&owÛ$4¬*óÏ4MùÓñLÅ¢R!$‰BW”!¶%¶JŽ²·¤§…CTqÉ„wQ¨XR¦v¤
å*IiñåÃ(Þ2|õ‡WÇûWÂó¨¥£¦}Á‰Nsióæû,¦-ÙÐ—ÀÊê:ÿ<õ8’µPy·+3'š~kVâÈh˜å`G@]KÜ^T$à§›éY¼[Ð´Òåö	5ý¦‰‹-BˆUœé."]Ý"„Xs·µ D|Ó!Dí1ù½Cl:¸~!ŒsMÝ}9¯ˆê~ëHbÝ«·%òíÛ¬€QÆOžžÞK<xÔ7b‰ƒÝ^é`!/y¯\Cc½ZgA’ÆÓ
'Å’'ôÛ˜|ã@A÷ë'd_›n0ãŠ#vHòÜ„iˆ½PQóÚ*PdûÔ¨ýå0g’üœù—ˆ"<<ðÔKÍ†ê¾Þaôqþk6yMR<×ìLÓ,Ä¡(6~JL1ñ›áPü€Å¸Šç_Lž½¶9…ZðÍ“¥e—×Á )Ãçz²-8£C`ñÌ'o‰üPíOC¤œ¯ª	X8û±óÂT|£§ØlBCTôu¾Ü>óCæ5òeÐµ‹ØâZô†¦MY\—ó}Ìîæ}BGüÙ¬¨3ëéâÏ[¨q[5c[5£é®ÛªÀÏ-ÛïŠQŽ(r#=ÿ2ô»a9ò:GÞNN„ÞíR3HÂtÃ‰IÎ`Qáð8au]P"Æ¸¥¶"wÂùÏO3Ñ
¼Ñq7ÓèN74´ÿÙ.¸¥»*r!·yB&Ã¾ÉøŒê3MžGV—ó8€—ªÈ2gnÕ<‘p—>×w!d'æ|B®Æeê_4pZ7³‹¨%À&‡j:œ¯£–0êÄÍ…òš0’£%÷sLîe±ò‡aÊ¢"ˆðá‡	ûtOó^Œ¾ÉQÓònPW2ä€*ýwÕÄÐÕ ÕWèÑžµKW-ùTEïèÉÍûb./¸	æÆéIéaá¥M1)ÍçzàÜ™TìÆüøJAWÓúÒ¬ÊM'y&uÝ\f8yAšä¢E7‹‘>Ç2ï.¼«ã°"¥Ì²ˆÌ,mÅH')ÐkX>ôÞ%ApX¡J¤â6ÿ„5£·—F#]—šª‰™_-t‰ê‡&¥ºÕTêº(©+“7ÐÏü|™Èz8jäê²rdqÚËFU±þSi4<õUf'dËÙ"ëØ$6^‚äPÚýýPëP©; |y{ÈcííÖå³Ê–þã?ñ?Ô}o€2Ý ÞÂU¼;ªñ—c”þ/ïÂ ¥ÿ‡ÿQ{t}”òõõB¤Ô:6éW?§Ml·ß$6yñÓùùë7o›ðÉ%¥z½æëã%ðä~›9Ö?rªÒ"Ù3]*,ß*ÌlI3Êz¥6BfC£/÷ÌS—fÐ°jÒJÇ/àé+jŠõ=9œæ^ìŸØŽyÀ%Ï/õy_¥ý>Q€áyO /Ôûå]¼ÚMˆiµÀ´&÷&—‰ÝqæŸ}á_Òçm!JiìååñçÉÙN€Y2 ì˜ß0ú¸,·€å°lC\ñŒ¼sòî~À$33ÄùKH‘QŠƒ†Q‰k~Ý+ý{‘Xì}ýMŽw§F®Q¤ÔÑÌçª?bäÑ‘ávn‰‘4‡¸’Baf[šñ¬wx¥OkÞ-‹ªš,jÖJ›bÚøu?ßrˆ"©Ì¶¿vìó®A2Ó¯Z~ D	ÛhN¶—=uêcê"Ø†§Þ””ml_ |YQÞÏBÉCï2\eÉ¨Uî–µ3”¸Y´›<ÏÍ{èèü@1ræ¨˜g‘Vê¼fØq¹A¶Š>‹<¨…H¬›&iHÙÎ†Ã0²°¿ Zˆme’9wöäËÛìäõN²« ïWðŒÞª¥›WCóÎ5ô †¯LÈ+¢7<ÍÛ”mÌ2xˆÊ÷Ê¶u`L‚:R¾S`’%²¼œ5Rƒ ³8bŠbzM'‘#%Œï˜ÙŽ4	?#‹¥V	VBøn1ºj¦,ç†ˆçoÌÒ­Hñâ†X³‡ŠÁÍ¨÷’$¾ø’"Ç§ïÛ¸0€Í2
xÿ @ÿû*ús¯o¬½ßZ( |ÙÝ)ˆ¡½º#ëAy9ÿmœÒÜÝ> W~Ï6Ñ¼mBy·V=yºŒ]¸·¬âðÁ~|·Û;,A{ë²á6ŠâÂ†98{/b?0d.­X;„²‡d_/áŠ"Ù†ŽCáPjÖºh¥%èjnÅ—&ô.¡Ùh…1Ô™cl¾‰s‹ÇÐD’Æ	n¦=ê¿‚GPƒ'È1â¬d%›±¸>æ7tŒÀ³Ï¿€ùó¾Eaxy¸?[ÈîÃÊ”B6,\±m•a3F·U†·`Ü–=¸enÙƒEz^¾±wFñ§0vÃq[þBËÙr$ë²Ä¬€Z^’„•,ÆÂ	Þ’IDÈEÖrª—(ÊÁéAÍ¶Kn)R†—‚ƒédj(i:F$&D£˜V\Á•ÒJ:³Ô à“ÅyÉ
±"]"r3„jšj–Ã
È—ó"Y™m‚o%?ÐykuªŒYõ‰wAM^±Ñfk(gÙÀðûàv™¸¥ZK!õ6ôL	]å’‡õn9,L¶¹a¥¸’}ŽiQãÜéV!»3Ã´¢.‡Ý¤^êpÝk“<™—á¨ ™UTÚ€€VƒâX]8O4¶ÕÇŽÄ@õ‡îEÎ„è†Þ“áÀn…Úe!N3ðÏí\Z2lµófˆrÖ÷ïÄ€ ÙÓ,#SxB0”Ù°–gÍI×Èl“ê:dï Õ7á&/³k>Û¥	NÕ,¶ØkìövÿÀ°2pr ìC‹ Xå^k`î~yMØWÝõÅûNÆ†Îäœ{~Bñv™…øõ Xù…[ÄÀ¼Õ!°?ýôã³—g_‹‚-©²×Û-]ì¨·{´+	ý­]û÷u‰,Hfyé¬N®Ž¨mc(l³j‡*h
ñ¬¬ß$½¦¯ô&ÓÔç"o¬÷JZ€®I:“ì`X"µ‚qóKr0£dSi"eÆzŽHzSÐN÷C_ñÓP|NßŸÿÊ~vÄRÃ!þâTvÁ<x˜âÐáÅÉ!áoN¯u¥ŠC,sëb¬B\BxËaóÐÞÒÕk… D«-ÒyEÉ‡@±‹pH>(y¤ÏæŸyi~ð³òÃhDŒÏ{943_ýæ·aœ™ Á|L]ßEI¿3vûî Fƒ*ôo°ö¡dl .éŸø*tßUÿrG`,+åäÙfa±Ýþîaÿb²2ut0ø*ìk1¸mýßmýßmýßz<L&J‰öf@a?{Ë!0ÏT×Ìƒw¸&`ç«ØhP±zó•ÌÔd‰M†H²ÅÌÖ"çiƒÐÑ›â½	J·u•zM\2kM8^cäÐ‚ŸðBŸe°N!Ÿœ¨RMNëÛMg¡ñ9(%—N31(5qà25åe¥f&³ÎPÎ€îì+¡åÈõ*‰ÏlÕÂÌ^ª¥
šíÝ“!y‘
“º"³ƒ].üþ{Ô*–Uxlm×D:óœ}ˆŒš5Q,-1á—3þ²¬üIªu*†ÐAÉvà3–kDˆÄØfóIÍB›kpvaàÈ42äÜè«‰]`
eõnƒq#Û¦¹{W:m«å1jüü.¬ÙDc4Ë7‚¨‚JU‹¥F1ÏÇø»Ô‡”´%i#¸Qú‘EÝó"Æv"+Š\*A.Ï[`‚¿áƒœÉFWI¦9ò†FM^¦§ZÀl@sÊªxY¶™Š»´èp™×ZV¹Õ:`™·V¦+¥ns¸¬þØšˆYU¬»m°¬úºmâet÷–!³Õkì.Ñ§;îJ2sììao¯1t¯wTÝÖÆÎØ8;Pn„B&‚Hcÿl¦ŠÍ±ZÑä´‘"ld] „ç;˜òcË¶§)
Û§AÉ“	õ^?×»U&g3dI‰•qG ˜TØHèÎI^jC’e®/Æ€ÑmÞ öœ“+`~æõAcMÉCe½¦–…–ÁF“ebøB£Ðª…¨à
[ß<|­ý[4vÝ™fÈLäîJ_°ŽlÂîw;•/êj\ÔWÃ(ãi÷;Ëë^¸0õ÷(ÿi‹–mÑ²-Z¶EË6–Òê­ÑM'Ö­[’8Ë¨«4¼› ¢ì!Æ•å/æÍ>ò©©c,Bgü†˜\°kµáì‘siN”hÖA Ï/WæÅ5šºÝ1¸?6þËÑ	ÔZ¡HÀBàÍÐÔË<„ê®«ÑcÕ*f®Ò[¶ã“.ÀÎOÒ™^`VÐlÇ)?‡¨åÂAÅ(I½Äæ£ÇéL™™›D³òAE$ªP>hã!>µu#5µF…|‰Ü±ZG`Gq„Ý•=
Jò™,!°¢$áÌüÄúxäÌÕ$øe#K)Êé¨}
mê†“l;{ê-tÁØ‰ÈåàwëÉ‹/‚Ç\r¡ š÷ÏŒŠìÓ&‡ƒÈ;O¸2çÌTFˆê "”÷4-ÊÍê“"_Ÿþ»*yj5ó5·¼u'X'Y»îÔÑ‹ºˆ™=DS‘“fÙ©TëRS<[²F‡¡Ï£úGO!$KÜ™Â¯ØÖ*´ÒoÕÙ$°ªB›l·öÃ[²!ÁZW»)—ÍOÑhë\?xÁz¦ßRïö.=Ã?‹(ðÔÍ5Bzùz#ã‹ˆ6ˆLÙFsúE&ƒBV¾Z¸ÚU“ôÒ§µb§±PÅl0Û–)î¯¼e°ZFïBÄØQK6±ÆêØë™"«Ã^gAªR¯ Áëk”öià(bxøô6*ÚLjˆ~dYx3ËF®8cÈóå¥P‹^]…<èX6ß¨7  ÀŸLzvÆz'VTµžGz×,ßÚ*¾w¬–®n®­¹ÛZˆ-¾éî í;»¨mÃÁõ¥ûž“7îB›újþé¯)J­#¸5¯Þ&„‹Ûwšù{ºT¶¯Y·o¿74á¶ƒÞ^éÂõ2Å¬s•70¦°¥iÑ@.­vƒÐŸÿ
uÇ‘¤ÝX±°B^†ë|8Ë8ñW)3>=’º-*×ÙÃ¢šÇe†¹`1-aC¤«Ræ+!lçÊ~!ê²¢&ÃŠ³ÚjÉf“ÚÃÓpÙ³©Åñ$ðÉóZ¶TGnù[Ò³ÄX‰=;TçÛfÿn³·Ù¿Ûìßmöïï¿=»	ýT" šÅè%âŽà[,e·¤þÊr'‘òD_‡R)·ß4›Ðè#¢Ç$å!’BÖ†Ð©M€’Ö¢ ¾$Ÿ"(&s^º€a®FÖ#Û’1;ûŠ÷%VÁžúHÛ­}W¥±Šü’d•Ôà•3Qw2}z_åIÚ£R¶a’I[…½WÜ–lUº ÑrÙè¡G»ÿBoa>æ¶ŠÔ9ºÊÒ2ó—]õM&p«°â©Be½1øs‰jïª34âŠl?SÿAì¶øžÆr\7?¬An¡÷905Š/¼°Ä50Šã2´;¡ŽmVp0°¥/¡­¢fìM(µtƒ¬„nœõ™²2g$Š^²ÕéDW/¥íÞ’‘û°_Hrnâ4*w\CÑîbvÒN6U3
5V‡€ù¯I^ÛbÈ¹Þ]NÕ¾é4ôMCÅ"…ŒdÎ¿ÔµÈuMj3™â—„DRŠA×„“²íÍét  •M'µƒC–E¨©¸ÁSXIr¯†/= òIÃ¾Mä³uÕÊè§÷0¸NMeZÇ57+@mC6—k*Èd²äÅBS½Ç,)Ø<åµ×Ry;7,å2C¹¾ŸdªY,Œ‡OÕGN×Ð#/bñóÎT‰%‘œ‰øèã{‡ÿ"ü¶hþiêUw<î±–bw˜—·ˆ³ÖÝn- •?ëÎH«O+ÑÄkÄZ›¯¶^è1szNu²xþ¥u”µþ¥[ÄYÍ6Y.åíëó»‹-÷—‰-î/I3?.]¸äú;.aà™Ú%š]Í˜y¤MMæÇšp+¦¹ÔÔEa$4NdoÓù1ü²®ÐHê˜dïFó/J*tyLƒá‚GŽó±À¢Þf3&W]à'±LcêPcúòža–là]ú„‰b—ÔZ,á£Xyì[ó˜iu2½„VZ®‹÷­£®¶]—ã®ÏÉº´ŽŒU‡äXÌãÎ‰P=xAË€zÑ
hkvîc®
Îýé•
æŸ ÕsúuEª•îÊƒCÍúP™	`4Þ"¬[„u‹°6Ýu‹°n¢:3Wšr3¯°pÕ¬Xçõñà)_û»Î4µ’3²lá²‚+#™ÈdY>×©-¡»ã˜¢ÌKÎa‡#
ApÅ#œÁÎQõ¹ÎÑQ4)Ï •’ˆõ ÿ¸yÈ6¥v×Æ#ucÚ&œÖZõn-%³˜°ŸÕù´i p"©™Pz ä®2G×šô¬2Eº4ß0ØCÂiÑyùÔ½
Mº—·c¦rþ÷*~ú°÷¨Ô;îöözíæ CÜ.`8ŽwWÉ½Æœ5§˜ýœ>÷–¦‹á’S?¶êåÚ¾x4ì˜ü\Y¤×û–sk_Ä¡¸ºø$oò¼°„gÁÁÎJð¬AYgN¡ÅŽ’·4”aX)è{Üß;ìïíõÈ*­¢ ¯T¤Ô;£»Fl‚A­Q¦pß…YÈì2þØ£w%c<Y“'lhò’­Eb•é}y#U†»$œîîz‘›’çÍ‚gq¾Ib;Aä}0mÆ¢_¬}®ƒÿ  ÿÿì}MsÛX–å¾~¦#:"+šMê[vÎJi»²Ü“ÎòX®ª®Úd@ä“%°Be:b½œõìfçe.´Ê]-›¬ï¹÷¾‡oŠ¤A¤²Ìˆ´“ExŸ÷žwî9Š°Uñ©c@š4×>ÂXÑt¹M]e¿2k;´}FMÌ8³“é´<wIÆîƒ$Ãüð«²ü©‰,ž¬¶QXè¦Öm”5%â·iÍ¥Í?ÃÞEpM¥˜¦`žû­ò·wß­·,†<¼¤úùÄD_óvZcž¶Ñ°žÙŒUm€GêºC$[.¸&incT2ÆÊP3½v¨dûÛÛû¼8Èûu4UyÞ½ãKómwˆKê¬K¾ÿÃÛqI©~ÞFó¤š<>¯¼™C“§Ã³
lù96Îo¹ ‡v‹ÐVùl[¨È°ï±mE™ð1ígQ®+ú¶êÂ2ýNøê1K‡Ü£$º*ã §±ðjR.ÐŒéJQ¬7³ ¢(§ˆßNâT9ç´fªpæï(9½‹“‰æç4xñ¾¦Ï ›;€£´–ï%´zwœeh¡›I¸¸è‹‡(]ßFývˆ²ÀwÝÝ^ù-ëe1°ó:’1²±æKÚiCE!_ ÞÂ÷–ÿ^å­˜O’j…/o¨AfñõüÖÐ¬XÑMý'{Ä²Ärùoôåw0¬¤b«Wœ0;ýDëñÓZØG9ÿY'åü]àž-0];d©3+Wóóh}‹- ìïíò§ybê;C+Ï»0¨>|Þ¬jðëB+]¼äñbˆ¥n`? OhÁ-‘¿I’ŒÍ¨jÏl·@W!5U?¬Dä`¯Å=ßB^4±;–S7‡N®iÑ%4ùšý‹¡^DIÈ=äßÚŽmø¤mæ:ùÒª@Qh Ž-G.xå,l‰qƒ‰²žƒ+ÁŒº¥©Nõxd9[ÝŠ[òÑ VüëÄD´øôãÿ«MÙFÒ´-]¥Ï•5ÙlDà	ž™…3Ú(91MîY'By}•Â~Ë”fsŸ"ôØ. iñ›¤Uº­[·f)*hÌõAªÓGjCóRŸ
ÌÂå'ê€"+7™Å´a·©7˜ˆiÉw®ÿ›··ú˜[õ¿H¨åbº±É	’u ©[°°)Ëè¯»ÚX®ÁX
Ó.N¥.*ˆ«¬ƒª_ùw1«i:¼Ð­Š¥ƒ­!³–©%ÊgrðÖ¥²an0ÄÞdô­wÆR «‚2©hrLÀß³Z"Oi|z5àÍÑx ^ ;<°å‚Ûàöá6Åÿæ'Æ?¶Àn}w{4ð5-/Yt‚	_’ã z·kP°ùæ»Ãõú¿$¸’­X%$–ØŠ‡ã›"[ñ¤¢ú9 ¶ÝxS±šw¶xŒ&E¼²‰‡´šäÜ1[ÅPÏ…U[sÆDIÌ9ptÌ"-)Ó•6ˆ•ñ“‡“dXõ0†ëÀqqÂÌFÄ¬Æ´Uú	+S]ÅÓ+?¨["‹À‘’ñÄ¤„ì÷ô‚oí:cëêð?²åÃ”Öî¡w‰%cwáUú_<"èz% ¹K{ä|î±À=¸Ç÷Xà¤\R’@k2¥ÛRXP·­5 @­%.mvš]Gmû‹Ú_–DVÎX¶¡Óƒ¶”Ä‚f\ýˆÚ¶¹”ÉQ\rÏY!käU‘…'âbZ½:
ËjW‡0=^XÓdëCDêy-Â†’‘#é£]ºH´Ý@q€¡tÇË¨ëœ˜2bÕnâ‘¼,*ž_WÔRÃb:¯(•‹ÂæîÌ;±Ò#Ý2_`ÅK…QˆH··ÎhÁm³ÜfçµZ¸Dn<smsÔæ1wS‘çÛ®Lèùsë•/¤	TœžFõ¯«(NU'‚;yn¡¼” ‚ótt~0:=tëæs‘‰ÿ2‚J¬E}ûk÷µà”¶ssyë\Ú/ünG,œz"yLÛWf„îÜ$V¤11@@1	Ã¼ëUóÐ±@j'%4¼~ä¾ìÐ«#X¹³øè°Sg Z¦Ó[¯´À‘¶WT´x$ þâ>¿b³1‹àªP›#§>*yñ[×-iÆG+YWG,ÌÑ~ž‡"Héº‡ÇBµƒP-@3ç©ŒÍPÚúø¢~¾3x±åz[ ‹öÉ6³ãZ¶è°Å¶7·/†GÐ›8êÜˆ®ù£Yü¯ v»Ý‰ùW<E<ñùð|œx\‹pb‡ÜÚ'H†‹B‰fzEá$—ºßÃÔlá¤N)²tÍ~´GÜè)šž#=F}QG‹žÒùbuÞfZ4Í"CsÛáPê¬}'wÊÂ÷§ì#×šÇßjI# áBCµŠ¥ž™¦†cìý%xúpÖ ™ÃéH©úçÁô‹÷²Ý¿K|ŒCl*{[í'ƒ(vo«½G;@÷^A{¯ /Ò+èm ªÐ³ÀL’^d&óÈîrN±ÝoÔ’ŽÉgù	®m’¼<¢„--l‰rî;ì a6¾õmfÅ™Ÿ¨.Ü	ìN	ƒ²-ù³ÙŽ¹›’åæ-ü2Î®Bs1§ãÇ•lõ¥EKH41£0€Å¿$ZXø¢löÖ‹cK¥y5Ç¶Æ>uný­5gË‡±Ÿô€úZžvìÅÊ&GpžŠ	@V£Œ:j ÚgÐmkÀ€ÐÝù¢œ9á"|>Çã(§h®K‰=:A7íë*žt
·¹8,
(Zí³Ñ¿§/ÌÒÇZ]Ì˜u9È
³œk¢{ú i­³¦¶0ý¯*V)*Gó:¨¢& ÛP9§ÖÒW#¨+Îë`èèùðètôcxNuêÈ-YAŸsIüûøÏ¬ôæVzYlî©Í"K+ÌM áÄÌ˜l-·ÉÍ_W¸ðIàòª¢wµ;mMoC%7Œ·ž ñv#°´ÒÆïhk¼Ü68›<Ö¦0Ûáß¶ lÿÞòÞ– Ûw¾÷Î\™pÒy	oÓv¯ñÕ×GÖ.^V…ø6¶oYQ°{8|^qh)ìž­€ÓŽ*8ÜÖpšê^¡ê¾´öp	¾æ;T-1¢ÍØpHù—ÛbÁÔ”ŸyßàR]AÓÔeèýqðÏòÌ3šÀE&0g â¤ffS¢H¡©àdcMGuS
ƒÿ ùÏ—g`ß¡´×B,;£	ä«–ï˜ÝÜ†údó–õ¥ƒiÚÕ‘Gã|Û5½™¸ )þ„-Žþßã¶s×þŽôÎ´ºp9|OPË½¶iÅ3óë8žÿ-N~,ÿk¥ýPZ J¯åX—[Ó™ÄâHê¢`ûO/(4Ó„^È|ïDæ¹Dí¸0ìð´,éùÉÆ
ƒÔ ±.PÑFÜÂò?)ô½o3Ÿ–°å§Õ:ÁÔŽÎþ05Z4çˆðÙ>5Þ¾VÚ¶ð1üCênÃiî	ÒÌä“ÍQiH•TàóM?¦à_Í'Ú,w	°½„ÈÓ$Þ1RRZnŒúœ†Uu£íüUI© ê·G¿ÙX"—ƒp íwõ²MFNáŒ@¯ëŽ°Rö7q'ºÙË‡kÓVÆÍÕB~“¦´ì%Œ¢y!üÊÖÂ–UúC(]-³ÔÎªÁ	×o>âT“gÇÏ©Áh,Æ÷lÄ%Âh/ù)pïT ´Ð-d5×!eSž( Æì­–-OrZh…JÿF³†õºÍ_ÀüCo½§má™Iƒ:–0÷ ð×!Â
Ò¢fMÃEåpùéš—»â,Àr‹!JÛci5Y¹˜œÐÈçŒ§&T×ß¬eñ§;ƒ²¯¶’%Ï´1D?|¼õ«T‡e‘÷×æ·?Ç2#âCì¥™úu5ùÏ‡¶‚¨á¶;·äúëÃ[BÛ;Ön›qù/«Äé*µ¦¨ëðpxÜuÑžßÒeGRÇ„;!98Ôû!Çƒ´¢YÁ1Tä§sŠ¼röpße‡Þ+†–¦1×‚c†“•‰áJSYî¯cÈ Gñ‡ Q[ãÊ(x<÷Á=è],oCË	Ö¼Ô:qzqGð-L:ôÞRp230²ª®k®Ç*,7|ŽÇnä§ÂM77ôiöÅC]®÷#O†}à%9$o5Sƒ!GóûbÔœbÔ4¥)ÃørÐ×nÑ®ƒÑÁéèrºàtvrøYðÖçBk{ÞØž7¶ç5c\/ÁZªVÛÊ%QÝ;`•ÝØØ$xT%Ëîˆ»ç€MPÿFÛÛïm=ä Òb-p*w›À¿Ú"¡)‡ÂÊ%Ù‡0–ÆŽ5E>–”YÕæUÂ×è”þîÖÛ—îê^1­`oR#v©½5{´e[”½TóŠÂ&Êi)™4&øulá)^ªé<fz»(±œ›ÕFÀ~AÏž YzŽ‘!Xu42\6G`Ö³^ðÃúÙÄÿØ[´ÁYåÈy`NÁ¯´5—írqlß"’`m†ºÖj‚§NA
®Ð<ÓÔ+ÆÑ­ÝÖi]fcàß[¯µaf¶×¸‡–d,ÕûW0®}„½"Î‰µ%Í‰86È¦”8B¶]Ë˜¸òÚD7o7ÓYvg'^ÞOQq†ÌQ€õ(K(”¶—ÎBÖ{²äd¤±ê‰»|HØ}Þà-ö·‡› y½±Ò>Ñ¿6ÀW`yzîà¼–nƒèÙ‡ÛÔ[Hzßˆèý¥å½íÙi#cÓHiÐÿ>ŽjnÄŸå5ÝqwH_}ÇÞ·ƒÍmsHïäyÍR¢ˆéOOÚ@½ÃƒáAW°7äÚÀûJ–?CáÝ9\„×.Y‰-Bf ¬+°ô}æŸS#ÍýéU )MºÔíòíæôS¢ñíò!Ê­½Kµ÷VC"j[Æv—Íøx—"èL×¾‹ïiÕ‹“›*Ñ]Fi¾n¢ãÎ>¼ù¦&…,‡œ2O¥Rtÿ½›_¬WCó N5Í¦ÀzÜû¸á.ÿ?WÎz‚îÆ=zâÒ ã²`Êº¡¯5QÕ¥ºs˜•4Á|OÚËIlc4%RIþjO^{Bu ºþkJ*b™‰iòò4VÊ>ó}<¾5¿Tï°]µg‡›s×¢±‚ &Y„ÿe®)ùnÏ\£4q*§Q>{Ov¼ƒý°’Åv¡™1M³ŒXmƒ$öÇÉòaˆ¹µ¹)§ì‘÷¾3³…3BfåÛ]µh
—UÊä9G×7Ù`ŠÕ[ø®EÈ‹]]éN-r)û4©ê}ùã^([Ü‚ÿûðÏ‰?£Í¬Vt\•ÄjeÚ­E!EšÆ^„–w8Èõö]ÿ¹Bà‚?
_Q(Vž¢ÏæÅ
TˆJÐ¤s×AÆ=ðê-JÔ/#ÁY"æ†£îá±\é  Nåñ®Ã@$4ãç
ÁÑŸVª¥ø­‰ ÔÄy=LãzÂ¿n±æz`é›Œ	4€ïiµ@£/`Â
alÜÓtùó} ¨Žš®05jª…¡	šÂØ±}~R×È‚mîñèèÉzëS®‡l2³}Œží¥¶›Û¯Ù¼Ì‹‡¹Jø~ù“œ [”,·1]O1³F{T-[µf4~3ÔDÊÄíÒæGœkÜ¢RÊÖ¹&Übu=üžç\´Çém9M0Î¦·jvbï|õ|¾š&/nZ­
&ÛÚB’r^3>ÇªêbÖ)sÃÓÑÁð„þÓŸ#úÓ)ìZIÏzJ-p«ì•”\ÓFgl]sX÷¿uÎ"#×ÙV†Õõò!åaåvã€¼`ãQ¸ÚŒ%é6p4Bw¥DkødC¸³»8·SŸ¡ç£7¨R¦•ÖúæMcHÔ,£9³Â”}b”C,Ôy=©‹:
©¸ž!ÈvæôjÙTãˆžÅÌ‚ND!WÇ­šŒN(Øì
éíŒ³Ùj®óò§;y¯¶Â+Ï´1¼GÕZ`‡î6¿µ%¸fÍFLÐ	G»õ›í×¥‹ïÖmr/Ú©™ÇÃç•JãŽ{>lEqÏ†çk‚¸ëx„PŸ‰ .ðP´Ó@-uýT<¤t¡x1›)î@Áu<¥…Jü|9i1!c&½IDz™ŒÃ	Î¿Çò$b4L)×´Ê¾½ú«jã+¤ô æ¢fhÿ)ÓR\AhGœ¯‹b¬¼`–Z¡}Öåñ¯–?‹S‰Â¸‹¦L<|˜ îN¹™èíG0\€|haŠäøã{wáî1Ü=†»Çp÷nÃûwíPëà[Ú»V‚·¼¹5bƒ„“ÜH¤%Jˆ°êUPb—`ë
S[f¿ëÄœšª´ù«EW‹MX€©xXÔI•ZG¥°×òƒ•™¦¥¯zºÐ«+ÈÃå~ÆVüúÎ±M)z½‹+½Â…¦4¨üVr#{
B_H‘íÜÜ!—íîQ›“‰%Ç˜Ð‹–gËiðKÎ‰æ¥gçâ€&ºNßY˜À¾Gj¼':Tô-,µ½'Åw]Ï7/¿5Ög½x¶[’ƒ‘”¾lÒÑ½Ò‚ ó‰×lù€¶q	‹Næv’þ<hû¸øžõ©øY³½û@’ êW!ÙDi>-aR?˜î%þè€=æ‚SÄhZS‰¢B—·ßÓ ‚‰3¶7Y×sf-0·’§õÓ©mX®¶,ýxŠ¯›tá$—Ó/"»…=Â­Åã|îÌ!ZrJUÕ@íeÁ€ùrãÑ®«(uè£gO×¬£`ÀšôáîPÍ†‹mjâ6Æ4i$Õ2p‡j¶½¹½%ò{sg(nå­ÿ­‰²éUí>ßl|¦ÏF8i7¹Bƒ)=:}~Ö¥Âbã|óúeYuUùùA;ÆyxLÒÊU=usÊ¨±òŠó€ë"iÙ1s=–4¯Uê3¼|Ó2Å*ˆ‘G+-'N5Y -^üïMB[ˆ…w“øÂVu{íqZô’øÂáFí‚ueCš— ûö&{(ÖX‚~õ:BÌ&iÉ¨cñ×¿ö,®æýÞ|˜S£¬h¼j!<²{J)÷Ó1n%²°ä“,ä	ç,$éœöààGS|½G÷ò‡{ùÃWÜËî €ü=oÏ¥Ðm³ì§ã¥SB²Å¥£S—¯cdì‹Yi=’Ê„"JNýB´ƒ&)Á‚Óª¦¡\ðR‰Ov	Vþ.4l»ÊØ<Ýæ÷¸Í_E¼R.*è_‘íCNC¹BGEÅÜ¨`©é¸™>ª6z·eço¡}ùŠ¶Ç1åÂ#y¿H{_"ÌÖÚTÐ«@"@G±ÆìØŸf×¡èB"<¢ÕpW¤øøÓàPœÄzaë@gGBgïÌ¤¨òkht°ÌÂÀ(ÚžÈðz2Ë ê€üÕDEÁÃù÷”.˜º—r§¼Äzðkifú¼`YavÏdÀ$1n¬Òž|§>:<ìÊ¢+\1&Oÿ}Ä^=Qq§Nœ©Ö,ÉŸœ˜b#Ì±„ÅïÄj¼Ü60–<Öæ@V6Zq¬æ÷¶‡±¾Å |Ã‡œÃWMwÛ!?W_¼ºüãÛ·x÷þ ë3,BNN‡‡iÄ7¯LP«çÃÃ5Q«7ß¾®bpE¼ŠZd 6èV9»æðäEé–÷m›¨¢u@ŒgôZD›"´`Œ,(ÁG¾§ÅÉúé†ð®£Tœâ÷T†(*
Ü¬«€ößVÝ'7„	«xD²n\É©äŠ™ñwœ4£¹0ùÇÙ:¿xHÚW¿xb·2µ«ôV¿]F»´:®g ÓRvah~õM’ÝÚžjI^<æ~÷k´<0ßøÑòSö6Hâ²"US,ûô>-\,‘‹-F˜¼´ƒ—_î±þ±WcU‹¥ñ„»»d®rbq¼÷Î¿¢9Çï~³üÄj7Xµþä—}@aGÏN;@ož=ï O;nV\……ññ‚¢[žÈ½£…¯(†oOƒqÓÈÝvxzÞA[œý#(%^L™7í]Ší{qØåíêmk^Ä…v´I)3W (¬ìu ‡ÿU¶T\õ‘ÝÑ*Í‹Lï|à>'Çæs„®Æëý^„2»‰?|cK¯+oØKµ.ô~ùñÚ`€² “*‚T¯Ÿª¬Þ‚0ýW"j¢žþeîø¸‘¾Ô=‰)ìõãu…+ ø–v‰Ú½¡…6Då"";æl*cÚB3”F˜88ñhÐT.ZÔ¿k€Þ,åƒ;F®Ñ®Ç™_‰û*(VK„àHÛ3ÅF‡ƒãS#(W^ZñZ­Œ$-2áÎöü)êTÙSÔ}“ÄÔ)cIî&Âõ‹±%±qÝ©ê-çgÅ6öÁ¥ðÕ©&IÌŽ·ÇY’‚JýhÍamv‹`þ×§¿d³àô4ôZXˆ.£±Q)(Æ:$½°ðÒ8Œyê2¢3G¢‚¶Dã2	•è¹Ù°°l6¦61ÿ0K,à¿PcJ‹¥0“ñ2B’Ð”‰|Ãª0<*·.Õl=ÔÕÊPbÚ‹R#wQƒðmÏé rG.¨Aç’GT¹•³EñÒ»¦TZØSàya¼\Qï]JYµ uÝC©S'år¾×SË·Ñüdã¢l“§Ín
^L×žø¥ã’
š,:ÈöÇn#•«ÉšE…ê¿í¬]jJ“x•Ñç–±Ðw÷ÂÓ¡2jž…®(¤LqD‰¡?o0bÚ\³G¶Õm‚ÀÒ§;`®¶þŠgÚ~ýh&-àë_ßÙÞÅæH
<õ.åd«k¶~×ÝA°tí]VH‹÷Gû¢ˆ=>kbOjìÂ[Cp·–º¤¦áÕoFk•DˆÉ³RÛ"\>è^†‚¼–á h.CŒ.Ä:	³Ô€PâÁrÓPª¼ü™RfUSZ¡¨§9ŸäP$Ñ¸ÐˆÔv ÈžâÄ0ü[65ú0ËBáµ+—ÑOáoãlr†ÞË|!ci„®_:K]ÿH…ôÖ9—~ž²eDo0ì«gÿñ8£á}õ2vök ²…ßþºÌ#ygfÈS¢X-lX½GO¾c´ÉxC¦ïçßz?ï5mqQ QUù\4æW­žX;áÇf£q­gŠ¯÷ˆí¾‚z_AÝv½}õN¼mRuˆ]]édùÓèg} ·´Åµ¸Û4RuS¬¹E j™Á=—ñØŠ±­¼Î…¦¼W)<R`ÃÁ@2?á?"ßsº5	°š„VúæbIðqÓËÛT÷ˆ~pO;ûîspjíÔô=
¡µ7¦Ìs”:5{f»m½átÈ{öŸµÊí4”jP`§@ ÄÇŒM/ª†Ôf-p LÎ¯Ër„hâE©}¥U"5 ²ð³Vôbà–N,ðy‹÷D°ËHYAXP(¤÷ñ–{ßo¬	9B!æÇþð‚åa¿¦”ÌòÉtb–ÌbA5ƒÐÓL¬	0­íAEéóù2‰QÚJßÀìcˆö‹;—8Ùã›ÐÞNÆÕ…:kÇ®ëeµ@’õÑâÛrÖÈ«™³·øÀQ.Ôj¶äJ[yt45@{<:ÒŸÑ1ý}Ü%&YÏziûP²¨ù)è<5[!ÝD]Ô§Iñ Ò93áMh¦*WêØm2|²#.WN®y •âÖs¢^-ž{¡ãpcEUßi±ô¯ôê½Xðt>Ö!·õ¡GúlgÀcÃµ¶€ñ4›ƒŽIåó¯Íomï¶óâG¦¼ôûY5Õî n¬Ýn—xc²ãóñråGÙž+@ÆgÃ³g­ ãáð°UŠq}?uj”ÿH†¸Ô •ö“lÂgÃ¡Òl8°–…vLÉ?Ìi‘¸ómå:†ÀÐC»æ§2ÌÝ¼âa¸üi<O@!exŠÍè8ÐöÒåƒ†,Øµ¼ëŒOM’Ær§_fððY~šã->±,|­WÓØêîÈÎ7æÉâöZ7ÊJtyœº}Ä§Ù;žj2&Á#uÑ/nc>S3l*™î‹¢íDÝEïÅ}Qô¾(z_]JË¾¡Ù¾½lô»•ùÊ‘Ä$XJ´^F‚¶Hr¶]rè]„–R°þÓ9k>Ì(GæãX¾¼ÖPç€ŠÄ®a ÑÇ‚7'¢þ"G­¦rËŸù§«ý½wZ`]~¾>òë¤©Y¹ÇÙ<§0dŒø/Œh›Ž`U mZ<·ÀÙ,“qdTD·¥¶­Dþ1Ä¹:b4p1¥ ¥Ÿæn#y$x±8Åó`>®xr×†çŠÖÓ-ÔŠ@Ñœž8,ÆN]íS ¢´H”}™d`Š£Vép^’ÜÄÖeº7§ïËìJó¢¹‰R9qî£ÃZÁ¿¤Ä&e1;Zº^ä(2Jhýl€.I_K´^[×tnÝúc©ÙÎK²+ !õ®2³˜Öf_T,éûäuœù!XŒ	«òÊ_ha„²¿xNÇã:†{K#‹ävJl,g7ýô`„È=WF…!¢ø^¬˜#c”Ä”uâe§Ð®©-Ü>ÔÆEU¿#ç!³ÈÙ‹g2q½F ²ÆuÌïüÕêšmË7™¬"³X‡G8œ±%óÐà›ÌìÖ’òºc®â9'öóÄ”Ç_eä<;ŸŽÎŸU²«Û®Lº#J6]l+À2Ù‚&ÜÜT¹Š²lyo{ªä«”GÏÙ<ø÷nLvO•l¸ïÁK\}—tÉvgðEëÏ†g‡+¸’ÏZaÌ£ášTÉÕ5ëß2¯{jí¦@S¼Š§WŒG!†H™ÅzSÑâm1åŠ©J÷¸Y­F£EqZF#ž!àù(Õë @¯4HôÙÄ~9û’ã­Ï{„s$bØ5 zi½¾ÃÐÜûâ0Ã+V4ÿ*K’€á×i-†¼6
Z¿QñŽ˜±SñŠc.ÂaJß­æJšŸGõÀ½ÀO,Aueî¡Ì=”¹¯fÿL t_ÍþVÍþ†7z0±‹©¯Ì;ãO­EíâÝŠšÙåÏÚ,R³Qm»Û›ij´È¢Éç†“lÀ4¶”Ê%³Ô”+äw	E~Ã»·&~½P}ðŒ-X¤<è,j®Ê7·Âm¸ÊfOGJ‘êÖs…Ä±­ˆèÃnƒ[¥N¬´˜àœy¤- ‹‰2šÀFä¨Š‡ƒj°~#€¡<1Xn‡ %ê5R´ŒùjY(ÿs:ËîØƒ^È¾¼ü¥h©ˆ/_½waz‘]'zù¡N
a€e)WKécrœÉ¡»wË·7·µT¶¬[î†^Üg!…Ï>ÄÞ›ˆŒÏØÃãdÔ±¸ä_dn‹yq/ã¥ÍÔ„ŒšnŒ´H
Ë( UŽï?1\€Î£œ3.@Éâ›%‡+ŽS×x@þ80Ã(ÕkçB¸ri66°© ]¹·£ÒU—dm Ò­KE]Ù”ßôÔƒmhfaÆûâQÑ°¸ÑWk¼ì}9Óø,Ô'U~8ðQMb¬6/v¶6‡e->j¨t¸Âf)•ßÙè„eOn~—FÃ]›‡Ëó'	)6 O`Šøtw bÓÕ¶Aù™6‡iWI‰9®ØòæöÀ¢#ˆr,K˜t+6Üv—¸".ßµæúN./ÛÁÅçÃ“N.ÃÖ:ìƒáùyà¢4GÙX#pfS|`ƒr2*Q1øŽ¼ô¼Sfø|ˆ_Á	Hž«®öS=ôû6wp‰L]Q=¶´Niåµ0ÑV	ãäq&Ö6ßz¯#¸À|äÒOZÄ¶•Í9è	cüˆétºúòçkfe.¢Ñ;‘ûåÕô†îVULæ½Þ#ŒkÈb^„ôX~nò§]¼WÇÜ«cîñÄ=ž¸Ç¿`<QÂBÏÐ¦2ß½Wf+bëúa¥aµno~8õÇF¶4Ý¸ÄTÇæ±"©…"ÜòS’šÐ:×9A-5C•¢Ó4ƒRïÅëðŽH{%’jö¹èîB=>•+ô«7‡Á˜ÞMKI½¡^#3³ÈÝínÍÙžA¼3÷KßÍ5Ù©Ä¥€•lÄG“’7Ø>’q<wby‘$ ¡ç`¥¼FŠ,Z¢ÉœvSÊ’6·ö'·oO´C³H…C¥L”~òÕL'’jÎÅø·‹ä†RÑchYÖ‰™ÔcÆ/Z ˆ9ÚÎ^ÏgªšS‰ÑÀ.JŽY+õSú|”ü7fô”ÐOÁ¤¿×e—­‡Â¾3w¡aÜ™“û¿åI!SŠ‚ÎRUºÎæl5ôñ©àÿ©bW\Rj*Ôï¢/+)*ËÊÉƒ_oe –àa>u[…ý’Öè8ÄwŽq±…)Ñæ­ý>˜š?ûÉlus‹ýM¾ ú!µ)_ËtH™]¡äv7«d$e¡´ÔÚØ÷B¿´†¶J­Ö:g]}ÕµÐÈæ4h÷}òâ6¡çò6¸ž¯ì–qB/D¶‘"«Q[íŠØ­U‡pç9CÔ=ª¬ ïØƒµAû6%¤)“èŠ×è£ÃhâÁèéÙé4bh›À‰øx‡xbÓå¶ù±6Fãª9¶ƒßÙK¤AtÁ¾j’÷¼ uzù÷yPçpbí¶;Äã&¾ÎÖÅGë«Wb‡gÏWa‡öa<<ªfo¾@°)%ÍÐ6©„|ÐyoÒ,a‚~€ìÛjãQ¨!Àr'(,Šq:ÁÂ&Jý;`”Ê¥Sµæy|È2Žr}ƒQl÷ò +Ù\"(Ùc¦1´xSQ.B¥õ—¥è •Hy¢;+b/¦ORÛLQ¹™G_ã@¡ˆ_Q_à$¿FQ_<t›G¨‰¨—@5úUÔÖ÷´Dª{ZâFÜÃˆ{q#­+Þ ãP™“ZX816?´TW¿w’|ØÈnãˆ¼iqa%1n£áôRÖüvùiÂö|“×-™Ø´	©!Íg¨¡8'H‚› ÑÓGŽÆ ë\½crJÍ­¿ðy÷Aýg¡áò!2ü³9…˜Ê‹jQêZ+—6:§¿ÏG]›®|çOeäè8éƒc›6D,Î[yßÑ?…ÇƒÄSø—½OfE÷ñ˜E§‹ÔAÖØ›3!L?#“©óÀÏTQÑaã–i¿ t}ôH$~Ç2"4d·¥$ÜÆ‹H÷Õ*Ô‡GëöàzHÛ7YrRdO£åÃµéEí06-Á—æ®Ü>Tž"w¥Ê(‰ÎL2õkî8ÔÖÓ`"Åæ9äF# –¤<§rïÓ°`²ó*t¶«r×5NH‰7r…kñæø6Xdv`+Ú®F¤Y:E€TãŽO™êy¼>Ûs-t®–pôÒ‰m,Á˜{½Â2’r,‘]¹TYÏ’yè¥œ§³lùSê©§;²¦À¨”2×a¶CZÖNŸÆVa6 ØbÓºV¿Ö6Ð=MµuûþM’{à*÷p²»Í¢Ï‚¢•øÛ¢fíí ¸æ·¶—6¶ƒùMãhV*õûíz£‹÷@âxëÙ«\¶"s§GÃÓvVßÉð¬˜;:>«Ôo‡Ì}ïã`ÄdZ0R‘7¸6ÑògXðÉÙ€VüÊ^2Ípô,ÿÀCCƒ~ì'ŒyAR/þ—L±± øvùp¥‘Df¡/|·ˆ|ê[å…Ù+ÇFŽ¸,XåÀz²ÇšSÎvå8ôO>¸}*[Ar	8Ž¤íÌWÑ>ÖŠa˜a{dN{ð1Jß{ŠÆF–‚=‘oOäÛ#p{nÀ}ÉÜ¿ùÉdùÀ‡xÆ†ˆ=€p´]µ€p´-™1{ƒN¡ÆŸ–i&¼ÑååÂ8‡1ÿ„5QYq	•–sT2,€s¤Ž™7Ô«0^¢xŸÆÇy$Ó´_>ÐfIÙ0\vèò°²,î5‘cÜ½è hTÄŠ·|T³†X¸[Oêê³ôS{¶€‚—(DDspHÁñ‚Fj=RJx%ZÔJÒÈ"M‘Žni°‹LÚê‡SæÆ9¹ÍË+rÔú8ÕîÏ{Ý¦½:®ä½Já’®/¡Wåo¦oÂÐ0wu^C#-‘î¶8:q'ã¸á½í˜‘›w{ É3L&@©Q¹õ²ÄDŸ¨[ŠtÄgMÃ•dciET°!œå{HK³0’	Ëùeq-P9Š‡Q¥Y˜ßZ?ôqè] Ši¾¶ÏlË)SÂr­4½lëÝšK75Z?ã¡­­,},Ó_B]”ù|‡÷&Q
¶màA±ÎÙÎ|ü BˆCZÕFÑEeÙÌˆ‡Ô#Ó»q`Ý@çÖ÷.>ÒbÄ7J½”-
²»uJ Ñj†£«Ü®sæº„C‹	^?ýÝ…¢¿å€Ï‚ oô!1ôblÏtJà¨M3…íU{9y(èªŠðhŒXq¬Ž˜@¨"+ÔÆ¤¾¸*”K¯'¶è:Ìošã:ã¸¤Æ²ƒÌèùÓ#=Ö±¹YúpwlÃÅ¶Ádñ@- ,ÀX„Åt!Æd¼‘‡öÅ‘}ql_œØ§öÅ™}qþ›ÿó›ÿ  ÿÿ 0p©s