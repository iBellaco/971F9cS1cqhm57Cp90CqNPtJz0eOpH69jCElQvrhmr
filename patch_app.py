import sys

with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    content = f.read()

target = """        com.example.util.CrashLogger.init(this)
        
        // --- Firebase App Check (Play Integrity) ---"""

replacement = """        com.example.util.CrashLogger.init(this)
        
        try {
            com.google.android.gms.ads.MobileAds.initialize(this) {}
            AppLogger.d("APP", "AdMob initialized.")
        } catch (e: Exception) {
            AppLogger.e("APP", "Error initializing AdMob", e)
        }
        
        // --- Firebase App Check (Play Integrity) ---"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
        f.write(content)
    print("Updated WildRiftApp.kt")
else:
    print("Target not found in WildRiftApp.kt")

