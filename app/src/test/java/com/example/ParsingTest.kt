package com.example

import com.example.model.Champion
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.File
import org.junit.Assert.assertTrue

class ParsingTest {
    @Test
    fun testParse() {
        val jsonString = File("src/main/assets/champions.json").readText()
        val format = Json { ignoreUnknownKeys = true }
        try {
            val champions = format.decodeFromString<List<Champion>>(jsonString)
            println("SUCCESS_PARSED: \${champions.size}")
            assertTrue(champions.isNotEmpty())
        } catch(e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
