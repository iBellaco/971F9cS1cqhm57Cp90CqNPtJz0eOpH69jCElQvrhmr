import urllib.request, re, json, time, concurrent.futures

def fetch_champion(slug):
    req = urllib.request.Request(f'https://bestbuildwr.com/champions/{slug}', headers={'User-Agent': 'Mozilla/5.0'})
    try:
        html = urllib.request.urlopen(req, timeout=10).read().decode('utf-8')
        match = re.search(r'<script id=\"__NEXT_DATA__\" type=\"application/json\">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            builds = data.get('props', {}).get('pageProps', {}).get('champion', {}).get('builds', [])
            return slug, builds
    except Exception as e:
        pass
    return slug, []

req = urllib.request.Request('https://bestbuildwr.com/champions', headers={'User-Agent': 'Mozilla/5.0'})
try:
    html = urllib.request.urlopen(req, timeout=10).read().decode('utf-8')
    match = re.search(r'<script id=\"__NEXT_DATA__\" type=\"application/json\">(.*?)</script>', html)
    if match:
        data = json.loads(match.group(1))
        champions = data.get('props', {}).get('pageProps', {}).get('champions', [])
        slugs = [c['slug'] for c in champions]
        print(f"Found {len(slugs)} champions.")
        
        results = {}
        with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
            futures = {executor.submit(fetch_champion, slug): slug for slug in slugs}
            for future in concurrent.futures.as_completed(futures):
                slug, builds = future.result()
                results[slug] = builds
                print(f"Fetched {slug}, {len(builds)} builds")
                
        with open('scraped_builds_list.json', 'w') as f:
            json.dump(results, f)
except Exception as e:
    print(e)
