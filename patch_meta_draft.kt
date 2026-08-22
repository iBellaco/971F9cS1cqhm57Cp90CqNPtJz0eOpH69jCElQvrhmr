        // --- INICIO INYECCIÓN ---
        // Damage distribution
        if (enemies.isNotEmpty()) {
            Text(tr("Balance de Daño Rival"), color = HextechGold, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))) {
                if (analysis.physicalDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.physicalDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFFE57373)))
                }
                if (analysis.magicDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.magicDamagePercent.toFloat()).fillMaxHeight().background(Color(0xFF64B5F6)))
                }
                if (analysis.trueDamagePercent > 0) {
                    Box(modifier = Modifier.weight(analysis.trueDamagePercent.toFloat()).fillMaxHeight().background(Color.White))
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${analysis.physicalDamagePercent}% " + tr("Físico"), color = Color(0xFFE57373), fontSize = 10.sp)
                Text("${analysis.magicDamagePercent}% " + tr("Mágico"), color = Color(0xFF64B5F6), fontSize = 10.sp)
                Text("${analysis.trueDamagePercent}% " + tr("Verdadero"), color = Color.White, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // My Champion Evaluation
        val myChamp = allies.find { it.primaryRole == activeRole || it.secondaryRoles.contains(activeRole) }
        if (myChamp != null) {
            val myEval = com.example.data.WildRiftRepository.evaluateChampion(myChamp, activeRole, allies, enemies, com.example.util.LocaleManager.currentLanguage)
            val shouldChange = myEval.estimatedWinrate < 49.0 || myEval.advantageBadge.contains("PELIGRO")
            
            Card(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).border(1.5.dp, if (shouldChange) DangerRed else HextechCyan, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = HextechSurface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(tr("TU ELECCIÓN ACTUAL"), color = if (shouldChange) DangerRed else HextechCyan, fontSize = 12.sp, fontWeight = FontWeight.Black)
                        Text(tr("Winrate Est.:") + " ${myEval.estimatedWinrate}%", color = if (shouldChange) DangerRed else HextechCyan, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ChampionAvatar(champion = myEval.champion, size = 48.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(myEval.champion.name, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(if (shouldChange) tr("⚠️ Considera cambiarlo") else tr("✅ Buena elección"), color = if (shouldChange) DangerRed else Color(0xFF81C784), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(myEval.tacticalReason, color = TextMuted, fontSize = 12.sp, lineHeight = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        // --- FIN INYECCIÓN ---
