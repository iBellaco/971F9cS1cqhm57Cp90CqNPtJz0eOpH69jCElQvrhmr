import json

def find_champions(data, path=""):
    if isinstance(data, dict):
        for k, v in data.items():
            if k == "champions" or k == "championList":
                print(f"Found at: {path}.{k}")
            find_champions(v, f"{path}.{k}")
    elif isinstance(data, list):
        for i, v in enumerate(data):
            find_champions(v, f"{path}[{i}]")

with open("wr_next_data.json") as f:
    data = json.load(f)

find_champions(data)
