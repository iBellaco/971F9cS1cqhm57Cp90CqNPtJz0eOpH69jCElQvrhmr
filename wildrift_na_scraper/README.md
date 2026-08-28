# Wild Rift NA Scraper

Scraper automatizado y API para recolectar estadísticas reales de jugadores de League of Legends: Wild Rift en la región de Norteamérica (NA).

## Funcionalidades
- Scraping automatizado del Leaderboard y Perfiles de Jugadores.
- Bypassing inicial de protecciones anti-bot mediante Playwright (navegador real).
- Manejo de paginación y recolección de datos desde estado interno (`__NEXT_DATA__`) de React/Next.js o DOM.
- Cálculo de estadísticas: Win Rate, Pick Rate.
- Exportación automática a SQLite, JSON, CSV y Excel.
- API local en FastAPI.
- Sistema de *Checkpoint* para reanudar el scraping en caso de error.

## Requisitos
- Python 3.9+
- Chromium (instalado vía Playwright)

## Instalación

1. **Crear un entorno virtual:**
```bash
python -m venv venv
```

2. **Activar el entorno virtual:**
- En Windows: `venv\Scripts\activate`
- En Linux/Mac: `source venv/bin/activate`

3. **Instalar dependencias:**
```bash
pip install -r requirements.txt
```

4. **Instalar Chromium para Playwright:**
```bash
playwright install chromium
```

5. **Configurar `config.json`:**
Revisa el archivo y ajusta los tiempos de retraso, modo headless, etc.

## Uso

### Ejecutar el scraper
```bash
# Modo oculto (por defecto)
python scraper_na.py

# Ver la ejecución en pantalla (modo visible)
python scraper_na.py --headed

# Iniciar de cero, ignorando checkpoints previos
python scraper_na.py --reset
```

### Ejecutar la API
```bash
uvicorn api:app --host 0.0.0.0 --port 8000
```
Consulta la documentación en `http://localhost:8000/docs`

### Ejecutar el Actualizador Automático (Scheduler)
```bash
python scheduler.py
```

## Estructura
- `data/`: Contiene los resultados en `.json`, `.csv`, `.xlsx`.
- `logs/`: Logs de ejecución.
- `diagnostics/`: Capturas y requests de red si hay errores.
- `src/`: Módulos principales del scraper y análisis.
