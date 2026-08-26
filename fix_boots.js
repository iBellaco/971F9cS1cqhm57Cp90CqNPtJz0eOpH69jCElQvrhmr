import fs from 'fs';

const filePath = 'app/src/main/java/com/example/data/WildRiftItemsData.kt';
let data = fs.readFileSync(filePath, 'utf8');

function updateItem(id, name, stats, passive) {
    const itemRegex = new RegExp(`(id = "${id}",[\\s\\S]*?name = ")([\\s\\S]*?)("[\\s\\S]*?\\n\\s*stats = ")([\\s\\S]*?)("[\\s\\S]*?\\n\\s*passive = ")([\\s\\S]*?)(")`, 'g');
    
    let matched = false;
    data = data.replace(itemRegex, (match, p1, oldName, p3, oldStats, p5, oldPassive, p7) => {
        matched = true;
        let updatedName = name || oldName;
        let updatedStats = stats || oldStats;
        let updatedPassive = (passive !== null) ? passive : oldPassive;
        
        return `${p1}${updatedName}${p3}${updatedStats}${p5}${updatedPassive}${p7}`;
    });
    if (!matched) {
        console.log(`Failed to match ID: ${id}`);
    }
}

// 1. plated_steelcaps
updateItem('plated_steelcaps', 'Botas blindadas', '+150 Vida máxima • +25 Armadura • +45 Velocidad de movimiento', 'Bloquear: Reduce el daño recibido de ataques de campeones en un 10%.');

// 2. ionian_boots_of_lucidity
updateItem('ionian_boots_of_lucidity', 'Botas jonias de la lucidez', '+50% Regeneración de maná • +15 Velocidad de habilidades • +45 Velocidad de movimiento', 'Invocación: Reduce el enfriamiento de los hechizos un 15%.');

// 3. boots_of_mana
updateItem('boots_of_mana', 'Botas de maná', '+25 Poder de habilidad • +8 Penetración mágica • +75% Regeneración de maná • +45 Velocidad de movimiento', 'Equilibrio: Los campeones sin maná obtienen un 50% de regeneración de vida básica.\\nSupermatón: Los ataques y habilidades activas infligen 18 de daño verdadero adicional a los súbditos.');

// 4. boots_of_dynamism
updateItem('boots_of_dynamism', 'Botas dinámicas', '+15 Daño de ataque • +10 Penetración de armadura • +45 Velocidad de movimiento', null);

// 5. boots_of_speed
updateItem('boots_of_speed', 'Botas de velocidad', '+25 Velocidad de movimiento', null);

// 6. gluttonous_greaves
updateItem('gluttonous_greaves', 'Grebas codiciosas', '+45 Velocidad de movimiento', 'Equilibrio de poder: Otorga 12 de daño de ataque o 20 de poder de habilidad (adaptable).\\nConversión: Otorga un 5% de omnisucción. Las asistencias o asesinatos otorgan un 0,5% de omnisucción adicional, hasta un máximo del 5%.\\nAhora y siempre: Cuando tienes más del 50% de vida, infliges un 5% de daño adicional. Cuando tienes menos del 50% de vida, potencia un 12% las curaciones y escudos.');

// 7. immortal_treds
updateItem('immortal_treds', 'Botas inmortales', null, null);

// 8. gunmetal_greaves
updateItem('gunmetal_greaves', 'Grebas de metal', '+50% Velocidad de ataque • +5% Succión física • +45 Velocidad de movimiento', 'Piernas noxianas: Los ataques contra campeones enemigos otorgan velocidad de movimiento (10% para campeones cuerpo a cuerpo/7% para campeones a distancia) que disminuye a lo largo de 2 s.\\nHoja bendita: Los ataques restauran 12 de vida al impactar.');

// 9. chainlaced_crushers
updateItem('chainlaced_crushers', 'Trituradoras encadenadas', '+150 Vida máxima • +30 Resistencia mágica • +30% Tenacidad • +45 Velocidad de movimiento', 'Perseverancia noxiana: Tras recibir daño mágico de un campeón, obtienes un escudo mágico que absorbe una cantidad de daño equivalente a 20-140 + 5% Vida máxima durante 5 s (12 s de enfriamiento).');

// 10. armored_advance
updateItem('armored_advance', 'Avance blindado', '+150 Vida máxima • +30 Armadura • +45 Velocidad de movimiento', 'Bloquear: Reduce el daño recibido de ataques de campeones en un 10%.\\nResistencia noxiana: Tras recibir daño físico de un campeón, obtienes un escudo físico que absorbe una cantidad de daño equivalente a 20-140 + 5% de la Vida máxima durante 5 s (12 s de enfriamiento).');

// 11. crimson_lucidity
updateItem('crimson_lucidity', 'Lucidez carmesí', '+75% Regeneración de maná • +25 Velocidad de habilidades • +45 Velocidad de movimiento', 'Invocación: Reduce el enfriamiento de los hechizos un 20%.\\nPrisa noxiana: Curar u otorgar un escudo a campeones aliados, lanzar un hechizo o infligir daño a enemigos con habilidades otorga velocidad de movimiento (10% para campeones cuerpo a cuerpo/8% para campeones a distancia) durante 4 s. Este efecto solo se puede activar una vez cada 4 s por habilidad.');

// 12. spellslinger_s_shoes
updateItem('spellslinger_s_shoes', 'Botas del lanzahechizos', '+35 Poder de habilidad • +18 Penetración mágica • +8% Penetración mágica • +100% Regeneración de maná • +45 Velocidad de movimiento', 'Equilibrio: Los campeones sin maná obtienen un 50% de regeneración de vida básica.\\nSupermatón: Los ataques y habilidades activas infligen 18 de daño verdadero adicional a los súbditos.');

// 13. armorcrusher_boots
updateItem('armorcrusher_boots', 'Botas quebrantarmaduras', '+20 Daño de ataque • +10 Penetración de armadura • +6% Penetración de armadura • +45 Velocidad de movimiento', 'Caminanubes: Otorga 20 de velocidad de movimiento fuera de combate.');

fs.writeFileSync(filePath, data, 'utf8');
console.log('Update finished.');
