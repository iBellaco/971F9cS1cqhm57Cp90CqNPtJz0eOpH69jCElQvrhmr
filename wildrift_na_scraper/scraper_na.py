import json
import os
import time
import argparse
from datetime import datetime
from src.database import init_db, get_db_connection
from src.leaderboard import scrape_leaderboard
from src.profiles import scrape_profile
from src.statistics import calculate_champion_stats, export_data
from src.logger import logger
from playwright.sync_api import sync_playwright

def load_config():
    with open('config.json', 'r') as f:
        return json.load(f)

def load_checkpoint():
    if os.path.exists('checkpoint.json'):
        with open('checkpoint.json', 'r') as f:
            return json.load(f)
    return {"last_player": 0, "total_players": 0, "completed": 0}

def save_checkpoint(data):
    with open('checkpoint.json', 'w') as f:
        json.dump(data, f, indent=2)

def main():
    parser = argparse.ArgumentParser(description='Wild Rift NA/CN Scraper')
    parser.add_argument('--headed', action='store_true', help='Run in headed mode')
    parser.add_argument('--reset', action='store_true', help='Reset checkpoint and start from scratch')
    parser.add_argument('--region', type=str, choices=['NA', 'CN'], default='NA', help='Select region to scrape (NA or CN)')
    args = parser.parse_args()
    
    config = load_config()
    if args.headed:
        config['headless'] = False
    
    # Set the active region based on user input
    config['active_region'] = args.region
    config['base_url'] = config['urls'].get(args.region, config['urls']['NA'])
        
    init_db()
    
    if args.reset and os.path.exists('checkpoint.json'):
        os.remove('checkpoint.json')
        logger.info("Resetting checkpoint.")
        
    checkpoint = load_checkpoint()
    
    # 1. Scrape Leaderboard if needed
    players = scrape_leaderboard(config)
    
    # Save players to DB
    conn = get_db_connection()
    cursor = conn.cursor()
    for p in players:
        cursor.execute('''
            INSERT OR REPLACE INTO players 
            (player_id, player_name, tag, rank, tier, marks, server, region, profile_url, last_updated)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ''', (p['player_id'], p['player_name'], p['tag'], p['rank'], p['tier'], p['marks'], p['server'], p['region'], p['profile_url'], p['last_updated']))
    conn.commit()
    
    # Load players to process
    cursor.execute("SELECT * FROM players LIMIT 50") # Limit for demonstration/safety
    db_players = cursor.fetchall()
    
    total = len(db_players)
    checkpoint['total_players'] = total
    start_idx = checkpoint['completed']
    
    logger.info(f"Starting profile scraping from index {start_idx}/{total}")
    
    # 2. Scrape Profiles
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=config['headless'])
        context = browser.new_context(user_agent="Mozilla/5.0")
        page = context.new_page()
        
        for i in range(start_idx, total):
            player = db_players[i]
            try:
                champs = scrape_profile(player, config, page)
                
                for c in champs:
                    cursor.execute('''
                        INSERT INTO player_champions 
                        (player_id, champion_id, champion_name, games, wins, losses, win_rate, position, kills, deaths, assists, kda, mvp, rating, collected_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    ''', (c['player_id'], c['champion_id'], c['champion_name'], c['games'], c['wins'], c['losses'], c['win_rate'], c['position'], c['kills'], c['deaths'], c['assists'], c['kda'], c['mvp'], c['rating'], c['collected_at']))
                conn.commit()
                
                checkpoint['completed'] = i + 1
                checkpoint['last_player'] = player['player_id']
                save_checkpoint(checkpoint)
                
                time.sleep(config.get("delay_min_seconds", 2))
                
            except Exception as e:
                logger.error(f"Error processing player {player['player_name']}: {e}")
                
        browser.close()
        
    conn.close()
    
    # 3. Calculate and Export
    calculate_champion_stats()
    export_data()
    
    logger.info("Scraping completed successfully.")

if __name__ == "__main__":
    main()
