with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "r") as f:
    lines = f.readlines()

new_lines = []
for line in lines:
    if line.strip() == 'spellsIcons = opt3SpellsIcons':
        if len(new_lines) > 0 and new_lines[-1].strip() == 'spellsIcons = opt3SpellsIcons':
            continue
        if len(new_lines) > 0 and new_lines[-1].strip() == ')':
            continue
    new_lines.append(line)

with open("app/src/main/java/com/example/util/ChampionRoleAdapter.kt", "w") as f:
    f.writelines(new_lines)
print("Done")
