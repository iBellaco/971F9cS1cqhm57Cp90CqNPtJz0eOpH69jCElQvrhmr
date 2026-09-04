package com.example.data

import com.example.model.RuneItem
import com.example.model.SummonerSpellItem

object WildRiftSpellsAndRunes {
    const val SPELL_FLASH = "file:///android_asset/offline_images/18d76484f095bb4fb9cd0cb2c3cf91c1.webp"
    const val SPELL_IGNITE = "file:///android_asset/offline_images/31e5dadff852efa11c7844c715c651f9.webp"
    const val SPELL_SMITE = "file:///android_asset/offline_images/37a842cbda8c1b4ba5eb5d21e402fac0.webp"
    const val SPELL_BARRIER = "file:///android_asset/offline_images/261392fb942c1e6e57e20c214b5730e3.webp"
    const val SPELL_EXHAUST = "file:///android_asset/offline_images/f96ebaac38178523dd89c37e26b36239.webp"
    const val SPELL_GHOST = "file:///android_asset/offline_images/6518ad9d35d6d046ed5f3b5a64bd2cdf.webp"
    const val SPELL_HEAL = "file:///android_asset/offline_images/eb2189d40d0876ae93b3953891311771.webp"
    const val SPELL_CLARITY = "file:///android_asset/offline_images/a31114e82043b9de82cc31001f932bbc.webp"
    const val SPELL_MARK = "file:///android_asset/offline_images/7ad34ef88977ab7359efc91e0cca76b8.webp"
    const val SPELL_TELEPORT = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/39fa1f0877518b03faf11db312af01da.png"
    const val SPELL_CLEANSE = "file:///android_asset/offline_images/130e573636a2a81e58b33430127c6c70.webp"
    const val SPELL_CHILLING_SMITE = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/fe9b881d78a97d694597dbb62133332a.png"

    fun getRuneDrawableRes(nameOrId: String): Int? {
        return null
    }

    fun getSpellIconByName(name: String): String {
        val clean = name.trim().lowercase()
        val found = summonerSpells.find {
            it.name.equals(clean, ignoreCase = true) ||
            it.id.equals(clean, ignoreCase = true) ||
            it.name.lowercase().contains(clean) ||
            clean.contains(it.name.lowercase())
        }
        if (found != null && found.iconUrl.isNotBlank()) return found.iconUrl

        return when {
            clean.contains("flash") || clean.contains("destello") -> SPELL_FLASH
            clean.contains("ignit") || clean.contains("prend") || clean.contains("incendi") -> SPELL_IGNITE
            clean.contains("smite") || clean.contains("castigo") || clean.contains("aplast") -> SPELL_SMITE
            clean.contains("barri") || clean.contains("barrer") -> SPELL_BARRIER
            clean.contains("exhaus") || clean.contains("extenu") -> SPELL_EXHAUST
            clean.contains("ghost") || clean.contains("fantasm") -> SPELL_GHOST
            clean.contains("heal") || clean.contains("cura") -> SPELL_HEAL
            clean.contains("cleanse") || clean.contains("limpi") -> SPELL_CLEANSE
            clean.contains("teleport") || clean.contains("teletrans") -> SPELL_TELEPORT
            clean.contains("clarity") || clean.contains("claridad") -> SPELL_CLARITY
            clean.contains("mark") || clean.contains("marca") || clean.contains("bola") -> SPELL_MARK
            else -> SPELL_FLASH
        }
    }

