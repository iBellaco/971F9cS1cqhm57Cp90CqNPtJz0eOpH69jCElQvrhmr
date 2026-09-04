import urllib.request
import re

url = "https://wr-meta.com/items/"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    html = urllib.request.urlopen(req).read().decode('utf-8')
    # Look for items. Usually they are in a grid or list.
    # Let's extract all item links and names
    items = re.findall(r'<a href="(/items/\d+-[^"]+\.html)".*?>\s*(?:<img[^>]+>\s*)?<span[^>]*>([^<]+)</span>', html)
    if not items:
        # alternative pattern
        items = re.findall(r'<a href="(/items/\d+-[^"]+\.html)".*?title="([^"]+)"', html)
        if not items:
            # Let's just find all a href="/items/..."
            links = re.findall(r'<a[^>]+href="(/items/\d+-[^"]+\.html)"[^>]*>(.*?)</a>', html, re.S)
            for link, content in links:
                name_match = re.search(r'<div class="item-name[^"]*">([^<]+)</div>', content)
                if name_match:
                    print(f"Found: {name_match.group(1)} -> {link}")
                else:
                    # try img alt
                    img_match = re.search(r'alt="([^"]+)"', content)
                    if img_match:
                         print(f"Found (img): {img_match.group(1)} -> {link}")
            print(f"Total links found: {len(links)}")
    else:
        for link, name in items:
            print(f"{name} -> {link}")
except Exception as e:
    print(f"Error: {e}")
