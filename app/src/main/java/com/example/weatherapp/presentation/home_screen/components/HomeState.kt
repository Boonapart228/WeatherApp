package com.example.weatherapp.presentation.home_screen.components

import android.location.Location
import com.example.weatherapp.R
import com.example.weatherapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val city: String = "",
    val inValidCity: Boolean = false,
    val supportingText: Int = R.string.empty,
    val currentLocation: Location? = null,
    val isTextFullyVisible: Boolean = false
)
