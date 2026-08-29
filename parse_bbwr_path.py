import json

def find_path(obj, key, path=""):
    if isinstance(obj, dict):
        if key in obj:
            print(f"Found at: {path} -> {key}")
        for k, v in obj.items():
            find_path(v, key, path + f"['{k}']")
    elif isinstance(obj, list):
        for i, item in enumerate(obj):
            find_path(item, key, path + f"[{i}]")

with open('bbwr.json', 'r') as f:
    data = json.load(f)
    find_path(data, 'tierlists')
