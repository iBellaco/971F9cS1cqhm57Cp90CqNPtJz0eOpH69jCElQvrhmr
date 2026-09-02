import sys

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

target = """                        @Suppress("UNCHECKED_CAST")
                        val unlocked = doc.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")
                        UserRecord(doc.id, email, role, lastActive, name, avatarId, unlocked, premiumUntil)"""

replacement = """                        @Suppress("UNCHECKED_CAST")
                        val unlocked = doc.get("unlockedAvatars") as? List<String> ?: listOf("default_poro")
                        @Suppress("UNCHECKED_CAST")
                        val regDevices = doc.get("registeredDevices") as? List<String> ?: emptyList()
                        UserRecord(doc.id, email, role, lastActive, name, avatarId, unlocked, premiumUntil, regDevices)"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
