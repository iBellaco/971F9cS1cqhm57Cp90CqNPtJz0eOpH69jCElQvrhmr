import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

start_idx = content.find('if (userRole == "admin"')
print(content[start_idx:start_idx+1000])
