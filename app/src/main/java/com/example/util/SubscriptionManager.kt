package com.example.util

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import com.example.data.AvatarCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object SubscriptionManager {
    private val _userRole = MutableStateFlow("free")
    val userRole: StateFlow<String> = _userRole.asStateFlow()
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _isBanned = MutableStateFlow(false)
    val isBanned: StateFlow<Boolean> = _isBanned.asStateFlow()

    private val _isPremium = MutableStateFlow(false)
    val isPremium: StateFlow<Boolean> = _isPremium.asStateFlow()

    private val _premiumUntil = MutableStateFlow<Long?>(null)
    val premiumUntil: StateFlow<Long?> = _premiumUntil.asStateFlow()

    private val _currentAvatarId = MutableStateFlow("default_poro")
    val currentAvatarId: StateFlow<String> = _currentAvatarId.asStateFlow()

    private val _currentRankBorder = MutableStateFlow("NONE")
    val currentRankBorder: StateFlow<String> = _currentRankBorder.asStateFlow()

    private val _unlockedAvatars = MutableStateFlow<List<String>>(emptyList())
    val unlockedAvatars: StateFlow<List<String>> = _unlockedAvatars.asStateFlow()

    private var roleListener: ListenerRegistration? = null

    init {
        com.example.util.AuthManager.getAuth()?.addAuthStateListener {
            val user = it.currentUser
            if (user == null) {
                _userRole.value = "free"
                _userName.value = ""
                _isPremium.value = false
                _isBanned.value = false
                _currentAvatarId.value = "default_poro"
                _unlockedAvatars.value = emptyList()
                roleListener?.remove()
                roleListener = null
            }
        }
    }

    fun init(context: Context) {
        val auth = AuthManager.getAuth()
        val user = auth?.currentUser

        if (user == null) {
            _userRole.value = "free"
            _userName.value = ""
            _isPremium.value = false
            _isBanned.value = false
            _currentAvatarId.value = "default_poro"
            _unlockedAvatars.value = emptyList()
            roleListener?.remove()
            roleListener = null
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)

        // Bootstrap document creation using get() first
        userRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                if (snapshot == null || !snapshot.exists()) {
                    val initialName = user.displayName?.takeIf { it.isNotBlank() } ?: user.email?.substringBefore("@") ?: ""
                    _userName.value = initialName
                    _currentAvatarId.value = "default_poro"
                    _unlockedAvatars.value = listOf("default_poro")
                    // Create if it doesn't exist. Use SetOptions.merge() just in case.
                    val userData = hashMapOf(
                        "role" to "free",
                        "email" to (user.email ?: ""),
                        "name" to initialName,
                        "avatarId" to "default_poro",
                        "unlockedAvatars" to listOf("default_poro"),
                        "last_active" to System.currentTimeMillis()
                    )
                    userRef.set(userData, SetOptions.merge())
                } else {
                    val dbName = snapshot.getString("name") ?: ""
                    if (dbName.isNotBlank()) {
                        _userName.value = dbName
                    }
                    val dbAvatarId = snapshot.getString("avatarId") ?: "default_poro"
                    val dbRankBorder = snapshot.getString("rankBorder") ?: "NONE"
                    _currentRankBorder.value = dbRankBorder
                    _currentAvatarId.value = dbAvatarId
                    @Suppress("UNCHECKED_CAST")
                    val dbUnlocked = snapshot.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")
                    _unlockedAvatars.value = dbUnlocked

                    val updateData = hashMapOf<String, Any>("last_active" to System.currentTimeMillis())
                    userRef.set(updateData, SetOptions.merge())
                }
            }
            
            // Listen for real-time changes
            roleListener?.remove()
            roleListener = userRef.addSnapshotListener { listenSnapshot, error ->
                if (error != null) {
                    Log.e("SubscriptionManager", "Error listening to role", error)
                    return@addSnapshotListener
                }

                if (listenSnapshot != null && listenSnapshot.exists()) {
                    var role = listenSnapshot.getString("role") ?: "free"
                    if (AuthManager.isAdminEmail(user.email)) {
                        role = "admin"
                    }
                    val sessionToken = listenSnapshot.getString("sessionToken")
                    val remoteDeviceId = listenSnapshot.getString("lastDeviceId")
                    val remoteTimestamp = listenSnapshot.getLong("lastActiveTimestamp") ?: 0L
                    com.example.util.DeviceAndSessionManager.handleSessionChanged(sessionToken, remoteDeviceId, remoteTimestamp, context)
                    val banned = listenSnapshot.getBoolean("banned") ?: false
                    val name = listenSnapshot.getString("name") ?: ""
                    val avatarId = listenSnapshot.getString("avatarId") ?: "default_poro"
                    val rankBorder = listenSnapshot.getString("rankBorder") ?: "NONE"
                    val until = listenSnapshot.getLong("premiumUntil")
                    @Suppress("UNCHECKED_CAST")
                    val unlocked = listenSnapshot.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")

                    if (name.isNotBlank()) {
                        _userName.value = name
                    }
                    _userRole.value = role
                    _isBanned.value = (role == "banned" || banned)
                    _premiumUntil.value = until
                    
                    val isPrem = when {
                        role == "admin" || AuthManager.isAdminEmail(user.email) -> true
                        role == "premium" -> {
                            until == null || until == 0L || until > System.currentTimeMillis()
                        }
                        else -> false
                    }
                    _isPremium.value = isPrem
                    _currentAvatarId.value = avatarId
                    
                    _unlockedAvatars.value = unlocked
                } else {
                    val isEmailAdmin = AuthManager.isAdminEmail(user.email)
                    _userName.value = user.displayName?.takeIf { it.isNotBlank() } ?: user.email?.substringBefore("@") ?: ""
                    _userRole.value = if (isEmailAdmin) "admin" else "free"
                    _isPremium.value = isEmailAdmin
                    _premiumUntil.value = null
                    _currentAvatarId.value = "default_poro"
                    _unlockedAvatars.value = emptyList()
                }
            }
        }
    }

    fun canEquipAvatar(avatarId: String): Boolean {
        if (_isPremium.value || _userRole.value == "admin") return true
        if (_unlockedAvatars.value.contains(avatarId)) return true
        val avatar = AvatarCatalog.avatars.find { it.id == avatarId }
        if (avatar != null && (avatar.isDefault || avatar.rarity.equals("común", true) || avatar.rarity.equals("comun", true))) return true
        return false
    }

    fun changeAvatar(
        avatarId: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Inicia sesión para cambiar de avatar")
            return
        }

        if (!canEquipAvatar(avatarId)) {
            onError("Este avatar requiere suscripción Premium.")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        userRef.set(hashMapOf("avatarId" to avatarId), SetOptions.merge())
            .addOnSuccessListener {
                _currentAvatarId.value = avatarId
                    
                onSuccess()
            }
            .addOnFailureListener {
                onError("Error al actualizar el avatar: ${it.message}")
            }
    }

    fun changeRankBorder(borderId: String, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Inicia sesión para cambiar de marco")
            return
        }
        if (!_isPremium.value && _userRole.value != "admin" && borderId != "NONE") {
            onError("Esta característica es exclusiva para usuarios Premium.")
            return
        }
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        userRef.set(hashMapOf("rankBorder" to borderId), SetOptions.merge())
            .addOnSuccessListener { _currentRankBorder.value = borderId; onSuccess() }
            .addOnFailureListener { onError("Error al actualizar el marco: ${it.message}") }
    }

    fun upgradeToPremium(durationMillis: Long? = null) {
        val user = AuthManager.getAuth()?.currentUser ?: return
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        val updateMap = hashMapOf<String, Any>(
            "role" to "premium"
        )
        if (durationMillis != null && durationMillis > 0) {
            val expireTime = System.currentTimeMillis() + durationMillis
            updateMap["premiumUntil"] = expireTime
        } else {
            updateMap["premiumUntil"] = 0L // Permanente / Vitalicio
        }
        
        userRef.set(updateMap, SetOptions.merge())
            .addOnSuccessListener {
                Log.d("SubscriptionManager", "Successfully upgraded to premium with duration: $durationMillis")
            }
            .addOnFailureListener {
                Log.e("SubscriptionManager", "Failed to upgrade", it)
            }
    }

    fun purchaseSubscription(durationMillis: Long, planName: String, price: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val user = AuthManager.getAuth()?.currentUser
        if (user == null) {
            onError("Debes iniciar sesión.")
            return
        }
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        val baseTime = if (_premiumUntil.value != null && _premiumUntil.value!! > System.currentTimeMillis()) {
            _premiumUntil.value!!
        } else {
            System.currentTimeMillis()
        }
        val newUntil = baseTime + durationMillis

        val updateMap = hashMapOf<String, Any>(
            "role" to "premium",
            "premiumUntil" to newUntil
        )

        /* 
         * 🔴 DEVSECOPS CRITICAL WARNING: BROKEN ACCESS CONTROL (OWASP API1:2023)
         * Escribir `role` y `premiumUntil` directamente desde el cliente hacia Firestore es un riesgo 
         * crítico. Un atacante puede interceptar y modificar esta petición o recompilar el APK 
         * para inyectar su propio `updateMap` y escalar privilegios (Privilege Escalation).
         * 
         * MITIGACIÓN (Ver firestore.rules):
         * 1. La escritura debe bloquearse para estos campos en Firebase Security Rules.
         * 2. Este bloque debe ser reemplazado por una llamada a una Cloud Function HTTPS Invocable:
         *    `FirebaseFunctions.getInstance().getHttpsCallable("processPayment").call(...)`
         *    La función validará el comprobante de Google Play/Stripe y actualizará el documento.
         */
        userRef.set(updateMap, SetOptions.merge())
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    SubscriptionHistoryManager.addRecordForUser(
                        uid = user.uid,
                        durationMillis = durationMillis,
                        planName = planName,
                        status = "Completado",
                        amount = price
                    )
                }
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Error desconocido")
            }
    }

    fun formatDuration(until: Long?): String {
        if (until == null || until == 0L) return "Vitalicio / Permanente"
        val diff = until - System.currentTimeMillis()
        if (diff <= 0) return "Expirado"

        val secondsTotal = diff / 1000
        val seconds = secondsTotal % 60
        val minutesTotal = secondsTotal / 60
        val minutes = minutesTotal % 60
        val hoursTotal = minutesTotal / 60
        val hours = hoursTotal % 24
        val daysTotal = hoursTotal / 24
        val days = daysTotal % 365
        val years = daysTotal / 365

        val parts = mutableListOf<String>()
        if (years > 0) parts.add("$years año" + if (years > 1L) "s" else "")
        if (days > 0) parts.add("$days día" + if (days > 1L) "s" else "")
        if (hours > 0) parts.add("$hours hora" + if (hours > 1L) "s" else "")
        if (minutes > 0) parts.add("$minutes minuto" + if (minutes > 1L) "s" else "")
        if (seconds > 0) parts.add("$seconds segundo" + if (seconds > 1L) "s" else "")

        return parts.joinToString(", ") + " restantes"
    }

    fun isExpiringSoon(): Boolean {
        if (_userRole.value == "admin") return false
        if (!_isPremium.value) return false
        val until = _premiumUntil.value ?: return false
        if (until == 0L) return false
        val diff = until - System.currentTimeMillis()
        // Consider expiring soon if less than 3 days (72 hours) and still positive
        return diff in 1..(3L * 24 * 60 * 60 * 1000L)
    }

    fun getRemainingPremiumTimeFormatted(): String {
        if (_userRole.value == "admin") return "Acceso Administrador (Vitalicio)"
        if (!_isPremium.value) return "Sin suscripción activa"
        val until = _premiumUntil.value ?: return "Activo (Permanente)"
        if (until == 0L) return "Activo (Permanente)"
        return formatDuration(until)
    }
}
