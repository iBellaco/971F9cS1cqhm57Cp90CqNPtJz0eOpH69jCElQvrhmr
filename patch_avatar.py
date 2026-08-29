import re

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

target = """        if (modelData != null) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(modelData)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .listener(
                        onError = { request, result -> 
                            com.example.util.AppLogger.e("ImageLoader", "Failed to load ${request.data}: ${result.throwable.message}") 
                        }
                    )
                    .build(),
                imageLoader = context.imageLoader,
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape)
            )"""

replacement = """        if (modelData != null && parsedUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(modelData)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .listener(
                        onError = { request, result -> 
                            com.example.util.AppLogger.e("ImageLoader", "Failed to load ${request.data}: ${result.throwable.message}") 
                        }
                    )
                    .build(),
                imageLoader = context.imageLoader,
                contentDescription = contentDescription ?: fallbackText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(shape),
                error = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_report_image)
            )
        } else {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.BrokenImage,
                contentDescription = "Missing Image",
                tint = borderColor.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )
        }"""

if target in text:
    text = text.replace(target, replacement)
    
    # Also add BrokenImage import
    text = text.replace("import androidx.compose.material.icons.filled.Warning", "import androidx.compose.material.icons.filled.Warning\\nimport androidx.compose.material.icons.filled.BrokenImage")
    
    with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
        f.write(text)
    print("Patched AppAssetImage")
else:
    print("Target not found")
