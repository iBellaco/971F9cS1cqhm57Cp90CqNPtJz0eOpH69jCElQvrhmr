import json
import urllib.request
import urllib.parse
import time
import random

def translate(text, target_lang):
    if not text or len(text) < 2: return text
    url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=es&tl={target_lang}&dt=t&q={urllib.parse.quote(text)}"
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        with urllib.request.urlopen(req) as response:
            res = json.loads(response.read().decode())
            return "".join([s[0] for s in res[0]])
    except Exception as e:
        print(f"Error translating: {e}")
        return text

en = json.load(open('app/src/main/assets/translations_en.json'))
pt = json.load(open('app/src/main/assets/translations_pt.json'))

changed = False
keys = list(en.keys())
for i, k in enumerate(keys):
    if en[k].endswith('(EN)'):
        print(f"[{i}/{len(keys)}] Translating to EN: {k[:30]}")
        en[k] = translate(k, 'en')
        changed = True
        time.sleep(0.3 + random.random()*0.3)

for i, k in enumerate(list(pt.keys())):
    if pt[k].endswith('(PT)'):
        print(f"[{i}/{len(pt)}] Translating to PT: {k[:30]}")
        pt[k] = translate(k, 'pt')
        changed = True
        time.sleep(0.3 + random.random()*0.3)

if changed:
    with open('app/src/main/assets/translations_en.json', 'w') as f:
        json.dump(en, f, ensure_ascii=False, indent=2)
    with open('app/src/main/assets/translations_pt.json', 'w') as f:
        json.dump(pt, f, ensure_ascii=False, indent=2)
    print("Translations done.")
