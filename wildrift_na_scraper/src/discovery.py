import json
import os
from playwright.sync_api import sync_playwright, Page
from .logger import logger

def save_diagnostics(page: Page, url: str, reason: str):
    os.makedirs('diagnostics', exist_ok=True)
    try:
        html = page.content()
        with open('diagnostics/page.html', 'w', encoding='utf-8') as f:
            f.write(html)
        page.screenshot(path='diagnostics/screenshot.png')
        with open('diagnostics/errors.log', 'a', encoding='utf-8') as f:
            f.write(f"Diagnostic saved for {url} due to: {reason}\n")
        logger.warning(f"Saved diagnostics for {url}")
    except Exception as e:
        logger.error(f"Failed to save diagnostics: {e}")

def discover_network(url: str, headless: bool = True):
    requests_data = []
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=headless)
        page = browser.new_page()
        
        def handle_request(route, request):
            requests_data.append({
                "method": request.method,
                "url": request.url,
                "headers": request.headers
            })
            route.continue_()
            
        def handle_response(response):
            if "api" in response.url or "json" in response.headers.get("content-type", ""):
                try:
                    status = response.status
                    requests_data.append({
                        "response_url": response.url,
                        "status": status,
                        "content_type": response.headers.get("content-type", "")
                    })
                except:
                    pass

        page.route("**/*", handle_request)
        page.on("response", handle_response)
        
        try:
            logger.info(f"Discovering network for {url}")
            page.goto(url, wait_until="networkidle", timeout=30000)
            
            os.makedirs('diagnostics', exist_ok=True)
            with open('diagnostics/network_requests.json', 'w', encoding='utf-8') as f:
                json.dump(requests_data, f, indent=2)
                
            logger.info(f"Saved {len(requests_data)} network events to diagnostics/network_requests.json")
            
        except Exception as e:
            logger.error(f"Discovery error: {e}")
            save_diagnostics(page, url, str(e))
        finally:
            browser.close()
