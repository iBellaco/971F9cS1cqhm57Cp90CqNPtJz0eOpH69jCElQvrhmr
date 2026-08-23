const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');
const path = require('path');

const BASE_URL = 'https://wildriftcore.com';
const LANG = '/es'; // Utilizar la versión en español para integrarse con la app

async function scrape() {
    console.log("Iniciando Scrapping de WildRiftCore...");
    
    let championsList = [];
    try {
        const { data } = await axios.get(`${BASE_URL}${LANG}/champions/`);
        const $ = cheerio.load(data);
        $('a[href^="/es/champions/"]').each((i, el) => {
            const href = $(el).attr('href');
            if (href !== '/es/champions/' && href.split('/').length === 5) {
                const champId = href.split('/')[3];
                if (!championsList.includes(champId)) championsList.push(champId);
            }
        });
        console.log(`✅ [Campeones] Encontrados: ${championsList.length}`);
    } catch (e) {
        console.error("❌ Error obteniendo lista de campeones:", e.message);
        return;
    }

    const scrapedChampions = [];
    
    // Scrape de los primeros 3 para prueba rápida (para no exceder límites de tiempo en la demo)
    // Se puede modificar para mapear los 141
    const testChamps = championsList.slice(0, 3);
    
    for (const champ of championsList) {
        console.log(`🔍 Analizando: ${champ}...`);
        try {
            const { data } = await axios.get(`${BASE_URL}${LANG}/champions/${champ}/`);
            const $ = cheerio.load(data);
            
            const title = $('title').text().split('—')[0].trim();
            const winrateText = $('meta[name="description"]').attr('content') || '';
            const wrMatch = winrateText.match(/WR (\d+\.\d+)%/);
            const winrate = wrMatch ? parseFloat(wrMatch[1]) : 50.0;
            
            const items = [];
            $('.build-teaser img').each((i, el) => {
                 const itemName = $(el).attr('alt');
                 if (itemName) items.push(itemName);
            });
            
            scrapedChampions.push({
                id: champ,
                name: title.replace(' Wild Rift', '').trim(),
                winrate: winrate,
                coreItems: items
            });
            
            // Retardo para evitar Rate Limiting (Protección Anti-DDoS de Cloudflare)
            await new Promise(r => setTimeout(r, 200)); 
        } catch(e) {
             console.error(`❌ Error en ${champ}:`, e.message);
        }
    }
    
    const outputPath = path.join(__dirname, '..', 'app', 'src', 'main', 'assets', 'scraped_champions.json');
    fs.writeFileSync(outputPath, JSON.stringify(scrapedChampions, null, 2));
    console.log(`💾 Datos guardados exitosamente en: ${outputPath}`);
}

scrape();
