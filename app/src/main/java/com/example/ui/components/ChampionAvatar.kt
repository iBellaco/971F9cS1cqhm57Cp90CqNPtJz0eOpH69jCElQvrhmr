package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
    size: Dp = 56.dp,
    showTierBadge: Boolean = true,
    modifier: Modifier = Modifier
) {
    val avatarBrush = when (champion.id) {
        "morgana" -> Brush.radialGradient(listOf(Color(0xFF8B5CF6), Color(0xFF2E1065), Color(0xFF0F051D)))
        "viego" -> Brush.radialGradient(listOf(Color(0xFF00F2FE), Color(0xFF005A82), Color(0xFF071426)))
        "nautilus" -> Brush.radialGradient(listOf(Color(0xFFD97706), Color(0xFF78350F), Color(0xFF1E1B18)))
        else -> Brush.radialGradient(listOf(Color(0xFF3A4B5C), Color(0xFF1E2A38), Color(0xFF0F1722)))
    }

    Box(contentAlignment = Alignment.BottomEnd, modifier = modifier.size(size)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(HextechDarkBg)
                .border(2.dp, HextechGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(avatarBrush),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = champion.name.take(2).uppercase(),
                    color = Color.White,
                    fontSize = (size.value * 0.32).sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (champion.avatarUrl.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(champion.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = champion.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(size - 4.dp)
                        .clip(CircleShape)
                )
            }
        }

        if (showTierBadge && champion.tier.isNotBlank()) {
            val tierColor = when (champion.tier) {
                "S+" -> TierSPlusColor
                "S" -> TierSColor
                "A" -> TierAColor
                "B" -> Color(0xFF4CAF50)
                "C" -> Color(0xFF8BC34A)
                else -> Color.Gray
            }
            Box(
                modifier = Modifier
                    .size(size * 0.35f)
                    .clip(CircleShape)
                    .background(HextechDarkBg)
                    .border(1.dp, HextechGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = champion.tier,
                    color = tierColor,
                    fontSize = (size.value * 0.16).sp,
                    fontWeight = FontWeight.ExtraBold
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
    val parsedUrl = url.trim()
    val isLocalFile = parsedUrl.startsWith("file://")
    
    var localImageBitmap by remember(parsedUrl) { mutableStateOf<androidx.compose.ui.graphics.ImageBitmap?>(null) }
    
    LaunchedEffect(parsedUrl) {
        if (isLocalFile) {
            withContext(Dispatchers.IO) {
                try {
                    val file = java.io.File(parsedUrl.removePrefix("file://"))
                    if (file.exists()) {
                        val bmp = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
                        if (bmp != null) {
                            localImageBitmap = bmp.asImageBitmap()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(HextechDarkBg)
            .border(1.dp, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = fallbackText.take(2).uppercase(),
            color = borderColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        if (isLocalFile && localImageBitmap != null) {
            Image(
                bitmap = localImageBitmap!!,
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape)
            )
        } else if (!isLocalFile && parsedUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(parsedUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape)
            )
        }
    }
}
