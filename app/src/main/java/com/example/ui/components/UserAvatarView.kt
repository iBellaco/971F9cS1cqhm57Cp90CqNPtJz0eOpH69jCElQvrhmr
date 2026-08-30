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

    val runicBorderBrush = Brush.sweepGradient(
        listOf(
            parsedBorderColor,
            HextechGoldLight,
            HextechCyan.copy(alpha = 0.8f),
            parsedBorderColor,
            HextechGoldLight,
            parsedBorderColor
        )
    )

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
                if (showBorder) {
                    Modifier.border(
                        width = if (size > 60.dp) 2.5.dp else 1.5.dp,
                        brush = runicBorderBrush,
                        shape = CircleShape
                    )
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
