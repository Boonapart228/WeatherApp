package com.example.weatherapp.data

import com.example.weatherapp.domain.models.NetworkResponse
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.KeysProvider
import com.example.weatherapp.domain.repository.UserSettings
import com.example.weatherapp.domain.repository.WeatherApiRepository
import com.example.weatherapp.domain.repository.WeatherDataValidator
import com.example.weatherapp.domain.repository.WeatherStoreRepository

class WeatherDataValidatorImpl(
    private val weatherApiRepository: WeatherApiRepository,
    private val userSettings: UserSettings,
    private val keysProvider: KeysProvider,
    private val weatherStoreRepository: WeatherStoreRepository
) : WeatherDataValidator {

    private fun setWeatherResponse(networkResponse: NetworkResponse<WeatherModel>) {
        weatherStoreRepository.setWeatherResponse(networkResponse)
    }

    private fun setWeatherLocationName(locationName: String) {
        weatherStoreRepository.setWeatherLocationName(locationName)
    }

    override suspend fun getWeatherByLocation(location: String): NetworkResponse<WeatherModel> {
        return try {
            val response = weatherApiRepository.getDataByQuery(
                query = location,
                languageCode = userSettings.getLanguage().languageCode,
                apikey = keysProvider.getWeatherApiKey()
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    setWeatherResponse(NetworkResponse.Success(it))
                    setWeatherLocationName(it.location.name)
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
                languageCode = userSettings.getLanguage().languageCode,
                apikey = keysProvider.getWeatherApiKey()
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    setWeatherResponse(NetworkResponse.Success(it))
                    setWeatherLocationName(it.location.name)
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