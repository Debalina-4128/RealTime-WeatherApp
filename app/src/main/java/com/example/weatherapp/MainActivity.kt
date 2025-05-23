package com.example.weatherapp

import android.os.Build
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.ui.theme.WeatherAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.CityInputScreen
import com.example.weatherapp.ui.theme.SplashScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.WeatherInfoScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val weatherViewModel: WeatherViewModel = viewModel()
                val weatherData by weatherViewModel.weather.collectAsState()

                AppNavHost(
                    navController = navController,
                    weather = weatherData,
                    weatherViewModel = weatherViewModel
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    weather: WeatherResponse?,
    weatherViewModel: WeatherViewModel
) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                navController = navController
            )
        }

        composable("cityInput") {
            CityInputScreen(
                navController = navController
            )
        }

        composable("weather_info/{cityName}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""

            LaunchedEffect(cityName) {
                weatherViewModel.fetchWeather(
                    cityName,
                    apiKey = "6d81d5917adab657390e64c561eefe5a"
                )
            }

            WeatherInfoScreen(
                weather = weatherViewModel.weather.collectAsState().value,
                modifier = Modifier
            )

        }

    }
}





