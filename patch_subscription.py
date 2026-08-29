import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

# Reemplazar todo el bloque "Manage FCM Topic subscription" hasta el "else" con nada o comentario
pattern = r"// Manage FCM Topic subscription for Premium users.*?if \(isPrem\) \{.*?\.addOnSuccessListener \{ Log\.d\(\"SubscriptionManager\", \"Subscribed to premium_meta_updates\"\).*?\} else \{.*?\.addOnSuccessListener \{ Log\.d\(\"SubscriptionManager\", \"Unsubscribed from premium_meta_updates\"\).*?\}"
text = re.sub(pattern, "// Manejado ahora por PushNotificationListener nativo", text, flags=re.DOTALL)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
