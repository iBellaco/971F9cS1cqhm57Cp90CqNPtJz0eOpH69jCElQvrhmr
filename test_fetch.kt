import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.json.JSONArray
import java.util.concurrent.TimeUnit

fun main() {
    val httpClient = OkHttpClient.Builder().build()
    
    val req1 = Request.Builder().url("https://game.gtimg.cn/images/lgamem/act/lrlib/js/heroList/hero_list.js")
        .header("User-Agent", "Mozilla/5.0")
        .build()
    val res1 = httpClient.newCall(req1).execute()
    val json1 = res1.body?.string() ?: ""
    println("Hero list length: " + json1.length)
    
    val req2 = Request.Builder().url("https://mlol.qt.qq.com/go/lgame_battle_info/hero_rank_list_v2")
        .header("User-Agent", "Mozilla/5.0")
        .header("Referer", "https://lolm.qq.com/act/a20220818raider/index.html")
        .build()
    val res2 = httpClient.newCall(req2).execute()
    val json2 = res2.body?.string() ?: ""
    println("Rank list length: " + json2.length)
}
