import re

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'Text("${tr(champ.primaryRole.shortName)} • ${tr("Runas")}: ${champ.recommendedRunes}", color = HextechGold, fontSize = 10.sp)',
    'Text("${tr(champ.primaryRole.shortName)}", color = HextechGold, fontSize = 10.sp)'
)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)
