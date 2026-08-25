package com.example.data.supabase

import android.net.Uri
import android.util.Log
import com.example.data.WildRiftItemsData
import com.example.data.supabase.model.WrItemDto
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Diagnóstico de URLs de imágenes de objetos retornados por la base de datos Supabase.
 * Permite identificar anomalías como prefijos faltantes, extensiones incorrectas, URLs vacías, etc.
 */
object SupabaseItemDiagnostics {
    private const val TAG = "SupabaseItemDiagnostics"
    private const val TABLE_ITEMS = "wr_items"

    enum class IssueType {
        MISSING_URL,
        MISSING_SCHEME_OR_PREFIX,
        RELATIVE_PATH_WITHOUT_DOMAIN,
        INVALID_OR_MISSING_EXTENSION,
        CLEARTEXT_HTTP_INSECURE,
        MALFORMED_URI,
        PLACEHOLDER_OR_INVALID_DOMAIN,
        CANONICAL_MISMATCH
    }

    data class ItemIconIssue(
        val itemId: String,
        val itemName: String,
        val category: String,
        val rawUrl: String,
        val issueType: IssueType,
        val description: String,
        val suggestedCanonicalUrl: String?
    )

    data class SupabaseItemDiagnosticReport(
        val totalItemsChecked: Int,
        val validCount: Int,
        val issueCount: Int,
        val issues: List<ItemIconIssue>,
        val fullLogOutput: String
    )

    private val VALID_EXTENSIONS = setOf("webp", "png", "jpg", "jpeg", "svg", "gif")

    /**
     * Inspecciona todas las URLs de objetos de Supabase y genera un informe de diagnóstico
     * tanto en Logcat (Log.i/Log.w/Log.e) como en un objeto structured `SupabaseItemDiagnosticReport`.
     */
    suspend fun diagnoseItemIconUrls(logToLogcat: Boolean = true): Result<SupabaseItemDiagnosticReport> = withContext(Dispatchers.IO) {
        try {
            val postgrest = SupabaseClientManager.client.postgrest
            val rawDtos = postgrest.from(TABLE_ITEMS).select().decodeList<WrItemDto>()

            val canonicalMapById = WildRiftItemsData.list.associateBy { it.id }
            val canonicalMapByName = WildRiftItemsData.list.associateBy { it.name.lowercase().trim() }

            val issues = mutableListOf<ItemIconIssue>()
            val logLines = mutableListOf<String>()

            fun appendLog(line: String) {
                logLines.add(line)
            }

            appendLog("════════════════════════════════════════════════════════════════════")
            appendLog("🔍 [SUPABASE ITEM ICONS DIAGNOSTIC REPORT]")
            appendLog("Total registros en tabla '$TABLE_ITEMS': ${rawDtos.size}")
            appendLog("────────────────────────────────────────────────────────────────────")

            rawDtos.forEach { dto ->
                val canonical = canonicalMapById[dto.id] ?: canonicalMapByName[dto.name.lowercase().trim()]
                val rawUrl = dto.iconUrl.trim()

                val detectedIssues = analyzeUrl(dto.id, dto.name, dto.category, rawUrl, canonical?.iconUrl)
                if (detectedIssues.isNotEmpty()) {
                    issues.addAll(detectedIssues)
                }
            }

            val totalCount = rawDtos.size
            val issueItemIds = issues.map { it.itemId }.distinct().size
            val healthyCount = (totalCount - issueItemIds).coerceAtLeast(0)

            appendLog("📊 RESUMEN:")
            appendLog("  • Ítems analizados: $totalCount")
            appendLog("  • Ítems con URLs válidas: $healthyCount")
            appendLog("  • Ítems con incidencias/advertencias: $issueItemIds (Total anomalías: ${issues.size})")
            appendLog("────────────────────────────────────────────────────────────────────")

            if (issues.isEmpty()) {
                appendLog("✅ ¡Todas las URLs de objetos en Supabase son válidas, con prefijo HTTPS y formato correcto!")
            } else {
                appendLog("⚠️ DETALLE DE INCIDENCIAS DETECTADAS:")
                issues.forEachIndexed { index, issue ->
                    appendLog("[#${index + 1}] Ítem: '${issue.itemName}' (ID: '${issue.itemId}') | Cat: '${issue.category}'")
                    appendLog("    ├─ URL en Supabase: ${if (issue.rawUrl.isBlank()) "<VACÍA>" else "'${issue.rawUrl}'"}")
                    appendLog("    ├─ Tipo de Anomalía: ${issue.issueType.name}")
                    appendLog("    ├─ Causa: ${issue.description}")
                    if (!issue.suggestedCanonicalUrl.isNullOrBlank()) {
                        appendLog("    └─ Corrección sugerida: '${issue.suggestedCanonicalUrl}'")
                    } else {
                        appendLog("    └─ Corrección sugerida: Ninguna (objeto no presente en catálogo local)")
                    }
                }
            }
            appendLog("════════════════════════════════════════════════════════════════════")

            val fullOutput = logLines.joinToString("\n")

            if (logToLogcat) {
                if (issues.isNotEmpty()) {
                    Log.w(TAG, "Diagnóstico finalizado con ${issues.size} incidencias detectadas:")
                } else {
                    Log.i(TAG, "Diagnóstico finalizado sin errores:")
                }
                logLines.forEach { line ->
                    if (line.startsWith("[#") || line.contains("⚠️") || line.contains("INCIDENCIA")) {
                        Log.w(TAG, line)
                    } else if (line.contains("❌") || line.contains("ERROR")) {
                        Log.e(TAG, line)
                    } else {
                        Log.i(TAG, line)
                    }
                }
            }

            Result.success(
                SupabaseItemDiagnosticReport(
                    totalItemsChecked = totalCount,
                    validCount = healthyCount,
                    issueCount = issues.size,
                    issues = issues,
                    fullLogOutput = fullOutput
                )
            )
        } catch (e: Exception) {
            val errorMsg = "Error al ejecutar diagnóstico de URLs de Supabase: ${e.message}"
            Log.e(TAG, errorMsg, e)
            Result.failure(e)
        }
    }

