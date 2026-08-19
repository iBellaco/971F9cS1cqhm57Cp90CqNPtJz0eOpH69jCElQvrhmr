import os
import re
import glob

# Collect all Kotlin files in ui directory
files = glob.glob('app/src/main/java/com/example/ui/**/*.kt', recursive=True)

# List of strings to translate
translations = {
    "Elige tu idioma": "Choose your language",
    "Automático (Sistema)": "System default",
    "Español": "Spanish",
    "Inglés": "English",
    "Continuar / Continue": "Continue",
    "Firestore & Logs Test Panel": "Firestore & Logs Test Panel",
    "Add Data": "Add Data",
    "Read Data": "Read Data",
    "Delete Data": "Delete Data",
    "Continuar a la App": "Continue to App",
    "Copy Logs": "Copy Logs",
    "Logs:": "Logs:",
    
    "Permiso de Superposición": "Overlay Permission",
    "Para que el asistente inteligente funcione en segundo plano sobre Wild Rift, Android requiere habilitar 'Aparecer encima' (Superposición).": "For the smart assistant to run in the background over Wild Rift, Android requires enabling the 'Display over other apps' (Overlay) permission.",
    "El asistente proporciona lecturas de pantalla y sugerencias en tiempo real sin salir del juego.": "The assistant provides screen readings and real-time suggestions without leaving the game.",
    "Conceder Permiso": "Grant Permission",
    "Cancelar": "Cancel",
    "Base de Datos Meta (Parche 16.16.1)": "Meta Database (Patch 16.16.1)",
    "Campeones": "Champions",
    "Sinergias": "Synergies",
    "Items": "Items",
    "Runas": "Runes",
    "Ver Guía & Estadísticas en Fuentes Meta:": "View Guide & Stats in Meta Sources:",
    "Hechizos de Invocador:": "Summoner Spells:",
    "Árbol de Runas Meta:": "Meta Rune Tree:",
    "Core Items (Obligatorios):": "Core Items:",
    "Objetos Situacionales:": "Situational Items:",
    "Mejores Sinergias (Composición):": "Best Synergies (Composition):",
    "Ventaja Contra:": "Strong Against:",
    "Débil Contra:": "Weak Against:",
    "Entendido": "Understood",
    
    "Añadir Datos": "Add Data",
    "Leer Datos": "Read Data",
    "Borrar Datos": "Delete Data",
    "Copiar Logs": "Copy Logs",
    "Logs Copied": "Logs Copied",
    "Data Added": "Data Added",
    "Data Read Success": "Data Read Success",
    "Data Deleted": "Data Deleted",
    "No se pudo cargar la información": "Could not load information",
    "Analizando Draft...": "Analyzing Draft...",
    "Activar Asistente (Superposición)": "Activate Assistant (Overlay)",
    "Buscando composiciones óptimas y counters...": "Searching for optimal compositions and counters...",
    "Asistente Wild Rift": "Wild Rift Assistant",
    "Cerrar": "Close",
    "Error": "Error",
    "Información": "Information"
}

translator_code = """
package com.example.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

val LocalLanguage = compositionLocalOf { "es" }

val translations = mapOf(
    "en" to mapOf(
"""
for es, en in translations.items():
    translator_code += f'        "{es}" to "{en}",\n'

translator_code += """    )
)

@Composable
fun tr(key: String): String {
    val lang = LocalLanguage.current
    if (lang == "es" || lang == "auto") return key
    return translations[lang]?.get(key) ?: key
}

fun trStr(lang: String, key: String): String {
    if (lang == "es" || lang == "auto") return key
    return translations[lang]?.get(key) ?: key
}
"""

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(translator_code)

def replace_strings(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Add import if needed
    if 'import com.example.util.tr' not in content and 'import androidx.compose.' in content:
        content = content.replace('import androidx.compose.runtime.Composable', 'import androidx.compose.runtime.Composable\nimport com.example.util.tr')

    for es_str, en_str in translations.items():
        # Text("es_str") -> Text(tr("es_str"))
        content = content.replace(f'Text("{es_str}"', f'Text(tr("{es_str}")')
        content = content.replace(f'Text(text = "{es_str}"', f'Text(text = tr("{es_str}")')
        content = content.replace(f'Toast.makeText(context, "{es_str}"', f'Toast.makeText(context, "{es_str}"') # Toast needs string context, keep as is for now or use trStr

    with open(filepath, 'w') as f:
        f.write(content)

for f in files:
    replace_strings(f)

print("Translation setup completed.")
