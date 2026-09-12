#!/bin/bash
sed -n '1,664p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt > temp.kt

cat << 'INNER_EOF' >> temp.kt
                var isWaitingPick = false
                var isUnpickedTextPresent = false
                var detectedEnemyChamp: Champion? = null

                for ((rawBlock, box) in enemySlotTexts[i].sortedBy { it.second?.top ?: 0 }) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }
                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        val low = line.lowercase(Locale.ROOT)
                        if (low.startsWith("jugador") || low.startsWith("player") || low.startsWith("jogador") || 
                            low.contains("preselecci") || low.contains("eligiendo") || 
                            low.contains("esperando") || low.contains("bloque") || low.contains("ayud")) {
                            isWaitingPick = true
                            isUnpickedTextPresent = true
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = false,
                                    slotIndex = i,
                                    tag = "JUGADOR/HOVER",
                                    color = android.graphics.Color.DKGRAY
                                )
                            )
                            continue
                        }

                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            detectedEnemyChamp = matched
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = false,
                                    slotIndex = i,
                                    tag = "RIVAL: ${matched.name}",
                                    color = android.graphics.Color.RED
                                )
                            )
                            AppLogger.d(TAG, "OCR Rival Slot $i -> Campeón 100%: ${matched.name}")
                            continue
                        }
                    }
                }

                if (isWaitingPick || isUnpickedTextPresent || (isWaitingPick && detectedEnemyChamp == null)) {
                    enemyOcrChampions[i] = null
                    enemySlots[i].champion = null
                    enemySlots[i].isLikelyUnpicked = true
                    enemySlotFilters[i].reset()
                } else if (detectedEnemyChamp != null) {
                    enemyOcrChampions[i] = detectedEnemyChamp
                    enemySlots[i].champion = detectedEnemyChamp
                    enemySlots[i].confidencePercent = 100
                    enemySlots[i].isLikelyUnpicked = false
                } else {
                    enemyOcrChampions[i] = null
                    enemySlots[i].champion = null
                    enemySlots[i].isLikelyUnpicked = true
                }
            }
INNER_EOF

sed -n '723,$p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt >> temp.kt
mv temp.kt app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
