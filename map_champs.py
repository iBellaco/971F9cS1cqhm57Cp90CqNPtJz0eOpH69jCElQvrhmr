import urllib.request, json, difflib

req = urllib.request.Request('https://game.gtimg.cn/images/lgamem/act/lrlib/js/heroList/hero_list.js', headers={'User-Agent': 'Mozilla/5.0'})
data = json.loads(urllib.request.urlopen(req).read().decode('utf-8'))

with open('our_champs.txt', 'r') as f:
    our_champs = [x.strip() for x in f.readlines()]

map_str = ""
for k, v in data['heroList'].items():
    alias = v['alias'].lower()
    
    # direct overrides for tricky ones
    if alias == 'gailun': match = 'garen'
    elif alias == 'yatuokesi': match = 'aatrox'
    elif alias == 'lakesi': match = 'lux'
    elif alias == 'emumu': match = 'amumu'
    elif alias == 'leienjiaer': match = 'rengar'
    elif alias == 'kaerma': match = 'karma'
    elif alias == 'sunwukong': match = 'wukong'
    elif alias == 'weien': match = 'vayne'
    elif alias == 'aike': match = 'ekko'
    elif alias == 'aolianna': match = 'orianna'
    elif alias == 'daianna': match = 'dianna'
    elif alias == 'zuoyi': match = 'zoe'
    elif alias == 'taidamier': match = 'tryndamere'
    elif alias == 'zhaoxin': match = 'xin_zhao'
    elif alias == 'kazike': match = 'khazix'
    elif alias == 'jinkesi': match = 'jinx'
    elif alias == 'hekalimu': match = 'hecarim'
    elif alias == 'youmi': match = 'yuumi'
    else:
        # Just use alias or a quick diff
        match = alias
    
    map_str += f'            "{k}" to "{match}", // {v["name"]}\n'

print(map_str[:500])
