with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    content = f.read()

bad_str = """    val pagerState = rememberPagerState(pageCount = { 5 })
    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current"""

good_str = """    val context = LocalContext.current
    val activity = context as? android.app.Activity
    val initialPage = if (activity?.intent?.getBooleanExtra("OPEN_TIER_LIST", false) == true) 2 else 0
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { 5 })
    
    // Clear intent so we don't reopen tier list on rotation
    androidx.compose.runtime.LaunchedEffect(Unit) {
        activity?.intent?.removeExtra("OPEN_TIER_LIST")
    }
    
    var showExitDialog by remember { mutableStateOf(false) }"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(content)
