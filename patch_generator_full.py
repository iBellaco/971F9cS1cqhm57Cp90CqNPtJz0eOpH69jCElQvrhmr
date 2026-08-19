import os

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_reason_method = """    fun generateMatchupReason(champion: Champion, target: String, type: String, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val champName = champion.name
        val sourceRole = champion.primaryRole
        val targetChamp = com.example.data.WildRiftRepository.getChampionByName(target)
        val targetRole = targetChamp?.primaryRole ?: LaneRole.MID
        val targetDamage = targetChamp?.damageType?.displayName ?: "mixto"

        return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    when (sourceRole) {
                        LaneRole.TOP -> "$champName saca ventaja contra $target mediante intercambios sostenidos, aprovechando que $target sufre contra la presión constante en solitario."
                        LaneRole.JUNGLE -> "El daño y movilidad de $champName le permite invadir o emboscar a $target fácilmente, arruinando su ruta de jungla o escalado."
                        LaneRole.MID -> "La capacidad de empuje y rotación de $champName asfixia a $target, impidiéndole farmear o rotar a tiempo para ayudar a su equipo."
                        LaneRole.ADC -> "$champName tiene mejor escalado o rango que $target, lo que le permite dominar los enfrentamientos directos en la línea del Dragón."
                        LaneRole.SUPPORT -> "Las herramientas de control o protección de $champName neutralizan perfectamente el estilo de juego de $target."
                    }
                } else {
                    when (sourceRole) {
                        LaneRole.TOP -> "$champName gains an advantage against $target through sustained trades, exploiting $target's weakness to constant solo pressure."
                        LaneRole.JUNGLE -> "$champName's damage and mobility allow them to easily invade or gank $target, ruining their jungle pathing or scaling."
                        LaneRole.MID -> "$champName's push and roam potential suffocates $target, preventing them from farming or roaming in time to help their team."
                        LaneRole.ADC -> "$champName has better scaling or range than $target, allowing them to dominate direct trades in the Dragon lane."
                        LaneRole.SUPPORT -> "$champName's control or peel tools perfectly neutralize $target's playstyle."
                    }
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    when (sourceRole) {
                        LaneRole.TOP -> "$target tiene el rango o el daño explosivo necesario para castigar los acercamientos de $champName, forzándolo a jugar bajo torre."
                        LaneRole.JUNGLE -> "$target controla mejor el mapa y puede hacer counter-gank a $champName o ganar los 1v1 en el río."
                        LaneRole.MID -> "El rango o la movilidad de $target hace que $champName tenga problemas para aplicar su daño o farmear sin recibir castigo."
                        LaneRole.ADC -> "$target tiene un mejor juego temprano o mayor rango, haciendo que la fase de líneas de $champName sea muy difícil."
                        LaneRole.SUPPORT -> "$target puede ignorar la iniciación de $champName o tiene suficiente asedio para desgastarlo antes de pelear."
                    }
                } else {
                    when (sourceRole) {
                        LaneRole.TOP -> "$target has the range or burst damage needed to punish $champName's engages, forcing them to play under turret."
                        LaneRole.JUNGLE -> "$target has better map control and can counter-gank $champName or win 1v1s in the river."
                        LaneRole.MID -> "$target's range or mobility makes it hard for $champName to deal damage or farm without being punished."
                        LaneRole.ADC -> "$target has a better early game or longer range, making $champName's laning phase very difficult."
                        LaneRole.SUPPORT -> "$target can ignore $champName's engage or has enough poke to wear them down before fights."
                    }
                }
            }
            "Situacional" -> {
                if (isEs) "Compra $target si el equipo enemigo tiene mucho daño $targetDamage, curaciones excesivas, o si necesitas sobrevivir a la iniciación rival."
                else "Buy $target if the enemy team has high $targetDamage damage, excessive healing, or if you need to survive enemy engages."
            }
            else -> { // Sinergia
                if (isEs) {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName complementa perfectamente el estilo de $target, dándole el espacio para infligir daño."
                        LaneRole.SUPPORT -> "$target provee el control de masas y la supervivencia que $champName necesita para brillar en las peleas."
                        LaneRole.JUNGLE -> "La iniciación de $target facilita enormemente que $champName pueda asegurar eliminaciones o aplicar todo su daño."
                        LaneRole.TOP -> "$target actúa como una sólida línea frontal (frontline), atrayendo la atención enemiga mientras $champName hace su trabajo."
                        LaneRole.MID -> "El daño de área o el control de masas de $target encaja de maravilla con el kit de habilidades de $champName."
                    }
                } else {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName perfectly complements $target's style, giving them the space to deal damage."
                        LaneRole.SUPPORT -> "$target provides the crowd control and survivability that $champName needs to shine in fights."
                        LaneRole.JUNGLE -> "$target's engage makes it incredibly easy for $champName to secure takedowns or apply all their damage."
                        LaneRole.TOP -> "$target acts as a solid frontline, drawing enemy attention while $champName does their job."
                        LaneRole.MID -> "$target's area damage or crowd control fits wonderfully with $champName's ability kit."
                    }
                }
            }
        }
    }"""

import re
content = re.sub(r'fun generateMatchupReason.*?(?=\n    fun generateTacticalAnalysis)', new_reason_method + '\n', content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("CoachingGenerator updated with sourceRole and targetRole logic!")
