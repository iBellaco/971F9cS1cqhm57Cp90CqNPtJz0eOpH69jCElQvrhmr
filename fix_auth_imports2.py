import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Make sure Icons is imported
if 'import androidx.compose.material.icons.Icons' not in text:
    text = text.replace('import androidx.compose.material.icons.filled.Visibility', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Visibility')

# Fix VisibilityOff
text = text.replace('Icons.Filled.VisibilityOff', 'androidx.compose.material.icons.filled.VisibilityOff')
text = text.replace('Icons.Filled.Visibility,', 'androidx.compose.material.icons.filled.Visibility,')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
