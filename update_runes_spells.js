const fs = require('fs');

let ktCode = fs.readFileSync('app/src/main/java/com/example/ui/components/admin/AdminRunesSpellsEditor.kt', 'utf-8');

// Rename the main composable
ktCode = ktCode.replace('fun AdminRunesSpellsEditorTab() {', 'fun AdminRunesSpellsEditorTabBase(initialSection: SubSection) {');

// Change the initial state
ktCode = ktCode.replace(
    'var activeSubSection by remember { mutableStateOf(SubSection.RUNES) }',
    'var activeSubSection by remember { mutableStateOf(initialSection) }'
);

// Remove the Row with FilterChips. Let's find it.
// It starts with:
//         // Sub-tabs switch (Runas / Hechizos / Objetivos)
//         Row(
// and ends with 
//         }
//         Spacer(modifier = Modifier.height(8.dp))
// Let's use a regex that matches this block.
let regex = /\s*\/\/\s*Sub-tabs switch \(Runas \/ Hechizos \/ Objetivos\)[\s\S]*?FilterChip[\s\S]*?FilterChip[\s\S]*?FilterChip[\s\S]*?modifier = Modifier\.weight\(1f\)\n            \)\n        \}/;
ktCode = ktCode.replace(regex, '');

// Add the wrapper functions at the end of the file
let wrappers = `
@Composable
fun AdminRunesEditorTab() {
    AdminRunesSpellsEditorTabBase(SubSection.RUNES)
}

@Composable
fun AdminSpellsEditorTab() {
    AdminRunesSpellsEditorTabBase(SubSection.SPELLS)
}

@Composable
fun AdminObjectivesEditorTab() {
    AdminRunesSpellsEditorTabBase(SubSection.OBJECTIVES)
}
`;

ktCode = ktCode + '\n' + wrappers;

fs.writeFileSync('app/src/main/java/com/example/ui/components/admin/AdminRunesSpellsEditor.kt', ktCode);
