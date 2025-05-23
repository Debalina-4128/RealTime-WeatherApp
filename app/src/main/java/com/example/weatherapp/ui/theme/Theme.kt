package com.example.weatherapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.ui.theme.*

private val LightWeatherColorScheme = lightColorScheme(
    primary = SkyBlue,
    secondary = Teal,
    tertiary = SunnyYellow,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onTertiary = DarkGray,
    onBackground = DarkGray,
    onSurface = DarkGray
)

private val DarkWeatherColorScheme = darkColorScheme(
    primary = DeepBlue,
    secondary = Teal,
    tertiary = SunnyYellow,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkWeatherColorScheme
        else -> LightWeatherColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
