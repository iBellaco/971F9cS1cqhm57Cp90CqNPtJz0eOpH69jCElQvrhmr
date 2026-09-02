<p align="center">
  <img src="https://raw.githubusercontent.com/github/explore/main/topics/android/android.png" alt="Android" width="60"/>
  <h1 align="center">Wild Rift Drafting Coach & Overlay</h1>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9.22-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Patch-7.2d%20Meta%20Ready-C89B3C?style=for-the-badge&logo=leagueoflegends&logoColor=white" alt="Wild Rift Patch 7.2d" />
  <img src="https://img.shields.io/badge/CI%2FCD-GitHub%20Actions%20Automated-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" alt="GitHub Actions" />
</p>

---

## ⚡ Características Principales

### 1. 🔍 Escaneo y Lectura de Draft por OCR en Tiempo Real
- **Detección Automática de Campeones:** Utiliza **Google ML Kit Text Recognition** sobre la captura de pantalla (`MediaProjection API`) para escanear en vivo la sala de selección de campeones (Draft de Wild Rift).
- **Análisis de Composición y Win Condition:** Identifica la identidad de equipo (Poke, Dive, Teamfight, Split-Push, Pick-off), analiza la ventaja en fase de líneas (matchup) y sintetiza la condición de victoria clave.

### 2. 🪟 Superposición Flotante en Pantalla (Overlay HUD)
- **Burbuja Flotante Minimalista:** Permite ejecutar la aplicación directamente sobre el juego Wild Rift sin salir de la partida mediante el permiso `SYSTEM_ALERT_WINDOW`.
- **Modo Compacto / Expandido:** Visualización fluida con control de toques y opacidad regulable para consultar builds, runas y tiempos de objetivos mientras juegas.
- **Acceso Rápido a Detección:** Botón de escaneo directo desde la superposición flotante para analizar la selección de campeones al instante.
- **HUD Multifuncional unificado:** Ahora incluye las pestañas de Draft, Tier List sincronizada y el Historial de partidas directamente en el Overlay.

### 3. 📚 Catálogo Oficial Completo y Localizado (Patch 7.2d Meta)
- **Sincronización con el Meta Chino:** Integración con servidores en vivo (Chinese Meta Sync Service) para ofrecer el Tier List y estadísticas de Winrate/Pickrate/Banrate más precisos y actualizados globalmente.
- **141 Campeones Oficiales:** Estadísticas detalladas, sinergias, counter-picks, habilidades, rutas de build y consejos de Coach por línea (Baron, Jungle, Mid, Duo, Support).
- **186+ Objetos:** Base de datos con costos de oro, estadísticas, pasivas explicadas y consejos tácticos traducidos íntegramente.
- **54 Runes (Runas):** Árboles completos (Clave, Precisión, Dominación, Valor, Inspiración).
- **13 Hechizos de Invocador (Summoner Spells) & Objetivos de Mapa.**

### 4. 🧠 Recomendaciones Tácticas Estructuradas (Coach de Élite)
Toda recomendación del asistente sigue el estándar profesional de análisis:
1. **Lectura del Draft y Condición de Victoria (Win Condition).**
2. **Picks recomendados y Sinergias de equipo.**
3. **Configuración Óptima de Runas y Hechizos.**
4. **Ruta de Objetos e Itemización Dinámica Situacional (Cortacuras, Penetración, Tenacidad).**
5. **Plan de Juego Macro y Objetivos (Fase de líneas niveles 1-5, mid/late game y micro-tips).**

### 5. 🔐 Autenticación, Suscripciones y Múltiples Perfiles (Premium)
- **Inicio de Sesión Seguro & Gestión de Dispositivos:** Integración con SDK de Firebase Auth. Sistema de seguridad que limita cuentas estándar a **2 dispositivos activos**, exceptuando a cuentas administradoras.
- **Perfiles Multicuenta:** Gestor avanzado (`AccountProfileManager`) para guardar el progreso y las métricas de drafts en múltiples cuentas o perfiles dentro del mismo dispositivo.
- **Suscripción Premium:** Accesos exclusivos a las copias de seguridad, historial avanzado de partidas desde el Overlay, y guardado en local/nube.

### 6. 💾 Exportación, Importación y Copias de Seguridad (JSON)
- **Respaldo Local:** Posibilidad de guardar los drafts analizados, historiales y perfiles generando un archivo `.json`.
- **Importación/Fusión Inteligente:** Opción para importar un historial exportado previamente a cualquier dispositivo, ofreciendo fusión inteligente (Merge mode) o sobrescritura completa, útil para sincronización de datos offline o migraciones.

