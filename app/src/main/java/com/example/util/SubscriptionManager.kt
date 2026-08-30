package com.example.util

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
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

    private var roleListener: ListenerRegistration? = null

    fun init(context: Context) {
        val auth = AuthManager.getAuth()
        val user = auth?.currentUser

        if (user == null) {
            _userRole.value = "free"
            _isPremium.value = false
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
                    // Create if it doesn't exist. Use SetOptions.merge() just in case.
                    val userData = hashMapOf(
                        "role" to "free",
                        "email" to (user.email ?: ""),
                        "name" to (user.displayName ?: ""),
                        "last_active" to System.currentTimeMillis()
                    )
                    userRef.set(userData, SetOptions.merge())
                } else {
                    val updateData = hashMapOf<String, Any>("last_active" to System.currentTimeMillis())
                    if (user.displayName?.isNotBlank() == true) {
                         // Only if we don't already have a name in the snapshot, or just rely on what is already in DB.
                         // Actually, we shouldn't overwrite the DB name if the DB already exists, because the admin might have changed it.
                    }
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
                    _userRole.value = role
                    _isBanned.value = (role == "banned" || banned)
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem
                    

                } else {
                                _userRole.value = "free"
            _isPremium.value = false
                }
            }
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
