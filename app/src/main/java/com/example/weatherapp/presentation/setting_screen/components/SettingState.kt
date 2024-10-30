package com.example.weatherapp.presentation.setting_screen.components

import com.example.weatherapp.R
import com.example.weatherapp.presentation.navigation.model.Screens
import com.example.weatherapp.presentation.setting_screen.model.FontSizePrefs

data class SettingState(
    val selectedScreen: Screens = Screens.SETTING_SCREEN,
    val expandedLanguageMenu: Boolean = false,
    val expandedFontSizeMenu: Boolean = false,
    val selectedLocaleId: Int = R.string.en,
    val selectedFontSize: Int = R.string.font_size_m,
    val selectedFontSizeCode: Int = R.string.time,
    val currentFontSizeIndex: Int = 0,
    val fontSizePrefs: FontSizePrefs = FontSizePrefs.DEFAULT
)
