package com.example.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.home_screen.HomeScreen
import com.example.weatherapp.presentation.home_screen.components.HomeViewModel
import com.example.weatherapp.presentation.setting_screen.SettingScreen
import com.example.weatherapp.presentation.setting_screen.components.SettingViewModel


@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screens.HOME_SCREEN.route
    ) {
        composable(route = Screens.HOME_SCREEN.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel,
                navigationToSetting = { navController.navigate(Screens.SETTING_SCREEN.route) })
        }
        composable(route = Screens.SETTING_SCREEN.route) {
            val viewModel: SettingViewModel = hiltViewModel()
            SettingScreen(viewModel = viewModel,
                navigationToHome = { navController.navigate(Screens.HOME_SCREEN.route) })
        }
    }
}