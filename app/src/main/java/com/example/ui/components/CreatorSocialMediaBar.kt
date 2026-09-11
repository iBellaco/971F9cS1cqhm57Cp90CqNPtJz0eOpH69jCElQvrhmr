package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
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
 * Barra rediseñada de Comunidad, Creadores y Streamers (Kick, YouTube, Twitch, Facebook, TikTok, Instagram, WhatsApp, Discord)
 * Permite abrir enlaces o copiarlos al portapapeles con un toque / botón de copia.
 */
@Composable
fun CreatorSocialMediaBar(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val platforms = listOf(
        SocialPlatform(
            id = "kick",
            name = "Kick",
            handle = "Streamer Kick",
            iconEmoji = "🟢",
            brandColor = Color(0xFF53FC18),
            secondaryColor = Color(0xFF38B000),
            url = "https://kick.com",
            tag = "btn_social_kick"
        ),
        SocialPlatform(
            id = "youtube",
            name = "YouTube",
            handle = "Streamer YT",
            iconEmoji = "▶️",
            brandColor = Color(0xFFFF0000),
            secondaryColor = Color(0xFFCC0000),
            url = "https://www.youtube.com",
            tag = "btn_social_youtube"
        ),
        SocialPlatform(
            id = "twitch",
            name = "Twitch",
            handle = "Streamer Twitch",
            iconEmoji = "🟣",
            brandColor = Color(0xFF9146FF),
            secondaryColor = Color(0xFF772CE8),
            url = "https://www.twitch.tv",
            tag = "btn_social_twitch"
        ),
        SocialPlatform(
            id = "facebook",
            name = "Facebook",
            handle = "Streamer FB",
            iconEmoji = "👥",
            brandColor = Color(0xFF1877F2),
            secondaryColor = Color(0xFF0C5DC7),
            url = "https://www.facebook.com",
            tag = "btn_social_facebook"
        ),
        SocialPlatform(
            id = "tiktok",
            name = "TikTok",
            handle = "Streamer TikTok",
            iconEmoji = "🎵",
            brandColor = Color(0xFF000000),
            secondaryColor = Color(0xFF25F4EE),
            url = "https://www.tiktok.com",
            tag = "btn_social_tiktok"
        ),
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
            url = "https://discord.gg/waYPnaMCB",
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
            // Header: Creador & Streamer Pro Esports Badge
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
                        text = tr("Streamers & Comunidad"),
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
            Text(
                text = "Toca para abrir plataforma o presiona el botón para copiar enlace (Kick, YT, Twitch, FB, TikTok)",
                color = TextMuted,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable Row of Platforms / Streamers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                platforms.forEach { platform ->
                    ProfessionalSocialButton(
                        platform = platform,
                        onClick = {
                            openUrl(context, platform.url, platform.name)
                        },
                        onCopy = {
                            copyToClipboard(context, platform.url, platform.name)
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
    onClick: () -> Unit,
    onCopy: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(115.dp)
            .height(48.dp)
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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
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
                Spacer(modifier = Modifier.width(4.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = platform.name,
                        color = TextPrimary,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Streamer",
                        color = HextechGoldLight,
                        fontSize = 8.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Copy button icon
            IconButton(
                onClick = onCopy,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copiar enlace de ${platform.name}",
                    tint = HextechCyan,
                    modifier = Modifier.size(12.dp)
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

private fun copyToClipboard(context: Context, url: String, platformName: String) {
    try {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("Enlace de $platformName", url)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "¡Enlace de $platformName copiado! 📋", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error al copiar enlace", Toast.LENGTH_SHORT).show()
    }
}
