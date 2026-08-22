import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = re.sub(r'versionCode = \d+', 'versionCode = 113', content)
content = re.sub(r'versionName = "[\d\.]+"', 'versionName = "2.12"', content)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'r') as f:
    content = f.read()

content = content.replace('v2.11', 'v2.12')

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'w') as f:
    f.write(content)
