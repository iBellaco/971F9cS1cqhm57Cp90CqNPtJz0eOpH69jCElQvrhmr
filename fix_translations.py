import json

def fix_json(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        data = json.load(f)
    
    new_data = {}
    for k, v in data.items():
        # Replace literal backslash-n in keys with actual newline characters
        # Wait, if the JSON key is "A\nB", it's decoded as actual newline by json.load
        # Let's see what the keys actually contain
        if '\\n' in k:
            new_k = k.replace('\\n', '\n')
            new_data[new_k] = v
        else:
            new_data[k] = v
            
    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(new_data, f, ensure_ascii=False, indent=2)

fix_json('app/src/main/assets/translations_en.json')
fix_json('app/src/main/assets/translations_pt.json')
