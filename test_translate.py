import urllib.request
import urllib.parse
text = "Hola mundo"
url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=es&tl=en&dt=t&q={urllib.parse.quote(text)}"
req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        print("Success:", response.read().decode())
except Exception as e:
    print("Error:", e)
