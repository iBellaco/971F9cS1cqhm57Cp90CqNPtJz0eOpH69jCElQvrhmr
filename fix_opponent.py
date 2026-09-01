import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'val enemyLaneOpponent = com.example.model.DraftRoleAssigner.assignRoles(enemySlots).find { it.assignedRole == activeRole }?.champion',
    'val enemyLaneOpponent = enemySlots.find { it.primaryRole == activeRole } ?: enemySlots.find { it.secondaryRoles.contains(activeRole) }'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
