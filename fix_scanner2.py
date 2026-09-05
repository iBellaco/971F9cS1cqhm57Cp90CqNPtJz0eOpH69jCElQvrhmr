import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

# Replace the allySlots logic
old_slots = """                            // Detección de Campeón Aliado por nombre explícito
                            if (allySlots[slotIdx] == null) {
                                val matchedDirect = matchChampions(lineText, allChamps)
                                val candidate = matchedDirect.firstOrNull() ?: run {
                                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                    words.firstNotNullOfOrNull { w ->
                                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                            matchChampions(w, allChamps).firstOrNull()
                                        } else null
                                    }
                                }
                                if (candidate != null) {
                                    allySlots[slotIdx] = candidate
                                    AppLogger.d(TAG, "Aliado detectado en slot $slotIdx: ${candidate.name}")
                                }
                            }"""

new_slots = """                            // Detección de Campeón Aliado por nombre explícito
                            val matchedDirect = matchChampions(lineText, allChamps)
                            val candidate = matchedDirect.firstOrNull() ?: run {
                                val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                                words.firstNotNullOfOrNull { w ->
                                    if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                                        matchChampions(w, allChamps).firstOrNull()
                                    } else null
                                }
                            }
                            if (candidate != null) {
                                // Mantenemos el array pero si está ocupado buscamos el siguiente libre
                                if (allySlots[slotIdx] == null) {
                                    allySlots[slotIdx] = candidate
                                } else if (allySlots.count { it?.id == candidate.id } == 0) {
                                    // Si ya existe alguien en el slot pero este es un campeón distinto, 
                                    // lo ponemos en el primer slot libre para no perderlo.
                                    val freeIdx = allySlots.indexOfFirst { it == null }
                                    if (freeIdx != -1) {
                                        allySlots[freeIdx] = candidate
                                    }
                                }
                                AppLogger.d(TAG, "Aliado detectado en Y:$yRatio -> ${candidate.name}")
                            }"""

text = text.replace(old_slots, new_slots)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)
