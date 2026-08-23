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

    fun getRuneDrawableRes(nameOrId: String): Int? {
        val clean = nameOrId.trim().lowercase()
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
            iconUrl = "undefined",
            description = "After channeling for 3.5 seconds, teleport your champion to an allied champion, structure, or ward (excludes areas in range of enemy inhibitors). You can only teleport to structures during the first 6 minutes of the game.Cooldown: 150s"
        ),
        RuneItem(
            id = "dark_harvest",
            name = "Cosecha Oscura",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1760126804_dark_harvest_rune.webp",
            description = "Bonus Damage, Stack AmplificationDamaging a champion below 50% health deals adaptive damage and harvests their soul, permanently increasing Dark Harvest's damage by 11.Dark Harvest damage: 35+ 11 per soul + 10% bonus AD + 5% AP Cooldown: 20s (Resets to 1s on takedown)"
        ),
        RuneItem(
            id = "empowerment",
            name = "Fortalecimiento",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729601_8005.webp",
            description = "Increased damage against championsHitting an enemy champion with 3 consecutive attacks deals bonus adaptive damage and amplifies your damage dealt by 8% until you leave combat with champions.Adaptive Damage: 40-165 ()Cooldown: 4s.Damage amplification will only take effect against champions."
        ),
        RuneItem(
            id = "lethal_tempo",
            name = "Compás Letal",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729643_8008.webp",
            description = "Attack SpeedGain stacks of Attack Speed when attacking enemy champions. Stacks up to 6 times. At max stacks, you gain bonus range and can exceed the Attack Speed cap.Each stack: increase 6-14% (Melee) or 3.5-8% (Ranged) Attack Speed for 6 seconds.At max stack bonus: Gain 25 (Melee) or 50 (Ranged) Attack Distance."
        ),
        RuneItem(
            id = "fleet_footwork",
            name = "Pies Veloces",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-06/1749148113_17377296298021.webp",
            description = "Mobility, HealMoving, attacking and casting builds Energy stacks. At 100 stacks, your next attack gains Attack Speed, heals you, grants bonus MS Movement Speed. If the attack is again st a champion, it also restores Mana or Energy.Bonus Attack Speed: 40%Health Restore: 15-110 () + 15% bonus AD + 10% AP .Bonus Movement Speed: 20% for 1s.When attacking a champion, restore 8% missing mana or 8% missing energy.When attacking minions or monsters, heals for 35% (Melee champions) or 15% (Ranged champions) of the original heal amount."
        ),
        RuneItem(
            id = "conqueror",
            name = "Conquistador",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729565_8010.webp",
            description = "Stacking Damage, VampGain stacks of Adaptive Force when hitting a champion with separate attacks or abilities. Stacks up to 6 times. When fully stacked, gain bonus omnivamp.Per stack: 3-5 bonus AD or 4-8 AP for 6s.Fully stacked bonus: Melee - 9%, Ranged - 5% bonus Omnivamp ."
        ),
        RuneItem(
            id = "grasp_undying",
            name = "Garras del Inmortal",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729699_8437.webp",
            description = "Tank, HealEvery 3s in combat, your next attack on a champion will be enhanced.Bonus magic damage: 3.3% HP Heal: 1.3% HP Permanently health increase: 10On Ranged champions, the effects are reduced by 60%."
        ),
        RuneItem(
            id = "guardian",
            name = "Guardián",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774950298_guardian.webp",
            description = "Protect, ShieldGuard allies within 350 units of you and allies you target with abilities for 2.5 second(s). While guarding, if you or the ally take more than a certain amount of damage, both of you gain a shield for 1.5 second(s).Shield: 40–165 () + 6% bonus HP + 15% AP Damage threshold: 70–240 damage taken ()Cooldown: 55–25s ()"
        ),
        RuneItem(
            id = "aery",
            name = "Aery",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729544_8214.webp",
            description = "Poke, ProtectYour attacks and abilities send Aery to a target, damaging enemies or shielding allies.Damage: 15-70 () + 10% bonus AD + 5% AP Shield: 25-120 () + 10% bonus AD + 5% AP Aery cannot be sent out again until she returns to you."
        ),
        RuneItem(
            id = "arcane_comet",
            name = "Cometa Arcano",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729714_8229.webp",
            description = "Poke, Stack AmplificationDamaging a champion with an ability hurls a comet at their location. When a comet hits an enemy champion, the next comet's damage increases.Damage: (15 to 100) + (2 x total hits on enemy champions) + 10% bonus AD + 5% AP .Cooldown: 16-8s ()"
        ),
        RuneItem(
            id = "phase_rush",
            name = "Irrupción de Fase",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729570_8230.webp",
            description = "Mobility, Ability HasteUsing basic attacks or abilities on an enemy champion 3 time(s) within 4s grants MS Movement Speed and reduces the remaining cooldown of basic abilities by 20%.Duration: 3s.Movement Speed bonus: Melee - 40%-60% () | Ranged - 20-35% ().Ability Haste: 10.Slow Resist: 60%.Cooldown: 21-7s ()"
        ),
        RuneItem(
            id = "first_strike",
            name = "Primer Golpe",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-01/1737729700_8369.webp",
            description = "Initiate, Damage Amplification, Bonus GoldInitiating combat with an enemy champion or dealing damage to them within 0.25s of engaging them in combat grants 10 gold and First Strike for 3s, allowing you to deal 7% bonus true damage to them. After the effect ends, gain bonus gold based on the bonus damage dealt for its duration.If you do not deal damage to the enemy champion within 0.25s of engaging them in combat, First Strike will go into a 10-second cooldown.Bonus gold:Melee: 65% of bonus damage.Ranged: 45% of bonus damage.Cooldown: 20-30s"
        ),
        RuneItem(
            id = "glacial_augment",
            name = "Soberano Gélido",
            category = "Runa Clave",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774949508_frost-sovereign.webp",
            description = "Control, SlowImmobilizing an enemy champion causes 3 beams to form around them, creating ice beneath them for 3 second(s) and slowing enemies inside. The slow lingers on enemies for 1.5 second(s) after they’ve left the ice zone. Gain a protective layer of ice around yourself, increasing your defenses. After a brief delay, the ice explodes, dealing a burst of magic damage around you.Slow: (1% of your bonus Health + 15%).Defenses: 35 + 75% bonus Armor and Magic Resist. Lasts 2.5 second(s).Magic damage: 15–100 () + 5% max HP Cooldown: 20s"
        ),

        // =========================================================================
        // 2. BRUJERÍA / INSPIRACIÓN (SORCERY) - 11 RUNAS OFICIALES DE WILD RIFT
        // =========================================================================
        RuneItem(
            id = "axiomatic_arcanist",
            name = "Arcanólogo Axiomático",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1761189941_axiom-arcanist.webp",
            description = "Empowered Ultimate AbilityYour ultimate ability has 10% increased damage, healing, and shielding. (AoE damage is reduced to a 5% increase.)Scoring a takedown on an enemy champion reduces your ultimate ability's remaining cooldown by 7%."
        ),
        RuneItem(
            id = "manaflow_band",
            name = "Banda de Maná",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392903_manaflow-band.webp",
            description = "Increase ManaHitting an enemy champion with and ability or empowered attack permanently increases your max mana by 30, up to 300 mana."
        ),
        RuneItem(
            id = "botanist",
            name = "Botanista",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952316_botanist.webp",
            description = "Empowered plant effectsWhen you destroy a plant, gain 10 gold and empowered plant effects. Soulflowers near the turrets also grant additional bonuses.Honeyfruit: Heal is increased by 20% when consumed.Scryer's Bloom: Vision granted lasts 20% longer when destroyed.Blast Cone: Gain 40% Movement Speed for 2.5 second(s) after the knockback."
        ),
        RuneItem(
            id = "hextech_flashtraption",
            name = "Hextello",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392779_hextech-flashtraption.webp",
            description = "Gain short-range movement while Flash is on cooldownWhile Flash is on cooldown, it is replaced by Hexflash. Dash a distance based on charge time (max 2s). Entering combat with enemy champions to trigger a 6-second cooldown. Cooldown: 18s"
        ),
        RuneItem(
            id = "transcendence",
            name = "Trascendencia",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392866_transcendence.webp",
            description = "Reduces ability cooldownsGain a bonus when reaching the following levels:At level 1, gain 5 Ability Haste;at level 5, gain bonus 5 Ability Haste;at level 9, after Basic Ability hit the target, reduce 8% the ability's cooldown time.Cooldown: 8s"
        ),
        RuneItem(
            id = "celerity",
            name = "Celeridad",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1761189545_celerity.webp",
            description = "Increase Movement SpeedGain 2% Movement Speed. All Movement Speed bonuses on you are also increased by 7%."
        ),
        RuneItem(
            id = "absolute_focus",
            name = "Concentración Absoluta",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952466_absolute-focus.webp",
            description = "Gain Attack Damage/Ability Power at high HealthWhile above 65% Health, gain a bonus 2–20 Attack Damage () or 2–30 Ability Power () (Adaptive)."
        ),
        RuneItem(
            id = "scorch",
            name = "Piroláser",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952748_scorch.webp",
            description = "Abilities deal bonus damageDamaging an enemy champion with an ability burns them, dealing 21-49 bonus magic damage () after 1 seconds.Cooldown: 8s"
        ),
        RuneItem(
            id = "nimbus_cloak",
            name = "Capa del Nimbo",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392930_nimbus-cloak.webp",
            description = "Spells increase Movement SpeedAfter using a Spell (Flash, Ignite, etc.), 10% - 40% movement bonus for 3 seconds. The speedup effectiveness depends on the Spell's cooldown."
        ),
        RuneItem(
            id = "gathering_storm",
            name = "Se Avecina Tormenta",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774952894_gathering-storm.webp",
            description = "Increase Attack Damage/Ability Power over timeStarting from 6 minutes into the game, gain increasing Attack Damage or Ability Power (Adaptive). Bonuses increase time, totaling 2/5/9/14/etc. AD or 4/10/18/28/etc. AP based on game time."
        ),
        RuneItem(
            id = "ixtali_seedjar",
            name = "Semillero Ixtalí",
            category = "Brujería",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392953_ixtali-seedjar.webp",
            description = "Plant fruits after destroying oneAfter destroying a plant, immediately gain a seeds that replaces your trinket for 60 seconds. The seed matures and self-destructs after it is planted at a target location. (Seeds you can pick up will also drop when an ally destroys a plant.)Seeds become obtainable 2 minutes after the game starts.Cooldown: Each plant has a unique 30s"
        ),

        // =========================================================================
        // 3. DOMINACIÓN (DOMINATION)
        // =========================================================================
        RuneItem(
            id = "sudden_impact",
            name = "Impacto Repentino",
            category = "Dominación",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753391561_sudden-impact.webp",
            description = "Triggers when in stealth or dashingDamaging an enemy champion deals a bonus 15-65 true damage after using a dash, leap, blink, teleport, or when exiting stealth for 4s.The damaging attack/ability gains bonuses at higher levels:Level 5: Deal an additional 5 true damage.Level 9: Deal an additional 5 true damage and gain 10% Movement Speed for 1.5s after dealing the damage.Cooldown: 10s"
        ),
        RuneItem(
            id = "cheap_shot",
            name = "Golpe Bajo",
            category = "Dominación",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753391621_cheap-shot.webp",
            description = "Targets movement-impaired enemiesDeals 10-45 bonus true damage to enemies whose movement is impaired.Cooldown: 7s"
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
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753391605_eyeball-collector.webp",
            description = "Kills increase Attack Damage/Ability PowerGains 1.5 AD or 3 AP after scoring a champion or epic monster takedown, stacking up to 8 times."
        ),
        RuneItem(
            id = "zombie_ward",
            name = "Centinela Zombi",
            category = "Dominación",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753391650_zombie-ward.webp",
            description = "Vision control increases Attack Damage/Ability PowerTakedowns on enemy wards spawn a Zombie Ward in its place, granting vision of the surrounding area for 120 seconds. Additionally gain 3 AD or 6 AP (max 5 stacks). (Assists on enemy wards also grant stacks and spawn Zombie Wards.)"
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
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753391664_ingenious-hunter.webp",
            description = "Kills increase Item Ability HasteGains 20 Item Ability Haste. For each champion or epic monster takedown you score, gain an additional 5 Item Ability Haste. Stacks up to 5 times."
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
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392053_triumph.webp",
            description = "Increase damage when low in HealthChampion takedowns restore 10% of lost health and 10% of maximum Mana Energyand grant 35 Movement Speed for 2 second(s)."
        ),
        RuneItem(
            id = "coup_de_grace",
            name = "Golpe de Gracia",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392209_coup-de-grace.webp",
            description = "Increase damage to low Health enemiesYour attacks deal 8% bonus adaptive damage to enemy champions with less than 40% Health."
        ),
        RuneItem(
            id = "last_stand",
            name = "Último Esfuerzo",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392114_last-stand.webp",
            description = "Increase damage when low in HealthWhen health is lower than 60% HP , attacks launched at enemy champions deal 5-11% bonus adaptive damage.Grants maximum bonus damage when Health is lower than 30% HP"
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
            description = "Increase bonus Attack SpeedGains 3% Attack Speed. Takedown monsters, enemy champions, or minions to gain up to an additional 18% Attack Speed."
        ),
        RuneItem(
            id = "legend_bloodline",
            name = "Leyenda: Linaje",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392254_legend-bloodline.webp",
            description = "Increase OmnivampGains 1% Omnivamp . Takedown monsters, enemy champions, or minions to gain up to an additional 7% Omnivamp ."
        ),
        RuneItem(
            id = "legend_tenacity",
            name = "Leyenda: Tenacidad",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392264_legend-tenacity.webp",
            description = "Increase Tenacity and Slow ResistGains 3% Tenacity and 3% Slow Resist. Takedown monsters, enemy champions, or minions to gain up to an additional 15% Tenacity and 20% Slow Resist."
        ),
        RuneItem(
            id = "brutal",
            name = "Brutalidad / Brutal",
            category = "Precisión",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392016_brutal.webp",
            description = "Attacks deal on-hit damageAttacks deal (5 + 6% bonus AD + 3% AP ) bonus adaptive damage to enemy champions."
        ),

        // =========================================================================
        // 5. VALOR (RESOLVE)
        // =========================================================================
        RuneItem(
            id = "bone_plating",
            name = "Revestimiento de Huesos",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392660_bone-plating.webp",
            description = "Anti-Burst DamageWhen taking damage from a champion, the current and next 3 champion abilities or attacks against you and within 1.5s deal 30-60 () less damage.Cooldown: 40s"
        ),
        RuneItem(
            id = "second_wind",
            name = "Segundo Aire",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392641_second-wind.webp",
            description = "Increase sustainGain 5 Health HP every 5 seconds.After taking damage from an enemy champion, regenerate 3 + (1.5% of your missing health) HP over the next 5 seconds. This effect is doubled for melee champions."
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
            description = "Increase max HealthFor every 3 enemy minions or 3 monster(s) killed nearby, permanently gain 3 max Health. Max Health can be increased indefinitely this way. Gain an additional 3% max Health upon reaching 30 stacks."
        ),
        RuneItem(
            id = "font_of_life",
            name = "Fuente de Vida",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392605_font-of-life.webp",
            description = "Team HealWhen your attacks or abilities hit an enemy champion, heal yourself and the lowest Health allied champion nearby.Ally: Heals for 1.5% of your max HP + 5% of your AP You: Heal for 1% of your max HP + 5% of your AP Healing is 130% effective if you're a melee champion. (Does not trigger if you or nearby allies are at full Health, or if no allies are nearby.)Cooldown: 15s"
        ),
        RuneItem(
            id = "demolish",
            name = "Demolición",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774951923_demolish.webp",
            description = "Destroy turrets fasterWhen within 550 range of an enemy turret, gain a charge every 0.5s, up to 6 times.When fully charged, your next attack against the turret deals an additional (100 + 22% max Health HP ) physical damage.Cooldown: 30s"
        ),
        RuneItem(
            id = "perseverance",
            name = "Perseverancia",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392452_perseverance.webp",
            description = "Increase survivability when crowd controlledGain 10% Tenacity. Gain 10-15 Armor and Magic Resistance () for 1.5 seconds when mmobilized. Refresh duration time when immobilized multiple times."
        ),
        RuneItem(
            id = "revitalize",
            name = "Revitalizar",
            category = "Valor",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753392494_revitalize.webp",
            description = "Empowered heals and shieldsGains a 5% amplification effect when Healing or granting Shields. If the target's Health is lower than 40%, the effect is amplified by an additional 10%."
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
