import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

target1 = """        val isAllyFullAp = allies.isNotEmpty() && allyMagicCount >= 3 && allyPhysCount == 0"""
replacement1 = """        val isAllyFullAp = allies.isNotEmpty() && allyMagicCount >= 3 && allyPhysCount == 0
        
        var allyPhysPct = 0
        var allyMagicPct = 0
        var allyTruePct = 0
        var allyCompositionWarning: String? = null
        if (allies.isNotEmpty()) {
            var aPhys = 0
            var aMag = 0
            var aTrue = 0
            allies.forEach { 
                when (it.damageType) {
                    DamageType.PHYSICAL -> aPhys++
                    DamageType.MAGIC -> aMag++
                    DamageType.TRUE_HYBRID -> aTrue++
                }
            }
            val totalAlly = (aPhys + aMag + aTrue).coerceAtLeast(1)
            allyPhysPct = (aPhys * 100) / totalAlly
            allyMagicPct = (aMag * 100) / totalAlly
            allyTruePct = (100 - (allyPhysPct + allyMagicPct)).coerceAtLeast(0)
            
            if (allyPhysPct >= 80) {
                allyCompositionWarning = com.example.util.trStr(lang, "Exceso de Daño Físico aliado (AD).", "Excesso de Dano Físico aliado (AD).", "Exceso de Daño Físico aliado (AD).")
            } else if (allyMagicPct >= 75) {
                allyCompositionWarning = com.example.util.trStr(lang, "Exceso de Daño Mágico aliado (AP).", "Excesso de Dano Mágico aliado (AP).", "Exceso de Daño Mágico aliado (AP).")
            }
        }"""

target2 = """        return DraftAnalysisResult(
            physicalDamagePercent = physPct,
            magicDamagePercent = magicPct,
            trueDamagePercent = truePct,
            frontlineStatus = frontlineStatus,"""
replacement2 = """        return DraftAnalysisResult(
            physicalDamagePercent = physPct,
            magicDamagePercent = magicPct,
            trueDamagePercent = truePct,
            allyPhysicalDamagePercent = allyPhysPct,
            allyMagicDamagePercent = allyMagicPct,
            allyTrueDamagePercent = allyTruePct,
            allyCompositionWarning = allyCompositionWarning,
            frontlineStatus = frontlineStatus,"""

content = content.replace(target1, replacement1)
content = content.replace(target2, replacement2)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
print("Done repo")
