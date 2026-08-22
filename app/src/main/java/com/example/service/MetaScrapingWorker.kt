package com.example.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import com.example.data.WildRiftRepository
import com.example.data.sync.ChineseMetaSyncService
import com.example.data.sync.TencentRankTier
import com.example.util.AppLogger
import java.text.Normalizer

class MetaScrapingWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d("MetaScrapingWorker", "Iniciando conexión con API Oficial de Tencent (lolm.qq.com)...")
            
            // 1. Obtener la lista de Héroes y sus IDs oficiales de Tencent
            val heroListUrl = URL("https://game.gtimg.cn/images/lgamem/act/lrlib/js/heroList/hero_list.js")
            val heroConn = heroListUrl.openConnection() as HttpURLConnection
            heroConn.requestMethod = "GET"
            heroConn.setRequestProperty("User-Agent", "Mozilla/5.0")
            
            val heroJsonStr = heroConn.inputStream.bufferedReader().use { it.readText() }
            val heroJson = JSONObject(heroJsonStr).getJSONObject("heroList")
            
            // Mapeo: ID de Tencent -> (Nombre Chino, Alias Pinyin)
            val tencentIdToData = mutableMapOf<String, Pair<String, String>>()
            val keys = heroJson.keys()
            while (keys.hasNext()) {
                val tencentId = keys.next()
                val heroData = heroJson.getJSONObject(tencentId)
                tencentIdToData[tencentId] = Pair(
                    heroData.getString("name"),
                    heroData.optString("alias", "")
                )
            }
            
            // 2. Obtener estadísticas del Meta (Win Rate, Pick Rate, Ban Rate)
            val rankUrl = URL("https://mlol.qt.qq.com/go/lgame_battle_info/hero_rank_list_v2")
            val rankConn = rankUrl.openConnection() as HttpURLConnection
            rankConn.requestMethod = "GET"
            rankConn.setRequestProperty("User-Agent", "Mozilla/5.0")
            
            val rankJsonStr = rankConn.inputStream.bufferedReader().use { it.readText() }
            // "0" es el ranking global (Diamante+)
            val rankData = JSONObject(rankJsonStr).getJSONObject("data").getJSONObject("0") 
            
            var updatedCount = 0
            val allChamps = WildRiftRepository.champions.toMutableList()
            
            // Iterar por roles (1=Top, 2=Jg, 3=Mid, 4=ADC, 5=Sup)
            val roleKeys = rankData.keys()
            while (roleKeys.hasNext()) {
                val roleId = roleKeys.next()
                val champArray = rankData.getJSONArray(roleId)
                
                for (i in 0 until champArray.length()) {
                    val stats = champArray.getJSONObject(i)
                    val tId = stats.getString("hero_id")
                    
                    val winRate = stats.getString("win_rate_percent").toDoubleOrNull() ?: continue
                    val pickRate = stats.getString("appear_rate_percent").toDoubleOrNull() ?: continue
                    val banRate = stats.getString("forbid_rate_percent").toDoubleOrNull() ?: continue
                    
                    val tencentInfo = tencentIdToData[tId] ?: continue
                    val tencentAlias = tencentInfo.second.lowercase()
                    
                    // Encontrar el campeón en nuestra base de datos
                    val index = allChamps.indexOfFirst { 
                        matchAlias(it.id, it.name, tencentAlias) 
                    }
                    
                    if (index != -1) {
                        val oldChamp = allChamps[index]
                        // Promediar o sobreescribir las estadísticas si aparece en múltiples roles
                        val newChamp = oldChamp.copy(
                            winrate = winRate,
                            pickRate = pickRate,
                            banRate = banRate
                        )
                        allChamps[index] = newChamp
                        updatedCount++
                        Log.d("MetaScrapingWorker", "Sincronizado: ${newChamp.name} -> WR: $winRate%, PR: $pickRate%, BR: $banRate%")
                    }
                }
            }

            // Sincronizar usando el servicio integral de estadísticas de Tencent China con cálculo de deltas
            ChineseMetaSyncService.syncChineseMeta(applicationContext, TencentRankTier.DIAMOND_PLUS, forceRefresh = true)
            
            AppLogger.d("MetaScrapingWorker", "Estadísticas extraídas y deltas calculados correctamente del servidor CN.")
            Result.success()
        } catch (e: Exception) {
            Log.e("MetaScrapingWorker", "Error al realizar scraping de la API China: ${e.message}", e)
            Result.retry()
        }
    }

    private fun matchAlias(ourId: String, ourName: String, tencentAlias: String): Boolean {
        val normId = ourId.lowercase().replace("_", "").replace(" ", "")
        val normName = ourName.lowercase().replace(" ", "").replace("'", "")
        val normTencent = tencentAlias.replace("_", "")
        
        // Mapeo duro para excepciones fonéticas/chinas
        val hardcodedMap = mapOf(
            "gailun" to "garen",
            "yatuokesi" to "aatrox",
            "lakesi" to "lux",
            "emumu" to "amumu",
            "kaiyin" to "kayn",
            "leienjiaer" to "rengar",
            "kaerma" to "karma",
            "sunwukong" to "wukong",
            "weien" to "vayne",
            "aike" to "ekko",
            "aolianna" to "orianna",
            "daianna" to "diana",
            "zuoyi" to "zoe",
            "taidamier" to "tryndamere",
            "zhaoxin" to "xinzhao",
            "kazike" to "khazix",
            "jinkesi" to "jinx",
            "hekalimu" to "hecarim",
            "youmi" to "yuumi",
            "salefenni" to "seraphine",
            "luikang" to "rakan",
            "xia" to "xayah",
            "kaisa" to "kaisa",
            "pailike" to "pyke",
            "weikusi" to "vex",
            "geluifu" to "graves",
            "katuosi" to "karthus",
            "tamu" to "tahmkench",
            "kalisita" to "kalista",
            "zeli" to "zeri"
        )
        
        if (hardcodedMap[normTencent] == normId || hardcodedMap[normTencent] == normName) return true
        
        return normId.contains(normTencent) || normTencent.contains(normId) ||
               normName.contains(normTencent) || normTencent.contains(normName)
    }
}
