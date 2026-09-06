import sys
with open("app/src/main/java/com/example/util/DeviceAndSessionManager.kt", "r") as f:
    content = f.read()

old_code = """        val userRef = db.collection("users").document(user.uid)
        val isAdmin = AuthManager.isCurrentUserAdmin()

        userRef.get().addOnSuccessListener { snapshot ->
            val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()"""

new_code = """        val userRef = db.collection("users").document(user.uid)

        userRef.get().addOnSuccessListener { snapshot ->
            val dbRole = snapshot.getString("role") ?: "free"
            val isAdmin = dbRole == "admin" || AuthManager.isCurrentUserAdmin()
            val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()"""

content = content.replace(old_code, new_code)
with open("app/src/main/java/com/example/util/DeviceAndSessionManager.kt", "w") as f:
    f.write(content)
