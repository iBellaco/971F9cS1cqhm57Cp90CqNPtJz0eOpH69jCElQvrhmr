import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

replacement = """                        )
                    }
                    // End Scrollable Column
                }
            }
        }
    } // End of Column (Main layout)

    // Notificaciones sobrepuestas (siempre visibles, incluso si esta minimizado)
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(start = 64.dp, top = 8.dp) // Empuja a la derecha de la burbuja (52dp)
    ) {
        com.example.ui.components.CooldownTrackerStateHolder.notifications.forEach { notif ->
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                enter = androidx.compose.animation.slideInHorizontally { it } + androidx.compose.animation.fadeIn(),
                exit = androidx.compose.animation.slideOutHorizontally { it } + androidx.compose.animation.fadeOut()
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.95f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, notif.color),
                    modifier = Modifier.widthIn(max = 240.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(notif.color.copy(alpha = 0.2f))
                                .border(1.dp, notif.color, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (notif.iconUrl.isNotEmpty()) {
                                coil.compose.AsyncImage(
                                    model = notif.iconUrl,
                                    contentDescription = null,
                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Text(
                                    text = notif.fallbackIcon,
                                    color = notif.color,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 8.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        // We translate the spell name dynamically here if possible, but message holds the string
                        val parts = notif.message.split(" de ")
                        val textStr = if (parts.size == 2) {
                            tr(parts[0]) + " de " + parts[1]
                        } else {
                            notif.message
                        }
                        Text(
                            text = textStr,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
} // End of Box
} // End of FloatingOverlayContent
"""

# The pattern searches for the last part of the file
pattern = r"                        \)\n                    \}\n                    // End Scrollable Column\n                \}\n            \}\n        \}\n    \}\n\}"
content = re.sub(pattern, replacement, content)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
