import sys

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

target = """@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoleChangeBottomSheet(
    currentRole: LaneRole,
    onRoleSelected: (LaneRole) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = HextechSurfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(tr("Selecciona tu Línea para esta Partida"), color = HextechGold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            LaneRole.entries.forEach { role ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (role == currentRole) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                        .border(1.dp, if (role == currentRole) HextechCyan else HextechCardBorder, RoundedCornerShape(10.dp))
                        .clickable { onRoleSelected(role) }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Icon(
                            painter = painterResource(id = role.iconResId),
                            contentDescription = null,
                            tint = if (role == currentRole) HextechCyan else HextechGold,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = tr(role.displayName),
                            color = if (role == currentRole) HextechCyan else TextPrimary,
                            fontSize = 16.sp,
                            fontWeight = if (role == currentRole) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                    if (role == currentRole) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}"""

replacement = """@Composable
private fun RoleChangeBottomSheet(
    currentRole: LaneRole,
    onRoleSelected: (LaneRole) -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant),
            border = BorderStroke(1.5.dp, HextechGold.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(tr("Selecciona tu Línea"), color = HextechGold, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                LaneRole.entries.forEach { role ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (role == currentRole) HextechCyan.copy(alpha = 0.2f) else HextechSurface)
                            .border(1.dp, if (role == currentRole) HextechCyan else HextechCardBorder, RoundedCornerShape(10.dp))
                            .clickable { onRoleSelected(role) }
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Icon(
                                painter = painterResource(id = role.iconResId),
                                contentDescription = null,
                                tint = if (role == currentRole) HextechCyan else HextechGold,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = tr(role.displayName),
                                color = if (role == currentRole) HextechCyan else TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = if (role == currentRole) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                        if (role == currentRole) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)

print("Patched RoleChangeBottomSheet")
