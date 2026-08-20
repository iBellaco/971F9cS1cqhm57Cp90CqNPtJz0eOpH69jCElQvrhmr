package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.supabase.SupabaseClientManager
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class TestData(val name: String, val timestamp: Long)

@Composable
fun DatabaseTestScreen(onContinue: () -> Unit = {}) {
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("Ready") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Supabase Test Panel", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                try {
                    status = "Testing..."
                    val client = SupabaseClientManager.client
                    // Just a basic check to see if we can query, we might not have a test_table though
                    status = "Supabase Client Initialized Successfully."
                } catch (e: Exception) {
                    status = "Error: ${e.message}"
                }
            }
        }) {
            Text("Test Supabase Connection")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onContinue) { Text("Continuar a la App") }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Status: $status")
    }
}
