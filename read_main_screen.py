import sys
with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    lines = f.readlines()

for i, line in enumerate(lines[220:300]):
    print(f"{i+220+1}: {line}", end="")
