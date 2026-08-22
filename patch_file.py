import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Fix LaunchedEffect block
content = content.replace("        LaunchedEffect(Unit) {", "        androidx.compose.runtime.LaunchedEffect(Unit) {")
content = content.replace("                            kotlinx.coroutines.launch {", "                            launch {")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
