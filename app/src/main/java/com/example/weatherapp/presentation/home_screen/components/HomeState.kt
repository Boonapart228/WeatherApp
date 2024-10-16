package com.example.weatherapp.presentation.home_screen.components

import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val city: String = "",
    val cityResult: String = "",
    val wModel: WeatherModel? = null
)
