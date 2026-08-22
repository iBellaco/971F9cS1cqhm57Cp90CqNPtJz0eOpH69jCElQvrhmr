import re

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

content = content.replace('Icons.Default.Star', 'Icons.Default.Menu')

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()

# Remove one of the annotations
content = content.replace('@OptIn(ExperimentalMaterial3Api::class)\n@OptIn(ExperimentalMaterial3Api::class)', '@OptIn(ExperimentalMaterial3Api::class)')
# Or just remove all and add one
content = re.sub(r'(@OptIn\(ExperimentalMaterial3Api::class\)\s*)+', '@OptIn(ExperimentalMaterial3Api::class)\n', content)

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)
