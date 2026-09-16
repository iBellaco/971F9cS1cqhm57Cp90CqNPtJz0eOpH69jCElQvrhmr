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

    private val zipPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            Toast.makeText(this, "Procesando archivo ZIP del dataset...", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                val success = ZipDatasetManager.importFromUri(applicationContext, uri)
                withContext(Dispatchers.Main) {
                    if (success) {
                        LocalVisionAnalyzer.reloadFromExtractedDataset(applicationContext)
                        Toast.makeText(
                            applicationContext,
                            "¡Dataset ZIP importado y sincronizado con éxito!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error al extraer el archivo ZIP. Verifica el formato.",
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
            zipPickerLauncher.launch("*/*")
        } catch (e: Exception) {
            AppLogger.e("ZipPickerActivity", "Error lanzando selector de archivos", e)
            Toast.makeText(this, "No se pudo abrir el selector de archivos", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
