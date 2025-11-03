# 📚 LecturaViva – Biblioteca Digital (Evaluación 2)

Aplicación Android (Jetpack Compose + Material3) para explorar catálogo, reservar libros, ver noticias y gestionar perfil con login/registro sencillo.

> **Stack**: Kotlin · Jetpack Compose · Material3 · Navigation-Compose · DataStore (Preferences) · Kotlinx Serialization

---   

## ✅ Requisitos de entorno

- **Android Studio**: Koala / Ladybug o superior  
- **AGP (Android Gradle Plugin)**: `8.5.2`  
- **Kotlin**: `1.9.24` *(o 1.9.23 si ajustas el Compose Compiler; ver *Solución de problemas*)*  
- **Compose BOM**: `2024.10.01`  
- **Compose Compiler**: `1.5.14`  
- **JDK / JVM**: **17** (Gradle JDK y Toolchain del proyecto)  
- **SDK**:  
  - `compileSdk = 34`  
  - `targetSdk = 34`  
  - `minSdk = 24`

---

## 🗂️ Estructura principal del proyecto

```
app/
 ├─ src/main/java/com/lecturaviva/app/
 │   ├─ data/
 │   │   ├─ auth/              # Lógica de registro/login (DataStore)
 │   │   ├─ local/             # AppDataStore (wrappers)
 │   │   └─ repo/              # Repositorios (Book/Reservation + mocks)
 │   ├─ navigation/            # AppNavHost + Routes
 │   ├─ ui/
 │   │   ├─ components/        # TopBar, botones, tarjetas
 │   │   └─ screens/           # Home, Catalog, BookDetail, Reserve, News, Profile, Login, Splash, History
 │   └─ theme/                 # Colores (forest/terracota/beige), Tipografías, Shapes
 └─ build.gradle.kts
```

---

## 🚀 Puesta en marcha

1. **Clonar el repo**
   ```bash
   git clone https://github.com/<tu-usuario>/LecturaVivaApp.git
   cd LecturaVivaApp
   ```

2. **Abrir en Android Studio**  
   `File → Open…` y selecciona la carpeta del proyecto.

3. **Configurar JDK 17 para Gradle**
   - `File → Settings → Build, Execution, Deployment → Build Tools → Gradle`
   - **Gradle JDK**: elige `Embedded JDK (JBR 21)` o un **JDK 17** instalado.

4. **Sincronizar Gradle**
   `File → Sync Project with Gradle Files`

5. **Ejecutar en emulador o dispositivo**
   - Crea un **AVD** (Pixel 6 / API 34 recomendado) o conecta tu teléfono con **depuración USB**.
   - Pulsa **Run ▶︎** sobre la configuración “app”.

---

## 🏗️ Compilar APK / AAB

### APK *debug*
```bash
./gradlew assembleDebug
```
Salida: `app/build/outputs/apk/debug/app-debug.apk`

### AAB *release*
```bash
./gradlew bundleRelease
```
Salida: `app/build/outputs/bundle/release/app-release.aab`

---

## 🔐 Login / Registro (demo)

- **Persistencia** con **DataStore (Preferences)** + **kotlinx.serialization**.
- **Flujo**: Splash → Login → Registro → Perfil.
- **Cerrar sesión**: limpia DataStore y redirige al Login.

---

## 📖 Catálogo y Reservas

- **Catálogo**: búsqueda por título/autor + filtros de género.
- **Reserva**: formulario con validaciones y `DatePicker`.
- **Historial**: muestra reservas y permite cancelarlas.

> Usa `java.time.LocalDate` con *desugaring* para compatibilidad API 24+.

---

## 🧭 Navegación

- **Navigation-Compose** con rutas en `Routes`.
- `AppNavHost` define: `Splash`, `Login`, `Home`, `Catalog`, `BookDetail/{id}`, `Reserve/{id}`, `News`, `History`, `Profile`.

---

## 🎨 Tema y estilos

- **Material3** + paleta: **ForestGreen**, **Terracotta**, **Beige**, **AccentBlue**.
- **Tipografías y shapes** definidas en `ui/theme/`.

---

## 🧪 Datos de prueba

Repositorios mock con libros y reservas precargadas.

---

## 🧯 Solución de problemas

### 1️⃣ Error de versiones Kotlin / Compose Compiler
> `This version (1.5.14) of the Compose Compiler requires Kotlin 1.9.24 ...`

**Solución:**  
Actualiza `build.gradle.kts` raíz a:
```kotlin
plugins {
  id("org.jetbrains.kotlin.android") version "1.9.24" apply false
  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.24" apply false
}
```

### 2️⃣ No encuentra JDK 17
Configura **Gradle JDK** en `Settings → Build Tools → Gradle → JDK = 17`.

### 3️⃣ Error `LocalDate.isBefore` en API < 26
Asegura que tienes:
```kotlin
coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
compileOptions { isCoreLibraryDesugaringEnabled = true }
```

### 4️⃣ Plugin de serialización no encontrado
Debe estar con versión en el **gradle raíz** y sin versión en el del **módulo**.

---

## 📦 Entregables

- ✅ Código funcional completo.
- ✅ README (.md) con consideraciones.
- ✅ APK debug (`app-debug.apk`).
- ✅ Capturas del funcionamiento.
- ✅ Informe y presentación según pauta.

---

## 👥 Autores

- **Cristian Padilla** – UX/UI y estructura de Compose  
- **Pablo Reyes** – Coordinación y lógica de negocio  
- **Matías Vargas** – Desarrollo y testing

---

## 🔧 Comandos útiles

```bash
./gradlew assembleDebug      # Generar APK
./gradlew bundleRelease      # Generar AAB
./gradlew clean assembleDebug  # Limpiar y recompilar
```

---

## 🛣️ Próximos pasos

- Integrar Room + Flow.  
- Añadir paginación y búsqueda avanzada.  
- Implementar sincronización remota con Retrofit/Ktor.  
- Crear tests UI (Compose UI Test).
