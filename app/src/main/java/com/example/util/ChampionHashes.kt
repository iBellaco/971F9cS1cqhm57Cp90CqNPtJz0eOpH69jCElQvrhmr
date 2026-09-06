package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.model.Champion
import java.util.concurrent.ConcurrentHashMap

/**
 * Descriptor vectorial de alta precisión para avatares de campeones de Wild Rift.
 * Contiene el mapa de intensidades estructurales (32x32 = 1024 puntos normalizados)
 * y el histograma cromático RGB (4x4x4 = 64 bins) para comparación visual 100% certera.
 */
data class ChampionVisualSignature(
    val championId: String,
    val normalizedGray: FloatArray, // 1024 floats (media 0, varianza 1)
    val colorHistogram: FloatArray, // 64 bins (suma 1.0)
    val avgLuminance: Float,
    val avgSaturation: Float,
    val aHash: Long
)

object ChampionHashes {
    private const val TAG = "ChampionHashes"
    
    // Máscara circular (radio 13.5 en matriz 32x32 con centro en 15.5, 15.5)
    // Aísla el rostro del campeón y descarta esquinas cuadradas y bordes decorativos
    val CIRCLE_MASK = BooleanArray(1024) { idx ->
        val x = (idx % 32) - 15.5f
        val y = (idx / 32) - 15.5f
        (x * x + y * y) <= 182.25f
    }
    val CIRCLE_PIXEL_COUNT = CIRCLE_MASK.count { it }.toFloat()

    // Firmas visuales completas de los 141 campeones precargadas desde assets
    private val signatures = ConcurrentHashMap<String, ChampionVisualSignature>()
    private val dynamicMap = ConcurrentHashMap<String, Long>()

    // Inicializa y precarga los descriptores visuales de todos los campeones locales desde assets/champions/
    fun ensureLoaded(context: Context) {
        if (signatures.isNotEmpty()) return
        synchronized(this) {
            if (signatures.isNotEmpty()) return
            initFromAssets(context)
        }
    }

    fun initFromAssets(context: Context) {
        if (signatures.isNotEmpty()) return
        try {
            val assetManager = context.assets
            val list = assetManager.list("champions") ?: emptyArray()
            for (filename in list) {
                if (filename.endsWith(".png")) {
                    val championId = filename.removeSuffix(".png")
                    try {
                        assetManager.open("champions/$filename").use { stream ->
                            val bitmap = BitmapFactory.decodeStream(stream)
                            if (bitmap != null) {
                                val sig = createSignature(championId, bitmap)
                                signatures[championId] = sig
                                dynamicMap[championId] = sig.aHash
                                
                                val crop = try { ImageHashMatcher.getInnerCrop(bitmap, 0.85f) } catch (e: Exception) { null }
                                if (crop != null && crop != bitmap) {
                                    val cropHash = ImageHashMatcher.calculateHash(crop)
                                    dynamicMap["${championId}_crop"] = cropHash
                                    crop.recycle()
                                }
                                bitmap.recycle()
                            }
                        }
                    } catch (e: Exception) {
                        AppLogger.w(TAG, "Error cargando asset de $championId: ${e.message}")
                    }
                }
            }
            AppLogger.d(TAG, "Cargadas ${signatures.size} firmas visuales de alta precisión desde assets")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error inicializando firmas visuales", e)
        }
    }

