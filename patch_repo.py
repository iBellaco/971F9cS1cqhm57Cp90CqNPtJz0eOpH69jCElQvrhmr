import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

target = """        var directMatchupWarning: String? = null
        var directCounterBestPick: String? = null

        val enemySett = enemies.find { it.id == "sett" }
        val enemyVi = enemies.find { it.id == "vi" }
        val enemyCaitlyn = enemies.find { it.id == "caitlyn" }
        val enemyZed = enemies.find { it.id == "zed" || it.id == "kayn" || it.id == "talon" || it.id == "khazix" }
        val enemyTanks = enemies.filter { it.isFrontline }

        if (enemySett != null && enemyVi != null) {
            directMatchupWarning = com.example.util.trStr(lang, "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad.")
            directCounterBestPick = "Morgana o Janna"
        } else if (enemyCaitlyn != null) {
            directMatchupWarning = com.example.util.trStr(lang, "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank.")
            directCounterBestPick = "Nautilus o Viego"
        } else if (enemyZed != null) {
            directMatchupWarning = com.example.util.trStr(lang, "Peligro de asesinos de burst") + " (${enemyZed.name}). " + com.example.util.trStr(lang, "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus).")
            directCounterBestPick = "Lulu, Nautilus o Zhonya"
        } else if (enemyTanks.size >= 2) {
            directMatchupWarning = com.example.util.trStr(lang, "Composición rival pesada") + " (${enemyTanks.joinToString { it.name }}). " + com.example.util.trStr(lang, "Requiere daño verdadero y % vida máxima.")
            directCounterBestPick = "Vayne, Sett, Gwen o Liandry"
        }"""

replacement = """        var directMatchupWarning: String? = null
        var directCounterBestPick: String? = null

        val enemyAssassins = enemies.filter { it.id in listOf("zed", "kayn", "talon", "khazix", "akali", "evelynn", "katarina", "fizz") }
        val enemyTanks = enemies.filter { it.isFrontline }
        val enemyRangedAdvantage = enemies.filter { it.id in listOf("caitlyn", "lux", "xerath", "varus", "ezreal") }
        
        if (isAllyFullAd && myRole != LaneRole.SUPPORT && myRole != LaneRole.ADC) {
            directMatchupWarning = com.example.util.trStr(lang, "Nuestra composición es full Daño Físico (AD). El enemigo acumulará armadura.")
            directCounterBestPick = com.example.util.trStr(lang, "Selecciona daño mágico (AP) para balancear")
        } else if (isAllyFullAp && myRole != LaneRole.SUPPORT) {
            directMatchupWarning = com.example.util.trStr(lang, "Nuestra composición es full Daño Mágico (AP). El enemigo acumulará resistencia mágica.")
            directCounterBestPick = com.example.util.trStr(lang, "Selecciona daño físico (AD) para balancear")
        } else if (physPct >= 80) {
            directMatchupWarning = com.example.util.trStr(lang, "El enemigo es predominantemente daño Físico (AD).")
            directCounterBestPick = "Rammus, Malphite, o " + com.example.util.trStr(lang, "apilar armadura (Corazón Helado/Malla de Espinas)")
        } else if (magicPct >= 75) {
            directMatchupWarning = com.example.util.trStr(lang, "El enemigo es predominantemente daño Mágico (AP).")
            directCounterBestPick = "Galio, Dr. Mundo, o " + com.example.util.trStr(lang, "apilar resistencia (Fuerza de la Naturaleza)")
        } else if (enemyAssassins.size >= 2) {
            directMatchupWarning = com.example.util.trStr(lang, "Peligro de asesinos de burst") + " (${enemyAssassins.joinToString { it.name }}). " + com.example.util.trStr(lang, "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Nautilus).")
            directCounterBestPick = "Lulu, Nautilus, Janna"
        } else if (enemyTanks.size >= 2) {
            directMatchupWarning = com.example.util.trStr(lang, "Composición rival pesada/tanque") + " (${enemyTanks.joinToString { it.name }}). " + com.example.util.trStr(lang, "Requiere daño verdadero, % vida máxima y penetración.")
            directCounterBestPick = "Vayne, Gwen, Lilia, o Liandry"
        } else if (enemyRangedAdvantage.size >= 2) {
            directMatchupWarning = com.example.util.trStr(lang, "Composición rival de pokeo y rango") + " (${enemyRangedAdvantage.joinToString { it.name }}). " + com.example.util.trStr(lang, "Evita asedios lentos. Requiere hard engage, emboscada o flanqueos rápidos.")
            directCounterBestPick = "Malphite, Vi, Jarvan IV, Hecarim"
        }"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
