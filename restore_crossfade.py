import os
import re

ui_dir = 'app/src/main/java/com/example/ui'

pattern = re.compile(r'(\.data\(.*?\))')

for root, _, files in os.walk(ui_dir):
    for file in files:
        if file.endswith('.kt'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r') as f:
                content = f.read()
            
            # Check if there are ImageRequest.Builder calls
            if 'ImageRequest.Builder' in content:
                # Add crossfade and placeholder right after .data(...)
                # Only if not already present
                if '.crossfade' not in content:
                    content = pattern.sub(r'\1\n.crossfade(true)\n.placeholder(com.example.R.drawable.ic_placeholder_loading)', content)
                    with open(filepath, 'w') as f:
                        f.write(content)

