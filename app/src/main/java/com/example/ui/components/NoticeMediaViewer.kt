package com.example.ui.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.scale
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import java.io.File
import coil.compose.AsyncImage
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object NoticeMediaUtils {
    val pauseAndMuteTrigger = kotlinx.coroutines.flow.MutableStateFlow(0)

    fun pauseAndMuteAll() {
        pauseAndMuteTrigger.value++
    }

    fun extractYouTubeVideoId(url: String): String? {
        if (url.isBlank()) return null
        val trimmed = url.trim()

        val patterns = listOf(
            Regex("(?:https?://)?(?:www\\.|m\\.)?youtube\\.com/watch\\?.*v=([a-zA-Z0-9_-]{11})"),
            Regex("(?:https?://)?(?:www\\.|m\\.)?youtu\\.be/([a-zA-Z0-9_-]{11})"),
            Regex("(?:https?://)?(?:www\\.|m\\.)?youtube\\.com/embed/([a-zA-Z0-9_-]{11})"),
            Regex("(?:https?://)?(?:www\\.|m\\.)?youtube\\.com/shorts/([a-zA-Z0-9_-]{11})"),
            Regex("(?:https?://)?(?:www\\.|m\\.)?youtube\\.com/live/([a-zA-Z0-9_-]{11})")
        )

        for (pattern in patterns) {
            val match = pattern.find(trimmed)
            if (match != null && match.groupValues.size > 1) {
                return match.groupValues[1]
            }
        }

        if (trimmed.length == 11 && trimmed.matches(Regex("^[a-zA-Z0-9_-]{11}$"))) {
            return trimmed
        }
        return null
    }

    fun isYouTubeUrl(url: String): Boolean {
        val trimmed = url.trim()
        return trimmed.contains("youtube.com", ignoreCase = true) ||
               trimmed.contains("youtu.be", ignoreCase = true) ||
               extractYouTubeVideoId(trimmed) != null
    }

    fun isVideo(context: Context, url: String): Boolean {
        if (url.isBlank()) return false
        val trimmed = url.trim().lowercase()
        if (trimmed.startsWith("data:image/")) return false
        if (trimmed.startsWith("data:video/")) return true
        if (isYouTubeUrl(url)) return true

        val cleanUrl = trimmed.substringBefore("?").substringBefore("#")
        val videoExtensions = listOf(".mp4", ".mkv", ".webm", ".mov", ".3gp", ".avi", ".m4v", ".ts")
        if (videoExtensions.any { cleanUrl.endsWith(it) || trimmed.contains(it) }) {
            return true
        }

        if (trimmed.contains("notice_videos") || trimmed.contains("/video") || trimmed.contains("video%2f") || trimmed.contains("video/")) {
            return true
        }

        if (trimmed.startsWith("file://") || trimmed.startsWith("/")) {
            val path = if (trimmed.startsWith("file://")) Uri.parse(url).path ?: "" else trimmed
            val ext = File(path).extension.lowercase()
            if (ext in listOf("mp4", "mkv", "webm", "mov", "3gp", "avi", "m4v")) return true
        }

        if (trimmed.startsWith("content://")) {
            try {
                val mimeType = context.contentResolver.getType(Uri.parse(url))
                if (mimeType?.startsWith("video/") == true) {
                    return true
                }
            } catch (_: Exception) {}
        }
        return false
    }

    fun isLocalVideo(context: Context, url: String): Boolean {
        return isVideo(context, url) && !isYouTubeUrl(url)
    }

    fun isValidNoticeMedia(url: String): Boolean {
        if (url.isBlank()) return true
        val trimmed = url.trim()
        if (trimmed.startsWith("content://") || trimmed.startsWith("file://") || trimmed.startsWith("data:image/") || trimmed.startsWith("data:video/")) return true
        if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            return true
        }
        return false
    }

    fun formatDurationMs(ms: Int): String {
        if (ms <= 0) return "00:00"
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}

