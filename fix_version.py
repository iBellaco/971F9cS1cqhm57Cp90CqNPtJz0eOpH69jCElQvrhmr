with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

import re
text = re.sub(r'versionCode = \d+', 'versionCode = 56', text)
text = re.sub(r'versionName = "\d+\.\d+\.\d+"', 'versionName = "1.1.56"', text)

with open('app/build.gradle.kts', 'w') as f:
    f.write(text)
