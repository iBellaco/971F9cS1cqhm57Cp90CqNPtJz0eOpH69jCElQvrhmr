const https = require('https');
const cheerio = require('cheerio');
https.get('https://wr-meta.com/items/', (res) => {
  let data = '';
  res.on('data', (chunk) => { data += chunk; });
  res.on('end', () => {
    const $ = cheerio.load(data);
    console.log("H2s:", $('h2').length);
    console.log("Categories:", $('.equip-col-in').length);
    console.log("Items:", $('.bild-img-short').length);
  });
});
