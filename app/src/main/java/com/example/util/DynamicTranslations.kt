package com.example.util

import android.content.Context
import org.json.JSONObject
import com.example.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DynamicTranslations {
    @Volatile
    private var enMap: Map<String, String>? = null
    @Volatile
    private var ptMap: Map<String, String>? = null

    private val scope = CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
        AppLogger.e("Translations", "Error loading translations safely", t)
    })

    fun load(context: Context) {
        if (enMap != null && ptMap != null) return
        
        scope.launch {
            loadSync(context)
        }
    }

    fun loadSync(context: Context) {
        if (enMap != null && ptMap != null) return
        try {
            if (enMap == null) {
                val jsonStr = context.assets.open("translations_en.json").bufferedReader().use { it.readText() }
                val json = JSONObject(jsonStr)
                val map = mutableMapOf<String, String>()
                val iter = json.keys()
                while (iter.hasNext()) {
                    val key = iter.next()
                    map[key] = json.getString(key)
                }
                enMap = map
            }
            if (ptMap == null) {
                val jsonStr = context.assets.open("translations_pt.json").bufferedReader().use { it.readText() }
                val json = JSONObject(jsonStr)
                val map = mutableMapOf<String, String>()
                val iter = json.keys()
                while (iter.hasNext()) {
                    val key = iter.next()
                    map[key] = json.getString(key)
                }
                ptMap = map
            }
        } catch (e: Exception) {
            AppLogger.e("Translations", "Failed to load dynamic translations", e)
        }
    }

    fun get(lang: String, key: String): String? {
        if (lang == "en") return enMap?.get(key)
        if (lang == "pt") return ptMap?.get(key)
        return null
    }
}