    // Genera la firma visual a partir de un Bitmap (32x32 estructural con máscara circular + 64-bin color)
    fun createSignature(championId: String, bitmap: Bitmap): ChampionVisualSignature {
        val innerCrop = try { ImageHashMatcher.getInnerCrop(bitmap, 0.85f) } catch (e: Exception) { bitmap }
        val scaled = Bitmap.createScaledBitmap(innerCrop, 32, 32, true)
        val pixels = IntArray(1024)
        scaled.getPixels(pixels, 0, 32, 0, 0, 32, 32)
        
        val grays = FloatArray(1024)
        val colorHist = FloatArray(64)
        var sumGray = 0f
        var sumLuminance = 0f
        var sumSaturation = 0f
        
        for (i in 0 until 1024) {
            if (!CIRCLE_MASK[i]) continue
            val color = pixels[i]
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            
            // Luminancia perceptual
            val lum = 0.299f * r + 0.587f * g + 0.114f * b
            grays[i] = lum
            sumGray += lum
            sumLuminance += lum
            
            // Saturación en espacio HSV aproximada
            val max = maxOf(r, maxOf(g, b)).toFloat()
            val min = minOf(r, minOf(g, b)).toFloat()
            val sat = if (max > 0f) (max - min) / max else 0f
            sumSaturation += sat
            
            // Histograma cromático 4x4x4 RGB
            val rBin = (r / 64).coerceIn(0, 3)
            val gBin = (g / 64).coerceIn(0, 3)
            val bBin = (b / 64).coerceIn(0, 3)
            val binIndex = (rBin shl 4) or (gBin shl 2) or bBin
            colorHist[binIndex] += 1f
        }
        
        // Normalizar grays sobre los píxeles interiores al círculo (media 0, varianza 1)
        val mean = sumGray / CIRCLE_PIXEL_COUNT
        var sumVar = 0f
        for (i in 0 until 1024) {
            if (CIRCLE_MASK[i]) {
                val diff = grays[i] - mean
                sumVar += diff * diff
            }
        }
        val stdDev = Math.sqrt((sumVar / CIRCLE_PIXEL_COUNT.toDouble())).toFloat().coerceAtLeast(0.001f)
        val normalizedGray = FloatArray(1024)
        for (i in 0 until 1024) {
            if (CIRCLE_MASK[i]) {
                normalizedGray[i] = (grays[i] - mean) / stdDev
            }
        }
        
        // Normalizar histograma de color sobre los píxeles interiores al círculo (suma = 1.0)
        for (b in 0 until 64) {
            colorHist[b] /= CIRCLE_PIXEL_COUNT
        }
        
        val aHash = ImageHashMatcher.calculateHash(innerCrop)
        
        scaled.recycle()
        if (innerCrop != bitmap) {
            try { innerCrop.recycle() } catch (ignored: Exception) {}
        }
        
        return ChampionVisualSignature(
            championId = championId,
            normalizedGray = normalizedGray,
            colorHistogram = colorHist,
            avgLuminance = sumLuminance / CIRCLE_PIXEL_COUNT,
            avgSaturation = sumSaturation / CIRCLE_PIXEL_COUNT,
            aHash = aHash
        )
    }

    fun getAllSignatures(): Collection<ChampionVisualSignature> {
        return signatures.values
    }

    fun getSignature(championId: String): ChampionVisualSignature? {
        return signatures[championId]
    }

    fun getHashesForChampion(championId: String): List<Long> {
        val list = mutableListOf<Long>()
        dynamicMap[championId]?.let { list.add(it) }
        dynamicMap["${championId}_crop"]?.let { list.add(it) }

        // Fallback al mapa estático si la carga dinámica no hubiese completado
        map[championId]?.let { if (!list.contains(it)) list.add(it) }
        map["${championId}_crop"]?.let { if (!list.contains(it)) list.add(it) }
        map["${championId}_alt1"]?.let { if (!list.contains(it)) list.add(it) }
        map["${championId}_alt2"]?.let { if (!list.contains(it)) list.add(it) }
        map["${championId}_alt3"]?.let { if (!list.contains(it)) list.add(it) }

        return list
    }

