package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val isPremiumHighlight: Boolean = false,
    val premiumFeatures: List<String> = emptyList()
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            title = "Domina el Meta",
            description = "Consulta la Tier List actualizada y descubre qué campeones están dominando en el parche actual. Mantente siempre un paso por delante.",
            icon = Icons.Default.Analytics
        ),
        OnboardingPage(
            title = "Asistente de Draft",
            description = "Crea composiciones perfectas. Analiza sinergias, counters y descubre tu win condition antes de que empiece la partida.",
            icon = Icons.Default.AutoAwesome
        ),
        OnboardingPage(
            title = "Premium",
            description = "Desbloquea el verdadero poder analítico y toma el control total de tus drafts.",
            icon = Icons.Default.WorkspacePremium,
            isPremiumHighlight = true,
            premiumFeatures = listOf(
                "Guardado de Drafts e Historial de Partidas",
                "Repertorio de Campeones Favoritos",
                "Personalización de Temas Visuales"
            )
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HextechDarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { position ->
                OnboardingPageContent(page = pages[position])
            }

            // Bottom Section (Indicators & Buttons)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Indicators
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(pages.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        val color = if (isSelected) {
                            if (pages[index].isPremiumHighlight) HextechGold else HextechCyan
                        } else TextMuted.copy(alpha = 0.3f)
                        
                        val width = if (isSelected) 24.dp else 10.dp
                        
                        Box(
                            modifier = Modifier
                                .height(10.dp)
                                .width(width)
                                .clip(CircleShape)
                                .background(color)
                        )
                    }
                }

                // Buttons
                if (pagerState.currentPage == pages.size - 1) {
                    Button(
                        onClick = onFinish,
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Comenzar",
                            color = HextechDarkBg,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        Text(
                            text = "Siguiente",
                            color = HextechCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon / Image
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(if (page.isPremiumHighlight) HextechGold.copy(alpha = 0.1f) else HextechSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                tint = if (page.isPremiumHighlight) HextechGold else HextechCyan,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Title
        Text(
            text = page.title,
            color = if (page.isPremiumHighlight) HextechGold else TextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = page.description,
            color = TextSecondary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )

        // Premium Features List
        if (page.isPremiumHighlight && page.premiumFeatures.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.5f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechGold.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    page.premiumFeatures.forEach { feature ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = feature,
                                color = TextPrimary,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
