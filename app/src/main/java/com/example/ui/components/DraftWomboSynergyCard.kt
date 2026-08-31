package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Champion
import com.example.model.DraftSlot
import com.example.ui.theme.*
import com.example.util.tr

data class WomboCombo(
    val title: String,
    val type: String, // "KNOCKUP", "SHOCKWAVE", "HYPERCARRY", "CC_BURST", "AREA_CURSE", "ENCHANTER"
    val champ1: Champion,
    val champ2: Champion,
    val description: String,
    val executionTip: String
)

object WomboComboSynergyDetector {
    fun detectWombos(allies: List<Champion>): List<WomboCombo> {
        val detected = mutableListOf<WomboCombo>()
        if (allies.size < 2) return emptyList()

        val names = allies.associateBy { it.name.lowercase().trim() }

        fun find(vararg targets: String): Champion? {
            for (t in targets) {
                val found = names.values.firstOrNull { 
                    it.name.contains(t, ignoreCase = true) || it.id.contains(t, ignoreCase = true) 
                }
                if (found != null) return found
            }
            return null
        }

        // 1. Yasuo + Airbone Knock-ups
        val yasuo = find("yasuo")
        val knockupper = find("malphite", "diana", "alistar", "yone", "wukong", "rakan", "gragas", "vi", "nautilus", "lee sin", "xin zhao")
        if (yasuo != null && knockupper != null && yasuo.id != knockupper.id) {
            detected.add(
                WomboCombo(
                    title = "💥 Wombo-Combo Aéreo Imparable",
                    type = "KNOCKUP",
                    champ1 = knockupper,
                    champ2 = yasuo,
                    description = "Iniciación de ${knockupper.name} con Derribo Aéreo masivo activa instantáneamente el R (Último Aliento) de Yasuo.",
                    executionTip = "Esperar a que ${knockupper.name} impacte a 2 o más enemigos antes de activar la Definitiva de Yasuo para máximo daño en cadena."
                )
            )
        }

        // 2. Orianna + Ball Delivery / Heavy Engage
        val orianna = find("orianna")
        val ballCarrier = find("malphite", "jarvan", "rakan", "diana", "amumu", "sett", "vi", "hecarim", "alistar")
        if (orianna != null && ballCarrier != null && orianna.id != ballCarrier.id) {
            detected.add(
                WomboCombo(
                    title = "🌀 Balón de Choque & Erradicación",
                    type = "SHOCKWAVE",
                    champ1 = ballCarrier,
                    champ2 = orianna,
                    description = "El balón de Orianna colocado sobre ${ballCarrier.name} permite un R (Onda de Choque) perfecto tras el salto.",
                    executionTip = "Colocar E (Proteger) sobre ${ballCarrier.name} justo antes del dive y presionar R en el punto de impacto."
                )
            )
        }

        // 3. Lulu/Yuumi + Hypercarry Protection
        val enchanter = find("lulu", "yuumi", "milio", "janna")
        val hypercarry = find("vayne", "jinx", "kog'maw", "twitch", "tristana", "zeri", "samira", "kaisa", "kai'sa")
        if (enchanter != null && hypercarry != null && enchanter.id != hypercarry.id) {
            detected.add(
                WomboCombo(
                    title = "🛡️ Hypercarry Blindado & Velocidad Letal",
                    type = "HYPERCARRY",
                    champ1 = enchanter,
                    champ2 = hypercarry,
                    description = "Buffs de velocidad de ataque, escudos y tamaño de ${enchanter.name} transforman a ${hypercarry.name} en una máquina imparable.",
                    executionTip = "Guardar la definitiva de soporte para contrarrestar el dive enemigo sobre el tirador en teamfights."
                )
            )
        }

        // 4. Samira / Lucian + Hard CC Chain All-In
        val aggressiveAdc = find("samira", "lucian", "draven", "kalista", "nilah")
        val hardCcSupport = find("nautilus", "leona", "thresh", "pyke", "blitzcrank", "braum")
        if (aggressiveAdc != null && hardCcSupport != null && aggressiveAdc.id != hardCcSupport.id) {
            detected.add(
                WomboCombo(
                    title = "⛓️ Cadena de CC & All-In Explosivo",
                    type = "CC_BURST",
                    champ1 = hardCcSupport,
                    champ2 = aggressiveAdc,
                    description = "El control de masas encadenado de ${hardCcSupport.name} garantiza la carga de pasiva y burst completo de ${aggressiveAdc.name}.",
                    executionTip = "En nivel 2 y nivel 5 buscar all-in inmediato tras el primer gancho o stun acertado."
                )
            )
        }

        // 5. Miss Fortune / Kennen + AoE Curse Lockdown
        val aoeDps = find("miss fortune", "kennen", "gangplank", "katarina", "fiddlesticks")
        val aoeLockdown = find("amumu", "sona", "seraphine", "galio", "jarvan")
        if (aoeDps != null && aoeLockdown != null && aoeDps.id != aoeLockdown.id) {
            detected.add(
                WomboCombo(
                    title = "🔥 Tormenta en Área & Encierro Masivo",
                    type = "AREA_CURSE",
                    champ1 = aoeLockdown,
                    champ2 = aoeDps,
                    description = "El aturdimiento masivo de ${aoeLockdown.name} congela al equipo rival dentro de la definitiva destructiva de ${aoeDps.name}.",
                    executionTip = "Luchar exclusivamente en cuellos de botella de la jungla o alrededor de los fosos de Dragón/Barón."
                )
            )
        }

        // 6. Lucian + Nami / Electrocutar
        val lucian = find("lucian")
        val nami = find("nami")
        if (lucian != null && nami != null) {
            detected.add(
                WomboCombo(
                    title = "🌊 Electro-Ráfaga Acuática",
                    type = "ENCHANTER",
                    champ1 = nami,
                    champ2 = lucian,
                    description = "La Habilidad 3 (Bendición de la Marea) de Nami se activa con los dobles disparos pasivos de Lucian aplicando electrocutar al instante.",
                    executionTip = "Nami aplica E sobre Lucian justo cuando este usa E (Persecución Implacable) hacia adelante."
                )
            )
        }

        return detected.distinctBy { "${it.champ1.name}_${it.champ2.name}" }
    }
}

