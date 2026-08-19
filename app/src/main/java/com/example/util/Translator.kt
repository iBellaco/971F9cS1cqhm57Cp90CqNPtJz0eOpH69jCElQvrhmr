
package com.example.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

val LocalLanguage = compositionLocalOf { "es" }

val translations = mapOf(
    "en" to mapOf(
        "Elige tu idioma" to "Choose your language",
        "Automático (Sistema)" to "System default",
        "Español" to "Spanish",
        "Inglés" to "English",
        "Continuar / Continue" to "Continue",
        "Firestore & Logs Test Panel" to "Firestore & Logs Test Panel",
        "Add Data" to "Add Data",
        "Read Data" to "Read Data",
        "Delete Data" to "Delete Data",
        "Continuar a la App" to "Continue to App",
        "Copy Logs" to "Copy Logs",
        "Logs:" to "Logs:",
        "Permiso de Superposición" to "Overlay Permission",
        "Para que el asistente inteligente funcione en segundo plano sobre Wild Rift, Android requiere habilitar 'Aparecer encima' (Superposición)." to "For the smart assistant to run in the background over Wild Rift, Android requires enabling the 'Display over other apps' (Overlay) permission.",
        "El asistente proporciona lecturas de pantalla y sugerencias en tiempo real sin salir del juego." to "The assistant provides screen readings and real-time suggestions without leaving the game.",
        "Conceder Permiso" to "Grant Permission",
        "Cancelar" to "Cancel",
        "Base de Datos Meta (Parche 16.16.1)" to "Meta Database (Patch 16.16.1)",
        "Campeones" to "Champions",
        "Sinergias" to "Synergies",
        "Items" to "Items",
        "Runas" to "Runes",
        "Ver Guía & Estadísticas en Fuentes Meta:" to "View Guide & Stats in Meta Sources:",
        "Hechizos de Invocador:" to "Summoner Spells:",
        "Árbol de Runas Meta:" to "Meta Rune Tree:",
        "Core Items (Obligatorios):" to "Core Items:",
        "Objetos Situacionales:" to "Situational Items:",
        "Mejores Sinergias (Composición):" to "Best Synergies (Composition):",
        "Ventaja Contra:" to "Strong Against:",
        "Débil Contra:" to "Weak Against:",
        "Entendido" to "Understood",
        "Añadir Datos" to "Add Data",
        "Leer Datos" to "Read Data",
        "Borrar Datos" to "Delete Data",
        "Copiar Logs" to "Copy Logs",
        "Logs Copied" to "Logs Copied",
        "Data Added" to "Data Added",
        "Data Read Success" to "Data Read Success",
        "Data Deleted" to "Data Deleted",
        "No se pudo cargar la información" to "Could not load information",
        "Analizando Draft..." to "Analyzing Draft...",
        "Activar Asistente (Superposición)" to "Activate Assistant (Overlay)",
        "Buscando composiciones óptimas y counters..." to "Searching for optimal compositions and counters...",
        "Asistente Wild Rift" to "Wild Rift Assistant",
        "Cerrar" to "Close",
        "Error" to "Error",
        "Información" to "Information",
    )
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
