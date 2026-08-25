import re

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r', encoding='utf-8') as f:
    content = f.read()

new_async = '''AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
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
                    imageLoader = LocalContext.current.imageLoader,
                    contentDescription = champion.name,'''

content = re.sub(r'AsyncImage\([\s\S]*?contentDescription = champion\.name,', new_async, content)

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w', encoding='utf-8') as f:
    f.write(content)
