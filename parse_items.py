import re
with open('app/src/main/java/com/example/data/WildRiftItemsData.kt') as f:
    code = f.read()

valid_items = []
for line in code.split('\n'):
    if 'name = "' in line:
        match = re.search(r'name = "(.*?)"', line)
        if match:
            valid_items.append(match.group(1))

print(len(valid_items))
with open('valid_items.txt', 'w') as f:
    f.write('\n'.join(valid_items))
