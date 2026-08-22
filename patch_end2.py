import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace("Modifier.fillMaxSize()", "androidx.compose.foundation.layout.fillMaxSize()")

content = content.rstrip()
if content.endswith("}"):
    content = content[:-1]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
