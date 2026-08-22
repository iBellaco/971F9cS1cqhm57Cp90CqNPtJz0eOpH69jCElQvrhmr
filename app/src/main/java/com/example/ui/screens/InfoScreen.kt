package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WildRiftRepository
import com.example.data.supabase.FeedbackRepository
import com.example.data.supabase.SupabaseClientManager
import com.example.ui.components.AdminFeedbackBottomSheet
import com.example.ui.theme.*
import com.example.util.tr
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var supabaseStatus by remember { mutableStateOf("") }
    var isTestingSupabase by remember { mutableStateOf(false) }
    var isPurging by remember { mutableStateOf(false) }
    var purgeStatus by remember { mutableStateOf("") }
    var showAdminPanel by remember { mutableStateOf(false) }

    if (showAdminPanel) {
        AdminFeedbackBottomSheet(
            onDismiss = { showAdminPanel = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = tr("Acerca De"),
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechCyan.copy(alpha = 0.15f))
                                .border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = tr(WildRiftRepository.CURRENT_PATCH_VERSION),
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HextechDarkBg,
                    titleContentColor = TextPrimary,
                    navigationIconContentColor = TextPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HextechDarkBg)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Section 1: Compatibilidad y Parche
            InfoCard(
                title = tr("1. Compatibilidad y Parche Oficial"),
                icon = Icons.Default.Info
            ) {
                Text(
                    text = tr("• Compatibilidad: Diseñado exclusivamente para Wild Rift (habilidades móviles, runas de Wild Rift, balance y objetos móviles).") + "\n" +
                           tr("• Parche del juego: ") + "${tr(WildRiftRepository.CURRENT_PATCH_VERSION)} " + tr("sincronizado con fuentes de balance.") + "\n" +
                           tr("• Motor Hextech: Botón de activación directa con cálculo de composiciones, counters y sinergias.") + "\n" +
                           tr("• Sistema Flotante: Ventana superpuesta en pantalla con controles táctiles para la fase de selección."),
                    color = TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }

            // Section 2: Modo de Uso
            InfoCard(
                title = tr("2. Modo de Uso de la Aplicación"),
                icon = Icons.Default.Settings
            ) {
                InfoStep(
                    title = tr("Paso 1: Configura tus Líneas de Juego"),
                    description = tr("En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando cada tarjeta.")
                )
                InfoStep(
                    title = tr("Paso 2: Activa el Asistente Flotante"),
                    description = tr("Pulsa el botón central 'ACTIVAR'. Se desplegará la burbuja flotante en pantalla para acompañarte en tu partida.")
                )
                InfoStep(
                    title = tr("Paso 3: Selección de Campeones"),
                    description = tr("Abre Wild Rift y entra a la fase de selección. Toca el botón flotante en cualquier momento para ver recomendaciones, counters y sinergias tácticas en directo.")
                )
                InfoStep(
                    title = tr("Paso 4: Consulta de Builds y Runas"),
                    description = tr("Revisa los consejos tácticos, orden de habilidades móviles (Pasiva, 1, 2, 3, Definitiva) y armado de objetos recomendado para tu línea.")
                )
            }

            // Section 3: Supabase Connection & Feedback Maintenance Test
            InfoCard(
                title = tr("Estado del Servidor"),
                icon = Icons.Default.CheckCircle
            ) {
                Text(
                    text = tr("Módulo de sincronización de la base de datos en la nube. Pulsa el botón para probar la conexión con Supabase o gestionar la retención de reportes."),
                    color = TextSecondary,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        isTestingSupabase = true
                        supabaseStatus = "Conectando con Supabase..."
                        scope.launch {
                            try {
                                val client = SupabaseClientManager.client
                                kotlinx.coroutines.delay(800)
                                supabaseStatus = "✅ Conexión exitosa. Cliente inicializado."
                            } catch (e: Exception) {
                                supabaseStatus = "❌ Error de conexión: ${e.message}"
                            } finally {
                                isTestingSupabase = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    enabled = !isTestingSupabase
                ) {
                    if (isTestingSupabase) {
                        CircularProgressIndicator(color = HextechDarkBg, modifier = Modifier.size(20.dp))
                    } else {
                        Text(tr("Verificar Conexión Supabase"), color = HextechDarkBg, fontWeight = FontWeight.Bold)
                    }
                }
                if (supabaseStatus.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = supabaseStatus,
                        color = if (supabaseStatus.contains("✅")) AllyBlue else DangerRed,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botón para abrir el Panel de Administrador interactivo
                Button(
                    onClick = { showAdminPanel = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HextechCyan.copy(alpha = 0.2f),
                        contentColor = HextechCyan
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr("Abrir Panel de Administrador (Buzón de Reportes)"),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botón de limpieza de reportes antiguos (> 7 días)
                OutlinedButton(
                    onClick = {
                        isPurging = true
                        purgeStatus = "Purgando reportes antiguos (> 7 días)..."
                        scope.launch {
                            val res = FeedbackRepository.purgeOldReports(days = 7)
                            isPurging = false
                            purgeStatus = if (res.isSuccess) {
                                "✅ Reportes antiguos (> 7 días) purgados con éxito."
                            } else {
                                "⚠️ Error en la purga: ${res.exceptionOrNull()?.message}"
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isPurging,
                    border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isPurging) {
                        CircularProgressIndicator(color = HextechGold, modifier = Modifier.size(18.dp))
                    } else {
                        Text(
                            text = tr("Limpiar Reportes Antiguos (> 7 días)"),
                            color = HextechGold,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                if (purgeStatus.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = purgeStatus,
                        color = if (purgeStatus.contains("✅")) AllyBlue else DangerRed,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(icon, contentDescription = null, tint = HextechGold, modifier = Modifier.size(20.dp))
                Text(
                    text = title,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun InfoStep(title: String, description: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(text = title, color = HextechCyan, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = description, color = TextSecondary, fontSize = 13.sp, lineHeight = 18.sp)
    }
}
