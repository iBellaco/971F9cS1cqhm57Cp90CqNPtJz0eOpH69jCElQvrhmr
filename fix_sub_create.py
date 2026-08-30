import re
with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

target = """                    val userData = hashMapOf(
                        "role" to "free",
                        "email" to (user.email ?: ""),
                        "last_active" to System.currentTimeMillis()
                    )
                    userRef.set(userData, SetOptions.merge())
                } else {
                    userRef.set(hashMapOf("last_active" to System.currentTimeMillis()), SetOptions.merge())"""

replacement = """                    val userData = hashMapOf(
                        "role" to "free",
                        "email" to (user.email ?: ""),
                        "name" to (user.displayName ?: ""),
                        "last_active" to System.currentTimeMillis()
                    )
                    userRef.set(userData, SetOptions.merge())
                } else {
                    val updateData = hashMapOf<String, Any>("last_active" to System.currentTimeMillis())
                    if (user.displayName?.isNotBlank() == true) {
                         // Only if we don't already have a name in the snapshot, or just rely on what is already in DB.
                         // Actually, we shouldn't overwrite the DB name if the DB already exists, because the admin might have changed it.
                    }
                    userRef.set(updateData, SetOptions.merge())"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
print("Updated SubscriptionManager creation")
