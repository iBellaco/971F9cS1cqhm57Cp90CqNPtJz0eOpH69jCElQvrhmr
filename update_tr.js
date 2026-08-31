const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'utf8');

code = code.replace(
    /text = "Aprovecha que las rotaciones en Wild Rift son extremadamente rápidas\. Prioriza limpiar tu oleada y rotar al Escurridizo o Dragón antes del minuto 5\. Si \$\{enemyOpponent\.name\} rota primero, castiga su torre para conseguir las valiosas placas de oro\.",/,
    'text = tr("Aprovecha que las rotaciones en Wild Rift son rápidas. Prioriza rotar al Dragón antes del minuto 5. Si %s rota primero, castiga su torre por placas.").format(enemyOpponent.name),'
);

code = code.replace(
    /text = "Tienes la ventaja de campeón\. Mantén el control de la oleada y usa los arbustos laterales para rotar rápido y emboscar \(roam\) a otras líneas\."/,
    'text = tr("Tienes la ventaja de campeón. Mantén el control de la oleada y usa los arbustos para rotar y emboscar (roam) a otras líneas.")'
);

code = code.replace(
    /text = "Mantén la calma y no cedas oro\. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre\."/,
    'text = tr("Mantén la calma y no cedas oro. En Wild Rift el juego tardío llega rápido; agrupa con tu equipo tan pronto caiga la primera torre.")'
);

code = code.replace(
    /text = "Duelo equilibrado\. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada\.",/,
    'text = tr("Duelo equilibrado. Mantén visión en el río con Lente Revelador antes de los objetivos y castiga cuando use habilidades en la oleada."),'
);

fs.writeFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', code);
