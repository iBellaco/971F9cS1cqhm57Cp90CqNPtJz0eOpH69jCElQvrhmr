import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Remove LandscapeVerticalTeamList
landscape_idx = text.find("private fun LandscapeVerticalTeamList(")
if landscape_idx != -1:
    end_landscape_idx = text.find("@Composable\nprivate fun FloatingDraftCoachView(", landscape_idx)
    text = text[:landscape_idx] + text[end_landscape_idx:]

# Rewrite FloatingDraftCoachView body
# We look for `    val enemySlots = remember(enemies.toList()) { ... }`
marker = "        defaultRoles.mapIndexedNotNull { index, role ->\n            enemies.getOrNull(index)?.let { DraftSlot(champion = it, assignedRole = role) }\n        }\n    }\n"
marker_idx = text.find(marker)
if marker_idx != -1:
    start_body_idx = marker_idx + len(marker)
    # We find the end of the FloatingDraftCoachView function by searching for `    }\n}\n@Composable\nprivate fun CoachContent(`
    end_body_idx = text.find("    }\n}\n@Composable\nprivate fun CoachContent(")
    
    new_body = """
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 4.dp)
    ) {
        // TABLERO DE DRAFT (EQUIPO ALIADO Y RIVAL) LADO A LADO
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            com.example.ui.components.DraftTeamPositionCard(
                modifier = Modifier.weight(1f),
                isOverlay = true,
                title = tr("Aliado"),
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
            com.example.ui.components.DraftTeamPositionCard(
                modifier = Modifier.weight(1f),
                isOverlay = true,
                title = tr("Rival"),
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

        Spacer(modifier = Modifier.height(8.dp))

        // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)
        CoachContent(
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
"""
    
    text = text[:start_body_idx] + new_body + text[end_body_idx:]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
