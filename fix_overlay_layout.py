import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# 1. Update the Card size
old_card = """                var isDraggingPanel by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .widthIn(min = 300.dp, max = 340.dp)
                        .height(530.dp)
                        .clip(RoundedCornerShape(16.dp)),"""

new_card = """                var isDraggingPanel by remember { mutableStateOf(false) }
                val configuration = androidx.compose.ui.platform.LocalConfiguration.current
                val isLandscapeMode = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
                
                Card(
                    modifier = Modifier
                        .width(if (isLandscapeMode) 560.dp else 330.dp)
                        .height(if (isLandscapeMode) 360.dp else 520.dp)
                        .clip(RoundedCornerShape(16.dp)),"""

text = text.replace(old_card, new_card)

# 2. Update the Draft layout (Ally / Enemy)
old_draft_layout = """    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 1. TABLERO DE DRAFT (EQUIPO ALIADO Y RIVAL) CON EL MODELO EXACTO DE LA APP
        com.example.ui.components.DraftTeamPositionCard(
            isOverlay = true,
            title = tr("Equipo Aliado"),
            isEnemy = false,
            slots = allySlots,
            activeUserRole = activeRole,
            detectedEnemyRole = null,
            onRoleSelect = { r -> 
                activeRole = r 
                com.example.util.UserPreferences.setActiveDraftRole(context, r)
            },
            onChampionClick = onSelectChampion
        )

        Spacer(modifier = Modifier.height(8.dp))

        com.example.ui.components.DraftTeamPositionCard(
            isOverlay = true,
            title = tr("Equipo Rival"),
            isEnemy = true,
            slots = enemySlots,
            activeUserRole = activeRole,
            detectedEnemyRole = explicitEnemyOpponent?.primaryRole,
            onRoleSelect = { },
            onChampionClick = onSelectChampion
        )

        Spacer(modifier = Modifier.height(12.dp))"""

new_draft_layout = """    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val isLandscapeMode = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 1. TABLERO DE DRAFT (EQUIPO ALIADO Y RIVAL) CON EL MODELO EXACTO DE LA APP
        if (isLandscapeMode) {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                    com.example.ui.components.DraftTeamPositionCard(
                        isOverlay = true,
                        title = tr("Equipo Aliado"),
                        isEnemy = false,
                        slots = allySlots,
                        activeUserRole = activeRole,
                        detectedEnemyRole = null,
                        onRoleSelect = { r -> 
                            activeRole = r 
                            com.example.util.UserPreferences.setActiveDraftRole(context, r)
                        },
                        onChampionClick = onSelectChampion
                    )
                }
                Box(modifier = Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                    com.example.ui.components.DraftTeamPositionCard(
                        isOverlay = true,
                        title = tr("Equipo Rival"),
                        isEnemy = true,
                        slots = enemySlots,
                        activeUserRole = activeRole,
                        detectedEnemyRole = explicitEnemyOpponent?.primaryRole,
                        onRoleSelect = { },
                        onChampionClick = onSelectChampion
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())
            ) {
                com.example.ui.components.DraftTeamPositionCard(
                    isOverlay = true,
                    title = tr("Equipo Aliado"),
                    isEnemy = false,
                    slots = allySlots,
                    activeUserRole = activeRole,
                    detectedEnemyRole = null,
                    onRoleSelect = { r -> 
                        activeRole = r 
                        com.example.util.UserPreferences.setActiveDraftRole(context, r)
                    },
                    onChampionClick = onSelectChampion
                )

                Spacer(modifier = Modifier.height(8.dp))

                com.example.ui.components.DraftTeamPositionCard(
                    isOverlay = true,
                    title = tr("Equipo Rival"),
                    isEnemy = true,
                    slots = enemySlots,
                    activeUserRole = activeRole,
                    detectedEnemyRole = explicitEnemyOpponent?.primaryRole,
                    onRoleSelect = { },
                    onChampionClick = onSelectChampion
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))"""

text = text.replace(old_draft_layout, new_draft_layout)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
