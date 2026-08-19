import os
import re

# Comprehensive mapping of champion ids to their specific matchups
matchups = {
    # BARON LANE
    "aatrox": {"adv": ["Sion", "Dr. Mundo", "Shen"], "con": ["Fiora", "Irelia", "Jax"], "syn": ["Amumu", "Orianna", "Yasuo"]},
    "camille": {"adv": ["Garen", "Nasus", "Darius"], "con": ["Jax", "Fiora", "Renekton"], "syn": ["Galio", "Orianna", "Vi"]},
    "darius": {"adv": ["Nasus", "Sion", "Garen"], "con": ["Vayne", "Teemo", "Jayce"], "syn": ["Nunu", "Ahri", "Thresh"]},
    "drmundo": {"adv": ["Malphite", "Teemo", "Singed"], "con": ["Gwen", "Vayne", "Fiora"], "syn": ["Soraka", "Yuumi", "Olaf"]},
    "fiora": {"adv": ["Aatrox", "Sion", "Camille"], "con": ["Malphite", "Kennen", "Jax"], "syn": ["Vi", "Jarvan IV", "Lulu"]},
    "garen": {"adv": ["Jax", "Riven", "Irelia"], "con": ["Vayne", "Darius", "Camille"], "syn": ["Yuumi", "Amumu", "Orianna"]},
    "gwen": {"adv": ["Dr. Mundo", "Sion", "Ornn"], "con": ["Fiora", "Jax", "Riven"], "syn": ["Jarvan IV", "Amumu", "Leona"]},
    "irelia": {"adv": ["Gnar", "Kayle", "Jayce"], "con": ["Jax", "Sett", "Fiora"], "syn": ["Sejuani", "Diana", "Wukong"]},
    "jax": {"adv": ["Camille", "Fiora", "Irelia"], "con": ["Malphite", "Kennen", "Jayce"], "syn": ["Lulu", "Orianna", "Galio"]},
    "jayce": {"adv": ["Darius", "Garen", "Singed"], "con": ["Irelia", "Wukong", "Malphite"], "syn": ["Nidalee", "Elise", "Zoe"]},
    "kayle": {"adv": ["Garen", "Singed", "Illaoi"], "con": ["Jax", "Irelia", "Renekton"], "syn": ["Master Yi", "Lulu", "Nunu"]},
    "kennen": {"adv": ["Jax", "Fiora", "Darius"], "con": ["Irelia", "Jayce", "Sylas"], "syn": ["Amumu", "Jarvan IV", "Malphite"]},
    "malphite": {"adv": ["Jax", "Tryndamere", "Fiora"], "con": ["Sylas", "Gwen", "Rumble"], "syn": ["Yasuo", "Orianna", "Miss Fortune"]},
    "nasus": {"adv": ["Kayle", "Garen", "Malphite"], "con": ["Darius", "Teemo", "Vayne"], "syn": ["Lulu", "Thresh", "Nami"]},
    "ornn": {"adv": ["Malphite", "Sion", "Renekton"], "con": ["Fiora", "Gwen", "Vayne"], "syn": ["Yasuo", "Orianna", "Anivia"]},
    "pantheon": {"adv": ["Yasuo", "Fiora", "Renekton"], "con": ["Malphite", "Shen", "Sion"], "syn": ["Taliyah", "Elise", "Nidalee"]},
    "renekton": {"adv": ["Riven", "Irelia", "Yasuo"], "con": ["Illaoi", "Gnar", "Kennen"], "syn": ["Elise", "Nidalee", "Taliyah"]},
    "riven": {"adv": ["Yasuo", "Irelia", "Jax"], "con": ["Renekton", "Poppy", "Kennen"], "syn": ["Amumu", "Sejuani", "Diana"]},
    "sett": {"adv": ["Irelia", "Yasuo", "Darius"], "con": ["Volibear", "Kennen", "Vayne"], "syn": ["Orianna", "Nami", "Lulu"]},
    "shen": {"adv": ["Jax", "Camille", "Irelia"], "con": ["Darius", "Gwen", "Aatrox"], "syn": ["Nocturne", "Evelynn", "Akali"]},
    "singed": {"adv": ["Jax", "Garen", "Darius"], "con": ["Vayne", "Teemo", "Kayle"], "syn": ["Yuumi", "Zilean", "Cassiopeia"]},
    "sion": {"adv": ["Malphite", "Teemo", "Nasus"], "con": ["Fiora", "Aatrox", "Darius"], "syn": ["Zoe", "Yasuo", "Orianna"]},
    "teemo": {"adv": ["Garen", "Darius", "Nasus"], "con": ["Pantheon", "Jayce", "Irelia"], "syn": ["Cassiopeia", "Twitch", "Shaco"]},
    "tryndamere": {"adv": ["Garen", "Dr. Mundo", "Sion"], "con": ["Malphite", "Teemo", "Jax"], "syn": ["Lulu", "Yuumi", "Zilean"]},
    "wukong": {"adv": ["Darius", "Jayce", "Gnar"], "con": ["Malphite", "Garen", "Illaoi"], "syn": ["Yasuo", "Orianna", "Miss Fortune"]},

    # JUNGLE
    "amumu": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Kha'Zix", "Lee Sin"], "syn": ["Miss Fortune", "Kennen", "Orianna"]},
    "diana": {"adv": ["Evelynn", "Kha'Zix", "Rengar"], "con": ["Olaf", "Xin Zhao", "Jarvan IV"], "syn": ["Yasuo", "Orianna", "Malphite"]},
    "ekko": {"adv": ["Karthus", "Evelynn", "Nidalee"], "con": ["Lee Sin", "Kha'Zix", "Xin Zhao"], "syn": ["Vi", "Sejuani", "Leona"]},
    "evelynn": {"adv": ["Lee Sin", "Xin Zhao", "Nunu"], "con": ["Rengar", "Kha'Zix", "Rek'Sai"], "syn": ["Shen", "Galio", "Yuumi"]},
    "fiddlesticks": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Kennen", "Amumu", "Miss Fortune"]},
    "gragas": {"adv": ["Jax", "Master Yi", "Evelynn"], "con": ["Olaf", "Lee Sin", "Xin Zhao"], "syn": ["Yasuo", "Orianna", "Malphite"]},
    "graves": {"adv": ["Lee Sin", "Kha'Zix", "Xin Zhao"], "con": ["Evelynn", "Nidalee", "Elise"], "syn": ["Leona", "Nautilus", "Thresh"]},
    "hecarim": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yuumi", "Zilean", "Orianna"]},
    "jarvaniv": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Orianna", "Miss Fortune", "Gnar"]},
    "kayn": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yuumi", "Lulu", "Shen"]},
    "khazix": {"adv": ["Evelynn", "Nidalee", "Lee Sin"], "con": ["Rengar", "Vi", "Olaf"], "syn": ["Yuumi", "Shen", "Galio"]},
    "kindred": {"adv": ["Lee Sin", "Xin Zhao", "Nunu"], "con": ["Kha'Zix", "Rengar", "Evelynn"], "syn": ["Zilean", "Taric", "Lulu"]},
    "leesin": {"adv": ["Evelynn", "Nidalee", "Kha'Zix"], "con": ["Olaf", "Vi", "Rengar"], "syn": ["Orianna", "Yasuo", "Shen"]},
    "lillia": {"adv": ["Udyr", "Volibear", "Garen"], "con": ["Kha'Zix", "Rengar", "Lee Sin"], "syn": ["Yasuo", "Miss Fortune", "Kennen"]},
    "masteryi": {"adv": ["Evelynn", "Nidalee", "Lee Sin"], "con": ["Rammus", "Jax", "Vi"], "syn": ["Lulu", "Yuumi", "Kayle"]},
    "nidalee": {"adv": ["Lee Sin", "Xin Zhao", "Nunu"], "con": ["Kha'Zix", "Rengar", "Evelynn"], "syn": ["Jayce", "Renekton", "Elise"]},
    "nocturne": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Shen", "Galio", "Orianna"]},
    "nunu": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yasuo", "Katarina", "Kennen"]},
    "rammus": {"adv": ["Master Yi", "Yasuo", "Tryndamere"], "con": ["Evelynn", "Nidalee", "Lillia"], "syn": ["Taunt", "Shen", "Galio"]},
    "rengar": {"adv": ["Evelynn", "Nidalee", "Kha'Zix"], "con": ["Rammus", "Vi", "Olaf"], "syn": ["Ivern", "Shen", "Orianna"]},
    "shyvana": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Orianna", "Yasuo", "Lulu"]},
    "skarner": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Thresh", "Blitzcrank", "Orianna"]},
    "taliyah": {"adv": ["Evelynn", "Nidalee", "Lee Sin"], "con": ["Kha'Zix", "Rengar", "Olaf"], "syn": ["Pantheon", "Renekton", "Leona"]},
    "talon": {"adv": ["Evelynn", "Nidalee", "Lee Sin"], "con": ["Kha'Zix", "Rengar", "Olaf"], "syn": ["Yuumi", "Shen", "Galio"]},
    "vi": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yasuo", "Orianna", "Ahri"]},
    "viego": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Yuumi", "Lulu", "Shen"]},
    "xinzhao": {"adv": ["Master Yi", "Evelynn", "Rengar"], "con": ["Olaf", "Lee Sin", "Kha'Zix"], "syn": ["Lulu", "Yuumi", "Orianna"]},

    # MID LANE
    "ahri": {"adv": ["Akali", "Katarina", "LeBlanc"], "con": ["Yasuo", "Zed", "Fizz"], "syn": ["Vi", "Jarvan IV", "Sejuani"]},
    "akali": {"adv": ["Yasuo", "Zed", "Katarina"], "con": ["Galio", "Annie", "Lissandra"], "syn": ["Amumu", "Sejuani", "Leona"]},
    "akshan": {"adv": ["Kassadin", "Sylas", "Katarina"], "con": ["Yasuo", "Zed", "Irelia"], "syn": ["Nami", "Lulu", "Yuumi"]},
    "annie": {"adv": ["Yasuo", "Akali", "Katarina"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Amumu", "Malphite", "Jarvan IV"]},
    "aurelionsol": {"adv": ["Katarina", "Akali", "Talon"], "con": ["Zed", "Fizz", "Kassadin"], "syn": ["Leona", "Nautilus", "Thresh"]},
    "brand": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Fizz", "Zed", "Katarina"], "syn": ["Amumu", "Leona", "Nautilus"]},
    "corki": {"adv": ["Azir", "Orianna", "Viktor"], "con": ["Zed", "Talon", "Fizz"], "syn": ["Leona", "Nautilus", "Thresh"]},
    "diana": {"adv": ["Katarina", "Zed", "Talon"], "con": ["Galio", "Annie", "Lissandra"], "syn": ["Yasuo", "Orianna", "Malphite"]},
    "fizz": {"adv": ["Syndra", "Orianna", "Veigar"], "con": ["Galio", "Lissandra", "Kassadin"], "syn": ["Vi", "Jarvan IV", "Sejuani"]},
    "galio": {"adv": ["Katarina", "Akali", "Fizz"], "con": ["Lucian", "Tristana", "Jayce"], "syn": ["Camille", "Jarvan IV", "Vi"]},
    "gragas": {"adv": ["Yasuo", "Zed", "Talon"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Yasuo", "Orianna", "Malphite"]},
    "irelia": {"adv": ["Syndra", "Orianna", "Veigar"], "con": ["Renekton", "Garen", "Sett"], "syn": ["Sejuani", "Diana", "Wukong"]},
    "jayce": {"adv": ["Kassadin", "Katarina", "Akali"], "con": ["Irelia", "Yasuo", "Zed"], "syn": ["Nidalee", "Elise", "Zoe"]},
    "kassadin": {"adv": ["Katarina", "Akali", "Fizz"], "con": ["Lucian", "Tristana", "Talon"], "syn": ["Vi", "Jarvan IV", "Sejuani"]},
    "katarina": {"adv": ["Veigar", "Lux", "Vel'Koz"], "con": ["Galio", "Annie", "Lissandra"], "syn": ["Amumu", "Malphite", "Nautilus"]},
    "kayle": {"adv": ["Kassadin", "Galio", "Vladimir"], "con": ["Zed", "Talon", "Fizz"], "syn": ["Master Yi", "Lulu", "Nunu"]},
    "lucian": {"adv": ["Kassadin", "Katarina", "Akali"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Nami", "Braum", "Leona"]},
    "morgana": {"adv": ["Ahri", "Lux", "Syndra"], "con": ["Talon", "Zed", "Fizz"], "syn": ["Caitlyn", "Jhin", "Ashe"]},
    "orianna": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Zed", "Fizz", "Katarina"], "syn": ["Malphite", "Jarvan IV", "Wukong"]},
    "seraphine": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Zed", "Fizz", "Katarina"], "syn": ["Ashe", "Miss Fortune", "Sona"]},
    "swain": {"adv": ["Yasuo", "Katarina", "Akali"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Jhin", "Caitlyn", "Ashe"]},
    "syndra": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Fizz", "Zed", "Katarina"], "syn": ["Elise", "Lee Sin", "Nidalee"]},
    "twistedfate": {"adv": ["Katarina", "Akali", "Talon"], "con": ["Fizz", "Zed", "Yasuo"], "syn": ["Nocturne", "Shen", "Pantheon"]},
    "veigar": {"adv": ["Kassadin", "Vladimir", "Ryze"], "con": ["Katarina", "Fizz", "Zed"], "syn": ["Thresh", "Blitzcrank", "Nautilus"]},
    "vex": {"adv": ["Yasuo", "Irelia", "Katarina"], "con": ["Syndra", "Orianna", "Xerath"], "syn": ["Amumu", "Malphite", "Nautilus"]},
    "vladimir": {"adv": ["Zed", "Talon", "Fizz"], "con": ["Kassadin", "Malzahar", "Anivia"], "syn": ["Amumu", "Sejuani", "Leona"]},
    "yasuo": {"adv": ["Gnar", "Ahri", "Syndra"], "con": ["Renekton", "Annie", "Malzahar"], "syn": ["Malphite", "Diana", "Alistar"]},
    "yone": {"adv": ["Zoe", "Lux", "Syndra"], "con": ["Renekton", "Annie", "Malzahar"], "syn": ["Malphite", "Diana", "Alistar"]},
    "zed": {"adv": ["Veigar", "Lux", "Vel'Koz"], "con": ["Lissandra", "Malzahar", "Zilean"], "syn": ["Vi", "Jarvan IV", "Sejuani"]},
    "ziggs": {"adv": ["Annie", "Malzahar", "Veigar"], "con": ["Fizz", "Zed", "Katarina"], "syn": ["Leona", "Nautilus", "Thresh"]},
    "zoe": {"adv": ["Azir", "Orianna", "Viktor"], "con": ["Zed", "Talon", "Fizz"], "syn": ["Ezreal", "Jhin", "Ashe"]},

    # DRAGON LANE (ADC)
    "ashe": {"adv": ["Vayne", "Kai'Sa", "Xayah"], "con": ["Draven", "Lucian", "Tristana"], "syn": ["Braum", "Thresh", "Seraphine"]},
    "caitlyn": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Sivir", "Jhin", "Ashe"], "syn": ["Morgana", "Lux", "Karma"]},
    "draven": {"adv": ["Vayne", "Ezreal", "Kai'Sa"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Thresh", "Leona", "Nautilus"]},
    "ezreal": {"adv": ["Lucian", "Jinx", "Tristana"], "con": ["Vayne", "Draven", "Caitlyn"], "syn": ["Karma", "Yuumi", "Sona"]},
    "jhin": {"adv": ["Caitlyn", "Ashe", "Varus"], "con": ["Vayne", "Draven", "Lucian"], "syn": ["Nami", "Zyra", "Swain"]},
    "jinx": {"adv": ["Vayne", "Ezreal", "Kai'Sa"], "con": ["Draven", "Lucian", "Tristana"], "syn": ["Lulu", "Janna", "Thresh"]},
    "kaisa": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Nautilus", "Leona", "Alistar"]},
    "lucian": {"adv": ["Vayne", "Ezreal", "Kai'Sa"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Braum", "Nami", "Thresh"]},
    "missfortune": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Leona", "Amumu", "Nautilus"]},
    "nilah": {"adv": ["Samira", "Vayne", "Kai'Sa"], "con": ["Caitlyn", "Ashe", "Xayah"], "syn": ["Taric", "Rakan", "Soraka"]},
    "samira": {"adv": ["Vayne", "Ezreal", "Kai'Sa"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Nautilus", "Leona", "Rell"]},
    "tristana": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Lulu", "Thresh", "Nautilus"]},
    "twitch": {"adv": ["Vayne", "Ezreal", "Kai'Sa"], "con": ["Draven", "Lucian", "Tristana"], "syn": ["Lulu", "Yuumi", "Janna"]},
    "varus": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Thresh", "Leona", "Nautilus"]},
    "vayne": {"adv": ["Ezreal", "Lucian", "Sivir"], "con": ["Caitlyn", "Ashe", "Draven"], "syn": ["Lulu", "Janna", "Nami"]},
    "xayah": {"adv": ["Vayne", "Kai'Sa", "Samira"], "con": ["Caitlyn", "Ashe", "Varus"], "syn": ["Rakan", "Thresh", "Nautilus"]},
    "zeri": {"adv": ["Vayne", "Ezreal", "Lucian"], "con": ["Draven", "Tristana", "Samira"], "syn": ["Yuumi", "Lulu", "Janna"]},

    # SUPPORT
    "alistar": {"adv": ["Leona", "Nautilus", "Blitzcrank"], "con": ["Janna", "Lulu", "Morgana"], "syn": ["Yasuo", "Samira", "Tristana"]},
    "annie": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Nautilus", "Blitzcrank"], "syn": ["Jhin", "Miss Fortune", "Ashe"]},
    "blitzcrank": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Braum"], "syn": ["Jinx", "Draven", "Caitlyn"]},
    "braum": {"adv": ["Leona", "Nautilus", "Blitzcrank"], "con": ["Lulu", "Janna", "Nami"], "syn": ["Lucian", "Ashe", "Ezreal"]},
    "camille": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Braum"], "syn": ["Jhin", "Miss Fortune", "Ashe"]},
    "galio": {"adv": ["Leona", "Nautilus", "Blitzcrank"], "con": ["Janna", "Lulu", "Morgana"], "syn": ["Yasuo", "Samira", "Tristana"]},
    "janna": {"adv": ["Leona", "Alistar", "Rakan"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Jinx", "Vayne", "Tristana"]},
    "karma": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Ezreal", "Caitlyn", "Ashe"]},
    "leona": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Alistar", "Braum", "Janna"], "syn": ["Samira", "Miss Fortune", "Jhin"]},
    "lulu": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Jinx", "Vayne", "Tristana"]},
    "lux": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Caitlyn", "Ezreal", "Ashe"]},
    "malphite": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Braum"], "syn": ["Yasuo", "Miss Fortune", "Orianna"]},
    "morgana": {"adv": ["Leona", "Nautilus", "Blitzcrank"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Caitlyn", "Jhin", "Ashe"]},
    "nami": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Janna"], "syn": ["Lucian", "Jhin", "Vayne"]},
    "nautilus": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Alistar", "Braum", "Morgana"], "syn": ["Samira", "Kai'Sa", "Jhin"]},
    "pyke": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Braum"], "syn": ["Draven", "Jhin", "Samira"]},
    "rakan": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Leona", "Alistar", "Janna"], "syn": ["Xayah", "Yasuo", "Miss Fortune"]},
    "senna": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Lucian", "Jhin", "Tahm Kench"]},
    "seraphine": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Sona", "Soraka", "Nami"], "syn": ["Ashe", "Miss Fortune", "Caitlyn"]},
    "sona": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Ashe", "Miss Fortune", "Ezreal"]},
    "soraka": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Vayne", "Jinx", "Tristana"]},
    "thresh": {"adv": ["Sona", "Soraka", "Nami"], "con": ["Morgana", "Zyra", "Brand"], "syn": ["Aphelios", "Jinx", "Draven"]},
    "yuumi": {"adv": ["Leona", "Alistar", "Braum"], "con": ["Blitzcrank", "Nautilus", "Pyke"], "syn": ["Zeri", "Ezreal", "Twitch"]}
}

def format_list(lst):
    return 'listOf(' + ', '.join(f'"{x}"' for x in lst) + ')'

files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt"
]

default_adv = format_list(["Yasuo", "Teemo", "Master Yi"])
default_con = format_list(["Darius", "Zed", "Lee Sin"])
default_syn = format_list(["Malphite", "Amumu", "Orianna"])

for file_path in files:
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
    
    chunks = content.split('Champion(')
    res = [chunks[0]]
    for chunk in chunks[1:]:
        id_m = re.search(r'id\s*=\s*"([^"]+)"', chunk)
        if id_m:
            champ_id = id_m.group(1).lower()
            if champ_id in matchups:
                adv = format_list(matchups[champ_id]["adv"])
                con = format_list(matchups[champ_id]["con"])
                syn = format_list(matchups[champ_id]["syn"])
            else:
                adv = default_adv
                con = default_con
                syn = default_syn
            
            chunk = re.sub(r'advantageAgainst\s*=\s*listOf\([^)]*\)', f'advantageAgainst = {adv}', chunk)
            chunk = re.sub(r'counteredBy\s*=\s*listOf\([^)]*\)', f'counteredBy = {con}', chunk)
            chunk = re.sub(r'synergies\s*=\s*listOf\([^)]*\)', f'synergies = {syn}', chunk)
            
        res.append('Champion(' + chunk)
        
    with open(file_path, "w", encoding="utf-8") as f:
        f.write("".join(res))

print("Champions updated successfully!")
