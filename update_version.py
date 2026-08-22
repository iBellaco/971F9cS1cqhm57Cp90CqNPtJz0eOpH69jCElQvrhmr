import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('versionCode = 115', 'versionCode = 116')
content = content.replace('versionName = "2.14"', 'versionName = "2.15"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
