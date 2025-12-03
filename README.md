🚀 README.md – LecturaViva (Versión Final para GitHub)
# 📚 LecturaViva – Biblioteca Digital Android  
Aplicación Android desarrollada con **Kotlin + Jetpack Compose + Firebase + Retrofit**, creada como proyecto académico para implementar:

- Autenticación real con Firebase  
- CRUD de usuarios en Firestore  
- Consumo de API externa (OpenLibrary)  
- Arquitectura por capas  
- Interfaz moderna y navegación fluida  

---

## 🧱 **Tecnologías principales**
| Área | Tecnología |
|------|------------|
| Lenguaje | **Kotlin (JVM 17)** |
| UI | **Jetpack Compose + Material3** |
| Navegación | Navigation-Compose |
| Backend | **Firebase Authentication + Firestore** |
| API Externa | **OpenLibrary REST API (GET)** |
| Networking | Retrofit + OkHttp |
| Estado | State Hoisting + ViewModel (simple) |
| Build | Gradle KTS + Compose BOM |

---

# ✅ **Requisitos del entorno**

- **Android Studio Ladybug / Koala** o superior  
- **AGP:** 8.5.2 o mayor  
- **Kotlin:** 1.9.24  
- **Compose BOM:** 2024.10.01  
- **JDK:** 17  
- **SDK Target:** 34  
- **Min SDK:** 24  

---

# 🗂️ **Estructura del Proyecto**



app/
├─ src/main/java/com/lecturaviva/app/
│ ├─ data/
│ │ ├─ model/ # Modelos Book, ExternalBook, User
│ │ ├─ repo/ # Firebase Repo + API Repo + Books Repo
│ │ │ ├─ BookRepository.kt
│ │ │ ├─ BookApiRepository.kt # Retrofit + OpenLibrary
│ │ │ ├─ UserRepository.kt # Registro/Login Firestore
│ │ │ ├─ ReservationRepository.kt
│ │ └─ firebase/ # Config FirebaseApp (implícito)
│
│ ├─ navigation/
│ │ ├─ Routes.kt
│ │ └─ AppNavHost.kt
│
│ ├─ ui/
│ │ ├─ components/ # TopBar, Cards, Buttons
│ │ └─ screens/
│ │ ├─ SplashScreen.kt
│ │ ├─ LoginScreen.kt
│ │ ├─ RegisterScreen.kt
│ │ ├─ HomeScreen.kt
│ │ ├─ CatalogScreen.kt # Conexión API externa
│ │ ├─ BookDetailScreen.kt
│ │ ├─ ReserveScreen.kt
│ │ ├─ NewsScreen.kt
│ │ ├─ HistoryScreen.kt
│ │ └─ ProfileScreen.kt
│
│ └─ theme/ # Colores, Tipos, Shapes
└─ build.gradle.kts


---

# 🔥 **Funciones principales de la app**

### ✔️ **1. Login / Registro con Firebase**
- Autenticación real vía **FirebaseAuth**
- Registro almacenado en **Firestore → colección `users`**
- Validación de email duplicado
- Redirección automática al Home

### ✔️ **2. CRUD con Firestore**
- CREATE: registro de usuario  
- READ: validación e inicio de sesión  
- CHECK: verificar si existe el usuario  
- UPDATE/DELETE: base preparada para ampliar (reservas, perfiles)

---

# 🌐 **3. API Externa – OpenLibrary (GET)**

Se implementa:



https://openlibrary.org/search.json?q=
<query>


- Búsqueda por autor/título  
- Mapeo de resultados a `ExternalBook`  
- Fusión con catálogo interno  
- Filtros por género  
- Sección del catálogo 100% funcional

Tecnologías:
- Retrofit  
- OkHttp  
- Gson  

---

# 🧭 **4. Navegación y Flujo de Pantallas**

| Pantalla | Propósito |
|----------|-----------|
| **Splash** | Carga inicial + branding |
| **Login** | Ingreso de usuario |
| **Register** | Crear cuenta con Firebase |
| **Home** | Acceso general |
| **Catalog** | Búsqueda interna + API externa |
| **BookDetail** | Detalle y reserva |
| **Reserve** | Formulario de reserva |
| **History** | Historial de reservas |
| **Profile** | Datos del usuario |

---

# 🎨 **5. Diseño y Estética**

Paleta personalizada:
- **ForestGreen** → Encabezados y acción  
- **Terracotta** → Contenido principal  
- **Beige** → Fondo suave  
- **AccentBlue** → Elementos destacados  

Basado en **Material3** + tipografía adaptada.

---

# 🚀 **Instalación y Ejecución**

### 1. Clonar el repositorio

```sh
git clone https://github.com/<tu-usuario>/LecturaVivaApp.git
cd LecturaVivaApp

2. Abrir en Android Studio

File → Open → proyecto

3. Configurar JDK 17

Settings → Build Tools → Gradle → JDK 17

4. Sincronizar Gradle

File → Sync Project with Gradle Files

5. Ejecutar

Run ▶︎ → app

🏗️ Compilar APK / AAB
📦 APK Debug
./gradlew assembleDebug


Salida:
app/build/outputs/apk/debug/app-debug.apk

🔐 Build firmado (Release)
./gradlew bundleRelease

🧯 Solución de problemas comunes
1️⃣ Error Kotlin / Compose Compiler

Actualizar versiones en build.gradle.kts raíz.

2️⃣ LocalDate en API < 26

Asegurar:

compileOptions { isCoreLibraryDesugaringEnabled = true }
coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

3️⃣ Firebase no inicializa

Revisar que google-services.json esté en /app.

4️⃣ Problemas de permisos de API externa

Verificar conexión HTTPS + logs de Retrofit.

📦 Entregables (para la evaluación)

✓ Código completo (GitHub)

✓ README profesional

✓ APK Debug

✓ Informe escrito

✓ Presentación Canva

✓ Demostración en clase

👥 Autores
Nombre	Rol
Cristian Padilla	UI/UX, arquitectura Jetpack Compose, integración API
Pablo Reyes	Lógica de negocio, soporte backend
Matías Vargas	Testing, validaciones y flujos
🛣️ Próximos pasos

Implementar Room como caché local

Agregar paginación y scroll infinito

Mejorar la reserva usando Firestore en tiempo real

Agregar tests UI con Compose Test

Soporte offline con WorkManager
