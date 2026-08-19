with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re
content = re.sub(r'label\s*=\s*"Línea Main"', 'label = tr("Línea Main")', content)
content = re.sub(r'label\s*=\s*"Segunda Línea"', 'label = tr("Segunda Línea")', content)
content = re.sub(r'label\s*=\s*"Rol Autofill"', 'label = tr("Rol Autofill")', content)

content = re.sub(r'text\s*=\s*"1\.\s*Toca\s*\'Conceder\s*Permiso\'\.\\n2\.\s*Activa\s*el\s*interruptor\s*para\s*Wild\s*Rift\s*Drafting\.\\n3\.\s*Regresa\s*a\s*la\s*app\s*y\s*pulsa\s*ACTIVAR\."', 
                 'text = tr("1. Toca \'Conceder Permiso\'.\\n2. Activa el interruptor para Wild Rift Drafting.\\n3. Regresa a la app y pulsa ACTIVAR.")', content)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
