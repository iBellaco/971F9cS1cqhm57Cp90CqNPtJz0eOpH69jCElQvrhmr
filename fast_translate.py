import urllib.request
import urllib.parse
import json
import time
import os
import re

with open('strings_to_translate.json', 'r') as f:
    strings = json.load(f)

def do_translate(text, target_lang):
    url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=es&tl={target_lang}&dt=t"
    data = urllib.parse.urlencode({"q": text}).encode("utf-8")
    for attempt in range(3):
        try:
            req = urllib.request.Request(url, data=data, headers={'User-Agent': 'Mozilla/5.0'})
            response = urllib.request.urlopen(req)
            result = json.loads(response.read().decode('utf-8'))
            return "".join([x[0] for x in result[0] if x[0]])
        except Exception as e:
            time.sleep(1)
    return text # Fallback to original if failed

def save_json(filename, data):
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    with open(filename, "w") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

en_map = {}
pt_map = {}

# try loading existing if any
try:
    with open("app/src/main/res/raw/translations_en.json", "r") as f:
        en_map = json.load(f)
except:
    pass

try:
    with open("app/src/main/res/raw/translations_pt.json", "r") as f:
        pt_map = json.load(f)
except:
    pass

# We can't do joined arrays if the delimiters get messed up by translation.
# Let's batch them in chunks of 50. BUT we will join them with something less breakable: `\n\n###\n\n`
delim = "\n\n###\n\n"
chunk_size = 30

for i in range(0, len(strings), chunk_size):
    chunk = strings[i:i+chunk_size]
    
    # Check if they are already translated
    needed_en = [c for c in chunk if c not in en_map]
    needed_pt = [c for c in chunk if c not in pt_map]

    if needed_en:
        joined_en = delim.join(needed_en)
        res = do_translate(joined_en, "en")
        parts = [p.strip() for p in res.split("###")]
        if len(parts) == len(needed_en):
            for orig, trans in zip(needed_en, parts):
                en_map[orig] = trans
        else:
            # Fallback one by one
            for orig in needed_en:
                en_map[orig] = do_translate(orig, "en")
                
    if needed_pt:
        joined_pt = delim.join(needed_pt)
        res = do_translate(joined_pt, "pt")
        parts = [p.strip() for p in res.split("###")]
        if len(parts) == len(needed_pt):
            for orig, trans in zip(needed_pt, parts):
                pt_map[orig] = trans
        else:
            # Fallback one by one
            for orig in needed_pt:
                pt_map[orig] = do_translate(orig, "pt")
                
    # Save every chunk
    save_json("app/src/main/res/raw/translations_en.json", en_map)
    save_json("app/src/main/res/raw/translations_pt.json", pt_map)
    print(f"Processed {min(i+chunk_size, len(strings))}/{len(strings)}")
    
print("All done!")
