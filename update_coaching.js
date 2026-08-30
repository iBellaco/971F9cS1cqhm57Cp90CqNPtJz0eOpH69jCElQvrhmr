const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', 'utf8');

const targetReason = `        return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        "\${champion.name} (\${champion.damageType.displayName}) tiene ventaja táctica sobre $target (\${targetDamage}). Aprovecha tus ventanas de tradeo y enfriamientos para dominar la línea y forzarlo bajo su torre."
                    } else {
                        "Este campeón tiene una fuerte ventaja sobre $target en la fase de líneas, aprovéchalo para conseguir prioridad de mapa."
                    }
                } else if (isPt) {
                    "\${champion.name} tem forte vantagem sobre $target na fase de rotas. Use suas trocas favoráveis para dominar e garantir visão."
                } else {
                    "\${champion.name} has a strong tactical advantage over $target in the laning phase. Leverage power spikes to control the lane."
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        "$target (\${targetDamage}) representa una amenaza alta para \${champion.name}. Respeta su rango y all-in, farmea pacientemente y espera el apoyo de tu jungla antes de pelear."
                    } else {
                        "Este campeón es vulnerable contra $target. Juega con seguridad, congela la oleada y espera asistencia de tu equipo."
                    }
                } else if (isPt) {
                    "$target é perigoso contra \${champion.name}. Jogue recuado, farme com segurança e espere a ajuda do seu caçador."
                } else {
                    "$target poses a high threat to \${champion.name}. Play cautiously, manage your wave, and wait for team rotations."
                }
            }`;

const newReason = `        val mySkill = champion.skills.find { it.slot == "1" }?.name ?: champion.skills.firstOrNull()?.name ?: "habilidades"
        val isMeRanged = champion.isRanged
        val isTargetRanged = targetChamp?.isRanged ?: false
        
        return when (type) {
            "Ventaja" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        if (isMeRanged && !isTargetRanged) {
                            "\${champion.name} puede abusar de su rango contra $target. Castígalo con $mySkill cada vez que intente farmear, y mantén la distancia para ganar la línea sin recibir daño \${targetDamage.lowercase()}."
                        } else if (!isMeRanged && isTargetRanged) {
                            "\${champion.name} tiene un all-in superior al de $target. Soporta el desgaste inicial y usa $mySkill para acortar distancias; una vez encima, no podrá sobrevivir a tu daño."
                        } else {
                            "\${champion.name} domina este enfrentamiento. Aprovecha el enfriamiento de las habilidades de $target para intercambiar daño con $mySkill, forzándolo a jugar bajo su torre."
                        }
                    } else {
                        "\${champion.name} tiene un kit superior frente a $target. Castiga sus errores de posicionamiento para conseguir prioridad de mapa."
                    }
                } else if (isPt) {
                    "\${champion.name} tem forte vantagem sobre $target. Puna-o com $mySkill sempre que tentar farmar."
                } else {
                    "\${champion.name} dominates $target. Abuse $mySkill cooldowns and force them under tower."
                }
            }
            "Debilidad" -> {
                if (isEs) {
                    if (targetChamp != null) {
                        if (!isMeRanged && isTargetRanged) {
                            "$target te castigará constantemente por tu falta de rango. Sacrifica algunos súbditos si es necesario, usa $mySkill solo para asegurar oro seguro y espera a tu jungla."
                        } else if (targetDamage.equals("Mágico", true)) {
                            "El daño mágico explosivo de $target es letal para \${champion.name}. Considera botas de resistencia, evita los intercambios largos y guarda $mySkill para protegerte o escapar."
                        } else {
                            "$target supera a \${champion.name} en 1vs1. Respeta su daño \${targetDamage.lowercase()}, no fuerces peleas innecesarias y maximiza tu farmeo bajo torre."
                        }
                    } else {
                        "\${champion.name} sufre mucho contra el kit de $target. Juega de forma conservadora y pide rotaciones tempranas."
                    }
                } else if (isPt) {
                    "$target é extremamente letal contra \${champion.name}. Jogue recuado e reserve $mySkill para defesa."
                } else {
                    "$target counters \${champion.name}. Play defensively, freeze the wave, and wait for ganks."
                }
            }`;

code = code.replace(targetReason, newReason);
fs.writeFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', code);
