import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

# Remove TUTORIAL from enum
content = content.replace("    META,\n    TUTORIAL", "    META")

# Remove onNavigateToTutorial parameter from DashboardScreen
content = content.replace("    onNavigateToTutorial: () -> Unit,\n", "")
content = content.replace("                        onNavigateToTutorial = onNavigateToTutorial,\n", "")
content = content.replace("                    onNavigateToTutorial = { currentScreen = AppScreen.TUTORIAL },\n", "")

# Remove AppScreen.TUTORIAL -> ...
content = re.sub(r'\s*AppScreen\.TUTORIAL -> \{[^\}]+\}', '', content)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
