import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

content = content.replace('''
  aaptOptions {
    noCompress("json")
  }
''', '''
  androidResources {
    noCompress.add("json")
  }
''')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
