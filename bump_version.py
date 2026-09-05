import re

with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

vc_match = re.search(r'versionCode = (\d+)', text)
vn_match = re.search(r'versionName = "(.*?)"', text)

if vc_match and vn_match:
    vc = int(vc_match.group(1))
    vn = vn_match.group(1)
    
    parts = vn.split('.')
    parts[-1] = str(int(parts[-1]) + 1)
    new_vn = '.'.join(parts)
    new_vc = vc + 1
    
    text = text.replace(f'versionCode = {vc}', f'versionCode = {new_vc}')
    text = text.replace(f'versionName = "{vn}"', f'versionName = "{new_vn}"')
    
    with open('app/build.gradle.kts', 'w') as f:
        f.write(text)
    print(f"Bumped to {new_vn}")
