import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''    val enemySlots = remember(WildRiftRepository.champions.toList()) {
        mutableStateListOf<Champion>().apply {
            addAll(generateRoleBasedDraft(usedDraftChampIds))
        }
    }''',
    '''    val enemySlots = remember(WildRiftRepository.champions.toList()) {
        mutableStateListOf<Champion>().apply {
            addAll(generateRoleBasedDraft(usedDraftChampIds).map { it.champion })
        }
    }'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
