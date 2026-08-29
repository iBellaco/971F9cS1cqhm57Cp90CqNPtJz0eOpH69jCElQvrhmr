package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionPlansBottomSheet(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

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
            Text(
                text = "Planes de Suscripción",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Free Card
            PlanCard(
                title = "Plan Gratuito",
                price = "Gratis",
                isPremium = false,
                features = listOf(
                    Pair("Acceso al Asistente de Draft", true),
                    Pair("Tier List y Catálogo", true),
                    Pair("Descarga de recursos offline", true),
                    Pair("Temas personalizados", false),
                    Pair("Estilos de barra de navegación", false)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Premium Card
            PlanCard(
                title = "Coach Premium",
                price = "$2.99 / mes",
                isPremium = true,
                features = listOf(
                    Pair("Acceso al Asistente de Draft", true),
                    Pair("Tier List y Catálogo", true),
                    Pair("Descarga de recursos offline", true),
                    Pair("Temas personalizados ilimitados", true),
                    Pair("Estilos de barra de navegación", true)
                )
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun PlanCard(
    title: String,
    price: String,
    isPremium: Boolean,
    features: List<Pair<String, Boolean>>
) {
    val borderColor = if (isPremium) HextechGold else TextMuted.copy(alpha = 0.5f)
    val bgColor = if (isPremium) HextechGold.copy(alpha = 0.05f) else HextechSurface

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(1.dp, borderColor)
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isPremium) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(20.dp).padding(end = 4.dp)
                            )
                        }
                        Text(
                            text = title,
                            color = if (isPremium) HextechGold else TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = price,
                        color = if (isPremium) HextechCyan else TextSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                
                if (isPremium) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(HextechGold)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("PRO", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = borderColor.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(16.dp))

            features.forEach { (feature, isIncluded) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (isIncluded) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = if (isIncluded) HextechCyan else DangerRed,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = feature,
                        color = if (isIncluded) TextPrimary else TextMuted,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}
