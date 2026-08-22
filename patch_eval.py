import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('val myChamp = allies.find { it.primaryRole == activeRole || it.secondaryRoles.contains(activeRole) }', '')
content = content.replace('if (myChamp != null) {', 'if (myChampion != null) {\n            val myChamp = myChampion')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