    fun getRuneIconByName(name: String): String {
        val clean = name.trim()
        if (clean.isEmpty()) return "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/3944de4f91ad3d524cf47ff53dd349aa.png"
        
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
            // Precisión / Precision aliases
            "brutal", "brutalidad" -> "Brutal"
            "triunfo", "triumph" -> "Triunfo"
            "fervor de batalla", "fervor", "battle fervor", "fervor of battle" -> "Fervor de Batalla"
            "último esfuerzo", "ultimo esfuerzo", "last stand" -> "Último Esfuerzo"
            "derribado", "cut down", "cazagigantes", "giant slayer" -> "Derribado"
            "golpe de gracia", "coup de grace" -> "Golpe de Gracia"
            "leyenda: presteza", "leyenda presteza", "presteza", "legend alacrity", "leyenda: celeridad", "leyenda celeridad" -> "Leyenda: Presteza"
            "leyenda: tenacidad", "leyenda tenacidad", "tenacidad", "legend tenacity" -> "Leyenda: Tenacidad"
            "leyenda: linaje", "leyenda linaje", "linaje", "legend bloodline" -> "Leyenda: Linaje"
            // Dominación / Domination aliases
            "golpe bajo", "cheap shot", "cheapshot" -> "Golpe Bajo"
            "impacto repentino", "sudden impact" -> "Impacto Repentino"
            "ataque potenciado", "empowered attack" -> "Ataque Potenciado"
            "asalto encadenado", "asalto en cadena", "chain assault" -> "Asalto Encadenado"
            "tirano", "tyrant" -> "Tirano"
            "soberbia", "arrogancia", "hubris" -> "Soberbia"
            "colección de globos oculares", "coleccion de globos oculares", "colección de ojos", "coleccion de ojos", "eyeball collection", "eyeball collector", "globos oculares" -> "Colección de Globos Oculares"
            "cazador ingenioso", "ingenious hunter" -> "Cazador Ingenioso"
            "cazador incesante", "cazador implacable", "relentless hunter" -> "Cazador Incesante"
            "guardián zombi", "guardian zombi", "centinela zombi", "zombie ward" -> "Guardián Zombi"
            // Extra WR and Community aliases
            "coraza osea", "coraza ósea", "revestimiento de huesos", "bone plating" -> "Revestimiento de Huesos"
            "segundo aire", "second wind", "fuerzas renovadas" -> "Fuerzas Renovadas"
            "orbe de anulacion", "orbe de anulación", "orbe anulador", "nullifying orb" -> "Orbe Anulador"
            "fuerza indomable", "inquebrantable", "unflinching" -> "Inquebrantable"
            "verdugo de gigantes", "derribado", "giant slayer", "cut down" -> "Derribado"
            "demolicion", "demolición", "demoler", "demolish" -> "Demoler"
            "impacto subito", "impacto súbito", "impacto repentino", "sudden impact" -> "Impacto Repentino"
            "coleccion de ojos", "colección de ojos", "coleccion de globos oculares", "colección de globos oculares", "eyeball collection" -> "Colección de Globos Oculares"
            "cazador voraz", "cazador ingenioso", "ingenious hunter" -> "Cazador Ingenioso"
            "claridad mental", "triunfo", "triumph" -> "Triunfo"
            "perspicacia cosmica", "perspicacia cósmica", "trascendencia", "transcendence" -> "Trascendencia"
            "piromancia", "pirolaser", "piroláser", "scorch" -> "Piroláser"
            "mercado del futuro", "se avecina tormenta", "gathering storm" -> "Se Avecina Tormenta"
            "dulces frutos", "botanista", "sweet tooth" -> "Botanista"
            "reverberaccion", "reverberacción", "aftershock", "soberano gelido", "soberano gélido", "glacial augment" -> "Soberano Gélido"
            "coraje del coloso", "valor de coloso", "courage of the colossus" -> "Coraje del Coloso"
            else -> null
        }
        if (canonicalName != null) {
            val target = runes.find { it.name.equals(canonicalName, ignoreCase = true) }
            if (target != null) return target.iconUrl
        }

