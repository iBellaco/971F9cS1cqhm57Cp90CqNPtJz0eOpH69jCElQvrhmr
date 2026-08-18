package com.example.data

import com.example.model.Champion
import com.example.model.ChampionSkill
import com.example.model.DamageType
import com.example.model.DraftAnalysisResult
import com.example.model.DraftRecommendation
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.model.MapObjectiveItem
import com.example.model.MetaDataSource
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem

object WildRiftRepository {

    // Versión canónica oficial de Wild Rift
    const val CURRENT_PATCH_VERSION = "Patch 5.3 (Wild Rift)"
    const val LAST_SYNC_STATUS = "Sincronización Automática Activa"

    // CDN base URL para avatares, habilidades, objetos y hechizos
    private const val CDN_VERSION = "14.20.1"
    private const val DDRAGON_CHAMP_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/champion"
    private const val DDRAGON_SPELL_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/spell"
    private const val DDRAGON_ITEM_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/item"
    private const val DDRAGON_PASSIVE_IMG = "https://ddragon.leagueoflegends.com/cdn/$CDN_VERSION/img/passive"

    val metaSources: List<MetaDataSource> = listOf(
        MetaDataSource(
            id = "wildriftcore",
            name = "WildRiftCore (ES)",
            url = "https://wildriftcore.com/es/",
            badge = "Español & Parches",
            description = "Portal líder en español con notas de parches, árboles completos de runas y análisis de cambios de balance de Wild Rift.",
            focusArea = "Runas en Español, Parches y Novedades"
        ),
        MetaDataSource(
            id = "bestbuildwr",
            name = "BestBuildWR",
            url = "https://bestbuildwr.com/",
            badge = "Pro Builds",
            description = "Optimización de builds de jugadores Grandmaster/Challenger en Wild Rift, rutas de ítems y órdenes de habilidades prioritarias.",
            focusArea = "Builds Óptimas e Ítems Situacionales"
        ),
        MetaDataSource(
            id = "wildriftfire",
            name = "WildRiftFire",
            url = "https://www.wildriftfire.com/",
            badge = "Tier List Global",
            description = "Referencia global de tier lists exclusivas de Wild Rift, guías maestras de campeones, sinergias de carril y runas meta.",
            focusArea = "Tier Lists Globales y Sinergias"
        ),
        MetaDataSource(
            id = "wrmeta",
            name = "WR-Meta",
            url = "https://wr-meta.com/",
            badge = "Estadísticas & Counters",
            description = "Analítica masiva con winrates, pickrates, banrates y enfrentamientos directos de los servidores globales de Wild Rift.",
            focusArea = "Estadísticas en Tiempo Real y Counters"
        )
    )

