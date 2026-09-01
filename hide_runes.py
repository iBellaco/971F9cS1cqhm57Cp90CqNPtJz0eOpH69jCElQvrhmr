import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'text = tr(" Runas:") + " ${myEval.champion.recommendedRunes} • " + tr("Toca para ver build completa"),',
    'text = tr("Toca para ver build completa"),'
)
content = content.replace(
    'text = tr(" Runas:") + " ${topPick.champion.recommendedRunes}",',
    'text = tr("Toca para ver build completa"),'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
