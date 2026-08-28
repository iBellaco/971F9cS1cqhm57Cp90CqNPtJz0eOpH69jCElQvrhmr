with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    lines = f.readlines()

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    for line in lines:
        if line.strip() == "Defaults":
            continue
        f.write(line)
