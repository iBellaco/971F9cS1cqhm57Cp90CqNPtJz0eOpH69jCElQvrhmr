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
try:
    with urllib.request.urlopen(req, context=ctx) as response:
        data = json.loads(response.read().decode())
        print(f"Total items in DB: {len(data)}")
        
        bad_ids = ["flash_basic", "ghost_basic", "heal_basic", "barrier_basic", 
                   "ignite_basic", "exhaust_basic", "smite_basic", 
                   "_hilling_smite_basic", "cleanse_basic", "teleport_basic"]
        
        for item in data:
            if item.get("id") in bad_ids:
                print(f"Deleting {item['id']}...")
                del_url = f"{url}?id=eq.{item['id']}"
                del_req = urllib.request.Request(del_url, headers=headers, method='DELETE')
                urllib.request.urlopen(del_req, context=ctx)
        
        print("Done deleting spells from items table.")
except Exception as e:
    print(f"Error: {e}")

