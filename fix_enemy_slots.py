import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Fix onLoadDraft
content = re.sub(
    r'enemySlots\.addAll\(enemies\)',
    r'enemySlots.addAll(enemies.map { it.champion })',
    content
)

# Fix enemyLaneOpponent calculation
content = content.replace(
    'val enemyLaneOpponent = enemySlots.find { it.assignedRole == activeRole }?.champion',
    'val enemyLaneOpponent = com.example.model.DraftRoleAssigner.assignRoles(enemySlots).find { it.assignedRole == activeRole }?.champion'
)

# Fix analyzeDraft enemies parameter
content = content.replace(
    'enemies = enemySlots.map { it.champion }',
    'enemies = enemySlots'
)

# Fix onRemoveEnemyRole inside MetaScreenMode.DRAFTING
# Wait, DraftAnalysisTab no longer takes onRemoveEnemyRole(LaneRole), but onRemoveEnemy(Champion)
# We will do this later manually or carefully.

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
