import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

# Grab the block from `val allySlots = arrayOfNulls<Champion>(5)` to `// 2. Detección Multi-Señal`
idx1 = text.find('val allySlots = arrayOfNulls<Champion>(5)')
idx2 = text.find('// 2. Detección Multi-Señal')

if idx1 != -1 and idx2 != -1:
    old_block = text[idx1:idx2]
    
    new_code = r"""val allySlots = arrayOfNulls<Champion>(5)
            val enemySlots = arrayOfNulls<Champion>(5)
            val allySlotRoles = arrayOfNulls<LaneRole>(5)
            val enemySlotRoles = arrayOfNulls<LaneRole>(5)
            val allySlotTexts = Array(5) { mutableListOf<String>() }
            val enemySlotTexts = Array(5) { mutableListOf<String>() }
            val enemySlotEmpty = BooleanArray(5)

            val detectedAllyChamps = mutableListOf<Pair<Champion, Float>>()
            val detectedAllyRoles = mutableListOf<Pair<LaneRole, Float>>()
            
            val detectedEnemyChamps = mutableListOf<Pair<Champion, Float>>()
            val detectedEnemyRoles = mutableListOf<Pair<LaneRole, Float>>()

            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isBlank()) continue
                    detectedWords.add(lineText)

                    val box = line.boundingBox
                    val centerX = box?.centerX() ?: 0
                    val centerY = box?.centerY() ?: 0
                    val xRatio = centerX.toFloat() / ocrWidth.toFloat()
                    val yRatio = centerY.toFloat() / ocrHeight.toFloat()

                    if (yRatio < 0.07f || yRatio > 0.89f) continue

                    val lower = lineText.lowercase(java.util.Locale.ROOT)

                    if (xRatio in 0.06f..0.33f) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        allySlotTexts[bucket].add(lineText)

                        val role = when {
                            lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                            lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                            lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                            lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                            lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte") -> LaneRole.SUPPORT
                            else -> null
                        }
                        if (role != null) detectedAllyRoles.add(role to yRatio)

                        val matchedDirect = matchChampions(lineText, allChamps)
                        val candidate = matchedDirect.firstOrNull() ?: run {
                            val words = lineText.split(Regex("[\s,.:/()_-]+")).filter { it.isNotBlank() }
                            words.firstNotNullOfOrNull { w ->
                                if (!ignoredWords.contains(w.lowercase(java.util.Locale.ROOT))) {
                                    matchChampions(w, allChamps).firstOrNull()
                                } else null
                            }
                        }
                        if (candidate != null && detectedAllyChamps.none { it.first.id == candidate.id }) {
                            detectedAllyChamps.add(candidate to yRatio)
                        }
                    }
                    else if (xRatio in 0.67f..0.94f) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        enemySlotTexts[bucket].add(lineText)

                        if (lower.contains("jugador") || lower.contains("player")) continue

                        val role = when {
                            lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                            lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                            lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                            lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                            lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte") -> LaneRole.SUPPORT
                            else -> null
                        }
                        if (role != null) detectedEnemyRoles.add(role to yRatio)

                        val matchedDirect = matchChampions(lineText, allChamps)
                        val candidate = matchedDirect.firstOrNull() ?: run {
                            val words = lineText.split(Regex("[\s,.:/()_-]+")).filter { it.isNotBlank() }
                            words.firstNotNullOfOrNull { w ->
                                if (!ignoredWords.contains(w.lowercase(java.util.Locale.ROOT))) {
                                    matchChampions(w, allChamps).firstOrNull()
                                } else null
                            }
                        }
                        if (candidate != null && detectedEnemyChamps.none { it.first.id == candidate.id } && detectedAllyChamps.none { it.first.id == candidate.id }) {
                            detectedEnemyChamps.add(candidate to yRatio)
                        }
                    }
                }
            }

            detectedAllyChamps.sortBy { it.second }
            detectedEnemyChamps.sortBy { it.second }
            detectedAllyRoles.sortBy { it.second }
            detectedEnemyRoles.sortBy { it.second }

            detectedAllyChamps.forEachIndexed { index, pair -> 
                if (index < 5) allySlots[index] = pair.first 
            }
            detectedEnemyChamps.forEachIndexed { index, pair -> 
                if (index < 5) enemySlots[index] = pair.first 
            }
            detectedAllyRoles.forEachIndexed { index, pair -> 
                if (index < 5) allySlotRoles[index] = pair.first 
            }
            detectedEnemyRoles.forEachIndexed { index, pair -> 
                if (index < 5) enemySlotRoles[index] = pair.first 
            }

            """
    
    new_code = new_code.replace(r'[\s', r'[\\s')
    text = text.replace(old_block, new_code)
    with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
        f.write(text)
else:
    print("Could not find the anchors!")
