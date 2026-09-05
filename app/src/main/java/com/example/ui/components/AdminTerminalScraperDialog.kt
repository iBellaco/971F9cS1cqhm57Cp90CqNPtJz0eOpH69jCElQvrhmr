package com.example.ui.components

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.BestBuildScraper
import com.example.util.WildRiftOfficialScraper
import com.example.util.tr
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

enum class ScraperSource(val title: String, val targetDesc: String, val badge: String) {
    WILD_RIFT_OFFICIAL(
        title = "Wild Rift Oficial (Imágenes & Campeones)",
        targetDesc = "https://wildrift.leagueoflegends.com/es-es/champions/",
        badge = "RIOT OFFICIAL WEB"
    ),
    BEST_BUILD_WR(
        title = "BestBuildWR (Meta Pro Builds)",
        targetDesc = "https://bestbuildwr.com",
        badge = "BESTBUILDWR SPA"
    )
}

/**
 * Terminal de Administración e Integración de Scrapers
 * Permite ejecutar:
 * 1. Scraper Oficial de Wild Rift (https://wildrift.leagueoflegends.com/es-es/champions/):
 *    Extrae lista de campeones oficiales, analiza su HTML/OG/imágenes, y genera archivos JSON, CSV y TXT.
 * 2. Crawler BestBuildWR (https://bestbuildwr.com):
 *    Extrae builds profesionales de meta global con exportación a Descargas.
 * Incluye visor de consola en tiempo real estilo Hacker / Hextech CLI y acceso a código Python reproducible.
 */
