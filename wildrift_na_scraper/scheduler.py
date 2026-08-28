import time
import json
import subprocess
from datetime import datetime
from src.logger import logger

def load_config():
    with open('config.json', 'r') as f:
        return json.load(f)

def run_scraper():
    logger.info("Starting scheduled scraper run...")
    try:
        subprocess.run(["python", "scraper_na.py"], check=True)
        logger.info("Scraper run completed.")
    except subprocess.CalledProcessError as e:
        logger.error(f"Scraper run failed with code {e.returncode}")
    except Exception as e:
        logger.error(f"Error running scraper: {e}")

def main():
    logger.info("Starting Wild Rift NA Scraper Scheduler...")
    
    while True:
        config = load_config()
        interval_hours = config.get("schedule_interval_hours", 24)
        
        run_scraper()
        
        sleep_seconds = interval_hours * 3600
        logger.info(f"Sleeping for {interval_hours} hours. Next run at {datetime.fromtimestamp(time.time() + sleep_seconds)}")
        time.sleep(sleep_seconds)

if __name__ == "__main__":
    main()
