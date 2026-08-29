import urllib.request, re, json, time, concurrent.futures

with open('scraped_builds_list.json', 'r') as f:
    results = json.load(f)

build_paths = []
for slug, builds in results.items():
    for b in builds:
        if 'path' in b:
            build_paths.append(b['path'])

print(f"Total builds to fetch: {len(build_paths)}")

def fetch_build(path):
    req = urllib.request.Request(f'https://bestbuildwr.com{path}', headers={'User-Agent': 'Mozilla/5.0'})
    try:
        html = urllib.request.urlopen(req, timeout=10).read().decode('utf-8')
        match = re.search(r'<script id=\"__NEXT_DATA__\" type=\"application/json\">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            build = data.get('props', {}).get('pageProps', {}).get('build', {})
            return path, build
    except Exception as e:
        pass
    return path, None

detailed_builds = {}
with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
    futures = {executor.submit(fetch_build, path): path for path in build_paths}
    for future in concurrent.futures.as_completed(futures):
        path, build = future.result()
        if build:
            detailed_builds[path] = build
            print(f"Fetched {path}")

with open('detailed_builds.json', 'w') as f:
    json.dump(detailed_builds, f)
