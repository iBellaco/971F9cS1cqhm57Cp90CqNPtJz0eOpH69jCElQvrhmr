import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target1 = """                    // A) Rol explícito
                    val role = DraftValidationLayer.parseRoleFromText(line)
                    if (role != null) {
                        slot.explicitRole = role
                        allySlotRolesCache[i] = role
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Rol explícito: ${role.shortName}")

                        // Detección precisa de slot del usuario por palabras clave del jugador
                        val containsUserClues = lines.any { l ->
                            val low = l.lowercase(Locale.ROOT)
                            low.contains("diego") || low.contains("porcentaje") || low.contains("victoria") || low.contains("tasa")
                        }
                        if (containsUserClues) {
                            userDetectedLane = role
                            AppLogger.d(TAG, "Slot del usuario confirmado en $i -> ${role.shortName}")
                        }
                    }"""

replacement1 = """                    // A) Rol explícito
                    val role = DraftValidationLayer.parseRoleFromText(line)
                    if (role != null) {
                        slot.explicitRole = role
                        allySlotRolesCache[i] = role
                        // En Wild Rift, solo el jugador local tiene su carril escrito explícitamente en el HUD
                        userDetectedLane = role
                        AppLogger.d(TAG, "OCR Aliado Slot $i -> Rol explícito: ${role.shortName} -> User Detected Lane!")
                    }"""

content = content.replace(target1, replacement1)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
