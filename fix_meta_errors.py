import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'enemies = enemySlots,',
    'enemies = enemySlots.map { it.champion },'
)

content = content.replace(
    'enemySlots: List<Champion>,',
    'enemySlots: List<DraftSlot>,'
)

content = content.replace(
    'enemySlots.remove(champ)',
    'enemySlots.removeAll { it.champion.id == champ.id }'
)

content = content.replace(
    'alreadySelected = allySlots.map { it.champion.id } + enemySlots.map { it.id },',
    'alreadySelected = allySlots.map { it.champion.id } + enemySlots.map { it.champion.id },'
)

content = content.replace(
    'if (!enemySlots.any { it.id == champ.id }) {',
    'if (!enemySlots.any { it.champion.id == champ.id }) {'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
