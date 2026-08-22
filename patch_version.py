with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('versionCode = 116', 'versionCode = 117')
content = content.replace('versionName = "2.15"', 'versionName = "2.16"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
