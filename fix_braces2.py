with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

import re
content = re.sub(r'(\s*Spacer\(modifier = Modifier\.height\(22\.dp\)\)\s*)(@Composable\s*private fun InfoSectionHeader)', r'\1\n            }\n        }\n    }\n}\n\n\2', content)

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
