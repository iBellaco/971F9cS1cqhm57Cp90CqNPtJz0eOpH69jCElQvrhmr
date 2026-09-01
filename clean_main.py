import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

# Remove duplicated onNavigateToTutorial in DashboardScreen
content = re.sub(
    r'onNavigateToTutorial: \(\) -> Unit,\s*onNavigateToTutorial: \(\) -> Unit,',
    r'onNavigateToTutorial: () -> Unit,',
    content
)

# Remove duplicated onNavigateToTutorial = onNavigateToTutorial,
content = re.sub(
    r'onNavigateToTutorial = onNavigateToTutorial,\s*onNavigateToTutorial = onNavigateToTutorial,',
    r'onNavigateToTutorial = onNavigateToTutorial,',
    content
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
