import os

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

new_reason_method = """    fun generateMatchupReason(champName: String, target: String, type: String, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val targetChamp = com.example.data.WildRiftRepository.getChampionByName(target)
        val targetRole = targetChamp?.primaryRole ?: LaneRole.MID
        val targetDamage = targetChamp?.damageType?.displayName ?: "explosivo"

        return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName tiene la capacidad de alcanzar y eliminar rápidamente a $target, aprovechando su fragilidad como tirador."
                        LaneRole.SUPPORT -> "$champName puede ignorar el control de masas de $target o aplicar demasiada presión para que pueda proteger a su equipo."
                        LaneRole.JUNGLE -> "$champName tiene ventaja invadiendo la jungla de $target o ganando las escaramuzas por los objetivos neutrales gracias a su daño."
                        LaneRole.TOP -> "$champName puede ganar los intercambios largos contra $target en la línea de Barón, desgastándolo o bloqueando su iniciación."
                        LaneRole.MID -> "$champName supera la limpieza de oleadas de $target y puede rotar primero, o tiene el daño necesario para castigar sus errores de posicionamiento."
                    }
                } else {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName has the ability to quickly gap-close and eliminate $target, taking advantage of their squishiness as a marksman."
                        LaneRole.SUPPORT -> "$champName can ignore $target's crowd control or apply too much pressure for them to protect their team."
                        LaneRole.JUNGLE -> "$champName has an advantage invading $target's jungle or winning skirmishes for neutral objectives thanks to their damage."
                        LaneRole.TOP -> "$champName can win extended trades against $target in the Baron lane, poking them down or blocking their engage."
                        LaneRole.MID -> "$champName out-pushes $target and can roam first, or has the necessary damage to punish their positioning mistakes."
                    }
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    when (targetRole) {
                        LaneRole.ADC -> "$target tiene suficiente rango y movilidad para mantener a $champName a distancia (kiting), desgastándolo progresivamente."
                        LaneRole.SUPPORT -> "Las herramientas de desenganche o protección (peel) de $target neutralizan completamente los intentos de iniciación de $champName."
                        LaneRole.JUNGLE -> "$target tiene mejor control de objetivos y puede hacer emboscadas más efectivas que $champName en el juego temprano."
                        LaneRole.TOP -> "$target tiene estadísticas base superiores o mejor sostenimiento, lo que le permite ganar los combates uno contra uno frente a $champName."
                        LaneRole.MID -> "El burst (daño explosivo) o el control de masas de $target impide que $champName pueda escalar o hacer daño libremente."
                    }
                } else {
                    when (targetRole) {
                        LaneRole.ADC -> "$target has enough range and mobility to kite $champName, wearing them down progressively."
                        LaneRole.SUPPORT -> "$target's disengage or peel tools completely neutralize $champName's engage attempts."
                        LaneRole.JUNGLE -> "$target has better objective control and can execute more effective early ganks than $champName."
                        LaneRole.TOP -> "$target has superior base stats or sustain, allowing them to win 1v1 fights against $champName."
                        LaneRole.MID -> "$target's burst damage or crowd control prevents $champName from scaling or dealing free damage."
                    }
                }
            }
            "Situacional" -> {
                if (isEs) "Compra $target si el equipo enemigo tiene mucho daño explosivo, curaciones excesivas, o si necesitas sobrevivir a la iniciación rival."
                else "Buy $target if the enemy team has high burst damage, excessive healing, or if you need to survive enemy engages."
            }
            else -> { // Sinergia
                if (isEs) {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName y $target combinan bien: $champName puede habilitar las peleas mientras $target inflige daño continuo desde la retaguardia."
                        LaneRole.SUPPORT -> "El control de masas y protección de $target le da a $champName la seguridad necesaria para entrar en combate de forma agresiva."
                        LaneRole.JUNGLE -> "La capacidad de emboscada de $target se complementa perfectamente con el daño y seguimiento que aporta $champName."
                        LaneRole.TOP -> "$target actúa como una excelente línea frontal (frontline) para que $champName no sea el objetivo principal del equipo enemigo."
                        LaneRole.MID -> "La combinación de daño de $champName junto con la utilidad o burst de $target en peleas grupales es devastadora."
                    }
                } else {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName and $target combine well: $champName can enable fights while $target deals consistent damage from the backline."
                        LaneRole.SUPPORT -> "$target's crowd control and peel gives $champName the safety needed to enter combat aggressively."
                        LaneRole.JUNGLE -> "$target's ganking potential synergizes perfectly with the damage and follow-up from $champName."
                        LaneRole.TOP -> "$target acts as an excellent frontline so that $champName is not the primary target of the enemy team."
                        LaneRole.MID -> "The combination of $champName's damage along with $target's utility or burst in teamfights is devastating."
                    }
                }
            }
        }
    }"""

import re
content = re.sub(r'fun generateMatchupReason.*?(?=\n    fun generateTacticalAnalysis)', new_reason_method + '\n', content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("CoachingGenerator updated!")
