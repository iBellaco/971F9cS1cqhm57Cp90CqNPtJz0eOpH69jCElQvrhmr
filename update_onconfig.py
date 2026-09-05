import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = """    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            floatingComposeView?.dispatchConfigurationChanged(newConfig)
            closeTargetComposeView?.dispatchConfigurationChanged(newConfig)
        } catch (_: Throwable) {}

        try {
            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                isDeviceLandscape.value = lastScreenWidth > lastScreenHeight
                screenCaptureManager?.refreshProjection()
            }
            
            // Reajustar coordenadas de la vista flotante para la nueva orientación de pantalla
            val params = floatingParams
            val view = floatingComposeView
            if (params != null && view != null && view.isAttachedToWindow) {
                val metrics = resources.displayMetrics
                val density = metrics.density
                val marginPx = (8 * density).toInt()
                val currentBubblePx = ((if (isCompactBubbleMode) 36f else 46f) * density).toInt()
                val isLandscape = metrics.widthPixels > metrics.heightPixels
                val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
                val cardHeightPx = ((if (isLandscape) 390 else 520) * density).toInt()
                val viewWidth = if (isOverlayExpanded) cardWidthPx else currentBubblePx
                val viewHeight = if (isOverlayExpanded) cardHeightPx else currentBubblePx
                val maxX = (metrics.widthPixels - viewWidth - marginPx).coerceAtLeast(marginPx)
                val maxY = (metrics.heightPixels - viewHeight - marginPx).coerceAtLeast(marginPx)
                
                params.x = params.x.coerceIn(marginPx, maxX)
                params.y = params.y.coerceIn(marginPx, maxY)
                try {
                    windowManager?.updateViewLayout(view, params)
                } catch (_: Exception) {}
                
                try {
                    val closeTargetParams = closeTargetComposeView?.layoutParams as? WindowManager.LayoutParams
                    if (closeTargetParams != null) {
                        closeTargetParams.y = (24 * density).toInt()
                        windowManager?.updateViewLayout(closeTargetComposeView, closeTargetParams)
                    }
                } catch (_: Exception) {}
            }
        } catch (e: Throwable) {
            AppLogger.w("FloatingService", "Error adaptando layout tras cambio de configuración: ${e.message}")
        }
    }"""

replacement = """    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        try {
            val metrics = resources.displayMetrics
            if (lastScreenWidth != metrics.widthPixels || lastScreenHeight != metrics.heightPixels) {
                lastScreenWidth = metrics.widthPixels
                lastScreenHeight = metrics.heightPixels
                isDeviceLandscape.value = lastScreenWidth > lastScreenHeight
                screenCaptureManager?.refreshProjection()
                
                // Recrear la vista flotante dinámicamente para adoptar el nuevo WindowManager config
                // sin perder estado (ya que OverlayState está a nivel de Service).
                val currentX = floatingParams?.x
                val currentY = floatingParams?.y
                createFloatingOverlay()
                
                // Restaurar la posición aproximada si existía
                if (currentX != null && currentY != null && floatingParams != null && floatingComposeView != null) {
                    val density = metrics.density
                    val marginPx = (8 * density).toInt()
                    val currentBubblePx = ((if (isCompactBubbleMode) 36f else 46f) * density).toInt()
                    val isLandscape = lastScreenWidth > lastScreenHeight
                    val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
                    val cardHeightPx = ((if (isLandscape) 390 else 520) * density).toInt()
                    val viewWidth = if (isOverlayExpanded) cardWidthPx else currentBubblePx
                    val viewHeight = if (isOverlayExpanded) cardHeightPx else currentBubblePx
                    val maxX = (lastScreenWidth - viewWidth - marginPx).coerceAtLeast(marginPx)
                    val maxY = (lastScreenHeight - viewHeight - marginPx).coerceAtLeast(marginPx)
                    
                    floatingParams!!.x = currentX.coerceIn(marginPx, maxX)
                    floatingParams!!.y = currentY.coerceIn(marginPx, maxY)
                    windowManager?.updateViewLayout(floatingComposeView, floatingParams)
                }
            } else {
                floatingComposeView?.dispatchConfigurationChanged(newConfig)
                closeTargetComposeView?.dispatchConfigurationChanged(newConfig)
            }
        } catch (e: Throwable) {
            AppLogger.w("FloatingService", "Error adaptando layout tras cambio de configuración: ${e.message}")
        }
    }"""

text = text.replace(target, replacement)
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

