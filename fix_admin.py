import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

# fix TextFieldDefaults.outlinedTextFieldColors -> OutlinedTextFieldDefaults.colors
text = text.replace('TextFieldDefaults.outlinedTextFieldColors', 'androidx.compose.material3.OutlinedTextFieldDefaults.colors')

# BackgroundDark is in com.example.ui.theme.BackgroundDark, let's just use HextechSurface if it's not imported.
text = text.replace('BackgroundDark', 'HextechSurface')

# Ensure context exists for Toast
if 'val context = androidx.compose.ui.platform.LocalContext.current' not in text:
    text = text.replace('val scope = rememberCoroutineScope()', 'val scope = rememberCoroutineScope()\n    val context = androidx.compose.ui.platform.LocalContext.current')

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
