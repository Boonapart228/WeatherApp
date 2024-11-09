package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel

interface WeatherDataValidator {
    suspend fun getWeatherByLocation(location: String): NetworkResponse<WeatherModel>
    suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherModel>
    suspend fun handleInvalidCityFormat(): NetworkResponse<WeatherModel>
}