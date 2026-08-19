with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    c = f.read()

import re

# Remove the broken block
c = re.sub(r'if \(matchupExplanationTarget != null && matchupExplanationType != null\) \{.*', '', c, flags=re.DOTALL)

append = """
    if (matchupExplanationTarget != null && matchupExplanationType != null) {
        val type = matchupExplanationType!!
        val target = matchupExplanationTarget!!
        val champName = champion.name
        
        val titleText = if (com.example.util.LocalLanguage.current == "es" || com.example.util.LocalLanguage.current == "auto") {
            when (type) {
                "Ventaja" -> "Ventaja contra $target"
                "Debilidad" -> "Débil contra $target"
                "Situacional" -> "Objeto Situacional: $target"
                else -> "Sinergia con $target"
            }
        } else {
            when (type) {
                "Ventaja" -> "Strong against $target"
                "Debilidad" -> "Weak against $target"
                "Situacional" -> "Situational Item: $target"
                else -> "Synergy with $target"
            }
        }
        
        val descText = if (com.example.util.LocalLanguage.current == "es" || com.example.util.LocalLanguage.current == "auto") {
            when (type) {
                "Ventaja" -> "$champName tiene una ventaja táctica sobre $target.\\n\\n¿Por qué?\\nSu kit de habilidades le permite esquivar el daño principal o castigar su falta de movilidad."
                "Debilidad" -> "$champName sufre contra $target.\\n\\n¿Por qué?\\nEl kit de $target cuenta con herramientas que contrarrestan directamente tu condición de victoria."
                "Situacional" -> "Este es un objeto situacional para $champName.\\n\\n¿Por qué usarlo?\\nSe recomienda comprar $target únicamente cuando la composición enemiga presenta una amenaza específica que este objeto contrarresta."
                else -> "$champName y $target forman un dúo letal.\\n\\n¿Por qué?\\nSus definitivas y habilidades pasivas se complementan de manera ideal para peleas en equipo."
            }
        } else {
            when (type) {
                "Ventaja" -> "$champName has a tactical advantage over $target.\\n\\nWhy?\\nTheir ability kit allows them to dodge main damage or severely punish their lack of mobility."
                "Debilidad" -> "$champName struggles against $target.\\n\\nWhy?\\n$target's kit has tools that directly counter your win condition."
                "Situacional" -> "This is a situational item for $champName.\\n\\nWhen to use it?\\nYou should only buy $target when the enemy team composition presents a specific threat."
                else -> "$champName and $target form a lethal duo.\\n\\nWhy?\\nTheir ultimates and passive abilities complement each other perfectly for team fights."
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
                    Text("Entendido", color = HextechCyan)
                }
            },
            containerColor = HextechSurface,
            titleContentColor = HextechGold,
            textContentColor = TextPrimary
        )
    }
}
"""

c = c.strip()
if c.endswith('}'):
    c = c[:-1]
c = c + "\n" + append

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(c)
