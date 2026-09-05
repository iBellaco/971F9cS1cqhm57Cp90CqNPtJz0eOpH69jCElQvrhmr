import re

filepath = 'app/src/main/java/com/example/util/ImageHashMatcher.kt'
with open(filepath, 'r') as f:
    text = f.read()

target = """        var minDistance = 15 // Menos tolerancia para iconos pequeños"""

replacement = """        var minDistance = 25 // Aumentamos la tolerancia para iconos porque su tamaño es más variable en el recorte"""

text = text.replace(target, replacement)
with open(filepath, 'w') as f:
    f.write(text)

