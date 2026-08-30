const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'utf8');

const regex = /                        val authUser = com\.example\.util\.AuthManager\.getAuth\(\)\?\.currentUser\s+IconButton\(\s*onClick = onNavigateToLogin,\s*modifier = Modifier\s*\.padding\(end = 4\.dp\)\s*\.size\(38\.dp\)\s*\.testTag\("nav_profile_avatar_button"\)\s*\) \{\s*if \(authUser != null\) \{\s*UserAvatarView\(\s*avatarId = currentAvatarId,\s*size = 36\.dp,\s*fallbackInitial = authUser\.displayName \?: authUser\.email \?: "U"\s*\)\s*\} else \{\s*Box\(\s*modifier = Modifier\s*\.size\(36\.dp\)\s*\.clip\(CircleShape\)\s*\.background\(HextechSurface\)\s*\.border\(1\.dp, HextechGold\.copy\(alpha = 0\.6f\), CircleShape\),\s*contentAlignment = Alignment\.Center\s*\) \{\s*Icon\(\s*imageVector = Icons\.Default\.Person,\s*contentDescription = "Usuario",\s*tint = HextechGold,\s*modifier = Modifier\.size\(20\.dp\)\s*\)\s*\}\s*\}\s*\}/m;

const replacement = `                        val authUser = com.example.util.AuthManager.getAuth()?.currentUser
                        if (authUser != null) {
                            IconButton(
                                onClick = onNavigateToLogin,
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
