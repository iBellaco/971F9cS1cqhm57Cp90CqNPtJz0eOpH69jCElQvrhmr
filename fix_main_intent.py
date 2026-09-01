import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

bad_str = """    val initialPage = if (activity?.intent?.getBooleanExtra("OPEN_TIER_LIST", false) == true) 2 else 0
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { 5 })
    
    // Clear intent so we don't reopen tier list on rotation
    androidx.compose.runtime.LaunchedEffect(Unit) {
        activity?.intent?.removeExtra("OPEN_TIER_LIST")
    }"""

good_str = """    val targetChampId = activity?.intent?.getStringExtra("OPEN_CHAMPION_DETAIL")
    val initialPage = if (activity?.intent?.getBooleanExtra("OPEN_TIER_LIST", false) == true || targetChampId != null) 2 else 0
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { 5 })
    
    // Clear intent so we don't reopen tier list on rotation
    androidx.compose.runtime.LaunchedEffect(Unit) {
        activity?.intent?.removeExtra("OPEN_TIER_LIST")
        activity?.intent?.removeExtra("OPEN_CHAMPION_DETAIL")
    }"""

content = content.replace(bad_str, good_str)

bad_str2 = """                2 -> {
                    MetaAndDraftScreen(
                        mode = MetaScreenMode.TIER_LIST,
                        userMainRole = mainRole,
                        onNavigateBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                }"""

good_str2 = """                2 -> {
                    MetaAndDraftScreen(
                        mode = MetaScreenMode.TIER_LIST,
                        userMainRole = mainRole,
                        initialChampionId = targetChampId,
                        onNavigateBack = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
                    )
                }"""

content = content.replace(bad_str2, good_str2)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
