with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

content = content.replace("}\n@Composable\nprivate fun InfoSectionHeader", "            }\n        }\n    }\n}\n\n@Composable\nprivate fun InfoSectionHeader")

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
