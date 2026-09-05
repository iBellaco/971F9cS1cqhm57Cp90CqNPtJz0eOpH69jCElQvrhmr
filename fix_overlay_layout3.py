import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

old_block = """    Column(
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
            onPickChampionForRole = { role ->
                val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                onOpenChampionPicker(true, index)
            },
            onRemoveChampionForRole = { role ->
                val roleIndex = defaultRoles.indexOf(role)
                if (roleIndex in 0 until 5) {
                    allies[roleIndex] = null
                    onManualEdit()
                }
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
            onPickChampionForRole = { role ->
                val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                onOpenChampionPicker(false, index)
            },
            onRemoveChampionForRole = { role ->
                val roleIndex = defaultRoles.indexOf(role)
                if (roleIndex in 0 until 5) {
                    enemies[roleIndex] = null
                    onManualEdit()
                }
            },
            onChampionClick = onSelectChampion
        )

        Spacer(modifier = Modifier.height(6.dp))"""

new_block = """    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val isLandscapeMode = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier.fillMaxSize()
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
                        onPickChampionForRole = { role ->
                            val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                            onOpenChampionPicker(true, index)
                        },
                        onRemoveChampionForRole = { role ->
                            val roleIndex = defaultRoles.indexOf(role)
                            if (roleIndex in 0 until 5) {
                                allies[roleIndex] = null
                                onManualEdit()
                            }
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
                        onPickChampionForRole = { role ->
                            val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                            onOpenChampionPicker(false, index)
                        },
                        onRemoveChampionForRole = { role ->
                            val roleIndex = defaultRoles.indexOf(role)
                            if (roleIndex in 0 until 5) {
                                enemies[roleIndex] = null
                                onManualEdit()
                            }
                        },
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
                    onPickChampionForRole = { role ->
                        val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                        onOpenChampionPicker(true, index)
                    },
                    onRemoveChampionForRole = { role ->
                        val roleIndex = defaultRoles.indexOf(role)
                        if (roleIndex in 0 until 5) {
                            allies[roleIndex] = null
                            onManualEdit()
                        }
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
                    onPickChampionForRole = { role ->
                        val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                        onOpenChampionPicker(false, index)
                    },
                    onRemoveChampionForRole = { role ->
                        val roleIndex = defaultRoles.indexOf(role)
                        if (roleIndex in 0 until 5) {
                            enemies[roleIndex] = null
                            onManualEdit()
                        }
                    },
                    onChampionClick = onSelectChampion
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))"""

text = text.replace(old_block, new_block)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
