import sys

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

target = """                                onClick = {
                                    onRoleChange("banned")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        // --- DYNAMIC RUNIC GOLDEN BORDER & CORNER ENGRAVINGS OVERLAY ---"""

replacement = """                                onClick = {
                                    onRoleChange("banned")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                
                // --- HARDWARE & DEVICES SECTION ---
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = LolBorderGoldDark.copy(alpha = 0.2f), modifier = Modifier.padding(horizontal = 4.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            androidx.compose.material.icons.Icons.Default.Devices,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Dispositivos (${user.registeredDevices.size}/2)",
                            color = TextSecondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (user.registeredDevices.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            user.registeredDevices.forEachIndexed { index, deviceId ->
                                Text(
                                    text = "Slot ${index + 1}: ${deviceId.take(12)}...",
                                    color = LolHextechCyan,
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "0 slots ocupados",
                            color = TextMuted,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
                
                // UID footer
                Text(
                    text = "UID: ${user.uid}",
                    color = TextMuted.copy(alpha = 0.35f),
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                )
            }
        }

        // --- DYNAMIC RUNIC GOLDEN BORDER & CORNER ENGRAVINGS OVERLAY ---"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)

print("Patch applied to AdminDashboardDialog.kt bottom layout")
