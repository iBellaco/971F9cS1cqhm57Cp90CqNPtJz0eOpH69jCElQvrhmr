import sys

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

content = content.replace('versionCode = 132', 'versionCode = 133')
content = content.replace('versionName = "1.3.20"', 'versionName = "1.3.21"')

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
print("Updated version to 1.3.21")
