package com.example.weatherapp.data

import com.example.weatherapp.data.api.RetrofitInstance
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Response

class WeatherApiRepositoryImpl : WeatherApiRepository {
    private val weatherApi = RetrofitInstance.weatherApiRepository
    override suspend fun getDataByQuery(
        apikey: String,
        query: String,
        languageCode: String,
        days : Int
    ): Response<WeatherModel> {
        return weatherApi.getDataByQuery(
            query = query,
            languageCode = languageCode,
            apikey = apikey,
            days = days
        )
    }
}