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

    // Obtener ID del dispositivo persistente y determinista por instalación
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        if (!cachedDeviceId.isNullOrBlank()) {
            return cachedDeviceId!!
        }

        val appContext = context.applicationContext ?: context

        // 1. Verificar archivo plano interno permanente
        val internalFile = java.io.File(appContext.filesDir, "device_id.txt")
        try {
            if (internalFile.exists()) {
                val fileId = internalFile.readText().trim()
                if (fileId.isNotBlank()) {
                    cachedDeviceId = fileId
                    return fileId
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error reading device_id.txt: ${e.message}")
        }

        // 2. Verificar SharedPreferences principales
        val prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedId = prefs.getString(KEY_PERSISTENT_DEVICE_ID, null)
        if (!savedId.isNullOrBlank()) {
            cachedDeviceId = savedId
            try { internalFile.writeText(savedId) } catch (_: Exception) {}
            return savedId
        }

        // 3. Verificar también en user_preferences por redundancia
        val userPrefs = appContext.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val backupId = userPrefs.getString(KEY_PERSISTENT_DEVICE_ID, null)
        if (!backupId.isNullOrBlank()) {
            cachedDeviceId = backupId
            prefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, backupId).apply()
            try { internalFile.writeText(backupId) } catch (_: Exception) {}
            return backupId
        }

        // 4. Obtener ANDROID_ID o generar hash determinista de hardware
        var hardwareId: String? = null
        try {
            hardwareId = Settings.Secure.getString(appContext.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
            Log.w(TAG, "Error obtaining ANDROID_ID: ${e.message}")
        }

        val finalId = if (!hardwareId.isNullOrBlank() && hardwareId != "9774d56d682e549c" && hardwareId != "UNKNOWN_DEVICE") {
            "WRD_$hardwareId"
        } else {
            // Huella digital de hardware determinista fija para este dispositivo físico
            val hardwareSeed = "${android.os.Build.MANUFACTURER}_${android.os.Build.MODEL}_${android.os.Build.BRAND}_${android.os.Build.DEVICE}_${android.os.Build.BOARD}_${android.os.Build.HARDWARE}"
            val deterministicUUID = UUID.nameUUIDFromBytes(hardwareSeed.toByteArray()).toString().replace("-", "").take(16)
            "WRD_HW_$deterministicUUID"
        }

        cachedDeviceId = finalId
        prefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, finalId).apply()
        userPrefs.edit().putString(KEY_PERSISTENT_DEVICE_ID, finalId).apply()
        try { internalFile.writeText(finalId) } catch (_: Exception) {}
        return finalId
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


