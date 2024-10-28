package com.example.weatherapp.data

import com.example.weatherapp.data.api.RetrofitInstance
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Response

class WeatherApiRepositoryImpl : WeatherApiRepository {
    private val weatherApi = RetrofitInstance.weatherApiRepository

    override suspend fun getDataByCity(
        apikey: String,
        city: String,
        languageCode: String
    ): Response<WeatherModel> {
        return weatherApi.getDataByCity(city = city, languageCode = languageCode)
    }
}