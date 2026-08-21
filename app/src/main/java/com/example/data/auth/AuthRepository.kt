package com.example.data.auth

import android.util.Log
import com.example.data.supabase.SupabaseClientManager
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    
    // We use Supabase Auth now
    val isUserLoggedIn: Boolean
        get() = SupabaseClientManager.client.auth.currentUserOrNull() != null
        
    fun getCurrentUser(): UserInfo? {
        return SupabaseClientManager.client.auth.currentUserOrNull()
    }

    suspend fun signIn(email: String, password: String): Result<UserInfo> {
        return withContext(Dispatchers.IO) {
            try {
                SupabaseClientManager.client.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                val user = SupabaseClientManager.client.auth.currentUserOrNull()
                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("Fallo al iniciar sesión en Supabase."))
                }
            } catch (e: Exception) {
                Log.e("AuthRepository", "Sign-in failed", e)
                Result.failure(e)
            }
        }
    }

    suspend fun signUp(email: String, password: String): Result<UserInfo> {
        return withContext(Dispatchers.IO) {
            try {
                SupabaseClientManager.client.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                }
                val user = SupabaseClientManager.client.auth.currentUserOrNull()
                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("Fallo al registrar en Supabase."))
                }
            } catch (e: Exception) {
                Log.e("AuthRepository", "Sign-up failed", e)
                Result.failure(e)
            }
        }
    }

    suspend fun signOut() {
        withContext(Dispatchers.IO) {
            try {
                SupabaseClientManager.client.auth.signOut()
            } catch (e: Exception) {
                Log.e("AuthRepository", "Sign-out failed", e)
            }
        }
    }
}
