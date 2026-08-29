sed -i 's/val role: String/val role: String,\n    val lastActive: Long/g' app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt
sed -i 's/val role = doc.getString("role") ?: "free"/val role = doc.getString("role") ?: "free"\n                    val lastActive = doc.getLong("last_active") ?: 0L/g' app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt
sed -i 's/UserRecord(doc.id, email, role)/UserRecord(doc.id, email, role, lastActive)/g' app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt
