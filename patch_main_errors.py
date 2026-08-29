import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

if 'import androidx.compose.foundation.background' not in text:
    text = text.replace('import androidx.compose.foundation.layout.*', 'import androidx.compose.foundation.layout.*\nimport androidx.compose.foundation.background')

if 'import com.example.ui.theme.TextSecondary' not in text:
    text = text.replace('import com.example.ui.theme.AppThemeManager', 'import com.example.ui.theme.AppThemeManager\nimport com.example.ui.theme.TextSecondary')

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
