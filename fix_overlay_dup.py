import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'if (allies.none { it.id == champ.id } && allies.size < 5) {',
    'if (allies.none { it.id == champ.id } && enemies.none { it.id == champ.id } && allies.size < 5) {'
)

content = content.replace(
    'if (enemies.none { it.id == champ.id } && enemies.size < 5) {',
    'if (enemies.none { it.id == champ.id } && allies.none { it.id == champ.id } && enemies.size < 5) {'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
