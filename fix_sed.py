with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if "text = WildRiftRepository.CURRENT_PATCH_VERSION," in line:
        # found the location
        # line + 5 is where the braces should be
        lines[i+5] = "                            }\n"
        lines[i+6] = "                        }\n"
        break

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.writelines(lines)
