import re

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "r") as f:
    content = f.read()

content = content.replace("import com.example.data.sync.FirestoreManager\n", "")

# The block starts around line 290 with "Migrar Base de Datos Completa a la Nube (Firestore)". 
# Let's remove the whole button and text.
block = r'''                    Text\("Migrar Base de Datos Completa a la Nube \(Firestore\)", color = HextechDarkBg, fontWeight = FontWeight\.Bold, textAlign = androidx\.compose\.ui\.text\.style\.TextAlign\.Center\)
                    Spacer\(modifier = Modifier\.height\(8\.dp\)\)
                    
                    Button\(
                        onClick = \{
                            isUploading = true
                            scope\.launch \{
                                try \{
                                    val manager = FirestoreManager\(\)
                                    val errorMsg = manager\.uploadLocalDataToFirestore\(\)
                                    isUploading = false
                                    if \(errorMsg == null\) \{
                                        Toast\.makeText\(context, "Base de datos migrada a la nube con éxito", Toast\.LENGTH_LONG\)\.show\(\)
                                    \} else \{
                                        Toast\.makeText\(context, "Error: \$errorMsg", Toast\.LENGTH_LONG\)\.show\(\)
                                    \}
                                \} catch \(e: Exception\) \{
                                    isUploading = false
                                    Toast\.makeText\(context, "Fallo inesperado: \$\{e\.message\}", Toast\.LENGTH_LONG\)\.show\(\)
                                \}
                            \}
                        \},
                        modifier = Modifier\.fillMaxWidth\(\)\.height\(50\.dp\),
                        colors = ButtonDefaults\.buttonColors\(containerColor = HextechDarkBg\)
                    \) \{
                        if \(isUploading\) \{
                            CircularProgressIndicator\(color = HextechGold, modifier = Modifier\.size\(24\.dp\)\)
                        \} else \{
                            Text\("Upload Local JSON to Cloud", color = HextechGold\)
                        \}
                    \}
                    Spacer\(modifier = Modifier\.height\(24\.dp\)\)'''

content = re.sub(block, "", content)
content = content.replace("var isUploading by remember { mutableStateOf(false) }", "")

with open("app/src/main/java/com/example/ui/screens/InfoScreen.kt", "w") as f:
    f.write(content)
