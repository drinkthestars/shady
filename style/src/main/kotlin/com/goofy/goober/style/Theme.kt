package com.goofy.goober.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

@Composable
fun ShadyTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(
            displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = FontFamily.Monospace),
            displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = FontFamily.Monospace),
            displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = FontFamily.Monospace),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Monospace),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Monospace),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = FontFamily.Monospace),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace),
            titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace),
            titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = FontFamily.Monospace),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Monospace),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.Monospace),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
        ),
        content = content
    )
}
