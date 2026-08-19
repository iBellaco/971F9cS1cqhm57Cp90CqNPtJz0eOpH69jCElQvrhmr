package com.example.data.auth

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    val auth: FirebaseAuth? by lazy {
        try {
            val instance = FirebaseAuth.getInstance()
            // Bypass App Verification / reCAPTCHA in emulator to prevent GMS crashes
            instance.firebaseAuthSettings.setAppVerificationDisabledForTesting(true)
            instance
        } catch (e: Exception) {
            Log.e("AuthRepository", "Firebase not initialized", e)
            null
        }
    }

    val isUserLoggedIn: Boolean
        get() = auth?.currentUser != null

    suspend fun signIn(email: String, password: String): Result<AuthResult> {
        return try {
            val result = auth?.signInWithEmailAndPassword(email, password)?.await()
            if (result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception("Fallo al iniciar sesión. Firebase no inicializado."))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign-in failed", e)
            Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<AuthResult> {
        return try {
            val result = auth?.createUserWithEmailAndPassword(email, password)?.await()
            if (result != null) {
                Result.success(result)
            } else {
                Result.failure(Exception("Fallo al registrar. Firebase no inicializado."))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign-up failed", e)
            Result.failure(e)
        }
    }

    fun signOut() {
        auth?.signOut()
    }
}
