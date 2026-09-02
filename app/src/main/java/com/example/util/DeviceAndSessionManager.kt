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
    private var localSessionToken: String? = null

    // Generar un token único para esta sesión de la app
    fun generateSessionToken(): String {
        localSessionToken = UUID.randomUUID().toString()
        return localSessionToken!!
    }

    // Obtener ID del dispositivo
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: "UNKNOWN_DEVICE"
    }

    // Registrar sesión y dispositivo en Firestore
    fun registerDeviceAndSession(context: Context, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Usuario no logueado")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        val deviceId = getDeviceId(context)
        val sessionToken = generateSessionToken()

        db.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)
            val registeredDevices = snapshot.get("registeredDevices") as? List<String> ?: emptyList()
            
            // Si es premium (o cualquier usuario), verificamos devices
            val userEmail = user.email ?: ""
            val isAdmin = userEmail == "barbachavezdiego@gmail.com"
            val mutableDevices = registeredDevices.toMutableList()
            if (!mutableDevices.contains(deviceId)) {
                if (mutableDevices.size >= 2 && !isAdmin) {
                    throw Exception("Límite de dispositivos alcanzado (Máx 2 dispositivos por cuenta).")
                }
                if (isAdmin && mutableDevices.size >= 10) mutableDevices.removeAt(0)
                mutableDevices.add(deviceId)
            }

            transaction.set(userRef, hashMapOf(
                "sessionToken" to sessionToken,
                "registeredDevices" to mutableDevices
            ), SetOptions.merge())
        }.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error registering device/session", e)
            onError(e.message ?: "Error desconocido")
        }
    }

    fun handleSessionChanged(remoteSessionToken: String?, context: Context) {
        if (localSessionToken == null) return // Aún no hemos inicializado sesión
        if (remoteSessionToken != null && remoteSessionToken != localSessionToken) {
            // ¡Alguien más inició sesión!
            Log.w(TAG, "Sesión concurrente detectada. Cerrando sesión local.")
            AuthManager.getAuth()?.signOut()
            Toast.makeText(context, "Sesión cerrada: Alguien inició sesión en otro dispositivo.", Toast.LENGTH_LONG).show()
            localSessionToken = null // Reset
        }
    }
}
