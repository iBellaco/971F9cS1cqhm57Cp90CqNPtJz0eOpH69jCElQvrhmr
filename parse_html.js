const fs = require('fs');
const cheerio = require('cheerio');

const html = fs.readFileSync('wr_items.html', 'utf-8');
const $ = cheerio.load(html);

const categoryMap = {
  'PHYSICAL DAMAGE ITEMS': 'ItemCategory.PHYSICAL',
  'Magic Damage Items': 'ItemCategory.MAGIC',
  'Defense Items': 'ItemCategory.DEFENSE',
  'Support Items': 'ItemCategory.SUPPORT',
  'ACTIVE SPELL ITEMS': 'ItemCategory.ACTIVE',
  'Boots tier 2': 'ItemCategory.BOOTS_T2',
  'Boots tier 3': 'ItemCategory.BOOTS_T3',
  'Mid Tier Items': 'ItemCategory.MID_TIER',
  'Basic Items': 'ItemCategory.BASIC'
};

const items = [];
let currentCategory = null;

// The structure is usually H2 for categories, then .items > .bild-img-short
$('h2, .bild-img-short').each((i, el) => {
  if ($(el).is('h2')) {
    const text = $(el).text().trim();
    if (categoryMap[text] || categoryMap[text.toUpperCase()] || Object.keys(categoryMap).some(k => k.toLowerCase() === text.toLowerCase())) {
       const match = Object.keys(categoryMap).find(k => k.toLowerCase() === text.toLowerCase());
       currentCategory = categoryMap[match];
    }
  } else if ($(el).hasClass('bild-img-short')) {
    if (!currentCategory) return;
    
    let name = $(el).find('.iname').text().trim();
    if (!name) name = $(el).find('.newsbox_h_short').text().trim();
    
    const img = $(el).find('.itemimage').attr('data-src') || $(el).find('.itemimage').attr('src');
    const fullImgUrl = img && img.startsWith('/') ? 'https://wr-meta.com' + img : img;
    
    const priceStr = $(el).find('.goldt').text().trim();
    const price = parseInt(priceStr.replace(/[^0-9]/g, '')) || 0;
    
    // We want to extract stats and passives nicely.
    // .istats for stats
    const statsArray = [];
    $(el).find('.istats').each((_, statEl) => {
       statsArray.push($(statEl).text().trim().replace(/\s+/g, ' '));
    });
    
    // other text in <p> after taking out .iname, .cdr, .istats, .goldt
    // Actually, getting text nodes and handling <br> properly
    const pNode = $(el).find('p');
    // replace <br> with newline to keep formatting
    pNode.find('br').replaceWith('\n');
    let fullText = pNode.text().trim();
    
    // Clean it up
    fullText = fullText.split('\n').map(l => l.trim()).filter(l => l.length > 0).join('\n');
    
    items.push({
      enName: name,
      category: currentCategory,
      price: price,
      stats: statsArray.join(' • '),
      desc: fullText,
      imgUrl: fullImgUrl
    });
  }
});

fs.writeFileSync('parsed_items.json', JSON.stringify(items, null, 2));
console.log(`Parsed ${items.length} items`);
