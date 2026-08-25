import urllib.request
import ssl

urls = [
    "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp",
    "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp",
    "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp",
    "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png"
]

for url in urls:
    try:
        req = urllib.request.Request(
            url, 
            headers={
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
                'Accept': 'image/webp,image/apng,image/*,*/*;q=0.8'
            }
        )
        ctx = ssl.create_default_context()
        with urllib.request.urlopen(req, context=ctx) as res:
            print(f"URL: {url} -> Status: {res.status}, Type: {res.headers.get('Content-Type')}, Length: {res.headers.get('Content-Length')}")
    except Exception as e:
        print(f"Error {url}: {e}")
