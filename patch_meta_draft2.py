import re

file_path = "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Let's remove from "Spacer(modifier = Modifier.height(12.dp))" before "Fuentes Oficiales y del Meta" up to the end of the view (which is right before Spacer(modifier = Modifier.height(24.dp)) )
# Specifically, we want to remove this block cleanly.

pattern = re.compile(r'        Spacer\(modifier = Modifier\.height\(12\.dp\)\)\n        Text\(\n            text = tr\("Fuentes Oficiales y del Meta"\).*?\{ source ->\n.*?\}\n            \}\n        \}\n', re.DOTALL)
content = pattern.sub("", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("MetaAndDraftScreen meta sources removed correctly!")