### 7. 🤖 Scraper Autónomo de Estadísticas (CI/CD)
- **Extracción Diaria Automática:** Un scraper de Python actualiza automáticamente el meta del juego, obteniendo métricas oficiales.
- **Workflow de GitHub Actions:** Cada 24 horas, la granja de servidores de GitHub ejecuta el scraper y sube los datos renovados (SQLite/JSON) directo al repositorio sin intervención humana.

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue una arquitectura **Clean Architecture + MVVM** moderna en Android:

```
├── app/src/main/java/com/example/
│   ├── data/                   # Repositorios, AccountProfileManager, ChineseMetaSyncService
│   │   ├── backup/             # Gestores de respaldo e importación (BackupRestoreManager)
│   │   └── local/              # Base de datos Room Database (Historial de Drafts y Tier Lists)
│   ├── model/                  # Modelos de dominio y entidades
│   ├── service/                # Foreground Services para Overlay flotante (FloatingAssistantService) y OCR
│   ├── ui/                     # Capa de presentación (Jetpack Compose + Material 3)
│   │   ├── screens/            # Pantallas principales (Draft, Meta, Catalog, Settings, DraftHistoryScreen)
│   │   ├── components/         # Componentes reutilizables Hextech UI
│   │   └── theme/              # Paleta de colores Hextech (Gold, Cyan, Dark Velvet)
│   ├── util/                   # DeviceAndSessionManager, SubscriptionManager, Locale
│   └── viewmodel/              # StateFlow y ViewModels reactivos
```

---

## 🔄 Sistema de Actualizaciones en la App (In-App Updates)
La aplicación integra `AppUpdateManager`, el cual mantiene la app siempre al día:
1. **Detección Automática:** Consulta periódicamente la API de **GitHub Releases** (`/repos/{owner}/{repo}/releases/latest`) y **Firebase Firestore** para detectar nuevas versiones disponibles.
2. **Comparación Semántica:** Evalúa `versionCode` y `versionName` respecto al build actual.
3. **Descarga e Instalación Segura:** Descarga el archivo APK mediante el `DownloadManager` de Android y abre el instalador de paquetes con `FileProvider` sin requerir configuraciones complejas.

---

## 🚀 Pipeline de CI/CD (GitHub Actions)
El repositorio incluye integración y despliegue continuos totalmente automatizados en `.github/workflows/build-apk.yml`:
- **Compilación Automática:** En cada `push` o `tag` en la rama `main`, GitHub Actions ejecuta la compilación de Gradle en un entorno reproducible.
- **Firma Determinista:** Decodifica `debug.keystore.base64` para garantizar que todos los APKs generados compartan la misma firma digital.
- **Generación de Releases:** Publica un nuevo release en GitHub con el archivo APK firmado adjunto y notas de versión automáticas.

---

## 📱 Requisitos y Permisos

| Permiso | Propósito |
| :--- | :--- |
| `SYSTEM_ALERT_WINDOW` | Superposición de la burbuja y ventana flotante sobre League of Legends: Wild Rift. |
| `FOREGROUND_SERVICE` | Mantenimiento del servicio táctico en segundo plano sin interrupciones del sistema operativo. |
| `FOREGROUND_SERVICE_MEDIA_PROJECTION` | Captura de fotogramas de la pantalla para el escaneo OCR del Draft. |
| `INTERNET` | Consulta de actualizaciones, sincronización de metadatos chinos y validaciones Premium. |
| `POST_NOTIFICATIONS` | Notificaciones de control del servicio flotante y avisos de nuevas versiones. |

---

## 🛠️ Compilación y Desarrollo Local

### Requisitos
- **Android Studio** Ladybug (2024.2+) o superior.
- **JDK 17** o JDK 21.
- **Android SDK:** `minSdk = 24`, `targetSdk = 36`, `compileSdk = 36`.

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/barbadiego695/wild-rift-coach.git
cd wild-rift-coach

# 2. Compilar el APK de depuración
./gradlew assembleDebug

# 3. Instalar en tu dispositivo Android
./gradlew installDebug
```

---

## 🌐 Internacionalización (Idiomas Soportados)
- 🇪🇸 **Español (Principal)** - Textos, pasivas, descripciones y consejos tácticos de Coach.
- 🇺🇸 **English** - Full UI localization and game terms.
- 🇧🇷 **Português** - Interface completa e termos oficiais adaptados.

---

## 📜 Licencia y Aviso Legal
Este proyecto es una herramienta de asistencia comunitaria y de código abierto para jugadores de **League of Legends: Wild Rift**. *League of Legends: Wild Rift y Riot Games son marcas comerciales o marcas registradas de Riot Games, Inc.* Este software no está respaldado ni afiliado oficialmente con Riot Games.
