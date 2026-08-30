const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/MainActivity.kt', 'utf8');

const regex1 = /RunicNavBarParticleAnimation\(\n\s*modifier = Modifier.matchParentSize\(\),\n\s*particleCount = 16,\n\s*accentColor = navAccent\n\s*\)/g;
const replacement1 = `if (AppThemeManager.isParticlesEnabled) {
                    RunicNavBarParticleAnimation(
                        modifier = Modifier.matchParentSize(),
                        particleCount = 16,
                        accentColor = navAccent
                    )
                }`;
code = code.replace(regex1, replacement1);

const regex2 = /RunicNavBarParticleAnimation\(\n\s*modifier = Modifier\n\s*\.matchParentSize\(\)\n\s*\.clipToBounds\(\),\n\s*particleCount = 14,\n\s*accentColor = navAccent\n\s*\)/g;
const replacement2 = `if (AppThemeManager.isParticlesEnabled) {
                    RunicNavBarParticleAnimation(
                        modifier = Modifier
                            .matchParentSize()
                            .clipToBounds(),
                        particleCount = 14,
                        accentColor = navAccent
                    )
                }`;
code = code.replace(regex2, replacement2);

fs.writeFileSync('app/src/main/java/com/example/MainActivity.kt', code);
