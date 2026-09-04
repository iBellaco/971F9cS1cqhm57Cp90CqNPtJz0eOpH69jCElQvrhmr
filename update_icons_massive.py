import re

file_path = 'app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

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
    'unflinching': 'https://i.postimg.cc/mZydKQsk/unshakeable.webp',
    
    # Precision
    'brutal': 'https://i.postimg.cc/tC7KgsqK/brutal.webp',
    'triumph': 'https://i.postimg.cc/52CDwDFP/triumph.webp',
    'battle_fervor': 'https://i.postimg.cc/qv3W8WCj/battle-zeal.webp',
    'last_stand': 'https://i.postimg.cc/mgHKQKFV/last-stand.webp',
    'cut_down': 'https://i.postimg.cc/QdTvpvWY/cut-down.png',
    'coup_de_grace': 'https://i.postimg.cc/L81GzGZw/coup-de-grace.webp',
    'legend_alacrity': 'https://i.postimg.cc/t4x8h8Vf/legend-alacrity.webp',
    'legend_tenacity': 'https://i.postimg.cc/8z6xhxrx/legend-tenacity.webp',
    'legend_bloodline': 'https://i.postimg.cc/yNCtnnFj/legend-bloodline.webp',

    # Domination
    'cheap_shot': 'https://i.postimg.cc/pXj45QJZ/cheap-shot.webp',
    'sudden_impact': 'https://i.postimg.cc/zXh6HwFd/sudden-impact.webp',
    'empowered_attack': 'https://i.postimg.cc/WbZCqmwX/empowered-attack.webp',
    'chain_assault': 'https://i.postimg.cc/wTNZR5Qw/chain-assault.webp',
    'tyrant': 'https://i.postimg.cc/J4XvH35K/tyrant.webp',
    'hubris': 'https://i.postimg.cc/5NzTQ5S7/hubris.webp',
    'eyeball_collection': 'https://i.postimg.cc/X75mBKk1/eyeball-collector.webp',
    'ingenious_hunter': 'https://i.postimg.cc/02SFKG0H/ingenious-hunter.webp',
    'relentless_hunter': 'https://i.postimg.cc/PrD9vbQR/relentless-hunter.webp',
    'zombie_ward': 'https://i.postimg.cc/28WMLd7t/zombie-ward.webp',

    # Sorcery
    'manaflow_band': 'https://i.postimg.cc/Hkn8TC82/manaflow-band.webp',
    'axiomatic_arcologist': 'https://i.postimg.cc/3xN03H02/axiom-arcanist.webp',
    'transcendence': 'https://i.postimg.cc/qvq6pd6c/transcendence.webp',
    'celerity': 'https://i.postimg.cc/kgGR7dRw/celerity.webp',
    'absolute_focus': 'https://i.postimg.cc/y8x37z3j/absolute-focus.webp',
    'nimbus_cloak': 'https://i.postimg.cc/kgGR7dRj/nimbus-cloak.webp',
    'scorch': 'https://i.postimg.cc/mgk1TG1m/scorch.webp',
    'gathering_storm': 'https://i.postimg.cc/y8x37z3r/gathering-storm.webp',
    'botanist': 'https://i.postimg.cc/GmtTb1Tj/botanist.webp',
    'hextech_flashtraption': 'https://i.postimg.cc/7ZhJqyJM/hextech-flashtraption.webp',
    'ixtali_seedjar': 'https://i.postimg.cc/8zcfTVf0/ixtali-seedjar.webp',

    # Keystones
    'dark_harvest': 'https://i.postimg.cc/VvC5grkT/dark-harvest.png',
    'electrocute': 'https://i.postimg.cc/zXhRSMyc/electrocutar-hanu.png',
    'lethal_tempo': 'https://i.postimg.cc/kGbDs65r/compas-letal-hanu.png',
    'fleet_footwork': 'https://i.postimg.cc/Znd0HBqt/pies-veloces-hanu.png',
    'conqueror': 'https://i.postimg.cc/HnyjzcL1/conqueror-hanu.png',
    'grasp_undying': 'https://i.postimg.cc/hvdhszGq/desgarrador-hanu.png',
    'guardian': 'https://i.postimg.cc/3NvW14wh/guardian.webp',
    'first_strike': 'https://i.postimg.cc/hhRv5DXw/8369.png',
    'aery': 'https://i.postimg.cc/prFyChdH/aery-hanu.png',
    'arcane_comet': 'https://i.postimg.cc/t7GJMqsm/cometa-hanu-bbwr.png',
    'phase_rush': 'https://i.postimg.cc/Hj1nhYrN/324314341234123-hanu-wr-bb.png',
    'glacial_augment': 'https://i.postimg.cc/qgHq5JN5/ice-overlord.webp',
    
    # Spells
    'ghost': 'https://i.postimg.cc/RhPfTCnS/1691694862-ghost.webp',
    'heal': 'https://i.postimg.cc/d3Wd9QT3/1691695008-heal.webp',
    'barrier': 'https://i.postimg.cc/2yHvxjBV/1691695152-barrier.webp',
    'exhaust': 'https://i.postimg.cc/gjMRKc6r/1691695333-exhaust.webp',
    'cleanse': 'https://i.postimg.cc/kGj8yMt5/1735511112-cleanse.webp',
    'flash': 'https://i.postimg.cc/6qHRh6Gt/1691694210-flash.webp',
    'ignite': 'https://i.postimg.cc/4y8t14hN/1691695236-ignite.webp',
    'smite': 'https://i.postimg.cc/NFNTxGrg/1691695722-chilling-smite.png', # Using chilling smite for smite base
}

for item_id, new_url in updates.items():
    # Regex to find the block for the specific id and replace its iconUrl
    # We use a pattern that matches id = "item_id", followed by any chars (lazy) until iconUrl = "..."
    # Then we capture everything around it to substitute.
    pattern = re.compile(rf'(id\s*=\s*"{item_id}".*?iconUrl\s*=\s*")[^"]*(")', re.DOTALL)
    content = pattern.sub(rf'\g<1>{new_url}\g<2>', content)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)

