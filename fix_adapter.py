with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "r") as f:
    lines = f.readlines()

new_lines = []
skip = False
for i, line in enumerate(lines):
    if line.strip() == 'spellsIcons = opt3SpellsIcons':
        if i + 1 < len(lines) and '        ). Incorpora penetración porcentual' in lines[i+1]:
            # This is the first one, keep it
            new_lines.append(line)
        elif i + 1 < len(lines) and '        // =========================================================================' in lines[i+1]:
            # This is the second one, skip it because we will skip until here
            pass
        else:
            new_lines.append(line)
    elif '        ). Incorpora penetración porcentual' in line:
        skip = True
        new_lines.append("        )\n")
    elif skip and line.strip() == 'spellsIcons = opt3SpellsIcons':
        # we reached the end of the bad block
        pass
    elif skip and line.strip() == ')':
        skip = False
    elif not skip:
        new_lines.append(line)

with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "w") as f:
    f.writelines(new_lines)
print("Done")
