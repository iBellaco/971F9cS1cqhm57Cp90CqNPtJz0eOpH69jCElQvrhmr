const fs = require('fs');

const parsed = JSON.parse(fs.readFileSync('parsed_items.json', 'utf-8'));
const currentKt = fs.readFileSync('current_items.kt', 'utf-8');

const regex = /add\(WildRiftItem\(\s*"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*ItemCategory\.([A-Z0-9_]+)\s*,\s*(\d+)\s*,\s*"([^"]*)"\s*,\s*"([^"]*)"\s*,\s*"([^"]*)"\s*\)\)/g;

let match;
const slugToEsMap = {};

while ((match = regex.exec(currentKt)) !== null) {
  const id = match[1];
  const esName = match[2];
  
  // the id is something like "bloodthirster_physical". We can strip the category part to get a pure slug
  let coreSlug = id.replace(/_(physical|magic|defense|support|active|boots_t2|boots_t3|mid_tier|basic)$/, '');
  slugToEsMap[coreSlug] = { id, esName };
  slugToEsMap[id] = { id, esName };
}

let ktCode = `package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

/**
 * Catálogo exhaustivo de todos los ${parsed.length} objetos de League of Legends: Wild Rift
 * obtenido y sincronizado minuciosamente directamente desde https://wr-meta.com/items/
 */
object WildRiftItemsData {
    val list: List<WildRiftItem> = buildList {
`;

parsed.forEach(item => {
  let esName = item.enName;
  let rawSlug = item.enName.toLowerCase().replace(/[^a-z0-9]+/g, '_');
  // strip trailing underscores
  rawSlug = rawSlug.replace(/_$/, '');
  
  let id = rawSlug;
  
  // Try to find Spanish mapping
  if (slugToEsMap[rawSlug]) {
    esName = slugToEsMap[rawSlug].esName;
    id = slugToEsMap[rawSlug].id;
  } else if (slugToEsMap[rawSlug + '_physical']) {
    esName = slugToEsMap[rawSlug + '_physical'].esName;
    id = slugToEsMap[rawSlug + '_physical'].id;
  } else if (slugToEsMap[rawSlug + '_magic']) {
    esName = slugToEsMap[rawSlug + '_magic'].esName;
    id = slugToEsMap[rawSlug + '_magic'].id;
  } else if (slugToEsMap[rawSlug + '_defense']) {
    esName = slugToEsMap[rawSlug + '_defense'].esName;
    id = slugToEsMap[rawSlug + '_defense'].id;
  } else if (slugToEsMap[rawSlug + '_support']) {
    esName = slugToEsMap[rawSlug + '_support'].esName;
    id = slugToEsMap[rawSlug + '_support'].id;
  } else if (slugToEsMap[rawSlug + '_boots_t3']) {
    esName = slugToEsMap[rawSlug + '_boots_t3'].esName;
    id = slugToEsMap[rawSlug + '_boots_t3'].id;
  } else if (slugToEsMap[rawSlug + '_active']) {
    esName = slugToEsMap[rawSlug + '_active'].esName;
    id = slugToEsMap[rawSlug + '_active'].id;
  } else if (slugToEsMap[rawSlug + '_mid_tier']) {
    esName = slugToEsMap[rawSlug + '_mid_tier'].esName;
    id = slugToEsMap[rawSlug + '_mid_tier'].id;
  } else if (slugToEsMap[rawSlug + '_basic']) {
    esName = slugToEsMap[rawSlug + '_basic'].esName;
    id = slugToEsMap[rawSlug + '_basic'].id;
  } else {
    // If we can't find it by raw slug, let's just use the raw slug and enName
    // We can also try mapping exact match of image, but that failed before. 
    id = rawSlug + "_" + item.category.replace('ItemCategory.', '').toLowerCase();
  }

  // escape quotes
  const safeDesc = item.desc.replace(/"/g, '\\"').replace(/\n/g, '\\n');
  const safeStats = item.stats.replace(/"/g, '\\"').replace(/\n/g, ' ');
  const safeName = esName.replace(/"/g, '\\"');
  
  ktCode += `        add(WildRiftItem(\n`;
  ktCode += `            id = "${id}",\n`;
  ktCode += `            name = "${safeName}",\n`;
  ktCode += `            category = ${item.category},\n`;
  ktCode += `            goldCost = ${item.price},\n`;
  ktCode += `            stats = "${safeStats}",\n`;
  ktCode += `            passive = "${safeDesc}",\n`;
  ktCode += `            iconUrl = "${item.imgUrl}"\n`;
  ktCode += `        ))\n`;
});

ktCode += `    }\n}\n`;

fs.writeFileSync('WildRiftItemsData_new.kt', ktCode);
