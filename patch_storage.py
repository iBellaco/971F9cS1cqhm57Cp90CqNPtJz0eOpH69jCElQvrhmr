import re

with open('app/src/main/java/com/example/util/NoticeMediaStorageManager.kt', 'r') as f:
    content = f.read()

old_func = """    suspend fun uploadVideoToCloud(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Iniciando subida de video a Firebase Storage...")
            val storageRef = FirebaseStorage.getInstance().reference
            val videoRef = storageRef.child("notice_videos/${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.mp4")
            videoRef.putFile(uri).await()
            val downloadUrl = videoRef.downloadUrl.await()
            Log.d(TAG, "Video subido exitosamente a la nube: $downloadUrl")
            downloadUrl.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error subiendo video a Firebase Storage: ${e.message}")
            // Si falla, intentamos hacer fallback al almacenamiento local
            saveMediaToInternalStorage(context, uri, isVideo = true)
        }
    }"""

new_func = """    suspend fun uploadVideoToCloud(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Iniciando subida de video a Firebase Storage...")
            val storageRef = FirebaseStorage.getInstance().reference
            val videoRef = storageRef.child("notice_videos/${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}.mp4")
            videoRef.putFile(uri).await()
            val downloadUrl = videoRef.downloadUrl.await()
            Log.d(TAG, "Video subido exitosamente a la nube: $downloadUrl")
            downloadUrl.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error subiendo video a Firebase Storage: ${e.message}")
            throw Exception("Firebase Storage no habilitado o sin reglas. Debes habilitarlo en tu consola de Firebase.")
        }
    }"""

content = content.replace(old_func, new_func)

with open('app/src/main/java/com/example/util/NoticeMediaStorageManager.kt', 'w') as f:
    f.write(content)
