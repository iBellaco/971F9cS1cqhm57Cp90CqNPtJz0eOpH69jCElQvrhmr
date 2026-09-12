import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    content = f.read()

old_is_expiring = """    fun isExpiringSoon(): Boolean {
        if (_userRole.value == "admin") return false"""

new_is_expiring = """    fun isExpiringSoon(): Boolean {
        if (_userRole.value == "admin" || _userRole.value == "moderador") return false"""

content = content.replace(old_is_expiring, new_is_expiring)

old_get_remaining = """    fun getRemainingPremiumTimeFormatted(): String {
        if (_userRole.value == "admin") return "Acceso Administrador (Vitalicio)"
        if (!_isPremium.value) return "Sin suscripción activa"
        val until = _premiumUntil.value ?: return "Activo (Permanente)"
        if (until == 0L) return "Activo (Permanente)"
        return formatDuration(until)
    }"""

new_get_remaining = """    fun getRemainingPremiumTimeFormatted(): String {
        if (_userRole.value == "admin") return "Acceso Administrador (Vitalicio)"
        if (_userRole.value == "moderador") return "Acceso Moderador (Vitalicio)"
        if (!_isPremium.value) return "Sin suscripción activa"
        val until = _premiumUntil.value ?: return "Activo (Permanente)"
        if (until == 0L) return "Activo (Permanente)"
        return formatDuration(until)
    }"""

content = content.replace(old_get_remaining, new_get_remaining)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(content)
