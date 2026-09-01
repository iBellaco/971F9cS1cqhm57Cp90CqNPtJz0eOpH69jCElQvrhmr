import re

with open('app/src/main/java/com/example/data/repository/DraftHistoryRepository.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'import com.example.model.DraftSlot',
    'import com.example.model.DraftSlot\nimport com.example.model.Champion'
)

# Fix map on enemies which is already a List<Champion>
content = content.replace(
    'val enemyIds = enemies.map { it.champion.id }',
    'val enemyIds = enemies.map { it.id }'
)

content = content.replace(
    'enemies = enemies.map { it.champion },',
    'enemies = enemies,'
)

content = content.replace(
    'val enemyNames = enemies.joinToString { it.champion.name }',
    'val enemyNames = enemies.joinToString { it.name }'
)

content = content.replace(
    'val enemyLaneOpponent = enemies.find { it.assignedRole == myRole }?.champion',
    'val enemyLaneOpponent = enemies.find { it.primaryRole == myRole } ?: enemies.find { it.secondaryRoles.contains(myRole) }'
)

with open('app/src/main/java/com/example/data/repository/DraftHistoryRepository.kt', 'w') as f:
    f.write(content)
