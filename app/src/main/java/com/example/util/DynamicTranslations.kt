package com.example.util

import android.content.Context
import org.json.JSONObject
import com.example.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object DynamicTranslations {
    private var enMap: Map<String, String>? = null
    private var ptMap: Map<String, String>? = null

    fun load(context: Context) {
        if (enMap != null && ptMap != null) return
        
        GlobalScope.launch(Dispatchers.IO) {
            try {
                if (enMap == null) {
                    val jsonStr = context.resources.openRawResource(R.raw.translations_en).bufferedReader().use { it.readText() }
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
                    val jsonStr = context.resources.openRawResource(R.raw.translations_pt).bufferedReader().use { it.readText() }
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
    }

    fun get(lang: String, key: String): String? {
        if (lang == "en") return enMap?.get(key)
        if (lang == "pt") return ptMap?.get(key)
        return null
    }
}
