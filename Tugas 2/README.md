# Jetpack Compose Basics App
Aplikasi Android sederhana yang dibangun menggunakan Jetpack Compose untuk mempelajari konsep dasar UI deklaratif, manajemen state, antarmuka daftar berkinerja tinggi (LazyColumn), serta animasi spring.

## Fitur Utama

    State Management & Persistence: Menggunakan rememberSaveable agar status aplikasi tetap terjaga saat rotasi layar.
     Performant Scrollable List: Menampilkan 1.000 item secara efisien menggunakan LazyColumn.
     Smooth Spring Animation: Animasi ekspansi/kolaps kartu yang mulus menggunakan .animateContentSize() dan efek spring().
     Material Design 3 & Dark Mode: Desain UI modern dengan dukungan tema terang (light mode) dan gelap (dark mode).
     Interactive Onboarding: Alur pindah antar layar (screen transition) sederhana dari Onboarding ke Main Content. 

## Teknologi yang Digunakan 

    Language: Kotlin
    UI Toolkit: Jetpack Compose (Material 3)
    Architecture: Single Activity (ComponentActivity) & State Hoisting
    Icons: androidx.compose.material:material-icons-extended

## Potongan Kode Penting (Code Highlights)

### 1. State Hoisting & Onboarding Flow
Memisahkan status UI dari komponen agar reusable dan mudah dites.

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

 

### 2. Animasi Spring pada Kartu
Menggunakan animateContentSize untuk memberikan efek animasi saat kartu diperluas.

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

 

### 3. Efficient List (LazyColumn)
Menampilkan daftar item tanpa masalah performa (lagging).

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

 

## Screenshot Hasil

## Project
    Git Repository:
    https://github.com/ReifanAlfattii/Pemrograman-Perangkat-Bergerak/tree/main/Tugas%202/basiclayouts
    Buka di Android Studio:
    Gunakan Android Studio Ladybug (atau versi lebih baru). 
    Sync Gradle: Pastikan koneksi internet stabil untuk mendownload dependency.
    Run App: Jalankan di emulator atau perangkat fisik (Android 11 / API 30+). 

 

## Lisensi
Project ini dibuat sebagai materi pembelajaran berdasarkan Codelab Resmi Google Jetpack Compose Basics. 