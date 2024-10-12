package com.example.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.components.SettingContent
import com.example.weatherapp.presentation.weather_screen.WeatherScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screens.SETTING_SCREEN.route
    ) {
        composable(route = Screens.WEATHER_SCREEN.route) {
            WeatherScreen()
        }
        composable(route = Screens.SETTING_SCREEN.route) {
            SettingContent()
        }
    }
}