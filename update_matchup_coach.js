const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'utf8');

const coachRegex = /text = if \(isMyCounter\)[\s\S]*?castiga cuando el rival use habilidades en los súbditos\.",/;

const coachTextNew = `text = if (isMyCounter) 
                                        "Tienes la ventaja de campeón. Mantén el control de la oleada y usa los arbustos laterales para rotar rápido y emboscar (roam) a otras líneas."
                                    else if (isEnemyCounter)
                                        "Mantén la calma y no cedas oro. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre."
                                    else
                                        "Duelo equilibrado. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada.",`;

if (coachRegex.test(code)) {
    code = code.replace(coachRegex, coachTextNew);
} else {
    console.log("Could not find coachTextOld with regex!");
}

fs.writeFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', code);
