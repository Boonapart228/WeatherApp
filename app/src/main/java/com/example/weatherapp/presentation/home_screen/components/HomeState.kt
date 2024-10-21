package com.example.weatherapp.presentation.home_screen.components

import com.example.weatherapp.R
import com.example.weatherapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val city: String = "",
    val inValidCity: Boolean = false,
    val supportingText: Int = R.string.city_no_digits
)
