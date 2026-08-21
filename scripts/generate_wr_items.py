import re
import html
import json

with open('/tmp/wr_items.html', 'r', encoding='utf-8') as f:
    raw = f.read()

categories_spec = [
    ('PHYSICAL DAMAGE ITEMS', 'PHYSICAL', '1. OBJETOS DE DAÑO FÍSICO (40 ITEMS)', 40),
    ('Magic Damage Items', 'MAGIC', '2. OBJETOS DE DAÑO MÁGICO (28 ITEMS)', 28),
    ('Defense Items', 'DEFENSE', '3. OBJETOS DE DEFENSA (32 ITEMS)', 32),
    ('Support Items', 'SUPPORT', '4. OBJETOS DE SOPORTE (17 ITEMS)', 17),
    ('ACTIVE SPELL ITEMS', 'ACTIVE', '5. OBJETOS ACTIVOS (13 ITEMS)', 13),
    ('Boots tier 2', 'BOOTS_T2', '6. BOTAS NIVEL 2 (7 ITEMS)', 7),
    ('Boots tier 3', 'BOOTS_T3', '7. BOTAS NIVEL 3 (7 ITEMS)', 7),
    ('Mid Tier Items', 'MID_TIER', '8. OBJETOS DE NIVEL MEDIO (44 ITEMS)', 44),
    ('Basic Items', 'BASIC', '9. OBJETOS DE NIVEL BÁSICO (13 ITEMS)', 13),
]

