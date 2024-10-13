package com.example.weatherapp.presentation.home_screen.components

sealed class HomeNavigationEvent {
    data object NavigationToSetting : HomeNavigationEvent()
}