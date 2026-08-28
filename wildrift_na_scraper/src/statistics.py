import pandas as pd
import sqlite3
import os
from datetime import datetime
from .database import get_db_connection
from .logger import logger

def calculate_champion_stats():
    logger.info("Calculating champion statistics...")
    conn = get_db_connection()
    
    # Load player champion data
    query = "SELECT * FROM player_champions"
    df = pd.read_sql_query(query, conn)
    
    if df.empty:
        logger.warning("No data found to calculate statistics.")
        conn.close()
        return
        
    # Total games per position
    pos_games = df.groupby('position')['games'].sum().to_dict()
    total_games = df['games'].sum()
    total_players = df['player_id'].nunique()
    
    # Calculate stats per champion and position
    stats = []
    
    grouped = df.groupby(['champion_id', 'champion_name', 'position'])
    
    for (c_id, c_name, pos), group in grouped:
        games = group['games'].sum()
        wins = group['wins'].sum()
        losses = group['losses'].sum()
        win_rate = (wins / games * 100) if games > 0 else 0
        
        # Pick rate based on total games in that position, or overall if position is UNKNOWN
        base_games = pos_games.get(pos, total_games)
        pick_rate = (games / base_games * 100) if base_games > 0 else 0
        
        stat = {
            'region': 'NA',
            'champion_id': c_id,
            'champion_name': c_name,
            'position': pos,
            'rank_filter': 'ALL',
            'games': int(games),
            'wins': int(wins),
            'losses': int(losses),
            'win_rate': round(win_rate, 2),
            'pick_rate': round(pick_rate, 2),
            'ban_rate': None, # No ban data in player profiles
            'sample_size': int(games),
            'players_sampled': int(total_players),
            'patch': 'AUTO-DETECTED', # Would be extracted dynamically in a full run
            'collected_at': datetime.now().isoformat()
        }
        stats.append(stat)
        
    # Save to database
    cursor = conn.cursor()
    for s in stats:
        cursor.execute('''
            INSERT OR REPLACE INTO champion_stats 
            (region, champion_id, champion_name, position, rank_filter, games, wins, losses, win_rate, pick_rate, ban_rate, sample_size, players_sampled, patch, collected_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ''', (s['region'], s['champion_id'], s['champion_name'], s['position'], s['rank_filter'], s['games'], s['wins'], s['losses'], s['win_rate'], s['pick_rate'], s['ban_rate'], s['sample_size'], s['players_sampled'], s['patch'], s['collected_at']))
        
    conn.commit()
    conn.close()
    
    logger.info(f"Calculated statistics for {len(stats)} champion/position combinations.")
    return stats

def export_data():
    os.makedirs('data', exist_ok=True)
    conn = get_db_connection()
    
    # Export players
    df_players = pd.read_sql_query("SELECT * FROM players", conn)
    df_players.to_json('data/na_players.json', orient='records', indent=2)
    
    # Export stats
    df_stats = pd.read_sql_query("SELECT * FROM champion_stats", conn)
    if not df_stats.empty:
        df_stats.to_json('data/na_champion_stats.json', orient='records', indent=2)
        df_stats.to_csv('data/na_champion_stats.csv', index=False)
        try:
            df_stats.to_excel('data/na_champion_stats.xlsx', index=False)
        except Exception as e:
            logger.warning(f"Could not export to Excel: {e}")
            
    conn.close()
    logger.info("Data exported successfully.")