    // ==========================================
    // CATÁLOGO DE HECHIZOS DE INVOCADOR (SUMMONER SPELLS)
    // ==========================================
    val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Destello (Flash)",
            cooldown = "150s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerFlash.png",
            description = "Teletransporta a tu campeón una corta distancia hacia la ubicación del cursor."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender (Ignite)",
            cooldown = "100s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerDot.png",
            description = "Quema a un campeón enemigo objetivo durante 5s, infligiendo daño verdadero y aplicando Heridas Graves (60%)."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Castigo (Smite)",
            cooldown = "45s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerSmite.png",
            description = "Inflige daño verdadero masivo a monstruos de la jungla o súbditos enemigos. Otorga mejoras de jungla."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera (Barrier)",
            cooldown = "110s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerBarrier.png",
            description = "Otorga un escudo que absorbe daño recibido durante 2 segundos."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación (Exhaust)",
            cooldown = "105s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerExhaust.png",
            description = "Ralentiza a un campeón enemigo un 60% y reduce su daño infligido un 40% durante 2.5s."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasma (Ghost)",
            cooldown = "90s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerHaste.png",
            description = "Otorga una ráfaga de velocidad de movimiento masiva e ignorar colisiones durante 6s, reiniciándose con bajas."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar (Heal)",
            cooldown = "120s",
            iconUrl = "$DDRAGON_SPELL_IMG/SummonerHeal.png",
            description = "Restaura vida a tu campeón y al aliado más cercano, otorgando un 30% de velocidad de movimiento durante 1s."
        )
    )

    // ==========================================
    // CATÁLOGO DE RUNAS DE WILD RIFT
    // ==========================================
    val runes: List<RuneItem> = listOf(
        RuneItem(
            id = "conqueror",
            name = "Conquistador (Conqueror)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            description = "Acumula Fuerza Adaptable al golpear campeones enemigos. Al llegar a 6 acumulaciones, otorga omnivampirismo adicional."
        ),
        RuneItem(
            id = "kraken_slayer",
            name = "Matakrakens (Kraken Slayer)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Cada 3 ataques básicos consecutivos inflige daño verdadero adicional escalable."
        ),
        RuneItem(
            id = "electrocute",
            name = "Electrocutar (Electrocute)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            description = "Golpear a un campeón con 3 ataques o habilidades individuales en 3s descarga un rayo de daño adaptativo explosivo."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe (First Strike)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            description = "Dañar a un enemigo antes de recibir daño otorga 9% de daño verdadero adicional durante 3s y oro según el daño infligido."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase (Phase Rush)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/phaserush/phaserush.png",
            description = "Impactar 3 ataques o habilidades otorga velocidad de movimiento masiva, aceleración de habilidades y 75% de resistencia a ralentizaciones."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Agarre del Perpetuo (Grasp)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            description = "Cada 4s en combate, tu siguiente ataque inflige daño mágico adicional según tu vida máxima, te cura y aumenta tu vida permanentemente."
        ),
        RuneItem(
            id = "aery",
            name = "Invocar a Aery (Aery)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            description = "Tus ataques y habilidades envían a Aery a dañar al objetivo enemigo o a otorgar un escudo a un aliado beneficiado."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Aumento Glacial (Glacial Augment)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png",
            description = "Inmovilizar a un campeón enemigo dispara 3 rayos glaciales que ralentizan el área y reducen el daño enemigo un 15%."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano (Arcane Comet)",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            description = "Dañar a un campeón con una habilidad arroja un cometa que inflige daño mágico adaptativo en su ubicación."
        )
    )

    // ==========================================
    // CATÁLOGO DE OBJETOS DE WILD RIFT
    // ==========================================
    val items: List<WildRiftItem> = listOf(
        // FÍSICOS (AD)
        WildRiftItem(
            id = "infinity_edge",
            name = "Filo del Infinito (Infinity Edge)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3400,
            stats = "+55 Daño de Ataque, +25% Prob. de Golpe Crítico",
            passive = "Pasiva - Infinito: Los golpes críticos infligen un 205% de daño en lugar del 175%.",
            iconUrl = "$DDRAGON_ITEM_IMG/3031.png"
        ),
        WildRiftItem(
            id = "blade_ruined_king",
            name = "Hoja del Rey Arruinado (BotRK)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3200,
            stats = "+20 Daño de Ataque, +35% Velocidad de Ataque, +10% Vampirismo Físico",
            passive = "Pasiva - Golpe de Niebla: Los ataques básicos infligen un 6% (9% cuerpo a cuerpo) de la vida actual del enemigo como daño físico.",
            iconUrl = "$DDRAGON_ITEM_IMG/3153.png"
        ),
        WildRiftItem(
            id = "trinity_force",
            name = "Fuerza de la Trinidad (Trinity Force)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3533,
            stats = "+250 Vida, +30 Daño de Ataque, +30% Vel. Ataque, +25 Aceleración de Habilidad",
            passive = "Pasiva - Hoja Encantada: Usar una habilidad potencia tu siguiente ataque infligiendo 200% del daño básico como daño físico adicional.",
            iconUrl = "$DDRAGON_ITEM_IMG/3078.png"
        ),
        WildRiftItem(
            id = "divine_sunderer",
            name = "Desgarrador Divino (Divine Sunderer)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3400,
            stats = "+400 Vida, +25 Daño de Ataque, +20 Aceleración de Habilidad",
            passive = "Pasiva - Hoja de Hechizo: Tras usar habilidad, el siguiente ataque inflige 10% de la vida máxima rival y cura un 7% de la vida máxima.",
            iconUrl = "$DDRAGON_ITEM_IMG/6632.png"
        ),
        WildRiftItem(
            id = "deaths_dance",
            name = "Baile de la Muerte (Death's Dance)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+35 Daño de Ataque, +40 Armadura, +15 Aceleración de Habilidad",
            passive = "Pasiva - Cauterio: El 35% del daño físico recibido se almacena y se sufre como sangrado a lo largo de 3s. Las bajas purgan el sangrado y restauran vida.",
            iconUrl = "$DDRAGON_ITEM_IMG/6333.png"
        ),
        WildRiftItem(
            id = "mortal_reminder",
            name = "Recordatorio Mortal (Mortal Reminder)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3000,
            stats = "+30 Daño de Ataque, +25% Prob. Crítico, +30% Penetración de Armadura",
            passive = "Pasiva - Sepulturero: Infligir daño físico aplica Heridas Graves (40% de reducción de curación) durante 3 segundos.",
            iconUrl = "$DDRAGON_ITEM_IMG/3033.png"
        ),
        WildRiftItem(
            id = "guardian_angel",
            name = "Ángel Guardián (Guardian Angel)",
            category = ItemCategory.PHYSICAL,
            goldCost = 3100,
            stats = "+40 Daño de Ataque, +40 Armadura",
            passive = "Pasiva - Renacer: Al recibir daño letal, resucita tras 4 segundos restaurando un 50% de la vida básica y 30% de maná (210s enfriamiento).",
            iconUrl = "$DDRAGON_ITEM_IMG/3026.png"
        ),

        // MÁGICOS (AP)
        WildRiftItem(
            id = "rabadon_deathcap",
            name = "Sombrero Mortal de Rabadon (Deathcap)",
            category = ItemCategory.MAGIC,
            goldCost = 3400,
            stats = "+110 Poder de Habilidad",
            passive = "Pasiva - Opus Mágico: Aumenta el Poder de Habilidad total entre un 20% y un 45% (según el nivel del campeón).",
            iconUrl = "$DDRAGON_ITEM_IMG/3089.png"
        ),
        WildRiftItem(
            id = "infinity_orb",
            name = "Orbe del Infinito (Infinity Orb)",
            category = ItemCategory.MAGIC,
            goldCost = 3150,
            stats = "+85 Poder de Habilidad, +5% Velocidad de Movimiento, +15 Penetración Mágica",
            passive = "Pasiva - Castigo Inevitable: Las habilidades y ataques potenciados infligen un golpe crítico mágico (120% de daño) a enemigos por debajo del 35% de vida.",
            iconUrl = "$DDRAGON_ITEM_IMG/4628.png"
        ),
        WildRiftItem(
            id = "liandrys_torment",
            name = "Tormento de Liandry (Liandry's Torment)",
            category = ItemCategory.MAGIC,
            goldCost = 3100,
            stats = "+70 Poder de Habilidad, +200 Vida, +10 Aceleración de Habilidad",
            passive = "Pasiva - Tormento: Infligir daño con habilidades quema al objetivo infligiendo daño mágico igual al 1% de su vida máxima por segundo durante 3s.",
            iconUrl = "$DDRAGON_ITEM_IMG/3151.png"
        ),
        WildRiftItem(
            id = "ludens_echo",
            name = "Eco de Luden (Luden's Echo)",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+85 Poder de Habilidad, +300 Maná Máximo, +20 Aceleración de Habilidad",
            passive = "Pasiva - Disparo Discordante: Moverse y lanzar habilidades acumula Cargas. A 100 cargas, el siguiente impacto desata una ráfaga a 3 objetivos cercanos.",
            iconUrl = "$DDRAGON_ITEM_IMG/3285.png"
        ),
        WildRiftItem(
            id = "void_staff",
            name = "Báculo del Vacío (Void Staff)",
            category = ItemCategory.MAGIC,
            goldCost = 2800,
            stats = "+70 Poder de Habilidad, +45% Penetración Mágica",
            passive = "Pasiva - Disolución: Otorga 45% de penetración mágica porcentual para destrozar campeones con resistencia mágica alta.",
            iconUrl = "$DDRAGON_ITEM_IMG/3135.png"
        ),
        WildRiftItem(
            id = "crown_shattered_queen",
            name = "Corona de la Reina Ahogada (Crown)",
            category = ItemCategory.MAGIC,
            goldCost = 3000,
            stats = "+60 Poder de Habilidad, +200 Vida, +200 Maná, +20 Aceleración de Habilidad",
            passive = "Pasiva - Salvaguarda: Otorga un escudo que reduce el daño entrante un 70% durante 1.5s al recibir daño de un campeón.",
            iconUrl = "$DDRAGON_ITEM_IMG/4644.png"
        ),

        // DEFENSA / TANQUE
        WildRiftItem(
            id = "sunfire_aegis",
            name = "Égida de Fuego Solar (Sunfire Aegis)",
            category = ItemCategory.DEFENSE,
            goldCost = 3000,
            stats = "+500 Vida, +15 Aceleración de Habilidad",
            passive = "Pasiva - Inmolar: Inflige daño mágico por segundo a enemigos cercanos. Al máximo de acumulaciones, tus ataques básicos queman con daño en área.",
            iconUrl = "$DDRAGON_ITEM_IMG/3068.png"
        ),
        WildRiftItem(
            id = "thornmail",
            name = "Malla de Espinas (Thornmail)",
            category = ItemCategory.DEFENSE,
            goldCost = 2700,
            stats = "+200 Vida, +75 Armadura",
            passive = "Pasiva - Espinas: Al recibir ataques básicos, refleja daño mágico al atacante y le aplica Heridas Graves (60% de reducción de curación).",
            iconUrl = "$DDRAGON_ITEM_IMG/3075.png"
        ),
        WildRiftItem(
            id = "spirit_visage",
            name = "Rostro Espiritual (Spirit Visage)",
            category = ItemCategory.DEFENSE,
            goldCost = 2900,
            stats = "+350 Vida, +50 Resistencia Mágica, +100% Regeneración de Vida, +20 Aceleración de Habilidad",
            passive = "Pasiva - Vitalidad Ilimitada: Aumenta todas las curaciones y escudos recibidos en tu campeón en un 30%.",
            iconUrl = "$DDRAGON_ITEM_IMG/3065.png"
        ),
        WildRiftItem(
            id = "randuins_omen",
            name = "Presagio de Randuin (Randuin's Omen)",
            category = ItemCategory.DEFENSE,
            goldCost = 2800,
            stats = "+400 Vida, +55 Armadura",
            passive = "Pasiva - Resiliencia: Reduce el daño de los golpes críticos recibidos en un 16% y ralentiza la velocidad de ataque del agresor un 15%.",
            iconUrl = "$DDRAGON_ITEM_IMG/3143.png"
        ),
        WildRiftItem(
            id = "frozen_heart",
            name = "Corazón de Hielo (Frozen Heart)",
            category = ItemCategory.DEFENSE,
            goldCost = 2700,
            stats = "+80 Armadura, +300 Maná, +20 Aceleración de Habilidad",
            passive = "Pasiva - Caricia Invernal: Reduce la velocidad de ataque de todos los enemigos cercanos hasta un 36%.",
            iconUrl = "$DDRAGON_ITEM_IMG/3110.png"
        ),

        // SOPORTE
        WildRiftItem(
            id = "spectral_sickle",
            name = "Hoz Espectral (Spectral Sickle)",
            category = ItemCategory.SUPPORT,
            goldCost = 500,
            stats = "+6 Daño de Ataque o +12 Poder de Habilidad Adaptable, +80 Vida",
            passive = "Pasiva - Tributo: Hostigar campeones o estructuras enemigas con habilidades o ataques básicos genera oro y se transforma en Garra de la Luna Negra.",
            iconUrl = "$DDRAGON_ITEM_IMG/3854.png"
        ),
        WildRiftItem(
            id = "relic_shield",
            name = "Escudo Reliquia (Relic Shield)",
            category = ItemCategory.SUPPORT,
            goldCost = 500,
            stats = "+100 Vida, +5 Aceleración de Habilidad",
            passive = "Pasiva - Botín de Guerra: Ejecuta súbditos con poca vida compartiendo el oro completo con el aliado más cercano y curándolo.",
            iconUrl = "$DDRAGON_ITEM_IMG/3858.png"
        ),
        WildRiftItem(
            id = "ardent_censer",
            name = "Incensario Ardiente (Ardent Censer)",
            category = ItemCategory.SUPPORT,
            goldCost = 2800,
            stats = "+60 Poder de Habilidad, +250 Vida, +10 Aceleración de Habilidad, +5% Velocidad Mov.",
            passive = "Pasiva - Fervor: Curar o poner escudos a un aliado os otorga a ambos un 10-30% de velocidad de ataque y 16-30 de daño mágico en ataques.",
            iconUrl = "$DDRAGON_ITEM_IMG/3504.png"
        ),

        // BOTAS & ENCANTAMIENTOS (EXCLUSIVOS DE WILD RIFT)
        WildRiftItem(
            id = "zhonya_enchant",
            name = "Encantamiento de Zhonya (Stasis)",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Éxtasis: Te vuelve invulnerable e imposible de seleccionar durante 2.5 segundos, pero incapaz de realizar acciones (90s enfriamiento).",
            iconUrl = "$DDRAGON_ITEM_IMG/3157.png"
        ),
        WildRiftItem(
            id = "proto_enchant",
            name = "Encantamiento Protocinturón (Protobelt)",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Misil de Fuego: Te impulsa hacia adelante y desata una ráfaga de proyectiles que infligen daño mágico en cono (60s enfriamiento).",
            iconUrl = "$DDRAGON_ITEM_IMG/3152.png"
        ),
        WildRiftItem(
            id = "quicksilver_enchant",
            name = "Encantamiento Fajín Mercurial (Quicksilver)",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Mercurio: Elimina al instante todos los efectos de control de masas que afecten a tu campeón y otorga 50% de velocidad de movimiento (60s enfriamiento).",
            iconUrl = "$DDRAGON_ITEM_IMG/3140.png"
        ),
        WildRiftItem(
            id = "gargoyle_enchant",
            name = "Encantamiento de Gárgola (Gargoyle)",
            category = ItemCategory.BOOTS_ENCHANTMENT,
            goldCost = 800,
            stats = "Mejora activa para cualquier bota mejorada",
            passive = "Activa - Piel Pétrea: Otorga un escudo masivo equivalente al 20% de tu vida máxima (aumentado al 40% si hay 3+ enemigos cerca).",
            iconUrl = "$DDRAGON_ITEM_IMG/3193.png"
        )
    )

    // ==========================================
    // CATÁLOGO DE OBJETIVOS DE MAPA (MONSTRUOS ÉPICOS DE WILD RIFT)
    // ==========================================
    val mapObjectives: List<MapObjectiveItem> = listOf(
        MapObjectiveItem(
            id = "infernal_dragon",
            name = "Dragón Infernal (Fuego)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/infernal.png",
            buffDescription = "Otorga a todo el equipo +3% de daño de ataque y +3% de poder de habilidad acumulable.",
            tactics = "Prioriza asegurar la línea de dragón empujando oleadas 30s antes de su aparición. Ideal para composiciones de daño explosivo."
        ),
        MapObjectiveItem(
            id = "mountain_dragon",
            name = "Dragón de Montaña (Tierra)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/mountain.png",
            buffDescription = "Otorga a todo el equipo +6% de armadura y resistencia mágica adicionales.",
            tactics = "Refuerza la línea frontal de los tanques, facilitando asedios prolongados bajo torre enemiga."
        ),
        MapObjectiveItem(
            id = "ocean_dragon",
            name = "Dragón de los Océanos (Agua)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/ocean.png",
            buffDescription = "Restaura un 2.5% de la vida faltante cada 5 segundos a todos los miembros del equipo.",
            tactics = "Otorga sustain inagotable en el mapa para desgastar al rival sin necesidad de volver a base."
        ),
        MapObjectiveItem(
            id = "ice_dragon",
            name = "Dragón de Hielo (Glacial)",
            spawnTime = "Minuto 4:00",
            respawnTime = "Reaparece cada 4:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/chemtech.png",
            buffDescription = "Otorga +7 de aceleración de habilidad a todo el equipo y crea zonas de escarcha.",
            tactics = "Permite rotar habilidades mucho más rápido en escaramuzas y peleas por el Barón."
        ),
        MapObjectiveItem(
            id = "rift_herald",
            name = "Heraldo de la Grieta (Rift Herald)",
            spawnTime = "Minuto 5:00",
            respawnTime = "Solo aparece 1 por partida",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/507.png",
            buffDescription = "Al recoger el Ojo del Heraldo, permite invocar al Heraldo para embestir y destruir placas de torretas enemigas.",
            tactics = "Úsalo en la línea de Barón o Mid para derribar la primera torreta y desbloquear rotaciones tempranas."
        ),
        MapObjectiveItem(
            id = "elder_dragon",
            name = "Dragón Anciano (Elder Dragon)",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/dragon-icons/elder.png",
            buffDescription = "Ataques y habilidades queman a los rivales. Si la vida del rival cae por debajo del 15%, es ejecutado de inmediato.",
            tactics = "El buff más decisivo de Wild Rift en el juego tardío. Asegura visión perimetral con centinelas antes de iniciar."
        ),
        MapObjectiveItem(
            id = "baron_nashor",
            name = "Barón Nashor",
            spawnTime = "Minuto 12:00",
            respawnTime = "Reaparece cada 5:00",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/508.png",
            buffDescription = "Otorga Mano del Barón: potencia el daño de los súbditos aliados cercanos y reduce el tiempo de Retirada a 4 segundos.",
            tactics = "Aprovecha el buff para asediar las tres líneas simultáneamente y forzar la caída de inhibidores."
        )
    )

    // ==========================================
    // ROSTER DE CAMPEONES DE WILD RIFT CON HABILIDADES COMPLETAS
    // ==========================================
    val champions: List<Champion> = listOf(
        Champion(
            id = "morgana",
            name = "Morgana",
            title = "La Desolada",
            ddragonId = "Morgana",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Morgana.png",
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
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            recommendedSpells = listOf("Destello", "Prender"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerDot.png"),
            coreItems = listOf("Tormento de Liandry", "Orbe del Infinito", "Corona de la Reina", "Sombrero Mortal de Rabadon"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3151.png", "$DDRAGON_ITEM_IMG/4628.png", "$DDRAGON_ITEM_IMG/4644.png", "$DDRAGON_ITEM_IMG/3089.png"),
            situationalItems = listOf("Morellonomicón", "Velo de Banshee", "Cetro de Cristal de Rylai"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3165.png", "$DDRAGON_ITEM_IMG/3102.png", "$DDRAGON_ITEM_IMG/3116.png"),
            skillOrder = "Max Q > W > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Sifón de Alma", "$DDRAGON_PASSIVE_IMG/Morgana_Passive.png", "Morgana se cura un porcentaje del daño mágico infligido a campeones y monstruos grandes."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Hechizo Oscuro", "$DDRAGON_SPELL_IMG/MorganaQ.png", "Lanza una esfera de magia estelar que inmoviliza al primer objetivo impactado hasta por 2.75s.", "10s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Sombra Atormentada", "$DDRAGON_SPELL_IMG/MorganaW.png", "Maldice una zona, infligiendo daño mágico continuo según la vida faltante del enemigo.", "12s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Escudo Negro", "$DDRAGON_SPELL_IMG/MorganaE.png", "Otorga un escudo a un aliado que absorbe daño mágico e inmune a todos los efectos de control de masas.", "16s"),
                ChampionSkill("4", "Definitiva (R)", "Grilletes de Alma", "$DDRAGON_SPELL_IMG/MorganaR.png", "Encadena a los campeones enemigos cercanos infligiendo daño y ralentizándolos; tras 3s los aturde.", "80s")
            ),
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "viego",
            name = "Viego",
            title = "El Rey Arruinado",
            ddragonId = "Viego",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Viego.png",
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
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "Castigo"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerSmite.png"),
            coreItems = listOf("Fuerza de la Trinidad", "Hoja del Rey Arruinado", "Desgarrador Divino", "Ángel Guardián"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3078.png", "$DDRAGON_ITEM_IMG/3153.png", "$DDRAGON_ITEM_IMG/6632.png", "$DDRAGON_ITEM_IMG/3026.png"),
            situationalItems = listOf("Baile de la Muerte", "Fauces de Malmortius", "Filo de la Noche"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/6333.png", "$DDRAGON_ITEM_IMG/3156.png", "$DDRAGON_ITEM_IMG/3814.png"),
            skillOrder = "Max Q > E > W",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Dominio del Soberano", "$DDRAGON_PASSIVE_IMG/Viego_Passive.png", "Viego puede poseer temporalmente los espectros de los campeones enemigos que ayuda a asesinar."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Hoja del Rey Arruinado", "$DDRAGON_SPELL_IMG/ViegoQ.png", "Apuñala hacia adelante infligiendo daño físico y curándose pasivamente en ataques consecutivos.", "5s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Fauce Espectral", "$DDRAGON_SPELL_IMG/ViegoW.png", "Carga y se abalanza hacia adelante lanzando una niebla que aturde al primer enemigo.", "8s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Camino del Tormento", "$DDRAGON_SPELL_IMG/ViegoE.png", "Envuelve un muro cercano en Niebla Negra, ganando camuflaje y velocidad de ataque.", "12s"),
                ChampionSkill("4", "Definitiva (R)", "Rompecorazones", "$DDRAGON_SPELL_IMG/ViegoR.png", "Se teletransporta e impacta al campeón enemigo con menos vida, ejecutándolo y ralentizando el área.", "90s")
            ),
            isRanged = false,
            isFrontline = false
        ),
        Champion(
            id = "sett",
            name = "Sett",
            title = "El Jefe",
            ddragonId = "Sett",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Sett.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.SUPPORT, LaneRole.MID),
            tier = "S+",
            winrate = 53.6,
            pickRate = 15.2,
            banRate = 16.8,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Colosal absorción de daño gracias a su Coraje acumulado y represalia en área con daño verdadero mortal.",
            advantageAgainst = listOf("Irelia", "Yasuo", "Riven", "Katarina", "Yone"),
            counteredBy = listOf("Vayne", "Fiora", "Morgana", "Jax", "Kennen"),
            synergies = listOf("Viego", "Morgana", "Jinx", "Ahri", "Orianna"),
            tacticalAdvice = "Sett: Castiga las iniciaciones cargando su Coraje al 100% y desatando Rompecaras en el centro de la pelea.",
            recommendedRunes = "Conquistador • Triunfo",
            runeTreeDetails = "Conquistador > Triunfo > Último Esfuerzo > Fuerzas Renovadas",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "Prender"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerDot.png"),
            coreItems = listOf("Rompecascos", "Fuerza de la Trinidad", "Coraza del Muerto", "Guantelete de Sterak"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3181.png", "$DDRAGON_ITEM_IMG/3078.png", "$DDRAGON_ITEM_IMG/3742.png", "$DDRAGON_ITEM_IMG/3053.png"),
            situationalItems = listOf("Malla de Espinas", "Fuerza de la Naturaleza", "Presagio de Randuin"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3075.png", "$DDRAGON_ITEM_IMG/4401.png", "$DDRAGON_ITEM_IMG/3143.png"),
            skillOrder = "Max W > Q > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Furia de la Arena", "$DDRAGON_PASSIVE_IMG/Sett_Passive.png", "Los ataques de Sett alternan entre puño izquierdo y derecho rápido. Gana regeneración de vida según la vida faltante."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Salen Chispas", "$DDRAGON_SPELL_IMG/SettQ.png", "Sett gana velocidad hacia enemigos y sus siguientes dos ataques infligen daño porcentual.", "7s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Trancazo", "$DDRAGON_SPELL_IMG/SettW.png", "Convierte el daño recibido en Coraje y dispara un golpe central que inflige daño verdadero masivo.", "14s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Rompecaras", "$DDRAGON_SPELL_IMG/SettE.png", "Atrae a los enemigos a ambos lados estrellándolos entre sí y aturdiéndolos.", "12s"),
                ChampionSkill("4", "Definitiva (R)", "El Espectáculo", "$DDRAGON_SPELL_IMG/SettR.png", "Agarra a un campeón enemigo y lo estampa contra el suelo, dañando y ralentizando a los rivales.", "80s")
            ),
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "vayne",
            name = "Vayne",
            title = "La Cazadora Nocturna",
            ddragonId = "Vayne",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Vayne.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S+",
            winrate = 53.9,
            pickRate = 14.8,
            banRate = 19.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Proyectiles de plata con daño verdadero del 12% de vida máxima del enemigo. Maestro del kiteo y la invisibilidad.",
            advantageAgainst = listOf("Cho'Gath", "Sett", "Sion", "Ornn", "Mundo", "Nautilus", "Vi"),
            counteredBy = listOf("Caitlyn", "Draven", "Lucian", "Teemo", "Tristana"),
            synergies = listOf("Lulu", "Janna", "Morgana", "Nami", "Yuumi"),
            tacticalAdvice = "Vayne: Destroza la primera línea enemiga con Daño Verdadero sin importar cuánta armadura acumulen.",
            recommendedRunes = "Cadencia Letal • Triunfo",
            runeTreeDetails = "Cadencia Letal > Triunfo > Cazador Titánico > Dulces Frutos (Secundaria: Inspiración)",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            recommendedSpells = listOf("Destello", "Fantasmal"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerHaste.png"),
            coreItems = listOf("Hoja del Rey Arruinado", "Cañón de Fuego Rápido", "Filo del Infinito", "Bailarín Espectral"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3153.png", "$DDRAGON_ITEM_IMG/3094.png", "$DDRAGON_ITEM_IMG/3031.png", "$DDRAGON_ITEM_IMG/3046.png"),
            situationalItems = listOf("Ángel Guardián", "Fauces de Malmortius", "Recordatorio Mortal"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3026.png", "$DDRAGON_ITEM_IMG/3156.png", "$DDRAGON_ITEM_IMG/3033.png"),
            skillOrder = "Max W > Q > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Cazadora Nocturna", "$DDRAGON_PASSIVE_IMG/Vayne_Passive.png", "Vayne obtiene 30 de velocidad de movimiento adicional al avanzar hacia campeones enemigos."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Voltereta", "$DDRAGON_SPELL_IMG/VayneQ.png", "Rueda en una dirección y potencia su siguiente ataque básico. Durante la definitiva otorga invisibilidad.", "4s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Proyectiles de Plata", "$DDRAGON_SPELL_IMG/VayneW.png", "Cada 3 ataques consecutivos inflige un porcentaje de la vida máxima del enemigo como daño verdadero.", "Pasiva"),
                ChampionSkill("3", "Habilidad 3 (E)", "Condena", "$DDRAGON_SPELL_IMG/VayneE.png", "Dispara un dardo que empuja hacia atrás al objetivo; si choca contra un muro, queda aturdido.", "16s"),
                ChampionSkill("4", "Definitiva (R)", "Hora Final", "$DDRAGON_SPELL_IMG/VayneR.png", "Obtiene daño de ataque masivo, triplica la velocidad de su pasiva e invisibilidad en cada Voltereta.", "75s")
            ),
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "janna",
            name = "Janna",
            title = "La Furia de la Tormenta",
            ddragonId = "Janna",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Janna.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = emptyList(),
            tier = "S+",
            winrate = 53.4,
            pickRate = 9.8,
            banRate = 6.2,
            damageType = DamageType.MAGIC,
            summary = "Reina del desengage. Neutraliza iniciadores cuerpo a cuerpo con tornados y su definitiva Monzón de curación masiva.",
            advantageAgainst = listOf("Sett", "Vi", "Rell", "Leona", "Alistar", "Samira", "Katarina"),
            counteredBy = listOf("Blitzcrank", "Pyke", "Lux", "Zyra", "Nautilus"),
            synergies = listOf("Vayne", "Jinx", "Caitlyn", "Kai'Sa", "Ezreal"),
            tacticalAdvice = "Janna: Interrumpe saltos de Vi y Sett con Tornado Aullante y salva a tus tiradores con Monzón.",
            recommendedRunes = "Aery • Flujo de Maná",
            runeTreeDetails = "Aery > Flujo de Maná > Trascendencia > Dulces Frutos (Secundaria: Inspiración)",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            recommendedSpells = listOf("Destello", "Extenuación"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerExhaust.png"),
            coreItems = listOf("Hoz Espectral", "Incensario Ardiente", "Bastón de Agua Fluyente", "Redención"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3854.png", "$DDRAGON_ITEM_IMG/3504.png", "$DDRAGON_ITEM_IMG/6616.png", "$DDRAGON_ITEM_IMG/3107.png"),
            situationalItems = listOf("Blandemalas Armonioso", "Corona de la Reina", "Velo de Banshee"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3011.png", "$DDRAGON_ITEM_IMG/4644.png", "$DDRAGON_ITEM_IMG/3102.png"),
            skillOrder = "Max E > W > Q",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Viento a Favor", "$DDRAGON_PASSIVE_IMG/Janna_Passive.png", "Otorga velocidad de movimiento a los aliados que se muevan hacia Janna."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Vendaval Aullante", "$DDRAGON_SPELL_IMG/JannaQ.png", "Crea un torbellino que se desplaza en línea recta, levantando por los aires a los enemigos.", "12s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Céfiro", "$DDRAGON_SPELL_IMG/JannaW.png", "Lanza un elemental de aire que inflige daño mágico y ralentiza al objetivo.", "7s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Ojo de la Tormenta", "$DDRAGON_SPELL_IMG/JannaE.png", "Escuda a un aliado o torreta otorgándole daño de ataque adicional mientras persista.", "14s"),
                ChampionSkill("4", "Definitiva (R)", "Monzón", "$DDRAGON_SPELL_IMG/JannaR.png", "Repele a todos los enemigos cercanos con una ráfaga mágica y canaliza curación continua en área.", "80s")
            ),
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "nautilus",
            name = "Nautilus",
            title = "El Titán de las Profundidades",
            ddragonId = "Nautilus",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Nautilus.png",
            primaryRole = LaneRole.SUPPORT,
            secondaryRoles = listOf(LaneRole.JUNGLE, LaneRole.TOP),
            tier = "S",
            winrate = 52.8,
            pickRate = 11.2,
            banRate = 12.0,
            damageType = DamageType.MAGIC,
            summary = "El campeón con mayor cantidad de control de masas del juego: anclaje, inmovilización pasiva, ralentización y definitiva fija.",
            advantageAgainst = listOf("Caitlyn", "Jinx", "Lux", "Ahri", "Ezreal", "Zed"),
            counteredBy = listOf("Morgana", "Olaf", "Braum", "Sivir", "Janna"),
            synergies = listOf("Kai'Sa", "Samira", "Yasuo", "Viego", "Tristana"),
            tacticalAdvice = "Nautilus: Inicia con Carga de las Profundidades garantizada sobre el carry rival para que tu equipo ejecute la baja.",
            recommendedRunes = "Aumento Glacial • Fuerzas Renovadas",
            runeTreeDetails = "Aumento Glacial > Fuente de Vida > Fuerzas Renovadas > Cazador Titánico",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png",
            recommendedSpells = listOf("Destello", "Prender"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerDot.png"),
            coreItems = listOf("Escudo Reliquia", "Promesa del Caballero", "Malla de Espinas", "Presagio de Randuin"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3858.png", "$DDRAGON_ITEM_IMG/3109.png", "$DDRAGON_ITEM_IMG/3075.png", "$DDRAGON_ITEM_IMG/3143.png"),
            situationalItems = listOf("Fuerza de la Naturaleza", "Corazón de Hielo", "Gárgola"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/4401.png", "$DDRAGON_ITEM_IMG/3110.png", "$DDRAGON_ITEM_IMG/3193.png"),
            skillOrder = "Max Q > W > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Golpe Escalonado", "$DDRAGON_PASSIVE_IMG/Nautilus_Passive.png", "Los primeros ataques básicos a un objetivo infligen daño físico adicional e inmovilizan brevemente."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Línea de Dragado", "$DDRAGON_SPELL_IMG/NautilusQ.png", "Lanza su ancla; si impacta a un enemigo, se atraen mutuamente infligiendo daño mágico.", "11s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Ira del Titán", "$DDRAGON_SPELL_IMG/NautilusW.png", "Se envuelve en un escudo de energía oscura y hace que sus ataques quemen en área.", "12s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Aguas Revueltas", "$DDRAGON_SPELL_IMG/NautilusE.png", "Genera tres explosiones en ondas concéntricas que dañan y ralentizan a los enemigos.", "6s"),
                ChampionSkill("4", "Definitiva (R)", "Carga de las Profundidades", "$DDRAGON_SPELL_IMG/NautilusR.png", "Dispara una onda expansiva subterránea teledirigida que persigue a un campeón y lo lanza por los aires.", "70s")
            ),
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "ahri",
            name = "Ahri",
            title = "La Mujer Zorro de Nueve Colas",
            ddragonId = "Ahri",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Ahri.png",
            primaryRole = LaneRole.MID,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.4,
            pickRate = 13.5,
            banRate = 8.1,
            damageType = DamageType.MAGIC,
            summary = "Excelente movilidad con 3 impulsos de definitiva y Encanto que inhabilita a los carries y amplifica el daño.",
            advantageAgainst = listOf("Katarina", "Zed", "Lux", "Twisted Fate", "Aurelion Sol"),
            counteredBy = listOf("Yasuo", "Kassadin", "Morgana", "Tristana", "Galio"),
            synergies = listOf("Vi", "Lee Sin", "Viego", "Nautilus", "Jinx"),
            tacticalAdvice = "Ahri: Flanquea en peleas grupales con Impulso Espiritual y conecta Encanto para forzar bajas instantáneas.",
            recommendedRunes = "Electrocutar • Impacto Repentino",
            runeTreeDetails = "Electrocutar > Impacto Repentino > Colección de Ojos > Dulces Frutos (Secundaria: Inspiración)",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            recommendedSpells = listOf("Destello", "Prender"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerDot.png"),
            coreItems = listOf("Eco de Luden", "Orbe del Infinito", "Sombrero Mortal de Rabadon", "Báculo del Vacío"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3285.png", "$DDRAGON_ITEM_IMG/4628.png", "$DDRAGON_ITEM_IMG/3089.png", "$DDRAGON_ITEM_IMG/3135.png"),
            situationalItems = listOf("Corona de la Reina", "Velo de Banshee", "Morellonomicón"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/4644.png", "$DDRAGON_ITEM_IMG/3102.png", "$DDRAGON_ITEM_IMG/3165.png"),
            skillOrder = "Max Q > W > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Hurto de Esencia", "$DDRAGON_PASSIVE_IMG/Ahri_Passive.png", "Asesinar súbditos y campeones otorga fragmentos de esencia que curan a Ahri."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Orbe del Engaño", "$DDRAGON_SPELL_IMG/AhriQ.png", "Lanza y recupera su orbe infligiendo daño mágico al salir y daño verdadero al regresar.", "7s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Fuego Zorruno", "$DDRAGON_SPELL_IMG/AhriW.png", "Libera tres llamas zorrunas que persiguen a los enemigos cercanos y gana velocidad de movimiento.", "6s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Encanto", "$DDRAGON_SPELL_IMG/AhriE.png", "Lanza un beso que enamora al primer enemigo impactado, haciéndolo caminar indefenso hacia ella.", "12s"),
                ChampionSkill("4", "Definitiva (R)", "Impulso Espiritual", "$DDRAGON_SPELL_IMG/AhriR.png", "Se desliza hasta 3 veces disparando rayos de esencia mágica. Las bajas enemigas otorgan cargas adicionales.", "80s")
            ),
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "caitlyn",
            name = "Caitlyn",
            title = "La Sheriff de Piltóver",
            ddragonId = "Caitlyn",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Caitlyn.png",
            primaryRole = LaneRole.ADC,
            secondaryRoles = emptyList(),
            tier = "S",
            winrate = 52.1,
            pickRate = 15.6,
            banRate = 11.4,
            damageType = DamageType.PHYSICAL,
            summary = "El mayor alcance de ataque básico inicial de Wild Rift. Asedio brutal bajo torretas con Trampas para Yordles.",
            advantageAgainst = listOf("Vayne", "Kaisa", "Samira", "Short-Range ADC"),
            counteredBy = listOf("Morgana", "Vi", "Nautilus", "Zed", "Yasuo"),
            synergies = listOf("Lux", "Morgana", "Nautilus", "Thresh", "Leona"),
            tacticalAdvice = "Caitlyn: Coloca trampas en los cuellos de botella de la jungla y objetivos antes de que aparezca el Dragón.",
            recommendedRunes = "Primer Golpe • Triunfo",
            runeTreeDetails = "Primer Golpe > Triunfo > Cazador Titánico > Dulces Frutos",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            recommendedSpells = listOf("Destello", "Barrera"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerBarrier.png"),
            coreItems = listOf("Recaudadora", "Filo del Infinito", "Cañón de Fuego Rápido", "Recordatorio Mortal"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/6676.png", "$DDRAGON_ITEM_IMG/3031.png", "$DDRAGON_ITEM_IMG/3094.png", "$DDRAGON_ITEM_IMG/3033.png"),
            situationalItems = listOf("Ángel Guardián", "Bailarín Espectral", "Fauces de Malmortius"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3026.png", "$DDRAGON_ITEM_IMG/3046.png", "$DDRAGON_ITEM_IMG/3156.png"),
            skillOrder = "Max Q > W > E",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Disparo a la Cabeza", "$DDRAGON_PASSIVE_IMG/Caitlyn_Passive.png", "Cada varios disparos o sobre objetivos atrapados en trampas, Caitlyn dispara un tiro crítico demoledor."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Pacificadora de Piltóver", "$DDRAGON_SPELL_IMG/CaitlynQ.png", "Prepara su rifle para disparar un proyectil de largo alcance que atraviesa a los enemigos.", "9s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Trampa para Yordles", "$DDRAGON_SPELL_IMG/CaitlynW.png", "Coloca una trampa invisible que inmoviliza a los enemigos que la pisan y activa Disparo a la Cabeza.", "12s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Red Calibre 90", "$DDRAGON_SPELL_IMG/CaitlynE.png", "Dispara una red pesada que ralentiza al objetivo y empuja a Caitlyn hacia atrás.", "14s"),
                ChampionSkill("4", "Definitiva (R)", "As en la Manga", "$DDRAGON_SPELL_IMG/CaitlynR.png", "Apunta a un campeón enemigo a distancia extrema y dispara un proyectil letal teledirigido.", "70s")
            ),
            isRanged = true,
            isFrontline = false
        ),
        Champion(
            id = "vi",
            name = "Vi",
            title = "La Defensora de Piltóver",
            ddragonId = "Vi",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Vi.png",
            primaryRole = LaneRole.JUNGLE,
            secondaryRoles = listOf(LaneRole.TOP),
            tier = "S",
            winrate = 52.0,
            pickRate = 10.4,
            banRate = 7.5,
            damageType = DamageType.PHYSICAL,
            summary = "Iniciación de fijación directa con Rompecaras y definitiva imparable que neutraliza al tirador enemigo.",
            advantageAgainst = listOf("Caitlyn", "Jinx", "Lux", "Ezreal", "Twisted Fate"),
            counteredBy = listOf("Morgana", "Janna", "Sett", "Viego", "Jax"),
            synergies = listOf("Yasuo", "Ahri", "Kai'Sa", "Morgana", "Sett"),
            tacticalAdvice = "Vi: Guarda Asalto y Lesiones para bloquear al carry enemigo cuando no tenga apoyo de escudos.",
            recommendedRunes = "Conquistador • Impacto Repentino",
            runeTreeDetails = "Conquistador > Impacto Repentino > Cazador Titánico > Pionero (Secundaria: Dominación)",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            recommendedSpells = listOf("Destello", "Castigo"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerSmite.png"),
            coreItems = listOf("Fuerza de la Trinidad", "Desgarrador Divino", "Baile de la Muerte", "Guantelete de Sterak"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3078.png", "$DDRAGON_ITEM_IMG/6632.png", "$DDRAGON_ITEM_IMG/6333.png", "$DDRAGON_ITEM_IMG/3053.png"),
            situationalItems = listOf("Malla de Espinas", "Ángel Guardián", "Coraza del Muerto"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3075.png", "$DDRAGON_ITEM_IMG/3026.png", "$DDRAGON_ITEM_IMG/3742.png"),
            skillOrder = "Max Q > E > W",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Blindaje", "$DDRAGON_PASSIVE_IMG/Vi_Passive.png", "Al impactar con habilidades, Vi genera un escudo temporal que absorbe daño."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Rompebóvedas", "$DDRAGON_SPELL_IMG/ViQ.png", "Carga sus guanteletes y se abalanza hacia adelante lanzando por los aires a los enemigos.", "8s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Golpes Abollantes", "$DDRAGON_SPELL_IMG/ViW.png", "Cada 3 ataques reduce la armadura del objetivo en un 20% y otorga velocidad de ataque.", "Pasiva"),
                ChampionSkill("3", "Habilidad 3 (E)", "Fuerza Excesiva", "$DDRAGON_SPELL_IMG/ViE.png", "Su siguiente ataque atraviesa al objetivo infligiendo daño de cono a los enemigos detrás.", "1s"),
                ChampionSkill("4", "Definitiva (R)", "Asalto y Lesiones", "$DDRAGON_SPELL_IMG/ViR.png", "Persigue a un campeón enemigo haciéndose imparable, lo lanza por los aires y lo estampa contra el suelo.", "70s")
            ),
            isRanged = false,
            isFrontline = true
        ),
        Champion(
            id = "chogath",
            name = "Cho'Gath",
            title = "El Terror del Vacío",
            ddragonId = "Chogath",
            avatarUrl = "$DDRAGON_CHAMP_IMG/Chogath.png",
            primaryRole = LaneRole.TOP,
            secondaryRoles = listOf(LaneRole.MID, LaneRole.SUPPORT),
            tier = "A+",
            winrate = 51.7,
            pickRate = 7.3,
            banRate = 5.2,
            damageType = DamageType.TRUE_HYBRID,
            summary = "Festín infinito: crece en tamaño, vida máxima y daño con cada acumulación devorando súbditos y campeones.",
            advantageAgainst = listOf("Rell", "Vi", "Alistar", "Malphite", "Nautilus"),
            counteredBy = listOf("Vayne", "Fiora", "Gwen", "Sett", "Morgana"),
            synergies = listOf("Viego", "Morgana", "Jinx", "Yasuo", "Ahri"),
            tacticalAdvice = "Cho'Gath: Asegura monstruos épicos combinando Festín (1200+ daño verdadero) con el Castigo de tu jungla.",
            recommendedRunes = "Agarre del Perpetuo • Segundo Aire",
            runeTreeDetails = "Agarre del Perpetuo > Fuente de Vida > Segundo Aire > Sobrecrecimiento (Secundaria: Inspiración)",
            primaryRuneIconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            recommendedSpells = listOf("Destello", "Prender"),
            spellsIcons = listOf("$DDRAGON_SPELL_IMG/SummonerFlash.png", "$DDRAGON_SPELL_IMG/SummonerDot.png"),
            coreItems = listOf("Corazón de Acero", "Égida de Fuego Solar", "Malla de Espinas", "Fuerza de la Naturaleza"),
            coreItemsIcons = listOf("$DDRAGON_ITEM_IMG/3068.png", "$DDRAGON_ITEM_IMG/3068.png", "$DDRAGON_ITEM_IMG/3075.png", "$DDRAGON_ITEM_IMG/4401.png"),
            situationalItems = listOf("Presagio de Randuin", "Rostro Espiritual", "Gárgola"),
            situationalItemsIcons = listOf("$DDRAGON_ITEM_IMG/3143.png", "$DDRAGON_ITEM_IMG/3065.png", "$DDRAGON_ITEM_IMG/3193.png"),
            skillOrder = "Max E > W > Q",
            skills = listOf(
                ChampionSkill("P", "Pasiva", "Carnívoro", "$DDRAGON_PASSIVE_IMG/ChoGath_Passive.png", "Asesinar unidades restaura vida y maná a Cho'Gath de forma permanente en fase de líneas."),
                ChampionSkill("1", "Habilidad 1 (Q)", "Ruptura", "$DDRAGON_SPELL_IMG/ChoGathQ.png", "Hace que el suelo estalle en púas levantando a los enemigos y ralentizándolos un 60%.", "7s"),
                ChampionSkill("2", "Habilidad 2 (W)", "Grito Salvaje", "$DDRAGON_SPELL_IMG/ChoGathW.png", "Desata un cono sónico que silencia e inflige daño mágico a los enemigos.", "11s"),
                ChampionSkill("3", "Habilidad 3 (E)", "Púas Vorpal", "$DDRAGON_SPELL_IMG/ChoGathE.png", "Los siguientes tres ataques disparan púas que infligen daño porcentual de vida y ralentizan.", "6s"),
                ChampionSkill("4", "Definitiva (R)", "Festín", "$DDRAGON_SPELL_IMG/ChoGathR.png", "Devora a un objetivo infligiendo daño verdadero masivo; si lo asesina gana tamaño y vida máxima permanente.", "65s")
            ),
            isRanged = false,
            isFrontline = true
        )
    )

    fun getChampionById(id: String): Champion? {
        return champions.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getChampionsByRole(role: LaneRole): List<Champion> {
        return champions.filter { it.primaryRole == role || it.secondaryRoles.contains(role) }
    }

    fun analyzeDraft(
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>
    ): DraftAnalysisResult {
        var physCount = 0
        var magicCount = 0
        var trueCount = 0

        enemies.forEach { champ ->
            when (champ.damageType) {
                DamageType.PHYSICAL -> physCount++
                DamageType.MAGIC -> magicCount++
                DamageType.TRUE_HYBRID -> trueCount++
            }
        }

        val totalEnemies = (physCount + magicCount + trueCount).coerceAtLeast(1)
        val physPct = (physCount * 100) / totalEnemies
        val magicPct = (magicCount * 100) / totalEnemies
        val truePct = 100 - (physPct + magicPct)

        val frontlineAllies = allies.count { it.isFrontline }
        val frontlineStatus = when {
            frontlineAllies >= 2 -> "Frontline Sólida (${frontlineAllies} Tanques/Luchadores)"
            frontlineAllies == 1 -> "Frontline Moderada (1 Tanque)"
            else -> "¡Cuidado! Falta Frontline / Iniciación en el equipo aliado"
        }

        var directMatchupWarning: String? = null
        var directCounterBestPick: String? = null

        val enemySett = enemies.find { it.id == "sett" }
        val enemyVi = enemies.find { it.id == "vi" }
        val enemyCaitlyn = enemies.find { it.id == "caitlyn" }

        if (enemySett != null && enemyVi != null) {
            directMatchupWarning = "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage o escudos antimagia."
            directCounterBestPick = "Morgana o Janna"
        } else if (enemyCaitlyn != null) {
            directMatchupWarning = "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus."
            directCounterBestPick = "Nautilus o Viego"
        }

        val availableChampions = champions.filter { champ ->
            !allies.any { it.id == champ.id } && !enemies.any { it.id == champ.id }
        }

        val candidates = availableChampions.filter { champ ->
            champ.primaryRole == myRole || champ.secondaryRoles.contains(myRole)
        }.ifEmpty { availableChampions }

        val recommendations = candidates.map { champ ->
            var score = champ.winrate

            val countersFound = champ.advantageAgainst.count { adv ->
                enemies.any { it.name.equals(adv, ignoreCase = true) || it.id.equals(adv, ignoreCase = true) }
            }
            score += (countersFound * 1.5)

            val synergiesFound = champ.synergies.count { syn ->
                allies.any { it.name.equals(syn, ignoreCase = true) || it.id.equals(syn, ignoreCase = true) }
            }
            score += (synergiesFound * 1.0)

            val badge = when {
                champ.tier == "S+" && countersFound >= 2 -> "★ ELECCIÓN ÓPTIMA (Counter & Meta)"
                champ.tier == "S+" -> "★ TIER S+ (Alta Prioridad)"
                countersFound >= 1 -> "✔ COUNTER DIRECTO (+${countersFound})"
                else -> "Recomendación Balanceada"
            }

            val reason = if (champ.tacticalAdvice.isNotBlank()) {
                champ.tacticalAdvice
            } else {
                "${champ.name}: Excelente selección en ${myRole.displayName}. Complementa la composición y ofrece ${champ.damageType.displayName}."
            }

            DraftRecommendation(
                champion = champ,
                estimatedWinrate = (score * 10).toInt() / 10.0,
                advantageBadge = badge,
                tacticalReason = reason,
                runes = champ.recommendedRunes
            )
        }.sortedByDescending { it.estimatedWinrate }

        return DraftAnalysisResult(
            physicalDamagePercent = physPct,
            magicDamagePercent = magicPct,
            trueDamagePercent = truePct,
            frontlineStatus = frontlineStatus,
            directMatchupWarning = directMatchupWarning,
            directCounterBestPick = directCounterBestPick,
            recommendations = recommendations
        )
    }
}
