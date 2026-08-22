import os
import re

directories = [
    'app/src/main/java/com/example/data/champions'
]

# Common items
boots_ad = ("Botas Blindadas", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3047.png")
boots_ap = ("Botas de Hechicero", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3020.png")
boots_cdr = ("Botas Jonias", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3158.png")

guardian_angel = ("Ángel Guardián", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3026.png")
rabadon = ("Sombrero Mortífero de Rabadon", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3089.png")
void_staff = ("Báculo del Vacío", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3135.png")

anti_heal_ad = ("Recordatorio Mortal", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3033.png")
anti_heal_ap = ("Morellonomicón", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png")
anti_heal_tank = ("Malla de Espinas", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png")

anti_shield_ad = ("Colmillo de Serpiente", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6695.png")

# Parse roles and damage type to decide additional items
def get_additional_items(role, damage_type, current_items):
    new_items = []
    if damage_type == "DamageType.MAGIC":
        if "Botas" not in str(current_items):
            new_items.append(boots_ap)
        if "Rabadon" not in str(current_items):
            new_items.append(rabadon)
        if "Báculo" not in str(current_items) and "Orbe" not in str(current_items):
            new_items.append(void_staff)
    elif role == "LaneRole.ADC":
        if "Botas" not in str(current_items):
            new_items.append(boots_ad)
        new_items.append(("Filo del Infinito", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3031.png"))
        new_items.append(("Recuerdos de Lord Dominik", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3036.png"))
    elif role in ["LaneRole.TOP", "LaneRole.JUNGLE"]:
        if "Botas" not in str(current_items):
            new_items.append(boots_ad)
        new_items.append(guardian_angel)
        new_items.append(("Danza de la Muerte", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6333.png"))
    elif role == "LaneRole.SUPPORT":
        if "Botas" not in str(current_items):
            new_items.append(boots_cdr)
        new_items.append(("Promesa del Caballero", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3109.png"))
        new_items.append(("Convergencia de Zeke", "https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3050.png"))
    else:
        new_items.append(boots_ad)
        new_items.append(guardian_angel)
    return new_items

def generate_swaps(role, damage_type, all_items_names, all_items_icons):
    swaps = []
    
    # Generic Swaps
    if damage_type == "DamageType.MAGIC":
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-1]}", coreItemIcon="{all_items_icons[-1]}", altItem="Morellonomicón", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3165.png", reasonTitle="ANTI-CURACIÓN", reasonDesc="Reduce la curación del enemigo.", againstWho="Soraka, Dr. Mundo, Aatrox")''')
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-2]}", coreItemIcon="{all_items_icons[-2]}", altItem="Despertar del Robaalmas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3041.png", reasonTitle="REDUCCIÓN DE ENFRIAMIENTO", reasonDesc="Más spam de definitivas tras asistencias.", againstWho="Equipos frágiles / Teamfights")''')
    elif role == "LaneRole.ADC":
        dom_idx = -1
        for i, n in enumerate(all_items_names):
            if "Dominik" in n: dom_idx = i
        if dom_idx != -1:
            swaps.append(f'''ItemSwap(coreItem="{all_items_names[dom_idx]}", coreItemIcon="{all_items_icons[dom_idx]}", altItem="Recordatorio Mortal", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3033.png", reasonTitle="PENETRACIÓN Y ANTI-CURACIÓN", reasonDesc="Aplica cortacuras a los enemigos tanque.", againstWho="Soraka, Dr. Mundo, Vladimir")''')
        
        ie_idx = -1
        for i, n in enumerate(all_items_names):
            if "Infinito" in n: ie_idx = i
        if ie_idx != -1:
            swaps.append(f'''ItemSwap(coreItem="{all_items_names[ie_idx]}", coreItemIcon="{all_items_icons[ie_idx]}", altItem="Colmillo de Serpiente", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/6695.png", reasonTitle="ANTI-ESCUDOS", reasonDesc="Reduce enormemente los escudos.", againstWho="Karma, Janna, Sett, Lulu")''')
    elif role == "LaneRole.SUPPORT":
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-1]}", coreItemIcon="{all_items_icons[-1]}", altItem="Pebetero Ardiente", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3504.png", reasonTitle="MEJORA DE ATAQUE", reasonDesc="Otorga velocidad de ataque a tus aliados.", againstWho="Ideal para ADCs de autoataques (Jinx, Vayne)")''')
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-2]}", coreItemIcon="{all_items_icons[-2]}", altItem="Relicario de los Solari", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3190.png", reasonTitle="ESCUDO EN ÁREA", reasonDesc="Protege contra ráfagas de daño de área.", againstWho="Kennen, Katarina, Fiddlesticks")''')
    else: # Bruisers / Tanks / Assassins
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-1]}", coreItemIcon="{all_items_icons[-1]}", altItem="Malla de Espinas", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/3075.png", reasonTitle="CORTACURAS FÍSICO", reasonDesc="Devuelve daño y reduce la curación de atacantes.", againstWho="Maestro Yi, Irelia, Yasuo")''')
        swaps.append(f'''ItemSwap(coreItem="{all_items_names[-2]}", coreItemIcon="{all_items_icons[-2]}", altItem="Fuerza de la Naturaleza", altItemIcon="https://ddragon.leagueoflegends.com/cdn/14.3.1/img/item/4401.png", reasonTitle="RESISTENCIA MÁGICA", reasonDesc="Gran velocidad de movimiento y defensa AP.", againstWho="Evelynn, Teemo, Brand")''')
        
    return swaps

for directory in directories:
    for filename in os.listdir(directory):
        if filename.endswith("Champions.kt"):
            filepath = os.path.join(directory, filename)
            with open(filepath, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Find all champions
            champs = re.findall(r'Champion\((.*?)\s+skills = listOf', content, re.DOTALL)
            
            for champ_data in champs:
                # Extract role
                role_match = re.search(r'primaryRole = (LaneRole\.[A-Z]+)', champ_data)
                role = role_match.group(1) if role_match else "LaneRole.MID"
                
                # Extract damage type
                dmg_match = re.search(r'damageType = (DamageType\.[A-Z_]+)', champ_data)
                dmg = dmg_match.group(1) if dmg_match else "DamageType.PHYSICAL"
                
                # Extract core items
                core_names_match = re.search(r'coreItems = listOf\((.*?)\)', champ_data, re.DOTALL)
                core_icons_match = re.search(r'coreItemsIcons = listOf\((.*?)\)', champ_data, re.DOTALL)
                
                if not core_names_match or not core_icons_match:
                    continue
                
                names_str = core_names_match.group(1)
                icons_str = core_icons_match.group(1)
                
                names = [n.strip().strip('"') for n in names_str.split(',') if n.strip()]
                icons = [i.strip().strip('"') for i in icons_str.split(',') if i.strip()]
                
                if len(names) >= 6:
                    continue # Already processed
                
                add_items = get_additional_items(role, dmg, names)
                
                for n, i in add_items:
                    names.append(n)
                    icons.append(i)
                
                new_names_str = ', '.join([f'"{n}"' for n in names])
                new_icons_str = ', '.join([f'"{i}"' for i in icons])
                
                new_champ_data = champ_data.replace(f'coreItems = listOf({core_names_match.group(1)})', f'coreItems = listOf({new_names_str})')
                new_champ_data = new_champ_data.replace(f'coreItemsIcons = listOf({core_icons_match.group(1)})', f'coreItemsIcons = listOf({new_icons_str})')
                
                # Generate Swaps
                swaps = generate_swaps(role, dmg, names, icons)
                swaps_str = ',\n'.join(swaps)
                
                if 'itemSwaps = listOf' not in new_champ_data:
                    new_champ_data = new_champ_data.replace(
                        'situationalItemsIcons = listOf(',
                        f'itemSwaps = listOf(\n{swaps_str}\n),\n            situationalItemsIcons = listOf('
                    )
                else:
                    new_champ_data = re.sub(r'itemSwaps = listOf\(.*?\)', f'itemSwaps = listOf(\n{swaps_str}\n)', new_champ_data, flags=re.DOTALL)
                
                content = content.replace(champ_data, new_champ_data)
            
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)

