package com.example.data

import com.example.model.ItemCategory
import com.example.model.WildRiftItem

/**
 * Catálogo exhaustivo de todos los 214 objetos de League of Legends: Wild Rift
 * obtenido y sincronizado minuciosamente directamente desde https://wr-meta.com/items/
 */
object WildRiftItemsData {
    val list: List<WildRiftItem> = buildList {
        add(WildRiftItem(
            id = "bloodthirster_physical",
            name = "Sanguinaria",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Attack Damage • +250 Max Health • +25% Critical Rate",
            passive = "Bloodthirster\nIncreases Physical Vamp\n+55 Attack Damage\n+250 Max Health\n+25% Critical Rate\nBloody: +8% Physical Vamp, Attacks that Critically Strike gain an additional 4% Physical Vamp.\n3000\nBloodthirster TIPS: This item is a staple for auto-attack carries, providing a strong boost to damage, critical chance, and attack speed. Its passive converts critical strikes into enhanced lifesteal, making extended trades and sustained fights lean in your favor. When you fall into critical health, a lifesaving shield activates, granting extra defenses and a chance to survive clutch moments. Ideal for marksmen and auto-attack fighters who need both high DPS and reliable sustain.",
            iconUrl = "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp"
        ))
        add(WildRiftItem(
            id = "guardian_angel_defense",
            name = "Ángel Guardián",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+45 Attack Damage • +40 Armor",
            passive = "Guardian Angel\nRevives at death\n+45 Attack Damage\n+40 Armor\nResurrect: Upon taking lethal damage, restores 50% Health and 100% Mana after 4 seconds of stasis. (180s Cooldown)\n3200\nGuardian Angel TIPS: This item is perfect for champions who need a second chance in teamfights. It is especially effective against champions with high burst damage, such as Zed, Syndra, or Zoe, as well as against strong diving champions like Camille, Kha'Zix, or Lee Sin. The Resurrection effect allows you to return to the fight after taking lethal damage, restoring health and mana, giving you a chance to continue fighting and assist your team even in critical moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp"
        ))
        add(WildRiftItem(
            id = "magnetic_blaster_physical",
            name = "Bláster Magnético",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+30 Attack Damage • +25% Critical Rate • +35% Attack Speed",
            passive = "Magnetic Blaster\nIncreases attack range and damage\n+30 Attack Damage\n+25% Critical Rate\n+35% Attack Speed\nFervor:  +5% Move Speed.\nEnergized: Moving and attacking will generate an Energized Attack.\nPower Blitz: Energized Attacks gain 100 range (50 range for melee attacks), deal 40-100 bonus magic damage, and grant 60 Movement Speed for 0.75 seconds. This damage bounces to 5 nearby enemies and can Critically Strike.\n(Deals 50-80% bonus damage against minions.)\n3000\nMagnetic Blaster TIPS: This item extends your attack range and adds hybrid damage by empowering every fourth attack with a magic burst that can bounce to multiple targets and critically strike. Moving and attacking charges the empowered strike, and upon activation you gain a hefty movement speed boost, aiding both chase and retreat.  — Perfect for marksmen and auto‑attack bruisers who want more reach, multi‑target damage, and extra mobility in skirmishes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300773_magnetic-blaster.webp"
        ))
        add(WildRiftItem(
            id = "blade_of_the_ruined_king_physical",
            name = "Hoja del Rey Arruinado",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+40 Attack Damage • +35% Attack Speed",
            passive = "Blade of the Ruined King\nAttacks deal bonus damage\n+40 Attack Damage\n+35% Attack Speed\nThirst:+10% Omni Vamp.\nRuined Strikes: Attacks deal bonus physical damage equal to 7% of the enemy's current Health on-hit. (Melee attacks deal 10%). Minion damage: 15. Max damage vs monsters: 60.\nDrain: Hitting a champion with 3 attacks or abilities deals 30-100 bonus magic damage and steals 25% of their Move Speed for 2 seconds. (60s Cooldown)\n3000\nBlade of the Ruined King TIPS: This item is a powerful tool for shredding enemy health and staying alive in extended fights. It grants attack power and attack speed, turning your basic hits into sustained damage that scales with the target’s current health — ideal against tanks and high-HP builds. Its active grants a strong slow and steals movement speed from the target, helping you chase or lock down priority targets. The lifesteal-like sustain it provides makes it a solid pick for champions who want to outlast opponents in prolonged exchanges.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300221_blade-of-the-ruined-king.webp"
        ))
        add(WildRiftItem(
            id = "runaan_s_hurricane_magic",
            name = "Huracán de Runaan",
            category = "Daño Físico",
            goldCost = 2900,
            stats = "+35% Attack Speed • +25% Critical Rate",
            passive = "Runaan's Hurricane\nRanged Attacks hit 3 targets\n+35% Attack Speed\n+25% Critical Rate\nWind's Fury: Attacks strike 2 additional nearby enemies, each dealing 55%. These strikes can Critically Strike and trigger on-hit effects.\nWind Blade: Attacks deal 15 bonus physical damage on-hit against targets.\nThis item cannot only be used by melee champions.\n2900\nRunaan's Hurricane TIPS: This item turns your basic attacks into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. It greatly improves waveclear, contributes strong multi-target damage in teamfights, and makes trading on single targets much riskier for the opponent due to distributed damage. Perfect for marksmen and on-hit builds who value sustained attack cadence and AOE presence. Melee users can use it too, but it shines brightest on ranged auto-attackers.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300839_yordle-runaans-hurricane.webp"
        ))
        add(WildRiftItem(
            id = "youmuu_s_ghostblade_physical",
            name = "Espada Fantasma de Youmuu",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Attack Damage • +15 Ability Haste",
            passive = "Youmuu's Ghostblade\nIncreases Movement Speed\n+55 Attack Damage\n+15 Ability Haste\nSlice:  +15 Armor Penetration.\nMomentum: Moving builds Momentum, granting up to 50 Move Speed at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nSpectral Haste: Attacking with max Momentum grants 25% Attack Speed for 4 seconds.\n3000\nYoumuu's Ghostblade TIPS: This item is perfect for assassins and champions who need to get in and out of fights quickly. It provides bonuses to attack damage and ability haste, along with armor penetration, helping you deal more damage to enemies. The Momentum effect increases your movement speed and armor penetration as you move, giving you an advantage in mobility during fights. When Momentum is fully stacked, attacks grant bonus attack speed, making the item a great choice for champions who need to quickly deal damage and escape from fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300869_youmuus-ghostblade.webp"
        ))
        add(WildRiftItem(
            id = "duskblade_of_draktharr_physical",
            name = "Filo Fantasma de Draktharr",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Attack Damage • +10 Ability Haste",
            passive = "Duskblade of Draktharr\nAttacks deal bonus damage\n+55 Attack Damage\n+10 Ability Haste\nRazor:  +18 Armor Penetration.\nNightstalker: The first attack against a champion deals 60-160 bonus physical damage and slows them by 99% for 0.35s (10s cooldown). Champion takedowns refresh cooldown.\n3000\nDuskblade of Draktharr TIPS: This item is a pure assassin tool: it boosts your armor penetration and makes your first strike on an enemy deal a deadly burst with a brief slow. Securing a takedown grants stealth and a fast reposition window, letting you escape or continue hunting—perfect for single-target picks. It shines on mobile killers who focus on quick executions and roams; it’s less effective against bulky, high-HP frontliners.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300931_yordle-duskblade-of-draktharr.webp"
        ))
        add(WildRiftItem(
            id = "infinity_edge_physical",
            name = "Filo del Infinito",
            category = "Daño Físico",
            goldCost = 3400,
            stats = "+65 Attack Damage • +25% Critical Rate",
            passive = "Infinity Edge\nIncreases Critical Strike Damage\n+65 Attack Damage\n+25% Critical Rate\nInfinity: Critical Strikes deal 205% damage instead of 175%.\nLimit Break: When your total gain from items exceeds 100%, every 1% excess grants 0.6 bonus Critical Damage.\n3400\nInfinity Edge TIPS: This item greatly amplifies your auto‑attack power by boosting the base damage of critical strikes and granting extra critical damage when your total crit chance from items is capped.  — Perfect for marksmen and auto‑attack fighters looking to maximize their crit damage and squeeze even more value out of high crit chance builds.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300938_infinity-edge.webp"
        ))
        add(WildRiftItem(
            id = "mortal_reminder_physical",
            name = "Recordatorio Mortal",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+25 Attack Damage • +25% Critical Rate • +15% Attack Speed",
            passive = "Mortal Reminder\nArmor Penetration (%) and reduced enemy healing\n+25 Attack Damage\n+25% Critical Rate\n+15% Attack Speed\nLast Whisper:  +30% Armor Penetration. Attacks that Critically Strike gain an additional  6% Armor Penetration.\nSepsis: Dealing physical damage to enemy champions applies 50% Grievous Wounds for 3 seconds.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n3300\nMortal Reminder TIPS: This item is perfect for auto‑attackers who need to shred through armor and cut down enemy healing. It boosts your penetration to deal more damage against tanky targets and applies grievous wounds on hit, reducing all healing and regen effects.  — Ideal against tanks and high‑heal champions, and for marksmen and auto‑attack fighters who need to pierce defenses and curb enemy sustain.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301062_mortal-reminder.webp"
        ))
        add(WildRiftItem(
            id = "black_cleaver_physical",
            name = "Black Cleaver",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+400 Max Health • +40 Attack Damage • +20 Ability Haste",
            passive = "Black Cleaver\nPhysical Damage reduces Armor\n+400 Max Health\n+40 Attack Damage\n+20 Ability Haste\nSunder: Dealing physical damage to a champion reduces their Armor by 6% for 6 seconds, stacking 5 times for 30% reduction.\nRage: Gain 20 Movement Speed when you deal physical damage. When moving toward enemy champions with 5 Sunder stacks, gain 40 Move Speed. Ranged champions gain halved values.\n3000\nBlack Cleaver TIPS: This item is perfect for champions who deal physical damage and need to fight tanky opponents. The \"Sunder\" effect reduces the enemy’s armor when dealing physical damage, making it effective at shredding through tanks with high resistance. The movement speed bonus, activated when dealing physical damage, helps maintain mobility on the battlefield. It's a great choice for fighters and junglers who can quickly apply multiple stacks of the item’s passive, reducing the enemy's armor and increasing the overall damage dealt.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301421_black-cleaver.webp"
        ))
        add(WildRiftItem(
            id = "manamune_physical",
            name = "Manamune",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25 Attack Damage • +300 Max Mana • +20 Ability Haste",
            passive = "Manamune\nConverts Mana to Attack Damage\n+25 Attack Damage\n+300 Max Mana\n+20 Ability Haste\nAwe: Grants Attack Damage equal to 1.5%  of max Mana and refunds 15%  of all Mana spent.\nMana Charge: Increases max Mana by 18 every attack or when Mana is spent. Caps at 700, bonus Mana, transforming Manamune into Muramana. Triggers up to 3 times every 10 seconds. You may only carry one Tear of the Goddess item at a time.\n2700\nManamune TIPS: This item is perfect for AD champions who rely on mana and abilities to deal damage. It provides bonuses to attack damage, maximum mana, and ability haste, allowing you to use your abilities effectively. The \"Mana Charge\" effect increases your mana with each attack or mana usage, which significantly boosts your damage once the item is completed. After reaching the maximum bonus mana, the item transforms into Muramana, greatly enhancing your attacks. It’s an excellent choice for champions who use mana to activate their abilities, such as Ezreal or Twitch.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301442_manamune.webp"
        ))
        add(WildRiftItem(
            id = "muramana_physical",
            name = "Muramana",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25 Attack Damage • +1000 Max Mana • +20 Ability Haste",
            passive = "Muramana\nConverts Mana to Attack Damage\n+25 Attack Damage\n+1000 Max Mana\n+20 Ability Haste\nAwe: Grants Attack Damage equal to 2%  of max Mana and refunds 15%  of all Mana spent.\nShock: When you hit an enemy champion with auto attack, it drains 2.5% of current Mana and deals bonus physical damage equal to the amount consumed. When dealing ability damage to enemy champion drains 4% of current mana and deals an additional physical damage equal to the amount consumed + 6%. This effect only triggers when remaining mana is above 20%. A single attack or ability will only trigger this effect once on the same champion.\n2700\nMuramana TIPS: This item is perfect for AD champions who rely on mana to activate their abilities and auto-attacks. It provides bonuses to attack damage, maximum mana, and ability haste, significantly enhancing your attacks and abilities. The \"Shock\" effect allows you to deal bonus physical damage by consuming mana with each auto-attack or ability. This is especially useful for champions like Ezreal, who actively use mana to deal damage. The effect doesn't trigger if your mana is below 20%, so it's important to manage your resources carefully. Overall, this item provides a huge power spike once completed and is ideal for champions who rely on mana and physical attacks.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301487_muramana.webp"
        ))
        add(WildRiftItem(
            id = "trinity_force_physical",
            name = "Fuerza de la Trinidad",
            category = "Daño Físico",
            goldCost = 3333,
            stats = "+333 Max Health • +30 Attack Damage • +30% Attack Speed • +20 Ability Haste",
            passive = "Trinity Force\nWell-Rounded\n+333 Max Health\n+30 Attack Damage\n+30% Attack Speed\n+20 Ability Haste\nFervor:  +5% Move Speed.\nSpellblade: Using an ability causes the next attack used within 10 seconds to deal bonus physical damage equal to 200% base AD(1.5s Cooldown). Damage is reduced vs structures.\nValor: Attacks grant 20 Move Speed for 2 seconds. Bonuses do not stack. Ranged champions gain halved values.\n3333\nTrinity Force TIPS: This item provides a well-rounded set of stats and enhances damage through the combination of abilities and basic attacks. It is ideal for champions who frequently weave abilities between attacks and rely on consistent trading. It works best on fighters and some mobile carries where versatility, speed, and burst damage in short skirmishes are important.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301537_trinity-force.webp"
        ))
        add(WildRiftItem(
            id = "maw_of_malmortius_physical",
            name = "Fauces de Malmortius",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+55 Attack Damage • +45 Magic Resistance • +10 Ability Haste",
            passive = "Maw of Malmortius\nConverts damage from attacks into a magic shield\n+55 Attack Damage\n+45 Magic Resistance\n+10 Ability Haste\nLifeline: Upon taking Magic Damage that would reduce your Health to below 35%, gain +10% Omni Vamp. until the end of combat and a magic shield that absorbs 220-530 Magic Damage for 3s. (70s Cooldown)\n3000\nMaw of Malmortius TIPS: This item provides a mix of damage, magic resistance and ability haste, but its core value is the anti-burst passive: when hit by a dangerous burst of magic damage, you instantly gain a strong protective shield and a temporary lifesteal/omnivamp effect that lasts through the fight. It lets you survive big AP detonations and re-enter skirmishes quickly.  Best for assassins, bruisers and AD champions who need to survive enemy magic burst and power through fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301577_maw-of-malmortius.webp"
        ))
        add(WildRiftItem(
            id = "death_s_dance_defense",
            name = "Danza de la Muerte",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+35 Attack Damage • +40 Armor • +15 Ability Haste",
            passive = "Death's Dance\nDelays damage taken\n+35 Attack Damage\n+40 Armor\n+15 Ability Haste\nDefy: Champion takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum health over 2 seconds.\nCauterize: 27% of all physical damage and magic damage received (12% for ranged champions) is dealt to you over 3 seconds as true damage instead.\n3100\nDeath's Dance TIPS: This item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with armor and ability haste, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301539_yordle-deaths-dance.webp"
        ))
        add(WildRiftItem(
            id = "phantom_dancer_physical",
            name = "Bailarín Espectral",
            category = "Daño Físico",
            goldCost = 2900,
            stats = "+20 Attack Damage • +25% Critical Rate • +40% Attack Speed",
            passive = "Phantom Dancer\nMovement speed and attack speed\n+20 Attack Damage\n+25% Critical Rate\n+40% Attack Speed\nSwift-Footed:  +5% Movement Speed.\nSpectral Waltz: One hit, your attacks grants 25% Attack Speed and +7% Movement Speedfor 6s. Bonuses do not stack. (10s cooldown reduced by 1s when your attack hits an enemy.)\n2900\nPhantom Dancer TIPS: This item grants a strong boost to attack speed and mobility, turning your basic attacks into a tool for controlling the tempo of fights. Hits on enemy champions temporarily increase your attack and movement speed, and frequent hits reduce the effect’s downtime — perfect for kiting, chasing, and extended duels. Ideal for marksmen and auto-attack bruisers who need mobility and consistent DPS.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302001_phantom-dancer.webp"
        ))
        add(WildRiftItem(
            id = "nashor_s_tooth_magic",
            name = "Diente de Nashor",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+45% Attack Speed • +20 Ability Haste",
            passive = "Nashor's Tooth\nAttacks deal bonus damage\n+45% Attack Speed\n+20 Ability Haste\nMagic Fang: Obtain 25 Attack Damage or 50 Ability Power (Adaptive).\nGnaw: Attacks deal adaptive damage (15 + 20% bonus+ 30% bonus) on hit.\n2800\nNashor's Tooth TIPS: This item is perfect for champions who blend auto-attacks with magic damage. It provides a hefty boost to attack speed and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either bonus attack damage or ability power, adding flexibility.  With each auto-attack, “Gnaw” deals adaptive magic damage on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for champions like Teemo, Kayle, and Jax, who rely on sustained auto-attacks supported by magic damage and need frequent ability usage to maximize DPS in extended engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302010_nashors-tooth.webp"
        ))
        add(WildRiftItem(
            id = "wit_s_end_physical",
            name = "Wit's End",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+45% Attack Speed • +45 Magic Resistance",
            passive = "Wit's End\nBasic Attack deals Bonus Damage\n+45% Attack Speed\n+45 Magic Resistance\nAt Wit's End: Basic attacks deal 10-55 bonus magic damage. While below 50%Health, dealing damage to an enemy champion heals you for (Melee 100% / Range 66%) of this effect's post-mitigation damage.\n2800\nWit's End TIPS: This item is perfect for champions who want to deal sustained damage and have some defensive stats against magic damage threats. It provides bonuses to attack speed and magic resistance, as well as adding magic damage to your auto-attacks, making it effective against magic-based threats. The \"While below 50% Health\" effect heals you when dealing damage to an enemy champion, increasing survivability in fights. This item is especially useful for champions like Vayne, Irelia, or Master Yi, who can benefit from its on-hit magic damage and health restoration effect.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302068_wits-end.webp"
        ))
        add(WildRiftItem(
            id = "essence_reaver_physical",
            name = "Segador de Esencia",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+40 Attack Damage • +25% Critical Rate • +20 Ability Haste",
            passive = "Essence Reaver\nAttacks grant Mana Regen, and damage amplification\n+40 Attack Damage\n+25% Critical Rate\n+20 Ability Haste\nSpellblade: Casting an ability generates a Spellblade charge (max 2 charges) that lasts up to 10 second(s). Hitting an enemy with an attack consumes a charge, dealing 70 bonusas physical damage and granting 40 Movement Speed for 2 second(s). This bonus damage can Critically Strike. Each ability generates only one charge per 2 second(s).\nMana Siphon: Attacks restore 3% missingMana on-hit.\n3000\nEssence Reaver TIPS: This item is perfect for hybrid auto‑attack champions who need mana sustain and enhanced damage after casting abilities. It boosts ability haste, and after using a skill, your next basic attack hits harder and grants a burst of movement speed. Additionally, auto‑attacks restore a portion of your missing mana, keeping you in fights longer.  — A great pick for marksmen and fighters who weave spells into their auto‑attack rotations for maximum DPS and mana sustainability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302228_essence-reaver.webp"
        ))
        add(WildRiftItem(
            id = "serylda_s_grudge_physical",
            name = "Rencor de Serylda",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+40 Attack Damage • +15 Ability Haste",
            passive = "Serylda’s Grudge\nArmor Penetration (%) and apply slows\n+40 Attack Damage\n+15 Ability Haste\nLast Whisper: Gain  +33% Armor Penetration.\nIcy: Damaging active abilities and empowered attacks slow enemies by 30% for 1 second.\nFrostbite: Apply Frostbite to enemies slowed by Icy for 6s. At 3 Frostbite stacks, all stacks are consumed to apply bleed, dealing (5 + 1-15 () + 15% bonus) physical damage over 2s. Also applies 50% Grievous Wounds for 3s. (5s cooldown per target)\n3300\nSerylda’s Grudge TIPS: This item blends heavy armor penetration with crowd control: your active abilities and empowered hits slow targets, and repeated slows trigger a bleed that also applies grievous wounds. Perfect for champions who need to kite, execute priority targets, and curb their healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302257_yordle-seryldas-grudge.webp"
        ))
        add(WildRiftItem(
            id = "navori_quickblades_physical",
            name = "Filoveloz de Navori",
            category = "Daño Físico",
            goldCost = 2700,
            stats = "+25% Critical Rate • +45% Attack Speed • +5% Move Speed",
            passive = "Navori Quickblades\nAbilities grant damage amplification and cooldown reduction\n+25% Critical Rate\n+45% Attack Speed\n+5% Move Speed\nDeft Strikes: Attacks reduce the remaining cooldowns of your basic abilities by 15%.\n2700\nNavori Quickblades TIPS: This item is perfect for auto‑attackers who aim to amplify their ability damage and reduce cooldowns. Critical strikes accelerate your non‑ultimate abilities, while your skills hit harder based on your crit chance.  — A top pick for marksmen and assassins who weave auto‑attacks with spells to swiftly eliminate targets and maintain combat momentum.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302298_navori-quickblades.webp"
        ))
        add(WildRiftItem(
            id = "edge_of_night_physical",
            name = "Edge of Night",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+250 Max Health • +50 Attack Damage",
            passive = "Edge of Night\nBlocks an enemy ability\n+250 Max Health\n+50 Attack Damage\nGouge:  +8 Armor Penetration.\nAnnul: Grants a spell shield that blocks the next hostile ability. This spell shield refreshes upon leaving combat with enemy champions. (35 second cooldown)\n3000\nEdge of Night TIPS: This item is perfect for champions who need a mix of offense and defense. It provides bonuses to maximum health and attack damage, along with increased armor penetration to help you shred through enemy defenses. The \"Annul\" effect grants you a spell shield, blocking the next enemy ability. This spell shield refreshes when leaving combat with enemy champions, making the item a great choice for champions who need protection from crowd control and to engage in fights, such as Ashe, Lux, or Morgana.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302335_edge-of-night.webp"
        ))
        add(WildRiftItem(
            id = "divine_sunderer_physical",
            name = "Desgarrador Divino",
            category = "Daño Físico",
            goldCost = 3400,
            stats = "+425 Max Health • +25 Attack Damage • +25 Ability Haste",
            passive = "Divine Sunderer\nAnti-Health attacks\n+425 Max Health\n+25 Attack Damage\n+25 Ability Haste\nSpellblade: After using an ability, your next attack within 10 seconds deals (10% melee / 7% ranged) of target’s maximum health as bonus physical damage. If the target is a champion, heal for (6% melee / 2.5% ranged) of the target's maximum health. (1.5s Cooldown) Damage is reduced vs structure.\n3400\nDivine Sunderer TIPS: This item offers a strong blend of survivability and damage: after using an ability your next basic attack is empowered to deal bonus damage based on the target’s max health, and it heals you when used on enemy champions. Perfect for fighter-bruisiers and solo laners who weave abilities into autos and need sustain versus tanks and duelists.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753303444_divine-sunderer.webp"
        ))
        add(WildRiftItem(
            id = "serpent_s_fang_physical",
            name = "Serpent's Fang",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+50 Attack Damage • +10 Ability Haste",
            passive = "Serpent's Fang\nAnti-Shielding\n+50 Attack Damage\n+10 Ability Haste\nStab:  +15 Armor Penetration.\nShield Reaver: Dealing damage to an enemy champion reduces any shields they gain for 3s. Melee champions apply (10% of bonus AD + 40)% shield reduction, capped at 60%; while ranged champions apply (10% of bonus AD + 25)% shield reduction, capped at 45%. When you damage an enemy who is unaffected by Shield Reaver, all shields on them are reduced by the same values.\n2800\nSerpent's Fang TIPS: This item is perfect for assassins and champions who face enemies with a lot of shields. It provides bonuses to attack damage and ability haste, along with increased armor penetration, making it effective against well-protected enemies. The \"Shield Reaver\" effect allows you to reduce the effectiveness of shields gained by enemy champions, depending on your bonus attack damage, helping to quickly break through shields. This item is especially useful against champions who rely on shields for defense, such as Lux, Braum, and others.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753303456_serpents-fang.webp"
        ))
        add(WildRiftItem(
            id = "chempunk_chainsword_physical",
            name = "Chempunk Chainsword",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+400 Max Health • +45 Attack Damage • +15 Ability Haste",
            passive = "Chempunk Chainsword\nReduces enemy healing\n+400 Max Health\n+45 Attack Damage\n+15 Ability Haste\nPunishment: Dealing physical damage to enemy champions applies 50% Grievous Wounds for 3 seconds.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n2800\nChempunk Chainsword TIPS: This item is perfect for champions who face enemies with high sustain, such as Dr. Mundo, Soraka, and Yuumi, who have significant healing abilities. The \"Punishment\" effect applies Grievous Wounds, reducing the effectiveness of healing and regeneration by 50%, making this item effective against champions who rely on healing. It’s also useful against champions who rely on lifesteal, such as Aatrox, Darius, and Fiora, as it helps reduce the effectiveness of their healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304959_chempunk-chainsword.webp"
        ))
        add(WildRiftItem(
            id = "the_collector_physical",
            name = "La Recaudadora",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+45 Attack Damage • +25% Critical Rate",
            passive = "The Collector\nExecute low health champions\n+45 Attack Damage\n+25% Critical Rate\nKiller:  +10 Armor Penetration.\nDeath and Taxes: Dealing damage that would leave an enemy champion below (4% + 2% Critical Rate) of their max Health executes them, permanently increases the max Health percentage execution threshold by 0.1%, and grants 25 bonus gold.\nLimited to 1 The Collector.\n3000\nThe Collector TIPS: This item turns your auto‑attacks into a finisher: it boosts your penetration for shredding armor and automatically executes low‑health enemies, rewarding you with extra gold.  — Perfect for marksmen and assassins who need reliable executes on vulnerable targets while snowballing their gold income.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304861_the-collector.webp"
        ))
        add(WildRiftItem(
            id = "sterak_s_gage_physical",
            name = "Sterak's Gage",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+400 Max Health",
            passive = "Sterak's Gage\nTaking damage triggers a shield\n+400 Max Health\nHeavy Handed: +50% base Attack Damage as bonus Attack Damage.\nLifeline: Damage that puts you under 35% Health grants a shield that equal to 75% of your bonus health that decays over 3 seconds (75s Cooldown).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 seconds.\n3200\nSterak's Gage TIPS: This item is perfect for champions who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum health, attack damage, and helps increase your survivability. The \"Lifeline\" effect activates when your health drops below 35%, granting a shield that absorbs damage, helping you survive heavy hits. \"Sterak's Fury\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 seconds, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against champions with burst damage, such as Zed and Talon, and against champions with heavy CC, like Lissandra and Nautilus.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305021_steraks-gage.webp"
        ))
        add(WildRiftItem(
            id = "spear_of_shojin_physical",
            name = "Lanza de Shojin",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+450 Max Health • +40 Attack Damage",
            passive = "Spear of Shojin\nGain damage bonuses\n+450 Max Health\n+40 Attack Damage\nDragonforce:  +20% Ability Haste.\nFocused Will: Dealing damage to monsters or enemies with abilities increases your champion’s ability and passive damage by 3% for 6s. (Stacks 4 times).\n3100\nSpear of Shojin TIPS: This item provides a mix of survivability and empowered ability usage: it increases your staying power, reduces ability cooldowns, and temporarily boosts your ability and passive damage after engaging enemies or clearing monsters. Perfect for duelist bruisers who want to cast more often in fights and gain an edge in extended skirmishes or split-push scenarios.  Suited for champions who weave autos with frequent ability casts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304984_spear-of-shojin.webp"
        ))
        add(WildRiftItem(
            id = "titanic_hydra_defense",
            name = "Hidra Titánica",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+450 Max Health • +40 Attack Damage",
            passive = "Titanic Hydra\nAttacks deal bonus damage in a area\n+450 Max Health\n+40 Attack Damage\nCleave: Every 1.75 second(s), your next attack deals bonus physical damage equal to 25 + 3% bonus (also applies to turrets), creating a shockwave that deals physical damage equal to 80 + 10% bonus to enemies behind the target. Ranged champions deal 75% of the damage.\n3000\nTitanic Hydra TIPS: This item turns your basic attacks into an AOE tool: periodically your next hit becomes a sweeping strike that deals bonus physical damage to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for melee bruisers and tanks who combine a big health pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304937_titanic-hydra.webp"
        ))
        add(WildRiftItem(
            id = "terminus_physical",
            name = "Terminus",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+40 Attack Damage • +30% Attack Speed",
            passive = "Terminus\nIncreases Armor Pen, Megic Pen, Armor, and Magic Resist\n+40 Attack Damage\n+30% Attack Speed\nShadow: Attacks deal 35 bonus magic damage on-hit.\nJuxtaposition: Alternate between Light and Dark on-hits when attacking. Light attacks grant 5-8 Armor and Magic Resist for 5 seconds on hit. Dark attacks grant 11% Armor Pen and 11% Magic Pen for 5 seconds on hit. Each on-hit effect stacks up to 3 times. While you have this item, bonus Armor Pen and Magic Pen granted by it is capped at 40%.\n3300\nTerminus TIPS: This item is perfect for champions who rely heavily on auto-attacks and can benefit from mixed penetration effects and stacking resistances. It provides bonuses to attack damage and attack speed, and adds bonus magic damage to your auto-attacks. The \"Juxtaposition\" effect alternates between Light and Dark on-hits, granting you bonuses to resistances or penetration depending on which effect is triggered. Light attacks grant armor and magic resist, while Dark attacks grant armor and magic penetration. This item is especially useful for champions who auto-attack frequently and can take advantage of the stacking resistances and penetration effects.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305038_terminus.webp"
        ))
        add(WildRiftItem(
            id = "sundered_sky_physical",
            name = "Sundered Sky",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            passive = "Sundered Sky\nPeriodically empowers attacks\n+350 Max Health\n+40 Attack Damage\n+15 Ability Haste\nLightshield Strike: The first attack against an enemy champion deals Critically Strikes (6s cooldown per target), dealing 160% damage  and restores Health (equal to 125% base Attack Damage + 6% of missing Health to you.\n3000\nSundered Sky TIPS: This item is perfect for AD bruisers who deal physical damage and need sustain during fights. It provides bonuses to maximum health, attack damage, and ability haste, helping to improve both survivability and damage output. The \"Lightshield Strike\" effect causes your first attack against an enemy champion to critically strike, dealing 160% damage and restoring health based on your base attack damage and a percentage of your missing health. This makes the item a great choice for AD bruisers who engage in fights and require both extra damage and sustain.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305059_sundered-sky.webp"
        ))
        add(WildRiftItem(
            id = "eclipse_physical",
            name = "Eclipse",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+65 Attack Damage • +20 Ability Haste",
            passive = "Eclipse\nGain a shield and deal bonus damage\n+65 Attack Damage\n+20 Ability Haste\nEver Rising Moon: Hitting an enemy champion with 2 separate attacks or abilities within 1.8s deals bonus physical damage equal to 7% of the target's max Health(3.5% for ranged champions), and grants you a shield that absorbs damage equal to 140 + 35% bonus Attack Damage (70 + 18% bonus Attack Damage for ranged champions) for 2s. (6s Cooldawn)\n3000\nEclipse TIPS: This item is perfect for assassins who want to burst down enemies quickly. It provides bonuses to attack damage and ability haste, allowing you to deal more damage and use your abilities more frequently. The \"Ever Rising Moon\" effect activates when hitting an enemy champion with two separate attacks or abilities within 1.8 seconds, dealing bonus physical damage based on the target's max health and granting you a shield that absorbs damage. This makes the item a great choice for assassins who want to quickly eliminate targets and gain extra survivability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305145_eclipse.webp"
        ))
        add(WildRiftItem(
            id = "soul_transfer_physical",
            name = "Transferencia de Alma",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+25 Attack Damage • +25% Critical Rate • +30% Attack Speed",
            passive = "Soul Transfer\nSummon a clone that attacks alongside you\n+25 Attack Damage\n+25% Critical Rate\n+30% Attack Speed\nShadow Dance: When your attack Critically Strikes an enemy champion or a large monster, summon a clone that lasts 4 second(s) to attack nearby enemies. The clone inherits 20% of your Attack Damage and additionally gains 30% of your Critical Rate as Attack Speed. Up to two clones can exist at one time.\nIf a clone moves more than 600 units away from you, it will disappear early.\n3200\nSoul Transfer TIPS: This item is perfect for champions relying on critical strikes and auto-attacks, especially in extended teamfights. It grants bonus attack damage, critical strike chance, and attack speed. On a critical strike against a champion or large monster, you summon a clone that attacks nearby enemies— the clone inherits a portion of your AD and converts extra crit chance into attack speed.  Ideal for marksmen who frequently land crits and need extra multi-target damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305204_soul-transfer.webp"
        ))
        add(WildRiftItem(
            id = "hullbreaker_physical",
            name = "Rompecascos",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+400 Max Health • +50 Attack Damage",
            passive = "Hullbreaker\nSplit-pushing power\n+400 Max Health\n+50 Attack Damage\nSet Sail: Gain 5% Movement Speed.\nSkipper: Every 4th attack against champions and epic monsters deals bonus physical damage equal to 160% base  plus 5%(ranged champions deal 40% of the damage), increased to 240% base  plus 9%against structures (ranged champions deal 40% of the damage).\nBoarding Party: Nearby allied siege and super minions gain 20-130 Armor (25% bonus if you're a ranged champion) and 10-120 Magic Resistance() (25% bonus if you're a ranged champion).\n3100\nHullbreaker TIPS: This item is the cornerstone of a powerful split‑push strategy. It provides significant health and attack damage, boosting both your survivability and tower‑breaking potential. The “Set Sail” passive steadily increases your movement speed, helping you rotate between lanes and avoid ganks. Every fifth attack on champions or epic monsters triggers “Skipper”, dealing hefty bonus physical damage—and even more against structures—making it indispensable for rapid turret takedowns. Additionally, “Boarding Party” buffs nearby allied siege and super minions with extra resistances, amplifying your split‑push threat. Perfect for solo laners who want to apply pressure on the map and force enemy responses without directly joining teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304821_hullbreaker.webp"
        ))
        add(WildRiftItem(
            id = "guinsoo_s_rageblade_magic",
            name = "Espadafuria de Guinsoo",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+30% Attack Speed",
            passive = "Guinsoo's Rageblade\nApplies on-hit effects\n+30% Attack Speed\nSurge: Gain 5% Move Speed.\nChaos: Gain 25 Attack Damage or 50 Ability Power (Adaptive).\nWrath: Attacks deal 30 magic damage but no longer Critical Strike. Fore every 1% Critical Strike Rate gained from items, your magic damage increases by 1.5, up to a max increase of 75 (reached at 50% Critical Rate).\nSeething Strike: Attacks grant 8% Attack Speed, staking up to 4 times for a maximum of 32% Attack Speed). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n3100\nGuinsoo's Rageblade TIPS: This item is perfect for on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit magic damage, provides a powerful ramp of attack speed and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1768000022_abb24fd724faa77b82baf985dea956b8eae9f31a-512x512.webp"
        ))
        add(WildRiftItem(
            id = "kraken_slayer_physical",
            name = "Verdugo de Krakens",
            category = "Daño Físico",
            goldCost = 2800,
            stats = "+40 Attack Damage • +30% Attack Speed",
            passive = "Kraken Slayer\nDeal bonus physical damage\n+40 Attack Damage\n+30% Attack Speed\nCloud Stride:  +5% Move Speed.\nBring it Down: Every third attack deals 120-160 () bonus physical damage (110-150 () for ranged champions), increased by 1% per 1% Health the target is missing, up to an increase of 70%.\n2800\nKraken Slayer TIPS: This item boosts your basic attacks by providing extra damage, attack speed, and a mobility bonus for better positioning. Periodically your hits deal bonus damage that scales with the target’s missing health, making it strong both versus bulky targets and for finishing off low-HP enemies. A top pick for marksmen and auto-attack focused builds that want reliable sustained DPS and execute potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630263_kraken-slayer.webp"
        ))
        add(WildRiftItem(
            id = "overlord_s_bloodmail_defense",
            name = "Armadura Sangrienta del Señor Supremo",
            category = "Daño Físico",
            goldCost = 3200,
            stats = "+450 Max Health • +30 Attack Damage",
            passive = "Overlord's Bloodmail\nGain Attack Damage when losing Health\n+450 Max Health\n+30 Attack Damage\nTyranny: Gain Attack Damage equal to 2.5% of your bonus Health.\nRetribution: Gain up to 9% increased Attack Damage based on your missing Health. Maximum Retribution bonus while below 30% Health.\n3200\nOverlord's Bloodmail TIPS: This item converts bonus health into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack health and embrace high-risk, high-reward skirmishes: the more bonus health you have, the stronger your raw attacks become, and when you fight at low health you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630274_overlords-bloodmail.webp"
        ))
        add(WildRiftItem(
            id = "experimental_hexplate_physical",
            name = "Hexoplaca Experimental",
            category = "Daño Físico",
            goldCost = 3000,
            stats = "+400 Max Health • +35 Attack Damage • +20% Attack Speed",
            passive = "Experimental Hexplate\nGain Attack Speed & Movement Speed when your ultimate is cast\n+400 Max Health\n+35 Attack Damage\n+20% Attack Speed\nHexcharged:  Gain +20 Ability Haste for your ultimate ability.\nOverdrive: After using your ultimate ability, gain 40% Attack Speed (20% for ranged champions) and 20% Movement Speed (10% for ranged champions) for 8s. (30s Cooldown)\n3000\nExperimental Hexplate TIPS: This item blends survivability with explosive offensive potential. It grants extra health, attack power and attack speed, shortens ultimate cooldowns, and—most importantly—grants a strong burst of attack and movement speed after using your ultimate, enabling you to convert your engage into high sustained damage and chase. Ideal for fighters and bruisers who rely on their ultimate to start fights and immediately follow up with empowered autos and mobility.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630328_experimental-hexplate.webp"
        ))
        add(WildRiftItem(
            id = "dominik_s_regards_physical",
            name = "Recuerdos de Lord Dominik",
            category = "Daño Físico",
            goldCost = 3300,
            stats = "+30 Attack Damage • +36% Armor Penetration • +25% Critical Rate",
            passive = "Dominik’s Regards\nAlready equipped with percentage armor penetration and bonus damage, but lacks durability\n+30 Attack Damage\n+36% Armor Penetration\n+25% Critical Rate\nGiant Slayer: Deal bonus damage based on the target’s bonus Health. At 1500 bonus Health, the bonus damage is increased up to 15%.\n3300\nDominik’s Regards TIPS: Is built to punish bulky foes. It converts a chunk of your offensive power into armor penetration and adds bonus damage that scales with the enemy’s extra health, making it a go-to pick when the enemy team stacks HP and armor. Because it focuses on penetration and damage rather than survivability, use it when you need to cut through tanky targets quickly while relying on positioning or teammates for protection.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1774953440_lord-dominiks-regards.webp"
        ))
        add(WildRiftItem(
            id = "stridebreaker_active",
            name = "Rompeavances",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+400 Max Health • +40 Attack Damage • +25% Attack Speed",
            passive = "Stridebreaker\nSlows enemies nearby after a short dash\n+400 Max Health\n+40 Attack Damage\n+25% Attack Speed\nBreaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Physical Damage to nearby enemies and slowing them by 40% for 3s (25s cooldown)\nStride (Passive): Gain 20 Movement Speed for 2 second(s) when you deal physical damage.\n3100\nStridebreaker TIPS: This item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily slow them, while the passive grants bonus movement speed whenever you deal physical damage. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within melee range.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_stridebreaker.webp"
        ))
        add(WildRiftItem(
            id = "goredrinker_active",
            name = "Bebedor de Sangre",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            passive = "Goredrinker\nDeal damage in an area\n+350 Max Health\n+40 Attack Damage\n+15 Ability Haste\nGoredrink (Passive): Gain8% Omni Vamp.\nThirsting Slash (Active): Deal 175% base AD as physical damage to nearby enemies. Restore Health equal to 20% plus 10% missing for each enemy champion hit. (12s cooldown)\n3100\nGoredrinker TIPS: This item is perfect for fighters who excel in extended combat. It grants Omni Vamp, while its active ability deals area physical damage and restores health based on the number of enemy champions hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389878_goredrinker.webp"
        ))
        add(WildRiftItem(
            id = "galeforce_active",
            name = "Fuerza del Viento",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+50 Attack Damage • +25% Critical Rate • +15% Attack Speed • +5% Move Speed",
            passive = "Galeforce\nGrants a dash and damage bonus\n+50 Attack Damage\n+25% Critical Rate\n+15% Attack Speed\n+5% Move Speed\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Health enemy near your destination, prioritizing champions. Deal physical damage equal to 40-125 () plus 35% bonus. (60s cooldown)\n3100\nGaleforce TIPS: This item greatly improves the mobility of marksmen and AD champions by granting a dash that also fires projectiles at the lowest-health nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for champions who value mobility, safety, and strong burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_galeforce.webp"
        ))
        add(WildRiftItem(
            id = "mercurial_scimitar_active",
            name = "Cimitarra Mercurial",
            category = "Daño Físico",
            goldCost = 3100,
            stats = "+45 Attack Damage • +10% Physical Vamp • +40 Magic Resistance",
            passive = "Mercurial Scimitar\nDispels crowd control\n+45 Attack Damage\n+10% Physical Vamp\n+40 Magic Resistance\nQuicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Slow Resist for 1.5 seconds. (60s Cooldown)\nCannot be used during knock up or knock back effects.\n3100\nMercurial Scimitar TIPS: This item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and slows once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep dealing damage against heavy-CC team compositions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp"
        ))
        add(WildRiftItem(
            id = "luden_s_echo_magic",
            name = "Eco de Luden",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+100 Ability Power • +500 Max Mana • +10 Ability Haste",
            passive = "Luden's Echo\nAbilities deal bonus damage\n+100 Ability Power\n+500 Max Mana\n+10 Ability Haste\nEcho: Your next damaging ability or empowered attack deals an additional 140 + 15% magic damage to the target and up to 3 nearby enemies. (9s Cooldown)\n2800\nLuden's Echo TIPS: This item greatly enhances your burst damage by empowering your next damaging ability or empowered attack with an additional magic explosion that also strikes nearby enemies. It is an excellent choice for mages who excel at wave clearing, poking multiple targets, and dominating short trades with high burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388218_ludens-echo.webp"
        ))
        add(WildRiftItem(
            id = "morellonomicon_support",
            name = "Morellonomicon",
            category = "Daño Mágico",
            goldCost = 2650,
            stats = "+300 Max Health • +75 Ability Power • +15 Ability Haste",
            passive = "Morellonomicon\nMagic damage reduces enemy healing\n+300 Max Health\n+75 Ability Power\n+15 Ability Haste\nAffliction: Dealing magic damage to enemy champions inflicts 50% Grievous Wounds for 3 seconds.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n2650\nMorellonomicon TIPS: This item is designed to counter champions with strong healing and sustain. Any magic damage you deal applies Grievous Wounds, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388237_morellonomicon.webp"
        ))
        add(WildRiftItem(
            id = "rabadon_s_deathcap_magic",
            name = "Sombrero Mortal de Rabadon",
            category = "Daño Mágico",
            goldCost = 3400,
            stats = "+130 Ability Power",
            passive = "Rabadon's Deathcap\nBoosts Ability Power\n+130 Ability Power\nOverkill: Increases Ability Power by 30%.\n3400\nRabadon's Deathcap TIPS: This item is the ultimate Ability Power amplifier for any mage. It not only provides a massive amount of AP but also increases your total Ability Power, dramatically boosting your spell damage, healing, and all other AP scaling effects. An essential late-game purchase for champions looking to maximize their spellcasting potential and unleash devastating burst damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388295_rabadons-deathcap.webp"
        ))
        add(WildRiftItem(
            id = "rylai_s_crystal_scepter_magic",
            name = "Cetro de Cristal de Rylai",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+350 Max Health • +65 Ability Power",
            passive = "Rylai's Crystal Scepter\nAbilities apply slows\n+350 Max Health\n+65 Ability Power\nIcy: Damaging abilities and empowered attacks slow enemies by 30% for 0.75 second.\n2700\nRylai's Crystal Scepter TIPS: This item enhances your crowd control by causing your abilities and empowered attacks to slow enemies with every hit. The bonus health improves your durability, while the consistent slow makes it much easier to land follow-up abilities, chase fleeing targets, and support your teammates. It is an excellent choice for damage-over-time mages and champions who rely on keeping enemies within the range of their abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388390_rylais-crystal-scepter.webp"
        ))
        add(WildRiftItem(
            id = "liandry_s_torment_magic",
            name = "Tormento de Liandry",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+300 Max Health • +70 Ability Power",
            passive = "Liandry's Torment\nAbilities deal bonus damage\n+300 Max Health\n+70 Ability Power\nTorment: Damaging abilities and empowered attacks burn enemies for 2% max Health magic damage for 3 seconds.\nMadness: Deals 2% more damage for each second in combat against champions, capped at 6% after 3 seconds.\n3000\nLiandry's Torment TIPS: This item excels in extended fights. Your abilities and empowered attacks ignite enemies, dealing damage over time based on their maximum health, while your overall damage steadily increases the longer you remain in combat. It is an excellent choice for damage-over-time mages and AP bruisers who want to wear down even the toughest frontline champions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388368_yorlde-liandrys-torment.webp"
        ))
        add(WildRiftItem(
            id = "rod_of_ages_magic",
            name = "Vara de las Edades",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+350 Max Health • +50 Ability Power • +400 Max Mana",
            passive = "Rod of Ages\nStats grow over time\n+350 Max Health\n+50 Ability Power\n+400 Max Mana\nEternity: Restore Mana equal to 15% of the damage taken from champions. Regen Health equal to 20% Mana spent. Capped at 25 Health per cast.\nVeteran: Each stack provides 15 Health, 30 Mana and 4 Ability Power, stacking at a rate of 1 every 35 seconds. Max of 10 stacks, providing 150 Health, 300 Mana, and 40 Ability Power.\n2700\nRod of Ages TIPS: This item grows stronger over the course of the game, gradually increasing its stats and becoming one of the best scaling options available. It provides an excellent balance of durability, mana, and Ability Power while restoring both health and mana during combat, allowing you to stay in fights much longer. A perfect choice for mages and AP bruisers who thrive in the late game and excel in extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388412_rod-of-ages.webp"
        ))
        add(WildRiftItem(
            id = "lich_bane_magic",
            name = "Perdición del Liche",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+100 Ability Power • +10 Ability Haste • +5% Move Speed",
            passive = "Lich Bane\nAttacks deal bonus damage aster ability casts\n+100 Ability Power\n+10 Ability Haste\n+5% Move Speed\nSpellblade: Using an ability causes the next attack used within 10 seconds to deal bonus magic damage equal to 75% base AD  + 45% AP . (1.5s Cooldown) Damage is reduced vs structures.\n2800\nLich Bane TIPS: This item is perfect for champions who weave abilities between their basic attacks. After casting a spell, your next attack is empowered with a powerful burst of bonus magic damage, greatly increasing your combo potential. The bonus movement speed also improves your mobility, making it easier to reposition and chase targets. An excellent choice for mobile mages, AP assassins, and hybrid champions who rely on short, high-damage ability rotations.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388405_lich-bane.webp"
        ))
        add(WildRiftItem(
            id = "nashor_s_tooth_magic",
            name = "Diente de Nashor",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+45% Attack Speed • +20 Ability Haste",
            passive = "Nashor's Tooth\nAttacks deal bonus damage\n+45% Attack Speed\n+20 Ability Haste\nMagic Fang: Obtain 25 Attack Damage or 50 Ability Power (Adaptive).\nGnaw: Attacks deal adaptive damage (15 + 20% bonus+ 30% bonus) on hit.\n2800\nNashor's Tooth TIPS: This item is perfect for champions who blend auto-attacks with magic damage. It provides a hefty boost to attack speed and reduces ability cooldowns, allowing you to cast spells more frequently in fights. The “Magic Fang” passive adapts to your build by granting either bonus attack damage or ability power, adding flexibility.  With each auto-attack, “Gnaw” deals adaptive magic damage on hit, making it especially effective against tanky targets and for wearing down opponents over time. This item is ideal for champions like Teemo, Kayle, and Jax, who rely on sustained auto-attacks supported by magic damage and need frequent ability usage to maximize DPS in extended engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753302010_nashors-tooth.webp"
        ))
        add(WildRiftItem(
            id = "archangel_s_staff_magic",
            name = "Archangel's Staff",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+60 Ability Power • +500 Max Mana • +25 Ability Haste",
            passive = "Archangel's Staff\nConverts Mana to Ability Power\n+60 Ability Power\n+500 Max Mana\n+25 Ability Haste\nAwe: Grants Ability Power equal to 1% max Mana and refunds 25%  of all Mana spent.\nMana Charge: Increases max Mana by 14 every time Mana is spent. Caps at 700 bonus Mana, transforming Archangel's Staff into Seraph's Embrace. Triggers up to 3 times every 10 seconds. You may only carry one Tear of the Goddess item at a time.\n3000\nArchangel's Staff TIPS: This item is perfect for mages who rely on a large mana pool and need a significant boost to their ability power. It provides bonuses to ability power, magic penetration, maximum mana, and ability haste, helping you deal damage and use your abilities frequently. The \"Awe\" effect increases your ability power based on your maximum mana and refunds a portion of mana spent, helping you sustain in fights. The \"Mana Charge\" effect increases your maximum mana every time you spend mana, eventually transforming the item into Seraph's Embrace, giving you additional bonuses. This item is ideal for champions who want to scale well into the late game with a large amount of AP and mana.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388461_archangels-staff.webp"
        ))
        add(WildRiftItem(
            id = "seraph_s_embrace_magic",
            name = "Seraph's Embrace",
            category = "Daño Mágico",
            goldCost = 2950,
            stats = "+60 Ability Power • +1200 Max Mana • +25 Ability Haste",
            passive = "Seraph's Embrace\nConverts Mana to Ability Power\n+60 Ability Power\n+1200 Max Mana\n+25 Ability Haste\nAwe: Grants Ability Power equal to 2% max Mana and refunds 25%  of all Mana spent.\nLifeline: Damage that puts you under 35%  Health consumes 20% of your current Mana to grant a shield, equal to that amount +100 for 2 seconds. (70s cooldown).\n2950\nSeraph's Embrace TIPS: This item is perfect for mages who need a large mana pool and survivability in team fights. It provides bonuses to ability power, magic penetration, maximum mana, and ability haste, helping you deal damage and use your abilities effectively. The \"Awe\" effect increases your ability power based on your maximum mana and refunds a portion of mana spent, allowing you to stay in fights longer. The \"Lifeline\" effect activates when your health drops below 35%, granting a shield that helps you survive the initial burst damage and prolong your presence in the fight. This item is ideal for champions who want to scale well into the late game with a large amount of AP and mana, while also gaining extra survivability.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388429_seraphs-embrace.webp"
        ))
        add(WildRiftItem(
            id = "infinity_orb_magic",
            name = "Orbe del Infinito",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+110 Ability Power • +15 Magic Penetration",
            passive = "Infinity Orb\nAbilities deal bonus damage\n+110 Ability Power\n+15 Magic Penetration\nInevitable Demise: Abilities and empowered attacks Critically Strike for 20% bonus damage against enemies below 35% Health.\n3100\nInfinity Orb TIPS: This item greatly enhances a mage's finishing power. It provides a large boost to Ability Power and magic penetration while allowing your abilities and empowered attacks to deal increased damage to low-health enemies. An excellent choice for mages and AP assassins who want to execute targets more reliably and maximize their burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388486_yordle-infinity-orb.webp"
        ))
        add(WildRiftItem(
            id = "oceanid_s_trident_support",
            name = "Tridente de Oceánida",
            category = "Daño Mágico",
            goldCost = 2600,
            stats = "+200 Max Health • +80 Ability Power • +10 Ability Haste",
            passive = "Oceanid's Trident\nAnti-Shielding\n+200 Max Health\n+80 Ability Power\n+10 Ability Haste\nLethal Weapon: Dealing ability damage to an enemy champion reduces any shields they gain for 3 seconds. Area of effect abilities apply (5% of bonus AP + 25)% shield reduction, capped at 45%; while single target abilities apply (5% of bonus AP + 40)% shield reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\n2600\nOceanid's Trident TIPS: This item is designed to counter shield-heavy champions. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against shield-reliant compositions, allowing your team to break through enemy defenses and eliminate priority targets more effectively.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388583_oceanids-trident.webp"
        ))
        add(WildRiftItem(
            id = "cosmic_drive_magic",
            name = "Impulso Cósmico",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+300 Max Health • +70 Ability Power • +25 Ability Haste • +5% Move Speed",
            passive = "Cosmic Drive\nDealing ability damage grants movement speed\n+300 Max Health\n+70 Ability Power\n+25 Ability Haste\n+5% Move Speed\nSpelldance: Dealing magic or true damage to champions grants 30 Movement Speed for 4 second(s).\n3000\nCosmic Drive TIPS: This item combines Ability Power, durability, and exceptional mobility. Dealing damage with your abilities grants a burst of movement speed, making it easier to kite enemies, chase fleeing targets, or reposition safely during fights. It is an excellent choice for mobile mages, AP bruisers, and champions who thrive in extended skirmishes while constantly staying on the move.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388596_cosmic-drive.webp"
        ))
        add(WildRiftItem(
            id = "riftmaker_magic",
            name = "Creador de Grietas",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+350 Max Health • +70 Ability Power • +15 Ability Haste",
            passive = "Riftmaker\nRamping Damage\n+350 Max Health\n+70 Ability Power\n+15 Ability Haste\nVoid Corruption: Every 1 second(s) in combat with enemy champions, deal 2% bonus damage, up to 8%.\nAt maximum strength, gainOmni Vamp. (10% for melee champions / 6% for ranged champions).\nVoid Infusion: Gain 2% of your bonus Health as Ability Power.\n3100\nRiftmaker TIPS: This item is built for extended fights, gradually increasing your damage the longer you remain in combat. Once fully ramped up, it grants Omni Vamp for improved sustain, while your bonus Health is partially converted into Ability Power, further increasing your overall damage. An excellent choice for AP bruisers and battlemages who excel in prolonged teamfights and thrive by scaling throughout combat.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388604_riftmaker.webp"
        ))
        add(WildRiftItem(
            id = "horizon_focus_magic",
            name = "Enfoque al Horizonte",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+80 Ability Power • +25 Ability Haste",
            passive = "Horizon Focus\nDeals bonus damage to marked targets\n+80 Ability Power\n+25 Ability Haste\nHypershot: Damaging an enemy champion with an ability from 600 units away reveals them for 8 seconds and increases damage dealt to them by 10%.\nFocus: When Hypershot is triggered, it reveals all enemy champions within 1.200 units of the target for 3s. (12s Cooldown)\n2700\nHorizon Focus TIPS: This item is perfect for long-range mages and poke-oriented champions. Hitting an enemy with a spell from a distance marks and reveals them while increasing all subsequent damage they take. It also exposes nearby enemy champions, providing valuable vision for your team and making follow-up attacks much easier. An excellent choice for artillery mages and champions who excel at controlling fights from a safe distance.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388673_horizon-focus.webp"
        ))
        add(WildRiftItem(
            id = "runaan_s_hurricane_magic",
            name = "Huracán de Runaan",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+35% Attack Speed • +25% Critical Rate",
            passive = "Runaan's Hurricane\nRanged Attacks hit 3 targets\n+35% Attack Speed\n+25% Critical Rate\nWind's Fury: Attacks strike 2 additional nearby enemies, each dealing 55%. These strikes can Critically Strike and trigger on-hit effects.\nWind Blade: Attacks deal 15 bonus physical damage on-hit against targets.\nThis item cannot only be used by melee champions.\n2900\nRunaan's Hurricane TIPS: This item turns your basic attacks into multi-target pressure: each attack fires extra bolts at nearby enemies that can trigger on-hit effects and crits. It greatly improves waveclear, contributes strong multi-target damage in teamfights, and makes trading on single targets much riskier for the opponent due to distributed damage. Perfect for marksmen and on-hit builds who value sustained attack cadence and AOE presence. Melee users can use it too, but it shines brightest on ranged auto-attackers.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300839_yordle-runaans-hurricane.webp"
        ))
        add(WildRiftItem(
            id = "malignance_magic",
            name = "Malignance",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+90 Ability Power • +500 Max Mana • +15 Ability Haste",
            passive = "Malignance\nAn item made for Ultimate-centric playstyles\n+90 Ability Power\n+500 Max Mana\n+15 Ability Haste\nScorn: Your Ultimate abilities gain 20 Ability Haste.\nHatefog: Damaging a champion with your Ultimate burns the ground beneath them for 3 second(s), dealing magic damage equal to 60 plus 5% AP per second and reducing their Magic Resist by 10. Burn radius increases with damage, reaching maximum radius at 800 damage.\n2700\nMalignance TIPS: This item is perfect for champions who focus on their ultimate abilities and want to maximize their effectiveness in fights. It provides bonuses to ability power, magic penetration, maximum mana, and ability haste. The \"Scorn\" effect reduces the cooldown of your ultimate ability, enhancing its efficiency and uptime. The \"Hatefog\" effect deals magic damage to enemies in the area after using your ultimate, creating a scorched earth effect. Enemies within this area take damage and have their magic resistance reduced, making this item ideal for champions who want to weaken their opponents and increase their damage. It’s especially useful against enemies with high magic resistance.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388765_malignance.webp"
        ))
        add(WildRiftItem(
            id = "guinsoo_s_rageblade_magic",
            name = "Espadafuria de Guinsoo",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+30% Attack Speed",
            passive = "Guinsoo's Rageblade\nApplies on-hit effects\n+30% Attack Speed\nSurge: Gain 5% Move Speed.\nChaos: Gain 25 Attack Damage or 50 Ability Power (Adaptive).\nWrath: Attacks deal 30 magic damage but no longer Critical Strike. Fore every 1% Critical Strike Rate gained from items, your magic damage increases by 1.5, up to a max increase of 75 (reached at 50% Critical Rate).\nSeething Strike: Attacks grant 8% Attack Speed, staking up to 4 times for a maximum of 32% Attack Speed). While fully stacked, every 3 attacks applies on-hit effects an additional 1 times.\n3100\nGuinsoo's Rageblade TIPS: This item is perfect for on-hit and high-attack-speed builds. It converts crit-focused stats into consistent on-hit magic damage, provides a powerful ramp of attack speed and stacking attack-speed bursts from consecutive hits. At full stacks your attacks trigger extra on-hit strikes more often, making it a top choice for players who want reliable, sustained damage in extended duels and teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1768000022_abb24fd724faa77b82baf985dea956b8eae9f31a-512x512.webp"
        ))
        add(WildRiftItem(
            id = "blackfire_torch_magic",
            name = "Antorcha de Fuego Negro",
            category = "Daño Mágico",
            goldCost = 2800,
            stats = "+80 Ability Power • +500 Maximum Mana • +20 Ability Haste",
            passive = "Blackfire Torch\nDeal burn damage\n+80 Ability Power\n+500 Maximum Mana\n+20 Ability Haste\nBaleful Blaze: Dealing damage with abilities causes enemies to burn for 20 + 2%magic damage per second for 3 seconds.\nDeal 40 plus 2%magic damage every second to monsters.\nBlackfire: For each enemy champion or monster affected by your Baleful Blaze, gain 4% Ability Power.\n2800\nBlackfire Torch TIPS: This item is perfect for mages who specialize in sustained spell damage. Your abilities ignite enemies, burning them over time, and the more enemies affected by the burn, the more Ability Power you gain. It excels on champions with area-of-effect and damage-over-time abilities, boosting both your overall damage and your ability to clear waves and jungle camps efficiently.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191589_blackfire-torch.webp"
        ))
        add(WildRiftItem(
            id = "dusk_and_dawn_magic",
            name = "Anochecer y Amanecer",
            category = "Daño Mágico",
            goldCost = 3100,
            stats = "+350 Maximum Health • +25% Attack Speed • +70 Ability Power • +20 Ability Haste",
            passive = "Dusk and Dawn\nApplies on-hit effects\n+350 Maximum Health\n+25% Attack Speed\n+70 Ability Power\n+20 Ability Haste\nSpellblade: After using an ability, your next attack deals (75% base+ 10%) bonus magic damage. After a brief delay, apply on-hits to the target 1 additional time. (1.5s Cooldown)\nDeals reduced damage to structures.\n3100\nDusk and Dawn TIPS: This item is perfect for champions who weave abilities into their basic attacks. After casting a spell, your next attack is empowered with bonus magic damage and then triggers all on-hit effects an additional time, dramatically increasing your overall damage output. It excels on hybrid AP fighters and melee mages who rely on chaining abilities and auto-attacks to maximize their DPS.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191855_2510_11zon.webp"
        ))
        add(WildRiftItem(
            id = "stormsurge_magic",
            name = "Sobrecarga de Tormenta",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+90 Ability Power • +15 Magic Penetration • +6% Move Speed",
            passive = "Stormsurge\nA powerful item for Scaling Mages\n+90 Ability Power\n+15 Magic Penetration\n+6% Move Speed\nStormraider: When damaging a champion, dealing damage equal to 25% of their max Health within 2.5 second(s) applies Squall to them and grants you 25% bonus Movement Speed for 2.5s. (25s Cooldown)\nSquall: After 2 second(s), strike the target, dealing magic damage equal to 125 plus 10%. If the target is killed before the strike, it detonates immediately in a large area and grants 25 gold.\n2900\nStormsurge TIPS: This item is perfect for mages capable of delivering heavy burst damage. After landing a strong combo, it marks the target, grants you a burst of movement speed, and follows up with an additional magic strike. If the target dies before the effect triggers, it immediately explodes in an area and rewards you with bonus gold. An excellent choice for scaling mages and AP assassins looking to secure kills and snowball their advantage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192090_4646_11zon.webp"
        ))
        add(WildRiftItem(
            id = "void_staff_magic",
            name = "Báculo del Vacío",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+95 Ability Power • +40% Magic Penetration",
            passive = "Void Staff\nMagic Penetration (%)\n+95 Ability Power\n+40% Magic Penetration\n3000\nVoid Staff TIPS: This item is the premier choice against enemies stacking magic resistance. It greatly increases the effectiveness of your spells through powerful magic penetration, allowing you to deal consistent damage even to the toughest targets. An excellent pickup for any mage once the enemy team starts investing in magic resistance.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192299_3135_11zon.webp"
        ))
        add(WildRiftItem(
            id = "cryptbloom_magic",
            name = "Criptoflora",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+75 Ability Power • +30% Magic Penetration • +20 Ability Haste",
            passive = "Cryptbloom\nRestore health on champion kill\n+75 Ability Power\n+30% Magic Penetration\n+20 Ability Haste\nLife from Death: When a champion that you damaged within 3s dies, a nova spreads from their corpse that restores 100 plus 20%Health to allies. (60s Cooldown)\n3000\nCryptbloom TIPS: This item combines powerful magic penetration with valuable team utility. In addition to boosting your spell damage, champions you recently damaged release a healing nova upon death, restoring health to nearby allies. It is an excellent choice for mages who want to deal heavy damage while providing extra sustain for their team during extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192528_3137_11zon.webp"
        ))
        add(WildRiftItem(
            id = "bloodletter_s_curse_magic",
            name = "Maldición del Sangrador",
            category = "Daño Mágico",
            goldCost = 2900,
            stats = "+350 Maximum Health • +65 Ability Power • +15 Ability Haste",
            passive = "Bloodletter's Curse\nReduces enemy's Magic Resist\n+350 Maximum Health\n+65 Ability Power\n+15 Ability Haste\nVile Decay: Dealing magic damage with abilities or passives to champions reduces their Magic Resist by 7.5% for 6 seconds (max 30%).\n2900\nBloodletter's Curse TIPS: This item greatly enhances your magic damage by gradually reducing the target's magic resistance whenever your abilities or passive effects deal damage. It excels in extended fights, allowing both you and your AP teammates to deal increasingly higher damage to the same target. An excellent choice for AP bruisers, damage-over-time mages, and champions who can consistently keep the debuff active on multiple enemies.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561458_bloodletters-curse.webp"
        ))
        add(WildRiftItem(
            id = "banshee_s_veil_defense",
            name = "Velo de la Banshee",
            category = "Daño Mágico",
            goldCost = 3000,
            stats = "+105 Ability Power • +40 Magic Resistance",
            passive = "Banshee's Veil\nBlocks an enemy ability\n+105 Ability Power\n+40 Magic Resistance\nAnnul: Grants a spell shield that blocks the next hostile ability. (30s Cooldown)\n3000\nBanshee's Veil TIPS: This item provides strong protection against magic damage while granting a spell shield that blocks the next hostile ability. It is especially effective against champions who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192846_3102_11zon.webp"
        ))
        add(WildRiftItem(
            id = "hextech_roketbelt_active",
            name = "Cinturón Protocohete Hextech",
            category = "Daño Mágico",
            goldCost = 2700,
            stats = "+250 Max Health • +70 Ability Power • +20 Ability Haste",
            passive = "Hextech Roketbelt\nSmall dash\n+250 Max Health\n+70 Ability Power\n+20 Ability Haste\nProtobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% magic damage. (30s Cooldown)\nIf champions or monsters are hit by more than one missile, missiles after the first will deal only 10% damage.\n2700\nHextech Roketbelt TIPS: This item combines Ability Power with extra mobility, allowing you to quickly close the gap or reposition during combat. Its active grants a short dash while firing a cone of rockets that deal area magic damage. It is an excellent choice for AP assassins, mobile mages, and engage-oriented champions who need to dive in, secure kills, or dodge key enemy abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389704_protobelt-enchant.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            category = "Daño Mágico",
            goldCost = 3300,
            stats = "+40 Armor • +110 Ability Power",
            passive = "Zhonya's Hourglass\nTurn invulnerable\n+40 Armor\n+110 Ability Power\nStasis (Active): Become invulnerable and untargetable for 2.5 seconds, but unable to move, attack, cast abilities or use items. (90s Cooldown)\n3300\nZhonya's Hourglass TIPS: This item combines high Ability Power with extra armor, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            category = "Daño Mágico",
            goldCost = 2600,
            stats = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Redemption\nHeal allies in an area\n+150 Max Health\n+50 Ability Power\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Health (based on ally's level) to allied units and deal 10% of max as true damage to enemy champions. (60s Cooldown)\nCan be cast while dead.\n2600\nRedemption TIPS: This item is designed to provide game-changing team support. Its active restores health to all allied units in a large area while dealing true damage to enemy champions, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "guardian_angel_defense",
            name = "Ángel Guardián",
            category = "Defensa",
            goldCost = 3200,
            stats = "+45 Attack Damage • +40 Armor",
            passive = "Guardian Angel\nRevives at death\n+45 Attack Damage\n+40 Armor\nResurrect: Upon taking lethal damage, restores 50% Health and 100% Mana after 4 seconds of stasis. (180s Cooldown)\n3200\nGuardian Angel TIPS: This item is perfect for champions who need a second chance in teamfights. It is especially effective against champions with high burst damage, such as Zed, Syndra, or Zoe, as well as against strong diving champions like Camille, Kha'Zix, or Lee Sin. The Resurrection effect allows you to return to the fight after taking lethal damage, restoring health and mana, giving you a chance to continue fighting and assist your team even in critical moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp"
        ))
        add(WildRiftItem(
            id = "sunfire_aegis_defense",
            name = "Égida de Fuego Solar",
            category = "Defensa",
            goldCost = 2900,
            stats = "+350 Max Health • +40 Armor • +15 Ability Haste",
            passive = "Sunfire Aegis\nBurns nearby emenies\n+350 Max Health\n+40 Armor\n+15 Ability Haste\nImmolate: While in combat, deal magic damage equal to 16-25 plus 0.8% bonus HPto nearby enemies per second. Damaging champions or epic monsters with Immolate increases its damage by 5% for 5s, stacking up to 6 times.\nFlametouch: At max Immolate stacks, attacks burn enemies around you for 50% of Immolate's damage over 3 seconds. Immolate deals 130% damage to monsters an 175-250% () to minions.\n2900\nSunfire Aegis TIPS: This item is perfect for tanks and bruisers who spend most of the fight in the middle of the action. It continuously burns nearby enemies, with the damage increasing the longer you remain in combat, and once fully stacked, your attacks spread additional fire around the target. It is an excellent choice for frontline champions who want consistent area damage while maintaining high durability, as well as faster wave and jungle camp clearing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389023_yordle-sunfire-aegis.webp"
        ))
        add(WildRiftItem(
            id = "randuin_s_omen_defense",
            name = "Presagio de Randuin",
            category = "Defensa",
            goldCost = 2800,
            stats = "+400 Max Health • +75 Armor",
            passive = "Randuin's Omen\nCounters Critical Strike Damage\n+400 Max Health\n+75 Armor\nResilience: Critically Struck deal 30% less damage to you.\nCountercurrent: Gain 1 stacks of Countercurrent when Critically Struck by physical damage. Each stuck grants 5% Movement Speed and 5% slow resist. Max 4 stacks.\n2800\nRanduin's Omen TIPS: This item is built to counter crit-heavy builds. It provides a large health pool and armor while reducing damage from critical strikes, making you much tougher in head-on engagements. When you are critically struck, you gain stacks that boost your movement and slow resistance, helping you hold position and control fight spacing.  — Perfect for tanks and bruisers who need to stand up to high-crit auto-attackers and survive extended teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389031_randuins-omen.webp"
        ))
        add(WildRiftItem(
            id = "thornmail_defense",
            name = "Malla de Espinas",
            category = "Defensa",
            goldCost = 2700,
            stats = "+200 Max Health • +75 Armor",
            passive = "Thornmail\nReflects damage and reduces enemy healing\n+200 Max Health\n+75 Armor\nThorns: When struck by an attack, deal 20 + 6% bonus Armor + 1% bonus Health magic damage to the attacker.\nEntwine: Apply 50% Grievous Wounds to enemy champions for 3 second(s) when stuck by their attacks or dealing damage to them.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n2700\nThornmail TIPS: This item reflects a portion of incoming physical damage back to attackers as magic damage and applies an effect that reduces enemy healing effectiveness. A strong pick versus teams with heavy auto-attack damage and sustain — ideal for tanks and bruisers who need to absorb focus and cut down opponent healing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389035_thornmail.webp"
        ))
        add(WildRiftItem(
            id = "warmog_s_armor_defense",
            name = "Armadura de Warmog",
            category = "Defensa",
            goldCost = 2850,
            stats = "+700 Max Health • +100% Health Regen • +20 Ability Haste",
            passive = "Warmog's Armor\nOut of combat Heath Regen\n+700 Max Health\n+100% Health Regen\n+20 Ability Haste\nWarmog's Heart: If you have at least 950 bonus Health, restore 3.5% Health per second if you haven't taken damage within the last 5 seconds.\nBlessed: Increases all healing and shielding effects on you by 30%.\n2850\nWarmog's Armor TIPS: This item is a top survivability pickup: it grants a massive health pool and strong out-of-combat regeneration, letting you recover quickly between fights. It also amplifies healing and shields, making you much harder to finish off. Perfect for tanks and bruisers who need high survivability and fast recovery after engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389074_warmogs-armor.webp"
        ))
        add(WildRiftItem(
            id = "sterak_s_gage_defense",
            name = "Sterak's Gage",
            category = "Defensa",
            goldCost = 3200,
            stats = "+400 Max Health",
            passive = "Sterak's Gage\nTaking damage triggers a shield\n+400 Max Health\nHeavy Handed: +50% base Attack Damage as bonus Attack Damage.\nLifeline: Damage that puts you under 35% Health grants a shield that equal to 75% of your bonus health that decays over 3 seconds (75s Cooldown).\nSterak's Fury: Triggering Lifeline increases size, empowers you, removes all crowd control effects on you (except Airborne), and grants 30% Tenacity for 4 seconds.\n3200\nSterak's Gage TIPS: This item is perfect for champions who need survivability in team fights, especially for tanks and fighters who take frontline positions. It provides bonuses to maximum health, attack damage, and helps increase your survivability. The \"Lifeline\" effect activates when your health drops below 35%, granting a shield that absorbs damage, helping you survive heavy hits. \"Sterak's Fury\" empowers you, increases your size, removes crowd control effects (except airborne), and grants 30% tenacity for 4 seconds, allowing you to survive and continue fighting through heavy crowd control and burst damage from enemies. This item is especially useful against champions with burst damage, such as Zed and Talon, and against champions with heavy CC, like Lissandra and Nautilus.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753305021_steraks-gage.webp"
        ))
        add(WildRiftItem(
            id = "iceborn_gauntlet_defense",
            name = "Guantelete de Hielo",
            category = "Defensa",
            goldCost = 3000,
            stats = "+300 Max Health • +50 Armor • +250 Max Mana • +30 Ability Haste",
            passive = "Iceborn Gauntlet\nAttacks create a slowing field\n+300 Max Health\n+50 Armor\n+250 Max Mana\n+30 Ability Haste\nSpellblade: Using an ability causes your next attack within 10 seconds to deal bonus physical damage equal to (100% base AD  + 25% Bonus Armor ) in an area and creates an icy field for 2 seconds that slows by 30%. Armor increases the size of the icy field. (1.5s Cooldown)\nDamage is reduced vs structures.\n3000\nIceborn Gauntlet TIPS: This item greatly boosts your health, armor, mana, and ability haste, making you much tankier. After casting an ability, your next attack in an area deals bonus physical damage and creates an icy field that slows enemies inside by 30%. The field’s size scales with your armor. This makes the item a great choice for champions who want to combine high survivability with crowd control and extra AOE damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389028_iceborn-gauntlet.webp"
        ))
        add(WildRiftItem(
            id = "dead_man_s_plate_defense",
            name = "Coraza del Muerto",
            category = "Defensa",
            goldCost = 2800,
            stats = "+350 Max Health • +70 Armor",
            passive = "Dead Man's Plate\nIncreases Movement Speed\n+350 Max Health\n+70 Armor\nRelentless: +5% Move Speed.\nMomentum: Moving builds Momentum, granting up to 40 Move Speed at 100 stacks. Attacking removes all Momentum. Stacks decay when movement is impaired.\nCrushing Blow: Attacks deal up to 100 bonus magic damage based on Momentum removed. Melee attacks with max Momentum slows by 75% for 1 second.\n2800\nDead Man's Plate TIPS: This item provides a substantial boost to health and armor, and its “Momentum” passive builds movement speed as you move—up to a cap—until you land an attack, which then triggers “Crushing Blow”, dealing bonus magic damage based on the momentum removed and slowing enemies at full stacks. This makes it an excellent choice for tanks and bruisers who need extra mobility to engage quickly and sustain through fights.  Tips: ideal for champions who need to close distance and absorb damage; pairs extremely well with Spellblade items (e.g., Divine Sunderer) for additional burst damage on engage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389106_dead-mans-plate.webp"
        ))
        add(WildRiftItem(
            id = "zeke_s_convergence_support",
            name = "Convergencia de Zeke",
            category = "Defensa",
            goldCost = 2700,
            stats = "+40 Armor • +350 Max Health • +150 Max Mana • +15 Ability Haste",
            passive = "Zeke's Convergence\nBoosts allies Attack Damage\n+40 Armor\n+350 Max Health\n+150 Max Mana\n+15 Ability Haste\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 seconds. Your blizzard deals a maximum of 320–600 damage, slows enemies by 25% and leaves a trail behind you. Allied champions on the trail gain 40 bonus Movement Speed for 1 second. (30s Cooldown)\n2700\nZeke's Convergence TIPS: This item is perfect for tanky support champions who initiate fights and provide frontline crowd control. It grants armor, health, mana, and ability haste. When you cast your ultimate, an icy blizzard surrounds you, dealing damage and slowing enemies, while leaving a trail that grants bonus movement speed to allies. During the effect, the attacks of a nearby marked ally deal additional magic damage, giving your team a powerful advantage in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp"
        ))
        add(WildRiftItem(
            id = "death_s_dance_defense",
            name = "Danza de la Muerte",
            category = "Defensa",
            goldCost = 3100,
            stats = "+35 Attack Damage • +40 Armor • +15 Ability Haste",
            passive = "Death's Dance\nDelays damage taken\n+35 Attack Damage\n+40 Armor\n+15 Ability Haste\nDefy: Champion takedowns cleanse Cauterize's remaining damage pool and restores 8% of your maximum health over 2 seconds.\nCauterize: 27% of all physical damage and magic damage received (12% for ranged champions) is dealt to you over 3 seconds as true damage instead.\n3100\nDeath's Dance TIPS: This item converts incoming damage into a delayed effect, letting you stay in fights longer and smooth out damage spikes. It boosts your survivability with armor and ability haste, and successful takedowns cleanse the delayed damage while instantly healing you.  — Perfect for bruisers and tanks who need to absorb bursts of damage and then quickly recover to keep fighting.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753301539_yordle-deaths-dance.webp"
        ))
        add(WildRiftItem(
            id = "winter_s_approach_defense",
            name = "Winter's Approach",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Max Health • +500 Max Mana • +15 Ability Haste",
            passive = "Winter's Approach\nConverts Mana to Health\n+350 Max Health\n+500 Max Mana\n+15 Ability Haste\nAwe: Grants bonus health equal to 8% of max Mana and refunds 15% of all Mana spent.\nMana Charge: Increases max Mana by 12 every attack, when Mana is spent or when taking damage from champions, epic monsters, or towers. Caps at 700 bonus Mana, transforming Winter's Approach into Fimbulwinter. Triggers up to 3 times every 10 seconds. You may only carry one Tear of the Goddess item at a time.\n2600\nWinter's Approach TIPS: This item is perfect for defensive champions, especially tanks who rely on frequent ability casts and auto-attacks. It provides bonuses to health, maximum mana, and ability haste, while also refunding a portion of mana spent, helping you stay in fights longer. The \"Mana Charge\" effect increases your maximum mana on each basic attack, mana expenditure, or when taking damage from champions, epic monsters, or turrets, allowing you to build a massive mana pool and eventually transform the item into a more powerful version. Combined with its health and defensive stats, this grants immense survivability and the ability to cast spells more often—ideal for spell-weaving tanks who can build huge shields and effectively control engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389175_winters-approach.webp"
        ))
        add(WildRiftItem(
            id = "fimbulwinter_defense",
            name = "Fimbulwinter",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Max Health • +1200 Max Mana • +15 Ability Haste",
            passive = "Fimbulwinter\nConverts Mana to Health\n+350 Max Health\n+1200 Max Mana\n+15 Ability Haste\nAwe: Grants bonus health equal to 10% of max Mana and refunds 15% of all Mana spent.\nFrozen Colossus: Immobilizing or slowing an enemy champion consumes 3% current mana and grants a shield for 3 seconds, absorbing 90-180 +4.5% current Mana, increased by 80% if there is more than 1 enemy champion nearby.\nOnly triggers when above 20% max Mana. (8s cooldown).\nShield is 50% effective for ranged champions.\n2600\nFimbulwinter TIPS: This item provides massive mana, health, and ability haste, and its “Frozen Colossus” passive consumes mana when you slow or immobilize an enemy to grant a strong shield that scales with your mana pool and increases near multiple enemies. Perfect for spell-weaving tanks needing extra protection from their mana reserves.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389109_fimbulwinter.webp"
        ))
        add(WildRiftItem(
            id = "force_of_nature_defense",
            name = "Fuerza de la Naturaleza",
            category = "Defensa",
            goldCost = 2750,
            stats = "+350 Max Health • +60 Magic Resistance • +5% Move Speed",
            passive = "Force of Nature\nStacking Magic Resist and Move Speed\n+350 Max Health\n+60 Magic Resistance\n+5% Move Speed\nAbsorb: Taking ability damage from enemy champions grants 1 stack(s) of Steadfast for 7 seconds, max 4 stacks. Receiving damage from an enemy Champion will refresh the duration of the stacks. At maximum stacks, gain 10% Movement Speed and reduce all incoming magic damage by 20%.\n2750\nForce of Nature TIPS: This item provides a substantial boost to health and magic resistance, and its “Absorb” passive stacks up when you take ability damage from enemy champions, reducing all incoming magic damage and granting bonus movement speed at max stacks. It’s perfect for tanks who need to withstand teams heavy in magic damage and maintain mobility to be in the right position during fights. Pick this up when the enemy team builds magic damage (e.g., Syndra, Brand) and you need extra movement speed to initiate effectively or escape dangerous situations.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389153_force-of-nature.webp"
        ))
        add(WildRiftItem(
            id = "frozen_heart_support",
            name = "Corazón de Hielo",
            category = "Defensa",
            goldCost = 2650,
            stats = "+80 Armor • +250 Max Mana • +20 Ability Haste",
            passive = "Frozen Heart\nReduces all nearby enemies Attack Speed\n+80 Armor\n+250 Max Mana\n+20 Ability Haste\nWinter's Caress: Basic attacks and magic damage caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the enemy champion for 3 seconds. Each stack of Chill slows enemy attack speed by 9%, up to a maximum of 4 stacks or 36% attack speed reduction. Each individual ability has a 3 seconds cooldown on applying Chill stacks.\n2650\nFrozen Heart TIPS: This item is ideal for tanks and support champions who need to slow enemy attack speed and maintain a healthy mana pool. It provides substantial bonuses to armor, mana, and ability haste. The “Winter’s Caress” passive applies up to four stacks of Chill on enemy champions through your basic attacks, abilities, or any magic damage they take—each stack slows their attack speed by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389159_frozen-heart.webp"
        ))
        add(WildRiftItem(
            id = "dawnshroud_support",
            name = "Manto del Amanecer",
            category = "Defensa",
            goldCost = 2700,
            stats = "+250 Max Health • +50 Armor • +30 Magic Resistance",
            passive = "Dawnshroud\nImmobilize effects damage and reveal around you\n+250 Max Health\n+50 Armor\n+30 Magic Resistance\nDawnbringer: When you immobilize a champion champion or are immobilized within 400 units of an enemy champion, reveal all nearby enemy champions for 3 seconds, deal magic damage equal to 40 + 2.5% bonusand gain 20% Armor and Magic Resistance (3s Cooldown)\n2700\nDawnshroud TIPS: This item is great for tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby champions, deals an explosive burst of magic damage, and briefly boosts your defenses. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp"
        ))
        add(WildRiftItem(
            id = "amaranth_s_twinguard_defense",
            name = "Protección Gemela de Amaranth",
            category = "Defensa",
            goldCost = 3100,
            stats = "+60 Armor • +60 Magic Resistance",
            passive = "Amaranth's Twinguard\nIn-combat durability\n+60 Armor\n+60 Magic Resistance\nEndurance: Gain 1 stacks of Endurance every 1 seconds while in combat with enemy champions (max 5 stacks). At maximum stacks, gain 20% size, 20% Tenacity, and increase Armor by 30% and Magic Resistance by 30% until out of combat with champion.\n3100\nAmaranth's Twinguard TIPS: This item is perfect for champions who spend a lot of time in the thick of fights and need extra durability and crowd control resistance. It provides bonuses to armor and magic resistance. The “Endurance” passive stacks up to five times during combat, and at full stacks you increase in size, gain enhanced tenacity, and receive bonus armor and magic resistance until you exit combat. This allows you to stay in the frontline longer and withstand enemy attacks more effectively. Due to its versatile utility, this item is one of the most popular defensive choices in the game and is used by the majority of tanks, fighters, and other classes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389236_amaranths-twinguard.webp"
        ))
        add(WildRiftItem(
            id = "mantle_of_the_twelfth_hour_defense",
            name = "Manto de la Duodécima Hora",
            category = "Defensa",
            goldCost = 2900,
            stats = "+200 Max Health • +40 Armor • +40 Magic Resistance",
            passive = "Mantle of the Twelfth Hour\nIncreases max Health when your Health is low\n+200 Max Health\n+40 Armor\n+40 Magic Resistance\nLifeline: Damage that puts you under 35%, grants bonus max Health equal to 180 + 45% bonus health for 3 seconds, and provides 50% Slow Resistance and 30 Movement Speed for 3 seconds. (70s Cooldown)\n2900\nMantle of the Twelfth Hour TIPS: This item is perfect for champions who find themselves on the brink of death and need an instant survivability boost. When you take damage that drops you below 35% health, you gain bonus maximum health, significant movement speed, and high slow resistance for a short duration. This gives you the chance to escape danger or stay in the fight. The item is especially effective for tanks and bruisers who need to endure critical moments while retaining mobility at low health.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389204_mantle-of-the-twelfth-hour.webp"
        ))
        add(WildRiftItem(
            id = "searing_crown_defense",
            name = "Corona Abrasadora",
            category = "Defensa",
            goldCost = 2700,
            stats = "+300 Max Health • +50 Armor",
            passive = "Searing Crown\nAttacks and damaging abilities burn enemies\n+300 Max Health\n+50 Armor\nFiery Touch: After dealing damage with an attack or ability, burn target for 3 seconds, dealing 1.4% of the target’s maximum health as magic damage per second (damage reduced to 0.8% for ranged users).\nDeals 150% damage to minions and monsters.\nMaximum 125 damage to monsters.\n2700\nSearing Crown TIPS: This item is perfect for champions who want to combine high survivability with bonus health-percentage damage. It provides a substantial boost to health and armor, and its “Fiery Touch” passive burns targets on hit with attacks or abilities, dealing magic damage equal to a percentage of their maximum health. This makes it effective against tanks and high-health champions, while also speeding up waveclear and jungle clear. Ideal for tanks and bruisers who want to leave a mark in fights while staying durable.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389203_searing-crown.webp"
        ))
        add(WildRiftItem(
            id = "heartsteel_defense",
            name = "Corazón de Acero",
            category = "Defensa",
            goldCost = 3000,
            stats = "+700 Max Health • +150% Health Regen • +20 Ability Haste",
            passive = "Heartsteel\nIncrease Maximum Health\n+700 Max Health\n+150% Health Regen\n+20 Ability Haste\nColossal Consumption: While within 700 units of an enemy champion, charges for 2.5 seconds before dealing a huge strike against the enemy champion. This charged attack deals bonus physical damage equal to 140 + 3.5% of maximum Health, and grants maximum Health equal to 15% of the damage dealt. The charge for each target has a 20 second cooldown.\n3000\nHeartsteel TIPS: This item is perfect for tanks and bruisers who want to combine maximum survivability with massive burst damage against enemy champions. It provides a huge health pool, enhanced out-of-combat regeneration, and ability haste.  The “Colossal Consumption” passive requires a 2.5-second charge when near an enemy champion, after which your next strike deals significant bonus physical damage based on your max health and grants you 15% of the damage dealt as bonus health. This allows you to both absorb damage and heal during skirmishes, making the item an excellent choice for extended fights and closing out teamfights.  Excellent synergy with Spirit Visage: the healing amplification and regeneration boost from Spirit Visage further enhance the health restoration from this item’s passive, providing incredible survivability and sustain in combat.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389201_heartsteel.webp"
        ))
        add(WildRiftItem(
            id = "titanic_hydra_defense",
            name = "Hidra Titánica",
            category = "Defensa",
            goldCost = 3000,
            stats = "+450 Max Health • +40 Attack Damage",
            passive = "Titanic Hydra\nAttacks deal bonus damage in a area\n+450 Max Health\n+40 Attack Damage\nCleave: Every 1.75 second(s), your next attack deals bonus physical damage equal to 25 + 3% bonus (also applies to turrets), creating a shockwave that deals physical damage equal to 80 + 10% bonus to enemies behind the target. Ranged champions deal 75% of the damage.\n3000\nTitanic Hydra TIPS: This item turns your basic attacks into an AOE tool: periodically your next hit becomes a sweeping strike that deals bonus physical damage to nearby enemies and affects targets behind the primary hit. It speeds up waveclear, adds extra damage in teamfights, and helps pressure structures when built appropriately. Best suited for melee bruisers and tanks who combine a big health pool with frequent autos — great for players who want impact both in 1v1 trades and prolonged engagements.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753304937_titanic-hydra.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            category = "Defensa",
            goldCost = 2600,
            stats = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Redemption\nHeal allies in an area\n+150 Max Health\n+50 Ability Power\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Health (based on ally's level) to allied units and deal 10% of max as true damage to enemy champions. (60s Cooldown)\nCan be cast while dead.\n2600\nRedemption TIPS: This item is designed to provide game-changing team support. Its active restores health to all allied units in a large area while dealing true damage to enemy champions, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "kaenic_rookern_defense",
            name = "Rookern Kaénico",
            category = "Defensa",
            goldCost = 2800,
            stats = "+350 Max Health • +100% Health Regen • +85 Magic Resistance",
            passive = "Kaenic Rookern\nGains a magic shield when out of combat\n+350 Max Health\n+100% Health Regen\n+85 Magic Resistance\nMagebane: After not taking magic damage for 12 seconds, gain a magic shield that absorbs damage equal to 50-150 + 14% of max Health.\n2800\nKaenic Rookern TIPS: This item is perfect for champions who need extra protection against magic damage, particularly when spending some time out of combat. It provides significant bonuses to health, regeneration, and magic resistance. The “Magebane” passive activates after 12 seconds without taking magic damage, granting you a magic shield that absorbs damage based on your maximum health. This allows you to safely recover between fights and confidently re-enter combat. It’s especially effective against teams heavy in AP damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389228_kaenic-rookern.webp"
        ))
        add(WildRiftItem(
            id = "yordle_trap_support",
            name = "Trampa Yordle",
            category = "Defensa",
            goldCost = 2600,
            stats = "+350 Max Health • +40 Armor • +15 Ability Haste",
            passive = "Yordle Trap\nRecommended for use with displacement abilities\n+350 Max Health\n+40 Armor\n+15 Ability Haste\nCatcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Movement Speed for 3 second(s) and mark the target, reducing their Armor and Magic Resist by 5–12 for 8 second(s). If the target dies while they are marked, their death grants 100–140 bonus gold () that will be evenly shared among you and nearby allies.\nThis bonus gold can only be obtained once every 10 second(s).\n2600\nYordle Trap TIPS: This item is designed for champions with displacement abilities and strong engage tools. Successfully displacing an enemy grants you bonus movement speed while marking the target, reducing their Armor and Magic Resistance to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive bonus gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389328_yordle-trap.webp"
        ))
        add(WildRiftItem(
            id = "radiant_virtue_defense",
            name = "Virtud Radiante",
            category = "Defensa",
            goldCost = 2850,
            stats = "+300 Max Health • +45 Armor • +15 Ability Haste",
            passive = "Radiant Virtue\nHeal allies upon casting your ultimate ability.\n+300 Max Health\n+45 Armor\n+15 Ability Haste\nGuiding Light: Upon casting your ultimate ability, you Transcend, increasing your max Health by 10% for 6s. While Transcended, allied champions within 1,200 units of you heal for 2.5% of your max Health per second over the duration. (60s cooldown) If you're a ranged champion, heals granted are reduced by 50%.\n2850\nRadiant Virtue TIPS: This item boosts your durability by granting extra max health, armor, and ability haste. Its passive causes you to transcend after casting your ultimate: you temporarily raise your max health, and nearby allies are healed based on that boosted health. The healing is reduced for ranged champions. A strong pick for frontliners and supports who want to survive engages while providing teamwide sustain during fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-10/1760128035_radiant-virtue.webp"
        ))
        add(WildRiftItem(
            id = "abyssal_mask_defense",
            name = "Máscara Abisal",
            category = "Defensa",
            goldCost = 3000,
            stats = "+400 Max Health • +55 Magic Resistance • +10 Ability Haste",
            passive = "Abyssal Mask\nReduces the Magic Resist of nearby enemies and increases yours\n+400 Max Health\n+55 Magic Resistance\n+10 Ability Haste\nUnmake: Curse enemy champions within 600 units, reducing their Magic Resist by 5 plus 1.2% bonus, up to 25 Magic Resist. For each enemy champion cursed, gain 9 bonus Magic Resist.\n3000\nAbyssal Mask TIPS: This item is a solid anti-magic pickup: it boosts your magic resistance while reducing the magic resist of nearby enemies, making them easier to shred with spell damage. Great for tanks and frontliners who need to both soak magic damage and amplify their team’s ability to take down AP threats and durable targets.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767912720_abyssal-mask.webp"
        ))
        add(WildRiftItem(
            id = "hollow_radiance_defense",
            name = "Fulgor Vacuo",
            category = "Defensa",
            goldCost = 2800,
            stats = "+400 Max Health • +40 Magic Resistance • +15 Ability Haste",
            passive = "Hollow Radiance\nDeals damage in an area\n+400 Max Health\n+40 Magic Resistance\n+15 Ability Haste\nImmolate: While in combat, deal magic damage equal to 20–30 plus 1% of bonus per second for 5 second(s) to nearby enemies. Deals 125% damage against monsters and 200% damage against minions.\nDesolate: Killing a neutral monster or an enemy deals magic damage equal to 30 plus 2% of bonus in an area around them.\n2800\nHollow Radiance TIPS: This item turns you into a steady source of pressure in fights: while engaged, it emits an area magic damage aura that helps clear waves and punish nearby small targets. On killing a neutral or enemy, it detonates for area damage, making it great for fast clears and threat creation when entering skirmishes. Perfect for tanks and frontline bruisers who need to hold the center of fights and force opponents into mistakes.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767913515_6664_11zon.webp"
        ))
        add(WildRiftItem(
            id = "knight_s_vow_support",
            name = "Promesa de Caballero",
            category = "Defensa",
            goldCost = 2500,
            stats = "+400 Max Health • +40 Armor • +10 Ability Haste",
            passive = "Knight's Vow\nRedirect damage to yourself and restore Health\n+400 Max Health\n+40 Armor\n+10 Ability Haste\nPledge: While in combat, deal magic damage equal to 20–30 plus 1% of bonus Health per second for 5 second(s) to nearby enemies. Deals 125% damage against monsters and 200% damage against minions.\nSacrifice: Killing a neutral monster or an enemy deals magic damage equal to 30 plus 2% of bonus Health in an area around them.\n2500\nKnight's Vow TIPS: This item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767914013_3109_11zon.webp"
        ))
        add(WildRiftItem(
            id = "overlord_s_bloodmail_defense",
            name = "Armadura Sangrienta del Señor Supremo",
            category = "Defensa",
            goldCost = 3200,
            stats = "+450 Max Health • +30 Attack Damage",
            passive = "Overlord's Bloodmail\nGain Attack Damage when losing Health\n+450 Max Health\n+30 Attack Damage\nTyranny: Gain Attack Damage equal to 2.5% of your bonus Health.\nRetribution: Gain up to 9% increased Attack Damage based on your missing Health. Maximum Retribution bonus while below 30% Health.\n3200\nOverlord's Bloodmail TIPS: This item converts bonus health into attack power and ramps up your damage when you drop into dangerous HP ranges — a hybrid pick for players who want to be both tanky and threatening. It suits bruisers and solo laners who stack health and embrace high-risk, high-reward skirmishes: the more bonus health you have, the stronger your raw attacks become, and when you fight at low health you deal amplified damage. Great for aggressive duelists who win trades by trading survivability for burst.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630274_overlords-bloodmail.webp"
        ))
        add(WildRiftItem(
            id = "unending_despair_defense",
            name = "Desesperación Eterna",
            category = "Defensa",
            goldCost = 3000,
            stats = "+200 Max Health • +45 Armor • +45 Magic Resistance",
            passive = "Unending Despair\nIncreases tanks' sustain in teamfights\n+200 Max Health\n+45 Armor\n+45 Magic Resistance\nAnguish: Every 4 second(s) while in combat with a champion, deal 3% of your max Health as magic damage to nearby champions and heal for 250% of the damage dealt. Anguish is unaffected by Item Ability Haste.\n3000\nUnending Despair TIPS: This item turns you into a self-sustaining frontline: while fighting you periodically deal magic damage around you and heal for a portion of that damage. Great for tanks and frontliners — it helps you soak focus, remain in the heart of fights longer, and excel in extended team engagements. Less effective in very short burst trades or on champions that avoid standing in the center of combat.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-03/1772630350_unending-despair.webp"
        ))
        add(WildRiftItem(
            id = "banshee_s_veil_defense",
            name = "Velo de la Banshee",
            category = "Defensa",
            goldCost = 3000,
            stats = "+105 Ability Power • +40 Magic Resistance",
            passive = "Banshee's Veil\nBlocks an enemy ability\n+105 Ability Power\n+40 Magic Resistance\nAnnul: Grants a spell shield that blocks the next hostile ability. (30s Cooldown)\n3000\nBanshee's Veil TIPS: This item provides strong protection against magic damage while granting a spell shield that blocks the next hostile ability. It is especially effective against champions who rely on landing a single key spell to start their combo or burst you down. A great choice for mages and AP fighters who need to maintain safe positioning and deny enemy engage or pick potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783192846_3102_11zon.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            category = "Defensa",
            goldCost = 3300,
            stats = "+40 Armor • +110 Ability Power",
            passive = "Zhonya's Hourglass\nTurn invulnerable\n+40 Armor\n+110 Ability Power\nStasis (Active): Become invulnerable and untargetable for 2.5 seconds, but unable to move, attack, cast abilities or use items. (90s Cooldown)\n3300\nZhonya's Hourglass TIPS: This item combines high Ability Power with extra armor, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "gargoyle_stoneplate_active",
            name = "Protector Pétreo",
            category = "Defensa",
            goldCost = 2900,
            stats = "+200 Max Health • +45 Armor • +45 Magic Resistance • +10 Ability Haste",
            passive = "Gargoyle Stoneplate\nShield\n+200 Max Health\n+45 Armor\n+45 Magic Resistance\n+10 Ability Haste\nStoneplate (Active): Gain a base shield that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Cooldown)\n2900\nGargoyle Stoneplate TIPS: This item greatly increases your survivability during teamfights. Its active grants a powerful shield that scales with your bonus Health, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp"
        ))
        add(WildRiftItem(
            id = "bulwark_of_the_mountain_support",
            name = "Baluarte de la Montaña",
            category = "Soporte",
            goldCost = 0,
            stats = "+175 Max Health • +10 Ability Haste",
            passive = "Bulwark of the Mountain\nKill minions to earn bonus gold\n+175 Max Health\n+10 Ability Haste\nSoulcast: Every 60 seconds, gains 75 gold, 25 Health and 2 Attack Damage, or 4 Ability Power (Adaptive); up to 250 Health and 20 Attack Damage or 40 Ability Power (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When out of combat, gain 10% Movement Speed when you move toward your Perfect Partner. If you're more than 2,500 units apart, this bonus increases to 30%.\n0\nBulwark of the Mountain TIPS: This item is designed for support players and grants passive gold income every 60 seconds along with Soulforce stacks that boost your health, attack damage, or ability power. At 10 stacks, you gain a significant adaptive stat bonus. While it reduces gold from killing minions and monsters, it accelerates your team’s economic pace. An additional effect deals extra damage to revealed Sight Wards, making it easier to clear vision and maintain map control.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389518_bulwark-of-the-mountain.webp"
        ))
        add(WildRiftItem(
            id = "black_mist_scythe_support",
            name = "Guadaña de Niebla Negra",
            category = "Soporte",
            goldCost = 0,
            stats = "+10 Ability Haste",
            passive = "Black Mist Scythe\nAttack champions and structures to gain bonus gold\n+10 Ability Haste\nVersatile: Gain 14 Attack Damage or 28 Ability Power (Adaptive).\nSoulcast: Every 60 seconds, gains 75 gold, 25 Health and 2 Attack Damage, or 4 Ability Power (Adaptive); up to 250 Health and 20 Attack Damage, or 40 Ability Power (Adaptive).\nDeal 2 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom. When out of combat, gain 10% Movement Speed when you move toward your Perfect Partner. If you're more than 2,500 units apart, this bonus increases to 30%.\n0\nBlack Mist Scythe TIPS: This item is designed for support players, granting passive bonuses to gold and stats. It reduces your gold from killing minions and monsters but provides 75 gold and 1 Soulforce stack every 60 seconds. Each Soulforce stack adaptively grants health, attack damage, or ability power, and at 10 stacks you gain a significant bonus to one of these stats. The item also increases your effectiveness in clearing vision by dealing extra damage to revealed enemy wards.  Ideal for map-control–focused supports who want to help their team without worrying about farming; you’ll steadily generate resources and strengthen your utility for both protect and peel.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389555_black-mist-scythe.webp"
        ))
        add(WildRiftItem(
            id = "morellonomicon_support",
            name = "Morellonomicon",
            category = "Soporte",
            goldCost = 2650,
            stats = "+300 Max Health • +75 Ability Power • +15 Ability Haste",
            passive = "Morellonomicon\nMagic damage reduces enemy healing\n+300 Max Health\n+75 Ability Power\n+15 Ability Haste\nAffliction: Dealing magic damage to enemy champions inflicts 50% Grievous Wounds for 3 seconds.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n2650\nMorellonomicon TIPS: This item is designed to counter champions with strong healing and sustain. Any magic damage you deal applies Grievous Wounds, greatly reducing the effectiveness of enemy healing and regeneration. It is an excellent choice for mages and AP supports against teams that rely heavily on healing, lifesteal, or regeneration.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388237_morellonomicon.webp"
        ))
        add(WildRiftItem(
            id = "ardent_censer_support",
            name = "Incensario Ardiente",
            category = "Soporte",
            goldCost = 2700,
            stats = "+250 Max Health • +45 Ability Power • +50% Mana Regen • +10 Ability Haste • +5% Heal and Shield Strength • +5% Move Speed.",
            passive = "Ardent Censer\nIncreases allies Attack Speed\n+250 Max Health\n+45 Ability Power\n+50% Mana Regen\n+10 Ability Haste\n+5% Heal and Shield Strength\n+5% Move Speed.\nCenser: When you heal or shield, an allied champion other than yourself, they gain 15-34% Attack Speed and their attacks deal 16-22 bonus magic damage. for 6 seconds. This damage can Critically Strike.\n2700\nArdent Censer TIPS: This item enhances your heals and shields, granting shielded allies increased attack speed and bonus magic damage on their attacks for a short duration. Perfect for enchanter supports who want to protect and empower their carries when it matters most.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388425_ardent-censer.webp"
        ))
        add(WildRiftItem(
            id = "harmonic_echo_support",
            name = "Eco Armónico",
            category = "Soporte",
            goldCost = 2800,
            stats = "+100 Max Health • +50 Ability Power • +300 Max Mana • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Harmonic Echo\nAbilities grant healing effects\n+100 Max Health\n+50 Ability Power\n+300 Max Mana\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nHarmonic Echo: Moving and casting abilities builds Harmony. At 100 Harmony your next healing or shielding ability on an ally restore an additional equal to (100-160 () + 15% AP) Health. If the target has less than 30% Health, heal effectiveness is increased to 130% of the original.\n2800\nHarmonic Echo TIPS: This item greatly enhances your healing and shielding capabilities. Moving and casting abilities builds Harmony, empowering your next heal or shield with additional healing, while allies at low health receive an even stronger recovery. It is an excellent choice for enchanter supports who focus on keeping their team alive and saving allies during critical moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388518_yordle-harmonic-echo.webp"
        ))
        add(WildRiftItem(
            id = "staff_of_flowing_water_support",
            name = "Staff of Flowing Water",
            category = "Soporte",
            goldCost = 2500,
            stats = "+100 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Staff of Flowing Water\nEnhance allies Ability Power and Ability Haste\n+100 Max Health\n+50 Ability Power\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nRapids: Healing or shielding an ally grants you both +15  Ability Haste and 30-50  (based on target's level) Ability Power for 6 seconds.\n2500\nStaff of Flowing Water TIPS: This item is perfect for champions who rely on healing or shielding abilities to support their team. It provides bonuses to maximum health, ability power, ability haste, and amplifies healing and shielding effects. The \"Rapids\" effect activates when you heal or shield an ally, granting both you and your ally bonuses to ability haste and ability power for 6 seconds. This item is especially useful for champions who actively support their team, such as Soraka, Nami, or Lulu.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388553_staff-of-flowing-water.webp"
        ))
        add(WildRiftItem(
            id = "oceanid_s_trident_support",
            name = "Tridente de Oceánida",
            category = "Soporte",
            goldCost = 2600,
            stats = "+200 Max Health • +80 Ability Power • +10 Ability Haste",
            passive = "Oceanid's Trident\nAnti-Shielding\n+200 Max Health\n+80 Ability Power\n+10 Ability Haste\nLethal Weapon: Dealing ability damage to an enemy champion reduces any shields they gain for 3 seconds. Area of effect abilities apply (5% of bonus AP + 25)% shield reduction, capped at 45%; while single target abilities apply (5% of bonus AP + 40)% shield reduction, capped at 60%. When you damage an enemy who is unaffected by Lethal Weapon, all shields on them are reduced by the same values.\n2600\nOceanid's Trident TIPS: This item is designed to counter shield-heavy champions. Your abilities significantly reduce the effectiveness of shields enemies receive, while the first hit can also weaken shields that are already active. It is an excellent choice for mages and AP supports against shield-reliant compositions, allowing your team to break through enemy defenses and eliminate priority targets more effectively.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388583_oceanids-trident.webp"
        ))
        add(WildRiftItem(
            id = "imperial_mandate_support",
            name = "Imperial Mandate",
            category = "Soporte",
            goldCost = 2500,
            stats = "+200 Max Health • +50 Ability Power • +20 Ability Haste",
            passive = "Imperial Mandate\nCrowd control grants additional ally damage\n+200 Max Health\n+50 Ability Power\n+20 Ability Haste\nCoordinated Fire: Abilities that Slow or Immobilize a champion deal 47-75 bonus magic damage and marks them for 4 seconds (6 seconds cooldown per enemy champion). Allied champion damage detonates the mark, dealing an additional 94-150 magic damage (based on ally level) and granting you both 20% Move Speed, for 2 seconds.\n2500\nImperial Mandate TIPS: This item is perfect for support champions who have abilities that slow or immobilize enemies, allowing you to activate effects for your team. It provides bonuses to maximum health, ability power, and ability haste, helping you deal damage while also enhancing teamwork with your allies. The \"Coordinated Fire\" effect activates when you slow or immobilize an enemy, dealing bonus magic damage and marking them for 4 seconds. When an allied champion damages the marked target, it detonates the mark, dealing additional magic damage and granting both you and your ally 20% bonus movement speed for 2 seconds. This item is especially useful for champions with crowd control abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753388613_imperial-mandate.webp"
        ))
        add(WildRiftItem(
            id = "zeke_s_convergence_support",
            name = "Convergencia de Zeke",
            category = "Soporte",
            goldCost = 2700,
            stats = "+40 Armor • +350 Max Health • +150 Max Mana • +15 Ability Haste",
            passive = "Zeke's Convergence\nBoosts allies Attack Damage\n+40 Armor\n+350 Max Health\n+150 Max Mana\n+15 Ability Haste\nHarbinger: Casting your ultimate surrounds you with a blizzard and ignites a nearby ally's attacks for 10 seconds. Your blizzard deals a maximum of 320–600 damage, slows enemies by 25% and leaves a trail behind you. Allied champions on the trail gain 40 bonus Movement Speed for 1 second. (30s Cooldown)\n2700\nZeke's Convergence TIPS: This item is perfect for tanky support champions who initiate fights and provide frontline crowd control. It grants armor, health, mana, and ability haste. When you cast your ultimate, an icy blizzard surrounds you, dealing damage and slowing enemies, while leaving a trail that grants bonus movement speed to allies. During the effect, the attacks of a nearby marked ally deal additional magic damage, giving your team a powerful advantage in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389063_zekes-convergence.webp"
        ))
        add(WildRiftItem(
            id = "frozen_heart_support",
            name = "Corazón de Hielo",
            category = "Soporte",
            goldCost = 2650,
            stats = "+80 Armor • +250 Max Mana • +20 Ability Haste",
            passive = "Frozen Heart\nReduces all nearby enemies Attack Speed\n+80 Armor\n+250 Max Mana\n+20 Ability Haste\nWinter's Caress: Basic attacks and magic damage caused by you or inflicted upon you and nearby allies, will apply stacks of Chill to the enemy champion for 3 seconds. Each stack of Chill slows enemy attack speed by 9%, up to a maximum of 4 stacks or 36% attack speed reduction. Each individual ability has a 3 seconds cooldown on applying Chill stacks.\n2650\nFrozen Heart TIPS: This item is ideal for tanks and support champions who need to slow enemy attack speed and maintain a healthy mana pool. It provides substantial bonuses to armor, mana, and ability haste. The “Winter’s Caress” passive applies up to four stacks of Chill on enemy champions through your basic attacks, abilities, or any magic damage they take—each stack slows their attack speed by 9%, up to 36% at full stacks. This weakens enemy marksmen and fighters, making it harder for them to deal sustained damage in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389159_frozen-heart.webp"
        ))
        add(WildRiftItem(
            id = "dawnshroud_support",
            name = "Manto del Amanecer",
            category = "Soporte",
            goldCost = 2700,
            stats = "+250 Max Health • +50 Armor • +30 Magic Resistance",
            passive = "Dawnshroud\nImmobilize effects damage and reveal around you\n+250 Max Health\n+50 Armor\n+30 Magic Resistance\nDawnbringer: When you immobilize a champion champion or are immobilized within 400 units of an enemy champion, reveal all nearby enemy champions for 3 seconds, deal magic damage equal to 40 + 2.5% bonusand gain 20% Armor and Magic Resistance (3s Cooldown)\n2700\nDawnshroud TIPS: This item is great for tanks and support initiators. When you immobilize an enemy or are immobilized near foes, it reveals nearby champions, deals an explosive burst of magic damage, and briefly boosts your defenses. Perfect for zone control, reliable engages, and countering enemy dive attempts.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389211_dawnshroud.webp"
        ))
        add(WildRiftItem(
            id = "yordle_trap_support",
            name = "Trampa Yordle",
            category = "Soporte",
            goldCost = 2600,
            stats = "+350 Max Health • +40 Armor • +15 Ability Haste",
            passive = "Yordle Trap\nRecommended for use with displacement abilities\n+350 Max Health\n+40 Armor\n+15 Ability Haste\nCatcher: After using abilities to apply crowd control effects that displace the enemy, gain 10% Movement Speed for 3 second(s) and mark the target, reducing their Armor and Magic Resist by 5–12 for 8 second(s). If the target dies while they are marked, their death grants 100–140 bonus gold () that will be evenly shared among you and nearby allies.\nThis bonus gold can only be obtained once every 10 second(s).\n2600\nYordle Trap TIPS: This item is designed for champions with displacement abilities and strong engage tools. Successfully displacing an enemy grants you bonus movement speed while marking the target, reducing their Armor and Magic Resistance to make them easier for your team to eliminate. If the marked target dies, you and nearby allies receive bonus gold, helping your team snowball its advantage. It is an excellent choice for tanks and engage supports with knockbacks, pulls, or knock-up abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389328_yordle-trap.webp"
        ))
        add(WildRiftItem(
            id = "knight_s_vow_support",
            name = "Promesa de Caballero",
            category = "Soporte",
            goldCost = 2500,
            stats = "+400 Max Health • +40 Armor • +10 Ability Haste",
            passive = "Knight's Vow\nRedirect damage to yourself and restore Health\n+400 Max Health\n+40 Armor\n+10 Ability Haste\nPledge: While in combat, deal magic damage equal to 20–30 plus 1% of bonus Health per second for 5 second(s) to nearby enemies. Deals 125% damage against monsters and 200% damage against minions.\nSacrifice: Killing a neutral monster or an enemy deals magic damage equal to 30 plus 2% of bonus Health in an area around them.\n2500\nKnight's Vow TIPS: This item lets you act as a protective anchor for a designated ally: some of the damage they take is redirected to you, and you heal when that ally deals damage. Perfect for tanky supports and peel-focused bruisers who want to keep a carry safe — it provides a reliable way to soak focus, sustain through fights, and maintain teamfight presence.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-01/1767914013_3109_11zon.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            category = "Soporte",
            goldCost = 2600,
            stats = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Redemption\nHeal allies in an area\n+150 Max Health\n+50 Ability Power\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Health (based on ally's level) to allied units and deal 10% of max as true damage to enemy champions. (60s Cooldown)\nCan be cast while dead.\n2600\nRedemption TIPS: This item is designed to provide game-changing team support. Its active restores health to all allied units in a large area while dealing true damage to enemy champions, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "mikael_s_blessing_active",
            name = "Bendición de Mikael",
            category = "Soporte",
            goldCost = 2500,
            stats = "+300 Max Health • +50% Mana Regen • +15 Ability Haste • +9% Heal and Shield Strength",
            passive = "Mikael's Blessing\nDispels crowd control from an ally\n+300 Max Health\n+50% Mana Regen\n+15 Ability Haste\n+9% Heal and Shield Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied champion, grant them crowd control immunity for 0.2s, and heal them for 150–250 Health. (75s Cooldown)\n2500\nMikael's Blessing TIPS: This item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their health, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783571078_3222_11zon.webp"
        ))
        add(WildRiftItem(
            id = "locket_of_the_iron_solari_active",
            name = "Relicario de los Solari de Hierro",
            category = "Soporte",
            goldCost = 2600,
            stats = "+200 Max Health • +30 Armor • +30 Magic Resistance • +10 Ability Haste",
            passive = "Locket of the Iron Solari\nTeam shield\n+200 Max Health\n+30 Armor\n+30 Magic Resistance\n+10 Ability Haste\nLocket (Active):  Grants a shield to yourself and nearby allied champions that each absorbs 250-370 damage for 2.5 seconds. (60s Cooldown)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 seconds.\n2600\nLocket of the Iron Solari TIPS: This item provides powerful team-wide protection during fights. Its active grants a shield to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389710_locket-enchant.webp"
        ))
        add(WildRiftItem(
            id = "shurelya_s_battlesong_active",
            name = "Canción de Batalla de Shurelya",
            category = "Soporte",
            goldCost = 2600,
            stats = "+55 Ability Power • +50% Mana Regeneration • +20 Ability Haste • +5% Move Speed",
            passive = "Shurelya's Battlesong\nGrans allies Movement Speed\n+55 Ability Power\n+50% Mana Regeneration\n+20 Ability Haste\n+5% Move Speed\nInspiring Speech (Active): Grant nearby allies champions 30% Move Speed for 4 seconds. (60s Cooldown)\n2600\nShurelya's Battlesong TIPS: This item greatly enhances your team's mobility. Its active grants nearby allied champions a burst of movement speed, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility champions who excel at controlling the pace of teamfights and enabling their teammates.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783187148_shurelyas-battlesong.webp"
        ))
        add(WildRiftItem(
            id = "quicksilver_sash_mid_tier",
            name = "Fajín de Mercurio",
            category = "Encantamientos",
            goldCost = 1100,
            stats = "",
            passive = "Quicksilver Sash\nDispels crowd control\nQuicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 seconds.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Slow Resist for 1.5 seconds. (60s Cooldown)\nCannot be used during knock up or knock back effects.\n1100\nQuicksilver Sash TIPS: This enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and slows, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389685_quicksilver-enchant.webp"
        ))
        add(WildRiftItem(
            id = "seeker_s_armguard_mid_tier",
            name = "Guardabrazo de la Buscadora",
            category = "Encantamientos",
            goldCost = 1200,
            stats = "+20 Armor • +35 Ability Power",
            passive = "Seeker's Armguard\nTurn invulnerable\n+20 Armor\n+35 Ability Power\nStasis (Active): Become invulnerable and untargetable for 2.5 seconds, but unable to move, attack, cast abilities or use items. (120s Cooldown)\n1200\nSeeker's Armguard TIPS: This item combines Ability Power with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2022-01/1641809649_seekers-armguard.png"
        ))
        add(WildRiftItem(
            id = "stridebreaker_active",
            name = "Rompeavances",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+400 Max Health • +40 Attack Damage • +25% Attack Speed",
            passive = "Stridebreaker\nSlows enemies nearby after a short dash\n+400 Max Health\n+40 Attack Damage\n+25% Attack Speed\nBreaking Shockwave (Active): Activate to dash a short distance, dealing 100% AD as Physical Damage to nearby enemies and slowing them by 40% for 3s (25s cooldown)\nStride (Passive): Gain 20 Movement Speed for 2 second(s) when you deal physical damage.\n3100\nStridebreaker TIPS: This item combines mobility, damage, and crowd control, making it easier to stick to your targets. Its active lets you dash a short distance, damage nearby enemies, and heavily slow them, while the passive grants bonus movement speed whenever you deal physical damage. It is an excellent choice for fighters and bruisers who want to engage quickly, chase down opponents, and keep enemies within melee range.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_stridebreaker.webp"
        ))
        add(WildRiftItem(
            id = "goredrinker_active",
            name = "Bebedor de Sangre",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+350 Max Health • +40 Attack Damage • +15 Ability Haste",
            passive = "Goredrinker\nDeal damage in an area\n+350 Max Health\n+40 Attack Damage\n+15 Ability Haste\nGoredrink (Passive): Gain8% Omni Vamp.\nThirsting Slash (Active): Deal 175% base AD as physical damage to nearby enemies. Restore Health equal to 20% plus 10% missing for each enemy champion hit. (12s cooldown)\n3100\nGoredrinker TIPS: This item is perfect for fighters who excel in extended combat. It grants Omni Vamp, while its active ability deals area physical damage and restores health based on the number of enemy champions hit. The more enemies you strike, the greater the healing, making it an excellent choice for diving into the middle of teamfights and surviving through heavy focus fire.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389878_goredrinker.webp"
        ))
        add(WildRiftItem(
            id = "galeforce_active",
            name = "Fuerza del Viento",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+50 Attack Damage • +25% Critical Rate • +15% Attack Speed • +5% Move Speed",
            passive = "Galeforce\nGrants a dash and damage bonus\n+50 Attack Damage\n+25% Critical Rate\n+15% Attack Speed\n+5% Move Speed\nCloudburst (Active): Dash in a target direction and fire 3 missile(s) at the lowest Health enemy near your destination, prioritizing champions. Deal physical damage equal to 40-125 () plus 35% bonus. (60s cooldown)\n3100\nGaleforce TIPS: This item greatly improves the mobility of marksmen and AD champions by granting a dash that also fires projectiles at the lowest-health nearby target. It is perfect for both finishing off weakened enemies and repositioning during fights, allowing you to dodge key abilities or quickly close the gap. An excellent choice for champions who value mobility, safety, and strong burst potential.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389842_galeforce.webp"
        ))
        add(WildRiftItem(
            id = "mercurial_scimitar_active",
            name = "Cimitarra Mercurial",
            category = "Encantamientos",
            goldCost = 3100,
            stats = "+45 Attack Damage • +10% Physical Vamp • +40 Magic Resistance",
            passive = "Mercurial Scimitar\nDispels crowd control\n+45 Attack Damage\n+10% Physical Vamp\n+40 Magic Resistance\nQuicksilver Sash (Active): Removes all crowd control debuffs from you and grants immunity to crowd control for 0.25s.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Slow Resist for 1.5 seconds. (60s Cooldown)\nCannot be used during knock up or knock back effects.\n3100\nMercurial Scimitar TIPS: This item is designed to counter crowd control. Its active removes most disabling effects and briefly grants immunity to follow-up control, while the passive provides additional resistance to crowd control and slows once the effect ends. It is an excellent choice for marksmen, fighters, and assassins who need to maintain their mobility and keep dealing damage against heavy-CC team compositions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783568239_3139_11zon.webp"
        ))
        add(WildRiftItem(
            id = "hextech_roketbelt_active",
            name = "Cinturón Protocohete Hextech",
            category = "Encantamientos",
            goldCost = 2700,
            stats = "+250 Max Health • +70 Ability Power • +20 Ability Haste",
            passive = "Hextech Roketbelt\nSmall dash\n+250 Max Health\n+70 Ability Power\n+20 Ability Haste\nProtobelt (Active): Dash forward and unleash a cone of missiles, dealing 100 plus 10% magic damage. (30s Cooldown)\nIf champions or monsters are hit by more than one missile, missiles after the first will deal only 10% damage.\n2700\nHextech Roketbelt TIPS: This item combines Ability Power with extra mobility, allowing you to quickly close the gap or reposition during combat. Its active grants a short dash while firing a cone of rockets that deal area magic damage. It is an excellent choice for AP assassins, mobile mages, and engage-oriented champions who need to dive in, secure kills, or dodge key enemy abilities.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389704_protobelt-enchant.webp"
        ))
        add(WildRiftItem(
            id = "zhonya_s_hourglass_active",
            name = "Reloj de Arena de Zhonya",
            category = "Encantamientos",
            goldCost = 3300,
            stats = "+40 Armor • +110 Ability Power",
            passive = "Zhonya's Hourglass\nTurn invulnerable\n+40 Armor\n+110 Ability Power\nStasis (Active): Become invulnerable and untargetable for 2.5 seconds, but unable to move, attack, cast abilities or use items. (90s Cooldown)\n3300\nZhonya's Hourglass TIPS: This item combines high Ability Power with extra armor, while its defining feature is the ability to become completely invulnerable for a short time. Its active effect allows you to survive lethal damage, avoid crucial enemy abilities, or buy time for your cooldowns to recover. It is an excellent choice for mages and AP assassins who need to outlive enemy focus and turn the tide of a teamfight.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389628_stasis-enchant.webp"
        ))
        add(WildRiftItem(
            id = "redemption_active",
            name = "Redención",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+150 Max Health • +50 Ability Power • +50% Mana Regen • +15 Ability Haste • +5% Heal and Shield Strength",
            passive = "Redemption\nHeal allies in an area\n+150 Max Health\n+50 Ability Power\n+50% Mana Regen\n+15 Ability Haste\n+5% Heal and Shield Strength\nIntervention (Active): Target a large area. After 2.5s, restore 150-350 Health (based on ally's level) to allied units and deal 10% of max as true damage to enemy champions. (60s Cooldown)\nCan be cast while dead.\n2600\nRedemption TIPS: This item is designed to provide game-changing team support. Its active restores health to all allied units in a large area while dealing true damage to enemy champions, making it a powerful tool for turning the tide of teamfights. The ability to cast it even after death makes it especially valuable for supports who want to continue impacting fights even after being eliminated.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389247_yordle-redeeming.webp"
        ))
        add(WildRiftItem(
            id = "gargoyle_stoneplate_active",
            name = "Protector Pétreo",
            category = "Encantamientos",
            goldCost = 2900,
            stats = "+200 Max Health • +45 Armor • +45 Magic Resistance • +10 Ability Haste",
            passive = "Gargoyle Stoneplate\nShield\n+200 Max Health\n+45 Armor\n+45 Magic Resistance\n+10 Ability Haste\nStoneplate (Active): Gain a base shield that absorbs damage equal to 100 plus 90% bonusand gain size, decayng over 2.5s. (60s Cooldown)\n2900\nGargoyle Stoneplate TIPS: This item greatly increases your survivability during teamfights. Its active grants a powerful shield that scales with your bonus Health, allowing you to withstand heavy focus fire and remain on the frontline longer. It is an excellent choice for tanks and bruisers who need to absorb large amounts of damage while protecting their team.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389735_stoneplate-enchant.webp"
        ))
        add(WildRiftItem(
            id = "mikael_s_blessing_active",
            name = "Bendición de Mikael",
            category = "Encantamientos",
            goldCost = 2500,
            stats = "+300 Max Health • +50% Mana Regen • +15 Ability Haste • +9% Heal and Shield Strength",
            passive = "Mikael's Blessing\nDispels crowd control from an ally\n+300 Max Health\n+50% Mana Regen\n+15 Ability Haste\n+9% Heal and Shield Strength\nPurify (Active): Remove all crowd control debuffs (excluding knock up and suppression) from an allied champion, grant them crowd control immunity for 0.2s, and heal them for 150–250 Health. (75s Cooldown)\n2500\nMikael's Blessing TIPS: This item is designed to protect allies from crowd control. Its active removes most disabling effects from a targeted ally, instantly restores their health, and briefly grants immunity to further crowd control. It is an excellent choice for supports who want to keep their carries alive and ensure they can continue fighting through crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783571078_3222_11zon.webp"
        ))
        add(WildRiftItem(
            id = "locket_of_the_iron_solari_active",
            name = "Relicario de los Solari de Hierro",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+200 Max Health • +30 Armor • +30 Magic Resistance • +10 Ability Haste",
            passive = "Locket of the Iron Solari\nTeam shield\n+200 Max Health\n+30 Armor\n+30 Magic Resistance\n+10 Ability Haste\nLocket (Active):  Grants a shield to yourself and nearby allied champions that each absorbs 250-370 damage for 2.5 seconds. (60s Cooldown)\nThis effect is reduced by 50% if the target has been affected by another Locket in the last 20 seconds.\n2600\nLocket of the Iron Solari TIPS: This item provides powerful team-wide protection during fights. Its active grants a shield to you and nearby allies, helping your team survive burst damage and reducing the impact of enemy engages. It is an excellent choice for tanks and supports who stay close to their teammates and want to maximize their team's survivability in teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389710_locket-enchant.webp"
        ))
        add(WildRiftItem(
            id = "shurelya_s_battlesong_active",
            name = "Canción de Batalla de Shurelya",
            category = "Encantamientos",
            goldCost = 2600,
            stats = "+55 Ability Power • +50% Mana Regeneration • +20 Ability Haste • +5% Move Speed",
            passive = "Shurelya's Battlesong\nGrans allies Movement Speed\n+55 Ability Power\n+50% Mana Regeneration\n+20 Ability Haste\n+5% Move Speed\nInspiring Speech (Active): Grant nearby allies champions 30% Move Speed for 4 seconds. (60s Cooldown)\n2600\nShurelya's Battlesong TIPS: This item greatly enhances your team's mobility. Its active grants nearby allied champions a burst of movement speed, allowing your team to engage fights, chase fleeing enemies, or disengage from dangerous situations more effectively. It is an excellent choice for supports and utility champions who excel at controlling the pace of teamfights and enabling their teammates.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783187148_shurelyas-battlesong.webp"
        ))
        add(WildRiftItem(
            id = "gluttonous_greaves_boots_t2",
            name = "Grebas Glotonas",
            category = "Botas N2",
            goldCost = 1000,
            stats = "+45 Move Speed",
            passive = "Gluttonous Greaves\nAttack Damage, Omnivamp\n+45 Move Speed\nBalance of Power: Gain 12 Attack Damage or 20 Ability Power (Adaptive).\nConversion: Gain 5% Omnivamp. Champion takedowns grant an additional 0.5% Omnivamp, up to 5%.\n1000\nGluttonous Greaves TIPS: These boots combine mobility, adaptive offensive power, and sustained healing. They increase your damage while Omnivamp restores health from all damage you deal. Champion takedowns further increase your Omnivamp, making them an excellent choice for champions who want to balance high damage output with strong sustain during extended fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389849_gluttonous-greaves.webp"
        ))
        add(WildRiftItem(
            id = "berserker_s_greaves_boots_t2",
            name = "Grebas de Berserker",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+35% Attack Speed • +45 Move Speed",
            passive = "Berserker's Greaves\nAttack Speed\n+35% Attack Speed\n+45 Move Speed\nBlessed Blade: Attacks restore 10 Health on hit.\n1200\nBerserker's Greaves TIPS: These boots grant a significant boost to attack speed and movement speed, while empowering your basic attacks with on‑hit life steal.  — A great pick for marksmen and auto‑attack bruisers who need mobility, rapid attack cadence, and constant sustain in fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389864_berserkers-greaves.webp"
        ))
        add(WildRiftItem(
            id = "mercury_s_treads_boots_t2",
            name = "Pasos de Mercurio",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+150 Max Health • +25 Magic Resistance • +30 Tenacity • +45 Move Speed",
            passive = "Mercury's Treads\nIncreases Magic resist\n+150 Max Health\n+25 Magic Resistance\n+30 Tenacity\n+45 Move Speed\n1200\nMercury's Treads TIPS: These boots increase your Magic Resistance while making you more resilient to crowd control through Tenacity. The bonus Health and movement speed improve both survivability and mobility, allowing you to perform more effectively against magic damage and heavy-CC team compositions. They are an excellent choice for tanks, fighters, and any champion who needs to stay in the fight longer.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389863_mercurys-treads.webp"
        ))
        add(WildRiftItem(
            id = "plated_steelcaps_boots_t2",
            name = "Punteras Revestidas",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+150 Max Health • +20 Armor • +45 Move Speed",
            passive = "Plated Steelcaps\nReduces damage from champion attacks\n+150 Max Health\n+20 Armor\n+45 Move Speed\nBlock: Reduces damage from champion attacks by 10%.\n1200\nPlated Steelcaps TIPS: These boots provide reliable protection against champions who rely heavily on basic attacks. They increase your Health and Armor, while the passive further reduces damage taken from enemy champion attacks. An excellent choice against marksmen, AD fighters, and other auto-attack-focused champions.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389651_plated-steelcaps.webp"
        ))
        add(WildRiftItem(
            id = "ionian_boots_of_lucidity_boots_t2",
            name = "Botas Jonias de la Lucidez",
            category = "Botas N2",
            goldCost = 1000,
            stats = "+50% Mana Regen • +15 Ability Haste • +45 Move Speed",
            passive = "Ionian Boots of Lucidity\nReduces ability cooldowns\n+50% Mana Regen\n+15 Ability Haste\n+45 Move Speed\nSummoned: Reduces spell cooldowns by 15%.\n1000\nIonian Boots of Lucidity TIPS: These boots are designed for champions who rely on casting abilities as often as possible. They provide mana regeneration, Ability Haste, and further reduce the cooldown of Summoner Spells, allowing you to use key abilities more frequently while bringing back Flash, Smite, Ignite, and other Summoner Spells faster. They are an excellent choice for mages, supports, fighters, and any champion who benefits from maximizing ability uptime.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389658_ionian-boots-of-lucidity.webp"
        ))
        add(WildRiftItem(
            id = "boots_of_mana_boots_t2",
            name = "Botas de Maná",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+25 Ability Power • +8 Magic Penetration • +75% Mana Regeneration • +45 Move Speed",
            passive = "Boots of Mana\nAbility Power, Magic Pen, Mana Regeneration\n+25 Ability Power\n+8 Magic Penetration\n+75% Mana Regeneration\n+45 Move Speed\nEquilibrium: Champions without Mana gain 50% bonus health Regen.\nBig Bully: Attacks and active abilities deal 18 bonus true damage to minions.\n1200\nBoots of Mana TIPS: These boots greatly enhance your early magic damage by providing Ability Power, magic penetration, and increased mana regeneration. They also improve wave clear by dealing bonus true damage to minions, while champions without Mana instead gain additional health regeneration. They are an excellent choice for mages and AP supports who value strong laning, frequent spell casting, and efficient wave clearing.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389619_boots-of-mana.webp"
        ))
        add(WildRiftItem(
            id = "boots_of_dynamism_boots_t2",
            name = "Botas de Dinamismo",
            category = "Botas N2",
            goldCost = 1200,
            stats = "+15 Attack Damage • +10 Armor Penetration • +45 Move Speed",
            passive = "Boots of Dynamism\nAttack Damage, Armor Pen\n+15 Attack Damage\n+10 Armor Penetration\n+45 Move Speed\n1200\nBoots of Dynamism TIPS: These boots increase your physical damage by providing bonus Attack Damage and armor penetration. They are especially effective during the early stages of the game, allowing you to cut through enemy defenses and win trades more easily. They are an excellent choice for marksmen, assassins, and fighters looking to maximize their damage output and eliminate enemies more efficiently.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389665_boots-of-dynamism.webp"
        ))
        add(WildRiftItem(
            id = "immortal_treds_boots_t3",
            name = "Botas Inmortales Nivel 3",
            category = "Botas N3",
            goldCost = 2000,
            stats = "+45 Move Speed",
            passive = "Immortal Treds\nDeal bonus damage or gain increased healing and shielding\n+45 Move Speed\nBalance of Power: Gain 12 Attack Damage or 20 Ability Power (Adaptive).\nConversion: Gain 5% Omnivamp. Champion takedowns grant an additional 0.5% Omnivamp, up to 5%.\nNow and Forever: When you have more than 50% Health, deal 5% bonus damage. When below 50% Health, gain 12% increased healing and shielding.\n2000\nImmortal Treds TIPS: These boots combine adaptive offensive power, sustain, and increased combat effectiveness. While above 50% Health, you deal increased damage, and when below 50% Health, you benefit from stronger healing and shielding to improve your survivability. The additional Omnivamp further restores Health from all damage you deal. They are an excellent choice for fighters, AP bruisers, and champions who want to balance offensive power with sustained durability throughout extended fights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561637_immortal-treds.webp"
        ))
        add(WildRiftItem(
            id = "gunmetal_greaves_boots_t3",
            name = "Grebas de Metal Nivel 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+50% Attack Speed • +5% Physical Vamp • +45 Move Speed",
            passive = "Gunmetal Greaves\nIncreases Attack Speed and Movement Speed\n+50% Attack Speed\n+5% Physical Vamp\n+45 Move Speed\nNoxian Gait: Attacks against enemy champions grant Movement Speed (10% for melee champions / 7% for ranged champions) decaying over 2 seconds.\nBlessed Blade: Attacks restore 12 Health on hit.\n2200\nGunmetal Greaves TIPS: These boots greatly increase your attack speed while improving your mobility in combat. Attacking enemy champions grants a burst of movement speed, making it easier to chase opponents or kite effectively, while Physical Vamp and on-hit healing provide valuable sustain during extended fights. They are an excellent choice for marksmen and champions who rely on frequent basic attacks to deal damage.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561598_gunmetal-greaves.webp"
        ))
        add(WildRiftItem(
            id = "chainlaced_crushers_boots_t3",
            name = "Trituradoras Eslabadas Nivel 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+150 Max Health • +30 Magic Resistance • +30% Tenacity • +45 Move Speed",
            passive = "Chainlaced Crushers\nGain a magic shield upon taking magic damage\n+150 Max Health\n+30 Magic Resistance\n+30% Tenacity\n+45 Move Speed\nNoxian Persistence: After taking magic damage from a champion, gain a magic shield that absorbs 10-120 plus 5% maxfor 5s. (12s Cooldown)\n2200\nChainlaced Crushers TIPS: These boots greatly improve your survivability against magic damage. After taking magic damage from an enemy champion, you gain a magic shield that helps absorb follow-up spells, while the bonus Magic Resistance and Tenacity make you far more resilient against AP threats and crowd control. They are an excellent choice against teams with heavy magic damage and strong CC.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561548_chainlaced-crushers.webp"
        ))
        add(WildRiftItem(
            id = "armored_advance_boots_t3",
            name = "Avance Blindado Nivel 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+150 Max Health • +30 Armor • +45 Move Speed",
            passive = "Armored Advance\nGrants Armor and a shield\n+150 Max Health\n+30 Armor\n+45 Move Speed\nBlock: Reduce damage from champion attacks by 10%.\nNoxian Endurance: After taking physical damage from a champion grants a physical shield that absorbs damage equal to 10-140 plus 8% max Health. (12s Cooldown)\n2200\nArmored Advance TIPS: These boots provide excellent protection against physical damage. They reduce damage taken from enemy champion attacks and grant a protective shield after taking physical damage from a champion, helping you survive extended trades and heavy bursts of physical damage. They are an excellent choice against marksmen, fighters, and other champions who rely primarily on physical attacks.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561641_armored-advance.webp"
        ))
        add(WildRiftItem(
            id = "crimson_lucidity_boots_t3",
            name = "Lucidez Carmesí Nivel 3",
            category = "Botas N3",
            goldCost = 2000,
            stats = "+75% Mana Regeneration • +25 Ability Haste • +45 Move Speed",
            passive = "Crimson Lucidity\nReduces ability cooldown\n+75% Mana Regeneration\n+25 Ability Haste\n+45 Move Speed\nSummoned: Reduces spell cooldown by 20%.\nNoxian Haste: Healing or shielding allied champions, casting a spell, or dealing damage to enemies with abilities grants Movement Speed (10% for melee champions / 8% for ranged champions) for 4 seconds.\nThis effect can only be triggered once every 4 seconds per ability.\n2000\nCrimson Lucidity TIPS: These boots are ideal for champions who rely on casting abilities as often as possible. They greatly reduce the cooldown of both abilities and Summoner Spells while granting bonus movement speed whenever you heal or shield allies, cast spells, or damage enemies with abilities. They are an excellent choice for mages, supports, and fighters who value high mobility and maximum ability uptime.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561706_crimson-lucidity.webp"
        ))
        add(WildRiftItem(
            id = "spellslinger_s_shoes_boots_t3",
            name = "Zapatos de Hechicero Nivel 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+35 Ability Power • +18 Magic Penetration • +8% Magic Penetration • +100% Mana Regeneration • +45 Move Speed",
            passive = "Spellslinger's Shoes\nDeal bonus damage to minions\n+35 Ability Power\n+18 Magic Penetration\n+8% Magic Penetration\n+100% Mana Regeneration\n+45 Move Speed\nEquilibrium: Champions without Mana gain 50% base Health Regen.\nBig Bully: Attacks and active abilities deal 18 bonus true damage to minions.\n2200\nSpellslinger's Shoes TIPS: These boots greatly increase your magic damage through a combination of Ability Power and both flat and percentage magic penetration. The high mana regeneration allows for frequent spell casting, while the bonus true damage to minions significantly improves wave clear. Champions without Mana instead gain increased health regeneration. They are an excellent choice for mages and AP supports who value strong damage, constant lane pressure, and efficient farming.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561512_spellslingers-shoes.webp"
        ))
        add(WildRiftItem(
            id = "armorcrusher_boots_boots_t3",
            name = "Botas Rompearmaduras Nivel 3",
            category = "Botas N3",
            goldCost = 2200,
            stats = "+25 Attack Damage • +12 Armor Penetration • +6% Armor Penetration • +45 Move Speed",
            passive = "Armorcrusher Boots\nGain out-of-combat Movement Speed\n+25 Attack Damage\n+12 Armor Penetration\n+6% Armor Penetration\n+45 Move Speed\nCloudwalker: Gain 20 out-of combat Move Speed.\n2200\nArmorcrusher Boots TIPS: These boots greatly increase your physical damage by providing bonus Attack Damage along with both flat and percentage armor penetration. The additional out-of-combat movement speed allows you to rotate around the map faster, chase enemies more effectively, and respond to fights more quickly. They are an excellent choice for marksmen, assassins, and fighters who value high mobility and maximum damage against armored targets.",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783561651_armorbreaker-boots.webp"
        ))
        add(WildRiftItem(
            id = "quicksilver_sash_mid_tier",
            name = "Fajín de Mercurio",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "",
            passive = "Quicksilver Sash\nDispels crowd control\nQuicksilver (Active): Removes all crowd control effects currently affecting you, and become immune to crowd control effects for 0.25 seconds.\nPerseverance (Passive): When the Quicksilver effects ends, grant 30% Tenacity and 30% Slow Resist for 1.5 seconds. (60s Cooldown)\nCannot be used during knock up or knock back effects.\n1100\nQuicksilver Sash TIPS: This enchant instantly removes most crowd control effects and briefly grants immunity to further disables. Once the effect ends, it provides increased resistance to crowd control and slows, helping you escape dangerous situations or continue fighting without interruption. It is an excellent choice against teams with heavy crowd control, allowing you to stay mobile and effective in crucial moments.",
            iconUrl = "https://wr-meta.com/uploads/posts/2025-07/1753389685_quicksilver-enchant.webp"
        ))
        add(WildRiftItem(
            id = "seeker_s_armguard_mid_tier",
            name = "Guardabrazo de la Buscadora",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Armor • +35 Ability Power",
            passive = "Seeker's Armguard\nTurn invulnerable\n+20 Armor\n+35 Ability Power\nStasis (Active): Become invulnerable and untargetable for 2.5 seconds, but unable to move, attack, cast abilities or use items. (120s Cooldown)\n1200\nSeeker's Armguard TIPS: This item combines Ability Power with extra defense, but its defining feature is the ability to become temporarily invulnerable. Its active effect lets you completely avoid lethal damage, dodge crucial enemy abilities, or buy time for your cooldowns to return. It is an excellent choice for mages and AP assassins who need to survive burst damage and outplay opponents in critical teamfights.",
            iconUrl = "https://wr-meta.com/uploads/posts/2022-01/1641809649_seekers-armguard.png"
        ))
        add(WildRiftItem(
            id = "vampiric_scepter_mid_tier",
            name = "Cetro Vampírico",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Attack Damage • +8% Physical Vamp",
            passive = "Vampiric Scepter\n+20 Attack Damage\n+8% Physical Vamp\n1200",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985546_1053.png"
        ))
        add(WildRiftItem(
            id = "zeal_mid_tier",
            name = "Fervor",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+15% Critical Rate • +15% Attack Speed",
            passive = "Zeal\nIncreases Movement Speed\n+15% Critical Rate\n+15% Attack Speed\nFervor:  +5% Move Speed.\n1400",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985588_3086.png"
        ))
        add(WildRiftItem(
            id = "kircheis_shard_mid_tier",
            name = "Fragmento de Kircheis",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+15 Attack Damage",
            passive = "Kircheis Shard\nAttacks deal bonus damage\n+15 Attack Damage\nJolt: Energy Attacks gain 50 bonus magic damage. Muving and attacking generate Energy Attacks.\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985587_2015.png"
        ))
        add(WildRiftItem(
            id = "serrated_dirk_mid_tier",
            name = "Daga Dentada",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Attack Damage",
            passive = "Serrated Dirk\n+20 Attack Damage\nSharp:  +8 Armor Penetration.\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985626_3134.png"
        ))
        add(WildRiftItem(
            id = "recurve_bow_mid_tier",
            name = "Arco Recurvo",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+30% Attack Speed",
            passive = "Recurve Bow\nAttack deal bonus damage\n+30% Attack Speed\nReinforced: Attacks deal 15 bonus physical damage on-hit against targets.\n1400",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985621_1043.png"
        ))
        add(WildRiftItem(
            id = "b_f_sword_mid_tier",
            name = "Espadón",
            category = "Nivel Medio",
            goldCost = 1500,
            stats = "+40 Attack Damage",
            passive = "B. F. Sword\n+40 Attack Damage\n1500",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985610_1038.png"
        ))
        add(WildRiftItem(
            id = "cloak_of_agility_mid_tier",
            name = "Capa de Agilidad",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20% Critical Rate",
            passive = "Cloak of Agility\n+20% Critical Rate\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985612_1018.png"
        ))
        add(WildRiftItem(
            id = "last_whisper_mid_tier",
            name = "Último Suspiro",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "",
            passive = "Last Whisper\nArmor Penetration (%)\nLast Whisper:  +12% Armor Penetration.\n800",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611440927_last-whisper.png"
        ))
        add(WildRiftItem(
            id = "executioner_s_calling_mid_tier",
            name = "Llamado del Verdugo",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+15 Attack Damage",
            passive = "Executioner's Calling\nPhysical Damage reduces enemy healing\n+15 Attack Damage\nRend: Physical Damage inflicts 40% Grievous Wounds to enemy champions for 3 seconds. Grievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n800",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985718_3123.png"
        ))
        add(WildRiftItem(
            id = "phage_mid_tier",
            name = "Bacteriófago",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+150 Max Health • +15 Attack Damage",
            passive = "Phage\nAttacks increases Movement Speed\n+150 Max Health\n+15 Attack Damage\nRage: Attacks grant 20 Move Speed and kills grant 60 Move Speed for 2 seconds. Bonuses do not stack. Ranged champions gain halved values.\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985701_3044.png"
        ))
        add(WildRiftItem(
            id = "stinger_mid_tier",
            name = "Aguijón",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+30% Attack Speed • +10 Ability Haste",
            passive = "Stinger\n+30% Attack Speed\n+10 Ability Haste\n1200",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985695_winged_moonplate_item_hd.jpg"
        ))
        add(WildRiftItem(
            id = "caulfield_s_warhammer_mid_tier",
            name = "Martillo de Guerra de Caulfield",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+25 Attack Damage • +10 Ability Haste",
            passive = "Caulfield's Warhammer\n+25 Attack Damage\n+10 Ability Haste\n1200",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611441611_caulfields-warhammer.png"
        ))
        add(WildRiftItem(
            id = "jaurim_s_fist_mid_tier",
            name = "Puño de Jaurim",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+175 Max Health • +15 Attack Damage",
            passive = "Jaurim's Fist\n+175 Max Health\n+15 Attack Damage\n1100",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611444901_jaurims-fist.png"
        ))
        add(WildRiftItem(
            id = "aether_wisp_mid_tier",
            name = "Brisa de Éter",
            category = "Nivel Medio",
            goldCost = 950,
            stats = "+35 Ability Power",
            passive = "Aether Wisp\nIncreases Movement Speed\n+35 Ability Power\nWisp:  +5% Move Speed.\n950",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611442631_aether-wisp.png"
        ))
        add(WildRiftItem(
            id = "lost_chapter_mid_tier",
            name = "Capítulo Perdido",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+35 Ability Power • +200 Max Mana • +10 Ability Haste",
            passive = "Lost Chapter\nRestore Mana when leveling up\n+35 Ability Power\n+200 Max Mana\n+10 Ability Haste\nEnlighten: Leveling up restores 20%max Mana over 3 seconds.\n1200",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985899_3802.png"
        ))
        add(WildRiftItem(
            id = "fiendish_codex_mid_tier",
            name = "Códice Diabólico",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+25 Ability Power • +10 Ability Haste",
            passive = "Fiendish Codex\n+25 Ability Power\n+10 Ability Haste\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985893_3108.png"
        ))
        add(WildRiftItem(
            id = "blasting_wand_mid_tier",
            name = "Varita Explosiva",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Ability Power",
            passive = "Blasting Wand\n+40 Ability Power\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985924_1026.png"
        ))
        add(WildRiftItem(
            id = "needlessly_large_rod_mid_tier",
            name = "Vara Innecesariamente Grande",
            category = "Nivel Medio",
            goldCost = 1400,
            stats = "+65 Ability Power",
            passive = "Needlessly Large Rod\n+65 Ability Power\n1400",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985984_1058.png"
        ))
        add(WildRiftItem(
            id = "haunting_guise_mid_tier",
            name = "Disfraz Encantado",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+200 Max Health • +30 Ability Power",
            passive = "Haunting Guise\nBoosts in-combat damage\n+200 Max Health\n+30 Ability Power\nMadness: Every 1 second(s) in combat with enemy champions, deal 2% bonus damage, up to 6%.\n1300",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611443224_haunting-guise.png"
        ))
        add(WildRiftItem(
            id = "sheen_mid_tier",
            name = "Brillo",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+10 Ability Haste",
            passive = "Sheen\nAttacks deal bonus damage after ability casts\n+10 Ability Haste\nSpellblade: Using an ability causes the next attack used within 10 seconds to deal bonus physical damage equal to 100% base attack damage . (1.5s Cooldown) Damage is reduced vs structures.\n800",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985975_3057.png"
        ))
        add(WildRiftItem(
            id = "oblivion_orb_mid_tier",
            name = "Orbe del Olvido",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "+35 Ability Power",
            passive = "Oblivion Orb\nMagic damage reduces enemy healing\n+35 Ability Power\nCursed Wounds: Dealing magic damage to enemy champions applies 40% Grievous Wounds for 3 seconds.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n800",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616985971_3916.png"
        ))
        add(WildRiftItem(
            id = "bami_s_cinder_mid_tier",
            name = "Ceniza de Bami",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+250 Max Health",
            passive = "Bami's Cinder\nBurns nearby emenies\n+250 Max Health\nCinders: Deals 10-20 magic damage per second to nearby enemies. Deals 15% bonus damage to minions and monsters.\n1300",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986281_6660.png"
        ))
        add(WildRiftItem(
            id = "spectre_s_cowl_mid_tier",
            name = "Hábito del Espectro",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+175 Max Health • +20 Magic Resistance",
            passive = "Spectre's Cowl\nBoosts Health Regen when tacking damage\n+175 Max Health\n+20 Magic Resistance\nSpectral Visit: Grants 150% Health Regen for 10 seconds after taking damage from an enemy champion.\n1100",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986306_3211.png"
        ))
        add(WildRiftItem(
            id = "kindlegem_mid_tier",
            name = "Gema Avivadora",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+175 Max Health • +10 Ability Haste",
            passive = "Kindlegem\n+175 Max Health\n+10 Ability Haste\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986347_3067.png"
        ))
        add(WildRiftItem(
            id = "giant_s_belt_mid_tier",
            name = "Cinturón de Gigante",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+300 Max Health",
            passive = "Giant's Belt\n+300 Max Health\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986359_1011.png"
        ))
        add(WildRiftItem(
            id = "warden_s_mail_mid_tier",
            name = "Malla del Guardián",
            category = "Nivel Medio",
            goldCost = 1050,
            stats = "+35 Armor",
            passive = "Warden's Mail\nReduces enemy's Attack Speed\n+35 Armor\nCold Steel: Reduce the Attack Speed of enemies by 15% for 1.5 seconds when struck by an attack.\n1050",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986370_3082.png"
        ))
        add(WildRiftItem(
            id = "catalyst_of_aeons_mid_tier",
            name = "Catalizador de Eones",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+200 Max Health • +300 Max Mana",
            passive = "Catalyst of Aeons\nConsumes Mana to heal\n+200 Max Health\n+300 Max Mana\nEternity: Restore Mana equal to 15% of the damage taken from champions. Regen Health equal to 20% of Mana spent. Capped at 15 Health per cast.\n1100",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-01/1611444645_catalyst-of-aeons.png"
        ))
        add(WildRiftItem(
            id = "chain_vest_mid_tier",
            name = "Cota de Malla",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Armor",
            passive = "Chain Vest\n+40 Armor\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986413_1031.png"
        ))
        add(WildRiftItem(
            id = "bramble_vest_mid_tier",
            name = "Chaleco de Zarzas",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+30 Armor",
            passive = "Bramble Vest\nReflects damage and reduces enemy healing\n+30 Armor\nThorns: When struck by an attack, deal 4 magic damage + 6% bonus armor  to the attacker and inflict 40% Grievous Wounds for 3 seconds if they are a champion.\nGrievous Wounds reduces the effectiveness of Healing and Regeneration effects.\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986594_3076.png"
        ))
        add(WildRiftItem(
            id = "hexdrinker_mid_tier",
            name = "Sorbemaleficios",
            category = "Nivel Medio",
            goldCost = 1200,
            stats = "+20 Attack Damage • +20 Magic Resistance",
            passive = "Hexdrinker\n+20 Attack Damage\n+20 Magic Resistance\n1200",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986547_3155.png"
        ))
        add(WildRiftItem(
            id = "negatron_cloak_mid_tier",
            name = "Manto de Negatrones",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Magic Resistance",
            passive = "Negatron Cloak\n+40 Magic Resistance\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986529_1057.png"
        ))
        add(WildRiftItem(
            id = "glacial_shroud_mid_tier",
            name = "Manto Glacial",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Armor • +150 Max Mana • +10 Ability Haste",
            passive = "Glacial Shroud\n+20 Armor\n+150 Max Mana\n+10 Ability Haste\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616986572_3024.png"
        ))
        add(WildRiftItem(
            id = "winged_moonplate_mid_tier",
            name = "Placa Lunar Alada",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+150 Max Health",
            passive = "Winged Moonplate\nIncreaces Move Speed\n+150 Max Health\nFlight:  +5% Move Speed.\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2021-03/1616981123_winged_moonplate_item_hd-min.png"
        ))
        add(WildRiftItem(
            id = "nashor_s_talon_mid_tier",
            name = "Garra de Nashor",
            category = "Nivel Medio",
            goldCost = 800,
            stats = "",
            passive = "Nashor's Talon\nGain Attack Damage or Ability Power\nMagic Needle: Gain 15 Attack Damage or 30 Ability Power (adaptive).\n800",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698258000_nashors-talon.png"
        ))
        add(WildRiftItem(
            id = "noonquiver_mid_tier",
            name = "Carcaj de Mediodía",
            category = "Nivel Medio",
            goldCost = 1350,
            stats = "+25 Attack Damage • +15% Attack Speed",
            passive = "Noonquiver\n+25 Attack Damage\n+15% Attack Speed\n1350",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-05/1685137714_noonquiver_item.webp"
        ))
        add(WildRiftItem(
            id = "hextech_alternator_mid_tier",
            name = "Alternador Hextech",
            category = "Nivel Medio",
            goldCost = 1100,
            stats = "+45 Ability Power",
            passive = "Hextech Alternator\nAbilities deal bonus damage\n+45 Ability Power\nRevved: Damaging abilities and empowered attacks against champions deal 25-60 bonus magic damage. (20s Cooldown)\n1100",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698176528_hextech_alternator_item.webp"
        ))
        add(WildRiftItem(
            id = "mejai_s_soulstealer_mid_tier",
            name = "Robaalmas de Mejai",
            category = "Nivel Medio",
            goldCost = 1800,
            stats = "+70 Max Health • +25 Ability Power",
            passive = "Mejai's Soulstealer\nTakedowns increase AP\n+70 Max Health\n+25 Ability Power\nGlory: Gain up to 30 stacks of Glory after a champion takedown. Melee champions gain 3 stack(s) for each kill and 2 stack(s) for each assist; ranged champions gain 4 stack(s) for each kill and 2 stack(s) for each assist. You lose 10 stack(s) on death.\nFear: Gain 5 AP for every stack of Glory you have. At 10 stack(s) of Glory and above, gain 10% bonus Movement Speed.\n1800\nMejai's Soulstealer TIPS: This item turns takedowns and assists into potent scaling power — collected stacks boost your spell damage and grant movement benefits at high stack counts. It’s a high-risk, high-reward choice: ideal for aggressive midlaners and snowballing champions who secure kills frequently, but vulnerable to heavy setbacks on death. Perfect when you can stay alive and keep accumulating advantages.",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698177176_mejai27s_soulstealer_item_hd.webp"
        ))
        add(WildRiftItem(
            id = "surging_scales_mid_tier",
            name = "Escamas Crecientes",
            category = "Nivel Medio",
            goldCost = 1300,
            stats = "+40 Armor",
            passive = "Surging Scales\nIn-combat Slow Resist\n+40 Armor\nSurge: Gain 20% Slow Resist while in combat with an enemy champion.\n1300",
            iconUrl = "https://wr-meta.com/uploads/posts/2023-10/1698258154_surging-scales.png"
        ))
        add(WildRiftItem(
            id = "forbidden_idol_mid_tier",
            name = "Ídolo Prohibido",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+100 Max Health • +5 Ability Haste • +4% Heal and Shield Strength • +25% Mana Regen",
            passive = "Forbidden Idol\nIncrease heal and shield strength\n+100 Max Health\n+5 Ability Haste\n+4% Heal and Shield Strength\n+25% Mana Regen\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2024-12/1735325004_forbidden_idol_item_hd.webp"
        ))
        add(WildRiftItem(
            id = "fated_ashes_mid_tier",
            name = "Cenizas Fatídicas",
            category = "Nivel Medio",
            goldCost = 900,
            stats = "+40 Ability Power",
            passive = "Fated Ashes\nAbilities deal damage over time\n+40 Ability Power\nKindle: Damaging abilities deal 5 bonus magic damage over 3 seconds.\nDeals an additional 15 magic damage to monsters.\n900",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783190595_fated-ashes.webp"
        ))
        add(WildRiftItem(
            id = "void_amethyst_mid_tier",
            name = "Amatista del Vacío",
            category = "Nivel Medio",
            goldCost = 1000,
            stats = "+20 Ability Power • +10% Magic Penetration",
            passive = "Void Amethyst\n+20 Ability Power\n+10% Magic Penetration\n1000",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783190939_void_amethyst.webp"
        ))
        add(WildRiftItem(
            id = "verdant_barrier_mid_tier",
            name = "Barrera Frondosa",
            category = "Nivel Medio",
            goldCost = 1600,
            stats = "+40 Ability Power • +25 Magic Resistance",
            passive = "Verdant Barrier\nBlocks an enemy ability\n+40 Ability Power\n+25 Magic Resistance\nAnnul: Grants a spell shield that blocks the next enemy ability. (50s Cooldown)\n1600",
            iconUrl = "https://wr-meta.com/uploads/posts/2026-07/1783191133_verdant_barrier_item_hd_11zon.png"
        ))
        add(WildRiftItem(
            id = "boots_of_speed_basic",
            name = "Botas de Velocidad",
            category = "Básicos",
            goldCost = 400,
            stats = "+25 Move Speed.",
            passive = "Boots of Speed\n+25 Move Speed.\n400",
            iconUrl = "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp"
        ))
        add(WildRiftItem(
            id = "long_sword_basic",
            name = "Espada Larga",
            category = "Básicos",
            goldCost = 500,
            stats = "+12 Attack Damage",
            passive = "Long Sword\n+12 Attack Damage\n500",
            iconUrl = "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp"
        ))
        add(WildRiftItem(
            id = "brawler_s_gloves_basic",
            name = "Guantes de Peleador",
            category = "Básicos",
            goldCost = 500,
            stats = "+10% Critical Rate",
            passive = "Brawler's Gloves\n+10% Critical Rate\n500",
            iconUrl = "https://i.postimg.cc/QBJq8zsr/1753390558-brawlers-gloves.webp"
        ))
        add(WildRiftItem(
            id = "dagger_basic",
            name = "Daga",
            category = "Básicos",
            goldCost = 500,
            stats = "+15% Attack Speed",
            passive = "Dagger\n+15% Attack Speed\n500",
            iconUrl = "https://i.postimg.cc/CZgNV0y5/1753390550-dagger.webp"
        ))
        add(WildRiftItem(
            id = "shimmering_spark_basic",
            name = "Chispa Reluciente",
            category = "Básicos",
            goldCost = 500,
            stats = "+50 Max Health",
            passive = "Shimmering Spark\nBurns nearby enemies\n+50 Max Health\nBurn: Deals 5-10 magic damage per second to nearby enemies.\n500",
            iconUrl = "https://i.postimg.cc/bGhTjPXv/1753390582-shimmering-spark.webp"
        ))
        add(WildRiftItem(
            id = "tear_of_the_goddess_basic",
            name = "Lágrima de la Diosa",
            category = "Básicos",
            goldCost = 500,
            stats = "+200 Max Mana • +5 Ability Haste",
            passive = "Tear of the Goddess\nIncreases Mana\n+200 Max Mana\n+5 Ability Haste\nAwe: 10% of Mana spent is refunded.\nMana Charge: Increases max Mana by 6 every time Mana is spent. Caps at 700 bonus Mana. Triggers up to 3 times every 10 seconds. You may only carry one Tear of the Goddess item at a time.\n500",
            iconUrl = "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png"
        ))
        add(WildRiftItem(
            id = "amplifying_tome_basic",
            name = "Tomo Amplificador",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Ability Power",
            passive = "Amplifying Tome\n+20 Ability Power\n500",
            iconUrl = "https://i.postimg.cc/qtTLdrfM/1753390572-amplifying-tome.webp"
        ))
        add(WildRiftItem(
            id = "ruby_crystal_basic",
            name = "Cristal de Rubí",
            category = "Básicos",
            goldCost = 500,
            stats = "+150 Max Health",
            passive = "Ruby Crystal\n+150 Max Health\n500",
            iconUrl = "https://i.postimg.cc/rdkJLT6y/1753390626-ruby-crystal.webp"
        ))
        add(WildRiftItem(
            id = "cloth_armor_basic",
            name = "Armadura de Tela",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Armor",
            passive = "Cloth Armor\n+20 Armor\n500",
            iconUrl = "https://i.postimg.cc/3yTBHhM8/1753390581-cloth-armor.webp"
        ))
        add(WildRiftItem(
            id = "null_magic_mantle_basic",
            name = "Manto Anulamagia",
            category = "Básicos",
            goldCost = 500,
            stats = "+20 Magic Resistance",
            passive = "Null-Magic Mantle\n+20 Magic Resistance\n500",
            iconUrl = "https://i.postimg.cc/4Kg5TGCJ/1753390606-null-magic-mantle.webp"
        ))
        add(WildRiftItem(
            id = "ring_of_revelation_basic",
            name = "Anillo de Revelación",
            category = "Básicos",
            goldCost = 300,
            stats = "+5 Ability Haste",
            passive = "Ring of Revelation\nReduces ability cooldowns\n+5 Ability Haste\n300",
            iconUrl = "https://i.postimg.cc/wtpVdzKH/1753390605-ring-of-revelation.webp"
        ))
        add(WildRiftItem(
            id = "relic_shield_basic",
            name = "Escudo Reliquia",
            category = "Básicos",
            goldCost = 500,
            stats = "+125 Max Health",
            passive = "Relic Shield\nKill minions to earn bonus gold\n+125 Max Health\nThis item is for support players. When equipped, it will reduce the gold you receive from killing minions and monsters. If there are multiples of this item within the party, only one of them can take effect at any given time.\nTribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Health 20-80:\n1. Using abilities or attacks to damage enemy champions or structures.\n2. Attacking minions below 65% Health. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: After earning 750 gold, this item upgrades into Bulwark of the Mountain and binds you and the ally with the most Tribute stacks as Perfect Partners.\n500",
            iconUrl = "https://i.postimg.cc/sBrcRzFs/1753390612-relic-shield.webp"
        ))
        add(WildRiftItem(
            id = "spectral_sickle_basic",
            name = "Hoz Espectral",
            category = "Básicos",
            goldCost = 500,
            stats = "Quest:",
            passive = "Spectral Sickle\nAttack champions and structures to gain bonus gold\nThis item is for support players. When equipped, it will reduce the gold you receive from killing minions and monsters. If there are multiples of this item within the party, only one of them can take effect at any given time.\nVersatile: Gain 10 Attack Damage or 20 Ability Power (Adaptive).\nTribute: Gain 1 encircling energy orb(s) every 30 seconds (max 3 orbs). While near an ally, the actions below will trigger Tribute, consuming 1 energy orb(s) to grant you 65 gold and restore your Health 20-80:\n1. Using abilities or attacks to damage enemy champions or structures.\n2. Attacking minions below 65% Health. This also executes them, and the gold generated from the minion kills is given to the ally nearest to you. 3. A nearby minion is killed while you have 3 orbs. Upon triggering Tribute, the ally nearest to you gains Tribute stacks.\nSentry: Deal 1 more damage to Sight Wards revealed by Sweeping Lens, Control Ward, and Scryer’s Bloom.\nRestraint: You do not earn gold generated from minion kills, but you earn gold equal to 50% of the bounty. The gold generated from your minion kills will be given to the ally nearest to you. Gold earned from monster kills is reduced by 50%.\nQuest: Earn 750 gold with this item to transform it into Black Mist Scythe and bind you and the ally with the most Tribute stacks as Perfect Partners.\n500",
            iconUrl = "https://i.postimg.cc/2qDwfYpY/1753390656-spectral-sickle.webp"
        ))
            }

    fun getItemByName(name: String): WildRiftItem? {
        return list.find { it.name.equals(name, ignoreCase = true) }
    }

    fun getItemIconByName(name: String): String {
        return list.find { it.name.equals(name, ignoreCase = true) }?.iconUrl ?: ""
    }
}
