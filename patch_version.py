import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

# increment version
match_code = re.search(r'versionCode = (\d+)', content)
if match_code:
    code = int(match_code.group(1))
    content = re.sub(r'versionCode = \d+', f'versionCode = {code + 1}', content)

match_name = re.search(r'versionName = "([^"]+)"', content)
if match_name:
    name = match_name.group(1)
    parts = name.split('.')
    if len(parts) == 2:
        new_name = f"{parts[0]}.{int(parts[1]) + 1}"
        content = re.sub(r'versionName = "[^"]+"', f'versionName = "{new_name}"', content)

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
