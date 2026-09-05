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
            // Luminancia aproximada estándar
            val luminance = (r * 0.299 + g * 0.587 + b * 0.114).toInt()
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
    
    // Calcula el hash y busca la mejor coincidencia para un icono de rol con métricas de confianza
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

    // Retorna el LaneRole directamente
    fun findRoleMatch(bitmap: Bitmap): LaneRole? {
        return findRoleMatchDetailed(bitmap)?.role
    }

    // Distancia de Hamming
    fun hammingDistance(hash1: Long, hash2: Long): Int {
        return java.lang.Long.bitCount(hash1 xor hash2)
    }

    // Busca el campeón más similar retornando detalles y porcentaje de confianza
    fun findBestMatchDetailed(bitmap: Bitmap, allChampions: List<Champion>, maxDistance: Int = 20): MatchResult? {
        val targetHash = calculateHash(bitmap)
        var bestMatch: Champion? = null
        var minDistance = maxDistance
        
        allChampions.forEach { champ ->
            val champHash = ChampionHashes.map[champ.id]
            if (champHash != null) {
                val dist = hammingDistance(targetHash, champHash)
                if (dist < minDistance) {
                    minDistance = dist
                    bestMatch = champ
                }
            }
        }
        
        return bestMatch?.let {
            val confidence = (((64 - minDistance).toFloat() / 64.0f) * 100).toInt().coerceIn(65, 99)
            MatchResult(champion = it, distance = minDistance, confidencePercent = confidence)
        }
    }

    // Busca el campeón más similar
    fun findBestMatch(bitmap: Bitmap, allChampions: List<Champion>): Champion? {
        return findBestMatchDetailed(bitmap, allChampions)?.champion
    }
}

