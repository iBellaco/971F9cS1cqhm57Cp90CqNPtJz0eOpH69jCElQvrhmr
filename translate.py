import os
import re

strings = {
    "Elige tu idioma": "Choose your language",
    "Automático (Sistema)": "System default",
    "Español": "Spanish",
    "English": "English",
    "Continuar / Continue": "Continue",
    "Continuar a la App": "Continue to App",
    "Add Data": "Add Data",
    "Read Data": "Read Data",
    "Delete Data": "Delete Data",
    "Copy Logs": "Copy Logs",
    "Añadir Datos": "Add Data",
    "Leer Datos": "Read Data",
    "Borrar Datos": "Delete Data",
    "Copiar Logs": "Copy Logs",
    "Logs Copied": "Logs Copied",
    "Data Added": "Data Added",
    "Data Read Success": "Data Read Success",
    "Data Deleted": "Data Deleted",
    "Entendido": "Understood",
    "Permiso de Superposición": "Overlay Permission",
    "Conceder Permiso": "Grant Permission",
    "Cancelar": "Cancel",
    "Ver Guía & Estadísticas en Fuentes Meta:": "View Guide & Stats in Meta Sources:",
    "Mejores Sinergias (Composición):": "Best Synergies (Composition):",
    "Débil Contra:": "Weak Against:",
    "Ventaja Contra:": "Strong Against:",
    "Objetos Situacionales:": "Situational Items:",
    "Core Items (Obligatorios):": "Core Items:",
    "Árbol de Runas Meta:": "Meta Rune Tree:",
    "Hechizos de Invocador:": "Summoner Spells:"
}

kotlin_code = """
package com.example.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable

val LocalLanguage = compositionLocalOf { "es" }

val translations = mapOf(
    "en" to mapOf(
"""
for k, v in strings.items():
    kotlin_code += f'        "{k}" to "{v}",\n'

kotlin_code += """    )
)

@Composable
fun tr(key: String): String {
    val lang = LocalLanguage.current
    if (lang == "es" || lang == "auto") return key
    return translations["en"]?.get(key) ?: key
}

fun trStr(lang: String, key: String): String {
    if (lang == "es" || lang == "auto") return key
    return translations["en"]?.get(key) ?: key
}
"""

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(kotlin_code)

def replace_in_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()
    
    # We will manually replace some keys to use tr(key) in compose
    # For now, let's just make the DatabaseTestScreen and LanguageSelectionScreen use Translator
    pass

