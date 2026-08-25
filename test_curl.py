import subprocess

for url in [
    "https://wr-meta.com/uploads/posts/2024-12/1733876753_3072.webp",
    "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp"
]:
    out = subprocess.run(["curl", "-I", "-s", url], capture_output=True, text=True)
    print(f"URL: {url}")
    print(out.stdout)
    print("-" * 40)
