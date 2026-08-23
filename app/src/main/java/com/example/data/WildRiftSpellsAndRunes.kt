package com.example.data

import com.example.model.RuneItem
import com.example.model.SummonerSpellItem

object WildRiftSpellsAndRunes {
    const val SPELL_FLASH = "https://static.wikia.nocookie.net/leagueoflegends/images/7/74/Flash.png/revision/latest?cb=20181116071628&path-prefix=es"
    const val SPELL_IGNITE = "https://static.wikia.nocookie.net/leagueoflegends/images/f/f4/Ignite.png/revision/latest?cb=20180514003345"
    const val SPELL_SMITE = "https://static.wikia.nocookie.net/leagueoflegends/images/0/05/Smite.png/revision/latest?cb=20180514003641"
    const val SPELL_BARRIER = "https://static.wikia.nocookie.net/leagueoflegends/images/c/cc/Barrier.png/revision/latest?cb=20180514002510"
    const val SPELL_EXHAUST = "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Exhaust.png/revision/latest?cb=20180514003128"
    const val SPELL_GHOST = "https://static.wikia.nocookie.net/leagueoflegends/images/a/ab/Ghost.png/revision/latest?cb=20180514003209"
    const val SPELL_HEAL = "https://static.wikia.nocookie.net/leagueoflegends/images/6/6e/Heal.png/revision/latest?cb=20180514003319"
    const val SPELL_CLARITY = "https://static.wikia.nocookie.net/leagueoflegends/images/7/71/Claridad.png/revision/latest?cb=20141013024826&path-prefix=es"
    const val SPELL_MARK = "https://static.wikia.nocookie.net/leagueoflegends/images/5/55/Marca.png/revision/latest?cb=20150802150053&path-prefix=es"
    const val SPELL_TELEPORT = "https://i.postimg.cc/13w5fnWb/1611110740-teleport-enchant.png"

    fun getRuneDrawableRes(nameOrId: String): Int? {
        val clean = nameOrId.trim().lowercase()
        return null
        return when {
            clean.contains("electrocut") -> com.example.R.drawable.ic_wr_rune_electrocute
            clean.contains("cosecha") || clean.contains("harvest") -> com.example.R.drawable.ic_wr_rune_dark_harvest
            clean.contains("fortalecimiento") || clean.contains("empower") || clean.contains("press the attack") || clean.contains("krakens") -> com.example.R.drawable.ic_wr_rune_empowerment
            clean.contains("compás") || clean.contains("compas") || clean.contains("lethal tempo") || clean.contains("cadencia") -> com.example.R.drawable.ic_wr_rune_lethal_tempo
            clean.contains("pies veloces") || clean.contains("fleet") || clean.contains("marcha") -> com.example.R.drawable.ic_wr_rune_fleet_footwork
            clean.contains("conquistador") || clean.contains("conqueror") -> com.example.R.drawable.ic_wr_rune_conqueror
            clean.contains("garras") || clean.contains("inmortal") || clean.contains("grasp") -> com.example.R.drawable.ic_wr_rune_grasp
            clean.contains("guardián") || clean.contains("guardian") -> com.example.R.drawable.ic_wr_rune_guardian
            clean.contains("aery") -> com.example.R.drawable.ic_wr_rune_aery
            clean.contains("cometa") || clean.contains("comet") -> com.example.R.drawable.ic_wr_rune_arcane_comet
            clean.contains("fase") || clean.contains("phase rush") || clean.contains("irrupción") || clean.contains("irrupcion") -> com.example.R.drawable.ic_wr_rune_phase_rush
            clean.contains("primer golpe") || clean.contains("first strike") -> com.example.R.drawable.ic_wr_rune_first_strike
            clean.contains("soberano") || clean.contains("gélido") || clean.contains("gelido") || clean.contains("glacial") -> com.example.R.drawable.ic_wr_rune_glacial_augment
            else -> null
        }
    }

