import re

with open('app/src/main/AndroidManifest.xml', 'r') as f:
    content = f.read()

content = content.replace('android:foregroundServiceType="specialUse"', 'android:foregroundServiceType="specialUse|mediaProjection"')

with open('app/src/main/AndroidManifest.xml', 'w') as f:
    f.write(content)
