import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('Text(text = "Roles", color = Color.Gray)', 'Text(text = com.example.util.tr("Roles"), color = Color.Gray)')
content = content.replace('Text(text = "Daño", color = Color.Gray)', 'Text(text = com.example.util.tr("Daño"), color = Color.Gray)')
content = content.replace('Text(text = "Tier", color = Color.Gray)', 'Text(text = com.example.util.tr("Tier"), color = Color.Gray)')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
