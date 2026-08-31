const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'utf8');

// Update Title
code = code.replace(/text = tr\("Matchup Preview 1v1"\)/g, 'text = tr("Análisis de Enfrentamiento")');

const synergyBlock = `                    // 3. Sinergias y Macro Wild Rift
                    Text(
                        text = "🤝 " + tr("Sinergias y Macro (Wild Rift)"),
                        color = HextechCyan,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(0.8.dp, HextechCyan.copy(alpha = 0.6f))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Condición de Victoria Móvil:",
                                color = HextechCyan,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Aprovecha que las rotaciones en Wild Rift son extremadamente rápidas. Prioriza limpiar tu oleada y rotar al Escurridizo o Dragón antes del minuto 5. Si \${enemyOpponent.name} rota primero, castiga su torre para conseguir las valiosas placas de oro.",
                                color = TextPrimary,
                                fontSize = 11.sp,
                                lineHeight = 14.5.sp
                            )
                        }
                    }`;

const itemizationRegex = /\s*\/\/ 3\. Itemización Reactiva[\s\S]*?\}\s*\}/;

if (itemizationRegex.test(code)) {
    code = code.replace(itemizationRegex, '\n' + synergyBlock);
} else {
    console.log("Could not find itemization block with regex!");
}

// Adjust Coach text
const coachTextOld = `                                    text = if (isMyCounter) 
                                        "Tienes la ventaja teórica. No te sobreconfíes con ganks del jungla rival y asegura la prioridad de oleadas para apoyar al Dragón/Heraldo."
                                    else if (isEnemyCounter)
                                        "Juega con paciencia y minimiza pérdidas de oro. El verdadero impacto de \${myChampion.name} llegará en las peleas 5v5 agrupadas."
                                    else
                                        "Matchup equilibrado de pura habilidad. Controla la visión del río y castiga cuando el rival use habilidades en los súbditos.",`;

const coachTextNew = `                                    text = if (isMyCounter) 
                                        "Tienes la ventaja de campeón. Mantén el control de la oleada y usa los arbustos laterales para rotar rápido y emboscar (roam) a otras líneas."
                                    else if (isEnemyCounter)
                                        "Mantén la calma y no cedas oro. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre."
                                    else
                                        "Duelo equilibrado. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada.",`;

if (code.includes(coachTextOld)) {
    code = code.replace(coachTextOld, coachTextNew);
} else {
    console.log("Could not find coachTextOld!");
}

fs.writeFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', code);
