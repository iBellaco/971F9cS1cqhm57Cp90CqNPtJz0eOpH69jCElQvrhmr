package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
fun WelcomePatchDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val patchVersion = WildRiftRepository.CURRENT_PATCH_VERSION
    val prefsKey = "has_seen_patch_${patchVersion.replace(" ", "_")}"
    val sharedPrefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    var showDialog by remember { mutableStateOf(!sharedPrefs.getBoolean(prefsKey, false)) }

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                sharedPrefs.edit().putBoolean(prefsKey, true).apply()
                showDialog = false
                onDismiss()
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                color = HextechDarkBg,
                border = androidx.compose.foundation.BorderStroke(
                    width = 1.5.dp,
                    brush = Brush.linearGradient(listOf(HextechCyan, HextechGold))
                ),
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                brush = Brush.radialGradient(listOf(HextechGold.copy(alpha = 0.3f), Color.Transparent)),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(28.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Patch Info",
                            tint = HextechGold,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = tr("¡Bienvenidos!"),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tr("Notas del Nuevo Parche"),
                        color = HextechCyan,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0F1522), RoundedCornerShape(10.dp))
                            .border(0.5.dp, Color(0xFF2A364F), RoundedCornerShape(10.dp))
                            .padding(14.dp)
                    ) {
                        Text(
                            text = tr("• Sincronización automática con: ") + patchVersion + "\n" +
                                   tr("• Análisis táctico actualizado con las últimas estadísticas del meta.\n") +
                                   tr("• Ajustes y corrección de íconos rúnicos."),
                            color = Color(0xFFCBD5E1),
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = tr("Organización: Coach de Élite (Wild Rift Drafting)"),
                        color = HextechGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
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
                            text = tr("Entendido"),
                            color = HextechDarkBg,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
