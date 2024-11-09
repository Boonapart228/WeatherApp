package com.example.weatherapp.domain.usecase.weather

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherDataValidator

class GetWeatherByCityUseCase(
    private val weatherDataValidator: WeatherDataValidator
) {
    suspend fun execute(city: String): NetworkResponse<WeatherModel> {
        return weatherDataValidator.getWeatherByCity(city)
    }
}