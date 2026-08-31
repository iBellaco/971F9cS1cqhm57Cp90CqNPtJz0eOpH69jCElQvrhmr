package com.example.util

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

                val mySkill = champion.skills.find { it.slot == "1" }?.name ?: champion.skills.firstOrNull()?.name ?: "habilidades"
        val isMeRanged = champion.isRanged
        val isTargetRanged = targetChamp?.isRanged ?: false
        
        return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        if (isMeRanged && !isTargetRanged) {
                            "${champion.name} puede abusar de su rango contra $target. Castígalo con $mySkill cada vez que intente farmear, y mantén la distancia para ganar la línea sin recibir daño ${targetDamage.lowercase()}."
                        } else if (!isMeRanged && isTargetRanged) {
                            "${champion.name} tiene un all-in superior al de $target. Soporta el desgaste inicial y usa $mySkill para acortar distancias; una vez encima, no podrá sobrevivir a tu daño."
                        } else {
                            "${champion.name} domina este enfrentamiento. Aprovecha el enfriamiento de las habilidades de $target para intercambiar daño con $mySkill, forzándolo a jugar bajo su torre."
                        }
                    } else {
                        "${champion.name} tiene un kit superior frente a $target. Castiga sus errores de posicionamiento para conseguir prioridad de mapa."
                    }
                } else if (isPt) {
                    "${champion.name} tem forte vantagem sobre $target. Puna-o com $mySkill sempre que tentar farmar."
                } else {
                    "${champion.name} dominates $target. Abuse $mySkill cooldowns and force them under tower."
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        if (!isMeRanged && isTargetRanged) {
                            "$target te castigará constantemente por tu falta de rango. Sacrifica algunos súbditos si es necesario, usa $mySkill solo para asegurar oro seguro y espera la rotación de tu jungla o el Fruto de Miel (1:15 min)."
                        } else if (targetDamage.equals("Mágico", true)) {
                            "El daño mágico explosivo de $target es letal para ${champion.name}. Considera botas de resistencia, evita los intercambios largos y guarda $mySkill para protegerte o escapar."
                        } else {
                            "$target supera a ${champion.name} en 1vs1. Respeta su daño ${targetDamage.lowercase()}, no fuerces peleas innecesarias y maximiza tu farmeo bajo torre."
                        }
                    } else {
                        "${champion.name} sufre mucho contra el kit de $target. Juega de forma conservadora y pide rotaciones tempranas."
                    }
                } else if (isPt) {
                    "$target é extremamente letal contra ${champion.name}. Jogue recuado e reserve $mySkill para defesa."
                } else {
                    "$target counters ${champion.name}. Play defensively, freeze the wave, and wait for ganks."
                }
            }
            "Situacional" -> {
                val advice = com.example.data.SituationalItemAdvisor.getAdvice(target)
                val localizedName = trStr(advice.name, lang)
                val localizedCat = trStr(advice.categoryName, lang)
                val localizedPurpose = trStr(advice.purpose, lang)
                val localizedKeyEffect = trStr(advice.keyEffect, lang)
                val localizedTip = trStr(advice.recommendationTip, lang)
                if (isEs) {
                    "️ **$localizedName ($localizedCat)**\n\n$localizedPurpose\n\n• **Efectivo contra:** ${advice.bestAgainst.joinToString(", ")}\n• **Efecto clave:** $localizedKeyEffect\n\n **Consejo:** $localizedTip"
                } else if (isPt) {
                    "️ **$localizedName ($localizedCat)**\n\n$localizedPurpose\n\n• **Eficaz contra:** ${advice.bestAgainst.joinToString(", ")}\n• **Efeito chave:** $localizedKeyEffect\n\n **Dica:** $localizedTip"
                } else {
                    "️ **$localizedName ($localizedCat)**\n\n$localizedPurpose\n\n• **Effective against:** ${advice.bestAgainst.joinToString(", ")}\n• **Key effect:** $localizedKeyEffect\n\n **Coach Tip:** $localizedTip"
                }
            }
            else -> { // Sinergia
                if (isEs) {
                    "Excelente sinergia con $target. La combinación de control de masas, daño y protección de ambos campeones garantiza una superioridad aplastante en peleas de equipo y toma de objetivos."
                } else if (isPt) {
                    "Excelente sinergia com $target. A combinação de habilidades garante grande vantagem nas lutas de equipe e objetivos neutros."
                } else {
                    "High synergy with $target. The combined crowd control, burst, and utility create immense teamfight superiority."
                }
            }
        }
    }

    fun generateTacticalAnalysis(champion: Champion, activeRole: LaneRole, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val isPt = lang == "pt"
        val roleStr = activeRole.displayName
        
        val qSkill = champion.skills.find { it.slot == "1" }?.name ?: if (isEs) "sus habilidades" else if (isPt) "suas habilidades" else "their abilities"
        val ultSkill = champion.skills.find { it.slot == "4" }?.name ?: if (isEs) "su definitiva" else if (isPt) "sua ultimate" else "their ultimate"
        
        val base = if (isEs) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isRanged) "**Fase Temprana (Línea de Wild Rift):** En la ${roleStr}, ${champion.name} debe abusar de su rango usando $qSkill para desgastar a los oponentes cuerpo a cuerpo y controlar la oleada." else "**Fase Temprana (Línea de Wild Rift):** En la ${roleStr}, ${champion.name} debe jugar alrededor de los enfriamientos de $qSkill, buscando intercambios cortos y controlando el Escurridizo del río (1:15 min) y la Flor del Adivino."
                LaneRole.JUNGLE -> "**Ruta de Jungla y Control de Río:** En la ${roleStr}, ${champion.name} debe priorizar el farmeo eficiente y buscar emboscadas (ganks) clave apoyándose en $qSkill para asegurar ventajas tempranas."
                LaneRole.MID -> "**Fase Temprana (Línea de Wild Rift):** En la ${roleStr}, la prioridad de ${champion.name} es conseguir el empuje (prio) usando $qSkill para poder rotar a los objetivos del río o emboscar junto al junglero."
                LaneRole.ADC -> "**Fase Temprana (Línea de Wild Rift):** En la ${roleStr}, ${champion.name} depende de un posicionamiento seguro. Utiliza $qSkill para asegurar súbditos y castigar los errores de posicionamiento del dúo rival."
                LaneRole.SUPPORT -> "**Fase Temprana (Línea de Wild Rift):** Como ${roleStr}, ${champion.name} dicta el ritmo de los intercambios. Usa $qSkill para presionar a los rivales, ganar prioridad de nivel 2 y proteger a tu tirador."
            }
        } else if (isPt) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isRanged) "**Fase Inicial (Rotas de Wild Rift):** Na ${roleStr}, ${champion.name} deve abusar do seu alcance usando $qSkill para desgastar oponentes corpo a corpo e controlar a onda." else "**Fase Inicial (Rotas de Wild Rift):** Na ${roleStr}, ${champion.name} deve jogar em torno dos tempos de recarga de $qSkill, buscando trocas curtas e garantindo a visão do rio."
                LaneRole.JUNGLE -> "**Fase de Limpeza:** Na ${roleStr}, ${champion.name} deve priorizar o farm eficiente e buscar emboscadas (ganks) apoiando-se em $qSkill para garantir vantagens iniciais."
                LaneRole.MID -> "**Fase Inicial (Rotas de Wild Rift):** Na ${roleStr}, a prioridade de ${champion.name} é conseguir o empurre (prio) usando $qSkill para poder rotacionar para os objetivos do rio ou ajudar o caçador."
                LaneRole.ADC -> "**Fase Inicial (Rotas de Wild Rift):** Na ${roleStr}, ${champion.name} depende de um posicionamento seguro. Use $qSkill para garantir tropas e punir erros de posicionamento da dupla rival."
                LaneRole.SUPPORT -> "**Fase Inicial (Rotas de Wild Rift):** Como ${roleStr}, ${champion.name} dita o ritmo das trocas. Use $qSkill para pressionar os rivais, ganhar prioridade de nível 2 e proteger seu atirador."
            }
        } else {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isRanged) "**Wild Rift Early Laning Phase:** In the ${roleStr}, ${champion.name} should abuse their range using $qSkill to poke melee opponents and control the wave." else "**Wild Rift Early Laning Phase:** In the ${roleStr}, ${champion.name} should play around $qSkill cooldowns, looking for short trades and securing river vision."
                LaneRole.JUNGLE -> "**Jungle Path & River Control:** In the ${roleStr}, ${champion.name} should prioritize efficient farming and look for key ganks relying on $qSkill to secure early leads."
                LaneRole.MID -> "**Wild Rift Early Laning Phase:** In the ${roleStr}, ${champion.name}'s priority is getting push (prio) using $qSkill to roam to river objectives or gank with the jungler."
                LaneRole.ADC -> "**Wild Rift Early Laning Phase:** In the ${roleStr}, ${champion.name} relies on safe positioning. Use $qSkill to secure minions and punish positioning mistakes from the enemy duo."
                LaneRole.SUPPORT -> "**Wild Rift Early Laning Phase:** As a ${roleStr}, ${champion.name} dictates the pace of trades. Use $qSkill to pressure opponents, gain level 2 priority, and peel for your marksman."
            }
        }
        
        val mid = if (isEs) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Juego Medio/Tardío (Macro Wild Rift):** En peleas grupales, ${champion.name} funciona como la principal línea frontal (frontline). Absorbe daño y busca usar $ultSkill en momentos críticos por objetivos." else "**Juego Medio/Tardío (Macro Wild Rift):** Empuja tu línea para aplicar presión dividida, pero recuerda que el mapa es corto: agrupa rápidamente a pie para los objetivos (Heraldo/Barón) y flanquea con tu $ultSkill a los objetivos vulnerables."
                LaneRole.JUNGLE -> "**Juego Medio/Tardío (Macro Wild Rift):** El mapa es pequeño y las rotaciones son rápidas. Prioriza asegurar el Dragón o Heraldo temprano, y usa tu $ultSkill para conseguir emboscadas clave que permitan a tu equipo tirar torres e invadir la jungla."
                LaneRole.MID -> "**Juego Medio/Tardío (Macro Wild Rift):** En este juego de ritmo acelerado, una emboscada tardía es fatal. Muévete siempre con tu equipo por el río o la jungla y usa tu $ultSkill de forma explosiva en espacios cerrados (pasillos de jungla) para borrar a los rivales."
                LaneRole.ADC -> "**Juego Medio/Tardío (Macro Wild Rift):** Agrupa con tu soporte lo antes posible. Los asedios a torres de inhibidor en Wild Rift son rápidos. Posiciónate seguro detrás de tu línea frontal y castiga con $ultSkill sin arriesgar tu vida."
                LaneRole.SUPPORT -> "**Juego Medio/Tardío (Macro Wild Rift):** Deniega la visión enemiga con Lente Revelador en el río antes de los objetivos neutrales (Dragones, Barón). Usa el corto enfriamiento de las botas encantadas y tu $ultSkill para salvar a tu ADC o enganchar al acarreador rival."
            }
        } else if (isPt) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Meio/Fim de Jogo (Macro Wild Rift):** Nas lutas de equipe, ${champion.name} funciona como a principal linha de frente. Absorva o dano e busque usar $ultSkill em momentos críticos." else "**Meio/Fim de Jogo (Macro Wild Rift):** Empurre sua rota para pressão dividida, mas lembre-se que o mapa é curto. Agrupe rapidamente para os objetivos e flanqueie com $ultSkill."
                LaneRole.JUNGLE -> "**Meio/Fim de Jogo (Macro Wild Rift):** O mapa é pequeno e as rotações são rápidas. Priorize garantir o Dragão ou Arauto cedo, e use seu $ultSkill para conseguir emboscadas decisivas."
                LaneRole.MID -> "**Meio/Fim de Jogo (Macro Wild Rift):** Neste jogo de ritmo acelerado, uma emboscada no late game é fatal. Mova-se com sua equipe e use $ultSkill de forma explosiva em espaços fechados da selva."
                LaneRole.ADC -> "**Meio/Fim de Jogo (Macro Wild Rift):** Agrupe-se com seu suporte. Os cercos às torres de inibidor em Wild Rift são muito rápidos; posicione-se atrás da linha de frente e cause dano com $ultSkill."
                LaneRole.SUPPORT -> "**Meio/Fim de Jogo (Macro Wild Rift):** Negue a visão inimiga com a Lente Detectora no rio. Use o baixo tempo de recarga das botas encantadas e seu $ultSkill para virar lutas a seu favor."
            }
        } else {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Mid/Late Game (Wild Rift Macro):** In teamfights, ${champion.name} acts as the primary frontline. Absorb damage and look for crucial $ultSkill opportunities around objectives." else "**Mid/Late Game (Wild Rift Macro):** You can split-push, but remember the map is small so enemies will collapse fast. Shove the wave and quickly group on foot for Baron or Elder, flanking with $ultSkill."
                LaneRole.JUNGLE -> "**Mid/Late Game (Wild Rift Macro):** The map is small and rotations are extremely fast. Secure early objectives (Dragon/Herald) and use $ultSkill for key ganks to snowball and break tier-1 turrets."
                LaneRole.MID -> "**Mid/Late Game (Wild Rift Macro):** In WR's fast-paced matches, getting caught out late is game-over. Move with your team, flank around the tight jungle paths, and unleash $ultSkill to burst isolated targets."
                LaneRole.ADC -> "**Mid/Late Game (Wild Rift Macro):** Group with your support immediately after laning phase. Inhibitor turret sieges are very fast; stay safely behind your frontline and spam your damage and $ultSkill."
                LaneRole.SUPPORT -> "**Mid/Late Game (Wild Rift Macro):** Clear river vision with Sweeping Lens before Dragons/Baron spawn. Use your active boot enchantments and $ultSkill to instantly turn fights around in the smaller skirmish spaces."
            }
        }
        
        return base + "\n\n" + mid
    }

    fun generateTacticalAdvice(champion: Champion, activeRole: LaneRole, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val isPt = lang == "pt"
        
        val specificAdvice = if (champion.tacticalAdvice.isNotBlank()) champion.tacticalAdvice else ""
        
        val roleAdvice = when (activeRole) {
            LaneRole.ADC -> if (isEs) "Concéntrate en tu posicionamiento y acumular oro." else if (isPt) "Concentre-se em seu posicionamento e acumular ouro." else "Focus on positioning and stacking gold."
            LaneRole.SUPPORT -> if (isEs) "Controla la visión (wards) y protege a tu equipo." else if (isPt) "Controle a visão (sentinelas) e proteja sua equipe." else "Control vision (wards) and peel for your team."
            LaneRole.MID -> if (isEs) "Usa tu presión para rotar a los objetivos." else if (isPt) "Use sua pressão para rotacionar para os objetivos." else "Use your pressure to roam to objectives."
            LaneRole.JUNGLE -> if (isEs) "Garantiza el control del mapa y los Dragones/Heraldos." else if (isPt) "Garanta o controle do mapa e dos Dragões/Arautos." else "Secure map control and Dragons/Heralds."
            LaneRole.TOP -> if (isEs) "Mantén la presión dividida o sé la iniciación del equipo." else if (isPt) "Mantenha a pressão dividida ou seja a iniciação da equipe." else "Keep split-push pressure or be the team's engage."
        }
        
        return if (specificAdvice.isNotBlank()) "$specificAdvice $roleAdvice" else roleAdvice
    }
}