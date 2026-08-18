package com.example.data

import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftRecommendation
import com.example.model.LaneRole
import com.example.model.MetaDataSource

object WildRiftRepository {

    const val CURRENT_PATCH_VERSION = "Patch 6.0b / 7.2c"
    const val LAST_SYNC_STATUS = "Sincronización Automática Activa"

    val metaSources: List<MetaDataSource> = listOf(
        MetaDataSource(
            id = "wildriftcore",
            name = "WildRiftCore (ES)",
            url = "https://wildriftcore.com/es/",
            badge = "Español & Parches",
            description = "Portal líder en español con notas de parches, árboles completos de runas y análisis de cambios de balance.",
            focusArea = "Runas en Español, Parches y Novedades"
        ),
        MetaDataSource(
            id = "bestbuildwr",
            name = "BestBuildWR",
            url = "https://bestbuildwr.com/",
            badge = "Pro Builds",
            description = "Optimización de builds de jugadores Grandmaster/Challenger, rutas de ítems y órdenes de habilidades prioritarias.",
            focusArea = "Builds Óptimas e Ítems Situacionales"
        ),
        MetaDataSource(
            id = "wildriftfire",
            name = "WildRiftFire",
            url = "https://www.wildriftfire.com/",
            badge = "Tier List Global",
            description = "Referencia global de tier lists, guías maestras de campeones, sinergias de carril y runas meta.",
            focusArea = "Tier Lists Globales y Sinergias"
        ),
        MetaDataSource(
            id = "wrmeta",
            name = "WR-Meta",
            url = "https://wr-meta.com/",
            badge = "Estadísticas & Counters",
            description = "Analítica masiva con winrates, pickrates, banrates y enfrentamientos directos de los servidores globales.",
            focusArea = "Estadísticas en Tiempo Real y Counters"
        )
    )

