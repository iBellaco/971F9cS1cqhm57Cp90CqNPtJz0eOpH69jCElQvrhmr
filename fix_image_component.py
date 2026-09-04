import re

with open("app/src/main/java/com/example/ui/components/ChampionAvatar.kt", "r") as f:
    content = f.read()

# We need to replace the AppAssetImage to just use standard AsyncImage again
# Because the fallback logic we added was specifically for file:///android_asset/

new_component = """@Composable
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
}"""

# Replace everything from @Composable fun AppAssetImage to the end of the file
import re
new_content = re.sub(r'@Composable\s*fun AppAssetImage\(.*', new_component, content, flags=re.DOTALL)

with open("app/src/main/java/com/example/ui/components/ChampionAvatar.kt", "w") as f:
    f.write(new_content)
    
print("AppAssetImage reverted.")
