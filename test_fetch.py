import urllib.request
import ssl

url = "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp"
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
    print("Status:", response.status)
    print("Length:", len(response.read()))
except Exception as e:
    print("Error:", e)
