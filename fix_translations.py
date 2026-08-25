import json
import urllib.request
import urllib.parse
import time
import re

en = json.load(open('app/src/main/assets/translations_en.json'))
pt = json.load(open('app/src/main/assets/translations_pt.json'))

# Fetch original keys that need translation
keys_to_translate = []
for k, v in en.items():
    if k == v and len(k) > 2:
        keys_to_translate.append(k)

print(f"To translate: {len(keys_to_translate)}")

def translate(texts, target_lang):
    if not texts: return []
    # Combine with a separator to do batch translation
    sep = " ||| "
    combined = sep.join(texts)
    url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=es&tl={target_lang}&dt=t&q={urllib.parse.quote(combined)}"
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)'})
    try:
        with urllib.request.urlopen(req) as response:
            res = json.loads(response.read().decode())
            translated_combined = "".join([s[0] for s in res[0]])
            return translated_combined.split(sep)
    except Exception as e:
        print(f"Error: {e}")
        return texts

chunk_size = 5
for i in range(0, len(keys_to_translate), chunk_size):
    chunk = keys_to_translate[i:i+chunk_size]
    
    en_chunk = translate(chunk, 'en')
    time.sleep(1)
    pt_chunk = translate(chunk, 'pt')
    time.sleep(1)
    
    for j, k in enumerate(chunk):
        if j < len(en_chunk): en[k] = en_chunk[j].strip()
        if j < len(pt_chunk): pt[k] = pt_chunk[j].strip()
    
    print(f"Translated chunk {i//chunk_size + 1}/{len(keys_to_translate)//chunk_size + 1}")

with open('app/src/main/assets/translations_en.json', 'w') as f:
    json.dump(en, f, ensure_ascii=False, indent=2)
with open('app/src/main/assets/translations_pt.json', 'w') as f:
    json.dump(pt, f, ensure_ascii=False, indent=2)
print("Done!")
