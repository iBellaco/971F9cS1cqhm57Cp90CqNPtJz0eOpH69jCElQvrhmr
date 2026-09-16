package com.example.data.sync

import android.content.Context
import android.graphics.*
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.util.Random

data class AugmentorResultSummary(
    val isRunning: Boolean,
    val progressText: String,
    val totalFound: Int,
    val processedCount: Int,
    val errorCount: Int,
    val logs: List<String>,
    val isFinished: Boolean
)

object DatasetAugmentor {
    private val _augmentorState = MutableStateFlow(
        AugmentorResultSummary(
            isRunning = false,
            progressText = "Listo para procesar carpeta de dataset...",
            totalFound = 0,
            processedCount = 0,
            errorCount = 0,
            logs = emptyList(),
            isFinished = false
        )
    )
    val augmentorState: StateFlow<AugmentorResultSummary> = _augmentorState.asStateFlow()

    private fun addLog(logs: MutableList<String>, msg: String) {
        logs.add(msg)
        if (logs.size > 150) logs.removeAt(0)
        _augmentorState.value = _augmentorState.value.copy(logs = logs.toList())
    }

    private fun generateNegative(src: Bitmap): Bitmap {
        val bmp = src.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(bmp)
        val paint = Paint()
        val matrix = ColorMatrix(floatArrayOf(
            -1f,  0f,  0f,  0f, 255f,
             0f, -1f,  0f,  0f, 255f,
             0f,  0f, -1f,  0f, 255f,
             0f,  0f,  0f,  1f,   0f
        ))
        paint.colorFilter = ColorMatrixColorFilter(matrix)
        canvas.drawBitmap(src, 0f, 0f, paint)
        return bmp
    }

    private fun generateContrast(src: Bitmap): Bitmap {
        val bmp = src.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(bmp)
        val paint = Paint()
        val scale = 1.4f
        val translate = -20f
        val matrix = ColorMatrix(floatArrayOf(
            scale, 0f, 0f, 0f, translate,
            0f, scale, 0f, 0f, translate,
            0f, 0f, scale, 0f, translate,
            0f, 0f, 0f, 1f, 0f
        ))
        paint.colorFilter = ColorMatrixColorFilter(matrix)
        canvas.drawBitmap(src, 0f, 0f, paint)
        return bmp
    }

    private fun generateBlur(src: Bitmap): Bitmap {
        val w = src.width
        val h = src.height
        val small = Bitmap.createScaledBitmap(src, maxOf(4, w / 4), maxOf(4, h / 4), true)
        return Bitmap.createScaledBitmap(small, w, h, false)
    }

    private fun generatePixelated(src: Bitmap): Bitmap {
        val w = src.width
        val h = src.height
        val block = 8
        val small = Bitmap.createScaledBitmap(src, maxOf(2, w / block), maxOf(2, h / block), false)
        return Bitmap.createScaledBitmap(small, w, h, false)
    }

    private fun generateNoise(src: Bitmap): Bitmap {
        val bmp = src.copy(Bitmap.Config.ARGB_8888, true)
        val random = Random()
        val w = bmp.width
        val h = bmp.height
        val count = (w * h * 0.12).toInt()
        for (i in 0 until count) {
            val x = random.nextInt(w)
            val y = random.nextInt(h)
            val color = if (random.nextBoolean()) Color.WHITE else Color.BLACK
            bmp.setPixel(x, y, color)
        }
        return bmp
    }

