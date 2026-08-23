const cheerio = require('cheerio');
async function run() {
  const res = await fetch('https://wr-meta.com/items/');
  const html = await res.text();
  const $ = cheerio.load(html);
  
  const items = [];
  $('.item-card, .item, .c-item').each((i, el) => {
    // we need to find the correct selector. let's just print some classes first
  });
  console.log("HTML length:", html.length);
  console.log("Sample HTML:", html.substring(0, 1000));
}
run();
