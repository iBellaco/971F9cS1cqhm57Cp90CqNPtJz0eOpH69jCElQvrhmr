import com.example.service.screen.ChampionNameResolver
import com.example.model.Champion
import com.example.data.WildRiftRepository

fun main() {
    val allChamps = WildRiftRepository.getChampions()
    val lines = listOf("MISS FORTUNE", "Kimmy Babadei", "RENEKTON", "matador de de", "THRESH", "WuKOng Baba", "PANTHEON", "GamesAllcruz", "CALLE CENTRAL", "D I E G O", "Marca estelar eterna")
    for (line in lines) {
        val c = ChampionNameResolver.findChampionInText(line, allChamps)
        println("'$line' -> ${c?.name}")
    }
}
