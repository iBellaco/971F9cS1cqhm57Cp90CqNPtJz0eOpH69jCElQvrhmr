package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.supabase.SupabaseClientManager
import com.example.data.supabase.SupabaseItemDiagnostics
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class TestData(val name: String, val timestamp: Long)

@Composable
fun DatabaseTestScreen(onContinue: () -> Unit = {}) {
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("Listo para ejecutar diagnósticos.") }
    var isRunning by remember { mutableStateOf(false) }
    var diagnosticLog by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HextechDarkBg)
            .padding(16.dp)
    ) {
        Text(
            text = "Panel de Diagnóstico de Supabase",
            color = HextechGold,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Inspección de conexión y validación de URLs de iconos de objetos",
            color = TextMuted,
            fontSize = 12.5.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        try {
                            isRunning = true
                            status = "Probando conexión..."
                            val client = SupabaseClientManager.client
                            status = "✅ Supabase Client inicializado correctamente: ${client.supabaseUrl}"
                        } catch (e: Exception) {
                            status = "❌ Error: ${e.message}"
                        } finally {
                            isRunning = false
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Test Conexión", fontSize = 12.sp, color = TextPrimary)
            }

            Button(
                onClick = {
                    scope.launch {
                        try {
                            isRunning = true
                            status = "Ejecutando diagnóstico de iconos en Supabase (tabla wr_items)..."
                            val result = SupabaseItemDiagnostics.diagnoseItemIconUrls(logToLogcat = true)
                            if (result.isSuccess) {
                                val report = result.getOrThrow()
                                status = "✅ Diagnóstico completado: ${report.validCount}/${report.totalItemsChecked} válidos, ${report.issueCount} anomalías."
                                diagnosticLog = report.fullLogOutput
                            } else {
                                status = "❌ Falló el diagnóstico: ${result.exceptionOrNull()?.message}"
                            }
                        } catch (e: Exception) {
                            status = "❌ Error: ${e.message}"
                        } finally {
                            isRunning = false
                        }
                    }
                },
                modifier = Modifier.weight(1.3f),
                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.BugReport, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Diagnosticar Iconos", fontSize = 12.sp, color = HextechDarkBg, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechGold),
            border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Text("Continuar a la App")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Estado: $status",
            color = if (status.startsWith("❌")) Color(0xFFEF4444) else if (status.startsWith("✅")) Color(0xFF10B981) else HextechCyan,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Console log box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF0B111A))
                .border(1.dp, HextechCyan.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            if (diagnosticLog != null) {
                Text(
                    text = diagnosticLog!!,
                    color = Color(0xFF94A3B8),
                    fontSize = 11.5.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.verticalScroll(scrollState)
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Presiona 'Diagnosticar Iconos' para inspeccionar todas las URLs de wr_items de Supabase y detectar extensiones faltantes, prefijos erróneos o fallos de renderizado.",
                        color = TextMuted,
                        fontSize = 12.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
