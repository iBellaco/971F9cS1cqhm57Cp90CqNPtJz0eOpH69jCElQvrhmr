const fs = require('fs');

let ktCode = fs.readFileSync('app/src/main/java/com/example/ui/components/AdminFeedbackPanel.kt', 'utf-8');

// Replace RUNES_SPELLS with RUNES, SPELLS, OBJECTIVES
ktCode = ktCode.replace(
    'RUNES_SPELLS("Runas & Hechizos", Icons.Default.AutoAwesome),',
    'RUNES("Runas", Icons.Default.AutoAwesome),\n    SPELLS("Hechizos", Icons.Default.Bolt),\n    OBJECTIVES("Objetivos", Icons.Default.Map),'
);

// Add imports for Icons.Default.Bolt and Icons.Default.Map if not there
if (!ktCode.includes('import androidx.compose.material.icons.filled.Bolt')) {
    ktCode = ktCode.replace('import androidx.compose.material.icons.filled.AutoAwesome', 'import androidx.compose.material.icons.filled.AutoAwesome\nimport androidx.compose.material.icons.filled.Bolt\nimport androidx.compose.material.icons.filled.Map');
}

// Replace AdminTab.RUNES_SPELLS -> ...
ktCode = ktCode.replace(
    /AdminTab\.RUNES_SPELLS -> \{\s*AdminRunesSpellsEditorTab\(\)\s*\}/,
    `AdminTab.RUNES -> { AdminRunesEditorTab() }\n                AdminTab.SPELLS -> { AdminSpellsEditorTab() }\n                AdminTab.OBJECTIVES -> { AdminObjectivesEditorTab() }`
);

// Add imports for the new tabs
ktCode = ktCode.replace('import com.example.ui.components.admin.AdminRunesSpellsEditorTab', 'import com.example.ui.components.admin.AdminRunesEditorTab\nimport com.example.ui.components.admin.AdminSpellsEditorTab\nimport com.example.ui.components.admin.AdminObjectivesEditorTab');

fs.writeFileSync('app/src/main/java/com/example/ui/components/AdminFeedbackPanel.kt', ktCode);
