import re

content = open('app/build.gradle.kts', 'r').read()

vc_match = re.search(r'versionCode = (\d+)', content)
vn_match = re.search(r'versionName = "([^"]+)"', content)

if vc_match and vn_match:
    vc = int(vc_match.group(1))
    vn = vn_match.group(1)
    
    parts = vn.split('.')
    if len(parts) >= 4:
        parts[-1] = str(int(parts[-1]) + 1)
        new_vn = '.'.join(parts)
    else:
        new_vn = vn + '.1'
        
    new_vc = vc + 1
    
    content = content.replace(f'versionCode = {vc}', f'versionCode = {new_vc}')
    content = content.replace(f'versionName = "{vn}"', f'versionName = "{new_vn}"')
    
    open('app/build.gradle.kts', 'w').write(content)
    print(f"Updated version to {new_vn} ({new_vc})")
