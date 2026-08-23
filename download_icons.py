import requests
from bs4 import BeautifulSoup
import os
import urllib.request
import re

url = "https://postimg.cc/gallery/CgjQzK5"
headers = {'User-Agent': 'Mozilla/5.0'}
res = requests.get(url, headers=headers)
soup = BeautifulSoup(res.text, 'html.parser')

os.makedirs('app/src/main/res/drawable', exist_ok=True)

# Postimg gallery contains links to individual image pages, which contain the direct download links
for a in soup.select('.gallery a'):
    page_url = a['href']
    res2 = requests.get(page_url, headers=headers)
    soup2 = BeautifulSoup(res2.text, 'html.parser')
    # Find the download button or main image
    img = soup2.select_one('#main-image')
    if img and img.has_attr('src'):
        img_url = img['src']
        # extract name from the url or page
        # maybe we can just get the filename from the title
        title = soup2.title.text.lower() if soup2.title else "icon"
        print(f"Found image: {title} - {img_url}")
        
        # We need to map them properly. Let's just download them all into temp and then we'll map them.
        filename = img_url.split('/')[-1]
        urllib.request.urlretrieve(img_url, f"tmp_{filename}")

print("Done")
