const axios = require('axios');
const cheerio = require('cheerio');

async function scrapeChampion(name) {
    const url = `https://wildriftcore.com/es/champions/${name}/`;
    try {
        const { data } = await axios.get(url);
        const $ = cheerio.load(data);
        console.log("Title:", $('title').text());
        // Find builds, items, runes
        console.log("Found images:", $('img').length);
        const items = [];
        $('.build-teaser img').each((i, el) => {
             items.push($(el).attr('alt'));
        });
        console.log("Items from build-teaser:", items);
    } catch (e) {
        console.error("Error scraping:", e.message);
    }
}
scrapeChampion('aatrox');
