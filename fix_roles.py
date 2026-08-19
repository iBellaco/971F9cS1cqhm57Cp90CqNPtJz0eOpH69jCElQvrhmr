import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r') as f:
    content = f.read()

# translate lane roles and match terminology
content = content.replace('Text(mainRole.name, color = HextechGold, fontWeight = FontWeight.Bold)', 
'''val roleName = if (com.example.util.LocalLanguage.current == "en") mainRole.name else when(mainRole.name) {
                                "MID" -> "MEDIO"
                                "TOP" -> "BARÓN (TOP)"
                                "JUNGLE" -> "JUNGLA"
                                "ADC" -> "DRAGÓN (ADC)"
                                "SUPPORT" -> "SOPORTE"
                                else -> mainRole.name
                            }
                            Text(roleName, color = HextechGold, fontWeight = FontWeight.Bold)''')

content = content.replace('Text(secondRole.name, color = HextechGold, fontWeight = FontWeight.Bold)', 
'''val roleName2 = if (com.example.util.LocalLanguage.current == "en") secondRole.name else when(secondRole.name) {
                                "MID" -> "MEDIO"
                                "TOP" -> "BARÓN (TOP)"
                                "JUNGLE" -> "JUNGLA"
                                "ADC" -> "DRAGÓN (ADC)"
                                "SUPPORT" -> "SOPORTE"
                                else -> secondRole.name
                            }
                            Text(roleName2, color = HextechGold, fontWeight = FontWeight.Bold)''')

content = content.replace('Text(autofillRole.name, color = DangerRed, fontWeight = FontWeight.Bold)', 
'''val roleName3 = if (com.example.util.LocalLanguage.current == "en") autofillRole.name else when(autofillRole.name) {
                                "MID" -> "MEDIO"
                                "TOP" -> "BARÓN (TOP)"
                                "JUNGLE" -> "JUNGLA"
                                "ADC" -> "DRAGÓN (ADC)"
                                "SUPPORT" -> "SOPORTE"
                                else -> autofillRole.name
                            }
                            Text(roleName3, color = DangerRed, fontWeight = FontWeight.Bold)''')

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w') as f:
    f.write(content)