    fun getSpellIconByName(name: String): String {
        return when (name.trim().lowercase()) {
            "destello", "flash" -> SPELL_FLASH
            "prender", "ignición", "ignite", "ignicion", "incendiar" -> SPELL_IGNITE
            "castigo", "smite" -> SPELL_SMITE
            "barrera", "barrier" -> SPELL_BARRIER
            "extenuación", "extenuacion", "exhaust" -> SPELL_EXHAUST
            "fantasma", "ghost" -> SPELL_GHOST
            "curar", "curación", "curacion", "heal" -> SPELL_HEAL
            "claridad", "clarity" -> SPELL_CLARITY
            "marca", "marca / lanzamiento", "mark", "snowball" -> SPELL_MARK
            "teleportación", "teletransporte", "teleport" -> SPELL_TELEPORT
            else -> SPELL_FLASH
        }
    }

    fun getRuneIconByName(name: String): String {
        val clean = name.trim()
        if (clean.isEmpty()) return "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png"
        
        // Direct match
        val exact = runes.find { it.name.equals(clean, ignoreCase = true) }
        if (exact != null) return exact.iconUrl

        // Check canonical aliases
        val canonicalName = when (clean.lowercase()) {
            "cadencia letal", "lethal tempo", "compas letal" -> "Compás Letal"
            "sobre la marcha", "fleet footwork", "pies veloces" -> "Pies Veloces"
            "estrategia ofensiva", "press the attack", "fortalecimiento", "ataque intensificado", "matakrakens", "kraken slayer" -> "Fortalecimiento"
            "invocar a aery", "summon aery", "aery" -> "Aery"
            "agarre del perpetuo", "grasp of the undying", "garras del inmortal", "replica", "réplica", "aftershock" -> "Garras del Inmortal"
            "aumento glacial", "glacial augment", "soberano gelido", "soberano gélido" -> "Soberano Gélido"
            "guardian" -> "Guardián"
            "dark harvest" -> "Cosecha Oscura"
            "electrocute" -> "Electrocutar"
            "phase rush", "irrupcion de fase", "irrupción de fase" -> "Irrupción de Fase"
            "first strike" -> "Primer Golpe"
            "conqueror" -> "Conquistador"
            "arcane comet", "cometa arcano" -> "Cometa Arcano"
            // Brujería / Sorcery aliases
            "arcanólogo axiomático", "arcanologo axiomatico", "arcanólogo", "arcanologo", "axiomatic arcanist" -> "Arcanólogo Axiomático"
            "banda de maná", "banda de mana", "banda de flujo de mana", "banda de flujo de maná", "manaflow band", "flujo de mana" -> "Banda de Maná"
            "botanista", "dulces frutos", "sweet tooth", "sweettooth" -> "Botanista"
            "hextello", "destello hextech", "hextech flashtraption", "hexflash" -> "Hextello"
            "trascendencia", "transcendence" -> "Trascendencia"
            "celeridad", "celerity" -> "Celeridad"
            "concentración absoluta", "concentracion absoluta", "absolute focus" -> "Concentración Absoluta"
            "piroláser", "pirolaser", "quemadura", "scorch" -> "Piroláser"
            "capa del nimbo", "nimbus cloak" -> "Capa del Nimbo"
            "se avecina tormenta", "tormenta creciente", "gathering storm" -> "Se Avecina Tormenta"
            "semillero ixtalí", "semillero ixtali", "ixtali seedjar", "semillero", "ixtali" -> "Semillero Ixtalí"
            else -> null
        }
        if (canonicalName != null) {
            val target = runes.find { it.name.equals(canonicalName, ignoreCase = true) }
            if (target != null) return target.iconUrl
        }

        val partial = runes.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
        return partial?.iconUrl ?: "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png"
    }

