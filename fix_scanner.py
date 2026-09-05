import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

# Replace the ally role assignment block
old_assign = """            // ASIGNACIÓN INTELIGENTE DE ALIADOS:
            // Cada ranura aliada se asigna a su carril correspondiente según OCR explícito, Smite o rol primario del campeón
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                val explicitRole = allySlotRoles[i]
                    ?: (if (slotHasSmite[i]) LaneRole.JUNGLE else null)
                    ?: champ.primaryRole

                if (!alliesByRole.containsKey(explicitRole)) {
                    alliesByRole[explicitRole] = champ
                }
            }

            // Asignar los campeones aliados restantes que hayan tenido colisión
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                if (alliesByRole.containsValue(champ)) continue

                val freeRole = champ.secondaryRoles.firstOrNull { !alliesByRole.containsKey(it) }
                    ?: standardOrder.firstOrNull { !alliesByRole.containsKey(it) }

                if (freeRole != null) {
                    alliesByRole[freeRole] = champ
                }
            }"""

new_assign = """            // ASIGNACIÓN INTELIGENTE DE ALIADOS:
            // Simplificado para mayor resiliencia ante pantallas de diferentes proporciones.
            val detectedAllyChamps = allySlots.filterNotNull().distinctBy { it.id }
            val availableAllyRoles = standardOrder.toMutableList()

            // 1. Asignar por rol primario explícito
            for (champ in detectedAllyChamps) {
                if (availableAllyRoles.contains(champ.primaryRole) && !alliesByRole.containsValue(champ)) {
                    val roleMatches = detectedAllyChamps.count { it.primaryRole == champ.primaryRole }
                    if (roleMatches == 1) {
                        alliesByRole[champ.primaryRole] = champ
                        availableAllyRoles.remove(champ.primaryRole)
                    }
                }
            }

            // 2. Asignar colisiones o roles secundarios
            for (champ in detectedAllyChamps) {
                if (alliesByRole.containsValue(champ)) continue
                val role = champ.primaryRole.takeIf { availableAllyRoles.contains(it) }
                    ?: champ.secondaryRoles.firstOrNull { availableAllyRoles.contains(it) }
                    ?: availableAllyRoles.firstOrNull()

                if (role != null) {
                    alliesByRole[role] = champ
                    availableAllyRoles.remove(role)
                }
            }"""

text = text.replace(old_assign, new_assign)

# Also fix the enemy assignment if we can improve it (it's already using a similar logic)
with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)
