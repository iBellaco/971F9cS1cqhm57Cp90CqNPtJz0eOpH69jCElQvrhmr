import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

if 'import androidx.compose.runtime.collectAsState' not in text:
    text = text.replace('import androidx.compose.runtime.getValue', 'import androidx.compose.runtime.getValue\nimport androidx.compose.runtime.collectAsState')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
