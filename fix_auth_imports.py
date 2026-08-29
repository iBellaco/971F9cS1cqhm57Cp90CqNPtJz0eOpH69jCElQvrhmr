import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Add imports
imports = """
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
"""
text = text.replace('import androidx.compose.animation.*', 'import androidx.compose.animation.*\n' + imports)

# Undo the previous fully qualified replacements
text = text.replace('modifier = Modifier.padding(top = 4.dp).androidx.compose.foundation.clickable {', 'modifier = Modifier.padding(top = 4.dp).clickable {')
text = text.replace('androidx.compose.material.icons.filled.VisibilityOff', 'Icons.Filled.VisibilityOff')
text = text.replace('androidx.compose.material.icons.filled.Visibility,', 'Icons.Filled.Visibility,')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
