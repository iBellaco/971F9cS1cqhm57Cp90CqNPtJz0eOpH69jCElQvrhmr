import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Replace ${tr(spell.name)} with ${spell.name} in LaunchedEffect
content = content.replace('message = "${tr(spell.name)} de $roleLabel disponible",', 'message = "${spell.name} de $roleLabel disponible",')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
