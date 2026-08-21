package com.example.data.supabase

import com.example.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth

/**
 * Gestor del cliente de Supabase.
 * Nota: Debes configurar SUPABASE_URL y SUPABASE_ANON_KEY en el panel de Secrets de AI Studio.
 */
object SupabaseClientManager {

    private const val DEFAULT_URL = "https://yreknglctxujpetgqhnw.supabase.co"
    private const val DEFAULT_KEY = "sb_publishable_bQJGpyYVR-uxtBmN03F5yA_ZuibUcAr"

    val client by lazy {
        val url = BuildConfig.SUPABASE_URL.takeIf { it.isNotBlank() } ?: DEFAULT_URL
        val key = BuildConfig.SUPABASE_ANON_KEY.takeIf { it.isNotBlank() } ?: DEFAULT_KEY

        createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = key
        ) {
            install(Postgrest)
            install(Auth)
        }
    }
}
