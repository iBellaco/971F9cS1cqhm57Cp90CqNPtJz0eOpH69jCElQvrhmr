import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = """    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 4.dp)
    ) {
        // TABLERO DE DRAFT VERSUS (ALIADO VS RIVAL POR LÍNEAS)
        OverlayVersusDraftBoard(
            allySlots = allySlots,
            enemySlots = enemySlots,
            activeUserRole = activeRole,
            onPickChampionForRole = { isAlly, role ->
                val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                onOpenChampionPicker(isAlly, index)
            },
            onRemoveChampionForRole = { isAlly, role ->
                val roleIndex = defaultRoles.indexOf(role)
                if (roleIndex in 0 until 5) {
                    if (isAlly) allies[roleIndex] = null else enemies[roleIndex] = null
                    onManualEdit()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)
        CoachContent(
            allies = allies,
            enemies = enemies,
            activeRole = activeRole,
            onActiveRoleChange = onActiveRoleChange,
            isFirstPick = isFirstPick,
            onFirstPickToggle = onFirstPickToggle,
            analysis = analysis,
            explicitEnemyOpponent = explicitEnemyOpponent,
            onSelectChampion = onSelectChampion,
            onSaveDraftClick = onSaveDraftClick,
            isSavedRecently = isSavedRecently,
            onClearAll = onClearAll,
            onGoToTierList = onGoToTierList,
            isPremium = isPremium
        )
    }"""

replacement = """    if (isLandscapeMode) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp)
        ) {
            // TABLERO DE DRAFT VERSUS (ALIADO VS RIVAL POR LÍNEAS)
            Box(modifier = Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                OverlayVersusDraftBoard(
                    allySlots = allySlots,
                    enemySlots = enemySlots,
                    activeUserRole = activeRole,
                    onPickChampionForRole = { isAlly, role ->
                        val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                        onOpenChampionPicker(isAlly, index)
                    },
                    onRemoveChampionForRole = { isAlly, role ->
                        val roleIndex = defaultRoles.indexOf(role)
                        if (roleIndex in 0 until 5) {
                            if (isAlly) allies[roleIndex] = null else enemies[roleIndex] = null
                            onManualEdit()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)
            Box(modifier = Modifier.weight(1.15f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                CoachContent(
                    allies = allies,
                    enemies = enemies,
                    activeRole = activeRole,
                    onActiveRoleChange = onActiveRoleChange,
                    isFirstPick = isFirstPick,
                    onFirstPickToggle = onFirstPickToggle,
                    analysis = analysis,
                    explicitEnemyOpponent = explicitEnemyOpponent,
                    onSelectChampion = onSelectChampion,
                    onSaveDraftClick = onSaveDraftClick,
                    isSavedRecently = isSavedRecently,
                    onClearAll = onClearAll,
                    onGoToTierList = onGoToTierList,
                    isPremium = isPremium
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 4.dp)
        ) {
            // TABLERO DE DRAFT VERSUS (ALIADO VS RIVAL POR LÍNEAS)
            OverlayVersusDraftBoard(
                allySlots = allySlots,
                enemySlots = enemySlots,
                activeUserRole = activeRole,
                onPickChampionForRole = { isAlly, role ->
                    val index = defaultRoles.indexOf(role).coerceAtLeast(0)
                    onOpenChampionPicker(isAlly, index)
                },
                onRemoveChampionForRole = { isAlly, role ->
                    val roleIndex = defaultRoles.indexOf(role)
                    if (roleIndex in 0 until 5) {
                        if (isAlly) allies[roleIndex] = null else enemies[roleIndex] = null
                        onManualEdit()
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)
            CoachContent(
                allies = allies,
                enemies = enemies,
                activeRole = activeRole,
                onActiveRoleChange = onActiveRoleChange,
                isFirstPick = isFirstPick,
                onFirstPickToggle = onFirstPickToggle,
                analysis = analysis,
                explicitEnemyOpponent = explicitEnemyOpponent,
                onSelectChampion = onSelectChampion,
                onSaveDraftClick = onSaveDraftClick,
                isSavedRecently = isSavedRecently,
                onClearAll = onClearAll,
                onGoToTierList = onGoToTierList,
                isPremium = isPremium
            )
        }
    }"""

text = text.replace(target, replacement)
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

