import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'text = "⚔️ Fuerte contra: " + champ.advantageAgainst.joinToString(", "),',
    'text = "⚔️ Fuerte contra: " + champ.advantageAgainst.take(3).joinToString(", "),'
)

content = content.replace(
    'text = "⚠️ Débil contra: " + champ.counteredBy.joinToString(", "),',
    'text = "⚠️ Débil contra: " + champ.counteredBy.take(3).joinToString(", "),'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
