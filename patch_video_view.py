import re

with open('app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt', 'r') as f:
    content = f.read()

old_android_view = """        AndroidView(
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

                fun updateMatrix(viewWidth: Int, viewHeight: Int) {
                    try {
                        val mp = mediaPlayerRef ?: return
                        val videoWidth = mp.videoWidth.toFloat()
                        val videoHeight = mp.videoHeight.toFloat()
                        if (videoWidth > 0 && videoHeight > 0 && viewWidth > 0 && viewHeight > 0) {
                            val scaleX = viewWidth / videoWidth
                            val scaleY = viewHeight / videoHeight
                            val scale = minOf(scaleX, scaleY)
                            val scaledWidth = scale * videoWidth
                            val scaledHeight = scale * videoHeight
                            val matrix = android.graphics.Matrix()
                            matrix.setScale(scaledWidth / viewWidth.toFloat(), scaledHeight / viewHeight.toFloat(), viewWidth / 2f, viewHeight / 2f)
                            textureView.setTransform(matrix)
                        }
                    } catch (_: Exception) {}
                }

                textureView.surfaceTextureListener = object : android.view.TextureView.SurfaceTextureListener {
                    override fun onSurfaceTextureAvailable(surface: android.graphics.SurfaceTexture, width: Int, height: Int) {
                        try {
                            if (mediaPlayerRef == null) {
                                mediaPlayerRef = android.media.MediaPlayer()
                            }
                            val mp = mediaPlayerRef!!
                            mp.setSurface(android.view.Surface(surface))
                            
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
                                    
                                    updateMatrix(textureView.width, textureView.height)
                                    
                                    if (isPlaying) {
                                        preparedMp.start()
                                    } else {
                                        // Draw the first frame if paused
                                        preparedMp.start()
                                        preparedMp.pause()
                                        preparedMp.seekTo(1)
                                    }
                                }
                                mp.setOnVideoSizeChangedListener { _, _, _ ->
                                    updateMatrix(textureView.width, textureView.height)
                                }
                                mp.prepareAsync()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onSurfaceTextureSizeChanged(surface: android.graphics.SurfaceTexture, width: Int, height: Int) {
                        updateMatrix(width, height)
                    }

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
        )"""

new_android_view = """        AndroidView(
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
                
                if (videoUriString.startsWith("file://")) {
                    val filePath = android.net.Uri.parse(videoUriString).path
                    if (filePath != null && java.io.File(filePath).exists()) {
                        videoView.setVideoPath(filePath)
                    } else {
                        videoView.setVideoURI(android.net.Uri.parse(videoUriString))
                    }
                } else if (videoUriString.startsWith("/")) {
                    videoView.setVideoPath(videoUriString)
                } else {
                    videoView.setVideoURI(android.net.Uri.parse(videoUriString))
                }
                
                videoView.setOnPreparedListener { mp ->
                    mediaPlayerRef = mp
                    mp.isLooping = true
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
                
                videoView.setOnErrorListener { _, _, _ -> true }

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
        )"""

content = content.replace(old_android_view, new_android_view)

with open('app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt', 'w') as f:
    f.write(content)
