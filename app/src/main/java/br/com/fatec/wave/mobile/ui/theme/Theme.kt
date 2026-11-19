package br.com.fatec.wave.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Esquema sempre escuro, com as cores do Wave
private val WaveColorScheme = darkColorScheme(
    primary = WaveAccent,
    onPrimary = Color.White,
    secondary = WaveLight,
    onSecondary = Color.White,
    tertiary = WaveMedium,
    background = WaveDark,
    onBackground = Color.White,
    surface = WaveDeep,
    onSurface = Color.White,
    surfaceVariant = WavePurple,
    onSurfaceVariant = WaveTexto
)

@Composable
fun WaveTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WaveColorScheme,
        typography = Typography,
        content = content
    )
}
