import re
with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

content = re.sub(r'\}\s*@Composable\s*private fun InfoSectionHeader', 
r'            }\n        }\n    }\n}\n\n@Composable\nprivate fun InfoSectionHeader', content)

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
