import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

text = text.replace('import androidx.compose.material.icons.filled.Visibility', 'import androidx.compose.material.icons.filled.Lock\nimport androidx.compose.material.icons.filled.LockOpen')
text = text.replace('import androidx.compose.material.icons.filled.VisibilityOff\n', '')
text = text.replace('androidx.compose.material.icons.filled.VisibilityOff', 'androidx.compose.material.icons.filled.Lock')
text = text.replace('androidx.compose.material.icons.filled.Visibility', 'androidx.compose.material.icons.filled.LockOpen')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