@Composable
fun NoticeMediaViewer(
    mediaUrl: String,
    modifier: Modifier = Modifier,
    isFullscreen: Boolean = false,
    onExpand: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null,
    onImageClick: (() -> Unit)? = null
) {
    if (mediaUrl.isBlank()) return
    val context = LocalContext.current
    val trimmedUrl = mediaUrl.trim()

    val isYt = remember(trimmedUrl) { NoticeMediaUtils.isYouTubeUrl(trimmedUrl) }
    val ytVideoId = remember(trimmedUrl) { NoticeMediaUtils.extractYouTubeVideoId(trimmedUrl) }
    val isVideo = remember(trimmedUrl) { NoticeMediaUtils.isVideo(context, trimmedUrl) }

    val containerModifier = if (isFullscreen) {
        modifier.fillMaxSize()
    } else {
        modifier
            .fillMaxWidth()
            .aspectRatio(985f / 425f)
    }

    Box(
        modifier = containerModifier
            .clip(RoundedCornerShape(if (isFullscreen) 12.dp else 8.dp))
            .background(Color.Black)
            .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(if (isFullscreen) 12.dp else 8.dp))
    ) {
        if (isYt && ytVideoId != null) {
            val lifecycleOwner = LocalLifecycleOwner.current
            var webViewInstance by remember { mutableStateOf<WebView?>(null) }

            DisposableEffect(lifecycleOwner, ytVideoId) {
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            try {
                                webViewInstance?.onResume()
                                webViewInstance?.resumeTimers()
                            } catch (_: Exception) {}
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            try {
                                webViewInstance?.onPause()
                                webViewInstance?.pauseTimers()
                            } catch (_: Exception) {}
                        }
                        Lifecycle.Event.ON_DESTROY -> {
                            try {
                                webViewInstance?.destroy()
                            } catch (_: Exception) {}
                        }
                        else -> {}
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            // YouTube Player with Error 153 fix, modern WebView settings, and custom Chrome user agent
            var isYtMuted by remember { mutableStateOf(true) }
            val currentPrimaryColor = MaterialTheme.colorScheme.primary

            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                databaseEnabled = true
                                mediaPlaybackRequiresUserGesture = false
                                loadWithOverviewMode = true
                                useWideViewPort = true
                                allowFileAccess = true
                                allowContentAccess = true
                                setSupportZoom(false)
                                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                                // Fixing YouTube Error 153: Emulate standard mobile Chrome browser
                                userAgentString = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.6668.70 Mobile Safari/537.36"
                            }
                            webChromeClient = WebChromeClient()
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                                    return false
                                }
                            }
                            val html = """
                                <!DOCTYPE html>
                                <html>
                                <head>
                                    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                                    <style>
                                        * { box-sizing: border-box; margin: 0; padding: 0; }
                                        body, html { width: 100%; height: 100%; background-color: #000000; overflow: hidden; }
                                        .video-wrapper { position: relative; width: 100%; height: 100%; }
                                        iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: 0; }
                                    </style>
                                    <script>
                                        function unmuteVideo() {
                                            var iframe = document.getElementById('ytplayer');
                                            if (iframe && iframe.contentWindow) {
                                                iframe.contentWindow.postMessage('{"event":"command","func":"unMute","args":""}', '*');
                                            }
                                        }
                                        function muteVideo() {
                                            var iframe = document.getElementById('ytplayer');
                                            if (iframe && iframe.contentWindow) {
                                                iframe.contentWindow.postMessage('{"event":"command","func":"mute","args":""}', '*');
                                            }
                                        }
                                    </script>
                                </head>
                                <body>
                                    <div class="video-wrapper">
                                        <iframe
                                            id="ytplayer"
                                            type="text/html"
                                            src="https://www.youtube-nocookie.com/embed/$ytVideoId?autoplay=1&mute=1&controls=1&playsinline=1&enablejsapi=1&rel=0&iv_load_policy=3&modestbranding=1&origin=https://www.youtube-nocookie.com&widget_referrer=https://www.youtube-nocookie.com"
                                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                            allowfullscreen>
                                        </iframe>
                                    </div>
                                </body>
                                </html>
                            """.trimIndent()
                            loadDataWithBaseURL("https://www.youtube-nocookie.com", html, "text/html", "UTF-8", "https://www.youtube-nocookie.com")
                            webViewInstance = this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // YouTube controls overlay (Unmute button + Fullscreen Expand)
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Botón de Quitar Silencio / Silenciar
                    IconButton(
                        onClick = {
                            isYtMuted = !isYtMuted
                            val jsFunc = if (isYtMuted) "muteVideo()" else "unmuteVideo()"
                            webViewInstance?.evaluateJavascript(jsFunc, null)
                        },
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                    ) {
                        Icon(
                            imageVector = if (isYtMuted) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                            contentDescription = if (isYtMuted) "Quitar silencio" else "Silenciar",
                            tint = if (isYtMuted) currentPrimaryColor else HextechGold,
                            modifier = Modifier.size(15.dp)
                        )
                    }

                    if (!isFullscreen && onExpand != null) {
                        IconButton(
                            onClick = onExpand,
                            modifier = Modifier
                                .size(28.dp)
                                .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                        ) {
                            Icon(
                                Icons.Default.Fullscreen,
                                contentDescription = "Pantalla Completa",
                                tint = currentPrimaryColor,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        } else if (isVideo) {
            // Reproductor universal de video (Local, Caché en disco, Base64 o Nube)
            LocalGalleryVideoPlayer(
                videoUriString = trimmedUrl,
                isFullscreen = isFullscreen,
                onExpand = onExpand
            )
        } else {
            // Image rendering (from Gallery, Base64 Data URL, or Image URL)
            val imageModel = remember(trimmedUrl) {
                if (trimmedUrl.startsWith("data:image/")) {
                    com.example.util.NoticeMediaStorageManager.decodeDataUriToBytes(trimmedUrl) ?: trimmedUrl
                } else if (trimmedUrl.startsWith("file://")) {
                    val path = Uri.parse(trimmedUrl).path ?: ""
                    java.io.File(path)
                } else {
                    trimmedUrl
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (onImageClick != null) {
                            Modifier.clickable { onImageClick() }
                        } else if (!isFullscreen && onExpand != null) {
                            Modifier.clickable { onExpand() }
                        } else Modifier
                    )
            ) {
                AsyncImage(
                    model = imageModel,
                    contentDescription = "Multimedia de Anuncio",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = if (isFullscreen) ContentScale.Fit else ContentScale.Crop
                )

                if (!isFullscreen && onExpand != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(6.dp)
                            .background(HextechSurface.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Default.Fullscreen, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(12.dp))
                            Text("Ampliar", color = HextechCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocalGalleryVideoPlayer(
    videoUriString: String,
    isFullscreen: Boolean,
    onExpand: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isMuted by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(true) }
    var isBuffering by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var retryKey by remember { mutableStateOf(0) }
    var mediaPlayerRef by remember { mutableStateOf<android.media.MediaPlayer?>(null) }
    val themePrimary = MaterialTheme.colorScheme.primary

    // Resolver ruta efectiva (Caché local, Base64 decodificado, archivo local o streaming cloud)
    val effectiveUriString by produceState<String?>(initialValue = null, key1 = videoUriString, key2 = retryKey) {
        val trimmed = videoUriString.trim()
        if (trimmed.startsWith("data:video/")) {
            val f = com.example.util.NoticeMediaStorageManager.saveBase64VideoToCache(context, trimmed)
            value = f?.absolutePath ?: trimmed
        } else if (trimmed.startsWith("http://", ignoreCase = true) || trimmed.startsWith("https://", ignoreCase = true)) {
            val cached = com.example.util.NoticeMediaStorageManager.getCachedVideoFile(context, trimmed)
            if (cached != null) {
                value = cached.absolutePath
            } else {
                value = trimmed
                // Descargar en segundo plano para próximas reproducciones
                kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                    com.example.util.NoticeMediaStorageManager.cacheVideoFromUrl(context, trimmed)
                }
            }
        } else if (trimmed.startsWith("file://") || trimmed.startsWith("/")) {
            val path = if (trimmed.startsWith("file://")) Uri.parse(trimmed).path ?: "" else trimmed
            val f = File(path)
            if (f.exists()) {
                value = f.absolutePath
            } else {
                hasError = true
                isBuffering = false
                errorMessage = "Archivo guardado localmente en otro teléfono. Súbelo a la nube desde el panel de avisos."
                value = null
            }
        } else {
            value = trimmed
        }
    }

    val pauseTrigger by NoticeMediaUtils.pauseAndMuteTrigger.collectAsState()
    LaunchedEffect(pauseTrigger) {
        if (pauseTrigger > 0) {
            isPlaying = false
            isMuted = true
            try {
                mediaPlayerRef?.let { mp ->
                    mp.setVolume(0f, 0f)
                    if (mp.isPlaying) mp.pause()
                }
            } catch (_: Exception) {}
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    try {
                        if (mediaPlayerRef?.isPlaying == true) {
                            mediaPlayerRef?.pause()
                        }
                    } catch (_: Exception) {}
                }
                Lifecycle.Event.ON_RESUME -> {
                    try {
                        if (isPlaying && mediaPlayerRef?.isPlaying == false) {
                            mediaPlayerRef?.start()
                        }
                    } catch (_: Exception) {}
                }
                Lifecycle.Event.ON_DESTROY -> {
                    try {
                        mediaPlayerRef?.release()
                        mediaPlayerRef = null
                    } catch (_: Exception) {}
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            try {
                mediaPlayerRef?.release()
                mediaPlayerRef = null
            } catch (_: Exception) {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (effectiveUriString != null && !hasError) {
            AndroidView(
                factory = { ctx ->
                    val videoView = VideoView(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }

                    val frameLayout = FrameLayout(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(android.graphics.Color.BLACK)
                        val params = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        ).apply {
                            gravity = android.view.Gravity.CENTER
                        }
                        addView(videoView, params)
                    }

                    val target = effectiveUriString!!
                    if (target.startsWith("/")) {
                        videoView.setVideoPath(target)
                    } else {
                        videoView.setVideoURI(Uri.parse(target))
                    }

                    videoView.setOnPreparedListener { mp ->
                        mediaPlayerRef = mp
                        mp.isLooping = true
                        isBuffering = false
                        hasError = false
                        val vol = if (isMuted) 0f else 1f
                        mp.setVolume(vol, vol)

                        if (isPlaying) {
                            videoView.start()
                        } else {
                            videoView.start()
                            videoView.pause()
                            videoView.seekTo(1)
                        }
                    }

                    videoView.setOnErrorListener { _, _, _ ->
                        isBuffering = false
                        hasError = true
                        errorMessage = "Error al reproducir video. Verifica conexión o enlace."
                        true
                    }

                    frameLayout
                },
                update = {
                    try {
                        val vol = if (isMuted) 0f else 1f
                        mediaPlayerRef?.setVolume(vol, vol)

                        if (isPlaying) {
                            if (mediaPlayerRef?.isPlaying == false) mediaPlayerRef?.start()
                        } else {
                            if (mediaPlayerRef?.isPlaying == true) mediaPlayerRef?.pause()
                        }
                    } catch (_: Exception) {}
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Indicador de carga Hextech elegante (evita pantalla negra estática)
        if (isBuffering && !hasError) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.75f))
            ) {
                CircularProgressIndicator(
                    color = HextechCyan,
                    strokeWidth = 2.5.dp,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Cargando video...",
                    color = HextechCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Mensaje de error visual con botón reintentar
        if (hasError) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f))
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.VideocamOff,
                    contentDescription = null,
                    tint = Color(0xFFFF6B6B),
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorMessage ?: "No se pudo reproducir el video",
                    color = Color(0xFFE0E0E0),
                    fontSize = 11.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        hasError = false
                        isBuffering = true
                        retryKey++
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                    border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                ) {
                    Text("Reintentar", color = HextechCyan, fontSize = 10.5.sp)
                }
            }
        }

        // Overlay Controls: Unmute / Mute + Fullscreen Expand ONLY
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Botón de Play / Pausa
            IconButton(
                onClick = {
                    isPlaying = !isPlaying
                },
                modifier = Modifier
                    .size(28.dp)
                    .background(Color.Black.copy(alpha = 0.65f), CircleShape)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                    tint = themePrimary,
                    modifier = Modifier.size(15.dp)
                )
            }

            // Botón de Quitar Silencio / Silenciar
            IconButton(
                onClick = {
                    isMuted = !isMuted
                    mediaPlayerRef?.let { mp ->
                        try {
                            val vol = if (isMuted) 0f else 1f
                            mp.setVolume(vol, vol)
                        } catch (_: Exception) {}
                    }
                },
                modifier = Modifier
                    .size(28.dp)
                    .background(Color.Black.copy(alpha = 0.65f), CircleShape)
            ) {
                Icon(
                    imageVector = if (isMuted) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                    contentDescription = if (isMuted) "Quitar silencio" else "Silenciar",
                    tint = if (isMuted) themePrimary else HextechGold,
                    modifier = Modifier.size(15.dp)
                )
            }

            if (!isFullscreen && onExpand != null) {
                IconButton(
                    onClick = onExpand,
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color.Black.copy(alpha = 0.65f), CircleShape)
                ) {
                    Icon(
                        Icons.Default.Fullscreen,
                        contentDescription = "Pantalla Completa",
                        tint = themePrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NoticeMediaFullscreenDialog(
    mediaUrl: String,
    externalUrl: String = "",
    noticeId: String = "",
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val trimmedUrl = mediaUrl.trim()
    val isVideo = remember(trimmedUrl) { NoticeMediaUtils.isVideo(context, trimmedUrl) }

    // MANTENER ORIENTACIÓN NATURAL POR DEFECTO. NO forzar rotación horizontal automática.
    var isLandscape by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Dialog(
        onDismissRequest = {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        val dialogWindow = (androidx.compose.ui.platform.LocalView.current.parent as? androidx.compose.ui.window.DialogWindowProvider)?.window
        SideEffect {
            dialogWindow?.let { win ->
                win.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                win.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(android.graphics.Color.BLACK))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .systemBarsPadding()
        ) {
            val openLinkAction: () -> Unit = {
                if (externalUrl.isNotBlank()) {
                    try {
                        if (noticeId.isNotBlank()) {
                            com.example.data.AppNoticeAnalyticsManager.recordClick(context, noticeId)
                        }
                        val cleanUrl = if (!externalUrl.startsWith("http://") && !externalUrl.startsWith("https://")) {
                            "https://${externalUrl.trim()}"
                        } else {
                            externalUrl.trim()
                        }
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cleanUrl)).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                    } catch (_: Exception) {}
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Header superior con botón de cerrar claro
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            onDismiss()
                        },
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.White.copy(alpha = 0.15f), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .then(
                            if (!isVideo && externalUrl.isNotBlank()) {
                                Modifier.clickable { openLinkAction() }
                            } else Modifier
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    NoticeMediaViewer(
                        mediaUrl = mediaUrl,
                        modifier = Modifier.fillMaxSize(),
                        isFullscreen = true,
                        onImageClick = if (!isVideo && externalUrl.isNotBlank()) openLinkAction else null
                    )

                    if (!isVideo && externalUrl.isNotBlank()) {
                        val infiniteTransition = rememberInfiniteTransition()
                        val pulseScale by infiniteTransition.animateFloat(
                            initialValue = 1f,
                            targetValue = 1.05f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(1000, easing = LinearEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 12.dp, bottom = 12.dp)
                                .scale(pulseScale)
                                .background(HextechDarkBg.copy(alpha = 0.85f), RoundedCornerShape(20.dp))
                                .border(1.dp, HextechGold.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
                                .clickable { openLinkAction() }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.TouchApp, contentDescription = null, tint = HextechGold, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Toca la imagen para abrir enlace", color = HextechGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isVideo) {
                        Button(
                            onClick = {
                                val nextLandscape = !isLandscape
                                isLandscape = nextLandscape
                                activity?.requestedOrientation = if (nextLandscape) {
                                    ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                                } else {
                                    ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, HextechCyan.copy(alpha = 0.6f))
                        ) {
                            Icon(
                                if (isLandscape) Icons.Default.ScreenLockPortrait else Icons.Default.ScreenRotation,
                                contentDescription = "Rotar Pantalla",
                                tint = HextechCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isLandscape) "Modo Vertical" else "Rotar Pantalla",
                                color = HextechCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    Button(
                        onClick = {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.25f))
                    ) {
                        Text("Cerrar", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = TextPrimary)
                    }
                }
            }
        }
    }
}