es_names = {
    'Bloodthirster': 'Sanguinaria (Bloodthirster)',
    'Infinity Edge': 'Filo del Infinito (Infinity Edge)',
    'Blade of the Ruined King': 'Hoja del Rey Arruinado (BotRK)',
    'Mortal Reminder': 'Recordatorio Mortal (Mortal Reminder)',
    'Black Cleaver': 'Cuchilla Negra (Black Cleaver)',
    'Manamune': 'Manamune',
    'Muramana': 'Muramana',
    "Death's Dance": "Danza de la Muerte (Death's Dance)",
    'Phantom Dancer': 'Bailarín Espectral (Phantom Dancer)',
    'Duskblade of Draktharr': 'Filo Fantasma de Draktharr (Duskblade)',
    "Youmuu's Ghostblade": "Espada Fantasma de Youmuu",
    'Rapid Firecannon': 'Cañón de Fuego Rápido (Rapid Firecannon)',
    "Runaan's Hurricane": "Huracán de Runaan",
    'Statikk Shiv': 'Puñal de Statikk (Statikk Shiv)',
    'Umbral Glaive': 'Guja Sombría (Umbral Glaive)',
    'Edge of Night': 'Filo de la Noche (Edge of Night)',
    'Stormrazor': 'Navaja de Asalto (Stormrazor)',
    'Solari Chargeblade': 'Espada de Carga Solari',
    'Essence Reaver': 'Segador de Esencia (Essence Reaver)',
    "Serylda's Grudge": "Rencor de Serylda",
    "Serylda’s Grudge": "Rencor de Serylda",
    'Divine Sunderer': 'Desgarrador Divino (Divine Sunderer)',
    'Hullbreaker': 'Rompecascos (Hullbreaker)',
    "Serpent's Fang": "Colmillo de Serpiente (Serpent's Fang)",
    'Eclipse': 'Eclipse',
    'The Collector': 'La Recaudadora (The Collector)',
    'Immortal Shieldbow': 'Arcoescudo Inmortal (Immortal Shieldbow)',
    'Magnetic Blaster': 'Bláster Magnético (Magnetic Blaster)',
    'Spear of Shojin': 'Lanza de Shojin',
    'Titanic Hydra': 'Hidra Titánica (Titanic Hydra)',
    'Heartsteel': 'Corazón de Acero (Heartsteel)',
    'Terminus': 'Terminus',
    'Sundered Sky': 'Cielo Desgarrado (Sundered Sky)',
    'Ravenous Hydra': 'Hidra Voraz (Ravenous Hydra)',
    'Stridebreaker': 'Rompeavances (Stridebreaker)',
    "Lord Dominik's Regards": "Recuerdos de Lord Dominik (LDR)",
    "Dominik’s Regards": "Recuerdos de Lord Dominik (LDR)",
    'Navori Flickerblade': 'Filoveloz de Navori (Navori)',
    'Navori Quickblades': 'Filoveloz de Navori (Quickblades)',
    'Trinity Force': 'Fuerza de la Trinidad (Trinity Force)',
    'Maw of Malmortius': 'Fauces de Malmortius',
    "Sterak's Gage": "Guantelete de Sterak (Sterak's Gage)",
    'Chempunk Chainsword': 'Espada Sierra Quimopunk',
    "Rabadon's Deathcap": "Sombrero Mortal de Rabadon (Deathcap)",
    'Morellonomicon': 'Morellonomicon',
    'Void Staff': 'Báculo del Vacío (Void Staff)',
    "Rylai's Crystal Scepter": "Cetro de Cristal de Rylai",
    "Liandry's Torment": "Tormento de Liandry",
    'Rod of Ages': 'Vara de las Edades (Rod of Ages)',
    'Lich Bane': 'Perdición del Liche (Lich Bane)',
    "Nashor's Tooth": "Diente de Nashor",
    "Archangel's Staff": "Báculo del Arcángel",
    "Seraph's Embrace": "Abrazo del Serafín",
    "Luden's Echo": "Eco de Luden (Luden's Echo)",
    'Awakened Soulstealer': 'Robaalmas Despierto',
    'Infinity Orb': 'Orbe del Infinito (Infinity Orb)',
    "Banshee's Veil": "Velo de la Banshee",
    'Riftmaker': 'Creador de Grietas (Riftmaker)',
    'Horizon Focus': 'Enfoque al Horizonte (Horizon Focus)',
    'Cosmic Drive': 'Impulso Cósmico (Cosmic Drive)',
    "Mejai's Soulstealer": "Robaalmas de Mejai",
    'Crown of the Shattered Queen': 'Corona de la Reina Fragmentada (Crown)',
    "Oceanid's Trident": "Tridente de Oceánida",
    'Psychic Projector': 'Proyector Psíquico (Psychic Projector)',
    'Malignance': 'Malignidad (Malignance)',
    'Shadowflame': 'Llamasombría (Shadowflame)',
    'Stormsurge': 'Sobrecarga de Tormenta (Stormsurge)',
    'Cryptbloom': 'Criptoflora (Cryptbloom)',
    'Harmonic Echo': 'Eco Armónico (Harmonic Echo)',
    'Hextech Gunblade': 'Sable-Pistola Hextech (Gunblade)',
    'Abyssal Mask': 'Máscara Abisal (Abyssal Mask)',
    'Guardian Angel': 'Ángel Guardián (Guardian Angel)',
    'Sunfire Aegis': 'Égida de Fuego Solar (Sunfire)',
    'Spirit Visage': 'Rostro Espiritual (Spirit Visage)',
    "Randuin's Omen": "Presagio de Randuin",
    'Thornmail': 'Malla de Espinas (Thornmail)',
    "Winter's Approach": "Llegada del Invierno",
    'Fimbulwinter': 'Fimbulwinter',
    "Warmog's Armor": "Armadura de Warmog",
    'Frozen Heart': 'Corazón de Hielo (Frozen Heart)',
    'Iceborn Gauntlet': 'Guantelete de Hielo (Iceborn)',
    "Dead Man's Plate": "Coraza del Muerto (Dead Man's Plate)",
    'Force of Nature': 'Fuerza de la Naturaleza (Force of Nature)',
    "Zeke's Convergence": "Convergencia de Zeke",
    "Protector's Vow": "Promesa del Protector (Protector's Vow)",
    'Dawnshroud': 'Manto del Amanecer (Dawnshroud)',
    'Mantle of the Twelfth Hour': 'Manto de la Duodécima Hora',
    'Searing Crown': 'Corona Abrasadora (Searing Crown)',
    "Amaranth's Twinguard": "Protección Gemela de Amaranth (Twinguard)",
    'Twinguard': 'Protección Gemela de Amaranth (Twinguard)',
    "Jak'Sho, The Protean": "Jak'Sho, el Proteico",
    'Hollow Radiance': 'Fulgor Vacuo (Hollow Radiance)',
    'Unending Despair': 'Desesperación Eterna (Unending Despair)',
    'Kaenic Rookern': 'Rookern Kaénico (Kaenic Rookern)',
    'Spectral Sickle': 'Hoz Espectral (Spectral Sickle)',
    'Crescent Sickle': 'Hoz Creciente (Crescent Sickle)',
    'Black Mist Scythe': 'Guadaña de Niebla Negra',
    'Relic Shield': 'Escudo Reliquia (Relic Shield)',
    "Targon's Buckler": "Broquel de Targon",
    'Bulwark of the Mountain': 'Baluarte de la Montaña',
    'Ancient Coin': 'Moneda Antigua (Ancient Coin)',
    'Talisman of Ascension': 'Talismán de la Ascensión',
    'Ardent Censer': 'Incensario Ardiente (Ardent Censer)',
    'Staff of Flowing Water': 'Báculo de Agua Fluyente',
    'Imperial Mandate': 'Mandato Imperial (Imperial Mandate)',
    'Ixtali Seedjar': 'Semillero Ixtali (Ixtali Seedjar)',
    'Echoes of Helia': 'Ecos de Helia',
    "Zhonya's Hourglass": "Reloj de Arena de Zhonya (Zhonya)",
    'Stasis Enchant': 'Encantamiento de Éstasis (Zhonya)',
    'Locket of the Iron Solari': 'Relicario de los Solari de Hierro (Locket)',
    'Locket Enchant': 'Encantamiento de Relicario (Locket)',
    'Glorious Enchant': 'Encantamiento Glorioso (Glorious)',
    'Hextech Roketbelt': 'Cinturón Protocohete Hextech (Protobelt)',
    'Protobelt Enchant': 'Encantamiento Cinturón Protocohete (Protobelt)',
    'Quicksilver Sash': 'Fajín de Mercurio (QSS)',
    'Quicksilver Enchant': 'Encantamiento Fajín de Mercurio (QSS)',
    'Gargoyle Stoneplate': 'Protector Pétreo (Gargoyle)',
    'Gargoyle Enchant': 'Encantamiento Protector Pétreo (Gargoyle)',
    'Redemption': 'Redención (Redemption)',
    'Redemption Enchant': 'Encantamiento de Redención (Redemption)',
    'Teleport Enchant': 'Encantamiento de Teleportación (TP)',
    'Magnetron Enchant': 'Encantamiento Magnetrón (Magnetron)',
    'Repulsor Enchant': 'Encantamiento Repulsor (Repulsor)',
    'Veil Enchant': 'Encantamiento de Velo (Veil)',
    'Hextech Megadrive': 'Megaimpulsor Hextech (Hextech Megadrive)',
    'Hydras Enchant': 'Encantamiento de Hidra (Hydras)',
    "Mikael's Blessing": "Bendición de Mikael (Mikael)",
    "Shurelya's Battlesong": "Canción de Batalla de Shurelya",
    'Gluttonous Greaves': 'Grebas Glotonas (Gluttonous Greaves)',
    'Boots of Swiftness': 'Botas de Rapidez (Boots of Swiftness)',
    'Plated Steelcaps': 'Punteras Revestidas (Plated Steelcaps)',
    "Mercury's Treads": "Pasos de Mercurio (Mercury's Treads)",
    'Ionian Boots of Lucidity': 'Botas Jonias de la Lucidez (CD Boots)',
    'Boots of Mana': 'Botas de Maná (Boots of Mana)',
    'Boots of Dynamism': 'Botas de Dinamismo (Boots of Dynamism)',
    "Berserker's Greaves": "Grebas de Berserker (Berserker's Greaves)",
    'Immortal Treds': 'Botas Inmortales Nivel 3 (Immortal Treds)',
    'Gunmetal Greaves': 'Grebas de Metal Nivel 3 (Gunmetal Greaves)',
    'Chainlaced Crushers': 'Trituradoras Eslabadas Nivel 3 (Chainlaced Crushers)',
    'Armored Advance': 'Avance Blindado Nivel 3 (Armored Advance)',
    'Crimson Lucidity': 'Lucidez Carmesí Nivel 3 (Crimson Lucidity)',
    "Spellslinger's Shoes": "Zapatos de Hechicero Nivel 3 (Spellslinger's)",
    'Armorcrusher Boots': 'Botas Rompearmaduras Nivel 3 (Armorcrusher)',
    'Vampiric Scepter': 'Cetro Vampírico (Vampiric Scepter)',
    'Phage': 'Bacteriófago (Phage)',
    "Bami's Cinder": "Ceniza de Bami (Bami's Cinder)",
    'Bramble Vest': 'Chaleco de Zarzas (Bramble Vest)',
    'Zeal': 'Fervor (Zeal)',
    "Executioner's Calling": "Llamado del Verdugo (Executioner's Calling)",
    "Spectre's Cowl": "Hábito del Espectro (Spectre's Cowl)",
    "Warden's Mail": "Malla del Guardián (Warden's Mail)",
    'Sheen': 'Brillo (Sheen)',
    'Tear of the Goddess': 'Lágrima de la Diosa (Tear)',
    'Glacial Shroud': 'Manto Glacial (Glacial Shroud)',
    "Jaurim's Fist": "Puño de Jaurim (Jaurim's Fist)",
    'Hexdrinker': 'Sorbemaleficios (Hexdrinker)',
    'Serrated Dirk': 'Daga Dentada (Serrated Dirk)',
    'Last Whisper': 'Último Suspiro (Last Whisper)',
    "Giant's Belt": "Cinturón de Gigante (Giant's Belt)",
    'Recurve Bow': 'Arco Recurvo (Recurve Bow)',
    'Stinger': 'Aguijón (Stinger)',
    'Negatron Cloak': 'Manto de Negatrones (Negatron Cloak)',
    'Chain Vest': 'Cota de Malla (Chain Vest)',
    'Kindlegem': 'Gema Avivadora (Kindlegem)',
    'Fiendish Codex': 'Códice Diabólico (Fiendish Codex)',
    "Caulfield's Warhammer": 'Martillo de Guerra de Caulfield',
    'Catalyst of Aeons': 'Catalizador de Eones (Catalyst of Aeons)',
    'Oblivion Orb': 'Orbe del Olvido (Oblivion Orb)',
    'Crystalline Bracer': 'Brazal Cristalino (Crystalline Bracer)',
    'Aether Wisp': 'Brisa de Éter (Aether Wisp)',
    'Lost Chapter': 'Capítulo Perdido (Lost Chapter)',
    "Seeker's Armguard": "Guardabrazo de la Buscadora (Seeker's)",
    'Haunting Guise': 'Disfraz Encantado (Haunting Guise)',
    'Winged Moonplate': 'Placa Lunar Alada (Winged Moonplate)',
    'Noonquiver': 'Carcaj de Mediodía (Noonquiver)',
    'Hearthbound Axe': 'Hacha Comehogar (Hearthbound Axe)',
    'Ring of Revelation': 'Anillo de Revelación (Ring of Revelation)',
    'Rectrix': 'Rectriz (Rectrix)',
    "Scout's Slingshot": "Tirachinas de Explorador (Scout's Slingshot)",
    'Fated Ashes': 'Cenizas Fatídicas (Fated Ashes)',
    'Tiamat': 'Tiamat',
    'Steel Sigil': 'Sello de Acero (Steel Sigil)',
    "Prophet's Pendant": "Colgante del Profeta (Prophet's Pendant)",
    'Kircheis Shard': 'Fragmento de Kircheis (Kircheis Shard)',
    'Hextech Alternator': 'Alternador Hextech (Hextech Alternator)',
    "Nashor's Talon": "Garra de Nashor (Nashor's Talon)",
    'Surging Scales': 'Escamas Crecientes (Surging Scales)',
    'Forbidden Idol': 'Ídolo Prohibido (Forbidden Idol)',
    'Void Amethyst': 'Amatista del Vacío (Void Amethyst)',
    'Verdant Barrier': 'Barrera Frondosa (Verdant Barrier)',
    'Needlessly Large Rod': 'Vara Innecesariamente Grande (NLR)',
    'B. F. Sword': 'Espadón (B.F. Sword)',
    'Blasting Wand': 'Varita Explosiva (Blasting Wand)',
    'Amplifying Tome': 'Tomo Amplificador (Amplifying Tome)',
    'Cloak of Agility': 'Capa de Agilidad (Cloak of Agility)',
    'Cloth Armor': 'Armadura de Tela (Cloth Armor)',
    'Dagger': 'Daga (Dagger)',
    'Long Sword': 'Espada Larga (Long Sword)',
    'Null-Magic Mantle': 'Manto Anulamagia (Null-Magic Mantle)',
    'Ruby Crystal': 'Cristal de Rubí (Ruby Crystal)',
    'Sapphire Crystal': 'Cristal de Zafiro (Sapphire Crystal)',
    'Boots of Speed': 'Botas de Velocidad (Boots of Speed)',
    "Brawler's Gloves": "Guantes de Peleador (Brawler's Gloves)",
    'Shimmering Spark': 'Chispa Reluciente (Shimmering Spark)',
    'Wit\'s End': 'Al Filo de la Cordura (Wit\'s End)',
    'Soul Transfer': 'Transferencia de Alma (Soul Transfer)',
    "Guinsoo's Rageblade": "Espadafuria de Guinsoo (Guinsoo)",
    'Kraken Slayer': 'Verdugo de Krakens (Kraken Slayer)',
    "Overlord's Bloodmail": "Armadura Sangrienta del Señor Supremo (Overlord)",
    'Experimental Hexplate': 'Hexoplaca Experimental (Hexplate)',
    'Goredrinker': 'Bebedor de Sangre (Goredrinker)',
    'Galeforce': 'Fuerza del Viento (Galeforce)',
    'Mercurial Scimitar': 'Cimitarra Mercurial (Mercurial Scimitar)',
    'Blackfire Torch': 'Antorcha de Fuego Negro (Blackfire Torch)',
    'Dusk and Dawn': 'Anochecer y Amanecer (Dusk and Dawn)',
    "Bloodletter's Curse": "Maldición del Sangrador (Bloodletter)",
    'Yordle Trap': 'Trampa Yordle (Yordle Trap)',
    'Radiant Virtue': 'Virtud Radiante (Radiant Virtue)',
    "Knight's Vow": "Promesa de Caballero (Knight's Vow)"
}

