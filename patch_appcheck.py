import re

with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

replacement = """
        super.onCreate()
        
        // --- Firebase App Check (Play Integrity) ---
        try {
            com.google.firebase.FirebaseApp.initializeApp(this)
            val firebaseAppCheck = com.google.firebase.appcheck.FirebaseAppCheck.getInstance()
            firebaseAppCheck.installAppCheckProviderFactory(
                com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory.getInstance()
            )
            AppLogger.d("APP", "Firebase App Check (Play Integrity) initialized.")
        } catch (e: Exception) {
            AppLogger.e("APP", "Error initializing Firebase App Check", e)
        }
"""

text = text.replace("super.onCreate()", replacement.strip())

with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
    f.write(text)
