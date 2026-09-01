import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'onPickEnemy: () -> Unit,',
    'onPickEnemyRole: (LaneRole) -> Unit,'
)

content = content.replace(
    'onRemoveEnemy: (Champion) -> Unit,',
    'onRemoveEnemyRole: (LaneRole) -> Unit,'
)

content = content.replace(
'''                        onPickEnemy = {
                            suggestedPickingRole = null
                            pickingForTeam = "ENEMY"
                        },''',
'''                        onPickEnemyRole = { role ->
                            suggestedPickingRole = role
                            pickingForTeam = "ENEMY"
                        },'''
)

content = content.replace(
'''                        onRemoveEnemy = { champ ->
                            enemySlots.removeAll { it.champion.id == champ.id }
                        },''',
'''                        onRemoveEnemyRole = { role ->
                            val idx = enemySlots.indexOfFirst { it.assignedRole == role }
                            if (idx >= 0) enemySlots.removeAt(idx)
                        },'''
)

content = content.replace(
'''        // Panel de Selección de Equipo Rival
        com.example.ui.components.DraftEnemyTeamCard(
            enemies = enemySlots.map { it.champion },
            onPickEnemy = onPickEnemy,
            onRemoveEnemy = onRemoveEnemy,
            onChampionClick = onSelectChampion
        )''',
'''        // Panel de Selección de Equipo Rival
        com.example.ui.components.DraftTeamPositionCard(
            title = "Equipo Rival",
            isEnemy = true,
            slots = enemySlots,
            activeUserRole = activeRole,
            onPickChampionForRole = onPickEnemyRole,
            onRemoveChampionForRole = onRemoveEnemyRole,
            onChampionClick = onSelectChampion
        )'''
)

# And fix line 632 where we add the enemy
content = content.replace(
'''                        if (!enemySlots.any { it.champion.id == champ.id }) {
                            enemySlots.add(champ)
                        }''',
'''                        if (!enemySlots.any { it.champion.id == champ.id }) {
                            val roleToAssign = suggestedPickingRole ?: LaneRole.TOP
                            val existingIndex = enemySlots.indexOfFirst { it.assignedRole == roleToAssign }
                            if (existingIndex >= 0) {
                                enemySlots[existingIndex] = DraftSlot(champ, roleToAssign)
                            } else {
                                if (enemySlots.size >= 5) {
                                    enemySlots.removeAt(enemySlots.size - 1)
                                }
                                enemySlots.add(DraftSlot(champ, roleToAssign))
                            }
                        }'''
)


with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
