package com.example.data.supabase

import android.content.Context
import android.util.Log
import com.example.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
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
            val count = client.postgrest.from("wr_patches").select().decodeList<com.example.data.supabase.model.WrPatchDto>()
            Result.success("Conexión exitosa. Se encontraron ${count.size} registros de parches.")
        } catch (e: Exception) {
            Log.e(TAG, "Error probando conexión a Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Retorna el script SQL oficial para que el usuario pueda crearlo con 1 clic en Supabase SQL Editor.
     */
    fun getSupabaseSqlSchema(): String {
        return """
-- =========================================================
-- ESQUEMA OFICIAL SUPABASE PARA WILD RIFT APP
-- Ejecuta este script en el SQL Editor de tu proyecto Supabase
-- =========================================================

-- 1. TABLA DE CONTROL DE PARCHES
CREATE TABLE IF NOT EXISTS public.wr_patches (
    id TEXT PRIMARY KEY DEFAULT 'current',
    version TEXT NOT NULL,
    notes TEXT DEFAULT '',
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. TABLA DE OBJETOS / ÍTEMS
CREATE TABLE IF NOT EXISTS public.wr_items (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    gold_cost INT NOT NULL DEFAULT 0,
    stats TEXT DEFAULT '',
    passive TEXT DEFAULT '',
    icon_url TEXT DEFAULT '',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 3. TABLA DE CAMPEONES Y META
CREATE TABLE IF NOT EXISTS public.wr_champions (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    title TEXT DEFAULT '',
    primary_role TEXT NOT NULL DEFAULT 'BARON',
    secondary_roles TEXT DEFAULT '',
    tier TEXT NOT NULL DEFAULT 'A',
    winrate FLOAT NOT NULL DEFAULT 50.0,
    pickrate FLOAT NOT NULL DEFAULT 5.0,
    banrate FLOAT NOT NULL DEFAULT 2.0,
    damage_type TEXT NOT NULL DEFAULT 'PHYSICAL',
    avatar_url TEXT DEFAULT '',
    counters TEXT DEFAULT '',
    synergies TEXT DEFAULT '',
    core_items TEXT DEFAULT '',
    situational_items TEXT DEFAULT '',
    is_ranged BOOLEAN DEFAULT FALSE,
    is_frontline BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 4. TABLA DE RUNAS
CREATE TABLE IF NOT EXISTS public.wr_runes (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    icon_url TEXT DEFAULT '',
    description TEXT DEFAULT '',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 5. TABLA DE HECHIZOS DE INVOCADOR
CREATE TABLE IF NOT EXISTS public.wr_spells (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    cooldown TEXT DEFAULT '',
    icon_url TEXT DEFAULT '',
    description TEXT DEFAULT '',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 6. HABILITAR SEGURIDAD (RLS) Y PERMITIR LECTURA/ESCRITURA PÚBLICA (ANON)
ALTER TABLE public.wr_patches ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.wr_items ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.wr_champions ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.wr_runes ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.wr_spells ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Allow public read wr_patches" ON public.wr_patches FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_patches" ON public.wr_patches FOR ALL USING (true) WITH CHECK (true);

CREATE POLICY "Allow public read wr_items" ON public.wr_items FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_items" ON public.wr_items FOR ALL USING (true) WITH CHECK (true);

CREATE POLICY "Allow public read wr_champions" ON public.wr_champions FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_champions" ON public.wr_champions FOR ALL USING (true) WITH CHECK (true);

CREATE POLICY "Allow public read wr_runes" ON public.wr_runes FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_runes" ON public.wr_runes FOR ALL USING (true) WITH CHECK (true);

CREATE POLICY "Allow public read wr_spells" ON public.wr_spells FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_spells" ON public.wr_spells FOR ALL USING (true) WITH CHECK (true);
        """.trimIndent()
    }
}
