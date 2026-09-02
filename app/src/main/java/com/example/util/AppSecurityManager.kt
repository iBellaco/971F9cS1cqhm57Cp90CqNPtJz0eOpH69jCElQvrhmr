package com.example.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Debug
import android.util.Log
import android.view.WindowManager
import java.io.File

object AppSecurityManager {
    
    private const val TAG = "AppSecurityManager"

    /**
     * OWASP MASVS: Resiliency Against Reverse Engineering (MSTG-RESILIENCE)
     * Basic Root Detection mechanism.
     */
    fun isDeviceRooted(): Boolean {
        return checkRootFiles() || checkTestKeys()
    }

    private fun checkTestKeys(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootFiles(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) {
                return true
            }
        }
        return false
    }

    /**
     * OWASP MASVS: Anti-Debugging
     */
    fun isDebuggerAttached(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }
    
    /**
     * OWASP MASVS: Emulator Detection
     */
    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.lowercase().contains("vbox")
                || Build.FINGERPRINT.lowercase().contains("test-keys")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT)
    }

    /**
     * OWASP MASVS: Prevent Screen Captures / UI Data Leakage
     * Appends FLAG_SECURE to the activity window, preventing screenshots and recent-apps preview leaks.
     */
    fun enableScreenProtection(activity: Activity) {
        try {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
            Log.i(TAG, "Screen protection (FLAG_SECURE) enabled.")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to set FLAG_SECURE", e)
        }
    }
    
    fun disableScreenProtection(activity: Activity) {
         try {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            Log.i(TAG, "Screen protection (FLAG_SECURE) disabled.")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to clear FLAG_SECURE", e)
        }
    }


    /**
     * OWASP MASVS: Secure Storage
     * Provides an instance of EncryptedSharedPreferences to store sensitive data (tokens, keys, etc.).
     */
    fun getEncryptedSharedPreferences(context: Context, prefName: String): android.content.SharedPreferences {
        return try {
            val masterKeyAlias = androidx.security.crypto.MasterKey.Builder(context)
                .setKeyScheme(androidx.security.crypto.MasterKey.KeyScheme.AES256_GCM)
                .build()

            androidx.security.crypto.EncryptedSharedPreferences.create(
                context,
                prefName,
                masterKeyAlias,
                androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            Log.w(TAG, "EncryptedSharedPreferences unavailable (e.g. Test/Robolectric or corrupted keystore). Falling back to standard SharedPreferences: ${e.message}")
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        }
    }

}
