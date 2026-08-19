with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re

# Just manually reconstruct that small block
content = re.sub(r'Text\(\s*text\s*=\s*tr\("1\.\s*Toca\s*\'Conceder\s*Permiso\'[^\{]*?\}\s*\},',
'''Text(
                            text = tr("1. Toca 'Conceder Permiso'.\\n2. Activa el interruptor para Wild Rift Drafting.\\n3. Regresa a la app y pulsa ACTIVAR."),
                            color = HextechCyan,
                            fontSize = 12.sp
                        )
                    }
                },''', content, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
