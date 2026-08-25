import urllib.request
import ssl

urls = [
    ("Espada Larga", "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp"),
    ("Botas de Velocidad", "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp"),
    ("Daga", "https://i.postimg.cc/CZgNV0y5/1753390550-dagger.webp"),
    ("Cetro de Cristal", "https://wr-meta.com/uploads/posts/2025-07/1753388390_rylais-crystal-scepter.webp")
]

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

for name, url in urls:
    req = urllib.request.Request(
        url, 
        headers={'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)'}
    )
    try:
        with urllib.request.urlopen(req, context=ctx, timeout=10) as resp:
            print(f"{name}: HTTP {resp.status}, Content-Type: {resp.headers.get('Content-Type')}, Length: {len(resp.read())}")
    except Exception as e:
        print(f"{name} ERROR: {e}")
