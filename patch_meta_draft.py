import re

file_path = "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pattern = re.compile(r'        Spacer\(modifier = Modifier.height\(12.dp\)\)\n        Text\(\n            text = tr\("Fuentes Oficiales y del Meta"\).*?Text\(tr\("Abrir"\), color = HextechDarkBg, fontSize = 11\.sp, fontWeight = FontWeight\.Bold\)\n                        \}\n                    \}\n                \}\n            \}\n        \}\n', re.DOTALL)
content = pattern.sub("", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("MetaAndDraftScreen meta sources removed!")
