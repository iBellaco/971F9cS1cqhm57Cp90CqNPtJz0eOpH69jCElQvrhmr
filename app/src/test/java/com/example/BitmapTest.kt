package com.example

import android.graphics.BitmapFactory
import org.junit.Test
import org.junit.Assert.*
import java.io.File
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class BitmapTest {
    @Test
    fun testBitmapDecode() {
        val files = listOf(
            "b6ab383b3a6e6dd5260aeceec8f32116.webp", // Fantasmal
            "d4d918cf045f4a9b1064cf18419c6125.webp", // Ataque Potenciado
            "a9e382435db8c5b4d3dada562fa99d42.webp", // Angel custodio
            "f358ee8ae30792b90102f147ca8ff505.webp"  // Curar (works)
        )
        
        files.forEach {
            val bitmap = BitmapFactory.decodeFile("src/main/assets/offline_images/$it")
            assertNotNull("Failed to decode $it", bitmap)
        }
    }
}