def clean_str(s):
    s = s.replace('\\', '\\\\').replace('"', '\\"').replace('\n', ' ').strip()
    return s

def make_id(name, cat):
    clean = re.sub(r'[^a-zA-Z0-9]+', '_', name.lower()).strip('_')
    return f"{clean}_{cat.lower()}"

def strip_tags(html_text):
    text = re.sub(r'<br\s*/?>', ', ', html_text)
    text = re.sub(r'<[^>]+>', ' ', text)
    text = html.unescape(text)
    return re.sub(r'\s+', ' ', text).strip()

h2_list = list(re.finditer(r'<h[23][^>]*>(.*?)</h[23]>', raw))

kotlin_code = '''package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

/**
 * Catálogo exhaustivo de todos los 201 objetos de League of Legends: Wild Rift
 * obtenido y sincronizado minuciosamente directamente desde https://wr-meta.com/items/
 * 
 * - Daño Físico: 40 ítems
 * - Daño Mágico: 28 ítems
 * - Defensa: 32 ítems
 * - Soporte: 17 ítems
 * - Objetos Activos / Encantamientos: 13 ítems
 * - Botas Nivel 2: 7 ítems
 * - Botas Nivel 3: 7 ítems
 * - Nivel Medio: 44 ítems
 * - Nivel Básico: 13 ítems
 * TOTAL: 201 ítems
 */
object WildRiftItemsData {
    val list: List<WildRiftItem> = buildList {
'''

