import re

with open('app/build.gradle.kts', 'r', encoding='utf-8') as f:
    content = f.read()

content = re.sub(r'versionCode = \d+', 'versionCode = 215', content)
content = re.sub(r'versionName = "[\d\.]+"', 'versionName = "3.13"', content)

with open('app/build.gradle.kts', 'w', encoding='utf-8') as f:
    f.write(content)
