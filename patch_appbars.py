import re
import glob

files = glob.glob('app/src/main/java/com/example/ui/screens/*.kt')

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Replace containerColor = HextechDarkBg inside TopAppBarDefaults with HextechDarkBg.copy(alpha = 0.3f) or Transparent
    content = re.sub(
        r'containerColor = HextechDarkBg\s*\)', 
        r'containerColor = androidx.compose.ui.graphics.Color.Transparent)', 
        content
    )

    with open(file, 'w', encoding='utf-8') as f:
        f.write(content)

