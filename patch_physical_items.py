import re

file_path = 'app/src/main/java/com/example/data/WildRiftItemsData.kt'
with open(file_path, 'r') as f:
    content = f.read()

def remove_item(id_name, text):
    # Match WildRiftItem( ... id = "id_name" ... )
    # This uses a non-greedy match that looks for the start of WildRiftItem and stops at the end of its block
    # We will just split the file or use a robust regex.
    pattern = r'\s*WildRiftItem\(\s*id = "' + id_name + r'".*?iconUrl = "[^"]*"\s*\),'
    return re.sub(pattern, '', text, flags=re.DOTALL)

content = remove_item("immortal_shieldbow", content)
content = remove_item("stormrazor", content)
content = remove_item("rapid_firecannon", content)
content = remove_item("statikk_shiv", content)

content = content.replace('name = "Divino Divino"', 'name = "Cercenador Divino"')

with open(file_path, 'w') as f:
    f.write(content)

