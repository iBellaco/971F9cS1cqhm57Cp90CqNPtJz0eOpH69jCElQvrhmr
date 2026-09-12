package com.example.data

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

data class AppNotice(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "Anuncio Importante",
    val content: String = "Bienvenido a Coach. ¡Consulta las últimas guías del parche y optimiza tu drafting!",
    val videoUrl: String = "", // Multimedia horizontal para panel de inicio
    val expandedImageUrl: String = "", // Imagen vertical para vista ampliada
    val externalUrl: String = "", // Enlace web externo opcional al tocar la imagen ampliada
    val tag: String = "Anuncios importantes", // "Anuncios importantes", "Ofertas", "Mantenimiento", "Noticia", "Streamer", "Publicidad"
    val titleColor: String = "#FFD700",
    val contentColor: String = "#CCCCCC",
    val isEnabled: Boolean = true,
    val budget: Double = 0.0 // Presupuesto asignado a este anuncio en USD
)

object AppNoticeManager {
    private const val TAG = "AppNoticeManager"
    private const val PREFS_NAME = "wild_rift_app_notices_prefs"
    private const val KEY_NOTICES_JSON = "notices_json_list"
    private const val KEY_INTERVAL_VALUE = "streamer_interval_value"
    private const val KEY_INTERVAL_UNIT = "streamer_interval_unit"

    private const val FIRESTORE_COLLECTION = "system_config"
    private const val FIRESTORE_DOC_NOTICES = "app_notices"

    private var firestoreListener: ListenerRegistration? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    private var isAuthenticatingAnonymously = false

    private val defaultNotices = listOf(
        AppNotice(
            title = "¡Nueva Actualización de Parche disponible!",
            content = "Consulta las tier lists actualizadas y optimiza tus picks para subir a Challenger.",
            tag = "Anuncios importantes",
            isEnabled = true
        ),
        AppNotice(
            title = "Oferta Especial en Esencias Azules",
            content = "¡Desbloquea avatares legendarios y bordes exclusivos con descuento por tiempo limitado!",
            tag = "Ofertas",
            isEnabled = true
        )
    )

    private val _notices = MutableStateFlow(defaultNotices)
    val notices: StateFlow<List<AppNotice>> = _notices.asStateFlow()

    private val _streamerIntervalValue = MutableStateFlow(10)
    val streamerIntervalValue: StateFlow<Int> = _streamerIntervalValue.asStateFlow()

    private val _streamerIntervalUnit = MutableStateFlow("seconds")
    val streamerIntervalUnit: StateFlow<String> = _streamerIntervalUnit.asStateFlow()

    fun init(context: Context) {
        val appContext = context.applicationContext
        // 1. Cargar caché local de inmediato (garantiza arranque instantáneo en 0ms)
        loadFromLocalStorage(appContext)
        preloadMedia(appContext, _notices.value)

        // 2. Intentar leer caché local de Firestore de inmediato (0ms de latencia)
        fetchFromFirestoreCache(appContext)

        // 3. Monitorear cambios de sesión/autenticación para mantener listener activo
        setupAuthStateListener(appContext)

        // 4. Conectar y sincronizar garantizando acceso inmediato sin bloqueos
        ensureAuthAndSync(appContext)
    }