    private fun analyzeUrl(
        itemId: String,
        itemName: String,
        category: String,
        rawUrl: String,
        canonicalUrl: String?
    ): List<ItemIconIssue> {
        val issues = mutableListOf<ItemIconIssue>()

        // 1. Verificación de URL vacía o nula
        if (rawUrl.isBlank()) {
            issues.add(
                ItemIconIssue(
                    itemId = itemId,
                    itemName = itemName,
                    category = category,
                    rawUrl = rawUrl,
                    issueType = IssueType.MISSING_URL,
                    description = "El campo 'icon_url' está vacío o nulo en Supabase.",
                    suggestedCanonicalUrl = canonicalUrl
                )
            )
            return issues
        }

        // 2. Verificación de prefijo o esquema (http://, https://)
        val lowerUrl = rawUrl.lowercase()
        if (!lowerUrl.startsWith("http://") && !lowerUrl.startsWith("https://")) {
            if (lowerUrl.startsWith("/") || lowerUrl.startsWith("uploads/") || lowerUrl.startsWith("images/")) {
                issues.add(
                    ItemIconIssue(
                        itemId = itemId,
                        itemName = itemName,
                        category = category,
                        rawUrl = rawUrl,
                        issueType = IssueType.RELATIVE_PATH_WITHOUT_DOMAIN,
                        description = "Ruta relativa detectada sin dominio base (ej. falta prefijo 'https://wr-meta.com').",
                        suggestedCanonicalUrl = canonicalUrl ?: "https://wr-meta.com/${rawUrl.trimStart('/')}"
                    )
                )
            } else if (lowerUrl.startsWith("//")) {
                issues.add(
                    ItemIconIssue(
                        itemId = itemId,
                        itemName = itemName,
                        category = category,
                        rawUrl = rawUrl,
                        issueType = IssueType.MISSING_SCHEME_OR_PREFIX,
                        description = "Falta el esquema de protocolo 'https:' en la URL ('//...').",
                        suggestedCanonicalUrl = "https:$rawUrl"
                    )
                )
            } else {
                issues.add(
                    ItemIconIssue(
                        itemId = itemId,
                        itemName = itemName,
                        category = category,
                        rawUrl = rawUrl,
                        issueType = IssueType.MISSING_SCHEME_OR_PREFIX,
                        description = "La URL no tiene prefijo 'https://' ni protocolo válido.",
                        suggestedCanonicalUrl = canonicalUrl
                    )
                )
            }
        }

        // 3. Verificación de HTTP no seguro (Android bloquea Cleartext Traffic por defecto)
        if (lowerUrl.startsWith("http://")) {
            issues.add(
                ItemIconIssue(
                    itemId = itemId,
                    itemName = itemName,
                    category = category,
                    rawUrl = rawUrl,
                    issueType = IssueType.CLEARTEXT_HTTP_INSECURE,
                    description = "URL usa 'http://' no seguro. Android bloquea tráfico no cifrado por defecto.",
                    suggestedCanonicalUrl = rawUrl.replaceFirst("http://", "https://", ignoreCase = true)
                )
            )
        }

        // 4. Verificación de extensión de archivo
        try {
            val uri = Uri.parse(rawUrl)
            val path = uri.path ?: ""
            val lastDotIndex = path.lastIndexOf('.')
            if (lastDotIndex == -1 || lastDotIndex == path.length - 1) {
                issues.add(
                    ItemIconIssue(
                        itemId = itemId,
                        itemName = itemName,
                        category = category,
                        rawUrl = rawUrl,
                        issueType = IssueType.INVALID_OR_MISSING_EXTENSION,
                        description = "La ruta de la imagen no contiene ninguna extensión de archivo (ej. .webp, .png).",
                        suggestedCanonicalUrl = canonicalUrl
                    )
                )
            } else {
                val extension = path.substring(lastDotIndex + 1).lowercase().trim()
                if (extension !in VALID_EXTENSIONS) {
                    issues.add(
                        ItemIconIssue(
                            itemId = itemId,
                            itemName = itemName,
                            category = category,
                            rawUrl = rawUrl,
                            issueType = IssueType.INVALID_OR_MISSING_EXTENSION,
                            description = "Extensión no soportada o sospechosa ('.$extension'). Se esperaba: .webp, .png, .jpg, .svg.",
                            suggestedCanonicalUrl = canonicalUrl
                        )
                    )
                }
            }
        } catch (e: Exception) {
            issues.add(
                ItemIconIssue(
                    itemId = itemId,
                    itemName = itemName,
                    category = category,
                    rawUrl = rawUrl,
                    issueType = IssueType.MALFORMED_URI,
                    description = "Error al parsear sintaxis de URI: ${e.message}",
                    suggestedCanonicalUrl = canonicalUrl
                )
            )
        }

        // 5. Verificación de placeholders o dominios erróneos
        if (lowerUrl.contains("placeholder") || lowerUrl.contains("example.com") || lowerUrl.contains("undefined") || lowerUrl.contains("null")) {
            issues.add(
                ItemIconIssue(
                    itemId = itemId,
                    itemName = itemName,
                    category = category,
                    rawUrl = rawUrl,
                    issueType = IssueType.PLACEHOLDER_OR_INVALID_DOMAIN,
                    description = "URL contiene texto de marcador de posición (placeholder/dummy data).",
                    suggestedCanonicalUrl = canonicalUrl
                )
            )
        }

        // 6. Diferencia con el catálogo canónico del cliente (si difiere)
        if (canonicalUrl != null && canonicalUrl.isNotBlank() && canonicalUrl != rawUrl && issues.isEmpty()) {
            issues.add(
                ItemIconIssue(
                    itemId = itemId,
                    itemName = itemName,
                    category = category,
                    rawUrl = rawUrl,
                    issueType = IssueType.CANONICAL_MISMATCH,
                    description = "La URL de Supabase difiere de la URL canónica HD del cliente.",
                    suggestedCanonicalUrl = canonicalUrl
                )
            )
        }

        return issues
    }
}
