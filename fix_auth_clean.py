import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Clean up broken imports
text = re.sub(r'import Icons\.Filled\.Info\n?', '', text)
text = text.replace('import androidx.compose.animation.*', 'import androidx.compose.animation.*\nimport androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Info')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
