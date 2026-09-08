import java.util.Locale

fun normalize(s: String): String = s.replace(Regex("[^a-zA-Z0-9]"), " ").lowercase(Locale.ROOT).trim()

fun levenshteinDistance(s1: String, s2: String): Int {
    if (s1.length > s2.length) return levenshteinDistance(s2, s1)
    var distances = IntArray(s1.length + 1) { it }
    for (i2 in s2.indices) {
        val newDistances = IntArray(s1.length + 1)
        newDistances[0] = i2 + 1
        for (i1 in s1.indices) {
            if (s1[i1] == s2[i2]) {
                newDistances[i1 + 1] = distances[i1]
            } else {
                newDistances[i1 + 1] = 1 + minOf(distances[i1], distances[i1 + 1], newDistances[i1])
            }
        }
        distances = newDistances
    }
    return distances[s1.length]
}

fun test(clean: String) {
    println("Testing: $clean")
    val words = clean.split(" ").filter { it.length >= 2 }
    if (clean.length in 5..12 && words.size == 1) {
        val champs = listOf("viego", "ekko", "miss fortune", "pantheon", "renata")
        for (champ in champs) {
            if (champ.length >= 5 && Math.abs(champ.length - clean.length) <= 1) {
                if (levenshteinDistance(clean, champ) <= 1) {
                    println("Matched fuzzy: $champ")
                }
            }
        }
    }
}

fun main() {
    test(normalize("MISS FORTUNE"))
    test(normalize("Kimmy Babadei"))
    test(normalize("GamesAllcruz"))
    test(normalize("matador de de"))
}
