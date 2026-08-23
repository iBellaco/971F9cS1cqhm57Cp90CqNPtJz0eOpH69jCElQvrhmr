import re
with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

text = re.sub(r'versionCode = 149', 'versionCode = 150', text)
text = re.sub(r'versionName = "2.48"', 'versionName = "2.49"', text)

with open('app/build.gradle.kts', 'w') as f:
    f.write(text)