    fun getRuneByName(name: String): RuneItem? {
        val clean = name.trim()
        if (clean.isEmpty()) return null
        val exact = runes.find { it.name.equals(clean, ignoreCase = true) }
        if (exact != null) return exact

        val canonicalName = when (clean.lowercase()) {
            "cadencia letal", "lethal tempo", "compas letal" -> "Compás Letal"
            "sobre la marcha", "fleet footwork", "pies veloces" -> "Pies Veloces"
            "estrategia ofensiva", "press the attack", "fortalecimiento", "ataque intensificado", "matakrakens", "kraken slayer" -> "Fortalecimiento"
            "invocar a aery", "summon aery", "aery" -> "Aery"
            "agarre del perpetuo", "grasp of the undying", "garras del inmortal", "replica", "réplica", "aftershock" -> "Garras del Inmortal"
            "aumento glacial", "glacial augment", "soberano gelido", "soberano gélido" -> "Soberano Gélido"
            "guardian" -> "Guardián"
            "dark harvest" -> "Cosecha Oscura"
            "electrocute" -> "Electrocutar"
            "phase rush", "irrupcion de fase", "irrupción de fase" -> "Irrupción de Fase"
            "first strike" -> "Primer Golpe"
            "conqueror" -> "Conquistador"
            "arcane comet", "cometa arcano" -> "Cometa Arcano"
            // Brujería / Sorcery aliases
            "arcanólogo axiomático", "arcanologo axiomatico", "arcanólogo", "arcanologo", "axiomatic arcanist" -> "Arcanólogo Axiomático"
            "banda de maná", "banda de mana", "banda de flujo de mana", "banda de flujo de maná", "manaflow band", "flujo de mana" -> "Banda de Maná"
            "botanista", "dulces frutos", "sweet tooth", "sweettooth" -> "Botanista"
            "hextello", "destello hextech", "hextech flashtraption", "hexflash" -> "Hextello"
            "trascendencia", "transcendence" -> "Trascendencia"
            "celeridad", "celerity" -> "Celeridad"
            "concentración absoluta", "concentracion absoluta", "absolute focus" -> "Concentración Absoluta"
            "piroláser", "pirolaser", "quemadura", "scorch" -> "Piroláser"
            "capa del nimbo", "nimbus cloak" -> "Capa del Nimbo"
            "se avecina tormenta", "tormenta creciente", "gathering storm" -> "Se Avecina Tormenta"
            "semillero ixtalí", "semillero ixtali", "ixtali seedjar", "semillero", "ixtali" -> "Semillero Ixtalí"
            else -> null
        }
        if (canonicalName != null) {
            return runes.find { it.name.equals(canonicalName, ignoreCase = true) }
        }

        return runes.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
    }

