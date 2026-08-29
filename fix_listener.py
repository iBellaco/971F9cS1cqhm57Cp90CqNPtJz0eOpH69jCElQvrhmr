with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'r') as f:
    text = f.read()

target = """    private var startTime = System.currentTimeMillis()
    fun startListening(context: Context) {"""

replacement = """    private var startTime = System.currentTimeMillis()
    private val processedIds = mutableSetOf<String>()
    
    fun startListening(context: Context) {"""

text = text.replace(target, replacement)

target2 = """        startTime = System.currentTimeMillis()
        FirebaseFirestore.getInstance().collection("global_notifications")"""

replacement2 = """        startTime = System.currentTimeMillis() - (5 * 60 * 1000)
        FirebaseFirestore.getInstance().collection("global_notifications")"""

text = text.replace(target2, replacement2)

with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'w') as f:
    f.write(text)
print("Done")
