with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

target = """                                val data = hashMapOf(
                                    "title" to pushTitle,
                                    "body" to pushBody,
                                    "target" to pushTarget,
                                    "createdAt" to System.currentTimeMillis()
                                )"""

replacement = """                                val data = hashMapOf(
                                    "title" to pushTitle,
                                    "body" to pushBody,
                                    "target" to pushTarget,
                                    "targetTier" to pushTarget,
                                    "isPremiumTarget" to (pushTarget == "premium"),
                                    "createdAt" to System.currentTimeMillis()
                                )"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
print("Fixed AdminDashboardDialog")
