import sys

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

old_code = """            } else {
                val fallback = availableRoles.firstOrNull() ?: continue
                finalMap[fallback] = champ
                confidences[fallback] = 60
                availableRoles.remove(fallback)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = fallback
            }
        }

        return ResolvedTeam(finalMap, confidences)
    }"""

new_code = """            } else {
                val fallback = availableRoles.firstOrNull() ?: continue
                finalMap[fallback] = champ
                confidences[fallback] = 60
                availableRoles.remove(fallback)
                assignedChampionIds.add(champ.id)
                slot.assignedRole = fallback
            }
        }

        // 6. OPTIMIZACIÓN POST-ASIGNACIÓN (Maximizar afinidad de roles)
        var changed = true
        while (changed) {
            changed = false
            val assignedRoles = finalMap.keys.toList()
            for (i in 0 until assignedRoles.size) {
                for (j in i + 1 until assignedRoles.size) {
                    val roleA = assignedRoles[i]
                    val roleB = assignedRoles[j]
                    val champA = finalMap[roleA]!!
                    val champB = finalMap[roleB]!!
                    
                    val scoreA = if (champA.primaryRole == roleA) 2 else if (champA.secondaryRoles.contains(roleA)) 1 else 0
                    val scoreB = if (champB.primaryRole == roleB) 2 else if (champB.secondaryRoles.contains(roleB)) 1 else 0
                    val currentTotal = scoreA + scoreB
                    
                    val swappedScoreA = if (champA.primaryRole == roleB) 2 else if (champA.secondaryRoles.contains(roleB)) 1 else 0
                    val swappedScoreB = if (champB.primaryRole == roleA) 2 else if (champB.secondaryRoles.contains(roleA)) 1 else 0
                    val swappedTotal = swappedScoreA + swappedScoreB
                    
                    if (swappedTotal > currentTotal) {
                        finalMap[roleA] = champB
                        finalMap[roleB] = champA
                        confidences[roleA] = if (swappedScoreB == 2) 95 else 80
                        confidences[roleB] = if (swappedScoreA == 2) 95 else 80
                        validSlots.find { it.champion?.id == champA.id }?.assignedRole = roleB
                        validSlots.find { it.champion?.id == champB.id }?.assignedRole = roleA
                        changed = true
                        auditList.add("Heurística: Intercambio de $roleA (${champB.name}) y $roleB (${champA.name}) para optimizar afinidad.")
                    }
                }
            }
        }

        return ResolvedTeam(finalMap, confidences)
    }"""

content = content.replace(old_code, new_code)

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
    f.write(content)

