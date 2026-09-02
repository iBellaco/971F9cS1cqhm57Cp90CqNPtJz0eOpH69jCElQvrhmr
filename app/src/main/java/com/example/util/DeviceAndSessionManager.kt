package com.example.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.UUID

object DeviceAndSessionManager {
    private const val TAG = "DeviceAndSessionManager"
    private const val PREFS_NAME = "device_session_prefs"
    private const val KEY_SESSION_TOKEN = "local_session_token"

    private var localSessionToken: String? = null

    // Obtener o generar un token único para esta sesión de la app
    fun getOrCreateSessionToken(context: Context): String {
        if (localSessionToken != null) return localSessionToken!!
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedToken = prefs.getString(KEY_SESSION_TOKEN, null)
        if (savedToken != null && savedToken.isNotBlank()) {
            localSessionToken = savedToken
            return savedToken
        }
        val newToken = UUID.randomUUID().toString()
        localSessionToken = newToken
        prefs.edit().putString(KEY_SESSION_TOKEN, newToken).apply()
        return newToken
    }

    // Forzar renovación de token de sesión (ej. tras nuevo login)
    fun refreshSessionToken(context: Context): String {
        val newToken = UUID.randomUUID().toString()
        localSessionToken = newToken
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_SESSION_TOKEN, newToken).apply()
        return newToken
    }

    // Obtener ID del dispositivo
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: "UNKNOWN_DEVICE"
    }

    // Registrar sesión y dispositivo en Firestore de manera segura y sin desconexiones accidentales
    fun registerDeviceAndSession(context: Context, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Usuario no logueado")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        val deviceId = getDeviceId(context)
        val sessionToken = getOrCreateSessionToken(context)
        val userEmail = user.email ?: ""
        val isAdmin = AuthManager.isAdminEmail(userEmail) || AuthManager.isCurrentUserAdmin()

        userRef.get().addOnSuccessListener { snapshot ->
            val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
            val mutableDevices = registeredDevices.toMutableList()

            if (!mutableDevices.contains(deviceId)) {
                if (mutableDevices.size >= 2 && !isAdmin) {
                    onError("Límite de dispositivos alcanzado (Máx 2 dispositivos por cuenta).")
                    return@addOnSuccessListener
                }
                if (isAdmin && mutableDevices.size >= 10) mutableDevices.removeAt(0)
                mutableDevices.add(deviceId)
            }

            val updatePayload = hashMapOf<String, Any>(
                "sessionToken" to sessionToken,
                "lastDeviceId" to deviceId,
                "lastActiveTimestamp" to System.currentTimeMillis(),
                "registeredDevices" to mutableDevices
            )

            userRef.set(updatePayload, SetOptions.merge())
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Non-blocking warning saving session: ${e.message}")
                    // No bloquear la sesión local por permisos menores de Firestore
                    onSuccess()
                }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Non-blocking warning getting user doc: ${e.message}")
            // Si no se pudo leer el doc por red, permitir seguir la sesión
            onSuccess()
        }
    }

    fun handleSessionChanged(remoteSessionToken: String?, remoteDeviceId: String?, context: Context) {
        val user = AuthManager.getAuth()?.currentUser ?: return
        val userEmail = user.email ?: ""
        val isAdmin = AuthManager.isAdminEmail(userEmail) || 
                      AuthManager.isCurrentUserAdmin() ||
                      SubscriptionManager.userRole.value == "admin"
        
        // Administradores nunca se desconectan por concurrencia
        if (isAdmin) return

        val currentDeviceId = getDeviceId(context)
        // Si el snapshot remoto proviene de este MISMO dispositivo o los datos son incompletos, no cerrar sesión jamás
        if (remoteDeviceId.isNullOrBlank() || remoteDeviceId == currentDeviceId) {
            if (!remoteSessionToken.isNullOrBlank()) {
                localSessionToken = remoteSessionToken
                val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                prefs.edit().putString(KEY_SESSION_TOKEN, remoteSessionToken).apply()
            }
            return
        }

        val currentLocalToken = getOrCreateSessionToken(context)
        if (!remoteSessionToken.isNullOrBlank() && remoteSessionToken != currentLocalToken) {
            // Solo desconectar si explícitamente se confirmó que otro dispositivo tomó la sesión activa
            if (remoteDeviceId != currentDeviceId && remoteDeviceId.length >= 6) {
                Log.w(TAG, "Sesión concurrente detectada desde otro dispositivo ($remoteDeviceId). Cerrando sesión local.")
                AuthManager.getAuth()?.signOut()
                Toast.makeText(context, "Sesión cerrada: Tu cuenta se inició en otro dispositivo.", Toast.LENGTH_LONG).show()
                localSessionToken = null
                val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                prefs.edit().remove(KEY_SESSION_TOKEN).apply()
            }
        }
    }
}

