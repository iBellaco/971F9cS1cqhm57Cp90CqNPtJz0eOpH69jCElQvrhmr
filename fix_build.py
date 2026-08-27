import re

with open("app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt") as f:
    content = f.read()

# Make sure situational items show properly
if "val buildList =" in content:
    print("Found buildList logic in ChampionDetailSheet")

