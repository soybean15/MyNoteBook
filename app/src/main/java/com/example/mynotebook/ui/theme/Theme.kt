package com.example.mynotebook.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primary,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = black1,
    surface = black2,
    onPrimary = black2,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,

)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = Purple700,
    secondary = Teal200,

    //Other default colors to override
    background = Color.White,
    surface = LightonSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = black1,

)

@Composable
fun MyNoteBookTheme(darkTheme: Boolean , content: @Composable () -> Unit) {
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