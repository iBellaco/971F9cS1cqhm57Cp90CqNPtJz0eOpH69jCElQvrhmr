import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

if "import androidx.compose.material.icons.filled.SupportAgent" not in content:
    content = content.replace("import androidx.compose.material.icons.filled.Warning", "import androidx.compose.material.icons.filled.Warning\nimport androidx.compose.material.icons.filled.SupportAgent")

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(content)
