import re

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "r") as f:
    content = f.read()

content = content.replace("background(Color(0xFF0F1522), RoundedCornerShape(8.dp))", "background(HextechSurfaceVariant, RoundedCornerShape(8.dp))")

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "w") as f:
    f.write(content)
