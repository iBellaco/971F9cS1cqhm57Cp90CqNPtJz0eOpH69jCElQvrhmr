import urllib.request
import json
import re

req = urllib.request.Request("https://bestbuildwr.com/tierlist", headers={'User-Agent': 'Mozilla/5.0'})
try:
    with urllib.request.urlopen(req) as response:
        html = response.read().decode('utf-8')
        match = re.search(r'<script id="__NEXT_DATA__" type="application/json">(.*?)</script>', html)
        if match:
            data = json.loads(match.group(1))
            def find_label(obj, path=""):
                if isinstance(obj, dict):
                    if "label" in obj and obj["label"] == "Top":
                        print(f"Found Top at: {path}")
                    for k, v in obj.items():
                        find_label(v, path + f"['{k}']")
                elif isinstance(obj, list):
                    for i, item in enumerate(obj):
                        find_label(item, path + f"[{i}]")
            find_label(data)
except Exception as e:
    print(e)
