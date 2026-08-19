with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re

# Need to fix:
# 380: text = tr("1. Toca 'Conceder Permiso'.\n2. Activa el interruptor para Wild Rift Drafting.\n3. Regresa a la app y pulsa ACTIVAR."),
content = re.sub(r'text\s*=\s*tr\("1\.\s*Toca[^\)]*\),', 
                 'text = tr("1. Toca \'Conceder Permiso\'.\\n2. Activa el interruptor para Wild Rift Drafting.\\n3. Regresa a la app y pulsa ACTIVAR."),', content)

# I can just replace the whole block to be safe
content = re.sub(r'Text\(\s*text\s*=\s*tr\("1\.\s*Toca\s*\'Conceder\s*Permiso\'\.\\n2\.\s*Activa[^,]+,\s*color\s*=\s*HextechCyan,\s*fontSize\s*=\s*12\.sp\s*\)', 
                 '''Text(
                            text = tr("1. Toca 'Conceder Permiso'.\\n2. Activa el interruptor para Wild Rift Drafting.\\n3. Regresa a la app y pulsa ACTIVAR."),
                            color = HextechCyan,
                            fontSize = 12.sp
                        )''', content, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
