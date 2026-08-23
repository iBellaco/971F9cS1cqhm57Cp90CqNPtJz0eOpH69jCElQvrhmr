cat << 'INNER_EOF' > replacement_main.txt
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = com.example.ui.theme.HextechDarkBg,
                contentColor = com.example.ui.theme.HextechGold
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(tr("Inicio")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HextechDarkBg,
                        selectedTextColor = HextechGold,
                        indicatorColor = HextechGold,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Groups, contentDescription = "Drafting") },
                    label = { Text(tr("Drafting")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HextechDarkBg,
                        selectedTextColor = HextechGold,
                        indicatorColor = HextechGold,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Catálogo") },
                    label = { Text(tr("Catálogo")) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HextechDarkBg,
                        selectedTextColor = HextechCyan,
                        indicatorColor = HextechCyan,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (selectedTab == 0) {
                MainDraftingScreen(
                    onNavigateToInfo = onNavigateToInfo,
                    onNavigateToMeta = { selectedTab = 2 }, // Navigates to Catalog tab
                    onNavigateToLogin = onNavigateToLogin,
                    mainRole = mainRole,
                    onMainRoleChange = onMainRoleChange,
                    secondRole = secondRole,
                    onSecondRoleChange = onSecondRoleChange,
                    autofillRole = autofillRole,
                    onAutofillRoleChange = onAutofillRoleChange,
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange
                )
            } else if (selectedTab == 1) {
                MetaAndDraftScreen(
                    showOnlyDrafting = true,
                    userMainRole = mainRole,
                    onNavigateBack = { selectedTab = 0 }
                )
            } else {
                MetaAndDraftScreen(
                    showOnlyDrafting = false,
                    userMainRole = mainRole,
                    onNavigateBack = { selectedTab = 0 }
                )
            }
        }
    }
INNER_EOF

# Replace lines 126 to 183 in MainActivity.kt
awk '
NR==126 {
    while ((getline line < "replacement_main.txt") > 0) {
        print line
    }
    close("replacement_main.txt")
    skip=1
}
NR==184 {
    skip=0
}
!skip { print }
' app/src/main/java/com/example/MainActivity.kt > temp.kt && mv temp.kt app/src/main/java/com/example/MainActivity.kt
