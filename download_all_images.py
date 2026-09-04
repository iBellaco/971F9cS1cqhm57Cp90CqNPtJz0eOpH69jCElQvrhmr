import os, re, json, hashlib, urllib.request, concurrent.futures
from urllib.error import HTTPError, URLError

assets_dir = 'app/src/main/assets/offline_images'
os.makedirs(assets_dir, exist_ok=True)

def get_filename(url):
    ext = url.split('.')[-1].split('?')[0]
    if len(ext) > 5 or not ext.isalnum(): ext = "webp"
    return hashlib.md5(url.encode('utf-8')).hexdigest() + "." + ext

def download_image(url):
    if not url.startswith('http'): return url
    fn = get_filename(url)
    path = os.path.join(assets_dir, fn)
    new_url = f"file:///android_asset/offline_images/{fn}"
    if not os.path.exists(path):
        try:
            req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
            with urllib.request.urlopen(req, timeout=10) as response, open(path, 'wb') as out_file:
                out_file.write(response.read())
        except Exception as e:
            print(f"Failed {url}: {e}")
            return url # fallback
    return new_url

# 1. Collect URLs
json_files = ['app/src/main/res/raw/champions_part1.json', 'app/src/main/res/raw/champions_part2.json']
all_urls = set()

for jf in json_files:
    if os.path.exists(jf):
        with open(jf, 'r', encoding='utf-8') as f:
            data = json.load(f)
        for champ in data:
            if 'avatarUrl' in champ and champ['avatarUrl'].startswith('http'): all_urls.add(champ['avatarUrl'])
            for skill in champ.get('skills', []):
                if 'iconUrl' in skill and skill['iconUrl'].startswith('http'): all_urls.add(skill['iconUrl'])
            if 'primaryRuneIconUrl' in champ and champ['primaryRuneIconUrl'].startswith('http'): all_urls.add(champ['primaryRuneIconUrl'])
            for icon in champ.get('spellsIcons', []):
                if icon.startswith('http'): all_urls.add(icon)
            for icon in champ.get('coreItemsIcons', []):
                if icon.startswith('http'): all_urls.add(icon)
            for icon in champ.get('situationalItemsIcons', []):
                if icon.startswith('http'): all_urls.add(icon)
            for swap in champ.get('itemSwaps', []):
                if swap.get('coreItemIcon', '').startswith('http'): all_urls.add(swap['coreItemIcon'])
                if swap.get('altItemIcon', '').startswith('http'): all_urls.add(swap['altItemIcon'])

kt_files = ['app/src/main/java/com/example/data/WildRiftItemsData.kt', 'app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'app/src/main/java/com/example/data/AvatarCatalog.kt']
url_pattern = re.compile(r'(https?://[^\s"\'\)]+)')
kt_file_contents = {}

for kf in kt_files:
    if os.path.exists(kf):
        with open(kf, 'r', encoding='utf-8') as f:
            content = f.read()
            kt_file_contents[kf] = content
            for match in url_pattern.finditer(content):
                all_urls.add(match.group(1))

print(f"Total unique URLs to download: {len(all_urls)}")

url_map = {}
success_count = 0

with concurrent.futures.ThreadPoolExecutor(max_workers=30) as executor:
    future_to_url = {executor.submit(download_image, url): url for url in all_urls}
    for future in concurrent.futures.as_completed(future_to_url):
        url = future_to_url[future]
        try:
            new_url = future.result()
            if new_url != url:
                url_map[url] = new_url
                success_count += 1
        except Exception as exc:
            pass

print(f"Successfully downloaded {success_count} images.")

# Apply to JSON
for jf in json_files:
    if os.path.exists(jf):
        with open(jf, 'r', encoding='utf-8') as f:
            content = f.read()
        for old, new in url_map.items():
            content = content.replace(old, new)
        with open(jf, 'w', encoding='utf-8') as f:
            f.write(content)

# Apply to KT
for kf, content in kt_file_contents.items():
    new_content = content
    for old, new in url_map.items():
        new_content = new_content.replace(old, new)
    with open(kf, 'w', encoding='utf-8') as f:
        f.write(new_content)

print("Replacement complete!")
