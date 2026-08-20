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

    val client by lazy {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
            install(Auth)
        }
    }
}
