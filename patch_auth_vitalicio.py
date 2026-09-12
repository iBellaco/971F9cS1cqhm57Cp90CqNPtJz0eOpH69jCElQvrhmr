import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    content = f.read()

old_auth_text = """                                    userRole == "admin" -> "Acceso vitalicio ilimitado a todas las funciones"
                                    userRole == "moderador" -> "Acceso a panel de soporte y OCR"
                                    isPremium -> "⏳ $remainingFormatted" """

new_auth_text = """                                    userRole == "admin" -> "Acceso vitalicio ilimitado a todas las funciones"
                                    userRole == "moderador" -> "Premium Vitalicio + Panel de Moderación"
                                    isPremium -> "⏳ $remainingFormatted" """

content = content.replace(old_auth_text, new_auth_text)

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(content)
