with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'r') as f:
    text = f.read()

target = """                for (dc in snapshots!!.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {"""

replacement = """                Log.d("PushListener", "Snapshot received. Changes size: ${snapshots?.documentChanges?.size}")
                for (dc in snapshots!!.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        Log.d("PushListener", "Added document: ${dc.document.data}")"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'w') as f:
    f.write(text)
print("Fixed PushNotificationListener Logs")
