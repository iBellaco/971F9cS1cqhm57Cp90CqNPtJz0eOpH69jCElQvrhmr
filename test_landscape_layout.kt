@Composable
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
        modifier = Modifier.fillMaxHeight().width(60.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Just the slots
    }
}