        val partial = runes.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
        return partial?.iconUrl ?: "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/3944de4f91ad3d524cf47ff53dd349aa.png"
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
            // Precisión / Precision aliases
            "brutal", "brutalidad" -> "Brutal"
            "triunfo", "triumph" -> "Triunfo"
            "fervor de batalla", "fervor", "battle fervor", "fervor of battle" -> "Fervor de Batalla"
            "último esfuerzo", "ultimo esfuerzo", "last stand" -> "Último Esfuerzo"
            "derribado", "cut down", "cazagigantes", "giant slayer" -> "Derribado"
            "golpe de gracia", "coup de grace" -> "Golpe de Gracia"
            "leyenda: presteza", "leyenda presteza", "presteza", "legend alacrity", "leyenda: celeridad", "leyenda celeridad" -> "Leyenda: Presteza"
            "leyenda: tenacidad", "leyenda tenacidad", "tenacidad", "legend tenacity" -> "Leyenda: Tenacidad"
            "leyenda: linaje", "leyenda linaje", "linaje", "legend bloodline" -> "Leyenda: Linaje"
            // Dominación / Domination aliases
            "golpe bajo", "cheap shot", "cheapshot" -> "Golpe Bajo"
            "impacto repentino", "sudden impact" -> "Impacto Repentino"
            "ataque potenciado", "empowered attack" -> "Ataque Potenciado"
            "asalto encadenado", "asalto en cadena", "chain assault" -> "Asalto Encadenado"
            "tirano", "tyrant" -> "Tirano"
            "soberbia", "arrogancia", "hubris" -> "Soberbia"
            "colección de globos oculares", "coleccion de globos oculares", "colección de ojos", "coleccion de ojos", "eyeball collection", "eyeball collector", "globos oculares" -> "Colección de Globos Oculares"
            "cazador ingenioso", "ingenious hunter" -> "Cazador Ingenioso"
            "cazador incesante", "cazador implacable", "relentless hunter" -> "Cazador Incesante"
            "guardián zombi", "guardian zombi", "centinela zombi", "zombie ward" -> "Guardián Zombi"
            // Extra WR and Community aliases
            "coraza osea", "coraza ósea", "revestimiento de huesos", "bone plating" -> "Revestimiento de Huesos"
            "segundo aire", "second wind", "fuerzas renovadas" -> "Fuerzas Renovadas"
            "orbe de anulacion", "orbe de anulación", "orbe anulador", "nullifying orb" -> "Orbe Anulador"
            "fuerza indomable", "inquebrantable", "unflinching" -> "Inquebrantable"
            "verdugo de gigantes", "derribado", "giant slayer", "cut down" -> "Derribado"
            "demolicion", "demolición", "demoler", "demolish" -> "Demoler"
            "impacto subito", "impacto súbito", "impacto repentino", "sudden impact" -> "Impacto Repentino"
            "coleccion de ojos", "colección de ojos", "coleccion de globos oculares", "colección de globos oculares", "eyeball collection" -> "Colección de Globos Oculares"
            "cazador voraz", "cazador ingenioso", "ingenious hunter" -> "Cazador Ingenioso"
            "claridad mental", "triunfo", "triumph" -> "Triunfo"
            "perspicacia cosmica", "perspicacia cósmica", "trascendencia", "transcendence" -> "Trascendencia"
            "piromancia", "pirolaser", "piroláser", "scorch" -> "Piroláser"
            "mercado del futuro", "se avecina tormenta", "gathering storm" -> "Se Avecina Tormenta"
            "dulces frutos", "botanista", "sweet tooth" -> "Botanista"
            "reverberaccion", "reverberacción", "aftershock", "soberano gelido", "soberano gélido", "glacial augment" -> "Soberano Gélido"
            "coraje del coloso", "valor de coloso", "courage of the colossus" -> "Coraje del Coloso"
            else -> null
        }
        if (canonicalName != null) {
            return runes.find { it.name.equals(canonicalName, ignoreCase = true) }
        }

        return runes.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
    }

    fun getSpellByName(name: String): SummonerSpellItem? {
        val clean = name.trim()
        if (clean.isEmpty()) return null
        val exact = summonerSpells.find { it.name.equals(clean, ignoreCase = true) || it.id.equals(clean, ignoreCase = true) }
        if (exact != null) return exact

        val canonicalId = when (clean.lowercase()) {
            "destello", "flash" -> "flash"
            "prender", "ignición", "ignite", "ignicion", "incendiar" -> "ignite"
            "castigo", "smite", "smite desafiante", "smite helado" -> "smite"
            "barrera", "barrier" -> "barrier"
            "extenuación", "extenuacion", "exhaust" -> "exhaust"
            "fantasmal", "fantasma", "ghost" -> "ghost"
            "curar", "curación", "curacion", "heal" -> "heal"
            "claridad", "clarity" -> "clarity"
            "marca", "marca / lanzamiento", "mark", "snowball" -> "mark"
            "teleportación", "teletransporte", "teleport" -> "teleport"
            "limpiar", "cleanse" -> "cleanse"
            else -> null
        }
        if (canonicalId != null) {
            return summonerSpells.find { it.id.equals(canonicalId, ignoreCase = true) }
        }

        return summonerSpells.find { clean.contains(it.name, ignoreCase = true) || it.name.contains(clean, ignoreCase = true) }
    }

    val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "ghost",
            name = "Fantasmal",
            cooldown = "90s",
            iconUrl = "file:///android_asset/offline_images/b6ab383b3a6e6dd5260aeceec8f32116.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nObtiene una gran mejora de velocidad de movimiento que decrece hasta un 25% de velocidad de movimiento adicional durante 8 s. La duración de Fantasmal aumenta en 6 s con cada asesinato o asistencia, lo que reinicia su efecto hasta la cifra inicial.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Curar",
            cooldown = "100s",
            iconUrl = "file:///android_asset/offline_images/f358ee8ae30792b90102f147ca8ff505.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nRestaura 110 de vida (de 110 a 400) y te otorga un 30% de velocidad de movimiento adicional durante 2 s a ti y al campeón aliado cercano más herido.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrera",
            cooldown = "100s",
            iconUrl = "file:///android_asset/offline_images/2bb7dec2b0fc0af6e5406f255a6a5e4c.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nOtorga un escudo que absorbe 120 de daño (120 - 560) durante 2,5 s.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Extenuación",
            cooldown = "100s",
            iconUrl = "file:///android_asset/offline_images/5b09c59859ca6e7474a523af7bf14be2.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nExtenúa al campeón enemigo objetivo, lo ralentiza un 35% y reduce su daño un 40% durante 2,5 s. La ralentización decrece mientras dura el efecto.",
            category = "Combate & Daño"
        ),
        SummonerSpellItem(
            id = "cleanse",
            name = "Limpiar",
            cooldown = "110s",
            iconUrl = "file:///android_asset/offline_images/9113cf18714bd733dde0bc537ef01213.webp",
            description = "Mapas aplicables: Wild Rift y el Abismo de los Lamentos.\n\nElimina las inhabilitaciones (incluidas las de los hechizos) que afectan a tu campeón y le otorga inmunidad a todas las inhabilitaciones durante 0,25 s.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "flash",
            name = "Destello",
            cooldown = "150s",
            iconUrl = "file:///android_asset/offline_images/b4f2c9b975912c3a88ceb806e74c23f1.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nTeleporta una breve distancia hacia la dirección en la que apunta.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Prender",
            cooldown = "100s",
            iconUrl = "file:///android_asset/offline_images/2dac8f8cb1e49d63d24c319b8fa9d521.webp",
            description = "Mapas aplicables: Wild Rift, Abismo de los Lamentos\n\nPrende fuego a un campeón enemigo, lo que inflige 72 de daño verdadero (72 - 380) durante 5 s, aplica heridas graves al objetivo y lo revela.",
            category = "Combate & Daño"
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Aplastar",
            cooldown = "10s",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/fe9b881d78a97d694597dbb62133332a.png",
            description = "Mapa aplicable: Wild Rift\n\nInflige 600 de daño verdadero a los monstruos, monstruos épicos o súbditos enemigos. Al utilizar Aplastar contra monstruos, recuperas 127 de vida (70 + 10%). Aplastar se convierte en Aplastamiento desalentador tras usarlo 3 veces.",
            category = "Combate & Daño"
        ),

        SummonerSpellItem(
            id = "clarity",
            name = "Claridad",
            cooldown = "90s",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/4c54501b94b310943f0b72fca94f1b49.jpg",
            description = "Mapas disponibles: Abismo de los Lamentos\n\nRestaura un 50% del maná máximo a tu campeón y un 25% a los aliados cercanos.",
            category = "Movilidad & Utilidad"
        ),
        SummonerSpellItem(
            id = "mark",
            name = "Marca y Deslizamiento",
            cooldown = "40s",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/e49e51b216be409ec13bcad51b4d61f7.jpg",
            description = "Mapa disponible: Abismo de los Lamentos\n\nLanza una bola de nieve que inflige 16 (16-100) de daño, marca a un enemigo y lo revela durante 5 s.\nPuede activarse de nuevo para deslizarse hasta el enemigo marcado, lo que inflige 16 (16-100) de daño al impactar.",
            category = "Movilidad & Utilidad"
        )
    )

    val runes: List<RuneItem> = listOf(
        // =========================================================================
        // 1. RUNAS CLAVE (KEYSTONES - OFICIALES WILD RIFT)
        // =========================================================================
        RuneItem(
            id = "dark_harvest",
            name = "Cosecha Oscura",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/b3a9fc6d66f55b87b250bc59a9b0c49d.png",
            description = "Daño adicional, amplificación de acumulaciones\n\nAl infligir daño a un campeón que tenga menos del 50% de vida, le infliges daño adaptable y cosechas su alma, lo que aumenta permanentemente el daño de Cosecha oscura en 11.\nDaño de Cosecha oscura: 35 + 11 por alma + 10% adicional DA + 5% PH.\n(20 s de enfriamiento. Se reinicia a 1 s con asesinatos o asistencias)."
        ),
        RuneItem(
            id = "electrocute",
            name = "Electrocutar",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/5e4d791ae65387ea1a0cba0ea2a0a5a9.png",
            description = "Daño explosivo\n\nEn 3 s, golpea a un mismo campeón enemigo con 3 ataques básicos o habilidades para infligirle daño adaptable adicional.\nDaño: 40-210 (nivel) + 10% adicional DA + 5% PH.\nEnfriamiento: 20-13 s (nivel)."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Compás Letal",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/042f6d5ca0aedd78272cd70145db6eb2.png",
            description = "Velocidad de ataque\n\nObtienes velocidad de ataque acumulable al atacar a campeones enemigos. Se acumula hasta 6 veces. Con el máximo de acumulaciones, obtienes alcance adicional y puedes superar el límite de velocidad de ataque.\nCada acumulación aumenta la velocidad de ataque un 6-14% (cuerpo a cuerpo) o un 3,5-8% (a distancia) durante 6 s.\nCon el máximo de acumulaciones, obtienes 25 (cuerpo a cuerpo) o 50 (a distancia) de alcance."
        ),
        RuneItem(
            id = "fleet_footwork",
            name = "Pies Veloces",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/fb187933f286d2b04c11a497370f56e4.png",
            description = "Movilidad, curación\n\nMoverse, atacar y utilizar habilidades generan acumulaciones de energía. Con 100 acumulaciones, tu siguiente ataque obtiene velocidad de ataque, te cura y te otorga velocidad de movimiento adicional. Si el ataque tiene como objetivo a un campeón, también restaura maná o energía.\nVelocidad de ataque adicional: 40%.\nVida restaurada: 15-110 (nivel) + 15% adicional DA + 10% PH.\nVelocidad de movimiento adicional: 20% durante 1 s.\nAl atacar a un campeón, restaura un 8% del maná que falte o un 8% de la energía que falte.\nAl atacar a súbditos o monstruos, restaura un 35% (campeones cuerpo a cuerpo) o un 15% (campeones a distancia) de la curación original."
        ),
        RuneItem(
            id = "conqueror",
            name = "Conquistador",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/3944de4f91ad3d524cf47ff53dd349aa.png",
            description = "Acumula daño y succión\n\nGolpear a un campeón con ataques o habilidades diferentes otorga acumulaciones de fuerza adaptable. Se acumula hasta 6 veces. Con el máximo de acumulaciones, obtienes omnisucción adicional.\nPor acumulación: 3-5 de daño de ataque o 4-8 de poder de habilidad adicionales durante 6 s.\nMejora al máximo de acumulaciones: 9% (cuerpo a cuerpo) o un 5% (a distancia) de omnisucción adicional."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Garras del Inmortal",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/1cb23295763af361f03056fb014867bc.png",
            description = "Tanque, curación\n\nCada 3 s que pases en combate, se potenciará tu siguiente ataque contra un campeón..\nDaño mágico adicional: 3,3% vida máx.\nCuración: 1,3% vida máx.\nAumento de vida permanente: 10\nCon campeones a distancia, los efectos se reducen un 60%."
        ),
        RuneItem(
            id = "guardian",
            name = "Guardián",
            category = "Clave",
            iconUrl = "file:///android_asset/offline_images/41858b307a1c3e70c29ac7ac3be80eec.webp",
            description = "Protección, escudo\n\nProtege a los aliados que se encuentren a 350 unidades de ti y a los aliados que sean objetivos de tus habilidades durante 2,5 s. Si a lo largo de su duración tu aliado o tú recibís más que una pequeña cantidad de daño, ambos obtenéis un escudo durante 1,5 s.\nEnfriamiento: 55-25 s (nivel).\nEscudo: 40-165 (nivel) + 6% adicional vida + 15% PH.\nUmbral de daño: 70-240 de daño recibido (nivel)."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/ff41a241d32a8ee1afedb8f2de38f559.png",
            description = "Iniciación, amplificación de daño, oro adicional\n\nIniciar un combate contra un campeón enemigo o infligirle daño durante los 0,25 s después de entrar en combate contra él te otorga 10 de oro y el efecto de Primer golpe durante 3 s, lo que te permite infligirle un 7% de daño verdadero adicional. Cuando el efecto desaparece, obtienes oro según el daño adicional infligido durante la duración del mismo.\nSi no infliges daño al campeón enemigo durante los 0,25 s después de entrar en combate contra él, Primer golpe entrará en enfriamiento durante 10 s.\nOro adicional:\nCuerpo a cuerpo: 60% de daño adicional.\nA distancia: 45% de daño adicional.\nEnfriamiento: 20-13 s (nivel)."
        ),
        RuneItem(
            id = "aery",
            name = "Aery",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/b633495bad5956cc2f7ef333cac09e3d.png",
            description = "Desgaste, protección\n\nTus ataques y habilidades envían a Aery a un objetivo para dañar a los enemigos u otorgar un escudo a los aliados.\nDaño: 15 - 70 (nivel) + 10% adicional DA + 5% PH.\nEscudo: 25 - 120 (nivel) + 10% adicional DA + 5% PH.\nNo se puede enviar a Aery de nuevo hasta que vuelva a ti."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/049130b38a1b100a522a8a906d9ec4a0.png",
            description = "Hostigar desde lejos, amplificación de acumulaciones\n\nInfligir daño con una habilidad a un campeón proyecta un cometa hacia su ubicación. Cuando un cometa golpea a un campeón enemigo, aumenta el daño del siguiente.\nDaño: (15 a 100) + (2 × golpes totales a campeones enemigos) + 10% adicional DA + 5% PH.\nEnfriamiento: 16-8 s (nivel)."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase",
            category = "Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/5cf88690cd560b65e17f1de220c8dd14.png",
            description = "Movilidad, velocidad de habilidades\n\nGolpear a un campeón enemigo con ataques básicos o habilidades 3 veces en 4 s otorga velocidad de movimiento y velocidad de habilidades básicas, y reduce el enfriamiento restante de las habilidades básicas en un 20%.\nDuración: 3 s.\nVelocidad de movimiento: 40%-60% (nivel) para los campeones cuerpo a cuerpo; 20%-35% (nivel) para los campeones a distancia.\nVelocidad de habilidades básicas: 10.\nEnfriamiento: 21-7 s (nivel)."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Soberano Gélido",
            category = "Clave",
            iconUrl = "file:///android_asset/offline_images/05bd9beefbd519684856c7fa7f539924.webp",
            description = "Control, ralentización\n\nAl inmovilizar a un campeón enemigo, se forman 3 rayos a su alrededor y hielo bajo sus pies durante 3 s, lo que ralentiza a los enemigos que estén en contacto con el hielo. La ralentización se sigue aplicando a los enemigos durante 1,5 s tras abandonar el área helada. Obtienes una capa de hielo protector que te rodea y aumenta tus defensas. Tras un breve lapso de tiempo, el hielo explota, lo que inflige daño mágico a tu alrededor.\nRalentización: (1% de tu vida adicional + 15)%.\nDefensas: 35 + 75% de armadura y resistencia mágica adicionales. Dura 2,5 s.\nDaño mágico: 15–100 (nivel) + 5% vida adicional.\nEnfriamiento: 20 s."
        ),
        // =========================================================================
        RuneItem(
            id = "cheap_shot",
            name = "Golpe Bajo",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/fb13022fe075bedfa416d07ee9bc20c8.webp",
            description = "Inflige de 10 a 45 de daño verdadero adicional a los enemigos cuya velocidad de movimiento se haya visto reducida (7 s de enfriamiento)."
        ),
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/8c918ab19728042876740ff35fb49a4b.webp",
            description = "Tras un deslizamiento, salto, teleportación instantánea, teleportación o al salir del sigilo, tu siguiente ataque o habilidad contra un enemigo en 4 s inflige de 15 a 65 de daño verdadero adicional (15 s de enfriamiento). Este efecto se potencia al subir de nivel:\nNivel 5: Inflige 5 de daño verdadero adicional.\nNivel 9: Inflige 5 de daño verdadero adicional y otorga un 10% de velocidad de movimiento durante 1,5 s tras infligir daño."
        ),
        RuneItem(
            id = "empowered_attack",
            name = "Ataque Potenciado",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/d4d918cf045f4a9b1064cf18419c6125.webp",
            description = "Cada 8 s, potencia tu siguiente ataque, que infligirá 20-60 de daño adaptable adicional a campeones enemigos. 80 % para campeones a distancia."
        ),
        RuneItem(
            id = "chain_assault",
            name = "Asalto Encadenado",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/c536eee52d5dabb87c53c30b7c07249d.webp",
            description = "Infligir daño a un campeón enemigo con una habilidad activa le aplica una marca, lo que provoca que los 2 próximos ataques o habilidades activas contra dicho campeón inflijan daño adaptable adicional (12–38 + 3% AD adicional + 1,5% AP) (15 s de enfriamiento)."
        ),
        RuneItem(
            id = "tyrant",
            name = "Tirano",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/1859892f42873247894ef586b1332183.webp",
            description = "Al infligir daño a un campeón con menos de un 50% de vida, infliges (20-70 + 6% AD adicional + 3% AP) de daño adaptable adicional (10 s de enfriamiento)."
        ),
        RuneItem(
            id = "hubris",
            name = "Soberbia",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/e40b7722a2c0c5f0e2d729951e3ebf5f.webp",
            description = "Las eliminaciones de campeones enemigos en 3 s tras infligirles daño otorgan (5 + 1 por eliminación de campeón conseguida) de fuerza adaptable durante 30 s."
        ),
        RuneItem(
            id = "eyeball_collection",
            name = "Colección de Globos Oculares",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/3ea38c490536ebf2eedbaaa2ef60c103.webp",
            description = "Otorga 1,5 de daño de ataque o 3 de poder de habilidad al participar en eliminaciones de campeones o monstruos épicos, lo que se acumula hasta 8 veces."
        ),
        RuneItem(
            id = "ingenious_hunter",
            name = "Cazador Ingenioso",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/3ebed0a5d707bba5ca21280da9e873d0.webp",
            description = "Otorga 20 de velocidad de objetos. Por cada eliminación de un campeón o monstruo épico que consigas, otorga 5 de velocidad de objetos. Se acumula hasta 5 veces."
        ),
        RuneItem(
            id = "relentless_hunter",
            name = "Cazador Incesante",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/f163b985cb3979cca9ec6f09d19ec0b0.webp",
            description = "Otorga 10 de velocidad de movimiento fuera de combate. Por cada eliminación de un campeón o monstruo épico que consigas, otorga 2 de velocidad de movimiento fuera de combate. Se acumula hasta 5 veces."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Guardián Zombi",
            category = "Dominación",
            iconUrl = "file:///android_asset/offline_images/01a6ffe6a2c8deaa8ff3780182ec47df.webp",
            description = "Las eliminaciones de guardianes enemigos crean un guardián zombi en su lugar, lo que otorga visión del área circundante durante 120 s. Otorga 3 de daño de ataque o 6 de poder de habilidad adicional (máximo: 5 acumulaciones). (Las asistencias de los guardianes enemigos también otorgan acumulaciones y crean guardianes zombi)."
        ),
        // =========================================================================
        // 4. PRECISIÓN (PRECISION) - 9 RUNAS EXACTAS WILD RIFT
        // =========================================================================
        RuneItem(
            id = "brutal",
            name = "Brutal",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/bb68dfc4069e81c091de03cc56bb19ee.webp",
            description = "Los ataques infligen (5 + 6% AD adicional + 3% AP) de daño adaptable adicional a los campeones enemigos."
        ),
        RuneItem(
            id = "triumph",
            name = "Triunfo",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/8440f9f949de559737c3a2a6f98aa3c1.webp",
            description = "Las eliminaciones de campeones restauran un 10% de la vida perdida y un 10% del maná y la energía máximos. Además, otorga 35 de velocidad de movimiento durante 2 s."
        ),
        RuneItem(
            id = "battle_fervor",
            name = "Fervor de Batalla",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/1c3a62530ffbaa03395e53b59dad1b94.webp",
            description = "Obtienes un 1,4% de amplificación de daño de las habilidades básicas acumulable cada 1 s mientras estás en combate con un campeón. Se acumula hasta 3 veces y solo tiene efecto contra campeones enemigos."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/01dcafed66011c9203391c3c9c8d2bd9.webp",
            description = "Cuando tu vida está por debajo del 60%, los ataques asestados a campeones enemigos infligen un 5%–11% de daño adicional. Otorga el daño adicional máximo cuando la vida es inferior al 30%."
        ),
        RuneItem(
            id = "cut_down",
            name = "Derribado",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/18b53462b04c252c3bd31f505b057154.png",
            description = "Tus ataques infligen un 6,5% de daño adicional a campeones enemigos con más del 60% de vida."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/44310a4e9aacb348d33284179a85025b.webp",
            description = "Inflige un 8% de daño adicional a campeones enemigos con menos del 40% de vida."
        ),
        RuneItem(
            id = "legend_alacrity",
            name = "Leyenda: Presteza",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/99c5a83ee8d7240813c025f34445e583.webp",
            description = "Otorga un 3% de velocidad de ataque. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 18% de velocidad de ataque adicional."
        ),
        RuneItem(
            id = "legend_tenacity",
            name = "Leyenda: Tenacidad",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/f37b808b9a307a02c015cd72730d9518.webp",
            description = "Otorga un 3% de tenacidad y un 3% de resistencia a las ralentizaciones. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 15% de tenacidad adicional y un 20% de resistencia a las ralentizaciones."
        ),
        RuneItem(
            id = "legend_bloodline",
            name = "Leyenda: Linaje",
            category = "Precisión",
            iconUrl = "file:///android_asset/offline_images/12edf84107ea6a9ce734f84a924e43f8.webp",
            description = "Otorga un 1% de omnisucción. Asesina a monstruos, súbditos y campeones enemigos o consigue asistencias para obtener hasta un 7% de omnisucción."
        ),

        // =========================================================================
        // 5. VALOR (RESOLVE)
        // =========================================================================
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/a157d23746384e0d7859f9568ec3c20b.webp",
            description = "Cuando tus ataques o habilidades golpean a un campeón enemigo, te curas a ti y al campeón aliado cercano con menos vida.\nAliado: Se cura un 1,5% de tu  máx. + 5% de tu .\nTú: Te curas un 1% de tu  máx. + 5% de tu .\nEnfriamiento: 20 s\nLa curación tiene un 130% de efectividad si eres un campeón cuerpo a cuerpo. (No se activa si tú o algún aliado cercano tenéis la vida al máximo o si no hay aliados cerca)."
        ),
        RuneItem(
            id = "courage_of_the_colossus",
            name = "Coraje del Coloso",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/d2b53952325ddafac69ea2f5f6514d83.webp",
            description = "Otorga un escudo que absorbe de 25 a 45 () + 1% del daño por vida máxima  durante 3 s al inmovilizar a un campeón enemigo (18 s de enfriamiento)."
        ),
        RuneItem(
            id = "nullifying_orb",
            name = "Orbe Anulador",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/725c20b4bb030bf5108ba2b3f2d2394f.webp",
            description = "Si un campeón te inflige daño suficiente para hacerte bajar del 35% de tu vida máxima, obtienes un escudo que absorbe de 60 a 180 () de daño durante 4 s (60 s de enfriamiento)."
        ),
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/cb4d33b3367a706b303ae7869dbf2725.webp",
            description = "Al recibir daño de un campeón, el ataque o habilidad actual y los 3 siguientes ataques o habilidades de campeones que te golpeen en los próximos 1,5 s te infligen 30 - 60 () menos de daño (40 s de enfriamiento)."
        ),
        RuneItem(
            id = "second_wind",
            name = "Fuerzas Renovadas",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/d7461b9b7f3b2a555d4fb77f3953005a.webp",
            description = "Otorga 5 de vida  cada 5 s.\nAl recibir daño de un campeón enemigo, regenera 3 + (un 1,5% de la vida que te falte)  durante 5 s. Este efecto se duplica para los campeones cuerpo a cuerpo."
        ),
        RuneItem(
            id = "unflinching",
            name = "Inquebrantable",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/ab50f592a380057c57ace11ca0544d46.webp",
            description = "Obtienes un 3% de armadura y de resistencia mágica. Por cada 1 campeones enemigos cercanos, otorga un 2% de armadura y resistencia mágica adicionales. Si el máximo de campeones enemigos está cerca (máximo: 3), también otorga un 20% de resistencia a ralentizaciones."
        ),
        RuneItem(
            id = "overgrowth",
            name = "Sobrecrecimiento",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/d0f7769dd346a7f58d346e76fdaaf03c.webp",
            description = "Por cada 3 súbditos enemigos o 3 monstruo(s) asesinados cerca, otorga 3 de vida máxima permanentemente. Así, la vida máxima puede aumentar indefinidamente. Otorga un 3% de vida máxima adicional al conseguir 30 acumulaciones."
        ),
        RuneItem(
            id = "revitalize",
            name = "Revitalizar",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/276861a233241114ae6135ac7273631e.webp",
            description = "Amplifica un 5% las curaciones y escudos. Si la vida del objetivo es inferior a un 40%, este efecto se amplifica un 10% adicional."
        ),
        RuneItem(
            id = "perseverance",
            name = "Perseverancia",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/060151362301203b0889182975813774.webp",
            description = "Otorga 10% de tenacidad. Otorga de 10 a 15 de armadura y resistencia mágica () durante 1,5 s cuando te inmovilizan. La duración se reinicia cuando te inmovilizan múltiples veces."
        ),
        RuneItem(
            id = "demolish",
            name = "Demoler",
            category = "Valor",
            iconUrl = "file:///android_asset/offline_images/9e00e87f247f498d7a679ba230d4f363.webp",
            description = "Al estar a 550 de distancia de una torreta enemiga, obtienes una carga cada 0,5 s, hasta un máximo de 6 veces. Con el máximo de cargas, el siguiente ataque que asestes a la torreta inflige (100 + 22% de la vida máxima ) de daño físico adicional (30 s de enfriamiento)."
        ),

        // =========================================================================
        // 6. BRUJERÍA (SORCERY)
        // =========================================================================
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Maná",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/cf841fc73be1a4604392acd226904127.webp",
            description = "Golpear a un campeón enemigo con una habilidad o ataque potenciado aumenta permanentemente tu maná máximo en 30, hasta 300 de maná."
        ),
        RuneItem(
            id = "axiomatic_arcologist",
            name = "Arcanólogo Axiomático",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/99e73665dee4f7b8f8a1a838bf61d6a2.webp",
            description = "Tu habilidad definitiva obtiene un 10% de daño, curación y escudos adicionales. El aumento del daño en área se reduce un 5%. Participar en el asesinato de un campeón enemigo reduce un 7% el enfriamiento restante de la definitiva."
        ),
        RuneItem(
            id = "transcendence",
            name = "Trascendencia",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/ff1f8d5cc4a4624eb5e019f1c28ec90b.webp",
            description = "Otorga una bonificación al alcanzar los siguientes niveles:\nEn el nivel 1, otorga 5 de velocidad de habilidades.\nEn el nivel 5, otorga 5 de velocidad de habilidades.\nEn el nivel 9, reduce un 8% el enfriamiento de las habilidades básicas cuando golpeen a un objetivo (8 s de enfriamiento)."
        ),
        RuneItem(
            id = "celerity",
            name = "Celeridad",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/72c785311595963dec678b5ae7c75c9d.webp",
            description = "Obtiene un 2% de velocidad de movimiento. Aumentan un 7% todas las bonificaciones de velocidad de movimiento que recibas."
        ),
        RuneItem(
            id = "absolute_focus",
            name = "Concentración Absoluta",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/9b72834211cb389ca9b933ab1c9dd27d.webp",
            description = "Con más del 65% de la vida, obtienes 2-20 de daño de ataque o 4-40 de poder de habilidad adicional (adaptable)."
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/ec24ca54bada55c6a026f382e07d8f45.webp",
            description = "Tras usar un hechizo (Destello, Prender, etc.), obtienes un 10-40% de velocidad de movimiento adicional durante 3 s. La eficacia de esta mejora depende del enfriamiento del hechizo utilizado."
        ),
        RuneItem(
            id = "scorch",
            name = "Piroláser",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/d4ce62df59d7a152ba7e53ef333c6099.webp",
            description = "Infligir daño a un campeón enemigo con una habilidad lo quema y le inflige entre 21 y 49 de daño mágico adicional (según el nivel) tras 1 s (8 s de enfriamiento)."
        ),
        RuneItem(
            id = "gathering_storm",
            name = "Se Avecina Tormenta",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/f98caff8099e6f42e524611a2fc28f31.webp",
            description = "Tras 6 min de partida, otorga 2 de daño de ataque o 4 de poder de habilidad (adaptable), que aumentan cada 3 minutos a 5 o 10, 9 o 18, 14 o 28, etc."
        ),

        RuneItem(
            id = "botanist",
            name = "Botanista",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/6302eb911db90ec1a48990dc9aac3be3.webp",
            description = "Cuando destruyes una planta, obtienes 10 de oro y efectos potenciados de la planta.\nFrutos de miel: Cuando se consumen, aumenta el efecto curativo un 20%.\nFlor del adivino: Cuando se destruye, la visión que otorga dura un 20% más.\nPiña explosiva: Tras el empujón, otorga un 40% de velocidad de movimiento durante 2,5 s."
        ),
        RuneItem(
            id = "hextech_flashtraption",
            name = "Hextello",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/2619a0d436aa05131b59ff1b8e844397.webp",
            description = "Cuando Destello está en enfriamiento, se reemplaza por Hextello. Tras una canalización de hasta 2 s, te trasladas a una ubicación nueva. La distancia varía en función el tiempo de canalización (18 s de enfriamiento).\nPasa a 6 s de enfriamiento al entrar en combate con campeones."
        ),
        RuneItem(
            id = "ixtali_seedjar",
            name = "Semillero Ixtalí",
            category = "Brujería",
            iconUrl = "file:///android_asset/offline_images/e3c001ca761db6981c7d05df2d0ddc16.webp",
            description = "Al destruir una planta, obtienes una semilla al instante que reemplaza tu talismán durante 60 s. La semilla madura y se autodestruye poco después tras plantarla en la ubicación objetivo. (Cuando un aliado destruye una planta, también aparecerán semillas que puedes recoger).\nLas semillas están disponibles a partir del minuto 2 de la partida.\nCada planta tiene un enfriamiento de 30 s.\nLas piñas explosivas que plantes te lanzan más lejos al detonar."
        )
    )
}