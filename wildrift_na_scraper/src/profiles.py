import json
import time
from datetime import datetime
from playwright.sync_api import sync_playwright, Page, TimeoutError
from .logger import logger
from .normalizer import normalize_position

def scrape_profile(player, config: dict, page: Page):
    url = player.get('profile_url')
    if not url:
        return []
        
    logger.info(f"Scraping profile: {url}")
    champions = []
    
    try:
        page.goto(url, wait_until="networkidle", timeout=30000)
        
        if "cloudflare" in page.content().lower() or "captcha" in page.content().lower():
            logger.warning("Bot protection detected. Waiting longer...")
            page.wait_for_timeout(10000)
            
        content = page.content()
        if '__NEXT_DATA__' in content:
            start = content.find('__NEXT_DATA__')
            s_tag = content.find('>', start) + 1
            e_tag = content.find('</script>', s_tag)
            
            try:
                next_data = json.loads(content[s_tag:e_tag])
                page_props = next_data.get('props', {}).get('pageProps', {})
                
                # Assuming champion stats are in user.champions or similar
                user_data = page_props.get('user', {})
                champ_stats = user_data.get('champions', [])
                
                for cs in champ_stats:
                    games = cs.get('games', 0)
                    wins = cs.get('wins', 0)
                    if games > 0:
                        champions.append({
                            'player_id': player.get('player_id'),
                            'champion_id': cs.get('championId', 0),
                            'champion_name': cs.get('championName', 'Unknown'),
                            'games': games,
                            'wins': wins,
                            'losses': games - wins,
                            'win_rate': (wins / games) * 100 if games > 0 else 0,
                            'position': normalize_position(cs.get('lane', 'UNKNOWN')),
                            'kills': cs.get('kills', 0),
                            'deaths': cs.get('deaths', 0),
                            'assists': cs.get('assists', 0),
                            'kda': cs.get('kda', 0),
                            'mvp': cs.get('mvp', 0),
                            'rating': cs.get('rating', 0),
                            'collected_at': datetime.now().isoformat()
                        })
            except Exception as e:
                logger.error(f"Error parsing profile {url}: {e}")
                
        else:
            logger.debug(f"No __NEXT_DATA__ found for {url}, falling back to DOM")
            # Implement DOM extraction if needed
            
    except TimeoutError:
        logger.error(f"Timeout on profile {url}")
    except Exception as e:
        logger.error(f"Error on profile {url}: {e}")
        
    return champions
