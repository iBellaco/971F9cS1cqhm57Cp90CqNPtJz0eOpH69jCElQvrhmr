import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Current tip logic:
'''
                        text = when (rune.category.lowercase()) {
                            "brujería" -> "Excelente para magos, soportes de utilidad y campeones que requieren aceleración de habilidades y maná continuo en fase de líneas."
                            "runa clave" -> "Pilar fundamental de tu estilo de juego. Elige según busques intercambios cortos (Electrocutar/Primer Golpe) o peleas largas (Conquistador/Cadencia Letal)."
                            "dominación" -> "Ideal para amplificar daño explosivo, penetración rápida y acumulación de daño por bajas o visión."
                            "precisión" -> "Máxima eficiencia en tiradores (ADC) y duelistas para potenciar daño sostenido, velocidad de ataque y remate de objetivos."
                            "valor" -> "Imprescindible para tanques y luchadores contra líneas difíciles para absorber daño y escalar vida máxima."
                            else -> "Aporta versatilidad, aceleración de hechizos de invocador y movilidad táctica por el mapa."
                        },
'''

new_tip_logic = '''text = when {
                            rune.category.lowercase().contains("clave") -> {
                                when (rune.name.lowercase()) {
                                    "electrocutar" -> "💡 Ideal para combos cortos de asesinos o magos que buscan estallar a un rival rápido."
                                    "cosecha oscura" -> "💡 Perfecto para campeones que escalan y aseguran asesinatos en peleas largas (ej. Katarina, Khazix)."
                                    "fortalecimiento" -> "💡 Excelente para tiradores o luchadores que dependen de ataques básicos rápidos."
                                    "compás letal" -> "💡 Fundamental en hypercarries como Jinx o Vayne para dominar las peleas largas."
                                    "pies veloces" -> "💡 Útil para sobrevivir líneas difíciles gracias a su curación y movilidad al kitear."
                                    "conquistador" -> "💡 La mejor opción para luchadores y duelistas que buscan intercambios prolongados (ej. Darius, Riven)."
                                    "garras del inmortal" -> "💡 Indispensable en tanques y colosos para tener sustain y escalar vida máxima."
                                    "guardián" -> "💡 Selecciona esta runa en soportes protectores (ej. Braum, Lulu) para mitigar burst enemigo."
                                    "aery" -> "💡 Muy versátil para soportes encantadores o magos de pokeo constante (ej. Karma, Orianna)."
                                    "cometa arcano" -> "💡 Ideal para magos de artillería que pokean a distancia (ej. Ziggs, Lux)."
                                    "irrupción de fase" -> "💡 Perfecta para magos de combo que necesitan reposicionarse rápido (ej. Orianna, Vladimir)."
                                    "primer golpe" -> "💡 Útil en asesinos o magos de ráfaga para escalar en oro rápidamente y explotar objetivos."
                                    "soberano gélido" -> "💡 Excelente para soportes de iniciación (ej. Leona, Nautilus) para potenciar su CC."
                                    else -> "Elige esta runa clave basándote en tu condición de victoria en la fase de líneas."
                                }
                            }
                            rune.category.lowercase() == "brujería" -> "Excelente para magos, soportes de utilidad y campeones que requieren aceleración de habilidades y maná continuo en fase de líneas."
                            rune.category.lowercase() == "dominación" -> "Ideal para amplificar daño explosivo, penetración rápida y acumulación de daño por bajas o visión."
                            rune.category.lowercase() == "precisión" -> "Máxima eficiencia en tiradores (ADC) y duelistas para potenciar daño sostenido, velocidad de ataque y remate de objetivos."
                            rune.category.lowercase() == "valor" -> "Imprescindible para tanques y luchadores contra líneas difíciles para absorber daño y escalar vida máxima."
                            else -> "Aporta versatilidad, aceleración de hechizos de invocador y movilidad táctica por el mapa."
                        },'''

text = re.sub(r'text = when \(rune\.category\.lowercase\(\)\) \{.*?\},', new_tip_logic, text, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
