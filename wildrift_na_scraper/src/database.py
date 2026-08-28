import sqlite3
import os
from .logger import logger

DB_PATH = 'wildrift_na.db'

def init_db():
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS players (
            player_id TEXT PRIMARY KEY,
            player_name TEXT,
            tag TEXT,
            rank TEXT,
            tier TEXT,
            marks INTEGER,
            server TEXT,
            region TEXT,
            profile_url TEXT,
            last_updated TEXT
        )
    ''')
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS champions (
            champion_id INTEGER PRIMARY KEY,
            champion_name TEXT
        )
    ''')
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS champion_stats (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            region TEXT,
            champion_id INTEGER,
            champion_name TEXT,
            position TEXT,
            rank_filter TEXT,
            games INTEGER,
            wins INTEGER,
            losses INTEGER,
            win_rate REAL,
            pick_rate REAL,
            ban_rate REAL,
            sample_size INTEGER,
            players_sampled INTEGER,
            patch TEXT,
            collected_at TEXT,
            UNIQUE(region, champion_id, position, rank_filter, patch, collected_at)
        )
    ''')
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS player_champions (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            player_id TEXT,
            champion_id INTEGER,
            champion_name TEXT,
            games INTEGER,
            wins INTEGER,
            losses INTEGER,
            win_rate REAL,
            position TEXT,
            kills REAL,
            deaths REAL,
            assists REAL,
            kda REAL,
            mvp INTEGER,
            rating REAL,
            collected_at TEXT
        )
    ''')
    
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS scrape_runs (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            started_at TEXT,
            ended_at TEXT,
            status TEXT,
            players_processed INTEGER,
            errors INTEGER
        )
    ''')
    
    # Indexes
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_region ON champion_stats (region)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_champ_id ON champion_stats (champion_id)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_position ON champion_stats (position)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_patch ON champion_stats (patch)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_collected_at ON champion_stats (collected_at)')
    cursor.execute('CREATE INDEX IF NOT EXISTS idx_player_id ON player_champions (player_id)')
    
    conn.commit()
    conn.close()
    logger.info("Database initialized successfully.")

def get_db_connection():
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    return conn
