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

                return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        "${champion.name} (${champion.damageType.displayName}) tiene ventaja táctica sobre $target (${targetDamage}). Aprovecha tus ventanas de tradeo y enfriamientos para dominar la línea y forzarlo bajo su torre."
                    } else {
                        "Este campeón tiene una fuerte ventaja sobre $target en la fase de líneas, aprovéchalo para conseguir prioridad de mapa."
                    }
                } else if (isPt) {
                    "${champion.name} tem forte vantagem sobre $target na fase de rotas. Use suas trocas favoráveis para dominar e garantir visão."
                } else {
                    "${champion.name} has a strong tactical advantage over $target in the laning phase. Leverage power spikes to control the lane."
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        "$target (${targetDamage}) representa una amenaza alta para ${champion.name}. Respeta su rango y all-in, farmea pacientemente y espera el apoyo de tu jungla antes de pelear."
                    } else {
                        "Este campeón es vulnerable contra $target. Juega con seguridad, congela la oleada y espera asistencia de tu equipo."
                    }
                } else if (isPt) {
                    "$target é perigoso contra ${champion.name}. Jogue recuado, farme com segurança e espere a ajuda do seu caçador."
                } else {
                    "$target poses a high threat to ${champion.name}. Play cautiously, manage your wave, and wait for team rotations."
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
                LaneRole.TOP -> if (champion.isRanged) "**Fase de Líneas:** En la ${roleStr}, ${champion.name} debe abusar de su rango usando $qSkill para desgastar a los oponentes cuerpo a cuerpo y controlar la oleada." else "**Fase de Líneas:** En la ${roleStr}, ${champion.name} debe jugar alrededor de los enfriamientos de $qSkill, buscando intercambios cortos y asegurando la visión del río."
                LaneRole.JUNGLE -> "**Fase de Limpieza:** En la ${roleStr}, ${champion.name} debe priorizar el farmeo eficiente y buscar emboscadas (ganks) clave apoyándose en $qSkill para asegurar ventajas tempranas."
                LaneRole.MID -> "**Fase de Líneas:** En la ${roleStr}, la prioridad de ${champion.name} es conseguir el empuje (prio) usando $qSkill para poder rotar a los objetivos del río o emboscar junto al junglero."
                LaneRole.ADC -> "**Fase de Líneas:** En la ${roleStr}, ${champion.name} depende de un posicionamiento seguro. Utiliza $qSkill para asegurar súbditos y castigar los errores de posicionamiento del dúo rival."
                LaneRole.SUPPORT -> "**Fase de Líneas:** Como ${roleStr}, ${champion.name} dicta el ritmo de los intercambios. Usa $qSkill para presionar a los rivales, ganar prioridad de nivel 2 y proteger a tu tirador."
            }
        } else if (isPt) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isRanged) "**Fase de Rotas:** Na ${roleStr}, ${champion.name} deve abusar do seu alcance usando $qSkill para desgastar oponentes corpo a corpo e controlar a onda." else "**Fase de Rotas:** Na ${roleStr}, ${champion.name} deve jogar em torno dos tempos de recarga de $qSkill, buscando trocas curtas e garantindo a visão do rio."
                LaneRole.JUNGLE -> "**Fase de Limpeza:** Na ${roleStr}, ${champion.name} deve priorizar o farm eficiente e buscar emboscadas (ganks) apoiando-se em $qSkill para garantir vantagens iniciais."
                LaneRole.MID -> "**Fase de Rotas:** Na ${roleStr}, a prioridade de ${champion.name} é conseguir o empurre (prio) usando $qSkill para poder rotacionar para os objetivos do rio ou ajudar o caçador."
                LaneRole.ADC -> "**Fase de Rotas:** Na ${roleStr}, ${champion.name} depende de um posicionamento seguro. Use $qSkill para garantir tropas e punir erros de posicionamento da dupla rival."
                LaneRole.SUPPORT -> "**Fase de Rotas:** Como ${roleStr}, ${champion.name} dita o ritmo das trocas. Use $qSkill para pressionar os rivais, ganhar prioridade de nível 2 e proteger seu atirador."
            }
        } else {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isRanged) "**Laning Phase:** In the ${roleStr}, ${champion.name} should abuse their range using $qSkill to poke melee opponents and control the wave." else "**Laning Phase:** In the ${roleStr}, ${champion.name} should play around $qSkill cooldowns, looking for short trades and securing river vision."
                LaneRole.JUNGLE -> "**Clearing Phase:** In the ${roleStr}, ${champion.name} should prioritize efficient farming and look for key ganks relying on $qSkill to secure early leads."
                LaneRole.MID -> "**Laning Phase:** In the ${roleStr}, ${champion.name}'s priority is getting push (prio) using $qSkill to roam to river objectives or gank with the jungler."
                LaneRole.ADC -> "**Laning Phase:** In the ${roleStr}, ${champion.name} relies on safe positioning. Use $qSkill to secure minions and punish positioning mistakes from the enemy duo."
                LaneRole.SUPPORT -> "**Laning Phase:** As a ${roleStr}, ${champion.name} dictates the pace of trades. Use $qSkill to pressure opponents, gain level 2 priority, and peel for your marksman."
            }
        }
        
        val mid = if (isEs) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Juego Medio/Tardío:** En peleas grupales, ${champion.name} funciona como la principal línea frontal (frontline). Absorbe daño y busca usar $ultSkill en momentos críticos por objetivos." else "**Juego Medio/Tardío:** Durante el juego medio, aplica presión dividida (split-push) si tienes ventaja, o únete al equipo usando $ultSkill de forma inteligente para eliminar carries rivales."
                LaneRole.JUNGLE -> "**Juego Medio/Tardío:** El control de Dragones y Barón es vital. En peleas de equipo por objetivos, busca posicionarte para iniciar peleas u obtener picks clave desatando $ultSkill."
                LaneRole.MID -> "**Juego Medio/Tardío:** Como fuente principal de daño ${champion.damageType.displayName}, debes posicionarte cautelosamente en las peleas de equipo y esperar el momento óptimo para desatar $ultSkill."
                LaneRole.ADC -> "**Juego Medio/Tardío:** Tu supervivencia es la máxima condición de victoria. Posiciónate en la retaguardia, esquiva el CC y usa $ultSkill cuando sea seguro para derretir la composición enemiga."
                LaneRole.SUPPORT -> "**Juego Medio/Tardío:** Tu control de visión alrededor del Barón y Dragón Ancestral gana partidas. Guarda $ultSkill para desarmar la iniciación enemiga o habilitar a tus carries."
            }
        } else if (isPt) {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Meio/Fim de Jogo:** Nas lutas de equipe, ${champion.name} funciona como a principal linha de frente. Absorva o dano e busque usar $ultSkill em momentos críticos." else "**Meio/Fim de Jogo:** Durante o meio de jogo, aplique pressão dividida (split-push) se tiver vantagem, ou junte-se à equipe usando $ultSkill para eliminar os carries rivais."
                LaneRole.JUNGLE -> "**Meio/Fim de Jogo:** O controle de Dragões e Barão é vital. Nas lutas de equipe, busque se posicionar para iniciar lutas ou obter abates chave com $ultSkill."
                LaneRole.MID -> "**Meio/Fim de Jogo:** Como fonte principal de dano ${champion.damageType.displayName}, você deve se posicionar com cautela nas lutas e esperar o momento ideal para usar $ultSkill."
                LaneRole.ADC -> "**Meio/Fim de Jogo:** Sua sobrevivência é a condição de vitória. Posicione-se na retaguarda, evite CC e use $ultSkill quando for seguro para derreter a equipe inimiga."
                LaneRole.SUPPORT -> "**Meio/Fim de Jogo:** Seu controle de visão ao redor do Barão e Dragão Ancião vence partidas. Guarde $ultSkill para desarmar a iniciação inimiga ou habilitar seus carries."
            }
        } else {
            when (activeRole) {
                LaneRole.TOP -> if (champion.isFrontline) "**Mid/Late Game:** In teamfights, ${champion.name} acts as the primary frontline. Absorb damage and look for crucial $ultSkill opportunities around objectives." else "**Mid/Late Game:** Apply split-push pressure if ahead, or group with the team using $ultSkill smartly to eliminate enemy carries."
                LaneRole.JUNGLE -> "**Mid/Late Game:** Controlling Dragons and Baron is vital. In teamfights, look to position for engages or key picks by unleashing $ultSkill."
                LaneRole.MID -> "**Mid/Late Game:** As the primary ${champion.damageType.displayName} damage source, position carefully in teamfights and wait for the optimal moment to unleash $ultSkill."
                LaneRole.ADC -> "**Mid/Late Game:** Your survival is the ultimate win condition. Position in the backline, dodge CC, and use $ultSkill when safe to melt the enemy composition."
                LaneRole.SUPPORT -> "**Mid/Late Game:** Vision control around Baron and Elder Dragon wins games. Save $ultSkill to disengage enemy dives or empower your carries."
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