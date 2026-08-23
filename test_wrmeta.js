const axios = require('axios');
const cheerio = require('cheerio');

async function test() {
    try {
        const { data } = await axios.get('https://wr-meta.com/items/');
        const $ = cheerio.load(data);
        
        let cnt = 0;
        $('img').each((i, el) => {
            const dataSrc = $(el).attr('data-src');
            const alt = $(el).attr('alt') || '';
            if (dataSrc && !dataSrc.includes('icon-stat') && !dataSrc.includes('Gold_icon') && (alt.includes('Rune') || alt.includes('Wild Rift'))) {
                console.log(alt, dataSrc);
                cnt++;
            }
        });
        console.log("Items found:", cnt);
    } catch(e) {
        console.error(e.message);
    }
}
test();
