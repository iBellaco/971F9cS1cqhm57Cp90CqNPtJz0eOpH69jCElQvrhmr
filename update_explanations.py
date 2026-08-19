with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()

# Update the AlertDialog logic to make it more descriptive and support English via tr() or dynamic text
replacement = """
    if (matchupExplanationTarget != null && matchupExplanationType != null) {
        val type = matchupExplanationType!!
        val target = matchupExplanationTarget!!
        val champName = champion.name
        
        val (titleText, descText) = if (com.example.util.LocalLanguage.current == "es" || com.example.util.LocalLanguage.current == "auto") {
            when (type) {
                "Ventaja" -> Pair("Ventaja contra $target", "$champName tiene una ventaja táctica sobre $target.\\n\\n¿Por qué?\\nSu kit de habilidades (movilidad, rango o control de masas) le permite esquivar el daño principal de $target, cancelar sus iniciaciones, o castigar severamente su falta de movilidad durante la fase de líneas y escaramuzas tempranas.")
                "Debilidad" -> Pair("Débil contra $target", "$champName sufre contra $target.\\n\\n¿Por qué?\\nEl kit de $target cuenta con herramientas (como burst damage rápido, silencios o escudos) que contrarrestan directamente tu condición de victoria, forzándote a jugar defensivamente bajo torre o depender de ganks de tu jungla.")
                "Situacional" -> Pair("Objeto Situacional: $target", "Este es un objeto situacional para $champName.\\n\\n¿Por qué usarlo?\\nSe recomienda comprar $target únicamente cuando la composición enemiga presenta una amenaza específica que este objeto contrarresta (ej. mucho daño mágico, tanques con mucha vida, o curaciones excesivas).")
                else -> Pair("Sinergia con $target", "$champName y $target forman un dúo letal.\\n\\n¿Por qué?\\nSus definitivas y habilidades pasivas se complementan de manera ideal. Por ejemplo, si uno aporta el Control de Masas (Aturdimientos/Derribos) en área, el otro puede aplicar su daño explosivo (Wombo Combo) sin que el enemigo pueda escapar en peleas por el Dragón o Barón.")
            }
        } else {
            when (type) {
                "Ventaja" -> Pair("Strong against $target", "$champName has a tactical advantage over $target.\\n\\nWhy?\\nTheir ability kit (mobility, range, or crowd control) allows them to dodge $target's main damage, cancel their engages, or severely punish their lack of mobility during the laning phase and early skirmishes.")
                "Debilidad" -> Pair("Weak against $target", "$champName struggles against $target.\\n\\nWhy?\\n$target's kit has tools (like fast burst damage, silences, or shields) that directly counter your win condition, forcing you to play defensively under the turret or rely heavily on jungle ganks.")
                "Situacional" -> Pair("Situational Item: $target", "This is a situational item for $champName.\\n\\nWhen to use it?\\nYou should only buy $target when the enemy team composition presents a specific threat that this item counters (e.g., heavy magic damage, high-health tanks, or excessive healing).")
                else -> Pair("Synergy with $target", "$champName and $target form a lethal duo.\\n\\nWhy?\\nTheir ultimates and passive abilities complement each other perfectly. For example, if one provides AoE Crowd Control (Stuns/Knock-ups), the other can apply explosive burst damage (Wombo Combo) without the enemy being able to escape during Dragon or Baron fights.")
            }
        }

        AlertDialog(
            onDismissRequest = { matchupExplanationTarget = null },
            title = {
                Text(
                    text = titleText,
                    color = HextechGold,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(descText, color = TextPrimary)
            },
            confirmButton = {
                TextButton(onClick = { matchupExplanationTarget = null }) {
                    Text(tr("Entendido"), color = HextechCyan)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold,
            textContentColor = TextPrimary
        )
    }
"""

import re
# We need to replace the entire 'if (matchupExplanationTarget != null && matchupExplanationType != null) { ... }' block
content = re.sub(r'if \(matchupExplanationTarget != null && matchupExplanationType != null\) \{.*?\}(?=\s*\n\})', replacement.strip(), content, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)
