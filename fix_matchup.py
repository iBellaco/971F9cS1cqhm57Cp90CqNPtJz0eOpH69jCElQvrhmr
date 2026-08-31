with open('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace the power spikes with 100% Wild Rift specific timings and mechanics
content = content.replace(
'''                                    text = if (isMyCounter) 
                                        "Ventaja en intercambios cortos. Presiona la oleada para conseguir nivel 2 primero y castigar su farmeo." 
                                    else if (isEnemyCounter) 
                                        "Precaución extrema. Cede los primeros súbditos cuerpo a cuerpo y juega cerca de tu torre hasta desbloquear tu kit."
                                    else 
                                        "Línea neutra. Administra el maná/energía y espera el fallo de su habilidad principal antes de intercambiar.",''',
'''                                    text = if (isMyCounter) 
                                        "Ventaja en intercambios tempranos. En Wild Rift la primera oleada otorga nivel 2 inmediato; presiona para denegar el Fruto de Miel (1:15)." 
                                    else if (isEnemyCounter) 
                                        "Precaución en fase temprana. Cede la prioridad de la primera oleada, farmea bajo torre y espera tu pico al nivel 3 (kit completo)."
                                    else 
                                        "Línea neutra de Wild Rift. Controla los arbustos de línea, guarda la Flor del Adivino y castiga tras esquivar su habilidad principal.",'''
)

content = content.replace(
'''                                    text = "Pico de Definitiva: Cuidado con la definitiva de ${enemyOpponent.name}. Si gastan su R sin impacto, dispones de una ventana de 60-80s de agresividad total.",''',
'''                                    text = "Pico de Definitiva (Nivel 5): En Wild Rift los enfriamientos de R son cortos (35-50s). Si ${enemyOpponent.name} falla su definitiva, castiga agresivamente antes del objetivo del minuto 5:00.",'''
)

content = content.replace(
'''                                    text = "Escalado: ${myChampion.name} aporta gran valor en peleas por Dragón/Barón. No te aísles si ${enemyOpponent.name} tiene potencial de split-push.",''',
'''                                    text = "Macro y Objetivos Móviles: Al minuto 5:00 asegura la primera rotación (Dragón elemental o Heraldo). En minuto 7:30 caen las placas de torre y a los 12:00 el Barón/Ancestral.",'''
)

content = content.replace(
'''                                    text = "Ventajas de ${enemyOpponent.name}: ${if (enemyOpponent.advantageAgainst.isNotEmpty()) enemyOpponent.advantageAgainst.take(3).joinToString(", ") else "Intercambio en línea"}",''',
'''                                    text = "Fuerte contra (Matchups favorables): ${if (enemyOpponent.advantageAgainst.isNotEmpty()) enemyOpponent.advantageAgainst.take(3).joinToString(", ") else "Intercambio en línea de Wild Rift"}",'''
)

with open('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
