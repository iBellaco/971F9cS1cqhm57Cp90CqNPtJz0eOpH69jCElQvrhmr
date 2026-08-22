import re

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'r') as f:
    content = f.read()

content = content.replace('Wild Rift Coach v2.12', 'Wild Rift Coach v2.13')
content = content.replace('Renombre oficial, contadores dinámicos en catálogos y evaluación táctica de tu pick en tiempo real.', 'Items situacionales dinámicos en build con alternativas, razones y counter-matchups. Expansión a Build Completa de 6 objetos.')

with open('app/src/main/java/com/example/ui/components/WelcomePatchDialog.kt', 'w') as f:
    f.write(content)
