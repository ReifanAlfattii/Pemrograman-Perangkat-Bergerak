# Happy Birthday Card — Aplikasi Sederhana dengan Composable

Aplikasi Android sederhana yang menampilkan kartu ucapan ulang tahun, dibangun
sepenuhnya dengan **Jetpack Compose**. Proyek ini adalah latihan dasar untuk
memahami cara membangun UI Android secara deklaratif — tanpa satu pun file layout
XML.

![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202026.02.01-4285F4?logo=jetpackcompose&logoColor=white)
![minSdk](https://img.shields.io/badge/minSdk-30-3DDC84?logo=android&logoColor=white)

---

## Hasil Akhir

<p align="center">
  <img src="docs/screenshot.png" alt="Tampilan aplikasi Happy Birthday Card" width="300">
</p>

Layar menampilkan dua elemen teks yang tersusun vertikal — ucapan berukuran besar
(100sp) di atas, dan nama pengirim berukuran kecil (36sp) tepat di bawahnya.

---

## Materi yang Dipelajari

### 1. User Interface (UI)

Antarmuka pengguna adalah tampilan visual di layar — teks, gambar, tombol — beserta
cara elemen-elemen tersebut diletakkan. UI adalah jembatan antara aplikasi dan
penggunanya.

### 2. Jetpack Compose

Jetpack Compose adalah toolkit modern untuk membangun UI Android. Bedanya dengan
cara lama (XML): kita **mendeskripsikan** UI lewat fungsi Kotlin, bukan menyusunnya
di file layout terpisah. Hasilnya kode jadi lebih sedikit dan lebih mudah dibaca.

### 3. Anotasi `@Composable`

Anotasi dipakai untuk melampirkan informasi tambahan ke kode. `@Composable` memberi
tahu compiler bahwa fungsi tersebut adalah fungsi composable — fungsi yang bertugas
merender elemen UI.

```kotlin
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    // ...
}
```

> **Aturan penamaan:** fungsi composable ditulis dengan **PascalCase**
> (`GreetingText`), bukan camelCase (`greetingText`). Ini konvensi penting supaya
> composable mudah dibedakan dari fungsi Kotlin biasa.

### 4. Anotasi `@Preview`

`@Preview` memungkinkan kita melihat tampilan composable langsung di Android Studio
**tanpa menjalankan emulator atau perangkat fisik**. Syaratnya: proyek harus
di-*build* minimal satu kali agar panel preview bisa muncul.

```kotlin
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
    }
}
```

Beberapa parameter `@Preview` yang berguna:

| Parameter | Fungsi |
|---|---|
| `showBackground = true` | Menampilkan latar belakang di preview |
| `showSystemUi = true` | Menampilkan bingkai ponsel lengkap dengan status bar |
| `name = "..."` | Memberi nama pada preview (berguna kalau punya banyak preview) |

### 5. SP vs DP — Satuan Ukuran

Android memakai dua satuan yang berbeda:

- **DP** (*Density-independent Pixels*) → untuk **tata letak**: lebar, tinggi, padding.
- **SP** (*Scalable Pixels*) → khusus untuk **ukuran font**.

Secara default 1 SP = 1 DP, tapi SP ikut membesar/mengecil mengikuti setelan ukuran
teks di ponsel pengguna. Inilah alasan `fontSize` selalu pakai `.sp` — supaya aplikasi
tetap bisa dibaca oleh pengguna yang menaikkan ukuran font di pengaturan.

```kotlin
Text(
    text = message,
    fontSize = 100.sp,
    lineHeight = 116.sp,
)
```

Jangan lupa import-nya: `androidx.compose.ui.unit.sp`

### 6. Hierarki UI — `Column`, `Row`, dan `Box`

Fungsi composable bisa memuat beberapa elemen UI sekaligus. Tapi kalau tidak diberi
panduan penyusunan, Compose akan **menumpuk** elemen-elemen itu di posisi yang sama
sehingga saling bertindihan.

Solusinya: bungkus dengan composable **tata letak** yang bertindak sebagai elemen
induk (*parent*), sementara `Text` di dalamnya menjadi turunan (*child*):

| Composable | Susunan |
|---|---|
| `Column` | Menyusun turunan secara **vertikal** (atas ke bawah) |
| `Row` | Menyusun turunan secara **horizontal** (kiri ke kanan) |
| `Box` | Menumpuk turunan di **atas satu sama lain** (berlapis) |

Di proyek ini dipakai `Column` supaya nama pengirim berada tepat di bawah ucapan.

---

## Penjelasan Kode

Seluruh kode ada di satu file: `app/src/main/java/com/example/happybirthday/MainActivity.kt`

### `MainActivity` — titik masuk aplikasi

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
                }
            }
        }
    }
}
```

- `setContent { }` — menggantikan `setContentView(R.layout.xxx)` pada cara lama.
  Di sinilah UI Compose didefinisikan.
- `HappyBirthdayTheme` — tema aplikasi (warna, tipografi) yang dibungkuskan ke
  seluruh UI di dalamnya.
- `Surface` — wadah dasar yang mengisi seluruh layar (`fillMaxSize()`) dan memberi
  warna latar sesuai tema.

### `GreetingText` — composable kartu ucapan

```kotlin
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
        )
        Text(
            text = from,
            fontSize = 36.sp
        )
    }
}
```

Fungsi ini menerima dua parameter `String` — `message` (ucapan) dan `from` (pengirim)
— lalu menampilkannya sebagai dua composable `Text` yang disusun vertikal oleh `Column`.

Karena isinya berupa parameter (bukan teks yang ditulis langsung), composable ini
bisa **dipakai ulang** untuk ucapan dan pengirim apa pun.

> **Kenapa ada `modifier: Modifier = Modifier`?**
> Ini konvensi Compose: setiap composable sebaiknya menerima parameter `modifier`
> dengan nilai default, agar pemanggilnya bisa menyesuaikan ukuran, padding, atau
> perilaku composable tersebut dari luar.

---

## Struktur Proyek

```
app/src/main/java/com/example/happybirthday/
├── MainActivity.kt          # Seluruh logika UI aplikasi
└── ui/theme/
    ├── Color.kt             # Definisi warna
    ├── Theme.kt             # HappyBirthdayTheme
    └── Type.kt              # Tipografi
```

---

## Cara Menjalankan

1. Clone repositori ini, lalu buka foldernya lewat **Android Studio**
   (*File → Open*).
2. Tunggu proses **Gradle Sync** selesai.
3. Build sekali (*Build → Make Project*) supaya panel **Preview** bisa muncul.
4. Jalankan lewat tombol **Run ▶** ke emulator atau perangkat fisik.

Atau lewat terminal:

```bash
./gradlew assembleDebug
```

APK hasil build ada di `app/build/outputs/apk/debug/app-debug.apk`.

---

## Mengubah Isi Kartu

Ganti string di dua tempat dalam `MainActivity.kt` — di `onCreate()` (untuk aplikasi
yang berjalan) dan di `BirthdayCardPreview()` (untuk panel preview):

```kotlin
GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
//                      ^^^^^^^^^^^^^^^^^^^         ^^^^^^^^^^
//                      ucapan                      pengirim
```

---

## Referensi

- [Android Basics with Compose — Google Codelabs](https://developer.android.com/courses/android-basics-compose/course)
- [Dokumentasi Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
