package com.example.ui.components.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.supabase.SupabaseClientManager
import com.example.data.supabase.model.*
import com.example.ui.theme.*
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AdminDatabaseViewerTab() {
    val scope = rememberCoroutineScope()
    var selectedTable by remember { mutableStateOf("wr_champions") }
    val tables = listOf("wr_champions", "wr_items", "wr_runes", "wr_spells")
    
    var isLoading by remember { mutableStateOf(false) }
    var tableData by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun fetchTableData() {
        isLoading = true
        errorMessage = null
        tableData = emptyList()
        scope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    val client = SupabaseClientManager.client
                    when (selectedTable) {
                        "wr_champions" -> client.postgrest.from(selectedTable).select().decodeList<WrChampionDto>().map { 
                            mapOf("ID" to it.id, "Nombre" to it.name, "Tags" to it.primaryRole) 
                        }
                        "wr_items" -> client.postgrest.from(selectedTable).select().decodeList<WrItemDto>().map { 
                            mapOf("ID" to it.id, "Nombre" to it.name, "Tipo" to it.category) 
                        }
                        "wr_runes" -> client.postgrest.from(selectedTable).select().decodeList<WrRuneDto>().map { 
                            mapOf("ID" to it.id, "Nombre" to it.name, "Rama" to it.category) 
                        }
                        "wr_spells" -> client.postgrest.from(selectedTable).select().decodeList<WrSpellDto>().map { 
                            mapOf("ID" to it.id, "Nombre" to it.name, "Cooldown" to it.cooldown) 
                        }
                        else -> emptyList()
                    }
                }
                tableData = data
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(selectedTable) {
        fetchTableData()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.List, contentDescription = null, tint = HextechGold, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Visor de Base de Datos", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { fetchTableData() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refrescar", tint = HextechCyan)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ScrollableTabRow(
            selectedTabIndex = tables.indexOf(selectedTable),
            containerColor = HextechSurface,
            contentColor = HextechCyan,
            edgePadding = 0.dp
        ) {
            tables.forEach { table ->
                Tab(
                    selected = selectedTable == table,
                    onClick = { selectedTable = table },
                    text = { Text(table.removePrefix("wr_").replaceFirstChar { it.uppercase() }) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = HextechCyan)
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $errorMessage", color = DangerRed)
            }
        } else if (tableData.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("La tabla está vacía.", color = TextMuted)
            }
        } else {
            Text("Total registros: ${tableData.size}", color = TextMuted, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tableData) { row ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
                            row.forEach { (key, value) ->
                                Row {
                                    Text("$key: ", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    Text(value, color = TextPrimary, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
