import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

aapt = """
  aaptOptions {
    noCompress("json")
  }
"""
content = re.sub(r'android\s*\{', 'android {' + aapt, content)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
