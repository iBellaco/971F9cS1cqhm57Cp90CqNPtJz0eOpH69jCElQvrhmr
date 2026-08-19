with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re

# I broke the text formatting by removing the newlines but putting them literal. Let's fix it.
content = content.replace('text = tr("1. Toca \'Conceder Permiso\'.2. Activa el interruptor para Wild Rift Drafting.3. Regresa a la app y pulsa ACTIVAR."),', 
                          'text = tr("1. Toca \'Conceder Permiso\'.\\n2. Activa el interruptor para Wild Rift Drafting.\\n3. Regresa a la app y pulsa ACTIVAR."),')

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
