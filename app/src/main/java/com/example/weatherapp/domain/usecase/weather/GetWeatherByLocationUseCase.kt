package com.example.weatherapp.domain.usecase.weather

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherDataValidator

class GetWeatherByLocationUseCase(
    private val weatherDataValidator: WeatherDataValidator
) {
    suspend fun execute(location : String) : NetworkResponse<WeatherModel> {
        return weatherDataValidator.getWeatherByLocation(location)
    }
}