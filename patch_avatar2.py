import re

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r', encoding='utf-8') as f:
    content = f.read()

new_image = '''coil.compose.SubcomposeAsyncImage(
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
                contentDescription = contentDescription ?: fallbackText,'''

content = re.sub(r'coil\.compose\.SubcomposeAsyncImage\([\s\S]*?contentDescription = contentDescription \?: fallbackText,', new_image, content)

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w', encoding='utf-8') as f:
    f.write(content)
