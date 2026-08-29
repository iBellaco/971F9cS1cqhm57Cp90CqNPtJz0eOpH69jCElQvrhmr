import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    text = f.read()

imports = """import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
"""
if 'import androidx.compose.foundation.pager.HorizontalPager' not in text:
    text = text.replace('import androidx.compose.foundation.layout.*', 'import androidx.compose.foundation.layout.*\n' + imports)

dashboard_state_old = """    var selectedTab by remember { mutableStateOf(0) }
    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current"""

dashboard_state_new = """    val pagerState = rememberPagerState(pageCount = { 5 })
    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()"""
text = text.replace(dashboard_state_old, dashboard_state_new)

text = re.sub(r'selectedTab == (\d)', r'pagerState.currentPage == \1', text)
text = re.sub(r'selectedTab = (\d)', r'coroutineScope.launch { pagerState.animateScrollToPage(\1) }', text)

# Now fix the when block inside Box
box_old = """        Box(modifier = Modifier.padding(paddingValues)) {
            when (pagerState.currentPage) {"""
box_new = """        Box(modifier = Modifier.padding(paddingValues)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true
            ) { page ->
                when (page) {"""
text = text.replace(box_old, box_new)

# Add closing brace for HorizontalPager
text = text.replace(
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
        }""",
    """                        onLanguageChange = onLanguageChange
                    )
                }
            }
            }
        }"""
)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(text)
