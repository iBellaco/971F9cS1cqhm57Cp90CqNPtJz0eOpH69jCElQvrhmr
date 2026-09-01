import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''                        onPickEnemyRole = { role ->
                            suggestedPickingRole = role
                            pickingForTeam = "ENEMY"
                        },''',
    '''                        onPickEnemy = {
                            suggestedPickingRole = null
                            pickingForTeam = "ENEMY"
                        },'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
