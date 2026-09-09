package com.example.service.screen

import android.content.Context
import com.example.util.AppSecurityManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Modelo de calibración geométrica de las áreas de escaneo de selección de campeones (Draft).
 * Los valores están expresados en ratios normalizados (0.0f a 1.0f) respecto a la pantalla/captura.
 */
data class VisionCalibrationConfig(
    // Posición horizontal X central de avatares (0..1)
    var allyAvatarCenterX: Float = 0.135f,
    var enemyAvatarCenterX: Float = 0.930f,

    // Diámetro del avatar relativo al alto de pantalla (0..1)
    var avatarDiameterRatio: Float = 0.114f,

    // Ratios verticales Y para los 5 slots aliados (0..4)
    var allySlotYRatios: MutableList<Float> = mutableListOf(0.193f, 0.326f, 0.460f, 0.593f, 0.726f),

    // Ratios verticales Y para los 5 slots enemigos (0..4)
    var enemySlotYRatios: MutableList<Float> = mutableListOf(0.172f, 0.302f, 0.432f, 0.561f, 0.691f),

    // Hechizos de invocador aliados
    var spellLeftRatio: Float = 0.024f,
    var spellSizeRatio: Float = 0.044f,

    // Rango horizontal OCR para detección de nombres/roles
    var allyOcrMinX: Float = 0.080f,
    var allyOcrMaxX: Float = 0.260f,
    var enemyOcrMinX: Float = 0.760f,
    var enemyOcrMaxX: Float = 0.945f,

    // Interruptores de visualización en el HUD de diagnóstico
    var showAvatarBoxes: Boolean = true,
    var showNameBoxes: Boolean = true,
    var showSpellBoxes: Boolean = true
) {
    fun toJsonString(indent: Boolean = true): String {
        val obj = JSONObject()
        obj.put("version", 1)
        obj.put("allyAvatarCenterX", allyAvatarCenterX.toDouble())
        obj.put("enemyAvatarCenterX", enemyAvatarCenterX.toDouble())
        obj.put("avatarDiameterRatio", avatarDiameterRatio.toDouble())

        val allyYArray = JSONArray()
        allySlotYRatios.forEach { allyYArray.put(it.toDouble()) }
        obj.put("allySlotYRatios", allyYArray)

        val enemyYArray = JSONArray()
        enemySlotYRatios.forEach { enemyYArray.put(it.toDouble()) }
        obj.put("enemySlotYRatios", enemyYArray)

        obj.put("spellLeftRatio", spellLeftRatio.toDouble())
        obj.put("spellSizeRatio", spellSizeRatio.toDouble())

        obj.put("allyOcrMinX", allyOcrMinX.toDouble())
        obj.put("allyOcrMaxX", allyOcrMaxX.toDouble())
        obj.put("enemyOcrMinX", enemyOcrMinX.toDouble())
        obj.put("enemyOcrMaxX", enemyOcrMaxX.toDouble())

        obj.put("showAvatarBoxes", showAvatarBoxes)
        obj.put("showNameBoxes", showNameBoxes)
        obj.put("showSpellBoxes", showSpellBoxes)

        return if (indent) obj.toString(2) else obj.toString()
    }

    companion object {
        private const val PREFS_KEY = "vision_calibration_json"

        fun fromJsonString(jsonStr: String): VisionCalibrationConfig {
            val config = VisionCalibrationConfig()
            try {
                val obj = JSONObject(jsonStr)
                if (obj.has("allyAvatarCenterX")) config.allyAvatarCenterX = obj.getDouble("allyAvatarCenterX").toFloat()
                if (obj.has("enemyAvatarCenterX")) config.enemyAvatarCenterX = obj.getDouble("enemyAvatarCenterX").toFloat()
                if (obj.has("avatarDiameterRatio")) config.avatarDiameterRatio = obj.getDouble("avatarDiameterRatio").toFloat()

                if (obj.has("allySlotYRatios")) {
                    val arr = obj.getJSONArray("allySlotYRatios")
                    val list = mutableListOf<Float>()
                    for (i in 0 until arr.length()) {
                        list.add(arr.getDouble(i).toFloat())
                    }
                    if (list.size == 5) config.allySlotYRatios = list
                }

                if (obj.has("enemySlotYRatios")) {
                    val arr = obj.getJSONArray("enemySlotYRatios")
                    val list = mutableListOf<Float>()
                    for (i in 0 until arr.length()) {
                        list.add(arr.getDouble(i).toFloat())
                    }
                    if (list.size == 5) config.enemySlotYRatios = list
                }

                if (obj.has("spellLeftRatio")) config.spellLeftRatio = obj.getDouble("spellLeftRatio").toFloat()
                if (obj.has("spellSizeRatio")) config.spellSizeRatio = obj.getDouble("spellSizeRatio").toFloat()

                if (obj.has("allyOcrMinX")) config.allyOcrMinX = obj.getDouble("allyOcrMinX").toFloat()
                if (obj.has("allyOcrMaxX")) config.allyOcrMaxX = obj.getDouble("allyOcrMaxX").toFloat()
                if (obj.has("enemyOcrMinX")) config.enemyOcrMinX = obj.getDouble("enemyOcrMinX").toFloat()
                if (obj.has("enemyOcrMaxX")) config.enemyOcrMaxX = obj.getDouble("enemyOcrMaxX").toFloat()

                if (obj.has("showAvatarBoxes")) config.showAvatarBoxes = obj.getBoolean("showAvatarBoxes")
                if (obj.has("showNameBoxes")) config.showNameBoxes = obj.getBoolean("showNameBoxes")
                if (obj.has("showSpellBoxes")) config.showSpellBoxes = obj.getBoolean("showSpellBoxes")
            } catch (_: Exception) {}
            return config
        }

        fun load(context: Context): VisionCalibrationConfig {
            return try {
                val prefs = AppSecurityManager.getEncryptedSharedPreferences(context, "app_prefs_enc")
                val jsonStr = prefs.getString(PREFS_KEY, null)
                if (jsonStr != null) fromJsonString(jsonStr) else VisionCalibrationConfig()
            } catch (_: Exception) {
                VisionCalibrationConfig()
            }
        }

        fun save(context: Context, config: VisionCalibrationConfig) {
            try {
                val prefs = AppSecurityManager.getEncryptedSharedPreferences(context, "app_prefs_enc")
                prefs.edit().putString(PREFS_KEY, config.toJsonString(false)).apply()
            } catch (_: Exception) {}
        }

        fun reset(context: Context): VisionCalibrationConfig {
            val def = VisionCalibrationConfig()
            save(context, def)
            return def
        }
    }
}
