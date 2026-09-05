import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

bad_closing = """                            }
                            }
                        }
                    }
                }
            }
        }
    }
}"""

good_closing = """                            }

                            IconButton(
                                onClick = { /* Auto-updating */ },
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                    .background(LolCardBg)
                                    .border(1.dp, LolBorderGold.copy(alpha = 0.5f), androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                            ) {
                                androidx.compose.material3.Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.Refresh,
                                    contentDescription = "Recargar",
                                    tint = LolGoldLight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}"""

text = text.replace(bad_closing, good_closing)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
