import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

old_upload = """                                if (isVideo) {
                                    val permanentLocalUrl = com.example.util.NoticeMediaStorageManager.saveMediaToInternalStorage(context, pickedUri, isVideo = true)
                                    videoUrl = permanentLocalUrl
                                    Toast.makeText(context, "Video guardado de forma permanente en el dispositivo", Toast.LENGTH_SHORT).show()
                                } else {"""

new_upload = """                                if (isVideo) {
                                    Toast.makeText(context, "Subiendo video a la nube, por favor espera...", Toast.LENGTH_LONG).show()
                                    val cloudVideoUrl = com.example.util.NoticeMediaStorageManager.uploadVideoToCloud(context, pickedUri)
                                    videoUrl = cloudVideoUrl
                                    Toast.makeText(context, "Video subido y procesado correctamente", Toast.LENGTH_SHORT).show()
                                } else {"""

content = content.replace(old_upload, new_upload)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
