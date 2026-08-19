import re

file_path = "app/build.gradle.kts"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will find versionCode 13 and versionName "1.13" or similar, and increment them
# If it's still 1 and 1.0, I will increment to 14 and 1.14 (based on previous session's progress)

# Let's extract them
vcode_match = re.search(r"versionCode\s*=\s*(\d+)", content)
vname_match = re.search(r'versionName\s*=\s*"([^"]+)"', content)

if vcode_match:
    old_vc = vcode_match.group(1)
    new_vc = str(int(old_vc) + 1)
    content = re.sub(r"versionCode\s*=\s*\d+", f"versionCode = {new_vc}", content)
    
if vname_match:
    old_vn = vname_match.group(1)
    if old_vn.startswith("1."):
        minor = int(old_vn.split(".")[1])
        new_vn = f"1.{minor + 1}"
    else:
        new_vn = "1.14" # fallback
    content = re.sub(r'versionName\s*=\s*"[^"]+"', f'versionName = "{new_vn}"', content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print(f"Patched build.gradle.kts to version {new_vc} / {new_vn}")
