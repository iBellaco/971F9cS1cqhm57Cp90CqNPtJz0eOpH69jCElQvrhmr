import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Remove duplicate imports of Icons
lines = text.split('\n')
new_lines = []
for line in lines:
    if line.startswith('import androidx.compose.material.icons.Icons'):
        continue
    new_lines.append(line)

text = '\n'.join(new_lines)
text = text.replace('import androidx.compose.material.icons.filled.Info', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Info')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
