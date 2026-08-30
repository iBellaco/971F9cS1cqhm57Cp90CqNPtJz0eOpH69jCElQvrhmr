package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.util.tr

private data class SocialPlatform(
    val id: String,
    val name: String,
    val handle: String,
    val iconEmoji: String,
    val brandColor: Color,
    val secondaryColor: Color,
    val url: String,
    val tag: String
)

/**
 * Barra rediseñada de Comunidad & Creador (Diego Barba Chavez)
 * Estilo Esports de alta gama con micro-gradientes, bordes biselados Hextech y badges de interacción.
 */
@Composable
fun CreatorSocialMediaBar(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val platforms = listOf(
        SocialPlatform(
            id = "instagram",
            name = "Instagram",
            handle = "@Diego.Barba",
            iconEmoji = "📸",
            brandColor = Color(0xFFE1306C),
            secondaryColor = Color(0xFFFD1D1D),
            url = "https://www.instagram.com/Diego.Barba.Chavez",
            tag = "btn_social_instagram"
        ),
        SocialPlatform(
            id = "facebook",
            name = "Facebook",
            handle = "Diego Barba",
            iconEmoji = "👥",
            brandColor = Color(0xFF1877F2),
            secondaryColor = Color(0xFF0056C6),
            url = "https://www.facebook.com/Diego.Barba.Chavez",
            tag = "btn_social_facebook"
        ),
        SocialPlatform(
            id = "whatsapp",
            name = "WhatsApp",
            handle = "Coach Direct",
            iconEmoji = "💬",
            brandColor = Color(0xFF25D366),
            secondaryColor = Color(0xFF128C7E),
            url = "https://chat.whatsapp.com/EUwKMDc6XJn8PaGyoAZn3s",
            tag = "btn_social_whatsapp"
        ),
        SocialPlatform(
            id = "discord",
            name = "Discord",
            handle = "WR Esports",
            iconEmoji = "🎮",
            brandColor = Color(0xFF5865F2),
            secondaryColor = Color(0xFF3842B8),
            url = "https://discord.gg/",
            tag = "btn_social_discord"
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface.copy(alpha = 0.92f)),
        border = BorderStroke(
            1.2.dp,
            Brush.horizontalGradient(
                listOf(
                    HextechGold.copy(alpha = 0.6f),
                    HextechCyan.copy(alpha = 0.4f),
                    HextechGold.copy(alpha = 0.6f)
                )
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            HextechDarkBg.copy(alpha = 0.75f),
                            HextechSurface.copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Creador & Comunidad Pro Esports Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(HextechGold.copy(alpha = 0.2f))
                            .border(1.dp, HextechGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Hub,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tr("Comunidad & Creador"),
                        color = HextechGoldLight,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.3.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(HextechCyan.copy(alpha = 0.15f))
                        .border(0.8.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 7.dp, vertical = 2.5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = HextechCyan,
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Diego Barba Chávez",
                        color = HextechCyan,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 4 Botones elegantes con diseño tipo cápsula/tarjeta esports
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                platforms.forEach { platform ->
                    ProfessionalSocialButton(
                        platform = platform,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            openUrl(context, platform.url, platform.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfessionalSocialButton(
    platform: SocialPlatform,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(HextechDarkBg.copy(alpha = 0.9f))
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(
                        platform.brandColor.copy(alpha = 0.85f),
                        platform.secondaryColor.copy(alpha = 0.4f)
                    )
                ),
                RoundedCornerShape(9.dp)
            )
            .clickable(onClick = onClick)
            .testTag(platform.tag),
        contentAlignment = Alignment.Center
    ) {
        // Sutil brillo ambiental en gradiente
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            platform.brandColor.copy(alpha = 0.22f),
                            Color.Transparent
                        )
                    )
                )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(platform.brandColor.copy(alpha = 0.25f))
                    .border(0.6.dp, platform.brandColor.copy(alpha = 0.7f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = platform.iconEmoji,
                    fontSize = 11.sp
                )
            }
            Spacer(modifier = Modifier.width(4.5.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = platform.name,
                    color = TextPrimary,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun openUrl(context: Context, url: String, platformName: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, "No se pudo abrir $platformName", Toast.LENGTH_SHORT).show()
    }
}

