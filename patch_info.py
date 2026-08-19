import re

file_path = "app/src/main/java/com/example/ui/screens/InfoScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace "Meta de Wild Rift" in WildRiftVersionBanner.kt
# Oh, that's a different file.

def replace_hardcoded(text):
    text = text.replace('text = "Asistente Táctico Wild Rift"', 'text = tr("Asistente Táctico Wild Rift")')
    text = text.replace('text = "Guía en Tiempo Real para Selección de Campeones"', 'text = tr("Guía en Tiempo Real para Selección de Campeones")')
    text = text.replace('title = "1. Acerca del Asistente"', 'title = tr("1. Acerca del Asistente")')
    text = text.replace('text = "Edición Móvil"', 'text = tr("Edición Móvil")')
    text = text.replace('"• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles).\\n" +', 'tr("• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles).") + "\\n" +')
    text = text.replace('"• Parche del Meta: ${WildRiftRepository.CURRENT_PATCH_VERSION} sincronizado con fuentes de balance.\\n" +', 'tr("• Parche del juego: ") + "${WildRiftRepository.CURRENT_PATCH_VERSION} " + tr("sincronizado con fuentes de balance.") + "\\n" +')
    text = text.replace('"• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias.\\n" +', 'tr("• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias.") + "\\n" +')
    text = text.replace('"• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección."', 'tr("• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección.")')
    text = text.replace('title = "3. Interfaz del Asistente (Overlay HUD)"', 'title = tr("3. Interfaz del Asistente (Overlay HUD)")')
    return text

content = replace_hardcoded(content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
