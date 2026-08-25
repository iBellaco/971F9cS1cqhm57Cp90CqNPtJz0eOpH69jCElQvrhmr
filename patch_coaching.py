import re

with open('app/src/main/java/com/example/util/CoachingGenerator.kt', 'r', encoding='utf-8') as f:
    content = f.read()

old_func = r'''fun generateTacticalAdvice\(champion: Champion, activeRole: LaneRole, lang: String\): String \{[\s\S]*?\}'''
new_func = '''fun generateTacticalAdvice(champion: Champion, activeRole: LaneRole, lang: String): String {
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
    }'''

content = re.sub(old_func, new_func, content)

with open('app/src/main/java/com/example/util/CoachingGenerator.kt', 'w', encoding='utf-8') as f:
    f.write(content)

