import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Add standard import
if 'import androidx.compose.material.icons.filled.Info' not in text:
    text = text.replace('import androidx.compose.material.icons.Icons', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Info')

text = text.replace('androidx.compose.material.icons.filled.Info', 'Icons.Filled.Info')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
