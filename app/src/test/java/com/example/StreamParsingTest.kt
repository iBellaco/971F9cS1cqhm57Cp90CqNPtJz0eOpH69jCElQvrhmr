package com.example

import org.junit.Test
import java.io.File
import kotlinx.serialization.json.Json
import com.example.model.Champion
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.ExperimentalSerializationApi

class StreamParsingTest {
    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun testParse() {
        val stream = File("src/main/assets/champions.json").inputStream()
        val format = Json { ignoreUnknownKeys = true }
        try {
            val champions = format.decodeFromStream<List<Champion>>(stream)
            println("Parsed ${champions.size} champions!")
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
