const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', 'utf8');

// Replace Macro translations
code = code.replace(/\*\*Juego Medio\/Tardío:\*\* El control de Dragones y Barón es vital\. En peleas de equipo por objetivos, busca posicionarte para iniciar peleas u obtener picks clave desatando \$ultSkill\./g, 
"**Juego Medio/Tardío (Macro Wild Rift):** El mapa es pequeño y las rotaciones son rápidas. Prioriza asegurar el Dragón o Heraldo temprano, y usa tu $ultSkill para conseguir emboscadas clave que permitan a tu equipo tirar torres e invadir la jungla.");

code = code.replace(/\*\*Juego Medio\/Tardío:\*\* Como fuente principal de daño \$\{champion.damageType.displayName\}, debes posicionarte cautelosamente en las peleas de equipo y esperar el momento óptimo para desatar \$ultSkill\./g, 
"**Juego Medio/Tardío (Macro Wild Rift):** En este juego de ritmo acelerado, una emboscada tardía es fatal. Muévete siempre con tu equipo por el río o la jungla y usa tu $ultSkill de forma explosiva en espacios cerrados (pasillos de jungla) para borrar a los rivales.");

code = code.replace(/\*\*Juego Medio\/Tardío:\*\* Tu supervivencia es la máxima condición de victoria\. Posiciónate en la retaguardia, esquiva el CC y usa \$ultSkill cuando sea seguro para derretir la composición enemiga\./g, 
"**Juego Medio/Tardío (Macro Wild Rift):** Agrupa con tu soporte lo antes posible. Los asedios a torres de inhibidor en Wild Rift son rápidos. Posiciónate seguro detrás de tu línea frontal y castiga con $ultSkill sin arriesgar tu vida.");

code = code.replace(/\*\*Juego Medio\/Tardío:\*\* Tu control de visión alrededor del Barón y Dragón Ancestral gana partidas\. Guarda \$ultSkill para desarmar la iniciación enemiga o habilitar a tus carries\./g, 
"**Juego Medio/Tardío (Macro Wild Rift):** Deniega la visión enemiga con Lente Revelador en el río antes de los objetivos neutrales (Dragones, Barón). Usa el corto enfriamiento de las botas encantadas y tu $ultSkill para salvar a tu ADC o enganchar al acarreador rival.");

code = code.replace(/\*\*Juego Medio\/Tardío:\*\* Durante el juego medio, aplica presión dividida \(split-push\) si tienes ventaja, o únete al equipo usando \$ultSkill de forma inteligente para eliminar carries rivales\./g, 
"**Juego Medio/Tardío (Macro Wild Rift):** Empuja tu línea para aplicar presión dividida, pero recuerda que el mapa es corto: agrupa rápidamente a pie para los objetivos (Heraldo/Barón) y flanquea con tu $ultSkill a los objetivos vulnerables.");

fs.writeFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', code);
