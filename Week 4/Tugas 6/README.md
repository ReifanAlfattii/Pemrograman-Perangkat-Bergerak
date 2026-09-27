# Dice Roller

Aplikasi Android sederhana menggunakan **Jetpack Compose**. Pengguna dapat menekan tombol **Roll** untuk menampilkan angka dadu secara acak dari 1 sampai 6.

## Tampilan

- Dadu berwarna biru estetik.
- Tombol **Roll** berwarna ungu.
- Dadu dan tombol berada di tengah layar.
- Hasil dadu berubah setiap tombol ditekan.

## Teknologi

- Kotlin
- Jetpack Compose
- Material 3
- Minimum SDK: API 24

## Struktur Utama

```text
app/src/main/java/com/example/diceroller/MainActivity.kt
app/src/main/res/drawable/dice_1.xml
app/src/main/res/drawable/dice_2.xml
app/src/main/res/drawable/dice_3.xml
app/src/main/res/drawable/dice_4.xml
app/src/main/res/drawable/dice_5.xml
app/src/main/res/drawable/dice_6.xml
```

## Cara Menjalankan

1. Buka project menggunakan Android Studio.
2. Tunggu proses Gradle Sync selesai.
3. Pilih emulator atau perangkat Android.
4. Klik tombol **Run**.
5. Tekan tombol **Roll** untuk melempar dadu.

## Konsep yang Digunakan

Aplikasi menggunakan `mutableStateOf` untuk menyimpan hasil dadu. Ketika tombol ditekan, nilai baru dihasilkan secara acak menggunakan `Random`, kemudian UI melakukan recomposition dan menampilkan gambar dadu yang sesuai.
