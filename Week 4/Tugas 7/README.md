# FoodHunt

Aplikasi pemesanan makanan sederhana dengan Kotlin dan Jetpack Compose. Project ini dibuat untuk Tugas 7 Pertemuan 4 (materi Button dan User Interaction), aplikasi nomor 4: Food Ordering.

Nama: (isi nama)  
NIM: (isi NIM)  
Kelas: (isi kelas)

<p>
  <img src="docs/screenshots/01-beranda.png" width="150" alt="Tampilan awal">
  <img src="docs/screenshots/02-jumlah.png" width="150" alt="Jumlah pesanan 3, total Rp 75.000">
  <img src="docs/screenshots/03-keranjang.png" width="150" alt="Badge keranjang dan Toast">
  <img src="docs/screenshots/04-favorit.png" width="150" alt="Tombol Favorit aktif">
  <img src="docs/screenshots/05-menu.png" width="150" alt="Daftar menu">
</p>

## Isi aplikasi

Semua fitur ada di satu layar. Bagian atas menampilkan foto makanan, nama, rating, perkiraan waktu masak, harga, dan deskripsi. Di bawahnya ada kartu jumlah pesanan dan total harga, lalu tombol-tombol aksi. Ada empat menu yang bisa dipilih: Nasi Goreng Spesial (Rp 25.000), Mie Ayam (Rp 18.000), Sate Ayam Madura (Rp 28.000), dan Es Teh Manis (Rp 6.000).

| Tombol | Komponen | Yang terjadi saat ditekan |
|---|---|---|
| − dan + | `FilledIconButton` | Mengurangi atau menambah jumlah pesanan. Total harga ikut berubah. Tombol − tidak aktif saat jumlahnya 1. |
| Tambah ke Keranjang | `Button` | Menambah angka di badge keranjang sesuai jumlah pesanan, menampilkan Toast, lalu mengembalikan jumlah ke 1. |
| Lihat Menu | `OutlinedButton` | Membuka daftar menu dari bawah layar (`ModalBottomSheet`). Menu yang dipilih langsung tampil di layar utama. |
| Favorit | `OutlinedButton` dan `FilledTonalButton` | Menandai makanan sebagai favorit. Tombol berganti dari hati kosong ke hati penuh, dan status favorit disimpan per makanan. |
| Ikon keranjang | `OutlinedIconButton` | Menampilkan Toast berisi jumlah item di keranjang. |

## Materi yang dipakai

| Materi | Dipakai di |
|---|---|
| Composable function | Layar dipecah menjadi `FoodTopBar`, `FoodImage`, `FoodInfo`, `OrderSummary`, `QuantitySelector`, `FavoriteButton`, dan `MenuBottomSheet` |
| Button dan `onClick` | Semua tombol di tabel atas |
| Event handling | Toast saat menambah ke keranjang, mengubah favorit, dan menekan ikon keranjang |
| State dan recomposition | `quantity`, `cartCount`, `favorites`, `selectedIndex`, dan `showMenu` |
| Modifier | Ukuran tombol, padding, sudut membulat, dan warna latar |
| Button dengan icon | Keranjang, hati, buku menu, serta − dan + |
| Jenis button Material 3 | `Button`, `OutlinedButton`, `FilledTonalButton`, `FilledIconButton`, `OutlinedIconButton` |

## Struktur project

```
app/src/main/
├── java/com/example/foodordering/
│   ├── MainActivity.kt         # mengatur status bar dan memanggil FoodOrderingScreen
│   ├── FoodOrderingScreen.kt   # layar utama dan semua tombol
│   ├── Food.kt                 # data class Food, daftar menu, formatRupiah()
│   └── ui/theme/
│       ├── Color.kt            # palet warna oranye
│       ├── Theme.kt            # tema Material 3 tanpa dynamic color
│       └── Type.kt             # font Plus Jakarta Sans
└── res/
    ├── drawable-nodpi/         # empat foto makanan (.webp)
    ├── drawable/               # ikon launcher
    └── font/                   # plus_jakarta_sans.ttf
```

## Cara menjalankan

1. Buka folder project di Android Studio.
2. Klik Sync Now kalau Android Studio meminta sinkronisasi Gradle.
3. Jalankan di emulator atau HP dengan Android 11 (API 30) ke atas.

Gradle project ini diatur memakai JDK 25 (lihat `gradle/gradle-daemon-jvm.properties`). Build dari Android Studio memakai JDK bawaannya (JetBrains Runtime). Untuk build dari terminal, arahkan `JAVA_HOME` ke folder `jbr` di instalasi Android Studio, lalu jalankan:

```bash
./gradlew assembleDebug
```

APK hasil build ada di `app/build/outputs/apk/debug/app-debug.apk`.

## Teknologi

- Kotlin 2.2.10
- Android Gradle Plugin 9.3.3 dan Gradle 9.5.0
- Jetpack Compose BOM 2026.02.01 (Material 3 1.4.0)
- `material-icons-extended` untuk ikon. Material 3 1.4.0 tidak membawa library ikon, jadi dependency ini perlu ditambahkan agar `Icons.Rounded.*` bisa dipakai.
- `compileSdk` dan `targetSdk` 37, `minSdk` 30

## Catatan

- State disimpan dengan `remember`, sama seperti di materi. Isi keranjang kembali kosong kalau layar diputar atau aplikasi ditutup.
- Halaman keranjang belum dibuat. Ikon keranjang baru menampilkan jumlah item lewat Toast.

## Kredit

- Foto makanan dari [Unsplash](https://unsplash.com) (Unsplash License): [nasi goreng](https://unsplash.com/photos/a-plate-of-rice-with-shrimp-and-vegetables-o6Oq7rBMqVc) oleh R Eris, [mie ayam](https://unsplash.com/photos/a-close-up-of-a-bowl-of-food-with-noodles-raUhnwikJ14) oleh Mufid Majnun, [sate ayam](https://unsplash.com/photos/a-white-plate-topped-with-meat-and-veggies-next-to-a-bowl-of-sauce-qCmoHnvA94o) oleh K Azwan, dan [es teh](https://unsplash.com/photos/clear-drinking-glass-with-tea-kbch-i63YTg) oleh Mae Mu.
- Font [Plus Jakarta Sans](https://github.com/tokotype/PlusJakartaSans) oleh Gumpita Rahayu (Tokotype), lisensi SIL Open Font License 1.1.
- Ikon dari Material Icons.
