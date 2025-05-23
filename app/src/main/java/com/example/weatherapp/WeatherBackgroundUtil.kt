package com.example.weatherapp

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getWeatherGradient(condition: String): Brush {
    return when (condition.lowercase()) {
        "clear", "sunny" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFfceabb), Color(0xFFf8b500))
        )
        "clouds", "cloudy" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFbdc3c7), Color(0xFF2c3e50))
        )
        "rain", "drizzle" -> Brush.verticalGradient(
            colors = listOf(Color(0xFF4e54c8), Color(0xFF8f94fb))
        )
        "thunderstorm" -> Brush.verticalGradient(
            colors = listOf(Color(0xFF232526), Color(0xFF414345))
        )
        "snow" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFe0eafc), Color(0xFFcfdef3))
        )
        "mist", "fog", "haze" -> Brush.verticalGradient(
            colors = listOf(Color(0xFF757F9A), Color(0xFFD7DDE8))
        )
        "night" -> Brush.verticalGradient(
            colors = listOf(Color(0xFF141E30), Color(0xFF243B55))
        )
        else -> Brush.verticalGradient( // fallback
            colors = listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
        )
    }
}
