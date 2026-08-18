package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AllyBlue
import com.example.ui.theme.DangerRed
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.HextechSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TierSColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                    ) {
                        Text(
                            text = "Acerca De",
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
                                text = "v2.1.0 • Parche 7.2c",
                                color = HextechCyan,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("info_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = HextechGold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = HextechDarkBg)
            )
        },
        containerColor = HextechDarkBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // ==========================================
            // SECCIÓN 1: VERSIÓN DE LA APLICACIÓN
            // ==========================================
            InfoSectionHeader(
                icon = Icons.Default.NewReleases,
                title = "1. Versión de la Aplicación",
                tint = HextechCyan
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = HextechSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Drafting Wild Rift",
                            color = HextechGold,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(HextechGold.copy(alpha = 0.15f))
                                .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Build 2.1.0",
                                color = HextechGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• Compatibilidad: Diseñado exclusivamente para League of Legends: Wild Rift (Móvil).\n" +
                               "• Parche del Meta: 7.2c (Ciclo 5.x/6.x/7.x con últimos ajustes de campeones e items).\n" +
                               "• Motor Hextech: Botón 3D con animación de partículas y rotación en tiempo real.\n" +
                               "• Sistema Flotante: Ventana superpuesta no intrusiva con controles gestuales y HUD de análisis táctico.",
                        color = TextPrimary,
                        fontSize = 12.5.sp,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // ==========================================
            // SECCIÓN 2: MODO DE USO DE LA APLICACIÓN
            // ==========================================
            InfoSectionHeader(
                icon = Icons.Default.HelpOutline,
                title = "2. Modo de Uso de la Aplicación",
                tint = HextechGold
            )
            Spacer(modifier = Modifier.height(10.dp))

            StepCard(
                stepNumber = "1",
                title = "Paso 1: Configura tus Preferencias de Línea",
                description = "En la pantalla principal, selecciona tu 'Línea Main', 'Segunda Línea' y 'Rol Autofill' tocando sobre cada tarjeta para abrir el selector rápido."
            )
            Spacer(modifier = Modifier.height(8.dp))

            StepCard(
                stepNumber = "2",
                title = "Paso 2: Activa el Asistente Hextech",
                description = "Pulsa el Orbe Hextech 3D central ('ACTIVAR'). Se desplegará la burbuja flotante con el icono de la cámara y el botón de cierre rápido."
            )
            Spacer(modifier = Modifier.height(8.dp))

            StepCard(
                stepNumber = "3",
                title = "Paso 3: Fase de Selección de Campeones (Champ Select)",
                description = "Abre Wild Rift y entra a la fase de selección. Toca la burbuja de la cámara en cualquier momento para activar el escaneo táctico y recibir consejos en directo."
            )
            Spacer(modifier = Modifier.height(8.dp))

            StepCard(
                stepNumber = "4",
                title = "Paso 4: Consulta de Counters y Sinergias",
                description = "Revisa el bocadillo de texto que aparece junto a la cámara: te indicará la mejor selección según los campeones que bloquee y elija el equipo enemigo."
            )
            Spacer(modifier = Modifier.height(8.dp))

            StepCard(
                stepNumber = "5",
                title = "Paso 5: Controles Gestuales y Cierre",
                description = "• Arrastra la burbuja a cualquier borde de tu pantalla.\n• Toca la burbuja para ciclar entre múltiples sugerencias tácticas.\n• Toca el botón rojo (X) o pulsa 'DETENER' en la app para finalizar."
            )

            Spacer(modifier = Modifier.height(22.dp))

            // ==========================================
            // SECCIÓN 3: RECOMENDACIONES
            // ==========================================
            InfoSectionHeader(
                icon = Icons.Default.Lightbulb,
                title = "3. Recomendaciones Tácticas & Seguridad",
                tint = HextechCyan
            )
            Spacer(modifier = Modifier.height(10.dp))

            RecommendationTopicCard(
                icon = Icons.Default.AutoAwesome,
                title = "Recomendaciones de Selección de Campeón",
                description = "• Si el rival elige un iniciador pesado (ej. Vi, Sett, Rell), prioriza campeones con desengage o escudos mágicos (como Morgana o Janna).\n" +
                              "• Si el rival acumula tanques con mucha vida, selecciona daño porcentual o verdadero (ej. Vayne, Fiora o Sett).\n" +
                              "• Si tus aliados son casi todos de daño físico (AD), elige obligatoriamente una opción de daño mágico (AP) para evitar que el rival se arme solo armadura.",
                tint = HextechCyan
            )
            Spacer(modifier = Modifier.height(8.dp))

            RecommendationTopicCard(
                icon = Icons.Default.Timer,
                title = "Recomendaciones de Tiempos y Objetivos",
                description = "• Dragón Elemental (Minuto 4:00): Asegura prioridad en la línea de Dragón 30 segundos antes empujando la oleada.\n" +
                              "• Heraldo de la Grieta (Minuto 5:00): Crucial para derribar la primera torre y liberar a tu carrilero solitario.\n" +
                              "• Barón Nashor & Dragón Anciano (Minuto 12:00): Coloca centinelas en las entradas de la jungla antes de iniciar.",
                tint = HextechGold
            )
            Spacer(modifier = Modifier.height(8.dp))

            RecommendationTopicCard(
                icon = Icons.Default.Security,
                title = "Recomendaciones de Seguridad (Modo Anti-Ban)",
                description = "Drafting Wild Rift no altera la memoria de la app ni inyecta código en los servidores de Riot Games. Funciona 100% como una superposición de asistencia visual independiente, garantizando total seguridad para tu cuenta.",
                tint = TierSColor,
                highlight = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Footer version banner
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Drafting Wild Rift • Asistente Externo Inteligente",
                    color = HextechGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Versión 2.1.0 • Optimizado para League of Legends: Wild Rift",
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun InfoSectionHeader(
    icon: ImageVector,
    title: String,
    tint: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            color = TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StepCard(
    stepNumber: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, HextechCardBorder)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(HextechCyan.copy(alpha = 0.15f))
                    .border(1.dp, HextechCyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber,
                    color = HextechCyan,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = TextMuted,
                    fontSize = 12.5.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
private fun RecommendationTopicCard(
    icon: ImageVector,
    title: String,
    description: String,
    tint: Color,
    highlight: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = androidx.compose.foundation.BorderStroke(
            if (highlight) 1.5.dp else 1.dp,
            if (highlight) HextechGold else HextechCardBorder
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(tint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    color = if (highlight) HextechGold else TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = if (highlight) HextechGoldLight.copy(alpha = 0.9f) else TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}
