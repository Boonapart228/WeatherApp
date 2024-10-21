package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.api.Constant
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherApiRepository
import retrofit2.Response

class GetDataByCityUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend fun execute(apiKey: String = Constant.API_KEY, city: String): Response<WeatherModel> {
        return weatherApiRepository.getDataByCity(apiKey, city)
    }
}