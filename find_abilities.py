import json

def find_abilities(data, path=""):
    if isinstance(data, dict):
        for k, v in data.items():
            if isinstance(v, str) and "La Espada de los Oscuros" in v:
                print(f"Found in value at: {path}.{k}")
            find_abilities(v, f"{path}.{k}")
    elif isinstance(data, list):
        for i, v in enumerate(data):
            if isinstance(v, str) and "La Espada de los Oscuros" in v:
                print(f"Found in value at: {path}[{i}]")
            find_abilities(v, f"{path}[{i}]")

with open("aatrox_data.json") as f:
    data = json.load(f)

find_abilities(data)
