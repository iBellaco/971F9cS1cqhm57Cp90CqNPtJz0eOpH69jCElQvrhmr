import os
import re
import urllib.request
import hashlib

def main():
    asset_dir = 'app/src/main/assets/offline_images'
    os.makedirs(asset_dir, exist_ok=True)
    
    java_dir = 'app/src/main/java'
    
    url_pattern = re.compile(r'(https://[^"\']+\.(?:png|jpg|jpeg|webp)[^"\']*)')
    
    urls_to_replace = {}
    
    for root, dirs, files in os.walk(java_dir):
        for file in files:
            if file.endswith('.kt'):
                filepath = os.path.join(root, file)
                with open(filepath, 'r') as f:
                    content = f.read()
                
                urls = url_pattern.findall(content)
                for url in urls:
                    if url not in urls_to_replace:
                        ext = 'png'
                        if '.webp' in url: ext = 'webp'
                        elif '.jpg' in url: ext = 'jpg'
                        elif '.jpeg' in url: ext = 'jpeg'
                        
                        hash_name = hashlib.md5(url.encode('utf-8')).hexdigest() + '.' + ext
                        urls_to_replace[url] = hash_name

    print(f"Found {len(urls_to_replace)} unique URLs to download.")
    for url, filename in urls_to_replace.items():
        local_path = os.path.join(asset_dir, filename)
        if not os.path.exists(local_path):
            try:
                req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
                with urllib.request.urlopen(req, timeout=10) as response, open(local_path, 'wb') as out_file:
                    out_file.write(response.read())
            except Exception as e:
                print(f"Failed to download {url}: {e}")

    for root, dirs, files in os.walk(java_dir):
        for file in files:
            if file.endswith('.kt'):
                filepath = os.path.join(root, file)
                with open(filepath, 'r') as f:
                    content = f.read()
                
                new_content = content
                for url, filename in urls_to_replace.items():
                    local_url = f"file:///android_asset/offline_images/{filename}"
                    new_content = new_content.replace(url, local_url)
                
                if new_content != content:
                    with open(filepath, 'w') as f:
                        f.write(new_content)
                    print(f"Updated {filepath}")

if __name__ == '__main__':
    main()
