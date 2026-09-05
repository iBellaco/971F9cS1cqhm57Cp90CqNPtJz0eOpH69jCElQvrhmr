            } finally {
                scaledOcrBmp?.recycle()
            }

            val ocrWidth = if (screenWidth > 1280) 1280 else screenWidth
            val ocrHeight = if (screenWidth > 1280) (screenHeight * (1280f / screenWidth)).toInt() else screenHeight

            val detectedWords = mutableListOf<String>()
            val allChamps = WildRiftRepository.champions

            // Coordenadas verticales oficiales de las 5 ranuras en Wild Rift (Landscape)
            val allySlotYCenters = floatArrayOf(0.185f, 0.325f, 0.465f, 0.605f, 0.745f)
            val enemySlotYCenters = floatArrayOf(0.170f, 0.305f, 0.445f, 0.585f, 0.725f)

            // Mapeo por defecto de roles según el orden habitual de selección en Wild Rift
            val defaultAllyRoles = arrayOf(LaneRole.ADC, LaneRole.SUPPORT, LaneRole.MID, LaneRole.JUNGLE, LaneRole.TOP)
            val defaultEnemyRoles = arrayOf(LaneRole.ADC, LaneRole.JUNGLE, LaneRole.MID, LaneRole.SUPPORT, LaneRole.TOP)

            val allySlots = arrayOfNulls<Champion>(5)
            val enemySlots = arrayOfNulls<Champion>(5)
            val allySlotRoles = arrayOfNulls<LaneRole>(5)
            val enemySlotRoles = arrayOfNulls<LaneRole>(5)
            val allySlotTexts = Array(5) { mutableListOf<String>() }
            val enemySlotTexts = Array(5) { mutableListOf<String>() }
            val enemySlotEmpty = BooleanArray(5)

            // 1. Procesamiento OCR exclusivo por columnas laterales delimitadas
            // Excluir zona central de selección de campeones (0.28 a 0.70) para evitar falsos positivos
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

                    // Descartar barra superior extrema (bans < 7%) y barra inferior (> 89%)
                    if (yRatio < 0.07f || yRatio > 0.89f) {
                        continue
                    }

                    // --- COLUMNA IZQUIERDA: EQUIPO ALIADO ---
                    // Acotado estrictamente entre 6% y 33% del ancho de pantalla
                    if (xRatio in 0.06f..0.33f) {
                        val slotIdx = when {
                            yRatio < 0.255f -> 0
                            yRatio < 0.395f -> 1
                            yRatio < 0.535f -> 2
                            yRatio < 0.675f -> 3
                            else -> 4
                        }

                        if (slotIdx in 0 until 5) {
                            allySlotTexts[slotIdx].add(lineText)
                            val lower = lineText.lowercase(Locale.ROOT)

                            // Detección de Rol/Línea por texto en la ranura
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                allySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                allySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva")) {
                                allySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                allySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte")) {
                                allySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Aliado por nombre explícito
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
                                    if (allySlots.none { it?.id == candidate.id }) {
                                        allySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Aliado detectado por OCR en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }

                    // --- COLUMNA DERECHA: EQUIPO ENEMIGO ---
                    // Acotado estrictamente entre 67% y 94% del ancho de pantalla
                    else if (xRatio in 0.67f..0.94f) {
                        val slotIdx = when {
                            yRatio < 0.255f -> 0
                            yRatio < 0.395f -> 1
                            yRatio < 0.535f -> 2
                            yRatio < 0.675f -> 3
                            else -> 4
                        }

                        if (slotIdx in 0 until 5) {
                            enemySlotTexts[slotIdx].add(lineText)
                            val lower = lineText.lowercase(Locale.ROOT)

                            // Si dice "Jugador 1/2/3/4/5" o "Player", ignorar la línea (nombre genérico) y continuar buscando el campeón
                            if (lower.contains("jugador") || lower.contains("player")) {
                                continue
                            }

                            // Detección de Rol/Línea enemigo si aparece
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                enemySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("bar0n") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                enemySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva")) {
                                enemySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("drag0n") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                enemySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") || lower.contains("suporte")) {
                                enemySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }

                            // Detección de Campeón Enemigo por nombre explícito
                            if (enemySlots[slotIdx] == null && !enemySlotEmpty[slotIdx]) {
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
                                    if (enemySlots.none { it?.id == candidate.id } && allySlots.none { it?.id == candidate.id }) {
                                        enemySlots[slotIdx] = candidate
                                        AppLogger.d(TAG, "Enemigo detectado por OCR en slot $slotIdx: ${candidate.name}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 1.5. DEDUPLICACIÓN GLOBAL: Asegurar unicidad total en las ranuras detectadas
            val seenSlotChampionIds = mutableSetOf<String>()
            for (i in 0 until 5) {
                val champ = allySlots[i] ?: continue
                if (seenSlotChampionIds.contains(champ.id)) {
                    AppLogger.w(TAG, "Eliminando duplicado aliado de ${champ.name} en slot $i")
                    allySlots[i] = null
                } else {
                    seenSlotChampionIds.add(champ.id)
                }
            }
            for (i in 0 until 5) {
                val champ = enemySlots[i] ?: continue
                if (seenSlotChampionIds.contains(champ.id)) {
                    AppLogger.w(TAG, "Eliminando duplicado enemigo de ${champ.name} en slot $i (ya existe en la draft)")
                    enemySlots[i] = null
                } else {
                    seenSlotChampionIds.add(champ.id)
                }
            }

            // 2. Detección Multi-Señal de la ranura del jugador local ("Tú / Yo voy")
            val slotScores = IntArray(5)
            val slotHasSmite = BooleanArray(5)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()

                // Señal A: Detección de texto de Maestría o 'Marca Estelar Eterna' (exclusivo de la tarjeta del jugador local)
                val hasEternalOrBadge = allySlotTexts[i].any { txt ->
                    val low = txt.lowercase(Locale.ROOT)
                    low.contains("marca") || low.contains("estelar") || low.contains("eterna") ||
                    low.contains("maestria") || low.contains("maestría") || low.contains("eterno")
                }
                if (hasEternalOrBadge) {
                    slotScores[i] += 12000
                    AppLogger.d(TAG, "Texto exclusivo de tarjeta local (Marca Estelar/Eterna) en slot $i (+12000)")
                }

                // Señal B: Marco Dorado Alado, Blasón y Gema Rubí en el lateral izquierdo y marco del avatar (X: 0.030f a 0.150f)
                // En Wild Rift, los aliados tienen un borde cian/azul neutro sin elementos dorados/ámbar.
                // El jugador local ("TÚ") tiene alas de dragón doradas prominentes y aro dorado con núcleo de gema rubí/fuego.
                val sampleXMin = (screenWidth * 0.030f).toInt().coerceAtLeast(0)
                val sampleXMax = (screenWidth * 0.150f).toInt().coerceAtMost(screenWidth - 1)
                val yMin = (yCenter - screenHeight * 0.055f).toInt().coerceAtLeast(0)
                val yMax = (yCenter + screenHeight * 0.055f).toInt().coerceAtMost(screenHeight - 1)

                var goldWingPixels = 0
                var rubyCorePixels = 0
                var orangeWingPixels = 0
