import urllib.request
import ssl

urls = [
    "https://wr-meta.com/uploads/posts/2025-07/1753300268_guardian-angel.webp",
    "https://wr-meta.com/uploads/posts/2025-07/1753300773_magnetic-blaster.webp",
    "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp"
]

for url in urls:
    req = urllib.request.Request(
        url, 
        headers={
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
            'Accept': 'image/webp,image/apng,image/*,*/*;q=0.8'
        }
    )
    try:
        context = ssl._create_unverified_context()
        response = urllib.request.urlopen(req, context=context)
        print("OK:", url)
    except Exception as e:
        print("Error for", url, ":", e)
