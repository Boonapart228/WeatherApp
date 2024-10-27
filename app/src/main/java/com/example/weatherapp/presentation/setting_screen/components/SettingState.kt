package com.example.weatherapp.presentation.setting_screen.components

import com.example.weatherapp.R
import com.example.weatherapp.presentation.navigation.model.Screens

data class SettingState(
    val selectedScreen: Screens = Screens.SETTING_SCREEN,
    val expanded: Boolean = false,
    val selectedLocaleId: Int = R.string.en,
)
