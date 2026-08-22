with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('versionCode = 117', 'versionCode = 118')
content = content.replace('versionName = "2.16"', 'versionName = "2.17"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
