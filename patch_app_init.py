import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r') as f:
    text = f.read()

replacement = """
                ChineseMetaSyncService.loadRegion(this@WildRiftApp)
                val region = ChineseMetaSyncService.currentRegion.value
                if (region == "CN") {
                    ChineseMetaSyncService.syncChineseMeta(this@WildRiftApp, forceRefresh = true)
                } else if (region == "BestBuildWR") {
                    com.example.data.sync.BestBuildWrScraper.syncGlobalTierList(this@WildRiftApp)
                }
"""

text = re.sub(r'ChineseMetaSyncService\.loadRegion\(this@WildRiftApp\)\s*if \(ChineseMetaSyncService\.currentRegion\.value == "CN"\) \{\s*ChineseMetaSyncService\.syncChineseMeta\(this@WildRiftApp, forceRefresh = true\)\s*\}', replacement.strip(), text)

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w') as f:
    f.write(text)
