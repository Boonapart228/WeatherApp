package com.example.weatherapp.data

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.domain.repository.WeatherApiRepository
import com.example.weatherapp.domain.repository.WeatherDataValidator

class WeatherDataValidatorImpl(
    private val weatherApiRepository: WeatherApiRepository,
    private val userSettings: UserSettings
) : WeatherDataValidator {
    override suspend fun getWeatherByLocation(location: String): NetworkResponse<WeatherModel> {
        return try {
            val response = weatherApiRepository.getDataByQuery(
                query = location,
                languageCode = userSettings.getLanguage().languageCode
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResponse.Success(it)
                } ?: NetworkResponse.Error("No data found")
            } else {
                NetworkResponse.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResponse.Error("Exception: ${e.message}")
        }
    }

    override suspend fun getWeatherByCity(city: String): NetworkResponse<WeatherModel> {
        return try {
            val response = weatherApiRepository.getDataByQuery(
                query = city,
                languageCode = userSettings.getLanguage().languageCode
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResponse.Success(it)
                } ?: run {
                    NetworkResponse.Error("No data found")
                }
            } else {
                NetworkResponse.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResponse.Error("Exception: ${e.message}")
        }
    }

    override suspend fun handleInvalidCityFormat(): NetworkResponse<WeatherModel> {
        return NetworkResponse.Error("In valid city")
    }
}