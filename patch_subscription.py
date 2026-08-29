import re

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    text = f.read()

if 'com.google.firebase.messaging.FirebaseMessaging' not in text:
    text = text.replace('import com.google.firebase.firestore.SetOptions', 'import com.google.firebase.firestore.SetOptions\nimport com.google.firebase.messaging.FirebaseMessaging')

subscribe_logic = """                    val role = listenSnapshot.getString("role") ?: "free"
                    _userRole.value = role
                    val isPrem = role == "premium" || role == "admin"
                    _isPremium.value = isPrem
                    
                    // Manage FCM Topic subscription for Premium users
                    if (isPrem) {
                        FirebaseMessaging.getInstance().subscribeToTopic("premium_meta_updates")
                            .addOnSuccessListener { Log.d("SubscriptionManager", "Subscribed to premium_meta_updates") }
                    } else {
                        FirebaseMessaging.getInstance().unsubscribeFromTopic("premium_meta_updates")
                            .addOnSuccessListener { Log.d("SubscriptionManager", "Unsubscribed from premium_meta_updates") }
                    }"""

text = re.sub(
    r'val role = listenSnapshot\.getString\("role"\) \?: "free"\s*_userRole\.value = role\s*_isPremium\.value = role == "premium" \|\| role == "admin"',
    subscribe_logic,
    text
)

free_unsubscribe = """            _userRole.value = "free"
            _isPremium.value = false
            FirebaseMessaging.getInstance().unsubscribeFromTopic("premium_meta_updates")"""
            
text = re.sub(r'_userRole\.value = "free"\s*_isPremium\.value = false(?![\s\S]*unsubscribeFromTopic)', free_unsubscribe, text)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.write(text)
