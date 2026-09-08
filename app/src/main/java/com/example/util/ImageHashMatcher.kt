package com.example.util

import android.graphics.Bitmap
import android.graphics.Color
import com.example.model.Champion
import com.example.model.LaneRole

data class RoleMatchResult(
    val role: LaneRole,
    val distance: Int,
    val confidence: Float
)

data class MatchResult(
    val champion: Champion,
    val distance: Int,
    val confidencePercent: Int,
    val topScore: Float = 0f,
    val secondChampion: Champion? = null,
    val secondScore: Float = 0f,
    val margin: Float = 0f,
    val isConfirmed: Boolean = true,
    val status: String = "CONFIRMADO",
    val reason: String = ""
)

data class VisualEvaluation(
    val candidate1: Champion?,
    val score1: Float,
    val candidate2: Champion?,
    val score2: Float,
    val margin: Float,
    val isConfirmed: Boolean,
    val status: String, // "CONFIRMADO", "RECHAZADO", "AMBIGUO", "VACIO"
    val reason: String
)

object ImageHashMatcher {

    // Calcula el aHash (Average Hash) de 64 bits para un Bitmap de recorte
    fun calculateHash(bitmap: Bitmap): Long {
        val scaled = Bitmap.createScaledBitmap(bitmap, 8, 8, true)
        val pixels = IntArray(64)
        scaled.getPixels(pixels, 0, 8, 0, 0, 8, 8)
        
        var totalLuminance = 0L
        val luminances = IntArray(64)
        
        for (i in 0 until 64) {
            val color = pixels[i]
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            val luminance = (r * 299 + g * 587 + b * 114) / 1000
            luminances[i] = luminance
            totalLuminance += luminance
        }
        
        val avgLuminance = totalLuminance / 64
        var hash = 0L
        
        for (i in 0 until 64) {
            if (luminances[i] >= avgLuminance) {
                hash = hash or (1L shl (63 - i))
            }
        }
        
        scaled.recycle()
        return hash
    }
    
    // Distancia de Hamming para hashes de 64 bits
    fun hammingDistance(hash1: Long, hash2: Long): Int {
        return java.lang.Long.bitCount(hash1 xor hash2)
    }

