package com.example.foodordering.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Dynamic color sengaja tidak dipakai supaya warna app tidak ikut wallpaper HP
private val FoodColorScheme = lightColorScheme(
    primary = Orange,
    onPrimary = Color.White,
    primaryContainer = OrangeSoft,
    onPrimaryContainer = OrangeDeep,
    background = Cream,
    onBackground = Espresso,
    surface = Color.White,
    onSurface = Espresso,
    onSurfaceVariant = Cocoa,
    surfaceContainerLow = Color.White,
    outline = Sand,
    outlineVariant = Sand,
    error = Tomato,
)

@Composable
fun FoodOrderingTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FoodColorScheme,
        typography = Typography,
        content = content,
    )
}
