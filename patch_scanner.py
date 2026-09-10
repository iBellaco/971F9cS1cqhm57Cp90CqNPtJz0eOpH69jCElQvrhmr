import re

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

# Add debug map
content = content.replace("val showCalibrationBoxes = kotlinx.coroutines.flow.MutableStateFlow(false)",
"val showCalibrationBoxes = kotlinx.coroutines.flow.MutableStateFlow(false)\n    val debugVisualMatches = kotlinx.coroutines.flow.MutableStateFlow<Map<String, String>>(emptyMap())")

# Update candidate logic
old_logic = """        if (context != null) {
            val candidateSlots = (allySlots + enemySlots).filter { it.champion == null && !it.isLikelyUnpicked }"""

new_logic = """        if (context != null) {
            val totalPickedSoFar = allySlots.count { it.champion != null } + enemySlots.count { it.champion != null }
            val isFinalTenthPick = totalPickedSoFar == 9
            val isCalibrating = showCalibrationBoxes.value

            val candidateSlots = if (isCalibrating) {
                // Durante la calibración escaneamos visualmente todo para dar feedback en vivo
                allySlots + enemySlots
            } else if (isFinalTenthPick) {
                // Flujo normal: solo escaneamos por imagen si es exactamente el 10º pick
                (allySlots + enemySlots).filter { it.champion == null && !it.isLikelyUnpicked }
            } else {
                emptyList()
            }
            
            val newDebugMatches = mutableMapOf<String, String>()"""

content = content.replace(old_logic, new_logic)

# Replace the inner threshold and match part
old_match = """                            val totalPickedSoFar = allySlots.count { it.champion != null } + enemySlots.count { it.champion != null }
                            val threshold = if (totalPickedSoFar == 9) 0.45f else 0.52f
                            val match = ChampionVisualMatcher.matchChampion(
                                context = context,
                                avatarCrop = avatarCrop,
                                candidates = allChamps,
                                excludedChampionIds = alreadyPickedIds,
                                minConfidenceThreshold = threshold
                            )"""

new_match = """                            val threshold = if (isFinalTenthPick) 0.45f else 0.52f
                            val match = ChampionVisualMatcher.matchChampion(
                                context = context,
                                avatarCrop = avatarCrop,
                                candidates = allChamps,
                                excludedChampionIds = alreadyPickedIds,
                                minConfidenceThreshold = threshold
                            )"""
content = content.replace(old_match, new_match)

# Store the result in newDebugMatches
old_found = """                        if (bestMatchResult != null) {
                            val matchedChamp = bestMatchResult!!.champion
                            targetSlot.champion = matchedChamp"""

new_found = """                        if (bestMatchResult != null) {
                            val matchedChamp = bestMatchResult!!.champion
                            
                            val prefix = if (allySlots.contains(targetSlot)) "ally" else "enemy"
                            val sIdx = if (prefix == "ally") allySlots.indexOf(targetSlot) else enemySlots.indexOf(targetSlot)
                            newDebugMatches["${prefix}_${sIdx}"] = "${matchedChamp.name} (${(bestMatchResult!!.confidence*100).toInt()}%)"
                            
                            if (targetSlot.champion == null && (!isCalibrating || isFinalTenthPick)) {
                                targetSlot.champion = matchedChamp"""

content = content.replace(old_found, new_found)

# And close the brace for targetSlot.champion = matchedChamp
old_close = """                            targetSlot.confidence = (bestMatchResult!!.confidence * 100).toInt()
                            
                            isLastPickVisualRecognized = true
                            lastPickVisualChampion = matchedChamp
                        }"""
new_close = """                            targetSlot.confidence = (bestMatchResult!!.confidence * 100).toInt()
                            
                            isLastPickVisualRecognized = true
                            lastPickVisualChampion = matchedChamp
                            }
                        }"""
content = content.replace(old_close, new_close)

# End of visual block update debug map
old_end = """            if (candidateSlots.isNotEmpty()) {
                val alreadyPickedIds = (allySlots.mapNotNull { it.champion?.id } + enemySlots.mapNotNull { it.champion?.id }).toSet()"""
new_end = """            if (candidateSlots.isNotEmpty()) {
                val alreadyPickedIds = if (!isCalibrating) (allySlots.mapNotNull { it.champion?.id } + enemySlots.mapNotNull { it.champion?.id }).toSet() else emptySet()"""
content = content.replace(old_end, new_end)

# After the loop over candidateSlots ends
old_loop_end = """                    }
                }
            }
        }

        // PASO 4: AGREGAR HECHIZOS (SUMMONER SPELLS) DE ALIADOS Y REVISAR OCR DE NOMBRES"""
new_loop_end = """                    }
                }
                debugVisualMatches.value = newDebugMatches
            } else {
                debugVisualMatches.value = emptyMap()
            }
        }

        // PASO 4: AGREGAR HECHIZOS (SUMMONER SPELLS) DE ALIADOS Y REVISAR OCR DE NOMBRES"""
content = content.replace(old_loop_end, new_loop_end)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)

