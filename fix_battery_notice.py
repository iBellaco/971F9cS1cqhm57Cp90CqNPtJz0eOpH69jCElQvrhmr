with open("app/src/main/java/com/example/ui/components/BatteryAndOverlayNoticeCard.kt", "r") as f:
    content = f.read()

content = content.replace('"Configuración para Segundo Plano"', 'tr("Configuración para Segundo Plano")')
content = content.replace('"Recomendaciones para funcionamiento óptimo sobre Wild Rift"', 'tr("Recomendaciones para funcionamiento óptimo sobre Wild Rift")')
content = content.replace('"Para que el asistente flote en tiempo real sobre tu partida de Wild Rift sin que Android cierre el proceso por consumo de memoria:"', 'tr("Para que el asistente flote en tiempo real sobre tu partida de Wild Rift sin que Android cierre el proceso por consumo de memoria:")')
content = content.replace('"1. Permiso de Superposición"', 'tr("1. Permiso de Superposición")')
content = content.replace('"✔ Concedido (Ventana flotante habilitada)"', 'tr("✔ Concedido (Ventana flotante habilitada)")')
content = content.replace('"✘ Pendiente: Toca para autorizar"', 'tr("✘ Pendiente: Toca para autorizar")')
content = content.replace('"2. Desactivar Ahorro de Batería"', 'tr("2. Desactivar Ahorro de Batería")')
content = content.replace('"✔ Sin restricciones (No se cerrará en segundo plano)"', 'tr("✔ Sin restricciones (No se cerrará en segundo plano)")')
content = content.replace('"✘ Optimizado: Toca para quitar restricción"', 'tr("✘ Optimizado: Toca para quitar restricción")')
content = content.replace('if (isServiceRunning) "Detener Flotante" else "Lanzar Flotante en Juego"', 'if (isServiceRunning) tr("Detener Flotante") else tr("Lanzar Flotante en Juego")')
content = content.replace('"Conceder Permiso"', 'tr("Conceder Permiso")')

with open("app/src/main/java/com/example/ui/components/BatteryAndOverlayNoticeCard.kt", "w") as f:
    f.write(content)
