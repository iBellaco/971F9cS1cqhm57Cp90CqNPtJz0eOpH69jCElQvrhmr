with open("app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt") as f:
    code = f.read()
if "val buildList = if (roleProfile.build8Items.isNotEmpty())" in code:
    print("Found buildList logic")
