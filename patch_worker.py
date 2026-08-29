import re

with open('app/src/main/java/com/example/service/MetaScrapingWorker.kt', 'r') as f:
    text = f.read()

replacement = """
            ChineseMetaSyncService.loadRegion(applicationContext)
            val region = ChineseMetaSyncService.currentRegion.value
            if (region == "CN") {
                ChineseMetaSyncService.syncChineseMeta(applicationContext, TencentRankTier.DIAMOND_PLUS, forceRefresh = true)
            } else if (region == "BestBuildWR") {
                com.example.data.sync.BestBuildWrScraper.syncGlobalTierList(applicationContext)
            }
"""

text = re.sub(r'ChineseMetaSyncService\.loadRegion\(applicationContext\)\s*if \(ChineseMetaSyncService\.currentRegion\.value == "CN"\) \{\s*ChineseMetaSyncService\.syncChineseMeta\(applicationContext, TencentRankTier\.DIAMOND_PLUS, forceRefresh = true\)\s*\}', replacement.strip(), text)

with open('app/src/main/java/com/example/service/MetaScrapingWorker.kt', 'w') as f:
    f.write(text)
