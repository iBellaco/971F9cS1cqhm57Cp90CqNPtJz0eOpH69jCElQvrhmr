import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    text = f.read()

# Need to import isLightAppTheme
text = text.replace('import com.example.ui.theme.HextechDarkBg', 'import com.example.ui.theme.HextechDarkBg\nimport com.example.ui.theme.isLightAppTheme')

# Replace the bug report button block to include the theme toggle
regex = r'(IconButton\(\s*onClick = \{ showBugReportDialog = true \}.*?\s*\)\s*\{\s*Icon\([^}]+\}\s*\))'

theme_toggle = """IconButton(
                            onClick = { isLightAppTheme = !isLightAppTheme },
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .clip(CircleShape)
                                .background(HextechSurface)
                        ) {
                            Icon(
                                imageVector = if (isLightAppTheme) androidx.compose.material.icons.Icons.Default.DarkMode else androidx.compose.material.icons.Icons.Default.LightMode,
                                contentDescription = "Toggle Theme",
                                tint = HextechCyan
                            )
                        }
                        """

text = re.sub(regex, theme_toggle + r'\1', text, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(text)
