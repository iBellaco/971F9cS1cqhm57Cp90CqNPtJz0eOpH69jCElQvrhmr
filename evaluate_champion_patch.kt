    fun evaluateChampion(
        champ: Champion,
        myRole: LaneRole,
        allies: List<Champion>,
        enemies: List<Champion>,
        lang: String = "es"
    ): DraftRecommendation {
        val otherAllies = allies.filter { it.id != champ.id }
        
        // Ally Damage Profile
        val allyPhysCount = otherAllies.count { it.damageType == DamageType.PHYSICAL }
        val allyMagicCount = otherAllies.count { it.damageType == DamageType.MAGIC }
        val isAllyFullAd = otherAllies.isNotEmpty() && allyPhysCount >= 3 && allyMagicCount == 0
        val isAllyFullAp = otherAllies.isNotEmpty() && allyMagicCount >= 3 && allyPhysCount == 0
        val frontlineAllies = otherAllies.count { it.isFrontline }

        var score = champ.winrate
        var badge = ""
        var reasonParts = mutableListOf<String>()
        var synergyText = ""
        var counterText = ""

        val directCounters = champ.advantageAgainst.filter { adv ->
            enemies.any { it.name.equals(adv, ignoreCase = true) || it.id.equals(adv, ignoreCase = true) }
        }
        val directWeaknesses = champ.counteredBy.filter { weak ->
            enemies.any { it.name.equals(weak, ignoreCase = true) || it.id.equals(weak, ignoreCase = true) }
        }
        val directSynergies = champ.synergies.filter { syn ->
            otherAllies.any { it.name.equals(syn, ignoreCase = true) || it.id.equals(syn, ignoreCase = true) }
        }

        // Enemy Counter Scoring
        score += (directCounters.size * 2.8)
        score -= (directWeaknesses.size * 2.2)

        // Ally Synergy Scoring
        score += (directSynergies.size * 2.2)

        // Damage Balance compensation
        if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
            score += 3.5 
        } else if (isAllyFullAp && champ.damageType == DamageType.PHYSICAL) {
            score += 3.5 
        }

        // Frontline compensation
        if (frontlineAllies == 0 && champ.isFrontline) {
            score += 2.8 
        }

        // Generate dynamic badge and reason based on the same logic in analyzeDraft
        if (directCounters.isNotEmpty() && directWeaknesses.isEmpty()) {
            badge = "⚡ COUNTER FUERTE (+${directCounters.size})"
            reasonParts.add("Tienes ventaja directa sobre ${directCounters.joinToString(", ")}.")
        } else if (directWeaknesses.isNotEmpty()) {
            badge = "⚠️ PELIGRO MATCHUP (-${directWeaknesses.size})"
            reasonParts.add("Peligro: Puedes sufrir contra ${directWeaknesses.joinToString(", ")}.")
        } else if (directSynergies.isNotEmpty()) {
            badge = "⚡ SINERGIA CON EQUIPO (+${directSynergies.size})"
            reasonParts.add("Sinergia óptima con ${directSynergies.joinToString(", ")}.")
        } else if (isAllyFullAd && champ.damageType == DamageType.MAGIC) {
            badge = "🔮 APERTURA MÁGICA"
            reasonParts.add("Proporcionas daño mágico muy necesario para evitar que el rival acumule Armadura.")
        } else if (isAllyFullAp && champ.damageType == DamageType.PHYSICAL) {
            badge = "🗡️ APERTURA FÍSICA"
            reasonParts.add("Proporcionas daño físico muy necesario para evitar que el rival acumule Resistencia Mágica.")
        } else if (frontlineAllies == 0 && champ.isFrontline) {
            badge = "🛡️ SALVADOR FRONTLINE"
            reasonParts.add("Excelente elección: el equipo no tenía tanques ni iniciación sólida.")
        } else {
            badge = "⚖️ SELECCIÓN ESTÁNDAR"
            reasonParts.add("Una opción neutral en este escenario.")
        }

        if (directSynergies.isNotEmpty()) {
            synergyText = "Buena combinación con: ${directSynergies.joinToString(", ")}"
        } else {
            synergyText = "Autosuficiente."
        }

        if (directCounters.isNotEmpty()) {
            counterText = "Anula a: ${directCounters.joinToString(", ")}"
        } else if (directWeaknesses.isNotEmpty()) {
            counterText = "Juega seguro contra: ${directWeaknesses.joinToString(", ")}"
        } else {
            counterText = "Enfrentamiento parejo."
        }
        
        // Return 
        return DraftRecommendation(
            champion = champ,
            estimatedWinrate = ((score * 10).toInt() / 10.0).coerceAtMost(70.0),
            advantageBadge = badge,
            tacticalReason = champ.tacticalAdvice + " " + reasonParts.joinToString(" "),
            runes = champ.recommendedRunes,
            synergyDetails = synergyText,
            counterDetails = counterText
        )
    }
