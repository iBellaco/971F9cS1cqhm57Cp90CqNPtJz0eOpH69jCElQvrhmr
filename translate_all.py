import urllib.request
import urllib.parse
import json
import time
import os

with open('strings_to_translate.json', 'r') as f:
    strings = json.load(f)

def translate_joined(texts, target_lang):
    url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=es&tl={target_lang}&dt=t"
    joined_text = " \n ||| \n ".join(texts)
    data = urllib.parse.urlencode({"q": joined_text}).encode("utf-8")
    try:
        req = urllib.request.Request(url, data=data, headers={'User-Agent': 'Mozilla/5.0'})
        response = urllib.request.urlopen(req)
        result = json.loads(response.read().decode('utf-8'))
        full_translated = "".join([x[0] for x in result[0]])
        # Clean up possible spaces around |||
        parts = [t.strip() for t in full_translated.split("|||")]
        return parts
    except Exception as e:
        print("Error:", e)
        return []

def translate_all(target_lang):
    result_map = {}
    chunk_size = 20
    for i in range(0, len(strings), chunk_size):
        chunk = strings[i:i+chunk_size]
        print(f"Translating {i} to {i+len(chunk)} for {target_lang}...")
        translated = translate_joined(chunk, target_lang)
        if len(translated) == len(chunk):
            for orig, trans in zip(chunk, translated):
                result_map[orig] = trans
        else:
            print("Mismatch length! Retrying one by one...")
            for text in chunk:
                t = translate_joined([text], target_lang)
                if t:
                    result_map[text] = t[0]
                time.sleep(0.5)
        time.sleep(1.0)
    return result_map

print("Starting English translation...")
en_map = translate_all("en")
print("Starting Portuguese translation...")
pt_map = translate_all("pt")

os.makedirs("app/src/main/res/raw", exist_ok=True)
with open("app/src/main/res/raw/translations_en.json", "w") as f:
    json.dump(en_map, f, ensure_ascii=False, indent=2)

with open("app/src/main/res/raw/translations_pt.json", "w") as f:
    json.dump(pt_map, f, ensure_ascii=False, indent=2)

print("Done generating JSON files.")
