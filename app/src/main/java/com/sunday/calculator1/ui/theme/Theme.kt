package com.sunday.calculator1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Orange500,
    primaryVariant = Grey300,
    secondary = Grey100,
    onPrimary = Color.Black,    // text-button
    onSecondary = Color.Black,  // text-calculation
    onSurface = Color.DarkGray  // text-result
)

private val DarkColorPalette = darkColors(
    primary = DeepOrange800,
    primaryVariant = Grey700,
    secondary = BlueGrey600,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.LightGray
)

@Composable
fun Calculator1Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}