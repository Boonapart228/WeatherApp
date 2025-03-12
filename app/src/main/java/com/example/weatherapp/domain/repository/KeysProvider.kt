package com.example.weatherapp.domain.repository

interface KeysProvider {
    fun getWeatherApiKey() : String
}