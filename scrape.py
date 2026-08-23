import urllib.request
import re

url = "https://postimg.cc/gallery/RHxYK5J"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    html = urllib.request.urlopen(req).read().decode('utf-8')
    pages = list(set(re.findall(r'https://postimg\.cc/[A-Za-z0-9]+', html)))
    for page in pages:
        if "gallery" in page: continue
        try:
            p_req = urllib.request.Request(page, headers={'User-Agent': 'Mozilla/5.0'})
            p_html = urllib.request.urlopen(p_req).read().decode('utf-8')
            img_links = re.findall(r'https://i\.postimg\.cc/[A-Za-z0-9]+/[^"]+\.png', p_html)
            if img_links:
                print(img_links[0])
        except:
            pass
except Exception as e:
    print(e)
