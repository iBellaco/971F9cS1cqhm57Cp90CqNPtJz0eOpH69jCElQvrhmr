from fastapi import FastAPI, Query, HTTPException
from fastapi.responses import JSONResponse
import sqlite3
from typing import List, Optional

app = FastAPI(title="Wild Rift NA API")

DB_PATH = 'wildrift_na.db'

def get_db():
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    return conn

@app.get("/health")
def health_check():
    return {"status": "ok"}

@app.get("/stats")
def get_stats(
    region: str = Query("NA", description="Region filter"),
    position: Optional[str] = Query(None, description="Position filter (e.g., JUNGLE)"),
    rank: Optional[str] = Query(None, description="Rank filter (e.g., DIAMOND+)")
):
    conn = get_db()
    cursor = conn.cursor()
    
    query = "SELECT * FROM champion_stats WHERE region = ?"
    params = [region.upper()]
    
    if position:
        query += " AND position = ?"
        params.append(position.upper())
        
    if rank:
        query += " AND rank_filter = ?"
        params.append(rank.upper())
        
    cursor.execute(query, params)
    rows = cursor.fetchall()
    conn.close()
    
    return [dict(row) for row in rows]

@app.get("/stats/{champion}")
def get_champion_stats(champion: str, region: str = "NA"):
    conn = get_db()
    cursor = conn.cursor()
    
    query = "SELECT * FROM champion_stats WHERE region = ? AND champion_name COLLATE NOCASE = ?"
    cursor.execute(query, (region.upper(), champion))
    rows = cursor.fetchall()
    conn.close()
    
    if not rows:
        raise HTTPException(status_code=404, detail="Champion not found")
        
    return [dict(row) for row in rows]

@app.get("/champions")
def get_champions():
    conn = get_db()
    cursor = conn.cursor()
    cursor.execute("SELECT DISTINCT champion_name FROM champion_stats ORDER BY champion_name")
    rows = cursor.fetchall()
    conn.close()
    return [row['champion_name'] for row in rows]

@app.get("/players")
def get_players(limit: int = 100):
    conn = get_db()
    cursor = conn.cursor()
    cursor.execute("SELECT player_id, player_name, tag, rank, tier, server FROM players LIMIT ?", (limit,))
    rows = cursor.fetchall()
    conn.close()
    return [dict(row) for row in rows]

@app.get("/positions")
def get_positions():
    return ["BARON", "JUNGLE", "MID", "DRAGON", "SUPPORT", "UNKNOWN"]

@app.get("/ranks")
def get_ranks():
    return ["ALL", "DIAMOND+", "MASTER+", "GRANDMASTER+", "CHALLENGER+"]
