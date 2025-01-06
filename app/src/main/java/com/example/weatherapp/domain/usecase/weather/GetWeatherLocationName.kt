package com.example.weatherapp.domain.usecase.weather

import com.example.weatherapp.domain.repository.WeatherStoreRepository

class GetWeatherLocationName(
  private val weatherStoreRepository: WeatherStoreRepository
) {
    fun execute() : String?{
    return weatherStoreRepository.getWeatherLocationName()
    }
}