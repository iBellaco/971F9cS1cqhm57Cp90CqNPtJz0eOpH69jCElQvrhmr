import json
with open('bbwr.json') as f:
    data = json.load(f)
def search(obj):
    if isinstance(obj, dict):
        if obj.get('name', '').upper() == 'NILAH':
            print("FOUND NILAH IN DICT:", obj)
        for k, v in obj.items():
            search(v)
    elif isinstance(obj, list):
        for item in obj:
            search(item)
search(data)
