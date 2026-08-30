package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.data.AvatarCatalog
import com.example.model.AvatarItem
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight

@Composable
fun UserAvatarView(
    avatarId: String?,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    fallbackInitial: String = "U",
    showBorder: Boolean = true,
    customBorderColor: Color? = null
) {
    val avatar: AvatarItem = AvatarCatalog.getAvatarById(avatarId ?: "default_poro")
    val parsedBorderColor = customBorderColor ?: try {
        Color(android.graphics.Color.parseColor(avatar.borderHex))
    } catch (e: Exception) {
        HextechGold
    }

        val rarityLower = avatar.rarity.lowercase()
    val isCommon = rarityLower == "común" || rarityLower == "comun" || rarityLower == "clásico"

    val borderWidth = when {
        rarityLower.contains("mítico") || rarityLower.contains("mitico") -> if (size > 60.dp) 3.5.dp else 2.5.dp
        rarityLower.contains("legendario") -> if (size > 60.dp) 3.dp else 2.dp
        rarityLower.contains("épico") || rarityLower.contains("epico") -> if (size > 60.dp) 2.5.dp else 1.5.dp
        rarityLower.contains("raro") -> if (size > 60.dp) 2.dp else 1.5.dp
        else -> 1.dp
    }

    val runicBorderBrush = when {
        rarityLower.contains("mítico") || rarityLower.contains("mitico") -> Brush.sweepGradient(listOf(Color(0xFFC4B5FD), Color(0xFF7C3AED), Color(0xFF5B21B6), Color(0xFFC4B5FD)))
        rarityLower.contains("legendario") -> Brush.sweepGradient(listOf(Color(0xFFFFD700), Color(0xFFB91C1C), Color(0xFF991B1B), Color(0xFFFFD700)))
        rarityLower.contains("épico") || rarityLower.contains("epico") -> Brush.sweepGradient(listOf(Color(0xFFE9D5FF), Color(0xFF9333EA), Color(0xFFE9D5FF)))
        rarityLower.contains("raro") -> Brush.linearGradient(listOf(Color(0xFF93C5FD), Color(0xFF2563EB), Color(0xFF93C5FD)))
        else -> Brush.linearGradient(listOf(parsedBorderColor, parsedBorderColor))
    }

    val actualShowBorder = showBorder && !isCommon

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0xFF1E293B),
                        Color(0xFF0F172A),
                        HextechDarkBg
                    )
                )
            )
            .then(
                if (actualShowBorder) {
                    if (rarityLower.contains("mítico") || rarityLower.contains("mitico") || rarityLower.contains("legendario")) {
                        Modifier.premiumBorderPainter(
                            rarity = rarityLower,
                            isMythic = rarityLower.contains("mítico") || rarityLower.contains("mitico"),
                            isLegendary = rarityLower.contains("legendario")
                        )
                    } else {
                        Modifier.border(
                            width = borderWidth,
                            brush = runicBorderBrush,
                            shape = CircleShape
                        )
                    }
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        // Fallback Initial text while loading or if offline
        Text(
            text = fallbackInitial.take(1).uppercase(),
            color = HextechGoldLight,
            fontWeight = FontWeight.ExtraBold,
            fontSize = (size.value * 0.38f).sp,
            fontFamily = FontFamily.Serif
        )

        if (avatar.imageUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatar.imageUrl)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = avatar.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}


fun Modifier.premiumBorderPainter(rarity: String, isMythic: Boolean, isLegendary: Boolean): Modifier {
    if (!isMythic && !isLegendary) return this
    
    return this.drawWithCache {
        val strokeWidth = if (isMythic) 4.dp.toPx() else 3.dp.toPx()
        
        val primaryColor = if (isMythic) Color(0xFFC4B5FD) else Color(0xFFFFD700)
        val secondaryColor = if (isMythic) Color(0xFF7C3AED) else Color(0xFFB91C1C)
        val darkColor = if (isMythic) Color(0xFF4C1D95) else Color(0xFF7F1D1D)
        
        val brush = Brush.sweepGradient(
            listOf(primaryColor, secondaryColor, darkColor, secondaryColor, primaryColor)
        )
        
        onDrawWithContent {
            drawContent()
            
            // Extravagant ring on top, inside the bounds
            drawCircle(
                brush = brush,
                radius = size.width / 2 - strokeWidth / 2,
                center = Offset(size.width / 2, size.height / 2),
                style = Stroke(
                    width = strokeWidth,
                    pathEffect = if (isMythic) PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f) else null
                )
            )
            
            if (isMythic) {
                // Additional inner ring for mythic
                drawCircle(
                    color = Color(0xFFE9D5FF),
                    radius = size.width / 2 - strokeWidth,
                    center = Offset(size.width / 2, size.height / 2),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
        }
    }
}