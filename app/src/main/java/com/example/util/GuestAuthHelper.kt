package com.example.util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

/**
 * Proveedor de autenticación transparente y resiliente para invitados y dispositivos sin login.
 * Permite que dispositivos que aún no han iniciado sesión puedan conectarse a los servicios de
 * sincronización de anuncios y comunicados en tiempo real.
 */
object GuestAuthHelper {
    private const val TAG = "GuestAuthHelper"
    const val GUEST_EMAIL = "coach.guest.reader@gmail.com"
    private const val GUEST_PASS = "CoachGuest@2026Wr"

    @Volatile
    private var isAuthenticating = false
    private val pendingCallbacks = mutableListOf<() -> Unit>()

    fun ensureAuth(onComplete: () -> Unit = {}) {
        val auth = try {
            FirebaseAuth.getInstance()
        } catch (e: Exception) {
            Log.w(TAG, "FirebaseAuth no disponible: ${e.message}")
            onComplete()
            return
        }

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Ya hay una sesión activa (usuario real, anónimo o invitado)
            onComplete()
            return
        }

        synchronized(pendingCallbacks) {
            pendingCallbacks.add(onComplete)
            if (isAuthenticating) return
            isAuthenticating = true
        }

        fun dispatchCallbacks() {
            val callbacks = synchronized(pendingCallbacks) {
                isAuthenticating = false
                val list = ArrayList(pendingCallbacks)
                pendingCallbacks.clear()
                list
            }
            callbacks.forEach { cb ->
                try { cb() } catch (_: Exception) {}
            }
        }

        // 1. Intento primario: Inicio anónimo estándar
        try {
            auth.signInAnonymously()
                .addOnSuccessListener {
                    Log.d(TAG, "Autenticación anónima exitosa (uid=${it.user?.uid})")
                    dispatchCallbacks()
                }
                .addOnFailureListener { anonErr ->
                    Log.w(TAG, "Inicio anónimo no disponible (${anonErr.message}). Iniciando con credenciales de lectura de respaldo...")
                    // 2. Intento secundario: Credencial de invitado de lectura universal
                    auth.signInWithEmailAndPassword(GUEST_EMAIL, GUEST_PASS)
                        .addOnSuccessListener {
                            Log.d(TAG, "Sesión de invitado de respaldo iniciada con éxito.")
                            dispatchCallbacks()
                        }
                        .addOnFailureListener { loginErr ->
                            Log.w(TAG, "Cuenta de invitado de respaldo no encontrada (${loginErr.message}). Registrando lector público...")
                            auth.createUserWithEmailAndPassword(GUEST_EMAIL, GUEST_PASS)
                                .addOnSuccessListener {
                                    Log.d(TAG, "Cuenta de lectura de respaldo creada y autenticada.")
                                    dispatchCallbacks()
                                }
                                .addOnFailureListener { createErr ->
                                    Log.e(TAG, "Fallo total en autenticación de invitado: ${createErr.message}")
                                    dispatchCallbacks()
                                }
                        }
                }
        } catch (e: Exception) {
            Log.e(TAG, "Excepción en ensureAuth: ${e.message}")
            dispatchCallbacks()
        }
    }
}
