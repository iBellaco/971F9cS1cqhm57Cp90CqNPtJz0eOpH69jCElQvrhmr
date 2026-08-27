import json

def find_abilities(data, path=""):
    if isinstance(data, dict):
        for k, v in data.items():
            if isinstance(v, str) and ("pasiva" in v.lower() or "habilidad" in v.lower() or "enfrentamiento" in v.lower() or "daño" in v.lower()):
                print(f"Found in value at: {path}.{k}")
            find_abilities(v, f"{path}.{k}")
    elif isinstance(data, list):
        for i, v in enumerate(data):
            if isinstance(v, str) and ("pasiva" in v.lower() or "habilidad" in v.lower() or "enfrentamiento" in v.lower() or "daño" in v.lower()):
                print(f"Found in value at: {path}[{i}]")
            find_abilities(v, f"{path}[{i}]")

with open("aatrox_data.json") as f:
    data = json.load(f)

find_abilities(data)
