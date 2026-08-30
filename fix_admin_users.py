import re
with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

target = """                    val role = doc.getString("role") ?: "free"
                    val lastActive = doc.getLong("last_active") ?: 0L
                    UserRecord(doc.id, email, role, lastActive)"""

replacement = """                    val role = doc.getString("role") ?: "free"
                    val lastActive = doc.getLong("last_active") ?: 0L
                    val name = doc.getString("name") ?: ""
                    UserRecord(doc.id, email, role, lastActive, name)"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
print("Updated loadUsers")
