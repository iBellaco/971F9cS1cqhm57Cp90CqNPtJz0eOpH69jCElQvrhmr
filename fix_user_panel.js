const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'utf8');

const regex = /                        IconButton\(\n\s*onClick = onNavigateToProfile,\n\s*modifier = Modifier\n\s*\.padding\(end = 4\.dp\)\n\s*\.size\(38\.dp\)\n\s*\.testTag\("nav_profile_avatar_button"\)\n\s*\) \{\n\s*if \(authUser != null\) \{\n\s*UserAvatarView\(\n\s*avatarId = currentAvatarId,\n\s*size = 36\.dp,\n\s*fallbackInitial = authUser\.displayName \?: authUser\.email \?: "U"\n\s*\)\n\s*\} else \{\n\s*Box\(\n\s*modifier = Modifier\n\s*\.size\(36\.dp\)\n\s*\.clip\(CircleShape\)\n\s*\.background\(HextechSurface\)\n\s*\.border\(1\.dp, HextechGold\.copy\(alpha = 0\.6f\), CircleShape\),\n\s*contentAlignment = Alignment\.Center\n\s*\) \{\n\s*Icon\(\n\s*imageVector = Icons\.Default\.Person,\n\s*contentDescription = "Usuario",\n\s*tint = HextechGold,\n\s*modifier = Modifier\.size\(20\.dp\)\n\s*\)\n\s*\}\n\s*\}\n\s*\}/m;

const replacement = `                        if (authUser != null) {
                            IconButton(
                                onClick = onNavigateToProfile,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .size(38.dp)
                                    .testTag("nav_profile_avatar_button")
                            ) {
                                UserAvatarView(
                                    avatarId = currentAvatarId,
                                    size = 36.dp,
                                    fallbackInitial = authUser.displayName ?: authUser.email ?: "U"
                                )
                            }
                        }`;

code = code.replace(regex, replacement);
fs.writeFileSync('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', code);
