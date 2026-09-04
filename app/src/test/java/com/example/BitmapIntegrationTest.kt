package com.example

import android.content.Context
import android.graphics.BitmapFactory
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class BitmapIntegrationTest {
    @Test
    fun testAssetOpenAndDecode() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val assetPath = "offline_images/b4f2c9b975912c3a88ceb806e74c23f1.webp" // Destello
        
        context.assets.open(assetPath).use { inputStream ->
            val b = BitmapFactory.decodeStream(inputStream)
            assertNotNull("Failed to decode Destello via assets.open", b)
        }
    }
}
