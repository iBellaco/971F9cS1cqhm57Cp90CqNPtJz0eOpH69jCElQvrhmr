import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Replace the Row block inside FloatingDraftCoachView
start_marker = "        // TABLERO DE DRAFT (EQUIPO ALIADO Y RIVAL) LADO A LADO\n        Row(\n            modifier = Modifier.fillMaxWidth(),"
end_marker = "        Spacer(modifier = Modifier.height(8.dp))\n\n        // CONTENIDO DEL COACH (CONTROLES Y ANÁLISIS)"

idx_start = text.find(start_marker)
idx_end = text.find(end_marker)

if idx_start != -1 and idx_end != -1:
    new_board_call = """        // TABLERO DE DRAFT VERSUS (ALIADO VS RIVAL POR LÍNEAS)
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

"""
    text = text[:idx_start] + new_board_call + text[idx_end:]


overlay_versus_board = """
@Composable
private fun OverlayVersusDraftBoard(
    allySlots: List<DraftSlot>,
    enemySlots: List<DraftSlot>,
    activeUserRole: LaneRole?,
    onPickChampionForRole: (isAlly: Boolean, LaneRole) -> Unit,
    onRemoveChampionForRole: (isAlly: Boolean, LaneRole) -> Unit
) {
    val roles = listOf(
        Triple(LaneRole.TOP, "TOP", R.drawable.ic_wr_role_solo),
        Triple(LaneRole.JUNGLE, "JUG", R.drawable.ic_wr_role_jungle),
        Triple(LaneRole.MID, "MID", R.drawable.ic_wr_role_mid),
        Triple(LaneRole.ADC, "DÚO", R.drawable.ic_wr_role_duo),
        Triple(LaneRole.SUPPORT, "SUP", R.drawable.ic_wr_role_support)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = HextechSurface),
        border = BorderStroke(1.dp, HextechCardBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(tr("ALIADO"), color = AllyBlue, fontWeight = FontWeight.Black, fontSize = 11.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
                Text("VS", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 10.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text(tr("RIVAL"), color = DangerRed, fontWeight = FontWeight.Black, fontSize = 11.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }

            roles.forEachIndexed { index, (role, label, iconRes) ->
                val allySlot = allySlots.find { it.assignedRole == role }
                val enemySlot = enemySlots.find { it.assignedRole == role }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ally Avatar
                    DraftAvatarBox(
                        slot = allySlot,
                        isEnemy = false,
                        isMyRole = activeUserRole == role,
                        onClick = { onPickChampionForRole(true, role) },
                        onRemove = { onRemoveChampionForRole(true, role) }
                    )

                    // Center Role
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(40.dp)) {
                        Icon(painterResource(id = iconRes), contentDescription = label, tint = HextechGold, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(label, color = TextSecondary, fontSize = 8.sp, fontWeight = FontWeight.SemiBold)
                    }

                    // Enemy Avatar
                    DraftAvatarBox(
                        slot = enemySlot,
                        isEnemy = true,
                        isMyRole = false,
                        onClick = { onPickChampionForRole(false, role) },
                        onRemove = { onRemoveChampionForRole(false, role) }
                    )
                }
                
                if (index < roles.size - 1) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(HextechCardBorder.copy(alpha=0.5f)))
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
private fun DraftAvatarBox(
    slot: DraftSlot?,
    isEnemy: Boolean,
    isMyRole: Boolean,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    val champ = slot?.champion
    val borderColor = if (isMyRole) HextechCyan else if (champ != null) (if (isEnemy) DangerRed else HextechGold) else HextechCardBorder
    
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(when {
                isMyRole -> HextechCyan.copy(alpha = 0.22f)
                champ != null -> if (isEnemy) DangerRed.copy(alpha=0.2f) else HextechGold.copy(alpha=0.2f)
                else -> Color(0xFF070D15)
            })
            .border(1.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (champ != null) {
            AppAssetImage(
                url = champ.avatarUrl,
                contentDescription = champ.name,
                fallbackText = champ.name,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
            )
            if (isMyRole) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(HextechCyan)
                        .padding(horizontal = 3.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = tr("TÚ"),
                        color = Color.Black,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(16.dp)
                    .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(bottomStart = 8.dp))
                    .clickable { onRemove() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = "Quitar", tint = Color.White, modifier = Modifier.size(12.dp))
            }
        } else {
            Icon(Icons.Default.Add, contentDescription = "Añadir", tint = TextMuted, modifier = Modifier.size(20.dp))
        }
    }
}

"""

# Append overlay_versus_board right before @Composable private fun CoachContent
coach_idx = text.find("@Composable\nprivate fun CoachContent")
if coach_idx != -1:
    text = text[:coach_idx] + overlay_versus_board + "\n" + text[coach_idx:]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

