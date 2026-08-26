import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    data = f.read()

# We need to find all occurrences of passive = "..." and passiveEn = "..." and replace actual newlines within them with \n
# Since there can be multiple newlines, we can use a regex that matches the whole string.

def replacer(match):
    # match.group(0) is the entire `passive = "..."`
    # We replace literal newlines with `\n`
    # But wait! If the string contains \n, how do we match it with regex?
    # We can match `passive = "` then anything until `",\n` or `"\n`
    pass

# A simpler way: parse the file line by line. If we are inside a passive/passiveEn definition (i.e. started with passive = "), 
# and it hasn't ended with ", we replace the newline with \n and concatenate.

lines = data.split('\n')
new_lines = []
inside_string = False
current_string = ""

for line in lines:
    if not inside_string:
        if re.search(r'(passive|passiveEn)\s*=\s*"', line) and not line.endswith('",') and not line.endswith('")') and not line.endswith('"'):
            # It starts a multiline string
            inside_string = True
            current_string = line
        else:
            new_lines.append(line)
    else:
        # We are inside a multiline string
        current_string += "\\n" + line
        if line.endswith('",') or line.endswith('")') or line.endswith('"'):
            inside_string = False
            new_lines.append(current_string)
            current_string = ""

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write('\n'.join(new_lines))
print("fixed all newlines")
