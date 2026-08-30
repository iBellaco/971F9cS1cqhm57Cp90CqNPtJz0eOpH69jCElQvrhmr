import re

with open('app/build.gradle.kts', 'r') as f:
    code = f.read()

def repl_code(m):
    return f'versionCode = {int(m.group(1)) + 1}'

def repl_name(m):
    parts = m.group(1).split('.')
    parts[-1] = str(int(parts[-1]) + 1)
    return f'versionName = "{".".join(parts)}"'

code = re.sub(r'versionCode\s*=\s*(\d+)', repl_code, code)
code = re.sub(r'versionName\s*=\s*"([^"]+)"', repl_name, code)

with open('app/build.gradle.kts', 'w') as f:
    f.write(code)

print("Version updated!")
