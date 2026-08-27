import re

files_to_patch = [
    'app/src/main/java/com/example/data/sync/ChineseMetaSyncService.kt',
    'app/src/main/java/com/example/data/sync/BestBuildWrScraper.kt',
    'app/src/main/java/com/example/data/local/WildRiftLocalCache.kt',
    'app/src/main/java/com/example/service/MetaScrapingWorker.kt'
]

for file in files_to_patch:
    with open(file, 'r') as f:
        content = f.read()
    
    # Replace assignment with clear and addAll
    content = re.sub(r'WildRiftRepository\.champions\s*=\s*([a-zA-Z0-9_]+)', r'WildRiftRepository.champions.clear(); WildRiftRepository.champions.addAll(\1)', content)
    
    with open(file, 'w') as f:
        f.write(content)
