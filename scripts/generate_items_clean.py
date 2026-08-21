import re
import html

with open('/tmp/wr_items.html', 'r', encoding='utf-8') as f:
    raw_html = f.read()

categories_spec = [
    ('Physical Damage', 'PHYSICAL', '1. OBJETOS DE DAÑO FÍSICO (40 ITEMS)', 40),
    ('Magic Damage', 'MAGIC', '2. OBJETOS DE DAÑO MÁGICO (28 ITEMS)', 28),
    ('Defensive Items', 'DEFENSE', '3. OBJETOS DE DEFENSA (32 ITEMS)', 32),
    ('Support Items', 'SUPPORT', '4. OBJETOS DE SOPORTE (17 ITEMS)', 17),
    ('Active Items', 'ACTIVE', '5. OBJETOS ACTIVOS (13 ITEMS)', 13),
    ('Boots Tier 2', 'BOOTS_T2', '6. BOTAS NIVEL 2 (7 ITEMS)', 7),
    ('Boots Tier 3', 'BOOTS_T3', '7. BOTAS NIVEL 3 (7 ITEMS)', 7),
    ('Mid-Tier', 'MID_TIER', '8. OBJETOS DE NIVEL MEDIO (44 ITEMS)', 44),
    ('Basic', 'BASIC', '9. OBJETOS DE NIVEL BÁSICO (13 ITEMS)', 13),
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
    'Navori Flickerblade': 'Filoveloz de Navori (Navori)',
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
    'Stasis Enchant': 'Encantamiento de Éstasis (Zhonya)',
    'Locket Enchant': 'Encantamiento de Relicario (Locket)',
    'Glorious Enchant': 'Encantamiento Glorioso (Glorious)',
    'Protobelt Enchant': 'Encantamiento Cinturón Protocohete (Protobelt)',
    'Quicksilver Enchant': 'Encantamiento Fajín de Mercurio (QSS)',
    'Gargoyle Enchant': 'Encantamiento Protector Pétreo (Gargoyle)',
    'Redemption Enchant': 'Encantamiento de Redención (Redemption)',
    'Teleport Enchant': 'Encantamiento de Teleportación (TP)',
    'Magnetron Enchant': 'Encantamiento Magnetrón (Magnetron)',
    'Repulsor Enchant': 'Encantamiento Repulsor (Repulsor)',
    'Veil Enchant': 'Encantamiento de Velo (Veil)',
    'Hextech Megadrive': 'Megaimpulsor Hextech (Hextech Megadrive)',
    'Hydras Enchant': 'Encantamiento de Hidra (Hydras)',
    'Gluttonous Greaves': 'Grebas Glotonas (Gluttonous Greaves)',
    'Boots of Swiftness': 'Botas de Rapidez (Boots of Swiftness)',
    'Plated Steelcaps': 'Punteras Revestidas (Plated Steelcaps)',
    "Mercury's Treads": "Pasos de Mercurio (Mercury's Treads)",
    'Ionian Boots of Lucidity': 'Botas Jonias de la Lucidez (CD Boots)',
    'Boots of Dynamism': 'Botas de Dinamismo (Boots of Dynamism)',
    "Berserker's Greaves": "Grebas de Berserker (Berserker's Greaves)",
    'Gluttonous Greaves (Tier 3)': 'Grebas Glotonas Nivel 3 (Gluttonous Greaves T3)',
    'Boots of Swiftness (Tier 3)': 'Botas de Rapidez Nivel 3 (Swiftness T3)',
    'Plated Steelcaps (Tier 3)': 'Punteras Revestidas Nivel 3 (Steelcaps T3)',
    "Mercury's Treads (Tier 3)": "Pasos de Mercurio Nivel 3 (Mercury T3)",
    'Ionian Boots of Lucidity (Tier 3)': 'Botas Jonias de la Lucidez Nivel 3 (Ionian T3)',
    'Boots of Dynamism (Tier 3)': 'Botas de Dinamismo Nivel 3 (Dynamism T3)',
    "Berserker's Greaves (Tier 3)": "Grebas de Berserker Nivel 3 (Berserker T3)",
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
    'Quiver': 'Carcaj (Quiver)',
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
    'Faerie Charm': 'Amuleto de las Hadas (Faerie Charm)'
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

# We will locate each `items-list-container` under the respective header
containers = re.findall(r'<div class=[\"\']items-list-container[\"\']>([\s\S]*?)</div>\s*</div>', raw_html)

print(f"Found {len(containers)} items-list-container blocks!")

kotlin_code = '''package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

/**
 * Catálogo exhaustivo de todos los 201 objetos de League of Legends: Wild Rift
 * obtenido y sincronizado directamente desde https://wr-meta.com/items/
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

for idx, (cat_title, cat_enum, section_header, exp_count) in enumerate(categories_spec):
    if idx >= len(containers):
        print(f"Warning: Container index {idx} out of range!")
        break
    
    container_html = containers[idx]
    
    kotlin_code += f'''        // =========================================================================
        // {section_header}
        // =========================================================================
'''
    # Find all items: <div class="item" ... title="..." ...> <a ...> <picture> <img ...> </a> </div>
    items = re.findall(r'<div class=[\"\']item[\"\'][^>]*title=[\"\']([\s\S]*?)[\"\'][^>]*>([\s\S]*?)</div>', container_html)
    
    cat_items_count = 0
    for title_attr, inner_html in items:
        unescaped_title = html.unescape(title_attr)
        
        # Name
        name_match = re.search(r"class=[\'\"]item-tooltip-title[\'\"]>([\s\S]*?)</div>", unescaped_title)
        raw_name = strip_tags(name_match.group(1)) if name_match else ""
        
        # Cost
        cost_match = re.search(r"class=[\'\"]item-tooltip-cost[\'\"]>([\s\S]*?)</div>", unescaped_title)
        cost_str = re.sub(r'[^\d]', '', cost_match.group(1)) if cost_match else "0"
        cost_val = int(cost_str) if cost_str else 0
        
        # Stats
        stats_match = re.search(r"class=[\'\"]item-tooltip-stats[\'\"]>([\s\S]*?)</div>", unescaped_title)
        stats_str = strip_tags(stats_match.group(1)) if stats_match else ""
        
        # Passives
        pass_match = re.search(r"class=[\'\"]item-tooltip-passives[\'\"]>([\s\S]*?)</div>\s*</div>", unescaped_title)
        passives_str = strip_tags(pass_match.group(1)) if pass_match else ""
        
        # Image
        img_match = re.search(r'data-src=[\"\']([^\"\']+)[\"\']', inner_html) or re.search(r'src=[\"\']([^\"\']+)[\"\']', inner_html)
        img_src = img_match.group(1) if img_match else ""
        if img_src and not img_src.startswith('http'):
            img_src = "https://wr-meta.com" + img_src
            
        alt_match = re.search(r'alt=[\"\']([^\"\']+)[\"\']', inner_html)
        if not raw_name and alt_match:
            raw_name = alt_match.group(1)
            
        display_name = es_names.get(raw_name, raw_name)
        item_id = make_id(raw_name, cat_enum)
        
        clean_name = clean_str(display_name)
        clean_stats = clean_str(stats_str)
        clean_pass = clean_str(passives_str)
        clean_img = clean_str(img_src)
        
        kotlin_code += f'        add(WildRiftItem("{item_id}", "{clean_name}", ItemCategory.{cat_enum}, {cost_val}, "{clean_stats}", "{clean_pass}", "{clean_img}"))\n'
        total_count += 1
        cat_items_count += 1
        
    print(f"Category {cat_enum}: {cat_items_count} items (expected {exp_count})")

kotlin_code += '''    }
}
'''

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(kotlin_code)

print(f"TOTAL: {total_count} items written to app/src/main/java/com/example/data/WildRiftItemsData.kt")
