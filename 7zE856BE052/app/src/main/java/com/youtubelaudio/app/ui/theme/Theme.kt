package com.youtubelaudio.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF3FB984),
    secondary = Color(0xFF2A9D8F),
    tertiary = Color(0xFF90E0EF),
    background = Color(0xFF0F1117),
    surface = Color(0xFF151923),
    surfaceVariant = Color(0xFF1E2230),
    onPrimary = Color(0xFF0F1117),
    onBackground = Color(0xFFEDEDED),
    onSurface = Color(0xFFEDEDED),
    onSurfaceVariant = Color(0xFFB9BCC3),
    outline = Color(0xFF3A3F4F)
)

@Composable
fun YoutubeAudioTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