    val champions: List<Champion> = listOf(
        Champion(
            id = "morgana",
            name = "Morgana",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.JUNGLE),
            tier = "S+",
            winrate = 54.2,
            pickRate = 12.8,
            banRate = 18.4,
            damageType = DamageType.MAGIC,
            summary = "Su Escudo Negro anula el CC de iniciadores rivales, blindando a los carries aliados y asegurando control de mapa.",
            advantageAgainst = listOf("Rell", "Vi", "Sett", "Caitlyn", "Braum", "Alistar", "Pyke", "Leona"),
            counteredBy = listOf("Zed", "Yasuo", "Yone", "Karma", "Kassadin"),
            synergies = listOf("Viego", "Vayne", "Cho'Gath", "Jinx", "Kai'Sa"),
            tacticalAdvice = "Morgana: Su Escudo Negro anula el CC de Vi, Sett y Rakan, protegiendo a Vayne y anulando la iniciación rival.",
            recommendedRunes = "Cometa Arcano • Impacto Repentino",
            runeTreeDetails = "Cometa Arcano > Quemadura > Cazador Titánico > Flujo de Maná (Secundaria: Inspiración / Perspicacia)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Tormento de Liandry", "Orbe del Infinito", "Corona de la Reina", "Sombrero Mortal de Rabadon"),
            situationalItems = listOf("Morellonomicón", "Velo de Banshee", "Cetro de Cristal de Rylai"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/morgana",
            wrMetaUrl = "https://wr-meta.com/champion/morgana",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/morgana",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/morgana"
        ),
        Champion(
            id = "viego",
            name = "Viego",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.TOP),
            tier = "S+",
            winrate = 54.1,
            pickRate = 16.5,
            banRate = 24.1,
            damageType = DamageType.PHYSICAL,
            summary = "Posesión de cuerpos enemigos al conseguir bajas, curación masiva, inmunidad transitoria y reinicios infinitos de su definitiva.",
            advantageAgainst = listOf("Caitlyn", "Vi", "Sett", "Cho'Gath", "Lee Sin", "Kha'Zix"),
            counteredBy = listOf("Rammus", "Jax", "Lulu", "Morgana", "Shen"),
            synergies = listOf("Vayne", "Morgana", "Sett", "Nautilus", "Ahri"),
            tacticalAdvice = "Viego: Reinicios en cadena en peleas grupales. Aprovecha el CC aliado para asegurar la primera baja y dominar el campo.",
            recommendedRunes = "Conquistador • Triunfo",
            runeTreeDetails = "Conquistador > Triunfo > Golpe de Gracia > Cazador Titánico (Secundaria: Inspiración / Calzado Mágico)",
            recommendedSpells = listOf("Destello", "Castigo"),
            coreItems = listOf("Fuerza de la Trinidad", "Hoja del Rey Arruinado", "Desgarrador Divino", "Ángel Guardián"),
            situationalItems = listOf("Baile de la Muerte", "Fauces de Malmortius", "Filo de la Noche"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/viego",
            wrMetaUrl = "https://wr-meta.com/champion/viego",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/viego",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/viego"
        ),
        Champion(
            id = "nautilus",
            name = "Nautilus",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.JUNGLE),
            tier = "S",
            winrate = 53.1,
            pickRate = 11.2,
            banRate = 9.8,
            damageType = DamageType.MAGIC,
            summary = "Mayor cantidad de CC asegurado en objetivo individual con su R teledirigida, ancla y pasiva.",
            advantageAgainst = listOf("Vayne", "Yasuo", "Katarina", "Zed", "Pyke", "Samira"),
            counteredBy = listOf("Morgana", "Olaf", "Fiora", "Janna", "Braum"),
            synergies = listOf("Kai'Sa", "Samira", "Yasuo", "Miss Fortune", "Jinx"),
            tacticalAdvice = "Nautilus: Inicia sobre objetivos prioritarios sin posibilidad de esquive con su R definitiva.",
            recommendedRunes = "Reverberación • Fuente de Vida",
            runeTreeDetails = "Reverberación > Fuente de Vida > Condicionamiento > Sobrecrecimiento (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Coraza del Muerto", "Malla de Espinas", "Protector Pétreo", "Fuerza de la Naturaleza"),
            situationalItems = listOf("Promesa del Caballero", "Convergencia de Zeke", "Máscara Abisal"),
            skillOrder = "Max Q > W > E",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/nautilus",
            wrMetaUrl = "https://wr-meta.com/champion/nautilus",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/nautilus",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/nautilus"
        ),
        Champion(
            id = "yasuo",
            name = "Yasuo",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.TOP, LaneRole.ADC),
            tier = "S",
            winrate = 51.8,
            pickRate = 18.2,
            banRate = 22.0,
            damageType = DamageType.PHYSICAL,
            summary = "Muro de Viento (W) bloquea todos los proyectiles del juego y daño crítico continuo con derribos aliados.",
            advantageAgainst = listOf("Caitlyn", "Ahri", "Lux", "Syndra", "Miss Fortune", "Twisted Fate"),
            counteredBy = listOf("Renekton", "Pantheon", "Sett", "Malphite", "Darius"),
            synergies = listOf("Malphite", "Nautilus", "Diana", "Rakan", "Gragas", "Vi"),
            tacticalAdvice = "Yasuo: El Muro de Viento anula el daño del ADC rival en peleas de dragón y Barón.",
            recommendedRunes = "Cadencia Letal • Triunfo",
            runeTreeDetails = "Cadencia Letal > Triunfo > Linaje > Golpe de Gracia (Secundaria: Valor / Fuerzas Renovadas)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Filo del Infinito", "Bailarín Espectral", "Arcoescudo Inmortal", "Sanguinaria"),
            situationalItems = listOf("Ángel Guardián", "Recordatorio Mortal", "Fauces de Malmortius"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/yasuo",
            wrMetaUrl = "https://wr-meta.com/champion/yasuo",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/yasuo",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/yasuo"
        ),
        Champion(
            id = "sett",
            name = "Sett",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.MID),
            tier = "S+",
            winrate = 53.7,
            pickRate = 15.1,
            banRate = 19.5,
            damageType = DamageType.PHYSICAL,
            summary = "Daño verdadero monumental con su W cargada al máximo y definitiva que destruye agrupaciones enemigas lanzando al tanque.",
            advantageAgainst = listOf("Yasuo", "Irelia", "Riven", "Cho'Gath", "Sion", "Garen"),
            counteredBy = listOf("Vayne", "Fiora", "Kennen", "Aatrox", "Teemo"),
            synergies = listOf("Orianna", "Miss Fortune", "Morgana", "Viego", "Diana"),
            tacticalAdvice = "Sett: Absorbe el daño inicial del rival y contraataca en el centro de la batalla con daño verdadero puro.",
            recommendedRunes = "Conquistador • Triunfo",
            runeTreeDetails = "Conquistador > Triunfo > Último Esfuerzo > Fuerzas Renovadas (Secundaria: Valor / Sobrecrecimiento)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Rompecascos", "Corazón de Acero", "Hidra Titánica", "Guantelete de Sterak"),
            situationalItems = listOf("Malla de Espinas", "Baile de la Muerte", "Fuerza de la Naturaleza"),
            skillOrder = "Max W > Q > E",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/sett",
            wrMetaUrl = "https://wr-meta.com/champion/sett",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/sett",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/sett"
        ),
        Champion(
            id = "aatrox",
            name = "Aatrox",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S+",
            winrate = 53.4,
            pickRate = 14.8,
            banRate = 17.3,
            damageType = DamageType.PHYSICAL,
            summary = "Curación absurda en combate sostenido y control de área con el filo de las 3 fases de su Q.",
            advantageAgainst = listOf("Sett", "Darius", "Garen", "Sion", "Renekton", "Jax"),
            counteredBy = listOf("Fiora", "Irelia", "Vayne", "Ignite", "Malphite"),
            synergies = listOf("Lulu", "Yuumi", "Morgana", "Sejuani", "Rakan"),
            tacticalAdvice = "Aatrox: Golpea los bordes de la Q para maximizar el derribo aéreo y curación sostenida.",
            recommendedRunes = "Conquistador • Brutal",
            runeTreeDetails = "Conquistador > Brutal > Golpe de Gracia > Cazador Titánico (Secundaria: Valor / Fuerzas Renovadas)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Cuchilla Negra", "Baile de la Muerte", "Eclipse", "Rencor de Serylda"),
            situationalItems = listOf("Guantelete de Sterak", "Rostros Espiritual", "Ángel Guardián"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/aatrox",
            wrMetaUrl = "https://wr-meta.com/champion/aatrox",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/aatrox",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/aatrox"
        ),
        Champion(
            id = "darius",
            name = "Darius",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.6,
            pickRate = 13.0,
            banRate = 16.0,
            damageType = DamageType.PHYSICAL,
            summary = "Hemorragia acumulable al 5to golpe activa Fuerza Noxiana y reinicios instantáneos con Guillotina Noxiana (R).",
            advantageAgainst = listOf("Garen", "Nasus", "Malphite", "Shen", "Sion"),
            counteredBy = listOf("Vayne", "Kennen", "Aatrox", "Teemo", "Fiora"),
            synergies = listOf("Ghost", "Lulu", "Thresh", "Nautilus", "Morgana"),
            tacticalAdvice = "Darius: Prioriza acumular las 5 marcas rápidamente antes de ejecutar con la R.",
            recommendedRunes = "Conquistador • Triunfo",
            runeTreeDetails = "Conquistador > Triunfo > Golpe de Gracia > Fuerzas Renovadas (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Fantasma"),
            coreItems = listOf("Fuerza de la Trinidad", "Coraza del Muerto", "Guantelete de Sterak", "Malla de Espinas"),
            situationalItems = listOf("Fuerza de la Naturaleza", "Baile de la Muerte", "Cuchilla Negra"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/darius",
            wrMetaUrl = "https://wr-meta.com/champion/darius",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/darius",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/darius"
        ),
        Champion(
            id = "fiora",
            name = "Fiora",
            primaryRole = LaneRole.TOP,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.8,
            pickRate = 11.5,
            banRate = 18.0,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Daño verdadero por porcentaje de vida máxima y Estocada (W) que refleja aturdimientos enemigos.",
            advantageAgainst = listOf("Aatrox", "Darius", "Sett", "Cho'Gath", "Ornn", "Sion"),
            counteredBy = listOf("Malphite", "Wukong", "Bramble Vest", "Kennen"),
            synergies = listOf("Twisted Fate", "Galio", "Shen", "Morgana"),
            tacticalAdvice = "Fiora: Guarda la W para bloquear la habilidad con mayor CC del rival (ej. Gancho de Thresh o W de Sett).",
            recommendedRunes = "Garras del Inmortal • Brutal",
            runeTreeDetails = "Garras del Inmortal > Brutal > Cazador Titánico > Dulces Frutos (Secundaria: Precisión / Triunfo)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Desgarrador Divino", "Rompecascos", "Baile de la Muerte", "Fauces de Malmortius"),
            situationalItems = listOf("Ángel Guardián", "Sanguinaria", "Fuerza de la Trinidad"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/fiora",
            wrMetaUrl = "https://wr-meta.com/champion/fiora",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/fiora",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/fiora"
        ),
        Champion(
            id = "janna",
            name = "Janna",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S",
            winrate = 52.9,
            pickRate = 9.4,
            banRate = 4.1,
            damageType = DamageType.MAGIC,
            summary = "Proporciona un gran desengage, escudos masivos y curación de área con Monzón (R).",
            advantageAgainst = listOf("Sett", "Vi", "Rakan", "Leona", "Alistar", "Samira"),
            counteredBy = listOf("Blitzcrank", "Pyke", "Nautilus", "Senna", "Lux"),
            synergies = listOf("Vayne", "Jinx", "Caitlyn", "Tristana", "Kai'Sa"),
            tacticalAdvice = "Janna: Proporciona un gran desengage y escudo para proteger a los tiradores frente a clavados rivales.",
            recommendedRunes = "Aery • Flujo de Maná",
            runeTreeDetails = "Aery > Flujo de Maná > Trascendencia > Quemadura (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Curación"),
            coreItems = listOf("Incensario Ardiente", "Ecos de Helia", "Mandato Imperial", "Redención"),
            situationalItems = listOf("Bastón de Aguas Fluidas", "Velo de Banshee", "Corona de la Reina"),
            skillOrder = "Max E > W > Q",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/janna",
            wrMetaUrl = "https://wr-meta.com/champion/janna",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/janna",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/janna"
        ),
        Champion(
            id = "lulu",
            name = "Lulu",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.5,
            pickRate = 12.1,
            banRate = 14.5,
            damageType = DamageType.MAGIC,
            summary = "Su polimorfismo y definitiva son vitales para transformar hiper-carries en bestias imparables.",
            advantageAgainst = listOf("Zed", "Katarina", "Master Yi", "Viego", "Vi", "Rengar"),
            counteredBy = listOf("Blitzcrank", "Brand", "Lux", "Zyra", "Pyke"),
            synergies = listOf("Jinx", "Vayne", "Twitch", "Tristana", "Kai'Sa"),
            tacticalAdvice = "Lulu: Su polimorfismo y definitiva son vitales para neutralizar a los asesinos y potenciar a Vayne.",
            recommendedRunes = "Aery • Flujo de Maná",
            runeTreeDetails = "Aery > Flujo de Maná > Trascendencia > Cazador Genio (Secundaria: Inspiración / Dulces Frutos)",
            recommendedSpells = listOf("Destello", "Extenuación"),
            coreItems = listOf("Incensario Ardiente", "Redención", "Bastón de Aguas Fluidas", "Corona de la Reina"),
            situationalItems = listOf("Protector Pétreo", "Mandato Imperial", "Banshee"),
            skillOrder = "Max E > W > Q",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lulu",
            wrMetaUrl = "https://wr-meta.com/champion/lulu",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/lulu",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/lulu"
        ),
        Champion(
            id = "chogath",
            name = "Cho'Gath",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S",
            winrate = 52.4,
            pickRate = 8.5,
            banRate = 5.2,
            damageType = DamageType.MAGIC,
            summary = "Escalado de vida infinito con Festín (R) y silencio de área que interrumpe combos enemigos.",
            advantageAgainst = listOf("Riven", "Yasuo", "Katarina", "Akali", "Irelia"),
            counteredBy = listOf("Fiora", "Vayne", "Gwen", "Sett"),
            synergies = listOf("Yasuo", "Orianna", "Miss Fortune", "Viego"),
            tacticalAdvice = "Cho'Gath: Asegura objetivos como Dragones con el daño verdadero instantáneo de su R Festín.",
            recommendedRunes = "Garras del Inmortal • Condicionamiento",
            runeTreeDetails = "Garras del Inmortal > Condicionamiento > Sobrecrecimiento > Cazador Titánico (Secundaria: Inspiración / Flujo de Maná)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Corazón de Acero", "Malla de Espinas", "Corona de la Reina", "Fuerza de la Naturaleza"),
            situationalItems = listOf("Protector Pétreo", "Cetro de Cristal de Rylai", "Máscara Abisal"),
            skillOrder = "Max E > W > Q",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/chogath",
            wrMetaUrl = "https://wr-meta.com/champion/chogath",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/chogath",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/chogath"
        ),
        Champion(
            id = "vayne",
            name = "Vayne",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S+",
            winrate = 53.9,
            pickRate = 17.1,
            banRate = 20.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Hiperescalado letal con Proyectiles de Plata (W) de daño verdadero por % de vida y movilidad con invisibilidad.",
            advantageAgainst = listOf("Cho'Gath", "Sion", "Sett", "Dr. Mundo", "Braum", "Leona"),
            counteredBy = listOf("Caitlyn", "Draven", "Lucian", "Teemo", "Nautilus"),
            synergies = listOf("Lulu", "Janna", "Morgana", "Nami", "Thresh", "Braum"),
            tacticalAdvice = "Vayne: Posiciónate detrás de Morgana y Cho'Gath para derretir la primera línea rival sin riesgo.",
            recommendedRunes = "Cadencia Letal • Triunfo",
            runeTreeDetails = "Cadencia Letal > Triunfo > Linaje > Golpe de Gracia (Secundaria: Valor / Cazador Titánico)",
            recommendedSpells = listOf("Destello", "Barrera"),
            coreItems = listOf("Hoja del Rey Arruinado", "Cañón de Fuego Rápido", "Filo del Infinito", "Arcoescudo Inmortal"),
            situationalItems = listOf("Fauces de Malmortius", "Ángel Guardián", "Recordatorio Mortal"),
            skillOrder = "Max W > Q > E",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/vayne",
            wrMetaUrl = "https://wr-meta.com/champion/vayne",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/vayne",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/vayne"
        ),
        Champion(
            id = "caitlyn",
            name = "Caitlyn",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 51.9,
            pickRate = 16.0,
            banRate = 11.4,
            damageType = DamageType.PHYSICAL,
            summary = "El mayor rango básico de tirador en el juego, control de trampas y cabezazos devastadores.",
            advantageAgainst = listOf("Vayne", "Kai'Sa", "Samira", "Short-range ADCs"),
            counteredBy = listOf("Yasuo", "Nautilus", "Vi", "Zed", "Malphite"),
            synergies = listOf("Lux", "Morgana", "Thresh", "Pyke", "Leona"),
            tacticalAdvice = "Caitlyn: Utiliza tu rango para castigar bajo torre y colocar trampas en los cuellos de botella.",
            recommendedRunes = "Primer Golpe • Brutal",
            runeTreeDetails = "Primer Golpe > Brutal > Golpe de Gracia > Linaje (Secundaria: Inspiración / Dulces Frutos)",
            recommendedSpells = listOf("Destello", "Barrera"),
            coreItems = listOf("Filo del Infinito", "Cañón de Fuego Rápido", "Recaudadora", "Recordatorio Mortal"),
            situationalItems = listOf("Arcoescudo Inmortal", "Filo Fantasma de Youmuu", "Ángel Guardián"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/caitlyn",
            wrMetaUrl = "https://wr-meta.com/champion/caitlyn",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/caitlyn",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/caitlyn"
        ),
        Champion(
            id = "vi",
            name = "Vi",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S",
            winrate = 52.5,
            pickRate = 12.4,
            banRate = 8.9,
            damageType = DamageType.PHYSICAL,
            summary = "Iniciación bloqueada imparable con su R y trituración de armadura porcentual.",
            advantageAgainst = listOf("Caitlyn", "Jinx", "Twitch", "Lux", "Ahri"),
            counteredBy = listOf("Morgana", "Janna", "Jax", "Lulu", "Shen"),
            synergies = listOf("Yasuo", "Ahri", "Orianna", "Miss Fortune", "Kai'Sa"),
            tacticalAdvice = "Vi: Fija al carry más vulnerable con tu R imparable para aislarlo de su equipo.",
            recommendedRunes = "Conquistador • Impacto Repentino",
            runeTreeDetails = "Conquistador > Impacto Repentino > Golpe de Gracia > Cazador Titánico (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Castigo"),
            coreItems = listOf("Fuerza de la Trinidad", "Danza de la Muerte", "Guantelete de Sterak", "Malla de Espinas"),
            situationalItems = listOf("Cuchilla Negra", "Fauces de Malmortius", "Fuerza de la Naturaleza"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/vi",
            wrMetaUrl = "https://wr-meta.com/champion/vi",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/vi",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/vi"
        ),
        Champion(
            id = "rell",
            name = "Rell",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 52.8,
            pickRate = 7.6,
            banRate = 6.4,
            damageType = DamageType.MAGIC,
            summary = "Iniciación en combo devastador que rompe escudos enemigos y agrupa a todos los rivales.",
            advantageAgainst = listOf("Sett", "Tahm Kench", "Shen", "Sion"),
            counteredBy = listOf("Janna", "Morgana", "Thresh", "Vayne", "Lulu"),
            synergies = listOf("Samira", "Miss Fortune", "Katarina", "Yasuo"),
            tacticalAdvice = "Rell: Rompe los escudos masivos de Sett o Morgana antes de activar la definitiva magnética.",
            recommendedRunes = "Reverberación • Fuente de Vida",
            runeTreeDetails = "Reverberación > Fuente de Vida > Condicionamiento > Cazador Titánico (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Promesa del Caballero", "Convergencia de Zeke", "Protector Pétreo"),
            situationalItems = listOf("Malla de Espinas", "Fuerza de la Naturaleza", "Coraza del Muerto"),
            skillOrder = "Max W > E > Q",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/rell",
            wrMetaUrl = "https://wr-meta.com/champion/rell",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/rell",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/rell"
        ),
        Champion(
            id = "ahri",
            name = "Ahri",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.2,
            pickRate = 14.3,
            banRate = 7.5,
            damageType = DamageType.MAGIC,
            summary = "Alta movilidad con 3 cargas de R y Encanto (E) que asegura eliminaciones rápidas.",
            advantageAgainst = listOf("Lux", "Kassadin", "Twisted Fate", "Veigar"),
            counteredBy = listOf("Yasuo", "Zed", "Sylas", "Yone"),
            synergies = listOf("Vi", "Lee Sin", "Jarvan IV", "Viego"),
            tacticalAdvice = "Ahri: Flanquea en peleas grupales para encantar al carry enemigo fuera de posición.",
            recommendedRunes = "Electrocutar • Impacto Repentino",
            runeTreeDetails = "Electrocutar > Impacto Repentino > Cazador de Ojos > Cazador Genio (Secundaria: Inspiración / Flujo de Maná)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon"),
            situationalItems = listOf("Corona de la Reina", "Morellonomicón", "Velo de Banshee"),
            skillOrder = "Max Q > W > E",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/ahri",
            wrMetaUrl = "https://wr-meta.com/champion/ahri",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/ahri",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/ahri"
        ),
        Champion(
            id = "zed",
            name = "Zed",
            primaryRole = LaneRole.MID,
            secondaryRoles = listOf(LaneRole.JUNGLE),
            tier = "S",
            winrate = 51.5,
            pickRate = 15.8,
            banRate = 26.3,
            damageType = DamageType.PHYSICAL,
            summary = "Asesino sombrío con burst letal mediante Marca de la Muerte (R) y gran capacidad de escape.",
            advantageAgainst = listOf("Lux", "Veigar", "Ashe", "Jinx", "Syndra"),
            counteredBy = listOf("Lulu", "Zhonya", "Malphite", "Sett", "Morgana"),
            synergies = listOf("Nautilus", "Vi", "Leona", "Rakan"),
            tacticalAdvice = "Zed: Espera a que el equipo rival gaste sus habilidades defensivas antes de entrar.",
            recommendedRunes = "Electrocutar • Impacto Repentino",
            runeTreeDetails = "Electrocutar > Impacto Repentino > Cazador de Ojos > Golpe de Gracia (Secundaria: Inspiración / Trascendencia)",
            recommendedSpells = listOf("Destello", "Prender"),
            coreItems = listOf("Filo Fantasma de Youmuu", "Colmillo de Serpiente", "Rencor de Serylda"),
            situationalItems = listOf("Filo de la Noche", "Ángel Guardián", "Fauces de Malmortius"),
            skillOrder = "Max Q > E > W",
            isRanged = false,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/zed",
            wrMetaUrl = "https://wr-meta.com/champion/zed",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/zed",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/zed"
        ),
        Champion(
            id = "kaisa",
            name = "Kai'Sa",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.6,
            pickRate = 18.9,
            banRate = 15.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Evolución de habilidades (Q, W, E), daño híbrido masivo y reposicionamiento con escudo con Instinto Asesino (R).",
            advantageAgainst = listOf("Ezreal", "Jhin", "Sivir", "Ashe"),
            counteredBy = listOf("Caitlyn", "Draven", "Nautilus", "Lucian"),
            synergies = listOf("Nautilus", "Thresh", "Leona", "Alistar", "Rell"),
            tacticalAdvice = "Kai'Sa: Salta con tu R a objetivos marcados por CC aliado para rematarlos con lluvia de Icathia.",
            recommendedRunes = "Cadencia Letal • Triunfo",
            runeTreeDetails = "Cadencia Letal > Triunfo > Linaje > Golpe de Gracia (Secundaria: Dominación / Cazador de Ojos)",
            recommendedSpells = listOf("Destello", "Curación"),
            coreItems = listOf("Verdugo de Krakens", "Bailarín Espectral", "Filo del Infinito", "Diente de Nashor"),
            situationalItems = listOf("Ángel Guardián", "Corona de la Reina", "Recordatorio Mortal"),
            skillOrder = "Max Q > E > W",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/kaisa",
            wrMetaUrl = "https://wr-meta.com/champion/kaisa",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/kaisa",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/kaisa"
        ),
        Champion(
            id = "lee_sin",
            name = "Lee Sin",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.2,
            pickRate = 17.4,
            banRate = 21.0,
            damageType = DamageType.PHYSICAL,
            summary = "Control temprano absoluto del mapa, invasiones agresivas y jugadas decisivas 'Insec' con su R Ira del Dragón.",
            advantageAgainst = listOf("Master Yi", "Kha'Zix", "Evelynn", "Ekko"),
            counteredBy = listOf("Rammus", "Sett", "Viego", "Poppy"),
            synergies = listOf("Yasuo", "Orianna", "Ahri", "Renekton"),
            tacticalAdvice = "Lee Sin: Presiona las líneas aliadas con CC antes del minuto 4 para asegurar la ventaja en el primer Dragón.",
            recommendedRunes = "Conquistador • Brutal",
            runeTreeDetails = "Conquistador > Brutal > Golpe de Gracia > Cazador Titánico (Secundaria: Inspiración / Pionero)",
            recommendedSpells = listOf("Destello", "Castigo"),
            coreItems = listOf("Eclipse", "Cuchilla Negra", "Baile de la Muerte", "Guantelete de Sterak"),
            situationalItems = listOf("Ángel Guardián", "Colmillo de Serpiente", "Fauces de Malmortius"),
            skillOrder = "Max Q > W > E",
            isRanged = false,
            isFrontline = true,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/lee-sin",
            wrMetaUrl = "https://wr-meta.com/champion/lee-sin",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/lee-sin",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/lee-sin"
        ),
        Champion(
            id = "samira",
            name = "Samira",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.MID),
            tier = "S+",
            winrate = 53.3,
            pickRate = 14.2,
            banRate = 25.4,
            damageType = DamageType.PHYSICAL,
            summary = "Bloqueo de proyectiles con Torbellino de Espadas (W) y definitiva de daño masivo en área estilo Rank S.",
            advantageAgainst = listOf("Miss Fortune", "Ezreal", "Jinx", "Twitch"),
            counteredBy = listOf("Nautilus", "Lulu", "Rammus", "Sett"),
            synergies = listOf("Nautilus", "Rell", "Thresh", "Alistar", "Leona"),
            tacticalAdvice = "Samira: Espera a que el enemigo use su control de masas pesado antes de activar Gatillo Infernal.",
            recommendedRunes = "Conquistador • Triunfo",
            runeTreeDetails = "Conquistador > Triunfo > Linaje > Último Esfuerzo (Secundaria: Valor / Fuerzas Renovadas)",
            recommendedSpells = listOf("Destello", "Extenuación"),
            coreItems = listOf("Arcoescudo Inmortal", "El Coleccionista", "Filo del Infinito", "Sanguinaria"),
            situationalItems = listOf("Recordatorio Mortal", "Ángel Guardián", "Danza de la Muerte"),
            skillOrder = "Max Q > E > W",
            isRanged = true,
            isFrontline = false,
            wildRiftFireUrl = "https://www.wildriftfire.com/guide/samira",
            wrMetaUrl = "https://wr-meta.com/champion/samira",
            wildRiftCoreUrl = "https://wildriftcore.com/es/campeones/samira",
            bestBuildWrUrl = "https://bestbuildwr.com/champions/samira"
        )
    )

    fun getChampionById(id: String): Champion? = champions.find { it.id.equals(id, ignoreCase = true) }

    fun getChampionsByRole(role: LaneRole): List<Champion> {
        return champions.filter { it.primaryRole == role || it.secondaryRoles.contains(role) }
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>
    ): DraftAnalysisResult {
        val totalAllies = allies.size
        var physCount = 0
        var magicCount = 0
        var hybridCount = 0
        var frontlineCount = 0

        allies.forEach { champ ->
            when (champ.damageType) {
                DamageType.PHYSICAL -> physCount++
                DamageType.MAGIC -> magicCount++
                DamageType.TRUE_HYBRID -> {
                    physCount++
                    magicCount++
                    hybridCount++
                }
            }
            if (champ.isFrontline) frontlineCount++
        }

        val totalScore = (physCount + magicCount).coerceAtLeast(1)
        val physPercent = if (totalAllies == 0) 50 else ((physCount.toDouble() / totalScore) * 100).toInt()
        val magicPercent = 100 - physPercent
        val truePercent = if (hybridCount > 0) 15 else 0

        val frontlineStatus = when {
            frontlineCount >= 2 -> "Sólida: Primera línea y absorción asegurada"
            frontlineCount == 1 -> "Moderada: Requiere posicionamiento defensivo"
            else -> "⚠️ Falta Vanguardia: Se recomienda elegir tanque o luchador con aguante"
        }

        // Identify enemy threats and direct lane rival
        val enemyRival = enemies.firstOrNull { it.primaryRole == myRole } ?: enemies.firstOrNull()
        val enemyRivalName = enemyRival?.name ?: "el rival directo"

        val directWarning = if (enemyRival != null) {
            "Mejor contra tu rival directo ($enemyRivalName):"
        } else null

        // Calculate recommendations for the user's selected role
        val roleCandidates = champions.filter { it.primaryRole == myRole || it.secondaryRoles.contains(myRole) }
            .filter { champ -> allies.none { it.id == champ.id } && enemies.none { it.id == champ.id } }

        val recommendations = roleCandidates.map { champ ->
            var score = champ.winrate
            val countersEnemy = enemies.count { enemy -> champ.advantageAgainst.contains(enemy.name) }
            val synergizesAlly = allies.count { ally -> champ.synergies.contains(ally.name) }
            
            score += countersEnemy * 3.5 + synergizesAlly * 2.0
            if (champ.tier == "S+") score += 2.0

            val advantageMatches = enemies.filter { champ.advantageAgainst.contains(it.name) }.map { it.name }
            val advantageBadge = if (advantageMatches.isNotEmpty()) {
                "Ventaja vs ${advantageMatches.joinToString("/")}"
            } else {
                "Tier ${champ.tier} Meta"
            }

            val synergyNames = allies.filter { champ.synergies.contains(it.name) }.map { it.name }
            val synergyText = if (synergyNames.isNotEmpty()) "Excelente sinergia con ${synergyNames.joinToString(", ")}. " else ""

            val reason = "Su kit contrarresta directamente a ${if (advantageMatches.isNotEmpty()) advantageMatches.joinToString(", ") else "la composición rival"}. $synergyText${champ.summary}"

            DraftRecommendation(
                champion = champ,
                estimatedWinrate = (champ.winrate + (countersEnemy * 2.8)).coerceIn(48.0, 72.5),
                advantageBadge = advantageBadge,
                tacticalReason = reason,
                runes = champ.recommendedRunes
            )
        }.sortedByDescending { it.estimatedWinrate }
        .take(5)

        val bestDirect = recommendations.firstOrNull()?.let {
            "${it.champion.name} (${it.champion.tier}): El kit de ${it.champion.name} contrarresta el estilo de juego de $enemyRivalName neutralizando sus mayores ventajas tácticas."
        }

        return DraftAnalysisResult(
            physicalDamagePercent = physPercent,
            magicDamagePercent = magicPercent,
            trueDamagePercent = truePercent,
            frontlineStatus = frontlineStatus,
            directMatchupWarning = directWarning,
            directCounterBestPick = bestDirect,
            recommendations = recommendations
        )
    }
}
