import json

with open('wr_next_data.json') as f:
    d = json.load(f)

champs = d['props']['pageProps']['page']['blades'][2]['items']
mapping = {}
for c in champs:
    name = c.get('title', '')
    url = c.get('action', {}).get('payload', {}).get('url', '')
    if name and url:
        # url is like /es-es/champions/aatrox/
        url_name = url.strip('/').split('/')[-1]
        mapping[name] = url_name
        
print(json.dumps(mapping, indent=2))
