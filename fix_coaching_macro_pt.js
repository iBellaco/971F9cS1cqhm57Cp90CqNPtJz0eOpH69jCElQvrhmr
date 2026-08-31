const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', 'utf8');

// Portuguese replacements
code = code.replace(/\*\*Meio\/Fim de Jogo:\*\* O controle de Dragões e Barão é vital\. Nas lutas de equipe, busque se posicionar para iniciar lutas ou obter abates chave com \$ultSkill\./g, 
"**Meio/Fim de Jogo (Macro Wild Rift):** O mapa é pequeno e as rotações são rápidas. Priorize garantir o Dragão ou Arauto cedo, e use seu $ultSkill para conseguir emboscadas decisivas.");

code = code.replace(/\*\*Meio\/Fim de Jogo:\*\* Como fonte principal de dano \$\{champion.damageType.displayName\}, você deve se posicionar com cautela nas lutas e esperar o momento ideal para usar \$ultSkill\./g, 
"**Meio/Fim de Jogo (Macro Wild Rift):** Neste jogo de ritmo acelerado, uma emboscada no late game é fatal. Mova-se com sua equipe e use $ultSkill de forma explosiva em espaços fechados da selva.");

code = code.replace(/\*\*Meio\/Fim de Jogo:\*\* Sua sobrevivência é a condição de vitória\. Posicione-se na retaguarda, evite CC e use \$ultSkill quando for seguro para derreter a equipe inimiga\./g, 
"**Meio/Fim de Jogo (Macro Wild Rift):** Agrupe-se com seu suporte. Os cercos às torres de inibidor em Wild Rift são muito rápidos; posicione-se atrás da linha de frente e cause dano com $ultSkill.");

code = code.replace(/\*\*Meio\/Fim de Jogo:\*\* Seu controle de visão ao redor do Barão e Dragão Ancião vence partidas\. Guarde \$ultSkill para desarmar a iniciação inimiga ou habilitar seus carries\./g, 
"**Meio/Fim de Jogo (Macro Wild Rift):** Negue a visão inimiga com a Lente Detectora no rio. Use o baixo tempo de recarga das botas encantadas e seu $ultSkill para virar lutas a seu favor.");

code = code.replace(/\*\*Meio\/Fim de Jogo:\*\* Durante o meio de jogo, aplique pressão dividida \(split-push\) se tiver vantagem, ou junte-se à equipe usando \$ultSkill para eliminar os carries rivais\./g, 
"**Meio/Fim de Jogo (Macro Wild Rift):** Empurre sua rota para pressão dividida, mas lembre-se que o mapa é curto. Agrupe rapidamente para os objetivos e flanqueie com $ultSkill.");

fs.writeFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', code);
