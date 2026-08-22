import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

pattern = r"val allies = remember \{.*?val enemies = remember \{.*?\}"
replacement = """val allies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }
    val enemies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }"""

content = re.sub(pattern, replacement, content, flags=re.DOTALL)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
