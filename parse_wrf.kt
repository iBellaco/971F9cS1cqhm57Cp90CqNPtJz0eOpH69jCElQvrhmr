import org.jsoup.Jsoup
import java.io.File

fun main() {
    val doc = Jsoup.parse(File("wrf.html"), "UTF-8")
    val tierBlocks = doc.select(".wf-tier-list__tiers__block")
    val champTiers = mutableMapOf<String, String>()
    
    for (block in tierBlocks) {
        val tierDiv = block.selectFirst(".tier") ?: continue
        val tierClass = tierDiv.className().replace("tier", "").trim()
        val mappedTier = when (tierClass) {
            "splus" -> "S+"
            "s" -> "S"
            "a" -> "A"
            "b" -> "B"
            "c" -> "C"
            else -> continue
        }
        
        val champLinks = block.select("a.ico-holder")
        for (link in champLinks) {
            val nameSpan = link.selectFirst("span")
            if (nameSpan != null && !nameSpan.hasClass("tier-delta")) {
                val name = nameSpan.text().uppercase().replace("'", "").replace(" ", "").replace(".", "")
                // println("$name -> $mappedTier")
                champTiers[name] = mappedTier
            } else {
                 // Sometime the name is just text inside the <a>?
                 val spans = link.select("span")
                 for (s in spans) {
                     if (!s.hasClass("tier-delta")) {
                          val name = s.text().uppercase().replace("'", "").replace(" ", "").replace(".", "")
                          champTiers[name] = mappedTier
                     }
                 }
            }
        }
    }
    
    println(champTiers.entries.take(20).joinToString("\n"))
}
