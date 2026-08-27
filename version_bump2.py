import re
with open("app/build.gradle.kts", "r") as f:
    c = f.read()

v_code = int(re.search(r"versionCode = (\d+)", c).group(1))
v_name = re.search(r'versionName = "(.*?)"', c).group(1)
parts = v_name.split(".")
parts[-1] = str(int(parts[-1]) + 1)
v_name_new = ".".join(parts)

c = re.sub(r"versionCode = \d+", f"versionCode = {v_code + 1}", c)
c = re.sub(r'versionName = ".*?"', f'versionName = "{v_name_new}"', c)

with open("app/build.gradle.kts", "w") as f:
    f.write(c)