@Composable
fun AdminTerminalScraperDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val clipboard = LocalClipboardManager.current
    val listState = rememberLazyListState()

    var selectedSource by remember { mutableStateOf(ScraperSource.WILD_RIFT_OFFICIAL) }
    var isRunning by remember { mutableStateOf(false) }
    val logs = remember { mutableStateListOf<TerminalLogEntry>() }
    var executionFinished by remember { mutableStateOf(false) }
    var successStatus by remember { mutableStateOf<Boolean?>(null) }
    var currentStepText by remember { mutableStateOf("Listo para ejecutar") }
    var totalChampsProcessed by remember { mutableStateOf(0) }
    var showRawPythonDialog by remember { mutableStateOf(false) }

    fun addLog(message: String, type: LogType = LogType.INFO) {
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        logs.add(TerminalLogEntry(time, message, type))
    }

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    LaunchedEffect(selectedSource) {
        logs.clear()
        addLog("======================================================================", LogType.CYAN)
        addLog(" ⚔️ TERMINAL ADMINISTRADOR - CRAWLER & SCRAPER ENGINE", LogType.GOLD)
        addLog("======================================================================", LogType.CYAN)
        addLog("Módulo Activo: ${selectedSource.title}", LogType.GOLD)
        addLog("Target: ${selectedSource.targetDesc}", LogType.INFO)
        addLog("Headers: User-Agent Mobile Android 14 • Chrome/140.0.0.0", LogType.INFO)
        if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) {
            addLog("Carpeta de destino: /Download/WildRift_Imagenes/", LogType.GOLD)
            addLog("Archivos generados: resultado.csv • urls_imagenes.txt • resumen.txt • resultado.json", LogType.INFO)
            addLog("Extracción: Algoritmo de scoring (Splash +90, Portrait +80, Nombre +100) y descarga binaria directa.", LogType.INFO)
        } else {
            addLog("Salidas: meta_pro_builds.json • meta_pro_builds.csv • meta_pro_builds.txt", LogType.INFO)
        }
        addLog("Presiona 'Ejecutar Scraper y Descargar' para iniciar la extracción en vivo.", LogType.WARNING)
    }

    fun startScraping() {
        if (isRunning) return
        isRunning = true
        executionFinished = false
        successStatus = null
        totalChampsProcessed = 0

        addLog("\n>>> INICIANDO PROCESO DE EXTRACCIÓN [${selectedSource.badge}]...", LogType.GOLD)
        addLog("[1] Conectando con ${selectedSource.targetDesc}...", LogType.INFO)

        scope.launch {
            val result = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) {
                WildRiftOfficialScraper.runScraper(context) { progressMsg ->
                    currentStepText = progressMsg
                    if (progressMsg.startsWith("[") && progressMsg.contains("/")) {
                        totalChampsProcessed++
                        addLog(progressMsg, LogType.GOLD)
                    } else if (progressMsg.contains("Guardada:")) {
                        addLog("  [OK] $progressMsg", LogType.SUCCESS)
                    } else if (progressMsg.startsWith("   ERROR") || progressMsg.startsWith("ERROR")) {
                        addLog("  [ERROR] $progressMsg", LogType.ERROR)
                    } else if (progressMsg.contains("RESULTADO FINAL") || progressMsg.contains("Guardando reportes") || progressMsg.contains("WILD RIFT - RESUMEN")) {
                        addLog(progressMsg, LogType.GOLD)
                    } else if (progressMsg.startsWith("===") || progressMsg.startsWith("---")) {
                        addLog(progressMsg, LogType.CYAN)
                    } else if (progressMsg.contains("Imagen seleccionada:")) {
                        addLog("  $progressMsg", LogType.CYAN)
                    } else {
                        addLog("  $progressMsg", LogType.INFO)
                    }
                }
            } else {
                BestBuildScraper.runScraper(context) { progressMsg ->
                    currentStepText = progressMsg
                    if (progressMsg.startsWith("[") && progressMsg.contains("Procesando")) {
                        totalChampsProcessed++
                        addLog("  [OK] $progressMsg", LogType.SUCCESS)
                    } else if (progressMsg.startsWith("Error") || progressMsg.startsWith("Fallo")) {
                        addLog("  [ERROR] $progressMsg", LogType.ERROR)
                    } else if (progressMsg.contains("Guardando") || progressMsg.contains("Completado") || progressMsg.contains("Total")) {
                        addLog("  [FILE] $progressMsg", LogType.GOLD)
                    } else {
                        addLog("  [*] $progressMsg", LogType.INFO)
                    }
                }
            }

            isRunning = false
            executionFinished = true
            successStatus = result

            if (result) {
                addLog("\n======================================================================", LogType.CYAN)
                addLog(" 🎉 EXTRACCIÓN COMPLETADA SATISFACTORIAMENTE", LogType.SUCCESS)
                addLog(" Archivos guardados en: /Almacenamiento interno/Download/WildRift_Imagenes/", LogType.GOLD)
                if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) {
                    addLog(" • resultado.csv (Listado de campeones, slug, imagen y estado)", LogType.INFO)
                    addLog(" • urls_imagenes.txt (Mapeo de Campeón | URL)", LogType.INFO)
                    addLog(" • resumen.txt (Estadísticas y recuento de descargas)", LogType.INFO)
                    addLog(" • resultado.json (Dataset en formato JSON)", LogType.INFO)
                    addLog(" • [Imágenes individuales descargadas por cada campeón]", LogType.SUCCESS)
                    Toast.makeText(context, "✅ Imágenes descargadas en Download/WildRift_Imagenes", Toast.LENGTH_LONG).show()
                } else {
                    addLog(" 1. bestbuildwr_builds.json (Dataset completo JSON)", LogType.INFO)
                    addLog(" 2. bestbuildwr_builds.csv (CSV con champion, build_name, build_url)", LogType.INFO)
                    addLog(" 3. bestbuildwr_builds.txt (Formato champion | build_url)", LogType.INFO)
                    Toast.makeText(context, "✅ Builds descargadas en la carpeta Descargas", Toast.LENGTH_LONG).show()
                }
                addLog("======================================================================", LogType.CYAN)
            } else {
                addLog("\n❌ Error durante la ejecución del Scraper. Revisa tu conexión de red.", LogType.ERROR)
            }
        }
    }

    Dialog(
        onDismissRequest = {
            if (!isRunning) onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.94f)
                .clip(RoundedCornerShape(16.dp))
                .border(1.5.dp, HextechGold, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF080D14))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
            ) {
                // Header Terminal Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechDarkBg)
                                .border(1.dp, HextechGold, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Terminal,
                                contentDescription = null,
                                tint = HextechGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "Terminal de Scrappers",
                                    color = HextechGold,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isRunning) HextechGold.copy(alpha = 0.2f) else HextechCyan.copy(alpha = 0.2f))
                                        .border(0.8.dp, if (isRunning) HextechGold else HextechCyan, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 5.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = if (isRunning) "BUSY (CRAWLER ACTIVO)" else selectedSource.badge,
                                        color = if (isRunning) HextechGold else HextechCyan,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                            Text(
                                text = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) "wildrift_images_scraper.py • Riot Games Web" else "bestbuildwr_crawler.py • Pro Builds Extractor",
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        IconButton(
                            onClick = { showRawPythonDialog = true },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = "Ver Script Python",
                                tint = HextechCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val fullLog = logs.joinToString("\n") { "[${it.time}] ${it.message}" }
                                clipboard.setText(AnnotatedString(fullLog))
                                Toast.makeText(context, "Log copiado al portapapeles", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copiar Terminal",
                                tint = HextechGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                if (!isRunning) onDismiss()
                            },
                            enabled = !isRunning,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = if (!isRunning) TextMuted else TextMuted.copy(alpha = 0.3f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Selector de Scrapper (Pestañas Wild Rift Oficial vs BestBuildWR)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0C1420))
                        .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ScraperSource.values().forEach { source ->
                        val isSelected = selectedSource == source
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) HextechGold.copy(alpha = 0.2f) else Color.Transparent)
                                .border(
                                    1.dp,
                                    if (isSelected) HextechGold else Color.Transparent,
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable(enabled = !isRunning) {
                                    if (selectedSource != source) {
                                        selectedSource = source
                                    }
                                }
                                .padding(vertical = 7.dp, horizontal = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (source == ScraperSource.WILD_RIFT_OFFICIAL) "🌐 Wild Rift Oficial (Imágenes)" else "⚡ BestBuildWR (Builds)",
                                color = if (isSelected) HextechGoldLight else TextMuted,
                                fontSize = 11.5.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Action Controls Panel
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { startScraping() },
                        enabled = !isRunning,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isRunning) HextechSurface else HextechGold,
                            disabledContainerColor = HextechSurface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, HextechGold)
                    ) {
                        if (isRunning) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = HextechGold,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Extrayendo datos...",
                                color = HextechGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.CloudDownload,
                                contentDescription = null,
                                tint = HextechDarkBg,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) "Scrapear Imágenes & Descargar" else "Scrapear Builds & Descargar",
                                color = HextechDarkBg,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = { logs.clear() },
                        enabled = !isRunning,
                        modifier = Modifier.height(44.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Limpiar", fontSize = 11.5.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Banner
                if (isRunning) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(HextechDarkBg)
                            .border(0.8.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Autorenew,
                            contentDescription = null,
                            tint = HextechCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = currentStepText,
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }

                // CLI Console Screen (Hacker / Matrix / LoL Engine Look)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF03070C))
                        .border(1.2.dp, Color(0xFF1B2838), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        items(logs) { log ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "[${log.time}] ",
                                    color = Color(0xFF4A6B82),
                                    fontSize = 10.5.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = log.message,
                                    color = when (log.type) {
                                        LogType.INFO -> Color(0xFFB0BEC5)
                                        LogType.SUCCESS -> Color(0xFF00FF7F)
                                        LogType.WARNING -> Color(0xFFFFD700)
                                        LogType.ERROR -> Color(0xFFFF453A)
                                        LogType.GOLD -> Color(0xFFF0E6D2)
                                        LogType.CYAN -> Color(0xFF00F0FF)
                                    },
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    lineHeight = 14.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Footer Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) {
                            "Destino: Downloads/WildRift_Imagenes/ (Imágenes + CSV + TXT + JSON)"
                        } else {
                            "Destino: Downloads/bestbuildwr_builds.json/.csv/.txt"
                        },
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                    Text(
                        text = "Logs: ${logs.size} líneas",
                        color = HextechGoldLight,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    if (showRawPythonDialog) {
        RawPythonCodeViewerDialog(
            selectedSource = selectedSource,
            onDismiss = { showRawPythonDialog = false }
        )
    }
}

private enum class LogType {
    INFO, SUCCESS, WARNING, ERROR, GOLD, CYAN
}

private data class TerminalLogEntry(
    val time: String,
    val message: String,
    val type: LogType
)

@Composable
private fun RawPythonCodeViewerDialog(
    selectedSource: ScraperSource,
    onDismiss: () -> Unit
) {
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current

    val officialPythonCode = """#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import re
import csv
import json
import time
import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse, unquote

# ============================================================
# CONFIGURACIÓN
# ============================================================

BASE_URL = "https://wildrift.leagueoflegends.com"
CHAMPIONS_URL = BASE_URL + "/es-es/champions/"

# CARPETA DE SALIDA
OUTPUT_DIR = "WildRift_Imagenes"

DELAY = 0.5
TIMEOUT = 30

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Linux; Android 14; Mobile) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/140.0.0.0 Mobile Safari/537.36"
    ),
    "Accept": "text/html,application/xhtml+xml,"
              "application/xml;q=0.9,image/avif,"
              "image/webp,*/*;q=0.8",
    "Accept-Language": "es-ES,es;q=0.9,en;q=0.8",
}

session = requests.Session()
session.headers.update(HEADERS)

os.makedirs(OUTPUT_DIR, exist_ok=True)


# ============================================================
# UTILIDADES
# ============================================================

def limpiar_url(url):
    if not url:
        return None
    url = url.strip()
    if url.startswith("//"):
        url = "https:" + url
    return urljoin(BASE_URL, url)


def descargar_html(url):
    try:
        response = session.get(url, timeout=TIMEOUT, allow_redirects=True)
        response.raise_for_status()
        return response.text
    except requests.RequestException as e:
        print(f"   ERROR: {e}")
        return None


def nombre_seguro(nombre):
    nombre = re.sub(r'[\\/:*?"<>|]', "", nombre)
    nombre = re.sub(r"\s+", " ", nombre)
    return nombre.strip()


def es_banner_invalido(url):
    if not url:
        return True
    u = unquote(url).lower()
    return (
        "5120x480" in u or
        "76190b66cbd804a79bb1a709498b5af75f36a735" in u or
        "20aeb6046d11ff197c4eeb93150853ddf2ff14c0" in u or
        "content_organization" in u or
        "128x128" in u or
        "96x96" in u or
        "riotbar" in u or
        "footer" in u or
        "favicon" in u
    )


def es_imagen(url):
    if not url or es_banner_invalido(url):
        return False
    u = unquote(url).lower()
    extensiones = (".jpg", ".jpeg", ".png", ".webp", ".avif")
    return any(ext in u for ext in extensiones)


def extension_imagen(url):
    path = urlparse(url).path.lower()
    if path.endswith(".jpeg"):
        return ".jpeg"
    if path.endswith(".png"):
        return ".png"
    if path.endswith(".webp"):
        return ".webp"
    if path.endswith(".avif"):
        return ".avif"
    return ".jpg"


# ============================================================
# DESCUBRIR CAMPEONES (NEXT_DATA + HTML)
# ============================================================

def obtener_campeones():
    print()
    print("=" * 70)
    print("OBTENIENDO CAMPEONES DE WILD RIFT")
    print("=" * 70)

    html = descargar_html(CHAMPIONS_URL)
    if not html:
        return []

    campeones = {}

    # 1. Extraer desde __NEXT_DATA__
    try:
        match_next = re.search(r'<script id="__NEXT_DATA__"[^>]*>(.*?)</script>', html, re.DOTALL)
        if match_next:
            data = json.loads(match_next.group(1))
            blades = data.get("props", {}).get("pageProps", {}).get("page", {}).get("blades", [])
            for blade in blades:
                if blade.get("type") == "characterCardGrid":
                    for item in blade.get("items", []):
                        title = item.get("title", "").strip()
                        media = item.get("media", {})
                        card_img = media.get("url")
                        action_url = item.get("action", {}).get("payload", {}).get("url", "")
                        clean_action = action_url.strip("/")
                        slug = clean_action.split("/")[-1].lower() if clean_action else ""
                        if slug and slug != "champions":
                            full_url = action_url if action_url.startswith("http") else f"{BASE_URL}/{clean_action}/"
                            name = title if title else slug.replace("-", " ").title()
                            campeones[slug] = {
                                "nombre": name,
                                "slug": slug,
                                "pagina": full_url,
                                "card_image_url": card_img
                            }
    except Exception as e:
        print(f"Aviso parseando __NEXT_DATA__: {e}")

    # 2. Fallback por tags <a>
    soup = BeautifulSoup(html, "lxml")
    for enlace in soup.find_all("a", href=True):
        href = limpiar_url(enlace.get("href"))
        if not href:
            continue
        path = urlparse(href).path
        match = re.match(r"^/es-es/champions/([^/]+)/?$", path)
        if not match:
            continue
        slug = match.group(1).lower()
        if not slug or slug == "champions":
            continue
        if slug not in campeones:
            nombre = enlace.get_text(" ", strip=True)
            if not nombre:
                nombre = slug.replace("-", " ").title()
            campeones[slug] = {
                "nombre": nombre,
                "slug": slug,
                "pagina": href,
                "card_image_url": None
            }

    resultado = list(campeones.values())
    resultado.sort(key=lambda x: x["slug"])
    print(f"\nCampeones encontrados: {len(resultado)}")
    return resultado


# ============================================================
# EXTRAER POSIBLES IMÁGENES Y SCORING
# ============================================================

def extraer_imagenes(html):
    soup = BeautifulSoup(html, "lxml")
    candidatos = []

    for meta in soup.find_all("meta"):
        propiedad = (meta.get("property") or meta.get("name") or "").lower()
        if propiedad in ("og:image", "og:image:url", "twitter:image", "twitter:image:src"):
            contenido = meta.get("content")
            if contenido and not es_banner_invalido(contenido):
                candidatos.append(contenido)

    for img in soup.find_all("img"):
        for atributo in ("src", "data-src", "data-original", "data-lazy-src", "data-image", "data-url"):
            valor = img.get(atributo)
            if valor:
                candidatos.append(valor)
        srcset = img.get("srcset")
        if srcset:
            for elemento in srcset.split(","):
                el = elemento.strip()
                if el:
                    candidatos.append(el.split()[0])

    for source in soup.find_all("source"):
        for atributo in ("src", "data-src", "data-original"):
            valor = source.get(atributo)
            if valor:
                candidatos.append(valor)
        srcset = source.get("srcset")
        if srcset:
            for elemento in srcset.split(","):
                el = elemento.strip()
                if el:
                    candidatos.append(el.split()[0])

    for elemento in soup.find_all(style=True):
        encontrados = re.findall(r'url\(["\']?([^"\')]+)', elemento.get("style", ""), re.IGNORECASE)
        candidatos.extend(encontrados)

    for script in soup.find_all("script"):
        texto = script.get_text()
        if not texto:
            continue
        urls = re.findall(r'https?://[^"\'<>\s\\]+', texto)
        candidatos.extend(urls)
        relativas = re.findall(r'["\']([^"\']+\.(?:jpg|jpeg|png|webp|avif)(?:\?[^"\']*)?)["\']', texto, re.IGNORECASE)
        candidatos.extend(relativas)

    resultado = []
    vistos = set()
    for cand in candidatos:
        cand = cand.strip()
        if not cand:
            continue
        url = limpiar_url(cand)
        if not url or not es_imagen(url):
            continue
        url = url.split("#")[0]
        if url not in vistos:
            vistos.add(url)
            resultado.append(url)
    return resultado


def puntuacion(url, nombre, slug):
    if es_banner_invalido(url):
        return -10000

    texto = unquote(url.lower())
    nombre_norm = re.sub(r"[^a-z0-9]", "", nombre.lower())
    slug_norm = re.sub(r"[^a-z0-9]", "", slug.lower())
    texto_norm = re.sub(r"[^a-z0-9]", "", texto)

    puntos = 0
    # Bonus prioritario: Card Portrait oficial (285x323)
    if "285x323" in texto or "285x328" in texto:
        puntos += 1000
    if "1280x720" in texto or "1920x1080" in texto or "1600x900" in texto:
        puntos += 300
    if "game_data" in texto:
        puntos += 150

    if nombre_norm and nombre_norm in texto_norm:
        puntos += 100
    if slug_norm and slug_norm in texto_norm:
        puntos += 100

    if "splash" in texto:
        puntos += 90
    if "portrait" in texto:
        puntos += 80
    if "champion" in texto:
        puntos += 50
    if "tile" in texto:
        puntos += 40
    if "loading" in texto:
        puntos += 30

    if "96x96" in texto:
        puntos -= 200
    if "128x128" in texto:
        puntos -= 250
    if "icon" in texto:
        puntos -= 80
    if "spell" in texto or "ability" in texto or "passive" in texto:
        puntos -= 120
    if "logo" in texto or "favicon" in texto:
        puntos -= 200
    if "banner" in texto or "header" in texto:
        puntos -= 300

    return puntos


def elegir_imagen(urls, nombre, slug):
    if not urls:
        return None
    validas = [u for u in urls if not es_banner_invalido(u)]
    if not validas:
        return None
    return sorted(validas, key=lambda x: puntuacion(x, nombre, slug), reverse=True)[0]


def extraer_mejor_imagen(html, nombre, slug, fallback_card):
    # 1. PRIORIDAD MÁXIMA: Card Portrait oficial (285x323) del catálogo
    if fallback_card and not es_banner_invalido(fallback_card):
        return fallback_card

    # 2. Buscar 285x323 en el JSON de Next.js
    try:
        match_next = re.search(r'<script id="__NEXT_DATA__"[^>]*>(.*?)</script>', html, re.DOTALL)
        if match_next:
            card_match = re.search(r'https?://cmsassets\.rgpub\.io/sanity/images/[^"\'<>\s\\]+285x32[0-9]\.(?:jpg|jpeg|png|webp)[^"\'<>\s\\]*', match_next.group(1), re.IGNORECASE)
            if card_match:
                url = card_match.group(0)
                if not es_banner_invalido(url):
                    return url

            data = json.loads(match_next.group(1))
            blades = data.get("props", {}).get("pageProps", {}).get("page", {}).get("blades", [])
            for blade in blades:
                if blade.get("type") == "landingMediaCarousel":
                    groups = blade.get("groups", [])
                    if groups:
                        media_url = groups[0].get("content", {}).get("media", {}).get("url")
                        if media_url and not es_banner_invalido(media_url):
                            return media_url
            
            # Buscar cualquier 1280x720 en el JSON
            splash_match = re.search(r'https?://cmsassets\.rgpub\.io/sanity/images/[^"\'<>\s\\]+1280x720\.(?:jpg|jpeg|png|webp)', match_next.group(1), re.IGNORECASE)
            if splash_match:
                url = splash_match.group(0)
                if not es_banner_invalido(url):
                    return url
    except Exception:
        pass

    # 3. Elegir según scoring descartando iconos/metadatos
    candidatos = extraer_imagenes(html)
    elegida = elegir_imagen(candidatos, nombre, slug)
    if elegida and puntuacion(elegida, nombre, slug) > 0 and not es_banner_invalido(elegida):
        return elegida

    return (fallback_card if fallback_card and not es_banner_invalido(fallback_card) else elegida)


def guardar_imagen(nombre, url):
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    nombre = nombre_seguro(nombre)
    extension = extension_imagen(url)
    archivo = os.path.join(OUTPUT_DIR, nombre + extension)

    # Limpiar posibles archivos residuales corruptos (< 4 kB como thumbnails 128x128)
    for ext in (".png", ".jpg", ".jpeg", ".webp"):
        posible = os.path.join(OUTPUT_DIR, nombre + ext)
        if os.path.exists(posible):
            tam = os.path.getsize(posible)
            if tam < 4000 or (ext == ".png" and extension != ".png"):
                try:
                    os.remove(posible)
                except Exception:
                    pass

    if os.path.exists(archivo) and os.path.getsize(archivo) > 5000:
        return archivo

    try:
        respuesta = session.get(url, timeout=60, stream=True)
        respuesta.raise_for_status()
        with open(archivo, "wb") as f:
            for bloque in respuesta.iter_content(chunk_size=65536):
                if bloque:
                    f.write(bloque)
        if os.path.getsize(archivo) == 0:
            os.remove(archivo)
            return None
        return archivo
    except Exception as e:
        print(f"      ERROR descargando: {e}")
        if os.path.exists(archivo):
            try:
                os.remove(archivo)
            except Exception:
                pass
        return None


# ============================================================
# FLUJO PRINCIPAL
# ============================================================

def main():
    inicio = time.time()
    print("=" * 70)
    print(" WILD RIFT - DESCARGADOR DE IMÁGENES OFICIALES")
    print("=" * 70)

    campeones = obtener_campeones()
    if not campeones:
        print("ERROR: No se encontraron campeones.")
        return

    resultados = []

    for i, c in enumerate(campeones, start=1):
        nombre = c["nombre"]
        slug = c["slug"]
        pagina = c["pagina"]
        card_img = c.get("card_image_url")

        print("-" * 70)
        print(f"[{i}/{len(campeones)}] {nombre}")
        print(f"Página: {pagina}")

        html = descargar_html(pagina)
        if not html:
            resultados.append({
                "campeon": nombre,
                "slug": slug,
                "url_imagen": "",
                "archivo": "",
                "estado": "ERROR_PAGINA"
            })
            time.sleep(DELAY)
            continue

        imagen = extraer_mejor_imagen(html, nombre, slug, card_img)
        if not imagen:
            print("   No se encontró imagen adecuada.")
            resultados.append({
                "campeon": nombre,
                "slug": slug,
                "url_imagen": "",
                "archivo": "",
                "estado": "SIN_IMAGEN"
            })
            time.sleep(DELAY)
            continue

        print(f"   Imagen seleccionada: {imagen}")
        archivo = guardar_imagen(nombre, imagen)

        if archivo:
            print(f"   Guardada: {archivo}")
            resultados.append({
                "campeon": nombre,
                "slug": slug,
                "url_imagen": imagen,
                "archivo": archivo,
                "estado": "OK"
            })
        else:
            print("   ERROR al guardar la imagen.")
            resultados.append({
                "campeon": nombre,
                "slug": slug,
                "url_imagen": imagen,
                "archivo": "",
                "estado": "ERROR_DESCARGA"
            })

        time.sleep(DELAY)

    # Guardar CSV, TXT y JSON
    csv_file = os.path.join(OUTPUT_DIR, "resultado.csv")
    with open(csv_file, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        writer.writerow(["campeon", "slug", "url_imagen", "archivo", "estado"])
        for r in resultados:
            writer.writerow([r["campeon"], r["slug"], r["url_imagen"], r["archivo"], r["estado"]])

    txt_file = os.path.join(OUTPUT_DIR, "urls_imagenes.txt")
    with open(txt_file, "w", encoding="utf-8") as f:
        for r in resultados:
            if r["url_imagen"]:
                f.write(f"{r['campeon']} | {r['url_imagen']}\n")

    json_file = os.path.join(OUTPUT_DIR, "resultado.json")
    with open(json_file, "w", encoding="utf-8") as f:
        json.dump(resultados, f, ensure_ascii=False, indent=2)

    descargadas = sum(1 for r in resultados if r["estado"] == "OK")
    errores = len(campeones) - descargadas
    duracion = time.time() - inicio

    resumen_file = os.path.join(OUTPUT_DIR, "resumen.txt")
    with open(resumen_file, "w", encoding="utf-8") as f:
        f.write(f"WILD RIFT - RESUMEN\n{'=' * 50}\n\n")
        f.write(f"Campeones encontrados: {len(campeones)}\n")
        f.write(f"Imágenes descargadas: {descargadas}\n")
        f.write(f"Errores: {errores}\n")
        f.write(f"Tiempo: {duracion:.1f} segundos\n")
        f.write(f"Carpeta: {OUTPUT_DIR}/\n")

    print("=" * 70)
    print(" RESULTADO FINAL")
    print("=" * 70)
    print(f"Campeones encontrados : {len(campeones)}")
    print(f"Imágenes descargadas  : {descargadas}")
    print(f"Errores               : {errores}")
    print(f"Tiempo                : {duracion:.1f} segundos")
    print(f"Carpeta de imágenes   : {OUTPUT_DIR}/")
    print("=" * 70)

if __name__ == "__main__":
    main()
""".trimIndent()

    val bestBuildPythonCode = """
import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin
import json
import csv
import time

BASE_URL = "https://bestbuildwr.com"
CHAMPIONS_URL = "https://bestbuildwr.com/champions"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Linux; Android 13; Mobile) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/130.0.0.0 Mobile Safari/537.36"
    )
}

session = requests.Session()
session.headers.update(HEADERS)


def descargar(url):
    try:
        respuesta = session.get(url, timeout=30)
        respuesta.raise_for_status()
        return respuesta.text
    except Exception as e:
        print(f"[ERROR] {url}: {e}")
        return None


def obtener_campeones():
    print("Obteniendo campeones...")
    html = descargar(CHAMPIONS_URL)
    if not html:
        return []

    soup = BeautifulSoup(html, "html.parser")
    campeones = {}

    for enlace in soup.find_all("a", href=True):
        href = enlace["href"]
        url = urljoin(BASE_URL, href)
        if "/champions/" not in url:
            continue
        parte = url.split("/champions/")[-1]
        if "/" in parte:
            continue

        nombre = enlace.get_text(" ", strip=True)
        if not nombre:
            nombre = parte.replace("-", " ").title()

        campeones[url] = {
            "nombre": nombre,
            "url": url
        }

    return list(campeones.values())


def main():
    campeones = obtener_campeones()
    print(f"Campeones encontrados: {len(campeones)}")


if __name__ == "__main__":
    main()
""".trimIndent()

    val currentCode = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) officialPythonCode else bestBuildPythonCode
    val titleText = if (selectedSource == ScraperSource.WILD_RIFT_OFFICIAL) {
        "🐍 Script Python (Wild Rift Oficial Images Scraper)"
    } else {
        "🐍 Script Python (BestBuildWR Pro Scraper)"
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.88f)
                .clip(RoundedCornerShape(14.dp))
                .border(1.2.dp, HextechCyan, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HextechDarkBg)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titleText,
                        color = HextechCyan,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(30.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF03070C))
                        .border(1.dp, HextechCardBorder, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Text(
                                text = currentCode,
                                color = HextechGreen,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        clipboard.setText(AnnotatedString(currentCode))
                        Toast.makeText(context, "Código copiado al portapapeles", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, tint = HextechDarkBg)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copiar Script Python", color = HextechDarkBg, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
