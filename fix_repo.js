const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/data/WildRiftRepository.kt', 'utf8');

const targetSynergy = `        synergyText = if (directSynergies.isNotEmpty()) "Buena combinación con: " + directSynergies.joinToString(", ") else "Autosuficiente en rotaciones."
        counterText = if (directCounters.isNotEmpty()) "Anula a: " + directCounters.joinToString(", ") else if (directWeaknesses.isNotEmpty()) "Juega seguro contra: " + directWeaknesses.joinToString(", ") else "Enfrentamiento parejo."`;

const newSynergy = `        val mainSkill = champ.skills.find { it.slot == "1" }?.name ?: champ.skills.firstOrNull()?.name ?: "habilidades"
        synergyText = if (directSynergies.isNotEmpty()) {
            "Sincroniza tus engages y combina $mainSkill junto con " + directSynergies.joinToString(", ") + " para dominar las peleas de equipo."
        } else {
            "Campeón independiente. Prioriza tu propio escalado y $mainSkill."
        }
        
        counterText = if (directCounters.isNotEmpty()) {
            "Usa tu $mainSkill para anular completamente a: " + directCounters.joinToString(", ") + "."
        } else if (directWeaknesses.isNotEmpty()) {
            "Cuidado con " + directWeaknesses.joinToString(", ") + ", pueden interrumpir tu $mainSkill fácilmente."
        } else {
            "Enfrentamiento estable sin counters directos a la vista."
        }`;

code = code.replace(targetSynergy, newSynergy);
fs.writeFileSync('app/src/main/java/com/example/data/WildRiftRepository.kt', code);
