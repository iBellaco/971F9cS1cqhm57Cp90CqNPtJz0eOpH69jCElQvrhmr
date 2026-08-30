import os
import re

emoji_pattern = re.compile(r'[\U00010000-\U0010ffff]')
for root, dirs, files in os.walk('app/src/main/java/com/example'):
    for file in files:
        if file.endswith('.kt'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                content = f.read()
                if emoji_pattern.search(content):
                    print(f"File contains emoji: {filepath}")

