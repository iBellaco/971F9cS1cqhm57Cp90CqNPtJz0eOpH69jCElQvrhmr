const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', 'utf8');

// The code looks like this:
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(
//                                text = "Runas sugeridas: ${myChampion.recommendedRunes}",
//                                color = TextMuted,
//                                fontSize = 10.5.sp
//                            )

code = code.replace(/Spacer\(modifier = Modifier.height\(4.dp\)\)\s*Text\(\s*text = "Runas sugeridas: \$\{myChampion\.recommendedRunes\}",\s*color = TextMuted,\s*fontSize = 10\.5\.sp\s*\)/m, '');

fs.writeFileSync('app/src/main/java/com/example/ui/components/MatchupPreviewDialog.kt', code);
