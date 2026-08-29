with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'r') as f:
    text = f.read()

target = """        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)"""

replacement = """        Log.d("PushListener", "Showing local notification: $title - $body")
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'w') as f:
    f.write(text)
print("Fixed PushNotificationListener")
