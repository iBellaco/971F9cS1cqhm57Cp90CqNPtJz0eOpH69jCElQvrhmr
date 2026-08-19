package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.util.tr
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.example.util.AppLogger
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

@Composable
fun DatabaseTestScreen(onContinue: () -> Unit) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    val logs by com.example.util.AppLogger.logs.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        AppLogger.d("DB_TEST", "Test panel loaded and logs initialized.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HextechDarkBg)
            .padding(16.dp)
    ) {
        Text(
            text = tr("Firestore & Logs Test Panel"),
            style = MaterialTheme.typography.headlineMedium,
            color = HextechGold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = {
                    isLoading = true
                    scope.launch {
                        try {
                            val db = FirebaseFirestore.getInstance()
                            val testDoc = hashMapOf(
                                "timestamp" to System.currentTimeMillis(),
                                "message" to "Connection Test Successful"
                            )
                            db.collection("TestConnection").document("test_doc").set(testDoc).await()
                            AppLogger.d("DB_TEST", "Successfully wrote test document to Firestore.")
                            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            
                            if (e is SecurityException) {
                                AppLogger.e("DB_TEST", "Ignored System SecurityException: ${e.message}")
                            } else {
                                AppLogger.e("DB_TEST", "Error adding data to Firestore", e)
                            }
                        } finally {
                            isLoading = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
            ) {
                Text(tr("Add Data"))
            }

            Button(
                onClick = {
                    isLoading = true
                    scope.launch {
                        try {
                            val db = FirebaseFirestore.getInstance()
                            val snapshot = db.collection("TestConnection").document("test_doc").get().await()
                            if (snapshot.exists()) {
                                AppLogger.d("DB_TEST", "Read success: \${snapshot.data}")
                                Toast.makeText(context, "Data Read Success", Toast.LENGTH_SHORT).show()
                            } else {
                                AppLogger.d("DB_TEST", "Document does not exist.")
                            }
                        } catch (e: Exception) {
                            AppLogger.e("DB_TEST", "Error reading data from Firestore", e)
                        } finally {
                            isLoading = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
            ) {
                Text(tr("Read Data"))
            }

            Button(
                onClick = {
                    isLoading = true
                    scope.launch {
                        try {
                            val db = FirebaseFirestore.getInstance()
                            db.collection("TestConnection").document("test_doc").delete().await()
                            AppLogger.d("DB_TEST", "Successfully deleted test document.")
                            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            AppLogger.e("DB_TEST", "Error deleting data from Firestore", e)
                        } finally {
                            isLoading = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = HextechGold)
            ) {
                Text(tr("Delete Data"))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { onContinue() }) {
                Text(tr("Continuar a la App"))
            }
            Button(onClick = {
                val allLogs = logs.joinToString("\n")
                clipboardManager.setText(AnnotatedString(allLogs))
                Toast.makeText(context, "Logs Copied", Toast.LENGTH_SHORT).show()
            }) {
                Text(tr("Copy Logs"))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(color = HextechGold)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(tr("Logs:"), color = HextechGold, style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        ) {
            items(logs) { log ->
                Text(text = log, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                HorizontalDivider(color = HextechGold.copy(alpha = 0.2f))
            }
        }
    }
}
