import re

file_path = "app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I need to add LazyVerticalGrid imports if they don't exist
if "import androidx.compose.foundation.lazy.grid." not in content:
    content = content.replace("import androidx.compose.foundation.lazy.items", "import androidx.compose.foundation.lazy.items\nimport androidx.compose.foundation.lazy.grid.LazyVerticalGrid\nimport androidx.compose.foundation.lazy.grid.GridCells\nimport androidx.compose.foundation.lazy.grid.items")

# Replace LazyColumn with LazyVerticalGrid in DraftChampionPickerSheet
# From:
# LazyColumn(
#     modifier = Modifier
#         .fillMaxWidth()
#         .height(380.dp),
#     verticalArrangement = Arrangement.spacedBy(8.dp)
# ) {
#     items(availableChamps) { champ ->
#         Row( ... ) { ... }
#     }
# }
old_list_pattern = r"LazyColumn\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.height\(380\.dp\),\s*verticalArrangement = Arrangement\.spacedBy\(8\.dp\)\s*\)\s*\{[\s\S]*?items\(availableChamps\) \{ champ ->[\s\S]*?\}\s*\}\s*\}"

new_grid = """LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 64.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(availableChamps) { champ ->
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onChampionPicked(champ) }
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ChampionAvatar(champion = champ, size = 56.dp, showTierBadge = false)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = champ.name,
                            color = TextPrimary,
                            fontSize = 10.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }"""

content = re.sub(old_list_pattern, new_grid, content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Patched DraftChampionPickerSheet")
