import fs from 'fs';

const filePath = 'app/src/main/java/com/example/data/WildRiftItemsData.kt';
let data = fs.readFileSync(filePath, 'utf8');

const regex = /id = "spectral_sickle",[\s\S]*?passive = "[\s\S]*?",\s*passiveEn = "/g;

const replaceWith = `id = "spectral_sickle",
            name = "Hoz espectral",
            nameEn = "Spectral Sickle",
            category = "Artículos Básicos",
            goldCost = 500,
            stats = "",
            statsEn = "Quest:",
            passive = "Este objeto es para los apoyos. Al comprarlo, reduce el oro recibido por asesinar a súbditos y monstruos. Si el grupo cuenta con múltiples objetos de este tipo, solo uno de ellos se activará.\\nVersátil: Otorga 10 de daño de ataque o 20 de poder de habilidad (adaptable).\\nTributo: Gana 1 orbe(s) de energía giratorio(s) cada 30 s (hasta un máximo de 3). Mientras estás cerca de un aliado, las acciones a continuación activarán Tributo, lo que consume 1 orbe(s) de energía, te otorga 65 de oro y recuperas 20-80 de vida:\\n1. Utilizar habilidades o ataques para infligir daño a campeones o estructuras enemigas.\\n2. Atacar a súbditos por debajo del 65% de vida. Esto también los ejecuta, y el oro que generes al asesinar súbditos se otorgará al aliado más cercano.\\n3. Con 3 orbes, asesina a un súbdito cercano.\\nAl activar Tributo, el aliado más cercano a ti obtiene acumulaciones de Tributo.\\nCentinela: Infliges 1 más de daño a los guardianes de visión revelados con lentes de gran alcance, guardianes de control o flores del adivino.\\nModeración: No consigues oro al asesinar súbditos, pero obtienes oro equivalente a un 50% de su botín. El oro que generes al asesinar súbditos se otorgará al aliado más cercano. El oro generado al asesinar monstruos se reduce un 50%.\\nMisión: Gana 750 de oro con este objeto para que se transforme en Guadaña de la Niebla Negra y te vincule al aliado con más acumulaciones de Tributo para convertiros en Compañeros ideales.",
            passiveEn = "`;

data = data.replace(regex, replaceWith);

fs.writeFileSync(filePath, data, 'utf8');
console.log('Fixed syntax error.');
