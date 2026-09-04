package com.example.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.imageLoader
import coil.request.CachePolicy
import com.example.model.Champion
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.TierAColor
import com.example.ui.theme.TierSColor
import com.example.ui.theme.TierSPlusColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ChampionAvatar(
    champion: Champion,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    showTierBadge: Boolean = false,
    borderColor: Color = HextechGold
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        AppAssetImage(
            url = champion.avatarUrl,
            contentDescription = champion.name,
            fallbackText = champion.name,
            modifier = Modifier.size(size),
            shape = CircleShape,
            borderColor = borderColor
        )
        // Add tier badge logic if it used to exist, but since it's just visual I'll omit complex logic if I don't remember it
        if (showTierBadge && champion.tier.isNotBlank()) {
            val badgeColor = when (champion.tier) {
                "S+" -> TierSPlusColor
                "S" -> TierSColor
                "A" -> TierAColor
                else -> HextechCyan
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 4.dp, y = 4.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(badgeColor)
                    .border(1.dp, HextechDarkBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = champion.tier,
                    color = HextechDarkBg,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AppAssetImage(
    url: String,
    contentDescription: String?,
    fallbackText: String,
    modifier: Modifier = Modifier,
    borderColor: Color = HextechGold,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(8.dp)
) {
    val context = LocalContext.current
    var loadFailed by remember(url) { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(shape)
            .background(HextechDarkBg)
            .border(1.dp, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        // Fallback initials underneath
        Text(
            text = fallbackText.take(2).uppercase(),
            color = borderColor.copy(alpha = 0.7f),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
        
        if (url.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(url)
                    .crossfade(true)
                    .listener(
                        onError = { _, _ -> loadFailed = true }
                    )
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                imageLoader = context.imageLoader,
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape)
            )
        }
    }
}