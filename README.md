Here’s a GitHub `README.md` file for your Jetpack Compose app with Koin:

---

# **Jetpack Compose App with Koin Dependency Injection**

This repository demonstrates how to use [Koin](https://insert-koin.io/) for Dependency Injection in a Jetpack Compose application. The project follows a modular structure, uses MVVM architecture, and integrates Retrofit for API calls.

---

## **Features**
- Modular project structure for scalability.
- Dependency Injection using Koin.
- MVVM architecture.
- Shared Preferences and Retrofit integration.
- Jetpack Compose UI.

---

## **Project Structure**
```
app/
├── core/
│   ├── data/           # Core data classes (e.g., Shared Preferences)
│   ├── di/             # Core DI modules
│   └── MainViewModel.kt
├── feature/
│   ├── data/           # Feature-specific data (e.g., API and repositories)
│   ├── di/             # Feature-specific DI modules
│   ├── domain/         # Feature interfaces (e.g., Repository)
│   ├── presentation/   # Compose screens and ViewModels
├── ui.theme/           # Theming resources
└── App.kt              # Application class
```

---

## **Setup and Installation**

### **1. Add Dependencies**
Update the `build.gradle.kts` file:
```kotlin
// Retrofit
implementation(libs.bundles.rerofit)

// Koin
implementation(libs.bundles.koin)
```

In `libs.versions.toml`:
```toml
[bundles]
koin = [
    "koin-core",
    "koin-android",
    "koin-androidx-compose"
]
rerofit = [
    "retrofit",
    "okhttp",
    "converter-gson"
]

[libraries]
koin-android = { group = "io.insert-koin", name = "koin-android", version = "3.5.3" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version = "2.11.0" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version = "2.11.0" }
okhttp = { module = "com.squareup.okhttp3:okhttp", version = "4.12.0" }
```

---

## **Implementation Guide**

### **1. Initialize Koin**
Create an `App` class and initialize Koin in the `onCreate` method:
```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModule, featureModule)
        }
    }
}
```

Update the `AndroidManifest.xml` to include the `App` class:
```xml
<application
    android:name=".App"
    ... >
</application>
```

---

### **2. Core Module**
Define the `coreModule` to provide shared dependencies:
```kotlin
val coreModule = module {
    single { androidContext().getSharedPreferences("pref", Context.MODE_PRIVATE) }
    single<Repository>(qualifier = named("RepositoryImpl2")) { RepoImpl2(get()) }
    viewModel { MainViewModel(get(named("RepositoryImpl2"))) }
}
```

---

### **3. Feature Module**
Define the `featureModule` for feature-specific dependencies:
```kotlin
val featureModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SomeApi.BASE_URL)
            .build()
            .create(SomeApi::class.java)
    }
    single<Repository>(qualifier = named("RepositoryImpl")) { RepoImpl(get()) }
    viewModel { FeatureViewModel(get(named("RepositoryImpl"))) }
}
```

---

### **4. Repository Implementation**
Implement the `Repository` interface:
```kotlin
interface Repository {
    suspend fun getData(): String
}

class RepoImpl2(private val preferences: SharedPreferences) : Repository {
    override suspend fun getData(): String {
        return "data2"
    }
}

class RepoImpl(private val someApi: SomeApi) : Repository {
    override suspend fun getData(): String {
        return someApi.getData()
    }
}
```

---

### **5. API Interface**
Create an API interface for Retrofit:
```kotlin
interface SomeApi {
    @GET("data")
    suspend fun getData(): String

    @GET("premium")
    suspend fun getPremiumData(): String

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}
```

---

### **6. ViewModels**
Create `MainViewModel` and `FeatureViewModel` for business logic:
```kotlin
class MainViewModel(private val repository: Repository) : ViewModel() {
    init {
        viewModelScope.launch {
            println("Main Data: ${repository.getData()}")
        }
    }
}

class FeatureViewModel(private val repo: Repository) : ViewModel() {
    suspend fun getData(): String {
        return repo.getData()
    }
}
```

---

### **7. Feature Screen**
Create a Compose screen for the feature:
```kotlin
@Composable
fun FeatureScreen(modifier: Modifier = Modifier, viewModel: FeatureViewModel = koinViewModel()) {
    LaunchedEffect(true) {
        println("Feature Data: ${viewModel.getData()}")
    }
}
```

---

### **8. Main Activity**
Set up the `MainActivity` to display the `FeatureScreen`:
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                    FeatureScreen()
                }
            }
        }
    }
}
```

---

## **Run the App**
1. Download the zip file of this repository and extract it.
2. Open it in Android Studio.
3. Run the app on an emulator or a real device.
4. Observe the logs for data fetched by `MainViewModel` and `FeatureViewModel`.

---

Let me know if you need any modifications!
