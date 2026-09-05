import re

filepath = 'app/src/main/java/com/example/service/screen/DraftVisionScanner.kt'
with open(filepath, 'r') as f:
    text = f.read()

target = """        val defaultAllyRoles = arrayOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()
        
        for (i in 0..4) {
            allySlots[i]?.let { alliesMap[defaultAllyRoles[i]] = it }
            enemySlots[i]?.let { enemiesMap[defaultAllyRoles[i]] = it }
        }"""

replacement = """        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()
        val standardOrder = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)

        // Asignar aliados por su rol natural (primario o secundario) para evitar posiciones fijas incorrectas
        val allyChamps = allySlots.filterNotNull()
        val availableAllyRoles = standardOrder.toMutableList()
        
        for (champ in allyChamps) {
            if (availableAllyRoles.contains(champ.primaryRole) && !alliesMap.containsValue(champ)) {
                val roleMatches = allyChamps.count { it.primaryRole == champ.primaryRole }
                if (roleMatches == 1) {
                    alliesMap[champ.primaryRole] = champ
                    availableAllyRoles.remove(champ.primaryRole)
                }
            }
        }
        for (champ in allyChamps) {
            if (!alliesMap.containsValue(champ)) {
                val assignedRole = if (availableAllyRoles.contains(champ.primaryRole)) {
                    champ.primaryRole
                } else {
                    champ.secondaryRoles.firstOrNull { availableAllyRoles.contains(it) }
                        ?: availableAllyRoles.firstOrNull()
                }
                if (assignedRole != null) {
                    alliesMap[assignedRole] = champ
                    availableAllyRoles.remove(assignedRole)
                }
            }
        }

        // Asignar enemigos por su rol natural
        val enemyChamps = enemySlots.filterNotNull()
        val availableEnemyRoles = standardOrder.toMutableList()
        
        for (champ in enemyChamps) {
            if (availableEnemyRoles.contains(champ.primaryRole) && !enemiesMap.containsValue(champ)) {
                val roleMatches = enemyChamps.count { it.primaryRole == champ.primaryRole }
                if (roleMatches == 1) {
                    enemiesMap[champ.primaryRole] = champ
                    availableEnemyRoles.remove(champ.primaryRole)
                }
            }
        }
        for (champ in enemyChamps) {
            if (!enemiesMap.containsValue(champ)) {
                val assignedRole = if (availableEnemyRoles.contains(champ.primaryRole)) {
                    champ.primaryRole
                } else {
                    champ.secondaryRoles.firstOrNull { availableEnemyRoles.contains(it) }
                        ?: availableEnemyRoles.firstOrNull()
                }
                if (assignedRole != null) {
                    enemiesMap[assignedRole] = champ
                    availableEnemyRoles.remove(assignedRole)
                }
            }
        }"""

text = text.replace(target, replacement)

with open(filepath, 'w') as f:
    f.write(text)

