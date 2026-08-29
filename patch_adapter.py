import re

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'r') as f:
    text = f.read()

# We need to replace the entire generate4BuildOptions function
# with a simpler one that returns only 2 options.

new_func = """
    fun generate4BuildOptions(
        champ: Champion,
        role: LaneRole,
        defaultBuild8: List<String>,
        defaultBootBase: String,
        defaultBootUpgrade: String,
        opt1Runes: List<String>,
        opt2Runes: List<String>,
        defaultSpells: List<String>,
        defaultSpellsIcons: List<String>
    ): List<ChampionBuildOption> {
        val resolvedSpells1 = ensureUniqueSpells(defaultSpells, role)
        val resolvedSpellsIcons1 = resolvedSpells1.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt1 = ChampionBuildOption(
            optionNumber = 1,
            title = "Opción 1: Build Principal (BestBuildWR)",
            subtitle = "",
            source = "BestBuildWR",
            badge = "ESTÁNDAR",
            tacticalReason = "Build principal extraída directamente del meta actual y los mejores jugadores.",
            items = champ.coreItems,
            bootBase = defaultBootBase,
            bootUpgrade = defaultBootUpgrade,
            runes = listOf(champ.recommendedRunes),
            spells = resolvedSpells1,
            spellsIcons = resolvedSpellsIcons1
        )

        val resolvedSpells2 = ensureUniqueSpells(if (champ.build2Spells.isNotEmpty()) champ.build2Spells else defaultSpells, role)
        val resolvedSpellsIcons2 = resolvedSpells2.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }

        val opt2 = ChampionBuildOption(
            optionNumber = 2,
            title = "Opción 2: Build Alternativa / Situacional",
            subtitle = "",
            source = "BestBuildWR / Coach",
            badge = "ADAPTATIVA",
            tacticalReason = "Build secundaria y situacional para adaptarte a diferentes composiciones enemigas o ventajas en la fase de líneas.",
            items = champ.situationalItems.ifEmpty { champ.coreItems.reversed() },
            bootBase = defaultBootBase,
            bootUpgrade = defaultBootUpgrade,
            runes = listOf(if (champ.build2Runes.isNotBlank()) champ.build2Runes else champ.recommendedRunes),
            spells = resolvedSpells2,
            spellsIcons = resolvedSpellsIcons2
        )

        return listOf(opt1, opt2)
    }
"""

text = re.sub(r'fun generate4BuildOptions\([^\{]+\{.*?(?=private fun generateSituationalSwaps)', new_func, text, flags=re.DOTALL)

with open('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'w') as f:
    f.write(text)
