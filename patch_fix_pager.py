import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

box_old = """        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {"""
box_new = """        Box(modifier = Modifier.padding(paddingValues)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true
            ) { page ->
                when (page) {"""
text = text.replace(box_old, box_new)

# Now fix the extra brace I added at the bottom of DashboardScreen.
text = text.replace(
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
            }
        }""",
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
        }"""
)

# wait, I probably didn't successfully replace it the first time. Let's make sure:
text = text.replace(
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
        }
}
@Composable""",
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
        }
    }
}
@Composable"""
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
