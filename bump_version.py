import re
with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

vc_match = re.search(r'versionCode = (\d+)', text)
vn_match = re.search(r'versionName = "([^"]+)"', text)

if vc_match and vn_match:
    old_vc = int(vc_match.group(1))
    old_vn = vn_match.group(1)
    
    new_vc = old_vc + 1
    # Simple semantic version bumping for the last digit
    parts = old_vn.split('.')
    parts[-1] = str(int(parts[-1]) + 1)
    new_vn = '.'.join(parts)
    
    text = text.replace(f'versionCode = {old_vc}', f'versionCode = {new_vc}')
    text = text.replace(f'versionName = "{old_vn}"', f'versionName = "{new_vn}"')
    
    with open('app/build.gradle.kts', 'w') as f:
        f.write(text)
    print(f"Bumped to {new_vc} / {new_vn}")
else:
    print("Could not find version info")
