with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'r') as f:
    text = f.read()

target = """        notificationManager.notify(Random.nextInt(), notificationBuilder.build())"""

replacement = """        try {
            notificationManager.notify(Random.nextInt(), notificationBuilder.build())
        } catch (e: SecurityException) {
            Log.e("PushListener", "Missing POST_NOTIFICATIONS permission", e)
        }"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'w') as f:
    f.write(text)
print("Fixed SecurityException in PushNotificationListener")
