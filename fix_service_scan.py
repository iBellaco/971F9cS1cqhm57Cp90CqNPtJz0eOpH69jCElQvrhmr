import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = """                        // 1. Asignación directa y de alta precisión por rol/posición
                        defaultRoles.forEachIndexed { idx, role ->
                            val scannedAlly = result.alliesByRole[role]
                            if (scannedAlly != null) {
                                assignAllySlot(idx, scannedAlly)
                            }
                            val scannedEnemy = result.enemiesByRole[role]
                            if (scannedEnemy != null) {
                                assignEnemySlot(idx, scannedEnemy)
                            }
                        }"""

replacement = """                        // 1. Asignación directa y de alta precisión por rol/posición
                        defaultRoles.forEachIndexed { idx, role ->
                            val scannedAlly = result.alliesByRole[role]
                            if (scannedAlly != null) {
                                assignAllySlot(idx, scannedAlly)
                            } else if (result.isSuccessful && !result.allies.contains(allies[idx])) {
                                // Si el escáner fue exitoso pero no vio al campeón que estaba aquí, y ese campeón tampoco se movió a otro lado, podríamos considerar limpiar, pero por seguridad conservamos el estado manual.
                            }
                            val scannedEnemy = result.enemiesByRole[role]
                            if (scannedEnemy != null) {
                                assignEnemySlot(idx, scannedEnemy)
                            }
                        }"""

text = text.replace(target, replacement)
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

