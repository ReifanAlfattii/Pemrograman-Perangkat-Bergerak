package com.example.foodordering.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodordering.R

// Plus Jakarta Sans (variable font): satu file untuk semua ketebalan.
// Ketebalannya harus diisi di variationSettings, kalau tidak semua teks jadi tipis.
// (variationSettings masih ditandai experimental oleh Compose, jadi perlu @OptIn)
@OptIn(ExperimentalTextApi::class)
private fun jakartaFont(weight: FontWeight) = Font(
    R.font.plus_jakarta_sans,
    weight = weight,
    variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
)

val JakartaSans = FontFamily(
    jakartaFont(FontWeight.Normal),
    jakartaFont(FontWeight.Medium),
    jakartaFont(FontWeight.SemiBold),
    jakartaFont(FontWeight.Bold),
    jakartaFont(FontWeight.ExtraBold),
)

private fun jakarta(weight: FontWeight, size: Int, lineHeight: Int) = TextStyle(
    fontFamily = JakartaSans,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
)

val Typography = Typography(
    headlineSmall = jakarta(FontWeight.ExtraBold, 24, 30),
    titleLarge = jakarta(FontWeight.ExtraBold, 22, 28),
    titleMedium = jakarta(FontWeight.Bold, 18, 24),
    titleSmall = jakarta(FontWeight.Bold, 15, 20),
    bodyLarge = jakarta(FontWeight.Normal, 16, 24),
    bodyMedium = jakarta(FontWeight.Normal, 14, 21),
    bodySmall = jakarta(FontWeight.Medium, 12, 16),
    labelLarge = jakarta(FontWeight.Bold, 15, 20),
    labelMedium = jakarta(FontWeight.SemiBold, 12, 16),
    labelSmall = jakarta(FontWeight.Bold, 11, 14),
)
