import kotlinx.serialization.json.Json
import java.io.File
fun main() {
    val text = File("app/src/main/res/raw/champions_part1.json").readText()
    try {
        val format = Json { ignoreUnknownKeys = true }
        format.decodeFromString<List<Any>>(text)
        println("Success")
    } catch (e: Exception) {
        println("Error: " + e.message)
    }
}
