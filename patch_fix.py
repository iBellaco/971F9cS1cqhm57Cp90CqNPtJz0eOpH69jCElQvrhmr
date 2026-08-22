import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Fix import for fillMaxSize
if "import androidx.compose.foundation.layout.fillMaxSize" not in content:
    content = content.replace("import androidx.compose.foundation.layout.fillMaxWidth", "import androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.fillMaxSize")

# Revert the wrong fillMaxSize usage
content = content.replace("modifier = androidx.compose.foundation.layout.fillMaxSize()", "modifier = Modifier.fillMaxSize()")

# Remove the very last '}' if it's extra
content = content.rstrip()
if content.endswith("}"):
    content = content[:-1]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