/**
 * Tarjeta de Sinergia y Wombo-Combo con Efecto Neón Hextech animado.
 */
@Composable
fun DraftWomboSynergyCard(
    wombo: WomboCombo,
    onChampionClick: (Champion) -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "neonPulse")
    val borderAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "borderGlow"
    )

    val neonGradient = Brush.horizontalGradient(
        listOf(
            HextechCyan.copy(alpha = borderAlpha),
            HextechGold.copy(alpha = borderAlpha),
            TierSPlusColor.copy(alpha = borderAlpha)
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.5.dp, neonGradient, RoundedCornerShape(14.dp))
            .shadow(6.dp, RoundedCornerShape(14.dp), spotColor = HextechCyan),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF07121E))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header con Chip Neón Pulsante
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(HextechGold.copy(alpha = 0.25f), HextechCyan.copy(alpha = 0.25f))
                                )
                            )
                            .border(1.dp, HextechGold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Whatshot,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = tr("💥 Sinergia Letal"),
                                color = HextechGold,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = wombo.title,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campeones involucrados en el Combo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(HextechDarkBg.copy(alpha = 0.8f))
                    .border(0.8.dp, HextechCardBorder, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Campeón 1
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onChampionClick(wombo.champ1) }
                ) {
                    ChampionAvatar(champion = wombo.champ1, size = 40.dp, showTierBadge = false)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = wombo.champ1.name,
                            color = TextPrimary,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tr(wombo.champ1.primaryRole.shortName),
                            color = HextechCyan,
                            fontSize = 10.sp
                        )
                    }
                }

                // Ícono de Enlace Neón
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(HextechGold.copy(alpha = 0.2f))
                        .border(1.dp, HextechGold.copy(alpha = borderAlpha), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ElectricBolt,
                        contentDescription = null,
                        tint = HextechGold,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Campeón 2
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onChampionClick(wombo.champ2) }
                ) {
                    ChampionAvatar(champion = wombo.champ2, size = 40.dp, showTierBadge = false)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = wombo.champ2.name,
                            color = TextPrimary,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tr(wombo.champ2.primaryRole.shortName),
                            color = HextechCyan,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción y Guía de Ejecución
            Text(
                text = wombo.description,
                color = TextPrimary.copy(alpha = 0.9f),
                fontSize = 11.5.sp,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF0F2336))
                    .border(0.6.dp, HextechCyan.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = HextechCyan,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Tip Coach: ${wombo.executionTip}",
                    color = HextechCyanLight,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
