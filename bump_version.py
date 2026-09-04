import re

file_path = 'app/build.gradle.kts'
with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Update versionCode
vc_match = re.search(r'versionCode = (\d+)', content)
if vc_match:
    new_vc = int(vc_match.group(1)) + 1
    content = re.sub(r'versionCode = \d+', f'versionCode = {new_vc}', content)

# Update versionName
vn_match = re.search(r'versionName = "1\.1\.(\d+)"', content)
if vn_match:
    new_vn = int(vn_match.group(1)) + 1
    content = re.sub(r'versionName = "1\.1\.\d+"', f'versionName = "1.1.{new_vn:02d}"', content)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)

print(f"Updated to versionCode {new_vc} / versionName 1.1.{new_vn:02d}")
