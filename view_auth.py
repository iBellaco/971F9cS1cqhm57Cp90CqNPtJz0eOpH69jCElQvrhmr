import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

print(content[content.find('// Premium Status Card'):content.find('// Expiring soon alert banner')])
