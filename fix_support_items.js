import fs from 'fs';

const filePath = 'app/src/main/java/com/example/data/WildRiftItemsData.kt';
let data = fs.readFileSync(filePath, 'utf8');

function updateItem(id, name, stats, passive) {
    const itemRegex = new RegExp(`(id = "${id}",[\\s\\S]*?name = ")([\\s\\S]*?)("[\\s\\S]*?\\n\\s*stats = ")([\\s\\S]*?)("[\\s\\S]*?\\n\\s*passive = ")([\\s\\S]*?)(")`, 'g');
    
    let matched = false;
    data = data.replace(itemRegex, (match, p1, oldName, p3, oldStats, p5, oldPassive, p7) => {
        matched = true;
        let updatedName = name !== null ? name : oldName;
        let updatedStats = stats !== null ? stats : oldStats;
        let updatedPassive = passive !== null ? passive : oldPassive;
        
        return `${p1}${updatedName}${p3}${updatedStats}${p5}${updatedPassive}${p7}`;
    });
    if (!matched) {
        console.log(`Failed to match ID: ${id}`);
    }
}

updateItem(
    'bulwark_of_the_mountain',
    'Baluarte de la montaña',
    '+175 Vida máxima • +10 Velocidad de habilidades',
    'Estallido de alma: Cada 60 s, obtienes 75 de oro, 25 de vida y 2 de daño de ataque o 4 de poder de habilidad (adaptable); hasta 250 de vida y 20 de daño de ataque o 40 de poder de habilidad (adaptable).\\nInfliges 2 más de daño a los guardianes de visión revelados con lentes de gran alcance, guardianes de control o flores del adivino.\\nFuera de combate, otorga un 10% de velocidad de movimiento al moverte hacia tu Compañero ideal. Si os encontráis a más de 2500 unidades de distancia, esta bonificación aumenta a un 30%.'
);

updateItem(
    'black_mist_scythe',
    'Guadaña de la Niebla Negra',
    '+10 Velocidad de habilidades',
    'Versátil: Otorga 14 de daño de ataque o 28 de poder de habilidad (adaptable).\\nEstallido de alma: Cada 60 s, obtienes 75 de oro, 25 de vida y 2 de daño de ataque o 4 de poder de habilidad (adaptable); hasta 250 de vida y 20 de daño de ataque o 40 de poder de habilidad (adaptable).\\nInfliges 2 más de daño a los guardianes de visión revelados con lentes de gran alcance, guardianes de control o flores del adivino.\\nFuera de combate, otorga un 10% de velocidad de movimiento al moverte hacia tu Compañero ideal. Si os encontráis a más de 2500 unidades de distancia, esta bonificación aumenta a un 30%.'
);

fs.writeFileSync(filePath, data, 'utf8');
console.log('Items updated.');
