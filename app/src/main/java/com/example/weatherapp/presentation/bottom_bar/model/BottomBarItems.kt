package com.example.weatherapp.presentation.bottom_bar.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R
import com.example.weatherapp.presentation.navigation.model.Screens

enum class BottomBarItems(val route: Screens, val icon: ImageVector, val label: Int) {
    HOME(Screens.HOME_SCREEN, Icons.Default.Home, R.string.home_bottom_bar),
    SETTING(Screens.SETTING_SCREEN, Icons.Default.Settings, R.string.setting_bottom_bar)
}