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
    const val SPELL_TELEPORT = "https://wr-meta.com/uploads/posts/2025-07/1753389748_teleport-enchant.webp"

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
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/electrocute/electrocute.png",
            description = "Golpear a un campeón con 3 ataques o habilidades individuales en 3 s inflige daño adaptable adicional.\nDaño: 35-189 + 40% adicional DA + 25% PH.\nEnfriamiento: 20 s."
        ),
        RuneItem(
            id = "dark_harvest",
            name = "Cosecha Oscura",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/darkharvest/darkharvest.png",
            description = "Al infligir daño a un campeón que tenga menos del 50% de vida, le infliges daño adaptable y cosechas su alma, lo que aumenta permanentemente el daño de Cosecha oscura en 11.\nDaño de Cosecha oscura: 35 + 11 por alma + 10% adicional DA + 5% PH.\n(20 s de enfriamiento. Se reinicia a 1 s con asesinatos o asistencias)."
        ),
        RuneItem(
            id = "empowerment",
            name = "Fortalecimiento",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Al asestar 3 ataques consecutivos a un campeón enemigo, le inflige daño adaptable adicional y potencia el daño que infliges un 8% hasta que abandonas el combate con campeones.\nDaño adaptable: 40–165. Enfriamiento: 4 s.\nLa amplificación de daño solo surtirá efecto contra campeones."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Compás Letal",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/lethaltempo/lethaltempotemp.png",
            description = "Obtienes velocidad de ataque acumulable al atacar a campeones enemigos. Se acumula hasta 6 veces. Con el máximo de acumulaciones, obtienes alcance adicional y puedes superar el límite de velocidad de ataque.\nCada acumulación aumenta la velocidad de ataque un 6-14% (cuerpo a cuerpo) o un 3,5-8% (a distancia) durante 6 s.\nCon el máximo de acumulaciones, obtienes 25 (cuerpo a cuerpo) o 50 (a distancia) de alcance."
        ),
        RuneItem(
            id = "fleet_footwork",
            name = "Pies Veloces",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/fleetfootwork/fleetfootwork.png",
            description = "Moverse, atacar y utilizar habilidades generan acumulaciones de energía. Con 100 acumulaciones, tu siguiente ataque obtiene velocidad de ataque, te cura y te otorga velocidad de movimiento adicional. Si el ataque tiene como objetivo a un campeón, también restaura maná o energía.\nVelocidad de ataque adicional: 40%.\nVida restaurada: 15-110 + 15% adicional DA + 10% PH.\nVelocidad de movimiento adicional: 20% durante 1 s.\nAl atacar a un campeón, restaura un 8% del maná que falte o un 8% de la energía que falte.\nAl atacar a súbditos o monstruos, restaura un 35% (cuerpo a cuerpo) o un 15% (a distancia) de la curación original."
        ),
        RuneItem(
            id = "conqueror",
            name = "Conquistador",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/conqueror/conqueror.png",
            description = "Golpear a un campeón con ataques o habilidades diferentes otorga acumulaciones de fuerza adaptable. Se acumula hasta 6 veces. Con el máximo de acumulaciones, obtienes omnisucción adicional.\nPor acumulación: 3-5 de daño de ataque o 4-8 de poder de habilidad adicionales durante 6 s.\nMejora al máximo de acumulaciones: 9% (cuerpo a cuerpo) o un 5% (a distancia) de omnisucción adicional."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Garras del Inmortal",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/graspoftheundying/graspoftheundying.png",
            description = "Cada 3 s que pases en combate, se potenciará tu siguiente ataque contra un campeón.\nDaño mágico adicional: 3,3% de vida máxima.\nCuración: 1,3% de vida máxima.\nAumento de vida permanente: 10.\nCon campeones a distancia, los efectos se reducen un 60%."
        ),
        RuneItem(
            id = "guardian",
            name = "Guardián",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/guardian/guardian.png",
            description = "Protege a los aliados que se encuentren a 350 unidades de ti y a los aliados que sean objetivos de tus habilidades durante 2,5 s. Si a lo largo de su duración tu aliado o tú recibís más que una pequeña cantidad de daño, ambos obtenéis un escudo durante 1,5 s.\nEnfriamiento: 55-25 s.\nEscudo: 40-165 + 6% adicional de vida + 15% PH.\nUmbral de daño: 70-240 de daño recibido."
        ),
        RuneItem(
            id = "aery",
            name = "Aery",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/summonaery/summonaery.png",
            description = "Tus ataques y habilidades envían a Aery a un objetivo para dañar a los enemigos u otorgar un escudo a los aliados.\nDaño: 15 - 70 + 10% adicional DA + 5% PH.\nEscudo: 25 - 120 + 10% adicional DA + 5% PH.\nNo se puede enviar a Aery de nuevo hasta que vuelva a ti."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/arcanecomet/arcanecomet.png",
            description = "Infligir daño con una habilidad a un campeón proyecta un cometa hacia su ubicación. Cuando un cometa golpea a un campeón enemigo, aumenta el daño del siguiente.\nDaño: (15 a 100) + (2 × golpes totales a campeones enemigos) + 10% adicional DA + 5% PH.\nEnfriamiento: 16-8 s."
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/phaserush/stormraiderssurgeruneicon2.png",
            description = "Golpear a un campeón enemigo con ataques básicos o habilidades 3 veces en 4 s otorga velocidad de movimiento y velocidad de habilidades básicas, y reduce el enfriamiento restante de las habilidades básicas en un 20%.\nDuración: 3 s.\nVelocidad de movimiento: 40%-60% para los campeones cuerpo a cuerpo; 20%-35% para los campeones a distancia.\nVelocidad de habilidades básicas: 10.\nEnfriamiento: 21-7 s."
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/firststrike/firststrike.png",
            description = "Iniciar un combate contra un campeón enemigo o infligirle daño durante los 0,25 s después de entrar en combate contra él te otorga 10 de oro y el efecto de Primer golpe durante 3 s, lo que te permite infligirle un 7% de daño verdadero adicional. Cuando el efecto desaparece, obtienes oro según el daño adicional infligido durante la duración del mismo.\nSi no infliges daño al campeón enemigo durante los 0,25 s después de entrar en combate contra él, Primer golpe entrará en enfriamiento durante 10 s.\nOro adicional: Cuerpo a cuerpo: 60% de daño adicional. A distancia: 45% de daño adicional.\nEnfriamiento: 20-13 s."
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Soberano Gélido",
            category = "Runa Clave",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/glacialaugment/glacialaugment.png",
            description = "Al inmovilizar a un campeón enemigo, se forman 3 rayos a su alrededor y hielo bajo sus pies durante 3 s, lo que ralentiza a los enemigos que estén en contacto con el hielo. La ralentización se sigue aplicando a los enemigos durante 1,5 s tras abandonar el área helada. Obtienes una capa de hielo protector que te rodea y aumenta tus defensas. Tras un breve lapso de tiempo, el hielo explota, lo que inflige daño mágico a tu alrededor.\nRalentización: (1% de tu vida adicional + 15)%.\nDefensas: 35 + 75% de armadura y resistencia mágica adicionales. Dura 2,5 s.\nDaño mágico: 15–100 + 5% vida adicional.\nEnfriamiento: 20 s."
        ),

        // =========================================================================
        // 2. BRUJERÍA / INSPIRACIÓN (SORCERY) - 11 RUNAS OFICIALES DE WILD RIFT
        // =========================================================================
        RuneItem(
            id = "axiomatic_arcanist",
            name = "Arcanólogo Axiomático",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/nullifyingorb/nullifyingorb.png",
            description = "Tu habilidad definitiva obtiene un 10% de daño, curación y escudos adicionales. El aumento del daño en área se reduce un 5%.\nParticipar en el asesinato de un campeón enemigo reduce un 7% el enfriamiento restante de la definitiva."
        ),
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Maná",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/manaflowband/manaflowband.png",
            description = "Golpear a un campeón enemigo con una habilidad o ataque potenciado aumenta permanentemente tu maná máximo en 30, hasta 300 de maná."
        ),
        RuneItem(
            id = "botanist",
            name = "Botanista",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/biscuitdelivery/biscuitdelivery.png",
            description = "Cuando destruyes una planta, obtienes 10 de oro y efectos potenciados de la planta.\nFrutos de miel: Cuando se consumen, aumenta el efecto curativo un 20%.\nFlor del adivino: Cuando se destruye, la visión que otorga dura un 20% más.\nPiña explosiva: Tras el empujón, otorga un 40% de velocidad de movimiento durante 2,5 s."
        ),
        RuneItem(
            id = "hextech_flashtraption",
            name = "Hextello",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/hextechflashtraption/hextechflashtraption.png",
            description = "Cuando Destello está en enfriamiento, se reemplaza por Hextello. Tras una canalización de hasta 2 s, te trasladas a una ubicación nueva. La distancia varía en función el tiempo de canalización (18 s de enfriamiento).\nPasa a 6 s de enfriamiento al entrar en combate con campeones."
        ),
        RuneItem(
            id = "transcendence",
            name = "Trascendencia",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/transcendence/transcendence.png",
            description = "Otorga una bonificación al alcanzar los siguientes niveles:\nEn el nivel 1, otorga 5 de velocidad de habilidades.\nEn el nivel 5, otorga 5 de velocidad de habilidades.\nEn el nivel 9, reduce un 8% el enfriamiento de las habilidades básicas cuando golpeen a un objetivo (8 s de enfriamiento)."
        ),
        RuneItem(
            id = "celerity",
            name = "Celeridad",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/celerity/celeritytemp.png",
            description = "Obtiene un 2% de velocidad de movimiento. Aumentan un 7% todas las bonificaciones de velocidad de movimiento que recibas."
        ),
        RuneItem(
            id = "absolute_focus",
            name = "Concentración Absoluta",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/absolutefocus/absolutefocus.png",
            description = "Con más del 65% de la vida, obtienes 2-20 de daño de ataque o 4-40 de poder de habilidad adicional (adaptable)."
        ),
        RuneItem(
            id = "scorch",
            name = "Piroláser",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/scorch/scorch.png",
            description = "Infligir daño a un campeón enemigo con una habilidad lo quema y le inflige entre 21 y 49 de daño mágico adicional (según el nivel) tras 1 s (8 s de enfriamiento)."
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/nimbuscloak/6361.png",
            description = "Tras usar un hechizo (Destello, Prender, etc.), obtienes un 10-40% de velocidad de movimiento adicional durante 3 s. La eficacia de esta mejora depende del enfriamiento del hechizo utilizado."
        ),
        RuneItem(
            id = "gathering_storm",
            name = "Se Avecina Tormenta",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/gatheringstorm/gatheringstorm.png",
            description = "Tras 6 min de partida, otorga 2 de daño de ataque o 4 de poder de habilidad (adaptable), que aumentan cada 3 minutos a 5 o 10, 9 o 18, 14 o 28, etc."
        ),
        RuneItem(
            id = "ixtali_seedjar",
            name = "Semillero Ixtalí",
            category = "Brujería",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/timewarptonic/timewarptonic.png",
            description = "Al destruir una planta, obtienes una semilla al instante que reemplaza tu talismán durante 60 s. La semilla madura y se autodestruye poco después tras plantarla en la ubicación objetivo. (Cuando un aliado destruye una planta, también aparecerán semillas que puedes recoger).\nLas semillas están disponibles a partir del minuto 2 de la partida.\nCada planta tiene un enfriamiento de 30 s.\nLas piñas explosivas que plantes te lanzan más lejos al detonar."
        ),

        // =========================================================================
        // 3. DOMINACIÓN (DOMINATION)
        // =========================================================================
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/suddenimpact/suddenimpact.png",
            description = "Usar un deslizamiento, salto, destello, teletransporte o salir de sigilo otorga +10 de penetración de armadura y +10 de penetración mágica adicionales durante 4 segundos."
        ),
        RuneItem(
            id = "cheap_shot",
            name = "Golpe Bajo",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/cheapshot/cheapshot.png",
            description = "Dañar a campeones con movimiento o acciones reducidas (ralentizados, inmovilizados, aturdidos) inflige 10-45 de daño verdadero adicional."
        ),
        RuneItem(
            id = "taste_of_blood",
            name = "Sabor a Sangre",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/tasteofblood/greenterror_tasteofblood.png",
            description = "Cúrate entre 18-35 (+20% AD extra / +10% AP) de vida cuando infliges daño a un campeón enemigo (enfriamiento: 20s)."
        ),
        RuneItem(
            id = "eyeball_collection",
            name = "Colección de Ojos",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/eyeballcollection/eyeballcollection.png",
            description = "Consigue ojos por cada derribo de campeón enemigo (hasta 10 cargas). Otorga +2 AD o +4 AP por carga, y un bono adicional de +10 AD / +20 AP al completar la colección."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Centinela Zombi",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/zombieward/zombieward.png",
            description = "Destruir centinelas enemigos engendra un centinela zombi aliado visible en su lugar que otorga visión extendida en el mapa y fuerza adaptativa."
        ),
        RuneItem(
            id = "ghost_poro",
            name = "Poro Fantasma",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ghostporo/ghostporo.png",
            description = "Cuando tus centinelas expiran, dejan atrás un Poro Fantasma que otorga visión del sector hasta que un campeón enemigo lo espante."
        ),
        RuneItem(
            id = "ingenious_hunter",
            name = "Cazador Ingenioso",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ingenioushunter/ingenioushunter.png",
            description = "Otorga aceleración de objetos activos y pasivas de equipamiento, reduciendo sustancialmente los enfriamientos de Éxtasis, Fajín, Corona y similares."
        ),
        RuneItem(
            id = "ultimate_hunter",
            name = "Cazador Supremo",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/ultimatehunter/ultimatehunter.png",
            description = "Tu habilidad definitiva obtiene +6 de aceleración de habilidad, más +5 de aceleración adicional por cada derribo único de campeón enemigo."
        ),
        RuneItem(
            id = "shield_breaker",
            name = "Rompeescudos",
            category = "Dominación",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/domination/relentlesshunter/relentlesshunter.png",
            description = "Inflige un 15% de daño adicional a enemigos que posean escudos activos y destruye rápidamente defensas temporales enemigas."
        ),

        // =========================================================================
        // 4. PRECISIÓN (PRECISION)
        // =========================================================================
        RuneItem(
            id = "triumph",
            name = "Triunfo",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/triumph.png",
            description = "Los derribos de campeones restauran un 10% de la vida faltante e infligen un 5% de daño adicional a campeones enemigos que tengan menos del 35% de vida."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/coupdegrace/coupdegrace.png",
            description = "Inflige un 7% de daño adicional a campeones enemigos que tengan menos del 40% de vida máxima. La mejor opción para rematar objetivos frágiles."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/sorcery/laststand/laststand.png",
            description = "Inflige entre 5% y 11% de daño adicional a campeones enemigos mientras estés por debajo del 60% de tu propia vida (máximo al 30% de salud)."
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
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendalacrity/legendalacrity.png",
            description = "Otorga un 3% de velocidad de ataque más un 1.5% adicional por cada carga de Leyenda (hasta +18% de velocidad de ataque total)."
        ),
        RuneItem(
            id = "legend_bloodline",
            name = "Leyenda: Linaje",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendbloodline/legendbloodline.png",
            description = "Otorga omnivampirismo y robo de vida permanente al acumular derribos de campeones, monstruos grandes y súbditos (hasta +7% de robo de vida)."
        ),
        RuneItem(
            id = "legend_tenacity",
            name = "Leyenda: Tenacidad",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/legendtenacity/legendtenacity.png",
            description = "Otorga un 5% de tenacidad más un 2.5% adicional por cada carga de Leyenda (hasta +20% de tenacidad y resistencia a ralentizaciones)."
        ),
        RuneItem(
            id = "brutal",
            name = "Brutalidad / Brutal",
            category = "Precisión",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/precision/presstheattack/presstheattack.png",
            description = "Los ataques básicos infligen 12-19 de daño adaptativo adicional al impacto contra campeones enemigos."
        ),

        // =========================================================================
        // 5. VALOR (RESOLVE)
        // =========================================================================
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/boneplating/boneplating.png",
            description = "Tras recibir daño de un campeón enemigo, los siguientes 3 ataques o habilidades del enemigo infligen entre 35-65 menos de daño durante 1.5s (enfriamiento: 35s)."
        ),
        RuneItem(
            id = "second_wind",
            name = "Segundo Aire",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/secondwind/secondwind.png",
            description = "Tras recibir daño de un campeón rival, regenera 6 (+2% de tu vida faltante) a lo largo de 5 segundos. Se duplica para campeones cuerpo a cuerpo."
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
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/overgrowth/overgrowth.png",
            description = "Absorbe la esencia vital de súbditos o monstruos que mueran cerca de ti para aumentar permanentemente tu vida máxima en 3. Al alcanzar 120 súbditos, ganas +3.5% de vida máxima adicional."
        ),
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/fontoflife/fontoflife.png",
            description = "Ralentizar o inmovilizar a un campeón enemigo lo marca durante 3s. Los aliados que ataquen al enemigo marcado se curan un porcentaje de tu vida máxima a lo largo de 2 segundos."
        ),
        RuneItem(
            id = "demolish",
            name = "Demolición",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/demolish/demolish.png",
            description = "Carga un ataque demoledor contra estructuras enemigas cuando estás a menos de 550 de distancia durante 3s, infligiendo 200 (+30% de tu vida máxima) como daño físico demoledor a torretas."
        ),
        RuneItem(
            id = "perseverance",
            name = "Perseverancia",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/unflinching/unflinching.png",
            description = "Otorga +10% de tenacidad pasiva. Aumenta hasta +20% de tenacidad adicional y resistencia a ralentizaciones según la vida faltante de tu campeón."
        ),
        RuneItem(
            id = "revitalize",
            name = "Revitalizar",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/revitalize/revitalize.png",
            description = "Las curaciones y escudos que lanzas o recibes son un 5% más potentes. Aumenta un 10% adicional sobre objetivos con menos del 40% de vida."
        ),
        RuneItem(
            id = "loyalty",
            name = "Lealtad",
            category = "Valor",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/resolve/guardian/guardian.png",
            description = "Ganas +2 de armadura y +5 de resistencia mágica. Tu aliado más cercano obtiene +5 de armadura y +2 de resistencia mágica adicionales."
        ),

        // =========================================================================
        // 6. INSPIRACIÓN (INSPIRATION)
        // =========================================================================
        RuneItem(
            id = "pathfinder",
            name = "Pionero / Explorador",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/pathfinder.png",
            description = "Ganas un +9% de velocidad de movimiento fuera de combate en el río, la jungla y maleza. Restaura un 1% de tu vida o maná faltante por segundo en estos terrenos."
        ),
        RuneItem(
            id = "cosmic_insight",
            name = "Perspicacia Cósmica",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/cosmicinsight/cosmicinsight.png",
            description = "Otorga +18 de aceleración para Hechizos de Invocador y +10 de aceleración de objetos, permitiendo tener Destello, Prender o Castigo listos mucho más rápido."
        ),
        RuneItem(
            id = "future_market",
            name = "Mercado del Futuro",
            category = "Inspiración",
            iconUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/inspiration/futuresmarket/futuresmarket.png",
            description = "Puedes endeudarte para comprar objetos en la tienda de la base antes de contar con el oro total requerido (límite de deuda de hasta 250 de oro tras 2 minutos)."
        )
    )
}
