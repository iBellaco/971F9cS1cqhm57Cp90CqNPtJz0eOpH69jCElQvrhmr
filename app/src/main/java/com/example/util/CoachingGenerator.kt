package com.example.util

import com.example.model.Champion
import com.example.model.LaneRole

object CoachingGenerator {
    fun generateMatchupReason(champName: String, target: String, type: String, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        return when (type) {
            "Ventaja" -> if (isEs) "$champName tiene ventaja porque su kit permite presionar a $target en el juego temprano, limitando su farmeo o esquivando su daño principal."
            else "$champName has an advantage because their kit allows pressuring $target in the early game, limiting their farm or dodging their main damage."
            
            "Debilidad" -> if (isEs) "$target posee herramientas de control de masas o burst que neutralizan directamente el escalado o la movilidad de $champName."
            else "$target has crowd control or burst tools that directly neutralize $champName's scaling or mobility."
            
            "Situacional" -> if (isEs) "Compra $target si el equipo enemigo tiene mucho daño explosivo, curaciones excesivas, o si necesitas sobrevivir a la iniciación rival."
            else "Buy $target if the enemy team has high burst damage, excessive healing, or if you need to survive enemy engages."
            
            else -> if (isEs) "$champName y $target tienen excelente sinergia en peleas grupales (Teamfights). Combinar sus habilidades definitivas garantiza daño en área o protección clave."
            else "$champName and $target have excellent synergy in teamfights. Combining their ultimate abilities guarantees AoE damage or key protection."
        }
    }

    fun generateTacticalAnalysis(champion: Champion, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        val roleStr = champion.primaryRole.displayName
        
        val base = if (isEs) {
            "**Fase de Líneas:** En ${roleStr}, ${champion.name} debe centrarse en asegurar súbditos y buscar intercambios cortos cuando sus habilidades principales estén disponibles. "
        } else {
            "**Laning Phase:** In ${roleStr}, ${champion.name} should focus on securing minions and looking for short trades when core abilities are off cooldown. "
        }
        
        val mid = if (isEs) {
            "**Juego Medio/Tardío:** Su principal condición de victoria es agruparse en objetivos (Dragón/Barón). Como fuente de daño ${champion.damageType.displayName}, es crucial el posicionamiento para evitar el CC enemigo y aplicar daño constante."
        } else {
            "**Mid/Late Game:** Their main win condition is grouping for objectives (Dragon/Baron). As a ${champion.damageType.displayName} damage source, positioning is crucial to avoid enemy CC and deal consistent damage."
        }
        
        return champion.summary + "\n\n" + base + "\n\n" + mid
    }

    fun generateTacticalAdvice(champion: Champion, lang: String): String {
        val isEs = lang == "es" || lang == "auto"
        return when (champion.primaryRole) {
            LaneRole.ADC -> if (isEs) "Concéntrate en farmear seguro y acumular oro para tus objetos clave. Posiciónate siempre detrás de tu soporte/tanque en peleas grupales." else "Focus on safe farming and stacking gold for key items. Always position behind your support/tank in teamfights."
            LaneRole.SUPPORT -> if (isEs) "Controla la visión (wards) en objetivos y protege a tus carries. Guarda tu CC para interrumpir al asesino enemigo." else "Control vision (wards) around objectives and peel for your carries. Save your CC to interrupt enemy assassins."
            LaneRole.MID -> if (isEs) "Usa tu presión de línea para rotar (roam) con el jungla. En peleas grupales, prioriza flanquear o aplicar daño explosivo al carry enemigo." else "Use lane pressure to roam with the jungler. In teamfights, prioritize flanking or bursting the enemy carry."
            LaneRole.JUNGLE -> if (isEs) "Garantiza el control de los Escurridizos (Scuttles) para visión y rastrea la ruta del jungla rival. Asegura los Dragones y Heraldos." else "Secure Scuttles for vision and track the enemy jungler's path. Secure Dragons and Heralds."
            LaneRole.TOP -> if (isEs) "Mantén la presión dividida (split-push) si tienes ventaja, o agruparte si tu equipo necesita iniciación o tanqueo para los objetivos." else "Keep split-push pressure if ahead, or group if your team needs engage or frontline for objectives."
        }
    }
}
