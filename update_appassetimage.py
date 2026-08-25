import re

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace AsyncImage usage in AppAssetImage with SubcomposeAsyncImage to show fallback text on error or loading
new_appasset = """
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
    
    val modelData: Any? = if (parsedUrl.startsWith("file://")) {
        java.io.File(parsedUrl.removePrefix("file://"))
    } else if (parsedUrl.isNotBlank()) {
        parsedUrl
    } else {
        null
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(HextechDarkBg)
            .border(1.dp, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        if (modelData == null) {
            Text(
                text = fallbackText.take(2).uppercase(),
                color = borderColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            coil.compose.SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(modelData)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape),
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = fallbackText.take(2).uppercase(),
                            color = borderColor.copy(alpha = 0.5f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                error = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = fallbackText.take(2).uppercase(),
                            color = borderColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}
"""

content = re.sub(r'@Composable\s+fun AppAssetImage\(.*?\)\s*\{.*\}', new_appasset.strip(), content, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w', encoding='utf-8') as f:
    f.write(content)

