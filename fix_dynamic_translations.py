import re

with open('app/src/main/java/com/example/util/DynamicTranslations.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Add WildRiftRepository lookup
if 'import com.example.data.WildRiftRepository' not in content:
    content = content.replace('import org.json.JSONObject', 'import org.json.JSONObject\nimport com.example.data.WildRiftRepository')

injection = """
    fun get(lang: String, key: String): String? {
        val staticTranslation = if (lang == "en") enMap?.get(key) else if (lang == "pt") ptMap?.get(key) else null
        if (staticTranslation != null) return staticTranslation
        
        val isEn = lang == "en"
        
        // ----------------------------------------------------
        // DYNAMIC REPOSITORY LOOKUP (SUPABASE LOCALIZED COLUMNS)
        // ----------------------------------------------------
        val itemByName = WildRiftRepository.items.find { it.name.equals(key, ignoreCase = true) }
        if (itemByName != null) {
            val loc = if (isEn) itemByName.nameEn else itemByName.namePt
            if (loc.isNotBlank()) return loc
        }
        val itemByStats = WildRiftRepository.items.find { it.stats.equals(key, ignoreCase = true) }
        if (itemByStats != null) {
            val loc = if (isEn) itemByStats.statsEn else itemByStats.statsPt
            if (loc.isNotBlank()) return loc
        }
        val itemByPassive = WildRiftRepository.items.find { it.passive.equals(key, ignoreCase = true) }
        if (itemByPassive != null) {
            val loc = if (isEn) itemByPassive.passiveEn else itemByPassive.passivePt
            if (loc.isNotBlank()) return loc
        }
        val champByName = WildRiftRepository.champions.find { it.name.equals(key, ignoreCase = true) }
        if (champByName != null) {
            val loc = if (isEn) champByName.nameEn else champByName.namePt
            if (loc.isNotBlank()) return loc
        }
        val champByTitle = WildRiftRepository.champions.find { it.title.equals(key, ignoreCase = true) }
        if (champByTitle != null) {
            val loc = if (isEn) champByTitle.titleEn else champByTitle.titlePt
            if (loc.isNotBlank()) return loc
        }
        val runeByName = WildRiftRepository.runes.find { it.name.equals(key, ignoreCase = true) }
        if (runeByName != null) {
            val loc = if (isEn) runeByName.nameEn else runeByName.namePt
            if (loc.isNotBlank()) return loc
        }
        val runeByDesc = WildRiftRepository.runes.find { it.description.equals(key, ignoreCase = true) }
        if (runeByDesc != null) {
            val loc = if (isEn) runeByDesc.descriptionEn else runeByDesc.descriptionPt
            if (loc.isNotBlank()) return loc
        }
        val spellByName = WildRiftRepository.summonerSpells.find { it.name.equals(key, ignoreCase = true) }
        if (spellByName != null) {
            val loc = if (isEn) spellByName.nameEn else spellByName.namePt
            if (loc.isNotBlank()) return loc
        }
        val spellByDesc = WildRiftRepository.summonerSpells.find { it.description.equals(key, ignoreCase = true) }
        if (spellByDesc != null) {
            val loc = if (isEn) spellByDesc.descriptionEn else spellByDesc.descriptionPt
            if (loc.isNotBlank()) return loc
        }
        
        return null
    }
"""

content = re.sub(r'fun get\(lang: String, key: String\): String\? \{.*?\n    \}', injection.strip(), content, flags=re.DOTALL)

with open('app/src/main/java/com/example/util/DynamicTranslations.kt', 'w', encoding='utf-8') as f:
    f.write(content)
