# Deskripsi studi kasus: Student Manager

## Latar belakang

Bagian akademik atau dosen wali biasanya perlu menyimpan data dasar mahasiswa: NIM, nama, dan program studi. Kalau data ini dicatat di buku atau berkas yang tersebar, mencari satu mahasiswa jadi lambat, NIM bisa tercatat dua kali, dan data yang sudah dihapus tidak bisa dikembalikan.

Student Manager adalah aplikasi Android untuk mencatat data tersebut di ponsel. Aplikasi ini dibangun dari wireframe yang berisi enam layar utama (splash, daftar mahasiswa, pencarian, form tambah, form edit, dan konfirmasi hapus) ditambah beberapa layar pendukung: empty state, menu, dan dropdown program studi.

## Tujuan

Aplikasi ini dibuat supaya pengguna bisa:

1. melihat semua mahasiswa yang tersimpan beserta jumlahnya,
2. mencari mahasiswa berdasarkan nama, NIM, atau program studi,
3. menambah, mengubah, dan menghapus data mahasiswa,
4. menyimpan data di perangkat sehingga data tetap ada setelah aplikasi ditutup.

## Pengguna

Pengguna aplikasi adalah satu orang petugas, misalnya staf akademik atau dosen wali, yang mengelola data mahasiswa di satu perangkat. Aplikasi tidak memakai login karena semua data hanya tersimpan di perangkat tersebut.

## Data yang dikelola

Setiap mahasiswa punya tiga atribut yang diisi pengguna:

| Atribut | Keterangan | Aturan |
|---|---|---|
| NIM | Nomor induk mahasiswa | Wajib, hanya angka, tidak boleh sama dengan mahasiswa lain |
| Nama | Nama lengkap | Wajib diisi |
| Program Studi | Dipilih dari dropdown | Wajib, salah satu dari enam pilihan |

Pilihan program studi: Informatika, Sistem Informasi, Teknik Komputer, Desain Komunikasi Visual, Manajemen, dan Akuntansi.

Di dalam database, setiap baris juga punya `id` yang dibuat otomatis. Pengguna tidak melihat kolom ini. Aplikasi memakainya untuk membedakan data saat mengedit dan menghapus.

## Fitur yang dibangun

### Fitur dari wireframe

Daftar mahasiswa adalah layar utama. Layar ini berisi kolom pencarian, teks "Jumlah mahasiswa: N", dan kartu untuk setiap mahasiswa. Kartu menampilkan avatar, nama, NIM, program studi, tombol edit, dan tombol hapus berwarna merah. Tombol + di pojok kanan bawah membuka form tambah.

Pencarian bekerja langsung saat pengguna mengetik, tanpa perlu menekan tombol cari. Kata kunci dicocokkan dengan nama, NIM, dan program studi, jadi mengetik "budi", "2301001", atau "informatika" sama-sama bisa menemukan Budi Santoso. Angka pada "Jumlah mahasiswa" mengikuti hasil pencarian, dan tombol X mengosongkan kolom pencarian.

Form tambah punya isian NIM dan Nama, dropdown Program Studi, serta tombol Batal dan Simpan. Form edit memakai tampilan yang sama, tetapi isiannya sudah terisi data mahasiswa yang dipilih.

Sebelum data dihapus, aplikasi menampilkan dialog "Hapus Mahasiswa?" dengan tombol Batal dan Hapus. Dialog ini menyebut nama dan NIM mahasiswa supaya pengguna tahu data mana yang akan hilang.

Kalau belum ada data sama sekali, aplikasi menampilkan empty state: ikon toga, teks "Belum ada data mahasiswa", dan petunjuk untuk menekan tombol +. Kalau datanya ada tetapi pencarian tidak menemukan hasil, pesannya "Mahasiswa tidak ditemukan".

Menu titik tiga di pojok kanan atas berisi Refresh, Tentang Aplikasi, dan Keluar. Refresh mengosongkan pencarian dan menampilkan ulang seluruh data. Tentang Aplikasi membuka dialog berisi versi dan teknologi yang dipakai. Keluar menutup aplikasi.

Splash screen ditandai opsional di wireframe dan tidak dibuat sebagai layar terpisah. Android 12 ke atas sudah menampilkan splash bawaan sistem dengan ikon aplikasi (toga di atas latar biru) setiap kali aplikasi dibuka.

### Tambahan di luar wireframe

Wireframe tidak mengatur apa yang terjadi kalau input salah atau aksi berhasil. Karena itu ada beberapa tambahan:

1. Validasi form. Pesan error muncul di bawah field yang bermasalah saat tombol Simpan ditekan, dan hilang saat field itu diperbaiki. Pengecekan NIM ganda memperhitungkan mode edit, jadi mahasiswa yang disimpan ulang dengan NIM-nya sendiri tidak dianggap duplikat.
2. Snackbar. Setelah menambah, mengubah, atau menghapus data, muncul pesan singkat di bagian bawah layar. Pesan setelah hapus punya tombol "Urungkan" untuk mengembalikan data yang baru dihapus.
3. Data contoh. Saat aplikasi pertama kali dipasang, database langsung berisi tiga mahasiswa dari wireframe: Budi Santoso, Siti Aminah, dan Andi Wijaya.
4. Penyimpanan lokal dengan Room (SQLite), sehingga data tetap ada setelah aplikasi ditutup atau ponsel dinyalakan ulang.

## Alur navigasi

```
Buka aplikasi
   |
Daftar Mahasiswa (Home) ──── ketik di kolom cari ──> daftar tersaring
   |        |        |
   |        |        └── ikon hapus ──> dialog konfirmasi ──> Snackbar + Urungkan
   |        └── ikon edit ──> Form Edit ──> Simpan ──> kembali ke Home + Snackbar
   └── tombol + ──> Form Tambah ──> Simpan ──> kembali ke Home + Snackbar
```

Tombol kembali dan tombol Batal di form selalu kembali ke daftar tanpa menyimpan perubahan.

## Batasan

Aplikasi ini sengaja dibuat sederhana:

1. data hanya tersimpan di satu perangkat dan tidak disinkronkan ke server,
2. tidak ada login atau pembagian peran pengguna,
3. data mahasiswa hanya NIM, nama, dan program studi, tanpa foto, angkatan, atau nilai,
4. daftar program studi tetap di kode dan tidak bisa diubah dari aplikasi.

## Teknologi

Aplikasi ditulis dengan Kotlin. Tampilannya dibuat dengan Jetpack Compose dan komponen Material 3. Data disimpan dengan Room, perpindahan layar diatur Navigation Compose, dan state layar dikelola dengan ViewModel. Target minimum adalah Android 11 (API 30).
