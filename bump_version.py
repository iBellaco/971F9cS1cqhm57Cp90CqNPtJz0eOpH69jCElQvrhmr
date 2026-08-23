import re

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

# Find versionCode
version_code_match = re.search(r'versionCode = (\d+)', content)
if version_code_match:
    old_version_code = int(version_code_match.group(1))
    new_version_code = old_version_code + 1
    content = re.sub(r'versionCode = \d+', f'versionCode = {new_version_code}', content)

# Find versionName
version_name_match = re.search(r'versionName = "(.*?)"', content)
if version_name_match:
    old_version_name = version_name_match.group(1)
    # Simple bump logic assuming format X.Y.Z
    parts = old_version_name.split('.')
    if len(parts) == 3:
        parts[2] = str(int(parts[2]) + 1)
        new_version_name = '.'.join(parts)
        content = re.sub(r'versionName = ".*?"', f'versionName = "{new_version_name}"', content)

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
print("Version bumped!")
