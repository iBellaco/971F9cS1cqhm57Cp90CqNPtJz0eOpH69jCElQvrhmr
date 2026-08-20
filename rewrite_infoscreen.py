import re
with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

# Let's fix Scaffold up to navigationIcon:
bad_block = re.search(r'title = \{.*?navigationIcon = \{', content, re.DOTALL)
if bad_block:
    new_block = '''title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = tr("Acerca De"),
                                color = TextPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(HextechCyan.copy(alpha = 0.15f))
                                    .border(1.dp, HextechCyan, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = WildRiftRepository.CURRENT_PATCH_VERSION,
                                    color = HextechCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    navigationIcon = {'''
    content = content.replace(bad_block.group(0), new_block)

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
