package com.example.util

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
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

        // Listen for real-time changes
        roleListener?.remove()
        roleListener = userRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("SubscriptionManager", "Error listening to role", error)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val role = snapshot.getString("role") ?: "free"
                _userRole.value = role
                _isPremium.value = role == "premium" || role == "admin"
            } else {
                // User document doesn't exist, create it with default "free" role
                val userData = hashMapOf(
                    "role" to "free",
                    "email" to (user.email ?: "")
                )
                userRef.set(userData)
                    .addOnSuccessListener {
                        Log.d("SubscriptionManager", "User document created with free role")
                        _userRole.value = "free"
                        _isPremium.value = false
                    }
                    .addOnFailureListener { e ->
                        Log.e("SubscriptionManager", "Error creating user document", e)
                    }
            }
        }
    }
}
