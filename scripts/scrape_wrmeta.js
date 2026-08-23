const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');

async function scrape() {
    console.log("Iniciando scraping de wr-meta.com...");
    try {
        const { data } = await axios.get('https://wr-meta.com/items/');
        const $ = cheerio.load(data);
        
        const scrapedItems = [];
        
        // La estructura de wr-meta agrupa en divs, vamos a buscar todos los p tags que tienen <b class="iname">
        $('b.iname').each((i, el) => {
            const p = $(el).closest('p');
            const img = p.prev('img');
            
            let dataSrc = img.attr('data-src') || img.attr('src');
            if (dataSrc && dataSrc.startsWith('/')) {
                dataSrc = 'https://wr-meta.com' + dataSrc;
            }
            
            const name = $(el).text().trim();
            // id: lowercase, replace spaces with _, remove non-alphanumeric
            let id = name.toLowerCase().replace(/[^a-z0-9]+/g, '_').replace(/^_+|_+$/g, '');
            // Algunos ids fijos para match:
            if (id === 'grasp_of_the_undying') id = 'grasp_undying';
            if (id === 'summon_aery') id = 'aery';
            if (id === 'axiom_arcanist') id = 'axiomatic_arcanist';
            if (id === 'font_of_life') id = 'font_of_life';
            
            // Descripción: cogemos todo el HTML del <p> y lo limpiamos
            let rawDesc = p.html();
            // remover <b class="iname">Name</b><br>
            rawDesc = rawDesc.replace(/<b class="iname">.*?<\/b><br>/, '');
            
            const $desc = cheerio.load(rawDesc);
            // Replace images with text tags like [AD], [AP] if needed, or just plain text
            $desc('img').each((j, imgEl) => {
                const src = $(imgEl).attr('src') || '';
                if (src.includes('ad.png')) $desc(imgEl).replaceWith(' AD ');
                else if (src.includes('ap.png')) $desc(imgEl).replaceWith(' AP ');
                else if (src.includes('hp.png')) $desc(imgEl).replaceWith(' HP ');
                else if (src.includes('ar.png')) $desc(imgEl).replaceWith(' Armor ');
                else if (src.includes('mr.png')) $desc(imgEl).replaceWith(' MR ');
                else if (src.includes('as.png')) $desc(imgEl).replaceWith(' AS ');
                else if (src.includes('ah.png')) $desc(imgEl).replaceWith(' Ability Haste ');
                else if (src.includes('ms.png')) $desc(imgEl).replaceWith(' MS ');
                else if (src.includes('csc.png')) $desc(imgEl).replaceWith(' Crit Chance ');
                else $desc(imgEl).replaceWith('');
            });
            
            let cleanDesc = $desc.text().replace(/\s+/g, ' ').trim();
            
            scrapedItems.push({
                id,
                name,
                iconUrl: dataSrc,
                description: cleanDesc
            });
        });
        
        fs.writeFileSync('wr_meta_data.json', JSON.stringify(scrapedItems, null, 2));
        console.log(`Scraping completado. ${scrapedItems.length} items encontrados.`);
        
    } catch(e) {
        console.error(e);
    }
}
scrape();
