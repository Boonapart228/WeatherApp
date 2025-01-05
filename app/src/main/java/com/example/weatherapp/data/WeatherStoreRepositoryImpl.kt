package com.example.weatherapp.data

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherStoreRepository

class WeatherStoreRepositoryImpl : WeatherStoreRepository {
    private var localeWeatherResponse : NetworkResponse<WeatherModel> = NetworkResponse.Default

    override fun setWeatherResponse(networkResponse: NetworkResponse<WeatherModel>) {
        localeWeatherResponse = networkResponse
    }

    override fun getWeatherResponse(): NetworkResponse<WeatherModel> {
        return localeWeatherResponse
    }
}