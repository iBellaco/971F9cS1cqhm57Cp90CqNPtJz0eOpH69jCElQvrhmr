const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', 'utf8');

// English replacements
code = code.replace(/\*\*Mid\/Late Game:\*\* Controlling Dragons and Baron is vital\. In teamfights, look to position for engages or key picks by unleashing \$ultSkill\./g, 
"**Mid/Late Game (Wild Rift Macro):** The map is small and rotations are extremely fast. Secure early objectives (Dragon/Herald) and use $ultSkill for key ganks to snowball and break tier-1 turrets.");

code = code.replace(/\*\*Mid\/Late Game:\*\* As the primary \$\{champion.damageType.displayName\} damage source, position carefully in teamfights and wait for the optimal moment to unleash \$ultSkill\./g, 
"**Mid/Late Game (Wild Rift Macro):** In WR's fast-paced matches, getting caught out late is game-over. Move with your team, flank around the tight jungle paths, and unleash $ultSkill to burst isolated targets.");

code = code.replace(/\*\*Mid\/Late Game:\*\* Your survival is the ultimate win condition\. Position in the backline, dodge CC, and use \$ultSkill when safe to melt the enemy composition\./g, 
"**Mid/Late Game (Wild Rift Macro):** Group with your support immediately after laning phase. Inhibitor turret sieges are very fast; stay safely behind your frontline and spam your damage and $ultSkill.");

code = code.replace(/\*\*Mid\/Late Game:\*\* Vision control around Baron and Elder Dragon wins games\. Save \$ultSkill to disengage enemy dives or empower your carries\./g, 
"**Mid/Late Game (Wild Rift Macro):** Clear river vision with Sweeping Lens before Dragons/Baron spawn. Use your active boot enchantments and $ultSkill to instantly turn fights around in the smaller skirmish spaces.");

code = code.replace(/\*\*Mid\/Late Game:\*\* Apply split-push pressure if ahead, or group with the team using \$ultSkill smartly to eliminate enemy carries\./g, 
"**Mid/Late Game (Wild Rift Macro):** You can split-push, but remember the map is small so enemies will collapse fast. Shove the wave and quickly group on foot for Baron or Elder, flanking with $ultSkill.");

fs.writeFileSync('app/src/main/java/com/example/util/CoachingGenerator.kt', code);
