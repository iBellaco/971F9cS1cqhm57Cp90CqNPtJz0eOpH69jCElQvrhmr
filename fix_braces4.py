with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

content = content.replace("                Spacer(modifier = Modifier.height(22.dp))\n\n\n\n@Composable\nprivate fun InfoSectionHeader(", 
"                Spacer(modifier = Modifier.height(22.dp))\n            }\n        }\n    }\n}\n\n@Composable\nprivate fun InfoSectionHeader(")

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
