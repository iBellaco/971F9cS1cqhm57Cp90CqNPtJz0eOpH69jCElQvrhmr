cat << 'PATCH' > avatar.patch
--- app/src/main/java/com/example/ui/components/UserAvatarView.kt
+++ app/src/main/java/com/example/ui/components/UserAvatarView.kt
@@ -19,6 +19,8 @@
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.Dp
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
+import androidx.compose.ui.graphics.graphicsLayer
+import kotlin.math.sin
 import coil.compose.AsyncImage
 import coil.request.CachePolicy
 import coil.request.ImageRequest
@@ -29,7 +31,8 @@
     modifier: Modifier = Modifier,
     size: Dp = 56.dp,
     fallbackInitial: String = "U",
     showBorder: Boolean = true,
-    customBorderColor: Color? = null
+    customBorderColor: Color? = null,
+    rankBorder: String = "NONE"
 ) {
     val avatar: AvatarItem = AvatarCatalog.getAvatarById(avatarId ?: "default_poro")
     val parsedBorderColor = customBorderColor ?: try {
@@ -53,8 +56,22 @@
         else -> Brush.linearGradient(listOf(parsedBorderColor, parsedBorderColor))
     }
     val actualShowBorder = showBorder && !isCommon
 
+    // Animation for Challenger border
+    val infiniteTransition = rememberInfiniteTransition(label = "ChallengerGlow")
+    val glowPulse by infiniteTransition.animateFloat(
+        initialValue = 0.5f,
+        targetValue = 1.0f,
+        animationSpec = infiniteRepeatable(
+            animation = tween(1500, easing = FastOutSlowInEasing),
+            repeatMode = RepeatMode.Reverse
+        ),
+        label = "GlowPulse"
+    )
+    val rotation by infiniteTransition.animateFloat(
+        initialValue = 0f,
+        targetValue = 360f,
+        animationSpec = infiniteRepeatable(
+            animation = tween(4000, easing = LinearEasing),
+            repeatMode = RepeatMode.Restart
+        ),
+        label = "Rotation"
+    )
+
     Box(
         modifier = modifier
             .size(size)
@@ -69,12 +86,18 @@
             )
             .then(
-                if (actualShowBorder) {
-                    if (rarityLower.contains("mítico") || rarityLower.contains("mitico") || rarityLower.contains("legendario")) {
+                if (rankBorder != "NONE") {
+                    Modifier.rankedBorderPainter(
+                        rank = rankBorder,
+                        glowPulse = glowPulse,
+                        rotation = rotation
+                    )
+                } else if (actualShowBorder) {
+                    if (rarityLower.contains("mítico") || rarityLower.contains("mitico") || rarityLower.contains("legendario")) {
                         Modifier.premiumBorderPainter(
                             rarity = rarityLower,
PATCH
patch app/src/main/java/com/example/ui/components/UserAvatarView.kt < avatar.patch
