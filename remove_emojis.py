import os
import re

# Regex to match emojis (this covers most common emojis)
emoji_pattern = re.compile(
    u"(\ud83d[\ude00-\ude4f])|"  # emoticons
    u"(\ud83d[\ude80-\udef3])|"  # transport & map symbols
    u"(\ud83c[\udf00-\udfff])|"  # miscellaneous symbols & pictographs
    u"(\ud83d[\udc00-\uddff])|"  # additional transport and others
    u"([\u2600-\u26ff])|"        # miscellaneous symbols
    u"([\u2700-\u27bf])|"        # dingbats
    u"([\U00010000-\U0010ffff])" # other supplementary planes
)

for root, dirs, files in os.walk('app/src/main'):
    for file in files:
        if file.endswith('.kt') or file.endswith('.xml'):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                content = f.read()
            
            cleaned_content = emoji_pattern.sub('', content)
            if cleaned_content != content:
                with open(filepath, 'w', encoding='utf-8') as f:
                    f.write(cleaned_content)
                print(f"Removed emojis from: {filepath}")

