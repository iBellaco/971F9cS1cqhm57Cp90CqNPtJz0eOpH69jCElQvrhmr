const fs = require('fs');
let ktCode = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf-8');

const newSpells = `val summonerSpells: List<SummonerSpellItem> = listOf(
        SummonerSpellItem(
            id = "flash",
            name = "Flash",
            cooldown = "150s",
            iconUrl = SPELL_FLASH,
            description = "Teleport a short distance forward or towards the aimed direction."
        ),
        SummonerSpellItem(
            id = "ghost",
            name = "Ghost",
            cooldown = "90s",
            iconUrl = SPELL_GHOST,
            description = "Gain a large burst of movement speed, that decays to 25% bonus movement speed for 8 seconds. With each takedown, Ghost's duration is extended by 6 seconds, refreshing its effects, up to the original amount."
        ),
        SummonerSpellItem(
            id = "heal",
            name = "Heal",
            cooldown = "100s",
            iconUrl = SPELL_HEAL,
            description = "Restore 110 Health (110-400) to you and the most wounded nearby ally champion, and grants both of you 30% bonus Movement Speed for 2 second(s).\\n\\nHealing is halved for champions recently affected by Heal."
        ),
        SummonerSpellItem(
            id = "barrier",
            name = "Barrier",
            cooldown = "100s",
            iconUrl = SPELL_BARRIER,
            description = "Gain a shield that absorbs 120 (120-560) damage for 2.5 seconds."
        ),
        SummonerSpellItem(
            id = "ignite",
            name = "Ignite",
            cooldown = "100s",
            iconUrl = SPELL_IGNITE,
            description = "Ignites target enemy champion, dealing 72 true damage (72-380) over 5 and applying 60% Grievous Wounds for the duration.\\n\\nGrievous Wound reduces the effectiveness of Healing and Regeneration effects."
        ),
        SummonerSpellItem(
            id = "exhaust",
            name = "Exhaust",
            cooldown = "100s",
            iconUrl = SPELL_EXHAUST,
            description = "Exhausts target enemy champion, reducing their Movement Speed by 35% and their damage dealt by 40% for 2.5 seconds."
        ),
        SummonerSpellItem(
            id = "smite",
            name = "Smite",
            cooldown = "10s",
            iconUrl = SPELL_SMITE,
            description = "Deal 600 true damage to monsters, epic monsters or enemy minions. Smiting a monster restore 127 Health (70 + 10%). Smite upgrades to Chilling Smite after 3 uses.\\n\\nJungle Expertise:\\nEarn 20% bonus gold and 20% bonus XP from monsters kills, but temporarily earn 60% less gold and XP from minions kills.\\nDeal 15% more attack damage and 30% more ability damage against monsters.\\nRestore 40 Health over 5 seconds after dealing damage to monsters.\\nRestore 4 Mana every second while in the jungle or river.\\nFrom 11:00 onward, monster kills will no longer grant bonus gold.\\nAt 2:00, the attack damage bonus against monsters will start to decay. At 5:00, it will be removed.\\nGains one charge every 45 seconds, up to a max of 2."
        ),
        SummonerSpellItem(
            id = "chilling_smite",
            name = "Chilling Smite",
            cooldown = "10s",
            iconUrl = SPELL_CHILLING_SMITE,
            description = "Deal 1000 true damage to a large or epic monster or minion. Smiting monsters restore 152 Health (70 + 10%).\\n\\nAgainst champions: Deal 40 true damage to enemy Champions and steals 25% of their Movement Speed for 2 seconds."
        ),
        SummonerSpellItem(
            id = "cleanse",
            name = "Cleanse",
            cooldown = "110s",
            iconUrl = SPELL_CLEANSE,
            description = "Removes disables (including spell debuffs) affecting your champion and grants immunity to disables for 0.25 seconds."
        ),
        SummonerSpellItem(
            id = "teleport",
            name = "Teleport",
            cooldown = "150s",
            iconUrl = SPELL_TELEPORT,
            description = "After channeling for 3.5 seconds, teleport your champion to an allied champion, structure, or ward (excludes areas in range of enemy inhibitors). You can only teleport to structures during the first 6 minutes of the game."
        ),
        SummonerSpellItem(
            id = "clarity",
            name = "Claridad",
            cooldown = "90s",
            iconUrl = SPELL_CLARITY,
            description = "Restaura el 50% del maná máximo a tu campeón y el 25% del maná a todos los aliados cercanos en el área de efecto (disponible en modos especiales y ARAM)."
        )
    )`;

const startIdx = ktCode.indexOf('val summonerSpells: List<SummonerSpellItem> = listOf(');
const endIdx = ktCode.indexOf('val runes: List<RuneItem> = listOf(');

if (startIdx !== -1 && endIdx !== -1) {
    ktCode = ktCode.substring(0, startIdx) + newSpells + '\n\n    ' + ktCode.substring(endIdx);
    fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', ktCode);
    console.log("Success");
} else {
    console.log("Could not find blocks");
}
