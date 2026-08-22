import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

eval_target = """        // My Champion Evaluation
        
        if (myChampion != null) {
            val myChamp = myChampion
            val myEval = com.example.data.WildRiftRepository.evaluateChampion(myChamp, activeRole, allies, enemies, "es")
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
        }"""
        
eval_replacement = """        // My Champion Evaluation
        if (myChampion != null) {
            val myChamp = myChampion
            val myEval = com.example.data.WildRiftRepository.evaluateChampion(myChamp, activeRole, allies, enemies, "es")
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
                        IconButton(onClick = onRemoveMyChampion, modifier = Modifier.size(24.dp)) {
                            Icon(Icons.Default.Close, contentDescription = tr("Eliminar"), tint = TextMuted, modifier = Modifier.size(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(myEval.tacticalReason, color = TextMuted, fontSize = 12.sp, lineHeight = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Button(
                onClick = onAddMyChampion,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = HextechCyan.copy(alpha=0.15f), contentColor = HextechCyan),
                border = androidx.compose.foundation.BorderStroke(1.dp, HextechCyan.copy(alpha=0.5f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(tr("SELECCIONAR MI CAMPEÓN"), fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }"""
content = content.replace(eval_target, eval_replacement)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
