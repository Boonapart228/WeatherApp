package com.example.weatherapp.domain.usecase.weather_validator

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherDataValidator

class HandleInvalidCityFormatUseCase(
    private val weatherDataValidator: WeatherDataValidator
) {
    suspend fun execute(): NetworkResponse<WeatherModel> {
       return weatherDataValidator.handleInvalidCityFormat()
    }
}