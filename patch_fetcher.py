import re

with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "r") as f:
    code = f.read()

# Change return type of fetchAndParseTencentLiveStats
code = code.replace(
    "private fun fetchAndParseTencentLiveStats(targetTier: TencentRankTier): Map<String, Triple<Double, Double, Double>> {",
    "private fun fetchAndParseTencentLiveStats(targetTier: TencentRankTier): Pair<String, Map<String, Triple<Double, Double, Double>>> {"
)

code = code.replace(
    "var parsedDataList: Map<String, Triple<Double, Double, Double>>? = null",
    "var parsedDataList: Map<String, Triple<Double, Double, Double>>? = null\n                var dtStatDate: String? = null"
)

code = code.replace(
    "parsedDataList = fetchAndParseTencentLiveStats(targetTier)",
    "val pair = fetchAndParseTencentLiveStats(targetTier)\n                    dtStatDate = pair.first\n                    parsedDataList = pair.second"
)

# Insert dtstatdate extraction in fetchAndParseTencentLiveStats
# Let's find "val tierData = dataObj.optJSONObject(tierKey) ?: return resultMap"
# Actually we need to return `Pair("", resultMap)` as default.
code = code.replace(
    "val resultMap = mutableMapOf<String, Triple<Double, Double, Double>>() // HeroId -> (WinRate, PickRate, BanRate)",
    "val resultMap = mutableMapOf<String, Triple<Double, Double, Double>>()\n        var dtStatDate = \"\""
)

code = code.replace(
    "val dataObj = jsonRoot.optJSONObject(\"data\") ?: return resultMap",
    "val dataObj = jsonRoot.optJSONObject(\"data\") ?: return Pair(dtStatDate, resultMap)\n            dtStatDate = jsonRoot.optString(\"dtstatdate\", \"\")"
)

code = code.replace(
    "val tierData = dataObj.optJSONObject(tierKey) ?: return resultMap",
    "val tierData = dataObj.optJSONObject(tierKey) ?: return Pair(dtStatDate, resultMap)"
)

code = code.replace(
    "return resultMap",
    "return Pair(dtStatDate, resultMap)"
)

# And use dtStatDate in the timestamp formatting
code = code.replace(
    "val nowTimestamp = nowFormat.format(Date())",
    "val nowTimestamp = if (!dtStatDate.isNullOrEmpty()) \"${dtStatDate!!.substring(0,4)}-${dtStatDate!!.substring(4,6)}-${dtStatDate!!.substring(6,8)} (API)\" else nowFormat.format(Date())"
)

with open("app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt", "w") as f:
    f.write(code)

