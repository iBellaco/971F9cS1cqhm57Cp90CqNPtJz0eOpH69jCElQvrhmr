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
     * Advanced Root & Hooking Detection (Frida, Xposed, Magisk, Zygisk).
     */
    fun isDeviceRooted(): Boolean {
        return checkRootFiles() || checkTestKeys() || checkMagiskMounts() || isFridaOrHookingDetected()
    }

    private fun checkTestKeys(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && (buildTags.contains("test-keys") || buildTags.contains("release-keys") == false && buildTags.contains("debug"))
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
            "/su/bin/su",
            "/data/local/tmp/su",
            "/system/xbin/daemonsu"
        )
        for (path in paths) {
            try {
                if (File(path).exists()) {
                    return true
                }
            } catch (_: Exception) {}
        }
        return false
    }

    private fun checkMagiskMounts(): Boolean {
        return try {
            val mounts = File("/proc/mounts").readText()
            mounts.contains("magisk") || mounts.contains("core/mirror") || mounts.contains("core/img")
        } catch (_: Exception) {
            false
        }
    }

    /**
     * OWASP MASVS: Anti-Hooking / Frida / Xposed Detection
     */
    fun isFridaOrHookingDetected(): Boolean {
        // 1. Check for Frida / Hooking artifacts in filesystem
        val fridaPaths = arrayOf(
            "/data/local/tmp/frida-server",
            "/data/local/tmp/re.frida.server",
            "/data/local/tmp/frida",
            "/system/lib/libgadget.so",
            "/data/local/tmp/libgadget.so"
        )
        for (p in fridaPaths) {
            try {
                if (File(p).exists()) return true
            } catch (_: Exception) {}
        }

        // 2. Check /proc/self/maps for injected hooking libraries
        try {
            val maps = File("/proc/self/maps").readLines()
            for (line in maps) {
                val lower = line.lowercase()
                if (lower.contains("frida") || lower.contains("xposed") || lower.contains("substrate") || lower.contains("gadget")) {
                    return true
                }
            }
        } catch (_: Exception) {}

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
