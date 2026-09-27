package com.example.foodordering

import java.util.Locale

data class Food(
    val name: String,
    val price: Int,
    val description: String,
    val rating: Double,
    val cookTime: String,
    val tag: String,
    val image: Int, // id foto di res/drawable-nodpi, contoh R.drawable.nasi_goreng
)

// Foto dari Unsplash (Unsplash License, bebas dipakai):
// R Eris, Mufid Majnun, K Azwan, dan Mae Mu
val menuList = listOf(
    Food(
        name = "Nasi Goreng Spesial",
        price = 25000,
        description = "Nasi goreng udang dengan telur dadar, kerupuk, dan acar segar.",
        rating = 4.8,
        cookTime = "15–20 mnt",
        tag = "Terlaris",
        image = R.drawable.nasi_goreng,
    ),
    Food(
        name = "Mie Ayam",
        price = 18000,
        description = "Mie kenyal dengan ayam kecap manis dan sawi hijau.",
        rating = 4.7,
        cookTime = "10–15 mnt",
        tag = "Favorit warga",
        image = R.drawable.mie_ayam,
    ),
    Food(
        name = "Sate Ayam Madura",
        price = 28000,
        description = "Sate ayam bakar dengan bumbu kacang, lontong, dan timun.",
        rating = 4.9,
        cookTime = "20–25 mnt",
        tag = "Rating tertinggi",
        image = R.drawable.sate_ayam,
    ),
    Food(
        name = "Es Teh Manis",
        price = 6000,
        description = "Teh dingin dengan es batu dan irisan jeruk nipis.",
        rating = 4.6,
        cookTime = "5 mnt",
        tag = "Paling segar",
        image = R.drawable.es_teh,
    ),
)

// 25000 -> "Rp 25.000"
fun formatRupiah(amount: Int): String =
    "Rp " + String.format(Locale.forLanguageTag("id-ID"), "%,d", amount)
