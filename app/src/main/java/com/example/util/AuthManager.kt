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
}
