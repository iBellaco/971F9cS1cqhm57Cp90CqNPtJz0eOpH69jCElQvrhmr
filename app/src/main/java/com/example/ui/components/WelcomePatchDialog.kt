package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.WildRiftRepository
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.util.tr

@Composable
fun WelcomePatchDialog(
    onDismiss: () -> Unit,
    forceShow: Boolean = false
) {
    val context = LocalContext.current
    val patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION
    val prefsKey = "has_seen_welcome_patch_${patchVersion.replace(" ", "_")}"
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    
    var showDialog by remember { 
        mutableStateOf(forceShow || !sharedPrefs.getBoolean(prefsKey, false)) 
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                sharedPrefs.edit().putBoolean(prefsKey, true).apply()
                showDialog = false
                onDismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = true, 
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .wrapContentHeight()
                    .padding(vertical = 24.dp),
                shape = RoundedCornerShape(22.dp),
                color = HextechDarkBg,
                border = androidx.compose.foundation.BorderStroke(
                    width = 1.5.dp,
                    brush = Brush.linearGradient(listOf(HextechGold, HextechCyan, HextechGold))
                ),
                tonalElevation = 12.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header with Icon & Close button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    brush = Brush.radialGradient(listOf(HextechGold.copy(alpha = 0.3f), Color.Transparent)),
                                    shape = CircleShape
                                )
                                .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.RocketLaunch,
                                contentDescription = "Wild Rift Coach",
                                tint = HextechGold,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                sharedPrefs.edit().putBoolean(prefsKey, true).apply()
                                showDialog = false
                                onDismiss()
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = tr("¡Bienvenidos a Wild Rift Coach!"),
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tr("Estado del Meta: "),
                            color = Color(0xFF94A3B8),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechCyan.copy(alpha = 0.2f))
                                .border(0.8.dp, HextechCyan, RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = patchVersion,
                                color = HextechCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Patch Features List
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0C1322), RoundedCornerShape(12.dp))
                            .border(0.8.dp, Color(0xFF1E293B), RoundedCornerShape(12.dp))
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        PatchFeatureRow(
                            icon = Icons.Default.Sync,
                            tint = HextechCyan,
                            title = tr("Catálogo Completo de Objetos"),
                            description = tr("Sincronizado con wr-meta.com con descripciones, estadísticas y costes actualizados.")
                        )
                        HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                        PatchFeatureRow(
                            icon = Icons.Default.RocketLaunch,
                            tint = HextechGold,
                            title = tr("Overlay Flotante Táctico"),
                            description = tr("Asistente en tiempo real durante tu fase de selección y partida en Wild Rift.")
                        )
                        HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                        PatchFeatureRow(
                            icon = Icons.Default.Security,
                            tint = Color(0xFF10B981),
                            title = tr("Seguridad y Rendimiento"),
                            description = tr("Ícono adaptativo optimizado para todas las capas de personalización (Xiaomi/Samsung).")
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = {
                            sharedPrefs.edit().putBoolean(prefsKey, true).apply()
                            showDialog = false
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = tr("¡Comenzar / Entendido!"),
                            color = HextechDarkBg,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PatchFeatureRow(
    icon: ImageVector,
    tint: Color,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(tint.copy(alpha = 0.15f), CircleShape)
                .border(0.8.dp, tint.copy(alpha = 0.4f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                color = Color(0xFF94A3B8),
                fontSize = 11.5.sp,
                lineHeight = 16.sp
            )
        }
    }
}
