import re

with open('app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt', 'r') as f:
    content = f.read()

old_box = """                    if (!isVideo && externalUrl.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 12.dp)
                                .background(HextechDarkBg.copy(alpha = 0.85f), RoundedCornerShape(20.dp))
                                .border(1.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
                                .clickable { openLinkAction() }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.TouchApp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Toca la imagen para abrir enlace", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }"""

new_box = """                    if (!isVideo && externalUrl.isNotBlank()) {
                        val infiniteTransition = rememberInfiniteTransition()
                        val pulseScale by infiniteTransition.animateFloat(
                            initialValue = 1f,
                            targetValue = 1.05f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(1000, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 12.dp, bottom = 12.dp)
                                .scale(pulseScale)
                                .background(HextechDarkBg.copy(alpha = 0.85f), RoundedCornerShape(20.dp))
                                .border(1.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
                                .clickable { openLinkAction() }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.TouchApp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Toca la imagen para abrir enlace", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }"""

content = content.replace(old_box, new_box)

with open('app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt', 'w') as f:
    f.write(content)
