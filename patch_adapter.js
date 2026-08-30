const fs = require('fs');

let content = fs.readFileSync('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'utf8');

const oldFuncStart = `    fun generate4BuildOptions(
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
        val resolvedSpells1 = ensureUniqueSpells(defaultSpells, role)`;

const newFuncStart = `    fun generate4BuildOptions(
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
        if (champ.builds.isNotEmpty()) {
            return champ.builds.mapIndexed { idx, b ->
                val resolvedSpells = ensureUniqueSpells(b.spells, role)
                val resolvedSpellsIcons = resolvedSpells.map { WildRiftSpellsAndRunes.getSpellIconByName(it) }
                ChampionBuildOption(
                    optionNumber = idx + 1,
                    title = "Opción ${"${idx + 1}:"} ${"${b.title}"}",
                    subtitle = "",
                    source = "BestBuildWR",
                    badge = if (idx == 0) "PRINCIPAL" else "SITUACIONAL",
                    tacticalReason = "Build adaptativa extraída directamente de los datos del meta actual.",
                    items = b.items,
                    bootBase = defaultBootBase,
                    bootUpgrade = defaultBootUpgrade,
                    runes = b.runes.split(",").map { it.trim() }.filter { it.isNotBlank() },
                    spells = resolvedSpells,
                    spellsIcons = resolvedSpellsIcons
                )
            }
        }
        val resolvedSpells1 = ensureUniqueSpells(defaultSpells, role)`;

content = content.replace(oldFuncStart, newFuncStart);

fs.writeFileSync('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', content);
console.log("Patched ChampionRoleAdapter.kt");