    private fun fetchFromFirestoreCache(appContext: Context) {
        try {
            val db = FirebaseFirestore.getInstance()
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_NOTICES)
                .get(com.google.firebase.firestore.Source.CACHE)
                .addOnSuccessListener { snapshot ->
                    if (snapshot != null && snapshot.exists()) {
                        processFirestoreSnapshot(appContext, snapshot)
                    }
                }
                .addOnFailureListener {
                    // Si aún no está en caché de Firestore, se usará la caché local ya cargada
                }
        } catch (_: Exception) {}
    }

    private fun setupAuthStateListener(context: Context) {
        if (authStateListener != null) return
        try {
            val auth = FirebaseAuth.getInstance()
            authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                Log.d(TAG, "Cambio de estado de autenticación detectado (uid=${user?.uid}, anon=${user?.isAnonymous})")
                if (user == null) {
                    ensureAuthAndSync(context)
                } else {
                    attachFirestoreListener(context, force = true)
                    syncFromCloud(context)
                }
            }
            auth.addAuthStateListener(authStateListener!!)
        } catch (e: Exception) {
            Log.w(TAG, "Error inicializando AuthStateListener: ${e.message}")
        }
    }

    private fun ensureAuthAndSync(context: Context) {
        val appContext = context.applicationContext
        // Iniciar Firestore y fetch de inmediato sin esperar autenticación
        fetchFromFirestoreCache(appContext)
        attachFirestoreListener(appContext, force = false)
        executeCloudFetch(appContext, null)

        // En segundo plano sin bloquear la UI ni la carga inicial
        val auth = try { FirebaseAuth.getInstance() } catch (_: Exception) { null }
        if (auth?.currentUser == null) {
            com.example.util.GuestAuthHelper.ensureAuth {
                attachFirestoreListener(appContext, force = true)
                executeCloudFetch(appContext, null)
            }
        }
    }

    fun syncFromCloud(context: Context, onComplete: ((Boolean) -> Unit)? = null) {
        val appContext = context.applicationContext
        // Inmediato desde caché y en paralelo desde la nube
        fetchFromFirestoreCache(appContext)
        executeCloudFetch(appContext, onComplete)
    }

    private fun executeCloudFetch(appContext: Context, onComplete: ((Boolean) -> Unit)?) {
        try {
            val db = FirebaseFirestore.getInstance()
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_NOTICES)
                .get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot != null && snapshot.exists()) {
                        processFirestoreSnapshot(appContext, snapshot)
                        onComplete?.invoke(true)
                    } else {
                        Log.d(TAG, "Documento de anuncios en Firestore no encontrado.")
                        onComplete?.invoke(false)
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error forzando sincronización desde Firestore: ${e.message}")
                    onComplete?.invoke(false)
                }
        } catch (e: Exception) {
            Log.e(TAG, "Excepción en executeCloudFetch: ${e.message}")
            onComplete?.invoke(false)
        }
    }

    private fun processFirestoreSnapshot(context: Context, snapshot: com.google.firebase.firestore.DocumentSnapshot) {
        try {
            val rawList = snapshot.get("notices") as? List<*>
            val intervalVal = snapshot.getLong("streamerIntervalValue")?.toInt()
            val intervalUnit = snapshot.getString("streamerIntervalUnit")

            if (intervalVal != null && !intervalUnit.isNullOrBlank()) {
                _streamerIntervalValue.value = intervalVal
                _streamerIntervalUnit.value = intervalUnit
                saveIntervalToPrefs(context, intervalVal, intervalUnit)
            }

            if (rawList != null) {
                val parsedNotices = mutableListOf<AppNotice>()
                for (item in rawList) {
                    val map = item as? Map<*, *> ?: continue
                    parsedNotices.add(
                        AppNotice(
                            id = map["id"]?.toString() ?: UUID.randomUUID().toString(),
                            title = map["title"]?.toString() ?: "Aviso",
                            content = map["content"]?.toString() ?: "",
                            videoUrl = map["videoUrl"]?.toString() ?: "",
                            expandedImageUrl = map["expandedImageUrl"]?.toString() ?: "",
                            externalUrl = map["externalUrl"]?.toString() ?: "",
                            tag = map["tag"]?.toString() ?: "Anuncios importantes",
                            titleColor = map["titleColor"]?.toString() ?: "#FFD700",
                            contentColor = map["contentColor"]?.toString() ?: "#CCCCCC",
                            isEnabled = (map["isEnabled"] as? Boolean) ?: true,
                            budget = (map["budget"] as? Number)?.toDouble() ?: 0.0
                        )
                    )
                }
                _notices.value = parsedNotices
                saveNoticesToPrefs(context, parsedNotices)
                preloadMedia(context, parsedNotices)
                Log.d(TAG, "Sincronizados exitosamente ${parsedNotices.size} anuncios desde Firestore para todos los dispositivos.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error procesando snapshot de Firestore: ${e.message}")
        }
    }

    fun attachFirestoreListener(context: Context, force: Boolean = false) {
        if (force) {
            try {
                firestoreListener?.remove()
            } catch (_: Exception) {}
            firestoreListener = null
        }
        if (firestoreListener != null) return
        try {
            val db = FirebaseFirestore.getInstance()
            firestoreListener = db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_NOTICES)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w(TAG, "Error escuchando anuncios de Firestore: ${error.message}")
                        try {
                            firestoreListener?.remove()
                        } catch (_: Exception) {}
                        firestoreListener = null
                        // Si falló por falta de autenticación y no hay usuario, reintentar asegurar credenciales
                        com.example.util.GuestAuthHelper.ensureAuth {
                            attachFirestoreListener(context, force = false)
                        }
                        return@addSnapshotListener
                    }
                    if (snapshot != null && snapshot.exists()) {
                        processFirestoreSnapshot(context, snapshot)
                    }
                }
        } catch (e: Exception) {
            Log.e(TAG, "No se pudo iniciar listener de Firestore: ${e.message}")
            firestoreListener = null
        }
    }

    private fun saveIntervalToPrefs(context: Context, value: Int, unit: String) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putInt(KEY_INTERVAL_VALUE, value)
                .putString(KEY_INTERVAL_UNIT, unit)
                .apply()
        } catch (_: Exception) {}
    }

    private fun saveNoticesToPrefs(context: Context, list: List<AppNotice>) {
        try {
            val arr = JSONArray()
            for (n in list) {
                val obj = JSONObject().apply {
                    put("id", n.id)
                    put("title", n.title)
                    put("content", n.content)
                    put("videoUrl", n.videoUrl)
                    put("expandedImageUrl", n.expandedImageUrl)
                    put("externalUrl", n.externalUrl)
                    put("tag", n.tag)
                    put("titleColor", n.titleColor)
                    put("contentColor", n.contentColor)
                    put("isEnabled", n.isEnabled)
                    put("budget", n.budget)
                }
                arr.put(obj)
            }
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_NOTICES_JSON, arr.toString()).apply()
        } catch (_: Exception) {}
    }

    private fun loadFromLocalStorage(context: Context) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val intervalVal = prefs.getInt(KEY_INTERVAL_VALUE, 10)
            val intervalUnit = prefs.getString(KEY_INTERVAL_UNIT, "seconds") ?: "seconds"
            _streamerIntervalValue.value = intervalVal
            _streamerIntervalUnit.value = intervalUnit

            val jsonStr = prefs.getString(KEY_NOTICES_JSON, null)
            if (!jsonStr.isNullOrBlank()) {
                val arr = JSONArray(jsonStr)
                val loaded = mutableListOf<AppNotice>()
                for (i in 0 until arr.length()) {
                    val obj = arr.getJSONObject(i)
                    loaded.add(
                        AppNotice(
                            id = obj.optString("id", UUID.randomUUID().toString()),
                            title = obj.optString("title", ""),
                            content = obj.optString("content", ""),
                            videoUrl = obj.optString("videoUrl", ""),
                            expandedImageUrl = obj.optString("expandedImageUrl", ""),
                            externalUrl = obj.optString("externalUrl", ""),
                            tag = obj.optString("tag", "Anuncios importantes"),
                            titleColor = obj.optString("titleColor", "#C8AA6E"),
                            contentColor = obj.optString("contentColor", "#A09B8C"),
                            isEnabled = obj.optBoolean("isEnabled", true),
                            budget = obj.optDouble("budget", 0.0)
                        )
                    )
                }
                if (loaded.isNotEmpty()) {
                    _notices.value = loaded
                    preloadMedia(context, loaded)
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error cargando desde almacenamiento local: ${e.message}")
        }
    }

    /**
     * Precarga en segundo plano todas las imágenes y videos para que los anuncios
     * carguen a máxima velocidad (0ms de espera) al ser vistos por el usuario.
     */
    fun preloadMedia(context: Context, list: List<AppNotice>) {
        val appContext = context.applicationContext
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            try {
                val imageLoader = coil.Coil.imageLoader(appContext)
                for (notice in list) {
                    if (!notice.isEnabled) continue
                    val vUrl = notice.videoUrl.trim()
                    val expUrl = notice.expandedImageUrl.trim()
                    if (vUrl.isNotBlank()) {
                        if (com.example.ui.components.NoticeMediaUtils.isVideo(appContext, vUrl)) {
                            if (vUrl.startsWith("http://", ignoreCase = true) || vUrl.startsWith("https://", ignoreCase = true)) {
                                com.example.util.NoticeMediaStorageManager.cacheVideoFromUrl(appContext, vUrl)
                            }
                        } else {
                            val model: Any = if (vUrl.startsWith("data:image/")) {
                                com.example.util.NoticeMediaStorageManager.decodeDataUriToBytes(vUrl) ?: vUrl
                            } else vUrl
                            val req = coil.request.ImageRequest.Builder(appContext)
                                .data(model)
                                .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                                .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                                .build()
                            imageLoader.enqueue(req)
                        }
                    }
                    if (expUrl.isNotBlank()) {
                        val expModel: Any = if (expUrl.startsWith("data:image/")) {
                            com.example.util.NoticeMediaStorageManager.decodeDataUriToBytes(expUrl) ?: expUrl
                        } else expUrl
                        val req = coil.request.ImageRequest.Builder(appContext)
                            .data(expModel)
                            .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                            .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                            .build()
                        imageLoader.enqueue(req)
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error en precarga de medios: ${e.message}")
            }
        }
    }

    fun saveAllNoticesAndInterval(
        context: Context,
        newNotices: List<AppNotice>,
        intervalValue: Int,
        intervalUnit: String,
        onComplete: ((success: Boolean, errorMsg: String?) -> Unit)? = null
    ) {
        val appContext = context.applicationContext
        _notices.value = newNotices
        _streamerIntervalValue.value = intervalValue
        _streamerIntervalUnit.value = intervalUnit
        saveNoticesToPrefs(appContext, newNotices)
        saveIntervalToPrefs(appContext, intervalValue, intervalUnit)

        try {
            val db = FirebaseFirestore.getInstance()
            val listData = newNotices.map { n ->
                mapOf(
                    "id" to n.id,
                    "title" to n.title,
                    "content" to n.content,
                    "videoUrl" to n.videoUrl,
                    "expandedImageUrl" to n.expandedImageUrl,
                    "externalUrl" to n.externalUrl,
                    "tag" to n.tag,
                    "titleColor" to n.titleColor,
                    "contentColor" to n.contentColor,
                    "isEnabled" to n.isEnabled,
                    "budget" to n.budget
                )
            }
            val data = hashMapOf(
                "notices" to listData,
                "streamerIntervalValue" to intervalValue,
                "streamerIntervalUnit" to intervalUnit,
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_NOTICES)
                .set(data, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d(TAG, "Anuncios e intervalo guardados y sincronizados exitosamente en Firestore para todos los dispositivos.")
                    onComplete?.invoke(true, null)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error guardando anuncios en Firestore: ${e.message}")
                    onComplete?.invoke(false, e.localizedMessage ?: e.message)
                }
        } catch (e: Exception) {
            Log.e(TAG, "Excepción al sincronizar anuncios con Firestore: ${e.message}")
            onComplete?.invoke(false, e.localizedMessage ?: e.message)
        }
    }

    private fun pushNoticesToFirestore(context: Context, noticesList: List<AppNotice>) {
        saveAllNoticesAndInterval(
            context = context,
            newNotices = noticesList,
            intervalValue = _streamerIntervalValue.value,
            intervalUnit = _streamerIntervalUnit.value
        )
    }

    fun saveStreamerInterval(context: Context, value: Int, unit: String) {
        val appContext = context.applicationContext
        _streamerIntervalValue.value = value
        _streamerIntervalUnit.value = unit
        saveIntervalToPrefs(appContext, value, unit)

        try {
            val db = FirebaseFirestore.getInstance()
            val data = hashMapOf(
                "streamerIntervalValue" to value,
                "streamerIntervalUnit" to unit,
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_NOTICES)
                .set(data, SetOptions.merge())
        } catch (_: Exception) {}
    }

    fun getStreamerIntervalMillis(context: Context): Long {
        val value = _streamerIntervalValue.value.coerceAtLeast(1)
        return when (_streamerIntervalUnit.value) {
            "minutes" -> value * 60 * 1000L
            "hours" -> value * 60 * 60 * 1000L
            else -> value * 1000L
        }
    }

    fun saveNotices(context: Context, newNotices: List<AppNotice>) {
        val appContext = context.applicationContext
        _notices.value = newNotices
        saveNoticesToPrefs(appContext, newNotices)
        pushNoticesToFirestore(appContext, newNotices)
    }

    // For backward compatibility if single update is called
    fun updateNotice(context: Context, notice: AppNotice) {
        val current = _notices.value.toMutableList()
        val index = current.indexOfFirst { it.id == notice.id }
        if (index >= 0) {
            current[index] = notice
        } else {
            current.add(notice)
        }
        saveNotices(context, current)
    }

    fun updateNoticeBudget(context: Context, noticeId: String, newBudget: Double) {
        val currentList = _notices.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == noticeId }
        if (index != -1) {
            val updated = currentList[index].copy(budget = newBudget.coerceAtLeast(0.0))
            currentList[index] = updated
            saveNotices(context, currentList)
        }
    }
}

