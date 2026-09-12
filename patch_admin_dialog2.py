import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

old_is_admin = """    val isAdmin = userRole == "admin" || AuthManager.isCurrentUserAdmin()"""
new_is_admin = """    val isAdmin = userRole == "admin" || userRole == "moderador" || AuthManager.isCurrentUserAdmin()"""

content = content.replace(old_is_admin, new_is_admin)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
