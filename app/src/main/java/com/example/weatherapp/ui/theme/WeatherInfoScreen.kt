package com.example.weatherapp.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Air
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.WeatherMetricItem
import com.example.weatherapp.WeatherViewModel
import com.example.weatherapp.model.WeatherResponse
import java.time.LocalTime

// Util function to get gradient background
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
        else -> Brush.verticalGradient(
            colors = listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherInfoScreen(
    weather: WeatherResponse?,
    modifier: Modifier = Modifier
) {
    val gradient = weather?.weather?.getOrNull(0)?.main?.let { getWeatherGradient(it) }
        ?: Brush.verticalGradient(listOf(Color.Blue, Color.Cyan))

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 16.dp)
    ) {
        weather?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                val iconUrl = "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
                AsyncImage(
                    model = iconUrl,
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(128.dp)
                )
                Text(
                    text = "${it.main.temp}°C",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White
                )
                Text(
                    text = it.weather[0].description.replaceFirstChar { c -> c.uppercaseChar() },
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.9f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "City: ${it.name}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        WeatherMetricItem(
                            icon = Icons.Default.WaterDrop,
                            label = "Humidity",
                            value = "${it.main.humidity}%",
                            iconSize = 36.dp,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                        WeatherMetricItem(
                            icon = Icons.Default.Air,
                            label = "Wind",
                            value = "${it.wind.speed} km/h",
                            iconSize = 36.dp,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        } ?: run {
            Text("No weather data available", color = Color.White)
        }
    }
}



