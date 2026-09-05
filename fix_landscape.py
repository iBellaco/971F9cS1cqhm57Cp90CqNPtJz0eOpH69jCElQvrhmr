import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

landscape_list_code = """@Composable
private fun LandscapeVerticalTeamList(
    isEnemy: Boolean,
    slots: List<com.example.ui.components.DraftSlot>,
    activeUserRole: LaneRole?,
    onPickChampionForRole: (LaneRole) -> Unit,
    onRemoveChampionForRole: (LaneRole) -> Unit,
    onChampionClick: (Champion) -> Unit
) {
    val roles = listOf(
        Triple(LaneRole.TOP, "TOP", com.example.R.drawable.ic_wr_role_solo),
        Triple(LaneRole.JUNGLE, "JUNGLA", com.example.R.drawable.ic_wr_role_jungle),
        Triple(LaneRole.MID, "MID", com.example.R.drawable.ic_wr_role_mid),
        Triple(LaneRole.ADC, "DÚO", com.example.R.drawable.ic_wr_role_duo),
        Triple(LaneRole.SUPPORT, "SOPORTE", com.example.R.drawable.ic_wr_role_support)
    )
    Column(
        modifier = Modifier.fillMaxHeight().width(64.dp).padding(vertical = 4.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        roles.forEach { (role, labelKey, iconRes) ->
            val slot = slots.find { it.assignedRole == role }
            val champ = slot?.champion
            val isMyRole = !isEnemy && role == activeUserRole
            val isOccupied = champ != null
            val borderColor = when {
                isMyRole -> com.example.ui.theme.HextechCyan
                isOccupied -> if (isEnemy) com.example.ui.theme.DangerRed.copy(alpha = 0.7f) else com.example.ui.theme.HextechGold
                else -> com.example.ui.theme.HextechCardBorder
            }
            val bgColor = when {
                isMyRole -> com.example.ui.theme.HextechCyan.copy(alpha = 0.2f)
                isOccupied -> if (isEnemy) com.example.ui.theme.DangerRed.copy(alpha = 0.15f) else com.example.ui.theme.HextechGold.copy(alpha = 0.15f)
                else -> Color(0xFF0A121D)
            }
            val iconTint = when {
                isMyRole -> com.example.ui.theme.HextechCyan
                isOccupied -> if (isEnemy) com.example.ui.theme.DangerRed else com.example.ui.theme.HextechGold
                else -> com.example.ui.theme.TextMuted
            }

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(bgColor)
                    .border(1.5.dp, borderColor, CircleShape)
                    .clickable { onPickChampionForRole(role) },
                contentAlignment = Alignment.Center
            ) {
                if (champ != null) {
                    com.example.ui.components.AppAssetImage(
                        url = champ.avatarUrl,
                        contentDescription = champ.name,
                        fallbackText = champ.name,
                        modifier = Modifier.fillMaxSize().clip(CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.75f))
                            .clickable { onRemoveChampionForRole(role) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Quitar",
                            tint = Color.White,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                } else {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = iconRes),
                        contentDescription = labelKey,
                        tint = iconTint,
                        modifier = Modifier.size(24.dp).padding(1.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingDraftCoachView("""

text = text.replace("@Composable\nprivate fun FloatingDraftCoachView(", landscape_list_code)

old_landscape_block = """        if (isLandscapeMode) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
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
                Box(modifier = Modifier.weight(1f)) {
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
        }"""

new_landscape_block = """        if (isLandscapeMode) {
            Row(
                modifier = Modifier.fillMaxWidth().height(360.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LandscapeVerticalTeamList(
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
                
                // Centro: Todo el Coach (scrollable verticalmente)
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(horizontal = 6.dp).verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                }
                
                LandscapeVerticalTeamList(
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
        }"""

text = text.replace(old_landscape_block, new_landscape_block)
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

