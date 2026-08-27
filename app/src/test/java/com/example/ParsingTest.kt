package com.example

import org.junit.Test
import java.io.File
import kotlinx.serialization.json.Json
import com.example.model.Champion

class ParsingTest {
    @Test
    fun testParse() {
        val jsonString = File("src/main/assets/champions.json").readText()
        val format = Json { ignoreUnknownKeys = true }
        try {
            val champions = format.decodeFromString<List<Champion>>(jsonString)
            println("Parsed ${champions.size} champions!")
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
