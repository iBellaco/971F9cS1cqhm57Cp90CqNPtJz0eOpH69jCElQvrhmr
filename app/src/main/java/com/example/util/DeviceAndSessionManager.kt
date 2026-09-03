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
    private const val KEY_PERSISTENT_DEVICE_ID = "persistent_device_id"

    private var localSessionToken: String? = null
    private var lastLocalLoginTimestamp: Long = 0L

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

    private var cachedDeviceId: String? = null

    // Obtener ID del dispositivo persistente y estable por instalación
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        if (!cachedDeviceId.isNullOrBlank()) {
            return cachedDeviceId!!
        }

        val appContext = context.applicationContext ?: context
        val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userPrefs = appContext.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val internalFile = java.io.File(appContext.filesDir, "device_id.txt")

        // 1. SharedPreferences
        val savedId = prefs.getString(KEY_PERSISTENT_DEVICE_ID, null)
        if (!savedId.isNullOrBlank()) {
            cachedDeviceId = savedId
            try { internalFile.writeText(savedId) } catch (_: Exception) {}
            return savedId
        }

        // 2. Internal file
        try {
            if (internalFile.exists()) {
                val fileId = internalFile.readText().trim()
                if (fileId.isNotBlank()) {
                    cachedDeviceId = fileId
                    prefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, fileId).apply()
                    return fileId
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error reading device_id.txt: ${e.message}")
        }

        // 3. User preferences backup
        val backupId = userPrefs.getString(KEY_PERSISTENT_DEVICE_ID, null)
        if (!backupId.isNullOrBlank()) {
            cachedDeviceId = backupId
            prefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, backupId).apply()
            try { internalFile.writeText(backupId) } catch (_: Exception) {}
            return backupId
        }

        // 4. Generate stable persistent installation UUID once and store it forever
        val newId = "WRD_INST_" + UUID.randomUUID().toString()
        cachedDeviceId = newId
        prefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, newId).apply()
        userPrefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, newId).apply()
        try { internalFile.writeText(newId) } catch (_: Exception) {}
        return newId
    }

    // Registrar sesión y dispositivo en Firestore de manera segura y sin desconexiones accidentales
    fun registerDeviceAndSession(context: Context, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Usuario no logueado")
            return
        }

        val deviceId = getDeviceId(context)
        val sessionToken = refreshSessionToken(context)
        val loginTimestamp = System.currentTimeMillis()
        lastLocalLoginTimestamp = loginTimestamp

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
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
                "lastActiveTimestamp" to loginTimestamp,
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

    fun handleSessionChanged(remoteSessionToken: String?, remoteDeviceId: String?, remoteTimestamp: Long = 0L, context: Context) {
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
            // Solo desconectar si explícitamente se confirmó que otro dispositivo tomó la sesión activa y ocurrió después de nuestro login
            val isSubsequentRemoteLogin = remoteTimestamp > (lastLocalLoginTimestamp + 3000L)
            if (remoteDeviceId != currentDeviceId && remoteDeviceId.length >= 6 && isSubsequentRemoteLogin) {
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