    suspend fun processFolder(context: Context, treeUri: Uri) {
        withContext(Dispatchers.IO) {
            val logs = mutableListOf<String>()
            addLog(logs, "Iniciando lectura de carpeta raíz...")

            val rootDir = DocumentFile.fromTreeUri(context, treeUri)
            if (rootDir == null || !rootDir.isDirectory) {
                addLog(logs, "[ERROR] No se pudo acceder a la carpeta seleccionada.")
                _augmentorState.value = AugmentorResultSummary(
                    isRunning = false,
                    progressText = "Error: Carpeta inválida",
                    totalFound = 0,
                    processedCount = 0,
                    errorCount = 1,
                    logs = logs,
                    isFinished = true
                )
                return@withContext
            }

            val championFolders = rootDir.listFiles().filter { it.isDirectory }
            addLog(logs, "Se detectaron ${championFolders.size} carpetas de campeones (Esperados: 141).")

            _augmentorState.value = AugmentorResultSummary(
                isRunning = true,
                progressText = "Procesando campeones...",
                totalFound = championFolders.size,
                processedCount = 0,
                errorCount = 0,
                logs = logs,
                isFinished = false
            )

            var processed = 0
            var errors = 0

            for ((index, folder) in championFolders.withIndex()) {
                val champName = folder.name ?: "Unknown"
                try {
                    // Buscar avatar.png o cualquier imagen dentro de la carpeta del campeón
                    val avatarFile = folder.listFiles().find {
                        it.name?.lowercase()?.contains("avatar") == true ||
                        it.name?.lowercase()?.endsWith(".png") == true ||
                        it.name?.lowercase()?.endsWith(".jpg") == true
                    }

                    if (avatarFile == null) {
                        addLog(logs, "[AVISO] $champName: No se encontró avatar.png")
                        errors++
                        continue
                    }

                    addLog(logs, "[${index + 1}/${championFolders.size}] Procesando variantes para $champName...")

                    val inputStream = context.contentResolver.openInputStream(avatarFile.uri)
                    val originalBitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()

                    if (originalBitmap == null) {
                        addLog(logs, "[ERROR] $champName: No se pudo decodificar el avatar")
                        errors++
                        continue
                    }

                    // 1. Negative
                    val negBmp = generateNegative(originalBitmap)
                    saveVariant(context, folder, "negative.png", negBmp, Bitmap.CompressFormat.PNG, 100)

                    // 2. Contrast
                    val conBmp = generateContrast(originalBitmap)
                    saveVariant(context, folder, "contrast.png", conBmp, Bitmap.CompressFormat.PNG, 100)

                    // 3. Blur
                    val blurBmp = generateBlur(originalBitmap)
                    saveVariant(context, folder, "blur.png", blurBmp, Bitmap.CompressFormat.PNG, 100)

                    // 4. Pixelated
                    val pixBmp = generatePixelated(originalBitmap)
                    saveVariant(context, folder, "pixelated.png", pixBmp, Bitmap.CompressFormat.PNG, 100)

                    // 5. Noise
                    val noiseBmp = generateNoise(originalBitmap)
                    saveVariant(context, folder, "noise.png", noiseBmp, Bitmap.CompressFormat.PNG, 100)

                    // 6. Low Quality (JPEG compression strong)
                    saveVariant(context, folder, "low_quality.jpg", originalBitmap, Bitmap.CompressFormat.JPEG, 15)

                    processed++
                    _augmentorState.value = _augmentorState.value.copy(
                        processedCount = processed,
                        errorCount = errors,
                        progressText = "Procesado: $champName (${index + 1}/${championFolders.size})"
                    )
                    delay(20)
                } catch (e: Exception) {
                    errors++
                    addLog(logs, "[ERROR] $champName: ${e.message}")
                    _augmentorState.value = _augmentorState.value.copy(errorCount = errors)
                }
            }

            addLog(logs, "Proceso completado. $processed campeones aumentados con 6 variantes cada uno.")
            _augmentorState.value = AugmentorResultSummary(
                isRunning = false,
                progressText = "Proceso finalizado. $processed campeones procesados.",
                totalFound = championFolders.size,
                processedCount = processed,
                errorCount = errors,
                logs = logs,
                isFinished = true
            )
        }
    }

    private fun saveVariant(
        context: Context,
        folder: DocumentFile,
        fileName: String,
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        quality: Int
    ) {
        // Si ya existe, eliminar o sobrescribir
        val existing = folder.findFile(fileName)
        val fileDoc = existing ?: folder.createFile(if (format == Bitmap.CompressFormat.PNG) "image/png" else "image/jpeg", fileName)
        if (fileDoc != null) {
            val out: OutputStream? = context.contentResolver.openOutputStream(fileDoc.uri, "w")
            out?.use {
                bitmap.compress(format, quality, it)
            }
        }
    }
}
