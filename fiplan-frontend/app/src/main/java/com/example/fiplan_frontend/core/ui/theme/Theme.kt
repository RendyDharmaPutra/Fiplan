package com.example.fiplan_frontend.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkSecondaryColor,
    tertiary = DarkTertiaryColor,
    background = DarkPrimaryBackgroundColor,
    surface = DarkSecondaryBackgroundColor,
    outline = DarkBorderColor,
    onBackground = DarkPrimaryTextColor,
    onSurface = DarkSecondaryTextColor,
    onPrimary = DarkPrimaryBackgroundColor,
    onError = DarkDangerTextColor,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = PrimaryBackgroundColor,
    surface = SecondaryBackgroundColor,
    outline = BorderColor,
    onBackground = PrimaryTextColor,
    onSurface = SecondaryTextColor,
    onPrimary = PrimaryBackgroundColor,
    onError = DangerTextColor
)

@Composable
fun FiplanfrontendTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}