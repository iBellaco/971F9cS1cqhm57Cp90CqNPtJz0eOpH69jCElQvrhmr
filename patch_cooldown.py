import re

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r', encoding='utf-8') as f:
    content = f.read()

old = '''AsyncImage(
                                        model = spell.iconUrl,
                                        contentDescription = tr(spell.name),
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )'''
new_str = '''AppAssetImage(
                                        url = spell.iconUrl,
                                        contentDescription = tr(spell.name),
                                        fallbackText = spell.iconFallback,
                                        modifier = Modifier.fillMaxSize(),
                                        borderColor = if (isActive) DangerRed else spell.accentColor,
                                        shape = RoundedCornerShape(6.dp)
                                    )'''

content = content.replace(old, new_str)
content = content.replace('import coil.compose.AsyncImage', 'import com.example.ui.components.AppAssetImage')

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w', encoding='utf-8') as f:
    f.write(content)
