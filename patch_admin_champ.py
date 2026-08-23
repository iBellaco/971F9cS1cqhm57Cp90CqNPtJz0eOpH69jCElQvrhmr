import re

with open("app/src/main/java/com/example/ui/components/admin/AdminChampionEditor.kt", "r") as f:
    content = f.read()

target = """                    OutlinedTextField(
                        value = editSummary,
                        onValueChange = { editSummary = it },
                        label = { Text("Descripción (Lore/Resumen)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))"""

replacement = """                    OutlinedTextField(
                        value = editSummary,
                        onValueChange = { editSummary = it },
                        label = { Text("Descripción (Lore/Resumen)", fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(tr("Habilidades"), color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    editSkills.forEachIndexed { index, skill ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = HextechSurface),
                            border = BorderStroke(1.dp, HextechCardBorder)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(skill.slotName, color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                OutlinedTextField(
                                    value = skill.name,
                                    onValueChange = { newName ->
                                        val newSkills = editSkills.toMutableList()
                                        newSkills[index] = skill.copy(name = newName)
                                        editSkills = newSkills
                                    },
                                    label = { Text(tr("Nombre de Habilidad"), fontSize = 10.sp) },
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = skill.description,
                                    onValueChange = { newDesc ->
                                        val newSkills = editSkills.toMutableList()
                                        newSkills[index] = skill.copy(description = newDesc)
                                        editSkills = newSkills
                                    },
                                    label = { Text(tr("Descripción"), fontSize = 10.sp) },
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                    minLines = 2
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/ui/components/admin/AdminChampionEditor.kt", "w") as f:
        f.write(content)
    print("PATCH APPLIED")
else:
    print("TARGET NOT FOUND")
