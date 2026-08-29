import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Replace userEmail with currentUser
text = re.sub(
    r'var userEmail by remember \{ mutableStateOf\(auth\?.currentUser\?.email\) \}\s*LaunchedEffect\(userEmail\) \{\s*SubscriptionManager.init\(context\)\s*\}\s*// Check if user is already authenticated\s*if \(userEmail != null\) \{\s*AuthenticatedProfilePanel\(\s*email = userEmail!!,\s*onSignOut = \{\s*auth\?.signOut\(\)\s*userEmail = null\s*\}\s*\)\s*return\s*\}\s*val uiState by viewModel.uiState.collectAsState\(\)\s*// Triggered when login/register succeeds to force a recomposition with the new user state\s*val onAuthSuccess: \(\) -> Unit = \{\s*userEmail = auth\?.currentUser\?.email',
    """var currentUser by remember { mutableStateOf(auth?.currentUser) }
    
    LaunchedEffect(currentUser) {
        SubscriptionManager.init(context)
    }
    
    // Check if user is already authenticated
    if (currentUser != null) {
        AuthenticatedProfilePanel(
            user = currentUser!!,
            onSignOut = {
                auth?.signOut()
                currentUser = null
            }
        )
        return
    }

    val uiState by viewModel.uiState.collectAsState()

    // Triggered when login/register succeeds to force a recomposition with the new user state
    val onAuthSuccess: () -> Unit = {
        currentUser = auth?.currentUser""",
    text
)

# Replace AuthenticatedProfilePanel signature
text = re.sub(
    r'fun AuthenticatedProfilePanel\(email: String, onSignOut: \(\) -> Unit\) \{',
    r'fun AuthenticatedProfilePanel(user: com.google.firebase.auth.FirebaseUser, onSignOut: () -> Unit) {',
    text
)

# Replace email text
replacement = """            val displayName = user.displayName?.takeIf { it.isNotBlank() } ?: user.email?.substringBefore("@") ?: "Usuario"
            Text(
                text = displayName,
                color = HextechGold,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            
            var isEmailVisible by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp).clickable { isEmailVisible = !isEmailVisible }
            ) {
                Text(
                    text = if (isEmailVisible) (user.email ?: "") else "••••••••@••••.com",
                    color = com.example.ui.theme.TextMuted,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = if (isEmailVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = null,
                    tint = com.example.ui.theme.TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))"""

text = re.sub(
    r'Text\(\s*text = email,\s*color = HextechCyan,\s*fontSize = 16.sp\s*\)\s*Spacer\(modifier = Modifier.height\(16.dp\)\)',
    replacement,
    text
)

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
