import re

with open('app/src/main/java/com/example/util/Translator.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Make sure WildRiftRepository is imported
if 'import com.example.data.WildRiftRepository' not in content:
    content = content.replace('import androidx.compose.runtime.Composable', 'import androidx.compose.runtime.Composable\nimport com.example.data.WildRiftRepository')

injection = """
    // ----------------------------------------------------
    // DYNAMIC REPOSITORY LOOKUP (SUPABASE LOCALIZED COLUMNS)
    // ----------------------------------------------------
    val itemByName = WildRiftRepository.items.find { it.name.equals(key, ignoreCase = true) }
    if (itemByName != null) {
        val loc = if (effectiveLang == "en") itemByName.nameEn else itemByName.namePt
        if (loc.isNotBlank()) return loc
    }
    val itemByStats = WildRiftRepository.items.find { it.stats.equals(key, ignoreCase = true) }
    if (itemByStats != null) {
        val loc = if (effectiveLang == "en") itemByStats.statsEn else itemByStats.statsPt
        if (loc.isNotBlank()) return loc
    }
    val itemByPassive = WildRiftRepository.items.find { it.passive.equals(key, ignoreCase = true) }
    if (itemByPassive != null) {
        val loc = if (effectiveLang == "en") itemByPassive.passiveEn else itemByPassive.passivePt
        if (loc.isNotBlank()) return loc
    }
    val champByName = WildRiftRepository.champions.find { it.name.equals(key, ignoreCase = true) }
    if (champByName != null) {
        val loc = if (effectiveLang == "en") champByName.nameEn else champByName.namePt
        if (loc.isNotBlank()) return loc
    }
    val champByTitle = WildRiftRepository.champions.find { it.title.equals(key, ignoreCase = true) }
    if (champByTitle != null) {
        val loc = if (effectiveLang == "en") champByTitle.titleEn else champByTitle.titlePt
        if (loc.isNotBlank()) return loc
    }
    val runeByName = WildRiftRepository.runes.find { it.name.equals(key, ignoreCase = true) }
    if (runeByName != null) {
        val loc = if (effectiveLang == "en") runeByName.nameEn else runeByName.namePt
        if (loc.isNotBlank()) return loc
    }
    val runeByDesc = WildRiftRepository.runes.find { it.description.equals(key, ignoreCase = true) }
    if (runeByDesc != null) {
        val loc = if (effectiveLang == "en") runeByDesc.descriptionEn else runeByDesc.descriptionPt
        if (loc.isNotBlank()) return loc
    }
    val spellByName = WildRiftRepository.summonerSpells.find { it.name.equals(key, ignoreCase = true) }
    if (spellByName != null) {
        val loc = if (effectiveLang == "en") spellByName.nameEn else spellByName.namePt
        if (loc.isNotBlank()) return loc
    }
    val spellByDesc = WildRiftRepository.summonerSpells.find { it.description.equals(key, ignoreCase = true) }
    if (spellByDesc != null) {
        val loc = if (effectiveLang == "en") spellByDesc.descriptionEn else spellByDesc.descriptionPt
        if (loc.isNotBlank()) return loc
    }
    // ----------------------------------------------------
"""

# Insert right after `if (effectiveLang == "es") return key`
content = content.replace('if (effectiveLang == "es") return key', f'if (effectiveLang == "es") return key\n{injection}')

with open('app/src/main/java/com/example/util/Translator.kt', 'w', encoding='utf-8') as f:
    f.write(content)
