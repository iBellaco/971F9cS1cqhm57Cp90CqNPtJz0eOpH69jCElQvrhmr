import re

with open('.github/workflows/build-apk.yml', 'r') as f:
    content = f.read()

# Let's remove the whole cleanup step
cleanup_pattern = r"      - name: Cleanup old releases.*?continue-on-error: true\n"
content = re.sub(cleanup_pattern, "", content, flags=re.DOTALL)

# Let's remove continue-on-error: true from create release step
create_pattern = r"(\s+- name: Create or Update GitHub Release.*?)continue-on-error: true\n"
content = re.sub(create_pattern, r"\1", content, flags=re.DOTALL)

with open('.github/workflows/build-apk.yml', 'w') as f:
    f.write(content)
