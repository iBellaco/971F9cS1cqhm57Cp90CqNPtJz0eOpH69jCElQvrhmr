import re

with open('app/src/main/java/com/example/data/repository/DraftHistoryRepository.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''        val enemySlotsAssigned = com.example.model.DraftRoleAssigner.assignRoles(enemies)
        val enemyDataList = enemySlotsAssigned.map {
            SavedDraftSlotData(
                championId = it.champion.id,
                championName = it.champion.name,
                role = it.assignedRole.name,
                avatarUrl = it.champion.avatarUrl
            )
        }''',
    '''        val enemyDataList = enemies.map {
            SavedDraftSlotData(
                championId = it.id,
                championName = it.name,
                role = it.primaryRole.name,
                avatarUrl = it.avatarUrl
            )
        }'''
)

with open('app/src/main/java/com/example/data/repository/DraftHistoryRepository.kt', 'w') as f:
    f.write(content)
