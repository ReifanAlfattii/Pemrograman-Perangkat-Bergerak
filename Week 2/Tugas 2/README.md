# Jetpack Compose Basics App

Aplikasi Android sederhana yang dibangun menggunakan Jetpack Compose untuk mempelajari konsep dasar UI deklaratif, manajemen state, antarmuka daftar berkinerja tinggi (`LazyColumn`), serta animasi spring.

---

## Fitur Utama

* **State Management & Persistence:** Menggunakan `rememberSaveable` agar status aplikasi tetap terjaga saat rotasi layar.
* **Performant Scrollable List:** Menampilkan 1.000 item secara efisien menggunakan `LazyColumn`.
* **Smooth Spring Animation:** Animasi ekspansi atau kolaps kartu yang mulus menggunakan `.animateContentSize()` dan efek `spring()`.
* **Material Design 3 & Dark Mode:** Desain UI modern dengan dukungan tema terang (*light mode*) dan gelap (*dark mode*).
* **Interactive Onboarding:** Alur pindah antar layar (*screen transition*) sederhana dari halaman *Onboarding* ke *Main Content*. 

---

## Teknologi yang Digunakan

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose (Material 3)
* **Architecture:** Single Activity (`ComponentActivity`) & State Hoisting
* **Icons:** `androidx.compose.material:material-icons-extended`

---

## Potongan Kode Penting (Code Highlights)

### 1. State Hoisting & Onboarding Flow
Memisahkan status UI dari komponen agar dapat digunakan kembali (*reusable*) dan mudah dites.

```kotlin
@Composable 
fun MyApp(modifier: Modifier = Modifier) { 
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}
```

### 2. Animasi Spring pada Kartu
Menggunakan `animateContentSize` untuk memberikan efek animasi yang halus saat komponen kartu diperluas.

```kotlin
Row( 
    modifier = Modifier 
        .padding(12.dp) 
        .animateContentSize( 
            animationSpec = spring( 
                dampingRatio = Spring.DampingRatioMediumBouncy, 
                stiffness = Spring.StiffnessLow 
            ) 
        ) 
) { 
    // Content layout 
}
```

### 3. Efficient List (LazyColumn)
Menampilkan daftar ribuan item secara optimal tanpa masalah performa (*lagging*).

```kotlin
@Composable 
private fun Greetings( 
    modifier: Modifier = Modifier, 
    names: List<String> = List(1000) { "\$it" } 
) { 
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) { 
        items(items = names) { name -> 
            Greeting(name = name) 
        } 
    } 
}
```

---

## Petunjuk Penggunaan

### Repositori Proyek
Tugas ini terletak pada folder berikut:
`Tugas 2/basiclayouts`

### Cara Menjalankan di Android Studio
1. **Buka Proyek:** Gunakan Android Studio Ladybug atau versi yang lebih baru.
2. **Sync Gradle:** Pastikan koneksi internet stabil untuk mengunduh seluruh *dependencies* yang diperlukan.
3. **Jalankan Aplikasi:** Jalankan aplikasi menggunakan emulator atau perangkat fisik (disarankan Android 11 / API 30+).

---

## Lisensi

Proyek ini dibuat sebagai materi pembelajaran akademik berdasarkan Codelab Resmi Google Jetpack Compose Basics.
