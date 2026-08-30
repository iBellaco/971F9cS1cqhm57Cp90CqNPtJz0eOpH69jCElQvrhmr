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

object SubscriptionManager {
    private val _userRole = MutableStateFlow("free")
    val userRole: StateFlow<String> = _userRole.asStateFlow()
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _isBanned = MutableStateFlow(false)
    val isBanned: StateFlow<Boolean> = _isBanned.asStateFlow()

    private val _isPremium = MutableStateFlow(false)
    val isPremium: StateFlow<Boolean> = _isPremium.asStateFlow()

    private val _currentAvatarId = MutableStateFlow("default_poro")
    val currentAvatarId: StateFlow<String> = _currentAvatarId.asStateFlow()

    private val _unlockedAvatars = MutableStateFlow<List<String>>(emptyList())
    val unlockedAvatars: StateFlow<List<String>> = _unlockedAvatars.asStateFlow()

    private var roleListener: ListenerRegistration? = null

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
                    val role = listenSnapshot.getString("role") ?: "free"
                    val banned = listenSnapshot.getBoolean("banned") ?: false
                    val name = listenSnapshot.getString("name") ?: ""
                    val avatarId = listenSnapshot.getString("avatarId") ?: "default_poro"
                    @Suppress("UNCHECKED_CAST")
                    val unlocked = listenSnapshot.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")

                    if (name.isNotBlank()) {
                        _userName.value = name
                    }
                    _userRole.value = role
                    _isBanned.value = (role == "banned" || banned)
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem
                    _currentAvatarId.value = avatarId
                    _unlockedAvatars.value = unlocked
                } else {
                    _userName.value = ""
                    _userRole.value = "free"
                    _isPremium.value = false
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

    fun upgradeToPremium() {
        val user = AuthManager.getAuth()?.currentUser ?: return
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        
        userRef.set(hashMapOf("role" to "premium"), SetOptions.merge())
            .addOnSuccessListener {
                Log.d("SubscriptionManager", "Successfully upgraded to premium")
            }
            .addOnFailureListener {
                Log.e("SubscriptionManager", "Failed to upgrade", it)
            }
    }
}
