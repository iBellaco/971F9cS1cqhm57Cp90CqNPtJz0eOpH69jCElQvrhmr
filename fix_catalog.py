import sys

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

target = """                                    Text(
                                        text = "${com.example.util.tr(champion.primaryRole.displayName)} • ${com.example.util.tr(champion.damageType.displayName)}",
                                        color = HextechCyan,
                                        fontSize = 11.5.sp
                                    )"""

replacement = """                                    Text(
                                        text = "${com.example.util.tr(champion.primaryRole.displayName)} • ${com.example.util.tr(champion.damageType.displayName)}",
                                        color = HextechCyan,
                                        fontSize = if (isOverlay) 9.5.sp else 11.5.sp
                                    )"""

content = content.replace(target, replacement)

target2 = """                            Text(
                                text = champion.summary,
                                color = TextMuted,
                                fontSize = 11.sp,
                                maxLines = 2,
                                lineHeight = 15.sp
                            )"""

replacement2 = """                            Text(
                                text = champion.summary,
                                color = TextMuted,
                                fontSize = if (isOverlay) 9.5.sp else 11.sp,
                                maxLines = if (isOverlay) 1 else 2,
                                lineHeight = if (isOverlay) 12.sp else 15.sp,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )"""

content = content.replace(target2, replacement2)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
