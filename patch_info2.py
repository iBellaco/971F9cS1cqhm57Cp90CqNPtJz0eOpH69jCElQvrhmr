import re

file_path = "app/src/main/java/com/example/ui/screens/InfoScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace hardcoded strings with tr() calls
content = content.replace('title = "1. Compatibilidad y Parche Oficial"', 'title = tr("1. Compatibilidad y Parche Oficial")')
content = content.replace('title = "2. Modo de Uso de la Aplicación"', 'title = tr("2. Modo de Uso de la Aplicación")')
content = content.replace('title = "4. Opciones de Administrador"', 'title = tr("4. Opciones de Administrador")')
content = content.replace('title = "Paso 1: Configura tus Líneas de Juego",', 'title = tr("Paso 1: Configura tus Líneas de Juego"),')
content = content.replace('description = "En la pantalla principal, selecciona tu \'Línea Main\', \'Segunda Línea\' y \'Rol Autofill\' tocando cada tarjeta."', 'description = tr("En la pantalla principal, selecciona tu \'Línea Main\', \'Segunda Línea\' y \'Rol Autofill\' tocando cada tarjeta.")')
content = content.replace('title = "Paso 2: Activa el Asistente Flotante",', 'title = tr("Paso 2: Activa el Asistente Flotante"),')
content = content.replace('description = "Pulsa el botón central \'ACTIVAR\'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida."', 'description = tr("Pulsa el botón central \'ACTIVAR\'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida.")')
content = content.replace('title = "Paso 3: Selección de Campeones (Champ Select)",', 'title = tr("Paso 3: Selección de Campeones (Champ Select)"),')
content = content.replace('description = "Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo."', 'description = tr("Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo.")')
content = content.replace('title = "Paso 4: Consulta de Builds y Runas",', 'title = tr("Paso 4: Consulta de Builds y Runas"),')
content = content.replace('description = "Revisa los consejos tácticos, orden de habilidades móviles (Pasiva, 1, 2, 3, Definitiva) y armado de objetos recomendado para tu línea."', 'description = tr("Revisa los consejos tácticos, orden de habilidades móviles (Pasiva, 1, 2, 3, Definitiva) y armado de objetos recomendado para tu línea.")')

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("InfoScreen patched again!")
