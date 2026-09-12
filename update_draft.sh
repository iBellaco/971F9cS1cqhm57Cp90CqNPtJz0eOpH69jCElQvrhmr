#!/bin/bash

sed -n '1,452p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt > temp.kt

cat << 'INNER_EOF' >> temp.kt
                var detectedRoleInSlot: LaneRole? = null
                var detectedChampInSlot: Champion? = null
                val summonerCandidates = mutableListOf<String>()
                var isUnpickedTextPresent = false

                for ((rawBlock, box) in entries) {
                    val safeBox = box ?: Rect(0, 0, 10, 10)
                    val sublines = rawBlock.split("\n").map { it.trim() }.filter { it.isNotBlank() }

                    // Comprobar si el texto está coloreado en dorado/amarillo característico del slot del usuario en Wild Rift
                    var hasYellowGoldText = false
                    try {
                        val sampleBox = Rect(
                            safeBox.left.coerceIn(0, width - 1),
                            safeBox.top.coerceIn(0, height - 1),
                            safeBox.right.coerceIn(0, width),
                            safeBox.bottom.coerceIn(0, height)
                        )
                        if (sampleBox.width() > 4 && sampleBox.height() > 4) {
                            var yellowHits = 0
                            val stepX = (sampleBox.width() / 6).coerceAtLeast(1)
                            val stepY = (sampleBox.height() / 4).coerceAtLeast(1)
                            for (sy in sampleBox.top until sampleBox.bottom step stepY) {
                                for (sx in sampleBox.left until sampleBox.right step stepX) {
                                    val px = bitmap.getPixel(sx, sy)
                                    val pr = android.graphics.Color.red(px)
                                    val pg = android.graphics.Color.green(px)
                                    val pb = android.graphics.Color.blue(px)
                                    if (pr > 165 && pg > 140 && pb < 115 && pr > pb * 1.5f) {
                                        yellowHits++
                                    }
                                }
                            }
                            if (yellowHits >= 3) {
                                hasYellowGoldText = true
                            }
                        }
                    } catch (_: Exception) {}

                    for (line in sublines) {
                        if (DraftValidationLayer.isNoiseText(line)) continue
                        
                        val lineLower = line.lowercase(Locale.ROOT)
                        if (lineLower.contains("preselecci") || lineLower.contains("eligiendo") || 
                            lineLower.contains("ayud") || lineLower.contains("bloque") || 
                            lineLower.contains("esperando")) {
                            isUnpickedTextPresent = true
                        }

                        // Comprobar si este slot contiene la etiqueta del usuario "(TÚ)" / "(TU)" / "(YOU)" / "(VOCÊ)" o coincide con su nombre
                        val lineNorm = DraftValidationLayer.normalize(line).lowercase(Locale.ROOT)
                        val lineCompressed = lineNorm.replace(" ", "")
                        val isUserTag = !isLegendaryRanked && (lineNorm == "tu" || lineNorm == "(tu)" || lineNorm == "you" || lineNorm == "(you)" ||
                                        lineNorm == "voce" || lineNorm == "(voce)" ||
                                        lineNorm.startsWith("(tu) ") || lineNorm.endsWith(" (tu)") ||
                                        lineNorm.startsWith("(you) ") || lineNorm.endsWith(" (you)") ||
                                        lineNorm.contains(" tú ") || lineNorm.contains("(tú)") ||
                                        currentUserNameClean.any { it.length >= 3 && lineCompressed == it })

                        if (isUserTag || hasYellowGoldText) {
                            userSlotIndex = i
                            userExplicitlyConfirmed = true
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = true,
                                    slotIndex = i,
                                    tag = if (isLegendaryRanked) "¡TU SLOT (DORADO)!" else "¡TU SLOT!",
                                    color = android.graphics.Color.YELLOW
                                )
                            )
                            AppLogger.d(TAG, "Slot del usuario confirmado en Slot Aliado $i ('$line') [Yellow=$hasYellowGoldText, Legendary=$isLegendaryRanked]")
                        }

                        // A) Rol / Línea explícito (ej: "Línea Central", "Carril de Barón", etc.)
                        val role = DraftValidationLayer.parseRoleFromText(line)
                        if (role != null) {
                            detectedRoleInSlot = role
                            slot.explicitRole = role
                            allySlotRolesCache[i] = role
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = true,
                                    slotIndex = i,
                                    tag = "LÍNEA: ${role.shortName}",
                                    color = android.graphics.Color.CYAN
                                )
                            )
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Línea: ${role.shortName}")
                            continue
                        }

                        // B) Texto de campeón detectado por OCR (100% autoritativo)
                        val matched = ChampionNameResolver.findChampionInText(line, allChamps)
                        if (matched != null) {
                            detectedChampInSlot = matched
                            textDiagnosticsList.add(
                                TextBlockDiagnostic(
                                    text = line,
                                    rect = safeBox,
                                    isAlly = true,
                                    slotIndex = i,
                                    tag = "CAMPEÓN: ${matched.name}",
                                    color = android.graphics.Color.GREEN
                                )
                            )
                            AppLogger.d(TAG, "OCR Aliado Slot $i -> Campeón 100%: ${matched.name}")
                            continue
                        }

                        // C) Nombre de invocador (SOLO SI NO ES CLASIFICATORIA LEGENDARIA)
                        if (!isLegendaryRanked && line.length in 1..28 && !DraftValidationLayer.isNoiseText(line) && !line.matches(Regex("^[0-9\\s:.,%#-]+$"))) {
                            if (ChampionNameResolver.findChampionInText(line, allChamps) == null) {
                                summonerCandidates.add(line)
                            }
                        }
                    }
                }

                // Si en este slot se detectó texto indicando que aún no se elige, se invalida el campeón (es un hover)
                if (isUnpickedTextPresent || (detectedRoleInSlot != null && detectedChampInSlot == null)) {
                    allyOcrChampions[i] = null
                    slot.champion = null
                    slot.isLikelyUnpicked = true
                    allySlotFilters[i].reset() // Resetear memoria temporal del slot para no arrastrar campeones previos
                } else if (detectedChampInSlot != null) {
                    allyOcrChampions[i] = detectedChampInSlot
                    slot.champion = detectedChampInSlot
                    slot.confidencePercent = 100
                    slot.isLikelyUnpicked = false
                } else {
                    allyOcrChampions[i] = null
                    slot.champion = null
                    slot.isLikelyUnpicked = true
                    allySlotFilters[i].reset()
                }
INNER_EOF

sed -n '584,$p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt >> temp.kt
mv temp.kt app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
