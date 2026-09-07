import sys
with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    lines = f.readlines()

for i, line in enumerate(lines[300:380]):
    print(f"{i+300+1}: {line}", end="")
