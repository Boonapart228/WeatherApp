package com.example.weatherapp.domain.usecase.weather

import com.example.weatherapp.data.api.Constant
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Response

class GetDataByQueryUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend fun execute(
        apiKey: String = Constant.API_KEY,
        query: String,
        languageCode: String
    ): Response<WeatherModel> {
        return weatherApiRepository.getDataByQuery(apiKey, query, languageCode)
    }
}