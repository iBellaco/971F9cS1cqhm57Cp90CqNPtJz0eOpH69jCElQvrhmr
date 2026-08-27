import kotlinx.serialization.json.Json
import com.example.model.Champion
import java.io.File

fun main() {
    val jsonString = File("app/src/main/assets/champions.json").readText()
    try {
        val format = Json { ignoreUnknownKeys = true }
        // We can't easily compile this without the gradle setup.
    } catch (e: Exception) {
        println(e)
    }
}