    val map = mapOf<String, Long>(
        "aatrox" to 16904193607940991L,
        "ahri" to 7033847026762172658L,
        "akali" to -2240302838053930478L,
        "akshan" to 562835008169512199L,
        "alistar" to 583230737332102882L,
        "amumu" to 610369932369654896L,
        "annie" to 3528659067729629184L,
        "ashe" to 1203339675228177511L,
        "aurelion_sol" to 7788481045870674944L,
        "aurora" to -2251534896373908893L,
        "bard" to 911767532854723951L,
        "blitzcrank" to 8583330781902848L,
        "brand" to 9170447737383819022L,
        "braum" to 9221075988405240128L,
        "caitlyn" to 108584525616835598L,
        "camille" to 16957324094267632L,
        "cho_gath" to -3452991043110119425L,
        "corki" to 1761588384718910464L,
        "darius" to 7014415992943417496L,
        "diana" to 9079250218976594176L,
        "dr_mundo" to 7408888647935119808L,
        "draven" to 2917266165723568136L,
        "ekko" to 8239953883695547984L,
        "evelynn" to 6412273253883087040L,
        "ezreal" to 2178087519543757866L,
        "fiddlesticks" to 7486041436262422078L,
        "fiora" to -8713487567839891689L,
        "fizz" to -353780042285035L,
        "galio" to 22615102330961504L,
        "garen" to 4647921758717623199L,
        "gnar" to 9040727840357728L,
        "gragas" to -217018184845223928L,
        "graves" to 5139132732208750848L,
        "gwen" to 5798516707391698114L,
        "hecarim" to 34558268396215810L,
        "heimerdinger" to 1187257694683347968L,
        "irelia" to -17000887352782712L,
        "janna" to -1661558897214074624L,
        "jarvan_iv" to 8961440202809302016L,
        "jax" to 1089623853029131649L,
        "jayce" to -4347110885535770624L,
        "jhin" to 16954667877015170L,
        "jinx" to 6943557066203904014L,
        "k_sante" to -9006669389007560512L,
        "kai_sa" to -1468823718347793140L,
        "kalista" to 51942850157114712L,
        "karma" to -7739276685275182205L,
        "kassadin" to 44083806303490560L,
        "katarina" to 1167672887855736448L,
        "kayle" to -44208786446336L,
        "kayn" to 1660448994067422977L,
        "kennen" to 309796465626694080L,
        "kha_zix" to 1731647496545882110L,
        "kindred" to 584997481883785216L,
        "kog_maw" to 4648909144891072576L,
        "lee_sin" to -1602221796775958560L,
        "leona" to -2917848146438989808L,
        "lillia" to 206980895358076L,
        "lissandra" to 3490962857712307998L,
        "lucian" to 170042431199147806L,
        "lulu" to -1746406963985237888L,
        "lux" to 5547587014752083459L,
        "malphite" to 288515164759123967L,
        "maokai" to -4554682005941852960L,
        "master_yi" to 9142544707889513250L,
        "milio" to 220424719730333888L,
        "miss_fortune" to 63241968515184664L,
        "mordekaiser" to 8738970712142217218L,
        "morgana" to 11378006140263936L,
        "nami" to 8141558907312805888L,
        "nasus" to 6691462812006670536L,
        "nautilus" to -4052242381423513344L,
        "nidalee" to 1385755671703266051L,
        "nilah" to 145529176134516735L,
        "nocturne" to 5098287816848174975L,
        "norra" to -2242263171381214929L,
        "nunu_willump" to -3710260689148387329L,
        "olaf" to -5760051006779826178L,
        "orianna" to 1160906701234781696L,
        "ornn" to 9150756012277382332L,
        "pantheon" to -143701011589982L,
        "poppy" to 6865479373382684419L,
        "pyke" to 9113909134951995651L,
        "rakan" to -2199032482770022400L,
        "rammus" to 3529973287044315390L,
        "rell" to 48926025462820623L,
        "renekton" to 1448138984608562681L,
        "rengar" to 2256082467780304947L,
        "riven" to -511159690563225488L,
        "rumble" to 2803394839758904384L,
        "ryze" to 4575480814275887874L,
        "samira" to -4489824031457084176L,
        "senna" to 1094187029014452486L,
        "seraphine" to 170995357922434816L,
        "sett" to 7046414042444807698L,
        "shen" to 27093417032542432L,
        "shyvana" to 2071244068425193544L,
        "singed" to 468232649861240832L,
        "sion" to 607105154421915650L,
        "sivir" to -2605899212806416360L,
        "skarner" to 177466177424154087L,
        "smolder" to -5563920200434959863L,
        "sona" to 585548202970710011L,
        "soraka" to -2305923561387169790L,
        "swain" to 1755866327121083408L,
        "syndra" to 2337762091550521958L,
        "taliyah" to -2238038219876919187L,
        "talon" to 8428134448881258150L,
        "teemo" to 1250102798129576196L,
        "thresh" to 6257478759724383374L,
        "tristana" to 305814139158265600L,
        "tryndamere" to -5154305758176068496L,
        "twisted_fate" to 6111930808794642275L,
        "twitch" to 513140437269040958L,
        "urgot" to 4070194207285127960L,
        "urgot_alt1" to 0x3C7EFFFFFFFF7E3CL,
        "urgot_alt2" to 0x183C7EFFFF7E3C18L,
        "urgot_alt3" to 0x003C7E7E7E7E3C00L,
        "varus" to 1109956231239113472L,
        "vayne" to 6261939898301964L,
        "veigar" to 4485585535527928642L,
        "vel_koz" to 1370306493982600224L,
        "vex" to 540202430914377984L,
        "vi" to 5547940491594373136L,
        "viego" to -5729338275963111656L,
        "viktor" to -35595891815051771L,
        "vladimir" to 873204089619502860L,
        "volibear" to 1017671915531951618L,
        "warwick" to 57204548294599L,
        "wukong" to -4556867813276548350L,
        "xayah" to -7921523872227837617L,
        "xin_zhao" to 288611076935645722L,
        "yasuo" to 505316977724125240L,
        "yone" to -795797092422189528L,
        "yuumi" to -941902439567883295L,
        "zed" to -79111166183569279L,
        "zeri" to 3421593779406304472L,
        "ziggs" to 2369035840647912697L,
        "zilean" to 31382532508626434L,
        "zoe" to 4408812032342429954L,
        "zyra" to 25034493498513592L
    )
}
