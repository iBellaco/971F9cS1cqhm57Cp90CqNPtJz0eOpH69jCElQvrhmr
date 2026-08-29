import re

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'r') as f:
    text = f.read()

text = text.replace('androidx.compose.material.icons.filled.Description', 'androidx.compose.material.icons.Icons.Default.Description')

if 'import androidx.compose.material.icons.filled.Description' not in text:
    text = text.replace('import androidx.compose.material.icons.filled.History', 'import androidx.compose.material.icons.filled.History\nimport androidx.compose.material.icons.filled.Description')

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'w') as f:
    f.write(text)
