with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    c = f.read()

old_code = """                // Asignar el nombre de invocador más limpio detectado
                val bestSummoner = summonerCandidates.firstOrNull { cand ->
                    !cand.equals(slot.champion?.name, ignoreCase = true) &&
                    ChampionNameResolver.findChampionInText(cand, allChamps) == null
                }
                if (!bestSummoner.isNullOrBlank()) {
                    allySummonerNamesCache[i] = bestSummoner
                    textDiagnosticsList.add(
                        TextBlockDiagnostic(
                            text = bestSummoner,
                            rect = Rect(0, 0, 10, 10),
                            isAlly = true,
                            slotIndex = i,
                            tag = "INVOCADOR",
                            color = android.graphics.Color.argb(255, 120, 180, 255)
                        )
                    )
                }"""

new_code = """                // Filtrar y limpiar candidatos a nombre de invocador respetando espacios y descartando ruido/chat/campeones
                val validSummonerLines = mutableListOf<String>()
                for (cand in summonerCandidates) {
                    val trimmed = cand.trim()
                    if (trimmed.length < 2) continue
                    if (DraftValidationLayer.isNoiseText(trimmed)) continue
                    if (trimmed.contains(":") || trimmed.contains("BETA", ignoreCase = true) || trimmed.contains("Porcentaje", ignoreCase = true)) continue
                    if (trimmed.matches(Regex("^[0-9\\s:.,%#-]+$"))) continue
                    if (DraftValidationLayer.parseRoleFromText(trimmed) != null) continue
                    if (ChampionNameResolver.findChampionInText(trimmed, allChamps) != null) continue
                    if (slot.champion != null && trimmed.equals(slot.champion?.name, ignoreCase = true)) continue
                    if (!validSummonerLines.contains(trimmed)) {
                        validSummonerLines.add(trimmed)
                    }
                }

                // Combinar líneas adyacentes si forman parte de un nombre con espacios (ej. "DIE" + "GO" -> "DIE GO")
                var bestSummoner: String? = null
                if (validSummonerLines.isNotEmpty()) {
                    bestSummoner = validSummonerLines.first()
                    if (validSummonerLines.size >= 2 && validSummonerLines[0].length <= 8 && validSummonerLines[1].length <= 12) {
                        val combined = "${validSummonerLines[0]} ${validSummonerLines[1]}"
                        if (combined.length <= 25) {
                            bestSummoner = combined
                        }
                    }
                }

                if (!bestSummoner.isNullOrBlank()) {
                    allySummonerNamesCache[i] = bestSummoner
                    textDiagnosticsList.add(
                        TextBlockDiagnostic(
                            text = bestSummoner,
                            rect = Rect(0, 0, 10, 10),
                            isAlly = true,
                            slotIndex = i,
                            tag = "INVOCADOR",
                            color = android.graphics.Color.argb(255, 120, 180, 255)
                        )
                    )
                }"""

if old_code in c:
    c = c.replace(old_code, new_code)
    with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
        f.write(c)
    print("DraftVisionScanner summoner logic patched successfully!")
else:
    print("Error: old_code not found!")
