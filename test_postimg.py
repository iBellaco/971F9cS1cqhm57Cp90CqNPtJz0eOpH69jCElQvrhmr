import urllib.request
import ssl

urls = [
    "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp",
    "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp",
    "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png"
]

for url in urls:
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        context = ssl._create_unverified_context()
        res = urllib.request.urlopen(req, context=context)
        print("OK:", url, res.status)
    except Exception as e:
        print("Error:", url, e)
