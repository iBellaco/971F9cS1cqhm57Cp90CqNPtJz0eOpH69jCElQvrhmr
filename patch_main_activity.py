import re

file_path = "app/src/main/java/com/example/MainActivity.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

import_firebase = "import com.google.firebase.FirebaseApp\n"
if "import com.google.firebase.FirebaseApp" not in content:
    content = content.replace("import android.os.Bundle", f"import android.os.Bundle\n{import_firebase}")

init_code = """
        super.onCreate(savedInstanceState)
        try {
            FirebaseApp.initializeApp(this)
            com.example.util.AppLogger.d("APP", "Firebase initialized in MainActivity")
        } catch (e: Exception) {
            com.example.util.AppLogger.e("APP", "Firebase init failed in MainActivity", e)
        }
"""
content = content.replace("super.onCreate(savedInstanceState)", init_code)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
