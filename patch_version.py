import sys

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

content = content.replace('versionCode = 137', 'versionCode = 138')
content = content.replace('versionName = "1.3.25"', 'versionName = "1.3.26"')

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
print("Updated version to 1.3.26")
