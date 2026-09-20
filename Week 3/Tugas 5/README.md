# Greeting Card

Mini project Jetpack Compose berupa kartu ucapan sederhana. Kartunya menampilkan emoji, judul, dan pesan, lalu ada tombol untuk mengganti ucapan yang tampil.

Dibuat sebagai tugas mata kuliah dengan pembagian berdasarkan NRP terakhir modulus 5.

| | |
|---|---|
| **Nama** | Reifan Al-fattii Cahyadewa |
| **NRP** | 5053251046 |
| **Pembagian** | 46 % 5 = 1 → Greeting Card |
| **Fokus Utama** | Dasar Composable |
| **Composable Utama** | `Column`, `Text`, `Button` |

## Tampilan

| Ucapan pertama | Setelah tombol ditekan |
|:---:|:---:|
| <img src="screenshots/01-hello-android.png" width="280"> | <img src="screenshots/02-selamat-datang.png" width="280"> |

## Fitur

- Kartu ucapan berisi emoji, judul, dan pesan
- Tombol untuk berpindah ke ucapan berikutnya
- Empat ucapan yang berputar, kembali ke awal setelah ucapan terakhir
- Teks tombol berubah dari `Start` menjadi `Ucapan Berikutnya`
- Identitas pembuat ditampilkan di bawah kartu

## Teknologi

- Kotlin 2.2.10
- Jetpack Compose (BOM 2026.02.01)
- Material 3
- Android Gradle Plugin 9.3.3
- minSdk 30, targetSdk 37

## Struktur Kode

```
app/src/main/java/com/example/greetingcard/
├── MainActivity.kt          # seluruh logika dan UI kartu
└── ui/theme/
    ├── Color.kt             # warna bawaan + warna khusus kartu
    ├── Theme.kt             # tema aplikasi
    └── Type.kt              # tipografi
```

`MainActivity.kt` berisi dua composable:

| Composable | Tugas |
|---|---|
| `GreetingCardApp()` | Menyimpan state ucapan ke berapa yang sedang tampil |
| `KartuUcapan()` | Menggambar kartunya saja, semua data masuk lewat parameter |

Pemisahan ini disebut *state hoisting*. Tujuannya supaya `KartuUcapan` tidak terikat pada state tertentu dan bisa dipakai ulang.

## Cara Menjalankan

1. Clone repository ini
   ```bash
   git clone https://github.com/USERNAME/GreetingCard.git
   ```
2. Buka foldernya lewat Android Studio (**File → Open**)
3. Tunggu Gradle sync selesai
4. Jalankan dengan tombol **Run** ke emulator atau perangkat fisik

Untuk melihat tampilannya tanpa menjalankan emulator, buka `MainActivity.kt` lalu aktifkan mode **Split** atau **Design**. Preview sudah tersedia di bagian bawah file.

## Mengubah Isi Ucapan

Daftar ucapan ada di `MainActivity.kt`. Tinggal tambah atau ubah isinya, tidak perlu menyentuh bagian lain.

```kotlin
private val daftarUcapan = listOf(
    Ucapan("👋", "Hello, Android!", "Welcome to Jetpack Compose"),
    Ucapan("🎉", "Selamat Datang!", "Semoga harimu menyenangkan"),
    Ucapan("💪", "Semangat Terus!", "Jangan lupa istirahat ya"),
    Ucapan("🙏", "Terima Kasih!", "Sudah mampir ke kartu ini")
)
```
