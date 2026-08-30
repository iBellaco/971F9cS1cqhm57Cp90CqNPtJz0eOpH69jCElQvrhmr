with open('app/build.gradle.kts', 'r') as f:
    code = f.read()

code = code.replace('versionCode = 203', 'versionCode = 204')
code = code.replace('versionName = "1.3.184"', 'versionName = "1.3.185"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(code)

print("Version updated to 204 (1.3.185)")
