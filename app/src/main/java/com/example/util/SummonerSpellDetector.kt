package com.example.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import com.example.data.WildRiftSpellsAndRunes

/**
 * Detector y Clasificador de Hechizos de Invocador (Summoner Spells) de Wild Rift.
 * Analiza la firma cromática y espectral de los iconos en pantalla
 * para identificar Destello, Prender, Castigo, Curar, Barrera, Extenuación, etc.
 */
object SummonerSpellDetector {

    data class SpellMatch(
        val spellId: String,
        val spellName: String,
        val iconUrl: String,
        val rect: Rect
    )

    fun detectSpell(spellBitmap: Bitmap, rect: Rect): SpellMatch? {
        if (spellBitmap.width < 8 || spellBitmap.height < 8) return null
        val w = spellBitmap.width
        val h = spellBitmap.height
        val pixels = IntArray(w * h)
        spellBitmap.getPixels(pixels, 0, w, 0, 0, w, h)

        var totalValid = 0
        var totalLum = 0L

        var yellowCount = 0     // Flash (Destello)
        var orangeGoldCount = 0 // Barrier (Barrera) / Smite (Castigo)
        var redFireCount = 0    // Ignite (Prender)
        var greenCount = 0      // Heal (Curar)
        var cyanCount = 0       // Ghost (Fantasmal)
        var darkBrownCount = 0  // Exhaust (Extenuación)

        // Tomar zona central interna (evitar bordes circulares o bordes oscuros)
        val startX = (w * 0.15f).toInt()
        val endX = (w * 0.85f).toInt()
        val startY = (h * 0.15f).toInt()
        val endY = (h * 0.85f).toInt()

        for (y in startY..endY) {
            for (x in startX..endX) {
                val color = pixels[y * w + x]
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)
                val lum = (r * 299 + g * 587 + b * 114) / 1000
                totalLum += lum
                totalValid++

                // 1. Curar (Heal): Verde esmeralda vivo
                if (g > 130 && g > r + 30 && g > b + 30) {
                    greenCount++
                }
                // 2. Fantasmal (Ghost): Cyan / Azul claro brillante
                else if (b > 140 && g > 120 && r < 120 && (b - r) > 35) {
                    cyanCount++
                }
                // 3. Prender (Ignite): Rojo intenso llameante
                else if (r > 160 && g < 100 && b < 70 && (r - g) > 60) {
                    redFireCount++
                }
                // 4. Castigo (Smite): Fuego anaranjado / espada ígnea
                else if ((r > 155 && g in 50..185 && b < 80 && (r - b) > 80) ||
                         (r > 190 && g in 100..210 && b < 85 && (r - b) > 90)) {
                    orangeGoldCount++
                }
                // 5. Destello (Flash): Amarillo / Dorado intenso
                else if (r > 170 && g > 150 && b < 110 && kotlin.math.abs(r - g) < 55) {
                    yellowCount++
                }
                // 6. Barrera (Barrier): Ámbar / Oro esférico
                else if (r > 175 && g in 120..190 && b < 90) {
                    orangeGoldCount++
                }
                // 7. Extenuación (Exhaust): Marrón / Bronce oscuro
                else if (r in 110..185 && g in 75..135 && b < 70 && (r - g) in 30..70) {
                    darkBrownCount++
                }
            }
        }

        if (totalValid == 0) return null
        val avgLum = totalLum.toFloat() / totalValid.toFloat()
        // Si el área es muy oscura (< 20 lum), se descarta (slot vacío o sin pick)
        if (avgLum < 20f) return null

        val greenRatio = greenCount.toFloat() / totalValid.toFloat()
        val cyanRatio = cyanCount.toFloat() / totalValid.toFloat()
        val redRatio = redFireCount.toFloat() / totalValid.toFloat()
        val orangeRatio = orangeGoldCount.toFloat() / totalValid.toFloat()
        val yellowRatio = yellowCount.toFloat() / totalValid.toFloat()
        val brownRatio = darkBrownCount.toFloat() / totalValid.toFloat()

        val (spellId, name) = when {
            greenRatio > 0.08f -> "heal" to "Curar"
            cyanRatio > 0.08f -> "ghost" to "Fantasmal"
            redRatio > 0.08f -> "ignite" to "Prender"
            orangeRatio > 0.10f -> "smite" to "Castigo"
            yellowRatio > 0.07f -> "flash" to "Destello"
            orangeRatio > 0.06f -> "barrier" to "Barrera"
            brownRatio > 0.08f -> "exhaust" to "Extenuación"
            yellowRatio + orangeRatio > 0.05f -> "flash" to "Destello"
            else -> "flash" to "Destello"
        }

        val item = WildRiftSpellsAndRunes.getSpellByName(spellId)
        val iconUrl = item?.iconUrl ?: "https://i.postimg.cc/6qHRh6Gt/1691694210-flash.webp"
        return SpellMatch(spellId, name, iconUrl, rect)
    }
}
