import re

with open('app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt', 'r') as f:
    text = f.read()

replacement = """
                    _syncState.value = ChineseSyncState.Success(
                        TencentRankTier.DIAMOND_PLUS,
                        WildRiftRepository.champions.size,
                        "Reciente",
                        "BestBuildWR",
                        false
                    )
"""

text = re.sub(r'_syncState\.value = ChineseSyncState\.Success\(\s*TencentRankTier\.DIAMOND_PLUS,\s*WildRiftRepository\.champions\.size,\s*"Reciente"\s*\)', replacement.strip(), text)

with open('app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt', 'w') as f:
    f.write(text)
