with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

import re
# The issue is literally newlines inside the `tr("")` block.
# I will just replace the text with `text = tr("PermisoSuperposicionTexto")` and map it in the translator.
content = re.sub(r'text\s*=\s*tr\("1\.\s*Toca\s*\'Conceder\s*Permiso\'.*?\),', 
                 'text = tr("PermisoSuperposicionTexto"),', content, flags=re.DOTALL)
content = re.sub(r'text\s*=\s*"Para\s*que\s*el\s*asistente.*?\(Superposición\)\.",', 
                 'text = tr("Para que el asistente inteligente funcione en segundo plano sobre Wild Rift, Android requiere habilitar \'Aparecer encima\' (Superposición)."),', content, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