    val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Destello",
            cooldown = "150s",
            iconUrl = SPELL_FLASH,
            description = "Teletransporta a tu campeón una corta distancia hacia la ubicación objetivo. El hechizo universal imprescindible en Wild Rift para esquivar habilidades, reposicionarse o realizar jugadas ofensivas sorpresa."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender",
            cooldown = "100s",
            iconUrl = SPELL_IGNITE,
            description = "Prende fuego a un campeón enemigo infligiendo 72-420 de daño verdadero durante 5s y aplicando Heridas Graves (60%) que reducen drásticamente todas las curaciones y regeneraciones."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Castigo",
            cooldown = "45s",
            iconUrl = SPELL_SMITE,
            description = "Inflige daño verdadero masivo a monstruos de la jungla y súbditos. Evoluciona a Castigo Desafiante o Helador tras asegurar 4 campamentos grandes, ralentizando o reduciendo el daño del campeón rival."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera",
            cooldown = "110s",
            iconUrl = SPELL_BARRIER,
            description = "Otorga un escudo temporal de 115-465 de absorción de daño durante 2 segundos. Ideal para tiradores (ADC) y magos de carril central para sobrevivir a ráfagas de daño de asesinos."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación",
            cooldown = "105s",
            iconUrl = SPELL_EXHAUST,
            description = "Ralentiza a un campeón enemigo un 60% y reduce su daño infligido un 40% durante 2.5s. Esencial para soportes y carrileros contra asesinos, duelistas e hipercarries enemigos."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasma",
            cooldown = "90s",
            iconUrl = SPELL_GHOST,
            description = "Otorga una aceleración masiva de velocidad de movimiento (hasta +45%) e inmunidad a colisiones de unidades durante 6s. Cada derribo de campeón reinicia su duración."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar",
            cooldown = "120s",
            iconUrl = SPELL_HEAL,
            description = "Restaura vida inmediatamente a tu campeón y al aliado más cercano con menor salud, otorgando +30% de velocidad de movimiento durante 1s a ambos."
        ),
        SummonerSpellItem(
            id = "teleport",
            name = "Teleportación",
            cooldown = "180s",
            iconUrl = SPELL_TELEPORT,
            description = "Tras canalizar durante 4s, teletransporta a tu campeón hacia una torreta, súbdito o centinela aliado. Permite split-pushing global y presencia instantánea en objetivos neutrales."
        ),
        SummonerSpellItem(
            id = "clarity",
            name = "Claridad",
            cooldown = "90s",
            iconUrl = SPELL_CLARITY,
            description = "Restaura el 50% del maná máximo a tu campeón y el 25% del maná a todos los aliados cercanos en el área de efecto (disponible en modos especiales y ARAM)."
        ),
        SummonerSpellItem(
            id = "mark_dash",
            name = "Marca / Lanzamiento",
            cooldown = "80s",
            iconUrl = SPELL_MARK,
            description = "Lanza una bola de nieve en línea recta; si impacta a un enemigo, inflige daño verdadero y permite reactivar el hechizo para deslizarse instantáneamente hacia él."
        )
    )

    val runes: List<RuneItem> = listOf(
        // =========================================================================
        // 1. RUNAS CLAVE (KEYSTONES - OFICIALES WILD RIFT)
        // =========================================================================
        RuneItem(
            id = "electrocute",
            name = "Electrocutar",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/zXhRSMyc/electrocutar-hanu.png",
            description = "Golpear a un campeón con 3 ataques o habilidades diferentes en 3s inflige daño adaptable adicional. Ideal para asesinos y combos rápidos."
        ),
        RuneItem(
            id = "dark_harvest",
            name = "Cosecha Oscura",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/VvC5grkT/dark-harvest.png",
            description = "Dañar a un campeón con menos del 50% de vida inflige daño adaptable adicional y cosecha su alma, aumentando permanentemente el daño de esta runa."
        ),
        RuneItem(
            id = "empowerment",
            name = "Fortalecimiento",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729601_8005.webp",
            description = "Impactar a un campeón con 3 ataques básicos consecutivos inflige daño adaptable adicional y amplifica tu daño contra campeones."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Compás Letal",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/kGbDs65r/compas-letal-hanu.png",
            description = "Ganas velocidad de ataque acumulable al atacar campeones. Al máximo de acumulaciones, ganas alcance de ataque adicional."
        ),
        RuneItem(
            id = "fleet_footwork",
            name = "Pies Veloces",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/Znd0HBqt/pies-veloces-hanu.png",
            description = "Moverte y atacar genera acumulaciones de Energía. A las 100 acumulaciones, tu próximo ataque cura y otorga velocidad de movimiento."
        ),
        RuneItem(
            id = "conqueror",
            name = "Conquistador",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/HnyjzcL1/conqueror-hanu.png",
            description = "Ganas daño adaptable al golpear a un campeón enemigo con ataques o habilidades. Al máximo de acumulaciones otorga omnivampirismo."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Garras del Inmortal",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/hvdhszGq/desgarrador-hanu.png",
            description = "Cada 3s en combate, tu próximo ataque básico contra un campeón inflige daño mágico adicional, te cura y aumenta tu vida máxima permanentemente."
        ),
        RuneItem(
            id = "guardian",
            name = "Guardián",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774950298_guardian.webp",
            description = "Proteges a aliados dentro de 350 unidades de ti. Si tú o el aliado reciben daño por encima de un umbral, ambos reciben un escudo."
        ),
        RuneItem(
            id = "aery",
            name = "Aery",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/prFyChdH/aery-hanu.png",
            description = "Tus ataques y habilidades envían a Aery a dañar enemigos o dar un escudo a aliados. No puede volver a enviarse hasta que regrese."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/t7GJMqsm/cometa-hanu-bbwr.png",
            description = "Dañar a un campeón con una habilidad lanza un cometa que inflige daño adaptable en su ubicación."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/Hj1nhYrN/324314341234123-hanu-wr-bb.png",
            description = "Usar 3 ataques o habilidades contra un campeón en 4s otorga velocidad de movimiento masiva y resistencia a ralentizaciones."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe",
            category = "Runa Clave",
            iconUrl = "https://i.postimg.cc/hhRv5DXw/8369.png",
            description = "Iniciar el combate contra un campeón enemigo otorga oro adicional y aumenta tu daño verdadero un 7% durante 3s. Otorga oro según el daño."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Soberano Gélido",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774949508_frost-sovereign.webp",
            description = "Inmovilizar a un enemigo crea áreas de hielo que ralentizan a los enemigos dentro del área y aumentan tus defensas."
        ),

        // =========================================================================
        // 2. BRUJERÍA / INSPIRACIÓN (SORCERY) - 11 RUNAS OFICIALES DE WILD RIFT
        // =========================================================================
        RuneItem(
            id = "axiomatic_arcanist",
            name = "Arcanólogo Axiomático",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1761189941_axiom-arcanist.webp",
            description = "Tu habilidad definitiva obtiene un 10% de daño, curación y escudo adicionales (5% para daño en área). Las bajas reducen su enfriamiento un 7%."
        ),
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Maná",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392903_manaflow-band.webp",
            description = "Golpear a un campeón enemigo con una habilidad o ataque potenciado aumenta tu maná máximo en 30, hasta 300."
        ),
        RuneItem(
            id = "botanist",
            name = "Botanista",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952316_botanist.webp",
            description = "Destruir una planta otorga 10 de oro extra y potencia los efectos de los frutos de miel, brotes del vidente y piñas explosivas."
        ),
        RuneItem(
            id = "hextech_flashtraption",
            name = "Hextello",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392779_hextech-flashtraption.webp",
            description = "Mientras Destello está en enfriamiento, es reemplazado por Hextello. Permite canalizar para un pequeño salto."
        ),
        RuneItem(
            id = "transcendence",
            name = "Trascendencia",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392866_transcendence.webp",
            description = "Al nivel 1 obtienes +5 aceleración de habilidad. Al nivel 5, +5 adicional. Al nivel 9, los impactos de habilidades reducen su propio enfriamiento."
        ),
        RuneItem(
            id = "celerity",
            name = "Celeridad",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1761189545_celerity.webp",
            description = "Otorga +2% de velocidad de movimiento base y aumenta las bonificaciones de velocidad un 7%."
        ),
        RuneItem(
            id = "absolute_focus",
            name = "Concentración Absoluta",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952466_absolute-focus.webp",
            description = "Mientras tengas más del 65% de vida, obtienes daño de ataque o poder de habilidad adicional adaptable."
        ),
        RuneItem(
            id = "scorch",
            name = "Piroláser",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952748_scorch.webp",
            description = "Tus habilidades queman a los enemigos, infligiendo daño mágico adicional al cabo de 1 segundo (8s enfriamiento)."
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392930_nimbus-cloak.webp",
            description = "Tras usar un hechizo de invocador, ganas entre un 10% y 40% de velocidad de movimiento por 3 segundos."
        ),
        RuneItem(
            id = "gathering_storm",
            name = "Se Avecina Tormenta",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952894_gathering-storm.webp",
            description = "A partir del minuto 6, obtienes daño adaptable progresivo a lo largo de la partida."
        ),
        RuneItem(
            id = "ixtali_seedjar",
            name = "Semillero Ixtalí",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392953_ixtali-seedjar.webp",
            description = "Destruir una planta te otorga una semilla para plantar otra planta artificial después (reemplaza tu baratija temporalmente)."
        ),

        // =========================================================================
        // 3. DOMINACIÓN (DOMINATION)
        // =========================================================================
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/zXh6HwFd/sudden-impact.webp",
            description = "Inflige daño verdadero adicional tras usar un deslizamiento, salto, teleportación o al salir de sigilo.\n\n💡 Consejo Coach: Aprovecha el pico de daño de esta runa para intercambios cortos inmediatamente después de usar tu habilidad de movilidad."
        ),
        RuneItem(
            id = "cheap_shot",
            name = "Golpe Bajo",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/pXj45QJZ/cheap-shot.webp",
            description = "Infliges daño verdadero adicional a campeones enemigos que tengan su movimiento impedido.\n\n💡 Consejo Coach: Sinergiza perfectamente con campeones que tienen ralentizaciones o inmovilizaciones fiables para asegurar daño verdadero gratis en fase de líneas."
        ),
        RuneItem(
            id = "empowered_attack",
            name = "Ataque Potenciado",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/WbZCqmwX/empowered-attack.webp",
            description = "Cada 10 segundos, tu siguiente ataque inflige daño adaptable adicional.\n\n💡 Consejo Coach: Excelente para el pokeo constante en la línea. Sincroniza tus agresiones cada vez que esta runa esté disponible para maximizar la presión."
        ),
        RuneItem(
            id = "eyeball_collection",
            name = "Colección de Ojos",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/X75mBKk1/eyeball-collector.webp",
            description = "Los derribos de campeones y monstruos épicos otorgan daño adaptable adicional, acumulable hasta 8 veces.\n\n💡 Consejo Coach: Prioriza las peleas tempranas y rotaciones conjuntas para alcanzar tu pico de estadísticas adicionales lo antes posible."
        ),
        RuneItem(
            id = "hubris",
            name = "Arrogancia",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/5NzTQ5S7/hubris.webp",
            description = "Tras conseguir un derribo de campeón, obtienes daño adaptable adicional durante unos segundos.\n\n💡 Consejo Coach: Perfecta para asesinos en peleas de equipo; busca asegurar el primer derribo rápido para encadenar tu daño contra los siguientes objetivos."
        ),
        RuneItem(
            id = "ingenious_hunter",
            name = "Cazador Ingenioso",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/02SFKG0H/ingenious-hunter.webp",
            description = "Otorgar derribos a campeones y monstruos épicos aumenta tu aceleración de objetos.\n\n💡 Consejo Coach: Imprescindible si dependes de objetos activos potentes (como Estasis o Protocinturón), te permitirá usarlos con mucha más frecuencia."
        ),
        RuneItem(
            id = "relentless_hunter",
            name = "Cazador Implacable",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/PrD9vbQR/relentless-hunter.webp",
            description = "Los derribos únicos de campeones otorgan velocidad de movimiento fuera de combate.\n\n💡 Consejo Coach: Ideal para junglas y soportes de rotación (roaming), usa la velocidad para generar presión global constante."
        ),
        RuneItem(
            id = "tyrant",
            name = "Tirano",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/J4XvH35K/tyrant.webp",
            description = "Infligir daño a campeones con menos de 50% de vida máxima otorga daño adaptable adicional.\n\n💡 Consejo Coach: Runa letal para ejecutar objetivos. Úsala si tu campeón destaca finalizando combates a corta y media distancia."
        ),
        RuneItem(
            id = "chain_assault",
            name = "Asalto en Cadena",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/wTNZR5Qw/chain-assault.webp",
            description = "Después de golpear con una habilidad, tu siguiente ataque básico inflige daño adaptable extra.\n\n💡 Consejo Coach: Excelente opción para luchadores o asesinos que intercalan ataques básicos entre sus habilidades para maximizar el DPS continuo."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Centinela Zombi",
            category = "Dominación",
            iconUrl = "https://i.postimg.cc/28WMLd7t/zombie-ward.webp",
            description = "Derribar centinelas enemigos otorga daño adaptable adicional y genera un centinela zombi.\n\n💡 Consejo Coach: Aumenta exponencialmente el control de visión. Ideal para junglas o soportes que abusan de Lentes del Oráculo."
        ),
        // =========================================================================
        // 4. PRECISIÓN (PRECISION)
        // =========================================================================
        RuneItem(
            id = "triumph",
            name = "Triunfo",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392053_triumph.webp",
            description = "Las bajas de campeones restauran 10% de tu vida y maná perdidos, y otorgan velocidad de movimiento."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392209_coup-de-grace.webp",
            description = "Tus ataques infligen un 8% de daño adaptable adicional a campeones con menos del 40% de vida."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392114_last-stand.webp",
            description = "Infliges entre 5% y 11% de daño adicional a campeones enemigos mientras tengas menos del 60% de vida. El daño extra es máximo al 30% de vida."
        ),
        RuneItem(
            id = "giant_slayer",
            name = "Cazagigantes",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/cutdown/cutdown.png",
            description = "Inflige hasta un 14% de daño físico y mágico adicional contra campeones enemigos que tengan mayor vida máxima adicional que tú."
        ),
        RuneItem(
            id = "legend_alacrity",
            name = "Leyenda: Celeridad",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392174_legend-alacrity.webp",
            description = "Otorgar derribos aumenta tu velocidad de ataque adicional, acumulable permanentemente."
        ),
        RuneItem(
            id = "legend_bloodline",
            name = "Leyenda: Linaje",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392254_legend-bloodline.webp",
            description = "Otorgar derribos a monstruos épicos y campeones aumenta tu omnivampirismo de manera permanente."
        ),
        RuneItem(
            id = "legend_tenacity",
            name = "Leyenda: Tenacidad",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392264_legend-tenacity.webp",
            description = "Ganas tenacidad y resistencia a ralentizaciones, con más acumulaciones por derribos."
        ),
        RuneItem(
            id = "brutal",
            name = "Brutalidad / Brutal",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392016_brutal.webp",
            description = "Tus ataques infligen daño mágico extra al impactar a campeones enemigos."
        ),

        // =========================================================================
        // 5. VALOR (RESOLVE)
        // =========================================================================
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392660_bone-plating.webp",
            description = "Al recibir daño de un campeón, las próximas habilidades o ataques entrantes infligen menos daño durante 1.5s."
        ),
        RuneItem(
            id = "second_wind",
            name = "Segundo Aire",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392641_second-wind.webp",
            description = "Otorga +5 vida cada 5s. Tras recibir daño de campeón, regeneras vida extra según la que te falte."
        ),
        RuneItem(
            id = "conditioning",
            name = "Condicionamiento",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/conditioning/conditioning.png",
            description = "A partir del minuto 3:00 de partida, otorga +8 de armadura y +8 de resistencia mágica adicionales y aumenta tus resistencias totales un 5% permanentemente."
        ),
        RuneItem(
            id = "overgrowth",
            name = "Sobrecrecimiento",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392435_overgrowth.webp",
            description = "Por cada 3 súbditos o monstruos asesinados cerca, ganas vida máxima permanente."
        ),
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392605_font-of-life.webp",
            description = "Tus ataques y habilidades curan a tu campeón y al aliado cercano más herido."
        ),
        RuneItem(
            id = "demolish",
            name = "Demolición",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774951923_demolish.webp",
            description = "Estar cerca de una torreta carga un golpe que inflige daño físico masivo a la estructura."
        ),
        RuneItem(
            id = "perseverance",
            name = "Perseverancia",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392452_perseverance.webp",
            description = "Ganas tenacidad base. Al ser inmovilizado ganas armadura y resistencia mágica temporales."
        ),
        RuneItem(
            id = "revitalize",
            name = "Revitalizar",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392494_revitalize.webp",
            description = "Tus curaciones y escudos son un 5% más fuertes (o 15% si el objetivo tiene menos del 40% de vida)."
        ),
        RuneItem(
            id = "loyalty",
            name = "Lealtad",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/guardian/guardian.png",
            description = "Ganas +2 de armadura y +5 de resistencia mágica. Tu aliado más cercano obtiene +5 de armadura y +2 de resistencia mágica adicionales."
        ),

        // =========================================================================
            )
}