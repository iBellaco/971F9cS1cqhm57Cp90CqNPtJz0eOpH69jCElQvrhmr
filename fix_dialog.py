with open("app/src/main/java/com/example/ui/components/AppUpdateDialog.kt", "r") as f:
    content = f.read()

content = content.replace('text = tr("Estás utilizando la versión más reciente (v${BuildConfig.VERSION_NAME})."),',
                          'text = tr("Estás utilizando la versión más reciente") + " (v${BuildConfig.VERSION_NAME}).",')

content = content.replace('text = updateInfo.releaseNotes.ifBlank {\n                                "• Actualización del nuevo icono emblemático.\\n• Compatibilidad de instalación sin conflicto de versiones.\\n• Mejoras generales de estabilidad y asistente en vivo."\n                            }',
                          'text = updateInfo.releaseNotes.ifBlank { tr("release_notes_fallback") }')

with open("app/src/main/java/com/example/ui/components/AppUpdateDialog.kt", "w") as f:
    f.write(content)
