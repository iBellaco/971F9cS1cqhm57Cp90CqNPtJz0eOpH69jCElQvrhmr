package com.example.data.supabase

import android.content.Context
import android.util.Log
import com.example.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import kotlinx.serialization.json.Json
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Gestor dinámico del cliente de Supabase.
 * Permite usar las claves por defecto de BuildConfig/Secrets o configurar
 * credenciales personalizadas desde el Panel de Administrador.
 */
object SupabaseClientManager {

    private const val TAG = "SupabaseClientManager"
    private const val PREFS_NAME = "supabase_config_prefs"
    private const val KEY_CUSTOM_URL = "custom_supabase_url"
    private const val KEY_CUSTOM_KEY = "custom_supabase_key"

    private const val DEFAULT_URL = "https://yreknglctxujpetgqhnw.supabase.co"
    private const val DEFAULT_KEY = "sb_publishable_bQJGpyYVR-uxtBmN03F5yA_ZuibUcAr"

    @Volatile
    private var customUrl: String? = null
    @Volatile
    private var customKey: String? = null
    @Volatile
    private var cachedClient: SupabaseClient? = null

    fun init(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        customUrl = prefs.getString(KEY_CUSTOM_URL, null)
        customKey = prefs.getString(KEY_CUSTOM_KEY, null)
    }

    fun getActiveUrl(): String {
        return customUrl?.takeIf { it.isNotBlank() }
            ?: BuildConfig.SUPABASE_URL.takeIf { it.isNotBlank() }
            ?: DEFAULT_URL
    }

    fun getActiveKey(): String {
        return customKey?.takeIf { it.isNotBlank() }
            ?: BuildConfig.SUPABASE_ANON_KEY.takeIf { it.isNotBlank() }
            ?: DEFAULT_KEY
    }

    fun isUsingCustomCredentials(): Boolean {
        return !customUrl.isNullOrBlank() && !customKey.isNullOrBlank()
    }

    fun saveCustomCredentials(context: Context, url: String, key: String) {
        val cleanUrl = url.trim().removeSuffix("/")
        val cleanKey = key.trim()
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_CUSTOM_URL, cleanUrl)
            .putString(KEY_CUSTOM_KEY, cleanKey)
            .apply()
        customUrl = cleanUrl
        customKey = cleanKey
        cachedClient = null // Forzar recreación del cliente
    }

    fun resetToDefaultCredentials(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        customUrl = null
        customKey = null
        cachedClient = null
    }

    val client: SupabaseClient
        get() {
            cachedClient?.let { return it }
            synchronized(this) {
                cachedClient?.let { return it }
                val url = getActiveUrl()
                val key = getActiveKey()
                Log.d(TAG, "Inicializando cliente de Supabase con URL: $url")
                val newClient = createSupabaseClient(
                    supabaseUrl = url,
                    supabaseKey = key
                ) {
                    install(Postgrest)
                    install(Auth)
                    defaultSerializer = KotlinXSerializer(Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    })
                }
                cachedClient = newClient
                return newClient
            }
        }

    /**
     * Prueba la conexión realizando una consulta mínima a Supabase.
     */
        suspend fun testConnection(): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Se asume que existe la tabla feedbacks
            val count = client.postgrest.from("feedbacks").select().data
            Result.success("Conexión exitosa. Se pudo conectar al panel de reportes.")
        } catch (e: Exception) {
            Log.e(TAG, "Error probando conexión a Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Retorna el script SQL oficial para que el usuario pueda crearlo con 1 clic en Supabase SQL Editor.
     */
        fun getSupabaseSqlSchema(): String {
        return """-- =========================================================
-- ESQUEMA OFICIAL SUPABASE PARA WILD RIFT APP (SOLO REPORTES)
-- =========================================================

-- 1. TABLA DE REPORTES / SUGERENCIAS
CREATE TABLE IF NOT EXISTS public.feedbacks (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type TEXT NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    app_version TEXT,
    device_info TEXT,
    status TEXT DEFAULT 'PENDING',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. HABILITAR SEGURIDAD (RLS) Y PERMITIR LECTURA/ESCRITURA PÚBLICA (ANON)
ALTER TABLE public.feedbacks ENABLE ROW LEVEL SECURITY;
CREATE POLICY "Allow public read feedbacks" ON public.feedbacks FOR SELECT USING (true);
CREATE POLICY "Allow public insert feedbacks" ON public.feedbacks FOR INSERT WITH CHECK (true);
CREATE POLICY "Allow public update feedbacks" ON public.feedbacks FOR UPDATE USING (true);
        """.trimIndent()
    }
}
