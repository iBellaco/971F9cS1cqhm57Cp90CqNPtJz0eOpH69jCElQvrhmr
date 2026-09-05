import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Make DraftAvatarBox smaller
text = text.replace('.size(46.dp)', '.size(38.dp)')

# Make row padding smaller
text = text.replace('modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),', 
                    'modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),')

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
