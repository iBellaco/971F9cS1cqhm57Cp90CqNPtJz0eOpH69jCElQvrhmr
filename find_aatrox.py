import json

def find_aatrox(data, path=""):
    if isinstance(data, dict):
        for k, v in data.items():
            if isinstance(v, str) and "Aatrox" in v:
                print(f"Found in value at: {path}.{k}")
            find_aatrox(v, f"{path}.{k}")
    elif isinstance(data, list):
        for i, v in enumerate(data):
            if isinstance(v, str) and "Aatrox" in v:
                print(f"Found in value at: {path}[{i}]")
            find_aatrox(v, f"{path}[{i}]")

with open("wr_next_data.json") as f:
    data = json.load(f)

find_aatrox(data)
