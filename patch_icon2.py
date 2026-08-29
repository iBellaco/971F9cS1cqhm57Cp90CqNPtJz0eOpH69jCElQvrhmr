import re

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'r') as f:
    text = f.read()

text = text.replace('androidx.compose.material.icons.Icons.Default.Description', 'Icons.Default.Description')

with open('app/src/main/java/com/example/ui/screens/DraftHistoryScreen.kt', 'w') as f:
    f.write(text)
