import re
with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

text = re.sub(r'versionCode = 1147', 'versionCode = 1148', text)
text = re.sub(r'versionName = "1.1.47"', 'versionName = "1.1.48"', text)

with open('app/build.gradle.kts', 'w') as f:
    f.write(text)
