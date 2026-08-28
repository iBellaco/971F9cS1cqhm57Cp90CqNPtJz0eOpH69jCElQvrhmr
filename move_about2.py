import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

# Match the about button block
about_btn_pattern = re.compile(r'androidx\.compose\.material3\.OutlinedButton\(\s*onClick = onNavigateToInfo,[\s\S]*?text = tr\("Acerca De"\),[\s\S]*?\}\s*Spacer\(modifier = Modifier\.height\(12\.dp\)\)\s*')

about_match = about_btn_pattern.search(code)
if about_match:
    about_text = about_match.group(0)
    code = code.replace(about_text, "")
    
    # insert below derechos de autor
    derechos_pattern = re.compile(r'textAlign = TextAlign\.Center\s*\)\s*\}\s*')
    derechos_match = derechos_pattern.search(code)
    
    if derechos_match:
        insert_pos = derechos_match.end()
        code = code[:insert_pos] + "                Spacer(modifier = Modifier.height(16.dp))\n                " + about_text + code[insert_pos:]
        
with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

