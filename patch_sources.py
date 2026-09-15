import sys
content = open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'r').read()
target = '''            val sources = listOf(
                Triple("WildRiftFire", "https://www.wildriftfire.com/", "Global"),
                Triple("BestBuildWR", "https://bestbuildwr.com/", "Global"),
                Triple("TencentSuperServer", "https://lolm.qq.com/", "CN")
            )'''
replacement = '''            val sources = listOf(
                Triple("WildRiftFire", "https://www.wildriftfire.com/", "Global"),
                Triple("BestBuildWR", "https://bestbuildwr.com/", "Global"),
                Triple("WR-Meta", "https://wr-meta.com/", "Global"),
                Triple("RiotCloudNA", "https://wildrift.leagueoflegends.com/en-us/", "NA"),
                Triple("TencentSuperServer", "https://lolm.qq.com/", "CN")
            )'''
if target in content:
    open('app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt', 'w').write(content.replace(target, replacement))
    print("SUCCESS")
else:
    print("FAILED")
