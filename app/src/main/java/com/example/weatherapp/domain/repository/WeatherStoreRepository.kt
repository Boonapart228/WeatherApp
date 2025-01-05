package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel

interface WeatherStoreRepository {
    fun setWeatherResponse(networkResponse: NetworkResponse<WeatherModel>)
    fun getWeatherResponse() : NetworkResponse<WeatherModel>
}