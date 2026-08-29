package com.example.util

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SubscriptionManager {
    private const val PREFS_NAME = "subscription_prefs"
    private const val KEY_IS_PREMIUM = "is_premium_user"

    private val _isPremium = MutableStateFlow(false)
    val isPremium: StateFlow<Boolean> = _isPremium.asStateFlow()

    fun init(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val auth = AuthManager.getAuth()
        val uid = auth?.currentUser?.uid ?: "anonymous"
        _isPremium.value = prefs.getBoolean("${KEY_IS_PREMIUM}_$uid", false)
    }

    fun setPremium(context: Context, premium: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val auth = AuthManager.getAuth()
        val uid = auth?.currentUser?.uid ?: "anonymous"
        prefs.edit().putBoolean("${KEY_IS_PREMIUM}_$uid", premium).apply()
        _isPremium.value = premium
    }
}
