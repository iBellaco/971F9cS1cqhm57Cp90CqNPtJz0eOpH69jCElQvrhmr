package com.example.util

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

object AuthManager {
    fun isFirebaseInitialized(): Boolean {
        return try {
            FirebaseApp.getInstance()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuth(): FirebaseAuth? {
        return if (isFirebaseInitialized()) {
            FirebaseAuth.getInstance()
        } else {
            null
        }
    }

    fun isAdminEmail(email: String?): Boolean {
        if (email.isNullOrBlank()) return false
        val clean = email.trim().lowercase()
        return clean == "barbadiego695@gmail.com" || 
               clean == "barbachavezdiego@gmail.com" ||
               clean.startsWith("barbadiego") ||
               clean.startsWith("barbachavez")
    }

    fun isCurrentUserAdmin(): Boolean {
        val user = getAuth()?.currentUser ?: return false
        if (isAdminEmail(user.email)) return true
        return SubscriptionManager.userRole.value == "admin"
    }
}

