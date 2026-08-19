import re

file_path = "app/src/main/java/com/example/util/CoachingGenerator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will rewrite the whole file to make it cleaner. 
# It's only 109 lines.

new_content = """package com.example.util

import com.example.model.Champion
import com.example.model.LaneRole

object CoachingGenerator {
    
    fun generateMatchupReason(champion: Champion, activeRole: LaneRole, target: String, type: String, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val isPt = lang == "pt"
        val champName = champion.name
        val sourceRole = activeRole
        
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
                } else if (isPt) {
                    when (sourceRole) {
                        LaneRole.TOP -> "$champName tem vantagem contra $target através de trocas contínuas, explorando a fraqueza de $target contra pressão solo constante."
                        LaneRole.JUNGLE -> "O dano e a mobilidade de $champName permitem invadir ou gankar $target facilmente, arruinando sua rota de selva ou escalonamento."
                        LaneRole.MID -> "A capacidade de empurrar e rotacionar de $champName sufoca $target, impedindo-o de farmar ou rotacionar a tempo para ajudar sua equipe."
                        LaneRole.ADC -> "$champName tem melhor escalonamento ou alcance que $target, permitindo dominar as trocas diretas na rota do Dragão."
                        LaneRole.SUPPORT -> "As ferramentas de controle ou proteção de $champName neutralizam perfeitamente o estilo de jogo de $target."
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
                } else if (isPt) {
                    when (sourceRole) {
                        LaneRole.TOP -> "$target tem o alcance ou dano explosivo necessário para punir as iniciações de $champName, forçando-o a jogar debaixo da torre."
                        LaneRole.JUNGLE -> "$target tem melhor controle de mapa e pode fazer counter-gank em $champName ou vencer os 1v1 no rio."
                        LaneRole.MID -> "O alcance ou a mobilidade de $target faz com que $champName tenha problemas para aplicar seu dano ou farmar sem ser punido."
                        LaneRole.ADC -> "$target tem um jogo inicial melhor ou maior alcance, dificultando muito a fase de rotas de $champName."
                        LaneRole.SUPPORT -> "$target pode ignorar a iniciação de $champName ou tem poke suficiente para desgastá-lo antes de lutar."
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
                else if (isPt) "Compre $target se a equipe inimiga tiver muito dano $targetDamage, curas excessivas, ou se você precisar sobreviver à iniciação rival."
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
                } else if (isPt) {
                    when (targetRole) {
                        LaneRole.ADC -> "$champName complementa perfeitamente o estilo de $target, dando-lhe espaço para causar dano."
                        LaneRole.SUPPORT -> "$target fornece o controle de grupo e a sobrevivência que $champName precisa para brilhar nas lutas."
                        LaneRole.JUNGLE -> "A iniciação de $target facilita muito para $champName garantir eliminações ou aplicar todo o seu dano."
                        LaneRole.TOP -> "$target atua como uma linha de frente sólida, atraindo a atenção inimiga enquanto $champName faz o seu trabalho."
                        LaneRole.MID -> "O dano em área ou controle de grupo de $target se encaixa maravilhosamente com o kit de habilidades de $champName."
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
    }

    fun generateTacticalAnalysis(champion: Champion, activeRole: LaneRole, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val isPt = lang == "pt"
        val roleStr = activeRole.displayName
        
        val base = if (isEs) {
            "**Fase de Líneas:** En ${roleStr}, ${champion.name} debe centrarse en asegurar súbditos y buscar intercambios cortos cuando sus habilidades principales estén disponibles. "
        } else if (isPt) {
            "**Fase de Rotas:** No ${roleStr}, ${champion.name} deve se concentrar em garantir tropas e buscar trocas curtas quando suas habilidades principais estiverem disponíveis. "
        } else {
            "**Laning Phase:** In ${roleStr}, ${champion.name} should focus on securing minions and looking for short trades when core abilities are off cooldown. "
        }
        
        val mid = if (isEs) {
            "**Juego Medio/Tardío:** Su principal condición de victoria es agruparse en objetivos (Dragón/Barón). Como fuente de daño ${champion.damageType.displayName}, es crucial el posicionamiento para evitar el CC enemigo y aplicar daño constante."
        } else if (isPt) {
            "**Meio/Fim de Jogo:** Sua principal condição de vitória é agrupar-se em objetivos (Dragão/Barão). Como uma fonte de dano ${champion.damageType.displayName}, o posicionamento é crucial para evitar o CC inimigo e causar dano consistente."
        } else {
            "**Mid/Late Game:** Their main win condition is grouping for objectives (Dragon/Baron). As a ${champion.damageType.displayName} damage source, positioning is crucial to avoid enemy CC and deal consistent damage."
        }
        
        return champion.summary + "\n\n" + base + "\n\n" + mid
    }

    fun generateTacticalAdvice(champion: Champion, activeRole: LaneRole, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val isPt = lang == "pt"
        return when (activeRole) {
            LaneRole.ADC -> if (isEs) "Concéntrate en farmear seguro y acumular oro para tus objetos clave. Posiciónate siempre detrás de tu soporte/tanque en peleas grupales." else if (isPt) "Concentre-se em farmar com segurança e acumular ouro para seus itens essenciais. Posicione-se sempre atrás do seu suporte/tanque nas lutas de equipe." else "Focus on safe farming and stacking gold for key items. Always position behind your support/tank in teamfights."
            LaneRole.SUPPORT -> if (isEs) "Controla la visión (wards) en objetivos y protege a tus carries. Guarda tu CC para interrumpir al asesino enemigo." else if (isPt) "Controle a visão (sentinelas) ao redor de objetivos e proteja seus carries. Guarde seu CC para interromper o assassino inimigo." else "Control vision (wards) around objectives and peel for your carries. Save your CC to interrupt enemy assassins."
            LaneRole.MID -> if (isEs) "Usa tu presión de línea para rotar (roam) con el jungla. En peleas grupales, prioriza flanquear o aplicar daño explosivo al carry enemigo." else if (isPt) "Use sua pressão de rota para rotacionar (roam) com o caçador. Nas lutas de equipe, priorize flanquear ou explodir o carry inimigo." else "Use lane pressure to roam with the jungler. In teamfights, prioritize flanking or bursting the enemy carry."
            LaneRole.JUNGLE -> if (isEs) "Garantiza el control de los Escurridizos (Scuttles) para visión y rastrea la ruta del jungla rival. Asegura los Dragones y Heraldos." else if (isPt) "Garanta o controle dos Aronguejos para visão e rastreie a rota do caçador inimigo. Garanta os Dragões e Arautos." else "Secure Scuttles for vision and track the enemy jungler's path. Secure Dragons and Heralds."
            LaneRole.TOP -> if (isEs) "Mantén la presión dividida (split-push) si tienes ventaja, o agruparte si tu equipo necesita iniciación o tanqueo para los objetivos." else if (isPt) "Mantenha a pressão dividida (split-push) se estiver em vantagem, ou agrupe-se se sua equipe precisar de iniciação ou tanque para os objetivos." else "Keep split-push pressure if ahead, or group if your team needs engage or frontline for objectives."
        }
    }
}
"""

with open(file_path, "w", encoding="utf-8") as f:
    f.write(new_content)

print("CoachingGenerator translated completely.")
