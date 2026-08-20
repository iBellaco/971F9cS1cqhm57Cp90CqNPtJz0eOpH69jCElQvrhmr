import os

filepath = "app/src/main/java/com/example/util/AppUpdateManager.kt"
with open(filepath, "r") as f:
    content = f.read()

content = content.replace("import com.google.firebase.firestore.FirebaseFirestore\n", "")
content = content.replace("Consulta tanto Firebase Firestore (colección 'app_config/update_info') como la API de GitHub Releases.", "Consulta la API de GitHub Releases.")

with open(filepath, "w") as f:
    f.write(content)
