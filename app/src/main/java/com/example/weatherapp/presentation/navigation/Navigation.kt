package com.example.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.home_screen.HomeScreen
import com.example.weatherapp.presentation.home_screen.components.HomeViewModel
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.SettingScreen
import com.example.weatherapp.presentation.setting_screen.components.SettingViewModel
import com.example.weatherapp.ui.theme.AppTheme


@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    val settingViewModel: SettingViewModel = hiltViewModel()
    val fontSizePrefs by settingViewModel.state.collectAsState()
    AppTheme(
        dynamicColor = false,
        fontSizePrefs = fontSizePrefs.fontSizePrefs,
        getPersonalizedTypography = settingViewModel::getPersonalizedTypography
    ) {
        NavigationHost(
            navController = navController,
            settingViewModel = settingViewModel
        )
    }
}

@Composable
private fun NavigationHost(
    navController: NavHostController,
    settingViewModel: SettingViewModel
) {
    NavHost(navController = navController, startDestination = Screens.HOME_SCREEN.route) {
        composable(route = Screens.HOME_SCREEN.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel,
                navigationToSetting = { navController.navigate(Screens.SETTING_SCREEN.route) })
        }
        composable(route = Screens.SETTING_SCREEN.route) {
            SettingScreen(viewModel = settingViewModel,
                navigationToHome = { navController.navigate(Screens.HOME_SCREEN.route) })
        }
    }
}