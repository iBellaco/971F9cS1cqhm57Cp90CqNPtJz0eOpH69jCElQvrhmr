#!/bin/bash

# Extract lines from start to line 375
sed -n '1,375p' app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt > temp.kt

# Append new LocalGalleryVideoPlayer
cat << 'INNER_EOF' >> temp.kt
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
    var mediaPlayerRef by remember { mutableStateOf<android.media.MediaPlayer?>(null) }
    val themePrimary = MaterialTheme.colorScheme.primary

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
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { ctx ->
                val frameLayout = FrameLayout(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setBackgroundColor(android.graphics.Color.BLACK)
                }

                val textureView = android.view.TextureView(ctx).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    ).apply {
                        gravity = android.view.Gravity.CENTER
                    }
                }

                textureView.surfaceTextureListener = object : android.view.TextureView.SurfaceTextureListener {
                    override fun onSurfaceTextureAvailable(surface: android.graphics.SurfaceTexture, width: Int, height: Int) {
                        try {
                            if (mediaPlayerRef == null) {
                                mediaPlayerRef = android.media.MediaPlayer()
                            }
                            val mp = mediaPlayerRef!!
                            mp.setSurface(android.graphics.Surface(surface))
                            
                            if (!mp.isPlaying) {
                                mp.reset()
                                if (videoUriString.startsWith("file://")) {
                                    val filePath = android.net.Uri.parse(videoUriString).path
                                    if (filePath != null && java.io.File(filePath).exists()) {
                                        mp.setDataSource(filePath)
                                    } else {
                                        mp.setDataSource(ctx, android.net.Uri.parse(videoUriString))
                                    }
                                } else if (videoUriString.startsWith("/")) {
                                    mp.setDataSource(videoUriString)
                                } else {
                                    mp.setDataSource(ctx, android.net.Uri.parse(videoUriString))
                                }
                                
                                mp.setOnPreparedListener { preparedMp ->
                                    preparedMp.isLooping = true
                                    val vol = if (isMuted) 0f else 1f
                                    preparedMp.setVolume(vol, vol)
                                    
                                    val videoWidth = preparedMp.videoWidth.toFloat()
                                    val videoHeight = preparedMp.videoHeight.toFloat()
                                    val viewWidth = textureView.width.toFloat()
                                    val viewHeight = textureView.height.toFloat()
                                    
                                    if (videoWidth > 0 && videoHeight > 0 && viewWidth > 0 && viewHeight > 0) {
                                        val scaleX = viewWidth / videoWidth
                                        val scaleY = viewHeight / videoHeight
                                        val scale = minOf(scaleX, scaleY)
                                        val scaledWidth = scale * videoWidth
                                        val scaledHeight = scale * videoHeight
                                        
                                        val matrix = android.graphics.Matrix()
                                        matrix.setScale(scaledWidth / viewWidth, scaledHeight / viewHeight, viewWidth / 2f, viewHeight / 2f)
                                        textureView.setTransform(matrix)
                                    }
                                    
                                    if (isPlaying) {
                                        preparedMp.start()
                                    } else {
                                        // Draw the first frame if paused
                                        preparedMp.start()
                                        preparedMp.pause()
                                        preparedMp.seekTo(1)
                                    }
                                }
                                mp.prepareAsync()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onSurfaceTextureSizeChanged(surface: android.graphics.SurfaceTexture, width: Int, height: Int) {}

                    override fun onSurfaceTextureDestroyed(surface: android.graphics.SurfaceTexture): Boolean {
                        try {
                            mediaPlayerRef?.setSurface(null)
                        } catch (_: Exception) {}
                        return true
                    }

                    override fun onSurfaceTextureUpdated(surface: android.graphics.SurfaceTexture) {}
                }

                frameLayout.addView(textureView)
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
INNER_EOF

# Append the rest of the file
sed -n '599,$p' app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt >> temp.kt
mv temp.kt app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt
