import re

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

vc_match = re.search(r"versionCode\s*=\s*(\d+)", content)
vn_match = re.search(r'versionName\s*=\s*"([^"]+)"', content)

if vc_match and vn_match:
    vc = int(vc_match.group(1)) + 1
    vn = vn_match.group(1)
    # increment patch version
    parts = vn.split(".")
    parts[-1] = str(int(parts[-1]) + 1)
    new_vn = ".".join(parts)
    
    content = re.sub(r"versionCode\s*=\s*\d+", f"versionCode = {vc}", content)
    content = re.sub(r'versionName\s*=\s*"[^"]+"', f'versionName = "{new_vn}"', content)
    
    with open("app/build.gradle.kts", "w") as f:
        f.write(content)
    print(f"Updated version to {new_vn} ({vc})")

