package com.example.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ChampionAvatar(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    borderColor: Color = HextechGold
) {
    AppAssetImage(
        url = imageUrl,
        contentDescription = contentDescription,
        fallbackText = contentDescription,
        modifier = modifier.size(size),
        shape = CircleShape,
        borderColor = borderColor
    )
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

    var assetBitmap by remember(parsedUrl) { mutableStateOf<androidx.compose.ui.graphics.ImageBitmap?>(null) }
    var loadFailed by remember(parsedUrl) { mutableStateOf(false) }
    
    // VISUAL TEST: Make background red if fallback is shown
    val bgColor = if (loadFailed) Color.Red.copy(alpha = 0.5f) else HextechDarkBg

    LaunchedEffect(parsedUrl) {
        if (parsedUrl.startsWith("file:///android_asset/")) {
            val assetPath = parsedUrl.removePrefix("file:///android_asset/")
            withContext(Dispatchers.IO) {
                try {
                    context.assets.open(assetPath).use { inputStream ->
                        val b = BitmapFactory.decodeStream(inputStream)
                        if (b != null) {
                            assetBitmap = b.asImageBitmap()
                        } else {
                            loadFailed = true
                        }
                    }
                } catch (e: Exception) {
                    loadFailed = true
                }
            }
        } else {
            // Not an asset, try to use Coil directly
        }
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(bgColor)
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
        
        val bitmap = assetBitmap
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape)
            )
        } else if (!parsedUrl.startsWith("file:///android_asset/") && parsedUrl.isNotBlank()) {
            val modelData: Any = if (parsedUrl.startsWith("file://")) {
                java.io.File(parsedUrl.removePrefix("file://"))
            } else {
                parsedUrl
            }
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(modelData)
                    .crossfade(true)
                    .placeholder(com.example.R.drawable.ic_placeholder_loading)
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
