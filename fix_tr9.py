with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re
content = re.sub(r'text\s*=\s*tr\("1\.\s*Toca\s*\'Conceder\s*Permiso\'[^"]*"\)', 
                 'text = tr("PermisoSuperposicionTexto")', content)

# just to be safe
content = content.replace('''text = tr("1. Toca 'Conceder Permiso'.2. Activa el interruptor para Wild Rift Drafting.3. Regresa a la app y pulsa ACTIVAR.")''', 
                          '''text = tr("PermisoSuperposicionTexto")''')

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
