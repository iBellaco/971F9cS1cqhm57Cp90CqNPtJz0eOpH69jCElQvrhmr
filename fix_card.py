import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''        // Panel de Selección Oficial de Posiciones - Equipo Rival
        DraftTeamPositionCard(
            title = "Equipo Rival",
            isEnemy = true,
            slots = enemySlots,
            activeUserRole = null,
            onPickChampionForRole = onPickEnemyRole,
            onRemoveChampionForRole = onRemoveEnemyRole,
            onChampionClick = onSelectChampion
        )''',
    '''        // Panel de Selección de Equipo Rival
        com.example.ui.components.DraftEnemyTeamCard(
            enemies = enemySlots,
            onPickEnemy = onPickEnemy,
            onRemoveEnemy = onRemoveEnemy,
            onChampionClick = onSelectChampion
        )'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
