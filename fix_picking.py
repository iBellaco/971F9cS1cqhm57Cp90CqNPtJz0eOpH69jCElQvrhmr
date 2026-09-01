import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''                    "ENEMY" -> {
                        val idx = enemySlots.indexOfFirst { it.assignedRole == targetRole }
                        if (idx >= 0) {
                            enemySlots[idx] = DraftSlot(champ, targetRole)
                        } else {
                            if (enemySlots.size >= 5) enemySlots.removeAt(enemySlots.size - 1)
                            enemySlots.add(DraftSlot(champ, targetRole))
                        }
                    }''',
    '''                    "ENEMY" -> {
                        if (enemySlots.size >= 5) enemySlots.removeAt(enemySlots.size - 1)
                        if (!enemySlots.any { it.id == champ.id }) {
                            enemySlots.add(champ)
                        }
                    }'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
