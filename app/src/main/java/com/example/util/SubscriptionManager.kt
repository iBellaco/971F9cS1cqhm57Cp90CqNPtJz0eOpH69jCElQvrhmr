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
                        "last_active" to System.currentTimeMillis()
                    )
                    userRef.set(userData, SetOptions.merge())
                } else {
                    userRef.set(hashMapOf("last_active" to System.currentTimeMillis()), SetOptions.merge())
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
                    _userRole.value = role
                    _isPremium.value = role == "premium" || role == "admin"
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
