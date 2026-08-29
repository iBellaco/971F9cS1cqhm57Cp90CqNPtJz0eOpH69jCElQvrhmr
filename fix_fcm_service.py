with open('app/src/main/java/com/example/service/FCMService.kt', 'r') as f:
    text = f.read()

target = """        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // Use the app's default icon
            .setContentTitle(title)"""

replacement = """        Log.d("FCMService", "Showing FCM notification: $title - $body")
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/service/FCMService.kt', 'w') as f:
    f.write(text)
print("Fixed FCMService Logs")
