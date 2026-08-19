with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

# Try changing Divider back to HorizontalDivider if that was the reason it failed a deprecation warning, though the build succeeded.
content = content.replace('Divider(color = HextechGold.copy(alpha = 0.2f))', 'HorizontalDivider(color = HextechGold.copy(alpha = 0.2f))')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
