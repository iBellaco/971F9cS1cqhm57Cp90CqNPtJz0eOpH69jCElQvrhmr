import json
import time
from playwright.sync_api import sync_playwright, Page, TimeoutError as PlaywrightTimeoutError
from .logger import logger
from .database import get_db_connection

def scrape_leaderboard(config: dict):
    base_url = config.get("base_url", "https://wildriftstats.org")
    headless = config.get("headless", True)
    
    players = []
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=headless)
        context = browser.new_context(
            user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
        )
        page = context.new_page()
        
        page_num = 1
        max_pages = 5  # For safety, adjust as needed or auto-detect
        
        while page_num <= max_pages:
            url = f"{base_url}/leaderboard?page={page_num}&region=NA"
            logger.info(f"Scraping leaderboard page {page_num}: {url}")
            try:
                page.goto(url, wait_until="networkidle", timeout=30000)
                
                # Check for anti-bot
                if "cloudflare" in page.content().lower() or "captcha" in page.content().lower() or "checking your browser" in page.content().lower():
                    logger.warning("Bot protection detected. Waiting longer...")
                    page.wait_for_timeout(10000)
                
                # Extract data from NEXT_DATA
                content = page.content()
                if '__NEXT_DATA__' in content:
                    start = content.find('__NEXT_DATA__')
                    s_tag = content.find('>', start) + 1
                    e_tag = content.find('</script>', s_tag)
                    
                    try:
                        next_data = json.loads(content[s_tag:e_tag])
                        page_props = next_data.get('props', {}).get('pageProps', {})
                        
                        # Just getting players from page props if they exist
                        # Assuming structure based on standard Next.js apps
                        users = page_props.get('users', [])
                        if not users:
                            # Fallback: check DOM
                            logger.info("No users in NEXT_DATA, falling back to DOM scraping")
                            users = extract_from_dom(page)
                            
                        if not users:
                            logger.warning(f"No users found on page {page_num}")
                            break
                            
                        for user in users:
                            # Process and standardize user
                            p_data = {
                                'player_id': str(user.get('id', user.get('name', ''))),
                                'player_name': user.get('name', 'Unknown'),
                                'tag': user.get('tag', ''),
                                'rank': user.get('rank', 'UNRANKED'),
                                'tier': user.get('tier', ''),
                                'marks': user.get('marks', 0),
                                'server': user.get('server', 'NA'),
                                'region': 'NA',
                                'profile_url': f"{base_url}/profile/{user.get('name', '')}-{user.get('tag', '')}",
                                'last_updated': ''
                            }
                            players.append(p_data)
                            
                        logger.info(f"Found {len(users)} players on page {page_num}")
                        
                        # Stop if less than expected items per page
                        if len(users) < 10: 
                            break
                            
                    except json.JSONDecodeError:
                        logger.error("Failed to parse __NEXT_DATA__")
                else:
                    logger.info("__NEXT_DATA__ not found. Attempting DOM scraping.")
                    dom_users = extract_from_dom(page)
                    if not dom_users:
                        break
                    players.extend(dom_users)
                
            except PlaywrightTimeoutError:
                logger.error(f"Timeout on page {page_num}")
                break
            except Exception as e:
                logger.error(f"Error on page {page_num}: {e}")
                break
                
            page_num += 1
            time.sleep(config.get("delay_min_seconds", 2))
            
        browser.close()
        
    return players

def extract_from_dom(page: Page):
    # This is a fallback dummy extractor. In a real scenario, we would inspect the actual DOM classes.
    # wildriftstats.org uses a table structure usually.
    users = []
    try:
        rows = page.locator("table tbody tr").all()
        for row in rows:
            cols = row.locator("td").all_inner_texts()
            if len(cols) >= 3:
                name_tag = cols[1].split('#')
                name = name_tag[0].strip()
                tag = name_tag[1].strip() if len(name_tag) > 1 else ''
                rank = cols[2].strip()
                users.append({
                    'id': f"{name}-{tag}",
                    'name': name,
                    'tag': tag,
                    'rank': rank,
                    'tier': '',
                    'server': 'NA'
                })
    except Exception as e:
        logger.error(f"DOM extraction error: {e}")
    return users
