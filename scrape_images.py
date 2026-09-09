import urllib.request, re, concurrent.futures

url = 'https://postimg.cc/gallery/NmLkMYy'
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
html = urllib.request.urlopen(req).read().decode('utf-8')
links = list(set(re.findall(r'href="(https://postimg.cc/[A-Za-z0-9]{8})"', html)))

direct_links = []
def fetch(l):
    try:
        page_html = urllib.request.urlopen(urllib.request.Request(l, headers={'User-Agent': 'Mozilla/5.0'})).read().decode('utf-8')
        dl_link = re.search(r'href="(https://i\.postimg\.cc/[^"]+)"', page_html)
        if dl_link:
            return dl_link.group(1)
    except Exception as e:
        pass
    return None

with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
    results = executor.map(fetch, links)
    for r in results:
        if r:
            direct_links.append(r)

with open('fast_links.txt', 'w') as f:
    for dl in direct_links:
        f.write(dl + '\n')
print(f"Saved {len(direct_links)} links")
