import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'onPickEnemyRole: (LaneRole) -> Unit,',
    'onPickEnemy: () -> Unit,'
)
content = content.replace(
    'onRemoveEnemyRole: (LaneRole) -> Unit,',
    'onRemoveEnemy: (Champion) -> Unit,'
)

content = content.replace(
    '''                        onPickEnemyRole = { suggestedRole ->
                            suggestedPickingRole = suggestedRole
                            pickingForTeam = "ENEMY"
                        },''',
    '''                        onPickEnemy = {
                            suggestedPickingRole = null
                            pickingForTeam = "ENEMY"
                        },'''
)

content = content.replace(
    '''                        onRemoveEnemyRole = { role ->
                            val idx = enemySlots.indexOfFirst { it.assignedRole == role }
                            if (idx >= 0) enemySlots.removeAt(idx)
                        },''',
    '''                        onRemoveEnemy = { champ ->
                            enemySlots.remove(champ)
                        },'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
