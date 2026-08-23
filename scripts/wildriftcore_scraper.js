const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');

async function scrapeAll() {
    console.log("Starting scrape of wildriftcore.com...");
    const baseUrl = 'https://wildriftcore.com';
    
    // Scrape Champion List
    let champions = [];
    try {
        const { data } = await axios.get(`${baseUrl}/es/champions/`);
        const $ = cheerio.load(data);
        
        $('a[href^="/es/champions/"]').each((i, el) => {
            const href = $(el).attr('href');
            if (href !== '/es/champions/' && href.split('/').length === 5) {
                const champId = href.split('/')[3];
                if (!champions.includes(champId)) {
                    champions.push(champId);
                }
            }
        });
        console.log(`Found ${champions.length} champions.`);
    } catch (e) {
        console.error("Failed to get champions list", e.message);
        return;
    }

    const scrapedData = [];
    
    // Limit to 5 for testing
    const testChamps = champions.slice(0, 5);
    
    for (const champ of testChamps) {
        console.log(`Scraping ${champ}...`);
        try {
            const { data } = await axios.get(`${baseUrl}/es/champions/${champ}/`);
            const $ = cheerio.load(data);
            
            const title = $('title').text().split('—')[0].trim();
            const items = [];
            $('.build-teaser img').each((i, el) => {
                 items.push($(el).attr('alt'));
            });
            
            scrapedData.push({
                id: champ,
                name: title,
                coreItems: items
            });
            
            // Sleep to avoid rate limiting
            await new Promise(r => setTimeout(r, 500));
        } catch(e) {
             console.error(`Error with ${champ}`, e.message);
        }
    }
    
    fs.writeFileSync('scraped_data_preview.json', JSON.stringify(scrapedData, null, 2));
    console.log("Saved preview to scraped_data_preview.json");
}

scrapeAll();
