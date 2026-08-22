import urllib.request, json
req = urllib.request.Request('https://game.gtimg.cn/images/lgamem/act/lrlib/js/heroList/hero_list.js', headers={'User-Agent': 'Mozilla/5.0'})
data = json.loads(urllib.request.urlopen(req).read().decode('utf-8'))
for k, v in data['heroList'].items():
    print(f'"{k}" to "{v["alias"]}", // {v["name"]}')
