package com.example.ui.components

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.ui.theme.*

object NoticeMediaUtils {
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

    fun isLocalVideo(context: Context, url: String): Boolean {
        if (url.isBlank()) return false
        val trimmed = url.trim().lowercase()

        if (trimmed.endsWith(".mp4") || trimmed.endsWith(".mkv") || trimmed.endsWith(".webm") ||
            trimmed.endsWith(".mov") || trimmed.endsWith(".3gp") || trimmed.endsWith(".avi")) {
            return true
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

    fun isValidNoticeMedia(url: String): Boolean {
        if (url.isBlank()) return true
        val trimmed = url.trim()
        if (trimmed.startsWith("content://") || trimmed.startsWith("file://")) return true
        if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            return isYouTubeUrl(trimmed)
        }
        return false
    }
}

@Composable
fun NoticeMediaViewer(
    mediaUrl: String,
    modifier: Modifier = Modifier,
    isFullscreen: Boolean = false,
    onExpand: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null
) {
    if (mediaUrl.isBlank()) return
    val context = LocalContext.current
    val trimmedUrl = mediaUrl.trim()

    val isYt = remember(trimmedUrl) { NoticeMediaUtils.isYouTubeUrl(trimmedUrl) }
    val ytVideoId = remember(trimmedUrl) { NoticeMediaUtils.extractYouTubeVideoId(trimmedUrl) }
    val isVideo = remember(trimmedUrl) { isYt || NoticeMediaUtils.isLocalVideo(context, trimmedUrl) }

    var isMuted by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(true) }
    var videoViewRef by remember { mutableStateOf<VideoView?>(null) }

    val containerModifier = if (isFullscreen) {
        modifier.fillMaxSize()
    } else {
        modifier
            .fillMaxWidth()
            .aspectRatio(985f / 425f)
            .then(
                if (onExpand != null) {
                    Modifier.clickable { onExpand() }
                } else Modifier
            )
    }

    Box(
        modifier = containerModifier
            .clip(RoundedCornerShape(if (isFullscreen) 12.dp else 8.dp))
            .background(Color.Black)
            .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(if (isFullscreen) 12.dp else 8.dp))
    ) {
        if (isYt && ytVideoId != null) {
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
                            mediaPlaybackRequiresUserGesture = false
                            loadWithOverviewMode = true
                            useWideViewPort = true
                        }
                        webViewClient = WebViewClient()
                        val html = """
                            <!DOCTYPE html>
                            <html>
                            <head>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <style>
                                    body, html { margin: 0; padding: 0; width: 100%; height: 100%; background-color: #000000; overflow: hidden; }
                                    iframe { width: 100%; height: 100%; border: none; }
                                </style>
                            </head>
                            <body>
                                <iframe src="https://www.youtube.com/embed/$ytVideoId?autoplay=1&mute=1&controls=1&playsinline=1&enablejsapi=1" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                            </body>
                            </html>
                        """.trimIndent()
                        loadDataWithBaseURL("https://www.youtube.com", html, "text/html", "utf-8", null)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        } else if (NoticeMediaUtils.isLocalVideo(context, trimmedUrl)) {
            // Local gallery video player with VideoView, visual rendering and default muted
            AndroidView(
                factory = { ctx ->
                    val frameLayout = FrameLayout(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(android.graphics.Color.BLACK)
                    }

                    val vv = VideoView(ctx).apply {
                        val lp = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        ).apply {
                            gravity = android.view.Gravity.CENTER
                        }
                        layoutParams = lp

                        try {
                            setVideoURI(Uri.parse(trimmedUrl))
                            setOnPreparedListener { mp ->
                                mp.isLooping = true
                                // Default muted requirement
                                mp.setVolume(if (isMuted) 0f else 1f, if (isMuted) 0f else 1f)
                                if (isPlaying) {
                                    start()
                                }
                            }
                            setOnErrorListener { _, _, _ -> true }
                        } catch (_: Exception) {}
                    }

                    frameLayout.addView(vv)
                    videoViewRef = vv
                    frameLayout
                },
                update = {
                    try {
                        if (isPlaying) {
                            if (!videoViewRef?.isPlaying!!) videoViewRef?.start()
                        } else {
                            if (videoViewRef?.isPlaying == true) videoViewRef?.pause()
                        }
                    } catch (_: Exception) {}
                },
                modifier = Modifier.fillMaxSize()
            )

            // Local Video Controls (Play/Pause & Mute/Unmute)
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                IconButton(
                    onClick = {
                        isPlaying = !isPlaying
                        if (isPlaying) videoViewRef?.start() else videoViewRef?.pause()
                    },
                    modifier = Modifier
                        .size(28.dp)
                        .background(HextechSurface.copy(alpha = 0.85f), CircleShape)
                ) {
                    Icon(
                        if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Pausar/Reproducir",
                        tint = HextechCyan,
                        modifier = Modifier.size(16.dp)
                    )
                }

                IconButton(
                    onClick = {
                        isMuted = !isMuted
                    },
                    modifier = Modifier
                        .size(28.dp)
                        .background(HextechSurface.copy(alpha = 0.85f), CircleShape)
                ) {
                    Icon(
                        if (isMuted) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                        contentDescription = "Silencio",
                        tint = if (isMuted) TextMuted else HextechGold,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        } else {
            // Image rendering (from Gallery or Image URL)
            AsyncImage(
                model = trimmedUrl,
                contentDescription = "Multimedia",
                modifier = Modifier.fillMaxSize(),
                contentScale = if (isFullscreen) ContentScale.Fit else ContentScale.Crop
            )
        }
    }
}

@Composable
fun NoticeMediaFullscreenDialog(
    mediaUrl: String,
    externalUrl: String = "",
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val trimmedUrl = mediaUrl.trim()
    val isYt = remember(trimmedUrl) { NoticeMediaUtils.isYouTubeUrl(trimmedUrl) }
    val isLocal = remember(trimmedUrl) { NoticeMediaUtils.isLocalVideo(context, trimmedUrl) }
    val isVideo = isYt || isLocal

    var isLandscape by remember { mutableStateOf(false) }

    DisposableEffect(isLandscape) {
        if (isVideo) {
            if (isLandscape) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Dialog(
        onDismissRequest = {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .then(
                            if (!isVideo && externalUrl.isNotBlank()) {
                                Modifier.clickable {
                                    try {
                                        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(externalUrl.trim()))
                                        context.startActivity(intent)
                                    } catch (_: Exception) {}
                                }
                            } else Modifier
                        )
                ) {
                    NoticeMediaViewer(
                        mediaUrl = mediaUrl,
                        modifier = Modifier.fillMaxSize(),
                        isFullscreen = true
                    )

                    // Optional external link badge if externalUrl is provided for an image
                    if (!isVideo && externalUrl.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(HextechSurface.copy(alpha = 0.9f))
                                .border(1.dp, HextechGold, RoundedCornerShape(8.dp))
                                .clickable {
                                    try {
                                        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(externalUrl.trim()))
                                        context.startActivity(intent)
                                    } catch (_: Exception) {}
                                }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Icon(Icons.Default.OpenInBrowser, contentDescription = null, tint = HextechGold, modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Abrir Enlace Web Externo ↗",
                                    color = HextechGold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isVideo) Arrangement.SpaceBetween else Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // ROTATE BUTTON ONLY IF IT IS A VIDEO
                    if (isVideo) {
                        Button(
                            onClick = { isLandscape = !isLandscape },
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
                    }

                    Button(
                        onClick = {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HextechGold, contentColor = HextechDarkBg),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cerrar", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
