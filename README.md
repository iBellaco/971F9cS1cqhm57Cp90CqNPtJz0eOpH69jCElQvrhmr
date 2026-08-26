# 🎮 Wild Rift Tactical Coach & Real-Time Drafting Assistant

<p align="center">
  <img src="https://raw.githubusercontent.com/barbadiego695/wild-rift-coach/main/docs/banner.png" alt="Wild Rift Coach Banner" width="100%" onerror="this.style.display='none'"/>
</p>

<p align="center">
  <strong>Asistente táctico de nivel Challenger y Superposición Flotante (Overlay HUD) para League of Legends: Wild Rift</strong>
  <br />
  <em>Optimización de Draft en tiempo real, escaneo OCR de pantalla, itemización dinámica adaptativa, catálogos oficiales y análisis macro-estratégico.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Plataforma-Android%207.0%2B%20(API%2024%2B)-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android Version" />
  <img src="https://img.shields.io/badge/Lenguaje-Kotlin%20100%25-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose%20%2F%20M3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" />
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

### 3. 📚 Catálogo Oficial Completo y Localizado (Patch 7.2d Meta)
- **141 Campeones Oficiales:** Estadísticas detalladas, sinergias, counter-picks, habilidades, rutas de build y consejos de Coach por línea (Baron, Jungle, Mid, Duo, Support).
- **186+ Objetos:** Base de datos con costos de oro, estadísticas, pasivas explicadas y consejos tácticos traducidos íntegramente al español, inglés y portugués.
- **54 Runes (Runas):** Árboles completos (Clave, Precisión, Dominación, Valor, Inspiración) con números exactos y campeones recomendados.
- **13 Hechizos de Invocador (Summoner Spells):** Tiempos de reutilización y análisis situacional de uso.
- **Objetivos de Mapa:** Temporizadores de reaparición, estadísticas y bonificaciones de Dragones Elementales, Heraldo de la Grieta, Barón Nashor y Dragón Ancestral.
- **Tier List Dinámica:** Clasificación por rangos Challenger, S+, S, A, B y C con tasas de victoria (Winrate), porcentaje de selección (Pickrate) y tasa de bloqueo (Banrate).

### 4. 🧠 Recomendaciones Tácticas Estructuradas (Coach de Élite)
Toda recomendación del asistente sigue el estándar profesional de análisis:
1. **Lectura del Draft y Condición de Victoria (Win Condition).**
2. **Picks recomendados y Sinergias de equipo.**
3. **Configuración Óptima de Runas y Hechizos.**
4. **Ruta de Objetos e Itemización Dinámica Situacional (Cortacuras, Penetración, Tenacidad).**
5. **Plan de Juego Macro y Objetivos (Fase de líneas niveles 1-5, mid/late game y micro-tips).**

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue una arquitectura **Clean Architecture + MVVM** moderna en Android:

```
├── app/src/main/java/com/example/
│   ├── data/                   # Repositorios, fuentes de datos locales y catálogos estáticos
│   │   ├── WildRiftChampionsData.kt
│   │   ├── WildRiftItemsData.kt
│   │   ├── WildRiftRunesData.kt
│   │   ├── WildRiftSpellsData.kt
│   │   ├── WildRiftObjectivesData.kt
│   │   ├── WildRiftTierListData.kt
│   │   └── update/             # Gestor de actualizaciones in-app (GitHub Releases & Firestore)
│   ├── database/               # Persistencia local con Room Database
│   │   ├── AppDatabase.kt
│   │   ├── dao/
│   │   └── entity/
│   ├── model/                  # Modelos de dominio y entidades
│   ├── service/                # Foreground Services para Overlay flotante y OCR
│   │   ├── OverlayService.kt
│   │   ├── ScreenCaptureService.kt
│   │   └── TextRecognitionHelper.kt
│   ├── ui/                     # Capa de presentación (Jetpack Compose + Material 3)
│   │   ├── screens/            # Pantallas principales (Draft, Meta, Catalog, Settings)
│   │   ├── components/         # Componentes reutilizables Hextech UI
│   │   └── theme/              # Paleta de colores Hextech (Gold, Cyan, Dark Velvet)
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
- **Firma Determinista:** Decodifica `debug.keystore.base64` para garantizar que todos los APKs generados compartan la misma firma digital, permitiendo actualizaciones continuas sin conflictos de firma de paquetes.
- **Generación de Releases:** Publica un nuevo release en GitHub con el archivo APK firmado adjunto y notas de versión automáticas.

---

## 📱 Requisitos y Permisos

| Permiso | Propósito |
| :--- | :--- |
| `SYSTEM_ALERT_WINDOW` | Superposición de la burbuja y ventana flotante sobre League of Legends: Wild Rift. |
| `FOREGROUND_SERVICE` | Mantenimiento del servicio táctico en segundo plano sin interrupciones del sistema operativo. |
| `FOREGROUND_SERVICE_MEDIA_PROJECTION` | Captura de fotogramas de la pantalla para el escaneo OCR del Draft. |
| `INTERNET` | Consulta de actualizaciones, sincronización de metadatos y estadísticas en vivo. |
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
Este proyecto es una herramienta de asistencia comunitaria y de código abierto para jugadores de **League of Legends: Wild Rift**. 
*League of Legends: Wild Rift y Riot Games son marcas comerciales o marcas registradas de Riot Games, Inc.* Este software no está respaldado ni afiliado oficialmente con Riot Games.
