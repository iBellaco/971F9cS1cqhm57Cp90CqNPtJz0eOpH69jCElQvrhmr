import urllib.request

postimg_urls = [
    ("Botas de Velocidad", "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp"),
    ("Espada Larga", "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp"),
    ("Guantes de Peleador", "https://i.postimg.cc/QBJq8zsr/1753390558-brawlers-gloves.webp"),
    ("Daga", "https://i.postimg.cc/CZgNV0y5/1753390550-dagger.webp"),
    ("Chispa Reluciente", "https://i.postimg.cc/bGhTjPXv/1753390582-shimmering-spark.webp"),
    ("Lágrima de la Diosa", "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png"),
    ("Tomo Amplificador", "https://i.postimg.cc/qtTLdrfM/1753390572-amplifying-tome.webp"),
    ("Cristal de Rubí", "https://i.postimg.cc/rdkJLT6y/1753390626-ruby-crystal.webp"),
    ("Armadura de Tela", "https://i.postimg.cc/3yTBHhM8/1753390581-cloth-armor.webp"),
    ("Manto Anulamagia", "https://i.postimg.cc/4Kg5TGCJ/1753390606-null-magic-mantle.webp"),
    ("Anillo de Revelación", "https://i.postimg.cc/wtpVdzKH/1753390605-ring-of-revelation.webp"),
    ("Escudo Reliquia", "https://i.postimg.cc/sBrcRzFs/1753390612-relic-shield.webp"),
    ("Hoz Espectral", "https://i.postimg.cc/2qDwfYpY/1753390656-spectral-sickle.webp")
]

for name, url in postimg_urls:
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'okhttp/4.12.0'})
        with urllib.request.urlopen(req, timeout=5) as r:
            print(f"OK: {name} -> size={len(r.read())}")
    except Exception as e:
        print(f"FAIL: {name} -> {e}")
