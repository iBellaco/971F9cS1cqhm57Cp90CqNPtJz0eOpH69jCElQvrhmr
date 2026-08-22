import re

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()

content = content.replace("@Composable\n@OptIn(ExperimentalMaterial3Api::class)\n@Composable", "@OptIn(ExperimentalMaterial3Api::class)\n@Composable")

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

content = content.replace("Icons.Default.Menu", "Icons.Default.FlashOn")

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)
