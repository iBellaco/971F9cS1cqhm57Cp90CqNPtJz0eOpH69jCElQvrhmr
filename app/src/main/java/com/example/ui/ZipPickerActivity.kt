package com.example.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.data.sync.ZipDatasetManager
import com.example.service.screen.LocalVisionAnalyzer
import com.example.util.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Actividad transparente para permitir al usuario seleccionar e importar el archivo ZIP
 * con el dataset de campeones directamente desde el asistente flotante o la app principal.
 */
class ZipPickerActivity : ComponentActivity() {

    private val openDocumentLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        handleSelectedZipUri(uri)
    }

    private val getContentLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        handleSelectedZipUri(uri)
    }

    private fun handleSelectedZipUri(uri: android.net.Uri?) {
        if (uri != null) {
            Toast.makeText(this, "Descomprimiendo e importando dataset ZIP...", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                val success = ZipDatasetManager.importFromUri(applicationContext, uri)
                withContext(Dispatchers.Main) {
                    if (success) {
                        LocalVisionAnalyzer.reloadFromExtractedDataset(applicationContext)
                        Toast.makeText(
                            applicationContext,
                            "¡Dataset ZIP descomprimido y activado como nuevo dataset local!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error al descomprimir el archivo ZIP. Verifica que contenga imágenes.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Selección de ZIP cancelada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            // Intentar con OpenDocument primero para acceso persistente al archivo .zip
            val mimeTypes = arrayOf(
                "application/zip",
                "application/x-zip-compressed",
                "application/octet-stream",
                "*/*"
            )
            openDocumentLauncher.launch(mimeTypes)
        } catch (e: Exception) {
            try {
                getContentLauncher.launch("*/*")
            } catch (e2: Exception) {
                AppLogger.e("ZipPickerActivity", "Error lanzando selector de archivos", e2)
                Toast.makeText(this, "No se pudo abrir el explorador de archivos", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
