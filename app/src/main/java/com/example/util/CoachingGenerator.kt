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
                if (isEs) "Este campeón tiene una fuerte ventaja sobre $target en la fase de líneas, aprovéchalo."
                else if (isPt) "Este campeão tem uma forte vantagem sobre $target na fase de rotas, aproveite."
                else "This champion has a strong advantage over $target in the laning phase, use it."
            }
            "Debilidad" -> {
                if (isEs) "Este campeón es débil contra $target. Juega con seguridad y espera ayuda de tu equipo."
                else if (isPt) "Este campeão é fraco contra $target. Jogue com segurança e espere ajuda da sua equipe."
                else "This champion is weak against $target. Play safely and wait for team assistance."
            }
            "Situacional" -> {
                if (isEs) "Este es un objeto situacional. Cómpralo contra $target para ganar ventaja."
                else if (isPt) "Este é um item situacional. Compre-o contra $target para ganhar vantagem."
                else "This is a situational item. Buy it against $target to gain an advantage."
            }
            else -> { // Sinergia
                if (isEs) "Excelente sinergia con $target para ganar las peleas de equipo."
                else if (isPt) "Excelente sinergia com $target para vencer as lutas de equipe."
                else "Excellent synergy with $target to win teamfights."
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
        return when (activeRole) {
            LaneRole.ADC -> if (isEs) "Concéntrate en farmear seguro y acumular oro para tus objetos clave. Posiciónate siempre detrás de tu soporte/tanque en peleas grupales." else if (isPt) "Concentre-se em farmar com segurança e acumular ouro para seus itens essenciais. Posicione-se sempre atrás do seu suporte/tanque nas lutas de equipe." else "Focus on safe farming and stacking gold for key items. Always position behind your support/tank in teamfights."
            LaneRole.SUPPORT -> if (isEs) "Controla la visión (wards) en objetivos y protege a tus carries. Guarda tu CC para interrumpir al asesino enemigo." else if (isPt) "Controle a visão (sentinelas) ao redor de objetivos e proteja seus carries. Guarde seu CC para interromper o assassino inimigo." else "Control vision (wards) around objectives and peel for your carries. Save your CC to interrupt enemy assassins."
            LaneRole.MID -> if (isEs) "Usa tu presión de línea para rotar (roam) con el jungla. En peleas grupales, prioriza flanquear o aplicar daño explosivo al carry enemigo." else if (isPt) "Use sua pressão de rota para rotacionar (roam) com o caçador. Nas lutas de equipe, priorize flanquear ou explodir o carry inimigo." else "Use lane pressure to roam with the jungler. In teamfights, prioritize flanking or bursting the enemy carry."
            LaneRole.JUNGLE -> if (isEs) "Garantiza el control de los Escurridizos (Scuttles) para visión y rastrea la ruta del jungla rival. Asegura los Dragones y Heraldos." else if (isPt) "Garanta o controle dos Aronguejos para visão e rastreie a rota do caçador inimigo. Garanta os Dragões e Arautos." else "Secure Scuttles for vision and track the enemy jungler's path. Secure Dragons and Heralds."
            LaneRole.TOP -> if (isEs) "Mantén la presión dividida (split-push) si tienes ventaja, o agruparte si tu equipo necesita iniciación o tanqueo para los objetivos." else if (isPt) "Mantenha a pressão dividida (split-push) se estiver em vantagem, ou agrupe-se se sua equipe precisar de iniciação ou tanque para os objetivos." else "Keep split-push pressure if ahead, or group if your team needs engage or frontline for objectives."
        }
    }
}
