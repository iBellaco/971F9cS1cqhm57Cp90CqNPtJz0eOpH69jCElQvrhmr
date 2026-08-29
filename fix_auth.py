import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

text = text.replace('color = HextechGold', 'color = com.example.ui.theme.HextechGold')
text = text.replace('modifier = Modifier.padding(top = 4.dp).clickable {', 'modifier = Modifier.padding(top = 4.dp).androidx.compose.foundation.clickable {')
text = text.replace('Icons.Filled.VisibilityOff', 'androidx.compose.material.icons.filled.VisibilityOff')
text = text.replace('Icons.Filled.Visibility,', 'androidx.compose.material.icons.filled.Visibility,')

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
