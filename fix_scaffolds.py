import re
import glob

files = glob.glob('app/src/main/java/com/example/**/*.kt', recursive=True)

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Find duplicate consecutive containerColors
    pattern = r'(containerColor = androidx\.compose\.ui\.graphics\.Color\.Transparent,\s*){2,}'
    if re.search(pattern, content):
        content = re.sub(pattern, r'containerColor = androidx.compose.ui.graphics.Color.Transparent,\n        ', content)
        with open(file, 'w', encoding='utf-8') as f:
            f.write(content)