    // Recorta la porción interior central del avatar para eliminar marcos dorados/rojos decorativos
    fun getInnerCrop(bitmap: Bitmap, scale: Float = 0.70f): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val targetW = (w * scale).toInt().coerceAtLeast(16)
        val targetH = (h * scale).toInt().coerceAtLeast(16)
        val startX = ((w - targetW) / 2).coerceIn(0, w - targetW)
        val startY = ((h - targetH) / 2).coerceIn(0, h - targetH)
        return Bitmap.createBitmap(bitmap, startX, startY, targetW, targetH)
    }

    // Calcula el dHash (Difference Hash) de 64 bits
    fun calculateDHash(bitmap: Bitmap): Long {
        val scaled = Bitmap.createScaledBitmap(bitmap, 9, 8, true)
        val pixels = IntArray(72)
        scaled.getPixels(pixels, 0, 9, 0, 0, 9, 8)
        
        var hash = 0L
        var bitIndex = 0
        for (y in 0 until 8) {
            for (x in 0 until 8) {
                val left = pixels[y * 9 + x]
                val right = pixels[y * 9 + (x + 1)]
                val leftLum = (Color.red(left) * 299 + Color.green(left) * 587 + Color.blue(left) * 114) / 1000
                val rightLum = (Color.red(right) * 299 + Color.green(right) * 587 + Color.blue(right) * 114) / 1000
                if (leftLum > rightLum) {
                    hash = hash or (1L shl (63 - bitIndex))
                }
                bitIndex++
            }
        }
        scaled.recycle()
        return hash
    }

    /**
     * Evalúa visualmente un recorte de avatar contra los 141 recursos de campeones locales.
     * Utiliza Correlación Cruzada Normalizada (NCC) de 1024 puntos estructurales sobre máscara circular
     * y Similitud Coseno de Histograma de Color (RGB 64 bins).
     *
     * Reglas estrictas:
     * 1. El rol NO interviene en la puntuación visual (identificación puramente basada en píxeles).
     * 2. Rastrea Top 1 y Top 2 candidatos y calcula margen de discriminación.
     * 3. Si no hay coincidencia suficientemente segura o hay ambigüedad, devuelve isConfirmed = false.
     * 4. Sin valores artificiales inflados.
     */
    fun evaluateVisualMatch(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        isAlly: Boolean = false
    ): VisualEvaluation {
        if (bitmap.width < 16 || bitmap.height < 16) {
            return VisualEvaluation(null, 0f, null, 0f, 0f, false, "RECHAZADO", "Dimensiones de bitmap inválidas (<16px)")
        }

        val cropInner = try { getInnerCrop(bitmap, 0.70f) } catch (e: Exception) { bitmap }

        // 1. Extraer píxeles de 32x32 para el recorte actual
        val scaled = Bitmap.createScaledBitmap(cropInner, 32, 32, true)
        val pixels = IntArray(1024)
        scaled.getPixels(pixels, 0, 32, 0, 0, 32, 32)

        val mask = ChampionHashes.CIRCLE_MASK
        val pixelCount = ChampionHashes.CIRCLE_PIXEL_COUNT

        var sumGray = 0f
        var sumLum = 0f
        var sumSat = 0f
        var maxLuminance = 0f
        var minLuminance = 255f
        val grays = FloatArray(1024)
        val cropColorHist = FloatArray(64)

        for (i in 0 until 1024) {
            if (!mask[i]) continue
            val color = pixels[i]
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)

            val lum = 0.299f * r + 0.587f * g + 0.114f * b
            grays[i] = lum
            sumGray += lum
            sumLum += lum
            if (lum > maxLuminance) maxLuminance = lum
            if (lum < minLuminance) minLuminance = lum

            val max = maxOf(r, maxOf(g, b)).toFloat()
            val min = minOf(r, minOf(g, b)).toFloat()
            val sat = if (max > 0f) (max - min) / max else 0f
            sumSat += sat

            val rBin = (r / 64).coerceIn(0, 3)
            val gBin = (g / 64).coerceIn(0, 3)
            val bBin = (b / 64).coerceIn(0, 3)
            val binIndex = (rBin shl 4) or (gBin shl 2) or bBin
            cropColorHist[binIndex] += 1f
        }

        val avgSaturation = sumSat / pixelCount
        val avgLuminance = sumLum / pixelCount

        // Normalizar histograma de color sobre píxeles del círculo
        for (b in 0 until 64) {
            cropColorHist[b] /= pixelCount
        }

        // Normalizar grays a media 0 y varianza 1 para NCC sobre píxeles del círculo
        val mean = sumGray / pixelCount
        var sumVar = 0f
        for (i in 0 until 1024) {
            if (mask[i]) {
                val diff = grays[i] - mean
                sumVar += diff * diff
            }
        }
        val stdDev = Math.sqrt((sumVar / pixelCount.toDouble())).toFloat().coerceAtLeast(0.001f)
        val cropNormGray = FloatArray(1024)
        for (i in 0 until 1024) {
            if (mask[i]) {
                cropNormGray[i] = (grays[i] - mean) / stdDev
            }
        }

        scaled.recycle()
        if (cropInner != bitmap) {
            try { cropInner.recycle() } catch (ignored: Exception) {}
        }

        // 2. FILTRADO ESTRICTO DE SLOT VACÍO / CASCO ESPARTANO / RUIDO:
        if (!isAlly) {
            val isSpartanHelmetOrEmpty = (avgSaturation < 0.14f && (avgLuminance < 80f || stdDev < 22f)) ||
                    (stdDev < 10f) || (avgLuminance < 25f)
            if (isSpartanHelmetOrEmpty) {
                return VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Casco espartano o slot rival sin selección activa")
            }
        } else {
            val isAllyEmptyOrFlat = (stdDev < 10f) || (avgLuminance < 20f)
            if (isAllyEmptyOrFlat) {
                return VisualEvaluation(null, 0f, null, 0f, 0f, false, "VACIO", "Slot aliado vacío o plano sin avatar")
            }
        }

        // 3. COMPARACIÓN VISUAL CONTRA TODAS LAS FIRMAS LOCALES (100% PUREZA DE IMAGEN)
        val signatures = ChampionHashes.getAllSignatures()
        var bestChamp: Champion? = null
        var bestScore = 0f
        var secondChamp: Champion? = null
        var secondScore = 0f

        if (signatures.isNotEmpty()) {
            for (sig in signatures) {
                // A) Correlación Cruzada Normalizada (NCC) estructural
                var dotProduct = 0f
                for (i in 0 until 1024) {
                    if (mask[i]) {
                        dotProduct += cropNormGray[i] * sig.normalizedGray[i]
                    }
                }
                val structuralScore = (dotProduct / pixelCount).coerceIn(0f, 1f)

                // B) Similitud cromática (Bhattacharyya)
                var colorScore = 0f
                for (b in 0 until 64) {
                    colorScore += Math.sqrt((cropColorHist[b] * sig.colorHistogram[b]).toDouble()).toFloat()
                }
                colorScore = colorScore.coerceIn(0f, 1f)

                // C) Puntuación visual pura
                // Restauramos un balance 60/40. El problema real no era el peso, sino que
                // el NCC estaba encontrando falsos positivos en el ruido de fondo rojo (scores < 0.40).
                // Al darle peso al color, obligamos al motor a rechazar campeones verdes/azules 
                // (como Teemo) cuando está viendo un retrato rojo (Katarina/Vlad).
                val totalScore = (0.60f * structuralScore) + (0.40f * colorScore)

                val champ = allChampions.find { it.id.equals(sig.championId, ignoreCase = true) } ?: continue

                if (totalScore > bestScore) {
                    secondScore = bestScore
                    secondChamp = bestChamp
                    bestScore = totalScore
                    bestChamp = champ
                } else if (totalScore > secondScore) {
                    secondScore = totalScore
                    secondChamp = champ
                }
            }
        }

        val margin = (bestScore - secondScore).coerceAtLeast(0f)

        // 4. CLASIFICACIÓN ESTRICTA: CONFIRMADO vs RECHAZADO vs AMBIGUO
        val minThreshold = 0.40f

        return when {
            bestChamp == null || bestScore < minThreshold -> {
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = false,
                    status = "RECHAZADO",
                    reason = "Puntuación insuficiente (score ${"%.2f".format(java.util.Locale.US, bestScore)} < $minThreshold)"
                )
            }
            bestScore >= 0.80f -> {
                // Coincidencia visual de muy alta fidelidad
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = true,
                    status = "CONFIRMADO",
                    reason = "Coincidencia de alta fidelidad (score ${"%.2f".format(java.util.Locale.US, bestScore)}, margen ${"%.2f".format(java.util.Locale.US, margin)})"
                )
            }
            margin < 0.018f -> {
                // Ambigüedad entre dos campeones con puntuación casi idéntica
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = false,
                    status = "AMBIGUO",
                    reason = "Coincidencia ambigua entre ${bestChamp.name} (${"%.2f".format(java.util.Locale.US, bestScore)}) y ${secondChamp?.name ?: "segundo"} (${"%.2f".format(java.util.Locale.US, secondScore)}) margen ${"%.2f".format(java.util.Locale.US, margin)} < 0.02"
                )
            }
            else -> {
                // Coincidencia clara con margen suficiente
                VisualEvaluation(
                    candidate1 = bestChamp,
                    score1 = bestScore,
                    candidate2 = secondChamp,
                    score2 = secondScore,
                    margin = margin,
                    isConfirmed = true,
                    status = "CONFIRMADO",
                    reason = "Coincidencia confirmada (score ${"%.2f".format(java.util.Locale.US, bestScore)}, margen ${"%.2f".format(java.util.Locale.US, margin)})"
                )
            }
        }
    }

    /**
     * Compara un recorte de avatar en tiempo real contra los 141 recursos de campeones locales.
     * Devuelve null si no existe una coincidencia suficientemente buena o si es ambigua.
     */
    fun findBestVisualMatch(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        preferredRole: LaneRole? = null,
        isAlly: Boolean = false
    ): MatchResult? {
        val eval = evaluateVisualMatch(bitmap, allChampions, isAlly)
        if (eval.isConfirmed && eval.candidate1 != null) {
            val confidence = ((eval.score1 * 100).toInt()).coerceIn(1, 100)
            val distance = ((1.0f - eval.score1) * 100).toInt()
            return MatchResult(
                champion = eval.candidate1,
                distance = distance,
                confidencePercent = confidence,
                topScore = eval.score1,
                secondChampion = eval.candidate2,
                secondScore = eval.score2,
                margin = eval.margin,
                isConfirmed = true,
                status = eval.status,
                reason = eval.reason
            )
        }
        return null
    }

    /**
     * Detecta si el recorte de hechizos de invocador de un slot contiene el hechizo Castigo (Smite).
     * En Wild Rift, Castigo posee un color rojo/naranja llameante característico con chispas doradas.
     * Si está presente, el jugador pertenece con certeza absoluta al carril de JUNGLA.
     */
    fun detectSmiteSpell(spellBitmap: Bitmap): Boolean {
        if (spellBitmap.width < 10 || spellBitmap.height < 10) return false
        val w = spellBitmap.width
        val h = spellBitmap.height
        val pixels = IntArray(w * h)
        spellBitmap.getPixels(pixels, 0, w, 0, 0, w, h)

        var smitePixelCount = 0
        for (color in pixels) {
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)

            // Tonalidad roja-anaranjada-dorada llameante de Castigo (Smite)
            val isFlame = (r > 155 && g in 50..185 && b < 80 && (r - b) > 80) ||
                          (r > 190 && g in 100..210 && b < 85 && (r - b) > 90)
            if (isFlame) {
                smitePixelCount++
            }
        }

        val fraction = smitePixelCount.toFloat() / pixels.size.toFloat()
        return fraction >= 0.035f // Mayor a 3.5% de píxeles ígneos de Castigo
    }

    // Fallback de comparación por aHash con umbral de tolerancia y prioridad por rol
    fun findBestMatchDetailed(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        maxDistance: Int = 22,
        preferredRole: LaneRole? = null
    ): MatchResult? {
        val innerCrop = try { getInnerCrop(bitmap) } catch (e: Exception) { bitmap }
        val targetHash = calculateHash(innerCrop)
        if (innerCrop != bitmap) {
            try { innerCrop.recycle() } catch (ignored: Exception) {}
        }

        var bestMatch: Champion? = null
        var minDistance = maxDistance
        
        allChampions.forEach { champ ->
            val hashes = ChampionHashes.getHashesForChampion(champ.id)
            for (champHash in hashes) {
                val dist = hammingDistance(targetHash, champHash)

                if (dist <= minDistance) {
                    minDistance = dist
                    bestMatch = champ
                }
            }
        }
        
        return bestMatch?.let {
            val confidence = (((64 - minDistance.coerceAtLeast(0)).toFloat() / 64.0f) * 100).toInt().coerceIn(1, 100)
            MatchResult(champion = it, distance = minDistance, confidencePercent = confidence)
        }
    }

    // Busca el campeón más similar
    fun findBestMatch(bitmap: Bitmap, allChampions: List<Champion>, preferredRole: LaneRole? = null): Champion? {
        return findBestVisualMatch(bitmap, allChampions, preferredRole)?.champion
            ?: findBestMatchDetailed(bitmap, allChampions, preferredRole = preferredRole)?.champion
    }

    // Busca coincidencia para un icono de rol
    fun findRoleMatchDetailed(bitmap: Bitmap, maxDistance: Int = 26): RoleMatchResult? {
        val targetHash = calculateHash(bitmap)
        var bestRole: LaneRole? = null
        var minDistance = maxDistance
        
        RoleHashes.map.forEach { (role, hashList) ->
            hashList.forEach { hash ->
                val dist = hammingDistance(targetHash, hash)
                if (dist < minDistance) {
                    minDistance = dist
                    bestRole = role
                }
            }
        }
        
        return bestRole?.let {
            val confidence = (64 - minDistance) / 64.0f
            RoleMatchResult(role = it, distance = minDistance, confidence = confidence)
        }
    }

    fun findRoleMatch(bitmap: Bitmap): LaneRole? {
        return findRoleMatchDetailed(bitmap)?.role
    }
}
