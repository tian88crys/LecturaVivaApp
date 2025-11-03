package com.lecturaviva.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🎨 Esquema de color claro
private val LightColors = lightColorScheme(
    primary = ForestGreen,
    onPrimary = Color.White,
    secondary = Terracotta,
    onSecondary = TextDark,
    background = BackgroundBeige,
    surface = CardLight,
    onBackground = TextDark,
    onSurface = TextDark
)

// 🌙 Esquema de color oscuro
private val DarkColors = darkColorScheme(
    primary = ForestGreenDark,
    onPrimary = Color.White,
    secondary = TerracottaDark,
    onSecondary = TextLight,
    background = BackgroundDark,
    surface = BackgroundDark,
    onBackground = TextLight,
    onSurface = TextLight
)

@Composable
fun LecturaVivaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
