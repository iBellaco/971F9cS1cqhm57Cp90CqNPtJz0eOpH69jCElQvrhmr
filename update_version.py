with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('versionCode = 202', 'versionCode = 203')
content = content.replace('versionName = "1.3.183"', 'versionName = "1.3.184"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)

print("Version updated to 203 (1.3.184)")
