const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

// Add imports
if (!code.includes('import androidx.compose.material3.OutlinedTextField')) {
    code = code.replace('import androidx.compose.material3.Text', 'import androidx.compose.material3.OutlinedTextField\nimport androidx.compose.material3.Text');
}
if (!code.includes('import androidx.compose.material.icons.filled.Search')) {
    code = code.replace('import androidx.compose.material.icons.Icons', 'import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Search');
}

// Modify state and grouping
const stateRegex = /val filterOptions = remember \{[\s\S]*?var isUpdating by remember \{ mutableStateOf\(false\) \}/m;
const stateReplacement = `val filterOptions = remember {
        listOf("Todas") + AvatarCatalog.avatars.map { it.region }.filter { validRegions.contains(it) }.distinct().sorted()
    }
    var selectedFilter by remember { mutableStateOf(filterOptions.firstOrNull() ?: "Todas") }
    
    var showPremiumRequiredDialog by remember { mutableStateOf<AvatarItem?>(null) }
    var isUpdating by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }`;
code = code.replace(stateRegex, stateReplacement);

const groupedRegex = /val groupedAvatars = remember\(selectedFilter\) \{[\s\S]*?\.groupBy \{ it\.region \}\.toSortedMap\(\)\n    \}/m;
const groupedReplacement = `val groupedAvatars = remember(selectedFilter, searchQuery) {
        AvatarCatalog.avatars.filter {
            val matchesRegion = selectedFilter == "Todas" || it.region.equals(selectedFilter, ignoreCase = true)
            val matchesSearch = searchQuery.isBlank() || it.name.contains(searchQuery, ignoreCase = true)
            matchesRegion && matchesSearch
        }.groupBy { it.region }.toSortedMap()
    }`;
code = code.replace(groupedRegex, groupedReplacement);

// Insert Search Bar UI before the Filter Chips Carousel
const filterChipsCarousel = `            // Filter Chips Carousel`;
const searchBarUi = `            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                placeholder = { Text(tr("Buscar avatar..."), color = TextMuted) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechGold,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechDarkBg,
                    unfocusedContainerColor = HextechSurface,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextSecondary
                )
            )

            // Filter Chips Carousel`;
code = code.replace(filterChipsCarousel, searchBarUi);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
