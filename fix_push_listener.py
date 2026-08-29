with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'r') as f:
    text = f.read()

target = """    private var startTime = System.currentTimeMillis()

    fun startListening(context: Context) {
        if (isListening) return
        isListening = true
        startTime = System.currentTimeMillis()
        FirebaseFirestore.getInstance().collection("global_notifications")
            .whereGreaterThan("createdAt", startTime)
            .addSnapshotListener { snapshots, e ->"""

replacement = """    private var startTime = System.currentTimeMillis()
    private val processedIds = mutableSetOf<String>()

    fun startListening(context: Context) {
        if (isListening) return
        isListening = true
        // Restar 5 minutos para evitar problemas de desincronización de reloj
        startTime = System.currentTimeMillis() - (5 * 60 * 1000)
        
        FirebaseFirestore.getInstance().collection("global_notifications")
            .whereGreaterThan("createdAt", startTime)
            .addSnapshotListener { snapshots, e ->"""

target2 = """                for (dc in snapshots!!.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        Log.d("PushListener", "Added document: ${dc.document.data}")"""

replacement2 = """                for (dc in snapshots!!.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        val docId = dc.document.id
                        if (processedIds.contains(docId)) continue
                        processedIds.add(docId)
                        
                        Log.d("PushListener", "Added document: ${dc.document.data}")"""

text = text.replace(target, replacement).replace(target2, replacement2)

with open('app/src/main/java/com/example/util/PushNotificationListener.kt', 'w') as f:
    f.write(text)
print("Updated PushNotificationListener")
