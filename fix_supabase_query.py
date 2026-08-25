import urllib.request
import json
import ssl

url = "https://yreknglctxujpetgqhnw.supabase.co/rest/v1/wr_items"
headers = {
    "apikey": "sb_publishable_bQJGpyYVR-uxtBmN03F5yA_ZuibUcAr",
    "Authorization": "Bearer sb_publishable_bQJGpyYVR-uxtBmN03F5yA_ZuibUcAr",
    "Content-Type": "application/json"
}
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

req = urllib.request.Request(url, headers=headers)
with urllib.request.urlopen(req, context=ctx) as response:
    data = json.loads(response.read().decode())
    for item in data:
        if "basic" in item.get("id").lower():
            print(item["id"])
