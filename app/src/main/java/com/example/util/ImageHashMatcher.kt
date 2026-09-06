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
    val confidencePercent: Int
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
     * Compara un recorte de avatar en tiempo real contra los 141 recursos de campeones locales
     * utilizando Correlación Cruzada Normalizada (NCC) de 1024 puntos estructurales y Similitud
     * Coseno de Histograma de Color (RGB 64 bins).
     *
     * Incluye detección y descarte estricto de slots vacíos (icono de casco espartano / sin selección en enemigos),
     * y ecualización de rango dinámico para avatares aliados atenuados en preselección.
     */
    fun findBestVisualMatch(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        preferredRole: LaneRole? = null,
        isAlly: Boolean = false
    ): MatchResult? {
        if (bitmap.width < 16 || bitmap.height < 16) return null

        val cropInner = try { getInnerCrop(bitmap, 0.82f) } catch (e: Exception) { bitmap }
        
        // 1. Extraer píxeles de 32x32 para el recorte actual
        val scaled = Bitmap.createScaledBitmap(cropInner, 32, 32, true)
        val pixels = IntArray(1024)
        scaled.getPixels(pixels, 0, 32, 0, 0, 32, 32)

        var sumGray = 0f
        var sumLum = 0f
        var sumSat = 0f
        var maxLuminance = 0f
        var minLuminance = 255f
        val grays = FloatArray(1024)
        val cropColorHist = FloatArray(64)

        for (i in 0 until 1024) {
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

        val avgSaturation = sumSat / 1024f
        val avgLuminance = sumLum / 1024f

        // Normalizar histograma de color
        for (b in 0 until 64) {
            cropColorHist[b] /= 1024f
        }

        // Normalizar grays a media 0 y varianza 1 para NCC
        val mean = sumGray / 1024f
        var sumVar = 0f
        for (i in 0 until 1024) {
            val diff = grays[i] - mean
            sumVar += diff * diff
        }
        val stdDev = Math.sqrt((sumVar / 1024.0)).toFloat().coerceAtLeast(0.001f)
        val cropNormGray = FloatArray(1024)
        for (i in 0 until 1024) {
            cropNormGray[i] = (grays[i] - mean) / stdDev
        }

        scaled.recycle()
        if (cropInner != bitmap) {
            try { cropInner.recycle() } catch (ignored: Exception) {}
        }

        // 2. FILTRADO DE SLOT VACÍO / CASCO ESPARTANO:
        // Los slots aliados NUNCA son vacíos (siempre hay 5 compañeros).
        // En el equipo enemigo, si un slot no ha elegido, muestra el casco espartano metálico gris
        // sobre fondo rojo carmesí. Tiene muy baja varianza estructural central o saturación muy baja.
        if (!isAlly) {
            val isSpartanHelmetOrEmpty = (avgSaturation < 0.15f && (avgLuminance < 85f || stdDev < 25f)) ||
                    (stdDev < 18f) || (avgLuminance < 40f)
            if (isSpartanHelmetOrEmpty) {
                // El rival aún no ha seleccionado ningún campeón (casco espartano/vacío)
                return null
            }
        }

        // 3. COMPARACIÓN CONTRA TODAS LAS FIRMAS PRECARGADAS
        val signatures = ChampionHashes.getAllSignatures()
        var bestChamp: Champion? = null
        var maxScore = 0f

        if (signatures.isNotEmpty()) {
            for (sig in signatures) {
                // A) Correlación Cruzada Normalizada (NCC) estructural:
                var dotProduct = 0f
                for (i in 0 until 1024) {
                    dotProduct += cropNormGray[i] * sig.normalizedGray[i]
                }
                val structuralScore = (dotProduct / 1024f).coerceIn(0f, 1f)

                // B) Similitud cromática (Bhattacharyya / Cosine de histogramas de color):
                var colorScore = 0f
                for (b in 0 until 64) {
                    colorScore += Math.sqrt((cropColorHist[b] * sig.colorHistogram[b]).toDouble()).toFloat()
                }
                colorScore = colorScore.coerceIn(0f, 1f)

                // C) Puntuación compuesta (más peso estructural para resistir tintes oscuros de preselección)
                var totalScore = if (isAlly) {
                    (0.60f * structuralScore) + (0.40f * colorScore)
                } else {
                    (0.55f * structuralScore) + (0.45f * colorScore)
                }

                val champ = allChampions.find { it.id.equals(sig.championId, ignoreCase = true) } ?: continue

                // Bonificación sutil por rol preferido (solo 0.015 para evitar falsos positivos)
                if (preferredRole != null) {
                    if (champ.primaryRole == preferredRole) {
                        totalScore += 0.015f
                    } else if (champ.secondaryRoles.contains(preferredRole)) {
                        totalScore += 0.008f
                    }
                }

                if (totalScore > maxScore) {
                    maxScore = totalScore
                    bestChamp = champ
                }
            }
        } else {
            // Fallback a hash clásico si aún no se han cargado las firmas completas
            return findBestMatchDetailed(bitmap, allChampions, maxDistance = 22, preferredRole = preferredRole)
        }

        // Umbral adaptativo calibrado
        val requiredThreshold = if (isAlly) {
            if (preferredRole != null) 0.52f else 0.55f
        } else {
            0.58f
        }

        if (maxScore >= requiredThreshold && bestChamp != null) {
            val confidence = ((maxScore * 100).toInt()).coerceIn(75, 99)
            val distance = ((1.0f - maxScore) * 100).toInt()
            return MatchResult(champion = bestChamp, distance = distance, confidencePercent = confidence)
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
                var dist = hammingDistance(targetHash, champHash)
                
                if (preferredRole != null) {
                    if (champ.primaryRole == preferredRole) {
                        dist -= 4
                    } else if (champ.secondaryRoles.contains(preferredRole)) {
                        dist -= 2
                    }
                }

                if (dist <= minDistance) {
                    minDistance = dist
                    bestMatch = champ
                }
            }
        }
        
        return bestMatch?.let {
            val confidence = (((64 - minDistance.coerceAtLeast(0)).toFloat() / 64.0f) * 100).toInt().coerceIn(70, 99)
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
