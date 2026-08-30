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
import com.example.util.tr
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Terminal de Administración e Integración BestBuildWR
 * Permite ejecutar el script/crawler de extracción directa de https://bestbuildwr.com,
 * visualizar los logs de ejecución en tiempo real en consola interactiva estilo CLI Hacker / Hextech,
 * y descargar automáticamente los archivos generados (.csv, .json, .txt) a la carpeta de Descargas del dispositivo.
 */
@Composable
fun AdminTerminalScraperDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val clipboard = LocalClipboardManager.current
    val listState = rememberLazyListState()

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

    LaunchedEffect(Unit) {
        addLog("======================================================================", LogType.CYAN)
        addLog(" ⚔️ TERMINAL ADMINISTRADOR - BESTBUILDWR SCRAPER & CRAWLER", LogType.GOLD)
        addLog("======================================================================", LogType.CYAN)
        addLog("Target: https://bestbuildwr.com/champions", LogType.INFO)
        addLog("Headers: User-Agent Mobile Android 13 • Chrome/130.0.0.0", LogType.INFO)
        addLog("Salidas: bestbuildwr_builds.json • bestbuildwr_builds.csv • bestbuildwr_builds.txt", LogType.INFO)
        addLog("Presiona 'Ejecutar Scraper y Descargar' para iniciar la extracción en vivo.", LogType.WARNING)
    }

    fun startScraping() {
        if (isRunning) return
        isRunning = true
        executionFinished = false
        successStatus = null
        totalChampsProcessed = 0

        addLog("\n>>> INICIANDO PROCESO DE EXTRACCIÓN...", LogType.GOLD)
        addLog("[1] Conectando con https://bestbuildwr.com/champions...", LogType.INFO)

        scope.launch {
            val result = BestBuildScraper.runScraper(context) { progressMsg ->
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

            isRunning = false
            executionFinished = true
            successStatus = result

            if (result) {
                addLog("\n======================================================================", LogType.CYAN)
                addLog(" 🎉 PROCESO COMPLETADO SATISFACTORIAMENTE", LogType.SUCCESS)
                addLog(" Archivos guardados en: /Almacenamiento interno/Download/", LogType.GOLD)
                addLog(" 1. bestbuildwr_builds.json (Dataset completo JSON)", LogType.INFO)
                addLog(" 2. bestbuildwr_builds.csv (CSV con champion, build_name, build_url)", LogType.INFO)
                addLog(" 3. bestbuildwr_builds.txt (Formato champion | build_url)", LogType.INFO)
                addLog("======================================================================", LogType.CYAN)
                Toast.makeText(context, "✅ Builds descargadas en la carpeta Descargas", Toast.LENGTH_LONG).show()
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
                .fillMaxHeight(0.92f)
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
                                    text = "Terminal de Administrador",
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
                                        text = if (isRunning) "BUSY (CRAWLER ACTIVO)" else "PYTHON / CRAWLER",
                                        color = if (isRunning) HextechGold else HextechCyan,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                            Text(
                                text = "bestbuildwr_scraper.py • Extractor & Exportador",
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

                Spacer(modifier = Modifier.height(10.dp))

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
                                text = "Descargando builds...",
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
                                text = "Ejecutar Scraper y Descargar",
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
                        text = "Destino: Downloads/bestbuildwr_builds.json/.csv/.txt",
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
    onDismiss: () -> Unit
) {
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current

    val pythonCode = """
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
        "AppleWebKit/537.36 "
        "(KHTML, like Gecko) "
        "Chrome/130.0.0.0 Mobile Safari/537.36"
    )
}

session = requests.Session()
session.headers.update(HEADERS)


def descargar(url):

    try:
        respuesta = session.get(
            url,
            timeout=30
        )

        respuesta.raise_for_status()

        return respuesta.text

    except Exception as e:

        print(f"[ERROR] {url}")
        print(e)

        return None


def obtener_campeones():

    print("Obteniendo campeones...")

    html = descargar(CHAMPIONS_URL)

    if not html:
        return []

    soup = BeautifulSoup(
        html,
        "html.parser"
    )

    campeones = {}

    for enlace in soup.find_all(
        "a",
        href=True
    ):

        href = enlace["href"]

        url = urljoin(
            BASE_URL,
            href
        )

        # Solo URLs de campeones
        if "/champions/" not in url:
            continue

        # Evitar subrutas
        parte = url.split("/champions/")[-1]

        if "/" in parte:
            continue

        nombre = enlace.get_text(
            " ",
            strip=True
        )

        if not nombre:
            nombre = parte.replace(
                "-",
                " "
            ).title()

        campeones[url] = {
            "nombre": nombre,
            "url": url
        }

    return list(
        campeones.values()
    )


def obtener_builds(campeon):

    print()
    print("=" * 60)
    print(
        f"CAMPEÓN: {campeon['nombre']}"
    )
    print(
        campeon["url"]
    )

    html = descargar(
        campeon["url"]
    )

    if not html:
        return []

    soup = BeautifulSoup(
        html,
        "html.parser"
    )

    builds = {}

    # 1. Extraer de __NEXT_DATA__ (Next.js SPA data)
    next_data = soup.find("script", id="__NEXT_DATA__")
    if next_data and next_data.string:
        try:
            data = json.loads(next_data.string)
            c_props = data.get("props", {}).get("pageProps", {}).get("champion", {})
            builds_list = c_props.get("builds", [])
            for b in builds_list:
                b_path = b.get("path") or f"/builds/{b.get('id')}-{b.get('slug')}"
                b_url = urljoin(BASE_URL, b_path)
                b_name = b.get("name") or "Build General"
                builds[b_url] = {
                    "champion": campeon["nombre"],
                    "build_url": b_url,
                    "build_name": b_name
                }
        except Exception:
            pass

    # 2. Buscar enlaces <a> en HTML
    for enlace in soup.find_all(
        "a",
        href=True
    ):

        href = enlace["href"]

        url = urljoin(
            BASE_URL,
            href
        )

        # Solo queremos: https://bestbuildwr.com/builds/...
        if not url.startswith(
            BASE_URL + "/builds/"
        ):
            continue

        # Evitar duplicados
        if url in builds:
            continue

        nombre = enlace.get_text(
            " ",
            strip=True
        )

        builds[url] = {
            "champion": campeon["nombre"],
            "build_url": url,
            "build_name": nombre if nombre else "Build General"
        }

    resultado = list(
        builds.values()
    )

    print(
        f"Builds encontradas: "
        f"{len(resultado)}"
    )

    for build in resultado:

        print(
            build["build_url"]
        )

    return resultado


def main():

    print()
    print("=" * 60)
    print("BESTBUILDWR SCRAPER")
    print("=" * 60)
    print()

    campeones = obtener_campeones()

    print(
        f"\nCampeones encontrados: "
        f"{len(campeones)}"
    )

    todas_las_builds = []

    for numero, campeon in enumerate(
        campeones,
        start=1
    ):

        print(
            f"\n[{numero}/{len(campeones)}]"
        )

        builds = obtener_builds(
            campeon
        )

        todas_las_builds.extend(
            builds
        )

        # Pausa para no realizar
        # demasiadas peticiones seguidas
        time.sleep(1)

    # Eliminar duplicados
    unicas = {}

    for build in todas_las_builds:

        unicas[
            build["build_url"]
        ] = build

    todas_las_builds = list(
        unicas.values()
    )

    print()
    print("=" * 60)
    print("RESULTADO")
    print("=" * 60)

    print(
        f"Total de builds: "
        f"{len(todas_las_builds)}"
    )

    # ------------------------------------------------
    # JSON
    # ------------------------------------------------

    with open(
        "bestbuildwr_builds.json",
        "w",
        encoding="utf-8"
    ) as archivo:

        json.dump(
            todas_las_builds,
            archivo,
            ensure_ascii=False,
            indent=2
        )

    # ------------------------------------------------
    # CSV
    # ------------------------------------------------

    with open(
        "bestbuildwr_builds.csv",
        "w",
        encoding="utf-8-sig",
        newline=""
    ) as archivo:

        escritor = csv.DictWriter(
            archivo,
            fieldnames=[
                "champion",
                "build_name",
                "build_url"
            ]
        )

        escritor.writeheader()

        escritor.writerows(
            todas_las_builds
        )

    # ------------------------------------------------
    # TXT
    # ------------------------------------------------

    with open(
        "bestbuildwr_builds.txt",
        "w",
        encoding="utf-8"
    ) as archivo:

        for build in todas_las_builds:

            archivo.write(
                f"{build['champion']} | "
                f"{build['build_url']}\n"
            )

    print()
    print("Archivos creados:")
    print(
        "  bestbuildwr_builds.json"
    )
    print(
        "  bestbuildwr_builds.csv"
    )
    print(
        "  bestbuildwr_builds.txt"
    )


if __name__ == "__main__":
    main()
""".trimIndent()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f)
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
                        text = "🐍 Script Python (BestBuildWR Scraper)",
                        color = HextechCyan,
                        fontSize = 14.sp,
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
                                text = pythonCode,
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
                        clipboard.setText(AnnotatedString(pythonCode))
                        Toast.makeText(context, "Código copiado", Toast.LENGTH_SHORT).show()
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
