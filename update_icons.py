import re

file_path = 'app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Map ID to new image URL
updates = {
    # Valor
    'bone_plating': 'https://i.postimg.cc/dtQWFLsw/bone-plating.webp',
    'courage_of_the_colossus': 'https://i.postimg.cc/MZmsgySX/courage-of-the-colossus.webp',
    'demolish': 'https://i.postimg.cc/fWfH1xNR/demolish.webp',
    'font_of_life': 'https://i.postimg.cc/FFVPwj4R/font-of-life.webp',
    'nullifying_orb': 'https://i.postimg.cc/3JrLhd8h/nullifying-orb.webp',
    'overgrowth': 'https://i.postimg.cc/zXDdNVz5/overgrowth.webp',
    'perseverance': 'https://i.postimg.cc/MKZswv6W/perseverance.webp',
    'revitalize': 'https://i.postimg.cc/025tvbk9/revitalize.webp',
    'second_wind': 'https://i.postimg.cc/sf4nbpr2/second-wind.webp',
    'unshakeable': 'https://i.postimg.cc/mZydKQsk/unshakeable.webp',
    
    # Precision
    'brutal': 'https://i.postimg.cc/tC7KgsqK/brutal.webp',
    'empowered_attack': 'https://i.postimg.cc/qv3W8WCj/battle-zeal.webp', # wait battle zeal? no wait. I will look for precise IDs later.
}