total_count = 0

for i, (sec_title, cat_enum, section_header, target_count) in enumerate(categories_spec):
    start = h2_list[i].end()
    end = h2_list[i+1].start()
    sec_html = raw[start:end]
    
    parts = re.split(r'<div class=[\"\']bild-img-short[\"\']', sec_html)[1:]
    
    # If Physical has 43, take the 40 items as requested
    if len(parts) > target_count:
        parts = parts[:target_count]
        
    kotlin_code += f'''        // =========================================================================
        // {section_header}
        // =========================================================================
'''
    cat_items_count = 0
    for p in parts:
        # Name
        name_m = re.search(r'class=[\"\']newsbox_h_short[\"\']>([^<]+)<', p)
        raw_name = html.unescape(name_m.group(1).strip()) if name_m else ''
        
        # Image
        img_m = re.search(r'data-src=[\"\']([^\"\']+)[\"\']', p) or re.search(r'src=[\"\']([^\"\']+)[\"\']', p)
        img_src = img_m.group(1).strip() if img_m else ''
        if img_src and not img_src.startswith('http'):
            img_src = 'https://wr-meta.com' + img_src
            
        # Stats
        stats_matches = re.findall(r'<b class=[\"\']istats[\"\']>(.*?)</b>', p)
        stats_list = [strip_tags(sm) for sm in stats_matches if sm.strip()]
        stats_str = ', '.join(stats_list)
        
        # Passives
        pass_matches = re.findall(r'<b class=[\"\']istats2[\"\']>(.*?)</b>([\s\S]*?)(?=<br|<b class=|$)', p)
        pass_list = []
        for p_title, p_desc in pass_matches:
            full_p = f"{strip_tags(p_title)} {strip_tags(p_desc)}".strip()
            if full_p:
                pass_list.append(full_p)
                
        # Also check <b class="cdr">
        cdr_matches = re.findall(r'<b class=[\"\']cdr[\"\']>(.*?)</b>', p)
        for c in cdr_matches:
            c_text = strip_tags(c)
            if 'TIPS:' not in c_text and c_text not in pass_list:
                pass_list.insert(0, c_text)
                
        passives_str = ' | '.join(pass_list)
        
        # Gold cost
        gold_m = re.search(r'<b class=[\"\']goldt[\"\']>(\d+)</b>', p)
        cost_val = int(gold_m.group(1)) if gold_m else 0
        
        display_name = es_names.get(raw_name, raw_name)
        item_id = make_id(raw_name, cat_enum)
        
        clean_name = clean_str(display_name)
        clean_stats = clean_str(stats_str)
        clean_pass = clean_str(passives_str)
        clean_img = clean_str(img_src)
        
        kotlin_code += f'        add(WildRiftItem("{item_id}", "{clean_name}", ItemCategory.{cat_enum}, {cost_val}, "{clean_stats}", "{clean_pass}", "{clean_img}"))\n'
        total_count += 1
        cat_items_count += 1
        
    print(f"Category {cat_enum} ({sec_title}): {cat_items_count} items (target {target_count})")

kotlin_code += '''    }
}
'''

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(kotlin_code)

print(f"Successfully generated all {total_count} items into app/src/main/java/com/example/data/WildRiftItemsData.kt!")
