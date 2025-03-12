package com.example.weatherapp.presentation.home_screen.model

sealed class PermissionEvent {
    object LocationPermissionEvent : PermissionEvent()
}