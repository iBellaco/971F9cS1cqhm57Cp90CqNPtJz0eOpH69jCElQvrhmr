package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import android.widget.Toast
import com.example.util.SubscriptionManager
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionPlansBottomSheet(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechDarkBg,
        tonalElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.WorkspacePremium,
                contentDescription = null,
                tint = HextechGold,
                modifier = Modifier
                    .size(48.dp)
                    .padding(bottom = 8.dp)
            )
            
            Text(
                text = "Desbloquea tu Máximo Potencial",
                color = TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Elige el plan que mejor se adapte a tu estilo de juego: pases temporales por horas/días o suscripción continua.",
                color = TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Premium Card (Mensual)
            PremiumPlanCard(
                title = "Coach Premium (Mensual)",
                price = "$1.00",
                period = "/ mes",
                isPopular = false,
                features = listOf(
                    FeatureItem("Acceso completo al Asistente de Draft 30 días", true),
                    FeatureItem("Descarga de recursos offline permanente", true),
                    FeatureItem("Colección de Avatares Exclusiva completa", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardado de draft", true, isHighlight = true, icon = Icons.Default.Save),
                    FeatureItem("Tier list personal", true, isHighlight = true, icon = Icons.Default.Star),
                    FeatureItem("Campeón favorito", true, isHighlight = true, icon = Icons.Default.Star)
                ),
                onSubscribe = {
                    com.example.util.SubscriptionManager.purchaseSubscription(
                        durationMillis = 30L * 24L * 60L * 60L * 1000L, // 30 days
                        planName = "Coach Premium (Mensual)",
                        price = "$1.00",
                        onSuccess = {
                            Toast.makeText(context, "Suscripción adquirida exitosamente.", Toast.LENGTH_LONG).show()
                            scope.launch {
                                sheetState.hide()
                                onDismiss()
                            }
                        },
                        onError = { err ->
                            Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Premium Anual Card
            PremiumPlanCard(
                title = "Coach Premium (Anual)",
                price = "$11.00",
                period = "/ año (Normalmente $12)",
                isPopular = true,
                features = listOf(
                    FeatureItem("1 mes gratis (Ahorro del 8%)", true, isHighlight = true, icon = Icons.Default.Star),
                    FeatureItem("Acceso completo al Asistente de Draft por 365 días", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Colección de Avatares Exclusiva", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardado de draft", true, isHighlight = true, icon = Icons.Default.Save),
                    FeatureItem("Tier list personal", true, isHighlight = true, icon = Icons.Default.Star),
                    FeatureItem("Campeón favorito", true, isHighlight = true, icon = Icons.Default.Star)
                ),
                onSubscribe = {
                    com.example.util.SubscriptionManager.purchaseSubscription(
                        durationMillis = 365L * 24L * 60L * 60L * 1000L, // 365 days
                        planName = "Coach Premium (Anual)",
                        price = "$11.00",
                        onSuccess = {
                            Toast.makeText(context, "Suscripción adquirida exitosamente.", Toast.LENGTH_LONG).show()
                            scope.launch {
                                sheetState.hide()
                                onDismiss()
                            }
                        },
                        onError = { err ->
                            Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Free Card (Muted)
            FreePlanCard(
                title = "Plan Gratuito",
                price = "Gratis",
                features = listOf(
                    FeatureItem("Acceso al asistente del draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Comunes Gratuitos", true),
                    FeatureItem("Colección de Avatares Premium", false),
                    FeatureItem("Temas Visuales por Región", false),
                    FeatureItem("Partículas Mágicas", false),
                    FeatureItem("Guardado de draft", false),
                    FeatureItem("Tier list personal", false),
                    FeatureItem("Campeón favorito", false)
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

data class FeatureItem(
    val text: String,
    val isIncluded: Boolean,
    val isHighlight: Boolean = false,
    val icon: androidx.compose.ui.graphics.vector.ImageVector? = null
)

@Composable
private fun PremiumPlanCard(
    title: String,
    price: String,
    period: String,
    isPopular: Boolean = false,
    features: List<FeatureItem>,
    onSubscribe: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(HextechGold, HextechCyan)
    )
    
    val bgGradientBrush = Brush.linearGradient(
        colors = listOf(HextechGold.copy(alpha = 0.15f), HextechCyan.copy(alpha = 0.05f))
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp), // Space for the floating badge
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(2.dp, gradientBrush)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgGradientBrush)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = title,
                                color = HextechGold,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = price,
                                    color = TextPrimary,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Text(
                                    text = period,
                                    color = TextSecondary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = HextechGold.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(20.dp))

                    features.forEach { feature ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (feature.isHighlight) HextechGold.copy(alpha = 0.2f) else Color.Transparent),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = feature.icon ?: Icons.Default.Check,
                                    contentDescription = null,
                                    tint = if (feature.isHighlight) HextechGold else HextechCyan,
                                    modifier = Modifier.size(if (feature.isHighlight) 16.dp else 20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = feature.text,
                                color = if (feature.isHighlight) HextechGold else TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = if (feature.isHighlight) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = onSubscribe,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = "Suscribirse Ahora",
                            color = HextechDarkBg,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
        
        // Floating Badge
        if (isPopular) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(gradientBrush, RoundedCornerShape(50))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "MÁS POPULAR",
                    color = HextechDarkBg,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
private fun FreePlanCard(
    title: String,
    price: String,
    features: List<FeatureItem>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, TextMuted.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        color = TextSecondary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = price,
                        color = TextMuted,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = TextMuted.copy(alpha = 0.1f))
            Spacer(modifier = Modifier.height(16.dp))

            features.forEach { feature ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (feature.isIncluded) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = if (feature.isIncluded) TextSecondary else DangerRed.copy(alpha = 0.7f),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = feature.text,
                        color = if (feature.isIncluded) TextSecondary else TextMuted,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
