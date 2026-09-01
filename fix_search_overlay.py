import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

replacement_logic = '''        var searchChampQuery by remember { mutableStateOf("") }
        val filteredList = remember(searchChampQuery) {
            WildRiftRepository.champions.filter {
                searchChampQuery.isBlank() || it.name.contains(searchChampQuery, ignoreCase = true)
            }.sortedBy { it.name }
        }'''

content = content.replace('''        val filteredList = remember {
            WildRiftRepository.champions.sortedBy { it.name }
        }''', replacement_logic)

replacement_ui = '''                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = searchChampQuery,
                        onValueChange = { searchChampQuery = it },
                        placeholder = { Text(tr("Buscar campeón..."), fontSize = 11.sp) },
                        modifier = Modifier.fillMaxWidth().height(46.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 11.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = HextechCyan,
                            unfocusedBorderColor = HextechCardBorder
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))'''

content = content.replace('''                    }



                    Spacer(modifier = Modifier.height(6.dp))''', replacement_ui)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
