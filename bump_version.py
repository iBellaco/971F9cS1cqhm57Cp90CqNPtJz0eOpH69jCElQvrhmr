import re

with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

# Find versionCode
version_code_match = re.search(r'versionCode = (\d+)', text)
if version_code_match:
    new_code = int(version_code_match.group(1)) + 1
    text = re.sub(r'versionCode = \d+', f'versionCode = {new_code}', text)

# Find versionName
version_name_match = re.search(r'versionName = "(.*?)"', text)
if version_name_match:
    current_name = version_name_match.group(1)
    parts = current_name.split('.')
    parts[-1] = str(int(parts[-1]) + 1)
    new_name = '.'.join(parts)
    text = re.sub(r'versionName = ".*?"', f'versionName = "{new_name}"', text)

with open('app/build.gradle.kts', 'w') as f:
    f.write(text)
