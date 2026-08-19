# 🎮 Wild Rift Drafting & Real-Time Tactical Assistant

Asistente táctico inteligente y superposición flotante (Overlay HUD) para **League of Legends: Wild Rift**, optimizado para la fase de selección de campeones (Draft), itemización dinámica, sinergias y análisis táctico.

---

## 🚀 Arquitectura y Sistema de Distribución Continua (CI/CD en GitHub)

### 1. Compilación y Publicación Automática (GitHub Actions)
El repositorio cuenta con un pipeline automatizado configurado en `.github/workflows/build-apk.yml`.

- **Disparador:** Cada vez que haces un `push` a la rama `main` o ejecutas manualmente el workflow (`workflow_dispatch`).
- **Firma Criptográfica Persistente:** Restaura la firma del keystore (`debug.keystore.base64`) de manera determinista, asegurando que **todas las versiones compilen con la misma clave de firma**.
- **Generación de Releases:** Publica automáticamente un nuevo **GitHub Release** adjuntando el archivo instalable `.apk` listo para su descarga.

---

## 🔄 Sistema de Actualizaciones en Tiempo Real (In-App Updates)

La aplicación implementa el gestor `AppUpdateManager` que verifica nuevas versiones de forma automática al iniciar la app y bajo demanda desde el menú **"Acerca de"**:

1. **Consulta Automática:**
   - Lee las versiones más recientes desde **GitHub Releases API** (`/repos/{owner}/{repo}/releases/latest`) y **Firebase Firestore** (`app_config/update_info`).
2. **Comparación Semántica de Versiones:**
   - Compara `BuildConfig.VERSION_CODE` y `BuildConfig.VERSION_NAME`.
3. **Alerta Interactiva (`AppUpdateDialog`):**
   - Muestra las novedades del parche / changelog.
   - Permite descargar e instalar el nuevo APK con un solo toque sin perder la configuración de usuario.
4. **Instalación sin Conflicto:**
   - Gracias a la unificación del certificado de firma y la correlación estricta de `versionCode`, las actualizaciones se instalan limpiamente sobre la versión existente.

---

## 🛠️ Tecnologías Utilizadas
- **Lenguaje:** Kotlin (100%)
- **UI / Design System:** Jetpack Compose + Material Design 3 (Tema Hextech)
- **Servicios:** Android Foreground Service con `SYSTEM_ALERT_WINDOW` y `MediaProjection`
- **Machine Learning / OCR:** Google ML Kit Text Recognition
- **Cloud Backend:** Firebase Firestore & Firebase Auth
- **Networking:** Retrofit, OkHttp3 & Coroutines Flow

---

## 📦 Control de Versiones
Para publicar una nueva versión:
1. Incrementa `versionCode` y `versionName` en `app/build.gradle.kts`.
2. Haz `push` a tu repositorio en GitHub.
3. GitHub Actions compilará el APK, creará el Release, y todos los usuarios de la app recibirán la notificación de actualización inmediatamente.
