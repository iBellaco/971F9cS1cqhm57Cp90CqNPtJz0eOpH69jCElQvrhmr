import re
import glob

files = glob.glob('app/src/main/java/com/example/ui/screens/*.kt')

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()

    # If file contains Scaffold(, add containerColor if not already there
    if 'Scaffold(' in content and 'containerColor = Color.Transparent' not in content:
        # Be careful not to mess up formatting.
        content = re.sub(r'Scaffold\(', r'Scaffold(\n        containerColor = androidx.compose.ui.graphics.Color.Transparent,', content)
        with open(file, 'w', encoding='utf-8') as f:
            f.write(content)

