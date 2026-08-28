with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

# find button
btn_start_str = "androidx.compose.material3.OutlinedButton(\n                    onClick = onNavigateToInfo"
btn_start = code.find(btn_start_str)

if btn_start != -1:
    btn_end_str = "                    )\n                }\n                Spacer(modifier = Modifier.height(12.dp))\n"
    btn_end = code.find(btn_end_str, btn_start)
    if btn_end != -1:
        btn_end += len(btn_end_str)
        
        btn_code = code[btn_start:btn_end]
        code = code[:btn_start] + code[btn_end:]
        
        # insert below derechos de autor
        derechos_end_str = "                        textAlign = TextAlign.Center\n                    )\n                }\n"
        derechos_end = code.find(derechos_end_str)
        if derechos_end != -1:
            derechos_end += len(derechos_end_str)
            
            code = code[:derechos_end] + "                Spacer(modifier = Modifier.height(12.dp))\n" + btn_code + code[derechos_end:]

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

