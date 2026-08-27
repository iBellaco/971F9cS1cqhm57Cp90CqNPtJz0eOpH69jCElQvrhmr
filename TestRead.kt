import java.io.File

fun main() {
    val text = File("app/src/main/assets/champions.json").bufferedReader().use { it.readText() }
    println(text.substring(520600, 520700))
}
