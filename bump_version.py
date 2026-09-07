import sys

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

content = content.replace('versionCode = 131', 'versionCode = 132')
content = content.replace('versionName = "1.3.19"', 'versionName = "1.3.20"')

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
print("Bumped version")
